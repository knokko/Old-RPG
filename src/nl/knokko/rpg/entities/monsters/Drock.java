package nl.knokko.rpg.entities.monsters;

import java.awt.Point;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.main.Game;

public class Drock extends Entity {

	public Drock(Game game, Point position, int level) {
		super(game, position, level);
		addHumanAI();
		strength = level;
		currentHealth = maxHealth = level * 50;
		applyDifficulty();
		elementStats.setResistance(Element.DARK, 50);
		elementStats.setResistance(Element.ROCK, 50);
		elementStats.setResistance(Element.FIRE, 50);
		elementStats.setResistance(Element.LIGHT, -50);
	}
	
	@Override
	public int getArmor(Attack attack){
		return attack.magic ? 4 : 6;
	}
	
	@Override
	public int getPower(){
		return 15;
	}
	
	@Override
	public int getLootXp(){
		return level * 4;
	}
	
	@Override
	public int getLootMoney(){
		return random.nextInt(level * 3);
	}
	
	@Override
	protected void addBody(){
		String n = getTexture().toLowerCase();
		models.add(new Model(n + "/leg.png", new Point(15, 18), new Point(2, 1), this));
		models.add(new Model(n + "/arm.png", new Point(20, 9), new Point(1, 4), this));
		models.add(new Model(n + "/body.png", new Point(15, 8), new Point(4, 7), this));
		models.add(new Model(n + "/arm.png", new Point(20, 9), new Point(1, 4), this));
		models.add(new Model(n + "/leg.png", new Point(15, 18), new Point(2, 1), this));
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
	
	public Model arm2(){
		return models.get(3);
	}
	
	public Model leg2(){
		return models.get(4);
	}
}
