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
import nl.knokko.rpg.world.maps.MapGenBase;

public class MapGenDarkForestNorth extends MapGenBase {

	public MapGenDarkForestNorth(){
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
		return "Dark Forest North";
	}

	@Override
	public ArrayList<Entity> getEnemies(){
		ArrayList<Entity> enemies = new ArrayList<Entity>();
		Point p = new Point();
		enemies.add(new DarkSoul(p, 25));
		enemies.add(new DarkSoul(p, 26));
		enemies.add(new DreamEater(p, 23));
		enemies.add(new DreamEater(p, 24));
		enemies.add(new DreamEater(p, 25));
		enemies.add(new Drock(p, 25));
		enemies.add(new Drock(p, 26));
		enemies.add(new Vampire(p, 24));
		enemies.add(new Vampire(p, 25));
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
		fill2(187, 181, 187, 192, 0);
		fill2(186, 179, 186, 181, 0);
		fill2(185, 177, 185, 179, 0);
		fill2(183, 177, 184, 177, 0);
		fill2(179, 176, 183, 176, 0);
		fill2(178, 174, 178, 176, 0);
		fill2(177, 172, 177, 174, 0);
		fill2(176, 170, 176, 172, 0);
		fill2(175, 168, 175, 170, 0);
		fill2(173, 167, 175, 167, 0);
		fill2(165, 166, 173, 166, 0);
		fill2(164, 161, 164, 166, 0);
		fill2(165, 157, 165, 161, 0);
		fill2(166, 153, 166, 157, 0);
		fill2(158, 165, 163, 165, 0);
		fill2(152, 164, 158, 164, 0);
		fill2(167, 151, 167, 153, 0);
		fill2(168, 149, 168, 151, 0);
		fill2(169, 149, 170, 149, 0);
		fill2(170, 148, 174, 148, 0);
		fill2(174, 145, 174, 147, 0);
		tiles2[173][147] = 0;
		fill2(175, 136, 175, 145, 0);
		fill2(171, 126, 178, 135, 0);
		fill2(179, 127, 179, 133, 0);
		fill2(180, 129, 180, 132, 0);
		tiles2[181][130] = 0;
		fill2(173, 125, 177, 125, 0);
		fill2(174, 124, 176, 124, 0);
		fill2(170, 127, 170, 133, 0);
		fill2(169, 128, 169, 131, 0);
		tiles2[168][129] = 0;
		tiles2[175][123] = 0;
		fill2(172, 136, 177, 136, 0);
		tiles2[175][130] = 45;
		fill(173, 128, 177, 132, 12);
		fill(174, 133, 176, 133, 12);
		fill(172, 129, 172, 131, 12);
		fill(174, 127, 176, 127, 12);
		fill(178, 129, 178, 131, 12);
		fill(175, 127, 175, 133, 33);
		fill2(175, 148, 178, 148, 0);
		fill2(178, 149, 182, 149, 0);
		fill2(182, 150, 190, 150, 0);
		fill2(175, 176, 177, 176, 0);
		fill2(172, 177, 175, 177, 0);
		fill2(170, 178, 172, 178, 0);
		tiles2[170][179] = 0;
		fill2(169, 179, 169, 181, 0);
		fill2(168, 181, 168, 183, 0);
		fill2(146, 163, 152, 163, 0);
		fill2(146, 159, 146, 162, 0);
		fill2(147, 155, 147, 159, 0);
		fill2(148, 152, 148, 155, 0);
		fill2(149, 148, 149, 152, 0);
		fill2(150, 142, 150, 148, 0);
		fill2(146, 142, 149, 142, 0);
		fill2(141, 141, 146, 141, 0);
		fill2(135, 140, 141, 140, 0);
		fill2(151, 143, 156, 143, 0);
		fill2(156, 139, 156, 142, 0);
		fill2(157, 134, 157, 139, 0);
		fill2(156, 129, 156, 134, 0);
		fill2(155, 126, 155, 129, 0);
		fill2(154, 122, 154, 126, 0);
		fill2(153, 119, 153, 122, 0);
		fill2(152, 115, 152, 119, 0);
		fill2(151, 109, 151, 114, 0);
		fill2(153, 108, 153, 114, 0);
		fill2(154, 108, 154, 114, 0);
		fill2(155, 109, 155, 114, 0);
		fill2(156, 107, 156, 114, 0);
		fill2(150, 107, 150, 114, 0);
		fill2(149, 108, 149, 114, 0);
		fill2(148, 108, 148, 113, 0);
		fill2(147, 106, 147, 113, 0);
		fill2(146, 107, 146, 112, 0);
		fill2(152, 109, 152, 114, 0);
		fill2(145, 108, 145, 110, 0);
		fill2(157, 108, 157, 114, 0);
		fill2(158, 107, 158, 113, 0);
		fill2(159, 108, 159, 111, 0);
		fill2(160, 109, 164, 109, 0);
		fill2(164, 108, 169, 108, 0);
		fill2(169, 107, 173, 107, 0);
		fill2(173, 106, 176, 106, 0);
		fill2(176, 105, 178, 105, 0);
		fill2(178, 104, 180, 104, 0);
		tiles2[180][103] = 0;
		fill2(181, 100, 181, 103, 0);
		fill2(182, 97, 182, 100, 0);
		fill2(181, 104, 181, 111, 0);
		fill2(182, 111, 182, 114, 0);
		fill2(183, 114, 183, 117, 0);
		fill2(183, 93, 183, 97, 0);
		fill2(184, 88, 184, 93, 0);
		fill2(183, 85, 183, 88, 0);
		fill2(182, 81, 182, 85, 0);
		fill2(181, 77, 181, 81, 0);
		fill2(180, 73, 180, 77, 0);
		fill2(179, 70, 179, 73, 0);
		tiles2[178][70] = 0;
		fill2(176, 69, 178, 69, 0);
		fill2(172, 68, 176, 68, 0);
		fill2(169, 67, 172, 67, 0);
		fill2(149, 58, 168, 74, 0);
		fill2(168, 58, 168, 64, 32);
		fill2(168, 71, 168, 74, 32);
		fill2(165, 58, 167, 61, 32);
		fill2(166, 62, 167, 62, 32);
		fill2(164, 58, 164, 59, 32);
		fill2(160, 58, 163, 58, 32);
		fill2(149, 58, 154, 58, 32);
		fill2(149, 59, 151, 59, 32);
		fill2(167, 73, 167, 74, 32);
		fill2(162, 73, 166, 74, 32);
		fill2(157, 74, 161, 74, 32);
		fill2(149, 72, 152, 74, 32);
		fill2(153, 73, 153, 74, 32);
		fill2(149, 66, 150, 71, 32);
		fill2(151, 67, 151, 69, 32);
		fill2(149, 60, 150, 62, 32);
		fill2(149, 63, 149, 65, 32);
		tiles2[152][68] = 32;
		fill(155, 62, 163, 70, 12);
		fill(154, 64, 154, 68, 12);
		fill(156, 61, 160, 61, 12);
		fill(164, 63, 164, 68, 12);
		fill(158, 71, 161, 71, 12);
		fill(165, 64, 165, 66, 12);
		fill(157, 60, 159, 60, 12);
		fill(153, 63, 153, 66, 12);
		tiles[154][63] = 12;
		tiles[164][69] = 12;
		fill(165, 65, 166, 68, 12);
		fill(152, 64, 152, 65, 12);
		tiles[159][72] = 12;
		tiles[161][61] = 12;
		tiles2[153][61] = 32;
		tiles2[163][59] = 32;
		tiles2[165][63] = 32;
		tiles2[167][71] = 32;
		tiles2[164][72] = 32;
		tiles2[157][72] = 32;
		tiles2[153][70] = 32;
		fill2(156, 58, 158, 58, 32);
		tiles2[166][69] = 32;
		fill2(140, 108, 144, 108, 0);
		fill2(134, 107, 140, 107, 0);
		fill2(129, 106, 134, 106, 0);
		fill2(121, 105, 129, 105, 0);
		fill2(116, 106, 121, 106, 0);
		fill2(110, 107, 116, 107, 0);
		fill2(121, 107, 121, 110, 0);
		fill2(122, 110, 122, 114, 0);
		fill2(123, 114, 123, 116, 0);
		fill2(124, 116, 124, 119, 0);
		fill2(105, 108, 110, 108, 0);
		fill2(92, 109, 105, 109, 0);
		fill2(86, 108, 92, 108, 0);
		fill2(83, 107, 86, 107, 0);
		fill2(81, 106, 83, 106, 0);
		fill2(80, 105, 81, 105, 0);
		tiles2[80][104] = 0;
		fill2(79, 102, 79, 104, 0);
		fill2(78, 96, 78, 102, 0);
		fill2(77, 93, 77, 96, 0);
		fill2(76, 88, 76, 93, 0);
		fill2(76, 81, 77, 87, 0);
		fill2(77, 73, 78, 80, 0);
		fill2(69, 80, 76, 80, 0);
		fill2(62, 79, 69, 79, 0);
		fill2(55, 78, 62, 78, 0);
		fill2(48, 79, 55, 79, 0);
		fill2(43, 80, 48, 80, 0);
		fill2(37, 81, 43, 81, 0);
		fill2(76, 66, 77, 72, 0);
		fill2(75, 58, 76, 65, 0);
		fill2(74, 46, 76, 57, 0);
		fill2(77, 49, 84, 49, 0);
		fill2(84, 50, 90, 50, 0);
		fill2(90, 51, 95, 51, 0);
		fill2(95, 52, 100, 52, 0);
		fill2(100, 53, 104, 53, 0);
		fill2(104, 54, 107, 54, 0);
		tiles2[107][55] = 0;
		fill2(108, 55, 108, 57, 0);
		fill2(109, 57, 109, 61, 0);
		fill2(108, 61, 108, 65, 0);
		fill2(74, 35, 76, 45, 0);
		tiles2[75][34] = 0;
		fill2(128, 139, 135, 139, 0);
		fill2(121, 140, 128, 140, 0);
		fill2(117, 141, 121, 141, 0);
		fill2(114, 142, 117, 142, 0);
		fill2(112, 143, 114, 143, 0);
		fill2(110, 144, 112, 144, 0);
		fill2(109, 145, 110, 145, 0);
		tiles2[109][146] = 0;
		fill2(108, 146, 108, 147, 0);
		fill2(107, 147, 107, 149, 0);
		fill2(106, 149, 106, 151, 0);
		fill2(130, 134, 130, 138, 0);
		fill2(131, 130, 131, 134, 0);
		fill2(132, 128, 132, 130, 0);
		tiles2[133][128] = 0;
		fill2(133, 127, 136, 127, 0);
		fill2(136, 126, 141, 126, 0);
		fill2(105, 151, 105, 153, 0);
		fill2(104, 153, 104, 156, 0);
		fill2(103, 156, 103, 161, 0);
		fill2(102, 161, 102, 167, 0);
		fill2(99, 150, 105, 150, 0);
		fill2(93, 149, 99, 149, 0);
		fill2(91, 148, 93, 148, 0);
		tiles2[91][147] = 0;
		fill2(90, 145, 90, 147, 0);
		fill2(89, 142, 89, 145, 0);
		tiles2[89][142] = 0;
		fill2(88, 136, 88, 142, 0);
		fill2(87, 133, 87, 136, 0);
		fill2(86, 131, 86, 133, 0);
		fill2(85, 130, 85, 131, 0);
		tiles2[84][130] = 0;
		fill2(83, 129, 84, 129, 0);
		fill2(79, 128, 83, 128, 0);
		fill2(73, 127, 79, 127, 0);
		fill2(101, 167, 101, 170, 0);
		fill2(100, 170, 100, 177, 0);
	}

