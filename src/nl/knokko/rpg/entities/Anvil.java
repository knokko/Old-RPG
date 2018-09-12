package nl.knokko.rpg.entities;

import java.awt.Point;

import nl.knokko.rpg.gui.GuiAnvil;
import nl.knokko.rpg.main.MainClass;

public class Anvil extends Entity {

	public Anvil(Point spawn) {
		super(spawn);
	}
	
	@Override
	public void interact(){
		MainClass.currentGUI = new GuiAnvil(MainClass.player);
	}
	
	@Override
	public String getTexture(){
		return null;
	}
}
