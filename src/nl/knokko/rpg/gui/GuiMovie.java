package nl.knokko.rpg.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.monsters.*;
import nl.knokko.rpg.entities.players.Chompo;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.utils.BackGrounds;
import nl.knokko.rpg.utils.JukeBox;
import nl.knokko.rpg.utils.Resources;
import nl.knokko.rpg.utils.SpecialText;

public abstract class GuiMovie extends Gui {
	
	public String[] text = new String[5];
	
	public int tick;
	public double roulator;

	public GuiMovie(Game game) {
		super(game, null);
	}
	
	public abstract void play(Graphics gr);
	
	public int maxTicks(){
		return ticks() * game.fpsFactor;
	}
	
	public abstract int ticks();
	
	@Override
	public abstract boolean canClose();
	
	@Override
	public void escapePressed(){
		if(canClose())
			game.currentGUI = null;
	}
	
	@Override
	public void paint(Graphics gr){
		play(gr);
		int t = 0;
		while(t < buttons.size()){
			buttons.get(t).paint(gr);
			++t;
		}
		gr.setFont(font());
		t = 0;
		while(t < 5){
			if(text[t] != null){
				double width = normalFont.getStringBounds(text[t], ((Graphics2D) gr).getFontRenderContext()).getWidth();
				gr.drawString(text[t], (int) (factorX(800) - width / 2), factorY((int) (400 + t * 50 + roulator)));
			}
			++t;
		}
		++tick;
		roulator += 2.5 / game.fpsFactor;
		if(roulator >= 50){
			roulateText();
			roulator = 0;
		}
		gr.setColor(Color.BLACK);
		gr.drawString("Use escape to skip this movie.", 10, 90);
		if(tick > maxTicks())
			finish();
	}
	
	public void finish(){
		game.currentGUI = null;
	}
	
	public void addText(String string){
		if(text[0] != null)
			roulateText();
		text[0] = string;
	}
	
	public void roulateText(){
		text[4] = text[3];
		text[3] = text[2];
		text[2] = text[1];
		text[1] = text[0];
		text[0] = null;
		roulator = 0;
	}
	
	public void roulateText(int roulate){
		
	}
	
	protected boolean canDivide(int number, double diviner){
		return number / diviner == (int)(number / diviner);
	}
	
	public static class GuiIntroMovie extends GuiMovie {
		
		public Image backGround = Resources.getImage("sprites/movies/backgrounds/fury mountains.png");
		public Image ground = Resources.getImage("sprites/movies/tiles/fury mountains platform.png");
		
		public MovieTile platform = new MovieTile("sprites/movies/tiles/fury mountains platform.png", 0, 700, 1600, 200);
		public MovieObject chomper = new MovieObject(game.player2, 1600, 730);
		public MovieObject chompo = new MovieObject(new Chompo(game, new Point(1620, 725)), 1620, 725);
		public MovieObject hurey = new MovieObject(new Hurey(game, new Point(200, 715), 15), 200, 715);
		public MovieObject zombie = new MovieObject(new Zombie(game, new Point(340, 720), 15), 340, 720);
		public MovieObject furyCaptain = new MovieObject(new FuryCaptain(game, new Point(1630, 730), 10), 1630, 730);
		
		public MovieObject[] furySlicers = new MovieObject[]{new MovieObject(new FurySlicer(game, new Point(1620, 700), 10), 1620, 700), new MovieObject(new FurySlicer(game, new Point(1600, 720), 10), 1600, 720), new MovieObject(new FurySlicer(game, new Point(1590, 740), 10), 1590, 740), new MovieObject(new FurySlicer(game, new Point(1620, 760), 10), 1620, 760)};
		public MovieObject[] furyDogs = new MovieObject[]{new MovieObject(new FuryDog(game, new Point(1650, 730), 30), 1650, 730), new MovieObject(new FuryDog(game, new Point(1630, 750), 30), 1630, 750), new MovieObject(new FuryDog(game, new Point(1620, 770), 30), 1620, 770), new MovieObject(new FuryDog(game, new Point(1650, 790), 30), 1650, 790)};
		
		public boolean status;
		
