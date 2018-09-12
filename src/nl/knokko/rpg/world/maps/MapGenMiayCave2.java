package nl.knokko.rpg.world.maps;

import java.awt.*;
import java.util.ArrayList;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.Villager;
import nl.knokko.rpg.entities.monsters.Bat;
import nl.knokko.rpg.entities.monsters.Spider;
import nl.knokko.rpg.entities.monsters.Troll;
import nl.knokko.rpg.entities.players.Richard;
import nl.knokko.rpg.gui.GuiChat;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.ItemArmor;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.items.SpecialItems;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.quests.TalkQuest;
import nl.knokko.rpg.tiles.Chest;
import nl.knokko.rpg.utils.BackGrounds;
import nl.knokko.rpg.utils.SpecialText;
import nl.knokko.rpg.world.Portal;
import nl.knokko.rpg.world.maps.MapGenBase;

public class MapGenMiayCave2 extends MapGenBase {

	public MapGenMiayCave2(){
		super();
		build();
		spawnMiners();
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
		return "Miay Cave Layer 2";
	}

	@Override
	public ArrayList<Entity> getEnemies(){
		ArrayList<Entity> enemies = new ArrayList<Entity>();
		Point p = new Point(-100000, -100000);
		Game g = Game.game;
		enemies.add(new Troll(g, p, 10));
		enemies.add(new Troll(g, p, 11));
		enemies.add(new Troll(g, p, 12));
		enemies.add(new Spider(g, p, 10));
		enemies.add(new Bat(g, p, 11));
		enemies.add(new Bat(g, p, 12));
		return enemies;
	}

	@Override
	public int getMaxEnemies(){
		return 3;
	}

	@Override
	public Point getStartPoint(MapGenBase old){
		return new Point();
	}
	
	@Override
	public String getMusic(){
		return "miay cave";
	}

