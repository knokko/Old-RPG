package nl.knokko.rpg.spells;

import java.awt.Graphics;
import java.awt.Point;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.utils.JukeBox;
import nl.knokko.rpg.utils.Resources;

public class AttackSpell extends Spell {
	
	public int spellPower;
	public Element spellElement;
	public boolean hasTexture;
	public Point start;
	
	public AttackSpell(Point point, Entity caster, Entity victim, int power, Element element, int manaCost, String spellName, boolean texture) {
		super(point, caster, victim, 0, manaCost, spellName);
		spellPower = power;
		spellElement = element;
		hasTexture = texture;
	}

	@Override
	public String getTexture() {
		return "sprites/spells/" + name + ".png";
	}

	@Override
	public Element getElement() {
		return spellElement;
	}

	@Override
	public int getPower() {
		return spellPower;
	}

	@Override
	public AttackSpell clone() {
		return new AttackSpell(position, user, target, spellPower, spellElement, mana, name, hasTexture).setText(text);
	}
	
	@Override
	public void update(){
		if(hasTexture)
			super.update();
		if(start == null){
			start = user.battlePoint.getLocation();
		}
		position = user.battlePoint;
		Point a = user.battlePoint;
		int s = 30;
		if(user.walkProgress < 5){
			++user.walkProgress;
		}
		else {
			user.walkProgress = 0;
		}
		battle.currentAttacker = user;
		if(!battle.attackerIsReturning){
			Point t = target.battlePoint;
			if(a.x < t.x){
				a.x += s;
			}
			if(a.x > t.x){
				a.x -= s;
			}
			if(a.y < t.y){
				a.y += s;
			}
			if(a.y > t.y){
				a.y -= s;
			}
			if(a.distance(t) < s){
				battle.attackerIsReturning = true;
				attack();
			}
		}
		else {
			Point b = start;
			if(a.x < b.x){
				a.x += s;
			}
			if(a.x > b.x){
				a.x -= s;
			}
			if(a.y < b.y){
				a.y += s;
			}
			if(a.y > b.y){
				a.y -= s;
			}
			if(a.distance(b) < s){
				user.battlePoint = b;
				user.walkProgress = 0;
				battle.attackerIsReturning = false;
				disabled = true;
			}
		}
	}
	
	@Override
	public void attack(){
		target.attack(new Attack(spellElement, false, false), user, getPower() * user.getPower(new Attack(spellElement, false, false), target), battle);
		if(battle != null)
			JukeBox.playShortSound("hit");
	}
	
	@Override
	public void paint(Graphics gr){
		if(hasTexture){
			texture = Resources.getImage(getTexture());
			gr.drawImage(texture, (position.x), (position.y), (texture.getWidth(null)), (texture.getHeight(null)), null);
		}
	}
	
	public static class BloodDrain extends AttackSpell {
		
		public int drainPercent;
		
		public BloodDrain(Point point, Entity caster, Entity victim, int power, Element element, int manaCost, String spellName, boolean texture, int percent) {
			super(point, caster, victim, power, element, manaCost, spellName, texture);
			drainPercent = percent;
		}
		
		@Override
		public void attack(){
			int prev = target.currentHealth;
			super.attack();
			user.heal(((prev - target.currentHealth) * drainPercent) / 100);
		}
		
		@Override
		public BloodDrain clone(){
			return new BloodDrain(position, user, target, spellPower, spellElement, mana, name, hasTexture, drainPercent).setText(text);
		}
	}
}
