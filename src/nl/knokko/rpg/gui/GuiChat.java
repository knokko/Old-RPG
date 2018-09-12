package nl.knokko.rpg.gui;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;

import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.utils.Resources;
import nl.knokko.rpg.utils.SpecialText;

public class GuiChat extends Gui {
	
	public static final GuiChat foid_forest = new GuiChat(Game.game, true, new SpecialText("So...", "This is the Foid Forest.","It doesn't look dangerous...", "It looks like a peaceful place."), new SpecialText("There aren't many safe places since the demons came.", "Let's go, I don't like this place.", "And the miners might be in danger.", "The cave should be in the North-East."), new SpecialText("Well, I think there is some interesting stuff here.", "I had like to check this place.")).setName("foid_forest").setSpeakers(new String[]{"Bart", "Chomper", "Bart"});
	public static final GuiChat miay_cave = new GuiChat(Game.game, true, new SpecialText("This is miay cave, the cave where the miners are lost.", "It looks like more dangerous than Foid Forest.", "Let's find the miners and get out of here."), new SpecialText("We could look for the miners...", "But we can also look for some treasures.", "I think they will be really expensive."), new SpecialText("Come on Bart, we are here to save the miners.", "We aren't here to become rich.")).setName("miay_cave").setSpeakers(new String[]{"Chomper", "Bart", "Chomper"});
	public static final GuiChat miay_cave_3 = new GuiChat(Game.game, true, new SpecialText("This looks like an extremely dangerous place.", "It's very unlikely the miners are here.", "They didn't tell anything about red rocks or lava."), new SpecialText("I think the miners are on the east side of the cave.", "We are done with the west side.")).setName("miay_cave_3").setSpeakers(new String[]{"Bart", "Chomper"});
	public static final GuiChat lost_plains_1 = new GuiChat(Game.game, true, new SpecialText("I believe this is called the lost plains.", "Dangerous demons have claimed this place.", "Nobody has been here in hundreds of years."), new SpecialText("Miay Cave is in the North-East of Foid Forest.", "I don't know what we are going to find here, but not the ", "miners. Let's return to Foid Forest."), new SpecialText("I think there will be good loot in this place,", "why wouldn't we explore it?"), new SpecialText("It's too dangerous here, if we go in, the miners aren't", "the only who are in trouble!")).setName("lost_plains_1").setSpeakers(new String[]{"Bart", "Chomper", "Bart", "Chomper"});
	public static final GuiChat lost_plains_2 = new GuiChat(Game.game, true, new SpecialText("We are in the lost plains, a very dangerous place.", "I'm afraid this is the only way to reach Ruff."), new SpecialText("We are with 3 man now, we might be able to pass this", "place."), new SpecialText("Don't forget to loot this place, we may need the money for", "weapons and armor."), new SpecialText("You may be right this time, but there is more than loot", "in this world."), new SpecialText("Come on, let's go!")).setName("lost_plains_2").setSpeakers(new String[]{"Richard", "Chomper", "Bart", "Chomper", "Richard"});
	public static final GuiChat ruff = new GuiChat(Game.game, true, new SpecialText("I didn't even know there were other villages in this world.", "And this one seems to be even richer than Runia."), new SpecialText("I said the shaman called me, do you believe me now?"), new SpecialText("Ok, ok, I believe you."), new SpecialText("Let's stop talking and look for the shaman.")).setName("ruff").setSpeakers(new String[]{"Bart", "Richard", "Bart", "Chomper"});
	
	public static ArrayList<GuiChat> conversations = new ArrayList<GuiChat>();
	
	public final boolean cancel;
	public boolean paintTheWorld;
	public final SpecialText[] texts;
	
	public String speaker;
	public String[] speakers;
	public String name;
	
	public Gui finishGui;
	public Point base;
	
	public int index;
	
	protected int cooldown;
	
