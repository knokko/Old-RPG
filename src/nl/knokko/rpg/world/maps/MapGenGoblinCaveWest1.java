package nl.knokko.rpg.world.maps;

import java.awt.*;
import java.util.ArrayList;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.monsters.Bat;
import nl.knokko.rpg.entities.monsters.Goblin;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.tiles.Chest;
import nl.knokko.rpg.utils.BackGrounds;
import nl.knokko.rpg.world.Portal;
import nl.knokko.rpg.world.maps.MapGenBase;

public class MapGenGoblinCaveWest1 extends MapGenBase {

	public MapGenGoblinCaveWest1(){
		super();
		build();
		placeChests();
		placePortals();
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
		return "Goblin Cave W1";
	}

	@Override
	public ArrayList<Entity> getEnemies(){
		ArrayList<Entity> enemies = new ArrayList<Entity>();
		Point p = new Point(-10000, -10000);
		enemies.add(new Bat(p, 42));
		enemies.add(new Goblin(p, 41));
		enemies.add(new Goblin(p, 42));
		enemies.add(new Goblin(p, 43));
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
		tiles[100][100] = 21;
		fill(98, 85, 102, 102, 21);
		tiles2[100][100] = 24;
		fill(72, 80, 123, 84, 21);
		fill2(97, 85, 97, 103, 28);
		fill2(98, 103, 103, 103, 28);
		fill2(103, 85, 103, 102, 28);
		fill(124, 68, 128, 125, 21);
		fill2(104, 85, 123, 85, 28);
		fill2(123, 86, 123, 120, 28);
		fill2(129, 73, 129, 126, 28);
		fill2(77, 79, 123, 79, 28);
		fill2(42, 79, 71, 79, 28);
		fill2(47, 85, 96, 85, 28);
		fill(129, 68, 142, 72, 21);
		fill2(130, 73, 143, 73, 28);
		fill2(143, 67, 143, 72, 28);
		fill2(123, 67, 142, 67, 28);
		fill2(123, 68, 123, 78, 28);
		fill(96, 121, 123, 125, 21);
		fill(124, 126, 128, 137, 21);
		fill(129, 133, 158, 137, 21);
		fill(72, 55, 76, 79, 21);
		fill(42, 50, 91, 54, 21);
		fill(87, 30, 91, 49, 21);
		fill(61, 25, 105, 29, 21);
		fill(56, 25, 60, 39, 21);
		fill2(77, 55, 92, 55, 28);
		fill2(92, 30, 92, 54, 28);
		fill2(86, 30, 86, 49, 28);
		fill2(93, 30, 106, 30, 28);
		fill2(106, 24, 106, 29, 28);
		fill2(55, 24, 105, 24, 28);
		fill2(55, 25, 55, 40, 28);
		fill2(56, 40, 61, 40, 28);
		fill2(61, 30, 61, 39, 28);
		fill2(62, 30, 85, 30, 28);
		tiles2[58][37] = 24;
		tiles[58][37] = 0;
		fill(37, 39, 41, 65, 21);
		fill(10, 39, 36, 43, 21);
		fill(10, 44, 14, 48, 21);
		fill(42, 61, 65, 65, 21);
		fill2(42, 49, 85, 49, 28);
		fill2(42, 38, 42, 48, 28);
		fill2(9, 38, 41, 38, 28);
		fill2(9, 39, 9, 49, 28);
		fill2(10, 49, 15, 49, 28);
		fill2(15, 44, 15, 48, 28);
		fill2(16, 44, 36, 44, 28);
		fill2(36, 45, 36, 66, 28);
		fill2(37, 66, 66, 66, 28);
		fill2(66, 60, 66, 65, 28);
		fill2(42, 60, 65, 60, 28);
		fill2(42, 55, 42, 59, 28);
		fill2(43, 55, 71, 55, 28);
		fill(154, 138, 158, 147, 21);
		fill(159, 143, 184, 147, 21);
		fill(180, 138, 184, 142, 21);
		fill(185, 143, 189, 147, 21);
		fill(180, 148, 184, 152, 21);
		fill2(179, 137, 179, 142, 28);
		fill2(180, 137, 185, 137, 28);
		fill2(185, 138, 185, 142, 28);
		fill2(186, 142, 190, 142, 28);
		fill2(190, 143, 190, 148, 28);
		fill2(185, 148, 189, 148, 28);
		fill2(185, 149, 185, 153, 28);
		fill2(179, 153, 184, 153, 28);
		fill2(179, 148, 179, 152, 28);
		fill2(159, 142, 178, 142, 28);
		fill2(153, 148, 178, 148, 28);
		fill(154, 112, 158, 132, 21);
		fill(159, 112, 182, 116, 21);
		fill(178, 117, 182, 121, 21);
		fill2(177, 117, 177, 122, 28);
		fill2(178, 122, 183, 122, 28);
		fill2(183, 111, 183, 121, 28);
		fill2(153, 111, 182, 111, 28);
		fill2(159, 117, 176, 117, 28);
		fill2(153, 112, 153, 132, 28);
		fill2(159, 118, 159, 141, 28);
		fill2(153, 138, 153, 147, 28);
		fill2(129, 127, 129, 132, 28);
		fill2(130, 132, 152, 132, 28);
		fill(96, 126, 100, 145, 21);
		fill(101, 141, 115, 145, 21);
		fill(77, 141, 95, 145, 21);
		fill(77, 146, 77, 155, 21);
		fill(78, 146, 81, 155, 21);
		tiles[79][153] = 24;
		fill2(123, 126, 123, 138, 28);
		fill2(124, 138, 152, 138, 28);
		fill2(95, 120, 122, 120, 28);
		fill2(101, 126, 122, 126, 28);
		fill2(101, 127, 101, 140, 28);
		fill2(102, 140, 116, 140, 28);
		fill2(116, 141, 116, 146, 28);
		fill2(82, 146, 115, 146, 28);
		fill2(82, 147, 82, 156, 28);
		fill2(76, 156, 81, 156, 28);
		fill2(76, 140, 76, 155, 28);
		fill2(77, 140, 95, 140, 28);
		fill2(95, 121, 95, 139, 28);
		fill(42, 80, 71, 84, 21);
		fill(42, 85, 46, 110, 21);
		fill(11, 106, 41, 110, 21);
		fill(11, 84, 15, 105, 21);
		fill(11, 111, 15, 122, 21);
		fill(47, 106, 66, 110, 21);
		fill(62, 111, 66, 135, 21);
		fill(45, 131, 61, 135, 21);
		fill(67, 131, 74, 135, 21);
		tiles[74][131] = 21;
		fill2(67, 130, 75, 130, 28);
		fill2(75, 131, 75, 136, 28);
		fill2(44, 136, 74, 136, 28);
		fill2(44, 130, 44, 135, 28);
		fill2(45, 130, 61, 130, 28);
		fill2(61, 111, 61, 129, 28);
		fill2(67, 105, 67, 129, 28);
		fill2(47, 105, 66, 105, 28);
		fill2(16, 111, 60, 111, 28);
		fill2(16, 112, 16, 123, 28);
		fill2(10, 123, 15, 123, 28);
		fill2(10, 83, 10, 122, 28);
		fill2(11, 83, 16, 83, 28);
		fill2(16, 84, 16, 105, 28);
		fill2(17, 105, 41, 105, 28);
		fill2(41, 79, 41, 104, 28);
		fill2(47, 86, 47, 104, 28);
		fill2(71, 56, 71, 78, 28);
		fill2(77, 56, 77, 78, 28);
	}

