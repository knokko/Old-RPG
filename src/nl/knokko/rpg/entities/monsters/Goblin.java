package nl.knokko.rpg.entities.monsters;

import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.utils.Random;

public class Goblin extends Entity {

	public Goblin(Game game, Point position, int level) {
		super(game, position, level);
		addHumanBody();
		addHumanAI();
		strength = level * 2;
		currentHealth = maxHealth = level * 30;
		applyDifficulty();
		elementStats.setResistance(Element.LIGHT, -100);
		elementStats.setResistance(Element.FIRE, -50);
		elementStats.setResistance(Element.LIGHTNING, -50);
		elementStats.setResistance(Element.DARK, 50);
		elementStats.setResistance(Element.EARTH, 50);
		elementStats.setResistance(Element.POISON, 50);
		weapon = new ItemStack(Items.pickaxe);
		if(Random.chance(2))
			armor[0] = Items.bronzeHelmet;
		if(Random.chance(2))
			armor[1] = Items.bronzeChestplate;
		if(Random.chance(2))
			armor[2] = Items.bronzeLeggings;
		if(Random.chance(2))
			armor[3] = Items.bronzeBoots;
	}
	
	@Override
	public ArrayList<ItemStack> getLoot(){
		ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
		if(Random.chance(13))
			loot.add(weapon);
		if(Random.chance(2))
			loot.add(new ItemStack(Items.bronze));
		if(Random.chance(15))
			loot.add(new ItemStack(Items.gold));
		return loot;
	}
	
	@Override
	public int getLootMoney(){
		return random.nextInt(level * 5);
	}
	
	@Override
	public Element getElement(){
		return Element.EARTH;
	}
	
	@Override
	public int getLootXp(){
		return level * 3;
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
}
