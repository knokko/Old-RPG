package nl.knokko.rpg.entities.players;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.effects.StatusEffect;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.entities.model.ModelEquipment;
import nl.knokko.rpg.inventory.Inventory;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.ItemArmor;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.spells.Spell;
import nl.knokko.rpg.spells.Spells;
import nl.knokko.rpg.utils.PointUtils;
import nl.knokko.rpg.utils.Random;
import nl.knokko.rpg.utils.Resources;
import nl.knokko.rpg.world.Conversation;
import nl.knokko.rpg.world.Portal;

public class Player extends Entity implements IPlayer {

	public int xp = 200;
	public int totalXp = 200;
	public Inventory inventory = new Inventory(15, 20);
	public ArrayList<Spell> learnedSpells = new ArrayList<Spell>();
	
	public byte safeSteps;
	public byte fightSteps = 61;
	public byte portalCooldown;
	public byte elementPoints = 50;
	
	public Player(Point point) {
		super(point);
		addHumanAI();
		weapon = new ItemStack(Items.woodAxe, 1);
		strength = 5;
		spirit = 5;
		currentHealth = maxHealth = 300;
		currentMana = maxMana = 200;
		inventory.money = 500;
	}

	@Override
	public String getTexture() {
		return "sprites/entities/player/" + getName();
	}
	
	public void saveData(){
		try {
			PrintWriter writer = new PrintWriter(Resources.getSaveFile() + "/" + getName().toLowerCase() + ".txt");
			writer.println("xp:" + xp);
			writer.println("totalxp:" + totalXp);
			writer.println("elementPoints:" + elementPoints);
			writer.println("strength:" + strength);
			writer.println("spirit:" + spirit);
			writer.println("maxhealth:" + maxHealth);
			writer.println("health:" + currentHealth);
			writer.println("maxmana:" + maxMana);
			writer.println("mana:" + currentMana);
			writer.println("x:" + position.x);
			writer.println("y:" + position.y);
			writer.println("movex: " + moveX);
			writer.println("movey: " + moveY);
			writer.println("rotation: " + rotation);
			elementStats.saveData(writer);
			writer.println("{");
			int counter = 0;
			while(counter < learnedSpells.size()){
				writer.println(learnedSpells.get(counter));
				++counter;
			}
			writer.println("}");
			counter = 0;
			while(counter < effects.size()){
				effects.get(counter).saveData(writer);
				++counter;
			}
			writer.close();
			writer = new PrintWriter(Resources.getSaveFile() + "/inventory " + getName().toLowerCase() + ".txt");
			inventory.saveData(writer);
			if(weapon != null){
				writer.println(weapon.size + "x" + weapon.item.id);
			}
			else {
				writer.println("empty");
			}
			int t = 0;
			while(t < armor.length){
				if(armor[t] != null){
					writer.println(armor[t].id);
				}
				else {
					writer.println("empty");
				}
				++t;
			}
			writer.close();
			if(getName().matches("Bart")){
				writer = new PrintWriter(Resources.getSaveFile() + "/players.txt");
				writer.println("player3: " + MainClass.player3);
				writer.close();
			}
		} catch (FileNotFoundException e) {
			MainClass.console.println("Player.saveData(): Failed to save the player data of " + getName() + ":");
			MainClass.console.println();
			e.printStackTrace(MainClass.console);
			MainClass.console.println();
		}
	}
	
	@Override
	public void saveBattleData(PrintWriter writer, int index){
		writer.println("player " + index + ": "+ getClass().getName() + "{");
		writer.println("x: " + battlePoint.x);
		writer.println("y: " + battlePoint.y);
		writer.println("walkprogress: " + walkProgress);
		writer.println("}");
	}
	
	@Override
	public void loadBattleData(BufferedReader reader){
		try {
			battlePoint = new Point(Integer.decode(reader.readLine().substring(3)), Integer.decode(reader.readLine().substring(3)));
			walkProgress = Byte.decode(reader.readLine().substring(14));
			reader.readLine();
		} catch (Exception e) {
			MainClass.console.println("failed to load player battle data:");
			e.printStackTrace(MainClass.console);
			MainClass.console.println();
		} 
	}
	
