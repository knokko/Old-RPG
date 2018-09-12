package nl.knokko.rpg.entities.monsters;

import java.awt.Point;

import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.ai.EntityAIMoveLegs;
import nl.knokko.rpg.entities.model.Model;

public class FuryDog extends Entity {

	public FuryDog(Point position, int level) {
		super(position, level);
		maxHealth = currentHealth = level * 200;
		strength = level * 4;
		elementStats.setResistance(Element.ROCK, -100);
		elementStats.setResistance(Element.WATER, -100);
		elementStats.setResistance(Element.FIRE, 50);
	}
	
	@Override
	public int getPower(){
		return 20;
	}
	
	@Override
	public Element getElement(){
		return Element.FIRE;
	}
	
	@Override
	public String getName(){
		return "Fury Dog";
	}
	
	@Override
	protected void addBody(){
		String n = getTexture().toLowerCase();
		models.add(new Model(n + "/leg.png", new Point(7, 15), new Point(2, 2), this));
		models.add(new Model(n + "/leg.png", new Point(17, 15), new Point(2, 2), this));
		models.add(new Model(n + "/body.png", new Point(12, 9), new Point(8, 4), this));
		models.add(new Model(n + "/head.png", new Point(25, 7), new Point(1, 3), this));
		models.add(new Model(n + "/leg.png", new Point(7, 15), new Point(2, 2), this));
		models.add(new Model(n + "/leg.png", new Point(17, 15), new Point(2, 2), this));
	}
	
	@Override
	protected void addAI(){
		ai.add(new EntityAIMoveLegs(this, getModel("leg1"), getModel("leg3"), getModel("leg2"), getModel("leg4")));
	}
	
	public Model leg1(){
		return models.get(0);
	}
	
	public Model leg2(){
		return models.get(1);
	}
	
	public Model body(){
		return models.get(2);
	}
	
	public Model head(){
		return models.get(3);
	}
	
	public Model leg3(){
		return models.get(4);
	}
	
	public Model leg4(){
		return models.get(5);
	}
}
