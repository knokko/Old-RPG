package nl.knokko.rpg.gui;

import java.awt.*;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import nl.knokko.rpg.entities.players.Player;
import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.spells.Spell;
import nl.knokko.rpg.spells.Spells;
import nl.knokko.rpg.toolbars.ToolBar;

public class GuiSpellTree extends Gui {
	
	public final Player player;
	
	public Vector2f camera = new Vector2f();
	public ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
	public ToolBar toolbar;
	
	public GuiSpellTree(Player thePlayer) {
		super(null);
		addButton(0, 700, 100, 800, "back");
		addButton(1200, 700, 1440, 800, "next player");
		//offensive spells
		addSpellButton(450, 150, 550, 250, Spells.fireball(), 100);
		addSpellButton(450, -250, 550, -150, Spells.iceball(), 100);
		addSpellButton(500, 450, 600, 550, Spells.needleshot(), 100);
		addSpellButton(700, -150, 800, -50, Spells.darkvortex(), 100);
		addSpellButton(700, 50, 800, 150, Spells.lightvortex(), 100);
		addSpellButton(1000, 500, 1100, 600, Spells.rock(), 100);
		addSpellButton(1000, -600, 1100, -500, Spells.shock(), 100);
		addSpellButton(1100, -400, 1200, -300, Spells.poisoncloud(), 100);
		addSpellButton(1100, 300, 1200, 400, Spells.windshot(), 100);
		addSpellButton(1050, -50, 1150, 50, Spells.grassknife(), 100);
		addSpellButton(1400, 200, 1500, 300, Spells.flash(), 200);
		addSpellButton(1400, -300, 1500, -200, Spells.fireblast(), 200);
		addSpellButton(1550, -450, 1650, -350, Spells.heatwave(), 300);
		//offensive strikes
		addSpellButton(-550, 150, -450, 250, Spells.fireslash(), 100);
		addSpellButton(-550, -250, -450, -150, Spells.waterslash(), 100);
		addSpellButton(-1100, 500, -1000, 600, Spells.rockslash(), 100);
		addSpellButton(-1100, -600, -1000, -500, Spells.electricslash(), 100);
		addSpellButton(-900, 300, -800, 400, Spells.airslash(), 100);
		addSpellButton(-900, -400, -800, -300, Spells.earthslash(), 100);
		addSpellButton(-1200, 300, -1100, 400, Spells.darkslash(), 100);
		addSpellButton(-1200, -400, -1100, -300, Spells.lightslash(), 100);
		addSpellButton(-1150, -50, -1050, 50, Spells.poisonslash(), 100);
		addSpellButton(-1400, -800, -1300, -700, Spells.blooddrain(), 200);
		addSpellButton(-850, -50, -750, 50, Spells.powerattack(), 200);
		//infecting spells
		addSpellButton(1350, 750, 1450, 850, Spells.burn(), 300);
		addSpellButton(100, 650, 200, 750, Spells.poison(), 100);
		addSpellButton(150, 900, 250, 1000, Spells.wither(), 200);
		//positive spells
		addSpellButton(-200, -850, -100, -750, Spells.strengthboost(), 100);
		addSpellButton(100, -850, 200, -750, Spells.heal(), 200);
		addSpellButton(150, -1050, 250, -950, Spells.shield(), 200);
		addSpellButton(-250, -1050, -150, -950, Spells.iceblade(), 100);
		addSpellButton(-50, -1200, 50, -1100, Spells.empower(), 100);
		//rectangles
		addRectangle(-600, -25, 500, 50);
		addRectangle(100, -25, 500, 50);
		addRectangle(-25, -600, 50, 500);
		addRectangle(-25, 100, 50, 500);
		player = thePlayer;
		player.refreshModels();
		checkLearnedSpells();
	}
	
	public void addSpellButton(int minX, int minY, int maxX, int maxY, Spell spell, int price){
		buttons.add(new SpellButton(minX, minY, maxX, maxY, spell, normalFont, Color.BLACK, price));
	}
	
	@Override
	public void click(Vector2f mouse){
		super.click(mouse);
		if(MainClass.currentGUI != this)
			return;
		int t = 0;
		while(t < buttons.size()){
			if(buttons.get(t) instanceof SpellButton){
				SpellButton but = (SpellButton) buttons.get(t);
				if(but.isHit(mouse)){
					if(but.canActivate(player)){
						but.activate(player);
					}
					return;
				}
			}
			++t;
		}
	}
	
	/*
	@Override
	public void paint(Graphics gr){
		gr.setColor(Color.BLUE);
		gr.fillRect(0, 0, MainClass.getWidth(), MainClass.getHeight());
		drawEntity(gr, player, new Point(factorX(1300), factorY(400)));
		int t = 0;
		while(t < buttons.size()){
			if(buttons.get(t) instanceof SpellButton){
				((SpellButton)buttons.get(t)).paint(gr, this);
			}
			++t;
		}
		t = 0;
		while(t < rectangles.size()){
			rectangles.get(t).paint(gr);
			++t;
		}
		t = 0;
		while(t < buttons.size()){
			if(buttons.get(t) != null && !(buttons.get(t) instanceof SpellButton)){
				buttons.get(t).paint(gr);
			}
			++t;
		}
		t = 0;
		if(toolbar != null){
			toolbar.paint(gr);
		}
	}
	*/
	
