package nl.knokko.rpg.entities.monsters.boss;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.ai.EntityAIMoveArms;
import nl.knokko.rpg.entities.ai.EntityAIMoveLegs;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.gui.GuiBattle;
import nl.knokko.rpg.gui.GuiChat;
import nl.knokko.rpg.items.SpecialItems;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.spells.Spell;
import nl.knokko.rpg.spells.Spells;
import nl.knokko.rpg.utils.BackGrounds;
import nl.knokko.rpg.utils.SpecialText;

public class Recking extends Entity {

	public Recking(Game game, Point position, int level) {
		super(game, position, level);
		rotation = 3;
		maxHealth = currentHealth = level * 200;
		maxMana = currentMana = level * 30;
		strength = level * 6;
		spirit = level * 5;
		applyDifficulty();
		elementStats.setResistance(Element.DARK, 50);
		elementStats.setResistance(Element.LIGHT, -100);
		elementStats.setResistance(Element.POISON, 50);
	}
	
	@Override
	public void onDeath(){
		game.world.entities.remove(this);
	}
	
	@Override
	public void interact(){
		game.currentGUI = new Text();
	}
	
	@Override
	public int getPower(){
		return 20;
	}
	
	@Override
	public Element getElement(){
		return Element.DARK;
	}
	
	@Override
	public int getArmor(Attack attack){
		return attack.magic ? 3 : 4;
	}
	
	@Override
	public int getLootMoney(){
		return 150;
	}
	
	@Override
	public int getLootXp(){
		return 200;
	}
	
	@Override
	public ArrayList<String> getSpecialLoot(){
		ArrayList<String> items = new ArrayList<String>();
		items.add(SpecialItems.DEMONIC_PEARL);
		return items;
	}
	
	@Override
	public ArrayList<Spell> getSpells(){
		ArrayList<Spell> spells = super.getSpells();
		if(currentMana >= 10){
			spells.add(Spells.poisonslash().setCastPower(strength * getWeaponPower(new Attack(Element.POISON, false, false))));
			spells.add(Spells.darkvortex().setCastPower(spirit * getWeaponPower(new Attack(Element.DARK, true, true))));
		}
		return spells;
	}
	
	@Override
	public void update(){
		super.update();
		if(game.specialItems.hasItem(SpecialItems.DEMONIC_PEARL))
			game.world.entities.remove(this);
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
	protected void addBody(){
		String n = getTexture().toLowerCase();
		models.add(new Model(n + "/leg.png", new Point(13, 18), new Point(2, 2), this));
		models.add(new Model(n + "/arm.png", new Point(23, 10), new Point(2, 3), this));
		models.add(new Model(n + "/body.png", new Point(13, 9), new Point(6, 7), this));
		models.add(new Model(n + "/head.png", new Point(20, 3), new Point(4, 4), this));
		models.add(new Model(n + "/arm.png", new Point(23, 11), new Point(2, 3), this));
		models.add(new Model(n + "/leg.png", new Point(12, 18), new Point(2, 2), this));
	}
	
	@Override
	protected void addAI(){
		ai.add(new EntityAIMoveArms(this, arm1(), arm2()));
		ai.add(new EntityAIMoveLegs(this, leg1(), leg2()));
	}
	
	private class Text extends GuiChat {

		public Text() {
			super(Recking.this.game, false, false, new SpecialText[]{new SpecialText(Color.RED, "Nobody can enter my room unpunished.", "And especially not a human.", "You will pay for this..."), new SpecialText(Color.RED, "with your life!")});
			setSpeaker("Recking");
		}
		
		@Override
		public void finish(){
			game.currentGUI = new GuiBattle(game, BackGrounds.red_cave, game.players(), new Entity[]{null, Recking.this});
		}
	}
}
