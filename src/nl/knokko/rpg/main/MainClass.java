package nl.knokko.rpg.main;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import nl.knokko.rpg.entities.monsters.boss.Bosses;
import nl.knokko.rpg.entities.players.Chomper;
import nl.knokko.rpg.entities.players.Player;
import nl.knokko.rpg.gui.Gui;
import nl.knokko.rpg.gui.GuiBattle;
import nl.knokko.rpg.gui.GuiChat;
import nl.knokko.rpg.gui.GuiMainMenu;
import nl.knokko.rpg.gui.GuiMovie;
import nl.knokko.rpg.gui.GuiOptions;
import nl.knokko.rpg.gui.GuiWorldMap;
import nl.knokko.rpg.items.SpecialItems;
import nl.knokko.rpg.quests.QuestMap;
import nl.knokko.rpg.quests.Quests;
import nl.knokko.rpg.render.DisplayManager;
import nl.knokko.rpg.render.Renderer;
import nl.knokko.rpg.utils.JukeBox;
import nl.knokko.rpg.utils.Natives;
import nl.knokko.rpg.utils.Resources;
import nl.knokko.rpg.world.World;
import nl.knokko.rpg.world.maps.MapGenRunia;

public class MainClass {
	
public static PrintWriter console;
	
	public static World world;
	public static Player player;
	public static Player player2;
	public static Player player3;
	public static QuestMap quests;
	public static SpecialItems specialItems;
	
	public static Gui currentGUI;
	public static int updateSpeed = 1;
	public static int difficulty = 1;
	
	public static boolean firstGame;
	
	private static boolean isStopping;
	private static boolean savedata;
	private static long startTime;
	
	public static Point camera = new Point(810, 450);
	
	private static int ticks;
	private static long renderTime;

	public static void main(String[] args) {
		prepare();
		setUp();
		init();
		while(!shouldStop()){
			update();
			render();
			DisplayManager.updateDisplay();
		}
		if(savedata)
			saveData();
		clean();
	}
	
	private static void prepare(){
		Natives.prepare();
	}
	
	private static void setUp(){
		DisplayManager.createDisplay();
		Renderer.setUp();
	}
	
	private static void init(){
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
		player = new Player(new Point(660, 30));
		player2 = new Chomper(null);
		world = new World();
		quests = new QuestMap();
		specialItems = new SpecialItems();
		startTime = System.nanoTime();
		currentGUI = new GuiMainMenu();
	}
	
	private static boolean shouldStop(){
		return isStopping;
	}
	
	private static void update(){
		if(currentGUI != null){
			currentGUI.update();
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
				currentGUI.escapePressed();
		}
		else {
			world.update();
			player.update();
		}
		MainController.update();
		JukeBox.update();
	}
	
	private static void render(){
		long startTime = System.nanoTime();
		if(currentGUI != null){
			Renderer.beforeRendering(currentGUI.getBackGroundColor());
			currentGUI.render();
			Renderer.afterRendering();
		}
		else {
			Renderer.beforeRendering(world.backGround);
			world.render();
			Renderer.afterRendering();
		}
		long endTime = System.nanoTime();
		if(world != null){
			ticks++;
			renderTime += (endTime - startTime);
		}
	}
	
	private static void clean(){
		if(ticks > 0)
			System.out.println("average render time was " + (renderTime / ticks) / 1000 + " microseconds");
		Renderer.cleanUp();
		DisplayManager.closeDisplay();
	}
	
	public static double getTime(){
		return (System.nanoTime() - startTime) / 1000000000.0;
	}
	
	public static Player nextPlayer(Player prevPlayer){
		if(prevPlayer == player2){
			if(player3 != null)
				return player3;
			return player;
		}
		if(prevPlayer == player){
			return player2;
		}
		return player;
	}
	
	public static void saveData(){
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
		GuiOptions.saveOptionData();
		specialItems.saveData();
		if(currentGUI instanceof GuiBattle)
			currentGUI.saveData();
		else
			new File(Resources.getSaveFile() + "/battle.txt").delete();
	}
	
	public static void close(boolean save){
		isStopping = true;
		savedata = save;
	}
	
	public static Vector2f getMousePosition(){
		return new Vector2f(((float)Mouse.getX() / Display.getWidth() - 0.5f) * 2, -((float)Mouse.getY() / Display.getHeight() - 0.5f) * 2);
	}
	
	public static GuiBattle getBattle(){
		if(currentGUI instanceof GuiBattle)
			return (GuiBattle) currentGUI;
		return null;
	}
	
	public static Player[] players() {
		return new Player[]{player, player2, player3};
	}
	
	public static void newGame(){
		firstGame = true;
		quests.addQuest(Quests.miners());
		GuiChat.addAllConversations();
		Bosses.addAllBosses();
		world.build(new MapGenRunia(), false);
		currentGUI = new GuiMovie.GuiIntroMovie();
	}
	
	public static void loadData(){
		GuiOptions.loadData();
		GuiWorldMap.loadData();
		quests.loadData();
		player.loadData();
		player2.loadData();
		GuiChat.loadData();
		Bosses.loadData();
		if(player3 != null)
			player3.loadData();
		specialItems.loadData();
		currentGUI = GuiBattle.loadData();
		world.loadData();
		if(currentGUI instanceof GuiBattle)
			((GuiBattle) currentGUI).startMusic();
		else
			currentGUI = null;
	}
	
	public static void healPlayers(){
		int t = 0;
		while(t < players().length){
			if(players()[t] != null){
				players()[t].currentHealth = players()[t].maxHealth;
				players()[t].currentMana = players()[t].maxMana;
			}
			++t;
		}
	}
}
