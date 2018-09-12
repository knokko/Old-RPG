package nl.knokko.rpg.entities.monsters;

import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.ItemWeapon;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.spells.Spell;
import nl.knokko.rpg.spells.Spells;
import nl.knokko.rpg.utils.Random;

public class Troll extends Entity {

	public Troll(Point position, int level) {
		super(position, level);
		addHumanBody();
		addHumanAI();
		strength = level;
		spirit = level;
		currentHealth = maxHealth = level * 40;
		applyDifficulty();
		elementStats.setResistance(Element.DARK, 50);
		elementStats.setResistance(Element.LIGHT, -100);
		elementStats.setResistance(Element.FIRE, -100);
		elementStats.setResistance(Element.EARTH, 50);
		if(random.nextBoolean() && level >= 15)
			weapon = new ItemStack(Items.torch);
		else
			weapon = new ItemStack(Items.pickaxe);
		if(level > 30){
			if(Random.chance(3))
				armor[0] = Items.bronzeHelmet;
			if(Random.chance(3))
				armor[1] = Items.bronzeChestplate;
			if(Random.chance(3))
				armor[2] = Items.bronzeLeggings;
			if(Random.chance(3))
				armor[3] = Items.bronzeBoots;
		}
	}
	
	@Override
	public ArrayList<ItemStack> getLoot(){
		ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
		if(Random.chance(13))
			loot.add(weapon);
		return loot;
	}
	
	@Override
	public int getLootMoney(){
		return random.nextInt(level * 3);
	}
	
	@Override
	public Element getElement(){
		return Element.EARTH;
	}
	
	@Override
	public int getLootXp(){
		return level * 2;
	}
	
	@Override
	public boolean canStrike(){
		return !useWand();
	}
	
	@Override
	public ArrayList<Spell> getSpells(){
		ArrayList<Spell> spells = new ArrayList<Spell>();
		if(useWand()){
			spells.add(Spells.fireball().setCastPower(spirit * getWeaponPower(new Attack(Element.FIRE, true, true))));
			spells.add(Spells.darkvortex().setCastPower(spirit * getWeaponPower(new Attack(Element.DARK, true, true))));
		}
		return spells;
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
	
	public boolean useWand(){
		if(weapon != null && weapon.item instanceof ItemWeapon){
			ItemWeapon Weapon = (ItemWeapon)weapon.item;
			return Weapon.power > Weapon.sharpness && currentMana >= 10;
		}
		return false;
	}
}
