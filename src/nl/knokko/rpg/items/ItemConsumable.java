package nl.knokko.rpg.items;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.gui.GuiBattle;
import nl.knokko.rpg.inventory.ItemStack;

public abstract class ItemConsumable extends Item {

	ItemConsumable(String itemName, int moneyValue) {
		super(itemName, moneyValue);
	}
	
	public abstract boolean consume(Entity entity, ItemStack stack);
	public abstract boolean consume(Entity entity, GuiBattle battle, ItemStack stack);
}
