package nl.knokko.rpg.entities.monsters.boss;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.utils.Resources;

public final class Bosses {
	
	public static ArrayList<String> bosses = new ArrayList<String>();
	
	public static final String FIRE_DRAGON_1 = "fire dragon 1";
	public static final String GOBLIN_KING = "goblin king";
	public static final String VOIDLINGS = "voidlings";
	
	public static void saveData(){
		try {
			PrintWriter writer = new PrintWriter(Resources.getSaveFile() + "/bosses.txt");
			int t = 0;
			while(t < bosses.size()){
				writer.println(bosses.get(t));
				++t;
			}
			writer.close();
		} catch(Exception ex){
			Game.console.println("Bosses.saveData(): Failed to save the bosses:");
			Game.console.println();
			ex.printStackTrace(Game.console);
			Game.console.println();
		}
	}
	
	public static void loadData(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Resources.getLoadFile() + "/bosses.txt"));
			String line = reader.readLine();
			while(line != null){
				bosses.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch(Exception ex){
			Game.console.println("failed to load the bosses; all bosses have been added to the list:");
			ex.printStackTrace(Game.console);
			Game.console.println();
			addAllBosses();
		}
	}
	
	public static void addAllBosses(){
		int t = 0;
		while(t < Bosses.class.getFields().length){
			try {
				if(Bosses.class.getFields()[t].get(null) instanceof String)
					bosses.add((String) Bosses.class.getFields()[t].get(null));
				++t;
			}catch (Exception e) {
				Game.console.println("failed to get all bosses:");
				Game.console.println();
				e.printStackTrace(Game.console);
				Game.console.println();
			}
		}
	}
}
