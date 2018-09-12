package nl.knokko.rpg.world.maps;

import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.monsters.*;
import nl.knokko.rpg.gui.GuiChat;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.items.SpecialItems;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.tiles.Chest;
import nl.knokko.rpg.utils.BackGrounds;
import nl.knokko.rpg.world.Conversation;
import nl.knokko.rpg.world.Portal;

public class MapGenFoidForest extends MapGenBase {

	public MapGenFoidForest() {
		super();
		build();
		placeChests();
		portals.add(new Portal(new Point(3030, 3000), "Foid Forest", null, null));
		portals.add(new Portal(new Point(780, 2850), "Foid Forest", null, null, "Lost Plains"));
		portals.add(new Portal(new Point(4770, 2130), "Foid Forest", null, null, "Miay Cave"));
		portals.add(new Portal(new Point(1950, 5280), "Foid Forest", null, null));
		if(GuiChat.hasConversation(GuiChat.foid_forest))
			conversations.add(new Conversation(GuiChat.foid_forest, new Point(3000, 3000)));
	}

	@Override
	public int getXSize() {
		return 300;
	}

	@Override
	public int getYSize() {
		return 300;
	}

	@Override
	public String getName() {
		return "Foid Forest";
	}
	
	@Override
	public String getMusic(){
		return "foid forest";
	}

	@Override
	public ArrayList<Entity> getEnemies() {
		ArrayList<Entity> enemies = new ArrayList<Entity>();
		Point p = new Point(-10000, -10000);
		Game g = Game.game;
		enemies.add(new Foid(g, p, 3));
		enemies.add(new Foid(g, p, 4));
		enemies.add(new Foid(g, p, 5));
		enemies.add(new Mantid(g, p, 4));
		enemies.add(new Mantid(g, p, 3));
		enemies.add(new Wasp(g, p, 4));
		enemies.add(new Plant(g, p, 4));
		enemies.add(new Plant(g, p, 5));
		enemies.add(new Plant(g, p, 3));
		enemies.add(new Snake(g, p, 4));
		enemies.add(new Snake(g, p, 5));
		return enemies;
	}

	@Override
	public int getMaxEnemies() {
		return 3;
	}
	
	@Override
	public Point entranceEast(){
		return new Point(3000, 3000);
	}
	
	@Override
	public Point entranceNorth(){
		return new Point(4770, 2160);
	}
	
	@Override
	public Point entranceWest(){
		return new Point(810, 2850);
	}
	
	@Override
	public Point entranceSouth(){
		return entranceWest();
	}
	
	@Override
	public String getFightBackGround(){
		return BackGrounds.forest;
	}
	
