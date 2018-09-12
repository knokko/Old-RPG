package nl.knokko.rpg.entities.monsters;

import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.ai.EntityAIMoveLegs;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.spells.Spell;
import nl.knokko.rpg.spells.Spells;

public class Spider extends Entity {

	public Spider(Point position, int level) {
		super(position, level);
		strength = level;
		spirit = level / 2;
		currentHealth = maxHealth = level * 40;
		currentMana = maxMana = level * 5;
		applyDifficulty();
		elementStats.setResistance(Element.POISON, 90);
		elementStats.setResistance(Element.FIRE, -50);
		elementStats.setResistance(Element.LIGHT, -100);
		elementStats.setResistance(Element.DARK, 50);
	}
	
	@Override
	public int getPower(){
		return 20;
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
			spells.add(Spells.poisonslash().setCastPower(strength * getWeaponPower(new Attack(Element.POISON, false, false))));
		return spells;
	}
	
	public Model leg1(){
		return models.get(0);
	}
	
	public Model leg2(){
		return models.get(1);
	}
	
	public Model leg3(){
		return models.get(2);
	}
	
	public Model leg4(){
		return models.get(3);
	}
	
	public Model body(){
		return models.get(4);
	}
	
	public Model head(){
		return models.get(5);
	}
	
	public Model leg5(){
		return models.get(6);
	}
	
	public Model leg6(){
		return models.get(7);
	}
	
	public Model leg7(){
		return models.get(8);
	}
	
	public Model leg8(){
		return models.get(9);
	}
	
	@Override
	protected void addBody(){
		String n = getTexture().toLowerCase();
		models.add(new Model(n + "/leg.png", new Point(5, 13), new Point(2, 1), this));
		models.add(new Model(n + "/leg.png", new Point(10, 15), new Point(2, 1), this));
		models.add(new Model(n + "/leg.png", new Point(16, 15), new Point(2, 1), this));
		models.add(new Model(n + "/leg.png", new Point(21, 13), new Point(2, 1), this));
		models.add(new Model(n + "/body.png", new Point(13, 6), new Point(4, 6), this));
		models.add(new Model(n + "/head.png", new Point(26, 8), new Point(4, 3), this));
		models.add(new Model(n + "/leg.png", new Point(5, 13), new Point(2, 1), this));
		models.add(new Model(n + "/leg.png", new Point(10, 15), new Point(2, 1), this));
		models.add(new Model(n + "/leg.png", new Point(16, 15), new Point(2, 1), this));
		models.add(new Model(n + "/leg.png", new Point(21, 13), new Point(2, 1), this));
	}
	
	@Override
	protected void addAI(){
		ai.add(new EntityAIMoveLegs(this, leg1(), leg5(), leg2(), leg6(), leg3(), leg7(), leg4(), leg8()));
	}
}
