package nl.knokko.rpg.world.maps;

import java.awt.Color;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.gui.GuiWorldMap;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.tiles.Chest;
import nl.knokko.rpg.utils.JukeBox;
import nl.knokko.rpg.utils.Resources;
import nl.knokko.rpg.utils.Utils;
import nl.knokko.rpg.world.Conversation;
import nl.knokko.rpg.world.Portal;
import nl.knokko.rpg.world.Shop;
import nl.knokko.rpg.world.World;

public abstract class MapGenBase {
	
	public byte[][] tiles;
	public byte[][] tiles2;
	public byte[][] tiles3;
	public boolean[][] exploredFields;
	
	public ArrayList<Portal> portals;
	public ArrayList<Shop> shops;
	public ArrayList<Entity> entities;
	public ArrayList<Chest> chests;
	public ArrayList<Conversation> conversations;
	
	public MapGenBase() {
		tiles = new byte[getXSize()][getYSize()];
		tiles2 = new byte[getXSize()][getYSize()];
		tiles3 = new byte[getXSize()][getYSize()];
		exploredFields = new boolean[getYSize()][getXSize()];
		portals = new ArrayList<Portal>();
		shops = new ArrayList<Shop>();
		entities = new ArrayList<Entity>();
		chests = new ArrayList<Chest>();
		conversations = new ArrayList<Conversation>();
		if(getMusic() != null)
			JukeBox.playMusic(getMusic(), true);
	}
	
	public Point minPoint(){
		return new Point();
	}
	
	public Point maxPoint(){
		return new Point(30 * getXSize(), 30 * getYSize());
	}
	
	/**
	 * The number of blocks on the x size of the world.
	 * @return The number of blocks on the x size.
	 */
	public abstract int getXSize();
	/**
	 * The number of blocks on the y size of the world.
	 * @return The number of blocks on the y size.
	 */
	public abstract int getYSize();
	/**
	 * This method specifies the background of the world.
	 * @return The background color of the world.
	 */
	public Color getBackGroundColor(){
		return null;
	}
	
	/**
	 * The name of the map.
	 * @return the mapname;
	 */
	public abstract String getName();
	
	/**
	 * This method specifies a list of enemies that can join random battles in the map.
	 * @return The list of enemies.
	 */
	public abstract ArrayList<Entity> getEnemies();
	
	/**
	 * The max amount of enemies that can join a battle;
	 * @return The max amount of enemies.
	 */
	public abstract int getMaxEnemies();
	
	/**
	 * The spawn point of the map in entity coordinates.
	 * @return the spawn point.
	 */
	public Point getStartPoint(MapGenBase previousMap){
		Point point = GuiWorldMap.positionForMap(previousMap);
		Point own = GuiWorldMap.positionForMap(this);
		int distanceX = point.x - own.x;
		int distanceY = -(point.y - own.y);
		if(distanceX > distanceY && distanceX > -distanceY && distanceX > -distanceX)
			return entranceEast();
		if(distanceX < distanceY && distanceX < -distanceY && distanceX < -distanceX)
			return entranceWest();
		if(distanceY > distanceX && distanceY > -distanceX && distanceY > -distanceY)
			return entranceNorth();
		return entranceSouth();
	}
	
	/**
	 * Enables/disables random fights in this map.
	 * @return random fights are allowed or not.
	 */
	public boolean allowRandomFights(){
		return true;
	}
	
	public void fill(int minX, int minY, int maxX, int maxY, int tile){
		int x = minX;
		while(x <= maxX){
			int y = minY;
			while(y <= maxY){
				tiles[x][y] = (byte)tile;
				++y;
			}
			++x;
		}
	}
	
	public void fill2(int minX, int minY, int maxX, int maxY, int tile){
		int x = minX;
		while(x <= maxX){
			int y = minY;
			while(y <= maxY){
				tiles2[x][y] = (byte)tile;
				++y;
			}
			++x;
		}
	}
	
	public void makeRect2(int minX, int minY, int maxX, int maxY, int tile){
		int x = minX;
		while(x <= maxX){
			tiles2[x][minY] = (byte)tile;
			tiles2[x][maxY] = (byte)tile;
			++x;
		}
		int y = minY;
		while(y <= maxY){
			tiles2[minX][y] = (byte)tile;
			tiles2[maxX][y] = (byte)tile;
			++y;
		}
	}
	
