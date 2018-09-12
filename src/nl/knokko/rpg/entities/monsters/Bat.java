package nl.knokko.rpg.entities.monsters;

import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.ai.EntityAIMoveArms;
import nl.knokko.rpg.entities.ai.EntityAIMoveFloating;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.gui.GuiBattle;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.spells.Spell;
import nl.knokko.rpg.spells.Spells;

public class Bat extends Entity {
	
	public Bat(Game game, Point position, int level) {
		super(game, position, level);
		strength = level * 2;
		currentHealth = maxHealth = level * 12;
		applyDifficulty();
		elementStats.setResistance(Element.DARK, 50);
		elementStats.setResistance(Element.EARTH, 100);
		elementStats.setResistance(Element.FIRE, -100);
		elementStats.setResistance(Element.LIGHT, -100);
		elementStats.setResistance(Element.LIGHTNING, -100);
		elementStats.setResistance(Element.AIR, -50);
		elementStats.setResistance(Element.ROCK, -50);
	}
	
	@Override
	public int getPower(){
		return 15;
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
	public Element getElement(){
		return Element.DARK;
	}
	
	@Override
	public ArrayList<ItemStack> getLoot(){
		ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
		loot.add(new ItemStack(Items.wing, random.nextInt(3)));
		return loot;
	}
	
	@Override
	public ArrayList<Spell> getSpells(){
		ArrayList<Spell> spells = new ArrayList<Spell>();
		if(currentMana >= 10)
			spells.add(Spells.airslash().setCastPower(strength * getWeaponPower(new Attack(Element.AIR, false, false))));
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
	
	public Model wing1(){
		return models.get(0);
	}
	
	public Model leg1(){
		return models.get(1);
	}
	
	public Model body(){
		return models.get(2);
	}
	
	public Model head(){
		return models.get(3);
	}
	
	public Model leg2(){
		return models.get(4);
	}
	
	public Model wing2(){
		return models.get(5);
	}
	
	@Override
	protected void addBody(){
		String n = getTexture().toLowerCase();
		models.add(new Model(n + "/wing.png", new Point(10, 10), new Point(8, 2), this));
		models.add(new Model(n + "/leg.png", new Point(12, 19), new Point(1, 1), this));
		models.add(new Model(n + "/body.png", new Point(13, 8), new Point(4, 6), this));
		models.add(new Model(n + "/head.png", new Point(13, 5), new Point(2, 2), this));
		models.add(new Model(n + "/leg.png", new Point(14, 19), new Point(1, 1), this));
		models.add(new Model(n + "/wing.png", new Point(10, 10), new Point(8, 2), this));
	}
	
	@Override
	protected void addAI(){
		ai.add(new EntityAIMoveFloating(this, -10, -10, 10, 10));
		ai.add(new EntityAIMoveArms(this, 2, leg1(), leg2()));
		ai.add(new EntityAIMoveArms(this, 3, wing1(), wing2()));
	}
}
