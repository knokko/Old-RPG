package nl.knokko.rpg.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.utils.Resources;

public class GuiOptions extends Gui {

	public GuiOptions(Game theGame) {
		super(theGame, null);
		addButtons();
	}
	
	public static void saveData(Game game){
		try {
			PrintWriter writer = new PrintWriter(Resources.getSaveFile() + "/options.txt");
			writer.println("speed: " + game.updateSpeed);
			writer.println("difficulty: " + game.difficulty);
			writer.println("fps factor: " + game.fpsFactor);
			writer.println("Do not edit the fps factor using this file! This can cause movement bugs!");
			writer.close();
		} catch (Exception e) {
			Game.console.println("GuiOptions.saveData(Game): Failed to save the options:");
			Game.console.println();
			e.printStackTrace(Game.console);
			Game.console.println();
		}
	}
	
	public static void loadData(Game game){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Resources.getLoadFile() + "/options.txt"));
			game.updateSpeed = Integer.decode(reader.readLine().substring(7));
			game.difficulty = Integer.decode(reader.readLine().substring(12));
			game.fpsFactor = Integer.decode(reader.readLine().substring(12));
			reader.close();
		} catch(Exception ex){
			Game.console.println("failed to load options:");
			ex.printStackTrace(Game.console);
			Game.console.println();
		}
	}
	
	@Override
	public void paint(Graphics gr){
		gr.setColor(Color.BLUE);
		gr.fillRect(0, 0, game.getWidth(), game.getHeight());
		super.paint(gr);
		gr.setColor(Color.BLACK);
		gr.setFont(font());
		gr.drawString("speed: " + game.updateSpeed, 220, 167);
		gr.drawString("difficulty: " + game.difficulty, 220, 317);
		gr.drawString("max fps: " + game.fpsFactor * 10, 220, 467);
		String dif = "very easy";
		if(game.difficulty == 1)
			dif = "easy";
		if(game.difficulty == 2)
			dif = "normal";
		if(game.difficulty == 3)
			dif = "hard";
		if(game.difficulty == 4)
			dif = "very hard";
		if(game.difficulty >= 5)
			dif = "extreme";
		gr.drawString(dif, factorX(700), factorY(317));
	}
	
	@Override
	public void buttonClicked(Button button){
		if(button.name.matches("back")){
			game.currentGUI = new GuiMenu(game);
			return;
		}
		if(button.id == 0){
			if(game.updateSpeed > 1)
				--game.updateSpeed;
		}
		else if(button.id == 1){
			if(game.updateSpeed < 5)
				++game.updateSpeed;
		}
		else if(button.id == 2){
			if(game.difficulty > 1)
				--game.difficulty;
		}
		else if(button.id == 3){
			if(game.difficulty < 4)
				++game.difficulty;
		}
		else if(button.id == 4){
			if(game.fpsFactor == 10)
				game.fpsFactor = 5;
			else if(game.fpsFactor > 1)
				--game.fpsFactor;
		}
		else if(button.id == 5){
			if(game.fpsFactor < 5)
				++game.fpsFactor;
			else if(game.fpsFactor == 5)
				game.fpsFactor = 10;
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
		addButton(10, 400, 200, 500, "decrease", 4);
		addButton(460, 400, 635, 500, "increase", 5);
		addButton(1200, 700, 1300, 800, "back");
	}
}