		public GuiBattle battle1 = new GuiBattle(game, BackGrounds.red_cave, new Entity[]{new Zombie(game, new Point(), 15), new Chompo(game, new Point()), game.player2, new Hurey(game, new Point(), 15)}, new Entity[]{new FurySlicer(game, new Point(), 10), new FurySlicer(game, new Point(), 10), new FurySlicer(game, new Point(), 10), new FurySlicer(game, new Point(), 10)}, false, "intro battle"){
			
			String command = "They taunted this fight!";
			Button button;
			Entity mark;
			int turn;
			int timer;
			
			@Override
			public void paint(Graphics gr){
				super.paint(gr);
				if(button != null && button.name.matches("next")){
					gr.setColor(new Color(0, 0, 0, 150));
					gr.fillRect(0, 0, game.getWidth(), game.getHeight());
					button.paint(gr);
					if(command.contains("yellow marker")){
						if(onTurn >= 0 && onTurn < players.length){
							Point p = players[onTurn].battlePoint;
							gr.drawImage(Resources.getImage("sprites/battlemarker.png"), factorX(p.x + 40), factorY(p.y - 40), factorX(16), factorY(32), null);
						}
					}
				}
				gr.setFont(font());
				gr.setColor(Color.RED);
				double width = normalFont.getStringBounds(command, ((Graphics2D) gr).getFontRenderContext()).getWidth();
				if(width > factorX(800)){
					int index = command.substring(0, command.length() / 2).lastIndexOf(" ");
					width = normalFont.getStringBounds(command.substring(0, index), ((Graphics2D) gr).getFontRenderContext()).getWidth();
					gr.drawString(command.substring(0, index), (int) (factorX(800) - width / 2), factorY((400)));
					width = normalFont.getStringBounds(command.substring(index), ((Graphics2D) gr).getFontRenderContext()).getWidth();
					gr.drawString(command.substring(index), (int) (factorX(800) - width / 2), factorY((450)));
				}
				else
					gr.drawString(command, (int) (factorX(800) - width / 2), factorY((400)));
			}
			
			@Override
			public void click(){
				Point point = game.getMousePosition();
				if(button != null && button.isHit(point))
					buttonClicked(button);
				int t = 0;
				while(t < players.length){
					Entity e = players[t];
					if(e == mark && e != null){
						if(point != null && point.x >= factorX(e.battlePoint.x) && point.x <= factorX(e.battlePoint.x + 120) && point.y >= factorY(e.battlePoint.y) && point.y <= factorY(e.battlePoint.y + 120)){
							game.currentGUI = new GuiBattle1Info(e);
							e.refreshModels();
						}
					}
					++t;
				}
				t = 0;
				while(t < enemies.length){
					Entity e = enemies[t];
					if(e == mark && e != null){
						if(point != null && point.x >= factorX(e.battlePoint.x) && point.x <= factorX(e.battlePoint.x + 120) && point.y >= factorY(e.battlePoint.y) && point.y <= factorY(e.battlePoint.y + 120)){
							game.currentGUI = new GuiBattle1Info(e);
							e.refreshModels();
						}
					}
					++t;
				}
			}
			
			@Override
			public void buttonClicked(Button button){
				super.buttonClicked(button);
				if(turn == 0){
					if(button.name.matches("next") || button.name.matches("special") || button.name.matches("iceball"))
						++timer;
					else if(button.name.matches("Fury Slicer")){
						timer = 0;
						++turn;
					}
				}
				else if(turn == 1){
					if(button.name.matches("next") || button.name.matches("attack"))
						++timer;
					else if(button.name.matches("Fury Slicer")){
						timer = 0;
						++turn;
					}
				}
				else if(turn == 2){
					if(button.name.matches("next") || button.name.matches("attack"))
						++timer;
					else if(button.name.matches("Fury Slicer")){
						timer = 0;
						++turn;
					}
				}
				else if(turn == 3){
					if(button.name.matches("next") || button.name.matches("attack"))
						++timer;
					else if(button.name.matches("Fury Slicer")){
						timer = 0;
						++turn;
					}
				}
				else if(turn == 4){
					if(button.name.matches("next")){
						if(timer == 3)
							clearButtons();
						if(timer == 4 && enemies[0] == currentAttacker)
							clearButtons();
						else
							++timer;
					}
					else if(button.name.matches("special") || button.name.matches("heal"))
						++timer;
					else if(button.name.matches("Chomper")){
						timer = 0;
						++turn;
					}
				}
				else if(turn == 5){
					if(button.name.matches("next") || button.name.matches("special") || button.name.matches("waterslash"))
						++timer;
					else if(button.name.matches("Fury Slicer")){
						timer = 0;
						++turn;
					}
				}
			}
			
			@Override
			public void update(){
				if(turn == 0){
					if(timer == 0){
						onTurn = 0;
						addNormalButtons();
						++timer;
						addButton(750, 500, 850, 600, "next");
						button = buttons.get(7);
					}
					else if(timer == 2){
						command = "Show them what you can do!";
						++timer;
					}
					else if(timer == 4){
						command = "The zombie is on turn now, you can see that at the yellow marker.";
						++timer;
					}
					else if(timer == 6){
						command = "This zombie is a wizard, he fights with spells.";
						++timer;
					}
					else if(timer == 8){
						command = "Click on the special button to choose a spell.";
						addNormalButtons();
						button = buttons.get(4);
						++timer;
					}
					else if(timer == 10){
						command = "Choose the spell iceball.";
						button = buttons.get(4);
						++timer;
					}
					else if(timer == 12){
						command = "Target the second Fury Slicer.";
						button = buttons.get(4);
						++timer;
					}
				}
				else if(turn == 1){
					if(timer == 0 && buttons.size() > 2){
						addButton(750, 500, 850, 600, "next");
						button = buttons.get(7);
						command = "Chompo is on turn now, the yellow marker points at him now.";
						++timer;
					}
					else if(timer == 2){
						command = "Chompo is a brawler, so he fights with his fists.";
						++timer;
					}
					else if(timer == 4){
						addNormalButtons();
						command = "Click on the attack button to use a simple attack.";
						button = buttons.get(5);
						++timer;
					}
					else if(timer == 6){
						button = buttons.get(4);
						command = "Target the third Fury Slicer.";
						++timer;
					}
				}
				else if(turn == 2){
					if(timer == 0 && buttons.size() > 2){
						addButton(750, 500, 850, 600, "next");
						button = buttons.get(7);
						command = "Chomper is on turn now. He doesn't have experience with fighting, so he is't strong.";
						++timer;
					}
					else if(timer == 2){
						command = "But your friends can use every help, even if you can't do a lot.";
						++timer;
					}
					else if(timer == 4){
						addNormalButtons();
						button = buttons.get(5);
						command = "Click on the attack button to attack someone.";
						++timer;
					}
					else if(timer == 6){
						button = buttons.get(3);
						command = "Target the first Fury Slicer.";
						++timer;
					}
				}
				else if(turn == 3){
					if(timer == 0 && buttons.size() > 2){
						addButton(750, 500, 850, 600, "next");
						button = buttons.get(7);
						command = "Hurey is on turn, so the yellow marker points at him.";
						++timer;
					}
					else if(timer == 2){
						command = "This guy is pretty strong, he can easily kill a Fury Slicer.";
						++timer;
					}
					else if(timer == 4){
						addNormalButtons();
						command = "Click on the attack button.";
						button = buttons.get(5);
						++timer;
					}
					else if(timer == 6){
						command = "Target the fourth Fury Slicer.";
						button = buttons.get(4);
						++timer;
					}
				}
				else if(turn == 4){
					if(timer == 0 && buttons.size() == 2 && currentAttacker == enemies[0]){
						addButton(750, 500, 850, 600, "next");
						button = buttons.get(2);
						command = "Your enemies are on turn now, but you have killed most of them already.";
						++timer;
						if(currentAttacker == enemies[0])
							return;
					}
					else if((timer == 1 || timer == 3) && currentAttacker == enemies[0])
						return;
					else if(timer == 2){
						command = "The last Fury Slicer will attack your friends now.";
						++timer;
						if(currentAttacker == enemies[0])
							return;
					}
					else if(timer == 4){
						if(buttons.size() == 7){
							addButton(750, 500, 850, 600, "next");
							button = buttons.get(7);
							command = "Everyone has been on turn, the zombie is on turn again.";
							++timer;
						}
						else
							clearButtons();
					}
					else if(timer == 6){
						command = "That coward just attacked Chomper, he is hurt. You should heal him!";
						++timer;
					}
					else if(timer == 8){
						addNormalButtons();
						command = "Click on the special button.";
						button = buttons.get(4);
						++timer;
					}
					else if(timer == 10){
						button = buttons.get(6);
						command = "Choose the spell heal.";
						++timer;
					}
					else if(timer == 12){
						button = buttons.get(6);
						command = "Heal chomper!";
						++timer;
					}
				}
				else if(turn == 5){
					if(timer == 0 && buttons.size() > 2){
						addButton(750, 500, 850, 600, "next");
						button = buttons.get(7);
						command = "He just attacked Chomper, it's time for revenge.";
						++timer;
					}
					else if(timer == 2){
						addNormalButtons();
						command = "Click on the last Fury Slicer to see his stats.";
						mark = enemies[0];
						++timer;
					}
					else if(timer == 8){
						addNormalButtons();
						command = "Click on the special button to use a special attack.";
						mark = null;
						button = buttons.get(4);
						++timer;
					}
					else if(timer == 10){
						command = "Choose the attack water slash.";
						button = buttons.get(5);
						++timer;
					}
					else if(timer == 12){
						command = "Finish the last Fury Slicer.";
						button = buttons.get(3);
						++timer;
					}
				}
				super.update();
			}
			
			@Override
			public Entity randomPlayer(){
				return players[2];
			}
			
			@Override
			public void clearButtons(){
				button = null;
				super.clearButtons();
			}
			
			@Override
			public void win(){
				game.currentGUI = GuiIntroMovie.this;
				JukeBox.playMusic("main menu");
			}
			
			@Override
			public void changeTurn(boolean start){
				if(turn != 0)
					super.changeTurn(start);
			}
			
			class GuiBattle1Info extends GuiBattleInfo {

				public GuiBattle1Info(Entity entity) {
					super(entity);
					++timer;
				}
				
				@Override
				public void click(){
					if(button != null && button.isHit(game.getMousePosition()))
						buttonClicked(button);
				}
				
				@Override
				public void buttonClicked(Button button){
					if(turn == 5){
						if(button.name.matches("element stats")){
							game.currentGUI = new GuiBattle1ElementStats();
							++timer;
							return;
						}
					}
					super.buttonClicked(button);
				}
				
				@Override
				public void update(){
					if(turn == 5){
						if(timer == 4){
							button = buttons.get(1);
							command = "You can see the stats of this enemy here. Click on element stats to see what elements he doens't like.";
							++timer;
						}
					}
					super.update();
				}
				
				@Override
				public void paint(Graphics gr){
					super.paint(gr);
					gr.setFont(font());
					gr.setColor(Color.BLACK);
					double width = normalFont.getStringBounds(command, ((Graphics2D) gr).getFontRenderContext()).getWidth();
					if(width > factorX(800)){
						int index = command.substring(0, command.length() / 2).lastIndexOf(" ");
						width = normalFont.getStringBounds(command.substring(0, index), ((Graphics2D) gr).getFontRenderContext()).getWidth();
						gr.drawString(command.substring(0, index), (int) (factorX(800) - width / 2), factorY((800)));
						width = normalFont.getStringBounds(command.substring(index), ((Graphics2D) gr).getFontRenderContext()).getWidth();
						gr.drawString(command.substring(index), (int) (factorX(800) - width / 2), factorY((850)));
					}
					else
						gr.drawString(command, (int) (factorX(800) - width / 2), factorY((800)));
					gr.drawString("turn = " + turn, 1000, 200);
					gr.drawString("timer = " + timer, 1000, 300);
				}
				
				@Override
				public void escapePressed(){
					battle1.escapePressed();
				}
				
				class GuiBattle1ElementStats extends GuiBattleElementStats {
					
					@Override
					public void click(){
						if(button != null && button.isHit(game.getMousePosition()))
							buttonClicked(button);
					}
					
					@Override
					public void escapePressed(){
						battle1.escapePressed();
					}
					
					@Override
					public void buttonClicked(Button button){
						if(turn == 5){
							if(button.name.matches("return to battle")){
								++timer;
								game.currentGUI = battle1;
								entity.refreshModels();
								return;
							}
						}
						super.buttonClicked(button);
					}
					
					@Override
					public void update(){
						if(turn == 5){
							if(timer == 6){
								button = buttons.get(0);
								command = "You can see what elements this guy can resist, and what elements this guy hates. You can see he doesn't like water, click on return to battle.";
								++timer;
							}
						}
						super.update();
					}
					
					@Override
					public void paint(Graphics gr){
						super.paint(gr);
						gr.setFont(font());
						gr.setColor(Color.BLACK);
						double width = normalFont.getStringBounds(command, ((Graphics2D) gr).getFontRenderContext()).getWidth();
						if(width > factorX(800)){
							int index = command.substring(0, command.length() / 2).lastIndexOf(" ");
							width = normalFont.getStringBounds(command.substring(0, index), ((Graphics2D) gr).getFontRenderContext()).getWidth();
							gr.drawString(command.substring(0, index), (int) (factorX(800) - width / 2), factorY((50)));
							width = normalFont.getStringBounds(command.substring(index), ((Graphics2D) gr).getFontRenderContext()).getWidth();
							gr.drawString(command.substring(index), (int) (factorX(800) - width / 2), factorY((100)));
						}
						else
							gr.drawString(command, (int) (factorX(800) - width / 2), factorY((50)));
						gr.drawString("turn = " + turn, 1000, 200);
						gr.drawString("timer = " + timer, 1000, 300);
					}
				}
			}
		};
		
