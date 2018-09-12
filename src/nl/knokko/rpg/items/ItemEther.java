package nl.knokko.rpg.items;

import nl.knokko.rpg.effects.EffectManaHeal;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.gui.GuiBattle;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.toolbars.ToolBar;

public class ItemEther extends ItemConsumable {
	
	public final int recovering;
	
	ItemEther(String itemName, int moneyValue, int manaHeal) {
		super(itemName, moneyValue);
		recovering = manaHeal;
	}

	@Override
	public boolean consume(Entity entity, ItemStack stack) {
		return entity.healMana(recovering);
	}

	@Override
	public boolean consume(Entity entity, GuiBattle battle, ItemStack stack) {
		battle.effects.add(new EffectManaHeal(battle, entity.battlePoint.getLocation()));
		return consume(entity, stack);
	}
	
	@Override
	public ToolBar getToolBar(){
		return new ToolBar(name, "Potion", "", "Price: " + value, "", "Restores " + recovering + " mana.").setSize(200, 130);
	}
}
