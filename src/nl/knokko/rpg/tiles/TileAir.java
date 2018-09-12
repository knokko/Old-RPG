package nl.knokko.rpg.tiles;

import java.awt.Point;

public class TileAir extends Tile {

	public TileAir() {
		super("air", false, true);
	}
	
	@Override
	public void render(Point position){}
}
