package nl.knokko.rpg.spells;

import java.awt.Graphics;
import java.awt.Point;

import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.main.Game;

public class LaserSpell extends LastSpell {

	public LaserSpell(Entity caster, Entity victim, int castPower, int power, int manaCost, String name, Element element, int duration) {
		super(caster, victim, castPower, power, manaCost, name, element, duration);
	}

	public LaserSpell(Entity caster, Entity victim, int castPower, int power, int manaCost, String name, Element element) {
		super(caster, victim, castPower, power, manaCost, name, element);
	}
	
	@Override
	public void paint(Graphics gr){
		Point tp = new Point(target.battlePoint.x + target.getSize() / 2, target.battlePoint.y + target.getSize() / 2);
		Point up = new Point(user.battlePoint.x + user.getSize() / 2, user.battlePoint.y + user.getSize() / 2);
		int dx = user.battlePoint.x + (tp.x - user.battlePoint.x) * 2;
		int dy = user.battlePoint.y + (tp.y - user.battlePoint.y) * 2;
		up.x += dx / 20;
		up.y += dy / 20;
		gr.setColor(getElement().color);
		int d = 10 - duration * 2;
		int y = 10 - d;
		while(y >= -10 + d){
			gr.drawLine(up.x, up.y + y, dx, dy + y);
			--y;
		}
		gr.fillOval(up.x - 40, up.y - 40, 80, 80);
	}
	
	@Override
	public LaserSpell clone(){
		return new LaserSpell(user, target, castPower, spellPower, mana, name, element, duration / Game.game.fpsFactor).setText(text);
	}
	
	@Override
	public String getTexture(){
		return "sprites/spells/" + name + ".png";
	}
}
