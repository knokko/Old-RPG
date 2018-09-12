package nl.knokko.rpg.tiles;

import java.awt.*;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.utils.PointUtils;
import nl.knokko.rpg.utils.Resources;
import main.Application;

public class Tile {
	
	public final boolean canCollide;
	public final boolean canWalkOver;
	public final String name;
	public final byte id;
	public final Image image;
	
	private static byte nextId;
	
	Tile(String tileName, boolean canWalk, boolean canPass) {
		name = tileName;
		canCollide = canPass;
		canWalkOver = canWalk;
		id = nextId;
		++nextId;
		Tiles.tiles.add(id, this);
		image = Resources.getImage("sprites/tiles/" + name + ".png");
	}
	
	@Override
	public String toString(){
		return name;
	}
	
	public void paint(Graphics gr, Point position){
		Point screen = PointUtils.gameToScreenPosition(position);
		if(screen.x >= -30 && screen.y >= -30 && (screen.x) <= Game.game.getWidth() && (screen.y) <= Game.game.getHeight()){
			gr.drawImage(image, (screen.x), (screen.y), (30) + 1, (30) + 1, null);
		}
	}
	
	public void paint2(Graphics gr, Point position){
		Point screen = Application.gameToScreenPosition(position);
		if(screen.x >= -30 && screen.y >= -30 && screen.x <= Application.app.getWidth() && screen.y <= Application.app.getHeight()){
			gr.drawImage(image, screen.x, screen.y, null);
		}
	}
	
	public boolean collide(Point point, Entity entity){
		return canCollide;
	}
	
	public boolean walkOver(Point point, Entity entity){
		return canWalkOver;
	}
}
