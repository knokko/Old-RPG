package nl.knokko.rpg.entities.players;

import java.awt.Point;

import nl.knokko.rpg.main.Game;

public class Chomper extends Player {

	public Chomper(Game app, Point point) {
		super(app, new Point(-10000, -1000));
	}

	@Override
	public String getName() {
		return "Chomper";
	}
}
