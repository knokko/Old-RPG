package nl.knokko.rpg.items;

import java.util.ArrayList;

import nl.knokko.rpg.toolbars.ToolBar;

public abstract class ItemArmor extends ItemEquipment {
	
	public final int armor;
	public final int magicArmor;
	
	public final ArrayList<String> lines = new ArrayList<String>();
	
	ItemArmor(String itemName, int value, int armorValue, int magicValue) {
		super(itemName, value);
		armor = armorValue;
		magicArmor = magicValue;
	}
	
	public int getArmorValue(){
		return armor;
	}
	
	public int getMagicValue(){
		return magicArmor;
	}
	
	@Override
	public ToolBar getToolBar(){
		ToolBar bar = new ToolBar(name, "Armor", "", "Price: " + value, "", "Gives " + getArmorValue() + " normal armor.", "Gives " + getMagicValue() + " magic armor").setSize(200, 150);
		int t = 0;
		while(t < lines.size()){
			bar.text.add(lines.get(t));
			bar.increaseSize(0, 20);
			++t;
		}
		return bar;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends ItemArmor> T addText(String... text){
		int t = 0;
		while(t < text.length){
			lines.add(text[t]);
			++t;
		}
		return (T) this;
	}
	
}
