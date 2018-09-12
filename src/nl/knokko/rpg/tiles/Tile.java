package nl.knokko.rpg.tiles;

import java.awt.Image;
import java.awt.Point;

import org.lwjgl.util.vector.Vector2f;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.render.Renderer;
import nl.knokko.rpg.render.Texture;
import nl.knokko.rpg.utils.PointUtils;
import nl.knokko.rpg.utils.Resources;

public class Tile {
	
	public final boolean canCollide;
	public final boolean canWalkOver;
	public final String name;
	public final byte id;
	public final Image image;
	
	protected Texture texture;
	
	private static byte nextId;
	
	Tile(String tileName, boolean canWalk, boolean canPass) {
		name = tileName;
		canCollide = canPass;
		canWalkOver = canWalk;
		id = nextId;
		++nextId;
		Tiles.tiles.add(id, this);
		image = Resources.getImage("sprites/tiles/" + name + ".png");
		texture = Resources.getTexture("sprites/tiles/" + name + ".png");
	}
	
	@Override
	public String toString(){
		return name;
	}
	
	public boolean collide(Point point, Entity entity){
		return canCollide;
	}
	
	public boolean walkOver(Point point, Entity entity){
		return canWalkOver;
	}

	public void render(Point position) {
		Vector2f screen1 = PointUtils.worldPointToScreenVector(position);
		Vector2f screen2 = PointUtils.worldPointToScreenVector(new Point(position.x + 30, position.y + 30));
		if(screen1.x <= 1 && screen2.x >= -1 && screen1.y >= -1 && screen2.y <= 1)
			Renderer.renderTextureBetween(screen1, screen2, texture.getID());
	}
}
