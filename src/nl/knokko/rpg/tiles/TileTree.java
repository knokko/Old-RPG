package nl.knokko.rpg.tiles;

import java.awt.Point;

import nl.knokko.rpg.render.Renderer;
import nl.knokko.rpg.utils.PointUtils;

import org.lwjgl.util.vector.Vector2f;

public class TileTree extends Tile {
	
	public int height;
	
	TileTree(String tileName, int treeHeight) {
		super(tileName, true, false);
		height = treeHeight;
	}
	
	@Override
	public void render(Point position){
		Vector2f screen1 = PointUtils.worldPointToScreenVector(new Point(position.x, position.y - height + 30));
		Vector2f screen2 = PointUtils.worldPointToScreenVector(new Point(position.x + 30, position.y + 30));
		if(screen1.x <= 1 && screen2.x >= -1 && screen1.y >= -1 && screen2.y <= 1)
			Renderer.renderTextureBetween(screen1, screen2, texture.getID());
	}
}
