package nl.knokko.rpg.world.maps;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.monsters.DarkSoul;
import nl.knokko.rpg.entities.monsters.DreamEater;
import nl.knokko.rpg.entities.monsters.Drock;
import nl.knokko.rpg.entities.monsters.Vampire;
import nl.knokko.rpg.entities.monsters.VoidLing;
import nl.knokko.rpg.entities.monsters.boss.Bosses;
import nl.knokko.rpg.gui.GuiBattle;
import nl.knokko.rpg.gui.GuiChat;
import nl.knokko.rpg.items.SpecialItems;
import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.tiles.Tiles;
import nl.knokko.rpg.utils.BackGrounds;
import nl.knokko.rpg.utils.Resources;
import nl.knokko.rpg.world.Portal;
import nl.knokko.rpg.world.World;
import nl.knokko.rpg.world.maps.MapGenBase;

public class MapGenDarkForestMid extends MapGenBase {
	
	protected boolean north;
	protected boolean east;
	protected boolean south;
	protected boolean west;

	public MapGenDarkForestMid(){
		super();
		build();
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
		return "Dark Forest Mid";
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
		if(old.getName().matches("Dark Forest North"))
			return entranceNorth();
		if(old.getName().matches("Dark Forest West"))
			return entranceWest();
		if(old.getName().matches("Dark Forest South"))
			return entranceSouth();
		return entranceEast();
	}

