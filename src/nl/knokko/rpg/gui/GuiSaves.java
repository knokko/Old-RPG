package nl.knokko.rpg.gui;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.utils.Resources;
import nl.knokko.rpg.utils.Resources.LoadFile;
import nl.knokko.rpg.utils.Resources.LoadTime;

public class GuiSaves extends Gui {
	
	private ArrayList<LoadFile> saveFiles;
	private ArrayList<Button> savesButtons = new ArrayList<Button>();
	
	private FileButton selectedFile;
	
	private int page;
	private int saves;

	public GuiSaves() {
		super(null);
		saveFiles = Resources.getLoadFiles();
		updateSaves();
	}
	
	@Override
	public void escapePressed(){
		MainClass.currentGUI = new GuiMainMenu();
	}
	
	/*
	@Override
	public void paint(Graphics gr){
		gr.setColor(Color.BLUE);
		gr.fillRect(0, 0, 1600, 900);
		gr.setColor(Color.YELLOW);
		gr.fillRect(0, 0, 1600, 90);
		gr.fillRect(0, 660, 1600, 250);
		gr.setColor(Color.BLACK);
		gr.drawLine(0, 90, 1600, 90);
		gr.drawLine(0, 660, 1600, 660);
		gr.setFont(normalFont);
		gr.drawString("page " + page, 1025, 60);
		super.paint(gr);
		if(selectedFile != null){
			gr.setColor(Color.GREEN);
			int x1 = selectedFile.minX;
			int x2 = selectedFile.maxX - x1;
			int y1 = selectedFile.minY;
			int y2 = selectedFile.maxY - y1;
			gr.drawRect(x1, y1, x2, y2);
			gr.drawRect(x1 + 1, y1 + 1, x2 - 2, y2 - 2);
			gr.drawRect(x1 + 2, y1 + 2, x2 - 4, y2 - 4);
			gr.drawRect(x1 + 3, y1 + 3, x2 - 6, y2 - 6);
			gr.setColor(Color.BLACK);
			gr.setFont(new Font("TimesRoman", 0, 40));
			gr.drawString("save file: " + selectedFile.file.getFileString(), 20, 700);
			gr.drawString("amount of files: " + saves, 20, 750);
			int i = 0;
			while(i < savesButtons.size()){
				savesButtons.get(i).paint(gr);
				++i;
			}
		}
	}
	*/
	
	@Override
	public void render(){
		super.render();
		if(selectedFile != null){
			for(Button button : savesButtons)
				button.render();
		}
	}
	
	@Override
	public void buttonClicked(Button button){
		if(button instanceof FileButton){
			selectedFile = (FileButton) button;
			saves = selectedFile.file.getLoadTimes().size();
			savesButtons.add(new Button(700, 720, 900, 820, "sprites/buttons/button.png", "delete", normalFont, Color.RED));
			savesButtons.add(new Button(950, 720, 1150, 820, "sprites/buttons/button.png", "open", normalFont, Color.BLACK));
		}
		else {
			if(button.name.equals("back"))
				MainClass.currentGUI = new GuiMainMenu();
			if(button.name.equals("previous page") && page > 0){
				page--;
				updateSaves();
			}
			if(button.name.equals("next page") && (page + 1) * 12 < saveFiles.size()){
				page++;
				updateSaves();
			}
			if(button.name.equals("delete")){
				if(selectedFile != null){
					selectedFile.file.delete();
					saveFiles = Resources.getLoadFiles();
					updateSaves();
					selectedFile = null;
					savesButtons = new ArrayList<Button>();
				}
			}
			if(button.name.equals("open")){
				if(selectedFile != null){
					MainClass.currentGUI = new GuiSaveFile();
				}
			}
			if(button.name.equals("new game"))
				MainClass.currentGUI = new GuiNewGame();
		}
	}
	
	@Override
	public void click(Vector2f mouse){
		super.click(mouse);
		int i = 0;
		while(i < savesButtons.size()){
			if(savesButtons.get(i).isHit(mouse))
				buttonClicked(savesButtons.get(i));
			++i;
		}
	}
	
	private void updateSaves(){
		buttons = new ArrayList<Button>();
		addButton(100, 10, 200, 80, "back");
		addButton(600, 10, 775, 80, "previous page");
		addButton(825, 10, 1000, 80, "next page");
		addButton(1300, 10, 1500, 80, "new game");
		int i = page * 12;
		int x = 50;
		while(x + 300 < 1600){
			int y = 100;
			while(y + 100 <= 650 && i < saveFiles.size()){
				buttons.add(new FileButton(x, y, x + 300, y + 100, saveFiles.get(i)));
				y += 150;
				i++;
			}
			x += 350;
		}
	}
	
	public class GuiNewGame extends Gui {
		
		private String fileName = "";
		
		private boolean canCreate;

		public GuiNewGame() {
			super(null);
			updateButtons();
		}
		
		@Override
		public void escapePressed(){
			MainClass.currentGUI = GuiSaves.this;
		}
		
		@Override
		public void keyTyped(char key){
			if(key != KeyEvent.CHAR_UNDEFINED){
				if(key == '\b'){
					if(fileName.length() > 0)
						fileName = fileName.substring(0, fileName.length() - 1);
				}
				else
					fileName += key;
				updateButtons();
			}
		}
		
		/*
		@Override
		public void paint(Graphics gr){
			gr.setColor(Color.BLUE);
			gr.fillRect(0, 0, 1600, 900);
			super.paint(gr);
			gr.setColor(Color.BLACK);
			gr.setFont(normalFont);
			gr.drawString("file name: " + fileName, 400, 300);
			if(!canCreate){
				gr.setColor(Color.RED);
				gr.drawString("You can't give your file this name!", 400, 500);
			}
		}
		*/
		
