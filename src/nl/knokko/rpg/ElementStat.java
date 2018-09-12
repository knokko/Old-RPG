package nl.knokko.rpg;


public class ElementStat {
	
	public final Element element;
	
	public int power;
	public int resistance;
	
	public ElementStat(Element ele) {
		element = ele;
	}
	
	@Override
	public String toString(){
		return "elementstat:" + element + ",power:" + power + ",resistance:" + resistance;
	}

}
