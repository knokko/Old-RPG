package nl.knokko.rpg.items;

import java.awt.Point;

import nl.knokko.rpg.entities.model.ModelEquipment.ArmorType;

public class ItemBoots extends ItemArmor {

	ItemBoots(String itemName, int value, int armorValue, int magicArmor) {
		super(itemName, value, armorValue, magicArmor);
	}

	@Override
	public Point getRotationPoint() {
		return ArmorType.LEG.rotationPoint;
	}

}
