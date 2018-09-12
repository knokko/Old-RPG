package nl.knokko.rpg.world;

import java.awt.Point;

import nl.knokko.rpg.gui.GuiChat;

public class Conversation {
	
	public GuiChat messages;
	public Point position;
	
	public Conversation(GuiChat chat, Point location) {
		messages = chat;
		position = location;
	}

}
