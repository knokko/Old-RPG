package nl.knokko.rpg.main;

import java.awt.*;
import java.awt.event.*;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.players.Player;
import nl.knokko.rpg.gui.*;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.tiles.Chest;
import nl.knokko.rpg.tiles.Tiles;
import nl.knokko.rpg.utils.Resources;
import nl.knokko.rpg.world.Shop;

public class Controller implements KeyListener, MouseListener, MouseMotionListener{
	
	public final Game game;
	public final Player player;
	public ItemStack mouseItemStack;
	
	public boolean[] pressedKeys = new boolean[300];
	public byte timeOut;
	
	public Controller(Game theGame) {
		game = theGame;
		player = game.player;
	}

	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		if(game.currentGUI != null){
			if(e.getButton() == 1){
				game.currentGUI.click();
			}
			if(e.getButton() == 3){
				game.currentGUI.rightClick(e.getPoint());
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {	
	}

	public void keyTyped(KeyEvent event) {
		if(game.currentGUI != null)
			game.currentGUI.keyTyped(event.getKeyChar());
	}

	public void keyPressed(KeyEvent e) {
		if(timeOut > 0)
			return;
		pressedKeys[e.getKeyCode()] = true;
		if(!Resources.hasStarted())
			return;
		char c = e.getKeyChar();
		if(c == 't'){
			System.out.println(game.player.position);
		}
		boolean b = game.currentGUI == null || game.currentGUI.canClose();
		if(c == 'i' && b)
			game.currentGUI = new GuiPlayerInventory(game, player);
		else if(c == 'o'){
			if(b)
				game.currentGUI = new GuiOptions(game);
			else if(game.currentGUI instanceof GuiBattle)
				game.currentGUI = ((GuiBattle)game.currentGUI).new GuiBattleOptions();
		}
		else if(c == 's' && b)
			game.currentGUI = new GuiSpellTree(game, player);
		else if(c == 'u' && b)
			game.currentGUI = new GuiStatsUpgrade(game, player);
		else if(c == 'e' && b)
			game.currentGUI = new GuiElementStats(game, player);
		else if(c == 'c' && b)
			game.currentGUI = new GuiCharacterInfo(game, player);
		else if(c == 'q' && b)
			game.currentGUI = new GuiQuests(game);
		else if(c == 'h' && b)
			game.currentGUI = new GuiHelpMenu(game);
		else if(c == 'm' && b)
			game.currentGUI = new GuiAreaMap(game, game.world);
	}

	public void keyReleased(KeyEvent e) {
		pressedKeys[e.getKeyCode()] = false;
		if(timeOut > 0)
			return;
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			if(game.currentGUI == null)
				game.currentGUI = new GuiMenu(game);
			else
				game.currentGUI.escapePressed();
		}
		if(e.getKeyChar() == ' '){
			byte r = player.rotation;
			Point target = new Point(player.position);
			Chest chest = null;
			if(r == 0)
				target.x += 30;
			else if(r == 1)
				target.y -= 30;
			else if(r == 2)
				target.x -= 30;
			else 
				target.y += 30;
			if(game.world.getTile(target.x, target.y, false).id == Tiles.altar.id)
				game.healPlayers();
			int t = 0;
			while(t < game.world.chests.size()){
				Point point = game.world.chests.get(t).position;
				if(target.equals(point)){
					chest = game.world.chests.get(t);
					t = game.world.chests.size();
				}
				++t;
			}
			if(chest != null){
				game.currentGUI = new GuiChest(game, "sprites/gui/chest.png", chest, player);
				chest.open();
			}
			t = 0;
			while(t < game.world.shops.size()){
				Shop shop = game.world.shops.get(t);
				if(target.equals(shop.position)){
					shop.open();
				}
				++t;
			}
			t = 0;
			while(t < game.world.entities.size()){
				Entity entity = game.world.entities.get(t);
				if(entity != null && target.equals(entity.position))
					entity.interact();
				++t;
			}
		}
	}
	
	public void update(){
		if(game.currentGUI == null){
			if(pressedKeys[37]){
				player.move(-30, 0);
			}
			if(pressedKeys[39]){
				player.move(30, 0);
			}
			if(pressedKeys[38]){
				player.move(0, -30);
			}
			if(pressedKeys[40]){
				player.move(0, 30);
			}
		}
		else
			game.currentGUI.keyPressed(pressedKeys);
		if(timeOut > 0)
			--timeOut;
	}

	public void mouseDragged(MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {}
	
	public void paint(Graphics gr){
		if(mouseItemStack != null){
			Point p = game.getMousePosition();
			mouseItemStack.paint(gr, p, new Point(p.x + (30), p.y + (30)));
		}
	}
}
