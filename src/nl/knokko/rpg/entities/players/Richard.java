package nl.knokko.rpg.entities.players;

import java.awt.Point;

import nl.knokko.rpg.gui.GuiChat;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.quests.TalkQuest;
import nl.knokko.rpg.utils.SpecialText;

public class Richard extends Player {

	public Richard(Game game, Point point) {
		super(game, point);
		strength = 20;
		spirit = 20;
		totalXp = 3000;
		weapon = new ItemStack(Items.pickaxe);
		inventory.addItemStack(new ItemStack(Items.torch));
		armor[0] = Items.foidHelmet;
		armor[1] = Items.foidChestplate;
		armor[2] = Items.foidLeggings;
		armor[3] = Items.foidBoots;
	}
	
	@Override
	public String getName(){
		return "Richard";
	}
	
	@Override
	public void interact(){
		TalkQuest q = (TalkQuest) game.quests.getQuest("miners");
		if(q != null){
			++q.progress;
			if(q.progress >= 3)
				q.complete();
		}
		game.world.entities.remove(this);
		game.player3 = this;
		String[] end = game.quests.hasQuest(q) ? new String[]{"We should tell the other miners we can leave.", "Than we can leave this cave."} : new String[]{"Let's go to Ruff!"};
		game.currentGUI = new GuiChat(game, false, 
				new SpecialText("Bart, Chomper, I'm glad you are here to save us.", "However, there is more we can do.", "The shaman of Ruff needs our help.", "He sent a special message to my mind."), 
				new SpecialText("He told me he is going to close a dark portal.", "That portal leads to the world of demons.", "He needs our help to close it.", "He will tell us more when we meet him in Ruff."),
				new SpecialText("I hate the demons, it would be my pleasure to stop them.", "It's time for my real revenge."), 
				new SpecialText("Chomper, I will do this to save the world.", "Not just because you want your revenge."), 
				new SpecialText("I will also do this to save the world.", "But especially for my revenge!"), 
				new SpecialText("Does anybody know where Ruff is?", "I have never heard anything about it."), 
				new SpecialText("The shaman said Ruff is on the west of the Lost Plains.", "It will be a dangerous journey."),
				new SpecialText("I don't think closing a portal will be easy.", "That will be more dangerous than traveling to Ruff.", "But I will do anything for my revenge."), 
				new SpecialText(end)).
				setSpeakers(new String[]{"Richard", "Richard", "Chomper", "Richard", "Chomper", "Bart", "Richard", "Chomper", "Richard"});
	}
}
