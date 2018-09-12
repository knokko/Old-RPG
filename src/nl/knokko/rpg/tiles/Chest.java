package nl.knokko.rpg.tiles;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import nl.knokko.rpg.inventory.Inventory;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.Item;
import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.utils.Resources;

public class Chest {
	
	public final int id;
	public final Point position;
	public final ArrayList<String> specialItems = new ArrayList<String>();
	
	public Inventory inventory;
	
	public Chest(int chestId, Point location, String... special_items ) {
		id = chestId;
		inventory = new Inventory(15, 3);
		position = location;
		int t = 0;
		while(t < special_items.length){
			if(!MainClass.specialItems.hasItem(special_items[t]))
				specialItems.add(special_items[t]);
			++t;
		}
	}
	
	public Chest setMoney(int money){
		inventory.money = money;
		return this;
	}
	
	public Chest addItem(Item item){
		inventory.addItemStack(new ItemStack(item));
		return this;
	}
	
	public Chest addItems(Item...items){
		int t = 0;
		while(t < items.length){
			addItem(items[t]);
			++t;
		}
		return this;
	}
	
	public Chest addItemStack(ItemStack stack){
		inventory.addItemStack(stack);
		return this;
	}
	
	public Chest addItemStack(Item item, int stackSize){
		inventory.addItemStack(new ItemStack(item, stackSize));
		return this;
	}
	
	public void saveData() throws Exception{
		PrintWriter writer = new PrintWriter(Resources.getSaveFile() + "/chests/chest " + id + ".txt");
		inventory.saveData(writer);
		writer.close();
	}
	
	public void loadData(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Resources.getLoadFile() + "/chests/chest " + id + ".txt"));
			inventory.loadData(reader);
			reader.close();
		} catch (Exception e) {}
	}
	
	public boolean isEmpty(){
		return inventory.isEmpty() && specialItems.isEmpty();
	}
	
	public void open(){}
}
