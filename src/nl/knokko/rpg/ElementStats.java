package nl.knokko.rpg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ElementStats {
	
	protected ElementStat[] stats;
	
	public ElementStats() {
		stats = new ElementStat[]{new ElementStat(Element.NORMAL), new ElementStat(Element.AIR), new ElementStat(Element.EARTH), new ElementStat(Element.FIRE), new ElementStat(Element.WATER), new ElementStat(Element.LIGHTNING), new ElementStat(Element.POISON), new ElementStat(Element.LIGHT), new ElementStat(Element.DARK), new ElementStat(Element.ROCK)};
	}
	
	public int getResistance(Element element){
		return stats[element.ordinal()].resistance;
	}
	
	public int getPower(Element element){
		return stats[element.ordinal()].power;
	}
	
	public void increasePower(Element element){
		stats[element.ordinal()].power += 10;
	}
	
	public void increaseResistance(Element element){
		stats[element.ordinal()].resistance += 10;
	}
	
	public void setPower(Element element, int power){
		stats[element.ordinal()].power = power;
	}
	
	public void setResistance(Element element, int resistance){
		stats[element.ordinal()].resistance = resistance;
	}
	
	@Override
	public String toString(){
		String s = "";
		int counter = 0;
		while(counter < stats.length){
			s += stats[counter] + "   ";
			++counter;
		}
		return s;
	}
	
	public void saveData(PrintWriter writer){
		int t = 0;
		while(t < stats.length){
			writer.println(stats[t].element + ":power:" + stats[t].power);
			writer.println(stats[t].element + ":resistance:" + stats[t].resistance);
			++t;
		}
	}
	
	public void loadData(BufferedReader reader) throws NumberFormatException, IOException{
		int t = 0;
		while(t < stats.length){
			int l = stats[t].element.toString().length() + 7;
			stats[t].power = Integer.decode(reader.readLine().substring(l));
			stats[t].resistance = Integer.decode(reader.readLine().substring(l + 5));
			++t;
		}
	}
}
