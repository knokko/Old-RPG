package nl.knokko.rpg.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.items.SpecialItems;
import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.utils.Resources;
import nl.knokko.rpg.utils.SpecialText;

public class GuiBluePrints extends Gui {
	
	public static final BluePrint sword = new BluePrint(new byte[]{0,0,2,0,0, 0,0,2,0,0, 0,0,2,0,0, 0,1,1,1,0, 0,0,1,0,0}, "sword");
	public static final BluePrint wand = new BluePrint(new byte[]{0,2,2,0,0, 0,2,2,0,0, 0,1,1,0,0, 0,1,1,0,0, 0,1,1,0,0}, "wand");
	public static final BluePrint helmet = new BluePrint(new byte[]{0,2,2,2,0, 0,2,0,2,0, 0,0,2,0,0, 0,0,0,0,0, 0,0,0,0,0}, "helmet");
	
	public static final BluePrint fromString(String blueprint){
		try {
			return (BluePrint) GuiBluePrints.class.getField(blueprint.substring(11)).get(null);
		} catch (Exception e) {
			MainClass.console.println("failed to get blue print " + blueprint + ":");
			e.printStackTrace(MainClass.console);
			MainClass.console.println();
			return null;
		}
	}
	
	public ArrayList<BluePrint> prints = new ArrayList<BluePrint>();
	public SpecialText bluePrints = new SpecialText(Color.BLUE, new Font("TimesRoman", 0, factorX(25))
			,"Blueprints show you how "
			,"to forge items with an anvil."
			,"You need wood and a material, "
			,"the material determines the power "
			,"of the item."
			,"You can find the wood and the "
			,"materials in chests."
			,"You can find anvils in some shops, "
			,"open them with the space bar.");

	public GuiBluePrints() {
		super(null);
		addButton(1250, 600, 1575, 700, "main inventory");
		addButton(1250, 750, 1520, 850, "special items");
		SpecialItems s = MainClass.specialItems;
		if(s.hasItem(SpecialItems.BLUE_PRINT_SWORD))
			prints.add(sword);
		if(s.hasItem(SpecialItems.BLUE_PRINT_WAND))
			prints.add(wand);
		if(s.hasItem(SpecialItems.BLUE_PRINT_HELMET))
			prints.add(helmet);
	}
	
	/*
	@Override
	public void paint(Graphics gr){
		gr.setColor(Color.BLUE);
		gr.fillRect(0, 0, factorX(1240), MainClass.getHeight());
		gr.setColor(Color.GREEN);
		gr.fillRect(factorX(1240), 0, factorX(400), MainClass.getHeight());
		gr.setColor(Color.YELLOW);
		gr.drawLine(factorX(1240), 0, factorX(1240), MainClass.getHeight());
		super.paint(gr);
		bluePrints.paint(gr, new Point(1250, 100));
		int t = 0;
		int x = 50;
		int y = 50;
		while(t < prints.size()){
			prints.get(t).paint(gr, factorX(x), factorY(y), factorX(200), factorY(200), true);
			++t;
			x += 250;
			if(x >= 1040){
				x = 50;
				y += 250;
			}
		}
	}
	*/
	
	@Override
	public void buttonClicked(Button button){
		if(button.name.matches("main inventory"))
			MainClass.currentGUI = new GuiPlayerInventory(MainClass.player);
		if(button.name.matches("special items"))
			MainClass.currentGUI = new GuiSpecialItems();
	}
	
	public static class BluePrint {
		
		public static Image image = Resources.getImage("sprites/blue print.png");
		public static Image empty = Resources.getImage("sprites/cursors/empty.png");
		
		public BluePrint(byte[] recipes, String display){
			recipe = recipes;
			name = display;
		}
		
		public byte[] recipe;
		public String name;
		
		public void paint(Graphics gr, int x, int y, int sizeX, int sizeY, boolean drawString){
			gr.drawImage(image, x, y, sizeX, sizeY, null);
			int sx = sizeX / 5;
			int sy = sizeY / 5;
			int y1 = 0;
			while(y1 < 5){
				int x1 = 0;
				while(x1 < 5){
					gr.drawImage(fromByte(recipe[x1 + y1 * 5]), x + (sx * x1), y + (sy * y1), sx, sy, null);
					++x1;
				}
				++y1;
			}
			if(drawString){
				gr.setFont(font());
				gr.setColor(Color.BLACK);
				gr.drawString(name, x + sx, y + sy * 6);
			}
		}
		
		private static Image fromByte(byte b){
			if(b == 1)
				return Items.wood.image;
			if(b == 2)
				return Items.miayGem.image;
			return empty;
		}
	}
}
