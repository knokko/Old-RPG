package nl.knokko.rpg.tiles;

import java.awt.Point;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.players.Player;
import nl.knokko.rpg.main.Game;

public class TilePanel extends Tile {
	
	public TilePanel(String tileName) {
		super(tileName, false, true);
	}
	
	public boolean collide(Point point, Entity entity){
		if(entity instanceof Player)
			Game.game.world.map.activatePanel(point);
		return super.collide(point, entity);
	}

}
