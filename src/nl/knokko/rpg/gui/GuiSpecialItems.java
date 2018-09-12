package nl.knokko.rpg.gui;

import nl.knokko.rpg.main.MainClass;

public class GuiSpecialItems extends Gui {

	public GuiSpecialItems() {
		super("sprites/gui/special items.png");
		addButton(750, 780, 1070, 880, "main inventory");
		addButton(1250, 780, 1500, 880, "blue prints");
	}
	
	/*
	@Override
	public void paint(Graphics gr){
		gr.setColor(Color.GREEN);
		gr.fillRect(0, 0, MainClass.getWidth(), MainClass.getHeight());
		gr.setColor(Color.YELLOW);
		gr.fillRect(0, 0, factorX(100), MainClass.getHeight());
		gr.fillRect(factorX(100), 0, MainClass.getWidth(), factorY(100));
		gr.fillRect(factorX(1200), factorY(100), factorX(400), factorY(800));
		gr.fillRect(factorX(100), factorY(800), factorX(1100), factorY(100));
		super.paint(gr);
		int t = 0;
		int x = 170;
		int y = 130;
		while(t < MainClass.specialItems.items.size()){
			Image image = Resources.getImage("sprites/special items/" + MainClass.specialItems.items.get(t) + ".png");
			gr.drawImage(image, factorX(x), factorY(y), factorX(120), factorY(120), null);
			x += 150;
			if(x > 1100){
				x = 150;
				y += 150;
			}
			++t;
		}
	}
	*/
	
	@Override
	public void buttonClicked(Button button){
		if(button.name.matches("main inventory"))
			MainClass.currentGUI = new GuiPlayerInventory(MainClass.player);
		if(button.name.matches("blue prints"))
			MainClass.currentGUI = new GuiBluePrints();
	}
}
