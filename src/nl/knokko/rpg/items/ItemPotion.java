package nl.knokko.rpg.items;

import nl.knokko.rpg.effects.EffectHeal;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.gui.GuiBattle;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.toolbars.ToolBar;

public class ItemPotion extends ItemConsumable {
	
	public int recovering;
	
	ItemPotion(String itemName, int moneyValue, int potionPower) {
		super(itemName, moneyValue);
		recovering = potionPower;
	}

	@Override
	public boolean consume(Entity entity, ItemStack stack) {
		return entity.heal(recovering);
	}

	@Override
	public boolean consume(Entity entity, GuiBattle battle, ItemStack stack) {
		if(consume(entity, stack)){
			battle.effects.add(new EffectHeal(battle, entity.battlePoint.getLocation()));
			return true;
		}
		return false;
	}
	
	@Override
	public ToolBar getToolBar(){
		return new ToolBar(name, "Potion", "", "Price: " + value, "", "Restores " + recovering + " health.").setSize(200, 130);
	}
}
