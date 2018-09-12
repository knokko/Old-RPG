package nl.knokko.rpg.effects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import nl.knokko.rpg.Element;
import nl.knokko.rpg.gui.GuiBattle;

public class EffectDamage extends Effect {
	
	public final int amount;
	public final Element element;
	
	public EffectDamage(GuiBattle theBattle, Point point, Element attackElement, int damage) {
		super(theBattle, point);
		amount = damage;
		element = attackElement;
	}

	@Override
	public int getMaxDuration() {
		return 10;
	}

	@Override
	public String getTexture() {
		return "";
	}
	
	@Override
	public void paint(Graphics gr){
		if(duration >= 1){
			int duration = this.duration;
			String s = amount + "";
			gr.setColor(element.color);
			gr.fillOval((position.x + 60 - 4 * duration), (position.y + 60 - 4 * duration), (8 * duration), (8 * duration));
			gr.setColor(Color.BLACK);
			gr.setFont(new Font("TimesRoman", 0, (3 * duration)));
			gr.drawString(s, ((int) (position.x + 60 - ((s.length() * 0.8)) * duration)), (int) (position.y + 60 + 1.33 * duration));
		}
	}
}
