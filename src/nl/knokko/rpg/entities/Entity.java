package nl.knokko.rpg.entities;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.ElementStats;
import nl.knokko.rpg.effects.EffectDamage;
import nl.knokko.rpg.effects.StatusEffect;
import nl.knokko.rpg.entities.ai.EntityAI;
import nl.knokko.rpg.entities.ai.EntityAIMoveArms;
import nl.knokko.rpg.entities.ai.EntityAIMoveHead;
import nl.knokko.rpg.entities.ai.EntityAIMoveLegs;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.entities.model.ModelEquipment;
import nl.knokko.rpg.entities.model.ModelEquipmentArm;
import nl.knokko.rpg.entities.model.ModelEquipmentLeg;
import nl.knokko.rpg.entities.players.Player;
import nl.knokko.rpg.gui.GuiBattle;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.ItemArmor;
import nl.knokko.rpg.items.ItemChestplate;
import nl.knokko.rpg.items.ItemEquipment;
import nl.knokko.rpg.items.ItemWeapon;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.spells.Spell;
import nl.knokko.rpg.tiles.Tile;
import nl.knokko.rpg.utils.PointUtils;
import nl.knokko.rpg.utils.Resources;

public abstract class Entity {
	
	public final Game game;
	public Point position;
	public Point battlePoint = new Point();
	public Image image;
	public Random random;
	public final ElementStats elementStats;
	
	public ArrayList<StatusEffect> effects = new ArrayList<StatusEffect>();
	public ArrayList<Model> models = new ArrayList<Model>();
	public ArrayList<EntityAI> ai = new ArrayList<EntityAI>();
	
	public int spirit = 2;
	public int strength = 2;
	public int maxHealth = 200;
	public int maxMana = 100;
	public int currentHealth = maxHealth;
	public int currentMana = maxMana;
	public int level;
	public int extraX;
	public int extraY;
	public byte moveX;
	public byte moveY;
	public byte moveSpeed = 10;
	public byte walkProgress;
	public byte rotation;
	public ItemStack weapon;
	public ItemArmor[] armor = new ItemArmor[4];
	
	protected boolean isHuman;
	
	public Entity(Game app, Point spawn) {
		random = new Random();
		game = app;
		position = spawn;
		elementStats = new ElementStats();
		addBody();
		addAI();
	}
	
	public Entity(Game game, Point position, int level){
		this(game, position);
		this.level = level;
	}
	
	@Override
	public String toString(){
		return getName();
	}
	
	@Override
	public Entity clone() {
		try {
			Entity entity;
			if(this instanceof Player)
				entity = getClass().getConstructor(Game.class, Point.class).newInstance(game, position.getLocation());
			else
				entity = getClass().getConstructor(Game.class, Point.class, int.class).newInstance(game, position.getLocation(), level);
			entity.effects = cloneEffects();
			entity.armor = armor;
			return entity;
		} catch(Exception ex){
			Game.console.println("failed to clone Entity:");
			ex.printStackTrace(Game.console);
			Game.console.println();
			return null;
		}
	}
	
	public int getSize(){
		return 1;
	}
	
	public void paint(Graphics gr){
		updateAI();
		if(isHuman)
			updateHumanArmor();
		int t = 0;
		if(rotation == 0 || rotation == 2){
			while(t < models.size()){
				models.get(t).paint((Graphics2D) gr);
				++t;
			}
			return;
		}
		Point screen = PointUtils.gameToScreenPosition(position);
		updateTexture();
		gr.drawImage(image, screen.x, screen.y, 30, 30, null);
		if(rotation != 3){
			if(weapon != null){
				((ItemWeapon) weapon.item).drawOnEntity(gr, this, 30, 30);
			}
		}
		t = 0;
		while(t < armor.length){
			if(armor[t] != null){
				armor[t].drawOnEntity(gr, this, 30, 30);
			}
			++t;
		}
		if(rotation == 3){
			if(weapon != null){
				((ItemWeapon) weapon.item).drawOnEntity(gr, this, 30, 30);
			}
		}
	}
	
