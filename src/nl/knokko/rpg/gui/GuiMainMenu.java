package nl.knokko.rpg.gui;

import java.awt.*;

import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.utils.*;

public class GuiMainMenu extends Gui {

	public GuiMainMenu(Game theGame) {
		super(theGame, "");
		String s = "sprites/buttons/button.png";
		Font font = new Font("TimesRoman", 0, 50);
		buttons.add(new Button(650, 50, 950, 150, s, "play game", font, Color.BLACK));
		buttons.add(new Button(650, 200, 950, 300, s, "delete data", font, Color.RED));
		buttons.add(new Button(650, 350, 950, 450, s, "help menu", font, Color.BLACK));
		buttons.add(new Button(650, 500, 950, 600, s, "quit game", font, Color.BLACK));
		JukeBox.playMusic("main menu", true);
	}
	
	@Override
	public void buttonClicked(Button button){
		if(button.name.matches("play game")){
			JukeBox.stop();
			game.currentGUI = new GuiSaves(game);
				/*
				game.currentGUI = new GuiChat(game, false, true, 
						new SpecialText("hello player,", "I will explain the controls here:", "Click on 'next' to read the next message.", "Click on 'back' to read the previous message.", "Click now on 'next'."),
						new SpecialText("If you have read all the messages, you can play the game.", "You can walk with the arrows on your keyboard.", "You can open chests and talk with people with the space ", "bar."),
						new SpecialText("You can open the menu with the escape button,", "you will need the menu for a lot of things.", "Click on the buttons in the menu to open them.", "Check the help button for more info."),
						new SpecialText("You can see information about your character in ", "the character info.", "You can open it with the button in the menu of the 'c' key.", "Use the 'next player' button to see info about your ", "other characters."),
						new SpecialText("You can store all your items in your inventory.", "You can open it with the button in the menu or the 'i' key", "You can move items with your mouse.", "The slots on the right are for your weapon and armor.", "Use the 'next player' button to switch character."),
						new SpecialText("You will automatically make a map of any location ", "you visit. You can open the map with the button ", "in the menu or the 'm' key. You are the red point on the ", "map. The yellow points are chests and the purple points are ", "doors. You can walk over the white areas, but not over the ", "black areas. The blue areas are the areas you haven't ", "explored yet."),
						new SpecialText("It is important to become stronger in this game.", "You will probably fight a lot.", "You can buy weapons or upgrade your characters."),
						new SpecialText("A way to become stronger is learning spells.", "This will allow you to do more than your basic attack.", "You can learn them in the spell gui.", "You can open it with the 'spells' button or the 's' key."),
						new SpecialText("The most important way for becoming stronger is ", "upgrading your stats. You can do this in the stats gui.", "You can open it with the 'stats upgrade' button or the 'u' ", "key. Click on the buttons in the gui to upgrade your stats."),
						new SpecialText("Another way to become stronger is improving your", "elemental stats. This will empower your attacks or improve ", "your defense for the specified element. You can open the", "gui with the 'elemental stats' button or the 'e' key."),
						new SpecialText("The last way to become stronger is buying armor", "and weapons. You can do this in shops, they are usually in ", "villages like this. Shops look like houses, but they have a ", "marker above the door. Walk to the place where you can", "face the shopkeeper and use the space bar to trade."),
						new SpecialText("Left click on the items of the shopkeeper to buy them.", "Right click with an item in your hand on the slots of the ", "shopkeeper to sell it. Use the inventory of your other ", "characters with the 'next player' button. Leave the shop ", "when you have bought weapons and open your inventory ", "and put them in the slots on the right."),
						new SpecialText(new Font("TimesRoman", 0, 48), "In most areas you will get fights with monsters. Kill them!", "Click on 'attack' and choose a target to use your basic attack.", "If you have spells, you can use them with the 'special' button.", "Use the 'items' button to use your potions.", "The 'wait' button will skip your turn.", "You can use the 'run' button when playing in easy or normal."),
						new SpecialText("The goal of this game is completing quests.", "You can find them in the menu or with the 'q' key.", "Click on a quest to see more info about the quest.", "Completing a quest will give you the reward and you will ", "often become further in the story."),
						new SpecialText("The game will automatically create a map while you are", "walking through areas.", "These maps are very  useful for finding chests and exits.", "Open the current map with the 'm' key or 'map' button."),
						new SpecialText(new Font("TimesRoman", 0, 48), "There are a few options in this game:", "speed: The speed of the fights and your character.", "difficulty: The power of the monsters and some other things.", "Open it with the 'options' button or the 'o' key."),
						new SpecialText("You can heal your characters with an altar.", "They are within a circle of white stones.", "Use the space bar on the core to heal your characters.", "The first altar is located in the South-East of Runia"),
						new SpecialText("Your first mission is saving the miners.", "They are unarmed in Miay Cave.", "Check your quests for more information."),
						new SpecialText("You are now in the village Runia.", "There are shops here for armor, weapons and potions.", "Succes! And don't forget the help button in the menu.", "(You can open the menu with escape.)", "Click on 'next' to start the game.")).setSpeaker("tutorial");
				*/
		}
		if(button.name.matches("delete data"))
			game.currentGUI = new GuiDeleteData();
		if(button.name.matches("help menu"))
			game.currentGUI = new GuiHelp();
		if(button.name.matches("quit game"))
			game.close(false);
	}
	