	@Override
	public String getMusic() {
		return "goblin tomb";
	}

	@Override
	public String getFightBackGround() {
		return BackGrounds.goblin_tomb_1;
	}
	
	public void placeChests(){
		placeChest(new Chest(131, new Point(4170, 2100)).setMoney(56).addItemStack(Items.bronze, 6));
		placeChest(new Chest(132, new Point(3060, 810)).setMoney(178));
		placeChest(new Chest(133, new Point(5460, 4200)).setMoney(14).addItemStack(Items.betterPotion, 7).addItemStack(Items.ether, 3));
		placeChest(new Chest(134, new Point(5610, 4350)).setMoney(82).addItemStack(Items.potion, 13).addItem(Items.orangeEther));
		placeChest(new Chest(135, new Point(5460, 4500)).setMoney(131).addItemStack(Items.orangePotion, 3).addItemStack(Items.potion, 4));
		placeChest(new Chest(136, new Point(5400, 4170)).setMoney(33).addItemStack(Items.potion, 8).addItemStack(Items.betterPotion, 5));
		placeChest(new Chest(137, new Point(3390, 4290)).addItem(Items.bronzeHelmet));
		placeChest(new Chest(138, new Point(360, 1380)).setMoney(245));
		placeChest(new Chest(139, new Point(1890, 1890)).addItemStack(Items.orangePotion, 4).addItemStack(Items.orangeEther, 3));
		placeChest(new Chest(140, new Point(390, 3600)).setMoney(96).addItemStack(Items.bronze, 6));
		placeChest(new Chest(141, new Point(390, 2580)).addItem(Items.gold));
		placeChest(new Chest(142, new Point(1410, 3990)).setMoney(73).addItemStack(Items.bronze, 3).addItem(Items.pickaxe));
		placeChest(new Chest(143, new Point(2160, 3990)).setMoney(152).addItemStack(Items.wing, 18));
	}
	
	public void placePortals(){
		portals.add(new Portal(new Point(3000, 3000), "Goblin Cave W1", new Point(5820, 2430), "Goblin Cave"));
		portals.add(new Portal(new Point(1740, 1110), "Goblin Cave W1", new Point(1740, 1110), "Goblin Tomb"));
		portals.add(new Portal(new Point(2370, 5490), "Goblin Cave W1", new Point(2370, 5490), "Goblin Tomb"));
	}
}
