package nl.knokko.rpg.world.maps;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.monsters.Goblin;
import nl.knokko.rpg.entities.monsters.boss.Bosses;
import nl.knokko.rpg.entities.monsters.boss.GoblinKing;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.items.SpecialItems;
import nl.knokko.rpg.tiles.Chest;
import nl.knokko.rpg.utils.BackGrounds;
import nl.knokko.rpg.world.Portal;

public class MapGenGoblinTomb extends MapGenBase {

	public MapGenGoblinTomb(){
		super();
		build();
		placeChests();
		placePortals();
		if(Bosses.bosses.contains("goblin king"))
			entities.add(new GoblinKing(new Point(4590, 4080), 50));
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
		return "Goblin Tomb";
	}

	@Override
	public ArrayList<Entity> getEnemies(){
		ArrayList<Entity> enemies = new ArrayList<Entity>();
		Point p = new Point(-10000, -10000);
		enemies.add(new Goblin(p, 45));
		enemies.add(new Goblin(p, 46));
		enemies.add(new Goblin(p, 47));
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
		fill(56, 18, 60, 39, 34);
		fill(25, 18, 55, 22, 34);
		fill2(55, 23, 55, 39, 49);
		fill2(55, 40, 61, 40, 49);
		fill2(61, 23, 61, 39, 49);
		fill2(30, 23, 54, 23, 49);
		fill(25, 23, 29, 51, 34);
		fill(30, 47, 48, 51, 34);
		fill(1, 36, 24, 40, 34);
		fill(25, 1, 29, 17, 34);
		fill(44, 52, 48, 72, 34);
		fill2(30, 24, 30, 46, 49);
		fill2(31, 46, 49, 46, 49);
		fill2(30, 17, 60, 17, 49);
		fill2(30, 0, 30, 16, 49);
		fill2(24, 0, 29, 0, 49);
		fill2(24, 1, 24, 35, 49);
		fill2(0, 35, 23, 35, 49);
		fill2(0, 36, 0, 41, 49);
		fill2(1, 41, 24, 41, 49);
		fill2(24, 42, 24, 52, 49);
		fill2(25, 52, 43, 52, 49);
		fill2(43, 53, 43, 67, 49);
		fill2(49, 47, 49, 73, 49);
		tiles2[58][37] = 24;
		fill(13, 68, 43, 72, 34);
		fill(13, 73, 17, 100, 34);
		fill(1, 96, 12, 100, 34);
		fill(44, 73, 48, 93, 34);
		fill(49, 89, 76, 93, 34);
		fill(61, 18, 93, 22, 34);
		fill(89, 23, 93, 47, 34);
		fill(64, 43, 88, 47, 34);
		fill(89, 48, 93, 57, 34);
		fill(94, 53, 116, 57, 34);
		fill(72, 70, 76, 88, 34);
		fill(112, 58, 116, 64, 34);
		fill(59, 64, 131, 78, 34);
		fill2(49, 74, 49, 88, 49);
		fill2(50, 88, 71, 88, 49);
		fill2(71, 79, 71, 87, 49);
		fill2(58, 79, 70, 79, 49);
		fill2(58, 63, 58, 78, 49);
		fill2(59, 63, 111, 63, 49);
		fill2(111, 58, 111, 62, 49);
		fill2(88, 58, 110, 58, 49);
		fill2(88, 48, 88, 57, 49);
		fill2(63, 48, 87, 48, 49);
		fill2(63, 42, 63, 47, 49);
		fill2(64, 42, 88, 42, 49);
		fill2(88, 23, 88, 41, 49);
		fill2(62, 23, 87, 23, 49);
		fill2(0, 95, 0, 101, 49);
		fill2(1, 95, 12, 95, 49);
		fill2(1, 101, 18, 101, 49);
		fill2(18, 73, 18, 100, 49);
		fill2(12, 67, 12, 94, 49);
		fill2(13, 67, 42, 67, 49);
		fill2(19, 73, 43, 73, 49);
		fill2(43, 74, 43, 94, 49);
		fill2(44, 94, 77, 94, 49);
		fill2(77, 79, 77, 93, 49);
		fill(97, 79, 101, 105, 34);
		fill(82, 101, 96, 105, 34);
		fill(82, 106, 86, 123, 34);
		fill(132, 73, 162, 77, 34);
		fill(158, 78, 162, 99, 34);
		fill(129, 95, 157, 99, 34);
		fill(129, 100, 133, 114, 34);
		fill(129, 115, 155, 119, 34);
		fill(151, 120, 155, 133, 34);
		fill(146, 134, 160, 157, 34);
		fill2(156, 133, 161, 133, 49);
		fill2(161, 134, 161, 158, 49);
		fill2(145, 158, 160, 158, 49);
		fill2(145, 133, 145, 157, 49);
		fill2(146, 133, 150, 133, 49);
		fill2(150, 120, 150, 132, 49);
		fill2(128, 120, 149, 120, 49);
		fill2(156, 114, 156, 132, 49);
		fill2(78, 79, 96, 79, 49);
		fill2(96, 80, 96, 100, 49);
		fill2(102, 79, 102, 106, 49);
		fill2(87, 106, 101, 106, 49);
		fill2(87, 107, 87, 124, 49);
		fill2(81, 124, 86, 124, 49);
		fill2(81, 100, 81, 123, 49);
		fill2(82, 100, 95, 100, 49);
		fill2(117, 52, 117, 63, 49);
		fill2(94, 52, 116, 52, 49);
		fill2(61, 17, 94, 17, 49);
		fill2(94, 18, 94, 51, 49);
		fill2(118, 63, 132, 63, 49);
		fill2(132, 64, 132, 72, 49);
		fill2(103, 79, 132, 79, 49);
		fill2(132, 78, 157, 78, 49);
		fill2(157, 79, 157, 94, 49);
		fill2(128, 94, 156, 94, 49);
		fill2(128, 95, 128, 119, 49);
		fill2(134, 114, 155, 114, 49);
		fill2(134, 100, 134, 113, 49);
		fill2(135, 100, 163, 100, 49);
		fill2(163, 78, 163, 99, 49);
		fill2(133, 72, 163, 72, 49);
		fill(77, 181, 81, 199, 34);
		fill(82, 194, 120, 198, 34);
		fill(116, 161, 120, 193, 34);
		fill(116, 159, 171, 163, 34);
		tiles2[79][183] = 24;
		fill(82, 181, 115, 185, 49);
		fill2(149, 147, 149, 155, 49);
		fill2(150, 147, 151, 147, 49);
		fill2(155, 147, 157, 147, 49);
		fill2(157, 148, 157, 155, 49);
		fill2(150, 155, 156, 155, 49);
		fill(167, 164, 171, 183, 34);
		fill(138, 179, 166, 183, 34);
		fill2(137, 178, 166, 178, 49);
		fill2(137, 179, 137, 184, 49);
		fill2(138, 184, 172, 184, 49);
		fill2(166, 164, 166, 177, 49);
		fill2(172, 158, 172, 183, 49);
		fill2(162, 158, 171, 158, 49);
		fill2(121, 164, 165, 164, 49);
		fill2(115, 158, 144, 158, 49);
		fill2(115, 159, 115, 180, 49);
		fill2(121, 165, 121, 199, 49);
		fill2(76, 199, 120, 199, 49);
		tiles2[76][199] = 49;
		fill2(82, 186, 82, 193, 49);
		fill2(83, 193, 115, 193, 49);
		fill2(115, 186, 115, 192, 49);
		fill(82, 181, 115, 185, 34);
		fill(92, 164, 96, 180, 34);
		fill(75, 164, 91, 168, 34);
		fill(75, 141, 79, 163, 34);
		fill(80, 141, 126, 145, 34);
		fill(122, 108, 126, 140, 34);
		fill(127, 125, 146, 129, 34);
		fill(97, 164, 110, 168, 34);
		fill(106, 154, 110, 163, 34);
		fill(58, 181, 76, 185, 34);
		fill(58, 154, 62, 180, 34);
		fill(103, 80, 127, 107, 34);
		fill(45, 154, 57, 158, 34);
		fill(45, 130, 50, 153, 34);
		fill(51, 130, 94, 134, 34);
		fill(90, 113, 94, 129, 34);
		fill(95, 113, 102, 117, 34);
		fill(103, 108, 107, 117, 34);
		fill(22, 154, 44, 158, 34);
		fill(22, 159, 26, 174, 34);
		fill(58, 108, 62, 129, 34);
		fill2(76, 186, 76, 198, 49);
		fill2(57, 186, 75, 186, 49);
		fill2(57, 159, 57, 185, 49);
		fill2(51, 153, 63, 153, 49);
		fill2(63, 154, 63, 180, 49);
		fill2(64, 180, 91, 180, 49);
		fill2(91, 169, 91, 179, 49);
		fill2(74, 169, 90, 169, 49);
		fill2(74, 140, 74, 168, 49);
		fill2(80, 146, 80, 163, 49);
		fill2(81, 163, 105, 163, 49);
		fill2(105, 153, 105, 162, 49);
		fill2(106, 153, 111, 153, 49);
		fill2(111, 154, 111, 169, 49);
		fill2(97, 169, 110, 169, 49);
		fill2(97, 170, 97, 180, 49);
		fill2(98, 180, 114, 180, 49);
		fill2(83, 186, 114, 186, 49);
		fill2(27, 159, 56, 159, 49);
		fill2(27, 160, 27, 175, 49);
		fill2(21, 175, 26, 175, 49);
		fill2(21, 153, 21, 174, 49);
		fill2(22, 153, 44, 153, 49);
		fill2(44, 129, 44, 152, 49);
		fill2(45, 129, 57, 129, 49);
		fill2(57, 107, 57, 128, 49);
		fill2(58, 107, 63, 107, 49);
		fill2(63, 108, 63, 129, 49);
		fill2(64, 129, 89, 129, 49);
		fill2(89, 112, 89, 128, 49);
		fill2(90, 112, 102, 112, 49);
		fill2(102, 107, 102, 111, 49);
		fill2(108, 108, 121, 108, 49);
		fill2(127, 108, 127, 124, 49);
		fill2(128, 124, 147, 124, 49);
		fill2(147, 125, 147, 130, 49);
		fill2(127, 130, 146, 130, 49);
		fill2(127, 131, 127, 146, 49);
		fill2(81, 146, 126, 146, 49);
		fill2(75, 140, 121, 140, 49);
		fill2(121, 109, 121, 139, 49);
		fill2(128, 80, 128, 93, 49);
		fill2(108, 109, 108, 118, 49);
		fill2(95, 118, 107, 118, 49);
		fill2(95, 119, 95, 135, 49);
		fill2(51, 135, 94, 135, 49);
		fill2(51, 136, 51, 152, 49);
		fill2(163, 73, 163, 77, 49);
	}

