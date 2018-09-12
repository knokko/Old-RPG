package nl.knokko.rpg.spells;

import java.awt.*;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.gui.GuiBattle;
import nl.knokko.rpg.toolbars.ToolBar;
import nl.knokko.rpg.utils.Resources;

public abstract class Spell {
	
	public Point position;
	public Entity user;
	public Entity target;
	
	public int castPower;
	public final int mana;
	public final String name;
	public GuiBattle battle;
	public ArrayList<String> text = new ArrayList<String>();
	
	public Image texture;
	
	public boolean disabled;
	
	public Spell(Point point, Entity caster, Entity victim, int power, int manaCost, String spellName) {
		position = point;
		user = caster;
		target = victim;
		castPower = power;
		name = spellName;
		mana = manaCost;
	}
	
	public abstract String getTexture();
	public abstract Element getElement();
	public abstract int getPower();
	
	public boolean isPositive(){
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Spell> T addText(String... text){
		int t = 0;
		while(t < text.length){
			this.text.add(text[t]);
			++t;
		}
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Spell> T setText(ArrayList<String> texts){
		text = texts;
		return (T) this;
	}
	
	public void paint(Graphics gr){
		texture = Resources.getImage(getTexture());
		gr.drawImage(texture, (position.x), (position.y), (texture.getWidth(null)), (texture.getHeight(null)), null);
	}
	
	public void update(){
		texture = Resources.getImage(getTexture());
	}
	
	public void attack(){}
	
	@Override
	public String toString(){
		return name;
	}
	
	@Override
	public boolean equals(Object other){
		return other instanceof Spell ? ((Spell)other).name.matches(name) : false;
	}
	
	public abstract Spell clone();
	
	public Spell setCastPower(int castpower){
		castPower = castpower;
		return this;
	}
	
	public void saveBattleData(PrintWriter writer, int index){
		if(!disabled){
			writer.println("spell " + index + ": " + this + "{");
			writer.println("x: " + position.x);
			writer.println("y: " + position.y);
			writer.println("castpower: " + castPower);
			writer.println("userindex: " + battle.getEntityIndex(user));
			writer.println("targetindex: " + battle.getEntityIndex(target));
			writer.println("}");
		}
	}
	
	public void loadBattleData(BufferedReader reader, GuiBattle battle) throws Exception{
		position = new Point(Integer.decode(reader.readLine().substring(3)), Integer.decode(reader.readLine().substring(3)));
		castPower = Integer.decode(reader.readLine().substring(11));
		user = battle.getEntity(Integer.decode(reader.readLine().substring(11)));
		target = battle.getEntity(Integer.decode(reader.readLine().substring(13)));
		this.battle = battle;
		reader.readLine();
	}
	
	public void setTarget(Entity Target){
		target = Target;
	}
	
	public void addInfo(ToolBar toolbar){
		int t = 0;
		toolbar.increaseSize(0, 80);
		toolbar.text.add(name.substring(0, 1).toUpperCase() + name.substring(1) + ":");
		toolbar.text.add("power: " + getPower());
		addMidInfo(toolbar);
		toolbar.text.add("element: " + getElement().name().toLowerCase());
		toolbar.text.add("");
		while(t < text.size()){
			toolbar.text.add(text.get(t));
			toolbar.increaseSize(0, 20);
			++t;
		}
		toolbar.text.add("This spell costs " + mana + " mana.");
	}
	
	protected void addMidInfo(ToolBar toolbar){}
	
	public boolean isFreeSpell(){
		return getPower() != 0;
	}
}
