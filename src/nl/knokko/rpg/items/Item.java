package nl.knokko.rpg.items;

import java.awt.*;

import nl.knokko.rpg.Element;
import nl.knokko.rpg.toolbars.ToolBar;
import nl.knokko.rpg.utils.Resources;

public class Item {
	
	public final short id;
	public final int value;
	public final String name;
	public final Image image;
	public final Element element;
	
	private static short nextId;
	
	Item(String itemName, int moneyValue) {
		this(itemName, moneyValue, Element.NORMAL);
	}
	
	Item(String itemName, int moneyValue, Element itemElement){
		id = nextId;
		++nextId;
		name = itemName;
		image = Resources.getImage("sprites/items/" + name + ".png");
		value = moneyValue;
		element = itemElement;
		Items.items.add(id, this);
	}
	
	public void paint(Graphics gr, Point screenPoint, Point screenPoint2){
		gr.drawImage(image, screenPoint.x, screenPoint.y, screenPoint2.x - screenPoint.x, screenPoint2.y - screenPoint.y, null);
	}
	
	@Override
	public String toString(){
		return name;
	}
	
	public ToolBar getToolBar(){
		return new ToolBar(name, "", "Price: " + value).setSize(150, 70);
	}
}
