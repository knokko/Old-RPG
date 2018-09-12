package nl.knokko.rpg.effects;

import java.awt.*;

import nl.knokko.rpg.gui.GuiBattle;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.utils.Resources;

public abstract class Effect {
	
	public final GuiBattle battle;
	public boolean enabled = true;
	public Point position;
	public int duration;
	
	public Effect(GuiBattle theBattle, Point point) {
		battle = theBattle;
		duration = getMaxDuration() * Game.game.fpsFactor;
		position = point;
	}
	
	public abstract int getMaxDuration();
	public abstract String getTexture();
	
	public void update(){
		if(duration > 0){
			--duration;
		}
		else {
			enabled = false;
		}
	}
	
	public void paint(Graphics gr){
		Image image = Resources.getImage(getTexture());
		gr.drawImage(image, (position.x), (position.y), (120), (120), null);
	}
}
