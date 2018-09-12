package nl.knokko.rpg.items;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.quests.Quest;
import nl.knokko.rpg.utils.Resources;

public final class SpecialItems {
	
	public static final String DEMONIC_PEARL = "demonic pearl";
	public static final String CURSED_EYE = "cursed eye";
	public static final String ANCIENT_SPELL_BOOK = "ancient spell book";
	
	public static final String BLUE_PRINT_SWORD = "blue print sword";
	public static final String BLUE_PRINT_WAND = "blue print wand";
	public static final String BLUE_PRINT_HELMET = "blue print helmet";
	
	public final ArrayList<String> items = new ArrayList<String>();
	public final ArrayList<String> bluePrints = new ArrayList<String>();
	
	public boolean hasItem(String item){
		return items.contains(item) || bluePrints.contains(item);
	}
	
	public void addItem(String item){
		if(item.startsWith("blue print") && !bluePrints.contains(item)){
			bluePrints.add(item);
			return;
		}
		if(!items.contains(item)){
			ArrayList<Quest> quests = MainClass.quests.quests;
			int t = 0;
			while(t < quests.size()){
				quests.get(t).addSpecialItem(item);
				++t;
			}
			items.add(item);
		}
	}
	
	public boolean removeItem(String item){
		return items.remove(item) ? true : bluePrints.remove(item);
	}
	
	public void saveData(){
		try {
			PrintWriter writer = new PrintWriter(Resources.getSaveFile() + "/special items.txt");
			int t = 0;
			while(t < items.size()){
				writer.println(items.get(t));
				++t;
			}
			t = 0;
			while(t < bluePrints.size()){
				writer.println(bluePrints.get(t));
				++t;
			}
			writer.close();
		} catch(Exception ex){
			MainClass.console.println("SpecialItems.saveData(): Failed to save special items:");
			MainClass.console.println();
			ex.printStackTrace(MainClass.console);
			MainClass.console.println();
		}
	}
	
	public void loadData(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Resources.getLoadFile() + "/special items.txt"));
			String line = reader.readLine();
			while(line != null){
				addItem(line);
				line = reader.readLine();
			}
			reader.close();
		} catch(Exception ex){
			MainClass.console.println("Failed to load special items:");
			ex.printStackTrace(MainClass.console);
			MainClass.console.println();
		}
	}
}
