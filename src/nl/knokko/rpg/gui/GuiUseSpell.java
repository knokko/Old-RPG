package nl.knokko.rpg.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.ArrayList;

import nl.knokko.rpg.entities.players.Player;
import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.spells.ButtonSpell;
import nl.knokko.rpg.spells.Spell;
import nl.knokko.rpg.utils.Resources;

public class GuiUseSpell extends Gui {
	
	public final Player player;
	public final ArrayList<Spell> spells;
	
	public Spell spell;

	public GuiUseSpell(Player user) {
		super(null);
		player = user;
		player.refreshModels();
		spells = player.getFreeSpells();
		addSpellButtons();
	}
	
	/*
	@Override
	public void paint(Graphics gr){
		gr.setColor(Color.BLUE);
		gr.fillRect(0, 0, MainClass.getWidth(), MainClass.getHeight());
		super.paint(gr);
		if(spell != null){
			gr.setColor(Color.GREEN);
			gr.setFont(font());
			int x = factorX(1250);
			gr.drawString(MainClass.player.currentHealth + "/" + MainClass.player.maxHealth, x, factorY(80));
			gr.drawString(MainClass.player2.currentHealth + "/" + MainClass.player2.maxHealth, x, factorY(230));
			if(MainClass.player3 != null)
				gr.drawString(MainClass.player3.currentHealth + "/" + MainClass.player3.maxHealth, x, factorY(380));
			gr.setColor(new Color(200, 0, 200));
			gr.drawString(MainClass.player.currentMana + "/" + MainClass.player.maxMana, x, factorY(140));
			gr.drawString(MainClass.player2.currentMana + "/" + MainClass.player2.maxMana, x, factorY(290));
			if(MainClass.player3 != null)
				gr.drawString(MainClass.player3.currentMana + "/" + MainClass.player3.maxMana, x, factorY(440));
		}
		drawEntity(gr, player, new Point(factorX(400), factorY(300)));
	}
	*/
	
	@Override
	public void buttonClicked(Button button){
		if(button.name.matches("back"))
			MainClass.currentGUI = new GuiMenu();
		else if(button.name.matches("back to spell select"))
			addSpellButtons();
		else if(button.name.matches("next player"))
			MainClass.currentGUI = new GuiUseSpell(MainClass.nextPlayer(player));
		else if(button.id >= 0 && player.currentMana >= spells.get(button.id).mana){
			spell = spells.get(button.id);
			addPlayerButtons();
		}
		else if(button.id < 0 && player.currentMana >= spell.mana){
			Player target = null;
			if(button.id == -1)
				target = MainClass.player;
			if(button.id == -2)
				target = MainClass.player2;
			if(button.id == -3)
				target = MainClass.player3;
			if(target != null){
				spell.setTarget(target);
				player.currentMana -= spell.mana;
				spell.attack();
			}
		}
	}
	
	@Override
	public Cursor getCursor(){
		return Resources.getCursor("wand");
	}
	
	protected void clearButtons(){
		buttons = new ArrayList<Button>();
		addButton(1300, 750, 1400, 850, "back");
		addButton(1300, 600, 1550, 700, "next player");
	}
	
	protected void addSpellButtons(){
		clearButtons();
		spell = null;
		int y = 0;
		while(y < spells.size()){
			buttons.add(new ButtonSpell(200, y * 60 + 50, 300, y * 60 + 100, new Font("TimesRoman", Font.PLAIN, 20), player.currentMana >= spells.get(y).mana ? Color.BLACK : Color.RED, y, spells.get(y)));
			++y;
		}
	}
	
	protected void addPlayerButtons(){
		clearButtons();
		addButton(1000, 50, 1200, 150, MainClass.player.getName(), -1);
		addButton(1000, 200, 1200, 300, MainClass.player2.getName(), -2);
		if(MainClass.player3 != null)
			addButton(1000, 350, 1200, 450, MainClass.player3.getName(), -3);
		addButton(200, 100, 600, 200, "back to spell select");
	}
	
	protected void addButton(int minX, int minY, int maxX, int maxY, String text, int id){
		buttons.add(new Button(minX, minY, maxX, maxY, "sprites/buttons/button.png", text, font(), Color.BLACK, id));
	}
}
