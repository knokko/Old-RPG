package nl.knokko.rpg.tiles;

import java.awt.*;

import nl.knokko.rpg.utils.PointUtils;
import main.Application;

public class TileRoof extends Tile {
	
	public int xSize;
	public int ySize;
	
	TileRoof(String tileName, int sizeX, int sizeY) {
		super(tileName, true, false);
		xSize = sizeX;
		ySize = sizeY;
	}
	
	@Override
	public void paint(Graphics gr, Point point){
		Point screen = PointUtils.gameToScreenPosition(point);
		gr.drawImage(image, (screen.x - 5), (screen.y), (xSize + 10), (ySize), null);
	}
	
	@Override
	public void paint2(Graphics gr, Point point){
		Point screen = Application.gameToScreenPosition(point);
		gr.drawImage(image, screen.x - 5, screen.y, xSize + 10, ySize, null);
	}
}
