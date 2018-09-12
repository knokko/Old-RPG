package nl.knokko.rpg.gui;

import java.awt.*;

import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.utils.Resources;

public class Button {
	
	public int minX;
	public int minY;
	public int maxX;
	public int maxY;
	public short id;
	public Image image;
	public String name;
	public Color color;
	public Font font;
	
	public Button(int minx, int miny, int maxx, int maxy, String resourceLocation, String buttonName, Font drawFont, Color drawColor) {
		minX = (minx);
		minY = (miny);
		maxX = (maxx);
		maxY = (maxy);
		image = Resources.getImage(resourceLocation);
		name = buttonName;
		color = drawColor;
		font = new Font(drawFont.getName(), 0, -1);
		id = 0;
	}
	
	public Button(int minx, int miny, int maxx, int maxy, String resourceLocation, String buttonName, Font drawFont, Color drawColor, int buttonId){
		this(minx, miny, maxx, maxy, resourceLocation, buttonName, drawFont, drawColor);
		id = (short) buttonId;
	}
	
	public void paint(Graphics gr){
		if(font.getSize() == -1)
			initSize(gr);
		gr.setFont(font);
		int width = maxX - minX;
		int height = maxY - minY;
		gr.drawImage(image, minX, minY, width, height, null);
		gr.setColor(color);
		if(isHit(Game.game.getMousePosition())){
			gr.setColor(Color.YELLOW);
			gr.drawRect(minX, minY, width, height);
			int r = color.getRed();
			int g = color.getGreen();
			int b = color.getBlue();
			if(r > 205)
				r -= 50;
			else
				r += 50;
			if(g > 205)
				g -= 50;
			else
				g += 50;
			if(b > 205)
				b -= 50;
			else
				b += 50;
			gr.setColor(new Color(r, g, b));
		}
		gr.drawString(name, minX + width / 20, maxY - height / 3);
	}
	
	public boolean isHit(Point point){
		return point != null && point.x >= minX && point.x <= maxX && point.y >= minY && point.y <= maxY;
	}
	
	protected void initSize(Graphics gr){
		int size = 150;
		font = new Font(font.getFontName(), 0, size);
		gr.setFont(font);
		double width = font.getStringBounds(name, ((Graphics2D) gr).getFontRenderContext()).getWidth();
		double height = font.getStringBounds(name, ((Graphics2D) gr).getFontRenderContext()).getHeight();
		while(width >= (maxX - minX) * 0.9 || height >= (maxY - minY) * 0.9){
			--size;
			font = new Font(font.getFontName(), 0, size);
			gr.setFont(font);
			width = font.getStringBounds(name, ((Graphics2D) gr).getFontRenderContext()).getWidth();
			height = font.getStringBounds(name, ((Graphics2D) gr).getFontRenderContext()).getHeight();
		}
	}
}
