package nl.knokko.rpg.entities.model;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.items.ItemEquipment;
import nl.knokko.rpg.utils.Resources;

public class ModelEquipmentLeg extends ModelEquipment {
	
	public String bootsName;

	public ModelEquipmentLeg(String sprite, ItemEquipment legs, ItemEquipment boots, Point base, Point rotate, Entity owner, boolean isGhost, ArmorType type) {
		super(sprite, legs, base, rotate, owner, isGhost, type);
		if(boots != null){
			bootsName = boots.name;
			setBoots(bootsName);
		}
	}
	
	public ModelEquipmentLeg(String sprite, ItemEquipment legs, ItemEquipment boots, Point base, Point rotate, Entity owner, ArmorType type){
		this(sprite, legs, boots, base, rotate, owner, false, type);
	}

	public ModelEquipmentLeg(String sprite, Point base, Point rotate, Entity owner, ArmorType type) {
		this(sprite, null, null, base, rotate, owner, type);
	}
	
	public ModelEquipmentLeg(String sprite, Point base, Point rotate, Entity owner, boolean isGhost, ArmorType type) {
		this(sprite, null, null, base, rotate, owner, isGhost, type);
	}

	public void setLegs(String legs){
		setEquipment(legs, bootsName);
	}
	
	public void setBoots(String boots){
		setEquipment(equipmentName, boots);
	}
	
	public void clearLegs(){
		setEquipment(null, bootsName);
	}
	
	public void clearBoots(){
		setEquipment(equipmentName, null);
	}
	
	public void setEquipment(String legsName, String bootsName){
		this.bootsName = bootsName;
		this.equipmentName = legsName;
		image = ghost ? Resources.getGhostImage(sprite) : Resources.getBuffImage(sprite);
		paintImage = Resources.copyImage(image);
		Graphics gr = paintImage.getGraphics();
		if(bootsName != null){
			char c1 = bootsName.contains("/") ? ' ' : '/';
			BufferedImage boots = Resources.getBuffImage("sprites/equipment/" + bootsName + c1 + "forward.png");
			gr.drawImage(boots, 0, 0, null);
		}
		if(equipmentName != null){
			char c = equipmentName.contains("/") ? ' ' : '/';
			BufferedImage leg = Resources.getBuffImage("sprites/equipment/" + equipmentName + c + "forward.png");
			gr.drawImage(leg, 0, 0, null);
		}
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-paintImage.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		paintImageLeft = op.filter(paintImage, null);
		refreshImages();
	}
	
	@Override
	public void setEquipment(String legs){
		setLegs(legs);
	}
	
	@Override
	public void clearEquipment(){
		equipmentName = null;
		bootsName = null;
		paintImage = Resources.copyImage(image);
		paintImageLeft = Resources.copyImage(imageLeft);
	}

}
