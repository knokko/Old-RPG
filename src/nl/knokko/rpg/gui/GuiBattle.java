package nl.knokko.rpg.gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.Element;
import nl.knokko.rpg.effects.Effect;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.players.Player;
import nl.knokko.rpg.gui.GuiSpellTree.SpellButton;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.ItemConsumable;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.main.Board;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.spells.ButtonSpell;
import nl.knokko.rpg.spells.Spell;
import nl.knokko.rpg.spells.Spells;
import nl.knokko.rpg.tiles.Chest;
import nl.knokko.rpg.toolbars.ToolBar;
import nl.knokko.rpg.utils.JukeBox;
import nl.knokko.rpg.utils.Resources;

public class GuiBattle extends Gui {
	
	public Entity[] players;
	public Entity[] enemies;
	
	public Point[] startPoints;
	protected Point attackerStartPoint;
	public Entity currentAttacker;
	protected Entity currentTarget;
	public boolean attackerIsReturning;
	protected ItemConsumable selectedPotion;
	public Random random = new Random();
	public ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
	public ArrayList<Effect> effects = new ArrayList<Effect>();
	public ArrayList<Spell> spells = new ArrayList<Spell>();
	public ArrayList<String> specialLoot = new ArrayList<String>();
	public Spell selectedSpell;
	public ToolBar toolbar;
	public String texture;
	public String music;
	
	public final Cursor sword = Resources.getCursor("sword");
	public final Cursor wand = Resources.getCursor("wand");
	public final Cursor potion = Resources.getCursor("potion");
	
	public int lootMoney;
	public int lootXp;
	public byte onTurn;
	protected int status;
	protected int selectedButton;
	protected byte cooldown;
	
	
	private GuiBattle(Game game, String texture){
		super(game, texture);
		this.texture = texture;
		players = new Entity[]{game.player, game.player2, game.player3};
		enemies = new Entity[4];
		startPoints = new Point[players.length + enemies.length];
		setStartPoints();
		JukeBox.playMusic("battle", true);
	}
	
	public GuiBattle(Game game, String texture, Entity[] players, Entity[] enemies){
		this(game, texture, players, enemies, true);
	}
	
	public GuiBattle(Game game, String texture, Entity[] players, Entity[] enemies, boolean startDirect){
		this(game, texture, players, enemies, startDirect, "battle");
	}
	
	public GuiBattle(Game theGame, String texture, Entity[] players, Entity[] enemies, boolean startDirect, String music) {
		super(theGame, texture);
		this.players = players;
		this.enemies = enemies;
		this.texture = texture;
		startPoints = new Point[players.length + enemies.length];
		placeFighters();
		//addNormalButtons();
		int t = 0;
		while(t < enemies.length){
			if(enemies[t] != null){
				loot.addAll(enemies[t].getLoot());
				lootMoney += enemies[t].getLootMoney();
				lootXp += enemies[t].getLootXp();
				specialLoot.addAll(enemies[t].getSpecialLoot());
			}
			++t;
		}
		setMusic(music);
		onTurn = (byte) (players.length - 1);
		changeTurn(true);
		if(startDirect)
			activate();
	}
	
	public GuiBattle setMusic(String song){
		music = song;
		return this;
	}
	
	public GuiBattle activate(){
		placeFighters();
		getCurrentEntity().updateEffects();
		startMusic();
		return this;
	}
	
	@Override
	public void paint(Graphics gr){
		gr.drawImage(image, 0, 0, game.getWidth(), game.getHeight(), null);
		int t = 0;
		while(t < players.length){
			if(players[t] != null){
				players[t].paintInBattle(gr, players[t] == currentAttacker && attackerIsReturning);
				if(t == onTurn){
					try {
						Point p = players[t].battlePoint;
						gr.drawImage(Resources.getImage("sprites/battlemarker.png"), factorX(p.x + 40), factorY(p.y - 40), factorX(16), factorY(32), null);
					} catch(Exception ex){}
				}
			}
			++t;
		}
		t = 0;
		while(t < enemies.length){
			if(enemies[t] != null){
				enemies[t].paintInBattle(gr, !(enemies[t] == currentAttacker && attackerIsReturning));
			}
			++t;
		}
		t = 0;
		while(t < effects.size()){
			Effect effect = effects.get(t);
			if(effect != null){
				effect.paint(gr);
			}
			++t;
		}
		t = 0;
		while(t < spells.size()){
			Spell spell = spells.get(t);
			if(spell != null){
				spell.paint(gr);
			}
			++t;
		}
		toolbar = null;
		t = 0;
		while(t < buttons.size()){
			Button b = buttons.get(t);
			b.paint(gr);
			Point m = game.getMousePosition();
			if(b.isHit(m)){
				toolbar = toolbarForButton(b);
				selectedButton = t;
			}
			++t;
		}
		if(toolbar != null)
			toolbar.paint(gr);
	}
	
	public ToolBar toolbarForButton(Button button) {
		ToolBar toolbar = new ToolBar();
		toolbar.min = new Point(button.minX, button.minY >= 450 ? button.minY - 50 : button.maxY + 50);
		addTextForButton(toolbar, button);
		if(button instanceof SpellButton)
			((SpellButton) button).spell.addInfo(toolbar);
		return toolbar;
	}
	