	public void build(){
		fill(0, 0, 199, 199, 33);
		tiles2[0][0] = 32;
		fill2(0, 0, 199, 199, 32);
		fill2(173, 100, 178, 100, 0);
		fill2(161, 99, 172, 101, 0);
		fill2(161, 94, 161, 98, 0);
		fill2(161, 102, 161, 106, 0);
		fill2(147, 94, 160, 94, 0);
		fill2(147, 106, 160, 106, 0);
		fill2(140, 95, 147, 95, 0);
		fill2(140, 105, 147, 105, 0);
		fill2(140, 102, 140, 104, 0);
		fill2(140, 96, 140, 98, 0);
		fill2(128, 98, 139, 98, 0);
		fill2(128, 102, 139, 102, 0);
		fill2(127, 96, 127, 98, 0);
		fill2(127, 102, 127, 104, 0);
		fill2(126, 94, 126, 96, 0);
		fill2(126, 104, 126, 106, 0);
		fill2(125, 93, 125, 94, 0);
		fill2(124, 92, 124, 93, 0);
		fill2(122, 92, 123, 92, 0);
		fill2(120, 91, 122, 91, 0);
		fill2(125, 106, 125, 107, 0);
		fill2(124, 107, 124, 108, 0);
		fill2(122, 108, 123, 108, 0);
		fill2(120, 109, 122, 109, 0);
		fill2(117, 90, 120, 90, 0);
		fill2(113, 89, 117, 89, 0);
		fill2(108, 88, 113, 88, 0);
		fill2(103, 87, 108, 87, 0);
		fill2(97, 86, 103, 86, 0);
		fill2(92, 87, 97, 87, 0);
		fill2(87, 88, 92, 88, 0);
		fill2(83, 89, 87, 89, 0);
		fill2(117, 110, 120, 110, 0);
		fill2(113, 111, 117, 111, 0);
		fill2(108, 112, 113, 112, 0);
		fill2(103, 113, 108, 113, 0);
		fill2(97, 114, 103, 114, 0);
		fill2(92, 113, 97, 113, 0);
		fill2(87, 112, 92, 112, 0);
		fill2(83, 111, 87, 111, 0);
		fill2(80, 110, 83, 110, 0);
		fill2(78, 109, 80, 109, 0);
		fill2(76, 108, 78, 108, 0);
		fill2(75, 107, 76, 107, 0);
		fill2(74, 106, 75, 106, 0);
		tiles2[74][105] = 0;
		fill2(73, 104, 74, 104, 0);
		fill2(73, 102, 73, 103, 0);
		fill2(80, 90, 83, 90, 0);
		fill2(78, 91, 80, 91, 0);
		fill2(76, 92, 78, 92, 0);
		fill2(75, 93, 76, 93, 0);
		fill2(74, 94, 75, 94, 0);
		tiles2[74][95] = 0;
		fill2(73, 96, 74, 96, 0);
		fill2(73, 97, 73, 98, 0);
		fill2(60, 98, 72, 98, 0);
		fill2(60, 102, 72, 102, 0);
		fill2(60, 95, 60, 97, 0);
		fill2(53, 95, 59, 95, 0);
		fill2(60, 103, 60, 105, 0);
		fill2(53, 105, 59, 105, 0);
		fill2(39, 94, 53, 94, 0);
		fill2(39, 106, 53, 106, 0);
		fill2(39, 95, 39, 105, 0);
		fill2(28, 99, 38, 101, 0);
		fill2(22, 100, 27, 100, 0);
		fill2(98, 99, 102, 101, 0);
		fill2(99, 98, 101, 98, 0);
		fill2(99, 102, 101, 102, 0);
		fill2(96, 94, 104, 94, 0);
		fill2(104, 95, 108, 95, 0);
		fill2(92, 95, 96, 95, 0);
		fill2(108, 96, 111, 96, 0);
		fill2(89, 96, 92, 96, 0);
		fill2(111, 97, 113, 97, 0);
		fill2(87, 97, 89, 97, 0);
		fill2(113, 98, 114, 98, 0);
		fill2(86, 98, 87, 98, 0);
		fill2(114, 99, 115, 99, 0);
		fill2(115, 100, 115, 101, 0);
		tiles2[114][101] = 0;
		fill2(113, 102, 114, 102, 0);
		fill2(111, 103, 113, 103, 0);
		fill2(108, 104, 111, 104, 0);
		fill2(104, 105, 108, 105, 0);
		fill2(96, 106, 104, 106, 0);
		fill2(92, 105, 96, 105, 0);
		fill2(89, 104, 92, 104, 0);
		fill2(87, 103, 89, 103, 0);
		fill2(86, 102, 87, 102, 0);
		fill2(85, 101, 86, 101, 0);
		fill2(85, 99, 86, 99, 0);
		tiles2[85][100] = 0;
		fill2(100, 87, 100, 93, 0);
		fill2(100, 107, 100, 113, 0);
		fill2(103, 100, 121, 100, 0);
		fill2(79, 100, 97, 100, 0);
		tiles2[78][100] = 42;
		tiles2[122][100] = 43;
		tiles2[99][100] = 37;
		tiles2[101][100] = 37;
		tiles2[100][98] = 37;
		tiles2[100][102] = 37;
		fill2(100, 162, 100, 170, 0);
		fill2(101, 158, 101, 162, 0);
		fill2(99, 158, 99, 162, 0);
		fill2(98, 152, 98, 158, 0);
		fill2(102, 152, 102, 158, 0);
		fill2(95, 151, 98, 151, 0);
		fill2(102, 151, 105, 151, 0);
		fill2(95, 145, 95, 150, 0);
		fill2(105, 145, 105, 150, 0);
		fill2(96, 137, 96, 145, 0);
		fill2(104, 137, 104, 145, 0);
		fill2(97, 130, 97, 137, 0);
		fill2(103, 130, 103, 137, 0);
		fill2(101, 129, 103, 129, 0);
		fill2(97, 129, 99, 129, 0);
		fill2(99, 121, 99, 128, 0);
		fill2(101, 121, 101, 128, 0);
		tiles2[100][121] = 44;
		tiles2[100][79] = 45;
		fill2(101, 71, 101, 79, 0);
		fill2(99, 71, 99, 79, 0);
		fill2(102, 71, 103, 71, 0);
		fill2(97, 71, 98, 71, 0);
		fill2(103, 63, 103, 70, 0);
		fill2(104, 55, 104, 63, 0);
		fill2(105, 49, 105, 55, 0);
		fill2(97, 63, 97, 70, 0);
		fill2(96, 55, 96, 63, 0);
		fill2(95, 49, 95, 55, 0);
		fill2(102, 49, 104, 49, 0);
		fill2(96, 49, 98, 49, 0);
		fill2(98, 42, 98, 48, 0);
		fill2(102, 42, 102, 48, 0);
		fill2(101, 38, 101, 42, 0);
		fill2(99, 38, 99, 42, 0);
		fill2(100, 30, 100, 38, 0);
	}
	