		public GuiBattle battle2 = new GuiBattle(game, BackGrounds.red_cave, new Entity[]{new Zombie(game, new Point(), 15), new Chompo(game, new Point()), game.player2, new Hurey(game, new Point(), 15)}, new Entity[]{new FuryDog(game, new Point(), 30), new FuryDog(game, new Point(), 30), new FuryDog(game, new Point(), 30), new FuryDog(game, new Point(), 30)}, false, "intro battle"){
			
			String command = "You are fighting for your life, don't give up!";
			Button button;
			Entity mark;
			int turn;
			int timer;
			
			@Override
			public void paint(Graphics gr){
				super.paint(gr);
				if(button != null && button.name.matches("next")){
					gr.setColor(new Color(0, 0, 0, 150));
					gr.fillRect(0, 0, game.getWidth(), game.getHeight());
					button.paint(gr);
					if(command.contains("yellow marker")){
						if(onTurn >= 0 && onTurn < players.length){
							Point p = players[onTurn].battlePoint;
							gr.drawImage(Resources.getImage("sprites/battlemarker.png"), factorX(p.x + 40), factorY(p.y - 40), factorX(16), factorY(32), null);
						}
					}
				}
				gr.setFont(font());
				gr.setColor(Color.RED);
				double width = normalFont.getStringBounds(command, ((Graphics2D) gr).getFontRenderContext()).getWidth();
				if(width > factorX(800)){
					int index = command.substring(0, command.length() / 2).lastIndexOf(" ");
					width = normalFont.getStringBounds(command.substring(0, index), ((Graphics2D) gr).getFontRenderContext()).getWidth();
					gr.drawString(command.substring(0, index), (int) (factorX(800) - width / 2), factorY((400)));
					width = normalFont.getStringBounds(command.substring(index), ((Graphics2D) gr).getFontRenderContext()).getWidth();
					gr.drawString(command.substring(index), (int) (factorX(800) - width / 2), factorY((450)));
				}
				else
					gr.drawString(command, (int) (factorX(800) - width / 2), factorY((400)));
				gr.drawString("turn = " + turn, 1000, 200);
				gr.drawString("timer = " + timer, 1000, 300);
			}
			
			@Override
			public void buttonClicked(Button button){
				super.buttonClicked(button);
				if(turn == 0){
					if(button.name.matches("next") || button.name.matches("special") || button.name.matches("iceball"))
						++timer;
					else if(button.name.matches("Fury Dog")){
						timer = 0;
						++turn;
					}
				}
				else if(turn >= 1 && turn <= 5 && turn != 4){
					if(button.name.matches("next") || button.name.matches("attack"))
						++timer;
					else if(button.name.matches("Fury Dog")){
						timer = 0;
						++turn;
					}
				}
				else if(turn == 4){
					if(button.name.matches("next")){
						if(timer == 3)
							clearButtons();
						if(timer == 4 && enemies[0] == currentAttacker)
							clearButtons();
						else
							++timer;
					}
					else if(button.name.matches("attack"))
						++timer;
					else if(button.name.matches("Fury Dog")){
						timer = 0;
						++turn;
					}
				}
			}
			
			@Override
			public void update(){
				if(turn == 0){
					if(timer == 0){
						onTurn = 0;
						addNormalButtons();
						++timer;
						addButton(750, 500, 850, 600, "next");
						button = buttons.get(7);
					}
					else if(timer == 2){
						command = "These enemies are probably stronger than the previous enemies. This will be a hard fight.";
						++timer;
					}
					else if(timer == 4){
						command = "As you saw the previous fight, fury monsters don't like water.";
						++timer;
					}
					else if(timer == 6){
						command = "Click on the special button to choose a spell.";
						addNormalButtons();
						button = buttons.get(4);
						++timer;
					}
					else if(timer == 8){
						command = "Choose iceball because they don't like water (and ice).";
						button = buttons.get(4);
						++timer;
					}
					else if(timer == 10){
						command = "Attack the first Fury Dog.";
						button = buttons.get(3);
						++timer;
					}
				}
				else if(turn == 1 && buttons.size() > 2){
					if(timer == 0){
						++timer;
						command = "The first Fury Dog is almost defeated, you don't need your powers to finish him.";
						addButton(750, 500, 850, 600, "next");
						button = buttons.get(7);
					}
					else if(timer == 2){
						++timer;
						command = "Click on the attack button.";
						addNormalButtons();
						button = buttons.get(5);
					}
					else if(timer == 4){
						command = "Finish the first Fury Dog!";
						button = buttons.get(3);
						++timer;
					}
				}
				else if(turn == 2 && buttons.size() > 2){
					if(timer == 0){
						++timer;
						command = "You have slain 1 of the Fury Dogs.";
						addButton(750, 500, 850, 600, "next");
						button = buttons.get(7);
					}
					else if(timer == 2){
						command = "However, there are 3 Dogs left who will try to take you down.";
						++timer;
					}
					else if(timer == 4){
						command = "Chomper won't deal a lot of damage to these dogs, but every help may be necessary.";
						++timer;
					}
					else if(timer == 6){
						command = "Click on the attack button to attack a Fury Dog.";
						addNormalButtons();
						button = buttons.get(5);
						++timer;
					}
					else if(timer == 8){
						command = "Attack the third Fury Dog";
						button = buttons.get(4);
						++timer;
					}
				}
				else if(turn == 3 && buttons.size() > 2){
					if(timer == 0){
						command = "The hurrey is stronger, he can deal more damage.";
						addButton(750, 500, 850, 600, "next");
						button = buttons.get(7);
						++timer;
					}
					else if(timer == 2){
						command = "Click on the attack button.";
						addNormalButtons();
						button = buttons.get(5);
						++timer;
					}
					else if(timer == 4){
						command = "Attack the third Fury Dog.";
						button = buttons.get(4);
						++timer;
					}
				}
				else if(turn == 4){
					if(timer == 0 && buttons.size() == 2 && currentAttacker == enemies[3]){
						addButton(750, 500, 850, 600, "next");
						button = buttons.get(2);
						command = "Your enemies are on turn, you will take a lot of damage now.";
						++timer;
						if(currentAttacker == enemies[3])
							return;
					}
					else if((timer == 1 || timer == 3) && currentAttacker == enemies[3])
						return;
					else if(timer == 2){
						command = "The Fury Dogs will attack your friends...";
						++timer;
						if(currentAttacker == enemies[0])
							return;
					}
					else if(timer == 4){
						if(buttons.size() == 7){
							addButton(750, 500, 850, 600, "next");
							button = buttons.get(7);
							command = "Those dogs have dealt huge damage to your team, 2 are already knock out.";
							++timer;
						}
						else
							clearButtons();
					}
					else if(timer == 6){
						command = "You have only 2 guys left to fight for your destiny. You aren't likely to win this.";
						++timer;
					}
					else if(timer == 8){
						command = "You won't earn anything with giving up, so fight until the end.";
						++timer;
					}
					else if(timer == 10){
						addNormalButtons();
						command = "Click on the attack button.";
						button = buttons.get(5);
						++timer;
					}
					else if(timer == 12){
						button = buttons.get(4);
						command = "Attack the third Fury Dog.";
						++timer;
					}
				}
				else if(turn == 5 && buttons.size() > 2){
					if(timer == 0){
						command = "Show these monsters the last power you have!";
						addButton(750, 500, 850, 600, "next");
						button = buttons.get(7);
						++timer;
					}
					else if(timer == 2){
						command = "Attack the third Fury Dog with everything you have.";
						addNormalButtons();
						button = buttons.get(5);
						++timer;
					}
					else if(timer == 4){
						button = buttons.get(4);
						++timer;
					}
				}
				super.update();
			}
			
			@Override
			public void click(){
				Point point = game.getMousePosition();
				if(button != null && button.isHit(point))
					buttonClicked(button);
				int t = 0;
				while(t < players.length){
					Entity e = players[t];
					if(e == mark && e != null){
						if(point != null && point.x >= factorX(e.battlePoint.x) && point.x <= factorX(e.battlePoint.x + 120) && point.y >= factorY(e.battlePoint.y) && point.y <= factorY(e.battlePoint.y + 120)){
							game.currentGUI = new GuiBattleInfo(e);
						}
					}
					++t;
				}
				t = 0;
				while(t < enemies.length){
					Entity e = enemies[t];
					if(e == mark && e != null){
						if(point != null && point.x >= factorX(e.battlePoint.x) && point.x <= factorX(e.battlePoint.x + 120) && point.y >= factorY(e.battlePoint.y) && point.y <= factorY(e.battlePoint.y + 120)){
							game.currentGUI = new GuiBattleInfo(e);
						}
					}
					++t;
				}
			}
			
			@Override
			public Entity randomPlayer(){
				if(players[0] != null)
					return players[0];
				if(players[1] != null)
					return players[1];
				if(players[3] != null)
					return players[3];
				return players[2];
			}
			
			@Override
			public void clearButtons(){
				button = null;
				super.clearButtons();
			}
			
			@Override
			public void lose(){
				game.currentGUI = GuiIntroMovie.this;
				JukeBox.stop();
			}
			
			@Override
			public void changeTurn(boolean start){
				if(turn != 0)
					super.changeTurn(start);
			}
		};
		
