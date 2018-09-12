package nl.knokko.rpg.entities.monsters;

import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.model.ModelEquipment;
import nl.knokko.rpg.entities.model.ModelEquipmentArm;
import nl.knokko.rpg.entities.model.ModelEquipmentLeg;
import nl.knokko.rpg.entities.players.IPlayer;
import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.spells.Spell;
import nl.knokko.rpg.spells.Spells;

public class Zombie extends Entity implements IPlayer {

	public Zombie(Point position, int level) {
		super(position, level);
		addHumanBody();
		addHumanAI();
		strength = level;
		spirit = level * 8;
		currentHealth = maxHealth = level * 100;
		currentMana = maxMana = level * 300;
		if(!isPlayer())
			applyDifficulty();
		elementStats.setResistance(Element.LIGHT, -100);
		elementStats.setResistance(Element.DARK, 150);
	}

	public boolean isPlayer() {
		if(MainClass.currentGUI != null && MainClass.currentGUI.getClass().getName().matches("rpg.gui.GuiMovie$GuiIntroMovie$1"))
			return true;
		return false;
	}
	
	@Override
	public int getPower(){
		return 20;
	}
	
	@Override
	public ArrayList<Spell> getSpells(){
		ArrayList<Spell> spells = new ArrayList<Spell>();
		if(currentMana >= 10){
			spells.add(Spells.fireball().setCastPower(spirit * getWeaponPower(new Attack(Element.FIRE, true, true))));
			spells.add(Spells.iceball().setCastPower(spirit * getWeaponPower(new Attack(Element.WATER, true, true))));
			spells.add(Spells.rock().setCastPower(spirit * getWeaponPower(new Attack(Element.ROCK, true, true))));
			spells.add(Spells.heal().setCastPower(spirit * getWeaponPower(new Attack(Element.LIGHT, true, true))));
		}
		return spells;
	}
	
	public ModelEquipmentLeg leg1(){
		return (ModelEquipmentLeg) models.get(0);
	}
	
	public ModelEquipment arm1(){
		return (ModelEquipment) models.get(1);
	}
	
	public ModelEquipment body(){
		return (ModelEquipment) models.get(2);
	}
	
	public ModelEquipment head(){
		return (ModelEquipment) models.get(3);
	}
	
	public ModelEquipmentArm arm2(){
		return (ModelEquipmentArm) models.get(4);
	}
	
	public ModelEquipmentLeg leg2(){
		return (ModelEquipmentLeg) models.get(5);
	}
}
