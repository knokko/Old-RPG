package nl.knokko.rpg.gui;

import java.awt.*;

import org.lwjgl.util.vector.Vector2f;

import nl.knokko.rpg.render.Renderer;
import nl.knokko.rpg.utils.Resources;

public class Button {
	public short id;
	public String name;
	public int texture;
	
	private Vector2f translation;
	private Vector2f scale;
	
	public Button(int minX, int minY, int maxX, int maxY, String resourceLocation, String buttonName, Font drawFont, Color drawColor) {
		texture = Resources.getButtonTexture(Color.MAGENTA, Color.BLACK, drawColor, buttonName);
		name = buttonName;
		id = 0;
		int midX = (minX + maxX) / 2;
		int midY = (minY + maxY) / 2;
		float relX = midX / 1600f;
		float relY = midY / 900f;
		translation = new Vector2f((relX - 0.5f) * 2, -(relY - 0.5f) * 2);
		int sizeX = maxX - minX;
		int sizeY = maxY - minY;
		scale = new Vector2f(sizeX / 1600f, sizeY / 900f);
	}
	
	public Button(int minx, int miny, int maxx, int maxy, String resourceLocation, String buttonName, Font drawFont, Color drawColor, int buttonId){
		this(minx, miny, maxx, maxy, resourceLocation, buttonName, drawFont, drawColor);
		id = (short) buttonId;
	}
	
	public void render(){
		Renderer.renderTexture(translation, scale, texture);
	}
	
	public boolean isHit(Vector2f point){
		return point != null && point.x >= minX() && point.x <= maxX() && point.y >= minY() && point.y <= maxY();
	}
	
	public float minX(){
		return translation.x - scale.x;
	}
	
	public float minY(){
		return translation.y - scale.y;
	}
	
	public float maxX(){
		return translation.x + scale.x;
	}
	
	public float maxY(){
		return translation.y + scale.y;
	}
	
	public float midX(){
		return translation.x;
	}
	
	public float midY(){
		return translation.y;
	}
}
