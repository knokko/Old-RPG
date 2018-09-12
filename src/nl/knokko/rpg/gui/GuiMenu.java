package nl.knokko.rpg.gui;

import java.awt.Color;
import java.awt.Font;

import nl.knokko.rpg.main.MainClass;

public class GuiMenu extends Gui{
	
	public GuiMenu() {
		super("");
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
			MainClass.currentGUI = new GuiCharacterInfo(MainClass.player);
		if(button.name.matches("inventory"))
			MainClass.currentGUI = new GuiPlayerInventory(MainClass.player);
		if(button.name.matches("elemental stats"))
			MainClass.currentGUI = new GuiElementStats(MainClass.player);
		if(button.name.matches("save and quit"))
			MainClass.close(true);
		if(button.name.matches("back"))
			MainClass.currentGUI = null;
		if(button.name.matches("stats upgrade"))
			MainClass.currentGUI = new GuiStatsUpgrade(MainClass.player);
		if(button.name.matches("save")){
			MainClass.saveData();
			MainClass.currentGUI = null;
		}
		if(button.name.matches("spells"))
			MainClass.currentGUI = new GuiSpellTree(MainClass.player);
		if(button.name.matches("help"))
			MainClass.currentGUI = new GuiHelpMenu();
		if(button.name.matches("quests"))
			MainClass.currentGUI = new GuiQuests();
		if(button.name.matches("options"))
			MainClass.currentGUI = new GuiOptions();
		if(button.name.matches("map"))
			MainClass.currentGUI = new GuiAreaMap(MainClass.world);
		if(button.name.matches("use spell"))
			MainClass.currentGUI = new GuiUseSpell(MainClass.player);
	}
	
	/*
	@Override
	public void paint(Graphics gr){
		gr.setColor(Color.BLUE);
		gr.fillRect(0, 0, MainClass.getWidth(), MainClass.getHeight());
		super.paint(gr);
	}
	*/
}
