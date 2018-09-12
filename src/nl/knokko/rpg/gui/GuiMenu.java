package nl.knokko.rpg.gui;

import java.awt.*;

import nl.knokko.rpg.main.Game;

public class GuiMenu extends Gui{
	
	public GuiMenu(Game game) {
		super(game, "");
		addButton(1200, 0, 1400, 100, "inventory");
		addButton(1200, 110, 1500, 210, "character info");
		addButton(1200, 220, 1400, 320, "quests");
		addButton(1200, 330, 1300, 430, "map");
		addButton(1200, 440, 1320, 540, "back");
		addButton(1200, 550, 1300, 650, "save");
		addButton(1200, 660, 1500, 760, "save and quit");
		addButton(1200, 770, 1300, 880, "help");
		addButton(100, 0, 300, 100, "spells");
		addButton(100, 110, 400, 210, "stats upgrade");
		addButton(100, 220, 450, 320, "elemental stats");
		addButton(100, 700, 350, 800, "options");
		addButton(100, 500, 300, 600, "use spell");
		buttons.add(new Button(650, 50, 950, 150, "sprites/buttons/button.png", "menu", new Font("TimesRoman", 0, 100), Color.BLACK));
	}
	
	public void buttonClicked(Button button){
		if(button.name.matches("character info"))
			game.currentGUI = new GuiCharacterInfo(game, game.player);
		if(button.name.matches("inventory"))
			game.currentGUI = new GuiPlayerInventory(game, game.player);
		if(button.name.matches("elemental stats"))
			game.currentGUI = new GuiElementStats(game, game.player);
		if(button.name.matches("save and quit"))
			game.close(true);
		if(button.name.matches("back"))
			game.currentGUI = null;
		if(button.name.matches("stats upgrade"))
			game.currentGUI = new GuiStatsUpgrade(game, game.player);
		if(button.name.matches("save")){
			game.saveData();
			game.currentGUI = null;
		}
		if(button.name.matches("spells"))
			game.currentGUI = new GuiSpellTree(game, game.player);
		if(button.name.matches("help"))
			game.currentGUI = new GuiHelpMenu(game);
		if(button.name.matches("quests"))
			game.currentGUI = new GuiQuests(game);
		if(button.name.matches("options"))
			game.currentGUI = new GuiOptions(game);
		if(button.name.matches("map"))
			game.currentGUI = new GuiAreaMap(game, game.world);
		if(button.name.matches("use spell"))
			game.currentGUI = new GuiUseSpell(game, game.player);
	}
	
	@Override
	public void paint(Graphics gr){
		gr.setColor(Color.BLUE);
		gr.fillRect(0, 0, game.getWidth(), game.getHeight());
		super.paint(gr);
	}
}
