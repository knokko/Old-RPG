package nl.knokko.rpg.tiles;

import java.util.ArrayList;

public final class Tiles {
	
	static ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	public static final Tile air = new TileAir();
	public static final Tile grass_ground = new Tile("grass", true, false);
	public static final Tile pavement = new Tile("pavement", true, false);
	public static final TileTree tree = new TileTree("tree", 45);
	public static final Tile wooden_wall = new Tile("wooden_wall", true, false);
	public static final TileRoof roof = new TileRoof("roof", 180, 180);
	public static final Tile wooden_floor = new Tile("wooden_floor", true, false);
	public static final Tile wooden_door = new Tile("wooden_door", true, false);
	public static final Tile potion_sign = new Tile("potion_sign", false, false);
	public static final Tile table = new Tile("table", true, false);
	public static final Tile weapon_sign = new Tile("weapon_sign", false, false);
	public static final Tile armor_sign = new Tile("armor_sign", false, false);
	public static final Tile water = new Tile("water", false, false);
	public static final Tile chest = new Tile("chest", true, false);
	public static final Tile sand_path = new Tile("sand_path", true, false);
	public static final Tile hill_front = new Tile("hill front", true, false);
	public static final Tile hill_side = new Tile("hill side", true, false);
	public static final Tile hill_corner = new Tile("hill corner", true, false);
	public static final Tile rock_hole = new Tile("rock hole", true, true);
	public static final Tile rock_corner_left = new Tile("rock corner left", true, false);
	public static final Tile rock_corent_right = new Tile("rock corner right", true, false);
	public static final Tile rock = new Tile("rock", true, false);
	public static final Tile rock_dark = new Tile("rock dark", true, false);
	public static final Tile rock_light = new Tile("rock light", true, false);
	public static final Tile ladder = new Tile("ladder", false, false);
	public static final Tile wooden_fence_front = new Tile("wooden fence front", true, false);
	public static final Tile wooden_fence_side = new Tile("wooden fence side", true, false);
	public static final Tile wooden_fence_corner = new Tile("wooden fence corner", true, false);
	public static final Tile stone_wall = new Tile("stone wall", true, false);
	public static final Tile red_rock = new Tile("red rock", true, false);
	public static final Tile black_rock = new Tile("black rock", true, false);
	public static final Tile lava = new Tile("lava", false, false);
	public static final Tile dark_tree = new TileTree("dark tree", 45);
	public static final Tile dark_grass = new Tile("dark grass", true, false);
	public static final Tile white_rock = new Tile("white rock", true, false);
	public static final Tile altar = new Tile("altar", false, false);
	public static final Tile anvil = new Tile("anvil", true, false);
	public static final Tile lantern = new Tile("lantern", false, false);
	public static final Tile blue_lantern = new Tile("blue lantern", false, false);
	public static final Tile red_lantern = new Tile("red lantern", false, false);
	public static final Tile yellow_lantern = new Tile("yellow lantern", false, false);
	public static final Tile green_lantern = new Tile("green lantern", false, false);
	public static final TilePanel blue_panel = new TilePanel("blue panel");
	public static final TilePanel red_panel = new TilePanel("red panel");
	public static final TilePanel yellow_panel = new TilePanel("yellow panel");
	public static final TilePanel green_panel = new TilePanel("green panel");
	public static final Tile cyst_tile = new Tile("cyst tile", true, false);
	public static final Tile cyst_chest = new Tile("cyst chest", true, false);
	public static final Tile cyst_portal = new Tile("cyst portal", false, true);
	public static final Tile golden_bricks = new Tile("golden bricks", true, false);
	public static final Tile golden_chest = new Tile("golden chest", true, false);
	
	public static final Tile fromId(byte id){
		return tiles.get(id);
	}
}