	public void paintInBattle(Graphics gr, boolean facingLeft){
		paintExtra(gr);
		updateAI();
		if(isHuman)
			updateHumanArmor();
		gr.setColor(Color.RED);
		gr.drawRect(battlePoint.x, battlePoint.y, 120 * getSize(), 120 * getSize());
		int t = 0;
		while(t < models.size()){
			models.get(t).paintInBattle((Graphics2D) gr, facingLeft);
			++t;
		}
		paintEffects(gr);
	}
	
	public boolean move(int x, int y){
		if(moveX == 0 && moveY == 0){
			Tile goal = game.world.getTile(position.x + x, position.y + y + moveY, true);
			Tile goal2 = game.world.getTile(position.x + x, position.y + y, false);
			if(x > 0){
				rotation = 0;
			}
			else if(x < 0){
				rotation = 2;
			}
			else if(y > 0){
				rotation = 3;
			}
			else if(y < 0){
				rotation = 1;
			}
			Point target = new Point(position.x + x, position.y + y);
			if(goal.walkOver(target, this) && goal2.collide(target, this)){
				int t = 0;
				while(t < game.world.entities.size()){
					Entity entity = game.world.entities.get(t);
					if(entity.position.equals(target))
							return false;
					++t;
				}
				if(game.player.position.equals(target))
					return false;
				moveX = (byte) x;
				moveY = (byte) y;
				return true;
			}
		}
		return false;
	}
	
	public void update(){
		if(moveX > 0){
			position.x += moveSpeed;
			moveX -= moveSpeed;
		}
		else if(moveX < 0){
			position.x -= moveSpeed;
			moveX += moveSpeed;
		}
		if(moveY > 0){
			position.y += moveSpeed;
			moveY -= moveSpeed;
		}
		else if(moveY < 0){
			position.y -= moveSpeed;
			moveY += moveSpeed;
		}
		if(moveX == 0 && moveY == 0)
			moveSpeed = (byte) (10 / game.fpsFactor);
	}
	
	public String getTexture(){
		return "sprites/entities/" + getName().toLowerCase();
	}

	public String getName(){
		return getClass().getSimpleName();
	}
	
	public int getArmor(Attack attack){
		int i = 1;
		int t = 0;
		while(t < armor.length){
			if(armor[t] != null){
				if(attack.magic){
					i += armor[t].getMagicValue();
				}
				else {
					i += armor[t].getArmorValue();
				}
			}
			++t;
		}
		return i;
	}
	
	public int getWeaponPower(Attack attack){
		if(weapon != null){
			if(attack.ranged){
				return ((ItemWeapon)weapon.item).power;
			}
			return ((ItemWeapon)weapon.item).sharpness;
		}
		return getPower();
	}
	
	public int getPower(){
		return 1;
	}
	
	public ArrayList<ItemStack> getLoot(){
		return new ArrayList<ItemStack>();
	}
	
	public int getLootMoney(){
		return 10;
	}
	
	public int getLootXp(){
		return 10;
	}
	
	public ArrayList<String> getSpecialLoot(){
		return new ArrayList<String>();
	}
	
	public boolean heal(int amount){
		if(currentHealth < maxHealth){
			currentHealth = Math.min(currentHealth + amount, maxHealth);
			return true;
		}
		return false;
	}
	
	public boolean healMana(int amount){
		if(currentMana < maxMana){
			currentMana = Math.min(currentMana + amount, maxMana);
			return true;
		}
		return false;
	}
	
	public void updateTexture(){
		if(rotation == 3){
			image = Resources.getImage(getTexture() + "_front.png");
		}
		if(rotation == 1){
			image = Resources.getImage(getTexture() + "_behind.png");
		}
	}
	
	public ItemArmor getHelmet(){
		return armor[0];
	}
	
	public ItemChestplate getChestPlate(){
		return (ItemChestplate) armor[1];
	}
	