	public static final MapGenBase fromString(String map){
		if(map.matches("Foid Forest"))
			return new MapGenFoidForest();
		if(map.matches("Runia inside"))
			return new MapGenRuniaInside();
		if(map.matches("Lost Plains"))
			return new MapGenLostPlains();
		if(map.matches("Lost Plains 2"))
			return new MapGenLostPlains2();
		if(map.matches("Miay Cave"))
			return new MapGenMiayCave();
		if(map.matches("Miay Cave Layer 2"))
			return new MapGenMiayCave2();
		if(map.matches("Miay Cave Layer 3"))
			return new MapGenMiayCave3();
		if(map.matches("Ruff"))
			return new MapGenRuff();
		if(map.startsWith("Ruff smallHouse"))
			return MapGenRuffInside.smallHouse(Integer.decode(map.substring(16)));
		if(map.startsWith("Ruff shop"))
			return new MapGenRuffInside.Shop(Integer.decode(map.substring(10)));
		if(map.startsWith("Ruff largeHouse"))
			return MapGenRuffInside.largeHouse(Integer.decode(map.substring(16)));
		if(map.startsWith("Ruff normalHouse"))
			return MapGenRuffInside.normalHouse(Integer.decode(map.substring(17)));
		if(map.startsWith("Ruff shamanHouse"))
			return MapGenRuffInside.shamanHouse();
		if(map.matches("Dark Forest"))
			return new MapGenDarkForestEast();
		if(map.matches("Dark Forest South"))
			return new MapGenDarkForestSouth();
		if(map.matches("Dark Forest Mid"))
			return new MapGenDarkForestMid();
		if(map.matches("Cyst"))
			return new MapGenCyst();
		if(map.matches("Dark Forest North"))
			return new MapGenDarkForestNorth();
		if(map.matches("Dark Forest West"))
			return new MapGenDarkForestWest();
		if(map.matches("Dead Dungeon 1"))
			return new MapGenDeadDungeon1();
		if(map.matches("Goblin Cave"))
			return new MapGenGoblinCave();
		if(map.matches("Goblin Cave W1"))
			return new MapGenGoblinCaveWest1();
		if(map.matches("Goblin Tomb"))
			return new MapGenGoblinTomb();
		return new MapGenRunia();
	}
	
	/**
	 * Places a chest in the map.
	 * @param chest The chest to place.
	 */
	public void placeChest(Chest chest){
		placeChest(13, chest);
	}
	
	public void placeChest(int tile, Chest chest){
		chests.add(chest);
		tiles2[chest.position.x / 30][chest.position.y / 30] = (byte) tile;
	}
	
	public abstract String getMusic();
	
	public abstract String getFightBackGround();

	public void saveData(){
		try {
			FileOutputStream writer = new FileOutputStream(Resources.getSaveFile() + "/maps/" + getName().toLowerCase() + ".txt");
			int y = 0;
			while(y < exploredFields.length){
				writer.write(fromBinair(y));
				++y;
			}
			writer.close();
		} catch(Exception ex){
			Game.console.println("Failed to save map data:");
			ex.printStackTrace(Game.console);
			Game.console.println();
		}
	}
	
	public void loadData(){
		try {
			FileInputStream stream = new FileInputStream(Resources.getLoadFile() + "/maps/" + getName().toLowerCase() + ".txt");
			int y = 0;
			while(y < exploredFields.length){
				int dim = getXSize() / 8;
				if(dim * 8 != getXSize())
					++dim;
				byte[] bytes = new byte[dim];
				stream.read(bytes);
				toBinair(bytes, y);
				++y;
			}
			stream.close();
		} catch(Exception ex){
			Game.console.println("Failed to load map data:");
			ex.printStackTrace(Game.console);
			Game.console.println();
		}
		int t = 0;
		while(t < chests.size()){
			chests.get(t).loadData();
			++t;
		}
	}
	
	protected void toBinair(byte[] bytes, int y){
		int t = 0;
		while(t < bytes.length){
			boolean[] bools = Utils.toBinair(bytes[t]);
			int x = 0;
			while(x < 8){
				if(t * 8 + x >= exploredFields[y].length)
					return;
				exploredFields[y][t * 8 + x] = bools[x];
				++x;
			}
			++t;
		}
	}
	
	protected byte[] fromBinair(int y){
		int dim = getXSize() / 8;
		if(dim * 8 != getXSize())
			++dim;
		byte[] bytes = new byte[dim];
		int t = 0;
		while(t < bytes.length){
			boolean[] bools = Arrays.copyOfRange(exploredFields[y], t * 8, (t + 1) * 8);
			bytes[t] = Utils.fromBinair(bools);
			++t;
		}
		return bytes;
	}
	
	public Point entranceSouth(){
		return new Point();
	}
	
	public Point entranceNorth(){
		return new Point();
	}
	
	public Point entranceEast(){
		return new Point();
	}
	
	public Point entranceWest(){
		return new Point();
	}
	
	public void activatePanel(Point location){}
	
	public void update(World world){}
	
	public void buildAltar(int minX, int minY){
		fill2(minX, minY + 2, minX, minY + 4, 34);
		tiles2[minX][minY + 3] = 34;
		tiles2[minX][minY + 4] = 34;
		tiles2[minX + 1][minY + 1] = 34;
		tiles2[minX + 1][minY + 5] = 34;
		tiles2[minX + 2][minY] = 34;
		tiles2[minX + 2][minY + 6] = 34;
		tiles2[minX + 3][minY] = 34;
		tiles2[minX + 4][minY] = 34;
		tiles2[minX + 4][minY + 6] = 34;
		tiles2[minX + 5][minY + 1] = 34;
		tiles2[minX + 5][minY + 5] = 34;
		tiles[minX + 3][minY + 6] = 23;
		tiles2[minX + 3][minY + 3] = 35;
		fill2(minX + 6, minY + 2, minX + 6, minY + 4, 34);
		fill(minX + 1, minY + 1, minX + 5, minY + 5, 23);
	}
}
