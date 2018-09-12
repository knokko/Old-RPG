package nl.knokko.rpg.spells;

import java.awt.Graphics;
import java.awt.Point;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.utils.Resources;

public class DropSpell extends Spell {
	
	public int spellPower;
	public Element element;
	
	public DropSpell(Entity caster, Entity victim, int castPower, int power, int manaCost, String spellName, Element spellElement) {
		this(victim.battlePoint.getLocation(), caster, victim, castPower, power, manaCost, spellName, spellElement);
	}

	public DropSpell(Point point, Entity caster, Entity victim, int castPower, int power, int manaCost, String spellName, Element spellElement) {
		super(point, caster, victim, castPower, manaCost, spellName);
		spellPower = power;
		element = spellElement;
		position.y -= 400;
	}
	
	@Override
	public void update(){
		super.update();
		Point b = user.battlePoint;
		if(position.equals(b)){
			position.x = target.battlePoint.x;
			position.y = target.battlePoint.y - 400;
		}
		position.y += 40 / Game.game.fpsFactor;
		if(position.y >= target.battlePoint.y)
			attack();
	}
	
	@Override
	public String getTexture(){
		return "sprites/spells/" + name + ".png";
	}

	@Override
	public Element getElement() {
		return element;
	}

	@Override
	public int getPower() {
		return spellPower;
	}

	@Override
	public DropSpell clone() {
		return new DropSpell(position, user, target, castPower, getPower(), mana, name, element).setText(text);
	}
	
	@Override
	public void attack(){
		target.attack(new Attack(element, true, true), user, getPower() * castPower, battle);
		disabled = true;
	}
	
	@Override
	public void paint(Graphics gr){
		texture = Resources.getImage(getTexture());
		gr.drawImage(texture, (position.x), (position.y), (120), (120), null);
	}
}
