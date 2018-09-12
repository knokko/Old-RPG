package nl.knokko.rpg.gui;

import java.awt.*;

import nl.knokko.rpg.entities.players.Player;
import nl.knokko.rpg.main.Game;

public class GuiStatsUpgrade extends Gui {
	
	public final Player player;
	
	public GuiStatsUpgrade(Game theGame, Player thePlayer) {
		super(theGame, "sprites/gui/stats menu.png");
		player = thePlayer;
		player.refreshModels();
		buttons.add(new Button(950, 750, 1250, 850, "sprites/buttons/button.png", "upgrade mana", normalFont, Color.PINK));
		buttons.add(new Button(330, 430, 640, 530, "sprites/buttons/button.png", "upgrade health", normalFont, Color.GREEN));
		buttons.add(new Button(800, 150, 1100, 250, "sprites/buttons/button.png", "upgrade spirit", normalFont, Color.BLUE));
		buttons.add(new Button(1200, 250, 1550, 350, "sprites/buttons/button.png", "upgrade strength", normalFont, Color.RED));
		addButton(740, 400, 860, 500, "back");
		addButton(700, 550, 950, 650, "next player");
	}
	
	@Override
	public void buttonClicked(Button button){
		if(button.name.matches("upgrade mana") && player.xp >= 100){
			player.xp -= 100;
			player.maxMana += 10;
			player.currentMana += 10;
		}
		if(button.name.matches("upgrade health") && player.xp >= 100){
			player.xp -= 100;
			player.maxHealth += 10;
			player.currentHealth += 10;
		}
		if(button.name.matches("upgrade spirit") && player.xp >= 100){
			player.xp -= 100;
			++player.spirit;
		}
		if(button.name.matches("upgrade strength") && player.xp >= 100){
			player.xp -= 100;
			++player.strength;
		}
		if(button.name.matches("back")){
			game.currentGUI = new GuiMenu(game);
		}
		if(button.name.matches("next player")){
			game.currentGUI = new GuiStatsUpgrade(game, game.nextPlayer(player));
		}
	}
	
	@Override
	public void paint(Graphics gr){
		super.paint(gr);
		Player p = player;
		gr.setFont(font());
		gr.setColor(Color.YELLOW);
		gr.drawString("experience: " + p.xp, factorX(400), factorY(50));
		gr.drawString("upgrading a stat costs 100 experience", factorX(400), factorY(100));
		gr.setColor(Color.RED);
		gr.drawString("strength: " + p.strength, factorX(400), factorY(300));
		gr.setColor(new Color(0, 0, 100));
		gr.drawString("spirit: " + p.spirit, factorX(400), factorY(250));
		gr.setColor(Color.GREEN);
		gr.drawString("maxhealth: " + p.maxHealth, factorX(400), factorY(150));
		gr.setColor(new Color(150, 0, 150));
		gr.drawString("maxmana: " + p.maxMana, factorX(400), factorY(200));
		drawEntity(gr, p, new Point(factorX(1100), factorY(10)));
	}
}
