package nl.knokko.rpg.world.maps;

import java.awt.*;
import java.util.ArrayList;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.monsters.*;
import nl.knokko.rpg.gui.GuiChat;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.tiles.Chest;
import nl.knokko.rpg.utils.BackGrounds;
import nl.knokko.rpg.world.Conversation;
import nl.knokko.rpg.world.Portal;
import nl.knokko.rpg.world.maps.MapGenBase;

public class MapGenMiayCave extends MapGenBase {

	public MapGenMiayCave(){
		super();
		build();
		placeChests();
		portals.add(new Portal(new Point(3000, 5970), "Miay Cave", null, null));
		portals.add(new Portal(new Point(4800, 4950), "Miay Cave", new Point(4800, 4950), "Miay Cave Layer 2"));
		portals.add(new Portal(new Point(1890, 4050), "Miay Cave", new Point(1890, 4050), "Miay Cave Layer 2"));
		if(GuiChat.hasConversation(GuiChat.miay_cave))
			conversations.add(new Conversation(GuiChat.miay_cave, new Point(3000, 5940)));
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
		return "Miay Cave";
	}

	@Override
	public ArrayList<Entity> getEnemies(){
		ArrayList<Entity> enemies = new ArrayList<Entity>();
		Game g = Game.game;
		Point p = new Point(-10000, -10000);
		enemies.add(new Troll(g, p, 7));
		enemies.add(new Troll(g, p, 8));
		enemies.add(new Troll(g, p, 9));
		enemies.add(new Bat(g, p, 8));
		enemies.add(new Bat(g, p, 9));
		return enemies;
	}

	@Override
	public int getMaxEnemies(){
		return 3;
	}

	@Override
	public String getMusic(){
		return "miay cave";
	}

