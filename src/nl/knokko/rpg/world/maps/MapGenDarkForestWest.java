package nl.knokko.rpg.world.maps;

import java.awt.*;
import java.util.ArrayList;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.monsters.DarkSoul;
import nl.knokko.rpg.entities.monsters.DreamEater;
import nl.knokko.rpg.entities.monsters.Drock;
import nl.knokko.rpg.entities.monsters.Vampire;
import nl.knokko.rpg.tiles.Chest;
import nl.knokko.rpg.utils.BackGrounds;
import nl.knokko.rpg.world.Portal;
import nl.knokko.rpg.world.maps.MapGenBase;

public class MapGenDarkForestWest extends MapGenBase {

	public MapGenDarkForestWest(){
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
		return null;
	}

	@Override
	public String getName(){
		return "Dark Forest West";
	}

	@Override
	public ArrayList<Entity> getEnemies(){
		ArrayList<Entity> enemies = new ArrayList<Entity>();
		Point p = new Point();
		enemies.add(new DarkSoul(p, 26));
		enemies.add(new DarkSoul(p, 27));
		enemies.add(new DreamEater(p, 24));
		enemies.add(new DreamEater(p, 25));
		enemies.add(new DreamEater(p, 26));
		enemies.add(new Drock(p, 26));
		enemies.add(new Drock(p, 27));
		enemies.add(new Vampire(p, 25));
		enemies.add(new Vampire(p, 26));
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
		fill(0, 0, 199, 199, 33);
		fill2(0, 0, 199, 199, 32);
		fill2(170, 100, 178, 100, 0);
		fill2(164, 99, 170, 99, 0);
		fill2(161, 98, 164, 98, 0);
		fill2(159, 97, 161, 97, 0);
		fill2(158, 96, 159, 96, 0);
		tiles2[158][95] = 0;
		fill2(157, 94, 157, 95, 0);
		fill2(156, 93, 156, 94, 0);
		fill2(155, 90, 155, 93, 0);
		fill2(154, 88, 154, 90, 0);
		fill2(153, 87, 153, 88, 0);
		fill2(151, 86, 153, 86, 0);
		fill2(148, 85, 151, 85, 0);
		fill2(145, 84, 148, 84, 0);
		fill2(139, 83, 145, 83, 0);
		fill2(122, 77, 138, 89, 0);
		fill2(139, 79, 139, 82, 0);
		fill2(139, 84, 139, 86, 0);
		fill2(136, 89, 138, 89, 32);
		fill2(132, 77, 138, 77, 32);
		fill2(122, 89, 130, 89, 32);
		fill2(122, 88, 127, 88, 32);
		fill2(122, 87, 126, 87, 32);
		fill2(122, 77, 128, 77, 32);
		fill2(122, 78, 126, 78, 32);
		fill2(134, 78, 138, 78, 32);
		fill2(137, 79, 139, 80, 32);
		fill2(122, 79, 124, 79, 32);
		fill2(122, 80, 123, 86, 32);
		fill2(123, 81, 123, 83, 0);
		fill2(132, 90, 132, 93, 0);
		fill2(131, 93, 131, 96, 0);
		fill2(130, 96, 130, 100, 0);
		fill2(129, 100, 129, 103, 0);
		fill2(130, 103, 130, 107, 0);
		fill2(131, 107, 131, 110, 0);
		fill2(132, 110, 132, 114, 0);
		fill2(133, 114, 133, 121, 0);
		fill2(151, 79, 151, 84, 0);
		fill2(152, 74, 152, 79, 0);
		fill2(156, 95, 156, 100, 0);
		fill2(157, 100, 157, 105, 0);
		fill2(158, 105, 158, 110, 0);
		fill2(157, 110, 157, 114, 0);
		fill2(156, 114, 156, 118, 0);
		fill2(155, 118, 155, 120, 0);
		fill2(154, 120, 154, 121, 0);
		tiles2[153][121] = 0;
		fill2(151, 122, 153, 122, 0);
		fill2(137, 120, 150, 126, 0);
		fill2(151, 123, 151, 124, 0);
		fill2(141, 119, 149, 119, 0);
		fill2(143, 118, 146, 118, 0);
		fill2(144, 127, 148, 127, 0);
		fill2(136, 121, 136, 124, 0);
		fill2(134, 121, 135, 122, 0);
		fill2(145, 114, 145, 117, 0);
		fill2(146, 110, 146, 114, 0);
		fill2(147, 106, 147, 110, 0);
		fill2(146, 101, 146, 106, 0);
		fill2(130, 71, 130, 76, 0);
		fill2(131, 67, 131, 71, 0);
		fill2(132, 64, 132, 67, 0);
		fill2(133, 61, 133, 64, 0);
		fill2(134, 55, 134, 61, 0);
		fill2(135, 49, 135, 55, 0);
		fill2(129, 49, 134, 49, 0);
		fill2(124, 50, 129, 50, 0);
		fill2(119, 51, 124, 51, 0);
		fill2(115, 52, 119, 52, 0);
		fill2(136, 43, 136, 49, 0);
		fill2(137, 39, 137, 43, 0);
		fill2(138, 37, 138, 39, 0);
		tiles2[139][37] = 0;
		fill2(139, 36, 141, 36, 0);
		fill2(141, 35, 146, 35, 0);
		fill2(146, 34, 151, 34, 0);
		fill2(151, 35, 156, 35, 0);
		fill2(156, 36, 158, 36, 0);
		fill2(158, 37, 162, 37, 0);
		fill2(162, 38, 165, 38, 0);
		fill2(165, 39, 167, 39, 0);
		tiles2[167][40] = 0;
		fill2(168, 40, 168, 41, 0);
		fill2(169, 41, 169, 44, 0);
		fill2(168, 44, 168, 47, 0);
		fill2(167, 47, 167, 51, 0);
		fill2(164, 51, 166, 51, 0);
		fill2(159, 52, 164, 52, 0);
		fill2(156, 34, 161, 34, 0);
		fill2(161, 33, 164, 33, 0);
		fill2(164, 32, 171, 32, 0);
		fill2(144, 48, 158, 56, 0);
		fill2(149, 47, 156, 47, 0);
		fill2(150, 46, 153, 46, 0);
		fill2(146, 47, 148, 47, 0);
		fill2(148, 57, 156, 57, 0);
		fill2(149, 58, 153, 58, 0);
		fill2(143, 49, 143, 53, 0);
		fill2(142, 49, 142, 51, 0);
		tiles2[143][48] = 0;
		tiles2[144][56] = 32;
		fill2(147, 128, 147, 132, 0);
		fill2(148, 132, 148, 136, 0);
		fill2(149, 136, 149, 140, 0);
		fill2(150, 140, 150, 142, 0);
		fill2(151, 142, 151, 144, 0);
		tiles2[152][144] = 0;
		fill2(152, 145, 153, 145, 0);
		fill2(153, 146, 156, 146, 0);
		fill2(156, 147, 160, 147, 0);
		fill2(160, 148, 165, 148, 0);
		fill2(165, 149, 168, 149, 0);
		fill2(168, 150, 170, 150, 0);
		tiles2[170][151] = 0;
		fill2(171, 151, 171, 153, 0);
		fill2(172, 153, 172, 157, 0);
		fill2(173, 157, 173, 163, 0);
		fill2(149, 146, 152, 146, 0);
		fill2(145, 147, 149, 147, 0);
		fill2(142, 148, 145, 148, 0);
		fill2(138, 149, 142, 149, 0);
		fill2(132, 148, 138, 148, 0);
		fill2(128, 147, 132, 147, 0);
		fill2(117, 82, 122, 82, 0);
		fill2(112, 81, 117, 81, 0);
		fill2(108, 80, 112, 80, 0);
		fill2(105, 79, 108, 79, 0);
		fill2(101, 78, 105, 78, 0);
		tiles2[101][77] = 0;
		fill2(100, 75, 100, 77, 0);
		fill2(99, 71, 99, 75, 0);
		fill2(98, 66, 98, 71, 0);
		fill2(97, 63, 97, 66, 0);
		fill2(96, 61, 96, 63, 0);
		tiles2[95][61] = 0;
		fill2(93, 60, 95, 60, 0);
		fill2(89, 59, 93, 59, 0);
		fill2(83, 60, 89, 60, 0);
		fill2(96, 79, 101, 79, 0);
		fill2(91, 80, 96, 80, 0);
		fill2(89, 81, 91, 81, 0);
		tiles2[89][82] = 0;
		fill2(88, 82, 88, 85, 0);
		fill2(87, 85, 87, 91, 0);
		fill2(68, 53, 82, 65, 0);
		fill2(83, 56, 83, 63, 0);
		fill2(67, 56, 67, 60, 0);
		fill2(70, 66, 75, 66, 0);
		fill2(71, 53, 80, 53, 32);
		fill2(74, 54, 77, 54, 32);
		fill2(79, 65, 82, 65, 32);
		tiles2[67][58] = 32;
		fill2(72, 66, 75, 66, 32);
		tiles2[68][65] = 32;
		fill2(71, 62, 76, 62, 28);
		fill2(78, 62, 81, 62, 28);
		fill2(71, 56, 71, 61, 28);
		fill2(72, 56, 81, 56, 28);
		fill2(81, 57, 81, 61, 28);
		tiles3[71][56] = 5;
		tiles3[71][56] = 5;
		tiles3[76][56] = 5;
		tiles2[77][62] = 7;
	}

	@Override
	public String getMusic() {
		return "dark forest";
	}

	@Override
	public String getFightBackGround() {
		return BackGrounds.dark_forest;
	}
	
	public void placeChests(){
		placeChest(new Chest(102, new Point(4590, 2220)));
		placeChest(new Chest(103, new Point(4380, 3030)));
		placeChest(new Chest(104, new Point(3450, 1560)));
		placeChest(new Chest(105, new Point(5130, 990)));
		placeChest(new Chest(106, new Point(4500, 1560)));
		placeChest(new Chest(107, new Point(4500, 1560)));
		placeChest(new Chest(108, new Point(3840, 4380)));
		placeChest(new Chest(109, new Point(2640, 2730)));
	}
	
	public void placePortals(){
		portals.add(new Portal(new Point(5340, 3000), "Dark Forest West", new Point(720, 3000), "Dark Forest Mid"));
		portals.add(new Portal(new Point(5190, 4890), "Dark Forest West", new Point(1170, 1260), "Dark Forest South"));
		portals.add(new Portal(new Point(2310, 1860), "Dark Forest West", new Point(180, 5940), "Dead Dungeon 1"));
	}
}
