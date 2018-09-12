package nl.knokko.rpg.quests;

import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.utils.SpecialText;

public class TalkQuest extends Quest {
	
	public final String name;
	public final Reward reward;
	public final Quest[] nextQuests;
	
	TalkQuest(String questName, Reward questReward, Quest... next) {
		name = questName;
		reward = questReward;
		nextQuests = next;
	}
	
	TalkQuest(String name){
		this(name, null);
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
	public TalkQuest clone() {
		return new TalkQuest(name, reward, nextQuests).setProgress(progress, maxProgress).setDiscription(discription, shortDiscription);
	}

	@Override
	public void complete() {
		if(reward != null)
			reward.apply();
		Game.game.quests.removeQuest(this);
		Game.game.quests.addQuests(nextQuests);
	}
	
	@Override
	public TalkQuest setDiscription(SpecialText discription, String shortDiscription){
		super.setDiscription(discription, shortDiscription);
		return this;
	}
	
	public TalkQuest setProgress(int prog, int max){
		progress = prog;
		maxProgress = max;
		return this;
	}

}
