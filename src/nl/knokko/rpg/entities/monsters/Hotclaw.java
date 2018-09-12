package nl.knokko.rpg.entities.monsters;

import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.ai.EntityAIMoveArms;
import nl.knokko.rpg.entities.ai.EntityAIMoveLegs;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.spells.Spell;
import nl.knokko.rpg.spells.Spells;

public class Hotclaw extends Entity {

	public Hotclaw(Game game, Point position, int level) {
		super(game, position, level);
		strength = level * 2;
		spirit = level;
		currentHealth = maxHealth = level * 50;
		currentMana = maxMana = level * 10;
		applyDifficulty();
		elementStats.setResistance(Element.FIRE, 200);
		elementStats.setResistance(Element.ROCK, 50);
		elementStats.setResistance(Element.NORMAL, 50);
		elementStats.setResistance(Element.LIGHTNING, 50);
		elementStats.setResistance(Element.WATER, -100);
	}
	
	@Override
	public int getPower(){
		return 20;
	}
	
	@Override
	public String getName(){
		return "Hot Claw";
	}
	
	@Override
	public String getTexture(){
		return "sprites/entities/hotclaw";
	}
	
	@Override
	public  int getLootXp(){
		return level * 3;
	}
	
	@Override
	public int getArmor(Attack attack){
		return attack.magic ? 2 : 3;
	}
	
	@Override
	public int getLootMoney(){
		return random.nextInt(level * 4);
	}
	
	@Override
	public Element getElement(){
		return Element.FIRE;
	}
	
	@Override
	public ArrayList<Spell> getSpells(){
		ArrayList<Spell> spells = super.getSpells();
		if(currentMana >= 10){
			spells.add(Spells.rockslash().setCastPower(strength * getWeaponPower(new Attack(Element.ROCK, false, false))));
			spells.add(Spells.rock().setCastPower(spirit * getWeaponPower(new Attack(Element.ROCK, true, true))));
		}
		return spells;
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
		models.add(new Model(n + "/leg.png", new Point(13, 19), new Point(2, 2), this));
		models.add(new Model(n + "/arm.png", new Point(18, 10), new Point(2, 3), this));
		models.add(new Model(n + "/body.png", new Point(13, 9), new Point(4, 6), this));
		models.add(new Model(n + "/head.png", new Point(13, 0), new Point(3, 7), this));
		models.add(new Model(n + "/arm.png", new Point(18, 10), new Point(2, 3), this));
		models.add(new Model(n + "/leg.png", new Point(15, 19), new Point(2, 2), this));
	}
	
	@Override
	protected void addAI(){
		ai.add(new EntityAIMoveArms(this, arm1(), arm2()));
		ai.add(new EntityAIMoveLegs(this, leg1(), leg2()));
	}
}
