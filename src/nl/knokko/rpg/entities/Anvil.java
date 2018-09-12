package nl.knokko.rpg.entities;

import java.awt.Point;

import nl.knokko.rpg.gui.GuiAnvil;
import nl.knokko.rpg.main.Game;

public class Anvil extends Entity {

	public Anvil(Game app, Point spawn) {
		super(app, spawn);
	}
	
	@Override
	public void interact(){
		game.currentGUI = new GuiAnvil(game, game.player);
	}
	
	@Override
	public String getTexture(){
		return null;
	}
}