	public void build(){
		fill2(155, 162, 155, 170, 21);
		fill2(164, 162, 164, 166, 21);
		fill2(165, 166, 165, 171, 21);
		fill2(156, 161, 159, 161, 21);
		fill2(159, 160, 163, 160, 21);
		tiles2[164][161] = 21;
		fill(156, 161, 164, 171, 23);
		fill2(156, 171, 156, 174, 21);
		tiles2[155][171] = 21;
		tiles2[155][161] = 21;
		tiles2[164][160] = 21;
		fill2(166, 171, 166, 176, 21);
		fill2(167, 176, 167, 177, 21);
		fill2(168, 177, 168, 178, 21);
		fill2(157, 174, 157, 177, 21);
		fill2(158, 177, 158, 181, 21);
		fill2(159, 181, 159, 182, 21);
		fill2(160, 182, 160, 183, 21);
		fill2(161, 183, 164, 183, 21);
		fill2(164, 184, 167, 184, 21);
		fill2(168, 179, 171, 179, 21);
		fill(158, 172, 166, 181, 23);
		fill(157, 172, 157, 173, 23);
		fill2(167, 185, 171, 185, 21);
		fill2(171, 186, 177, 186, 21);
		fill2(172, 179, 177, 179, 21);
		fill2(177, 178, 179, 178, 21);
		fill2(179, 177, 180, 177, 21);
		fill2(177, 185, 183, 185, 21);
		fill2(183, 186, 192, 186, 21);
		fill2(181, 175, 181, 177, 21);
		fill2(182, 172, 182, 175, 21);
		fill2(188, 172, 188, 174, 21);
		fill2(189, 174, 189, 176, 21);
		fill2(189, 177, 190, 177, 21);
		fill2(190, 178, 192, 178, 21);
		fill2(192, 179, 195, 179, 21);
		fill2(195, 180, 197, 180, 21);
		fill2(197, 181, 198, 181, 21);
		fill2(198, 182, 199, 182, 21);
		fill2(192, 187, 193, 187, 21);
		fill2(193, 188, 194, 188, 21);
		fill2(194, 189, 194, 192, 21);
		fill2(192, 192, 193, 192, 21);
		fill2(189, 193, 192, 193, 21);
		fill2(199, 183, 199, 190, 21);
		fill2(198, 191, 199, 191, 21);
		fill2(198, 192, 198, 195, 21);
		fill2(197, 195, 197, 197, 21);
		fill2(195, 197, 196, 197, 21);
		fill2(192, 198, 195, 198, 21);
		fill2(185, 199, 192, 199, 21);
		fill2(181, 198, 185, 198, 21);
		fill2(185, 192, 189, 192, 21);
		tiles2[185][191] = 21;
		fill2(184, 190, 184, 191, 21);
		fill2(182, 189, 184, 189, 21);
		fill2(175, 188, 182, 188, 21);
		fill2(175, 199, 181, 199, 21);
		fill2(169, 198, 174, 198, 21);
		fill2(172, 189, 175, 189, 21);
		fill2(167, 190, 172, 190, 21);
		fill2(166, 197, 169, 197, 21);
		fill2(165, 195, 165, 197, 21);
		fill2(164, 193, 164, 195, 21);
		fill2(165, 191, 167, 191, 21);
		fill2(165, 192, 165, 193, 21);
		fill(167, 179, 195, 184, 23);
		fill(165, 183, 166, 183, 23);
		fill(161, 182, 166, 182, 23);
		tiles[167][178] = 23;
		tiles[167][178] = 23;
		fill(172, 185, 176, 185, 23);
		fill(180, 178, 189, 178, 23);
		fill(182, 177, 188, 177, 23);
		fill(182, 176, 188, 176, 23);
		fill(183, 175, 188, 175, 23);
		fill(183, 172, 187, 174, 23);
		fill(184, 185, 198, 185, 23);
		fill(196, 183, 198, 184, 23);
		tiles[196][182] = 23;
		tiles[197][182] = 23;
		tiles[196][181] = 23;
		tiles[196][181] = 23;
		fill(195, 186, 196, 196, 23);
		fill(197, 186, 197, 194, 23);
		fill(198, 186, 198, 190, 23);
		tiles[193][186] = 23;
		tiles[194][187] = 23;
		tiles[194][186] = 23;
		tiles[194][186] = 23;
		fill(169, 193, 194, 198, 23);
		fill(172, 189, 184, 192, 23);
		fill(166, 191, 168, 196, 23);
		fill(169, 191, 171, 192, 23);
		tiles[165][194] = 23;
		fill2(181, 166, 181, 172, 21);
		fill2(187, 167, 187, 172, 21);
		fill2(180, 161, 180, 166, 21);
		fill2(186, 162, 186, 167, 21);
		fill2(185, 156, 185, 162, 21);
		fill2(179, 159, 179, 161, 21);
		fill2(184, 154, 184, 156, 21);
		fill2(178, 158, 178, 159, 21);
		fill2(183, 153, 183, 154, 21);
		fill2(179, 153, 182, 153, 21);
		fill2(174, 157, 178, 157, 21);
		fill2(176, 152, 179, 152, 21);
		fill2(174, 151, 176, 151, 21);
		fill2(167, 156, 174, 156, 21);
		fill2(171, 150, 174, 150, 21);
		fill2(167, 149, 171, 149, 21);
		fill2(160, 157, 167, 157, 21);
		fill2(166, 148, 167, 148, 21);
		fill2(165, 147, 165, 148, 21);
		fill2(164, 145, 164, 147, 21);
		fill2(165, 143, 165, 145, 21);
		fill2(166, 141, 166, 143, 21);
		fill2(167, 140, 167, 141, 21);
		fill2(168, 139, 168, 140, 21);
		fill2(169, 139, 170, 139, 21);
		fill2(170, 138, 174, 138, 21);
		fill2(154, 158, 160, 158, 21);
		fill2(149, 159, 154, 159, 21);
		fill2(157, 145, 157, 148, 21);
		fill2(155, 149, 157, 149, 21);
		fill2(151, 150, 155, 150, 21);
		fill2(148, 151, 151, 151, 21);
		fill2(158, 142, 158, 145, 21);
		fill2(159, 140, 159, 142, 21);
		fill2(143, 152, 148, 152, 21);
		fill2(144, 160, 149, 160, 21);
		fill2(140, 153, 143, 153, 21);
		fill2(137, 154, 140, 154, 21);
		fill2(139, 161, 144, 161, 21);
		fill2(160, 138, 160, 140, 21);
		fill2(161, 137, 161, 138, 21);
		fill2(161, 136, 163, 136, 21);
		fill2(163, 135, 164, 135, 21);
		fill2(164, 134, 165, 134, 21);
		fill2(165, 133, 167, 133, 21);
		fill2(167, 132, 170, 132, 21);
		fill2(170, 131, 173, 131, 21);
		fill2(173, 130, 177, 130, 21);
		fill2(177, 131, 179, 131, 21);
		fill2(179, 132, 181, 132, 21);
		fill2(174, 137, 177, 137, 21);
		fill2(177, 136, 179, 136, 21);
		fill2(179, 135, 181, 135, 21);
		fill2(182, 134, 184, 134, 21);
		tiles2[184][133] = 21;
		fill2(182, 132, 183, 132, 21);
		fill(171, 132, 179, 136, 23);
		fill(180, 133, 183, 134, 23);
		fill(174, 131, 176, 131, 23);
		fill(171, 137, 173, 137, 23);
		fill(166, 133, 170, 137, 23);
		fill(161, 137, 166, 140, 23);
		fill(164, 135, 165, 136, 23);
		fill(167, 138, 169, 139, 23);
		fill(160, 141, 164, 156, 23);
		fill(165, 141, 165, 142, 23);
		fill(158, 143, 159, 157, 23);
		fill(165, 150, 174, 156, 23);
		fill(165, 149, 166, 149, 23);
		fill(175, 152, 179, 157, 23);
		fill(180, 154, 184, 165, 23);
		tiles[179][158] = 23;
		fill(182, 166, 186, 171, 23);
		fill(185, 163, 185, 165, 23);
		fill(151, 150, 157, 157, 23);
		fill(144, 153, 153, 158, 23);
		fill(149, 152, 150, 152, 23);
		fill(139, 155, 143, 160, 23);
		fill(144, 159, 148, 159, 23);
		fill(141, 154, 143, 154, 23);
		tiles2[184][132] = 21;
		fill2(137, 162, 139, 162, 21);
		fill2(135, 163, 137, 163, 21);
		fill2(135, 153, 137, 153, 21);
		fill2(133, 152, 135, 152, 21);
		fill2(133, 164, 135, 164, 21);
		fill2(124, 164, 126, 164, 21);
		fill2(122, 163, 124, 163, 21);
		fill2(120, 162, 122, 162, 21);
		fill2(124, 152, 126, 152, 21);
		fill2(122, 153, 124, 153, 21);
		fill2(120, 154, 122, 154, 21);
		fill2(126, 149, 126, 151, 21);
		fill2(133, 149, 133, 151, 21);
		fill2(134, 145, 134, 149, 21);
		fill2(126, 147, 126, 148, 21);
		fill2(127, 142, 127, 147, 21);
		fill2(128, 140, 128, 142, 21);
		fill2(135, 141, 135, 145, 21);
		fill2(129, 137, 129, 140, 21);
		fill2(136, 138, 136, 141, 21);
		fill2(137, 138, 139, 138, 21);
		fill2(139, 137, 142, 137, 21);
		fill2(142, 136, 145, 136, 21);
		fill2(145, 137, 148, 137, 21);
		fill2(148, 138, 152, 138, 21);
		fill2(152, 139, 155, 139, 21);
		tiles2[155][138] = 21;
		tiles2[156][138] = 21;
		tiles2[156][137] = 21;
		tiles2[157][137] = 21;
		tiles2[157][136] = 21;
		fill2(136, 132, 139, 132, 21);
		fill2(139, 131, 143, 131, 21);
		fill2(143, 132, 149, 132, 21);
		fill2(149, 133, 152, 133, 21);
		fill2(152, 134, 155, 134, 21);
		fill2(155, 135, 157, 135, 21);
		fill2(132, 131, 136, 131, 21);
		fill2(129, 130, 132, 130, 21);
		fill2(125, 129, 129, 129, 21);
		fill2(127, 136, 129, 136, 21);
		fill2(124, 135, 127, 135, 21);
		fill2(121, 134, 124, 134, 21);
		fill2(119, 133, 121, 133, 21);
		fill2(124, 128, 125, 128, 21);
		fill2(123, 126, 123, 128, 21);
		fill2(117, 132, 119, 132, 21);
		fill2(117, 130, 117, 131, 21);
		fill2(116, 127, 116, 130, 21);
		fill2(115, 123, 115, 127, 21);
		fill2(122, 123, 122, 126, 21);
		fill2(123, 120, 123, 123, 21);
		fill2(116, 118, 116, 123, 21);
		fill2(124, 116, 124, 120, 21);
		fill2(117, 117, 117, 118, 21);
		fill2(118, 114, 118, 117, 21);
		fill2(125, 112, 125, 116, 21);
		fill2(119, 110, 119, 114, 21);
		fill2(120, 107, 120, 110, 21);
		fill2(124, 109, 124, 112, 21);
		fill2(123, 106, 123, 109, 21);
		fill2(121, 105, 121, 107, 21);
		fill2(122, 105, 123, 105, 21);
		fill(120, 107, 123, 123, 23);
		fill(124, 113, 124, 115, 23);
		fill(117, 117, 119, 122, 23);
		tiles[119][131] = 23;
		fill(119, 115, 119, 116, 23);
		fill(116, 123, 122, 130, 23);
		tiles[118][131] = 23;
		fill(120, 131, 136, 133, 23);
		fill(123, 129, 128, 130, 23);
		fill(130, 134, 135, 140, 23);
		fill(136, 133, 152, 136, 23);
		fill(140, 132, 142, 132, 23);
		fill(136, 137, 138, 137, 23);
		fill(125, 134, 129, 135, 23);
		fill(153, 135, 156, 138, 23);
		fill(149, 137, 152, 137, 23);
		fill(129, 141, 134, 148, 23);
		fill(127, 143, 128, 164, 23);
		fill(129, 149, 133, 164, 23);
		fill(134, 154, 138, 162, 23);
		tiles[134][153] = 23;
		tiles[134][163] = 23;
		fill(122, 153, 126, 163, 23);
		fill(118, 155, 121, 161, 23);
		fill2(126, 165, 126, 169, 21);
		fill2(132, 164, 132, 168, 21);
		fill2(133, 168, 133, 173, 21);
		fill2(127, 169, 127, 176, 21);
		fill2(128, 176, 128, 180, 21);
		fill2(134, 173, 134, 178, 21);
		fill2(135, 178, 135, 182, 21);
		fill2(129, 180, 129, 185, 21);
		fill2(130, 185, 130, 189, 21);
		fill2(136, 182, 136, 184, 21);
		fill2(137, 184, 137, 185, 21);
		fill2(131, 189, 132, 189, 21);
		fill2(132, 190, 135, 190, 21);
		fill2(137, 186, 142, 186, 21);
		fill2(135, 191, 138, 191, 21);
		fill2(138, 192, 143, 192, 21);
		fill2(144, 191, 144, 192, 21);
		fill2(145, 189, 145, 191, 21);
		fill2(143, 186, 143, 187, 21);
		tiles2[144][187] = 21;
		tiles2[145][187] = 21;
		tiles2[145][188] = 21;
		fill(127, 165, 132, 176, 23);
		fill(129, 177, 134, 185, 23);
		fill(131, 186, 143, 189, 23);
		fill(136, 190, 144, 191, 23);
		fill(144, 188, 144, 189, 23);
		fill(135, 183, 136, 185, 23);
		fill(133, 174, 133, 176, 23);
		fill2(112, 161, 119, 161, 21);
		fill2(116, 153, 120, 153, 21);
		fill2(110, 152, 116, 152, 21);
		fill2(108, 160, 112, 160, 21);
		fill2(103, 159, 108, 159, 21);
		fill2(100, 158, 103, 158, 21);
		fill2(106, 151, 110, 151, 21);
		fill2(99, 150, 106, 150, 21);
		fill2(95, 151, 99, 151, 21);
		fill2(92, 152, 95, 152, 21);
		fill2(95, 157, 100, 157, 21);
		fill2(91, 156, 95, 156, 21);
		fill2(88, 155, 91, 155, 21);
		fill2(88, 153, 92, 153, 21);
		tiles2[88][154] = 21;
		fill(100, 151, 110, 158, 23);
		fill(111, 153, 119, 160, 23);
		fill(109, 159, 110, 159, 23);
		fill(92, 152, 99, 156, 23);
		fill(89, 154, 91, 154, 23);
		fill(102, 153, 108, 156, 12);
		fill(105, 157, 107, 157, 12);
		fill(101, 154, 101, 155, 12);
		fill(103, 152, 106, 152, 12);
		fill(109, 154, 109, 155, 12);
		fill2(58, 131, 58, 137, 21);
		fill2(58, 130, 60, 130, 21);
		fill2(60, 129, 63, 129, 21);
		fill2(63, 128, 65, 128, 21);
		fill2(65, 129, 68, 129, 21);
		fill2(68, 130, 69, 130, 21);
		fill2(70, 130, 70, 133, 21);
		fill2(59, 137, 59, 139, 21);
		fill2(60, 139, 60, 142, 21);
		fill2(71, 133, 71, 137, 21);
		fill2(72, 137, 72, 142, 21);
		fill2(61, 142, 61, 144, 21);
		fill2(73, 142, 73, 147, 21);
		fill2(62, 144, 62, 146, 21);
		fill2(74, 147, 74, 151, 21);
		fill2(63, 146, 63, 148, 21);
		fill2(64, 148, 64, 151, 21);
		fill2(65, 151, 65, 154, 21);
		fill2(64, 154, 64, 156, 21);
		fill2(62, 156, 63, 156, 21);
		fill2(58, 157, 62, 157, 21);
		fill2(73, 151, 73, 153, 21);
		fill2(74, 153, 74, 156, 21);
		fill2(75, 156, 75, 157, 21);
		fill2(75, 158, 79, 158, 21);
		fill2(59, 164, 62, 164, 21);
		fill2(62, 165, 64, 165, 21);
		fill2(64, 166, 66, 166, 21);
		fill2(66, 167, 71, 167, 21);
		fill2(71, 168, 76, 168, 21);
		fill2(76, 167, 80, 167, 21);
		fill2(53, 163, 59, 163, 21);
		fill2(49, 162, 53, 162, 21);
		fill2(55, 156, 58, 156, 21);
		fill2(46, 161, 49, 161, 21);
		fill2(46, 159, 46, 160, 21);
		fill2(45, 157, 45, 159, 21);
		fill2(44, 153, 44, 157, 21);
		fill2(54, 153, 54, 156, 21);
		fill2(53, 149, 53, 153, 21);
		fill2(43, 146, 43, 153, 21);
		fill2(52, 146, 52, 149, 21);
		fill2(42, 139, 42, 146, 21);
		fill2(51, 141, 51, 146, 21);
		fill2(41, 134, 41, 139, 21);
		fill2(50, 134, 50, 141, 21);
		fill2(51, 129, 51, 134, 21);
		fill2(42, 129, 42, 134, 21);
		fill2(52, 126, 52, 129, 21);
		fill2(43, 125, 43, 129, 21);
		fill2(53, 125, 56, 125, 21);
		tiles2[53][126] = 21;
		fill2(56, 124, 60, 124, 21);
		fill2(44, 123, 44, 125, 21);
		fill2(45, 122, 45, 123, 21);
		fill2(45, 121, 47, 121, 21);
		fill2(47, 120, 51, 120, 21);
		fill2(51, 119, 57, 119, 21);
		fill2(57, 118, 63, 118, 21);
		fill2(60, 125, 66, 125, 21);
		fill2(66, 126, 71, 126, 21);
		fill2(63, 117, 69, 117, 21);
		fill2(69, 118, 74, 118, 21);
		fill2(71, 125, 76, 125, 21);
		fill2(76, 124, 79, 124, 21);
		fill2(74, 119, 77, 119, 21);
		fill2(77, 120, 80, 120, 21);
		fill2(79, 123, 83, 123, 21);
		fill2(80, 121, 82, 121, 21);
		tiles2[83][122] = 21;
		tiles2[82][122] = 21;
		tiles2[83][123] = 0;
		tiles2[83][122] = 0;
		fill(59, 130, 70, 139, 23);
		tiles[64][129] = 23;
		fill(65, 140, 73, 165, 23);
		fill(61, 140, 64, 144, 23);
		fill(63, 145, 64, 147, 23);
		fill(71, 138, 71, 139, 23);
		fill(49, 157, 64, 162, 23);
		fill(60, 163, 64, 164, 23);
		fill(45, 126, 49, 156, 23);
		fill(45, 121, 49, 125, 23);
		fill(43, 125, 44, 152, 23);
		fill(42, 135, 42, 138, 23);
		fill(50, 121, 51, 133, 23);
		fill(52, 120, 79, 124, 23);
		tiles[52][125] = 23;
		fill(58, 119, 73, 119, 23);
		fill(64, 118, 68, 118, 23);
		fill(67, 125, 70, 125, 23);
		tiles[80][122] = 23;
		tiles[81][122] = 23;
		fill(50, 142, 51, 156, 23);
		fill(52, 150, 53, 156, 23);
		fill(46, 157, 48, 160, 23);
		fill2(79, 159, 85, 159, 21);
		fill2(80, 168, 84, 168, 21);
		fill2(84, 169, 89, 169, 21);
		fill2(85, 160, 87, 160, 21);
		fill2(87, 161, 90, 161, 21);
		fill2(89, 170, 95, 170, 21);
		fill2(90, 162, 93, 162, 21);
		fill2(93, 163, 99, 163, 21);
		fill2(95, 171, 99, 171, 21);
		fill2(99, 172, 101, 172, 21);
		fill2(101, 173, 102, 173, 21);
		tiles2[102][174] = 21;
		fill2(103, 174, 103, 176, 21);
		fill2(99, 164, 102, 164, 21);
		fill2(102, 165, 105, 165, 21);
		fill2(105, 166, 107, 166, 21);
		fill2(108, 166, 108, 168, 21);
		fill2(109, 168, 109, 171, 21);
		fill2(110, 171, 110, 174, 21);
		fill2(111, 174, 111, 177, 21);
		fill2(112, 177, 112, 181, 21);
		fill2(104, 176, 104, 181, 21);
		fill2(111, 181, 111, 184, 21);
		fill2(110, 184, 110, 187, 21);
		fill2(109, 187, 109, 189, 21);
		fill2(105, 181, 105, 186, 21);
		fill2(106, 187, 106, 192, 21);
		tiles2[105][187] = 21;
		fill2(108, 189, 108, 190, 21);
		fill2(108, 191, 108, 192, 21);
		tiles2[107][192] = 21;
		fill(105, 174, 111, 184, 23);
		fill(106, 185, 108, 191, 23);
		fill(109, 185, 109, 186, 23);
		fill(101, 167, 108, 173, 23);
		fill(104, 174, 104, 175, 23);
		fill(109, 172, 109, 173, 23);
		fill(89, 164, 100, 170, 23);
		tiles[100][171] = 23;
		fill(101, 165, 104, 166, 23);
		fill(80, 161, 88, 168, 23);
		fill(89, 162, 92, 163, 23);
		fill(74, 158, 79, 166, 23);
		fill(80, 160, 84, 160, 23);
		fill(67, 166, 75, 167, 23);
		tiles[74][157] = 23;
		tiles[73][140] = 0;
		tiles[73][141] = 0;
		tiles[108][182] = 0;
	}
	
