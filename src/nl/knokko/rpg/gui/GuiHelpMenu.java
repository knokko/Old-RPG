package nl.knokko.rpg.gui;

import java.awt.*;
import java.util.ArrayList;

import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.utils.SpecialText;

public class GuiHelpMenu extends Gui {
	
	public byte index;
	
	public SpecialText mainText;
	public SpecialText currentText;
	
	public SpecialText inventoryText;
	public SpecialText trainingText;
	public SpecialText weaponsText;
	public SpecialText shopsText;
	public SpecialText controlText;
	public SpecialText battleText;
	public SpecialText mapText;
	public SpecialText forgeText;
	public SpecialText tips;
	
	public GuiHelpMenu() {
		super("sprites/gui/help.png");
		addFirstButtons();
		mainText = new SpecialText(Color.WHITE, new Font("TimesRoman", 1, 30), "This is the helpguide for beginners.",
		"Click on a button at the bottom of the screen for more info about that subject.");
		initInventory();
		initTraining();
		initWeapons();
		initShops();
		initControls();
		initBattle();
		initMap();
		initForge();
		initTips();
	}
	
	/*
	@Override
	public void paint(Graphics gr){
		super.paint(gr);
		mainText.paint(gr, new Point(factorX(400), factorY(40)));
		if(currentText != null){
			currentText.paint(gr, new Point(factorX(10), factorY(150)));
		}
	}
	*/
	
	@Override
	public void buttonClicked(Button button){
		if(button.name.matches("back"))
			MainClass.currentGUI = new GuiMenu();
		if(button.name.matches("inventory"))
			currentText = inventoryText;
		if(button.name.matches("training"))
			currentText = trainingText;
		if(button.name.matches("weapons"))
			currentText = weaponsText;
		if(button.name.matches("shops"))
			currentText = shopsText;
		if(button.name.matches("controls"))
			currentText = controlText;
		if(button.name.matches("battles"))
			currentText = battleText;
		if(button.name.matches("map"))
			currentText = mapText;
		if(button.name.matches("next"))
			addSecondButtons();
		if(button.name.matches("previous"))
			addFirstButtons();
		if(button.name.matches("forging"))
			currentText = forgeText;
		if(button.name.matches("tips"))
			currentText = tips;
	}
	
	public void addFirstButtons(){
		buttons = new ArrayList<Button>();
		addButton(100, 750, 200, 850, "back");
		addButton(250, 750, 450, 850, "inventory");
		addButton(470, 750, 660, 850, "training");
		addButton(680, 750, 870, 850, "weapons");
		addButton(890, 750, 1020, 850, "shops");
		addButton(1040, 750, 1250, 850, "controls");
		addButton(1270, 750, 1420, 850, "battles");
		addButton(1450, 750, 1550, 850, "next");
	}
	
	public void addSecondButtons(){
		buttons = new ArrayList<Button>();
		addButton(100, 750, 200, 850, "back");
		addButton(250, 750, 450, 850, "forging");
		addButton(470, 750, 570, 850, "map");
		addButton(590, 750, 690, 850, "tips");
		addButton(1350, 750, 1550, 850, "previous");
	}
	
	private void initInventory(){
		inventoryText = new SpecialText(new Color(100, 100, 0), new Font("TimesRoman", 1, factorX(35)), "Your inventory is the place where you store your items.",
		"Your inventory has a lot of slots, every slot can store a large number of the same item.",
		"If you left click on a slot, you will take the item in the slot and put the item on the mouse in the slot.",
		"If you right click on a slot and you are holding multiple items, you will put 1 item in the slot.",
		"",
		"The 5 slots on the right are for your weapon and armor, the top one is for your weapon.",
		"",
		"If you hold your mouse on an item, you will see some information about the item.",
		"Some items will show more information than other items.",
		"",
		"You can use potions by taking a potion on the mouse and click on 'use potion'.");
	}
	
	private void initTraining(){
		trainingText = new SpecialText(new Color(0, 150, 0), new Font("TimesRoman", 1, factorX(35)),
		"In this game, you can train your characters so they will become stronger",
		"Your characters will get xp after winning a battle.",
		"You can open the menu with the Esc(ape) button.",
		"You should see 3 buttons on the left side of the screen.",
		"You can upgrade your elemental stats, your basic stats and you can learn spells.",
		"Upgrading (elemental) stats will reduce your xp by 100.",
		"Spells can cost any amount of xp.",
		"You can switch the player by clicking on the next player button.",
		"", "",
		"You should train your characters differently because not every monster has the same weakness.",
		"So I should train one character as mage, and the other as warrior.",
		"",
		"Warrior characters should be trained in strength and health.",
		"Mage characters should be trained in spirit, mana and health.",
		"The elemental stats are made for training a character to reduce and empower damage of one element.");
	}
	
