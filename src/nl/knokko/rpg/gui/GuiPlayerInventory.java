package nl.knokko.rpg.gui;

import java.awt.*;

import org.lwjgl.util.vector.Vector2f;

import nl.knokko.rpg.entities.players.Player;
import nl.knokko.rpg.inventory.ItemSlot;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.*;
import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.main.MainController;
import nl.knokko.rpg.toolbars.ToolBar;
import nl.knokko.rpg.utils.Resources;

public class GuiPlayerInventory extends Gui {
	
	public Image money;
	public Image weaponSlot;
	
	public final Player player;
	
	public ToolBar toolbar;
	
	public GuiPlayerInventory(Player owner) {
		super(null);
		addButton(1200, 200, 1320, 300, "back");
		addButton(1200, 350, 1450, 450, "next player");
		addButton(1200, 500, 1470, 600, "special items");
		money = Resources.getImage("sprites/money.png");
		weaponSlot = Resources.getImage("sprites/buttons/weapon slot.png");
		if(!(this instanceof GuiAnvil))
			buttons.add(new Button(1000, 100, 1200, 200, "", "use potion", normalFont, Color.BLUE));
		player = owner;
		player.refreshModels();
	}
	
	/*
	@Override
	public void paint(Graphics gr){
		gr.setColor(Color.GREEN);
		gr.fillRect(0, 0, MainClass.getWidth(), MainClass.getHeight());
		super.paint(gr);
		player.inventory.paint(gr, new Point(factorX(50), factorY(50)));
		gr.drawImage(money, factorX(50), factorY(700), factorX(64), factorY(64), null);
		gr.setColor(Color.BLACK);
		gr.setFont(new Font("TimesRoman", 0, factorX(40)));
		gr.drawString("money = " + MainClass.player.inventory.money, factorX(150), factorY(750));
		Point mouse = MainClass.getMousePosition();
		if(mouse.x >= factorX(549) && mouse.x <= factorX(581) && mouse.y >= factorY(49) && mouse.y <= factorY(81)){
			weaponSlot = Resources.getImage("sprites/buttons/weapon slot_active.png");
		}
		else if(player.weapon == null){
			weaponSlot = Resources.getImage("sprites/buttons/weapon slot.png");
		}
		else {
			weaponSlot = Resources.getImage("sprites/buttons/weapon slot_empty.png");
		}
		gr.drawImage(weaponSlot, factorX(549), factorY(49), factorX(32), factorY(32), null);
		int t = 1;
		while(t < 5){
			gr.setColor(new Color(100, 50, 0));
			Point min = new Point(factorX(549), factorY(49 + 32 * t));
			Point max = new Point(factorX(581), factorY(49 + 32 + 32 * t));
			if(mouse.x >= min.x && mouse.x < max.x && mouse.y >= min.y && mouse.y < max.y){
				gr.setColor(Color.BLUE);
			}
			gr.fillRect(min.x, min.y, factorX(32), factorY(32));
			if(player.armor[t - 1] != null){
				gr.drawImage(player.armor[t - 1].image, min.x, min.y, factorX(30), factorY(30), null);
			}
			gr.setColor(Color.YELLOW);
			gr.drawRect(min.x - 1, min.y - 1, factorX(33), factorY(33));
			++t;
		}
		if(player.weapon != null){
			player.weapon.paint(gr, new Point(factorX(550), factorY(50)), new Point(factorX(580), factorY(80)));
		}
		gr.setFont(font());
		gr.setColor(new Color(0, 100, 0));
		gr.drawString("health: " + player.currentHealth + " / " + player.maxHealth, factorX(800), factorY(250));
		gr.setColor(Color.BLUE);
		gr.drawString("mana: " + player.currentMana + " / " + player.maxMana, factorX(800), factorY(300));
		gr.setColor(Color.GRAY);
		gr.drawString("armor: " + player.getArmor(new Attack(Element.NORMAL, false, false)), factorX(800), factorY(350));
		gr.setColor(new Color(100, 0, 100));
		gr.drawString("magic armor: " + player.getArmor(new Attack(Element.NORMAL, true, true)), factorX(800), factorY(400));
		drawEntity(gr, player, new Point(factorX(800), factorY(400)));
		if(toolbar != null){
			toolbar.paint(gr);
		}
	}
	*/
	
