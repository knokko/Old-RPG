package nl.knokko.rpg.gui;

import java.awt.*;

import nl.knokko.rpg.entities.players.Player;
import nl.knokko.rpg.inventory.InventoryShop;
import nl.knokko.rpg.inventory.ItemSlot;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.Item;
import nl.knokko.rpg.items.ItemWeapon;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.toolbars.ToolBar;
import nl.knokko.rpg.utils.PointUtils;
import nl.knokko.rpg.utils.Resources;

public class GuiShop extends Gui {
	
	public final Player player;
	public final InventoryShop shop;
	public Image weaponSlot;
	public final Image money;
	public ToolBar toolbar;
	
	public GuiShop(Game theGame, Player buyer, InventoryShop theShop) {
		super(theGame, "sprites/gui/shop.png");
		addButton(400, 700, 650, 800, "next player");
		addButton(1000, 700, 1150, 800, "leave");
		money = Resources.getImage("sprites/money.png");
		weaponSlot = Resources.getImage("sprites/buttons/weapon slot.png");
		player = buyer;
		player.refreshModels();
		shop = theShop;
	}
	
	@Override
	public void paint(Graphics gr){
		super.paint(gr);
		player.inventory.paint(gr, new Point(factorX(50), factorY(50)));
		shop.paint(gr, new Point(factorX(1000), factorY(50)));
		gr.drawImage(money, factorX(50), factorY(700), factorX(64), factorY(64), null);
		gr.setColor(Color.BLACK);
		gr.setFont(new Font("TimesRoman", 0, factorX(40)));
		gr.drawString("money = " + game.player.inventory.money, factorX(150), factorY(750));
		Point mouse = game.getMousePosition();
		if(mouse.x >= factorX(550) && mouse.x <= factorX(580) && mouse.y >= factorY(50) && mouse.y <= factorY(80)){
			weaponSlot = Resources.getImage("sprites/buttons/weapon slot_active.png");
		}
		else {
			weaponSlot = Resources.getImage("sprites/buttons/weapon slot.png");
		}
		gr.drawImage(weaponSlot, factorX(549), factorY(49), factorX(32), factorY(32), null);
		if(player.weapon != null){
			player.weapon.paint(gr, new Point(factorX(550), factorY(50)), new Point(factorX(580), factorY(80)));
		}
		gr.drawImage(Resources.getImage(player.getTexture() + "_behind.png"), factorX(550), factorY(200), factorX(480), factorY(480), null);
		if(player.getName().matches("Bart"))
			gr.drawImage(Resources.getImage("sprites/entities/player/hair behind.png"), factorX(550), factorY(200), factorX(480), factorY(480), null);
		else if(player.getName().matches("Richard"))
			gr.drawImage(Resources.getImage("sprites/entities/player/hair richard behind.png"), factorX(550), factorY(200), factorX(480), factorY(480), null);
		byte r = player.rotation;
		player.rotation = 1;
		int t = 0;
		while(t < player.armor.length){
			if(player.armor[t] != null){
				player.armor[t].drawOnEntity(gr, player, factorX(480), factorY(480), new Point(factorX(550), factorY(200)));
			}
			++t;
		}
		player.rotation = r;
		if(toolbar != null){
			toolbar.paint(gr);
		}
	}
	
	@Override
	public void buttonClicked(Button button){
		if(button.name.matches("leave")){
			game.currentGUI = null;
			if(game.controller.mouseItemStack != null){
				if(player.inventory.addItemStack(game.controller.mouseItemStack)){
					game.controller.mouseItemStack = null;
				}
			}
		}
		if(button.name.matches("next player")){
			game.currentGUI = new GuiShop(game, game.nextPlayer(player), shop);
		}
	}
	
	@Override
	public void click(){
		super.click();
		Point mouse = game.getMousePosition();
		ItemStack stack = game.controller.mouseItemStack;
		ItemSlot slot = player.inventory.getSlot(new Point(50, 50), mouse);
		if(slot != null){
			game.controller.mouseItemStack = slot.addItemStack(stack);
		}
		if(mouse.x >= factorX(550) && mouse.x <= factorX(580) && mouse.y >= factorY(50) && mouse.y <= factorY(80)){
			if(stack == null && player.weapon != null){
				game.controller.mouseItemStack = player.weapon;
				player.weapon = null;
			}
			if(stack != null && stack.item instanceof ItemWeapon){
				game.controller.mouseItemStack = player.weapon;
				player.weapon = stack;
			}
		}
		Item item = shop.getItem(new Point(1000, 50), mouse);
		if(item != null && game.player.inventory.money >= item.value){
			if(stack == null){
				game.controller.mouseItemStack = new ItemStack(item, 1);
				game.player.inventory.money -= item.value;
			}
			else if(stack.item == item){
				++game.controller.mouseItemStack.size;
				game.player.inventory.money -= item.value;
			}
		}
	}
	
	@Override
	public void rightClick(Point mouse){
		mouse = PointUtils.mouseToScreenPoint(mouse);
		ItemStack s = game.controller.mouseItemStack;
		if(s != null && mouse.x >= factorX(1000) && mouse.y >= factorY(50) && mouse.x <= shop.xSize * factorX(32) + factorX(1000) && mouse.y <= shop.ySize * factorY(32) + factorY(50)){
			--s.size;
			game.player.inventory.money += s.item.value;
			if(s.size <= 0){
				game.controller.mouseItemStack = null;
			}
		}
	}
	
	@Override
	public void update(){
		Point mouse = game.getMousePosition();
		ItemSlot slot = player.inventory.getSlot(new Point(50, 50), mouse);
		toolbar = null;
		if(slot == null){
			Item item = shop.getItem(new Point(1000, 50), mouse);
			if(item != null){
				toolbar = item.getToolBar();
				if(toolbar != null){
					toolbar.min = mouse;
				}
			}
		}
		if(slot != null && slot.stack != null){
			toolbar = slot.stack.item.getToolBar();
			if(toolbar != null){
				toolbar.min = mouse;
			}
		}
		if(mouse.x >= factorX(550) && mouse.x <= factorX(580) && mouse.y >= factorY(50) && mouse.y <= factorY(80) && player.weapon != null){
			toolbar = player.weapon.item.getToolBar();
			toolbar.min = mouse;
		}
	}
	
	@Override
	public Cursor getCursor(){
		return Resources.getCursor("hand");
	}
}
