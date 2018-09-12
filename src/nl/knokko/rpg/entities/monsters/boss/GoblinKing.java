package nl.knokko.rpg.entities.monsters.boss;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.monsters.GoblinWarrior;
import nl.knokko.rpg.gui.GuiBattle;
import nl.knokko.rpg.gui.GuiChat;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.spells.Spell;
import nl.knokko.rpg.spells.Spells;
import nl.knokko.rpg.utils.BackGrounds;
import nl.knokko.rpg.utils.SpecialText;

public class GoblinKing extends GoblinWarrior {

	public GoblinKing(Point position, int level) {
		super(position, level);
		rotation = 1;
		weapon = new ItemStack(Items.kingsSword);
	}
	
	@Override
	public String getName(){
		return "Goblin King";
	}
	
	@Override
	public String getTexture(){
		return "sprites/entities/goblin";
	}
	
	@Override
	public void update(){
		super.update();
		if(position.distance(MainClass.player.position) <= 90)
			MainClass.currentGUI = new Text();
	}
	
	@Override
	public void onDeath(){
		super.onDeath();
		MainClass.world.entities.remove(this);
		Bosses.bosses.remove("goblin king");
	}
	
	@Override
	public void interact(){
		MainClass.currentGUI = new Text();
	}
	
	@Override
	public ArrayList<ItemStack> getLoot(){
		ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
		loot.add(weapon);
		return loot;
	}
	
	@Override
	public ArrayList<Spell> getSpells(){
		ArrayList<Spell> spells = new ArrayList<Spell>();
		if(currentMana >= 10){
			spells.add(Spells.earthslash().setCastPower(strength * getWeaponPower(new Attack(Element.EARTH, false, false))));
			spells.add(Spells.poisonslash().setCastPower(strength * getWeaponPower(new Attack(Element.POISON, false, false))));
			spells.add(Spells.rockslash().setCastPower(strength * getWeaponPower(new Attack(Element.ROCK, false, false))));
		}
		return spells;
	}
	
	private static Color c(){
		return Element.EARTH.color;
	}
	
	private class Text extends GuiChat {

		public Text() {
			super(false, new SpecialText(c(), "So... you are those looters", "who are ruining our tomb.", "Why are you doing this?"), new SpecialText("We are looking for the spellbook of", "an old shamen who is barried here.", "We need that book to close the", "portal to the world of demons."), new SpecialText(c(), "Do you also have a good reason to slaughter", "all my goblins and loot every chest you see?"), new SpecialText("Your goblins always attack us first,", "we are just defending ourselves.", "And about the money, we don't know", "what is waiting for us at the portal.", "We may need good equipment to fight it."), new SpecialText(c(), "There are more ways to get money.", "But stealing ours is no option!", "Give all our treasures back and never return!"), new SpecialText("We can't just hand in everything we may need.", "There is more at stake than a goblin tomb."), new SpecialText(c(), "Enough guys, if they don't give it back...", "We will have to take it back!"));
			setSpeakers(new String[]{"Goblin King", "Bart", "Goblin King", "Chomper", "Goblin King", "Bart", "Goblin King"});
		}
		
		@Override
		public void finish(){
			MainClass.currentGUI = new GuiBattle(BackGrounds.goblin_tomb_2, MainClass.players(), new Entity[]{null, new GoblinWarrior(new Point(-10000, -10000), 45), GoblinKing.this}){
				
				@Override
				public void escapePressed(){
					super.escapePressed();
					if(MainClass.currentGUI == null){
						MainClass.player.position.x = 4590;
						MainClass.player.position.y = 3960;
					}
				}
			};
		}
	}
}
