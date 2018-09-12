package nl.knokko.rpg.world.maps;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.entities.Anvil;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.Villager;
import nl.knokko.rpg.inventory.InventoryShop;
import nl.knokko.rpg.items.Item;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.tiles.Tiles;
import nl.knokko.rpg.utils.SpecialText;
import nl.knokko.rpg.world.Portal;
import nl.knokko.rpg.world.Shop;

public class MapGenRuniaInside extends MapGenBase {

	public MapGenRuniaInside() {
		super();
		fill(8, 8, 13, 12, 6);
		fill(42, 8, 51, 12, 6);
		fill(5, 42, 14, 46, 6);
		fill(43, 42, 52, 46, 6);
		fill(8, 76, 13, 80, 6);
		
		fill2(42, 9, 51, 9, 9);
		fill2(5, 43, 14, 43, 9);
		fill2(43, 43, 52, 43, 9);
		
		makeRect2(7, 7, 14, 13, 4);
		makeRect2(41, 7, 52, 13, 4);
		makeRect2(4, 41, 15, 47, 4);
		makeRect2(42, 41, 53, 47, 4);
		makeRect2(7, 75, 14, 81, 4);
		
		tiles2[10][13] = 7;
		tiles2[47][13] = 7;
		tiles2[9][47] = 7;
		tiles2[48][47] = 7;
		tiles2[11][81] = 7;
		
		portals.add(new Portal(new Point(300, 390), "Runia inside", new Point(390, 360), "Runia"));
		portals.add(new Portal(new Point(1410, 390), "Runia inside", new Point(900, 360), "Runia"));
		portals.add(new Portal(new Point(270, 1410), "Runia inside", new Point(450, 600), "Runia"));
		portals.add(new Portal(new Point(1440, 1410), "Runia inside", new Point(900, 600), "Runia"));
		portals.add(new Portal(new Point(330, 2430), "Runia inside", new Point(420, 1020), "Runia"));
		
		shops.add(new Shop(new Point(1410, 270), new InventoryShop(5, 2, new Item[]{Items.potion, Items.orangePotion, Items.ether, Items.orangeEther})));
		shops.add(new Shop(new Point(270, 1290), new InventoryShop(3, 4, new Item[]{Items.woodAxe, Items.pickaxe, Items.mantidBlade, Items.woodSword, Items.stoneSword, Items.miaySword, Items.torch, Items.miayRod, null, Items.woodHammer, Items.miayHammer})));
		shops.add(new Shop(new Point(1440, 1290), new InventoryShop(4, 2, new Item[]{Items.foidHelmet, Items.foidChestplate, Items.foidLeggings, Items.foidBoots, Items.miayHelmet, Items.miayChestplate, Items.miayLeggings, Items.miayBoots})));
		
		entities.add(new Anvil(new Point(1410, 1320)));
		tiles2[47][44] = Tiles.anvil.id;
		
		entities.add(new Villager(new Point(1410, 240), false));
		entities.add(new Villager(new Point(270, 1260), false));
		entities.add(new Villager(new Point(1440, 1260), false));
		entities.add(new Villager(new Point(270, 330), new SpecialText("My friend still hasn't returned from the mines.", "Save him please.")));
	}

	@Override
	public int getXSize() {
		return 300;
	}

	@Override
	public int getYSize() {
		return 300;
	}

	@Override
	public Color getBackGroundColor() {
		return Color.BLACK;
	}

	@Override
	public String getName() {
		return "Runia inside";
	}

	@Override
	public ArrayList<Entity> getEnemies() {
		return null;
	}

	@Override
	public int getMaxEnemies() {
		return 0;
	}

	@Override
	public Point getStartPoint(MapGenBase previous) {
		return new Point(300, 300);
	}
	
	@Override
	public boolean allowRandomFights(){
		return false;
	}

	@Override
	public String getFightBackGround() {
		return null;
	}

	@Override
	public String getMusic(){
		return "runia";
	}
}