	public void build(){
		tiles2[100][199] = 18;
		tiles2[101][199] = 21;
		tiles2[99][199] = 21;
		tiles[100][199] = 21;
		tiles2[102][199] = 21;
		tiles2[103][199] = 21;
		tiles2[97][199] = 21;
		tiles2[98][199] = 21;
		fill2(96, 191, 96, 198, 21);
		tiles2[96][199] = 21;
		tiles[97][198] = 23;
		tiles[98][198] = 23;
		tiles[99][198] = 23;
		tiles[100][198] = 23;
		tiles[101][198] = 23;
		tiles[102][198] = 23;
		tiles[103][198] = 23;
		fill(97, 191, 103, 197, 23);
		fill2(104, 191, 104, 199, 21);
		fill2(104, 190, 110, 190, 21);
		fill2(110, 191, 111, 191, 21);
		tiles2[111][192] = 21;
		tiles2[112][192] = 21;
		tiles2[112][193] = 21;
		tiles2[112][193] = 21;
		tiles2[112][194] = 21;
		tiles2[112][194] = 21;
		tiles2[112][194] = 21;
		tiles2[113][194] = 21;
		tiles2[113][195] = 21;
		fill2(93, 190, 96, 190, 21);
		fill2(91, 189, 93, 189, 21);
		tiles2[91][188] = 21;
		tiles2[90][188] = 21;
		tiles2[90][187] = 21;
		fill2(85, 187, 89, 187, 21);
		fill2(84, 188, 85, 188, 21);
		fill2(84, 189, 84, 193, 21);
		fill2(75, 194, 84, 194, 21);
		fill2(97, 182, 104, 182, 21);
		fill2(94, 181, 97, 181, 21);
		fill2(89, 180, 94, 180, 21);
		fill2(83, 181, 89, 181, 21);
		tiles2[82][182] = 21;
		tiles2[81][183] = 21;
		tiles2[80][183] = 21;
		tiles2[80][183] = 21;
		tiles2[79][183] = 21;
		tiles2[79][184] = 21;
		tiles2[78][184] = 21;
		fill2(74, 194, 74, 196, 21);
		fill2(73, 196, 73, 199, 21);
		fill2(74, 184, 77, 184, 21);
		fill2(68, 186, 68, 191, 21);
		fill2(64, 191, 67, 191, 21);
		fill2(61, 192, 64, 192, 21);
		fill2(66, 199, 72, 199, 21);
		fill2(60, 198, 66, 198, 21);
		fill(97, 183, 103, 190, 23);
		fill(94, 182, 96, 189, 23);
		fill(92, 187, 93, 188, 23);
		tiles[91][187] = 23;
		fill(83, 182, 93, 186, 23);
		fill(90, 181, 93, 181, 23);
		fill(69, 185, 83, 193, 23);
		tiles[84][187] = 23;
		fill(80, 184, 82, 184, 23);
		tiles[82][183] = 23;
		fill(75, 188, 80, 190, 12);
		fill(76, 187, 79, 187, 12);
		tiles[74][189] = 12;
		tiles[74][189] = 12;
		tiles[73][189] = 12;
		tiles[74][190] = 12;
		tiles[73][190] = 12;
		tiles[73][190] = 12;
		tiles[73][190] = 12;
		tiles[72][190] = 12;
		tiles[72][190] = 12;
		fill(74, 191, 80, 191, 12);
		tiles[81][190] = 12;
		tiles[81][189] = 12;
		tiles[81][191] = 12;
		tiles[82][189] = 12;
		fill(76, 192, 78, 192, 12);
		fill(60, 193, 72, 197, 23);
		tiles[73][194] = 23;
		tiles[73][194] = 23;
		tiles[73][194] = 23;
		tiles[73][195] = 23;
		tiles[73][195] = 23;
		tiles[73][195] = 23;
		fill(67, 198, 72, 198, 23);
		fill(65, 192, 68, 192, 23);
		fill2(71, 183, 73, 183, 21);
		fill2(69, 182, 71, 182, 21);
		fill2(68, 181, 69, 181, 21);
		fill2(67, 177, 67, 181, 21);
		fill2(65, 186, 67, 186, 21);
		fill2(63, 185, 65, 185, 21);
		tiles2[63][184] = 21;
		fill2(62, 182, 62, 184, 21);
		fill2(61, 179, 61, 182, 21);
		fill2(54, 199, 60, 199, 21);
		fill2(52, 198, 54, 198, 21);
		tiles2[52][197] = 21;
		fill2(51, 195, 51, 197, 21);
		fill2(58, 192, 60, 192, 21);
		fill2(56, 191, 58, 191, 21);
		fill2(54, 190, 56, 190, 21);
		fill2(53, 189, 54, 189, 21);
		tiles2[52][189] = 21;
		tiles2[52][190] = 21;
		fill2(47, 195, 50, 195, 21);
		fill2(44, 196, 47, 196, 21);
		fill2(49, 190, 51, 190, 21);
		fill2(45, 191, 49, 191, 21);
		fill2(40, 197, 44, 197, 21);
		fill2(41, 192, 45, 192, 21);
		fill2(37, 198, 40, 198, 21);
		fill2(34, 199, 37, 199, 21);
		tiles2[33][198] = 21;
		tiles2[32][198] = 21;
		tiles2[32][197] = 21;
		tiles2[31][197] = 21;
		tiles2[31][196] = 21;
		fill2(37, 191, 41, 191, 21);
		fill2(34, 190, 37, 190, 21);
		fill2(33, 190, 33, 192, 21);
		fill2(32, 192, 32, 194, 21);
		tiles2[31][194] = 21;
		tiles2[31][195] = 21;
		fill(55, 192, 59, 198, 23);
		fill(33, 193, 54, 194, 23);
		fill(34, 192, 54, 192, 23);
		fill(32, 195, 54, 195, 23);
		fill(32, 196, 43, 196, 23);
		fill(50, 191, 55, 191, 23);
		tiles[53][190] = 23;
		fill(53, 196, 54, 197, 23);
		tiles[52][196] = 23;
		fill(33, 197, 39, 197, 23);
		tiles[35][198] = 23;
		tiles[34][198] = 23;
		tiles[36][198] = 23;
		fill(34, 191, 36, 191, 23);
		fill2(60, 172, 60, 179, 21);
		fill2(66, 170, 66, 177, 21);
		fill2(59, 169, 59, 172, 21);
		fill2(65, 165, 65, 170, 21);
		fill2(58, 166, 58, 169, 21);
		fill2(64, 162, 64, 165, 21);
		tiles2[57][166] = 21;
		tiles2[56][167] = 21;
		fill2(53, 168, 55, 168, 21);
		fill2(50, 169, 53, 169, 21);
		fill2(47, 168, 50, 168, 21);
		tiles2[46][167] = 21;
		tiles2[45][166] = 21;
		tiles2[45][166] = 21;
		fill2(63, 160, 63, 162, 21);
		fill2(60, 160, 62, 160, 21);
		fill2(57, 159, 60, 159, 21);
		fill2(53, 158, 57, 158, 21);
		fill2(50, 157, 53, 157, 21);
		fill2(47, 158, 50, 158, 21);
		tiles2[42][163] = 21;
		tiles2[41][164] = 21;
		fill2(40, 165, 40, 167, 21);
		fill2(41, 167, 41, 172, 21);
		fill2(44, 167, 44, 170, 21);
		fill2(42, 172, 42, 175, 21);
		fill2(45, 170, 45, 172, 21);
		tiles2[46][173] = 21;
		tiles2[43][176] = 21;
		tiles2[44][177] = 21;
		fill2(45, 178, 49, 178, 21);
		fill2(47, 174, 52, 174, 21);
		tiles2[53][175] = 21;
		tiles2[54][176] = 21;
		fill2(55, 177, 55, 181, 21);
		fill2(53, 182, 55, 182, 21);
		fill2(50, 183, 53, 183, 21);
		fill2(45, 182, 50, 182, 21);
		fill2(40, 181, 45, 181, 21);
		fill2(35, 180, 40, 180, 21);
		fill2(38, 176, 42, 176, 21);
		fill2(35, 177, 38, 177, 21);
		fill2(32, 179, 35, 179, 21);
		fill2(33, 177, 34, 177, 21);
		tiles2[32][178] = 21;
		tiles2[32][177] = 21;
		fill(43, 174, 52, 181, 23);
		fill(53, 176, 53, 181, 23);
		fill(54, 177, 54, 181, 23);
		tiles[52][182] = 23;
		tiles[52][182] = 23;
		tiles[52][182] = 23;
		tiles[51][182] = 23;
		tiles[51][182] = 23;
		tiles[51][182] = 23;
		fill(32, 177, 42, 179, 23);
		tiles[42][180] = 23;
		tiles[41][180] = 23;
		fill(61, 172, 66, 182, 23);
		fill(62, 183, 73, 184, 23);
		tiles[68][182] = 23;
		tiles[67][182] = 23;
		fill(66, 185, 68, 185, 23);
		fill2(42, 160, 42, 162, 21);
		fill2(41, 157, 41, 160, 21);
		fill2(40, 153, 40, 157, 21);
		fill2(46, 154, 46, 158, 21);
		fill2(45, 149, 45, 154, 21);
		fill2(39, 146, 39, 153, 21);
		fill2(46, 144, 46, 149, 21);
		fill2(40, 139, 40, 146, 21);
		fill2(47, 140, 47, 144, 21);
		fill2(41, 133, 41, 139, 21);
		fill2(42, 131, 42, 134, 21);
		fill2(48, 137, 48, 140, 21);
		fill2(48, 136, 53, 136, 21);
		fill2(43, 131, 49, 131, 21);
		fill2(49, 132, 55, 132, 21);
		fill2(53, 137, 61, 137, 21);
		fill2(55, 131, 64, 131, 21);
		fill2(65, 131, 65, 133, 21);
		fill2(66, 133, 66, 136, 21);
		fill2(65, 137, 66, 137, 21);
		fill2(61, 138, 65, 138, 21);
		fill(43, 132, 65, 136, 23);
		tiles[64][137] = 23;
		tiles[63][137] = 23;
		tiles[63][137] = 23;
		tiles[62][137] = 23;
		tiles[62][137] = 23;
		tiles[62][137] = 23;
		fill(41, 133, 47, 144, 23);
		fill(40, 145, 45, 157, 23);
		fill(42, 158, 57, 166, 23);
		tiles[41][165] = 23;
		tiles[41][166] = 23;
		fill(42, 167, 44, 173, 23);
		tiles[45][173] = 23;
		fill(47, 167, 55, 167, 23);
		tiles[52][168] = 23;
		tiles[51][168] = 23;
		fill(59, 160, 63, 171, 23);
		fill(58, 160, 58, 165, 23);
		fill(64, 166, 64, 171, 23);
		tiles[65][171] = 23;
		fill2(104, 183, 107, 183, 21);
		fill2(107, 184, 111, 184, 21);
		fill2(111, 183, 116, 183, 21);
		fill2(118, 186, 118, 189, 21);
		fill2(118, 190, 120, 190, 21);
		fill2(120, 191, 123, 191, 21);
		fill2(113, 195, 116, 195, 21);
		fill2(116, 196, 119, 196, 21);
		fill2(116, 180, 116, 183, 21);
		fill2(119, 184, 119, 186, 21);
		fill2(120, 182, 120, 184, 21);
		fill2(121, 179, 121, 182, 21);
		fill2(113, 179, 116, 179, 21);
		fill2(122, 176, 122, 179, 21);
		fill2(121, 174, 121, 176, 21);
		fill2(119, 173, 121, 173, 21);
		fill2(116, 172, 119, 172, 21);
		fill2(111, 171, 116, 171, 21);
		fill2(108, 178, 113, 178, 21);
		fill2(108, 172, 111, 172, 21);
		fill2(103, 177, 108, 177, 21);
		fill2(105, 171, 108, 171, 21);
		fill2(100, 170, 105, 170, 21);
		fill2(97, 176, 103, 176, 21);
		fill2(94, 169, 100, 169, 21);
		fill2(94, 175, 97, 175, 21);
		fill2(91, 174, 94, 174, 21);
		fill2(89, 168, 94, 168, 21);
		fill2(85, 169, 89, 169, 21);
		fill2(87, 173, 91, 173, 21);
		fill2(82, 172, 87, 172, 21);
		fill2(82, 170, 84, 170, 21);
		tiles2[82][171] = 21;
		fill(85, 169, 100, 172, 23);
		fill(98, 171, 108, 176, 23);
		fill(92, 173, 97, 174, 23);
		tiles[84][171] = 23;
		tiles[83][171] = 23;
		fill(109, 172, 119, 178, 23);
		fill(116, 174, 120, 184, 23);
		tiles[121][177] = 23;
		tiles[121][178] = 23;
		fill(104, 184, 118, 190, 23);
		fill2(119, 197, 124, 197, 21);
		fill2(124, 198, 129, 198, 21);
		fill2(123, 192, 127, 192, 21);
		fill2(127, 191, 129, 191, 21);
		fill2(129, 190, 132, 190, 21);
		fill2(132, 189, 135, 189, 21);
		fill2(135, 186, 135, 188, 21);
		fill2(136, 181, 136, 186, 21);
		fill2(129, 199, 135, 199, 21);
		fill2(135, 198, 140, 198, 21);
		fill2(140, 197, 147, 197, 21);
		fill2(147, 198, 152, 198, 21);
		fill2(152, 199, 157, 199, 21);
		fill2(144, 190, 149, 190, 21);
		fill2(144, 186, 144, 189, 21);
		fill2(143, 182, 143, 186, 21);
		fill2(149, 191, 152, 191, 21);
		fill2(152, 192, 158, 192, 21);
		fill2(157, 198, 163, 198, 21);
		fill2(163, 199, 168, 199, 21);
		fill2(158, 193, 163, 193, 21);
		fill2(169, 193, 175, 193, 21);
		fill2(175, 194, 180, 194, 21);
		fill2(168, 198, 173, 198, 21);
		fill2(173, 197, 176, 197, 21);
		fill2(176, 198, 180, 198, 21);
		fill2(180, 199, 185, 199, 21);
		fill2(185, 196, 185, 198, 21);
		fill2(180, 195, 185, 195, 21);
		fill2(168, 190, 168, 193, 21);
		fill2(167, 188, 167, 190, 21);
		fill2(166, 186, 166, 188, 21);
		fill2(163, 191, 163, 192, 21);
		fill2(162, 189, 162, 191, 21);
		fill2(159, 188, 162, 188, 21);
		fill2(155, 187, 159, 187, 21);
		fill2(163, 185, 166, 185, 21);
		fill2(159, 184, 163, 184, 21);
		fill2(150, 188, 155, 188, 21);
		fill2(156, 183, 159, 183, 21);
		fill2(152, 184, 156, 184, 21);
		tiles2[151][185] = 21;
		tiles2[150][186] = 21;
		tiles2[150][187] = 21;
		fill2(137, 174, 137, 181, 21);
		fill2(142, 177, 142, 182, 21);
		fill2(143, 171, 143, 177, 21);
		fill2(136, 169, 136, 174, 21);
		fill2(144, 165, 144, 171, 21);
		fill2(135, 163, 135, 169, 21);
		fill(113, 191, 123, 195, 23);
		tiles[112][191] = 23;
		fill(120, 192, 129, 197, 23);
		fill(130, 190, 140, 198, 23);
		fill(141, 191, 152, 197, 23);
		fill(153, 193, 173, 198, 23);
		fill(176, 195, 184, 198, 23);
		fill(174, 194, 175, 196, 23);
		fill(162, 185, 166, 191, 23);
		fill(164, 191, 167, 192, 23);
		fill(151, 185, 161, 187, 23);
		fill(157, 184, 158, 184, 23);
		fill(137, 167, 142, 190, 23);
		fill(143, 187, 143, 190, 23);
		fill(136, 187, 136, 189, 23);
		fill2(134, 161, 134, 163, 21);
		fill2(133, 159, 133, 161, 21);
		fill2(145, 162, 145, 165, 21);
		fill2(146, 158, 146, 162, 21);
		fill2(130, 159, 133, 159, 21);
		fill2(127, 158, 130, 158, 21);
		fill2(127, 152, 133, 152, 21);
		fill2(133, 151, 136, 151, 21);
		fill2(136, 150, 141, 150, 21);
		fill2(141, 151, 144, 151, 21);
		fill2(123, 153, 127, 153, 21);
		fill2(119, 152, 123, 152, 21);
		fill2(124, 159, 127, 159, 21);
		fill2(119, 160, 124, 160, 21);
		fill2(115, 159, 119, 159, 21);
		fill2(113, 153, 119, 153, 21);
		fill2(109, 154, 113, 154, 21);
		fill2(111, 158, 115, 158, 21);
		fill2(107, 157, 111, 157, 21);
		fill2(106, 155, 109, 155, 21);
		tiles2[106][156] = 21;
		tiles2[106][157] = 21;
		fill2(144, 152, 152, 152, 21);
		fill2(147, 158, 150, 158, 21);
		fill2(152, 153, 157, 153, 21);
		fill2(150, 159, 154, 159, 21);
		fill2(157, 154, 162, 154, 21);
		fill2(154, 160, 156, 160, 21);
		fill2(157, 160, 157, 162, 21);
		fill2(163, 154, 163, 157, 21);
		fill2(164, 157, 164, 161, 21);
		fill2(163, 161, 163, 164, 21);
		fill2(158, 163, 158, 168, 21);
		fill2(159, 168, 163, 168, 21);
		fill2(163, 165, 163, 167, 21);
		fill(136, 151, 143, 170, 23);
		fill(144, 153, 157, 158, 23);
		fill(158, 155, 163, 168, 23);
		fill(155, 159, 157, 159, 23);
		fill(144, 159, 145, 164, 23);
		fill(113, 153, 135, 158, 23);
		fill(134, 152, 135, 162, 23);
		fill(120, 159, 123, 159, 23);
		fill(107, 155, 112, 157, 23);
		fill(137, 155, 142, 158, 12);
		fill(139, 154, 142, 154, 12);
		fill(143, 155, 143, 156, 12);
		fill(136, 159, 140, 159, 12);
		tiles[136][158] = 12;
		tiles[136][157] = 12;
		tiles[63][135] = 0;
		tiles2[63][135] = 24;
		tiles2[160][165] = 24;
		tiles[160][165] = 0;
	}
	
