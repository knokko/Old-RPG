package nl.knokko.rpg.spells;

import java.awt.Point;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.players.IPlayer;

public class MassSpell extends Spell {
	
	public boolean targetLeft;
	public int power;
	public int duration;
	public Element element;

	public MassSpell(Entity caster, boolean left, int castPower, int mana, String name, Element spellElement, int spellPower, int spellDuration) {
		super(new Point(), caster, null, castPower, mana, name);
		power = spellPower;
		element = spellElement;
		duration = spellDuration;
		targetLeft = left;
	}

	@Override
	public String getTexture() {
		return "sprites/spells/" + name +  " " + duration + ".png";
	}

	@Override
	public Element getElement() {
		return element;
	}

	@Override
	public int getPower() {
		return power;
	}

	@Override
	public MassSpell clone() {
		return new MassSpell(user, targetLeft, castPower, mana, name, element, power, duration).setText(text);
	}
	
	/*
	@Override
	public void paint(Graphics gr){
		gr.drawImage(Resources.getImage(getTexture()), 0, 0, MainClass.getWidth(), MainClass.getHeight(), null);
	}
	*/
	
	@Override
	public void attack(){
		Entity[] targets = targetLeft ? battle.players : battle.enemies;
		int t = 0;
		while(t < targets.length){
			if(targets[t] != null)
				targets[t].attack(new Attack(element, true, true), user, castPower * getPower(), battle);
			++t;
		}
		disabled = true;
	}
	
	public void setTarget(Entity Target){
		targetLeft = false;
		if(Target instanceof IPlayer)
			targetLeft = ((IPlayer) Target).isPlayer();
	}
	
	@Override
	public void update(){
		if(duration > 0)
			--duration;
		else 
			attack();
	}
}
