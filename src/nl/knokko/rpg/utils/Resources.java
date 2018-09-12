package nl.knokko.rpg.utils;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import nl.knokko.rpg.main.Game;

public final class Resources {
	
	public static final String SAVE_FOLDER = "rpg save files";
	
	private static String loadFileName;
	private static String loadFileTime;
	private static String saveFileName;
	private static String saveFileTime;
	
	private static final Map<String, BufferedImage> buffImages = new HashMap<String, BufferedImage>();
	private static final Map<String, Image> images = new HashMap<String, Image>();
	
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
			Game.console.println("Failed to load image " + resource + ".");
			ex.printStackTrace(Game.console);
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
			Game.console.println("Failed to load image " + resource + ".");
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
		
		public void load(Game game){
			setLoadFile(getFileString(), getTimeString());
			game.loadData();
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
}
