package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.JFrame;

import nl.knokko.rpg.tiles.Tiles;
import nl.knokko.rpg.utils.SpecialText;
import utils.Encoder;

public class Application extends JFrame implements KeyListener, MouseListener, MouseWheelListener, MouseMotionListener{

	private static final long serialVersionUID = 6656179451134829342L;
	public static final Font font = new Font("TimesRoman", 0, 50);
	
	public static final Application app = new Application();
	public static final Screen screen = new Screen();
	
	public static final int sizeX = 200;
	public static final int sizeY = 200;
	
	public static SpecialText[] texts = new SpecialText[]{new SpecialText(Color.BLACK, font, "layer:1"), new SpecialText(Color.BLACK, font, "tile: grass")};
	
	public static byte[][] tiles1 = new byte[sizeX][sizeY];
	public static byte[][] tiles2 = new byte[sizeX][sizeY];
	public static byte[][] tiles3 = new byte[sizeX][sizeY];
	
	public static ArrayList<String> tileLines = new ArrayList<String>();
	public static ArrayList<String> chestLines = new ArrayList<String>();
	
	public static Point camera = new Point(80, 90);
	public static Point mouse = new Point();
	public static Point selectedPoint;
	
	public static PrintWriter console;
	
	public static byte currentTile = 1;
	public static byte currentLayer = 1;
	public static String currentText;
	
	public static boolean[] pressedKeys = new boolean[300];
	public static boolean[] pressedMouse = new boolean[10];
	
	public static final Point gameToScreenPosition(Point old){
		int x = old.x - camera.x + app.getWidth() / 2;
		int y = old.y - camera.y + app.getHeight() / 2;
		return new Point(x, y);
	}
	
	public static final Point gameToMousePosition(Point old){
		int x = old.x - camera.x + 8 + app.getWidth() / 2;
		int y = old.y - camera.y + 30 + app.getHeight() / 2;
		return new Point(x, y);
	}
	
	public static final Point screenToGamePosition(Point screen){
		int x = screen.x + camera.x - app.getWidth() / 2;
		int y = screen.y + camera.y - app.getHeight() / 2;
		return new Point(x, y);
	}
	
	public static final Point mouseToGamePosition(Point mouse){
		int x = mouse.x + camera.x - 8 - app.getWidth() / 2;
		int y = mouse.y + camera.y - 30 - app.getHeight() / 2;
		return new Point(x, y);
	}

	public Application() {
		setSize(1600, 900);
		addKeyListener(this);
		addMouseListener(this);
		addMouseWheelListener(this);
		addMouseMotionListener(this);
	}
	
	public static void main(String[] arguments){
		try {
			console = new PrintWriter("console.txt");
		} catch(Exception ex){
			ex.printStackTrace();
		}
		app.run();
	}
	