	public void placeChests(){
		placeChest(new Chest(46, new Point(3210, 4680)).setMoney(53));
		placeChest(new Chest(47, new Point(4140, 4620)).addItem(Items.pickaxe).setMoney(13));
		placeChest(new Chest(48, new Point(4620, 5610)).setMoney(23).addItem(Items.torch));
		placeChest(new Chest(49, new Point(5430, 5940)).addItem(Items.orangePotion).setMoney(56));
		placeChest(new Chest(50, new Point(4230, 5790)).addItemStack(Items.potion, 2).addItem(Items.ether));
		placeChest(new Chest(51, new Point(3090, 5730)).addItem(Items.orangeEther).setMoney(24));
		placeChest(new Chest(52, new Point(2250, 5760)).setMoney(85));
		placeChest(new Chest(53, new Point(960, 5880)).addItem(Items.miayGem));
		placeChest(new Chest(54, new Point(990, 5340)).addItem(Items.stoneSword));
		placeChest(new Chest(55, new Point(1200, 4410)).setMoney(62));
		placeChest(new Chest(56, new Point(2490, 5130)).addItems(Items.orangeEther, Items.potion));
	}

	@Override
	public Point getStartPoint(MapGenBase previousMap) {
		return new Point(3000, 5940);
	}

	@Override
	public String getFightBackGround() {
		return BackGrounds.miay_cave;
	}
}
