package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import nl.knokko.rpg.tiles.Tiles;

public class Screen extends JPanel {

	private static final long serialVersionUID = 7928350004222931055L;
	
	@Override
	public void paint(Graphics gr){
		int width = Application.app.getWidth();
		int height = Application.app.getHeight();
		Point min = Application.gameToScreenPosition(new Point(0,0));
		Point max = Application.gameToScreenPosition(new Point(Application.tiles1.length * 30, Application.tiles1[0].length * 30));
		gr.setColor(Color.BLUE);
		gr.fillRect(0, 0, width, height);
		gr.setColor(Color.BLACK);
		int y = min.y + 30;
		if(!Application.pressedKeys[KeyEvent.VK_B]){
			while(y < max.y){
				gr.drawLine(min.x, y, max.x, y);
				gr.drawLine(min.x, y - 1, max.x, y - 1);
				y += 30;
			}
			int x1 = min.x + 30;
			while(x1 < max.x){
				gr.drawLine(x1, min.y, x1, max.y);
				gr.drawLine(x1 - 1, min.y, x1 - 1, max.y);
				x1 += 30;
			}
		}
		y = 0;
		while(y < Application.tiles1[0].length){
			int x = 0;
			while(x < Application.tiles1.length){
				Tiles.fromId(Application.tiles1[x][y]).paint2(gr, new Point(x * 30, y * 30));
				++x;
			}
			++y;
		}
		y = 0;
		while(y < Application.tiles2[0].length){
			int x = 0;
			while(x < Application.tiles2.length){
				Tiles.fromId(Application.tiles2[x][y]).paint2(gr, new Point(x * 30, y * 30));
				++x;
			}
			++y;
		}
		y = 0;
		while(y < Application.tiles3[0].length){
			int x = 0;
			while(x < Application.tiles3.length){
				Tiles.fromId(Application.tiles3[x][y]).paint2(gr, new Point(x * 30, y * 30));
				++x;
			}
			++y;
		}
		gr.setColor(Color.RED);
		gr.drawLine(min.x, min.y, max.x, min.y);
		gr.drawLine(min.x, min.y, min.x, max.y);
		gr.drawLine(max.x, min.y, max.x, max.y);
		gr.drawLine(min.x, max.y, max.x, max.y);
		if(Application.selectedPoint != null){
			Point s = Application.gameToScreenPosition(Application.selectedPoint);
			s.x /= 30;
			s.y /= 30;
			s.x *= 30;
			s.y *= 30;
			gr.setColor(new Color(100, 0, 100));
			gr.drawRect(s.x, s.y, 29, 29);
		}
		Point m = Application.mouse.getLocation();
		m.x -= 8;
		m.y -= 30;
		m.x /= 30;
		m.x *= 30;
		m.y /= 30;
		m.y *= 30;
		gr.setColor(Color.YELLOW);
		gr.drawRect(m.x, m.y, 29, 29);
		gr.setColor(Color.BLACK);
		gr.drawRect(0, 0, width, 100);
		gr.setColor(Color.GREEN);
		gr.fillRect(1, 1, width - 1, 99);
		gr.setColor(Color.BLACK);
		gr.drawRect(175, 25, 50, 50);
		gr.drawRect(275, 25, 50, 50);
		gr.drawRect(775, 25, 50, 50);
		gr.drawRect(875, 25, 50, 50);
		gr.drawRect(930, 25, 100, 50);
		Application.texts[0].paint(gr, new Point(20, 60));
		Application.texts[1].paint(gr, new Point(420, 60));
		gr.drawString("+", 187, 68);
		gr.drawString("-", 290, 63);
		gr.drawString("+", 787, 68);
		gr.drawString("" + Application.currentTile, 940, 68);
		gr.drawString("-", 890, 63);
		Point mouse = Application.mouseToGamePosition(Application.mouse);
		gr.drawString("x: " + mouse.x, 1400, 35);
		gr.drawString("y: " + mouse.y, 1400, 85);
		mouse.x /= 30;
		mouse.y /= 30;
		gr.drawString("x: " + mouse.x, 1200, 35);
		gr.drawString("y: " + mouse.y, 1200, 85);
	}
}
