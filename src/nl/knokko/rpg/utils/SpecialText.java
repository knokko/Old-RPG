package nl.knokko.rpg.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import nl.knokko.rpg.gui.Gui;

public class SpecialText {

	public String[] text;
	public Font font;
	public Color color;
	
	public SpecialText(Color Color, Font Font, String... theText){
		text = theText;
		color = Color;
		font = new Font(Font.getName(), Font.getStyle(), (Font.getSize()));
	}
	
	public SpecialText(Color color, String... text){
		this(color, Gui.normalFont, text);
	}
	
	public SpecialText(Font font, String... text){
		this(Color.BLACK, font, text);
	}
	
	public SpecialText(String... text){
		this(Color.BLACK, Gui.normalFont, text);
	}
	
	public void paint(Graphics gr, Point start){
		int t = 0;
		gr.setColor(color);
		gr.setFont(font);
		while(t < text.length){
			gr.drawString(text[t], start.x, start.y);
			start.y += font.getSize();
			++t;
		}
	}
	
	public SpecialText clone(){
		return new SpecialText(color, font, text);
	}
}
