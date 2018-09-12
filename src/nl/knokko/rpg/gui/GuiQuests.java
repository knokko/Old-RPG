package nl.knokko.rpg.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.quests.Quest;
import nl.knokko.rpg.quests.QuestMap;

public class GuiQuests extends Gui {
	
	public final QuestMap quests;
	public int selectedQuest;
	
	public GuiQuests(Game theGame) {
		super(theGame, null);
		addButton(1400, 700, 1500, 800, "back");
		quests = game.quests;
	}
	
	@Override
	public void paint(Graphics gr){
		gr.setColor(Color.BLUE);
		gr.fillRect(0, 0, game.getWidth(), game.getHeight());
		super.paint(gr);
		gr.setFont(font());
		gr.setColor(new Color(200, 200, 0));
		gr.fillRect(0, 0, factorX(800), game.getHeight());
		gr.setColor(Color.BLACK);
		int t = 0;
		int y = 0;
		gr.drawLine(0, 0, 0, factorY(860));
		gr.drawLine(factorX(800), 0, factorX(800), factorY(860));
		while(y <= 860){
			gr.drawLine(0, y, factorX(800), y);
			y += factorY(50);
		}
		y = factorY(40);
		while(t < quests.quests.size()){
			quests.quests.get(t).paintSimple(gr, new Point(factorX(25), y));
			++t;
			y+= factorY(50);
		}
		Quest quest = null;
		if(selectedQuest < quests.quests.size()){
			quest = quests.quests.get(selectedQuest);
			if(quest != null)
				quest.paint(gr, new Point(factorX(820), factorY(40)));
		}
		gr.setColor(Color.RED);
		gr.drawRect(0, selectedQuest * factorY(50), factorX(800), factorY(50));
	}
	
	@Override
	public void buttonClicked(Button button){
		if(button.name.matches("back"))
			game.currentGUI = new GuiMenu(game);
	}
	
	@Override
	public void click(){
		super.click();
		Point mouse = game.getMousePosition();
		if(mouse.x <= factorX(800)){
			selectedQuest = mouse.y / factorY(50);
		}
	}
}
