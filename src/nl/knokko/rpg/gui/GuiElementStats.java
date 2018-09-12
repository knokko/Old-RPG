package nl.knokko.rpg.gui;

import java.awt.*;

import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.players.Player;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.utils.PointUtils;

public class GuiElementStats extends Gui {
	
	public final Player player;
	
	public GuiElementStats(Game theGame, Player thePlayer) {
		super(theGame, "sprites/gui/elementstats.png");
		player = thePlayer;
		player.refreshModels();
		addButton(1300, 500, 1400, 600, "back");
		addButton(1300, 650, 1550, 750, "next player");
	}
	
	@Override
	public void buttonClicked(Button button){
		if(button.name.matches("back")){
			game.currentGUI = new GuiMenu(game);
		}
		else if(button.name.matches("next player")){
			game.currentGUI = new GuiElementStats(game, game.nextPlayer(player));
		}
	}
	
	@Override
	public void paint(Graphics gr){
		super.paint(gr);
		gr.setFont(new Font("TimesRoman", 0, factorX(40)));
		gr.setColor(Color.BLACK);
		gr.drawString("right click in the grid to upgrade the power or resistance of an element", factorX(10), factorY(150));
		gr.drawString("upgrading costs 100 xp and 1 element point and increases the power or resistance by 10%", factorX(10), factorY(200));
		gr.drawString("you have " + player.xp + " xp and you have " + player.elementPoints + " element points.", factorX(10), factorY(250));
		Entity s = player;
		gr.setColor(new Color(150, 0, 150));
		gr.fillRect(0, factorY(350), game.getWidth(), factorY(150));
		gr.setColor(Color.BLACK);
		gr.drawLine(0, factorY(350), game.getWidth(), factorY(350));
		gr.drawLine(0, factorY(400), game.getWidth(), factorY(400));
		gr.drawLine(0, factorY(450), game.getWidth(), factorY(450));
		gr.drawLine(0, factorY(500), game.getWidth(), factorY(500));
		gr.drawLine(0, factorY(350), 0, factorY(500));
		gr.drawLine(factorX(200), factorY(350), factorX(200), factorY(500));
		gr.drawLine(factorX(275), factorY(350), factorX(275), factorY(500));
		gr.drawLine(factorX(400), factorY(350), factorX(400), factorY(500));
		gr.drawLine(factorX(500), factorY(350), factorX(500), factorY(500));
		gr.drawLine(factorX(700), factorY(350), factorX(700), factorY(500));
		gr.drawLine(factorX(830), factorY(350), factorX(830), factorY(500));
		gr.drawLine(factorX(980), factorY(350), factorX(980), factorY(500));
		gr.drawLine(factorX(1090), factorY(350), factorX(1090), factorY(500));
		gr.drawLine(factorX(1200), factorY(350), factorX(1200), factorY(500));
		gr.drawLine(factorX(1300), factorY(350), factorX(1300), factorY(500));
		gr.drawString("element", 0, factorY(390));
		gr.drawString("power", 0, factorY(440));
		gr.drawString("resistance", 0, factorY(490));
		gr.setColor(Element.AIR.color);
		gr.drawString("air", factorX(210), factorY(390));
		gr.drawString("" + s.getPower(Element.AIR), factorX(200), factorY(440));
		gr.drawString("" + s.getResistance(Element.AIR), factorX(200), factorY(490));
		gr.setColor(Element.EARTH.color);
		gr.drawString("earth", factorX(285), factorY(390));
		gr.drawString(s.getPower(Element.EARTH) + "", factorX(285), factorY(440));
		gr.drawString(s.getResistance(Element.EARTH) + "", factorX(285), factorY(490));
		gr.setColor(Element.FIRE.color);
		gr.drawString("fire", factorX(415), factorY(390));
		gr.drawString(s.getPower(Element.FIRE) + "", factorX(400), factorY(440));
		gr.drawString(s.getResistance(Element.FIRE) + "", factorX(400), factorY(490));
		gr.setColor(Element.LIGHTNING.color);
		gr.drawString("lightning", factorX(510), factorY(390));
		gr.drawString(s.getPower(Element.LIGHTNING) + "", factorX(510), factorY(440));
		gr.drawString(s.getResistance(Element.LIGHTNING) + "", factorX(510), factorY(490));
		gr.setColor(Element.WATER.color);
		gr.drawString("water", factorX(710), factorY(390));
		gr.drawString(s.getPower(Element.WATER) + "", factorX(710), factorY(440));
		gr.drawString(s.getResistance(Element.WATER) + "", factorX(710), factorY(490));
		gr.setColor(Element.POISON.color);
		gr.drawString("poison", factorX(840), factorY(390));
		gr.drawString(s.getPower(Element.POISON) + "", factorX(840), factorY(440));
		gr.drawString(s.getResistance(Element.POISON) + "", factorX(840), factorY(490));
		gr.setColor(Element.LIGHT.color);
		gr.drawString("light", factorX(990), factorY(390));
		gr.drawString(s.getPower(Element.LIGHT) + "", factorX(990), factorY(440));
		gr.drawString(s.getResistance(Element.LIGHT) + "", factorX(990), factorY(490));
		gr.setColor(Element.DARK.color);
		gr.drawString("dark", factorX(1100), factorY(390));
		gr.drawString(s.getPower(Element.DARK) + "", factorX(1100), factorY(440));
		gr.drawString(s.getResistance(Element.DARK) + "", factorX(1100), factorY(490));
		gr.setColor(Element.ROCK.color);
		gr.drawString("rock", factorX(1210), factorY(390));
		gr.drawString(s.getPower(Element.ROCK) + "", factorX(1210), factorY(440));
		gr.drawString(s.getResistance(Element.ROCK) + "", factorX(1210), factorY(490));
		drawEntity(gr, s, new Point(factorX(700), factorY(510)));
	}
	
	@Override
	public void rightClick(Point mouse){
		mouse = PointUtils.mouseToScreenPoint(mouse);
		if(mouse.x < factorX(1300) && mouse.y > factorY(400) && mouse.y < factorY(500) && player.xp >= 100 && player.elementPoints > 0){
			Element element = null;
			if(mouse.x > factorX(200) && mouse.x < factorX(275))
				element = Element.AIR;
			else if(mouse.x > factorX(275) && mouse.x < factorX(400))
				element = Element.EARTH;
			else if(mouse.x > factorX(400) && mouse.x < factorX(500))
				element = Element.FIRE;
			else if(mouse.x > factorX(500) && mouse.x < factorX(700))
				element = Element.LIGHTNING;
			else if(mouse.x > factorX(700) && mouse.x < factorX(830))
				element = Element.WATER;
			else if(mouse.x > factorX(830) && mouse.x < factorX(980))
				element = Element.POISON;
			else if(mouse.x > factorX(980) && mouse.x < factorX(1090))
				element = Element.LIGHT;
			else if(mouse.x > factorX(1090) && mouse.x < factorX(1200))
				element = Element.DARK;
			else if(mouse.x > factorX(1200) && mouse.x < factorX(1300))
				element = Element.ROCK;
			if(element != null){
				if(mouse.y < factorY(450)){
					player.xp -= 100;
					--player.elementPoints;
					player.elementStats.increasePower(element);
				}
				else if(mouse.y > factorY(450)){
					player.xp -= 100;
					--player.elementPoints;
					player.elementStats.increaseResistance(element);
				}
			}
		}
	}
}
