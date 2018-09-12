package nl.knokko.rpg.effects;

import java.awt.Point;

import nl.knokko.rpg.gui.GuiBattle;

public class EffectManaHeal extends EffectHeal {

	public EffectManaHeal(GuiBattle theBattle, Point point) {
		super(theBattle, point);
	}
	
	@Override
	public String getTexture() {
		return "sprites/effects/manaheal" + (int)progress + ".png";
	}
}
