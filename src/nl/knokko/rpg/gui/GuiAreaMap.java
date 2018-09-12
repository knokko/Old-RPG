package nl.knokko.rpg.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.tiles.Chest;
import nl.knokko.rpg.tiles.Tiles;
import nl.knokko.rpg.world.World;
import nl.knokko.rpg.world.maps.MapGenBase;

public class GuiAreaMap extends Gui {
	
	public final MapGenBase map;
	public final World world;
	public boolean[][] fields;
	public ArrayList<Chest> chests;

	public GuiAreaMap(Game theGame, World worl) {
		super(theGame, null);
		map = worl.map;
		world = worl;
		chests = world.chests;
		fields = new boolean[world.worldSizeY][world.worldSizeX];
		byte[][] b = world.tiles;
		byte[][] b2 = world.tiles2;
		int x = 0;
		while(x < b.length){
			int y = 0;
			while(y < b[x].length){
				boolean z = Tiles.fromId(b[x][y]).canWalkOver;
				boolean z2 = Tiles.fromId(b2[x][y]).canCollide;
				fields[y][x] = z && z2;
				++y;
			}
			++x;
		}
		addButton(1400, 700, 1500, 800, "back");
	}
	
	@Override
	public void escapePressed(){
		saveData();
		game.currentGUI = new GuiMenu(game);
	}
	
	@Override
	public void buttonClicked(Button button){
		if(button.name.matches("back"))
			escapePressed();
	}
	
	@Override
	public void paint(Graphics gr){
		gr.setColor(Color.BLUE);
		gr.fillRect(0, 0, game.getWidth(), game.getHeight());
		int mul = 1200 / map.exploredFields[0].length;
		int mul2 = 700 / map.exploredFields.length;
		if(mul > mul2)
			mul = mul2;
		mul *= game.getWidth() / 1600.0;
		super.paint(gr);
		int y = 0;
		while(y < map.exploredFields.length){
			int x = 0;
			while(x < map.exploredFields[y].length){
				boolean z1 = map.exploredFields[y][x];
				boolean z2 = fields[y][x];
				if(z2)
					gr.setColor(Color.WHITE);
				else
					gr.setColor(Color.BLACK);
				if(!z1)
					gr.setColor(Color.BLUE);
				byte tile = map.tiles2[x][y];
				if(tile == Tiles.green_panel.id || tile == Tiles.red_panel.id || tile == Tiles.blue_panel.id || tile == Tiles.yellow_panel.id)
					gr.setColor(Color.GREEN);
				gr.fillRect(x * mul + 100, y * mul + 100, mul, mul);
				++x;
			}
			++y;
		}
		int t = 0;
		while(t < chests.size()){
			if(chests.get(t).isEmpty())
				gr.setColor(Color.GRAY);
			else
				gr.setColor(Color.YELLOW);
			Point p = chests.get(t).position;
			if(map.exploredFields[p.y / 30][p.x / 30]){
				gr.fillRect(100 - mul + (p.x / 30) * mul, 100 - mul + (p.y / 30) * mul, mul * 3, mul * 3);
				gr.setColor(Color.BLACK);
				gr.fillRect(100 + (p.x / 30) * mul, 100 + (p.y / 30) * mul, mul, mul);
			}
			++t;
		}
		t = 0;
		while(t < world.portals.size()){
			gr.setColor(new Color(250, 0, 250));
			Point p = world.portals.get(t).position;
			if(map.exploredFields[p.y / 30][p.x / 30]){
				gr.fillRect(100 - mul + (p.x / 30) * mul, 100 - mul + (p.y / 30) * mul, mul * 3, mul * 3);
				gr.setColor(Color.BLACK);
				gr.fillRect(100 + (p.x / 30) * mul, 100 + (p.y / 30) * mul, mul, mul);
			}
			++t;
		}
		gr.setColor(Color.RED);
		Point p = game.player.position;
		gr.fillRect(100 - mul + (p.x / 30) * mul, 100 - mul + (p.y / 30) * mul, mul * 3, mul * 3);
		gr.setColor(Color.BLACK);
		gr.fillRect(100 + (p.x / 30) * mul, 100 + (p.y / 30) * mul, mul, mul);
	}
}
