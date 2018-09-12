package nl.knokko.rpg;

public class Attack {
	
	public final Element element;
	public final boolean ranged;
	public final boolean magic;
	
	public Attack(Element attackElement, boolean isRanged, boolean isMagic) {
		element = attackElement;
		ranged = isRanged;
		magic = isMagic;
	}
}
