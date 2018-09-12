package nl.knokko.rpg.entities.monsters;

import java.awt.Point;

import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.main.Game;

public class FuryCaptain extends Entity {

	public FuryCaptain(Game game, Point position, int level){
		super(game, position, level);
		addFuryBody();
		addHumanAI();
		maxHealth = currentHealth = level * 150;
		strength = level * 3;
		applyDifficulty();
		elementStats.setResistance(Element.ROCK, -100);
		elementStats.setResistance(Element.WATER, -100);
		elementStats.setResistance(Element.FIRE, 50);
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
	public String getName(){
		return "Fury Captain";
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