	private void addTextForButton(ToolBar toolbar, Button button){
		if(button.name.matches("attack")){
			toolbar.text.add("Attack somebody with a basic attack.");
			toolbar.increaseToSize(320, 0);
			toolbar.increaseSize(0, 25);
		}
		else if(button.name.matches("special")){
			toolbar.text.add("Select a spell and use it on somebody.");
			toolbar.increaseToSize(320, 0);
			toolbar.increaseSize(0, 25);
		}
		else if(button.name.matches("use item")){
			toolbar.text.add("Select a potion in your inventory and give");
			toolbar.text.add("it to someone.");
			toolbar.increaseToSize(340, 0);
			toolbar.increaseSize(0, 45);
		}
		else if(button.name.matches("wait")){
			toolbar.text.add("Skip this turn.");
			toolbar.increaseToSize(130, 0);
			toolbar.increaseSize(0, 25);
		}
		else if(button.name.matches("run")){
			toolbar.text.add("Run away from this fight.");
			toolbar.text.add("You won't get any xp or loot.");
			toolbar.increaseToSize(250, 0);
			toolbar.increaseSize(0, 45);
		}
		else if(button.name.matches("save and quit")){
			toolbar.text.add("Save all your progress and stop the game.");
			toolbar.increaseToSize(355, 0);
			toolbar.increaseSize(0, 25);
		}
		else if(button.name.matches("options")){
			toolbar.text.add("You can change several options in this menu.");
			toolbar.increaseToSize(365, 0);
			toolbar.increaseSize(0, 25);
		}
		else if(button.id <= -1 && button.id >= -4){
			if(selectedPotion != null){
				toolbar.text.add("Use this potion on this enemy,");
				toolbar.text.add("this action will heal this enemy.");
				toolbar.text.add("Only use this potion if you know");
				toolbar.text.add("what you are doing!");
				toolbar.increaseSize(0, 85);
				toolbar.increaseToSize(300, 0);
			}
			else if(selectedSpell != null){
				toolbar.text.add("Use this spell on this enemy.");
				toolbar.increaseToSize(250, 0);
				toolbar.increaseSize(0, 25);
			}
			else {
				toolbar.text.add("Attack this enemy.");
				toolbar.increaseToSize(170, 0);
				toolbar.increaseSize(0, 25);
			}
		}
		else if(button.id <= -5 && button.id >= -8){
			if(selectedPotion != null){
				toolbar.text.add("Use this potion on this player.");
				toolbar.increaseToSize(250, 0);
				toolbar.increaseSize(0, 25);
			}
			else if(selectedSpell != null){
				toolbar.text.add("Use this spell on this player.");
				toolbar.increaseToSize(500, 0);
				toolbar.increaseSize(0, 25);
			}
			else {
				toolbar.text.add("Attack this player, only do this");
				toolbar.text.add("if you know what you are doing!");
				toolbar.increaseToSize(300, 0);
				toolbar.increaseSize(0, 45);
			}
		}
		else if(button instanceof ButtonSpell){
			Spell spell = ((ButtonSpell) button).spell;
			toolbar.text.add("Use the following spell on somebody:");
			toolbar.text.add("");
			spell.addInfo(toolbar);
			toolbar.increaseSize(0, 65);
			toolbar.increaseToSize(320, 0);
			toolbar.min.x += 150;
			toolbar.color = spell.getElement().color;
			if(toolbar.min.y + toolbar.height > game.getHeight() - 5)
				toolbar.min.y = game.getHeight() - toolbar.height - 5;
		}
		else if(button.id >= 0 && Items.fromId(button.id) instanceof ItemConsumable){
			ItemConsumable potion = (ItemConsumable) Items.fromId(button.id);
			toolbar.text.add("Use the following item");
			toolbar.text.add("on somebody:");
			toolbar.text.add("");
			toolbar.increaseSize(0, 65);
			toolbar.add(potion.getToolBar());
			toolbar.min.x += 150;
		}
	}

	@Override
	public void escapePressed(){
		if(game.difficulty < 3){
			if(onTurn >= 0){
				super.escapePressed();
				game.player.onBattleEnd();
				game.player2.onBattleEnd();
				if(game.player3 != null)
					game.player3.onBattleEnd();
				stopMusic();
			}
		}
	}
	
	@Override
	public boolean canClose(){
		return false;
	}
	
	@Override
	public Cursor getCursor(){
		if(status == 1)
			return sword;
		if(status == 2)
			return wand;
		if(status == 3)
			return potion;
		return null;
	}
	
	@Override
	public void keyPressed(boolean[] keys){
		if(cooldown > 0){
			--cooldown;
			return;
		}
		if(keys[KeyEvent.VK_LEFT]){
			if(status == 0){
				if(selectedButton > 2 && selectedButton <= 5)
					--selectedButton;
				else if(selectedButton == 2 && game.difficulty < 3)
					selectedButton = 6;
				else if(selectedButton == 1)
					selectedButton = 0;
			}
			else if(status == 1){
				if(selectedButton > 2 && selectedButton < 3 + enemies.length)
					--selectedButton;
				else if(selectedButton == 1)
					selectedButton = 0;
				else if(selectedButton > 3 + enemies.length)
					--selectedButton;
			}
			else if(status == 2 || status == 3){
				if(selectedButton == 1)
					selectedButton = 0;
				else if(selectedButton > 2)
					selectedButton = 2;
			}
			moveMouse();
		}
		else if(keys[KeyEvent.VK_RIGHT]){
			if(status == 0){
				if(selectedButton >= 2 && selectedButton < 5)
					++selectedButton;
				else if(selectedButton == 6)
					selectedButton = 2;
				else if(selectedButton == 0)
					selectedButton = 1;
			}
			else if(status == 1){
				if(selectedButton >= 2 && selectedButton < 2 + enemies.length)
					++selectedButton;
				else if(selectedButton == 0)
					selectedButton = 1;
				else if(selectedButton >= 3 + enemies.length && selectedButton < buttons.size() - 1)
					++selectedButton;
			}
			else if(status == 2 || status == 3){
				if(selectedButton == 0)
					selectedButton = 1;
				else if(selectedButton == 2)
					selectedButton = 3;
			}
			moveMouse();
		}
		else if(keys[KeyEvent.VK_DOWN]){
			if(status == 0)
				selectedButton = 5;
			else if(status == 1){
				if(selectedButton > 2 + enemies.length)
					selectedButton -= enemies.length;
				else if(selectedButton < 2)
					selectedButton = 2;
			}
			else if(status == 2 || status == 3){
				if(selectedButton < 2)
					selectedButton = 2;
				else if(selectedButton > 3)
					--selectedButton;
			}
			moveMouse();
		}
		else if(keys[KeyEvent.VK_UP]){
			if(status == 0)
				selectedButton = 0;
			else if(status == 1){
				if(selectedButton == 2 || selectedButton > 2 + enemies.length)
					selectedButton = 0;
				else if(selectedButton > 2 && selectedButton <= 2 + enemies.length)
					selectedButton += enemies.length;
			}
			else if(status == 2 || status == 3){
				if(selectedButton == 2)
					selectedButton = 0;
				else if(selectedButton > 2)
					++selectedButton;
			}
			moveMouse();
		}
		else if(keys[KeyEvent.VK_SPACE]){
			if(selectedButton >= 0 && selectedButton < buttons.size()){
				buttonClicked(buttons.get(selectedButton));
				cooldown = 10;
			}
		}
	}
	
