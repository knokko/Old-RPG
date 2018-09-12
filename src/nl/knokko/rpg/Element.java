package nl.knokko.rpg;

import java.awt.Color;

public enum Element {
	
	NORMAL(new Color(100, 100, 100)),
	AIR(new Color(100, 100, 250)),
	EARTH(new Color(0, 150, 0)),
	FIRE(new Color(200, 120, 0)),
	WATER(Color.BLUE),
	LIGHTNING(Color.YELLOW),
	POISON(new Color(100, 0, 100)),
	LIGHT(Color.WHITE),
	DARK(new Color(20, 20, 20)),
	ROCK(new Color(100, 50, 0));
	
	public final Color color;
	
	Element(Color theColor){
		color = theColor;
	}
	
	@Override
	public String toString(){
		return super.toString().toLowerCase();
	}
}
