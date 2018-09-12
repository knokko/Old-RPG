package nl.knokko.rpg.main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFrame;

import nl.knokko.rpg.entities.monsters.boss.Bosses;
import nl.knokko.rpg.entities.players.*;
import nl.knokko.rpg.gui.*;
import nl.knokko.rpg.items.SpecialItems;
import nl.knokko.rpg.quests.QuestMap;
import nl.knokko.rpg.quests.Quests;
import nl.knokko.rpg.utils.JukeBox;
import nl.knokko.rpg.utils.Resources;
import nl.knokko.rpg.world.World;
import nl.knokko.rpg.world.maps.MapGenRunia;

public class Game extends JFrame implements Runnable{

	/**
	 * The serial version id...
	 */
	public static final long serialVersionUID = -6157096712067208240L;
	public static Game game;
	public static PrintWriter console;
	
	public final Board board;
	public final Controller controller;
	public final World world;
	public final Player player;
	public final Player player2;
	public Player player3;
	public final QuestMap quests;
	public final SpecialItems specialItems;
	
	public final Cursor empty = Resources.getCursor("empty");
	public final Cursor arrow = Resources.getCursor("arrow");
	
	public Gui currentGUI;
	
	public int fpsFactor = 5;
	public int updateSpeed = 1;
	public int difficulty = 1;
	
	public boolean firstGame;
	
	private boolean stop;
	private boolean savedata;
	private long startTime;
	
	public Point camera = new Point(810, 450);

	public Game() {
		game = this;
		try {
			console = new PrintWriter("console.txt"){
				
				@Override
				public void write(String text){
					super.write(getTime() + ": " + text);
				}
			};
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		player = new Player(this, new Point(660, 30));
		player2 = new Chomper(this, null);
		board = new Board(this);
		controller = new Controller(this);
		world = new World(this);
		quests = new QuestMap(this);
		specialItems = new SpecialItems(this);
		startTime = System.nanoTime();
		addKeyListener(controller);
		addMouseListener(controller);
		addMouseMotionListener(controller);
		setUndecorated(true);
		setExtendedState(MAXIMIZED_BOTH);
		add(board);
		setVisible(true);
		currentGUI = new GuiMainMenu(this);
		setTitle("RPG");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		run();
	}
	
	public static void main(String... args){
		game = new Game();
	}
	
	public void run(){
		Gui previousGUI = currentGUI;
		refreshModels();
		while(true){
			try {
				if(stop)
					close();
				long previousTime = System.nanoTime();
				setCursor(getCursor());
				if(currentGUI != previousGUI)
					refreshModels();
				previousGUI = currentGUI;
				if(currentGUI != null)
					currentGUI.update();
				else {
					player.update();
					world.update();
				}
				controller.update();
				board.repaint();
				JukeBox.update();
				long tickTime = System.nanoTime() - previousTime;
				long skipTime = (100000000 / getUpdateSpeed() - tickTime) / 1000000;
				if(skipTime > 0)
					Thread.sleep(skipTime);
				else 
					console.println("Game is lagging!");
			} catch (Exception e) {
				e.printStackTrace();
				close(true);
			}
		}
	}
	
	public void saveData(){
		Resources.setSaveTime();
		Resources.writeDataFolders();
		player.saveData();
		player2.saveData();
		if(player3 != null)
			player3.saveData();
		world.saveData();
		quests.saveData();
		Bosses.saveData();
		GuiChat.saveStaticData();
		GuiWorldMap.saveStaticData();
		GuiOptions.saveData(this);
		specialItems.saveData();
		if(currentGUI instanceof GuiBattle)
			currentGUI.saveData();
		else
			new File(Resources.getSaveFile() + "/battle.txt").delete();
	}
	
	public void loadData(){
		GuiOptions.loadData(this);
		GuiWorldMap.loadData();
		quests.loadData();
		player.loadData();
		player2.loadData();
		GuiChat.loadData();
		Bosses.loadData();
		if(player3 != null)
			player3.loadData();
		specialItems.loadData();
		currentGUI = GuiBattle.loadData(this);
		world.loadData();
		if(currentGUI instanceof GuiBattle)
			((GuiBattle) currentGUI).startMusic();
		else
			currentGUI = null;
	}
	
	public void newGame(){
		firstGame = true;
		quests.addQuest(Quests.miners());
		GuiChat.addAllConversations();
		Bosses.addAllBosses();
		world.build(new MapGenRunia(), false);
		currentGUI = new GuiMovie.GuiIntroMovie(this);
	}
	
	public void close(boolean save){
		stop = true;
		savedata = save;
	}
	
	private void close(){
		if(savedata)
			saveData();
		console.close();
		JukeBox.stop();
		System.exit(0);
	}
	
	public Player nextPlayer(Player player){
		if(player == player2){
			if(player3 != null)
				return player3;
			return this.player;
		}
		if(player == this.player){
			return player2;
		}
		return this.player;
	}
	
	public GuiBattle getBattle(){
		if(currentGUI instanceof GuiBattle)
			return (GuiBattle) currentGUI;
		return null;
	}
	
	@Override
	public void dispose(){
		close(!(currentGUI instanceof GuiGameOver || currentGUI instanceof GuiMainMenu || currentGUI instanceof GuiMainMenu.GuiDeleteData || currentGUI instanceof GuiMainMenu.GuiHelp));
	}
	
	@Override
	public Cursor getCursor(){
		if(currentGUI != null){
			Cursor cursor = currentGUI.getCursor();
			if(cursor == null)
				cursor = arrow;
			return cursor;
		}
		return empty;
	}
	
	@Override
	public boolean isCursorSet(){
		return true;
	}

	public Player[] players() {
		return new Player[]{player, player2, player3};
	}
	
	public void healPlayers(){
		board.setColor(Color.GREEN, 10);
		int t = 0;
		while(t < players().length){
			if(players()[t] != null){
				players()[t].currentHealth = players()[t].maxHealth;
				players()[t].currentMana = players()[t].maxMana;
			}
			++t;
		}
	}
	
	public void refreshModels(){
		int t = 0;
		while(t < players().length){
			if(players()[t] != null)
				players()[t].refreshModels();
			++t;
		}
	}
	
	public double getTime(){
		return (System.nanoTime() - startTime) / 1000000000.0;
	}
	
	@Override
	public int getWidth(){
		return 1600;
	}
	
	@Override
	public int getHeight(){
		return 900;
	}
	
	public int width(){
		return super.getWidth();
	}
	
	public int height(){
		return super.getHeight();
	}
	
	public Point getMousePosition(){
		if(super.getMousePosition() != null)
			return new Point((int)(super.getMousePosition().x / Board.factor()), (int) (super.getMousePosition().y / Board.factor()));
		return new Point(-100, -100);
	}
	
	public int getUpdateSpeed(){
		return updateSpeed * fpsFactor;
	}
}
