package nl.knokko.rpg.world.maps;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.Villager;
import nl.knokko.rpg.inventory.InventoryShop;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.utils.SpecialText;
import nl.knokko.rpg.world.Portal;

public class MapGenRuffInside {
	
	public static House smallHouse(int door){
		return new House(door, 6, 7);
	}
	
	public static House largeHouse(int door){
		return new House(door, 12, 7);
	}
	
	public static House normalHouse(int door){
		return new House(door, 8, 7);
	}
	
	public static House shamanHouse(){
		return new House(23, 13, 7);
	}
	
	private static class House extends MapGenBase{
		
		private int door;
		private int sizeX;
		private int sizeY;
		
		private House(int houseDoor, int sx, int sy){
			sizeX = sx;
			sizeY = sy;
			door = houseDoor;
			tiles = new byte[sx][sy];
			tiles2 = new byte[sx][sy];
			tiles3 = new byte[sx][sy];
			fill(0, 0, sx - 1, sy - 1, 6);
			int tile = door == 23 ? 4 : 28;
			fill2(0, 0, sx - 1, 0, tile);
			fill2(0, 0, 0, sy - 1, tile);
			fill2(0, sy - 1, sx - 1, sy - 1, tile);
			fill2(sx - 1, 0, sx - 1, sy - 1, tile);
			placePortal();
			SpecialText text = textForDoor(door);
			if(text != null)
				entities.add(new Villager.RuffVillager(new Point(60, 60), text));
			else if(door == 23)
				entities.add(Villager.ruffShaman(new Point(90, 90)));
		}
		
		@Override
		public int getXSize() {return sizeX;}

		@Override
		public int getYSize() {return sizeY;}

		@Override
		public Color getBackGroundColor() {return Color.BLACK;}

		@Override
		public String getName() {return "Ruff " + typeForDoor(door) + " " + door;}

		@Override
		public ArrayList<Entity> getEnemies() {return null;}

		@Override
		public int getMaxEnemies(){return 0;}

		@Override
		public Point getStartPoint(MapGenBase previousMap) {return new Point();}

		@Override
		public String getFightBackGround() {return null;}
		
		@Override
		public boolean allowRandomFights(){return false;}
		
		@Override
		public String getMusic(){
			return "ruff";
		}
		
		private void placePortal(){
			Point p = new Point(sizeX / 2, doorIsSouth(door) ? 6 : 0);
			tiles2[p.x][p.y] = 7;
			String type = typeForDoor(door);
			portals.add(new Portal(new Point(p.x * 30, p.y * 30), "Ruff " + type + " " + door, destinationForDoor(door), "Ruff"));
		}
	}
	
	public static class Shop extends House {
		
		public Shop(int door){
			super(door, 12, 7);
			fill2(1, 2, 10, 2, 9);
			entities.add(new Villager(new Point(180, 30), false));
			if(door == 1)
				shops.add(new nl.knokko.rpg.world.Shop(new Point(180, 60), new InventoryShop(4, 2, Items.miayHelmet, Items.miayChestplate, Items.miayLeggings, Items.miayBoots, Items.tarfHelmet, Items.tarfChestplate, Items.tarfLeggings, Items.tarfBoots)));
			if(door == 2)
				shops.add(new nl.knokko.rpg.world.Shop(new Point(180, 60), new InventoryShop(2, 3, Items.miaySword, Items.tarfSword, Items.miayHammer, null, Items.miayRod)));
			if(door == 15)
				shops.add(new nl.knokko.rpg.world.Shop(new Point(180,60), new InventoryShop(2, 2, Items.orangePotion, Items.orangeEther, Items.betterPotion, Items.betterEther)));
		}
	}
	
	public static final Point destinationForDoor(int door){
		if(door == 1)
			return new Point(2040, 660);
		if(door == 2)
			return new Point(1020, 660);
		if(door == 3 || door == 6)
			return new Point(930, 1170);
		if(door == 4 || door == 7)
			return new Point(1170, 1170);
		if(door == 5 || door == 8)
			return new Point(1440, 1170);
		if(door == 9 || door == 12)
			return new Point(1620, 1170);
		if(door == 10 || door == 13)
			return new Point(1890, 1170);
		if(door == 11 || door == 14)
			return new Point(2130, 1170);
		if(door == 15 || door == 16)
			return new Point(1050, 2160);
		if(door == 17 || door == 18)
			return new Point(1410, 2160);
		if(door == 19 || door == 20)
			return new Point(1650, 2160);
		if(door == 21 || door == 22)
			return new Point(2010, 2160);
		if(door == 23)
			return new Point(1950, 1680);
		return new Point();
	}
	
	public static final String typeForDoor(int door){
		if(door <= 2 || door == 15)
			return "shop";
		if(door == 16)
			return "largeHouse";
		if(door > 16 && door < 21)
			return "normalHouse";
		if(door == 23)
			return "shamanHouse";
		if(door > 20)
			return "largeHouse";
		return "smallHouse";
	}
	
	public static final boolean doorIsSouth(int door){
		boolean flag1 = door < 6;
		boolean flag2 = door > 8 && door < 12;
		boolean flag3 = door == 15 || door == 17;
		boolean flag4 = door == 19 || door == 21 || door == 23;
		return flag1 || flag2 || flag3 || flag4;
	}
	
	public static final SpecialText textForDoor(int door){
		if(door == 3)
			return new SpecialText("Good morning", "Wait... is it morning?");
		if(door == 5)
			return new SpecialText("Leave my house!", "Now!");
		if(door == 6)
			return new SpecialText("What are you doing in my house?");
		if(door == 8)
			return new SpecialText("Hello stranger,", "Do you like this village?");
		if(door == 9)
			return new SpecialText("The shaman lives in the wooden house.");
		if(door == 10)
			return new SpecialText("Why do I not know you?", "Where do you come from?");
		if(door == 12)
			return new SpecialText("You come from Runia?", "I didn't even know there were other villages.");
		if(door == 14){
			if(MainClass.quests.hasQuest("shaman"))
				return new SpecialText("Aren't you going to help the shaman?", "He is in his wooden house.");
			return new SpecialText("Hello, welcome in Ruff!");
		}
		if(door == 17){
			if(MainClass.quests.hasQuest("shaman"))
				return new SpecialText("The shaman called you with a magic message?", "Than there is something special.", "Such spells are hard to cast.");
			return new SpecialText("What are you doing in Ruff?");
		}
		if(door == 18)
			return new SpecialText("I really want to live in a large house.", "But they are so expensive!");
		if(door == 19)
			return new SpecialText("Why are you wearing weapons and armor in my house?");
		if(door == 21)
			return new SpecialText("I like living in a large house.");
		return null;
	}
}
