package nl.knokko.rpg.entities.monsters;

import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.ai.EntityAIMoveArms;
import nl.knokko.rpg.entities.ai.EntityAIMoveFloating;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.spells.Spell;
import nl.knokko.rpg.spells.Spells;

public class Spirit extends Entity {

	public Spirit(Game game, Point position, int level) {
		super(game, position, level);
		spirit = level * 3;
		currentHealth = maxHealth = level * 15;
		currentMana = maxMana = level * 20;
		applyDifficulty();
		elementStats.setResistance(Element.NORMAL, 100);
		elementStats.setResistance(Element.FIRE, -50);
		elementStats.setResistance(Element.LIGHT, -50);
	}
	
	@Override
	public boolean canStrike(){
		return false;
	}
	
	@Override
	public int getPower(){
		return 30;
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
	public ArrayList<ItemStack> getLoot(){
		ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
		if(random.nextBoolean())
			loot.add(new ItemStack(Items.cystEssence));
		return loot;
	}
	
	@Override
	public ArrayList<Spell> getSpells(){
		ArrayList<Spell> spells = super.getSpells();
		if(currentMana >= 10){
			spells.add(Spells.darkvortex().setCastPower(spirit * getWeaponPower(new Attack(Element.DARK, true, true))));
			spells.add(Spells.lightvortex().setCastPower(spirit * getWeaponPower(new Attack(Element.LIGHT, true, true))));
		}
		return spells;
	}
	
	@Override
	protected void addBody(){
		String n = getTexture().toLowerCase();
		models.add(new Model(n + "/arm.png", new Point(17, 10), new Point(1, 2), this));
		models.add(new Model(n + "/tail.png", new Point(13, 19), new Point(3, 0), this));
		models.add(new Model(n + "/body.png", new Point(13, 6), new Point(3, 6), this));
		models.add(new Model(n + "/head.png", new Point(13, 0), new Point(2, 6), this));
		models.add(new Model(n + "/arm.png", new Point(17, 10), new Point(1, 2), this));
	}
	
	@Override
	protected void addAI(){
		ai.add(new EntityAIMoveArms(this, arm1(), arm2()));
		ai.add(new EntityAIMoveFloating(this, -10, -10, 10, 10));
	}
	
	public Model arm1(){
		return models.get(0);
	}
	
	public Model tail(){
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
}
