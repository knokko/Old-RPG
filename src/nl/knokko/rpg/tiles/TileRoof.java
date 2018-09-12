package nl.knokko.rpg.tiles;

import java.awt.Point;

import nl.knokko.rpg.render.Renderer;
import nl.knokko.rpg.utils.PointUtils;

import org.lwjgl.util.vector.Vector2f;

public class TileRoof extends Tile {
	
	public int xSize;
	public int ySize;
	
	TileRoof(String tileName, int sizeX, int sizeY) {
		super(tileName, true, false);
		xSize = sizeX;
		ySize = sizeY;
	}
	
	@Override
	public void render(Point position){
		Vector2f screen1 = PointUtils.worldPointToScreenVector(new Point(position.x - 5, position.y));
		Vector2f screen2 = PointUtils.worldPointToScreenVector(new Point(position.x + xSize + 5, position.y + ySize));
		if(screen1.x <= 1 && screen2.x >= -1 && screen1.y >= -1 && screen2.y <= 1)
			Renderer.renderTextureBetween(screen1, screen2, texture.getID());
	}
}
