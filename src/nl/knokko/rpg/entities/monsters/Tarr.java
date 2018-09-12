package nl.knokko.rpg.entities.monsters;

import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.main.Game;

public class Tarr extends Entity {

	public Tarr(Game app, Point spawn, int level) {
		super(app, spawn, level);
		addHumanAI();
		strength = level;
		currentHealth = maxHealth = level * 40;
		applyDifficulty();
		elementStats.setResistance(Element.AIR, 50);
		elementStats.setResistance(Element.EARTH, 50);
		elementStats.setResistance(Element.FIRE, 50);
		elementStats.setResistance(Element.NORMAL, 50);
		elementStats.setResistance(Element.WATER, -100);
	}
	
	@Override
	public int getPower(){
		return 10;
	}
	
	@Override
	public int getArmor(Attack attack){
		return attack.magic ? 2 : 5;
	}
	
	@Override
	public int getLootXp(){
		return level * 4;
	}
	
	@Override
	public int getLootMoney(){
		return random.nextInt(level * 4);
	}
	
	@Override
	public Element getElement(){
		return Element.ROCK;
	}
	
	@Override
	public ArrayList<ItemStack> getLoot(){
		ArrayList<ItemStack> loot = super.getLoot();
		if(random.nextBoolean())
			loot.add(new ItemStack(Items.tarf));
		return loot;
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
		models.add(new Model(n + "/leg.png", new Point(16, 18), new Point(2, 2), this));
		models.add(new Model(n + "/arm.png", new Point(20, 11), new Point(1, 3), this));
		models.add(new Model(n + "/body.png", new Point(15, 8), new Point(4, 7), this));
		models.add(new Model(n + "/head.png", new Point(15, 3), new Point(5, 3), this));
		models.add(new Model(n + "/arm.png", new Point(20, 11), new Point(1, 3), this));
		models.add(new Model(n + "/leg.png", new Point(16, 18), new Point(2, 2), this));
	}
}
