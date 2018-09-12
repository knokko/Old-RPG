package nl.knokko.rpg.entities.model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import org.lwjgl.util.vector.Vector2f;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.render.Renderer;
import nl.knokko.rpg.render.Texture;
import nl.knokko.rpg.utils.PointUtils;
import nl.knokko.rpg.utils.Resources;

public class Model {

	public BufferedImage image;
	public BufferedImage image2;
	public BufferedImage imageLeft;
	public BufferedImage imageLeft2;
	public Point rotationPoint;
	public Point basePoint;
	public Entity entity;
	public String name;
	public String sprite;
	
	public double prevZoom;
	public float rotation;
	
	public boolean ghost;
	
	protected Texture texture;
	protected Texture textureLeft;
	
	public Model(String sprite, Point base, Point rotate, Entity owner, boolean isGhost) {
		this.sprite = sprite;
		ghost = isGhost;
		rotationPoint = rotate;
		entity = owner;
		basePoint = new Point(base.x, base.y);
		name = sprite.substring(sprite.lastIndexOf("/") + 1, sprite.length() - 4);
		image = ghost ? Resources.getGhostImage(sprite) : Resources.getBuffImage(sprite);
		texture = Resources.getRotateableTexture(sprite, rotate);
		textureLeft = Resources.getFlippedRotateableTexture(sprite, rotate);
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-image.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		imageLeft = op.filter(image, null);
	}
	
	public Model(String sprite, Point base, Point rotate, Entity owner){
		this(sprite, base, rotate, owner, false);
	}
	
	public void paintInBattle(Graphics2D gr, boolean left){
		paintInBattle(gr, left, entity.battlePoint.x, entity.battlePoint.y, 4, 4);
	}
	
	public void paintInBattle(Graphics2D gr, boolean left, int x, int y, int sx, int sy){
		Point screen = new Point(x + extraX() * sx + baseX(left) * sx - image.getWidth() * (sx / 2), y + basePoint.y * sy + extraY() * sy);
		if(imageLeft2 == null)
			imageLeft2 = scaleToSize(imageLeft, sx, sy);
		if(image2 == null)
			image2 = scaleToSize(image, sx, sy);
		if(rotation == 0){
			gr.drawImage(left ? imageLeft2 : image2, screen.x, screen.y, null);
			return;
		}
		AffineTransform tx = new AffineTransform();
		AffineTransformOp op = new AffineTransformOp(tx, 2);
		gr.drawImage(left ? imageLeft2 : image2, op, screen.x, screen.y);
	}
	
	public void paint(Graphics2D gr){
		Point screen = PointUtils.gameToScreenPosition(new Point(entity.position.x + extraX() + baseX(entity.rotation == 2) - image.getWidth() / 2, entity.position.y + basePoint.y + extraY()));
		AffineTransform tx = new AffineTransform();
		if(imageLeft2 == null)
			imageLeft2 = Resources.copyImage(imageLeft);
		if(image2 == null)
			image2 = Resources.copyImage(image);
		AffineTransformOp op = new AffineTransformOp(tx, 2);
		gr.drawImage(entity.rotation == 2 ? imageLeft2 : image2, op, screen.x, screen.y);
	}
	
	public void render(){
		Vector2f screen = PointUtils.worldPointToScreenVector(new Point(entity.position.x + 15 + baseX(entity.rotation == 2), entity.position.y + 15 - basePoint.y));
		render(screen);
	}
	
	protected void render(Vector2f screen){
		renderTexture(screen, texture, textureLeft);
	}
	
	protected void renderTexture(Vector2f screen, Texture texture, Texture textureLeft){
		Renderer.renderRotatedTexture(screen, new Vector2f(texture.getOldWidth() / 1600f, texture.getOldHeight() / 900f), rotation, entity.rotation == 2 ? textureLeft.getID() : texture.getID());
	}
	
	public int baseX(boolean left){
		return left ? -basePoint.x : basePoint.x;
	}
	
	public int rotatePointX(boolean left){
		return left ? image.getWidth() - rotationPoint.x : rotationPoint.x;
	}
	
	public void setImage(String sprite){
		name = sprite.substring(sprite.lastIndexOf("/") + 1, sprite.length() - 4);
		image = ghost ? Resources.getGhostImage(sprite) : Resources.getBuffImage(sprite);
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-image.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		imageLeft = op.filter(image, null);
	}
	
	public static BufferedImage scaleToSize(BufferedImage uploadImage, double x, double y){
		  AffineTransform atx = new AffineTransform();
		  atx.scale(x, y);
		  try {
			  AffineTransformOp afop = new AffineTransformOp(atx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			  uploadImage = afop.filter(uploadImage, null);
		  } catch(Exception ex){
			  System.out.println("determinant = " + atx.getDeterminant() + " x = " + x + " y = " + y);
		  }
		  return uploadImage;
	}
	
	public int extraX(){
		return entity.extraX;
	}
	
	public int extraY(){
		return entity.extraY;
	}
	
	public void refreshImages(){
		if(ghost)
			setImage(sprite);
		image2 = null;
		imageLeft2 = null;
	}
}