	public static void loadData(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Resources.getLoadFile() + "/conversations.txt"));
			String line = reader.readLine();
			while(line != null){
				conversations.add(fromString(line));
				line = reader.readLine();
			}
			reader.close();
		} catch(Exception ex){
			Game.console.println("Failed to load the conversations; all conversations will be added:");
			ex.printStackTrace(Game.console);
			Game.console.println();
			addAllConversations();
		}
	}
	
	public static void addAllConversations(){
		int t = 0;
		while(t < GuiChat.class.getFields().length){
			Field field = GuiChat.class.getFields()[t];
			try {
				if(field.get(null) instanceof GuiChat)
					conversations.add((GuiChat) field.get(null));
			} catch (Exception e) {}
			++t;
		}
	}
	
	public static void saveStaticData(){
		try {
			PrintWriter writer = new PrintWriter(Resources.getSaveFile() + "/conversations.txt");
			int t = 0;
			while(t < conversations.size()){
				writer.println(conversations.get(t).name);
				++t;
			}
			writer.close();
		} catch(Exception ex){
			Game.console.println("GuiChat.saveStaticData(): Failed to save the conversations:");
			Game.console.println();
			ex.printStackTrace(Game.console);
			Game.console.println();
		}
	}
	
	public static GuiChat fromString(String chat){
		try {
			return (GuiChat) GuiChat.class.getField(chat).get(null);
		} catch(Exception ex){
			Game.console.println("Failed to get conversation " + chat);
			Game.console.println();
			ex.printStackTrace(Game.console);
			return null;
		}
	}
	
	public static boolean hasConversation(String chat){
		return hasConversation(fromString(chat));
	}
	
	public static boolean hasConversation(GuiChat chat){
		return conversations.contains(chat);
	}

	public GuiChat(Game theGame, boolean canCancel, boolean paintWorld, Point basePoint, SpecialText... messages) {
		super(theGame, null);
		cancel = canCancel;
		texts = messages;
		paintTheWorld = paintWorld;
		base = basePoint;
		addButton(base.x + 1250, base.y + 10, base.x + 1400, base.y + 90, "next");
		addButton(base.x + 1250, base.y + 110, base.x + 1400, base.y + 190, "back");
		if(cancel)
			addButton(base.x + 1250, base.y + 210, base.x + 1400, base.y + 290, "quit");
	}
	
	public GuiChat(Game game, boolean cancel, boolean paintWorld, SpecialText...texts){
		this(game, cancel, paintWorld, new Point(100, 500), texts);
	}
	
	public GuiChat(Game game, boolean cancel, SpecialText... specialTexts){
		this(game, cancel, false, specialTexts);
	}
	
	@Override
	public void buttonClicked(Button button){
		if(button.name.matches("next"))
			next();
		if(button.name.matches("back"))
			back();
		if(button.name.matches("quit"))
			escapePressed();
	}
	
	@Override
	public void escapePressed(){
		if(cancel)
			finish();
	}
	
	@Override
	public void paint(Graphics gr){
		if(paintTheWorld){
			gr.setColor(game.world.backGround);
			gr.fillRect(0, 0, game.getWidth(), game.getHeight());
			game.world.paint(gr);
			paintTheWorld = false;
		}
		super.paint(gr);
		gr.setColor(Color.BLUE);
		gr.fillRect(factorX(base.x), factorY(base.y + 50), factorX(1200), factorY(350));
		gr.setColor(Color.BLACK);
		gr.drawRect(factorX(base.x), factorY(base.y + 50), factorX(1200), factorY(350));
		texts[index].paint(gr, new Point(factorX(base.x + 20), factorY(base.y + 100)));
		gr.setColor(new Color(150, 0, 150));
		gr.fillRect(factorX(base.x), factorY(base.y), factorX(1200), factorY(50));
		gr.setColor(Color.BLACK);
		gr.drawRect(factorX(base.x), factorY(base.y), factorX(1200), factorY(50));
		gr.setFont(font());
		gr.setColor(texts[index].color);
		String speak = speaker;
		if(speak == null && speakers != null && index < speakers.length)
			speak = speakers[index];
		if(speak == null)
			speak = "";
		gr.drawString(index + 1 + " / " + texts.length + "    " + speak, factorX(base.x + 100), factorY(base.y + 40));
	}
	
	public void keyPressed(boolean[] event){
		if(cooldown > 0){
			--cooldown;
			return;
		}
		if(event[78] || event[32]){
			next();
			cooldown = 8;
		}
		if(event[66]){
			back();
			cooldown = 8;
		}
	}
	
	@Override
	public boolean canClose(){
		return cancel;
	}
	
	public void finish(){
		game.currentGUI = finishGui;
		if(name != null)
			conversations.remove(this);
		game.controller.timeOut = 10;
	}
	
	protected void next(){
		if(index + 1 < texts.length)
			++index;
		else
			finish();
	}
	
	protected void back(){
		if(index > 0)
			--index;
	}
	
	@Override
	public GuiChat clone(){
		return new GuiChat(game, paintTheWorld, cancel, texts);
	}
	
	public GuiChat setSpeaker(String theSpeaker){
		speaker = theSpeaker;
		return this;
	}
	
	public GuiChat setSpeakers(String[] speaker){
		speakers = speaker;
		return this;
	}
	
	public GuiChat setName(String n){
		name = n;
		return this;
	}
	
	public GuiChat setFinishGui(Gui finish){
		finishGui = finish;
		return this;
	}
}
