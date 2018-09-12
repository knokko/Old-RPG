package nl.knokko.rpg.inventory;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import nl.knokko.rpg.items.ItemConsumable;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.main.Game;

public class Inventory {
	
	public int sizeX;
	public int sizeY;
	public int money;
	
	public ItemSlot[][] itemSlots;
	
	public Inventory(ItemSlot[][] slots, int xSize, int ySize) {
		itemSlots = slots;
		sizeX = xSize;
		sizeY = ySize;
	}
	
	public Inventory(ItemStack[] stacks){
		sizeX = 10;
		sizeY = (stacks.length / 10) + 1;
		itemSlots = new ItemSlot[10][(sizeY) + 1];
		int t = 0;
		int t2 = 0;
		int t3 = 0;
		while(t < stacks.length){
			if(t2 < 10){
				itemSlots[t2][t3] = new ItemSlot(stacks[t]);
				++t2;
			}
			else {
				t2 = 0;
				++t3;
				itemSlots[t2][t3]= new ItemSlot(stacks[t]);
			}
			++t;
		}
	}
	
	public Inventory(int xSize, int ySize){
		sizeX = xSize;
		sizeY = ySize;
		itemSlots = new ItemSlot[xSize][ySize];
		int x = 0;
		while(x < xSize){
			int y = 0;
			while(y < ySize){
				itemSlots[x][y] = new ItemSlot(null);
				++y;
			}
			++x;
		}
	}
	
	public void paint(Graphics gr, Point minScreen){
		gr.setColor(new Color(100, 50, 0));
		Point mouse = Game.game.getMousePosition();
		int x = 0;
		while(x < sizeX){
			int y = 0;
			while(y < sizeY){
				gr.setColor(new Color(100, 50, 0));
				Point min = new Point(minScreen.x + (32 * x), minScreen.y + (32 * y));
				Point max = new Point(minScreen.x + (32 + 32 * x), minScreen.y + (32 + 32 * y));
				if(mouse.x >= min.x && mouse.x < max.x && mouse.y >= min.y && mouse.y < max.y){
					gr.setColor(Color.BLUE);
				}
				gr.fillRect(min.x, min.y, (32), (32));
				if(itemSlots[x][y] != null && itemSlots[x][y].stack != null){
					itemSlots[x][y].stack.paint(gr, new Point(min.x + 1, min.y + 1), new Point(max.x - 1, max.y - 1));
				}
				gr.setColor(Color.YELLOW);
				gr.drawRect(min.x - 1, min.y - 1, (33), (33));
				++y;
			}
			++x;
		}
	}
	
	public ItemSlot getSlot(Point min, Point screen){
		int x = (screen.x - (min.x)) / (32);
		int y = (screen.y - (min.y)) / (32);
		if(x >= 0 && x < sizeX && y >= 0 && y < sizeY){
			ItemSlot slot = itemSlots[x][y];
			if(slot == null){
				itemSlots[x][y] = new ItemSlot(null);
				slot = new ItemSlot(null);
			}
			return slot;
		}
		return null;
	}
	
	public boolean addItemStack(ItemStack stack){
		int x = 0;
		while(x < sizeX){
			int y = 0;
			while(y < sizeY){
				ItemStack stack1 = itemSlots[x][y].stack;
				if(stack1 != null && stack1.item == stack.item){
					itemSlots[x][y].stack.size += stack.size;
					return true;
				}
				++y;
			}
			++x;
		}
		x = 0;
		while(x < sizeX){
			int y = 0;
			while(y < sizeY){
				ItemStack stack1 = itemSlots[x][y].stack;
				if(stack1 == null){
					itemSlots[x][y] = new ItemSlot(stack);
					return true;
				}
				++y;
			}
			++x;
		}
		return false;
	}
	
	public void removeItemStack(ItemStack stack){
		int x = 0;
		while(x < sizeX && stack.size > 0){
			int y = 0;
			while(y < sizeY && stack.size > 0){
				ItemStack stack1 = itemSlots[x][y].stack;
				if(stack1 != null && stack1.item == stack.item){
					itemSlots[x][y].stack.size -= stack.size;
					if(itemSlots[x][y].stack.size <= 0){
						stack.size = (short) -itemSlots[x][y].stack.size;
						itemSlots[x][y].stack = null;
					}
				}
				++y;
			}
			++x;
		}
	}
	
	public void saveData(PrintWriter writer){
		writer.println("sizeX:" + sizeX);
		writer.println("sizeY:" + sizeY);
		writer.println("money:" + money);
		int x = 0;
		while(x < sizeX){
			int y = 0;
			while(y < sizeY){
				ItemSlot slot = itemSlots[x][y];
				if(slot != null && slot.stack != null){
					writer.println(slot.stack.size + "x" + slot.stack.item.id);
				}
				else {
					writer.println("empty");
				}
				++y;
			}
			++x;
		}
	}
	
	public void loadData(BufferedReader reader) throws NumberFormatException, IOException{
		sizeX = Integer.decode(reader.readLine().substring(6));
		sizeY = Integer.decode(reader.readLine().substring(6));
		money = Integer.decode(reader.readLine().substring(6));
		itemSlots = new ItemSlot[sizeX][sizeY];
		int x = 0;
		while(x < sizeX){
			int y = 0;
			while(y < sizeY){
				String line = reader.readLine();
				if(line == null || line.contains("empty")){
					itemSlots[x][y] = new ItemSlot(null);
				}
				else {
					int lx = line.indexOf("x");
					int size = Integer.decode(line.substring(0, lx));
					int id = Integer.decode(line.substring(lx + 1));
					itemSlots[x][y] = new ItemSlot(new ItemStack(Items.fromId(id), size));
				}
				++y;
			}
			++x;
		}
	}
	
	public ArrayList<ItemStack> getConsumableItems(){
		ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
		int y = 0;
		while(y < sizeY){
			int x = 0;
			while(x < sizeX){
				ItemStack stack = itemSlots[x][y].stack;
				if(stack != null && stack.item instanceof ItemConsumable){
					stacks.add(stack);
				}
				++x;
			}
			++y;
		}
		return stacks;
	}
	
	public ArrayList<ItemStack> getItems(){
		ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
		int y = 0;
		while(y < sizeY){
			int x = 0;
			while(x < sizeX){
				ItemStack stack = itemSlots[x][y].stack;
				if(stack != null)
					stacks.add(stack);
				++x;
			}
			++y;
		}
		return stacks;
	}
	
	public boolean isEmpty(){
		if(money > 0)
			return false;
		int x = 0;
		while(x < sizeX){
			int y = 0;
			while(y < sizeY){
				ItemSlot slot = itemSlots[x][y];
				if(slot != null && slot.stack != null && slot.stack.size > 0 && slot.stack.item != null)
					return false;
				++y;
			}
			++x;
		}
		return true;
	}
}
