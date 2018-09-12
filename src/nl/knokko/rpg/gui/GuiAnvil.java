package nl.knokko.rpg.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import nl.knokko.rpg.entities.players.Player;
import nl.knokko.rpg.inventory.Inventory;
import nl.knokko.rpg.inventory.ItemSlot;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.Item;
import nl.knokko.rpg.items.ItemArmor;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.main.MainController;
import nl.knokko.rpg.render.Renderer;
import nl.knokko.rpg.render.Texture;
import nl.knokko.rpg.utils.PointUtils;
import nl.knokko.rpg.utils.Resources;

public class GuiAnvil extends GuiPlayerInventory {
	
	public static final Texture BORDER_TEXTURE = Resources.createBorderTexture(Color.BLUE);
	
	public ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	
	public Inventory inventory = new Inventory(5, 5);
	
	public boolean[][] greenFields = new boolean[5][5];

	public GuiAnvil(Player owner) {
		super(owner);
		buttons.remove(2);
		addButton(1200, 50, 1320, 150, "forge");
		recipes.add(new Recipe(Items.woodAxe, 2, 4, Items.wood, Items.wood, Items.wood, Items.wood, null, Items.wood, null, Items.wood));
		recipes.add(new Recipe(Items.darkSkin, 2, 1, Items.foidskin, Items.darkEssence));
		addSword(Items.woodSword, Items.wood);
		addSword(Items.miaySword, Items.miayGem);
		addSword(Items.tarfSword, Items.tarf);
		addSword(Items.darkBlade, Items.darkEssence);
		addSword(Items.bronzeSword, Items.bronze);
		addHammer(Items.woodHammer, Items.wood);
		addHammer(Items.miayHammer, Items.miayGem);
		addHammer(Items.darkHammer, Items.darkEssence);
		addHammer(Items.bronzeHammer, Items.bronze);
		addArmor(Items.foidArmor, Items.foidskin);
		addArmor(Items.miayArmor, Items.miayGem);
		addArmor(Items.tarfArmor, Items.tarf);
		addArmor(Items.darkArmor, Items.darkSkin);
		addArmor(Items.frodeArmor, Items.frodeskin);
		addArmor(Items.bronzeArmor, Items.bronze);
		addWand(Items.miayRod, Items.miayGem);
		addWand(Items.darkWand, Items.darkEssence);
	}
	
	private void addHelmet(Item goal, Item material){
		recipes.add(new Recipe(goal, 3, 3, material, material, material, material, null, material, null, material, null));
	}
	
	private void addChestplate(Item goal, Item material){
		recipes.add(new Recipe(goal, 4, 4, material, material, material, material, null, material, material, null, null, material, material, null, null, material, material, null));
	}
	
	private void addLeggings(Item goal, Item material){
		recipes.add(new Recipe(goal, 3, 4, material, material, material, material, null, material, material, null, material, material, null, material));
	}
	
	private void addBoots(Item goal, Item material){
		recipes.add(new Recipe(goal, 2, 4, null, material, null, material, material, material, material, material));
	}
	
	private void addArmor(ItemArmor[] armor, Item material){
		addHelmet(armor[0], material);
		addChestplate(armor[1], material);
		addLeggings(armor[2], material);
		addBoots(armor[3], material);
	}
	
	private void addSword(Item sword, Item material){
		recipes.add(new Recipe(sword, 3, 5, null, material, null, null, material, null, null, material, null, Items.wood, Items.wood, Items.wood, null, Items.wood, null));
	}
	
	private void addWand(Item rod, Item material){
		recipes.add(new Recipe(rod, 2, 5, material, material, material, material, Items.wood, Items.wood, Items.wood, Items.wood, Items.wood, Items.wood));
	}
	
	private void addHammer(Item hammer, Item material){
		recipes.add(new Recipe(hammer, 3, 5, material, material, material, null, Items.wood, null, null, Items.wood, null, null, Items.wood, null, null, Items.wood, null));
	}
	
	@Override
	public void render(){
		super.render();
		Point minScreen = new Point(1000, 30);
		int x = 0;
		while(x < 5){
			int y = 0;
			while(y < 5){
				if(greenFields[x][y]){
					Vector2f min = PointUtils.screenPointToScreenVector(new Point(minScreen.x + 32 * x, minScreen.y + 32 * y));
					Vector2f max = PointUtils.screenPointToScreenVector(new Point(minScreen.x + 32 * x + 32, minScreen.y + 32 * y + 32));
					//gr.drawRect(min.x - 1, min.y - 1, (33), (33));
					Renderer.renderTextureBetween(min, max, BORDER_TEXTURE.getID());
				}
				++y;
			}
			++x;
		}
	}
	
