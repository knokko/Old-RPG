package nl.knokko.rpg.world;

import java.awt.*;

import nl.knokko.rpg.gui.GuiWorldMap;
import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.world.maps.MapGenBase;

public class Portal {
	
	public Point position;
	public Point destination;
	public String map1;
	public String map2;
	
	public String[] maps;
	
	public boolean heal;
	
	public Portal(Point location, String map, Point location2, String destinationMap, String... newMaps) {
		this(location, map, location2, destinationMap, false, newMaps);
	}
	
	public Portal(Point location, String map, Point location2, String destinationMap, boolean healPlayers, String...newMaps){
		position = location;
		destination = location2;
		map1 = map;
		map2 = destinationMap;
		heal = healPlayers;
		maps = newMaps;
	}
	
	public boolean collides(Point point){
		if(point.equals(position)){
			if(heal)
				MainClass.healPlayers();
			if(destination != null && map2 != null){
				if(MainClass.player.portalCooldown <= 0){
					MapGenBase map = MapGenBase.fromString(map2);
					MainClass.world.build(map);
					MainClass.player.position = destination;
					MainClass.player.portalCooldown = 10;
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
				else
					return false;
			}
			else {
				int t = 0;
				while(t < maps.length){
					GuiWorldMap.addMap(maps[t]);
					++t;
				}
				MainClass.currentGUI = new GuiWorldMap();
			}
			return true;
		}
		return false;
	}
}