	public ItemArmor getLeggings(){
		return armor[2];
	}
	
	public ItemArmor getBoots(){
		return armor[3];
	}
	
	protected void checkHealth(){
		if(currentHealth > maxHealth){
			currentHealth = maxHealth;
		}
		if(currentHealth <= 0){
			onDeath();
		}
	}
	
	public int getResistance(Element element){
		int resistance = elementStats.getResistance(element);
		if(weapon != null)
			resistance += ((ItemEquipment) weapon.item).getElementResistance(element);
		int t = 0;
		while(t < armor.length){
			if(armor[t] != null)
				resistance += armor[t].getElementResistance(element);
			++t;
		}
		return resistance;
	}
	
	public void attack(Attack attack, Entity attacker, int damage, GuiBattle battle){
		int t = 0;
		while(t < effects.size()){
			damage = effects.get(t).defence(attack, attacker, damage);
			++t;
		}
		damage -= getResistance(attack.element) * 0.01 * damage;
		damage /= getArmor(attack);
		currentHealth -= damage;
		if(battle != null)
			battle.effects.add(new EffectDamage(battle, battlePoint, attack.element, damage));
		checkHealth();
	}
	
	public int getPower(Element element){
		int power = elementStats.getPower(element);
		if(weapon != null){
			power += ((ItemEquipment) weapon.item).getElementPower(element);
		}
		int t = 0;
		while(t < armor.length){
			if(armor[t] != null)
				power += armor[t].getElementPower(element);
			++t;
		}
		return power;
	}
	
	public int getPower(Attack attack, Entity target){
		int wPower = getWeaponPower(attack);
		if(weapon != null)
			((ItemWeapon) weapon.item).applyEffects(target, this);
		int power = strength * wPower;
		power += getPower(attack.element) * 0.01 * power;
		int t = 0;
		while(t < effects.size()){
			power = effects.get(t).attack(attack, target, power);
			++t;
		}
		return power;
	}
	
	public Element getWeaponElement(){
		if(weapon != null)
			return weapon.item.element;
		return getElement();
	}
	
	public void applyDifficulty(){
		maxHealth *= game.difficulty;
		currentHealth *= game.difficulty;
		maxMana *= game.difficulty;
		currentMana *= game.difficulty;
		spirit *= game.difficulty;
		strength *= game.difficulty;
	}
	
	public void saveBattleData(PrintWriter writer, int index){
		writer.println("enemy " + index + ": "+ getClass().getName() + "{");
		writer.println("x: " + battlePoint.x);
		writer.println("y: " + battlePoint.y);
		writer.println("spirit: " + spirit);
		writer.println("strength: " + strength);
		writer.println("maxhealth: " + maxHealth);
		writer.println("maxmana: " + maxMana);
		writer.println("health: " + currentHealth);
		writer.println("mana: " + currentMana);
		writer.println("level: " + level);
		writer.println("walkprogress: " + walkProgress);
		if(weapon != null)
			writer.println(weapon.size + "x" + weapon.item.id);
		else
			writer.println("empty");
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
		elementStats.saveData(writer);
		t = 0;
		writer.println("{");
		while(t < effects.size()){
			effects.get(t).saveData(writer);
			++t;
		}
		writer.println("}");
	}
	
