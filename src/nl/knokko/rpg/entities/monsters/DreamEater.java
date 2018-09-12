package nl.knokko.rpg.entities.monsters;

import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.gui.GuiBattle;
import nl.knokko.rpg.spells.Spell;
import nl.knokko.rpg.spells.Spells;

public class DreamEater extends Entity {

	public DreamEater(Point position, int level) {
		super(position, level);
		addHumanAI();
		strength = level * 2;
		spirit = level;
		currentMana = maxMana = level * 10;
		currentHealth = maxHealth = level * 60;
		applyDifficulty();
		elementStats.setResistance(Element.DARK, 100);
		elementStats.setResistance(Element.POISON, 50);
		elementStats.setResistance(Element.LIGHT, -100);
		elementStats.setResistance(Element.ROCK, -50);
		elementStats.setResistance(Element.FIRE, -50);
	}
	
	@Override
	public String getName(){
		return "Dream Eater";
	}
	
	@Override
	public int getPower(){
		return 15;
	}
	
	@Override
	public int getLootXp(){
		return level * 5;
	}
	
	@Override
	public int getLootMoney(){
		return random.nextInt(level * 4);
	}
	
	@Override
	public int getArmor(Attack attack){
		return attack.magic ? 2 : 3;
	}
	
	@Override
	public ArrayList<Spell> getSpells(){
		ArrayList<Spell> spells = super.getSpells();
		if(currentMana >= 20)
			spells.add(Spells.blooddrain().setCastPower(strength * getWeaponPower(new Attack(Element.DARK, false, false))));
		if(currentMana >= 10){
			spells.add(Spells.darkslash().setCastPower(strength * getWeaponPower(new Attack(Element.DARK, false, false))));
			spells.add(Spells.darkvortex().setCastPower(spirit * getWeaponPower(new Attack(Element.DARK, true, true))));
		}
		return spells;
	}
	
	@Override
	public Spell selectSpell(Entity target, GuiBattle battle){
		if(currentHealth < maxHealth && currentMana >= 20 && target.getResistance(Element.DARK) < 100)
			return getSpells().get(0);
		return super.selectSpell(target, battle);
	}
	
	public Model leg1(){
		return models.get(0);
	}
	
	public Model arm1(){
		return models.get(1);
	}
	
	public Model body(){
		return models.get(2);
	}
	
	public Model head(){
		return models.get(3);
	}
	
	public Model arm2(){
		return models.get(4);
	}
	
	public Model leg2(){
		return models.get(5);
	}
	
	@Override
	protected void addBody(){
		String n = getTexture().toLowerCase();
		models.add(new Model(n + "/leg.png", new Point(16, 18), new Point(1, 1), this));
		models.add(new Model(n + "/arm.png", new Point(21, 6), new Point(1, 6), this));
		models.add(new Model(n + "/body.png", new Point(15, 8), new Point(4, 7), this));
		models.add(new Model(n + "/head.png", new Point(15, 1), new Point(3, 8), this));
		models.add(new Model(n + "/arm.png", new Point(21, 6), new Point(1, 6), this));
		models.add(new Model(n + "/leg.png", new Point(16, 18), new Point(1, 1), this));
	}
}
