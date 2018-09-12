package nl.knokko.rpg.quests;

import java.awt.*;
import java.io.*;

import nl.knokko.rpg.gui.Gui;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.utils.Resources;
import nl.knokko.rpg.utils.SpecialText;

public abstract class Quest {
	
	public SpecialText discription;
	public String shortDiscription;
	
	public int progress;
	public int maxProgress;
	
	public abstract boolean needsUpdate();
	public abstract String toString();
	public abstract Quest clone();
	
	public void saveData(PrintWriter writer) {
		writer.println("progress: " + progress);
	}

	public void loadData(BufferedReader reader) {
		try {
			progress = Integer.decode(reader.readLine().substring(10));
		} catch (Exception e) {
			e.printStackTrace(Game.console);
		}
	}
	public abstract void complete();
	
	public void saveData(boolean Catch){
		try {
			PrintWriter writer = new PrintWriter(Resources.getSaveFile() + "/quests/" + this + ".txt");
			saveData(writer);
			writer.close();
		} catch(Exception ex){
			Game.console.println("Failed to save the quests:");
			ex.printStackTrace(Game.console);
			Game.console.println();
		}
	}
	
	public void loadData(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Resources.getLoadFile() + "/quests/" + this + ".txt"));
			loadData(reader);
			reader.close();
		} catch(Exception ex){
			ex.printStackTrace(Game.console);
		}
	}
	
	public boolean equals(Object other){
		return other instanceof Quest ? toString().matches(other.toString()) : false;
	}
	
	public void update(){}
	public void addSpecialItem(String item){}
	
	public void paint(Graphics gr, Point base){
		if(discription != null)
			discription.paint(gr, base);
	}
	
	public void paintSimple(Graphics gr, Point base){
		gr.setColor(discription.color);
		gr.setFont(Gui.font());
		String disc = this + " :   " + shortDiscription;
		if(maxProgress > 1)
			disc += "   " + progress + " / " + maxProgress;
		disc = disc.replaceAll("_", " ");
		gr.drawString(disc, base.x, base.y);
	}
	
	public Quest setDiscription(SpecialText discrip, String shortDisc){
		discription = discrip;
		shortDiscription = shortDisc;
		return this;
	}
	
	public static class Reward {
		
		public final int xp;
		public final int money;
		
		public final ItemStack[] items;
		
		public Reward(int rewardXp, int rewardMoney,ItemStack... rewardItems){
			xp = rewardXp;
			money = rewardMoney;
			items = rewardItems;
		}
		
		public void apply(Game game){
			game.player.addXp(xp);
			game.player2.addXp(xp);
			game.player.inventory.money += money;
			int t = 0;
			while(t < items.length){
				game.player.inventory.addItemStack(items[t]);
				++t;
			}
		}
		
		public void apply(){
			apply(Game.game);
		}
	}
}