		public GuiChat conversation1 = new GuiChat(game, false, false, new Point(200, 300), new SpecialText("Well Chomper, this is what I wanted to show you.", "You haven't come to the Fury Mountains for nothing."), new SpecialText("That portal looks quite awesome.", "What would be behind the portal?"), new SpecialText("There will be another dimension behind it.", "But nobody has been there because the portal is unstable.", "It may close after you walk through it.", "So nobody takes the risk to enter it."), new SpecialText("Well...", "I understand nobody enters the portal.", "Shall we just enjoy this moment?"), new SpecialText("Yes, that's a good idea.")).setSpeakers(new String[]{"Chompo", "Chomper", "Chompo", "Chomper", "Chompo"}).setFinishGui(this);
		public GuiChat conversation2 = new GuiChat(game, false, false, new Point(200, 300), new SpecialText("Attention please.", "Our captain is going to speak."), new SpecialText("We are going to conquer the new dimension.", "But we need someone to test the portal, it can be unstable.", "Does anybody want to do that for us?")).setSpeakers(new String[]{"Fury Slicer", "Fury Captain"}).setFinishGui(this);
		public GuiChat conversation3 = new GuiChat(game, false, false, new Point(200, 300), new SpecialText("If nobody wants to help us, we will pick one."), new SpecialText("Hey, you can't do that!", "If nobody wants to do it, nobody goes."), new SpecialText("I am the captain, I CAN do that.", "If nobody wants to enter the portal, I will pick you."), new SpecialText("That is ridiculous!", "Nobody can steal my son from me!"), new SpecialText("If nobody comes within 5 seconds,", "I will pick that sheeky black guy.")).setSpeakers(new String[]{"Fury Captain", "Chomper", "Fury Captain", "Chompo", "Fury Captain"}).setFinishGui(this);
		public GuiChat conversation4 = new GuiChat(game, false, false, new Point(200, 300), new SpecialText("The 5 seconds are over.", "Go through the portal, black guy."), new SpecialText("No, I won't."), new SpecialText("That was no question, enter the portal."), new SpecialText("He said no."), new SpecialText("I don't care, he goes through the portal.", "Whether he wants it, or not.", "However, you have a choice.", "You can enter it yourself or my men ", "will throw you through the portal."), new SpecialText("I will fight if I have to.", "Don't force me to do that."), new SpecialText("If you want a fight, you can get it."), new SpecialText("I don't want to fight, only if I have to."), new SpecialText("If you don't want a fight, go through the portal."), new SpecialText("No, do it yourself."), new SpecialText("Guys, attack him."), new SpecialText("I will defend my son until the end."), new SpecialText("You will end up in prison."), new SpecialText("I will also defend that guy, this is ridiculous!")).setSpeakers(new String[]{"Fury Captain", "Chomper", "Fury Captain", "Chompo", "Fury Captain", "Chomper", "Fury Captain", "Chomper", "Fury Captain", "Chomper", "Fury Captain", "Chompo", "Fury Captain", "Hurey"}).setFinishGui(this);
		public GuiChat conversation5 = new GuiChat(game, false, false, new Point(200, 300), new SpecialText("Is there anyone else who likes prisons?", "You are welcome."), new SpecialText("Yes, they are right.", "You can't do this!"), new SpecialText("Believe me, I can, we have enough prisons here.")).setSpeakers(new String[]{"Fury Captain", "Zombie", "Fury Captain"}).setFinishGui(this);
		public GuiChat conversation6 = new GuiChat(game, false, false, new Point(200, 300), new SpecialText("Enough, show those fools some respect."), new SpecialText("Yes, captain!")).setSpeakers(new String[]{"Fury Captain", "Fury Slicers"}).setFinishGui(this);
		public GuiChat conversation7 = new GuiChat(game, false, false, new Point(200, 300), new SpecialText("You just killed 4 of my soldiers.", "That will be a long prison sentence.", "And that black guy still has to enter the portal."), new SpecialText("We are not going anywhere,", "you have used your power for bad things.", "Run for your life, or we will kill you too."), new SpecialText("I don't think so, I have asked for assistance.", "They can arrive any moment, you had better stop", " talking like that.")).setSpeakers(new String[]{"Fury Captain", "Hurey", "Fury Captain"}).setFinishGui(this);
		public GuiChat conversation8 = new GuiChat(game, false, false, new Point(200, 300), new SpecialText("If you surrender now, I will decrease the duration", "of your prison sentence.", "So, what do you say?"), new SpecialText("I had rather die!"), new SpecialText("These dogs are trained to fight until you are knock out.", "They are not trained to kill you.", "You have to pay for your behaviour, you can't just die."), new SpecialText("If that's the case, we won't kill the dogs if we defeat them."), new SpecialText("Dogs, they haven't changed their mind.", "So take them down!")).setSpeakers(new String[]{"Fury Captain", "Chompo", "Fury Captain", "Zombie", "Fury Captain"}).setFinishGui(this);

