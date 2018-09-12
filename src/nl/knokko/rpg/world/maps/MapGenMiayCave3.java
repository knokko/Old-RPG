package nl.knokko.rpg.world.maps;

import java.awt.*;
import java.util.ArrayList;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.monsters.Bat;
import nl.knokko.rpg.entities.monsters.Hotclaw;
import nl.knokko.rpg.entities.monsters.Spider;
import nl.knokko.rpg.entities.monsters.Troll;
import nl.knokko.rpg.entities.monsters.boss.Bosses;
import nl.knokko.rpg.entities.monsters.boss.FireDragon;
import nl.knokko.rpg.entities.monsters.boss.Recking;
import nl.knokko.rpg.gui.GuiChat;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.items.SpecialItems;
import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.tiles.Chest;
import nl.knokko.rpg.utils.BackGrounds;
import nl.knokko.rpg.world.Conversation;
import nl.knokko.rpg.world.Portal;

public class MapGenMiayCave3 extends MapGenBase {

	public MapGenMiayCave3(){
		super();
		build();
		placeChests();
		placePortals();
		spawnBosses();
		if(GuiChat.hasConversation(GuiChat.miay_cave_3) && MainClass.quests.hasQuest("miners"))
			conversations.add(new Conversation(GuiChat.miay_cave_3, new Point(3240, 5460)));
	}

	@Override
	public int getXSize(){
		return 200;
	}

	@Override
	public int getYSize(){
		return 200;
	}

	@Override
	public Color getBackGroundColor(){
		return Color.BLACK;
	}

	@Override
	public String getName(){
		return "Miay Cave Layer 3";
	}

	@Override
	public ArrayList<Entity> getEnemies(){
		ArrayList<Entity> enemies = new ArrayList<Entity>();
		Point p = new Point();
		enemies.add(new Troll(p, 18));
		enemies.add(new Troll(p, 19));
		enemies.add(new Troll(p, 20));
		enemies.add(new Spider(p, 18));
		enemies.add(new Spider(p, 19));
		enemies.add(new Bat(p, 18));
		enemies.add(new Bat(p, 19));
		enemies.add(new Hotclaw(p, 15));
		return enemies;
	}

	@Override
	public int getMaxEnemies(){
		return 4;
	}

	@Override
	public Point getStartPoint(MapGenBase old){
		return new Point();
	}

