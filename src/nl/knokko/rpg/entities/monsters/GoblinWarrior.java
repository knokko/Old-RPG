package nl.knokko.rpg.entities.monsters;

import java.awt.Point;

import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.Items;

public class GoblinWarrior extends Goblin {

	public GoblinWarrior(Point position, int level) {
		super(position, level);
		armor[0] = Items.bronzeHelmet;
		armor[1] = Items.bronzeChestplate;
		armor[2] = Items.bronzeLeggings;
		armor[3] = Items.bronzeBoots;
		weapon = new ItemStack(Items.bronzeSword);
	}
	
	@Override
	public String getName(){
		return "Goblin Warrior";
	}
	
	@Override
	public String getTexture(){
		return "sprites/entities/goblin";
	}
}
