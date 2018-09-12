package nl.knokko.rpg.gui;

import java.awt.*;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.players.Player;
import nl.knokko.rpg.main.Game;

public class GuiCharacterInfo extends Gui {
	
	public final Player player;
	
	public GuiCharacterInfo(Game theGame, Player owner) {
		super(theGame, null);
		addButton(1200, 200, 1320, 300, "back");
		addButton(1200, 350, 1450, 450, "next player");
		addButton(650, 50, 950, 150, "character info");
		player = owner;
	}
	
	@Override
	public void buttonClicked(Button button){
		if(button.name.matches("back")){
			game.currentGUI = new GuiMenu(game);
		}
		if(button.name.matches("next player")){
			game.currentGUI = new GuiCharacterInfo(game, game.nextPlayer(player));
		}
	}
	
	@Override
	public void paint(Graphics gr){
		double factor = game.getWidth() / 1600.0;
		gr.setColor(Color.BLUE);
		gr.fillRect(0, 0, game.getWidth(), game.getHeight());
		super.paint(gr);
		gr.setFont(font());
		int h = (int) (((double)player.currentHealth / (double)player.maxHealth) * 400 * factor);
		int m = (int) (((double)player.currentMana / (double)player.maxMana) * 400 * factor);
		gr.setColor(Color.GREEN);
		gr.fillRect(factorX(200), factorY(150), h, factorY(50));
		gr.setColor(new Color(200, 0, 200));
		gr.fillRect(factorX(200), factorY(220), m, factorY(50));
		gr.setColor(Color.RED);
		gr.fillRect(factorX(200) + h, factorY(150), factorX(400) - h, factorY(50));
		gr.fillRect(factorX(200) + m, factorY(220), factorX(400) - m, factorY(50));
		gr.setColor(new Color(0, 100, 0));
		gr.drawString("health: " + player.currentHealth + " / " + player.maxHealth, factorX(200), factorY(192));
		gr.setColor(Color.BLUE);
		gr.drawString("mana: " + player.currentMana + " / " + player.maxMana, factorX(200), factorY(262));
		gr.setColor(Color.YELLOW);
		gr.drawString("experience: " + player.xp, factorX(200), factorY(342));
		gr.drawString("total experience: " + player.totalXp, factorX(200), factorY(402));
		gr.setColor(new Color(0, 0, 155));
		gr.drawString("spirit: " + player.spirit, factorX(200), factorY(462));
		gr.setColor(Color.RED);
		gr.drawString("strength: " + player.strength, factorX(200), factorY(522));
		gr.setColor(Color.GRAY);
		gr.drawString("armor: " + player.getArmor(new Attack(Element.NORMAL, false, false)), factorX(200), factorY(582));
		gr.setColor(new Color(100, 0, 100));
		gr.drawString("magic armor: " + player.getArmor(new Attack(Element.NORMAL, false, true)), factorX(200), factorY(642));
		drawEntity(gr, player, new Point(factorX(800), factorY(300)));
	}
}