	public void build(){
		tiles[108][182] = 29;
		fill2(102, 180, 102, 185, 30);
		fill2(103, 176, 103, 180, 30);
		fill2(104, 174, 104, 176, 30);
		fill2(105, 174, 107, 174, 30);
		fill2(107, 173, 111, 173, 30);
		fill2(111, 174, 113, 174, 30);
		fill2(114, 174, 114, 177, 30);
		fill2(113, 177, 113, 181, 30);
		fill2(112, 181, 112, 185, 30);
		fill2(101, 185, 101, 191, 30);
		fill2(111, 185, 111, 188, 30);
		fill2(110, 188, 110, 192, 30);
		fill2(100, 191, 100, 193, 30);
		fill2(109, 192, 109, 194, 30);
		fill2(108, 194, 108, 195, 30);
		fill2(106, 196, 108, 196, 30);
		fill2(95, 193, 99, 193, 30);
		fill2(102, 197, 106, 197, 30);
		fill2(97, 198, 102, 198, 30);
		fill2(91, 199, 97, 199, 30);
		fill2(92, 192, 95, 192, 30);
		fill2(89, 191, 92, 191, 30);
		fill2(86, 198, 91, 198, 30);
		fill2(87, 190, 89, 190, 30);
		fill2(82, 197, 86, 197, 30);
		fill2(84, 189, 87, 189, 30);
		fill2(75, 196, 82, 196, 30);
		fill2(69, 197, 75, 197, 30);
		fill2(83, 187, 83, 189, 30);
		fill2(82, 183, 82, 187, 30);
		fill2(74, 183, 74, 185, 30);
		fill2(73, 185, 73, 187, 30);
		fill2(71, 188, 73, 188, 30);
		fill2(70, 188, 70, 191, 30);
		fill2(67, 192, 70, 192, 30);
		fill2(64, 198, 69, 198, 30);
		fill2(58, 199, 64, 199, 30);
		fill2(62, 193, 67, 193, 30);
		fill(104, 174, 113, 181, 29);
		fill(102, 180, 108, 196, 29);
		fill(109, 182, 111, 188, 29);
		fill(109, 189, 109, 191, 29);
		fill(86, 193, 101, 198, 29);
		tiles[101][192] = 29;
		fill2(75, 179, 75, 183, 30);
		fill2(83, 175, 83, 183, 30);
		fill2(76, 176, 76, 179, 30);
		fill2(82, 171, 82, 175, 30);
		fill2(75, 173, 75, 176, 30);
		fill2(81, 168, 81, 171, 30);
		fill2(80, 166, 80, 168, 30);
		fill2(74, 171, 74, 173, 30);
		fill2(79, 165, 79, 166, 30);
		fill2(78, 164, 78, 165, 30);
		fill2(75, 164, 77, 164, 30);
		fill2(71, 163, 75, 163, 30);
		fill2(69, 171, 73, 171, 30);
		fill2(66, 172, 69, 172, 30);
		fill2(66, 162, 71, 162, 30);
		fill2(62, 161, 66, 161, 30);
		fill2(62, 173, 66, 173, 30);
		fill2(58, 174, 62, 174, 30);
		fill2(58, 160, 62, 160, 30);
		fill2(50, 175, 58, 175, 30);
		fill2(50, 159, 58, 159, 30);
		fill2(46, 160, 50, 160, 30);
		fill2(46, 174, 50, 174, 30);
		fill2(42, 173, 46, 173, 30);
		fill2(38, 172, 42, 172, 30);
		fill2(42, 161, 46, 161, 30);
		fill2(38, 162, 42, 162, 30);
		fill2(33, 163, 38, 163, 30);
		fill2(34, 171, 38, 171, 30);
		fill2(29, 170, 34, 170, 30);
		fill2(26, 169, 29, 169, 30);
		fill2(31, 162, 33, 162, 30);
		fill2(30, 161, 30, 162, 30);
		fill2(29, 158, 29, 161, 30);
		fill2(24, 168, 26, 168, 30);
		fill2(23, 167, 24, 167, 30);
		fill2(22, 163, 22, 167, 30);
		fill2(21, 160, 21, 163, 30);
		fill2(20, 156, 20, 160, 30);
		fill2(28, 153, 28, 158, 30);
		fill(70, 188, 83, 196, 29);
		fill(84, 190, 86, 196, 29);
		fill(87, 191, 91, 192, 29);
		fill(64, 193, 69, 197, 29);
		fill(76, 165, 79, 187, 29);
		fill(80, 169, 81, 187, 29);
		fill(82, 176, 82, 182, 29);
		fill(74, 184, 75, 187, 29);
		fill(47, 162, 61, 172, 31);
		fill(47, 160, 61, 161, 31);
		fill(47, 173, 61, 174, 31);
		fill(62, 162, 65, 172, 31);
		fill(43, 162, 46, 172, 31);
		fill(42, 165, 42, 169, 31);
		fill(66, 165, 66, 169, 31);
		fill(67, 163, 75, 171, 29);
		tiles[75][172] = 29;
		fill(66, 170, 66, 171, 29);
		fill(66, 163, 66, 164, 29);
		fill(42, 167, 66, 167, 29);
		fill(34, 163, 41, 171, 29);
		fill(42, 163, 42, 164, 29);
		fill(42, 170, 42, 171, 29);
		fill(26, 162, 33, 169, 29);
		fill2(19, 151, 19, 156, 30);
		fill2(27, 151, 27, 153, 30);
		fill2(18, 149, 18, 151, 30);
		fill2(17, 147, 17, 149, 30);
		fill2(28, 149, 28, 151, 30);
		fill2(29, 147, 29, 149, 30);
		fill2(16, 144, 16, 147, 30);
		fill2(30, 144, 30, 147, 30);
		fill2(15, 138, 15, 144, 30);
		fill2(31, 138, 31, 144, 30);
		fill2(16, 135, 16, 138, 30);
		fill2(30, 135, 30, 138, 30);
		fill2(17, 133, 17, 135, 30);
		fill2(29, 133, 29, 135, 30);
		fill2(28, 131, 28, 133, 30);
		fill2(18, 131, 18, 133, 30);
		fill2(19, 129, 19, 131, 30);
		fill2(27, 129, 27, 131, 30);
		fill2(26, 128, 26, 129, 30);
		tiles2[25][128] = 30;
		fill2(20, 128, 20, 129, 30);
		tiles2[21][128] = 30;
		fill2(21, 127, 25, 127, 30);
		fill(22, 151, 27, 167, 29);
		fill(28, 159, 28, 161, 29);
		fill(20, 151, 21, 159, 29);
		fill(18, 131, 28, 150, 29);
		fill(16, 136, 17, 146, 29);
		fill(29, 136, 30, 146, 29);
		fill(20, 128, 26, 130, 29);
		fill(18, 139, 21, 143, 31);
		fill(25, 139, 28, 143, 31);
		fill(19, 138, 20, 138, 31);
		fill(17, 140, 17, 142, 31);
		fill(22, 140, 22, 142, 31);
		fill(19, 144, 20, 144, 31);
		fill(24, 140, 24, 142, 31);
		fill(26, 144, 27, 144, 31);
		fill(29, 140, 29, 142, 31);
		fill(26, 138, 27, 138, 31);
		fill2(59, 192, 62, 192, 30);
		fill2(55, 191, 59, 191, 30);
		fill2(51, 190, 55, 190, 30);
		fill2(53, 198, 58, 198, 30);
		fill2(47, 197, 53, 197, 30);
		fill2(48, 189, 51, 189, 30);
		fill2(44, 188, 48, 188, 30);
		fill2(40, 196, 47, 196, 30);
		fill2(34, 195, 40, 195, 30);
		fill2(39, 187, 44, 187, 30);
		fill2(28, 194, 34, 194, 30);
		fill2(33, 186, 39, 186, 30);
		fill2(29, 185, 33, 185, 30);
		fill2(26, 184, 29, 184, 30);
		fill2(23, 183, 26, 183, 30);
		fill2(22, 182, 23, 182, 30);
		tiles2[21][182] = 30;
		tiles2[21][181] = 30;
		fill2(20, 179, 20, 181, 30);
		fill2(23, 193, 28, 193, 30);
		fill2(12, 179, 12, 182, 30);
		fill2(11, 182, 11, 184, 30);
		fill2(10, 184, 10, 186, 30);
		fill2(9, 186, 9, 188, 30);
		fill2(8, 188, 8, 191, 30);
		fill2(20, 194, 23, 194, 30);
		fill2(17, 195, 20, 195, 30);
		fill2(15, 196, 17, 196, 30);
		fill2(12, 197, 15, 197, 30);
		fill2(9, 198, 11, 198, 30);
		fill2(4, 199, 9, 199, 30);
		fill2(4, 197, 4, 198, 30);
		fill2(3, 194, 3, 197, 30);
		fill2(4, 193, 4, 194, 30);
		fill2(5, 192, 8, 192, 30);
		tiles2[5][193] = 30;
		fill2(11, 173, 11, 179, 30);
		fill2(19, 175, 19, 179, 30);
		fill2(10, 167, 10, 173, 30);
		fill2(18, 170, 18, 175, 30);
		fill2(17, 165, 17, 170, 30);
		fill2(9, 163, 9, 167, 30);
		fill2(16, 161, 16, 165, 30);
		fill2(8, 158, 8, 163, 30);
		fill2(15, 156, 15, 161, 30);
		fill2(14, 153, 14, 156, 30);
		fill2(7, 151, 7, 158, 30);
		fill2(13, 149, 13, 153, 30);
		fill2(6, 149, 6, 151, 30);
		fill2(5, 147, 5, 149, 30);
		fill2(12, 145, 12, 149, 30);
		fill2(11, 138, 11, 145, 30);
		fill2(4, 143, 4, 147, 30);
		fill2(3, 139, 3, 143, 30);
		fill2(4, 134, 4, 139, 30);
		fill2(12, 131, 12, 138, 30);
		fill2(5, 127, 5, 134, 30);
		fill2(13, 124, 13, 131, 30);
		fill2(6, 122, 6, 127, 30);
		fill2(14, 121, 14, 124, 30);
		fill2(15, 120, 15, 121, 30);
		tiles2[16][120] = 30;
		fill2(16, 119, 18, 119, 30);
		fill2(7, 119, 7, 122, 30);
		fill2(8, 116, 8, 119, 30);
		fill2(9, 115, 9, 116, 30);
		fill2(10, 114, 10, 115, 30);
		fill2(11, 114, 18, 114, 30);
		fill2(18, 113, 26, 113, 30);
		fill2(18, 118, 28, 118, 30);
		fill2(26, 112, 30, 112, 30);
		fill2(30, 111, 34, 111, 30);
		fill2(28, 117, 38, 117, 30);
		fill2(34, 110, 37, 110, 30);
		fill2(37, 109, 42, 109, 30);
		fill2(38, 116, 45, 116, 30);
		fill(53, 193, 63, 198, 29);
		fill(47, 189, 52, 196, 29);
		tiles[52][189] = 0;
		fill(53, 191, 58, 192, 29);
		fill(34, 188, 46, 195, 29);
		fill(9, 186, 33, 193, 29);
		fill(4, 193, 11, 198, 29);
		fill(12, 194, 19, 195, 29);
		fill(12, 196, 14, 196, 29);
		fill(34, 187, 38, 187, 29);
		fill(12, 166, 17, 185, 29);
		tiles[11][185] = 29;
		fill(18, 181, 21, 185, 29);
		fill(18, 176, 19, 180, 29);
		fill(22, 184, 28, 185, 29);
		tiles[22][183] = 29;
		fill(9, 153, 14, 167, 29);
		fill(11, 168, 11, 172, 29);
		fill(15, 162, 15, 165, 29);
		fill(7, 145, 12, 152, 29);
		fill(8, 153, 8, 157, 29);
		fill(4, 134, 11, 144, 29);
		fill(5, 145, 6, 148, 29);
		fill(6, 122, 12, 133, 29);
		fill(8, 116, 15, 121, 29);
		fill(13, 122, 13, 123, 29);
		fill(11, 115, 28, 118, 29);
		fill(19, 113, 28, 114, 29);
		fill2(43, 109, 66, 109, 30);
		fill2(46, 116, 66, 116, 30);
		fill2(66, 85, 66, 108, 30);
		fill2(66, 117, 66, 139, 30);
		fill2(74, 109, 96, 109, 30);
		fill2(74, 85, 74, 108, 30);
		fill2(67, 85, 73, 85, 30);
		fill2(74, 116, 96, 116, 30);
		fill2(96, 110, 96, 115, 30);
		fill2(74, 117, 74, 139, 30);
		fill2(67, 139, 73, 139, 30);
		fill(67, 86, 73, 138, 29);
		fill(38, 110, 95, 115, 29);
		fill(29, 112, 37, 116, 29);
		fill(35, 111, 37, 111, 29);
	}

	@Override
	public String getFightBackGround() {
		return BackGrounds.red_cave;
	}
	
	@Override
	public String getMusic(){
		return "miay cave 3";
	}
	
	public void placeChests(){
		placeChest(new Chest(65, new Point(150, 5940)).addItems(Items.orangeEther, Items.orangePotion));
		placeChest(new Chest(66, new Point(2850, 3390)).addItem(Items.fireTooth));
		placeChest(new Chest(67, new Point(2100, 2580)).setMoney(500));
		placeChest(new Chest(68, new Point(2100, 4140)).addItemStack(Items.miayGem, 5));
	}
	
	public void placePortals(){
		tiles2[108][182] = 24;
		portals.add(new Portal(new Point(3240, 5460), "Miay Cave Layer 3", new Point(3240, 5460), "Miay Cave Layer 2"));
	}
	
	public void spawnBosses(){
		if(!MainClass.specialItems.hasItem(SpecialItems.DEMONIC_PEARL))
			entities.add(new Recking(new Point(690, 3990), 15));
		if(Bosses.bosses.contains("fire dragon 1"))
			entities.add(new FireDragon(new Point(1950, 3360), 10));
	}
}
