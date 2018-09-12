package nl.knokko.rpg.entities.monsters;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.spells.Spell;
import nl.knokko.rpg.spells.Spells;

public class VoidLing extends Entity {

	public VoidLing(Point position, int level) {
		super(position, level);
		addGhostBody();
		addHumanAI();
		strength = level * 2;
		spirit = level * 2;
		currentHealth = maxHealth = level * 50;
		currentMana = maxMana = level * 50;
		applyDifficulty();
		elementStats.setResistance(Element.LIGHT, -100);
		elementStats.setResistance(Element.FIRE, -50);
		elementStats.setResistance(Element.POISON, 100);
		elementStats.setResistance(Element.NORMAL, 100);
		elementStats.setResistance(Element.DARK, 200);
	}
	
	@Override
	public void paintInBattle(Graphics gr, boolean facingLeft){
		if(random.nextInt(10) == 2)
			refreshModels();
		super.paintInBattle(gr, facingLeft);
	}
	
	@Override
	public int getPower(){
		return level;
	}
	
	@Override
	public Element getElement(){
		return Element.DARK;
	}
	
	@Override
	public int getLootXp(){
		return level * 2;
	}
	
	@Override
	public int getLootMoney(){
		return random.nextInt(level * 2);
	}
	
	@Override
	public ArrayList<ItemStack> getLoot(){
		ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
		if(random.nextBoolean())
			loot.add(new ItemStack(Items.darkEssence));
		return loot;
	}
	
	@Override
	public ArrayList<Spell> getSpells(){
		ArrayList<Spell> spells = new ArrayList<Spell>();
		if(currentMana >= 10){
			spells.add(Spells.darkvortex().setCastPower(spirit * getWeaponPower(new Attack(Element.DARK, true, true))));
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
}
