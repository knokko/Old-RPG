package nl.knokko.rpg.quests;

import java.io.*;
import java.util.ArrayList;

import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.utils.Resources;

public class QuestMap {
	
	public ArrayList<Quest> quests = new ArrayList<Quest>();
	public ArrayList<Quest> updateQuests = new ArrayList<Quest>();
	
	public void saveData(){
		try {
			PrintWriter writer = new PrintWriter(Resources.getSaveFile() + "/quests.txt");
			int t = 0;
			while(t < quests.size()){
				writer.println(quests.get(t));
				quests.get(t).saveData(true);
				++t;
			}
			writer.close();
		} catch (Exception e) {
			MainClass.console.println("QuestMap.saveData(): Failed to save the quests data:");
			MainClass.console.println();
			e.printStackTrace(MainClass.console);
			MainClass.console.println();
		}
	}
	
	public void loadData(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Resources.getLoadFile() + "/quests.txt"));
			String line = reader.readLine();
			while(line != null){
				Quest quest = Quests.fromString(line);
				if(quest != null){
					quest.loadData();
					quests.add(quest);
					if(quest.needsUpdate())
						updateQuests.add(quest);
				}
				line = reader.readLine();
			}
			reader.close();
		} catch(Exception ex){
			MainClass.console.println("Failed to load the quests:");
			ex.printStackTrace(MainClass.console);
			MainClass.console.println();
		}
	}
	
	public void update(){
		int t = 0;
		while(t < updateQuests.size()){
			updateQuests.get(t).update();
			++t;
		}
	}
	
	public Quest getQuest(String quest){
		int t = 0;
		while(t < quests.size()){
			if(quests.get(t).toString().matches(quest))
				return quests.get(t);
			++t;
		}
		return null;
	}
	
	public boolean hasQuest(String quest){
		int t = 0;
		while(t < quests.size()){
			if(quests.get(t).toString().matches(quest))
				return true;
			++t;
		}
		return false;
	}
	
	public boolean hasQuest(Quest quest){
		return quests.contains(quest);
	}
	
	public void addQuest(Quest quest, boolean needsUpdate){
		if(!quests.contains(quest))
			quests.add(quest);
		if(needsUpdate && !updateQuests.contains(quest))
			updateQuests.add(quest);
	}
	
	public void addQuests(Quest... quest){
		if(quest != null){
			int t = 0;
			while(t < quest.length){
				addQuest(quest[t]);
				++t;
			}
		}
	}
	
	public void addQuest(Quest quest){
		addQuest(quest, quest.needsUpdate());
	}
	
	public void removeQuest(Quest quest){
		if(quest != null){
			quests.remove(quest);
			updateQuests.remove(quest);
		}
	}
	
	public void removeQuest(String quest){
		removeQuest(Quests.fromString(quest));
	}
}
