package nl.knokko.rpg.utils;

import java.awt.Point;

import nl.knokko.rpg.main.Game;

public final class PointUtils {
	
	public static Point mouseToScreenPoint(Point mouse){
		return new Point(mouse.x, mouse.y);
	}
	
	public static Point screenToMousePoint(Point screen){
		return new Point(screen.x, screen.y);
	}
	
	public static Point gameToScreenPosition(Point position){
		Point camera = Game.game.camera;
		int x = position.x - camera.x + 800;
		int y = position.y - camera.y + 450;
		return new Point(x, y);
	}
	
	public static Point screenToGamePoint(Point screen){
		Point camera = Game.game.camera;
		int x = screen.x + camera.x - 800;
		int y = screen.y + camera.y - 450;
		return new Point(x, y);
	}
	
	public static Point mouseToGamePoint(Point mouse){
		Point camera = Game.game.camera;
		int x = mouse.x + camera.x - 800;
		int y = mouse.y + camera.y - 450;
		return new Point(x, y);
	}
	
	public static Point factor(Point old){
		return new Point((old.x), (old.y));
	}
}
