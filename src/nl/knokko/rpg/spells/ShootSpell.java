package nl.knokko.rpg.spells;

import java.awt.Point;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.utils.Utils;

public class ShootSpell extends Spell {
	
	public final Element element;
	public final int power;
	public final String texture;
	public final String textureBackward;
	
	public int motionX;
	public int motionY;

	public ShootSpell(Point point, Entity caster, Entity victim, int power, Element spellElement, int spellPower, int manaCost, String name, boolean isSided) {
		super(point, caster, victim, power, manaCost, name);
		texture = "sprites/spells/" + name + ".png";
		if(isSided){
			textureBackward = texture.substring(0, texture.length() - 4) + " backward.png";
		}
		else {
			textureBackward = texture;
		}
		this.power = spellPower;
		element = spellElement;
	}

	@Override
	public String getTexture() {
		if(motionX >= 0){
			return texture;
		}
		return textureBackward;
	}

	@Override
	public Element getElement() {
		return element;
	}

	@Override
	public int getPower() {
		return power;
	}
	
	public void moveToTarget(){
		position.x += motionX;
		position.y += motionY;
	}
	
	@Override
	public void attack(){
		target.attack(new Attack(getElement(), true, true), user, castPower * getPower(), battle);
		disabled = true;
	}
	
	public int getSpeed(){
		return 50 / Game.game.fpsFactor;
	}
	
	@Override
	public void update(){
		moveToTarget();
		setMotion();
		if(position.distance(target.battlePoint) <= getSpeed()){
			attack();
		}
	}
	
	public void setMotion(){
		int distanceX = target.battlePoint.x - position.x;
		int distanceY = target.battlePoint.y - position.y;
		double distance = Math.hypot(distanceX, distanceY);
		motionX = Utils.intFromDouble((distanceX / distance) * getSpeed());
		motionY = Utils.intFromDouble((distanceY / distance) * getSpeed());
	}

	@Override
	public ShootSpell clone() {
		ShootSpell spell = new ShootSpell(position, user, target, castPower, element, power, mana, name, !texture.matches(textureBackward)).setText(text);
		spell.motionX = motionX;
		spell.motionY = motionY;
		spell.disabled = disabled;
		return spell;
	}
}
