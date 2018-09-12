package nl.knokko.rpg.gui;

import java.awt.*;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.main.*;
import nl.knokko.rpg.utils.Resources;

public class Gui {
	
	public static final Font normalFont = new Font("TimesRoman", Font.PLAIN, 50);
	
	public static Font font(){
		return new Font(normalFont.getName(), normalFont.getStyle(), factorX(normalFont.getSize()));
	}
	
	public ArrayList<Button> buttons = new ArrayList<Button>();
	public final Image image;
	
	public Gui(String texture) {
		if(texture != null)
			image = Resources.getImage(texture);
		else
			image = null;
	}
	
	public void click(Vector2f mouse){
		int t = 0;
		while(t < buttons.size()){
			Button button = buttons.get(t);
			if(button.isHit(mouse)){
				buttonClicked(button);
				return;
			}
			++t;
		}
	}
	
	public void buttonClicked(Button button){}
	public void rightClick(Vector2f mouse){}
	
	public void escapePressed(){
		MainClass.currentGUI = null;
	}
	
	public void addButton(int minX, int minY, int maxX, int maxY, String text){
		buttons.add(new Button(minX, minY, maxX, maxY, "sprites/buttons/button.png", text, normalFont, Color.BLACK));
	}
	
	public void render(){
		for(Button button : buttons)
			button.render();
	}
	
	public void update(){}
	public void keyPressed(){}
	public void keyTyped(char key){}
	
	public void drawEntity(Graphics gr, Entity player, Point point){
		int t = 0;
		while(t < player.models.size()){
			player.models.get(t).paintInBattle((Graphics2D) gr, false, point.x, point.y, 8, 8);
			++t;
		}
		player.paintEffects(gr);
	}
	
	public void saveData(){}
	
	public boolean canClose(){
		return true;
	}
	
	/**
	 * Deprecated method
	 * @param old
	 * @return old
	 */
	public static int factorX(int old){
		return old;
	}
	
	/**
	 * Deprecated method
	 * @param old
	 * @return old
	 */
	public static int factorY(int old){
		return old;
	}
	
	public Cursor getCursor(){
		return null;
	}
	
	public Color getBackGroundColor(){
		return Color.BLUE;
	}
}
