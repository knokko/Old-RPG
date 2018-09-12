package nl.knokko.rpg.entities.monsters;

import java.awt.Point;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.model.Model;

public class AstralWolf extends Entity {

	public AstralWolf(Point position, int level) {
		super(position, level);
		addWolfBody();
		addWolfAI();
		strength = level * 6;
		currentHealth = maxHealth = level * 75;
		applyDifficulty();
		elementStats.setResistance(Element.DARK, 50);
		elementStats.setResistance(Element.FIRE, -50);
		elementStats.setResistance(Element.LIGHT, -50);
		elementStats.setResistance(Element.NORMAL, 100);
	}
	
	@Override
	public int getArmor(Attack attack){
		return 8;
	}
	
	@Override
	public String getName(){
		return "Astral Wolf";
	}
	
	@Override
	public int getLootXp(){
		return level * 10;
	}
	
	@Override
	public int getLootMoney(){
		return random.nextInt(level * 10);
	}
	
	@Override
	public int getPower(){
		return 20;
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
