package nl.knokko.rpg.entities.monsters;

import java.awt.Point;

import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.main.Game;

public class AstralWarrior extends Entity {

	public AstralWarrior(Game game, Point position, int level) {
		super(game, position, level);
		addHumanBody();
		addHumanAI();
		strength = level * 2;
		currentHealth = maxHealth = level * 30;
		applyDifficulty();
		weapon = new ItemStack(Items.darkBlade);
		if(random.nextBoolean())
			armor[0] = Items.darkHelmet;
		if(random.nextBoolean())
			armor[1] = Items.darkChestplate;
		if(random.nextBoolean())
			armor[2] = Items.darkLeggings;
		if(random.nextBoolean())
			armor[3] = Items.darkChestplate;
		elementStats.setResistance(Element.DARK, 50);
		elementStats.setResistance(Element.FIRE, -50);
		elementStats.setResistance(Element.LIGHT, -50);
		elementStats.setResistance(Element.NORMAL, 100);
	}
	
	@Override
	public int getLootXp(){
		return level * 6;
	}
	
	@Override
	public int getLootMoney(){
		return random.nextInt(level * 5);
	}
	
	@Override
	public String getName(){
		return "Astral Warrior";
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
