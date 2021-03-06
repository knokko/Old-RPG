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

public class Mantid extends Entity {

	public Mantid(Game app, Point spawn, int level) {
		super(app, spawn, level);
		addHumanAI();
		currentHealth = maxHealth = level * 20;
		strength = level;
		applyDifficulty();
		elementStats.setResistance(Element.POISON, 75);
		elementStats.setResistance(Element.EARTH, 50);
		elementStats.setResistance(Element.FIRE, -100);
		elementStats.setResistance(Element.LIGHTNING, -100);
	}
	
	@Override
	public int getPower(){
		return 12;
	}
	
	@Override
	public Element getElement(){
		return Element.POISON;
	}
	
	@Override
	public int getLootMoney(){
		return random.nextInt(level * 3);
	}
	
	@Override
	public ArrayList<ItemStack> getLoot(){
		ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
		if(Random.chance(10)){
			loot.add(new ItemStack(Items.mantidBlade, 1));
		}
		return loot;
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
	
	protected void addBody(){
		String n = getTexture().toLowerCase();
		models.add(new Model(n + "/leg.png", new Point(14, 18), new Point(2, 2), this));
		models.add(new Model(n + "/arm.png", new Point(19, 10), new Point(3, 3), this));
		models.add(new Model(n + "/body.png", new Point(13, 8), new Point(4, 6), this));
		models.add(new Model(n + "/head.png", new Point(13, 3), new Point(3, 3), this));
		models.add(new Model(n + "/arm.png", new Point(19, 10), new Point(3, 3), this));
		models.add(new Model(n + "/leg.png", new Point(15, 18), new Point(2, 2), this));
	}
}
