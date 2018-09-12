package nl.knokko.rpg.tiles;

import java.awt.*;

import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.utils.PointUtils;
import main.Application;

public class TileTree extends Tile {
	
	public int height;
	
	TileTree(String tileName, int treeHeight) {
		super(tileName, true, false);
		height = treeHeight;
	}
	
	@Override
	public void paint(Graphics gr, Point point){
		Point screen = PointUtils.gameToScreenPosition(point);
		if(screen.x >= -30 && screen.y >= -45 && (screen.x) <= Game.game.getWidth() && (screen.y) <= Game.game.getHeight())
			gr.drawImage(image, (screen.x), (screen.y - height + 30), (30), (height), null);
	}
	
	@Override
	public void paint2(Graphics gr, Point point){
		Point screen = Application.gameToScreenPosition(point);
		if(screen.x >= -30 && screen.y >= -45 && screen.x <= Application.app.getWidth() && screen.y <= Application.app.getHeight())
			gr.drawImage(image, screen.x, screen.y - height + 30, null);
	}
}
