package nl.knokko.rpg.effects;

import java.io.PrintWriter;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.utils.Resources;

public class EffectPoison extends StatusEffect {
	
	public Element element;
	
	public EffectPoison(Entity target){
		super(target);
	}

	public EffectPoison(Entity target, int power) {
		this(target, power, Element.POISON);
	}
	
	public EffectPoison(Entity target, int power, Element poisonElement){
		super(target, power);
		element = poisonElement;
		image = Resources.getImage("sprites/effects/poison " + element + ".png");
	}
	
	@Override
	public void loadData(String line){
		super.loadData(line);
		element = Element.valueOf(getString(line, "element").toUpperCase());
		image = Resources.getImage("sprites/effects/poison " + element + ".png");
	}
	
	@Override
	public void saveData(PrintWriter writer){
		writer.println(getName() + "[power:" + power + ",element:" + element + "]");
	}
	
	@Override
	public void update(){
		if(entity != null)
			entity.attack(new Attack(element, true, true), null, power, MainClass.getBattle());
	}
	
	@Override
	public EffectPoison clone(){
		return new EffectPoison(entity, power, element);
	}
	
	@Override
	public boolean equals(Object other){
		if(other instanceof EffectPoison){
			EffectPoison effect = (EffectPoison) other;
			return effect.element == element;
		}
		return false;
	}

	@Override
	public boolean isPositive() {
		return power < 0;
	}
}
