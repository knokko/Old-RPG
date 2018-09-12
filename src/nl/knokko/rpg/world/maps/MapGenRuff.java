package nl.knokko.rpg.world.maps;

import java.awt.*;
import java.util.ArrayList;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.Villager.RuffVillager;
import nl.knokko.rpg.gui.GuiChat;
import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.utils.SpecialText;
import nl.knokko.rpg.world.Conversation;
import nl.knokko.rpg.world.Portal;
import nl.knokko.rpg.world.maps.MapGenBase;

public class MapGenRuff extends MapGenBase {

	public MapGenRuff(){
		super();
		build();
		placePortals();
		spawnVillagers();
		if(GuiChat.hasConversation(GuiChat.ruff) && MainClass.quests.hasQuest("shaman"))
			conversations.add(new Conversation(GuiChat.ruff, new Point(2190, 660)));
	}

	public void spawnVillagers() {
		entities.add(new RuffVillager(new Point(1380, 780), new SpecialText("I'm so happy we have guardians in our village.", "If the monsters would be able to come in...")));
		entities.add(new RuffVillager(new Point(1980, 870), new SpecialText("Isn't this a beautiful day?")));
		entities.add(new RuffVillager(new Point(1290, 1110), new SpecialText("I am going to buy a new house.", "Shall I take this house, or that one right?", "Or that house in the south...")));
		entities.add(new RuffVillager(new Point(1710, 1620), new SpecialText("The shaman lives in this wooden house.")));
	}

	@Override
	public int getXSize(){
		return 100;
	}

	@Override
	public int getYSize(){
		return 100;
	}

	@Override
	public String getName(){
		return "Ruff";
	}

	@Override
	public ArrayList<Entity> getEnemies(){
		return null;
	}

	@Override
	public int getMaxEnemies(){
		return 0;
	}

