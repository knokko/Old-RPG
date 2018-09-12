package nl.knokko.rpg.entities.players;

import java.awt.Point;

import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.spells.Spells;

public class Chompo extends Player {

	public Chompo(Point point) {
		super(point);
		weapon = null;
		strength = 50;
		currentHealth = maxHealth = 1000;
		currentMana = maxMana = 300;
		if(!learnedSpells.contains(Spells.powerattack()))
			learnedSpells.add(Spells.powerattack());
		if(!learnedSpells.contains(Spells.empower()))
			learnedSpells.add(Spells.empower());
		if(!learnedSpells.contains(Spells.waterslash()))
			learnedSpells.add(Spells.waterslash());
	}
	
	@Override
	public String getName(){
		return "Chompo";
	}
	
	@Override
	public int getPower(){
		return 30;
	}
	
	@Override
	public void loadData(){
		super.loadData();
		if(!learnedSpells.contains(Spells.powerattack()))
			learnedSpells.add(Spells.powerattack());
		if(!learnedSpells.contains(Spells.empower()))
			learnedSpells.add(Spells.empower());
	}
	
	@Override
	protected void addBody(){
		String n = getTexture().toLowerCase();
		models.add(new Model(n + "/leg.png", new Point(14, 24), new Point(3, 1), this));
		models.add(new Model(n + "/arm.png", new Point(17, 12), new Point(3, 3), this));
		models.add(new Model(n + "/body.png", new Point(13, 10), new Point(6, 8), this));
		models.add(new Model(n + "/head.png", new Point(13, 0), new Point(4, 10), this));
		models.add(new Model(n + "/arm.png", new Point(17, 12), new Point(3, 3), this));
		models.add(new Model(n + "/leg.png", new Point(14, 24), new Point(3, 1), this));
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
