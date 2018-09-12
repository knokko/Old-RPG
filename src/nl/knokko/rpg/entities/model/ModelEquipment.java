package nl.knokko.rpg.entities.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.items.ItemEquipment;
import nl.knokko.rpg.utils.PointUtils;
import nl.knokko.rpg.utils.Resources;

public class ModelEquipment extends Model {
	
	private byte ticks;
	
	public BufferedImage paintImage;
	public BufferedImage paintImageLeft;
	public String equipmentName;

	public ModelEquipment(String sprite, ItemEquipment equipment, Point base, Point rotate, Entity owner, boolean isGhost) {
		super(sprite, base, rotate, owner, isGhost);
		if(equipment != null){
			setEquipment(equipment.name);
			equipmentName = equipment.name;
		}
		else
			clearEquipment();
	}
	
	public ModelEquipment(String sprite, Point base, Point rotate, Entity owner){
		this(sprite, base, rotate, owner, false);
	}
	
	public ModelEquipment(String sprite, Point base, Point rotate, Entity owner, boolean isGhost){
		this(sprite, null, base, rotate, owner, isGhost);
	}
	
	public void setEquipment(String equipmentName){
		this.equipmentName = equipmentName;
		char c = equipmentName.contains("/") ? ' ' : '/';
		image = ghost ? Resources.getGhostImage(sprite) : Resources.getBuffImage(sprite);
		BufferedImage equipment = Resources.getBuffImage("sprites/equipment/" + equipmentName + c + "forward.png");
		paintImage = Resources.copyImage(image);
		Graphics gr = paintImage.getGraphics();
		gr.drawImage(equipment, 0, 0, null);
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-paintImage.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		paintImageLeft = op.filter(paintImage, null);
		refreshImages();
	}
	
	public void clearEquipment(){
		equipmentName = null;
		image = ghost? Resources.getGhostImage(sprite) : Resources.getBuffImage(sprite);
		paintImage = Resources.copyImage(image);
		paintImageLeft = Resources.copyImage(imageLeft);
		refreshImages();
	}
	
	@Override
	public void paintInBattle(Graphics2D gr, boolean left, int x, int y, int sx, int sy){
		Point screen = new Point(x + extraX() + sx + baseX(left) * sx - paintImage.getWidth() * (sx / 2), y + extraY() * sy + basePoint.y * sy);
		if(imageLeft2 == null || ticks == 0)
			imageLeft2 = scaleToSize(paintImageLeft, sx, sy);
		if(image2 == null || ticks == 0)
			image2 = scaleToSize(paintImage, sx, sy);
		if(rotation == 0){
			gr.drawImage(left ? imageLeft2 : image2, screen.x, screen.y, null);
			++ticks;
			return;
		}
		AffineTransform tx = new AffineTransform();
		setRotation(tx, sx, sy, left);
		AffineTransformOp op = new AffineTransformOp(tx, 2);
		gr.drawImage(left ? imageLeft2 : image2, op, screen.x, screen.y);
		++ticks;
	}
	
	@Override
	public void paint(Graphics2D gr){
		Point screen = PointUtils.gameToScreenPosition(new Point(entity.position.x + extraX() + baseX(entity.rotation == 2) - paintImage.getWidth() / 2, entity.position.y + extraY() + basePoint.y));
		AffineTransform tx = new AffineTransform();
		setRotation(tx, 1, 1, entity.rotation == 2);
		if(imageLeft2 == null || ticks == 0)
			imageLeft2 = Resources.copyImage(paintImageLeft);
		if(image2 == null || ticks == 0)
			image2 = Resources.copyImage(paintImage);
		AffineTransformOp op = new AffineTransformOp(tx, 2);
		gr.drawImage(entity.rotation == 2 ? imageLeft2 : image2, op, screen.x, screen.y);
		++ticks;
	}
	
	@Override
	public void setImage(String sprite){
		super.setImage(sprite);
		paintImage = Resources.copyImage(image);
		paintImageLeft = Resources.copyImage(imageLeft);
	}
}