	@Override
	public void paint(Graphics gr){
		gr.setColor(Color.BLUE);
		gr.fillRect(0, 0, game.getWidth(), game.getHeight());
		super.paint(gr);
	}
	
	@Override
	public void escapePressed(){
		game.close(false);
	}
	
	@Override
	public boolean canClose(){
		return false;
	}
	
	public class GuiDeleteData extends Gui {
		
		public SpecialText text;
		
		public GuiDeleteData() {
			super(GuiMainMenu.this.game, "");
			initText();
			addButton(450, 500, 600, 600, "yes");
			addButton(1000, 500, 1150, 600, "no");
		}
		
		@Override
		public void paint(Graphics gr){
			gr.setColor(Color.BLACK);
			gr.fillRect(factorX(400), factorY(225), factorX(800), factorY(450));
			gr.setColor(Color.RED);
			gr.drawRect(factorX(400), factorY(225), factorX(800), factorY(450));
			text.paint(gr, new Point(factorX(420), factorY(280)));
			gr.setColor(Color.RED);
			gr.fillRect(factorX(450), factorY(500), factorX(150), factorY(100));
			gr.setColor(new Color(100, 0, 0));
			gr.drawRect(factorX(450), factorY(500), factorX(150), factorY(100));
			gr.setColor(Color.GREEN);
			gr.fillRect(factorX(1000), factorY(500), factorX(150), factorY(100));
			gr.setColor(new Color(0, 100, 0));
			gr.drawRect(factorX(1000), factorY(500), factorX(150), factorY(100));
			gr.setFont(new Font("TimesRoman", 0, factorX(80)));
			gr.setColor(Color.BLACK);
			gr.drawString("yes", factorX(470), factorY(570));
			gr.drawString("no", factorX(1035), factorY(570));
		}
		
		@Override
		public void buttonClicked(Button button){
			if(button.name.matches("yes")){
				Resources.removeData();
				game.close(false);
			}
			if(button.name.matches("no"))
				game.currentGUI = GuiMainMenu.this;
		}
		
		@Override
		public void escapePressed(){
			game.currentGUI = GuiMainMenu.this;
		}

		@Override
		public boolean canClose(){
			return false;
		}

		private void initText(){
			text = new SpecialText(Color.RED, new Font("TimesRoman", 0, factorX(45)),
			"Are you sure you want to delete all data?",
			"All your progress will be lost.",
			"You will not be able to undo this unless", " you made a back-up.",
			"The game will close to remove all data.");
		}
	}
	
	public class GuiHelp extends GuiHelpMenu {

		public GuiHelp() {
			super(GuiMainMenu.this.game);
		}
		
		@Override
		public void buttonClicked(Button button){
			if(button.name.matches("back")){
				game.currentGUI = GuiMainMenu.this;
			}
			else {
				super.buttonClicked(button);
			}
		}
		
		@Override
		public void escapePressed(){
			game.currentGUI = GuiMainMenu.this;
		}

		@Override
		public boolean canClose(){
			return false;
		}
	}
}