	/*
	@Override
	public void paint(Graphics gr){
		super.paint(gr);
		inventory.paint(gr, new Point(factorX(1000), factorY(30)));
		gr.setColor(Color.BLUE);
		int x = 0;
		while(x < 5){
			int y = 0;
			while(y < 5){
				if(greenFields[x][y]){
					Point minScreen = new Point(factorX(1000), factorY(30));
					Point min = new Point(minScreen .x + (32 * x), minScreen.y + (32 * y));
					gr.drawRect(min.x - 1, min.y - 1, (33), (33));
				}
				++y;
			}
			++x;
		}
		if(toolbar != null)
			toolbar.paint(gr);
		greenFields = new boolean[5][5];
	}
	*/
	
	@Override
	public void buttonClicked(Button button){
		if(button.name.matches("next player"))
			MainClass.currentGUI = new GuiAnvil(MainClass.nextPlayer(player));
		if(button.name.matches("back"))
			escapePressed();
		if(button.name.matches("forge") && MainController.mouseItemStack == null){
			int t = 0;
			while(t < recipes.size()){
				if(recipes.get(t).check(inventory)){
					int x = 0;
					while(x < 5){
						int y = 0;
						while(y < 5){
							if(greenFields[x][y]){
								inventory.itemSlots[x][y].stack.size--;
								if(inventory.itemSlots[x][y].stack.size <= 0)
									inventory.itemSlots[x][y].stack = null;
							}
							++y;
						}
						++x;
					}
					MainController.mouseItemStack = new ItemStack(recipes.get(t).result);
					return;
				}
				++t;
			}
		}
	}
	
	@Override
	public void click(Vector2f mouse){
		super.click(mouse);
		ItemStack stack = MainController.mouseItemStack;
		ItemSlot slot = inventory.getSlot(new Vector2f(0.625f, 0.03333f), mouse);
		if(slot != null){
			MainController.mouseItemStack = slot.addItemStack(stack);
		}
	}
	
	@Override
	public void rightClick(Vector2f mouse){
		super.rightClick(mouse);
		ItemSlot slot = inventory.getSlot(new Vector2f(0.625f, 0.03333f), mouse);
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
	public void update(){
		super.update();
		Vector2f mouse = MainClass.getMousePosition();
		ItemSlot slot = inventory.getSlot(new Vector2f(0.625f, 0.03333f), mouse);
		if(slot != null && slot.stack != null)
			toolbar = slot.stack.item.getToolBar();
		if(toolbar != null)
			toolbar.min = mouse;
		int t = 0;
		while(t < recipes.size()){
			recipes.get(t).check(inventory);
			++t;
		}
	}
	
	@Override
	public void escapePressed(){
		MainClass.currentGUI = null;
		if(MainController.mouseItemStack != null){
			if(player.inventory.addItemStack(MainController.mouseItemStack))
				MainController.mouseItemStack = null;
			else
				MainClass.currentGUI = this;
		}
		int x = 0;
		Inventory i = inventory;
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
		MainClass.player.inventory.money += i.money;
		i.money = 0;
		if(!inventory.isEmpty())
			MainClass.currentGUI = this;
	}
	
	@Override
	public boolean canClose(){
		escapePressed();
		boolean bool = MainClass.currentGUI == null;
		MainClass.currentGUI = this;
		return bool;
	}
	
	@Override
	public Cursor getCursor(){
		return Resources.getCursor("hand");
	}
	
	public class Recipe {
		
		public Item[][] items;
		public Item result;
		
		public int sizeX;
		public int sizeY;
		
		public Recipe(Item goal, int sx, int sy, Item... recipe){
			items = new Item[sx][sy];
			result = goal;
			sizeX = sx;
			sizeY = sy;
			int y = 0;
			int t = 0;
			while(y < items[0].length){
				int x = 0;
				while(x < items.length){
					items[x][y] = recipe[t];
					x++;
					t++;
				}
				++y;
			}
		}
		
		public boolean check(Inventory inventory){
			int x = 0;
			while(x <= inventory.sizeX - sizeX){
				int y = 0;
				while(y <= inventory.sizeY - sizeY){
					if(check(inventory, x, y)){
						return true;
					}
					++y;
				}
				++x;
			}
			return false;
		}
		
		protected boolean check(Inventory inventory, int ix, int iy){
			boolean[][] fields = new boolean[5][5];
			int x = 0;
			while(x < sizeX){
				int y = 0;
				while(y < sizeY){
					ItemSlot slot = inventory.itemSlots[ix + x][iy + y];
					ItemStack stack = slot.stack;
					Item item = items[x][y];
					if(stack == null && item != null)
						return false;
					if(stack != null && item != stack.item)
						return false;
					if(item != null)
						fields[ix + x][iy + y] = true;
					++y;
				}
				++x;
			}
			x = 0;
			while(x < 5){
				int y = 0;
				while(y < 5){
					if(fields[x][y])
						greenFields[x][y] = true;
					++y;
				}
				++x;
			}
			return true;
		}
	}
}
