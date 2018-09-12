package nl.knokko.rpg.world.maps;

import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.Villager;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.tiles.Chest;
import nl.knokko.rpg.utils.SpecialText;
import nl.knokko.rpg.world.Portal;

public class MapGenRunia extends MapGenBase {

	public MapGenRunia() {
		super();
		placePortals();
		placeChests();
		spawnVillagers();
		build();
	}
	
	@Override
	public Point maxPoint(){
		return new Point(1140, 1260);
	}
	@Override
	public int getXSize() {
		return 100;
	}

	@Override
	public int getYSize() {
		return 50;
	}

	@Override
	public String getName() {
		return "Runia";
	}

	@Override
	public ArrayList<Entity> getEnemies() {
		return null;
	}

	@Override
	public int getMaxEnemies() {
		return 0;
	}
	
	@Override
	public boolean allowRandomFights(){
		return false;
	}

	@Override
	public Point getStartPoint(MapGenBase previous) {
		return new Point(660, 0);
	}
	
	@Override
	public String getMusic(){
		return "runia";
	}
	
	protected void placeChests(){
		placeChest(new Chest(1, new Point(150, 210)).setMoney(10));
	}
	
	protected void spawnVillagers(){
		Game game = Game.game;
		boolean b = game.quests.hasQuest("miners");
		entities.add(new Villager(new Point(150, 150), game, b ? new SpecialText("Hello Bart,", "I have heard you are going to save the miners.", "Good luck with your mission!") : new SpecialText("You saved the miners, well done guys!")));
		entities.add(new Villager(new Point(930, 600), game, b ? new SpecialText("We can't do anything without the miners.", "They have all our important things.", "Save them please!") : new SpecialText("Our miay gems are back and the miners are safe.", "Thank you!")));
		entities.add(new Villager(new Point(300, 660), game, new SpecialText("Noooo, the aliens have come.", "They will kill everyone!", "We are all going to die!", "Nooo!", "It won't take long until they find our village.")));
		entities.add(new Villager(new Point(600, 210), game, new SpecialText("Hey Bart,", "You will need good weapons and armor to enter the forest.", "Have you visited the shops yet?")));
		entities.add(Villager.guard(game, new Point(630, 0), false, Items.miayArmor, Items.miaySword, new SpecialText("I will defend Runia until the end.")));
		entities.add(Villager.guard(game, new Point(690, 0), false, Items.miayArmor, Items.miaySword, new SpecialText("I will fight until I am tired.")));
	}
	
	protected void placePortals(){
		portals.add(new Portal(new Point(660, 0), "Runia", null, ""));
		portals.add(new Portal(new Point(390, 330), "Runia", new Point(300, 360), "Runia inside"));
		portals.add(new Portal(new Point(900, 330), "Runia", new Point(1410, 360), "Runia inside"));
		portals.add(new Portal(new Point(450, 570), "Runia", new Point(270, 1380), "Runia inside"));
		portals.add(new Portal(new Point(900, 570), "Runia", new Point(1440, 1380), "Runia inside"));
		portals.add(new Portal(new Point(420, 990), "Runia", new Point(330, 2400), "Runia inside"));
	}
	
	public void build(){
		fill(0, 0, 99, 49, 1);
		fill(22, 0, 22, 34, 2);
		fill(7, 12, 30, 12, 2);
		fill(7, 20, 30, 20, 2);
		fill(7, 34, 30, 34, 2);
		fill2(0, 0, 20, 4, 3);
		fill2(0, 5, 2, 49, 3);
		fill2(24, 0, 99, 4, 3);
		fill2(41, 5, 99, 49, 3);
		fill2(3, 37, 41, 49, 3);
		makeRect2(10, 5, 17, 11, 4);
		makeRect2(24, 5, 35, 11, 4);
		makeRect2(10, 13, 21, 19, 4);
		makeRect2(24, 13, 35, 19, 4);
		fill2(3, 25, 20, 25, 25);
		fill2(25, 25, 40, 25, 25);
		makeRect2(10, 27, 17, 33, 4);
		tiles3[10][27] = 5;
		tiles3[12][27] = 5;
		
		tiles3[10][5] = 5;
		tiles3[12][5] = 5;
		
		tiles3[24][5] = 5;
		tiles3[30][5] = 5;
		
		tiles3[10][13] = 5;
		tiles3[16][13] = 5;
		
		tiles3[30][13] = 5;
		tiles3[24][13] = 5;
		
		tiles3[30][10] = 8;
		tiles3[15][18] = 10;
		tiles3[30][18] = 11;
		tiles2[13][11] = 7;
		tiles2[30][11] = 7;
		tiles2[15][19] = 7;
		tiles2[30][19] = 7;
		tiles2[14][33] = 7;
		
		buildAltar(25, 27);
	}

	@Override
	public String getFightBackGround() {
		return null;
	}
}
