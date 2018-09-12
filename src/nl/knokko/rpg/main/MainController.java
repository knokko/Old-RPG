package nl.knokko.rpg.main;

import java.awt.Point;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.gui.GuiChest;
import nl.knokko.rpg.gui.GuiMenu;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.tiles.Chest;
import nl.knokko.rpg.tiles.Tiles;
import nl.knokko.rpg.world.Shop;

public final class MainController {
	
	public static ItemStack mouseItemStack;
	
	public static byte timeOut;
	
	public static void update(){
		while(Mouse.next()){
			if(MainClass.currentGUI != null){
				if(Mouse.getEventButtonState()){
					
				}
				else {
					if(Mouse.getEventButton() == 0)
						MainClass.currentGUI.click(new Vector2f(((float)Mouse.getEventX() / Display.getWidth() - 0.5f) * 2, ((float)Mouse.getEventY() / Display.getHeight() - 0.5f) * 2));
					if(Mouse.getEventButton() == 1)
						MainClass.currentGUI.rightClick(new Vector2f(((float)Mouse.getEventX() / Display.getWidth() - 0.5f) * 2, ((float)Mouse.getEventY() / Display.getHeight() - 0.5f) * 2));
				}
			}
		}
		while(Keyboard.next()){
			if(Keyboard.getEventKeyState()){
				if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE){
					if(MainClass.currentGUI == null)
						MainClass.currentGUI = new GuiMenu();
					else
						MainClass.currentGUI.escapePressed();
					return;
				}
				if(MainClass.currentGUI != null)
					MainClass.currentGUI.keyTyped(Keyboard.getEventCharacter());
			}
			else {
				
			}
		}
		if(MainClass.currentGUI == null){
			if(Keyboard.isKeyDown(Keyboard.KEY_UP))
				MainClass.player.move(0, -30);
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
				MainClass.player.move(0, 30);
			if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
				MainClass.player.move(-30, 0);
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
				MainClass.player.move(30, 0);
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
				byte r = MainClass.player.rotation;
				Point target = new Point(MainClass.player.position);
				Chest chest = null;
				if(r == 0)
					target.x += 30;
				else if(r == 1)
					target.y -= 30;
				else if(r == 2)
					target.x -= 30;
				else 
					target.y += 30;
				if(MainClass.world.getTile(target.x, target.y, false).id == Tiles.altar.id)
					MainClass.healPlayers();
				int t = 0;
				while(t < MainClass.world.chests.size()){
					Point point = MainClass.world.chests.get(t).position;
					if(target.equals(point)){
						chest = MainClass.world.chests.get(t);
						t = MainClass.world.chests.size();
					}
					++t;
				}
				if(chest != null){
					MainClass.currentGUI = new GuiChest("sprites/gui/chest.png", chest, MainClass.player);
					chest.open();
				}
				t = 0;
				while(t < MainClass.world.shops.size()){
					Shop shop = MainClass.world.shops.get(t);
					if(target.equals(shop.position)){
						shop.open();
					}
					++t;
				}
				t = 0;
				while(t < MainClass.world.entities.size()){
					Entity entity = MainClass.world.entities.get(t);
					if(entity != null && target.equals(entity.position))
						entity.interact();
					++t;
				}
			}
		}
		else {
			MainClass.currentGUI.keyPressed();
		}
		if(timeOut > 0)
			--timeOut;
	}
}
