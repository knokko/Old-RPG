package nl.knokko.rpg.effects;

import java.awt.*;
import java.io.*;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.toolbars.ToolBar;
import nl.knokko.rpg.utils.PointUtils;
import nl.knokko.rpg.utils.Resources;

public abstract class StatusEffect {
	
	public Entity entity;
	public Image image;
	
	public int power;
	
	public static StatusEffect loadData(BufferedReader reader, Entity entity) throws IOException{
		String line = reader.readLine();
		if(line != null && !line.contains("}")){
			int b1 = line.indexOf("[");
			if(b1 >= 0){
				StatusEffect effect = fromString(line.substring(0, b1), entity);
				effect.loadData(line.substring(b1 + 1));
				return effect;
			}
		}
		return null;
	}
	
	public static StatusEffect fromString(String string, Entity entity){
		try {
			return (StatusEffect) Class.forName("nl.knokko.rpg.effects.Effect" + string).getConstructor(Entity.class).newInstance(entity);
		} catch (Exception e) {
			e.printStackTrace(MainClass.console);
			return null;
		}
	}
	
	public StatusEffect(Entity target){
		this(target, 0);
	}
	
	public abstract boolean isPositive();
	
	public StatusEffect(Entity target, int castPower){
		power = castPower;
		entity = target;
		if(!(this instanceof EffectPoison))
			image = Resources.getImage("sprites/effects/" + getName() + ".png");
	}
	
	public void paint(Graphics gr, Point point){
		Point screen = PointUtils.factor(point);
		int height = (image.getHeight(null));
		int width = image.getWidth(null);
		gr.drawImage(image, screen.x, screen.y, (image.getWidth(null)), height, null);
		gr.setColor(Color.BLACK);
		gr.setFont(new Font("TimesRoman", 0, (width) / 2));
		gr.drawString(""  + power, screen.x, screen.y + height);
	}
	
	public void paint(Graphics gr, int x, int y, int width, int height){
		gr.drawImage(image, x, y, width, height, null);
		gr.setColor(Color.BLACK);
		gr.setFont(new Font("TimesRoman", 0, (width) / 2));
		gr.drawString(""  + power, x, y + height);
	}
	
	public String getName(){
		return getClass().getSimpleName().substring(6);
	}
	
	public void update(){}
	
	public int defence(Attack attack, Entity attacker, int damage){
		return damage;
	}
	
	public int attack(Attack attack, Entity target, int damage){
		return damage;
	}
	
	public void saveData(PrintWriter writer){
		writer.println(getName() + "[power:" + power + "]");
	}
	
	public void loadData(String line){
		power = Integer.decode(getString(line, "power"));
	}
	
	protected static String getString(String line, String key){
		int index = line.indexOf(key);
		if(index < 0)
			return null;
		int index2 = line.indexOf(",", index);
		if(index2 < 0)
			index2 = line.indexOf("]");
		return line.substring(index + key.length() + 1, index2);
	}
	
	@Override
	public abstract StatusEffect clone();
	
	@Override
	public boolean equals(Object other){
		if(other instanceof StatusEffect){
			StatusEffect effect = (StatusEffect) other;
			return effect.getName().matches(getName());
		}
		return false;
	}
	
	public void onBattleEnd(){}
	
	public void combine(StatusEffect other){
		power += other.power;
	}
	
	public void setCastPower(int power){
		this.power *= power;
	}
	
	public void addInfo(ToolBar toolbar){}
}
