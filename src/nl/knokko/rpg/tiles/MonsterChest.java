package nl.knokko.rpg.tiles;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import nl.knokko.rpg.gui.GuiBattle;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.utils.Resources;

public class MonsterChest extends Chest {
	
	public GuiBattle surprise;

	public MonsterChest(int chestId, Point location, GuiBattle battle, String... special_items) {
		super(chestId, location, special_items);
		surprise = battle;
		surprise.lootMoney += inventory.money;
		surprise.loot.addAll(inventory.getItems());
	}
	
	public void checkSurprise(){
		if(surprise != null){
			int d = 0;
			int t = 0;
			while(t < surprise.enemies.length){
				if(surprise.enemies[t] == null)
					++d;
				++t;
			}
			if(d == surprise.enemies.length)
				surprise = null;
		}
	}
	
	@Override
	public void saveData() throws FileNotFoundException{
		PrintWriter writer = new PrintWriter(Resources.getSaveFile() + "/chests/chest " + id + ".txt");
		inventory.saveData(writer);
		checkSurprise();
		writer.println(surprise != null ? "surprise" : "cleared");
		writer.close();
	}
	
	public void loadData(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Resources.getLoadFile() + "/chests/chest " + id + ".txt"));
			inventory.loadData(reader);
			if(!reader.readLine().matches("surprise"))
				surprise = null;
			reader.close();
		} catch (Exception e) {}
	}
	
	@Override
	public boolean isEmpty(){
		checkSurprise();
		return super.isEmpty() && surprise == null;
	}
	
	@Override
	public void open(){
		checkSurprise();
		if(surprise != null){
			Game.game.currentGUI = surprise;
			surprise.activate();
		}
	}
}
