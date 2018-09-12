package nl.knokko.rpg.entities;

import java.awt.Color;
import java.awt.Point;

import nl.knokko.rpg.entities.model.ModelEquipment;
import nl.knokko.rpg.entities.model.ModelEquipmentArm;
import nl.knokko.rpg.entities.model.ModelEquipmentLeg;
import nl.knokko.rpg.entities.players.Richard;
import nl.knokko.rpg.gui.GuiChat;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.ItemArmor;
import nl.knokko.rpg.items.ItemWeapon;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.quests.Quests;
import nl.knokko.rpg.quests.TalkQuest;
import nl.knokko.rpg.utils.Random;
import nl.knokko.rpg.utils.SpecialText;

public class Villager extends Entity {
	
	public SpecialText[] text;
	public GuiChat chat;
	public Point spawnPoint;
	
	public boolean canWalk;
	
	public Villager(Game app, Point spawn) {
		this(spawn, app);
	}
	
	public Villager(Point spawn, Game game, SpecialText... talk){
		this(spawn, game, true, talk);
	}
	
	public Villager(Point spawn, Game game, GuiChat talk){
		this(spawn, game, true, talk);
	}
	
	public Villager(Point spawn, Game game, boolean walk, SpecialText... talk){
		super(game, spawn);
		addHumanBody();
		addHumanAI();
		if(walk)
			rotation = Random.randomByte(3);
		else
			rotation = 3;
		updateTexture();
		text = talk;
		spawnPoint = spawn.getLocation();
		moveSpeed = 5;
		canWalk = walk;
	}
	
	public Villager(Point spawn, Game game, boolean walk, GuiChat Chat){
		this(spawn, game, walk);
		chat = Chat;
	}

	@Override
	public String getTexture() {
		return "sprites/entities/villager";
	}

	@Override
	public String getName() {
		return "villager";
	}
	
	public void interact(){
		if(text != null && text.length > 0)
			game.currentGUI = new GuiChat(game, true, text.clone()).setSpeaker(getName());
		else if(chat != null)
			game.currentGUI = chat.clone();
	}
	
	@Override
	public void update(){
		super.update();
		if(canWalk && Random.chance(20) && moveX == 0 && moveY == 0){
			Point target = position.getLocation();
			rotation = Random.randomByte(3);
			if(rotation == 0)
				target.x += 30;
			if(rotation == 1)
				target.y -= 30;
			if(rotation == 2)
				target.x -= 30;
			if(rotation == 3)
				target.y += 30;
			double distance = target.distance(spawnPoint);
			if(distance <= 120){
				move(target.x - position.x, target.y - position.y);
			}
		}
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
	
	public static Villager guard(Game game, Point spawn, boolean walk, ItemArmor[] armor, ItemWeapon weapon, SpecialText... text){
		Villager villager = new Villager(spawn, game, walk, text);
		villager.armor[0] = armor[0];
		villager.armor[1] = armor[1];
		villager.armor[2] = armor[2];
		villager.armor[3] = armor[3];
		villager.weapon = new ItemStack(weapon, 1);
		return villager;
	}
	
	public static Villager questMiner(Game game, Point spawn, boolean walk, ItemArmor[] armor, ItemWeapon weapon, GuiChat text){
		Miner miner = new Miner(spawn, game, walk, text){
			
			@Override
			public void interact(){
				super.interact();
				if(game.quests.hasQuest(Quests.miners())){
					TalkQuest q = (TalkQuest)game.quests.getQuest("miners");
					q.progress++;
					if(q.progress >= 3){
						q.complete();
						game.player3 = new Richard(game, new Point());
					}
					game.world.entities.remove(this);
				}
			}
		};
		miner.armor[0] = armor[0];
		miner.armor[1] = armor[1];
		miner.armor[2] = armor[2];
		miner.armor[3] = armor[3];
		miner.weapon = new ItemStack(weapon, 1);
		return miner;
	}
	
	public static class Miner extends Villager{
		
		public Miner(Game game, Point spawn){
			this(spawn, game);
		}
		
		public Miner(Point spawn, Game game, SpecialText... text){
			this(spawn, game, true, text);
		}
		
		public Miner(Point spawn, Game game, GuiChat talk){
			this(spawn, game, true, talk);
		}
		
		public Miner(Point spawn, Game game, boolean walk, SpecialText... talk) {
			super(spawn, game, walk, talk);
		}
		
		public Miner(Point spawn, Game game, boolean walk, GuiChat talk){
			super(spawn, game, walk, talk);
		}
		
		@Override
		public String getName(){
			return "miner";
		}
		
		@Override
		public String getTexture(){
			return "sprites/entities/miner";
		}
	}

	public static RuffVillager ruffShaman(Point spawn, Game game) {
		RuffVillager shaman = new RuffVillager(spawn, game){
			
			@Override
			public String getName(){
				return "Shaman";
			}
			
			@Override
			public String getTexture(){
				return "sprites/entities/ruff shaman";
			}
			
			@Override
			public void interact(){
				GuiChat chat1 = new GuiChat(game, true, new SpecialText(Color.GREEN, "Thank you for coming, I need your help.", "I am going to close the portal to the world of demons.", "But that isn't easy, I need some rare items."),
						new SpecialText(Color.GREEN, "One of the items I need is a demonic pearl.", "The recking is a demon that has a pearl.", "It lives in the deepest part of Miay Cave.", "Kill it and give me the pearl."));
				if(game.quests.hasQuest(Quests.shaman())){
					game.quests.getQuest("shaman").complete();
					game.currentGUI = chat1;
				}
				else if(game.quests.hasQuest(Quests.close_portal()))
					game.currentGUI = chat1;
				else
					game.currentGUI = new GuiChat(game, true, new SpecialText(Color.GREEN, "I love nature, but demons..."));
			}
		};
		shaman.weapon = new ItemStack(Items.miayRod);
		return shaman;
	}
	
	public static class RuffVillager extends Villager {

		public RuffVillager(Point spawn, Game game, SpecialText... talk) {
			super(spawn, game, talk);
		}

		@Override
		public String getTexture() {
			return "sprites/entities/ruff villager";
		}
	}
}
