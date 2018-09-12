package nl.knokko.rpg.entities.monsters;

import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.gui.GuiBattle;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.spells.Spell;
import nl.knokko.rpg.spells.Spells;

public class Vampire extends Entity {

	public Vampire(Game game, Point position, int level) {
		super(game, position, level);
		addHumanBody();
		addHumanAI();
		strength = level * 3;
		currentHealth = maxHealth = level * 20;
		currentMana = maxMana = level * 10;
		elementStats.setResistance(Element.DARK, 50);
		elementStats.setResistance(Element.LIGHT, -100);
		elementStats.setResistance(Element.FIRE, -50);
		weapon = new ItemStack(Items.darkBlade);
	}
	
	@Override
	public int getPower(){
		return 20;
	}
	
	@Override
	public int getLootXp(){
		return level * 3;
	}
	
	@Override
	public int getLootMoney(){
		return random.nextInt(level * 2);
	}
	
	@Override
	public Element getElement(){
		return Element.DARK;
	}
	
	@Override
	public ArrayList<Spell> getSpells(){
		ArrayList<Spell> spells = new ArrayList<Spell>();
		if(currentMana >= 20)
			spells.add(Spells.blooddrain().setCastPower(strength * getWeaponPower(new Attack(Element.DARK, false, false))));
		return spells;
	}
	
	@Override
	public Spell selectSpell(Entity target, GuiBattle battle){
		if(currentHealth < maxHealth && currentMana >= 20 && target.getResistance(Element.DARK) < 100){
			Spell blood = Spells.blooddrain().setCastPower(strength * getWeaponPower(new Attack(Element.DARK, false, false)));
			blood.target = target;
			return blood;
		}
		return super.selectSpell(target, battle);
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
