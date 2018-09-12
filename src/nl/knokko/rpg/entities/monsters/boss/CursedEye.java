package nl.knokko.rpg.entities.monsters.boss;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.entities.model.ModelEquipment;
import nl.knokko.rpg.entities.model.ModelEquipmentArm;
import nl.knokko.rpg.gui.GuiBattle;
import nl.knokko.rpg.gui.GuiChat;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.items.SpecialItems;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.spells.Spell;
import nl.knokko.rpg.spells.Spells;
import nl.knokko.rpg.utils.BackGrounds;
import nl.knokko.rpg.utils.SpecialText;

public class CursedEye extends Entity {

	public CursedEye(Game game, Point position, int level) {
		super(game, position, level);
		rotation = 3;
		maxHealth = currentHealth = level * 300;
		maxMana = currentMana = level * 50;
		strength = level;
		spirit = level;
		addHumanAI();
		applyDifficulty();
		elementStats.setResistance(Element.DARK, 75);
		elementStats.setResistance(Element.LIGHT, -100);
		weapon = new ItemStack(Items.cystScepter);
	}
	
	@Override
	public String getName(){
		return "Cursed Eye";
	}
	
	@Override
	public void update(){
		super.update();
		if(game.specialItems.hasItem(SpecialItems.CURSED_EYE))
			game.world.entities.remove(this);
	}
	
	@Override
	public void onDeath(){
		game.world.entities.remove(this);
	}
	
	@Override
	public void interact(){
		game.currentGUI = new Text(game, this);
	}
	
	@Override
	public Element getElement(){
		return Element.DARK;
	}
	
	@Override
	public int getArmor(Attack attack){
		return attack.magic ? 10 : 8;
	}
	
	@Override
	public int getLootMoney(){
		return 15 * level;
	}
	
	@Override
	public int getLootXp(){
		return 20 * level;
	}
	
	@Override
	public ArrayList<String> getSpecialLoot(){
		ArrayList<String> items = new ArrayList<String>();
		items.add(SpecialItems.CURSED_EYE);
		return items;
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
			spells.add(Spells.darkvortex().setCastPower(spirit * getWeaponPower(new Attack(Element.DARK, true, true))));
			spells.add(Spells.lightvortex().setCastPower(spirit * getWeaponPower(new Attack(Element.LIGHT, true, true))));
			spells.add(Spells.lightslash().setCastPower(strength * getWeaponPower(new Attack(Element.LIGHT, false, false))));
		}
		return spells;
	}
	
	@Override
	protected void addBody(){
		String n = getTexture().toLowerCase();
		models.add(new Model(n + "/leg.png", new Point(14, 12), new Point(1, 1), this));
		models.add(new Model(n + "/arm.png", new Point(19, -2), new Point(2, 12), this));
		models.add(new Model(n + "/eye.png", new Point(13, 2), new Point(6, 6), this));
		models.add(new ModelEquipmentArm(n + "/arm.png", new Point(19, -2), new Point(2, 12), this));
		models.add(new Model(n + "/leg.png", new Point(14, 12), new Point(1, 1), this));
		isHuman = true;
	}
	
	@Override
	public void updateHumanArmor(){
		if(weapon != null && (((ModelEquipment)getModel("arm2")).equipmentName == null || !((ModelEquipment)getModel("arm2")).equipmentName.matches(weapon.item.name)))
			((ModelEquipmentArm) getModel("arm2")).setWeapon(weapon.item.name);
		else if(weapon == null && ((ModelEquipment)getModel("arm2")).equipmentName != null)
			((ModelEquipmentArm) getModel("arm2")).clearWeapon();
	}
	
	public Model leg1(){
		return models.get(0);
	}
	
	public Model arm1(){
		return models.get(1);
	}
	
	public Model eye(){
		return models.get(2);
	}
	
	public ModelEquipmentArm arm2(){
		return (ModelEquipmentArm) models.get(3);
	}
	
	public Model leg2(){
		return models.get(4);
	}
	
	private static class Text extends GuiChat {
		
		public CursedEye eye;
		
		public static Color color(){
			return new Color(100, 0, 100);
		}

		public Text(Game game, CursedEye darkeye) {
			super(game, false, false, new SpecialText[]{new SpecialText(color(), "Humans don't belong in my dimension", "I guess you are coming for my eye."), new SpecialText(color(), "We won't allow you to close the portals.", "You don't know what you are fighting against.", "Your dimension will be ours soon.", "There will be no place for humans anymore."), new SpecialText(color(), "You are not the only humans who will die, ", "you are just the first.")});
			setSpeaker("Cursed Eye");
			eye = darkeye;
		}
		
		@Override
		public void finish(){
			game.currentGUI = new GuiBattle(game, BackGrounds.cyst, game.players(), new Entity[]{null, eye});
		}
	}
}