	public void build(){
		fill(0, 0, 99, 99, 1);
		fill2(28, 14, 74, 14, 25);
		fill2(74, 15, 74, 85, 26);
		fill2(28, 15, 28, 85, 26);
		fill2(29, 85, 73, 85, 25);
		tiles2[28][85] = 27;
		tiles2[74][85] = 27;
		fill(0, 22, 99, 22, 2);
		fill2(62, 21, 73, 21, 28);
		fill2(62, 15, 62, 20, 28);
		fill2(63, 15, 73, 15, 28);
		fill2(73, 16, 73, 20, 28);
		fill(63, 16, 72, 20, 6);
		tiles3[62][15] = 5;
		tiles3[68][15] = 5;
		tiles2[68][21] = 7;
		tiles3[68][20] = 11;
		fill2(29, 21, 40, 21, 28);
		fill2(29, 15, 29, 20, 28);
		fill2(30, 15, 40, 15, 28);
		fill2(40, 16, 40, 20, 28);
		fill(30, 16, 39, 20, 6);
		tiles3[29][15] = 5;
		tiles3[35][15] = 5;
		tiles2[34][21] = 7;
		tiles3[34][20] = 10;
		fill(51, 0, 51, 71, 2);
		fill(29, 39, 73, 39, 2);
		fill2(29, 32, 29, 38, 28);
		fill2(30, 38, 34, 38, 28);
		fill2(34, 32, 34, 37, 28);
		fill2(30, 32, 33, 32, 28);
		tiles3[29][32] = 5;
		tiles2[31][38] = 7;
		fill2(37, 32, 37, 38, 28);
		fill2(38, 32, 42, 32, 28);
		fill2(42, 33, 42, 38, 28);
		fill2(38, 38, 41, 38, 28);
		tiles2[39][38] = 7;
		tiles3[37][32] = 5;
		tiles3[45][32] = 5;
		fill2(45, 32, 50, 32, 28);
		fill2(45, 33, 45, 38, 28);
		fill2(46, 38, 50, 38, 28);
		fill2(50, 33, 50, 37, 28);
		tiles2[48][38] = 7;
		fill2(29, 40, 29, 46, 28);
		fill2(30, 46, 34, 46, 28);
		fill2(34, 40, 34, 45, 28);
		fill2(30, 40, 33, 40, 28);
		tiles3[29][40] = 5;
		fill2(37, 40, 37, 46, 28);
		fill2(38, 46, 42, 46, 28);
		fill2(42, 40, 42, 45, 28);
		fill2(38, 40, 41, 40, 28);
		fill2(45, 40, 45, 46, 28);
		fill2(46, 46, 50, 46, 28);
		fill2(50, 40, 50, 45, 28);
		fill2(46, 40, 49, 40, 28);
		tiles3[37][40] = 5;
		tiles3[45][40] = 5;
		tiles2[31][40] = 7;
		tiles2[39][40] = 7;
		tiles2[48][40] = 7;
		fill2(52, 32, 57, 32, 28);
		fill2(57, 33, 57, 38, 28);
		fill2(52, 38, 56, 38, 28);
		fill2(52, 33, 52, 37, 28);
		fill2(60, 38, 65, 38, 28);
		fill2(65, 32, 65, 37, 28);
		fill2(60, 32, 64, 32, 28);
		fill2(60, 33, 60, 37, 28);
		fill2(68, 38, 73, 38, 28);
		fill2(68, 32, 68, 37, 28);
		fill2(69, 32, 73, 32, 28);
		fill2(73, 33, 73, 37, 28);
		tiles3[52][32] = 5;
		tiles3[60][32] = 5;
		tiles3[68][32] = 5;
		tiles3[54][38] = 7;
		tiles3[63][38] = 7;
		tiles3[71][38] = 7;
		fill2(52, 40, 52, 46, 28);
		fill2(53, 46, 57, 46, 28);
		fill2(57, 40, 57, 45, 28);
		fill2(53, 40, 56, 40, 28);
		fill2(60, 40, 60, 46, 28);
		fill2(61, 46, 65, 46, 28);
		fill2(65, 40, 65, 45, 28);
		fill2(61, 40, 64, 40, 28);
		fill2(68, 40, 68, 46, 28);
		fill2(69, 46, 73, 46, 28);
		fill2(73, 40, 73, 45, 28);
		fill2(69, 40, 72, 40, 28);
		tiles3[52][40] = 5;
		tiles3[60][40] = 5;
		tiles3[68][40] = 5;
		tiles2[54][40] = 7;
		tiles2[63][40] = 7;
		tiles2[71][40] = 7;
		fill(29, 72, 73, 72, 2);
		fill2(29, 65, 29, 71, 28);
		fill2(30, 65, 40, 65, 28);
		fill2(40, 66, 40, 71, 28);
		fill2(30, 71, 39, 71, 28);
		tiles3[29][65] = 5;
		tiles3[35][65] = 5;
		tiles2[35][71] = 7;
		tiles3[35][70] = 8;
		fill2(29, 73, 40, 73, 28);
		fill2(40, 74, 40, 79, 28);
		fill2(29, 79, 39, 79, 28);
		fill2(29, 74, 29, 78, 28);
		fill2(43, 65, 43, 71, 28);
		fill2(44, 65, 50, 65, 28);
		fill2(50, 66, 50, 71, 28);
		fill2(44, 71, 49, 71, 28);
		fill2(43, 73, 43, 79, 28);
		fill2(44, 79, 50, 79, 28);
		fill2(50, 73, 50, 78, 28);
		fill2(44, 73, 49, 73, 28);
		tiles3[43][65] = 5;
		tiles3[45][65] = 5;
		tiles3[29][73] = 5;
		tiles3[35][73] = 5;
		tiles3[43][73] = 5;
		tiles3[45][73] = 5;
		tiles2[35][73] = 7;
		tiles2[47][71] = 7;
		tiles2[47][73] = 7;
		fill2(52, 65, 52, 71, 28);
		fill2(53, 71, 58, 71, 28);
		fill2(59, 65, 59, 71, 28);
		fill2(53, 65, 58, 65, 28);
		fill2(52, 73, 52, 79, 28);
		fill2(53, 79, 59, 79, 28);
		fill2(59, 73, 59, 78, 28);
		fill2(53, 73, 58, 73, 28);
		fill2(62, 65, 62, 71, 28);
		fill2(63, 65, 73, 65, 28);
		fill2(73, 66, 73, 71, 28);
		fill2(63, 71, 72, 71, 28);
		fill2(62, 73, 62, 79, 28);
		fill2(63, 79, 73, 79, 28);
		fill2(73, 73, 73, 78, 28);
		fill2(63, 73, 72, 73, 28);
		tiles3[52][65] = 5;
		tiles3[54][65] = 5;
		tiles3[52][73] = 5;
		tiles3[54][73] = 5;
		tiles3[54][73] = 5;
		tiles3[62][65] = 5;
		tiles3[68][65] = 5;
		tiles3[62][73] = 5;
		tiles3[68][73] = 5;
		tiles2[55][71] = 7;
		tiles2[55][73] = 7;
		tiles2[67][71] = 7;
		tiles2[67][73] = 7;
		fill2(59, 49, 59, 55, 4);
		fill2(60, 55, 71, 55, 4);
		fill2(71, 49, 71, 54, 4);
		fill2(60, 49, 70, 49, 4);
		tiles3[59][49] = 5;
		tiles3[65][49] = 5;
		tiles3[66][49] = 5;
		tiles2[65][55] = 7;
		fill(29, 56, 73, 56, 2);
		tiles2[33][53] = 3;
		tiles2[44][50] = 3;
		tiles2[49][55] = 3;
		tiles2[47][62] = 3;
		tiles2[31][59] = 3;
		tiles2[38][83] = 3;
		tiles2[53][82] = 3;
		tiles2[53][82] = 3;
		tiles2[62][84] = 3;
		tiles2[69][81] = 3;
		tiles2[72][64] = 3;
		tiles2[60][61] = 3;
		tiles2[67][58] = 3;
		tiles2[56][50] = 3;
		tiles2[67][47] = 3;
		tiles2[45][30] = 3;
		tiles2[37][24] = 3;
		tiles2[44][19] = 3;
		tiles2[57][17] = 3;
		tiles2[60][21] = 3;
		tiles2[58][26] = 3;
		tiles2[64][30] = 3;
		tiles2[69][26] = 3;
		tiles2[69][26] = 3;
		tiles2[74][22] = 0;
		tiles2[28][22] = 0;
		tiles2[51][14] = 0;
		buildAltar(35, 49);
	}
	
