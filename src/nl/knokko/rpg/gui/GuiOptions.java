package nl.knokko.rpg.gui;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.utils.Resources;

public class GuiOptions extends Gui {

	public GuiOptions() {
		super(null);
		addButtons();
	}
	
	public static void saveOptionData(){
		try {
			PrintWriter writer = new PrintWriter(Resources.getSaveFile() + "/options.txt");
			writer.println("speed: " + MainClass.updateSpeed);
			writer.println("difficulty: " + MainClass.difficulty);
			writer.close();
		} catch (Exception e) {
			MainClass.console.println("GuiOptions.saveOptionData(): Failed to save the options:");
			MainClass.console.println();
			e.printStackTrace(MainClass.console);
			MainClass.console.println();
		}
	}
	
	public static void loadData(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Resources.getLoadFile() + "/options.txt"));
			MainClass.updateSpeed = Integer.decode(reader.readLine().substring(7));
			MainClass.difficulty = Integer.decode(reader.readLine().substring(12));
			reader.close();
		} catch(Exception ex){
			MainClass.console.println("failed to load options:");
			ex.printStackTrace(MainClass.console);
			MainClass.console.println();
		}
	}
	
	/*
	@Override
	public void paint(Graphics gr){
		gr.setColor(Color.BLUE);
		gr.fillRect(0, 0, MainClass.getWidth(), MainClass.getHeight());
		super.paint(gr);
		gr.setColor(Color.BLACK);
		gr.setFont(font());
		gr.drawString("speed: " + MainClass.updateSpeed, 220, 167);
		gr.drawString("difficulty: " + MainClass.difficulty, 220, 317);
		gr.drawString("max fps: " + MainClass.fpsFactor * 10, 220, 467);
		String dif = "very easy";
		if(MainClass.difficulty == 1)
			dif = "easy";
		if(MainClass.difficulty == 2)
			dif = "normal";
		if(MainClass.difficulty == 3)
			dif = "hard";
		if(MainClass.difficulty == 4)
			dif = "very hard";
		if(MainClass.difficulty >= 5)
			dif = "extreme";
		gr.drawString(dif, factorX(700), factorY(317));
	}
	*/
	
	@Override
	public void buttonClicked(Button button){
		if(button.name.matches("back")){
			MainClass.currentGUI = new GuiMenu();
			return;
		}
		if(button.id == 0){
			if(MainClass.updateSpeed > 1)
				--MainClass.updateSpeed;
		}
		else if(button.id == 1){
			if(MainClass.updateSpeed < 5)
				++MainClass.updateSpeed;
		}
		else if(button.id == 2){
			if(MainClass.difficulty > 1)
				--MainClass.difficulty;
		}
		else if(button.id == 3){
			if(MainClass.difficulty < 4)
				++MainClass.difficulty;
		}
		addButtons();
	}
	
	public void addButton(int minX, int minY, int maxX, int maxY, String text, int id){
		buttons.add(new Button(minX, minY, maxX, maxY, "sprites/buttons/button.png", text, normalFont, Color.BLACK, id));
	}
	
	public void addButtons(){
		buttons = new ArrayList<Button>();
		addButton(10, 100, 200, 200, "decrease", 0);
		addButton(400, 100, 575, 200, "increase", 1);
		addButton(10, 250, 200, 350, "decrease", 2);
		addButton(460, 250, 635, 350, "increase", 3);
		addButton(1200, 700, 1300, 800, "back");
	}
}
