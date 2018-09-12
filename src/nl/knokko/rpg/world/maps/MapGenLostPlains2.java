package nl.knokko.rpg.world.maps;

import java.awt.*;
import java.util.ArrayList;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.monsters.Bird;
import nl.knokko.rpg.entities.monsters.Frode;
import nl.knokko.rpg.entities.monsters.Tarr;
import nl.knokko.rpg.gui.GuiWorldMap;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.items.SpecialItems;
import nl.knokko.rpg.tiles.Chest;
import nl.knokko.rpg.utils.BackGrounds;
import nl.knokko.rpg.world.Portal;
import nl.knokko.rpg.world.maps.MapGenBase;

public class MapGenLostPlains2 extends MapGenBase {

	public MapGenLostPlains2(){
		super();
		build();
		placeChests();
		portals.add(new Portal(new Point(1170, 2070), "Lost Plains 2", null, null, "Ruff"));
		portals.add(new Portal(new Point(6390, 1920), "Lost Plains 2", null, null, "Lost Plains"));
	}

	@Override
	public int getXSize(){
		return 250;
	}

	@Override
	public int getYSize(){
		return 100;
	}

	@Override
	public String getName(){
		return "Lost Plains 2";
	}
	
	@Override
	public ArrayList<Entity> getEnemies() {
		ArrayList<Entity> enemies = new ArrayList<Entity>();
		Point p = new Point(-10000, -10000);
		enemies.add(new Tarr(p, 10));
		enemies.add(new Tarr(p, 9));
		enemies.add(new Frode(p, 9));
		enemies.add(new Frode(p, 10));
		enemies.add(new Frode(p, 11));
		enemies.add(new Bird(p, 11));
		enemies.add(new Bird(p, 12));
		return enemies;
	}

	@Override
	public int getMaxEnemies(){
		return 4;
	}
	
	@Override
	public String getFightBackGround(){
		return BackGrounds.plains;
	}

	@Override
	public Point getStartPoint(MapGenBase previous){
		float distanceX = GuiWorldMap.positionForMap(previous).x - GuiWorldMap.positionForMap(this).x;
		if(distanceX > 0)
			return new Point(6360, 1920);
		return new Point(1200, 2070);
	}

	@Override
	public String getMusic(){
		return "lost plains";
	}
	
	public void placeChests(){
		placeChest(new Chest(20, new Point(5580, 1770)).setMoney(90));
		placeChest(new Chest(21, new Point(5340, 1500)).addItems(Items.orangePotion, Items.ether).setMoney(27));
		placeChest(new Chest(22, new Point(4290, 1020)).addItemStack(Items.ether, 2));
		placeChest(new Chest(23, new Point(5430, 1200)).addItemStack(Items.wood, 7).setMoney(64));
		placeChest(new Chest(24, new Point(5880, 1200)).addItemStack(Items.wing, 2).setMoney(35));
		placeChest(new Chest(25, new Point(4830, 450)).addItemStack(Items.feather, 5).setMoney(56));
		placeChest(new Chest(26, new Point(4830, 600)).addItemStack(Items.potion, 3).addItemStack(Items.wing, 2));
		placeChest(new Chest(27, new Point(3570, 2190)).addItem(Items.ether).setMoney(78));
		placeChest(new Chest(28, new Point(1290, 2250)).addItemStack(Items.ether, 2).addItemStack(Items.wing, 3));
		placeChest(new Chest(29, new Point(1860, 1950)).addItemStack(Items.orangePotion, 2).addItemStack(Items.feather, 3));
		placeChest(new Chest(30, new Point(1500, 1500)).addItem(Items.orangeEther).setMoney(43));
		placeChest(new Chest(31, new Point(1230, 720)).addItemStack(Items.frodeskin, 3).setMoney(36));
		placeChest(new Chest(32, new Point(2190, 600)).addItems(Items.orangePotion, Items.potion));
		placeChest(new Chest(33, new Point(2910, 540)).addItemStack(Items.potion, 3).addItem(Items.frodeskin));
		placeChest(new Chest(34, new Point(2280, 750), SpecialItems.BLUE_PRINT_WAND).addItemStack(Items.wing, 2));
		placeChest(new Chest(35, new Point(4560, 540)).addItemStack(Items.feather, 6).setMoney(60));
		placeChest(new Chest(36, new Point(3540, 1320)).addItemStack(Items.snakeTooth, 5).setMoney(18));
		placeChest(new Chest(37, new Point(3210, 1140)).addItem(Items.foidChestplate));
		placeChest(new Chest(38, new Point(1530, 630)).setMoney(107));
		placeChest(new Chest(39, new Point(1500, 630)).addItems(Items.feather, Items.foidBoots));
		placeChest(new Chest(40, new Point(2280, 1200)).addItemStack(Items.foidskin, 10));
	}

