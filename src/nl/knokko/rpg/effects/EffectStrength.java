package nl.knokko.rpg.effects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.PrintWriter;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.toolbars.ToolBar;
import nl.knokko.rpg.utils.Resources;

public class EffectStrength extends StatusEffect {
	
	public double multiplier;
	
	public EffectStrength(Entity target) {
		super(target);
	}

	public EffectStrength(Entity target, int extra, double mul) {
		super(target, extra);
		multiplier = mul;
		initImage();
	}
	
	@Override
	public int attack(Attack attack, Entity target, int damage){
		if(!attack.magic){
			damage += power;
			damage *= multiplier;
		}
		return damage;
	}
	
	@Override
	public void loadData(String line){
		power = Integer.decode(getString(line, "extra"));
		multiplier = Double.parseDouble(getString(line, "multiplier"));
		initImage();
	}
	
	@Override
	public void saveData(PrintWriter writer){
		writer.println(getName() + "[multiplier:" + multiplier + ",extra:" + power + "]");
	}
	
	@Override
	public void combine(StatusEffect other){
		super.combine(other);
		if(other instanceof EffectStrength)
			multiplier *= ((EffectStrength) other).multiplier;
		initImage();
	}
	
	@Override
	public EffectStrength clone(){
		return new EffectStrength(entity, power, multiplier);
	}
	
	@Override
	public boolean isPositive(){
		return multiplier > 1 || power > 0;
	}
	
	public void paint(Graphics gr, int x, int y, int width, int height){
		gr.drawImage(image, x, y, width, height, null);
		gr.setColor(Color.GREEN);
		gr.setFont(new Font("TimesRoman", 0, (width) / 4));
		String mul = "" + multiplier;
		int index = mul.indexOf(".");
		if(index + 3 <= mul.length())
			mul = mul.substring(0, index + 3);
		gr.drawString("+"  + power + " x" + mul, x, y + height);
	}
	
	protected void initImage(){
		if(isPositive())
			image = Resources.getImage("sprites/effects/strength.png");
		else
			image = Resources.getImage("sprites/effects/weakness.png");
	}
	
	@Override
	public void setCastPower(int power){
		this.power *= power / 10;
	}
	
	@Override
	public void addInfo(ToolBar toolbar){
		toolbar.text.add("extra damage: " + power);
		toolbar.text.add("damage multiplier: " + multiplier);
		toolbar.increaseSize(0, 40);
	}
}
