package nl.knokko.rpg.effects;

import java.awt.Point;

import nl.knokko.rpg.gui.GuiBattle;

public class EffectHeal extends Effect {
	
	public int progress;
	
	public EffectHeal(GuiBattle theBattle, Point point) {
		super(theBattle, point);
		progress = 1;
	}

	@Override
	public int getMaxDuration() {
		return 9;
	}

	@Override
	public String getTexture() {
		return "sprites/effects/heal" + (int)progress + ".png";
	}
	
	@Override
	public void update(){
		super.update();
		progress += 1;
	}

}
