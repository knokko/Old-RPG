package nl.knokko.rpg.effects;

import java.awt.Font;
import java.awt.Graphics;
import java.io.PrintWriter;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.toolbars.ToolBar;

public class EffectEnchantWeapon extends StatusEffect {
	
	public Element element;

	public EffectEnchantWeapon(Entity target) {
		super(target);
	}

	public EffectEnchantWeapon(Entity target, int castPower, Element Element) {
		super(target, castPower);
		element = Element;
	}

	@Override
	public boolean isPositive() {
		return true;
	}

	@Override
	public EffectEnchantWeapon clone() {
		return new EffectEnchantWeapon(entity, power, element);
	}
	
	@Override
	public boolean equals(Object other){
		if(other instanceof EffectEnchantWeapon){
			EffectEnchantWeapon effect = (EffectEnchantWeapon) other;
			return effect.element == element;
		}
		return false;
	}
	
	@Override
	public void loadData(String line){
		super.loadData(line);
		element = Element.valueOf(getString(line, "element").toUpperCase());
	}
	
	@Override
	public void saveData(PrintWriter writer){
		writer.println(getName() + "[power:" + power + ",element:" + element + "]");
	}
	
	
	@Override
	public void paint(Graphics gr, int x, int y, int width, int height){
		gr.drawImage(image, x, y, width, height, null);
		gr.setColor(element.color);
		gr.setFont(new Font("TimesRoman", 0, (width) / 2));
		gr.drawString(""  + power, x, y + height);
	}
	
	@Override
	public int attack(Attack attack, Entity target, int damage){
		target.attack(new Attack(element, false, false), entity, power, MainClass.getBattle());
		return damage;
	}
	
	@Override
	public void setCastPower(int cpower){
		power *= cpower / 5;
	}
	
	@Override
	public void addInfo(ToolBar toolbar){
		toolbar.text.add("effect power: " + power);
		toolbar.increaseSize(0,  20);
	}
}
