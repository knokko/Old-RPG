package nl.knokko.rpg.quests;

import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.utils.SpecialText;

public class ItemQuest extends Quest {
	
	public String specialItem;
	public String name;
	public Reward reward;
	public Quest[] nextQuests;
	
	ItemQuest(String questName, String item, Reward questReward, Quest... followQuests) {
		name = questName;
		specialItem = item;
		reward = questReward;
	}

	@Override
	public boolean needsUpdate() {
		return false;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public ItemQuest clone() {
		return new ItemQuest(name, specialItem, reward).setDiscription(discription, shortDiscription);
	}
	
	@Override
	public void saveData(boolean bool){}
	
	@Override
	public void loadData(){}

	@Override
	public void complete() {
		if(reward != null)
			reward.apply();
		Game.game.quests.removeQuest(this);
		Game.game.quests.addQuests(nextQuests);
	}
	
	@Override
	public ItemQuest setDiscription(SpecialText discription, String shortDiscription){
		super.setDiscription(discription, shortDiscription);
		return this;
	}
	
	@Override
	public void addSpecialItem(String item){
		if(item.matches(specialItem))
			complete();
	}
}
