package nl.knokko.rpg.entities.monsters.boss;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.ai.EntityAIMoveArms;
import nl.knokko.rpg.entities.ai.EntityAIMoveHead;
import nl.knokko.rpg.entities.ai.EntityAIMoveLegs;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.gui.GuiBattle;
import nl.knokko.rpg.gui.GuiChat;
import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.spells.Spell;
import nl.knokko.rpg.spells.Spells;
import nl.knokko.rpg.utils.BackGrounds;
import nl.knokko.rpg.utils.SpecialText;

public class FireDragon extends Entity {

	public FireDragon(Point position, int level) {
		super(position, level);
		strength = level * 4;
		spirit = level * 3;
		currentHealth = maxHealth = level * 300;
		currentMana = maxMana = level * 50;
		applyDifficulty();
		rotation = 2;
		elementStats.setResistance(Element.FIRE, 50);
		elementStats.setResistance(Element.ROCK, -100);
		elementStats.setResistance(Element.WATER, -50);
		elementStats.setResistance(Element.AIR, 50);
	}
	
	@Override
	public int getPower(){
		return 30;
	}
	
	@Override
	public String getName(){
		return "Fire Dragon";
	}
	
	@Override
	public int getLootXp(){
		return level * 10;
	}
	
	@Override
	public int getLootMoney(){
		return level * 5;
	}
	
	@Override
	public int getArmor(Attack attack){
		return 3;
	}
	
	@Override
	public int getSize(){
		return 2;
	}
	
	@Override
	public ArrayList<Spell> getSpells(){
		ArrayList<Spell> spells = super.getSpells();
		if(currentMana >= 10){
			spells.add(Spells.airslash().setCastPower(strength * getWeaponPower(new Attack(Element.AIR, false, false))));
			spells.add(Spells.fireslash().setCastPower(strength * getWeaponPower(new Attack(Element.FIRE, false, false))));
			spells.add(Spells.fireball().setCastPower(spirit * getWeaponPower(new Attack(Element.FIRE, false, false))));
			spells.add(Spells.windshot().setCastPower(spirit * getWeaponPower(new Attack(Element.AIR, false, false))));
			spells.add(Spells.rock().setCastPower(spirit * getWeaponPower(new Attack(Element.ROCK, false, false))));
		}
		return spells;
	}
	
	@Override
	public void onDeath(){
		super.onDeath();
		Bosses.bosses.remove("fire dragon 1");
		MainClass.world.entities.remove(this);
	}
	
	@Override
	public void update(){
		if(position.distance(MainClass.player.position) <= 100)
			MainClass.currentGUI = new Text();
	}
	
	public Model wing1(){
		return models.get(0);
	}
	
	public Model leg1(){
		return models.get(1);
	}
	
	public Model leg2(){
		return models.get(2);
	}
	
	public Model body(){
		return models.get(3);
	}
	
	public Model head(){
		return models.get(4);
	}
	
	public Model leg3(){
		return models.get(5);
	}
	
	public Model leg4(){
		return models.get(6);
	}
	
	public Model wing2(){
		return models.get(7);
	}
	
	@Override
	protected void addBody(){
		String n = getTexture().toLowerCase();
		models.add(new Model(n + "/wing.png", new Point(27, 18), new Point(29, 11), this));
		models.add(new Model(n + "/leg.png", new Point(24, 33), new Point(3, 2), this));
		models.add(new Model(n + "/leg.png", new Point(44, 33), new Point(3, 2), this));
		models.add(new Model(n + "/body.png", new Point(27, 25), new Point(24, 7), this));
		models.add(new Model(n + "/head.png", new Point(54, 21), new Point(2, 10), this));
		models.add(new Model(n + "/leg.png", new Point(22, 33), new Point(3, 2), this));
		models.add(new Model(n + "/leg.png", new Point(42, 33), new Point(3, 2), this));
		models.add(new Model(n + "/wing.png", new Point(28, 18), new Point(29, 11), this));
	}
	
	@Override
	protected void addAI(){
		ai.add(new EntityAIMoveLegs(this, leg1(), leg3(), leg2(), leg4()));
		ai.add(new EntityAIMoveArms(this, wing1(), wing2()));
		ai.add(new EntityAIMoveHead(this, head()));
	}
	
	private class Text extends GuiChat {

		private Text() {
			super(false, new SpecialText(new Color(250, 125, 0), "You have come too far in my cave."), new SpecialText("This is not your cave.", "This cave doesn't have an owner."), new SpecialText(new Color(250, 125, 0), "I am the guardian of the treasures of this cave.", "I will protect it with my life.", "You shall not pass."), new SpecialText("Well...", "That sounds like a waste of your life.", "Show me what you can do!"));
			setSpeakers(new String[]{"Dragon", "Bart", "Dragon", "Chomper"});
		}
		
		@Override
		public void finish(){
			MainClass.currentGUI = new GuiBattle(BackGrounds.red_cave, MainClass.players(), new Entity[]{null, FireDragon.this}){
				
				@Override
				public void escapePressed(){
					super.escapePressed();
					if(MainClass.currentGUI == null){
						MainClass.player.position.x = 1830;
						MainClass.player.position.y = 3390;
					}
				}
			};
		}
		
	}
}
