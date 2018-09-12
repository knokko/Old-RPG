package nl.knokko.rpg.quests;

import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.utils.SpecialText;

public class MultiQuest extends Quest {
	
	public final String name;
	public final Reward reward;
	public final Quest[] nextQuests;
	
	MultiQuest(String questName, Reward questReward, Quest... followQuests) {
		name = questName;
		reward = questReward;
		nextQuests = followQuests;
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
	public MultiQuest clone() {
		return new MultiQuest(name, reward, nextQuests).setDiscription(discription, shortDiscription);
	}

	@Override
	public void complete() {
		if(reward != null)
			reward.apply();
		MainClass.quests.removeQuest(this);
		MainClass.quests.addQuests(nextQuests);
	}
	
	@Override
	public MultiQuest setDiscription(SpecialText discription, String shortDiscription){
		super.setDiscription(discription, shortDiscription);
		return this;
	}
}