	public void run(){
		setVisible(true);
		add(screen);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		backUp();
		loadData();
		while(true){
			try {
				update();
				screen.repaint();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace(console);
				e.printStackTrace();
				stop();
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		Point p = e.getPoint();
		mouse = p.getLocation();
		pressedMouse[e.getButton()] = true;
		if(e.getButton() == 1){
			if(p.y >= 55 && p.y <= 105){
				if(p.x >= 183 && p.x <= 233)
					setLayer(currentLayer + 1);
				else if(p.x >= 283 && p.x <= 333)
					setLayer(currentLayer - 1);
				else if(p.x >= 783 && p.x <= 833)
					setMouseBlock((byte) (currentTile + 1));
				else if(p.x >= 883 && p.x <= 933)
					setMouseBlock((byte) (currentTile - 1));
			}
			else if(p.y > 130){
				if(selectedPoint != null){
					fill(mouseToGamePosition(mouse), selectedPoint, currentTile, currentLayer);
					pressedMouse[1] = false;
					selectedPoint = null;
				}
			}
		}
		else if(e.getButton() == 3){
			selectedPoint = mouseToGamePosition(p);
		}
	}

	public void mouseReleased(MouseEvent e) {
		pressedMouse[e.getButton()] = false;
	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void keyPressed(KeyEvent e) {
		int number = e.getKeyCode() - 48;
		if(number >= 0 && number <= 9){
			setMouseBlock((byte) number);
		}
		pressedKeys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		pressedKeys[e.getKeyCode()] = false;
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			stop();
		}
		if(e.getKeyChar() == 'm')
			tileLines.add("		//TODO this place is marked!");
	}
	
	public static void update(){
		int speed = pressedKeys[32] ? 90 : 30;
		if(pressedKeys[37])
			camera.x -= speed;
		if(pressedKeys[38])
			camera.y -= speed;
		if(pressedKeys[39])
			camera.x += speed;
		if(pressedKeys[40])
			camera.y += speed;
		if(pressedMouse[1])
			if(mouse.y > 130){
				setBlock(mouse, currentTile, currentLayer);
			}
		if(!app.isEnabled()){
			stop();
		}
	}
	
	private static void backUp(){
		File file = new File("Generated.java");
		try {
			Files.copy(file.toPath(), new File("back-up.java").toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace(console);
		}
	}
	
	public static void save(){
		try {
			PrintWriter writer = new PrintWriter("Generated.java");
			writer.println("package nl.knokko.rpg.world.maps");
			writer.println();
			writer.println("import java.awt.*;");
			writer.println("import java.util.ArrayList;");
			writer.println("import nl.knokko.rpg.entities.Entity;");
			writer.println("import nl.knokko.rpg.tiles.Chest");
			writer.println();
			writer.println("public class Generated extends MapGenBase {");
			writer.println();
			writer.println("	public Generated(){");
			writer.println("		super();");
			writer.println("		build();");
			writer.println("		placeChests();");
			writer.println("	}");
			writer.println();
			writer.println("	@Override");
			writer.println("	public int getXSize(){");
			writer.println("		return " + tiles1.length + ";");
			writer.println("	}");
			writer.println();
			writer.println("	@Override");
			writer.println("	public int getYSize(){");
			writer.println("		return " + tiles1[0].length + ";");
			writer.println("	}");
			writer.println();
			writer.println("	@Override");
			writer.println("	public Color getBackGroundColor(){");
			writer.println("		return Color.BLUE;");
			writer.println("	}");
			writer.println();
			writer.println("	@Override");
			writer.println("	public String getName(){");
			writer.println("		return getClass().getName();");
			writer.println("	}");
			writer.println();
			writer.println("	@Override");
			writer.println("	public ArrayList<Entity> getEnemies(){");
			writer.println("		ArrayList<Entity> enemies = new ArrayList<Entity>();");
			writer.println("		return enemies;");
			writer.println("	}");
			writer.println();
			writer.println("	@Override");
			writer.println("	public int getMaxEnemies(){");
			writer.println("		return 4;");
			writer.println("	}");
			writer.println();
			writer.println("	@Override");
			writer.println("	public Point getStartPoint(MapGenBase old){");
			writer.println("		return new Point();");
			writer.println("	}");
			writer.println("	");
			writer.println("	@Override");
			writer.println("	public String getMusic(){");
			writer.println("		return null;");
			writer.println("	}");
			writer.println();
			writer.println("	@Override");
			writer.println("	public String getFightBackGround(){");
			writer.println("		return BackGrounds.foid_forest;");
			writer.println("	}");
			writer.println();
			writer.println("	public void build(){");
			int i = 0;
			while(i < tileLines.size()){
				writer.println(tileLines.get(i));
				++i;
			}
			writer.println("	}");
			writer.println();
			writer.println("	public void placeChests(){");
			i = 0;
			while(i < chestLines.size()){
				writer.println(chestLines.get(i));
				++i;
			}
			writer.println("	}");
			writer.println();
			writer.println("}");
			writer.close();
			Encoder.save();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void loadData(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader("back-up.java"));
			int n = 0;
			String line = reader.readLine();
			while(n < 5){
				if(line != null && !line.isEmpty()){
					if(line.contains("fill") && line.contains("(") && line.contains(","))
						fillBlocks(line);
					else if(line.contains("tiles") && line.contains("][") && line.contains("="))
						setBlock(line);
					else if(line.contains("placeChest("))
						placeChest(line);
					n = 0;
				}
				else {
					++n;
				}
				line = reader.readLine();
			}
			reader.close();
		} catch(Exception ex){
			ex.printStackTrace(console);
			ex.printStackTrace();
		}
	}
	
	private static void placeChest(String line){
		int tile = 13;
		int index1 = line.indexOf("(");
		int index2 = line.indexOf("(", index1 + 1);
		int index3 = line.indexOf(",");
		int index4 = line.indexOf(",", index2);
		int index5 = line.indexOf("(", index2 + 1);
		int index6 = line.indexOf(",", index5);
		int index7 = line.indexOf(")");
		if(index3 != index4)
			tile = Integer.decode(line.substring(index1 + 1, index3));
		int x = Integer.decode(line.substring(index5 + 1, index6));
		int y = Integer.decode(line.substring(index6 + 1, index7).replaceAll(" ", ""));
		chestLines.add(line);
		tiles2[x / 30][y / 30] = (byte) tile;
	}
	
	private static void setBlock(String line){
		int index1 = line.indexOf("[");
		int index2 = line.indexOf("]");
		int index3 = line.indexOf("[", index1 + 1);
		int index4 = line.indexOf("]", index2 + 1);
		int index5 = line.indexOf("=");
		int index6 = line.indexOf(";");
		int x = Integer.decode(line.substring(index1 + 1, index2));
		int y = Integer.decode(line.substring(index3 + 1, index4));
		byte tile = Byte.decode(line.substring(index5 + 2, index6));
		byte layer;
		try {
			layer = Byte.decode(line.charAt(7) + "");
		} catch(Exception ex){
			layer = 1;
		}
		setBlock(gameToMousePosition(new Point(x * 30, y * 30)), tile, layer);
	}
	
	public static void setBlock(Point pos, byte tile, byte layer){
		Point point = mouseToGamePosition(pos);
		if(layer == 1){
			try {
				tiles1[point.x / 30][point.y / 30] = tile;
				tileLines.add("		tiles[" + point.x / 30 + "][" + point.y / 30 + "] = " + tile + ";");
			} catch(Exception ex){}
		}
		else if(layer == 2){
			try {
				tiles2[point.x / 30][point.y / 30] = tile;
				tileLines.add("		tiles2[" + point.x / 30 + "][" + point.y / 30 + "] = " + tile + ";");
			} catch(Exception ex){}
		}
		else if(layer == 3){
			try {
				tiles3[point.x / 30][point.y / 30] = tile;
				tileLines.add("		tiles3[" + point.x / 30 + "][" + point.y / 30 + "] = " + tile + ";");
			} catch(Exception ex){}
		}
		if(tile == 13 || tile == 47 || tile == 50){
			if(tile == 13)
				chestLines.add("		placeChest(new Chest(-1, new Point(" + point.x + ", " + point.y + ")));");
			else
				chestLines.add("		placeChest(" + tile + ", new Chest(-1, new Point(" + point.x + ", " + point.y + ")));");
		}
	}
	
	private static void fillBlocks(String line){
		int index0 = line.indexOf("(");
		int index1 = line.indexOf(",");
		int index2 = line.indexOf("," , index1 + 1);
		int index3 = line.indexOf(",", index2 + 1);
		int index4 = line.indexOf(",", index3 + 1);
		int index5 = line.indexOf(")");
		int minX = Integer.decode(line.substring(index0 + 1, index1));
		int minY = Integer.decode(line.substring(index1 + 2, index2));
		int maxX = Integer.decode(line.substring(index2 + 2, index3));
		int maxY = Integer.decode(line.substring(index3 + 2, index4));
		byte tile = Byte.parseByte(line.substring(index4 + 2, index5));
		byte layer;
		try {
			layer = Byte.parseByte(line.charAt(6) + "");
		} catch(Exception ex){
			layer = 1;
		}
		fill(new Point(minX * 30, minY * 30), new Point(maxX * 30, maxY * 30), tile, layer);
	}
	
	public static void fill(Point first, Point second, byte tile, byte layer){
		int minX = Math.min(first.x, second.x) / 30;
		int maxX = Math.max(first.x, second.x) / 30;
		int minY = Math.min(first.y, second.y) / 30;
		int maxY = Math.max(first.y, second.y) / 30;
		try {
			int x = minX;
			while(x <= maxX){
				int y = minY;
				while(y <= maxY){
					if(layer == 1)
						tiles1[x][y] = tile;
					if(layer == 2)
						tiles2[x][y] = tile;
					if(layer == 3)
						tiles3[x][y] = tile;
					++y;
				}
				++x;
			}
			if(layer == 1)
				tileLines.add("		fill(" + minX + ", " + minY + ", " + maxX + ", " + maxY + ", " + tile + ");");
			if(layer == 2)
				tileLines.add("		fill2(" + minX + ", " + minY + ", " + maxX + ", " + maxY + ", " + tile + ");");
			if(layer == 3)
				tileLines.add("		fill3(" + minX + ", " + minY + ", " + maxX + ", " + maxY + ", " + tile + ");");
		} catch(Exception ex){}
	}
	
	public static void setLayer(int layer){
		if(layer < 1){
			layer = 3;
		}
		if(layer > 3){
			layer = 1;
		}
		currentLayer = (byte) layer;
		texts[0] = new SpecialText(Color.BLACK, font, "layer:" + layer);
	}
	
	public static void setMouseBlock(byte tile){
		currentTile = tile;
		texts[1] = new SpecialText(Color.BLACK, font, "tile: " + Tiles.fromId(tile));
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getWheelRotation() < 0)
			setLayer(currentLayer + 1);
		else
			setLayer(currentLayer - 1);
	}

	public void keyTyped(KeyEvent e) {}

	public void mouseDragged(MouseEvent e) {
		mouse = e.getPoint();
	}

	public void mouseMoved(MouseEvent e) {
		mouse = e.getPoint();
	}
	
	public static void stop(){
		if(console != null)
			console.close();
		save();
		System.exit(0);
	}
	
	@Override
	public void dispose(){
		//super.dispose();
		stop();
	}
}