		public GuiIntroMovie(Game game) {
			super(game);
		}

		@Override
		public void play(Graphics gr){
			gr.drawImage(backGround, 0, 0, game.getWidth(), game.getHeight(), null);
			if(status){
				play2(gr);
				return;
			}
			paintPortal(gr, 600, 670);
			platform.paint(gr);
			zombie.paint(gr, this);
			chomper.paint(gr, this);
			chompo.paint(gr, this);
			hurey.paint(gr, this);
			int i = 0;
			while(i < furySlicers.length){
				furySlicers[i].paint(gr, this);
				if(i == 1)
					furyCaptain.paint(gr, this);
				++i;
			}
			i = 0;
			while(i < furyDogs.length && furySlicers.length == 0){
				furyDogs[i].paint(gr, this);
				if(i == 1)
					furyCaptain.paint(gr, this);
				++i;
			}
			if(furySlicers.length == 0 && furyDogs.length == 0)
				furyCaptain.paint(gr, this);
			gr.setColor(Color.BLACK);
			if(tick == game.fpsFactor * 30)
				addText("In the fury mountains...");
			if(tick == game.fpsFactor * 70)
				addText("Lives a demon called Chomper.");
			if(tick >= game.fpsFactor * 80 && tick <= game.fpsFactor * 90)
				zombie.x += 5.0 / game.fpsFactor;
			if(tick == game.fpsFactor * 110)
				addText("A spirit managed to open a portal.");
			if(tick == game.fpsFactor * 120)
				hurey.entity.rotation = 2;
			if(tick >= game.fpsFactor * 120 && tick <= game.fpsFactor * 130)
				hurey.x -= 3.0 / game.fpsFactor;
			if(tick == game.fpsFactor * 150)
				addText("The portal leads to another dimension.");
			if(tick == game.fpsFactor * 190)
				addText("Chomper is here to see the mysterious portal with his father.");
			if(tick == game.fpsFactor * 230)
				addText("The portal can be unstable, but nobody is sure.");
			if(tick == game.fpsFactor * 270)
				addText("That's why nobody has entered the dimension.");
			if(tick == game.fpsFactor * 290)
				hurey.entity.rotation = 0;
			if(tick >= game.fpsFactor * 290 && tick <= game.fpsFactor * 300)
				hurey.x += 5.0 / game.fpsFactor;
			if(tick == game.fpsFactor * 300)
				addText("This will change soon...");
			if(tick > game.fpsFactor * 300 && tick < game.fpsFactor * 450){
				chomper.x -= 5.0 / game.fpsFactor;
				chompo.x -= 5.0 / game.fpsFactor;
				chomper.entity.rotation = 2;
				chompo.entity.rotation = 2;
			}
			if(tick == game.fpsFactor * 455)
				game.currentGUI = conversation1;
			if(tick >= game.fpsFactor * 470 && tick <= game.fpsFactor * 590){
				int t = 0;
				while(t < furySlicers.length){
					furySlicers[t].x -= 5.0 / game.fpsFactor;
					furySlicers[t].entity.rotation = 2;
					++t;
				}
				furyCaptain.x -= 5.0 / game.fpsFactor;
				furyCaptain.entity.rotation = 2;
			}
			if(tick == game.fpsFactor * 600){
				game.currentGUI = conversation2;
				chomper.entity.rotation = 0;
				chompo.entity.rotation = 0;
			}
			if(tick == game.fpsFactor * 700)
				game.currentGUI = conversation3;
			if(tick == game.fpsFactor * 800)
				game.currentGUI = conversation4;
			if(tick > game.fpsFactor * 800 && tick < game.fpsFactor * 900){
				hurey.x += 6.0 / game.fpsFactor;
			}
			if(tick == game.fpsFactor * 900)
				game.currentGUI = conversation5;
			if(tick > game.fpsFactor * 900 && tick < game.fpsFactor * 1000){
				zombie.x += 5.0 / game.fpsFactor;
			}
			if(tick == game.fpsFactor * 1000)
				game.currentGUI = conversation6;
			if(tick == game.fpsFactor * 1001){
				game.currentGUI = battle1.activate();
				furySlicers = new MovieObject[0];
			}
			if(tick == game.fpsFactor * 1020)
				game.currentGUI = conversation7;
			if(tick >= game.fpsFactor * 1050 && tick <= game.fpsFactor * 1120){
				int t = 0;
				while(t < furyDogs.length){
					furyDogs[t].x -= 8.0 / game.fpsFactor;
					furyDogs[t].entity.rotation = 2;
					++t;
				}
			}
			if(tick == game.fpsFactor * 1121)
				game.currentGUI = conversation8;
			if(tick == game.fpsFactor * 1122)
				game.currentGUI = battle2.activate();
			if(tick == game.fpsFactor * 1123){
				status = true;
				backGround = Resources.getImage("sprites/movies/backgrounds/foid forest.png");
			}
		}
		