	@Override
	public String getFightBackGround() {
		return BackGrounds.dark_forest;
	}
	
	public void placePortals(){
		portals.add(new Portal(new Point(5610, 5760), "Dark Forest North", new Point(4380, 1170), "Dark Forest"));
		portals.add(new Portal(new Point(3000, 5310), "Dark Forest North", new Point(3000, 930), "Dark Forest Mid"));
		portals.add(new Portal(new Point(2250, 1020), "Dark Forest North", null, null, "Dead Mansion"));
	}
	
	public void placeChests(){
		placeChest(new Chest(90, new Point(5700, 4500)).addItemStack(Items.miayGem, 5).setMoney(77));
		placeChest(new Chest(91, new Point(5040, 5490)).addItemStack(Items.darkEssence, 4).addItemStack(Items.darkSkin, 6));
		placeChest(new Chest(92, new Point(4680, 3210)).addItem(Items.orangeEther).addItemStack(Items.betterPotion, 3));
		placeChest(new Chest(93, new Point(5490, 3510)).addItemStack(Items.darkSkin, 7).setMoney(58));
		placeChest(new Chest(94, new Point(5400, 2100)).addItemStack(Items.orangePotion, 3).addItemStack(Items.ether, 2).setMoney(23));
		placeChest(new Chest(95, new Point(4650, 1740)).setMoney(178));
		placeChest(new Chest(96, new Point(3720, 3570)).addItems(Items.betterPotion, Items.orangeEther, Items.potion).setMoney(94));
		placeChest(new Chest(97, new Point(5250, 3900)).addItem(Items.darkLeggings));
		placeChest(new Chest(98, new Point(1110, 2430)).addItemStack(Items.wood, 28).setMoney(32));
		placeChest(new Chest(99, new Point(3240, 1950)).addItemStack(Items.darkEssence, 9));
		placeChest(new Chest(100, new Point(4230, 3780)).addItemStack(Items.potion, 27).addItemStack(Items.orangeEther, 2));
		placeChest(new Chest(101, new Point(2190, 3810)).setMoney(318));
	}

	@Override
	public String getMusic() {
		return "dark forest";
	}
}
