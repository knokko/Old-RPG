package nl.knokko.rpg.world.maps;

import java.awt.*;
import java.util.ArrayList;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.monsters.DarkSoul;
import nl.knokko.rpg.entities.monsters.DreamEater;
import nl.knokko.rpg.entities.monsters.Drock;
import nl.knokko.rpg.entities.monsters.Vampire;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.tiles.Chest;
import nl.knokko.rpg.utils.BackGrounds;
import nl.knokko.rpg.world.Portal;

public class MapGenDarkForestSouth extends MapGenBase {

	public MapGenDarkForestSouth(){
		super();
		build();
		placePortals();
		placeChests();
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
		return null;
	}

	@Override
	public String getName(){
		return "Dark Forest South";
	}

	@Override
	public ArrayList<Entity> getEnemies(){
		ArrayList<Entity> enemies = new ArrayList<Entity>();
		Point p = new Point();
		enemies.add(new DarkSoul(p, 24));
		enemies.add(new DarkSoul(p, 25));
		enemies.add(new DreamEater(p, 22));
		enemies.add(new DreamEater(p, 23));
		enemies.add(new DreamEater(p, 24));
		enemies.add(new Drock(p, 24));
		enemies.add(new Drock(p, 25));
		enemies.add(new Vampire(p, 23));
		enemies.add(new Vampire(p, 24));
		return enemies;
	}

	@Override
	public int getMaxEnemies(){
		return 4;
	}

	@Override
	public Point getStartPoint(MapGenBase old){
		return new Point(4170, 3720);
	}

	public void build(){
		tiles[199][199] = 33;
		fill(0, 0, 199, 199, 33);
		fill2(0, 0, 199, 199, 32);
		fill2(160, 19, 160, 26, 0);
		fill2(156, 26, 159, 26, 0);
		fill2(152, 27, 156, 27, 0);
		fill2(149, 28, 152, 28, 0);
		fill2(149, 29, 149, 33, 0);
		fill2(148, 33, 148, 40, 0);
		fill2(138, 33, 147, 33, 0);
		fill2(149, 40, 149, 46, 0);
		fill2(150, 46, 150, 52, 0);
		fill2(160, 27, 160, 29, 0);
		fill2(161, 29, 161, 34, 0);
		fill2(162, 34, 162, 37, 0);
		fill2(162, 38, 168, 38, 0);
		fill2(168, 39, 168, 44, 0);
		fill2(167, 45, 167, 53, 0);
		fill2(166, 46, 166, 52, 0);
		fill2(165, 48, 165, 50, 0);
		fill2(168, 45, 168, 54, 0);
		fill2(169, 45, 169, 54, 0);
		fill2(170, 46, 170, 53, 0);
		fill2(171, 48, 171, 50, 0);
		fill2(149, 52, 149, 58, 0);
		fill2(148, 58, 148, 64, 0);
		fill2(137, 65, 154, 65, 0);
		fill2(154, 66, 154, 69, 0);
		fill2(129, 66, 137, 66, 0);
		fill2(119, 67, 129, 67, 0);
		fill2(133, 67, 133, 79, 0);
		fill2(132, 79, 132, 86, 0);
		fill2(126, 86, 131, 86, 0);
		fill2(155, 69, 155, 77, 0);
		fill2(156, 77, 159, 77, 0);
		fill2(159, 78, 166, 78, 0);
		fill2(113, 68, 119, 68, 0);
		fill2(112, 69, 113, 71, 0);
		fill2(103, 71, 111, 71, 0);
		fill2(103, 72, 103, 76, 0);
		fill2(104, 76, 115, 76, 0);
		fill2(115, 77, 115, 83, 0);
		fill2(95, 70, 103, 70, 0);
		fill2(89, 69, 95, 69, 0);
		fill2(86, 70, 89, 70, 0);
		fill2(81, 71, 86, 71, 0);
		fill2(75, 72, 81, 72, 0);
		fill2(75, 73, 75, 77, 0);
		fill2(76, 77, 76, 83, 0);
		fill2(66, 83, 75, 83, 0);
		fill2(58, 82, 66, 82, 0);
		fill2(52, 81, 58, 81, 0);
		fill2(44, 80, 52, 80, 0);
		fill2(58, 66, 58, 80, 0);
		fill2(59, 66, 65, 66, 0);
		fill2(65, 67, 69, 67, 0);
		fill2(44, 74, 44, 79, 0);
		fill2(43, 69, 43, 74, 0);
		fill2(42, 65, 42, 69, 0);
		fill2(41, 60, 41, 65, 0);
		fill2(40, 54, 40, 60, 0);
		fill2(39, 43, 39, 54, 0);
		fill2(29, 60, 39, 60, 0);
		fill2(29, 61, 29, 64, 0);
		fill2(166, 79, 166, 83, 0);
		fill2(167, 83, 167, 87, 0);
		fill2(168, 87, 168, 91, 0);
		fill2(169, 91, 174, 91, 0);
		fill2(174, 92, 179, 92, 0);
		fill2(179, 93, 179, 94, 0);
		fill2(180, 94, 180, 98, 0);
		fill2(181, 98, 181, 101, 0);
		fill2(164, 92, 168, 92, 0);
		fill2(159, 93, 164, 93, 0);
		fill2(156, 94, 159, 94, 0);
		fill2(155, 95, 156, 98, 0);
		fill2(154, 98, 154, 102, 0);
		fill2(155, 102, 155, 107, 0);
		fill2(156, 107, 156, 113, 0);
		fill2(150, 113, 155, 113, 0);
		fill2(145, 112, 150, 112, 0);
		fill2(145, 108, 145, 111, 0);
		fill2(144, 103, 144, 108, 0);
		fill2(157, 113, 157, 119, 0);
		fill2(158, 119, 158, 124, 0);
		fill2(159, 124, 159, 130, 0);
		fill2(152, 130, 158, 130, 0);
		fill2(145, 129, 152, 129, 0);
		fill2(135, 125, 144, 132, 0);
		fill2(134, 127, 134, 129, 0);
		fill2(138, 133, 142, 133, 0);
		fill2(145, 126, 145, 130, 0);
		tiles2[139][124] = 18;
		tiles2[139][124] = 18;
		tiles2[138][124] = 19;
		tiles2[140][124] = 20;
		fill2(130, 34, 138, 34, 0);
		fill2(129, 34, 129, 38, 0);
		fill2(128, 38, 128, 42, 0);
		fill2(127, 42, 127, 44, 0);
		fill2(122, 45, 127, 45, 0);
		fill2(116, 46, 122, 46, 0);
		fill2(111, 47, 116, 47, 0);
		fill2(105, 48, 111, 48, 0);
		fill2(105, 49, 105, 54, 0);
		fill2(106, 54, 109, 54, 0);
		fill2(109, 53, 116, 53, 0);
		fill2(116, 52, 122, 52, 0);
		fill2(98, 49, 104, 49, 0);
		fill2(90, 48, 98, 48, 0);
		fill2(84, 47, 90, 47, 0);
		fill2(84, 42, 84, 46, 0);
		fill2(83, 36, 83, 42, 0);
		fill2(84, 36, 91, 36, 0);
		fill2(91, 37, 97, 37, 0);
		fill2(97, 32, 97, 36, 0);
		fill2(98, 25, 98, 32, 0);
		fill2(77, 35, 83, 35, 0);
		fill2(71, 34, 77, 34, 0);
		fill2(65, 33, 71, 33, 0);
		fill2(97, 16, 97, 25, 0);
		fill2(99, 26, 106, 26, 0);
		fill2(106, 25, 112, 25, 0);
		fill2(112, 24, 119, 24, 0);
	}
	
