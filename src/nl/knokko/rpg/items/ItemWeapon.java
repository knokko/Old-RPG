package nl.knokko.rpg.items;

import java.awt.Color;
import java.util.ArrayList;

import nl.knokko.rpg.Element;
import nl.knokko.rpg.effects.StatusEffect;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.toolbars.ToolBar;

public class ItemWeapon extends ItemEquipment {
	
	public final ArrayList<StatusEffect> effects = new ArrayList<StatusEffect>();
	public final ArrayList<String> extraText = new ArrayList<String>();
	
	public Color textColor = Color.BLACK;
	
	public final int power;
	public final int sharpness;
	
	ItemWeapon(String itemName, int specialPower, int weaponPower, int value) {
		this(itemName, specialPower, weaponPower, value, Element.NORMAL);
	}
	
	ItemWeapon(String itemName, int specialPower, int weaponPower, int value, Element element) {
		super(itemName, value, element);
		power = specialPower;
		sharpness = weaponPower;
	}
	
	@Override
	public ToolBar getToolBar(){
		ToolBar bar = new ToolBar(Game.game, Color.BLUE, textColor, name, "Weapon", "", "Price: " + value, "", "Strike power: " + sharpness, "Ranged power: " + power, "Element: " + element.toString().toLowerCase());
		bar.setSize(200, 170);
		int t = 0;
		while(t < extraText.size()){
			bar.text.add(extraText.get(t));
			bar.increaseSize(0, 20);
			++t;
		}
		return bar;
	}
	
	public ItemWeapon addEffect(StatusEffect effect){
		effects.add(effect);
		return this;
	}
	
	public ItemWeapon addText(String... text){
		int t = 0;
		while(t < text.length){
			extraText.add(text[t]);
			++t;
		}
		return this;
	}
	
	public ItemWeapon setTextColor(Color color){
		textColor = color;
		return this;
	}
	
	public void applyEffects(Entity target, Entity user){
		int t = 0;
		while(t < effects.size()){
			StatusEffect effect = effects.get(t).clone();
			effect.setCastPower(user.strength);
			effect.entity = target;
			target.addEffect(effect);
			++t;
		}
	}
}