	public void loadBattleData(BufferedReader reader) throws NumberFormatException, IOException{
		battlePoint = new Point(Integer.decode(reader.readLine().substring(3)), Integer.decode(reader.readLine().substring(3)));
		spirit = Integer.decode(reader.readLine().substring(8));
		strength = Integer.decode(reader.readLine().substring(10));
		maxHealth = Integer.decode(reader.readLine().substring(11));
		maxMana = Integer.decode(reader.readLine().substring(9));
		currentHealth = Integer.decode(reader.readLine().substring(8));
		currentMana = Integer.decode(reader.readLine().substring(6));
		level = Integer.decode(reader.readLine().substring(7));
		walkProgress = Byte.decode(reader.readLine().substring(14));
		try {
			String weap = reader.readLine();
			int indx = weap.indexOf("x");
			weapon = new ItemStack(Items.fromId(Integer.decode(weap.substring(indx + 1))), Integer.decode(weap.substring(0, indx)));
		} catch(Exception ex){}
		int t = 0;
		while(t < 4){
			String line = reader.readLine();
			if(!line.contains("empty"))
				armor[t] = (ItemArmor) Items.fromId(Integer.decode(line));
			++t;
		}
		elementStats.loadData(reader);
		String line = reader.readLine();
		while(!line.contains("}") && line != null){
			StatusEffect effect = StatusEffect.loadData(reader, this);
			if(effect != null)
				effects.add(effect);
			else
				return;
		}
	}
	
	public Element getElement(){
		return Element.NORMAL;
	}
	
	public ArrayList<Spell> getSpells(){
		ArrayList<Spell> spells = new ArrayList<Spell>();
		return spells;
	}
	
	public Spell selectSpell(Entity target, GuiBattle battle){
		ArrayList<Spell> spells = getSpells();
		if(spells.size() > 0 && target != null){
			Spell best = null;
			Entity e = target.clone();
			Attack attack = new Attack(getWeaponElement(), false, false);
			int maxdamage = 0;
			if(canStrike()){
				e.attack(attack, this, getPower(attack, target), null);
				maxdamage = e.maxHealth - e.currentHealth;
			}
			int t = 0;
			while(t < spells.size()){
				Spell spell = spells.get(t);
				if(spell != null){
					e = target.clone();
					spell.target = e;
					spell.user = this;
					spell.attack();
					int damage = e.maxHealth - e.currentHealth;
					if(damage > maxdamage){
						best = spell.clone();
						maxdamage = damage;
					}
				}
				++t;
			}
			if(best != null)
				best.target = target;
			return best;
		}
		return null;
	}
	
	public void addEffect(StatusEffect effect){
		if(effect != null && !effects.contains(effect))
			effects.add(effect);
		else {
			int index = effects.indexOf(effect);
			effects.get(index).combine(effect);
		}
	}
	
	public boolean hasEffect(StatusEffect effect){
		int t = 0;
		while(t < effects.size()){
			if(effects.get(t).getName().matches(effect.getName()))
				return true;
			++t;
		}
		return false;
	}
	
	public void updateEffects(){
		int t = 0;
		while(t < effects.size()){
			effects.get(t).update();
			++t;
		}
	}
	
	public void onBattleEnd(){
		int t = 0;
		while(t < effects.size()){
			effects.get(t).onBattleEnd();
			++t;
		}
		effects = new ArrayList<StatusEffect>();
	}
	
	public boolean canStrike(){
		return true;
	}
	
	public void interact(){}
	
	public void onDeath(){
		currentHealth = 0;
		effects = new ArrayList<StatusEffect>();
	}
	
	public ArrayList<StatusEffect> cloneEffects(){
		ArrayList<StatusEffect> statusEffects = new ArrayList<StatusEffect>(effects.size());
		int t = 0;
		while(t < effects.size()){
			statusEffects.add(effects.get(t).clone());
			++t;
		}
		return statusEffects;
	}
	
	public void paintExtra(Graphics gr){
		int m = (int) (((double)currentMana / (double)maxMana) * 100 * getSize());
		int h = (int)(((double)currentHealth / (double)maxHealth) * 100 * getSize());
		gr.setColor(new Color(200, 0, 200));
		gr.fillRect((battlePoint.x), (battlePoint.y - 30 * getSize()), (m), (20 * getSize()));
		gr.setColor(Color.GREEN);
		gr.fillRect((battlePoint.x), (battlePoint.y - 60 * getSize()), (h), (20 * getSize()));
		gr.setColor(Color.RED);
		gr.fillRect((battlePoint.x + m), (battlePoint.y - 30 * getSize()), (getSize() * 100 - m), (20 * getSize()));
		gr.fillRect((battlePoint.x + h), (battlePoint.y - 60 * getSize()), (getSize() * 100 - h), (20 * getSize()));
		gr.setColor(Color.BLACK);
		gr.drawRect((battlePoint.x), (battlePoint.y - 30 * getSize()), (100 * getSize()), (20 * getSize()));
		gr.drawRect((battlePoint.x), (battlePoint.y - 60 * getSize()), (100 * getSize()), (20 * getSize()));
	}
	
