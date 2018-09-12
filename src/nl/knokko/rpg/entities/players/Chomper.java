package nl.knokko.rpg.entities.players;

import java.awt.Point;

public class Chomper extends Player {

	public Chomper(Point point) {
		super(new Point(-10000, -1000));
	}

	@Override
	public String getName() {
		return "Chomper";
	}
}