		@Override
		public void buttonClicked(Button button){
			if(button.name.equals("back"))
				MainClass.currentGUI = GuiSaves.this;
			if(button.name.equals("start")){
				Resources.setSaveName(fileName);
				Resources.start();
				MainClass.newGame();
			}
		}
		
		private void updateButtons(){
			buttons = new ArrayList<Button>();
			addButton(100, 10, 200, 80, "back");
			File test = new File(Resources.SAVE_FOLDER + "/a" + fileName + "a");
			canCreate = test.mkdir();
			if(canCreate){
				test.delete();
				addButton(1400, 10, 1500, 80, "start");
			}
		}
		
	}
	
	public class GuiSaveFile extends Gui {
		
		private ArrayList<LoadTime> loadTimes = new ArrayList<LoadTime>();
		private ArrayList<Button> timesButtons = new ArrayList<Button>();
		
		private TimeButton selectedTime;
		
		private int page;

		public GuiSaveFile() {
			super(null);
			updateFiles();
		}
		
		@Override
		public void escapePressed(){
			MainClass.currentGUI = GuiSaves.this;
		}
		
		@Override
		public void buttonClicked(Button button){
			if(button instanceof TimeButton){
				selectedTime = (TimeButton) button;
				saves = selectedFile.file.getLoadTimes().size();
				timesButtons.add(new Button(700, 720, 900, 820, "sprites/buttons/button.png", "delete", normalFont, Color.RED));
				timesButtons.add(new Button(950, 720, 1150, 820, "sprites/buttons/button.png", "load", normalFont, Color.BLACK));
			}
			else {
				if(button.name.equals("back"))
					MainClass.currentGUI = GuiSaves.this;
				if(button.name.equals("previous page") && page > 0){
					page--;
					updateFiles();
				}
				if(button.name.equals("next page") && (page + 1) * 12 < loadTimes.size()){
					page++;
					updateFiles();
				}
				if(button.name.equals("delete")){
					if(selectedTime != null){
						selectedTime.file.delete();
						loadTimes = selectedFile.file.getLoadTimes();
						updateFiles();
						selectedTime = null;
						timesButtons = new ArrayList<Button>();
					}
				}
				if(button.name.equals("load")){
					Resources.setLoadFile(selectedFile.file.getFileString(), selectedTime.file.getTimeString());
					Resources.setSaveName(selectedFile.file.getFileString());
					Resources.start();
					MainClass.loadData();
				}
			}
		}
		
		/*
		@Override
		public void paint(Graphics gr){
			gr.setColor(Color.BLUE);
			gr.fillRect(0, 0, 1600, 900);
			gr.setColor(Color.YELLOW);
			gr.fillRect(0, 0, 1600, 90);
			gr.fillRect(0, 660, 1600, 250);
			gr.setColor(Color.BLACK);
			gr.drawLine(0, 90, 1600, 90);
			gr.drawLine(0, 660, 1600, 660);
			gr.setFont(normalFont);
			gr.drawString("page " + page, 1025, 60);
			super.paint(gr);
			if(selectedTime != null){
				gr.setColor(Color.GREEN);
				int x1 = selectedTime.minX;
				int x2 = selectedTime.maxX - x1;
				int y1 = selectedTime.minY;
				int y2 = selectedTime.maxY - y1;
				gr.drawRect(x1, y1, x2, y2);
				gr.drawRect(x1 + 1, y1 + 1, x2 - 2, y2 - 2);
				gr.drawRect(x1 + 2, y1 + 2, x2 - 4, y2 - 4);
				gr.drawRect(x1 + 3, y1 + 3, x2 - 6, y2 - 6);
				gr.setColor(Color.BLACK);
				gr.setFont(new Font("TimesRoman", 0, 40));
				gr.drawString("load time: " + selectedTime.file.getNiceTimeString(), 20, 700);
				int i = 0;
				while(i < timesButtons.size()){
					timesButtons.get(i).paint(gr);
					++i;
				}
			}
		}
		*/
		
		@Override
		public void render(){
			super.render();
			if(selectedTime != null){
				for(Button button : timesButtons)
					button.render();
			}
		}
		
		@Override
		public void click(Vector2f mouse){
			super.click(mouse);
			int i = 0;
			while(i < timesButtons.size()){
				if(timesButtons.get(i).isHit(mouse))
					buttonClicked(timesButtons.get(i));
				++i;
			}
		}
		
		private void updateFiles(){
			loadTimes = selectedFile.file.getLoadTimes();
			buttons = new ArrayList<Button>();
			addButton(100, 10, 200, 80, "back");
			addButton(600, 10, 775, 80, "previous page");
			addButton(825, 10, 1000, 80, "next page");
			int i = page * 12;
			int x = 50;
			while(x + 300 < 1600){
				int y = 100;
				while(y + 100 <= 650 && i < loadTimes.size()){
					buttons.add(new TimeButton(x, y, x + 300, y + 100, loadTimes.get(i)));
					y += 150;
					i++;
				}
				x += 350;
			}
		}
	}
	
	public static class FileButton extends Button {
		
		public final LoadFile file;

		public FileButton(int minx, int miny, int maxx, int maxy, LoadFile file) {
			super(minx, miny, maxx, maxy, "sprites/buttons/save button.png", file.getFileString(), normalFont, Color.BLACK);
			this.file = file;
		}
		
	}
	
	public static class TimeButton extends Button {
		
		public final LoadTime file;

		public TimeButton(int minx, int miny, int maxx, int maxy, LoadTime file) {
			super(minx, miny, maxx, maxy, "sprites/buttons/save button.png", file.getNiceTimeString(), normalFont, Color.BLACK);
			this.file = file;
		}
	}
}