	public void placePortals(){
		portals.add(new Portal(new Point(4800, 570), "Dark Forest South", new Point(4140, 5250), "Dark Forest"));
		portals.add(new Portal(new Point(1170, 1290), "Dark Forest South", new Point(5190, 4860), "Dark Forest West"));
		portals.add(new Portal(new Point(4170, 3720), "Dark Forest South", null, null, "Goblin Cave"));
		portals.add(new Portal(new Point(2910, 480), "Dark Forest South", new Point(3000, 5070), "Dark Forest Mid"));
	}
	
	public void placeChests(){
		placeChest(new Chest(80, new Point(3780, 2580)).setMoney(194));
		placeChest(new Chest(81, new Point(5040, 1500)).addItems(Items.betterEther, Items.potion));
		placeChest(new Chest(82, new Point(3450, 2490)).addItemStack(Items.darkEssence, 3));
		placeChest(new Chest(83, new Point(2070, 2010)).addItemStack(Items.betterPotion, 2));
		placeChest(new Chest(84, new Point(870, 1920)).addItemStack(Items.wood, 20).setMoney(77));
		placeChest(new Chest(85, new Point(5430, 3030)).addItemStack(Items.potion, 5).addItemStack(Items.ether, 3));
		placeChest(new Chest(86, new Point(4320, 3090)).addItemStack(Items.darkEssence, 2).setMoney(12));
		placeChest(new Chest(87, new Point(3660, 1560)).setMoney(188));
		placeChest(new Chest(88, new Point(1950, 990)).addItems(Items.orangeEther, Items.orangePotion).setMoney(148));
		placeChest(new Chest(89, new Point(3570, 720)).addItem(Items.darkBoots));
	}
	
	@Override
	public String getFightBackGround() {
		return BackGrounds.dark_forest;
	}

	@Override
	public String getMusic() {
		return "dark forest";
	}
}