	public void spawnMiners(){
		Game g = Game.game;
		TalkQuest q = (TalkQuest) g.quests.getQuest("miners");
		if(q != null){
			if(q.progress < 1)
				entities.add(Villager.questMiner(g, new Point(5340, 5760), true, new ItemArmor[4], Items.pickaxe, new GuiChat(g, false, false, new SpecialText("Bart?", "You are here to safe us?"), new SpecialText("Yes, I am."), new SpecialText("Thank you very much.")).setSpeakers(new String[]{"Miner", "Bart", "Miner"})));
			if(q.progress < 2)
				entities.add(Villager.questMiner(g, new Point(5280, 5700), true, new ItemArmor[4], Items.pickaxe, new GuiChat(g, false, false, new SpecialText("Hey Bart,", "What are you doing here?"), new SpecialText("I am here to save you."), new SpecialText("You are going to save us?"), new SpecialText("Yes, like I said."), new SpecialText("Thank you.")).setSpeakers(new String[]{"Miner", "Bart", "Miner", "Bart", "Miner"})));
			if(q.progress < 3)
				entities.add(new Richard(g, new Point(5220, 5820)));
		}
	}
	
	public void placeChests(){
		placeChest(new Chest(56, new Point(5250, 5820)).addItems(Items.pickaxe, Items.miayGem));
		placeChest(new Chest(57, new Point(5220, 4020), SpecialItems.BLUE_PRINT_HELMET).setMoney(124));
		placeChest(new Chest(58, new Point(3660, 3180)).addItemStack(new ItemStack(Items.miayGem, 3)).setMoney(46));
		placeChest(new Chest(59, new Point(4200, 5670)).addItem(Items.miaySword));
		placeChest(new Chest(60, new Point(2910, 4620)).addItems(Items.potion, Items.orangePotion, Items.ether).setMoney(53));
		placeChest(new Chest(61, new Point(2010, 3750)).addItemStack(Items.miayGem, 4).setMoney(12));
		placeChest(new Chest(62, new Point(2040, 3750)).setMoney(176));
		placeChest(new Chest(63, new Point(2070, 3540)).addItems(Items.orangeEther, Items.orangePotion));
		placeChest(new Chest(64, new Point(1920, 3540)).addItem(Items.miayHelmet));
	}
	
	public void placePortals(){
		tiles2[160][165] = 24;
		portals.add(new Portal(new Point(4800, 4950), "Miay Cave Layer 2", new Point(4800, 4950), "Miay Cave"));
		tiles2[63][135] = 24;
		portals.add(new Portal(new Point(1890, 4050), "Miay Cave Layer 2", new Point(1890, 4050), "Miay Cave"));
		tiles2[108][182] = 24;
		portals.add(new Portal(new Point(3240, 5460), "Miay Cave Layer 2", new Point(3240, 5460), "Miay Cave Layer 3"));
	}

	@Override
	public String getFightBackGround() {
		return BackGrounds.miay_cave;
	}
}