	public void paintEffects(Graphics gr){
		int t = 0;
		while(t < effects.size()){
			effects.get(t).paint(gr, new Point(battlePoint.x + t * 30, battlePoint.y));
			++t;
		}
	}
	
	public void refreshModels(){
		int t = 0;
		while(t < models.size()){
			models.get(t).refreshImages();
			++t;
		}
	}
	
	protected void addWolfBody(){
		String n = getTexture().toLowerCase();
		models.add(new Model(n + "/leg.png", new Point(12, 18), new Point(2, 2), this));
		models.add(new Model(n + "/leg.png", new Point(22, 18), new Point(2, 2), this));
		models.add(new Model(n + "/tail.png", new Point(5, 12), new Point(8, 4), this));
		models.add(new Model(n + "/body.png", new Point(17, 12), new Point(8, 4), this));
		models.add(new Model(n + "/head.png", new Point(29, 13), new Point(1, 3), this));
		models.add(new Model(n + "/leg.png", new Point(12, 18), new Point(2, 2), this));
		models.add(new Model(n + "/leg.png", new Point(22, 18), new Point(2, 2), this));
	}
	
	protected void addWolfAI(){
		ai.add(new EntityAIMoveLegs(this, getModel("leg1"), getModel("leg3"), getModel("leg2"), getModel("leg4")));
		ai.add(new EntityAIMoveHead(this, getModel("tail")));
	}
	
	protected void addFuryBody(){
		String n = getTexture().toLowerCase();
		models.add(new Model(n + "/leg.png", new Point(14, 23), new Point(3, 1), this));
		models.add(new Model(n + "/arm.png", new Point(21, 11), new Point(1, 3), this));
		models.add(new Model(n + "/body.png", new Point(13, 9), new Point(5, 8), this));
		models.add(new Model(n + "/head.png", new Point(13, 0), new Point(4, 9), this));
		models.add(new Model(n + "/arm.png", new Point(21, 11), new Point(1, 3), this));
		models.add(new Model(n + "/leg.png", new Point(14, 23), new Point(3, 1), this));
	}
	
	protected void addHumanBody(){
		String n = getTexture().toLowerCase();
		models.add(new ModelEquipmentLeg(n + "/leg.png", new Point(15, 18), new Point(1, 1), this));
		models.add(new ModelEquipment(n + "/arm.png", new Point(17, -2), new Point(2, 12), this));
		models.add(new ModelEquipment(n + "/body.png", new Point(13, 6), new Point(4, 6), this));
		models.add(new ModelEquipment(n + "/head.png", new Point(13, 0), new Point(4, 5), this));
		models.add(new ModelEquipmentArm(n + "/arm.png", new Point(17, -2), new Point(2, 12), this));
		models.add(new ModelEquipmentLeg(n + "/leg.png", new Point(15, 18), new Point(1, 1), this));
		isHuman = true;
	}
	
	protected void addGhostBody(){
		String n = getTexture().toLowerCase();
		models.add(new ModelEquipmentLeg(n + "/leg.png", new Point(15, 18), new Point(1, 1), this, true));
		models.add(new ModelEquipment(n + "/arm.png", new Point(17, -2), new Point(2, 12), this, true));
		models.add(new ModelEquipment(n + "/body.png", new Point(13, 6), new Point(4, 6), this, true));
		models.add(new ModelEquipment(n + "/head.png", new Point(13, 0), new Point(4, 5), this, true));
		models.add(new ModelEquipmentArm(n + "/arm.png", new Point(17, -2), new Point(2, 12), this, true));
		models.add(new ModelEquipmentLeg(n + "/leg.png", new Point(15, 18), new Point(1, 1), this, true));
		isHuman = true;
	}
	