	public void build(){
		fill(0, 0, 249, 99, 1);
		fill(232, 70, 249, 73, 12);
		fill(218, 71, 231, 74, 12);
		fill(205, 72, 217, 75, 12);
		fill(192, 73, 204, 76, 12);
		fill(181, 72, 191, 75, 12);
		fill(164, 71, 180, 74, 12);
		fill(149, 72, 163, 75, 12);
		fill2(213, 0, 213, 71, 1);
		fill(200, 64, 212, 64, 14);
		fill(182, 71, 206, 71, 15);
		fill2(182, 71, 206, 71, 15);
		fill(182, 71, 206, 71, 1);
		fill(206, 66, 206, 70, 16);
		fill(206, 66, 206, 70, 1);
		fill2(206, 65, 206, 70, 16);
		fill2(201, 65, 206, 65, 15);
		fill2(189, 65, 199, 65, 15);
		tiles2[206][71] = 17;
		tiles2[182][71] = 17;
		fill2(182, 58, 182, 70, 16);
		fill2(189, 58, 189, 64, 16);
		tiles2[189][65] = 17;
		fill2(182, 57, 189, 57, 15);
		fill(200, 65, 200, 68, 14);
		fill(186, 68, 199, 68, 14);
		fill(186, 63, 186, 67, 14);
		fill2(195, 51, 207, 51, 15);
		fill2(209, 51, 212, 51, 15);
		fill2(212, 43, 212, 50, 16);
		tiles2[212][43] = 16;
		tiles2[212][51] = 17;
		fill2(195, 47, 195, 50, 16);
		tiles2[195][51] = 17;
		fill2(187, 42, 212, 42, 15);
		fill2(187, 47, 195, 47, 15);
		fill2(173, 42, 186, 42, 15);
		fill2(173, 43, 173, 51, 16);
		fill2(187, 48, 187, 52, 16);
		fill2(174, 52, 186, 52, 15);
		tiles2[173][52] = 17;
		tiles2[187][52] = 17;
		fill2(208, 45, 209, 63, 14);
		fill2(208, 45, 209, 63, 0);
		fill(208, 45, 209, 63, 14);
		fill(183, 45, 207, 45, 14);
		fill(183, 46, 183, 50, 14);
		fill(179, 50, 182, 50, 14);
		fill(200, 56, 200, 63, 14);
		fill(177, 56, 199, 56, 14);
		fill2(153, 52, 161, 52, 15);
		fill2(141, 52, 151, 52, 15);
		fill2(162, 38, 162, 51, 16);
		tiles2[162][52] = 17;
		fill2(163, 37, 188, 37, 15);
		tiles2[162][37] = 17;
		tiles2[162][37] = 15;
		fill2(141, 28, 141, 51, 16);
		tiles2[141][52] = 17;
		fill2(141, 28, 170, 28, 15);
		fill2(188, 38, 188, 40, 16);
		tiles2[188][41] = 17;
		fill2(189, 41, 201, 41, 15);
		fill2(202, 29, 202, 40, 16);
		tiles2[202][41] = 17;
		fill2(184, 28, 202, 28, 15);
		fill2(171, 22, 171, 27, 16);
		fill2(183, 15, 183, 27, 16);
		tiles2[183][28] = 17;
		tiles2[171][28] = 17;
		fill2(160, 14, 183, 14, 15);
		fill2(161, 21, 171, 21, 15);
		fill2(160, 15, 160, 20, 16);
		tiles2[160][21] = 17;
		fill2(165, 56, 176, 56, 14);
		fill2(165, 57, 165, 66, 14);
		fill2(155, 66, 164, 66, 14);
		fill2(147, 65, 155, 65, 14);
		fill2(165, 56, 180, 56, 0);
		fill2(165, 57, 165, 66, 0);
		fill2(155, 66, 164, 66, 0);
		fill2(147, 65, 155, 65, 0);
		fill(176, 56, 176, 64, 14);
		fill(157, 64, 175, 64, 14);
		fill(151, 63, 157, 63, 14);
		fill(144, 62, 151, 62, 14);
		fill(139, 73, 148, 76, 12);
		fill(129, 74, 138, 77, 12);
		fill(121, 73, 128, 76, 12);
		fill(108, 74, 120, 77, 12);
		fill(95, 75, 107, 78, 12);
		fill(84, 76, 94, 79, 12);
		tiles2[143][69] = 3;
		tiles2[156][58] = 3;
		tiles2[145][49] = 3;
		tiles2[157][42] = 3;
		tiles2[151][32] = 3;
		tiles2[165][29] = 3;
		tiles2[170][36] = 3;
		tiles2[183][31] = 3;
		tiles2[191][38] = 3;
		tiles2[177][24] = 3;
		tiles2[172][17] = 3;
		tiles2[165][19] = 3;
		tiles2[198][32] = 3;
		tiles2[170][45] = 3;
		tiles2[173][55] = 3;
		tiles2[162][67] = 3;
		tiles2[196][64] = 3;
		tiles2[206][57] = 3;
		tiles2[211][47] = 3;
		tiles2[221][58] = 3;
		tiles2[217][68] = 3;
		tiles2[227][63] = 3;
		tiles2[227][53] = 3;
		tiles2[232][59] = 3;
		tiles2[236][51] = 3;
		tiles2[190][49] = 3;
		tiles2[180][59] = 3;
		tiles2[175][45] = 3;
		tiles2[202][49] = 3;
		tiles2[170][65] = 3;
		tiles2[144][54] = 3;
		fill(75, 77, 83, 80, 12);
		fill(65, 76, 74, 79, 12);
		fill(58, 75, 64, 78, 12);
		fill(52, 76, 57, 79, 12);
		fill(46, 77, 51, 80, 12);
		fill(36, 78, 45, 81, 12);
		fill(32, 77, 35, 80, 12);
		fill(26, 78, 31, 81, 12);
		fill(17, 79, 25, 82, 12);
		fill(6, 80, 16, 83, 12);
		fill(0, 79, 5, 82, 12);
		fill(134, 61, 144, 61, 14);
		fill(133, 54, 133, 62, 14);
		fill(127, 62, 132, 62, 14);
		fill2(113, 48, 132, 48, 15);
		tiles2[112][48] = 17;
		fill2(112, 34, 112, 47, 16);
		fill2(132, 42, 132, 47, 16);
		fill2(132, 20, 132, 40, 16);
		tiles2[132][48] = 17;
		tiles2[141][27] = 3;
		fill2(141, 22, 141, 25, 16);
		fill2(142, 25, 154, 25, 15);
		fill2(155, 15, 155, 24, 16);
		fill2(120, 14, 155, 14, 15);
		fill2(120, 15, 120, 25, 16);
		fill2(132, 20, 141, 20, 15);
		tiles2[141][21] = 16;
		tiles2[141][25] = 17;
		tiles2[155][25] = 17;
		fill2(88, 25, 120, 25, 15);
		tiles2[120][25] = 17;
		fill2(102, 33, 112, 33, 15);
		fill2(102, 34, 102, 44, 16);
		tiles2[102][45] = 17;
		fill2(73, 45, 101, 45, 15);
		fill2(73, 33, 94, 33, 15);
		fill2(73, 34, 73, 44, 16);
		tiles2[73][45] = 17;
		tiles2[87][25] = 17;
		fill2(87, 21, 87, 24, 16);
		fill2(77, 14, 77, 24, 16);
		fill2(77, 14, 110, 14, 15);
		fill2(110, 15, 110, 20, 16);
		tiles2[110][21] = 17;
		fill2(87, 21, 109, 21, 15);
		fill2(73, 34, 94, 34, 15);
		tiles2[94][34] = 17;
		tiles2[142][26] = 3;
		fill2(64, 24, 77, 24, 15);
		fill2(52, 33, 72, 33, 15);
		tiles2[77][24] = 17;
		tiles2[77][24] = 17;
		tiles2[63][24] = 17;
		fill2(52, 21, 52, 32, 16);
		fill2(52, 20, 68, 20, 15);
		tiles2[69][20] = 17;
		fill2(39, 14, 76, 14, 15);
		fill2(39, 15, 39, 46, 16);
		fill2(49, 20, 49, 36, 16);
		fill2(49, 19, 69, 19, 15);
		fill2(75, 15, 75, 23, 16);
		tiles2[75][24] = 17;
		fill2(40, 46, 58, 46, 15);
		tiles2[39][46] = 17;
		tiles2[49][37] = 17;
		fill2(50, 37, 65, 37, 15);
		fill2(65, 38, 65, 41, 16);
		fill2(65, 43, 65, 56, 16);
		fill2(39, 48, 57, 48, 15);
		tiles2[58][48] = 17;
		tiles2[58][47] = 16;
		fill2(46, 56, 64, 56, 15);
		tiles2[65][56] = 17;
		fill2(39, 49, 39, 68, 16);
		fill2(39, 71, 39, 77, 16);
		fill2(39, 70, 51, 70, 15);
		fill2(51, 74, 51, 76, 16);
		fill2(51, 71, 51, 72, 16);
		fill2(40, 68, 64, 68, 15);
		fill2(65, 62, 65, 67, 16);
		fill2(47, 61, 65, 61, 15);
		fill2(46, 57, 46, 60, 16);
		tiles2[46][61] = 17;
		tiles2[65][68] = 17;
		tiles2[39][68] = 17;
		tiles2[52][33] = 17;
		fill(39, 69, 75, 69, 14);
		fill(133, 41, 133, 53, 14);
		fill(127, 41, 132, 41, 14);
		fill(73, 51, 73, 68, 14);
		fill(68, 51, 72, 51, 14);
		fill(68, 42, 68, 50, 14);
		fill(62, 42, 67, 42, 14);
		fill(118, 61, 127, 61, 14);
		fill(112, 60, 118, 60, 14);
		fill(106, 59, 112, 59, 14);
		fill(100, 60, 106, 60, 14);
		fill(89, 61, 100, 61, 14);
		fill(86, 62, 89, 62, 14);
		fill(84, 63, 86, 63, 14);
		fill(82, 64, 84, 64, 14);
		fill(80, 65, 82, 65, 14);
		fill(77, 66, 79, 66, 14);
		fill(76, 66, 76, 69, 14);
		tiles[80][66] = 14;
		tiles[77][67] = 14;
		tiles[76][66] = 1;
		tiles2[73][74] = 3;
		tiles2[78][63] = 3;
		tiles2[65][59] = 3;
		tiles2[50][60] = 3;
		tiles2[72][48] = 3;
		tiles2[56][39] = 3;
		tiles2[45][44] = 3;
		tiles2[41][29] = 3;
		tiles2[47][19] = 3;
		tiles2[59][25] = 3;
		tiles2[74][29] = 3;
		tiles2[82][20] = 3;
		tiles2[99][16] = 3;
		tiles2[96][33] = 3;
		tiles2[115][41] = 3;
		tiles2[105][50] = 3;
		tiles2[87][54] = 3;
		tiles2[98][64] = 3;
		tiles2[117][61] = 3;
		tiles2[123][58] = 3;
		tiles2[130][52] = 3;
		tiles2[137][46] = 3;
		tiles2[138][38] = 3;
		tiles2[134][23] = 3;
		tiles2[139][58] = 3;
		tiles2[129][73] = 3;
		tiles2[214][45] = 3;
		tiles2[231][42] = 3;
		tiles2[220][39] = 3;
		tiles2[220][39] = 3;
	}
}
