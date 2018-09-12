package nl.knokko.rpg.entities.monsters;

import java.awt.Point;

import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.ai.EntityAIMoveArms;
import nl.knokko.rpg.entities.ai.EntityAIMoveFloating;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.main.Game;

public class Wasp extends Entity {

	public Wasp(Game app, Point spawn, int level) {
		super(app, spawn, level);
		strength = level;
		maxHealth = currentHealth = 10 * level;
		applyDifficulty();
		elementStats.setResistance(Element.POISON, 75);
		elementStats.setResistance(Element.EARTH, 100);
		elementStats.setResistance(Element.FIRE, -100);
		elementStats.setResistance(Element.LIGHTNING, -100);
		elementStats.setResistance(Element.AIR, -50);
		elementStats.setResistance(Element.ROCK, -100);
	}
	
	@Override
	public Element getElement(){
		return Element.POISON;
	}
	
	@Override
	public int getPower(){
		return 15;
	}
	
	@Override
	public int getLootXp(){
		return level * 3;
	}
	
	@Override
	public int getLootMoney(){
		return random.nextInt(level * 3);
	}
	
	public Model wing1(){
		return models.get(0);
	}
	
	public Model leg1(){
		return models.get(1);
	}
	
	public Model leg2(){
		return models.get(2);
	}
	
	public Model leg3(){
		return models.get(3);
	}
	
	public Model stinger(){
		return models.get(4);
	}
	
	public Model body(){
		return models.get(5);
	}
	
	public Model head(){
		return models.get(6);
	}
	
	public Model leg4(){
		return models.get(7);
	}
	
	public Model leg5(){
		return models.get(8);
	}
	
	public Model leg6(){
		return models.get(9);
	}
	
	public Model wing2(){
		return models.get(10);
	}
	
	protected void addBody(){
		String n = getTexture().toLowerCase();
		models.add(new Model(n + "/wing.png", new Point(13, 10), new Point(4, 2), this));
		models.add(new Model(n + "/leg.png", new Point(18, 9), new Point(1, 2), this));
		models.add(new Model(n + "/leg.png", new Point(18, 13), new Point(1, 2), this));
		models.add(new Model(n + "/leg.png", new Point(18, 17), new Point(1, 2), this));
		models.add(new Model(n + "/stinger.png", new Point(13, 17), new Point(4, 1), this));
		models.add(new Model(n + "/body.png", new Point(13, 8), new Point(4, 6), this));
		models.add(new Model(n + "/head.png", new Point(13, 2), new Point(3, 3), this));
		models.add(new Model(n + "/leg.png", new Point(18, 9), new Point(1, 2), this));
		models.add(new Model(n + "/leg.png", new Point(18, 13), new Point(1, 2), this));
		models.add(new Model(n + "/leg.png", new Point(18, 17), new Point(1, 2), this));
		models.add(new Model(n + "/wing.png", new Point(13, 10), new Point(4, 2), this));
	}
	
	protected void addAI(){
		ai.add(new EntityAIMoveArms(this, leg1(), leg4(), leg2(), leg5(), leg3(), leg6()));
		ai.add(new EntityAIMoveArms(this, 10, wing1(), wing2()));
		ai.add(new EntityAIMoveFloating(this, -10, -10, 10, 10));
	}
}
