package nl.knokko.rpg.gui;

import java.awt.*;
import java.util.ArrayList;

import nl.knokko.rpg.entities.players.Player;
import nl.knokko.rpg.inventory.Inventory;
import nl.knokko.rpg.inventory.ItemSlot;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.tiles.Chest;
import nl.knokko.rpg.toolbars.ToolBar;
import nl.knokko.rpg.utils.PointUtils;
import nl.knokko.rpg.utils.Resources;

public class GuiChest extends Gui {
	
	public final Chest chest;
	public final Image money;
	public final Player player;
	
	public ToolBar toolbar;
	
	public GuiChest(Game theGame, String texture, Chest theChest, Player thePlayer) {
		super(theGame, texture);
		chest = theChest;
		player = thePlayer;
		addButton(1000, 100, 1200, 200, "close");
		addButton(1000, 250, 1250, 350, "take money");
		addButton(1000, 400, 1200, 500, "take all");
		money = Resources.getImage("sprites/money.png");
		int t = 0;
		while(t < chest.specialItems.size()){
			game.specialItems.addItem(chest.specialItems.get(t));
			++t;
		}
	}
	
	@Override
	public void click(){
		super.click();
		Point mouse = game.getMousePosition();
		ItemStack stack = game.controller.mouseItemStack;
		ItemSlot slot = player.inventory.getSlot(new Point(50, 150), mouse);
		if(slot == null)
			slot = chest.inventory.getSlot(new Point(50, 30), mouse);
		if(slot != null)
			game.controller.mouseItemStack = slot.addItemStack(stack);
	}
	
	@Override
	public void paint(Graphics gr){
		gr.setColor(Color.RED);
		gr.fillRect(0, 0, game.getWidth(), game.getHeight());
		super.paint(gr);
		chest.inventory.paint(gr, new Point(factorX(50), factorY(30)));
		player.inventory.paint(gr, new Point(factorX(50), factorY(150)));
		int t = 0;
		ArrayList<String> s = chest.specialItems;
		while(t < s.size()){
			if(s.get(t).contains("blue print"))
				GuiBluePrints.fromString(s.get(t)).paint(gr, factorX(1400), factorY(100 + 200 * t), factorX(120), factorY(120), true);
			else {
				Image image = Resources.getImage("sprites/special items/" + s.get(t) + ".png");
				gr.drawImage(image, factorX(1400), factorY(100 + 200 * t), factorX(120), factorY(120), null);
				gr.setFont(new Font("TimesRoman", 1, factorX(30)));
				gr.setColor(Color.YELLOW);
				gr.drawString(s.get(t), factorX(1400), factorY(240 + 200 * t));
			}
			++t;
		}
		gr.drawImage(money, factorX(600), factorY(50), factorX(64), factorY(64), null);
		gr.drawImage(money, factorX(600), factorY(700), factorX(64), factorY(64), null);
		gr.setColor(Color.BLACK);
		gr.setFont(font());
		gr.drawString("money = " + game.player.inventory.money, factorX(700), factorY(750));
		gr.drawString("money = " + chest.inventory.money, factorX(700), factorY(100));
		if(toolbar != null){
			toolbar.paint(gr);
		}
	}
	
	@Override
	public void buttonClicked(Button button){
		if(button.name.matches("close")){
			escapePressed();
		}
		if(button.name.matches("take money")){
			game.player.inventory.money += chest.inventory.money;
			chest.inventory.money = 0;
		}
		if(button.name.matches("take all")){
			int x = 0;
			Inventory i = chest.inventory;
			while(x < i.sizeX){
				int y = 0;
				while(y < i.sizeY){
					ItemStack stack = i.itemSlots[x][y].stack;
					if(stack != null && player.inventory.addItemStack(stack)){
						i.itemSlots[x][y].stack = null;
					}
					++y;
				}
				++x;
			}
			game.player.inventory.money += i.money;
			i.money = 0;
		}
	}
	
	@Override
	public void rightClick(Point mouse){
		mouse = PointUtils.mouseToScreenPoint(mouse);
		ItemSlot slot = player.inventory.getSlot(new Point(50, 150), mouse);
		if(slot == null){
			slot = chest.inventory.getSlot(new Point(50, 30), mouse);
		}
		if(slot != null){
			ItemStack stack = slot.stack;
			ItemStack stack2 = game.controller.mouseItemStack;
			if(stack == null && stack2 != null){
				slot.stack = new ItemStack(stack2.item, 1);
				--stack2.size;
				if(stack2.size <= 0){
					game.controller.mouseItemStack = null;
				}
			}
			if(stack != null && stack2 != null && stack.item == stack2.item){
				++stack.size;
				--stack2.size;
				if(stack2.size <= 0){
					game.controller.mouseItemStack = null;
				}
			}
		}
	}
	
	@Override
	public void update(){
		Point mouse = game.getMousePosition();
		ItemSlot slot = player.inventory.getSlot(new Point(50, 150), mouse);
		if(slot == null){
			slot = chest.inventory.getSlot(new Point(50, 30), mouse);
		}
		if(slot != null && slot.stack != null){
			toolbar = slot.stack.item.getToolBar();
			if(toolbar != null){
				toolbar.min = mouse;
			}
		}
		else {
			toolbar = null;
		}
	}
	
	@Override
	public void escapePressed(){
		game.currentGUI = null;
		if(game.controller.mouseItemStack != null){
			if(player.inventory.addItemStack(game.controller.mouseItemStack)){
				game.controller.mouseItemStack = null;
			}
		}
		chest.specialItems.clear();
	}
	
	@Override
	public Cursor getCursor(){
		return Resources.getCursor("hand");
	}
}
