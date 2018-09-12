package nl.knokko.rpg.quests;

import java.awt.Color;
import java.awt.Font;

import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.items.SpecialItems;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.quests.Quest.Reward;
import nl.knokko.rpg.utils.SpecialText;

public final class Quests {
	
	public static final Font questFont = new Font("TimesRoman", 0, (30));
	
	private static final SpecialText discSaveMiners = new SpecialText(questFont, "The miners of Runia went to Miay Cave to collect miay gems.", "However, when they were gone, the demons appeared.", "The miners should be back in Runia already.", "Everyone thinks they are lost or dead.", "", "It is your mission to find and save the miners.", "The entrace of Miay Cave is in the north-east of Foid Forest.", "Foid Forest is north from Runia.");
	private static final SpecialText discMeetShaman = new SpecialText(questFont, "Richard got a magic message from the shaman of Ruff.", "He told he needed help to stop the demons.", "He will tell you more when you speak to him in Ruff.", "", "Ruff is on the west side of the Lost Plains.", "The Lost Plains are on the west side of Foid Forest.");
	private static final SpecialText discDemonicPearl = new SpecialText(questFont, "To close the gate to the world of the demons,", "the shaman needs a demonic pearl.", "", "In the deepest of Miay Cave lives a powerfull demon.", "It's called a recking, they have demonic pearls.", "", "It won't give you his pearl.", "You must kill it!");
	private static final SpecialText discCursedEye = new SpecialText(questFont, "There is a strong dark power coming from Dark Forest.", "I am sure that's the location of the Cursed Eye.", "The Cursed Eye has his own soul, it will try to kill you.", "We need that eye to find the location of the portal.");
	private static final SpecialText discAncientSpellBook = new SpecialText(questFont, "In order to close the portal to the world of the demons,", "I need to cast the right spell.", "However, I don't know that spell.", "A fellow shaman found a way to open and close portals,", "but he died centuries ago.", "Now, I am glad he wrote his spells down in a book.", "That book is barried with him in a tomb under a huge cave.", "One of the entrances, is located ", "in the southern part of Dark Forest.", "Find the book and take it.");
	private static final SpecialText discClosePortal = new SpecialText(new Color(200, 0, 200), questFont, "The shaman is going to close the nearest portal.", "This is one of the most difficult things to do.", "", "You will need several rare items and you have to know the ", "location of the portal.", "", "One of the items you need is a demonic pearl.", "It's a rare item with special powers.", "These pearls were used to open the portal to this world.", "We will also need them to close portals.", "", "To locate the portal, we need the Cursed Eye.", "It's a demon with a huge eye as body.", " I am almost sure the Dark Eye is hiding in Dark Forest.", "", "Unfortunutely, I don't know the spell to close the portal.", "However, another shaman found out", "how to open and close portals.", "He wrote that down in a spellbook,", "that book is barried with him,", "under a cave in the southern of Dark Forest.");
	
	private static final MultiQuest closePortal = new MultiQuest("close_portal", new Reward(1500, 2000)).setDiscription(discClosePortal, "close the portal");
	private static final ItemQuest demonicPearl = new ItemQuest("demonic_pearl", SpecialItems.DEMONIC_PEARL, new Reward(400, 0)).setDiscription(discDemonicPearl, "get demonic pearl");
	private static final ItemQuest cursedEye = new ItemQuest("cursed_eye", SpecialItems.CURSED_EYE, new Reward(600, 0)).setDiscription(discCursedEye, "get cursed eye");
	private static final ItemQuest ancientSpellBook = new ItemQuest("ancient_spellbook", SpecialItems.ANCIENT_SPELL_BOOK, new Reward(700, 0)).setDiscription(discAncientSpellBook, "get the spellbook");
	private static final TalkQuest meetShaman = new TalkQuest("shaman", new Reward(400, 0, new ItemStack(Items.orangeEther), new ItemStack(Items.orangePotion)), demonicPearl, cursedEye, ancientSpellBook, closePortal).setDiscription(discMeetShaman, "meet the shaman of Ruff");
	private static final TalkQuest saveMiners = new TalkQuest("miners", new Reward(300, 500, new ItemStack(Items.miayGem, 3)), meetShaman).setProgress(0, 3).setDiscription(discSaveMiners, "miners saved");
	
	public static final Quest fromString(String quest){
		try {
			return (Quest) Quests.class.getMethod(quest).invoke(null);
		} catch(Exception ex){
			ex.printStackTrace(Game.console);
			return null;
		}
	}
	
	public static final TalkQuest miners(){
		return saveMiners.clone();
	}
	
	public static final TalkQuest shaman(){
		return meetShaman.clone();
	}
	
	public static final ItemQuest demonic_pearl(){
		return demonicPearl.clone();
	}
	
	public static final MultiQuest close_portal(){
		return closePortal.clone();
	}
	
	public static final ItemQuest cursed_eye(){
		return cursedEye.clone();
	}
	
	public static final ItemQuest ancient_spellbook(){
		return ancientSpellBook.clone();
	}
}
