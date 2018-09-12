package nl.knokko.rpg.utils;

import static java.lang.Math.min;
import static org.lwjgl.opengl.GL11.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL12;

import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.render.Texture;


public final class Resources {
	
	public static final String SAVE_FOLDER = "rpg save files";
	
	private static String loadFileName;
	private static String loadFileTime;
	private static String saveFileName;
	private static String saveFileTime;
	
	private static final Map<String, BufferedImage> buffImages = new HashMap<String, BufferedImage>();
	private static final Map<String, Image> images = new HashMap<String, Image>();
	
	private static final HashMap<String, Texture> textures = new HashMap<String, Texture>();
	private static final HashMap<String, Texture> flippedTextures = new HashMap<String, Texture>();
	
	private static final HashMap<RotateName, Texture> rotatingTextures = new HashMap<RotateName, Texture>();
	private static final HashMap<RotateName, Texture> flippedRotatingTextures = new HashMap<RotateName, Texture>();
	
	private static boolean started;

	public static final void writeDataFolders(){
		new File(SAVE_FOLDER).mkdir();
		new File(SAVE_FOLDER + "/a" + saveFileName + "a").mkdir();
		new File(getSaveFile()).mkdir();
		new File(getSaveFile() + "/chests").mkdir();
		new File(getSaveFile() + "/quests").mkdir();
		new File(getSaveFile() + "/maps").mkdir();
		new File(getSaveFile() + "/mapdata").mkdir();
	}
	
	public static final void removeData(){
		removeFolder(SAVE_FOLDER);
	}
	
	public static boolean hasStarted(){
		return started;
	}
	
	public static void start(){
		started = true;
	}
	
