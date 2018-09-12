package nl.knokko.rpg.entities.monsters;

import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.ai.EntityAIMoveFloating;
import nl.knokko.rpg.entities.ai.EntityAIMoveRandom;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.entities.model.ModelEquipment;
import nl.knokko.rpg.entities.model.ModelEquipment.ArmorType;
import nl.knokko.rpg.entities.model.ModelEquipmentArm;
import nl.knokko.rpg.entities.model.ModelEquipmentLeg;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.spells.Spell;
import nl.knokko.rpg.spells.Spells;

public class DarkSoul extends Entity {

	public DarkSoul(Point position, int level) {
		super(position, level);
		spirit = level * 3;
		currentHealth = maxHealth = level * 15;
		currentMana = maxMana = level * 20;
		applyDifficulty();
		elementStats.setResistance(Element.DARK, 200);
		elementStats.setResistance(Element.NORMAL, 100);
		elementStats.setResistance(Element.LIGHT, -100);
		elementStats.setPower(Element.DARK, 50);
	}
	
	@Override
	public String getName(){
		return "Dark Soul";
	}
	
	@Override
	public boolean canStrike(){
		return false;
	}
	
	@Override
	public int getPower(){
		return 25;
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
			loot.add(new ItemStack(Items.darkEssence));
		return loot;
	}
	
	@Override
	public ArrayList<Spell> getSpells(){
		ArrayList<Spell> spells = super.getSpells();
		if(currentMana >= 10){
			spells.add(Spells.darkvortex().setCastPower(spirit * getWeaponPower(new Attack(Element.DARK, true, true))));
			spells.add(Spells.poisoncloud().setCastPower(spirit * getWeaponPower(new Attack(Element.POISON, true, true))));
		}
		return spells;
	}
	
	@Override
	protected void addBody(){
		String n = getTexture().toLowerCase();
		models.add(new ModelEquipmentLeg(n + "/leg.png", new Point(14, 16), new Point(1, 1), this, ArmorType.LEG));
		models.add(new ModelEquipment(n + "/arm.png", new Point(19, 6), new Point(1, 3), this, ArmorType.ARM));
		models.add(new ModelEquipment(n + "/body.png", new Point(13, 8), new Point(2, 3), this, ArmorType.CHESTPLATE));
		models.add(new ModelEquipment(n + "/head.png", new Point(13, 1), new Point(2, 6), this, ArmorType.HELMET));
		models.add(new ModelEquipmentArm(n + "/arm.png", new Point(19, 6), new Point(1, 3), this, ArmorType.ARM));
		models.add(new ModelEquipmentLeg(n + "/leg.png", new Point(14, 16), new Point(1, 1), this, ArmorType.LEG));
	}
	
	@Override
	protected void addAI(){
		ai.add(new EntityAIMoveFloating(this, -10, -10, 10, 10));
		ai.add(new EntityAIMoveRandom(this, 5, 0, 360, leg1(), arm1(), arm2(), leg2()));
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
