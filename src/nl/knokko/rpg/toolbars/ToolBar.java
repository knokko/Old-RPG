package nl.knokko.rpg.toolbars;

import java.awt.*;
import java.util.ArrayList;

import nl.knokko.rpg.main.Game;

public class ToolBar {
	
	public static final Font font = new Font("TimesRoman", 0, 20);
	
	public final Game game;
	public Color color;
	public Color textColor;
	
	public ArrayList<String> text;
	public Point min;
	
	public int width;
	public int height;
	
	public ToolBar(Game theGame, Color fillColor, Color textcolor, String... strings) {
		text = new ArrayList<String>();
		game = theGame;
		color = fillColor;
		textColor = textcolor;
		int t = 0;
		while(t < strings.length){
			text.add(strings[t]);
			++t;
		}
	}
	
	public ToolBar(){
		this(new String[0]);
	}
	
	public ToolBar(String... text){
		this(Color.BLUE, text);
	}
	
	public ToolBar(Color color, String... text){
		this(Game.game, color, text);
	}
	
	public ToolBar(Game game, Color color, String... text){
		this(game, color, Color.BLACK, text);
	}
	
	public void paint(Graphics gr){
		gr.setColor(color);
		gr.setFont(font());
		gr.fillRect(min.x, min.y, width, height);
		gr.setColor(textColor);
		gr.drawRect(min.x, min.y, width, height);
		int x = min.x + (10);
		int y = min.y + (20);
		gr.setFont(font());
		int t = 0;
		while(t < text.size()){
			gr.drawString(text.get(t), x, y);
			++t;
			y += (20);
		}
	}
	
	public ToolBar setSize(int w, int h){
		width = (w);
		height = (h);
		return this;
	}
	
	public ToolBar increaseSize(int w, int h){
		width += (w);
		height += (h);
		return this;
	}
	
	public ToolBar increaseToSize(int w, int h){
		width = (w) > width ? (w) : width;
		height = (h) > height ? (h) : height;
		return this;
	}
	
	public Font font(){
		return new Font(font.getName(), font.getStyle(), (font.getSize()));
	}

	public void add(ToolBar toolBar) {
		if(toolBar.width > width)
			width = toolBar.width;
		height += toolBar.height;
		text.addAll(toolBar.text);
	}
}