	public static final void removeFolder(String path){
		File[] files = new File(path).listFiles();
		int t = 0;
		if(files != null){
			while(t < files.length){
				if(files[t] != null){
					if(files[t].isDirectory())
						removeFolder(files[t].getPath());
					else
						files[t].delete();
				}
				++t;
			}
			try {
				Files.delete(new File(path).toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	public static int getTextureDirect(String fileName){
		int currentID = textures.getOrDefault(fileName, -1);
		if(currentID != -1)
			return currentID;
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG", Loader.class.getClassLoader().getResource(fileName).openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		int id = texture.getTextureID();
		textures.put(fileName, id);
		return id;
	}
	*/
	
	public static Texture getTexture(String fileName){
		Texture texture = textures.get(fileName);
		if(texture != null)
			return texture;
		BufferedImage original = getBuffImage(fileName);
		BufferedImage image = toPowerOfTwo(original);
		texture = new Texture(image, original);
		textures.put(fileName, texture);
		return texture;
	}
	
	public static Texture getTexture(BufferedImage image){
		BufferedImage newImage = toPowerOfTwo(image);
		return new Texture(newImage, image);
	}
	
	public static Texture getFlippedTexture(String fileName){
		Texture texture = flippedTextures.get(fileName);
		if(texture != null)
			return texture;
		BufferedImage original = flipImage(getBuffImage(fileName));
		BufferedImage image = toPowerOfTwo(original);
		texture = new Texture(image, original);
		flippedTextures.put(fileName, texture);
		return texture;
	}
	
	public static Texture getRotateableTexture(String fileName, Point rotationPoint){
		RotateName rn = new RotateName(fileName, rotationPoint);
		Texture texture = rotatingTextures.get(rn);
		if(texture != null)
			return texture;
		BufferedImage original = toRotatingTexture(getBuffImage(fileName), rotationPoint);
		BufferedImage image = toPowerOfTwo(original);
		texture = new Texture(image, original);
		rotatingTextures.put(rn, texture);
		return texture;
	}
	
	public static Texture getFlippedRotateableTexture(String fileName, Point rotationPoint){
		RotateName rn = new RotateName(fileName, rotationPoint);
		Texture texture = flippedRotatingTextures.get(rn);
		if(texture != null)
			return texture;
		BufferedImage original = flipImage(toRotatingTexture(getBuffImage(fileName), rotationPoint));
		BufferedImage image = toPowerOfTwo(original);
		texture = new Texture(image, original);
		flippedRotatingTextures.put(rn, texture);
		return texture;
	}
	
	public static Texture createBorderTexture(Color color){
		BufferedImage border = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = border.createGraphics();
		g.setColor(color);
		g.drawLine(0, 0, 0, 32);
		g.drawLine(0, 0, 32, 0);
		g.drawLine(32, 0, 32, 32);
		g.drawLine(0, 32, 32, 32);
		g.dispose();
		return getTexture(border);
	}
	
	private static BufferedImage toPowerOfTwo(BufferedImage image){
		double logW = Math.log10(image.getWidth()) / Math.log10(2);
		int w = image.getWidth();
		int h = image.getHeight();
		if((int)logW != logW)
			w = (int) Math.pow(2, (int)logW + 1);
		double logH = Math.log10(image.getHeight()) / Math.log10(2);
		if((int)logH != logH)
			h = (int) Math.pow(2, (int)logH + 1);
		BufferedImage newImage = new BufferedImage(w, h, image.getType());
		Graphics2D g2 = newImage.createGraphics();
		g2.drawImage(image, 0, 0, w, h, null);
		g2.dispose();
		return newImage;
	}
	
	private static BufferedImage toRotatingTexture(BufferedImage original, Point rotationPoint){
		int deltaX = Math.max(rotationPoint.x, original.getWidth() - rotationPoint.x);
		int deltaY = Math.max(rotationPoint.y, original.getHeight() - rotationPoint.y);
		BufferedImage image = new BufferedImage(deltaX * 2, deltaY * 2, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		int x;
		int y;
		if(rotationPoint.x < original.getWidth() - rotationPoint.x)
			x = image.getWidth() - original.getWidth();
		else
			x = 0;
		if(rotationPoint.y < original.getHeight() - rotationPoint.y)
			y = image.getHeight() - original.getHeight();
		else
			y = 0;
		g.drawImage(original, x, y, null);
		return image;
	}
	
	public static int createTextureID(BufferedImage image, boolean allowAlpha){
	    ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * (allowAlpha ? 4 : 3)); //4 for RGBA, 3 for RGB
	    for(int y = 0; y < image.getHeight(); y++){
	        for(int x = 0; x < image.getWidth(); x++){
	        	Color color = new Color(image.getRGB(x, y));
	        	if(allowAlpha)
	        		color = new Color(color.getRed(), color.getGreen(), color.getBlue(), image.getAlphaRaster().getPixel(x, y, new int[1])[0]);
	            buffer.put((byte) color.getRed());
	            buffer.put((byte) color.getGreen());
	            buffer.put((byte) color.getBlue());
	            if(allowAlpha)
	            	buffer.put((byte) color.getAlpha());
	        }
	    }
	    buffer.flip();
	    int textureID = glGenTextures();
	    glBindTexture(GL_TEXTURE_2D, textureID);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
	    glTexImage2D(GL_TEXTURE_2D, 0, allowAlpha ? GL_RGBA8 : GL_RGB8, image.getWidth(), image.getHeight(), 0, allowAlpha ? GL_RGBA : GL_RGB, GL_UNSIGNED_BYTE, buffer);
	    System.out.println("Resources.getTexture(BufferedImage) textureID = " + textureID);
	    return textureID;
	}
	
	public static Image getImage(String resource){
		if(resource == null || resource.contains("null"))
			return null;
		try {
			Image image = images.get(resource);
			if(image == null){
				image = new ImageIcon(Utils.class.getClassLoader().getResource(resource.toLowerCase())).getImage();
				images.put(resource, image);
			}
			return image;
		} catch(Exception ex){
			MainClass.console.println("Failed to load image " + resource + ".");
			ex.printStackTrace(MainClass.console);
			return null;
		}
	}
	
	public static Image[] getImages(String... resources){
		Image[] images = new Image[resources.length];
		int t = 0;
		while(t < images.length){
			images[t] = getImage(resources[t]);
			++t;
		}
		return images;
	}
	
	public static Image[] getEntityImages(String path){
		return getImages(path + ".png", path + "_running.png", path + "_backward.png", path + "_running_backward.png");
	}
	
	public static BufferedImage getBuffImage(String resource){
		if(resource == null || resource.contains("null"))
			return null;
		try {
			BufferedImage image = copyImage(buffImages.get(resource));
			if(image == null){
				image = ImageIO.read(Utils.class.getClassLoader().getResource(resource.toLowerCase()));
				buffImages.put(resource, copyImage(image));
			}
			return image;
		} catch(Exception ex){
			MainClass.console.println("Failed to load image " + resource + ".");
			return null;
		}
	}
	
	public static BufferedImage getGhostImage(String resource){
		BufferedImage image = getBuffImage(resource);
		java.util.Random random = new java.util.Random();
		int x = 0;
		while(x < image.getWidth()){
			int y = 0;
			while(y < image.getHeight()){
				int[] pixel = image.getAlphaRaster().getPixel(x, y, new int[4]);
				if(pixel[0] != 0)
					pixel[0] = pixel[0] / 2 + random.nextInt(pixel[0] / 2);
				image.getAlphaRaster().setPixel(x, y, pixel);
				++y;
			}
			++x;
		}
		return image;
	}
	
	public static Cursor getCursor(String name, Point point){
		return Toolkit.getDefaultToolkit().createCustomCursor(getImage("sprites/cursors/" + name + ".png"), point, name);
	}
	
	public static Cursor getCursor(String name){
		return getCursor(name, new Point());
	}
	
	public static String getLoadFile(){
		return SAVE_FOLDER + "/a" + loadFileName + "a/" + loadFileTime;
	}
	
	public static String getSaveFile(){
		return SAVE_FOLDER + "/a" + saveFileName + "a/" + saveFileTime;
	}
	
	public static void setLoadFile(String loadFile, String loadTime){
		loadFileName = loadFile;
		loadFileTime = loadTime;
	}
	
	public static ArrayList<LoadFile> getLoadFiles(){
		ArrayList<LoadFile> loadFiles = new ArrayList<LoadFile>();
		File save = new File(SAVE_FOLDER);
		if(!save.exists())
			save.mkdir();
		File[] saves = new File(SAVE_FOLDER).listFiles();
		int i = 0;
		while(i < saves.length){
			File file = saves[i];
			if(file.listFiles() != null && file.listFiles().length > 0)
				loadFiles.add(new LoadFile(file.getName()));
			++i;
		}
		return loadFiles;
	}
	
	public static void setSaveName(String saveFile){
		saveFileName = saveFile;
	}
	
	private static void setSaveTime(String saveTime){
		saveFileTime = saveTime;
	}
	
	public static void setSaveTime(){
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH) + 1;
		int year = c.get(Calendar.YEAR);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		setSaveTime(year + " " + (month >= 10 ? month : "0" + month) + " " + (day >= 10 ? day : "0" + day) + "_" + (hour >= 10 ? hour : "0" + hour) + ";" + (minute >= 10 ? minute : "0" + minute) + ";" + (second >= 10 ? second : "0" + second));
	}
	
	public static BufferedImage copyImage(BufferedImage source){
		if(source == null)
			return null;
	    BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
	    Graphics g = b.getGraphics();
	    g.drawImage(source, 0, 0, null);
	    g.dispose();
	    return b;
	}
	
	public static int getButtonTexture(Color buttonColor, Color borderColor, Color textColor, String text){
		BufferedImage textImage = createTextImage(text, textColor);
		BufferedImage image = new BufferedImage(textImage.getWidth(), textImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		g2.setColor(buttonColor);
		int borderWidth = image.getWidth() / 30;
		int borderHeight = image.getHeight() / 30;
		g2.fillRect(0, 0, image.getWidth(), image.getHeight());
		g2.setColor(borderColor);
		g2.fillRect(0, 0, image.getWidth(), borderHeight);
		g2.fillRect(0, 0, borderWidth, image.getHeight());
		g2.fillRect(0, image.getHeight() - borderHeight, image.getWidth(), borderHeight);
		g2.fillRect(image.getWidth() - borderWidth, 0, borderWidth, image.getHeight());
		g2.drawImage(textImage, 0, 0, image.getWidth(), image.getHeight(), null);
		return createTextureID(image, true);
	}
	
	/*
	private static void drawText(BufferedImage source, String text, Color textColor){
		BufferedImage textImage = createTextImage(text, textColor);
		source.createGraphics().drawImage(textImage, 0, 0, source.getWidth(), source.getHeight(), null);
	}
	*/
	
	private static BufferedImage createTextImage(String text, Color textColor){
		int power = (int) Math.max(Math.log10(text.length() * 30) / Math.log10(2), 4);
		int s = (int) Math.pow(2, power);
		BufferedImage image = new BufferedImage(s, s, BufferedImage.TYPE_INT_ARGB);
		if(text.length() > 0){
			Graphics2D g2 = image.createGraphics();
			g2.setFont(new Font("TimesRoman", 0, 40));
			Rectangle2D bounds = g2.getFontMetrics().getStringBounds(text, g2);
			if(bounds.getWidth() == 0 || bounds.getHeight() == 0)
				return image;
			double preferredWidth = image.getWidth() * 0.9;
			double preferredHeight = image.getHeight() * 0.9;
			double factor = min(preferredWidth / bounds.getWidth(), preferredHeight / bounds.getHeight());
			g2.setColor(textColor);
			g2.setFont(new Font("TimesRoman", 0, (int) (40 * factor)));
			Rectangle2D newBounds = g2.getFontMetrics().getStringBounds(text, g2);
			int x = (int) ((image.getWidth() - newBounds.getWidth()) / 2);
			int y = (int) ((image.getHeight() - newBounds.getCenterY()) / 2);
			g2.drawString(text, x, y);
			g2.setColor(Color.BLACK);
			int w = (int)newBounds.getWidth();
			int h = (int) newBounds.getHeight();
			int width = w;
			int height = h;
			y -= newBounds.getHeight() / 1.5f;
			double logW = Math.log10(w) / Math.log10(2);
			if((int)logW != logW)
				w = (int) Math.pow(2, (int)logW + 1);
			double logH = Math.log10(h) / Math.log10(2);
			if((int)logH != logH)
				h = (int) Math.pow(2, (int)logH + 1);
			image = resizeImage(image, x, y, w, h, width, height);
			g2 = image.createGraphics();
			g2.setColor(Color.BLACK);
			g2.drawLine(0, 0, image.getWidth() - 1, 0);
			g2.drawLine(0, 0, 0, image.getHeight() - 1);
			g2.drawLine(0, image.getHeight() - 1, image.getWidth() - 1, image.getHeight() - 1);
			g2.drawLine(image.getWidth() - 1, 0, image.getWidth() - 1, image.getHeight() - 1);
		}
		return image;
	}
	
	private static BufferedImage resizeImage(BufferedImage original, int x, int y, int width, int height, int preferredWidth, int preferredHeight){
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		int spaceX = (width - preferredWidth) / 2;
		int spaceY = (height - preferredHeight) / 2;
		newImage.createGraphics().drawImage(original, spaceX, spaceY, width + spaceX, height + spaceY, x, y, x + width, y + height, null);
		return newImage;
	}
	
	private static BufferedImage flipImage(BufferedImage original){
		BufferedImage image = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
		Graphics2D g = image.createGraphics();
		g.drawImage(original, original.getWidth(), 0, -original.getWidth(), original.getHeight(), null);
		g.dispose();
		return image;
	}
	
	public static class LoadFile {
		
		public final String fileName;
		public final File file;
		
		public LoadFile(String fileName){
			this.fileName = fileName;
			this.file = new File(SAVE_FOLDER + "/" + fileName);
		}
		
		public ArrayList<LoadTime> getLoadTimes(){
			ArrayList<LoadTime> times = new ArrayList<LoadTime>();
			File[] files = file.listFiles();
			int i = 0;
			while(i < files.length){
				File file = files[i];
				char[] chars = file.getName().toCharArray();
				int s = 0;
				int c = 0;
				int u = 0;
				int i1 = 0;
				while(i1 < chars.length){
					if(chars[i1] == ' ')
						++s;
					if(chars[i1] == ';')
						++c;
					if(chars[i1] == '_')
						++u;
					++i1;
				}
				if(s == 2 && c == 2 && u == 1)
					times.add(new LoadTime(this, file.getName()));
				++i;
			}
			return times;
		}
		
		public String getFileString(){
			return fileName.substring(1, fileName.length() - 1);
		}
		
		public void delete(){
			removeFolder(SAVE_FOLDER + "/" + fileName);
		}
	}
	
	public static class LoadTime {
		
		public final String fileTime;
		public final File file;
		public final LoadFile loadFile;
		
		public LoadTime(LoadFile loadFile, String fileTime){
			this.fileTime = fileTime;
			this.file = new File(SAVE_FOLDER + "/" + loadFile.fileName + "/" + fileTime);
			this.loadFile = loadFile;
		}
		
		public void load(){
			setLoadFile(getFileString(), getTimeString());
			MainClass.loadData();
		}
		
		public void delete(){
			removeFolder(SAVE_FOLDER + "/" + loadFile.fileName + "/" + getTimeString());
		}
		
		public String getTimeString(){
			return fileTime;
		}
		
		public String getNiceTimeString(){
			return fileTime.replace(' ', '/').replace(';', ':').replace('_', ' ');
		}
		
		public String getFileString(){
			return loadFile.fileName;
		}
	}
	
	private static class RotateName {
		
		private String fileName;
		private Point rotationPoint;
		
		private RotateName(String fileName, Point rotationPoint){
			this.fileName = fileName;
			this.rotationPoint = rotationPoint;
		}
		
		@Override
		public boolean equals(Object other){
			if(other instanceof RotateName){
				RotateName rn = (RotateName) other;
				return rn.fileName.equals(fileName) && rn.rotationPoint.equals(rotationPoint);
			}
			return false;
		}
	}
}
