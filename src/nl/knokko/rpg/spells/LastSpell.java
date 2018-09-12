package nl.knokko.rpg.spells;

import java.awt.Graphics;
import java.awt.Point;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.effects.StatusEffect;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.toolbars.ToolBar;
import nl.knokko.rpg.utils.Resources;

public class LastSpell extends Spell {
	
	public final int spellPower;
	public final Element element;
	public int duration;
	
	public LastSpell(Entity caster, Entity victim, int castPower, int power, int manaCost, String spellName, Element spellElement, int spellDuration) {
		super(new Point(), caster, victim, castPower, manaCost, spellName);
		spellPower = power;
		element = spellElement;
		duration = spellDuration * Game.game.fpsFactor;
	}
	
	public LastSpell(Entity caster, Entity victim, int castPower, int power, int manaCost, String spellName, Element spellElement){
		this(caster, victim, castPower, power, manaCost, spellName, spellElement, 5);
	}

	@Override
	public String getTexture() {
		return "sprites/spells/" + name + " " + (duration / Game.game.fpsFactor) + ".png";
	}
	
	@Override
	public void update(){
		position = target.battlePoint;
		if(duration > 0)
			--duration;
		else 
			attack();
	}
	
	@Override
	public void attack(){
		target.attack(new Attack(element, true, true), user, castPower * getPower(), battle);
		disabled = true;
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
	public LastSpell clone() {
		return new LastSpell(user, target, castPower, spellPower, mana, name, element, duration / Game.game.fpsFactor).setText(text);
	}
	
	@Override
	public void paint(Graphics gr){
		texture = Resources.getImage(getTexture());
		gr.drawImage(texture, (position.x), (position.y), (120), (120), null);
	}
	
	public static class Infect extends LastSpell {
		
		private StatusEffect effect;

		public Infect(Entity caster, Entity victim, int castPower, int power, int manaCost, String spellName, Element spellElement, int duration, StatusEffect statusEffect) {
			super(caster, victim, castPower, power, manaCost, spellName, spellElement, duration);
			effect = statusEffect.clone();
		}
		
		public Infect(Entity caster, Entity victim, int castPower, int power, int manaCost, String name, Element element, StatusEffect effect){
			this(caster, victim, castPower, power, manaCost, name, element, 5, effect);
		}
		
		@Override
		public Infect clone(){
			return new Infect(user, target, castPower, spellPower, mana, name, element, duration / Game.game.fpsFactor, effect.clone()).setText(text);
		}
		
		@Override
		public void attack(){
			super.attack();
			target.addEffect(effect.clone());
		}
		
		@Override
		public Infect setCastPower(int power){
			super.setCastPower(power);
			effect.power *= power / 10;
			return this;
		}
		
		@Override
		public void setTarget(Entity target){
			super.setTarget(target);
			effect.entity = target;
		}
		
		@Override
		public void addMidInfo(ToolBar toolbar){
			toolbar.text.add("poison power: " + effect.power / 10.0);
			toolbar.increaseSize(0, 20);
		}
	}
	
	public static class Empower extends LastSpell {
		
		private StatusEffect effect;

		public Empower(Entity caster, Entity receiver, int castPower, int manaCost, String spellName, Element spellElement, StatusEffect statusEffect) {
			this(caster, receiver, castPower, manaCost, spellName, spellElement, 5, statusEffect);
		}
		
		public Empower(Entity caster, Entity receiver, int castPower, int manaCost, String spellName, Element spellElement, int duration, StatusEffect statusEffect) {
			super(caster, receiver, castPower, 0, manaCost, spellName, spellElement, duration);
			effect = statusEffect.clone();
		}
		
		@Override
		public void attack(){
			target.addEffect(effect.clone());
			disabled = true;
		}
		
		@Override
		public void setTarget(Entity target){
			super.setTarget(target);
			effect.entity = target;
		}
		
		@Override
		public Empower setCastPower(int power){
			effect.setCastPower(power);
			return this;
		}
		
		@Override
		public Empower clone(){
			return new Empower(user, target, castPower, mana, name, element, duration / Game.game.fpsFactor, effect).setText(text);
		}
		
		@Override
		public boolean isPositive(){
			return effect.isPositive();
		}
		
		@Override
		public void addMidInfo(ToolBar toolbar){
			effect.addInfo(toolbar);
		}
	}
	
	public static class Heal extends LastSpell{

		public Heal(Entity caster, Entity victim, int castPower, int power, int manaCost, String spellName, Element spellElement) {
			super(caster, victim, castPower, power, manaCost, spellName, spellElement);
		}
		
		public Heal(Entity caster, Entity victim, int castPower, int power, int manaCost, String spellName, Element spellElement, int duration) {
			super(caster, victim, castPower, power, manaCost, spellName, spellElement, duration);
		}
		
		@Override
		public boolean isPositive(){
			return true;
		}
		
		@Override
		public Heal clone() {
			return new Heal(user, target, castPower, spellPower, mana, name, element, duration / Game.game.fpsFactor).setText(text);
		}
		
		@Override
		public void attack(){
			target.heal(castPower * getPower());
			disabled = true;
		}
		
		@Override
		public void addInfo(ToolBar bar){
			super.addInfo(bar);
			bar.text.add("");
			bar.text.add("This spell will");
			bar.text.add("heal the target.");
			bar.increaseSize(0, 60);
		}
	}
}