	@Override
	public void buttonClicked(Button button){
		if(button.name.matches("back")){
			MainClass.currentGUI = new GuiMenu();
			if(MainController.mouseItemStack != null){
				if(player.inventory.addItemStack(MainController.mouseItemStack)){
					MainController.mouseItemStack = null;
				}
			}
		}
		if(button.name.matches("use potion")){
			ItemStack stack = MainController.mouseItemStack;
			if(stack != null && stack.item instanceof ItemConsumable){
				if(((ItemConsumable)stack.item).consume(player, stack)){
					--stack.size;
					if(stack.size <= 0){
						MainController.mouseItemStack = null;
					}
				}
			}
		}
		if(button.name.matches("next player"))
			MainClass.currentGUI = new GuiPlayerInventory(MainClass.nextPlayer(player));
		if(button.name.matches("special items"))
			MainClass.currentGUI = new GuiSpecialItems();
	}
	
	@Override
	public void click(Vector2f mouse){
		super.click(mouse);
		ItemStack stack = MainController.mouseItemStack;
		ItemSlot slot = player.inventory.getSlot(new Vector2f(0.03125f, 0.05556f), mouse);
		if(slot != null){
			MainController.mouseItemStack = slot.addItemStack(stack);
		}
		if(mouse.x >= factorX(549) && mouse.x <= factorX(581)){
			if(mouse.y >= factorY(49) && mouse.y <= factorY(81)){
				if(stack == null && player.weapon != null){
					MainController.mouseItemStack = player.weapon;
					player.weapon = null;
				}
				if(stack != null && stack.item instanceof ItemWeapon){
					MainController.mouseItemStack = player.weapon;
					player.weapon = stack;
				}
			}
			if(mouse.y >= factorY(82) && mouse.y <= factorY(114)){
				if(stack == null && player.armor[0] != null){
					MainController.mouseItemStack = new ItemStack(player.armor[0], 1);
					player.armor[0] = null;
				}
				if(stack != null && stack.item instanceof ItemHelmet && stack.size == 1){
					ItemHelmet armor = (ItemHelmet) stack.item;
					MainController.mouseItemStack = new ItemStack(player.armor[0], 1);
					if(player.armor[0] == null){
						MainController.mouseItemStack = null;
					}
					player.armor[0] = armor;
				}
				if(stack != null && player.armor[0] == null && stack.item instanceof ItemHelmet){
					player.armor[0] = (ItemArmor) stack.item;
					--stack.size;
					if(stack.size <= 0){
						MainController.mouseItemStack = null;
					}
				}
			}
			if(mouse.y >= factorY(115) && mouse.y <= factorY(147)){
				if(stack == null && player.armor[1] != null){
					MainController.mouseItemStack = new ItemStack(player.armor[1], 1);
					player.armor[1] = null;
				}
				else if(stack != null && stack.item instanceof ItemChestplate && stack.size == 1){
					ItemChestplate armor = (ItemChestplate) stack.item;
					MainController.mouseItemStack = new ItemStack(player.armor[1], 1);
					if(player.armor[1] == null){
						MainController.mouseItemStack = null;
					}
					player.armor[1] = armor;
				}
				else if(stack != null && player.armor[1] == null && stack.item instanceof ItemChestplate){
					player.armor[1] = (ItemArmor) stack.item;
					--stack.size;
					if(stack.size <= 0){
						MainController.mouseItemStack = null;
					}
				}
			}
			if(mouse.y >= factorY(148) && mouse.y <= factorY(180)){
				if(stack == null && player.armor[2] != null){
					MainController.mouseItemStack = new ItemStack(player.armor[2], 1);
					player.armor[2] = null;
				}
				else if(stack != null && stack.item instanceof ItemLeggings && stack.size == 1){
					ItemLeggings armor = (ItemLeggings) stack.item;
					MainController.mouseItemStack = new ItemStack(player.armor[2], 1);
					if(player.armor[2] == null){
						MainController.mouseItemStack = null;
					}
					player.armor[2] = armor;
				}
				else if(stack != null && player.armor[2] == null && stack.item instanceof ItemLeggings){
					player.armor[2] = (ItemArmor) stack.item;
					--stack.size;
					if(stack.size <= 0){
						MainController.mouseItemStack = null;
					}
				}
			}
			if(mouse.y >= factorY(181) && mouse.y <= factorY(213)){
				if(stack == null && player.armor[3] != null){
					MainController.mouseItemStack = new ItemStack(player.armor[3], 1);
					player.armor[3] = null;
				}
				else if(stack != null && stack.item instanceof ItemBoots && stack.size == 1){
					ItemBoots armor = (ItemBoots) stack.item;
					MainController.mouseItemStack = new ItemStack(player.armor[3], 1);
					if(player.armor[3] == null){
						MainController.mouseItemStack = null;
					}
					player.armor[3] = armor;
				}
				else if(stack != null && player.armor[2] == null && stack.item instanceof ItemBoots){
					player.armor[3] = (ItemArmor) stack.item;
					--stack.size;
					if(stack.size <= 0){
						MainController.mouseItemStack = null;
					}
				}
			}
		}
		player.updateHumanArmor();
	}
	
