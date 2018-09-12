package nl.knokko.rpg.spells;

import java.awt.Color;
import java.awt.Font;

import nl.knokko.rpg.gui.Button;

public class ButtonSpell extends Button {
	
	public final Spell spell;

	public ButtonSpell(int minx, int miny, int maxx, int maxy, Font drawFont, Color drawColor, int buttonId, Spell spellToUse) {
		super(minx, miny, maxx, maxy, "sprites/buttons/" + (spellToUse.isPositive() ? "positive" : "negative") + " " + spellToUse.getElement() + ".png", spellToUse.name, drawFont, drawColor, buttonId);
		spell = spellToUse;
	}
}
