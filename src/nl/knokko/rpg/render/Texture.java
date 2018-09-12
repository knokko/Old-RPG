package nl.knokko.rpg.render;

import java.awt.image.BufferedImage;

import nl.knokko.rpg.utils.Resources;

public class Texture {
	
	private BufferedImage original;
	private BufferedImage image;
	
	private int textureID;
	
	/**
	 * Create a new instance of Texture. This method should only be called from Resources.
	 * @param image The BufferedImage after ensuring the width and height are a power of two.
	 * @param original The BufferedImage before ensuring the width and height are a power of two.
	 */
	public Texture(BufferedImage image, BufferedImage original) {
		this.image = image;
		this.original = original;
		refresh();
	}
	
	@Override
	public boolean equals(Object other){
		if(other instanceof Texture){
			Texture texture = (Texture) other;
			return texture.image == image;
		}
		return false;
	}
	
	public void refresh(){
		textureID = Resources.createTextureID(image, image.getAlphaRaster() != null);
	}
	
	public BufferedImage getImage(){
		return image;
	}
	
	public int getID(){
		return textureID;
	}
	
	public int getOldWidth(){
		return original.getWidth();
	}
	
	public int getOldHeight(){
		return original.getHeight();
	}
	
	public int getWidth(){
		return image.getWidth();
	}
	
	public int getHeight(){
		return image.getHeight();
	}
}
