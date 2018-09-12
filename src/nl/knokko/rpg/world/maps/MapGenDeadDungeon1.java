package nl.knokko.rpg.world.maps;

import java.awt.*;
import java.util.ArrayList;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.monsters.Spider;
import nl.knokko.rpg.entities.monsters.Zombie;
import nl.knokko.rpg.utils.BackGrounds;
import nl.knokko.rpg.world.Portal;
import nl.knokko.rpg.world.maps.MapGenBase;

public class MapGenDeadDungeon1 extends MapGenBase {

	public MapGenDeadDungeon1(){
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
		return Color.BLACK;
	}

	@Override
	public String getName(){
		return "Dead Dungeon 1";
	}

	@Override
	public ArrayList<Entity> getEnemies(){
		ArrayList<Entity> enemies = new ArrayList<Entity>();
		Point p = new Point();
		enemies.add(new Spider(p, 40));
		enemies.add(new Zombie(p, 10));
		return enemies;
	}

	@Override
	public int getMaxEnemies(){
		return 4;
	}

	@Override
	public Point getStartPoint(MapGenBase old){
		return new Point();
	}

	public void build(){
		fill2(0, 199, 10, 199, 28);
		fill2(10, 194, 10, 198, 28);
		fill2(0, 193, 10, 193, 28);
		fill2(0, 194, 0, 198, 28);
		fill(1, 194, 9, 198, 6);
		tiles2[6][199] = 7;
	}
	
	public void placePortals(){
		portals.add(new Portal(new Point(180, 5970), "Dead Dungeon 1", new Point(2310, 1890), "Dark Forest West"));
	}

	@Override
	public String getMusic() {
		return "dead mansion";
	}

	@Override
	public String getFightBackGround() {
		return BackGrounds.miay_cave;
	}
}
