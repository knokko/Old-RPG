package nl.knokko.rpg.world.maps;

import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.monsters.*;
import nl.knokko.rpg.gui.GuiChat;
import nl.knokko.rpg.gui.GuiWorldMap;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.tiles.Chest;
import nl.knokko.rpg.utils.BackGrounds;
import nl.knokko.rpg.world.Conversation;
import nl.knokko.rpg.world.Portal;

public class MapGenLostPlains extends MapGenBase {

	public MapGenLostPlains() {
		super();
		build();
		placeChests();
		portals.add(new Portal(new Point(5130, 2100), "Lost Plains", null, null));
		portals.add(new Portal(new Point(930, 1680), "Lost Plains", null, null, "Lost Plains 2"));
		if(GuiChat.hasConversation(GuiChat.lost_plains_1) && Game.game.quests.hasQuest("miners"))
			conversations.add(new Conversation(GuiChat.lost_plains_1, new Point(5100, 2100)));
		else if(GuiChat.hasConversation(GuiChat.lost_plains_2) && !Game.game.quests.hasQuest("miners"))
			conversations.add(new Conversation(GuiChat.lost_plains_2, new Point(5100, 2100)));
	}

	@Override
	public int getXSize() {
		return 200;
	}

	@Override
	public int getYSize() {
		return 100;
	}

	@Override
	public String getName() {
		return "Lost Plains";
	}

	@Override
	public ArrayList<Entity> getEnemies(){
		ArrayList<Entity> enemies = new ArrayList<Entity>();
		Game g = Game.game;
		Point p = new Point(-10000, -10000);
		enemies.add(new Tarr(g, p, 8));
		enemies.add(new Tarr(g, p, 7));
		enemies.add(new Frode(g, p, 7));
		enemies.add(new Frode(g, p, 8));
		enemies.add(new Frode(g, p, 9));
		enemies.add(new Bird(g, p, 9));
		enemies.add(new Bird(g, p, 10));
		return enemies;
	}

	@Override
	public int getMaxEnemies() {
		return 4;
	}

	@Override
	public Point getStartPoint(MapGenBase previous) {
		int distanceX = GuiWorldMap.positionForMap(previous).x - GuiWorldMap.positionForMap(this).x;
		if(distanceX < 0)
			return new Point(960, 1680);
		return new Point(5100, 2100);
	}
	
	@Override
	public String getFightBackGround(){
		return BackGrounds.plains;
	}
	
	@Override
	public String getMusic(){
		return "lost plains";
	}
	
	protected void placeChests(){
		placeChest(new Chest(7, new Point(3810, 2460)).addItemStack(Items.potion, 2).addItem(Items.ether));
		placeChest(new Chest(8, new Point(3630, 1470)).setMoney(35));
		placeChest(new Chest(9, new Point(4260, 1710)).setMoney(40));
		placeChest(new Chest(10, new Point(2670, 2100)).setMoney(30).addItemStack(Items.potion, 3));
		placeChest(new Chest(11, new Point(4350, 660)).setMoney(100).addItemStack(Items.ether, 2));
		placeChest(new Chest(12, new Point(4170, 600)).addItemStack(Items.potion, 3));
		placeChest(new Chest(13, new Point(4830, 1380)).setMoney(50).addItem(Items.potion));
		placeChest(new Chest(14, new Point(1410, 840)).setMoney(20).addItem(Items.ether));
		placeChest(new Chest(15, new Point(1620, 840)).setMoney(110));
		placeChest(new Chest(16, new Point(4020, 720)).addItem(Items.potion).setMoney(80));
		placeChest(new Chest(17, new Point(3030, 450)).addItem(Items.mantidBlade).addItemStack(Items.wood, 5));
		placeChest(new Chest(18, new Point(1080, 1080)).addItem(Items.woodAxe).setMoney(43));
		placeChest(new Chest(19, new Point(1050, 1830)).setMoney(115));
	}
	