	@Override
	public void buttonClicked(Button button){
		if(button.name.matches("back")){
			MainClass.currentGUI = new GuiMenu();
			return;
		}
		if(button.name.matches("next player")){
			MainClass.currentGUI = new GuiSpellTree(MainClass.nextPlayer(player));
		}
	}
	
	@Override
	public void update(){
		toolbar = null;
		Vector2f mouse = MainClass.getMousePosition();
		if(mouse.x > 0.875f)
			camera.x += 0.0125f;
		if(mouse.x < -0.875f)
			camera.x -= 0.0125f;
		if(mouse.y > 0.875f)
			camera.y += 0.0125f;
		if(mouse.y < -0.875f)
			camera.y -= 0.0125f;
		mouse.x += camera.x;
		mouse.y += camera.y;
		int t = 0;
		while(t < buttons.size()){
			if(buttons.get(t) instanceof SpellButton){
				SpellButton but = (SpellButton) buttons.get(t);
				if(but.isHit(mouse)){
					toolbar = but.getToolBar();
					toolbar.min = MainClass.getMousePosition();
				}
			}
			++t;
		}
	}
	
	@Override
	public void keyPressed(){
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			camera.x -= factorX(20);
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			camera.y -= factorY(20);
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			camera.x += factorX(20);
		if(Keyboard.isKeyDown(Keyboard.KEY_UP))
			camera.y += factorY(20);
	}
	
	protected void checkLearnedSpells(){
		int t = 0;
		ArrayList<Spell> s = player.learnedSpells;
		while(t < buttons.size()){
			if(buttons.get(t) instanceof SpellButton){
				SpellButton but = (SpellButton) buttons.get(t);
				int t2 = 0;
				while(t2 < s.size()){
					if(but.spell.name.matches(s.get(t2).name)){
						but.isBought = true;
					}
					++t2;
				}
			}
			++t;
		}
	}
	
	public void addRectangle(int minX, int minY, int width, int height){
		rectangles.add(new Rectangle(minX, minY, width, height, Color.YELLOW, new Color(50, 0, 50)));
	}
	
	public class Rectangle {
		
		public Color color;
		public Color color2;
		
		public int x;
		public int y;
		public int width;
		public int height;
		
		public Rectangle(int posX, int posY, int rectWidth, int rectHeight, Color rectColor, Color drawColor){
			x = (posX);
			y = (posY);
			width = (rectWidth);
			height = (rectHeight);
			color = rectColor;
			color2 = drawColor;
		}
		
		/*
		public void paint(Graphics gr){
			int dx = x - camera.x + MainClass.getWidth() / 2;
			int dy = y - camera.y + MainClass.getHeight() / 2;
			if(dx + width < 0 || dx > MainClass.getWidth() || dy + height < 0 || dy > MainClass.getHeight())
				return;
			gr.setColor(color);
			gr.fillRect(dx, dy, width, height);
			gr.setColor(color2);
			gr.drawRect(dx, dy, width, height);
		}
		*/
	}
	
	public class SpellButton extends Button {
		
		public final Spell spell;
		public final int price;
		
		public boolean isBought;
		public boolean canBuy;
		
		public SpellButton(int minx, int miny, int maxx, int maxy, Spell theSpell, Font drawFont, Color drawColor, int spellPrice) {
			super(minx, miny, maxx, maxy, theSpell.getTexture(), theSpell.name, drawFont, drawColor);
			spell = theSpell;
			price = spellPrice;
		}
		
		public Spell getSpell(){
			return spell.clone();
		}
		
		/*
		public void paint(Graphics gr, GuiSpellTree gui){
			Game game = MainClass.game;
			int mx = minX - gui.camera.x + MainClass.getWidth() / 2;
			int my = minY - gui.camera.y + MainClass.getHeight() / 2;
			if(mx + (maxX - minX) < 0 || mx > MainClass.getWidth() || my + (maxY - minY) < 0 || my > MainClass.getHeight())
				return;
			if(isBought){
				gr.setColor(Color.GREEN);
			}
			else if(gui.player.xp >= price){
				gr.setColor(Color.YELLOW);
				canBuy = true;
			}
			else {
				canBuy = false;
				gr.setColor(Color.RED);
			}
			gr.fillRect(mx - factorX(20), my - factorY(20), maxX - minX + factorX(40), maxY - minY + factorY(40));
			gr.setColor(new Color(100, 0, 100));
			gr.fillRect(mx, my, maxX - minX, maxY - minY);
			gr.drawImage(image, mx, my, maxX - minX, maxY - minY, null);
			gr.setColor(Color.YELLOW);
			gr.drawRect(mx, my, maxX - minX, maxY - minY);
		}
		*/
		
		public ToolBar getToolBar(){
			ToolBar bar = new ToolBar(spell.getElement().color).setSize(320, 100);
			spell.addInfo(bar);
			if(isBought){
				bar.text.add("");
				bar.text.add("You have already");
				bar.text.add("learned this spell.");
			}
			else if(canBuy){
				bar.text.add("");
				bar.text.add("Price: " + price + " xp");
				bar.text.add("Click to learn!");
			}
			else {
				bar.text.add("");
				bar.text.add("Price: " + price + " xp");
				bar.text.add("You don't have");
				bar.text.add("enough xp.");
				bar.increaseSize(0, 20);
			}
			return bar;
		}
		
		public void activate(Player player){
			isBought = true;
			player.learnedSpells.add(getSpell());
			player.xp -= price;
		}
		
		public boolean canActivate(Player player){
			return !isBought && player.xp >= price;
		}
	}
}