	public void loadData(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Resources.getLoadFile() + "/" + getName().toLowerCase() + ".txt"));
			xp = Integer.decode(reader.readLine().substring(3));
			totalXp = Integer.decode(reader.readLine().substring(8));
			elementPoints = Byte.decode(reader.readLine().substring(14));
			strength = Integer.decode(reader.readLine().substring(9));
			spirit = Integer.decode(reader.readLine().substring(7));
			maxHealth = Integer.decode(reader.readLine().substring(10));
			currentHealth = Integer.decode(reader.readLine().substring(7));
			maxMana = Integer.decode(reader.readLine().substring(8));
			currentMana = Integer.decode(reader.readLine().substring(5));
			position = new Point(Integer.decode(reader.readLine().substring(2)), Integer.decode(reader.readLine().substring(2)));
			moveX = Byte.decode(reader.readLine().substring(7));
			moveY = Byte.decode(reader.readLine().substring(7));
			rotation = Byte.decode(reader.readLine().substring(10));
			elementStats.loadData(reader);
			learnedSpells = new ArrayList<Spell>();
			if(learnedSpells.isEmpty()){
				reader.readLine();
				String string = reader.readLine();
				while(string != null && !string.contains("}")){
					learnedSpells.add(Spells.fromString(string));
					string = reader.readLine();
				}
				StatusEffect effect = StatusEffect.loadData(reader, this);
				while(effect != null){
					effects.add(effect);
					effect = StatusEffect.loadData(reader, this);
				}
			}
			reader.close();
			reader = new BufferedReader(new FileReader(Resources.getLoadFile() + "/inventory " + getName().toLowerCase() + ".txt"));
			inventory.loadData(reader);
			String line = reader.readLine();
			if(line == null || line.contains("empty")){
				weapon = null;
			}
			else {
				int lx = line.indexOf("x");
				int size = Integer.decode(line.substring(0, lx));
				int id = Integer.decode(line.substring(lx + 1));
				weapon = new ItemStack(Items.fromId(id), size);
			}
			int t = 0;
			line = reader.readLine();
			while(t < armor.length){
				try {
					armor[t] = (ItemArmor) Items.fromId(Integer.decode(line));
				} catch(Exception ex){}
				line = reader.readLine();
				++t;
			}
			reader.close();
			if(getName().matches("Bart")){
				reader = new BufferedReader(new FileReader(Resources.getLoadFile() + "/players.txt"));
				line = reader.readLine();
				if(line != null){
					String player3 = line.substring(9);
					try {
						Class<?> playerC = Class.forName("nl.knokko.rpg.entities.players." + player3);
						MainClass.player3 = (Player) playerC.getConstructor(Point.class).newInstance(new Point());
					} catch(Exception ex){
						MainClass.console.println("There is no player 3: " + ex.getLocalizedMessage());
						MainClass.console.println();
					}
				}
			}
		} catch(Exception ex){
			MainClass.console.println("Failed to load the player data:");
			ex.printStackTrace(MainClass.console);
			MainClass.console.println();
		}
	}
	
	@Override
	public void update(){
		super.update();
		updateTexture();
		if(portalCooldown > 0)
			--portalCooldown;
		if(MainClass.world != null && MainClass.world.map != null && getName().matches("Bart")){
			if(MainClass.world.map.getBackGroundColor() == null){
				if(position.x >= MainClass.world.map.minPoint().x + 810 && position.x <= MainClass.world.map.maxPoint().x - 810)
					MainClass.camera.x = position.x;
				if(position.y >= MainClass.world.map.minPoint().y + 450 && position.y <= MainClass.world.map.maxPoint().y - 450)
					MainClass.camera.y = position.y;
			}
			else {
				MainClass.camera = position.getLocation();
			}
		}
	}

	@Override
	public String getName() {
		return "Bart";
	}
	
	@Override
	public boolean move(int x, int y){
		ArrayList<Portal> portals = MainClass.world.portals;
		int t = 0;
		while(t < portals.size()){
			if(portals.get(t).collides(new Point(position.x + x, position.y + y))){
				return true;
			}
			++t;
		}
		t = 0;
		while(t < MainClass.world.conversations.size()){
			Conversation con = MainClass.world.conversations.get(t);
			if(position.equals(con.position)){
				MainClass.currentGUI = con.messages;
				MainClass.world.conversations.remove(t);
				return false;
			}
			++t;
		}
		if(super.move(x, y)){
			updateMap();
			if((Random.chance(50) && safeSteps <= 0) || fightSteps <= 0){
				MainClass.world.startRandomFight();
				safeSteps = 31;
				fightSteps = 121;
			}
			if(safeSteps > 0)
				--safeSteps;
			if(fightSteps > 0)
				--fightSteps;
			return true;
		}
		return false;
	}
	
	@Override
	public ArrayList<Spell> getSpells(){
		ArrayList<Spell> spells = new ArrayList<Spell>();
		int t = 0;
		while(t < learnedSpells.size()){
			Spell spell = learnedSpells.get(t).clone();
			spell.user = this;
			spell.position = battlePoint.getLocation();
			spell.setCastPower(spirit * getWeaponPower(new Attack(spell.getElement(), true, true)));
			spells.add(spell);
			++t;
		}
		return spells;
	}
	
	public ArrayList<Spell> getFreeSpells(){
		ArrayList<Spell> fspells = new ArrayList<Spell>();
		int t = 0;
		while(t < learnedSpells.size()){
			Spell spell = learnedSpells.get(t).clone();
			if(spell.isFreeSpell()){
				spell.user = this;
				spell.setCastPower(spirit * getWeaponPower(new Attack(spell.getElement(), true, true)));
				fspells.add(spell);
			}
			++t;
		}
		return fspells;
	}
	
	@Override
	public String toString(){
		return getName();
	}
	
	@Override
	public void paint(Graphics gr){
		updateHumanArmor();
		super.paint(gr);
		Point screen = PointUtils.gameToScreenPosition(position);
		if((rotation == 3 || rotation == 1) && getHelmet() == null){
			Image hair = null;
			if(getName().matches("Bart"))
				hair = Resources.getImage("sprites/entities/player/hair " + (rotation == 3 ? "front" : "behind") + ".png");
			if(getName().matches("Richard"))
				hair = Resources.getImage("sprites/entities/player/hair richard " + (rotation == 3 ? "front" : "behind") + ".png");
			gr.drawImage(hair, screen.x, screen.y, 30, 30, null);
		}
	}
	
	public void addXp(int Xp){
		xp += Xp;
		totalXp += Xp;
	}
	
	public void updateMap(){
		try {
			boolean[][] b = MainClass.world.map.exploredFields;
			int y = position.y - 450 + moveY;
			if(y < 0)
				y = 0;
			while(y <= position.y + 420 + moveY && y / 30 < b.length){
				int x = position.x - 810 + moveX;
				if(x < 0)
					x = 0;
				while(x <= position.x + 750 + moveX && x / 30 < b[y / 30].length){
					b[y / 30][x / 30] = true;
					x += 30;
				}
				y += 30;
			}
		} catch(NullPointerException ex){}
	}

	public boolean isPlayer() {
		return true;
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
	
	@Override
	public void updateHumanArmor(){
		if(getName().matches("Chompo"))
			return;
		super.updateHumanArmor();
		ModelEquipment head = (ModelEquipment) head();
		if(getName().matches("Bart") && armor[0] == null && (head.equipmentName == null || !head.equipmentName.matches("hair bart")))
			head.setEquipment("hair bart");
		else if(getName().matches("Richard") && armor[0] == null && (head.equipmentName == null || !head.equipmentName.matches("hair richard")))
			head.setEquipment("hair richard");
	}
	
	@Override
	protected void addBody(){
		addHumanBody();
	}
}
