package nl.knokko.rpg.entities.ai;

import java.awt.Point;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.gui.GuiBattle;
import nl.knokko.rpg.main.MainClass;

public class EntityAIMoveHead extends EntityAI {
	
	public final Model[] heads;
	public final int speed;
	
	protected boolean[] sides;
	protected Point previous;
	
	public EntityAIMoveHead(Entity target, Model... headsToMove){
		this(target, 1, headsToMove);
	}

	public EntityAIMoveHead(Entity target, int shakeSpeed, Model... headsToMove) {
		super(target);
		heads = headsToMove;
		speed = shakeSpeed;
		sides = new boolean[heads.length];
		previous = entity.battlePoint.getLocation();
	}
	
	@Override
	public void update(){
		float mx = 0;
		if(MainClass.currentGUI == null)
			mx = entity.moveX;
		if(MainClass.currentGUI instanceof GuiBattle){
			if(previous.x == 0)
				previous.x = entity.battlePoint.x;
			mx = (entity.battlePoint.x - previous.x);
			previous = entity.battlePoint.getLocation();
			if(mx == 0)
				rotate(speed, 10);
		}
		if(mx == 0)
			return;
		if(mx < 0)
			mx = -mx;
		mx /= 2;
		rotate(mx * speed, 25);
	}
	
	private void rotate(float mx, int max){
		mx *= 1.3;
		int t = 0;
		while(t < heads.length){
			Model head = heads[t];
			if(sides[t])
				head.rotation += mx;
			else 
				head.rotation -= mx;
			if(head.rotation > max || head.rotation < -max){
				float extra;
				if(head.rotation > max)
					extra = head.rotation - max;
				else
					extra = -head.rotation - max;
				if(sides[t])
					head.rotation = max - extra;
				else 
					head.rotation = -max + extra;
				sides[t] = !sides[t];
			}
			++t;
		}
	}
}
