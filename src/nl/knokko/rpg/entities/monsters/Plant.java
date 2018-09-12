package nl.knokko.rpg.entities.monsters;

import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.entities.model.ModelEquipment;
import nl.knokko.rpg.entities.model.ModelEquipmentArm;
import nl.knokko.rpg.entities.model.ModelEquipmentLeg;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.spells.Spell;
import nl.knokko.rpg.spells.Spells;

public class Plant extends Entity {

	public Plant(Game app, Point spawn, int level) {
		super(app, spawn, level);
		addHumanAI();
		spirit = level;
		strength = level / 2;
		currentHealth = maxHealth = level * 30;
		applyDifficulty();
		elementStats.setResistance(Element.EARTH, 75);
		elementStats.setResistance(Element.WATER, 110);
		elementStats.setResistance(Element.FIRE, -100);
		elementStats.setResistance(Element.POISON, -100);
		elementStats.setResistance(Element.LIGHTNING, 50);
		elementStats.setResistance(Element.AIR, -50);
		elementStats.setResistance(Element.LIGHT, 110);
	}
	
	@Override
	public int getPower(){
		return 10;
	}
	
	@Override
	public int getLootXp(){
		return level * 2;
	}
	
	@Override
	public int getLootMoney(){
		return random.nextInt(level * 2);
	}
	
	@Override
	public ArrayList<Spell> getSpells(){
		ArrayList<Spell> spells = new ArrayList<Spell>();
		if(currentMana >= 10)
			spells.add(Spells.needleshot().setCastPower(spirit * getWeaponPower(new Attack(Element.NORMAL, true, true))));
		return spells;
	}
	
	@Override
	public Element getElement(){
		return Element.EARTH;
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
	
	protected void addBody(){
		String n = getTexture().toLowerCase();
		models.add(new ModelEquipmentLeg(n + "/leg.png", new Point(11, 20), new Point(1, 1), this));
		models.add(new ModelEquipment(n + "/arm.png", new Point(19, 10), new Point(1, 2), this));
		models.add(new ModelEquipment(n + "/body.png", new Point(13, 8), new Point(3, 6), this));
		models.add(new ModelEquipment(n + "/head.png", new Point(13, 1), new Point(7, 5), this));
		models.add(new ModelEquipmentArm(n + "/arm.png", new Point(19, 10), new Point(1, 2), this));
		models.add(new ModelEquipmentLeg(n + "/leg.png", new Point(14, 20), new Point(1, 1), this));
	}
}
