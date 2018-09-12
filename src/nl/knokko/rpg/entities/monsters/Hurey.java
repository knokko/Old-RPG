package nl.knokko.rpg.entities.monsters;

import java.awt.Point;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.ai.EntityAIMoveLegs;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.entities.players.IPlayer;
import nl.knokko.rpg.main.MainClass;

public class Hurey extends Entity implements IPlayer{

	public Hurey(Point position, int level) {
		super(position, level);
		strength = level * 4;
		currentHealth = maxHealth = level * 200;
		if(!isPlayer())
			applyDifficulty();
	}

	public boolean isPlayer() {
		if(MainClass.currentGUI != null && MainClass.currentGUI.getClass().getName().matches(""))
			return true;
		return false;
	}
	
	@Override
	public int getPower(){
		return 25;
	}
	
	@Override
	protected void addBody(){
		String t = getTexture().toLowerCase();
		models.add(new Model(t + "/leg.png", new Point(20, 37), new Point(5, 4), this));
		models.add(new Model(t + "/body.png", new Point(13, 9), new Point(28, 27), this));
		models.add(new Model(t + "/leg.png", new Point(20, 37), new Point(5, 4), this));
	}
	
	@Override
	protected void addAI(){
		ai.add(new EntityAIMoveLegs(this, leg1(), leg2()));
	}
	
	public Model leg1(){
		return models.get(0);
	}
	
	public Model body(){
		return models.get(1);
	}
	
	public Model leg2(){
		return models.get(2);
	}
}
