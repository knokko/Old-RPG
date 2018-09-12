package nl.knokko.rpg.main;

import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import javax.swing.JPanel;

public class Board extends JPanel {
	
	public Game game;
	private Color color;
	
	private int timer;
	private int maxTimer;
	protected Graphics2D g2;
	private static double factor;
	
	/**
	 * version UID?
	 */
	public static final long serialVersionUID = -3612297826402807296L;
	
	private Graphics2D g = new Graphics2D(){

		@Override
		public Graphics create() {
			return this;
		}

		@Override
		public void translate(int x, int y) {
			g2.translate(x, y);
		}

		@Override
		public Color getColor() {
			return g2.getColor();
		}

		@Override
		public void setColor(Color c) {
			g2.setColor(c);
		}

		@Override
		public void setPaintMode() {
			g2.setPaintMode();
		}

		@Override
		public void setXORMode(Color c1) {
			g2.setXORMode(c1);
		}

		@Override
		public Font getFont() {
			return g2.getFont();
		}

		@Override
		public void setFont(Font font) {
			g2.setFont(new Font(font.getName(), font.getStyle(), (int) (font.getSize() * factor)));
		}

		@Override
		public FontMetrics getFontMetrics(Font f) {
			return g2.getFontMetrics(f);
		}

		@Override
		public Rectangle getClipBounds() {
			return g2.getClipBounds();
		}

		@Override
		public void clipRect(int x, int y, int width, int height) {
			g2.clipRect(x, y, width, height);
		}

		@Override
		public void setClip(int x, int y, int width, int height) {
			g2.setClip(x, y, width, height);
		}

		@Override
		public Shape getClip() {
			return g2.getClip();
		}

		@Override
		public void setClip(Shape clip) {
			g2.setClip(clip);
		}

		@Override
		public void copyArea(int x, int y, int width, int height, int dx, int dy) {
			g2.copyArea((int) (x * factor), (int) (y * factor), (int) (width * factor), (int) (height * factor), (int) (dx * factor), (int) (dy * factor));
		}

		@Override
		public void drawLine(int x1, int y1, int x2, int y2) {
			g2.drawLine((int)(x1 * factor), (int)(y1 * factor), (int)(x2 * factor), (int)(y2 * factor));
		}

		@Override
		public void fillRect(int x, int y, int width, int height) {
			g2.fillRect((int) (x * factor), (int) (y * factor), (int) (width * factor), (int) (height * factor));
		}

		@Override
		public void clearRect(int x, int y, int width, int height) {
			g2.clearRect((int) (x * factor), (int) (y * factor), (int) (width * factor), (int) (height * factor));
		}

		@Override
		public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
			g2.drawRoundRect((int) (x * factor), (int) (y * factor), (int) (width * factor), (int) (height * factor), (int)(arcWidth * factor), (int) (arcHeight * factor));
		}