	@Override
	public Point entranceEast(){
		return new Point(2190, 660);
	}
	
	@Override
	public Point entranceNorth(){
		return new Point(1530, 450);
	}
	
	@Override
	public Point entranceWest(){
		return new Point(870, 660);
	}
	
	@Override
	public Point entranceSouth(){
		return entranceWest();
	}
	
	public void placePortals(){
		portals.add(new Portal(new Point(2220, 660), "Ruff", null, null));
		portals.add(new Portal(new Point(840, 660), "Ruff", null, null, "Dark Forest"));
		portals.add(new Portal(new Point(1530, 420), "Ruff", null, null));
		portals.add(new Portal(new Point(2040, 630), "Ruff", new Point(180, 150), "Ruff shop 1"));
		portals.add(new Portal(new Point(1020, 630), "Ruff", new Point(180, 150), "Ruff shop 2"));
		portals.add(new Portal(new Point(930, 1140), "Ruff", new Point(90, 150), "Ruff smallHouse 3"));
		portals.add(new Portal(new Point(1170, 1140), "Ruff", new Point(90, 150), "Ruff smallHouse 4"));
		portals.add(new Portal(new Point(1440, 1140), "Ruff", new Point(90, 150), "Ruff smallHouse 5"));
		portals.add(new Portal(new Point(930, 1200), "Ruff", new Point(90, 30), "Ruff smallHouse 6"));
		portals.add(new Portal(new Point(1170, 1200), "Ruff", new Point(90, 30), "Ruff smallHouse 7"));
		portals.add(new Portal(new Point(1440, 1200), "Ruff", new Point(90, 30), "Ruff smallHouse 8"));
		portals.add(new Portal(new Point(1620, 1140), "Ruff", new Point(90, 150), "Ruff smallHouse 9"));
		portals.add(new Portal(new Point(1890, 1140), "Ruff", new Point(90, 150), "Ruff smallHouse 10"));
		portals.add(new Portal(new Point(2130, 1140), "Ruff", new Point(90, 150), "Ruff smallHouse 11"));
		portals.add(new Portal(new Point(1620, 1200), "Ruff", new Point(90, 30), "Ruff smallHouse 12"));
		portals.add(new Portal(new Point(1890, 1200), "Ruff", new Point(90, 30), "Ruff smallHouse 13"));
		portals.add(new Portal(new Point(2130, 1200), "Ruff", new Point(90, 30), "Ruff smallHouse 14"));
		portals.add(new Portal(new Point(1050, 2130), "Ruff", new Point(180, 150), "Ruff shop 15"));
		portals.add(new Portal(new Point(1050, 2190), "Ruff", new Point(180, 30), "Ruff largeHouse 16"));
		portals.add(new Portal(new Point(1410, 2130), "Ruff", new Point(120, 150), "Ruff normalHouse 17"));
		portals.add(new Portal(new Point(1410, 2190), "Ruff", new Point(120, 30), "Ruff normalHouse 18"));
		portals.add(new Portal(new Point(1650, 2130), "Ruff", new Point(120, 150), "Ruff normalHouse 19"));
		portals.add(new Portal(new Point(1650, 2190), "Ruff", new Point(120, 30), "Ruff normalHouse 20"));
		portals.add(new Portal(new Point(2010, 2130), "Ruff", new Point(180, 150), "Ruff largeHouse 21"));
		portals.add(new Portal(new Point(2010, 2190), "Ruff", new Point(180, 30), "Ruff largeHouse 22"));
		portals.add(new Portal(new Point(1950, 1650), "Ruff", new Point(180, 150), "Ruff shamanHouse 23"));
	}

	@Override
	public String getFightBackGround() {
		return null;
	}
	
	@Override
	public boolean allowRandomFights(){
		return false;
	}
	
	@Override
	public String getMusic(){
		return "ruff";
	}
}
