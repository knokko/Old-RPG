package nl.knokko.rpg.entities.monsters;

import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.spells.Spell;
import nl.knokko.rpg.spells.Spells;

public class Frode extends Entity {

	public Frode(Point spawn, int level) {
		super(spawn, level);
		addWolfBody();
		addWolfAI();
		strength = level * 3;
		spirit = level;
		currentHealth = maxHealth = level * 60;
		currentMana = maxMana = level * 5;
		applyDifficulty();
		elementStats.setResistance(Element.FIRE, 75);
		elementStats.setResistance(Element.WATER, -100);
	}
	
	@Override
	public int getPower(){
		return 15;
	}
	
	@Override
	public Element getElement(){
		return Element.FIRE;
	}
	
	@Override
	public int getLootXp(){
		return level * 3;
	}
	
	@Override
	public int getLootMoney(){
		return random.nextInt(level * 2);
	}
	
	@Override
	public ArrayList<ItemStack> getLoot(){
		ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
		if(random.nextBoolean())
			loot.add(new ItemStack(Items.frodeskin));
		return loot ;
	}
	
	@Override
	public ArrayList<Spell> getSpells(){
		ArrayList<Spell> spells = new ArrayList<Spell>();
		if(currentMana >= 10){
			spells.add(Spells.fireball().setCastPower(spirit * getWeaponPower(new Attack(Element.FIRE, true, true))));
		}
		return spells;
	}
	
	public Model leg1(){
		return models.get(0);
	}
	
	public Model leg2(){
		return models.get(1);
	}
	
	public Model tail(){
		return models.get(2);
	}
	
	public Model body(){
		return models.get(3);
	}
	
	public Model head(){
		return models.get(4);
	}
	
	public Model leg3(){
		return models.get(5);
	}
	
	public Model leg4(){
		return models.get(6);
	}
}