	@Override
	public void rightClick(Vector2f mouse){
		ItemSlot slot = player.inventory.getSlot(new Vector2f(0.03125f, 0.05556f), mouse);
		if(slot != null){
			ItemStack stack = slot.stack;
			ItemStack stack2 = MainController.mouseItemStack;
			if(stack == null && stack2 != null){
				slot.stack = new ItemStack(stack2.item, 1);
				--stack2.size;
				if(stack2.size <= 0){
					MainController.mouseItemStack = null;
				}
			}
			if(stack != null && stack2 != null && stack.item == stack2.item){
				++stack.size;
				--stack2.size;
				if(stack2.size <= 0){
					MainController.mouseItemStack = null;
				}
			}
		}
	}
	
	@Override
	public void escapePressed(){
		if(MainController.mouseItemStack != null){
			if(player.inventory.addItemStack(MainController.mouseItemStack))
				MainController.mouseItemStack = null;
			else
				return;
		}
		super.escapePressed();
	}
	
	@Override
	public boolean canClose(){
		if(MainController.mouseItemStack != null){
			if(player.inventory.addItemStack(MainController.mouseItemStack))
				MainController.mouseItemStack = null;
			else
				return false;
		}
		return true;
	}
	
	@Override
	public void update(){
		Vector2f mouse = MainClass.getMousePosition();
		ItemSlot slot = player.inventory.getSlot(new Vector2f(0.03125f, 0.05556f), mouse);
		toolbar = null;
		if(slot != null && slot.stack != null){
			toolbar = slot.stack.item.getToolBar();
		}
		if(mouse.x >= factorX(549) && mouse.x <= factorX(581)){
			if(mouse.y >= factorY(49) && mouse.y <= factorY(81) && player.weapon != null){
				toolbar = player.weapon.item.getToolBar();
			}
			if(mouse.y > factorY(81) && mouse.y <= factorY(113) && player.armor[0] != null){
				toolbar = player.armor[0].getToolBar();
			}
			if(mouse.y > factorY(113) && mouse.y <= factorY(145)  && player.armor[1] != null){
				toolbar = player.armor[1].getToolBar();
			}
			if(mouse.y > factorY(145) && mouse.y <= factorY(177) && player.armor[2] != null){
				toolbar = player.armor[2].getToolBar();
			}
			if(mouse.y > factorY(177) && mouse.y <= factorY(209) && player.armor[3] != null){
				toolbar = player.armor[3].getToolBar();
			}
		}
		if(toolbar != null){
			toolbar.min = mouse;
		}
	}
	
	@Override
	public Cursor getCursor(){
		return Resources.getCursor("hand");
	}
}
