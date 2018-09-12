package nl.knokko.rpg.world.maps;

import java.awt.*;
import java.util.ArrayList;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.monsters.DarkSoul;
import nl.knokko.rpg.entities.monsters.DreamEater;
import nl.knokko.rpg.entities.monsters.Drock;
import nl.knokko.rpg.entities.monsters.Vampire;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.tiles.Chest;
import nl.knokko.rpg.utils.BackGrounds;
import nl.knokko.rpg.world.Portal;
import nl.knokko.rpg.world.maps.MapGenBase;

public class MapGenDarkForestEast extends MapGenBase {

	public MapGenDarkForestEast(){
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
	public String getName(){
		return "Dark Forest";
	}

	@Override
	public ArrayList<Entity> getEnemies(){
		ArrayList<Entity> enemies = new ArrayList<Entity>();
		Game g = Game.game;
		Point p = new Point();
		enemies.add(new DarkSoul(g, p, 22));
		enemies.add(new DarkSoul(g, p, 23));
		enemies.add(new DreamEater(g, p, 21));
		enemies.add(new DreamEater(g, p, 22));
		enemies.add(new DreamEater(g, p, 23));
		enemies.add(new Drock(g, p, 23));
		enemies.add(new Drock(g, p, 24));
		enemies.add(new Vampire(g, p, 22));
		enemies.add(new Vampire(g, p, 23));
		return enemies;
	}

	@Override
	public int getMaxEnemies(){
		return 4;
	}

	@Override
	public Point getStartPoint(MapGenBase old){
		return new Point(5040, 3180);
	}
	
	@Override
	public String getMusic(){
		return "dark forest";
	}

	public void build(){
		fill(0, 0, 199, 199, 33);
		fill2(0, 0, 199, 199, 32);
		fill2(158, 106, 168, 106, 0);
		fill2(152, 103, 157, 110, 0);
		fill2(139, 106, 151, 107, 0);
		fill2(155, 91, 155, 102, 0);
		fill2(144, 91, 166, 91, 0);
		fill2(144, 81, 144, 90, 0);
		fill2(136, 80, 156, 80, 0);
		fill2(135, 76, 135, 80, 0);
		fill2(134, 70, 134, 76, 0);
		fill2(156, 75, 156, 79, 0);
		fill2(157, 68, 157, 75, 0);
		fill2(156, 60, 156, 68, 0);
		fill2(133, 63, 133, 70, 0);
		fill2(121, 63, 132, 63, 0);
		fill2(116, 64, 121, 64, 0);
		fill2(116, 65, 116, 69, 0);
		fill2(134, 56, 134, 63, 0);
		fill2(135, 52, 135, 56, 0);
		fill2(155, 54, 155, 60, 0);
		fill2(154, 49, 154, 54, 0);
		fill2(148, 49, 153, 49, 0);
		fill2(147, 45, 147, 49, 0);
		fill2(146, 39, 146, 45, 0);
		fill2(125, 80, 134, 80, 0);
		fill2(125, 71, 125, 79, 0);
		fill2(136, 52, 140, 52, 0);
		fill2(141, 52, 141, 57, 0);
		fill2(142, 57, 142, 62, 0);
		fill2(143, 62, 146, 62, 0);
		fill2(147, 62, 147, 67, 0);
		fill2(146, 67, 146, 73, 0);
		fill2(140, 67, 145, 73, 0);
		fill2(139, 68, 139, 71, 0);
		fill2(147, 69, 147, 71, 0);
		fill2(142, 66, 144, 66, 0);
		fill2(142, 74, 144, 74, 0);
		fill2(142, 69, 144, 71, 12);
		tiles2[145][70] = 12;
		fill2(141, 68, 143, 68, 12);
		fill2(141, 69, 141, 70, 12);
		tiles2[143][72] = 12;
		tiles2[140][69] = 12;
		tiles2[143][67] = 12;
		fill2(155, 111, 155, 118, 0);
		fill2(147, 118, 154, 118, 0);
		fill2(147, 119, 147, 126, 0);
		fill(132, 133, 166, 146, 12);
		fill(135, 132, 159, 132, 12);
		fill(137, 131, 154, 131, 12);
		fill(143, 130, 150, 130, 12);
		fill(145, 129, 148, 129, 12);
		fill(167, 134, 170, 144, 12);
		fill(171, 137, 171, 144, 12);
		fill(172, 140, 172, 144, 12);
		fill(127, 135, 131, 146, 12);
		fill(126, 137, 126, 146, 12);
		fill(125, 140, 125, 146, 12);
		fill(126, 147, 160, 147, 12);
		fill(129, 148, 157, 148, 12);
		fill(132, 149, 154, 149, 12);
		fill(134, 150, 149, 150, 12);
		fill(140, 151, 147, 151, 12);
		fill2(137, 131, 154, 149, 0);
		fill2(155, 132, 160, 147, 0);
		fill2(155, 148, 157, 148, 0);
		fill2(161, 133, 166, 146, 0);
		fill2(167, 134, 170, 144, 0);
		fill2(171, 137, 171, 144, 0);
		fill2(172, 140, 172, 144, 0);
		fill2(143, 130, 150, 130, 0);
		fill2(145, 129, 148, 129, 0);
		fill2(134, 133, 136, 150, 0);
		fill2(127, 135, 133, 147, 0);
		fill2(132, 133, 133, 134, 0);
		fill2(135, 132, 136, 132, 0);
		fill2(126, 137, 126, 147, 0);
		fill2(125, 140, 125, 146, 0);
		fill2(129, 148, 133, 148, 0);
		fill2(132, 149, 133, 149, 0);
		fill2(137, 150, 149, 150, 0);
		fill2(140, 151, 147, 151, 0);
		fill2(141, 127, 147, 127, 0);
		fill2(141, 128, 141, 130, 0);
		fill2(136, 130, 140, 130, 0);
		fill2(134, 131, 136, 131, 0);
		fill2(131, 132, 134, 132, 0);
		fill2(126, 134, 131, 134, 0);
		tiles2[131][133] = 0;
		fill2(126, 135, 126, 136, 0);
		fill2(125, 136, 125, 139, 0);
		fill2(124, 139, 124, 147, 0);
		fill2(125, 147, 125, 148, 0);
		fill2(126, 148, 128, 148, 0);
		fill2(128, 149, 131, 149, 0);
		fill2(131, 150, 133, 150, 0);
		fill2(133, 151, 139, 151, 0);
		fill2(139, 152, 144, 152, 0);
		fill2(147, 128, 149, 128, 0);
		fill2(149, 129, 151, 129, 0);
		fill2(151, 130, 155, 130, 0);
		fill2(155, 131, 160, 131, 0);
		fill2(161, 132, 167, 132, 0);
		fill2(167, 133, 171, 133, 0);
		fill2(171, 134, 171, 136, 0);
		fill2(172, 136, 172, 139, 0);
		fill2(173, 139, 173, 145, 0);
		fill2(144, 153, 144, 162, 0);
		fill2(133, 163, 156, 163, 0);
		fill2(124, 162, 133, 162, 0);
		fill2(156, 154, 156, 162, 0);
		fill2(139, 164, 139, 169, 0);
		fill2(138, 169, 138, 175, 0);
		fill2(129, 105, 139, 105, 0);
		fill2(129, 106, 138, 106, 0);
		fill2(122, 104, 129, 104, 0);
		fill2(117, 103, 122, 103, 0);
		fill2(133, 108, 139, 108, 0);
		fill2(128, 109, 133, 109, 0);
		fill2(123, 110, 128, 110, 0);
		fill2(123, 111, 123, 114, 0);
		fill2(115, 114, 122, 114, 0);
		fill2(110, 102, 117, 102, 0);
		fill2(106, 115, 115, 115, 0);
		fill2(102, 101, 110, 101, 0);
		fill2(96, 116, 106, 116, 0);
		fill2(102, 117, 102, 127, 0);
		fill2(103, 127, 120, 127, 0);
		fill2(120, 128, 120, 135, 0);
		fill2(93, 127, 101, 127, 0);
		fill2(93, 128, 93, 136, 0);
		fill2(92, 136, 92, 143, 0);
		fill2(82, 143, 91, 143, 0);
		fill2(99, 100, 102, 100, 0);
		fill2(99, 93, 99, 99, 0);
		fill2(98, 84, 98, 93, 0);
		fill2(99, 79, 99, 84, 0);
		fill2(100, 69, 100, 79, 0);
		fill2(102, 102, 102, 108, 0);
		fill2(89, 115, 96, 115, 0);
		fill2(82, 114, 89, 114, 0);
	}
	
	public void placePortals(){
		portals.add(new Portal(new Point(4380, 1170), "Dark Forest", new Point(5610, 5730), "Dark Forest North"));
		portals.add(new Portal(new Point(4140, 5250), "Dark Forest", new Point(4800, 570), "Dark Forest South"));
		portals.add(new Portal(new Point(2460, 3420), "Dark Forest", new Point(5310, 3000), "Dark Forest Mid"));
		portals.add(new Portal(new Point(5040, 3180), "Dark Forest", null, null));
	}
	
	public void placeChests(){
		placeChest(new Chest(69, new Point(3480, 2070)).addItemStack(Items.darkEssence, 3));
		placeChest(new Chest(70, new Point(4980, 2730)).setMoney(180));
		placeChest(new Chest(71, new Point(3750, 2130)).addItem(Items.orangeEther).addItemStack(Items.potion, 3));
		placeChest(new Chest(72, new Point(4290, 2220)).addItem(Items.darkBlade));
		placeChest(new Chest(73, new Point(5190, 4350)).addItem(Items.orangePotion).setMoney(130));
		placeChest(new Chest(74, new Point(3720, 4860)).addItemStack(Items.potion, 10).setMoney(30));
		placeChest(new Chest(75, new Point(4680, 4620)).addItemStack(Items.ether, 4).setMoney(20));
		placeChest(new Chest(76, new Point(3600, 4050)).setMoney(100).addItemStack(Items.wood, 20));
		placeChest(new Chest(77, new Point(2460, 4290)).addItem(Items.darkEssence).setMoney(80));
		placeChest(new Chest(78, new Point(3000, 2070)).addItem(Items.darkWand));
		placeChest(new Chest(79, new Point(3060, 3240)).setMoney(100).addItem(Items.orangeEther));
	}

	@Override
	public String getFightBackGround() {
		return BackGrounds.dark_forest;
	}
}
