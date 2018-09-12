package nl.knokko.rpg.entities.monsters;

import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.ai.EntityAIMoveArms;
import nl.knokko.rpg.entities.ai.EntityAIMoveFloating;
import nl.knokko.rpg.entities.ai.EntityAIMoveWings;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.spells.Spell;
import nl.knokko.rpg.spells.Spells;

public class Bird extends Entity {

	public Bird(Game app, Point spawn, int level) {
		super(app, spawn, level);
		strength = level;
		spirit = level * 2;
		currentHealth = maxHealth = level * 50;
		currentMana = maxMana = level * 10;
		applyDifficulty();
		elementStats.setResistance(Element.AIR, -50);
		elementStats.setResistance(Element.EARTH, 100);
		elementStats.setResistance(Element.FIRE, -100);
		elementStats.setResistance(Element.LIGHTNING, -100);
		elementStats.setResistance(Element.NORMAL, 50);
		elementStats.setResistance(Element.POISON, -50);
		elementStats.setResistance(Element.WATER, -50);
		elementStats.setResistance(Element.ROCK, -100);
	}
	
	@Override
	public int getPower(){
		return 10;
	}
	
	@Override
	public Element getElement(){
		return Element.AIR;
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
	public ArrayList<ItemStack> getLoot(){
		ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
		if(random.nextBoolean())
			loot.add(new ItemStack(Items.wing));
		if(random.nextBoolean())
			loot.add(new ItemStack(Items.feather, 2));
		return loot;
	}
	
	@Override
	public ArrayList<Spell> getSpells(){
		ArrayList<Spell> spells = new ArrayList<Spell>();
		if(currentMana >= 10){
			spells.add(Spells.windshot().setCastPower(spirit * getWeaponPower(new Attack(Element.AIR, true, true))));
		}
		return spells;
	}
	
	public Model wing1(){
		return models.get(0);
	}
	
	public Model leg1(){
		return models.get(1);
	}
	
	public Model body(){
		return models.get(2);
	}
	
	public Model head(){
		return models.get(3);
	}
	
	public Model leg2(){
		return models.get(4);
	}
	
	public Model wing2(){
		return models.get(5);
	}
	
	@Override
	protected void addBody(){
		String n = getTexture().toLowerCase();
		models.add(new Model(n + "/wing.png", new Point(10, 11), new Point(12, 2), this));
		models.add(new Model(n + "/leg.png", new Point(14, 17), new Point(2, 0), this));
		models.add(new Model(n + "/body.png", new Point(13, 8), new Point(8, 4), this));
		models.add(new Model(n + "/head.png", new Point(24, 10), new Point(4, 4), this));
		models.add(new Model(n + "/leg.png", new Point(14, 17), new Point(2, 0), this));
		models.add(new Model(n + "/wing.png", new Point(10, 11), new Point(12, 2), this));
	}
	
	@Override
	protected void addAI(){
		ai.add(new EntityAIMoveFloating(this, -10, -10, 10, 10));
		ai.add(new EntityAIMoveArms(this, 2, wing1(), wing2()));
		ai.add(new EntityAIMoveWings(this, 2, 25, leg1(), leg2()));
	}
}