	private void initWeapons(){
		weaponsText = new SpecialText(Color.GRAY, new Font("TimesRoman", 1, factorX(40)), "Weapons and armor are important items in this MainClass.",
		"Weapons multiply your damage by its power, so weapons make fights much easier to win.",
		"Every time you get damage, your armor will divine the damage through its value, ", 
		"so you can survive much longer.",
		"",
		"You can buy armor at special shops, they are marked with a chestplate sign",
		"You can buy weapons at shops which are marked with a sword sign.");
	}
	
	private void initShops(){
		shopsText = new SpecialText(new Color(150, 150, 0), new Font("TimesRoman", 1, factorX(35)),
		"Shops are houses where you can buy useful things like weapons, armor and potions.",
		"You need money to buy things, you can get money for winning battles, selling loot and find it in chests.",
		"You can open shops and chests with the space bar if you are facing it.",
		"",
		"You can buy items by clicking on it in the shop.",
		"Right clicking on the shop will sell your current item if you have one at your mouse.");
	}
	
	private void initControls(){
		controlText = new SpecialText(Color.WHITE, new Font("TimesRoman", 1, factorX(45)),
		"You can move your character with the arrows.",
		"You can open shops and chests with the space bar.",
		"You can open your menu with Esc(ape), and click on buttons to activate them.",
		"But you probably already knew the last part because you opened this menu.");
	}
	
	private void initBattle(){
		battleText = new SpecialText(Color.RED, new Font("TimesRoman", 1, factorX(23)),
		"In most maps, you will enter random battles with monsters.",
		"This will not happen in places like villages.",
		"",
		"If you win a battle, you can get loot money and items.",
		"You will always get experience.",
		"If you run away, you will not get anything.",
		"",
		"You can click on 5 buttons if one of your characters is on turn:",
		"You can use 'attack' to use your default attack on a monster you can choose.",
		"you can use 'special' to use a special move your character has learned, this will consume mana.",
		"You can use an item in the battle with the 'use item' button.",
		"You can use 'wait' to skip the turn of your current character.",
		"And you can run, than you will not get anything.",
		"",
		"After selecting a move or item, you will have to choose a target.",
		"You can choose your own characters if the move has a positive effect,",
		"you can choose your enemies if the move has a negative effect.",
		"",
		"If you click on a character during the battle, you will see his stats.",
		"You can also view his elemental stats with the 'elemental stats' button.",
		"",
		"If a character is out of health, it will not be able to fight anymore and disappear.",
		"If a character is out of mana, it will not be able to cast spells anymore",
		"If all monsters are out of health, you have won the battle and can take the loot.",
		"Your characters will reappear after the battle if you have won.",
		"If all your characters are out of health, you have lost the battle and the game will continue where you have saved for the last time.");
	}
	
	private void initMap(){
		mapText = new SpecialText(Color.yellow, new Font("TimesRoman", 1, factorX(40)), 
				"In this game, your character creates a map of any place he comes.", 
				"You can open this map with the button in the menu or with the 'm' key.", 
				"",
				"The red point on the map marks your location.", 
				"The yellow points are chests.",
				"The grey points are empty chests.",
				"The blue points are doors or exits.", 
				"You can walk over the white area of the map.", 
				"You can't walk over the black area of the map.", 
				"You haven't discovered the blue areas yet.");
	}
	
	private void initForge(){
		forgeText = new SpecialText(new Color(200, 100, 0), new Font("TimesRoman", 1, factorX(35)),
				"Several shops have an anvil, you can use this to forge items.",
				"You can open it with the space bar if your character looks to it.",
				"",
				"The anvil has a 5x5 grid, you can place and take items in this grid.",
				"It looks like your inventory, but you can't store items there.",
				"",
				"If you put the right items in the right shape, the lines at the items will become blue.",
				"That means you can forge an item, if you click on the 'forge' button and",
				"you don't have an item on your mouse, the forged items will appear on your mouse.",
				"",
				"You need simple materials to forge, you can loot them from monsers or",
				"find them in chests.",
				"",
				"Forging can be cheaper than buying items in the shop and you can forge items",
				"whick you can't buy in the shop.");
	}
	
	private void initTips(){
		tips = new SpecialText(new Color(0, 150, 0), new Font("TimesRoman", 1, factorX(29)),
				"Here are some tips to start with the game:",
				"",
				"Almost every monster in the begin is weak against fire,",
				"so buy fire spells in the spells menu (open it with the 's' key).",
				"",
				"Use one of your characters as mage and one character as fighter.",
				"Some monsters in the feature will be hard to kill with magic or brute force,",
				"that's why you will need them both.",
				"Buy a rod for the mage and give an axe or sword to the fighter.",
				"Learn your mage fireball and learn your fighter fireslash.",
				"",
				"Don't run away for every fight, this will keep your characters weak.",
				"And running away for a boss won't help you at all.",
				"",
				"Use the most of your starting money for armor.",
				"There is a huge difference between a little armor and no armor at all!",
				"",
				"Use the altar in the South-East of Runia to heal.",
				"",
				"Use your map (press the 'm' key) to find chests and entrances.",
				"It will also help you not to get lost.");
	}
}