		@Override
		public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
			g2.fillRoundRect((int) (x * factor), (int) (y * factor), (int) (width * factor), (int) (height * factor), (int) (arcWidth * factor), (int) (arcHeight * factor));
		}

		@Override
		public void drawOval(int x, int y, int width, int height) {
			g2.drawOval((int) (x * factor), (int) (y * factor), (int) (width * factor), (int) (height * factor));
		}

		@Override
		public void fillOval(int x, int y, int width, int height) {
			g2.fillOval((int) (x * factor), (int) (y * factor), (int) (width * factor), (int) (height * factor));
		}

		@Override
		public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
			g2.drawArc((int) (x * factor), (int) (y * factor), (int) (width * factor), (int) (height * factor), startAngle, arcAngle);
		}

		@Override
		public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
			g2.fillArc((int) (x * factor), (int) (y * factor), (int) (width * factor), (int) (height * factor), startAngle, arcAngle);
		}

		@Override
		public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
			int t = 0;
			while(t < nPoints - 1){
				drawLine(xPoints[t], yPoints[t], xPoints[t + 1], yPoints[t + 1]);
				++t;
			}
		}

		@Override
		public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
			drawPolyline(xPoints, yPoints, nPoints);
			drawLine(xPoints[nPoints - 1], yPoints[nPoints - 1], xPoints[0], yPoints[0]);
		}

		@Override
		public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
			int t = 0;
			while(t < nPoints){
				xPoints[t] *= factor;
				yPoints[t] *= factor;
				++t;
			}
			g2.fillPolygon(xPoints, yPoints, nPoints);
		}

		@Override
		public void drawString(String str, int x, int y) {
			g2.drawString(str, (int) (x * factor), (int) (y * factor));
		}

		@Override
		public void drawString(AttributedCharacterIterator iterator,int x, int y) {
			g2.drawString(iterator, (int) (x * factor), (int) (y * factor));
		}

		@Override
		public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
			return g2.drawImage(img, (int) (x * factor), (int) (y * factor), Board.this);
		}

		@Override
		public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
			return g2.drawImage(img, (int) (x * factor), (int) (y * factor), (int) (width * factor), (int) (height * factor), observer);
		}

		@Override
		public boolean drawImage(Image img, int x, int y,Color bgcolor, ImageObserver observer) {
			return g2.drawImage(img, (int) (x * factor), (int) (y * factor), bgcolor, observer);
		}

		@Override
		public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
			return g2.drawImage(img, (int) (x * factor), (int) (y * factor), (int) (width * factor), (int) (height * factor), bgcolor, observer);
		}

		@Override
		public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
			return g2.drawImage(img, (int) (dx1 * factor), (int) (dy1 * factor), (int) (dx2 * factor), (int) (dy2 * factor), (int) (sx1 * factor), (int) (sy1 * factor), (int) (sx2 * factor), (int) (sy2 * factor), observer);
		}

		@Override
		public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
			return g2.drawImage(img, (int) (dx1 * factor), (int) (dy1 * factor), (int) (dx2 * factor), (int) (dy2 * factor), (int) (sx1 * factor), (int) (sy1 * factor), (int) (sx2 * factor), (int) (sy2 * factor), bgcolor, observer);
		}

		@Override
		public void dispose() {
			g2.dispose();
		}

		@Override
		public void draw(Shape s) {
			g2.draw(s);
		}

		@Override
		public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) {
			return g2.drawImage(img, xform, obs);
		}

		@Override
		public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {
			g2.drawImage(img, op, (int) (x * factor), (int) (y * factor));
		}

		@Override
		public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
			g2.drawRenderedImage(img, xform);
		}

		@Override
		public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
			g2.drawRenderableImage(img, xform);
		}

		@Override
		public void drawString(String str, float x, float y) {
			g2.drawString(str, (float) (x * factor), (float) (y * factor));
		}

		@Override
		public void drawString(AttributedCharacterIterator iterator, float x, float y) {
			g2.drawString(iterator, (float) (x * factor), (float) (y * factor));
		}

		@Override
		public void drawGlyphVector(GlyphVector g, float x, float y) {
			g2.drawGlyphVector(g, (float) (x * factor), (float) (y * factor));
		}

		@Override
		public void fill(Shape s) {
			g2.fill(s);
		}

		@Override
		public boolean hit(Rectangle rect, Shape s, boolean onStroke) {
			return g2.hit(rect, s, onStroke);
		}

		@Override
		public GraphicsConfiguration getDeviceConfiguration() {
			return g2.getDeviceConfiguration();
		}

		@Override
		public void setComposite(Composite comp) {
			g2.setComposite(comp);
		}

		@Override
		public void setPaint(Paint paint) {
			g2.setPaint(paint);
		}

		@Override
		public void setStroke(Stroke s) {
			g2.setStroke(s);
		}

		@Override
		public void setRenderingHint(Key hintKey, Object hintValue) {
			g2.setRenderingHint(hintKey, hintValue);
		}

		@Override
		public Object getRenderingHint(Key hintKey) {
			return g2.getRenderingHint(hintKey);
		}

		@Override
		public void setRenderingHints(Map<?, ?> hints) {
			g2.setRenderingHints(hints);
		}

		@Override
		public void addRenderingHints(Map<?, ?> hints) {
			g2.addRenderingHints(hints);
		}

		@Override
		public RenderingHints getRenderingHints() {
			return g2.getRenderingHints();
		}

		@Override
		public void translate(double tx, double ty) {
			g2.translate(tx, ty);
		}

		@Override
		public void rotate(double theta) {
			g2.rotate(theta);
		}

		@Override
		public void rotate(double theta, double x, double y) {
			g2.rotate(theta, x * factor, y * factor);
		}

		@Override
		public void scale(double sx, double sy) {
			g2.scale(sx, sy);
		}

		@Override
		public void shear(double shx, double shy) {
			g2.shear(shx, shy);
		}

		@Override
		public void transform(AffineTransform Tx) {
			g2.transform(Tx);
		}

		@Override
		public void setTransform(AffineTransform Tx) {
			g2.setTransform(Tx);
		}

		@Override
		public AffineTransform getTransform() {
			return g2.getTransform();
		}

		@Override
		public Paint getPaint() {
			return g2.getPaint();
		}

		@Override
		public Composite getComposite() {
			return g2.getComposite();
		}

		@Override
		public void setBackground(Color color) {
			g2.setBackground(color);
		}

		@Override
		public Color getBackground() {
			return g2.getBackground();
		}

		@Override
		public Stroke getStroke() {
			return g2.getStroke();
		}

		@Override
		public void clip(Shape s) {
			g2.clip(s);
		}

		@Override
		public FontRenderContext getFontRenderContext() {
			return g2.getFontRenderContext();
		}
		
	};
	
	public static double factor(){
		return factor;
	}
	
	public Board(Game theGame){
		game = theGame;
	}
	
	public void setColor(Color color, int time){
		this.color = color;
		timer = time;
		maxTimer = time;
	}
	
	@Override
	public void paint(Graphics gr){
		if(!game.getTitle().isEmpty()){
			double factorX = game.width() / 1600.0 >= 1 ? game.width() / 1600 : game.width() / 1600.0;
			double factorY = game.height() / 900.0 >= 1 ? game.height() / 900 : game.height() / 900.0;
			factor = Math.min(factorX, factorY);
			g2 = (Graphics2D) gr;
			if(game.currentGUI == null){
				g.setColor(game.world.backGround);
				g.fillRect(0, 0, game.getWidth(), game.getHeight());
				game.world.paint(g);
			}
			else if(game.currentGUI != null){
				game.currentGUI.paint(g);
				game.controller.paint(g);
			}
			if(color != null){
				int a = (int) (timer / (double)maxTimer * color.getAlpha());
				g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), a));
				g.fillRect(0, 0, game.getWidth(), game.getHeight());
				--timer;
				if(timer <= 0){
					color = null;
					maxTimer = 0;
				}
			}
		}
	}
}
