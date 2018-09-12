package nl.knokko.rpg.inventory;

import org.lwjgl.util.vector.Vector2f;

import nl.knokko.rpg.items.Item;

public class InventoryShop {
	
	protected Item[][] items;
	public int xSize;
	public int ySize;
	
	public InventoryShop(int sizeX, int sizeY, Item... inventory) {
		xSize = sizeX;
		ySize = sizeY;
		items = new Item[sizeX][sizeY];
		int y = 0;
		int t = 0;
		while(y < sizeY&& t < inventory.length){
			int x = 0;
			while(x < sizeX && t < inventory.length){
				items[x][y] = inventory[t];
				++t;
				++x;
			}
			++y;
		}
	}
	
	public InventoryShop(int sizeX, int sizeY){
		xSize = sizeX;
		ySize = sizeY;
		items = new Item[sizeX][sizeY];
	}
	
	/*
	public void paint(Graphics gr, Point minScreen){
		gr.setColor(new Color(100, 50, 0));
		Vector2f mouse = MainClass.getMousePosition();
		int x = 0;
		while(x < xSize){
			int y = 0;
			while(y < ySize){
				gr.setColor(new Color(100, 50, 0));
				Point min = new Point(minScreen.x + (32 * x), minScreen.y + (32 * y));
				Point max = new Point(minScreen.x + (32 + 32 * x), minScreen.y + (32 + 32 * y));
				if(mouse.x >= min.x && mouse.x < max.x && mouse.y >= min.y && mouse.y < max.y){
					gr.setColor(Color.BLUE);
				}
				gr.fillRect(min.x, min.y, (32), (32));
				if(items[x][y] != null){
					items[x][y].paint(gr, min, max);
				}
				gr.setColor(Color.YELLOW);
				gr.drawRect(min.x - 1, min.y - 1, (32) + 1, (32) + 1);
				++y;
			}
			++x;
		}
	}
	*/
	
	public Item getItem(Vector2f min, Vector2f screen){
		float fx = (screen.x - min.x + 1) / 2;
		float fy = (screen.y - min.y + 1) / 2;
		int x = (int) (fx / 0.02f);
		int y = (int) (fy / 0.0356f);
		if(x >= 0 && x < xSize && y >= 0 && y < ySize){
			return items[x][y];
		}
		return null;
	}
	
	public boolean addItem(Item item){
		int y = 0;
		while(y < ySize){
			int x = 0;
			while(x < xSize){
				Item item2 = items[x][y];
				if(item2 == item){
					return false;
				}
				if(item2 == null){
					items[x][y] = item;
					return true;
				}
				++x;
			}
			++y;
		}
		return false;
	}
}
