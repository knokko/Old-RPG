package nl.knokko.rpg.items;

import java.awt.Point;

import nl.knokko.rpg.entities.model.ModelEquipment.ArmorType;
import nl.knokko.rpg.render.Texture;
import nl.knokko.rpg.utils.Resources;

public class ItemChestplate extends ItemArmor {
	
	private Texture rotateableArmTexture;
	private Texture rotateableArmTextureLeft;
	
	ItemChestplate(String itemName, int value, int armor, int magicArmor) {
		super(itemName, value, armor, magicArmor);
		rotateableArmTexture = Resources.getRotateableTexture("sprites/equipment/" + itemName + "/arm forward.png", getRotationPoint());
		rotateableArmTextureLeft = Resources.getFlippedRotateableTexture("sprites/equipment/" + itemName + "/arm forward.png", getRotationPoint());
	}

	@Override
	public Point getRotationPoint() {
		return ArmorType.CHESTPLATE.rotationPoint;
	}
	
	public Texture getRotateableArmTexture(boolean left){
		return left ? rotateableArmTextureLeft : rotateableArmTexture;
	}
}
