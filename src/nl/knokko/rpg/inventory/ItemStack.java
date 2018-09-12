package nl.knokko.rpg.inventory;

import java.awt.*;

import nl.knokko.rpg.items.Item;

public class ItemStack {
	
	public short size;
	public Item item;
	
	public ItemStack(Item stackItem, int stackSize) {
		size = (short) stackSize;
		item = stackItem;
	}
	
	public ItemStack(Item stackItem){
		item = stackItem;
		size = 1;
	}
	
	public String toString(){
		return size + "x" + item;
	}
	
	public void paint(Graphics gr, Point screenPoint, Point screenPoint2){
		if(item != null && size > 0){
			item.paint(gr, screenPoint, screenPoint2);
			gr.setColor(Color.BLACK);
			gr.setFont(new Font("TimesRoman", 0, (20)));
			gr.drawString("" + size, screenPoint.x, screenPoint2.y);
		}
	}
}
