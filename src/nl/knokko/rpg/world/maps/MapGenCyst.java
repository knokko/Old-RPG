package nl.knokko.rpg.world.maps;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.monsters.AstralWarrior;
import nl.knokko.rpg.entities.monsters.AstralWolf;
import nl.knokko.rpg.entities.monsters.DarkSoul;
import nl.knokko.rpg.entities.monsters.Spirit;
import nl.knokko.rpg.entities.monsters.boss.CursedEye;
import nl.knokko.rpg.gui.GuiBattle;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.tiles.Chest;
import nl.knokko.rpg.tiles.MonsterChest;
import nl.knokko.rpg.utils.BackGrounds;
import nl.knokko.rpg.utils.Resources;
import nl.knokko.rpg.world.Portal;
import nl.knokko.rpg.world.World;
import nl.knokko.rpg.world.maps.MapGenBase;

public class MapGenCyst extends MapGenBase {

	public MapGenCyst(){
		super();
		build();
		placeChests();
		portals.add(new Portal(new Point(2940, 5970), "Cyst", new Point(3000, 3000), "Dark Forest Mid", true));
		entities.add(new CursedEye(new Point(2940, 4050), 70));
	}
	
	protected boolean west;
	protected boolean east;

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
		return new Color(20, 0, 75);
	}

	@Override
	public String getName(){
		return "Cyst";
	}

	@Override
	public ArrayList<Entity> getEnemies(){
		ArrayList<Entity> enemies = new ArrayList<Entity>();
		Point p = new Point();
		enemies.add(new AstralWarrior(p, 28));
		enemies.add(new AstralWarrior(p, 29));
		enemies.add(new AstralWarrior(p, 30));
		enemies.add(new Spirit(p, 29));
		enemies.add(new Spirit(p, 30));
		enemies.add(new DarkSoul(p, 28));
		return enemies;
	}

	@Override
	public int getMaxEnemies(){
		return 4;
	}

	@Override
	public Point getStartPoint(MapGenBase old){
		return new Point(2940, 5940);
	}

	public void build(){
		tiles2[98][199] = 48;
		fill(98, 189, 98, 199, 46);
		fill(99, 189, 103, 189, 46);
		fill(93, 189, 97, 189, 46);
		fill(104, 189, 104, 192, 46);
		fill(92, 189, 92, 192, 46);
		fill(95, 180, 95, 188, 46);
		fill(101, 180, 101, 188, 46);
		fill(96, 180, 100, 180, 46);
		fill(86, 192, 91, 192, 46);
		fill(86, 185, 86, 191, 46);
		fill(77, 185, 85, 185, 46);
		fill(77, 181, 77, 184, 46);
		fill(70, 181, 76, 181, 46);
		fill(69, 181, 69, 188, 46);
		fill(60, 188, 68, 188, 46);
		fill(74, 172, 74, 180, 46);
		fill(75, 172, 83, 172, 46);
		fill(104, 185, 104, 188, 46);
		fill(104, 184, 115, 184, 46);
		fill(105, 192, 112, 192, 46);
		fill(116, 179, 116, 184, 46);
		fill(83, 173, 83, 178, 46);
		fill(84, 178, 90, 178, 46);
		fill(69, 178, 69, 180, 46);
		fill(53, 178, 68, 178, 46);
		fill(53, 174, 53, 177, 46);
		fill(53, 179, 53, 181, 46);
		fill(44, 181, 52, 181, 46);
		fill(44, 182, 44, 192, 46);
		fill(45, 192, 53, 192, 46);
		fill(38, 173, 53, 173, 46);
		fill(38, 160, 38, 172, 46);
		fill(98, 171, 98, 179, 46);
		fill(99, 171, 101, 171, 46);
		fill(95, 171, 97, 171, 46);
		fill(95, 165, 95, 170, 46);
		fill(96, 165, 101, 165, 46);
		fill(101, 166, 101, 170, 46);
		tiles2[95][168] = 37;
		tiles2[101][168] = 37;
		fill(98, 166, 98, 170, 46);
		fill(27, 159, 45, 159, 46);
		fill(45, 152, 45, 158, 46);
		fill(46, 152, 55, 152, 46);
		fill(27, 130, 27, 158, 46);
		fill(28, 145, 35, 145, 46);
		fill(19, 145, 26, 145, 46);
		fill(28, 138, 35, 138, 46);
		fill(19, 138, 26, 138, 46);
		fill(28, 151, 35, 151, 46);
		fill(19, 151, 26, 151, 46);
		tiles2[27][130] = 42;
		fill(117, 179, 128, 179, 46);
		fill(128, 169, 128, 178, 46);
		fill(112, 169, 127, 169, 46);
		fill(129, 179, 139, 179, 46);
		fill(139, 180, 139, 188, 46);
		fill(140, 188, 161, 188, 46);
		fill(154, 183, 154, 187, 46);
		fill(154, 189, 154, 193, 46);
		tiles2[161][188] = 45;
		fill(97, 138, 99, 154, 46);
		fill(96, 133, 96, 137, 46);
		fill(95, 134, 95, 136, 46);
		fill(97, 132, 99, 137, 46);
		fill(100, 133, 100, 137, 46);
		fill(101, 134, 101, 136, 46);
	}
	
	public void placeChests() {
		placeChest(47, new Chest(110, new Point(2700, 5340)).addItemStack(Items.darkEssence, 2));
		placeChest(47, new Chest(111, new Point(3360, 5760)).addItemStack(Items.cystEssence, 2));
		placeChest(47, new Chest(112, new Point(1800, 5640)).addItems(Items.betterEther, Items.betterPotion));
		placeChest(47, new Chest(113, new Point(1590, 5760)).setMoney(258));
		placeChest(47, new Chest(114, new Point(1650, 4560)).addItemStack(Items.lightEssence, 3));
		placeChest(47, new Chest(115, new Point(3360, 5070)).addItemStack(Items.orangePotion, 5));
		placeChest(47, new Chest(116, new Point(4620, 5490)).addItems(Items.cystEssence, Items.darkEssence));
		placeChest(47, new Chest(117, new Point(4620, 5790)).addItem(Items.orangeEther).setMoney(187));
		String b = getFightBackGround();
		Point p = new Point();
		placeChest(47, new MonsterChest(118, new Point(1050, 4140), new GuiBattle(b, MainClass.players(), new Entity[]{new AstralWolf(p, 35)}, false)));
		placeChest(47, new MonsterChest(119, new Point(570, 4140), new GuiBattle(b, MainClass.players(), new Entity[]{new AstralWolf(p, 35)}, false)));
		placeChest(47, new MonsterChest(120, new Point(570, 4350), new GuiBattle(b, MainClass.players(), new Entity[]{new AstralWolf(p, 35)}, false)));
		placeChest(47, new MonsterChest(121, new Point(1050, 4350), new GuiBattle(b, MainClass.players(), new Entity[]{new AstralWolf(p, 35)}, false)));
		placeChest(47, new MonsterChest(122, new Point(1050, 4530), new GuiBattle(b, MainClass.players(), new Entity[]{new AstralWolf(p, 35)}, false)));
		placeChest(47, new MonsterChest(123, new Point(570, 4530), new GuiBattle(b, MainClass.players(), new Entity[]{new AstralWolf(p, 35)}, false)).addItem(Items.cystBlade));
	}

	@Override
	public String getFightBackGround() {
		return BackGrounds.cyst;
	}
	
	@Override
	public void activatePanel(Point point){
		if(point.equals(new Point(810, 3900)))
			west = true;
		if(point.equals(new Point(4830, 5640)))
			east = true;
		activateLanterns();
	}
	
	protected void activateLanterns(){
		World world = MainClass.world;
		if(west)
			world.tiles2[95][168] = 38;
		if(east)
			world.tiles2[101][168] = 41;
		if(west && east){
			int y = 164;
			while(y >= 155){
				world.tiles[98][y] = 46;
				--y;
			}
		}
	}
	
	@Override
	public void loadData(){
		super.loadData();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Resources.getLoadFile() + "/mapdata/cyst.txt"));
			east = reader.readLine().substring(14).matches("on");
			west = reader.readLine().substring(14).matches("on");
			reader.close();
		} catch (Exception ex) {
			MainClass.console.println("Failed to load cyst data:");
			ex.printStackTrace(MainClass.console);
			MainClass.console.println();
		}
		activateLanterns();
	}
	
	@Override
	public void saveData(){
		super.saveData();
		try {
			PrintWriter writer = new PrintWriter(Resources.getSaveFile() + "/mapdata/cyst.txt");
			writer.println("east lantern: " + (east ? "on" : "off"));
			writer.println("west lantern: " + (west ? "on" : "off"));
			writer.close();
		}  catch(Exception ex){
			MainClass.console.println("Failed to save cyst data:");
			ex.printStackTrace(MainClass.console);
			MainClass.console.println();
		}
	}
	
	@Override
	public String getMusic(){
		return "cyst";
	}
}
