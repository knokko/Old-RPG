package nl.knokko.rpg.entities.model;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.items.ItemEquipment;
import nl.knokko.rpg.utils.Resources;

public class ModelEquipmentArm extends ModelEquipment {
	
	public String weaponName;

	public ModelEquipmentArm(String sprite, ItemEquipment equipment, ItemEquipment weapon, Point base, Point rotate, Entity owner, boolean isGhost, ArmorType type) {
		super(sprite, equipment, base, rotate, owner, isGhost, type);
		if(weapon != null){
			weaponName = weapon.name;
			setWeapon(weaponName);
		}
	}
	
	public ModelEquipmentArm(String sprite, ItemEquipment equipment, ItemEquipment weapon, Point base, Point rotate, Entity owner, ArmorType type){
		this(sprite, equipment, weapon, base, rotate, owner, false, type);
	}
	
	@Override
	public void setEquipment(String arms){
		setArmor(arms);
	}
	
	@Override
	public void clearEquipment(){
		equipmentName = null;
		weaponName = null;
		paintImage = Resources.copyImage(image);
		paintImageLeft = Resources.copyImage(imageLeft);
	}

	public ModelEquipmentArm(String sprite, Point base, Point rotate, Entity owner, ArmorType type) {
		this(sprite, null, null, base, rotate, owner, type);
	}
	
	public ModelEquipmentArm(String sprite, Point base, Point rotate, Entity entity, boolean isGhost, ArmorType type) {
		this(sprite, null, null, base, rotate, entity, isGhost, type);
	}

	public void setWeapon(String weapon){
		setEquipment(equipmentName, weapon);
	}
	
	public void setArmor(String armor){
		setEquipment(armor, weaponName);
	}
	
	public void clearWeapon(){
		setEquipment(equipmentName, null);
	}
	
	public void clearArmor(){
		setEquipment(null, weaponName);
	}
	
	public void setEquipment(String armorName, String weaponName){
		this.weaponName = weaponName;
		this.equipmentName = armorName;
		image = ghost ? Resources.getGhostImage(sprite) : Resources.getBuffImage(sprite);
		paintImage = Resources.copyImage(image);
		Graphics gr = paintImage.getGraphics();
		if(weaponName != null){
			char c1 = weaponName.contains("/") ? ' ' : '/';
			BufferedImage weapon = Resources.getBuffImage("sprites/equipment/" + weaponName + c1 + "forward.png");
			gr.drawImage(weapon, 0, 0, null);
		}
		if(equipmentName != null){
			char c = equipmentName.contains("/") ? ' ' : '/';
			BufferedImage arm = Resources.getBuffImage("sprites/equipment/" + equipmentName + c + "forward.png");
			gr.drawImage(arm, 0, 0, null);
		}
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-paintImage.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		paintImageLeft = op.filter(paintImage, null);
		refreshImages();
	}

}