	public void placePortals(){
		portals.add(new Portal(entranceEast(), "Dark Forest Mid", new Point(2490, 3420), "Dark Forest"));
		portals.add(new Portal(entranceSouth(), "Dark Forest Mid", new Point(2910, 510), "Dark Forest South"));
		portals.add(new Portal(entranceNorth(), "Dark Forest Mid", new Point(3000, 5280), "Dark Forest North"));
		portals.add(new Portal(entranceWest(), "Dark Forest Mid", new Point(5310, 3000), "Dark Forest West"));
	}

	@Override
	public String getFightBackGround() {
		return BackGrounds.dark_forest;
	}
	
	@Override
	public Point entranceNorth(){
		return new Point(3000, 930);
	}
	
	@Override
	public Point entranceEast(){
		return new Point(5340, 3000);
	}
	
	@Override
	public Point entranceSouth(){
		return new Point(3000, 5100);
	}
	
	@Override
	public Point entranceWest(){
		return new Point(690, 3000);
	}
	
	@Override
	public void activatePanel(Point point){
		if(point.equals(new Point(3000, 3630)))
			south = true;
		if(point.equals(new Point(3000, 2370)))
			north = true;
		if(point.equals(new Point(2340, 3000)))
			west = true;
		if(point.equals(new Point(3660, 3000)))
			east = true;
		activateLanterns();
	}
	
	@Override
	public void loadData(){
		super.loadData();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Resources.getLoadFile() + "/mapdata/dark forest.txt"));
			north = reader.readLine().substring(15).matches("on");
			east = reader.readLine().substring(14).matches("on");
			south = reader.readLine().substring(15).matches("on");
			west = reader.readLine().substring(14).matches("on");
			reader.close();
		} catch (Exception ex) {
			MainClass.console.println("Failed to load dark forest data:");
			ex.printStackTrace(MainClass.console);
			MainClass.console.println();
		}
		activateLanterns();
	}
	
	@Override
	public void saveData(){
		super.saveData();
		try {
			PrintWriter writer = new PrintWriter(Resources.getSaveFile() + "/mapdata/dark forest.txt");
			writer.println("north lantern: " + (north ? "on" : "off"));
			writer.println("east lantern: " + (east ? "on" : "off"));
			writer.println("south lantern: " + (south ? "on" : "off"));
			writer.println("west lantern: " + (west ? "on" : "off"));
			writer.close();
		}  catch(Exception ex){
			MainClass.console.println("Failed to save dark forest data:");
			ex.printStackTrace(MainClass.console);
			MainClass.console.println();
		}
	}

	@Override
	public String getMusic(){
		return "dark forest";
	}
	
	@Override
	public void update(World world){
		if(Bosses.bosses.contains("voidlings") && MainClass.specialItems.hasItem(SpecialItems.CURSED_EYE)){
			if(new Point(3000, 3000).distance(MainClass.player.position) < 90 && !new Point(3000, 300).equals(MainClass.player.position)){
				Point p = new Point(-10000, -10000);
				MainClass.currentGUI = new GuiChat(false).setFinishGui(new GuiBattle(BackGrounds.dark_forest, MainClass.players(), new Entity[]{new VoidLing(p, 30), new VoidLing(p, 30), new VoidLing(p, 30), new VoidLing(p, 30)}));
			}
		}
	}
	
	protected void activateLanterns(){
		World world = MainClass.world;
		if(world.map == this){
			if(north)
				world.tiles2[100][98] = Tiles.green_lantern.id;
			if(east)
				world.tiles2[101][100] = Tiles.red_lantern.id;
			if(south)
				world.tiles2[100][102] = Tiles.yellow_lantern.id;
			if(west)
				world.tiles2[99][100] = Tiles.blue_lantern.id;
			if(north && east && south && west){
				world.tiles2[100][100] = 48;
				portals.add(new Portal(new Point(3000, 3000), "Dark Forest Mid", new Point(2940, 5940), "Cyst", true));
			}
		}
	}
}
