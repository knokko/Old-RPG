package nl.knokko.rpg.gui;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.utils.JukeBox;
import nl.knokko.rpg.utils.Resources;
import nl.knokko.rpg.world.maps.MapGenBase;

public class GuiWorldMap extends Gui {
	
	public static ArrayList<String> foundMaps = new ArrayList<String>();
	public static GuiWorldMap instance;
	
	public MapGenBase old;
	public Vector2f camera = new Vector2f(0.5f, 0.5f);
	
	public GuiWorldMap() {
		super("sprites/map.png");
		instance = this;
		JukeBox.stop();
		old = MainClass.world.map;
		addMap(old.getName());
		addMapButton(820, 390, 900, 440, "Runia");
		addMapButton(700, 430, 870, 480, "Foid Forest");
		if(foundMaps.contains("Lost Plains"))
			addMapButton(500, 390, 650, 440, "Lost Plains");
		if(foundMaps.contains("Lost Plains 2"))
			addMapButton(350, 360, 520, 410, "Lost Plains 2");
		if(foundMaps.contains("Miay Cave"))
			addMapButton(840, 290, 990, 340, "Miay Cave");
		if(foundMaps.contains("Ruff"))
			addMapButton(230, 310, 330, 360, "Ruff");
		if(foundMaps.contains("Dark Forest"))
			addMapButton(20, 250, 180, 300, "Dark Forest");
		if(foundMaps.contains("Goblin Cave"))
			addMapButton(50, 350, 200, 400 ,"Goblin Cave");
	}
	
	@Override
	public void buttonClicked(Button button){
		transferPlayer(MapGenBase.fromString(button.name));
	}
	
	public void transferPlayer(MapGenBase map){
		MainClass.currentGUI = null;
		MainClass.world.build(map);
		if(!map.getName().matches(old.getName()))
			MainClass.player.position = map.getStartPoint(old);
		Point camera = MainClass.camera;
		camera.x = MainClass.player.position.x;
		camera.y = MainClass.player.position.y;
		if(camera.x > map.maxPoint().x - 810)
			camera.x = map.maxPoint().x - 810;
		if(camera.x < map.minPoint().x + 810)
			camera.x = map.minPoint().x + 810;
		if(camera.y > map.maxPoint().y - 450)
			camera.y = map.maxPoint().y - 450;
		if(camera.y < map.minPoint().y + 450)
			camera.y = map.minPoint().y + 450;
	}
	
	public void addMapButton(int minX, int minY, int maxX, int maxY, String map){
		buttons.add(new MapButton(minX, minY, maxX, maxY, map));
	}
	
	public static void addMap(String map){
		if(!foundMaps.contains(map))
			foundMaps.add(map);
	}
	
	public static void saveStaticData(){
		if(!foundMaps.isEmpty()){
			try {
				PrintWriter writer = new PrintWriter(Resources.getSaveFile() + "/discovered maps.txt");
				int t = 0;
				while(t < foundMaps.size()){
					writer.println(foundMaps.get(t));
					++t;
				}
				writer.close();
			} catch(Exception ex){
				MainClass.console.println("GuiWorldMap.saveStaticData(): Failed to save the discovered maps:");
				MainClass.console.println();
				ex.printStackTrace(MainClass.console);
				MainClass.console.println();
			}
		}
	}
	
	public static void loadData(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Resources.getLoadFile() + "/discovered maps.txt"));
			String line = reader.readLine();
			while(line != null && !line.isEmpty()){
				addMap(line);
				line = reader.readLine();
			}
			reader.close();
		} catch(Exception ex){
			MainClass.console.println("Failed to load the discovered areas:");
			ex.printStackTrace(MainClass.console);
			MainClass.console.println();
		}
	}
	
	public static Vector2f positionForMap(MapGenBase map){
		return positionForMap(map.getName());
	}
	
	public static Vector2f positionForMap(String map){
		int t = 0;
		while(t < instance.buttons.size()){
			Button but = instance.buttons.get(t);
			if(but.name.matches(map))
				return new Vector2f(but.minX(), but.midY());
			++t;
		}
		return new Vector2f();
	}
	
	public class MapButton extends Button {

		public MapButton(int minx, int miny, int maxx, int maxy, String mapName) {
			super(minx, miny, maxx, maxy, "", mapName, new Font("TimesRoman", Font.BOLD, 30), Color.BLACK);
		}
		
		/*
		@Override
		public void paint(Graphics gr){
			if(font.getSize() == -1)
				initSize(gr);
			int x = minX - camera.x + MainClass.getWidth() / 2;
			int y = minY - camera.y + MainClass.getHeight() / 2;
			int width = maxX - minX;
			int height = maxY - minY;
			gr.setColor(color);
			if(MainClass.getMousePosition() != null && isHit(MainClass.getMousePosition()))
				gr.setColor(Color.YELLOW);
			gr.setFont(font);
			gr.drawString(name, x + width / 20, y + height - height / 3);
		}
		*/
	}
}