	public void startMusic(){
		if(music == null)
			music = "battle";
		JukeBox.playMusic(music, true);
	}
	
	protected void moveMouse(){
		if(selectedButton >= buttons.size())
			selectedButton = buttons.size() - 1;
		cooldown = 5;
		Robot bot;
		try {
			bot = new Robot();
		} catch (AWTException e) {
			Game.console.println("This game is not allowed to move the mouse: " + e.getLocalizedMessage());
			bot = null;
		}
		if(selectedButton >= 0 && selectedButton < buttons.size() && bot != null){
			Button but = buttons.get(selectedButton);
			bot.mouseMove((int) ((but.minX + (but.maxX - but.minX) / 2) * Board.factor()), (int) ((but.minY + (but.maxY - but.minY) / 2) * Board.factor()));
		}
	}
	
	protected void setStartPoints(){
		int t = 0;
		int f = 0;
		while(t < players.length){
			if(t > 0 && t < 3){
				f += 20;
			}
			else if(t == 3){
				f -= 30;
			}
			startPoints[t] = new Point(150 + f, 300 + 100 * t);
			++t;
		}
		t = 0;
		while(t < enemies.length){
			if(t > 0 && t < 3){
				f += 20;
			}
			else if(t == 3){
				f -= 30;
			}
			startPoints[t + players.length] = new Point(1300 - f, 300 + 100 * t);
			++t;
		}
	}
	
	protected void placeFighters(){
		int t = 0;
		int f = 0;
		while(t < players.length){
			if(t > 0 && t < 3){
				f += 20;
			}
			else if(t == 3){
				f -= 30;
			}
			if(players[t] != null)
				players[t].battlePoint = new Point(150 + f, 300 + 100 * t);
			startPoints[t] = new Point(150 + f, 300 + 100 * t);
			++t;
		}
		t = 0;
		while(t < enemies.length){
			if(t > 0 && t < 3){
				f += 20;
			}
			else if(t == 3){
				f -= 30;
			}
			if(enemies[t] != null){
				enemies[t].battlePoint = new Point(1300 - f, 300 + 100 * t);
				startPoints[t + players.length] = new Point(1300 - f, 300 + 100 * t);
			}
			++t;
		}
	}
	
	public void attack(Entity attacker, Entity target, boolean isRanged){
		if(!isRanged){
			int ind = getEntityIndex(attacker);
			
			if(ind >= 0)
				attackerStartPoint = startPoints[ind].getLocation();
			else
				attackerStartPoint = startPoints[-ind - 1 + players.length].getLocation();
			currentAttacker = attacker;
			currentTarget = target;
			attackerIsReturning = false;
		}
	}
	
	@Override
	public void update(){
		int i = 0;
		while(i < effects.size()){
			Effect effect = effects.get(i);
			if(effect != null){
				effect.update();
				if(!effect.enabled){
					effects.remove(i);
				}
			}
			++i;
		}
		if(currentAttacker != null && currentTarget != null && spells.isEmpty()){
			Point a = currentAttacker.battlePoint;
			int s = 30 / game.fpsFactor;
			if(currentAttacker.walkProgress < 5){
				++currentAttacker.walkProgress;
			}
			else {
				currentAttacker.walkProgress = 0;
			}
			if(!attackerIsReturning){
				Point t = currentTarget.battlePoint;
				if(a.x < t.x){
					a.x += s;
				}
				if(a.x > t.x){
					a.x -= s;
				}
				if(a.y < t.y){
					a.y += s;
				}
				if(a.y > t.y){
					a.y -= s;
				}
				if(a.distance(t) < s){
					attackerIsReturning = true;
					strike(false, false);
				}
			}
			else {
				Point b = attackerStartPoint;
				if(a.x < b.x){
					a.x += s;
				}
				if(a.x > b.x){
					a.x -= s;
				}
				if(a.y < b.y){
					a.y += s;
				}
				if(a.y > b.y){
					a.y -= s;
				}
				if(a.distance(b) < s){
					currentAttacker.battlePoint = b;
					currentAttacker.walkProgress = 0;
					currentAttacker = null;
					currentTarget = null;
					attackerStartPoint = null;
					attackerIsReturning = false;
					changeTurn(true);
				}
			}
		}
		int t = 0;
		while(t < spells.size()){
			Spell spell = spells.get(t);
			if(spell != null){
				spell.update();
				if(spell.disabled){
					spells.remove(t);
					if(spells.isEmpty()){
						changeTurn(true);
					}
				}
			}
			else {
				spells.remove(t);
			}
			++t;
		}
		checkWin();
	}
	
	protected void strike(boolean isMagic, boolean isRanged){
		Attack attack = new Attack(currentAttacker.getWeaponElement(), false, false);
		int power = currentAttacker.getPower(attack, currentTarget);
		currentTarget.attack(attack, currentAttacker, power, this);
		JukeBox.playShortSound("hit");
	}
	
	public Entity randomPlayer(){
		if(game.currentGUI == this){
			checkWin();
			if(game.currentGUI != this){
				Game.console.println("GuiBattle#randomPlayer(): The battle is over; returning null...");
				return null;
			}
		}
		Entity entity = players[random.nextInt(players.length)];
		while(entity == null)
			entity = players[random.nextInt(players.length)];
		return entity;
	}
	
	public void checkWin(){
		int t = 0;
		int deads = 0;
		while(t < players.length){
			if(players[t] != null && players[t].currentHealth <= 0){
				players[t].moveX = 0;
				players[t].moveY = 0;
				players[t] = null;
			}
			if(players[t] == null){
				++deads;
			}
			++t;
		}
		if(deads == players.length){
			lose();
			return;
		}
		t = 0;
		deads = 0;
		while(t < enemies.length){
			if(enemies[t] != null && enemies[t].currentHealth <= 0){
				enemies[t] = null;
			}
			if(enemies[t] == null){
				++deads;
			}
			++t;
		}
		if(deads == enemies.length){
			win();
		}
	}
	
