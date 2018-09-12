package nl.knokko.rpg.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import nl.knokko.rpg.main.MainClass;
import nl.knokko.rpg.render.DisplayManager;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public final class JukeBox {
	
	private static ArrayList<String> lines;
	
	private static int[] indexes;
	private static String song;
	
	private static int lineNumber;
	private static byte lineIndex;
	private static int updateTimer;
	
	private static boolean autoRestart;
	private static boolean disabled;

	public static void playShortSound(String sound){
		try {
			if(sound.contains("+")){
				int index = sound.indexOf("+");
				playShortSound(sound.substring(0, index));
				playShortSound(sound.substring(index + 1));
				return;
			}
			if(sound.startsWith("P"))
				sound = "piano/" + sound.substring(1);
			if(sound.startsWith("S"))
				sound = "sytrus/" + sound.substring(1);
			URL url = JukeBox.class.getClassLoader().getResource("sounds/" + sound + ".wav");
        	AudioStream audios = new AudioStream(url.openStream());
        	AudioPlayer.player.start(audios);
		} catch (Exception ex) {
			if(MainClass.console != null){
				MainClass.console.println("failed to play sound " + sound + " :");
				MainClass.console.println();
				ex.printStackTrace(MainClass.console);
			}
			else {
				System.out.println("failed to play sound " + sound + " :");
				System.out.println();
				ex.printStackTrace();
			}
    	}
	}
	
	public static void playMusic(String music){
		playMusic(music, false);
	}
	
	public static void playMusic(String music, boolean restartAuto){
		boolean b = song != null && song.equals(music);
		if(b || disabled)
			return;
		try {
			stop();
			song = music;
			autoRestart = restartAuto;
			lines = new ArrayList<String>();
			URL url = JukeBox.class.getClassLoader().getResource("sounds/music/" + music + ".music");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String line = reader.readLine();
			while(line != null){
				lines.add(line);
				line = reader.readLine();
			}
			initIndexes();
			reader.close();
		} catch (Exception e) {
			MainClass.console.println("failed to play music " + music + ":");
			MainClass.console.println();
			e.printStackTrace(MainClass.console);
		}
	}
	
	public static void playMusic2(String music, boolean restartAuto){
		if(disabled)
			return;
		try {
			stop();
			autoRestart = restartAuto;
			lines = new ArrayList<String>();
			BufferedReader reader = new BufferedReader(new FileReader(music + ".music"));
			String line = reader.readLine();
			while(line != null){
				lines.add(line);
				line = reader.readLine();
			}
			initIndexes();
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void update(){
		if(updateTimer <= 0){
			if(lines != null && !lines.isEmpty()){
				if(lineNumber < lines.size()){
					if(lineIndex < 9){
						++lineIndex;
					}
					else {
						lineIndex = 0;
						++lineNumber;
						if(lineNumber < lines.size())
							initIndexes();
						else {
							restart();
							return;
						}
					}
				}
				else {
					restart();
					return;
				}
				if(indexes == null)
					initIndexes();
				String line = lines.get(lineNumber);
				int i1 = 0;
				if(lineIndex > 0)
					i1 = indexes[lineIndex - 1];
				int i2 = line.length();
				if(lineIndex < 9)
					i2 = indexes[lineIndex];
				if(line.charAt(i1) == '-' && i2 > 0)
					i1++;
				String sound = line.substring(i1, i2);
				if(!sound.isEmpty())
					playShortSound(sound);
			}
			updateTimer = MainClass.updateSpeed * DisplayManager.FPS_CAP / 10;
		}
		--updateTimer;
	}
	
	public static void stop(){
		song = null;
		lines = null;
		indexes = null;
		lineNumber = 0;
		lineIndex = -1;
	}
	
	public static boolean isPlaying(){
		return lines != null;
	}
	
	public static void enable(){
		disabled = false;
	}
	
	public static void disable(){
		disabled = true;
	}
	
	private static void initIndexes(){
		String line = lines.get(lineNumber);
		indexes = new int[9];
		int i1 = line.indexOf("-");
		int i2 = line.indexOf("-", i1 + 1);
		int i3 = line.indexOf("-", i2 + 1);
		int i4 = line.indexOf("-", i3 + 1);
		int i5 = line.indexOf("-", i4 + 1);
		int i6 = line.indexOf("-", i5 + 1);
		int i7 = line.indexOf("-", i6 + 1);
		int i8 = line.indexOf("-", i7 + 1);
		int i9 = line.indexOf("-", i8 + 1);
		indexes[0] = i1;
		indexes[1] = i2;
		indexes[2] = i3;
		indexes[3] = i4;
		indexes[4] = i5;
		indexes[5] = i6;
		indexes[6] = i7;
		indexes[7] = i8;
		indexes[8] = i9;
	}
	
	private static void restart(){
		if(autoRestart){
			lineNumber = 0;
			lineIndex = -1;
			initIndexes();
		}
		else {
			stop();
			return;
		}
	}
}
