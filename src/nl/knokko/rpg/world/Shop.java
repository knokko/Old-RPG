package nl.knokko.rpg.world;

import java.awt.Point;

import nl.knokko.rpg.entities.players.Player;
import nl.knokko.rpg.gui.GuiShop;
import nl.knokko.rpg.inventory.InventoryShop;
import nl.knokko.rpg.main.Game;

public class Shop {
	
	public Point position;
	public InventoryShop shop;
	
	public Shop(Point point, InventoryShop inventory) {
		position = point;
		shop = inventory;
	}
	
	public void open(Player player){
		Game.game.currentGUI = new GuiShop(Game.game, player, shop);
	}
	
	public void open(){
		open(Game.game.player);
	}

}