		public void play2(Graphics gr){
			//TODO sprites/backgrounds/foid forest.png afmaken
		}

		@Override
		public boolean canClose() {
			return true;
		}

		@Override
		public int ticks() {
			return 1200;
		}
		
		@Override
		public void finish(){
			game.currentGUI = null;
			game.updateSpeed = 1;
			game.player2.currentHealth = game.player2.maxHealth;
		}
		
		protected void paintPortal(Graphics gr, int bx, int by){
			gr.setColor(Color.BLACK);
			gr.drawOval(factorX(5 + bx), factorY(5 + by), factorX(50), factorY(50));
			Random random = new Random();
			int x = 5;
			while(x <= 55){
				int y = 5;
				while(y <= 55){
					if(new Point(30, 30).distance(x, y) < 25){
						gr.setColor(new Color(100 + random.nextInt(50), 0, 100 + random.nextInt(50)));
						gr.fillRect(factorX(x + bx), factorY(y + by), 1, 1);
					}
					++y;
				}
				++x;
			}
		}
	}
	
	public static class MovieObject {
		
		public Entity entity;
		
		public float x;
		public float y;
		
		public MovieObject(Entity entity, int posX, int posY){
			x = posX;
			y = posY;
			this.entity = entity;
		}
		
		public void paint(Graphics gr, Gui gui){
			entity.battlePoint.x = (int) x;
			entity.battlePoint.y = (int) y;
			entity.updateAI();
			int t = 0;
			while(t < entity.models.size()){
				entity.models.get(t).paintInBattle((Graphics2D) gr, entity.rotation == 2, (int)x, (int)y, 2, 2);
				++t;
			}
		}
	}
	
	public static class MovieTile {
		
		public Image image;
		
		public int x;
		public int y;
		
		public int width;
		public int height;
		
		public MovieTile(String resource, int screenX, int screenY, int paintWidth, int paintHeight){
			image = Resources.getImage(resource);
			x = screenX;
			y = screenY;
			width = paintWidth;
			height = paintHeight;
		}
		
		public void paint(Graphics gr){
			gr.drawImage(image, x, y, width, height, null);
		}
	}
}
