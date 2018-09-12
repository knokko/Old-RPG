package nl.knokko.rpg.world;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.gui.GuiBattle;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.tiles.Chest;
import nl.knokko.rpg.tiles.Tile;
import nl.knokko.rpg.tiles.Tiles;
import nl.knokko.rpg.utils.Resources;
import nl.knokko.rpg.world.maps.MapGenBase;
import nl.knokko.rpg.world.maps.MapGenRunia;

public class World {
	
	public final Game game;
	
	public String mapName = "default";
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public ArrayList<Portal> portals = new ArrayList<Portal>();
	public ArrayList<Shop> shops = new ArrayList<Shop>();
	public ArrayList<Chest> chests = new ArrayList<Chest>();
	public ArrayList<Conversation> conversations = new ArrayList<Conversation>();
	public byte[][] tiles;
	public byte[][] tiles2;
	public byte[][] tiles3;
	public Color backGround = Color.blue;
	public int worldSizeX;
	public int worldSizeY;
	public MapGenBase map;
	
	public World(Game theGame) {
		game = theGame;
	}
	
	public void paint(Graphics gr){
		int y = 0;
		while(y < worldSizeY){
			int x = 0;
			while(x < worldSizeX){
				Tiles.fromId(tiles[x][y]).paint(gr, new Point(x * 30, y * 30));
				++x;
			}
			++y;
		}
		game.player.paint(gr);
		int t = 0;
		while(t < entities.size()){
			entities.get(t).paint(gr);
			++t;
		}
		y = 0;
		while(y < worldSizeY){
			int x = 0;
			while(x < worldSizeX){
				Tiles.fromId(tiles2[x][y]).paint(gr, new Point(x * 30, y * 30));
				++x;
			}
			++y;
		}
		y = 0;
		while(y < worldSizeY){
			int x = 0;
			while(x < worldSizeX){
				Tiles.fromId(tiles3[x][y]).paint(gr, new Point(x * 30, y * 30));
				++x;
			}
			++y;
		}
	}
	
	public Tile getTile(int x, int y, boolean getGround){
		int tx = x / 30;
		int ty = y / 30;
		if(tx < 0 || ty < 0 || tx >= worldSizeX || ty >= worldSizeY){
			return Tiles.air;
		}
		if(getGround){
			return Tiles.fromId(tiles[tx][ty]);
		}
		return Tiles.fromId(tiles2[tx][ty]);
	}
	
	public void build(MapGenBase map, boolean needLoad){
		game.saveData();
		chests = map.chests;
		portals = map.portals;
		shops = map.shops;
		entities = map.entities;
		conversations = map.conversations;
		mapName = map.getName();
		backGround = map.getBackGroundColor();
		tiles = map.tiles;
		tiles2 = map.tiles2;
		tiles3 = map.tiles3;
		this.map = map;
		worldSizeX = map.getXSize();
		worldSizeY = map.getYSize();
		if(needLoad)
			map.loadData();
	}
	
	public void build(MapGenBase map){
		build(map, true);
	}
	
	public void setTile(Tile tile, Point point){
		tiles[point.x / 30][point.y / 30] = tile.id;
	}
	
	public void update(){
		if(map != null)
			map.update(this);
		int t = 0;
		while(t < entities.size()){
			entities.get(t).update();
			++t;
		}
	}
	
	public void startRandomFight(){
		if(map.allowRandomFights()){
			Random random = new Random();
			ArrayList<Entity> entities = map.getEnemies();
			Entity[] enemies = new Entity[2 + random.nextInt(map.getMaxEnemies() - 1)];
			int t = 0;
			while(t < enemies.length){
				enemies[t] = entities.get(random.nextInt(entities.size()));
				entities = map.getEnemies();
				++t;
			}
			game.currentGUI = new GuiBattle(game, map.getFightBackGround(), new Entity[]{game.player, game.player2, game.player3}, enemies);
		}
	}
	
	public void loadData(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Resources.getLoadFile() + "/world.txt"));
			String map = reader.readLine().substring(4);
			mapName = map;
			MapGenBase map2 = MapGenBase.fromString(map);
			build(map2);
			Point camera = Game.game.camera;
			camera.x = Game.game.player.position.x;
			camera.y = Game.game.player.position.y;
			if(camera.x > map2.maxPoint().x - 750)
				camera.x = map2.maxPoint().x - 750;
			if(camera.x < map2.minPoint().x + 810)
				camera.x = map2.minPoint().x + 810;
			if(camera.y > map2.maxPoint().y - 450)
				camera.y = map2.maxPoint().y - 450;
			if(camera.y < map2.minPoint().y + 450)
				camera.y = map2.minPoint().y + 450;
			int t = 0;
			while(t < chests.size()){
				chests.get(t).loadData();
				++t;
			}
			reader.close();
		} catch(Exception ex){
			Game.console.println("Failed to load the current map; Runia will be build:");
			ex.printStackTrace(Game.console);
			Game.console.println();
			build(new MapGenRunia());
		}
	}
	
	public void saveData(){
		try {
			PrintWriter writer = new PrintWriter(Resources.getSaveFile() + "/world.txt");
			writer.println("map:" + mapName);
			writer.close();
			int t = 0;
			while(t < chests.size()){
				chests.get(t).saveData();
				++t;
			}
		} catch (Exception e) {
			Game.console.println("World.saveData(): Failed to save the map:");
			Game.console.println();
			e.printStackTrace(Game.console);
			Game.console.println();
		}
		if(map != null)
			map.saveData();
	}
}