	public void build(){
		fill(0, 0, 299, 299, 1);
		fill2(0, 0, 299, 299, 3);
		fill2(90, 95, 100, 100, 0);
		fill2(75, 97, 90, 97, 0);
		fill2(74, 97, 75, 110, 0);
		fill2(60, 111, 80, 119, 0);
		fill2(81, 115, 95, 116, 0);
		fill2(95, 104, 95, 114, 0);
		fill2(70, 120, 70, 140, 0);
		fill2(52, 140, 69, 140, 0);
		fill2(71, 133, 80, 133, 0);
		fill2(81, 133, 81, 140, 0);
		fill2(69, 100, 69, 110, 0);
		fill2(60, 100, 68, 100, 0);
		fill2(60, 90, 60, 110, 0);
		fill2(40, 90, 59, 90, 0);
		fill2(40, 85, 40, 110, 0);
		fill2(30, 110, 39, 110, 0);
		fill2(52, 141, 52, 170, 0);
		fill2(40, 155, 51, 155, 0);
		fill2(40, 130, 40, 154, 0);
		fill2(40, 129, 58, 129, 0);
		fill2(58, 120, 58, 128, 0);
		fill2(59, 120, 60, 120, 0);
		fill2(30, 85, 55, 85, 0);
		fill2(30, 85, 30, 95, 0);
		fill2(26, 95, 30, 95, 0);
		fill(61, 112, 77, 117, 12);
		fill(62, 111, 64, 118, 12);
		fill(60, 113, 60, 115, 12);
		fill(65, 118, 67, 118, 12);
		fill(63, 119, 65, 119, 12);
		fill2(66, 119, 69, 119, 3);
		fill2(68, 118, 69, 118, 3);
		tiles[77][112] = 1;
		fill(68, 112, 71, 112, 1);
		fill2(68, 112, 71, 112, 3);
		fill2(65, 111, 68, 111, 3);
		tiles[77][117] = 1;
		fill(70, 117, 73, 117, 1);
		tiles2[101][100] = 0;
		tiles2[61][111] = 3;
		fill2(55, 75, 55, 84, 0);
		fill2(56, 74, 81, 75, 0);
		fill2(81, 63, 81, 73, 0);
		fill2(45, 63, 80, 63, 0);
		fill2(45, 64, 45, 72, 0);
		fill2(81, 56, 81, 62, 0);
		fill2(81, 76, 81, 81, 0);
		fill2(82, 80, 101, 81, 0);
		fill2(101, 82, 101, 88, 0);
		fill2(85, 88, 100, 88, 0);
		fill2(85, 89, 85, 92, 0);
		fill2(82, 56, 102, 56, 0);
		fill2(82, 68, 101, 69, 0);
		fill2(98, 74, 98, 79, 0);
		fill2(94, 74, 97, 74, 0);
		fill2(94, 70, 94, 73, 0);
		fill2(101, 67, 116, 67, 0);
		fill2(116, 68, 116, 72, 0);
		fill2(116, 73, 127, 73, 0);
		fill2(127, 72, 137, 72, 0);
		fill2(137, 71, 144, 71, 0);
		fill2(116, 52, 116, 66, 0);
		fill2(97, 52, 115, 52, 0);
		fill2(116, 51, 122, 51, 0);
		fill2(122, 50, 128, 50, 0);
		fill2(128, 49, 133, 49, 0);
		fill2(145, 71, 145, 73, 0);
		fill2(146, 73, 146, 77, 0);
		fill2(147, 77, 153, 77, 0);
		fill2(154, 74, 164, 79, 0);
		fill2(158, 71, 160, 73, 0);
		tiles2[158][71] = 19;
		tiles2[160][71] = 20;
		tiles2[159][71] = 18;
		fill2(53, 170, 57, 170, 0);
		fill2(57, 171, 61, 171, 0);
		fill2(61, 172, 65, 172, 0);
		fill2(65, 173, 65, 176, 0);
	}
	
	public void placeChests(){
		placeChest(new Chest(2, new Point(2850, 3120)).addItemStack(Items.wood, 5).addItem(Items.woodAxe).setMoney(15));
		placeChest(new Chest(3, new Point(2430, 4200)).addItemStack(Items.potion, 2).addItem(Items.foidskin).addItemStack(Items.wood, 3).setMoney(5));
		placeChest(new Chest(4, new Point(1800, 3360)).addItemStack(Items.foidskin, 4));
		placeChest(new Chest(5, new Point(900, 3300)).setMoney(25));
		placeChest(new Chest(6, new Point(1830, 3540)).addItem(Items.woodSword).setMoney(20));
		placeChest(new Chest(41, new Point(1350, 2160)).addItems(Items.foidHelmet, Items.potion));
		placeChest(new Chest(42, new Point(2550, 2760), SpecialItems.BLUE_PRINT_SWORD));
		placeChest(new Chest(43, new Point(3060, 1710)).addItems(Items.ether, Items.potion));
		placeChest(new Chest(44, new Point(2910, 1530)).addItemStack(Items.wing, 3).addItemStack(Items.potion, 2));
		placeChest(new Chest(45, new Point(4020, 1470)).addItemStack(Items.orangePotion, 2).setMoney(37));
	}
}
