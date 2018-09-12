package nl.knokko.rpg.entities.monsters;

import java.awt.Point;
import java.util.ArrayList;

import nl.knokko.rpg.Element;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.ai.EntityAIMoveTail;
import nl.knokko.rpg.entities.model.ModelTail;
import nl.knokko.rpg.inventory.ItemStack;
import nl.knokko.rpg.items.Items;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.utils.Random;

public class Snake extends Entity {

	public Snake(Game game, Point position, int level) {
		super(game, position, level);
		strength = level;
		currentHealth = maxHealth = level * 20;
		applyDifficulty();
		elementStats.setResistance(Element.POISON, 75);
		elementStats.setResistance(Element.LIGHTNING, 50);
		elementStats.setResistance(Element.WATER, -50);
		elementStats.setResistance(Element.FIRE, -100);
		elementStats.setResistance(Element.EARTH, -50);
	}
	
	@Override
	public Element getElement(){
		return Element.POISON;
	}
	
	@Override
	public int getPower(){
		return 15;
	}
	
	@Override
	public int getLootXp(){
		return level * 2;
	}
	
	@Override
	public int getLootMoney(){
		return random.nextInt(level * 2);
	}
	
	@Override
	public ArrayList<ItemStack> getLoot(){
		ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
		if(Random.chance(5))
			loot.add(new ItemStack(Items.snakeTooth));
		return loot;
	}
	
	@Override
	protected void addBody(){
		String n = getTexture().toLowerCase();
		int y = 27;
		int x = 15;
		models.add(new ModelTail(n + "/tail9.png", new Point(-9 + x, y), new Point(1, 1), this));
		models.add(new ModelTail(n + "/tail8.png", new Point(-7 + x, y), new Point(1, 1), this));
		models.add(new ModelTail(n + "/tail7.png", new Point(-5 + x, y), new Point(1, 1), this));
		models.add(new ModelTail(n + "/tail6.png", new Point(-3 + x, y), new Point(1, 1), this));
		models.add(new ModelTail(n + "/tail5.png", new Point(-1 + x, y), new Point(1, 1), this));
		models.add(new ModelTail(n + "/tail4.png", new Point(1 + x, y), new Point(1, 1), this));
		models.add(new ModelTail(n + "/tail3.png", new Point(3 + x, y), new Point(1, 1), this));
		models.add(new ModelTail(n + "/tail2.png", new Point(5 + x, y), new Point(1, 1), this));
		models.add(new ModelTail(n + "/tail1.png", new Point(7 + x, y), new Point(1, 1), this));
		models.add(new ModelTail(n + "/head.png", new Point(11 + x, y - 1), new Point(1, 1), this));
		int t = 0;
		while(t < models.size()){
			ModelTail left = null;
			ModelTail right = null;
			if(t > 0)
				left = (ModelTail) models.get(t - 1);
			if(t < models.size() - 1)
				right = (ModelTail) models.get(t + 1);
			((ModelTail) models.get(t)).connect(left, right);
			++t;
		}
	}
	
	@Override
	protected void addAI(){
		ai.add(new EntityAIMoveTail(this, 3, 3, models.toArray(new ModelTail[10])));
	}
}