	public void build(){
		fill2(171, 0, 199, 99, 3);
		fill2(170, 0, 170, 46, 3);
		fill2(170, 58, 170, 64, 3);
		fill2(170, 94, 170, 99, 3);
		fill(0, 0, 199, 99, 1);
		fill(191, 78, 199, 81, 12);
		fill2(191, 78, 199, 80, 0);
		fill(181, 79, 190, 82, 12);
		fill2(181, 79, 190, 81, 0);
		fill(162, 80, 180, 83, 12);
		fill2(162, 80, 180, 82, 0);
		fill(146, 81, 161, 84, 12);
		fill(128, 82, 145, 85, 12);
		fill(113, 83, 127, 86, 12);
		fill(97, 82, 112, 85, 12);
		tiles2[171][70] = 0;
		fill(154, 70, 171, 70, 14);
		fill(154, 69, 154, 72, 14);
		fill(140, 73, 154, 73, 14);
		fill(135, 67, 144, 67, 14);
		fill(129, 50, 129, 65, 14);
		fill(129, 49, 140, 49, 14);
		fill(129, 66, 135, 66, 14);
		fill(144, 68, 154, 68, 14);
		fill(132, 74, 140, 74, 14);
		fill(132, 72, 132, 77, 14);
		fill(124, 72, 131, 72, 14);
		fill(127, 77, 131, 77, 14);
		fill(127, 78, 127, 81, 14);
		fill(124, 68, 124, 71, 14);
		fill(115, 68, 123, 68, 14);
		fill(114, 60, 114, 70, 14);
		fill(105, 60, 113, 60, 14);
		fill(104, 55, 104, 60, 14);
		fill(92, 55, 103, 55, 14);
		fill(85, 56, 92, 56, 14);
		fill(73, 57, 85, 57, 14);
		fill(90, 70, 113, 70, 14);
		fill(140, 40, 140, 48, 14);
		fill(140, 40, 157, 40, 14);
		fill(157, 41, 157, 54, 14);
		fill(157, 54, 165, 54, 14);
		fill(165, 22, 165, 54, 14);
		fill(150, 22, 165, 22, 14);
		tiles2[164][73] = 3;
		tiles2[168][66] = 3;
		tiles2[152][70] = 3;
		tiles2[155][63] = 3;
		tiles2[160][77] = 3;
		tiles2[166][76] = 3;
		fill2(120, 60, 144, 60, 15);
		fill2(145, 45, 145, 59, 16);
		fill2(120, 35, 120, 59, 16);
		fill2(145, 45, 154, 45, 15);
		fill2(120, 35, 160, 35, 15);
		fill2(154, 46, 154, 57, 16);
		fill2(160, 36, 160, 50, 16);
		tiles2[161][50] = 15;
		tiles2[160][50] = 17;
		tiles2[162][50] = 17;
		fill2(162, 30, 162, 49, 16);
		fill2(155, 57, 168, 57, 15);
		tiles2[154][57] = 17;
		tiles2[168][57] = 17;
		fill2(168, 14, 168, 56, 16);
		fill2(140, 14, 168, 14, 15);
		fill2(140, 30, 162, 30, 15);
		fill2(140, 15, 140, 29, 16);
		tiles2[140][30] = 17;
		tiles2[145][60] = 17;
		tiles2[120][60] = 17;
		tiles2[129][60] = 0;
		fill2(85, 65, 110, 65, 15);
		fill2(85, 75, 110, 75, 15);
		fill2(85, 66, 85, 74, 16);
		fill2(110, 66, 110, 74, 16);
		tiles2[110][70] = 0;
		tiles2[85][75] = 17;
		tiles2[110][75] = 17;
		fill(87, 81, 96, 84, 12);
		fill(76, 82, 86, 85, 12);
		fill2(102, 51, 112, 51, 15);
		fill2(92, 51, 100, 51, 15);
		fill2(113, 30, 113, 50, 16);
		tiles2[113][51] = 17;
		fill2(91, 35, 91, 50, 16);
		fill2(113, 29, 138, 29, 15);
		tiles2[138][29] = 17;
		fill2(138, 19, 138, 28, 16);
		fill2(119, 18, 138, 18, 15);
		tiles2[118][18] = 17;
		fill2(118, 15, 118, 17, 16);
		fill2(100, 14, 118, 14, 15);
		fill2(100, 15, 100, 24, 16);
		tiles2[100][25] = 17;
		fill2(61, 25, 99, 25, 15);
		fill2(75, 34, 91, 34, 15);
		fill2(75, 35, 75, 43, 16);
		tiles2[75][44] = 17;
		fill2(45, 44, 74, 44, 15);
		fill2(50, 34, 69, 34, 15);
		fill2(50, 25, 60, 25, 15);
		fill2(50, 26, 50, 33, 16);
		tiles2[50][34] = 17;
		fill2(44, 26, 44, 43, 16);
		tiles2[44][44] = 17;
		fill2(44, 25, 49, 25, 15);
		tiles2[91][51] = 17;
		fill(70, 81, 75, 84, 12);
		fill(61, 80, 69, 83, 12);
		fill(52, 79, 60, 82, 12);
		fill(43, 78, 51, 81, 12);
		fill(34, 79, 42, 82, 12);
		fill(23, 80, 33, 83, 12);
		fill(15, 79, 22, 82, 12);
		fill(6, 78, 14, 81, 12);
		fill(0, 77, 5, 80, 12);
		fill(63, 58, 73, 58, 14);
		fill(55, 59, 63, 59, 14);
		fill(50, 58, 55, 58, 14);
		fill(43, 57, 50, 57, 14);
		fill(33, 56, 43, 56, 14);
		fill2(31, 54, 41, 54, 15);
		fill2(43, 54, 52, 54, 15);
		tiles2[31][54] = 17;
		tiles2[52][54] = 17;
		fill2(31, 35, 31, 53, 16);
		fill2(52, 48, 52, 53, 16);
		fill2(42, 47, 52, 47, 15);
		tiles2[41][47] = 17;
		fill2(41, 35, 41, 46, 16);
		fill2(31, 34, 41, 34, 15);
		fill2(55, 65, 61, 65, 15);
		fill2(64, 65, 73, 65, 15);
		fill2(73, 66, 73, 76, 16);
		tiles2[73][77] = 17;
		fill2(32, 77, 72, 77, 15);
		tiles2[31][77] = 17;
		fill2(31, 58, 31, 76, 16);
		fill2(31, 58, 39, 58, 15);
		fill2(39, 59, 39, 64, 16);
		fill2(40, 65, 54, 65, 15);
		tiles2[39][65] = 17;
		tiles2[30][55] = 1;
		tiles2[30][56] = 1;
		tiles2[30][57] = 1;
		tiles2[47][46] = 3;
		tiles2[48][45] = 3;
		tiles2[151][56] = 3;
		tiles2[148][50] = 3;
		tiles2[138][61] = 3;
		tiles2[133][73] = 3;
		tiles2[125][79] = 3;
		tiles2[117][73] = 3;
		tiles2[109][64] = 3;
		tiles2[117][53] = 3;
		tiles2[150][32] = 3;
		tiles2[132][43] = 3;
		tiles2[134][55] = 3;
		tiles2[108][50] = 3;
		tiles2[95][39] = 3;
		tiles2[104][31] = 3;
		tiles2[110][21] = 3;
		tiles2[120][26] = 3;
		tiles2[135][20] = 3;
		tiles2[92][27] = 3;
		tiles2[75][32] = 3;
		tiles2[54][40] = 3;
		tiles2[63][51] = 3;
		tiles2[74][63] = 3;
		tiles2[62][73] = 3;
		tiles2[41][68] = 3;
		tiles2[44][59] = 3;
		tiles2[90][77] = 3;
		tiles2[93][60] = 3;
		tiles2[82][42] = 3;
	}
}