	public void lose(){
		JukeBox.stop();
		game.currentGUI = new GuiGameOver(game);
	}
	
	public void win(){
		Chest chest = new Chest(-1, new Point(-100, -100));
		int t = 0;
		while(t < enemies.length){
			if(enemies[t] != null){
				enemies[t].walkProgress = 0;
				enemies[t].onBattleEnd();
			}
			++t;
		}
		t = 0;
		while(t < players.length){
			if(players[t] != null){
				players[t].walkProgress = 0;
				players[t].onBattleEnd();
			}
			++t;
		}
		int i = 0;
		while(i < loot.size()){
			chest.inventory.addItemStack(loot.get(i));
			++i;
		}
		i = 0;
		while(i < specialLoot.size()){
			chest.specialItems.add(specialLoot.get(i));
			++i;
		}
		i = 0;
		while(i < players.length){
			if(players[i] instanceof Player){
				((Player)players[i]).xp += lootXp;
				((Player)players[i]).totalXp += lootXp;
			}
			++i;
		}
		chest.inventory.money = lootMoney;
		stopMusic();
		game.currentGUI = new GuiChest(game, "", chest, game.player);
	}
	
	protected void stopMusic(){
		JukeBox.stop();
		String music = game.world.map.getMusic();
		if(music != null)
			JukeBox.playMusic(music, true);
	}
	
	public Entity randomEnemy(){
		checkWin();
		Entity entity = enemies[random.nextInt(enemies.length)];
		while(entity == null){
			entity = enemies[random.nextInt(enemies.length)];
		}
		return entity;
	}
	
	public void changeTurn(boolean start){
		if(onTurn < players.length - 1){
			++onTurn;
		}
		else {
			onTurn =  (byte) -enemies.length;
		}
		if(start){
			getCurrentEntity();
			if(onTurn < 0){
				getCurrentEntity().updateEffects();
				if(getCurrentEntity().currentHealth <= 0){
					changeTurn(true);
					return;
				}
				clearButtons();
				Spell spell = getCurrentEntity().selectSpell(randomPlayer(), this);
				if(spell == null && getCurrentEntity().canStrike()){
					attack(getCurrentEntity(), randomPlayer(), false);
				}
				else if(spell != null){
					spell.user = getCurrentEntity();
					if(spell.target == null)
						spell.target = randomPlayer();
					spell.position = getCurrentEntity().battlePoint.getLocation();
					spell.battle = this;
					spell.disabled = false;
					getCurrentEntity().currentMana -= spell.mana;
					spells.add(spell);
				}
				else {
					changeTurn(true);
				}
			}
			else {
				getCurrentEntity().updateEffects();
				addNormalButtons();
			}
		}
	}
	
	public Entity getCurrentEntity(){
		Entity entity = null;
		while(entity == null){
			if(onTurn >= 0){
				entity = players[onTurn];
			}
			else {
				entity = enemies[-onTurn - 1];
			}
			if(entity == null){
				changeTurn(false);
			}
		}
		return entity;
	}
	
	@Override
	public void saveData(){
		try {
			PrintWriter writer = new PrintWriter(Resources.getSaveFile() + "/battle.txt");
			writer.println("texture: " + texture);
			writer.println("onTurn: " + onTurn);
			if(attackerStartPoint != null){
				writer.println("startpoint attacker x: " + attackerStartPoint.x);
				writer.println("startpoint attacker y: " + attackerStartPoint.y);
			}
			else {
				writer.println("startpoint attacker: null");
				writer.println("startpoint attacker: null");
			}
			writer.println(currentAttacker != null);
			if(currentTarget != null){
				writer.println("target: " + getEntityIndex(currentTarget));
			}
			else 
				writer.println("target: -5");
			writer.println("attacker returning: " + attackerIsReturning);
			writer.println("lootmoney: " + lootMoney);
			writer.println("lootxp: " + lootXp);
			int t = 0;
			while(t < enemies.length){
				Entity enemy = enemies[t];
				if(enemy != null){
					enemy.saveBattleData(writer, t);
				}
				else
					writer.println("enemy " + t + ": null");
				++t;
			}
			t = 0;
			while(t < players.length){
				Entity player = players[t];
				if(player != null)
					player.saveBattleData(writer, t);
				else
					writer.println("player " + t + ": null");
				++t;
			}
			t = 0;
			writer.println("loot: {");
			while(loot != null && t < loot.size()){
				writer.println(loot.get(t).size + "X" + loot.get(t).item.id);
				++t;
			}
			t = 0;
			while(specialLoot != null && t < specialLoot.size()){
				writer.println(specialLoot.get(t));
				++t;
			}
			writer.println("}");
			t = 0;
			while(t < spells.size()){
				Spell spell = spells.get(t);
				if(spell != null)
					spell.saveBattleData(writer, t);
				++t;
			}
			writer.close();
		} catch(Exception ex){
			Game.console.println("GuiBattle.saveData(): Failed to save this battle:");
			Game.console.println();
			ex.printStackTrace(Game.console);
			Game.console.println();
		}
	}
	