	protected void addHumanAI(){
		ai.add(new EntityAIMoveLegs(this, getModel("leg1"), getModel("leg2")));
		ai.add(new EntityAIMoveArms(this, getModel("arm1"), getModel("arm2")));
	}
	
	protected Model getModel(String model){
		try {
			return (Model) getClass().getMethod(model).invoke(this);
		} catch (Exception ex) {
			Game.console.println("Entity " + this + " doesn't have model " + model + "!");
			return null;
		} 
	}
	
	public void updateHumanArmor(){
		if(armor[0] != null && (((ModelEquipment)getModel("head")).equipmentName == null || !((ModelEquipment)getModel("head")).equipmentName.matches(armor[0].name)))
			((ModelEquipment) getModel("head")).setEquipment(armor[0].name);
		else if(armor[0] == null && ((ModelEquipment)getModel("head")).equipmentName != null)
			((ModelEquipment) getModel("head")).clearEquipment();
		if(armor[1] != null && (((ModelEquipment)getModel("body")).equipmentName == null || !((ModelEquipment)getModel("body")).equipmentName.matches(armor[1].name))){
			((ModelEquipment) getModel("body")).setEquipment(armor[1].name);
			((ModelEquipment) getModel("arm1")).setEquipment(armor[1].name + "/arm");
			((ModelEquipmentArm) getModel("arm2")).setArmor(armor[1].name + "/arm");
		}
		else if(armor[1] == null && ((ModelEquipment)getModel("body")).equipmentName != null){
			((ModelEquipment)getModel("body")).clearEquipment();
			((ModelEquipment)getModel("arm1")).clearEquipment();
			((ModelEquipmentArm) getModel("arm2")).clearArmor();
		}
		if(armor[2] != null && (((ModelEquipment)getModel("leg1")).equipmentName == null || !((ModelEquipment)getModel("leg1")).equipmentName.matches(armor[2].name))){
			((ModelEquipmentLeg) getModel("leg1")).setLegs(armor[2].name);
			((ModelEquipmentLeg) getModel("leg2")).setLegs(armor[2].name);
		}
		else if(armor[2] == null && ((ModelEquipment)getModel("leg1")).equipmentName != null){
			((ModelEquipmentLeg) getModel("leg1")).clearLegs();
			((ModelEquipmentLeg) getModel("leg2")).clearLegs();
		}
		if(armor[3] != null && (((ModelEquipmentLeg)getModel("leg1")).bootsName == null || !((ModelEquipmentLeg)getModel("leg1")).bootsName.matches(armor[3].name))){
			((ModelEquipmentLeg) getModel("leg1")).setBoots(armor[3].name);
			((ModelEquipmentLeg) getModel("leg2")).setBoots(armor[3].name);
		}
		else if(armor[3] == null && ((ModelEquipmentLeg)getModel("leg1")).bootsName != null){
			((ModelEquipmentLeg) getModel("leg1")).clearBoots();
			((ModelEquipmentLeg) getModel("leg2")).clearBoots();
		}
		if(weapon != null && (((ModelEquipment)getModel("arm2")).equipmentName == null || !((ModelEquipment)getModel("arm2")).equipmentName.matches(weapon.item.name)))
			((ModelEquipmentArm) getModel("arm2")).setWeapon(weapon.item.name);
		else if(weapon == null && ((ModelEquipment)getModel("arm2")).equipmentName != null)
			((ModelEquipmentArm) getModel("arm2")).clearWeapon();
	}
	
	public void updateAI(){
		int t = 0;
		while(t < ai.size()){
			ai.get(t).update();
			++t;
		}
	}
	
	protected void addBody(){}
	
	protected void addAI(){}
}
