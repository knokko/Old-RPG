package nl.knokko.rpg.utils;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public final class BackGrounds {
	
	public static final String base = "sprites/background/";
	
	public static final String forest = base + "forest.png";
	public static final String miay_cave = base + "miay cave.png";
	public static final String plains = base + "plains.png";
	public static final String red_cave = base + "red cave.png";
	public static final String dark_forest = base + "dark forest.png";
	public static final String cyst = base + "cyst.png";
	public static final String goblin_tomb_1 = base + "goblin tomb 1.png";
	public static final String goblin_tomb_2 = base + "goblin tomb 2.png";
	public static final String goblin_cave = base + "goblin cave.png";
	
	public static void createFoidForest(){
		Random random = new Random();
		int minTreeLine = 85;
		int maxTreeLine = 95;
		BufferedImage image = new BufferedImage(400, 225, BufferedImage.TYPE_INT_RGB);
		int x = 0;
		while(x < image.getWidth()){
			int y = 0;
			while(y < image.getHeight()){
				if(y < minTreeLine)
					image.setRGB(x, y, new Color(0, 38, 255).getRGB());
				else
					image.setRGB(x, y, new Color(50 + random.nextInt(50), 155 + random.nextInt(100), random.nextInt(40)).getRGB());
				++y;
			}
			++x;
		}
		int y = minTreeLine;
		while(y <= maxTreeLine){
			x = -random.nextInt(10);
			while(x < image.getWidth()){
				addTree(image, new Color(90, 35, 0), new Color(0, 132, 0), x, y, random);
				x += 40 + random.nextInt(20);
			}
			++y;
		}
		try {
			ImageIO.write(image, "PNG", new File("generated.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void addTree(BufferedImage image, Color wood, Color leaves, int x, int y, Random random){
		int width = 5 + random.nextInt(5);
		int height = 30 + random.nextInt(20);
		int radius = width / 3;
		addFourAngle(image, new Color(wood.getRed(), wood.getGreen(), wood.getBlue() + 100), wood, new Point(x, y), new Point(x + width, y - 1 + random.nextInt(3)), new Point(x + width, y - height), new Point(x, y - height));
		addCircle(image, new Color(leaves.getRed() + 100, leaves.getGreen(), leaves.getBlue()), leaves, new Point(x + width / 2, (int) (y - height + 0.6 * radius)), radius);
	}
	
	private static void addCircle(BufferedImage image, Color lines, Color fill, Point center, int radius){
		int maxX = center.x + radius;
		int maxY = center.y + radius;
		int x = center.x - radius;
		while(x <= maxX){
			int y = center.y - radius;
			while(y <= maxY){
				if(x >= 0 && y >= 0 && x < image.getWidth() && y < image.getHeight()){
					int distance = getInt(Math.hypot(center.x - x, center.y - y));
					if(distance == radius)
						image.setRGB(x, y, lines.getRGB());
					if(distance < radius)
						image.setRGB(x, y, fill.getRGB());
				}
				++y;
			}
			++x;
		}
	}
	
	private static void addFourAngle(BufferedImage image, Color lines, Color fill, Point p1, Point p2, Point p3, Point p4){
		int[][] xdata = new int[image.getHeight()][2];
		int[][] ydata = new int[image.getWidth()][2];
		int y = 0;
		while(y < xdata.length){
			xdata[y][0] = Integer.MAX_VALUE;
			xdata[y][1] = Integer.MIN_VALUE;
			++y;
		}
		int x = 0;
		while(x < ydata.length){
			ydata[x][0] = Integer.MAX_VALUE;
			ydata[x][1] = Integer.MIN_VALUE;
			++x;
		}
		addLine(image, lines, p1, p2, xdata, ydata);
		addLine(image, lines, p2, p3, xdata, ydata);
		addLine(image, lines, p3, p4, xdata, ydata);
		addLine(image, lines, p4, p1, xdata, ydata);
		y = 0;
		while(y < xdata.length){
			if(xdata[y][0] == Integer.MAX_VALUE)
				xdata[y][0] = -1;
			if(xdata[y][1] == Integer.MIN_VALUE)
				xdata[y][1] = image.getWidth();
			++y;
		}
		x = 0;
		while(x < ydata.length){
			if(ydata[x][0] == Integer.MAX_VALUE)
				ydata[x][0] = -1;
			if(ydata[x][1] == Integer.MIN_VALUE)
				ydata[x][1] = image.getHeight();
			++x;
		}
		int startY = Math.min(Math.min(p1.y, p2.y), Math.min(p3.y, p4.y));
		int maxY = Math.max(Math.max(p1.y, p2.y), Math.max(p3.y, p4.y));
		int minX = Math.min(Math.min(p1.x, p2.x), Math.min(p3.x, p4.x));
		int maxX = Math.max(Math.max(p1.x, p2.x), Math.max(p3.x, p4.x));
		y = startY;
		while(y <= maxY){
			x = minX;
			while(x <= maxX){
				if(x > xdata[y][0] && x < xdata[y][1] && y > ydata[x][0] && y < ydata[x][1])
					image.setRGB(x, y, fill.getRGB());
				++x;
			}
			++y;
		}
	}
	
	private static void addLine(BufferedImage image, Color lines, Point p1, Point p2, int[][] xdata, int[][] ydata){
		int dx = p2.x - p1.x;
		int dy = p2.y - p1.y;
		if(dx != 0){
			float rcx = dy / (float)dx;
			int x = Math.min(0, dx);
			while(x <= Math.max(0, dx)){
				int y = getInt(p1.y + x * rcx);
				int ax = p1.x + x;
				if(ax >= 0 && ax < image.getWidth() && y >= 0 && y < image.getHeight()){
					image.setRGB(ax, y, lines.getRGB());
					if(ax < xdata[y][0])
						xdata[y][0] = ax;
					if(ax > xdata[y][1])
						xdata[y][1] = ax;
					if(y < ydata[ax][0])
						ydata[ax][0] = y;
					if(y > ydata[ax][1])
						ydata[ax][1] = y;
				}
				if(ax < 0)
					xdata[y][0] = -1;
				if(ax >= image.getWidth())
					xdata[y][1] = image.getWidth();
				if(y < 0)
					ydata[ax][0] = -1;
				if(y >= image.getHeight())
					ydata[ax][1] = image.getHeight();
				++x;
			}
		}
		if(dy != 0){
			float rcY = dx / (float)dy;
			int y = Math.min(0, dy);
			while(y <= Math.max(0, dy)){
				int x = getInt(p1.x + y * rcY);
				int ay = p1.y + y;
				if(ay >= 0 && ay < image.getHeight() && x >= 0 && x < image.getWidth()){
					image.setRGB(x, ay, lines.getRGB());
					if(x < xdata[ay][0])
						xdata[ay][0] = x;
					if(x > xdata[ay][1])
						xdata[ay][1] = x;
					if(ay < ydata[x][0])
						ydata[x][0] = ay;
					if(ay > ydata[x][1])
						ydata[x][1] = ay;
				}
				if(x < 0)
					xdata[ay][0] = -1;
				if(x >= image.getWidth())
					xdata[ay][1] = image.getWidth();
				if(ay < 0)
					ydata[x][0] = -1;
				if(ay >= image.getHeight())
					ydata[x][1] = image.getHeight();
				++y;
			}
		}
	}
	
	private static int getInt(double f){
		double decimals = f - (int)f;
		if(decimals >= 0.5)
			return (int)f + 1;
		if(decimals <= -0.5)
			return (int)f - 1;
		return (int)f;
	}
}