	public static GuiBattle loadData(Game game){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Resources.getLoadFile() + "/battle.txt"));
			GuiBattle battle = new GuiBattle(game, reader.readLine().substring(9));
			battle.onTurn = Byte.decode(reader.readLine().substring(8));
			try {
				battle.attackerStartPoint = new Point(Integer.decode(reader.readLine().substring(23)), Integer.decode(reader.readLine().substring(23)));
			} catch(Exception ex){
				reader.readLine();
			}
			boolean attack = reader.readLine().contains("true");
			int targetIndex = Integer.decode(reader.readLine().substring(8));
			battle.attackerIsReturning = ((reader.readLine().substring(20))).contains("true");
			battle.lootMoney = Integer.decode(reader.readLine().substring(11));
			battle.lootXp = Integer.decode(reader.readLine().substring(8));
			String ent = reader.readLine();
			int t = 0;
			while(ent.contains("enemy") && t < 4){
				try {
					if(!ent.contains("null")){
						int ind = ent.indexOf("{");
						battle.enemies[t] = (Entity) Class.forName(ent.substring(9, ind)).getConstructor(Game.class, Point.class, int.class).newInstance(game, new Point(-10000, -10000), 0);
						if(battle.enemies[t] != null)
							battle.enemies[t].loadBattleData(reader);
					}
				} catch(Exception ex){
					Game.console.println("failed to load battle enemy " + t + ":");
					ex.printStackTrace(Game.console);
					Game.console.println();
				}
				ent = reader.readLine();
				++t;
			}
			t = 0;
			while(ent.contains("player") && t < 4){
				try {
					if(!ent.contains("null")){
						battle.players[t] = (Entity) Game.class.getField("player" + (t == 0 ? "" : t + 1)).get(game);
						if(battle.players[t] != null)
							battle.players[t].loadBattleData(reader);
					}
				} catch(Exception ex){
					Game.console.println("failed to load battle player " + t + ":");
					ex.printStackTrace(Game.console);
					Game.console.println();
				}
				ent = reader.readLine();
				++t;
			}
			String line = reader.readLine();
			while(!line.contains("}")){
				int indx = line.indexOf("X");
				if(indx != -1)
					battle.loot.add(new ItemStack(Items.fromId(Integer.decode(line.substring(indx + 1))), Integer.decode(line.substring(0, indx))));
				else
					battle.specialLoot.add(line);
				line = reader.readLine();
			}
			line = reader.readLine();
			while(line != null && line.contains("spell")){
				int ind1 = line.indexOf(":");
				int ind2 = line.indexOf("{");
				try {
					Spell spell = Spells.fromString(line.substring(ind1 + 2, ind2));
					spell.loadBattleData(reader, battle);
					battle.spells.add(spell);
				} catch(Exception ex){
					Game.console.println("failed to load battle spells:");
					ex.printStackTrace(Game.console);
					Game.console.println();
				}
				line = reader.readLine();
			}
			reader.close();
			battle.currentTarget = battle.getEntity(targetIndex);
			if(attack)
				battle.currentAttacker = battle.getEntity(battle.onTurn);
			battle.getCurrentEntity();
			boolean flag = (battle.currentAttacker == null || battle.currentTarget == null) && battle.spells.isEmpty();
			if(battle.onTurn < 0 && flag){
				battle.clearButtons();
				ArrayList<Spell> spells = battle.getCurrentEntity().getSpells();
				if(spells.isEmpty() || (battle.random.nextBoolean() && battle.getCurrentEntity().canStrike())){
					battle.attack(battle.getCurrentEntity(), battle.randomPlayer(), false);
				}
				else if(spells.size() > 0){
					Spell spell = spells.get(battle.random.nextInt(spells.size()));
					spell.user = battle.getCurrentEntity();
					spell.target = battle.randomPlayer();
					spell.position = battle.getCurrentEntity().battlePoint.getLocation();
					spell.battle = battle;
					battle.getCurrentEntity().currentMana -= spell.mana;
					battle.spells.add(spell);
				}
				else {
					battle.changeTurn(true);
				}
			}
			else if(flag){
				battle.getCurrentEntity().updateEffects();
				battle.addNormalButtons();
			}
			return battle;
		} catch(Exception ex){
			Game.console.println("failed to load battle: (this is ok if you were not in a battle while you saved)" + ex.getLocalizedMessage());
			Game.console.println();
			return null;
		}
	}
	
	public Entity getEntity(int turn){
		if(turn >= 0 && turn < players.length)
			return players[turn];
		if(turn < 0 && turn >= -enemies.length)
			return enemies[-turn - 1];
		return null;
	}
	
	public int getEntityIndex(Entity entity){
		int target = -50;
		int t = 0;
		while(t < players.length){
			if(players[t] == entity)
				target = t;
			++t;
		}
		t = -4;
		while(t < 0){
			if(enemies.length > (-t - 1) && enemies[-t - 1] == entity)
				target = t;
			++t;
		}
		return target;
	}
	
	@Override
	public void buttonClicked(Button button){
		if(button instanceof ButtonSpell){
			ButtonSpell button2 = (ButtonSpell) button;
			if(getCurrentEntity().currentMana >= button2.spell.mana){
				selectedSpell = button2.spell;
				addAttackButtons();
			}
			return;
		}
		if(button.name.matches("options"))
			game.currentGUI = new GuiBattleOptions();
		if(button.name.matches("save and quit"))
			game.close(true);
		if(button.name.matches("attack"))
			addAttackButtons();
		else if(button.name.matches("wait"))
			changeTurn(true);
		else if(button.name.matches("back")){
			addNormalButtons();
			selectedSpell = null;
			selectedPotion = null;
		}
		else if(button.name.matches("special"))
			addSpecialButtons(getCurrentEntity());
		else if(button.name.matches("use item") && getCurrentEntity() instanceof Player)
			addItemButtons((Player) getCurrentEntity());
		else if(button.name.matches("run")){
			escapePressed();
			return;
		}
		else if(button.id == -1)
			activateAttack(0, false);
		else if(button.id == -2)
			activateAttack(1, false);
		else if(button.id == -3)
			activateAttack(2, false);
		else if(button.id == -4)
			activateAttack(3, false);
		else if(selectedPotion != null){
			if(button.name.matches("back")){
				addNormalButtons();
				return;
			}
			if(button.id == -5 && players.length > 0)
				usePotion(players[0]);
			if(button.id == -6 && players.length > 1)
				usePotion(players[1]);
			if(button.id == -7 && players.length > 2)
				usePotion(players[2]);
			if(button.id == -8 && players.length > 3)
				usePotion(players[3]);
		}
		else if(button.id == -5 && players.length > 0)
			activateAttack(0, true);
		else if(button.id == -6 && players.length > 1)
			activateAttack(1, true);
		else if(button.id == -7 && players.length > 2)
			activateAttack(2, true);
		else if(button.id == -8 && players.length > 3)
			activateAttack(3, true);
		if(button.id >= 0 && Items.fromId(button.id) instanceof ItemConsumable){
			selectedPotion = (ItemConsumable) Items.fromId(button.id);
			addAttackButtons();
		}
	}
	
	@Override
	public void click(){
		Point mouse = game.getMousePosition();
		int t = 0;
		while(t < buttons.size()){
			Button button = buttons.get(t);
			if(button.isHit(mouse)){
				buttonClicked(button);
				return;
			}
			++t;
		}
		t = 0;
		while(t < players.length){
			Entity e = players[t];
			if(e != null){
				if(mouse.x >= factorX(e.battlePoint.x) && mouse.x <= factorX(e.battlePoint.x + 120) && mouse.y >= factorY(e.battlePoint.y) && mouse.y <= factorY(e.battlePoint.y + 120)){
					game.currentGUI = new GuiBattleInfo(e);
					e.refreshModels();
				}
			}
			++t;
		}
		t = 0;
		while(t < enemies.length){
			Entity e = enemies[t];
			if(e != null){
				if(mouse.x >= factorX(e.battlePoint.x) && mouse.x <= factorX(e.battlePoint.x + 120) && mouse.y >= factorY(e.battlePoint.y) && mouse.y <= factorY(e.battlePoint.y + 120)){
					game.currentGUI = new GuiBattleInfo(e);
					e.refreshModels();
				}
			}
			++t;
		}
	}
	
	protected void addNormalButtons(){
		clearButtons();
		addButton(100, 700, 200, 800, "wait");
		addButton(250, 700, 450, 800, "use item");
		addButton(500, 700, 700, 800, "special");
		addButton(750, 700, 900, 800, "attack");
		if(game.difficulty < 3)
			buttons.add(new Button(10, 700, 90, 800, "sprites/buttons/button.png", "run", normalFont, Color.RED));
		selectedButton = 5;
		moveMouse();
	}
	
	protected void addAttackButtons(){
		clearButtons();
		status = 1;
		int t = 0;
		addButton(100, 700, 250, 800, "back");
		while(t < enemies.length){
			if(enemies[t] != null){
				buttons.add(new Button(300 * t + 300, 700, 300 * t + 550, 800, "sprites/buttons/button.png", enemies[t].getName(), normalFont, Color.RED, -t - 1));
			}
			++t;
		}
		t = 0;
		while(t < players.length){
			if(players[t] != null){
				buttons.add(new Button(300 + 300 * t, 550, 550 + 300 * t, 650, "sprites/buttons/button.png", players[t].getName(), normalFont, Color.GREEN, -t - 5));
			}
			++t;
		}
		if((selectedSpell == null || !selectedSpell.isPositive()) && selectedPotion == null)
			selectedButton = 3;
		else
			selectedButton = 3 + enemies.length;
		moveMouse();
	}
	
	protected void addItemButtons(Player player){
		clearButtons();
		status = 3;
		selectedButton = 3;
		int t = 0;
		int x = 250;
		addButton(100, 700, 200, 800, "back");
		ArrayList<ItemStack> items = player.inventory.getConsumableItems();
		while(t < items.size() && x < game.getWidth() - 100){
			int y = 700;
			while(y > 0 && t < items.size()){
				addPotionButton(x, y, x + 100, y + 50, items.get(t));
				y -= 51;
				++t;
			}
			x += 101;
		}
		if(selectedButton >= buttons.size())
			selectedButton = 2;
		moveMouse();
	}
	
	protected void addPotionButton(int minX, int minY, int maxX, int maxY, ItemStack stack){
		buttons.add(new PotionButton(minX, minY, maxX, maxY, stack));
	}
	
	protected void addSpecialButtons(Entity current){
		clearButtons();
		status = 2;
		selectedButton = 3;
		addButton(100, 700, 200, 800, "back");
		ArrayList<Spell> spells = current.getSpells();
		int t = 0;
		int x = 250;
		while(t < spells.size() && x < game.getWidth() - 100){
			int y = 750;
			while(y > 0 && t < spells.size()){
				buttons.add(new ButtonSpell(x, y, x + 100, y + 50, new Font("TimesRoman", Font.PLAIN, 20), current.currentMana >= spells.get(t).mana ? Color.BLACK : Color.RED, 30 + t, spells.get(t)));
				y -= 51;
				++t;
			}
			x += 101;
		}
		if(selectedButton >= buttons.size())
			selectedButton = 2;
		moveMouse();
	}
	
	protected void clearButtons(){
		buttons = new ArrayList<Button>();
		addButton(100, 25, 300, 125, "options");
		addButton(400, 25, 700, 125, "save and quit");
		status = 0;
		selectedButton = -1;
	}
	
	protected void activateAttack(int index, boolean player){
		if(selectedSpell == null && selectedPotion == null){
			attack(getCurrentEntity(), player ? players[index] : enemies[index], false);
			clearButtons();
		}
		else if(selectedSpell != null)
			castSpell(player ? players[index] : enemies[index]);
		else 
			usePotion(player ? players[index] : enemies[index]);
	}
	
	protected void castSpell(Entity target){
		selectedSpell.setTarget(target);
		selectedSpell.battle = this;
		selectedSpell.position = getCurrentEntity().battlePoint.getLocation();
		spells.add(selectedSpell);
		getCurrentEntity().currentMana -= selectedSpell.mana;
		clearButtons();
		selectedSpell = null;
	}
	
	protected void usePotion(Entity target){
		if(selectedPotion.consume(target, this, new ItemStack(selectedPotion, 1))){
			((Player)getCurrentEntity()).inventory.removeItemStack(new ItemStack(selectedPotion, 1));
			selectedPotion = null;
			changeTurn(true);
		}
	}
	
	public class GuiBattleInfo extends Gui {
		
		public final Entity entity;
		
		public GuiBattleInfo(Entity theEntity) {
			super(GuiBattle.this.game, null);
			entity = theEntity;
			addButton(1200, 200, 1520, 300, "return to battle");
			addButton(1200, 350, 1520, 450, "element stats");
			addButton(1200, 500, 1520, 600, "equipment");
			addButton(1200, 650, 1520, 750, "options");
		}
		
		@Override
		public void paint(Graphics gr){
			gr.setColor(Color.RED);
			gr.fillRect(0, 0, game.getWidth(), game.getHeight());
			super.paint(gr);
			gr.setFont(font());
			gr.setColor(Color.YELLOW);
			if(entity instanceof Player){
				gr.drawString("xp: " + ((Player)entity).xp, factorX(200), factorY(122));
			}
			else {
				gr.drawString("level " + entity.level + " " + entity, factorX(200), factorY(122));
			}
			int h = (int) (((double)entity.currentHealth / (double)entity.maxHealth) * 400);
			int m = (int) (((double)entity.currentMana / (double)entity.maxMana) * 400);
			gr.setColor(Color.GREEN);
			gr.fillRect(factorX(200), factorY(150), factorX(h), factorY(50));
			gr.setColor(new Color(200, 0, 200));
			gr.fillRect(factorX(200), factorY(220), factorX(m), factorY(50));
			gr.setColor(Color.RED);
			gr.fillRect(factorX(200 + h), factorY(150), factorX(400 - h), factorY(50));
			gr.fillRect(factorX(200 + m), factorY(220), factorX(400 - m), factorY(50));
			gr.setColor(Color.BLACK);
			gr.drawRect(factorX(200), factorY(150), factorX(400), factorY(50));
			gr.drawRect(factorX(200), factorY(220), factorX(400), factorY(50));
			gr.setColor(new Color(0, 100, 0));
			gr.drawString("health: " + entity.currentHealth + " / " + entity.maxHealth, factorX(200), factorY(192));
			gr.setColor(Color.BLUE);
			gr.drawString("mana: " + entity.currentMana + " / " + entity.maxMana, factorX(200), factorY(262));
			gr.setColor(new Color(0, 0, 155));
			gr.drawString("spirit: " + entity.spirit, factorX(200), factorY(342));
			gr.setColor(new Color(100, 0, 0));
			gr.drawString("strength: " + entity.strength, factorX(200), factorY(402));
			gr.setColor(Color.GRAY);
			gr.drawString("armor: " + entity.getArmor(new Attack(Element.NORMAL, false, false)), factorX(200), factorY(462));
			gr.setColor(new Color(100, 0, 100));
			gr.drawString("magic armor: " + entity.getArmor(new Attack(Element.NORMAL, false, true)), factorX(200), factorY(522));
			drawEntity(gr, entity, new Point(factorX(800), factorY(300)));
			gr.setColor(new Color(100, 0, 0));
			gr.setFont(font());
			gr.drawString("power: " + entity.getWeaponPower(new Attack(Element.NORMAL, false, false)), factorX(200), factorY(582));
			gr.drawString("magic power: " + entity.getWeaponPower(new Attack(Element.NORMAL, true, true)), factorX(200), factorY(642));
		}
		
		@Override
		public void escapePressed(){
			game.currentGUI = GuiBattle.this;
			entity.refreshModels();
		}
		
		@Override
		public void buttonClicked(Button button){
			if(button.name.matches("return to battle"))
				escapePressed();
			if(button.name.matches("element stats"))
				game.currentGUI = new GuiBattleElementStats();
			if(button.name.matches("equipment"))
				game.currentGUI = new GuiBattleEquipment();
			if(button.name.matches("options"))
				game.currentGUI = new GuiBattleOptions();
		}

		@Override
		public boolean canClose(){
			return false;
		}
		
		public class GuiBattleElementStats extends Gui {

			public GuiBattleElementStats() {
				super(GuiBattleInfo.this.game, null);
				addButton(1200, 200, 1520, 300, "return to battle");
				addButton(1200, 550, 1520, 650, "basic stats");
				addButton(1200, 700, 1520, 800, "equipment");
			}
			
			@Override
			public void paint(Graphics gr){
				gr.setColor(Color.RED);
				gr.fillRect(0, 0, game.getWidth(), game.getHeight());
				super.paint(gr);
				gr.setFont(font());
				gr.setColor(new Color(150, 0, 150));
				gr.fillRect(0, factorY(350), game.getWidth(), factorY(150));
				gr.setColor(Color.BLACK);
				gr.drawLine(0, factorY(350), game.getWidth(), factorY(350));
				gr.drawLine(0, factorY(400), game.getWidth(), factorY(400));
				gr.drawLine(0, factorY(450), game.getWidth(), factorY(450));
				gr.drawLine(0, factorY(500), game.getWidth(), factorY(500));
				int f3 = factorY(350);
				int f5 = factorY(500);
				gr.drawLine(0, f3, 0, f5);
				gr.drawLine(factorX(200), f3, factorX(200), f5);
				gr.drawLine(factorX(300), f3, factorX(300), f5);
				gr.drawLine(factorX(400), f3, factorX(400), f5);
				gr.drawLine(factorX(500), f3, factorX(500), f5);
				gr.drawLine(factorX(700), f3, factorX(700), f5);
				gr.drawLine(factorX(830), f3, factorX(830), f5);
				gr.drawLine(factorX(980), f3, factorX(980), f5);
				gr.drawLine(factorX(1130), f3, factorX(1130), f5);
				gr.drawLine(factorX(1240), f3, factorX(1240), f5);
				gr.drawLine(factorX(1350), f3, factorX(1350), f5);
				gr.drawLine(factorX(1450), f3, factorX(1450), f5);
				int f39 = factorY(390);
				int f44 = factorY(440);
				int f49 = factorY(490);
				gr.setColor(Color.BLACK);
				gr.drawString("element", 0, f39);
				gr.drawString("power", 0, f44);
				gr.drawString("resistance", 0, f49);
				gr.setColor(Element.AIR.color);
				gr.drawString("air", factorX(210), f39);
				gr.drawString("" + entity.getPower(Element.AIR), factorX(200), f44);
				gr.drawString("" + entity.getResistance(Element.AIR), factorX(200), f49);
				gr.setColor(Element.EARTH.color);
				gr.drawString("earth", factorX(300), f39);
				gr.drawString(entity.getPower(Element.EARTH) + "", factorX(300), f44);
				gr.drawString(entity.getResistance(Element.EARTH) + "", factorX(300), f49);
				gr.setColor(Element.FIRE.color);
				gr.drawString("fire", factorX(415), f39);
				gr.drawString(entity.getPower(Element.FIRE) + "", factorX(400), f44);
				gr.drawString(entity.getResistance(Element.FIRE) + "", factorX(400), f49);
				gr.setColor(Element.LIGHTNING.color);
				gr.drawString("lightning", factorX(510), f39);
				gr.drawString(entity.getPower(Element.LIGHTNING) + "", factorX(510), f44);
				gr.drawString(entity.getResistance(Element.LIGHTNING) + "", factorX(510), f49);
				gr.setColor(Element.WATER.color);
				gr.drawString("water", factorX(710), f39);
				gr.drawString(entity.getPower(Element.WATER) + "", factorX(710), f44);
				gr.drawString(entity.getResistance(Element.WATER) + "", factorX(710), f49);
				gr.setColor(Element.POISON.color);
				gr.drawString("poison", factorX(840), f39);
				gr.drawString(entity.getPower(Element.POISON) + "", factorX(840), f44);
				gr.drawString(entity.getResistance(Element.POISON) + "", factorX(840), f49);
				gr.setColor(Element.NORMAL.color);
				gr.drawString("normal", factorX(990), f39);
				gr.drawString(entity.getPower(Element.NORMAL) + "", factorX(990), f44);
				gr.drawString(entity.getResistance(Element.NORMAL) + "", factorX(990), f49);
				gr.setColor(Element.LIGHT.color);
				gr.drawString("light", factorX(1140), f39);
				gr.drawString(entity.getPower(Element.LIGHT) + "", factorX(1140), f44);
				gr.drawString(entity.getResistance(Element.LIGHT) + "", factorX(1140), f49);
				gr.setColor(Element.DARK.color);
				gr.drawString("dark", factorX(1250), f39);
				gr.drawString(entity.getPower(Element.DARK) + "", factorX(1250), f44);
				gr.drawString(entity.getResistance(Element.DARK) + "", factorX(1250), f49);
				gr.setColor(Element.ROCK.color);
				gr.drawString("rock", factorX(1360), f39);
				gr.drawString(entity.getPower(Element.ROCK) + "", factorX(1360), f44);
				gr.drawString(entity.getResistance(Element.ROCK) + "", factorX(1360), f49);
			}
			
			@Override
			public void buttonClicked(Button button){
				if(button.name.matches("return to battle")){
					game.currentGUI = GuiBattle.this;
					entity.refreshModels();
				}
				if(button.name.matches("basic stats")){
					game.currentGUI = GuiBattleInfo.this;
				}
				if(button.name.matches("equipment")){
					game.currentGUI = new GuiBattleEquipment();
				}
			}
			
			@Override
			public void escapePressed(){
				game.currentGUI = GuiBattleInfo.this;
			}

			@Override
			public boolean canClose(){
				return false;
			}
		}
		
		public class GuiBattleEquipment extends Gui {

			public GuiBattleEquipment() {
				super(GuiBattleInfo.this.game, null);
				addButton(600, 300, 920, 400, "return to battle");
				addButton(600, 450, 920, 550, "basic stats");
				addButton(600, 600, 920, 700, "element stats");
			}
			
			@Override
			public void paint(Graphics gr){
				gr.setColor(Color.RED);
				gr.fillRect(0, 0, game.getWidth(), game.getHeight());
				super.paint(gr);
				drawEntity(gr, entity, new Point(factorX(1000), factorY(300)));
				gr.setColor(Color.GRAY);
				gr.setFont(font());
				if(entity.getHelmet() != null)
					gr.drawString("" + entity.getHelmet(), factorX(100), factorY(100));
				else
					gr.drawString("no helmet", factorX(100), factorY(100));
				if(entity.getChestPlate() != null)
					gr.drawString("" + entity.getChestPlate(), factorX(100), factorY(200));
				else
					gr.drawString("no chestplate", factorX(100), factorY(200));
				if(entity.getLeggings() != null)
					gr.drawString("" + entity.getLeggings(), factorX(100), factorY(300));
				else
					gr.drawString("no leggings", factorX(100), factorY(300));
				if(entity.getBoots() != null)
					gr.drawString("" + entity.getBoots(), factorX(100), factorY(400));
				else
					gr.drawString("no boots", factorX(100), factorY(400));
				if(entity.weapon != null)
					gr.drawString("" + entity.weapon.item, factorX(100), factorY(600));
				else 
					gr.drawString("no weapon", factorX(100), factorY(600));
			}
			
			@Override
			public void escapePressed(){
				game.currentGUI = GuiBattleInfo.this;
			}
			
			@Override
			public void buttonClicked(Button button){
				if(button.name.matches("return to battle")){
					game.currentGUI = GuiBattle.this;
					entity.refreshModels();
				}
				if(button.name.matches("basic stats")){
					game.currentGUI = GuiBattleInfo.this;
				}
				if(button.name.matches("element stats")){
					game.currentGUI = new GuiBattleElementStats();
				}
			}

			@Override
			public boolean canClose(){
				return false;
			}
		}
	}
	public class GuiBattleOptions extends GuiOptions {

		public GuiBattleOptions() {
			super(GuiBattle.this.game);
		}
		
		@Override
		public void escapePressed(){
			game.currentGUI = GuiBattle.this;
		}
		
		@Override
		public void buttonClicked(Button button){
			if(button.name.matches("back")){
				game.currentGUI = GuiBattle.this;
				return;
			}
			super.buttonClicked(button);
		}
		
		@Override
		public boolean canClose(){
			return false;
		}
	}
	
	public static class PotionButton extends Button {
		
		public final ItemStack item;

		public PotionButton(int minx, int miny, int maxx, int maxy, ItemStack stack) {
			super(minx, miny, maxx, maxy, "sprites/buttons/button.png", stack.item.name + " x " + stack.size, new Font("TimesRoman", Font.PLAIN, 20), Color.BLACK, stack.item.id);
			item = stack;
		}
		
		@Override
		public void paint(Graphics gr){
			super.paint(gr);
			item.paint(gr, new Point(maxX + 10, minY), new Point(maxX + 40, minY + 30));
		}
	}
}