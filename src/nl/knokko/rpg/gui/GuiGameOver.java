package nl.knokko.rpg.gui;

import java.awt.*;

import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.utils.JukeBox;
import nl.knokko.rpg.utils.SpecialText;

public class GuiGameOver extends Gui {
	
	public SpecialText title;
	public SpecialText text;
	
	public GuiGameOver(Game theGame) {
		super(theGame, "sprites/gui/game over.png");
		initText();
		addButton(690, 700, 910, 800, "quit game");
		JukeBox.stop();
	}
	
	@Override
	public void paint(Graphics gr){
		super.paint(gr);
		title.paint(gr, new Point(factorX(600), factorY(100)));
		text.paint(gr, new Point(factorX(480), factorY(250)));
	}
	
	@Override
	public void buttonClicked(Button button){
		if(button.name.matches("quit game"))
			game.close(false);
	}
	
	@Override
	public boolean canClose(){
		return false;
	}
	
	private void initText(){
		title = new SpecialText(Color.RED, new Font("TimesRoman", 1, factorX(60)), "Game Over");
		text = new SpecialText(Color.RED, new Font("TimesRoman", 0, factorX(30)),
		"All your characters are dead, the game is over.",
		"If you want to try it again, you restart the game.",
		"Than you can continue from where you saved for the last time,",
		"or the last time you entered a new map.");
	}
}