	@Override
	public String getMusic() {
		return "goblin tomb";
	}

	@Override
	public String getFightBackGround() {
		return BackGrounds.goblin_tomb_2;
	}
	
	public void placeChests(){
		placeChest(50, new Chest(144, new Point(4590, 4530), SpecialItems.ANCIENT_SPELL_BOOK));
		placeChest(50, new Chest(145, new Point(810, 90)).setMoney(324));
		placeChest(50, new Chest(146, new Point(90, 1140)).setMoney(120).addItem(Items.gold));
		placeChest(50, new Chest(147, new Point(4200, 5430)).addItemStack(Items.gold, 2));
		placeChest(50, new Chest(148, new Point(90, 2940)).addItemStack(Items.miayGem, 7));
		placeChest(50, new Chest(149, new Point(1890, 2130)).setMoney(296));
		placeChest(50, new Chest(150, new Point(2670, 2130)).setMoney(12).addItem(Items.gold));
		placeChest(50, new Chest(151, new Point(1980, 1350)).addItem(Items.bronzeHammer));
		placeChest(50, new Chest(152, new Point(2520, 3630)).addItem(Items.gold).setMoney(337));
		placeChest(50, new Chest(153, new Point(4320, 3810)).setMoney(382));
		placeChest(50, new Chest(154, new Point(1800, 3300)).addItemStack(Items.gold, 2));
		placeChest(50, new Chest(155, new Point(720, 5160)).addItem(Items.gold).addItem(Items.bronze));
		placeChest(50, new Chest(156, new Point(3450, 2940)).setMoney(400));
		placeChest(50, new Chest(157, new Point(3450, 2460)).addItem(Items.gold));
		placeChest(50, new Chest(158, new Point(3240, 4680)).addItemStack(Items.gold, 2));
	}
	
	public void placePortals(){
		portals.add(new Portal(new Point(1740, 1110), "Goblin Tomb", new Point(1740, 1110), "Goblin Cave W1"));
		portals.add(new Portal(new Point(2370, 5490), "Goblin Tomb", new Point(2370, 5490), "Goblin Cave W1"));
	}
}
