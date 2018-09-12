package nl.knokko.rpg.gui;

import org.lwjgl.util.vector.Vector2f;

import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.players.Player;
import nl.knokko.rpg.main.MainClass;

public class GuiElementStats extends Gui {
	
	public final Player player;
	
	public GuiElementStats(Player thePlayer) {
		super("sprites/gui/elementstats.png");
		player = thePlayer;
		player.refreshModels();
		addButton(1300, 500, 1400, 600, "back");
		addButton(1300, 650, 1550, 750, "next player");
	}
	
	@Override
	public void buttonClicked(Button button){
		if(button.name.matches("back")){
			MainClass.currentGUI = new GuiMenu();
		}
		else if(button.name.matches("next player")){
			MainClass.currentGUI = new GuiElementStats(MainClass.nextPlayer(player));
		}
	}
	
	/*
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
		gr.fillRect(0, factorY(350), MainClass.getWidth(), factorY(150));
		gr.setColor(Color.BLACK);
		gr.drawLine(0, factorY(350), MainClass.getWidth(), factorY(350));
		gr.drawLine(0, factorY(400), MainClass.getWidth(), factorY(400));
		gr.drawLine(0, factorY(450), MainClass.getWidth(), factorY(450));
		gr.drawLine(0, factorY(500), MainClass.getWidth(), factorY(500));
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
	*/
	
	@Override
	public void rightClick(Vector2f mouse){
		if(mouse.x < 0.625f && mouse.y > -0.11111f && mouse.y < 0.11111f && player.xp >= 100 && player.elementPoints > 0){
			Element element = null;
			if(mouse.x > -0.75f && mouse.x < -0.65625f)
				element = Element.AIR;
			else if(mouse.x > 0.65625f && mouse.x < -0.5f)
				element = Element.EARTH;
			else if(mouse.x > -0.5f && mouse.x < -0.375f)
				element = Element.FIRE;
			else if(mouse.x > 0.375f && mouse.x < -0.125f)
				element = Element.LIGHTNING;
			else if(mouse.x > -0.125f && mouse.x < 0.0375f)
				element = Element.WATER;
			else if(mouse.x > 0.0375f && mouse.x < 0.225f)
				element = Element.POISON;
			else if(mouse.x > 0.225f && mouse.x < 0.3625f)
				element = Element.LIGHT;
			else if(mouse.x > 0.3625f && mouse.x < 0.5f)
				element = Element.DARK;
			else if(mouse.x > 0.5f && mouse.x < 0.625f)
				element = Element.ROCK;
			if(element != null){
				if(mouse.y < 0){
					player.xp -= 100;
					--player.elementPoints;
					player.elementStats.increasePower(element);
				}
				else if(mouse.y > 0){
					player.xp -= 100;
					--player.elementPoints;
					player.elementStats.increaseResistance(element);
				}
			}
		}
	}
}
