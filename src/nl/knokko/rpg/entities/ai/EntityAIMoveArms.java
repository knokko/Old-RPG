package nl.knokko.rpg.entities.ai;

import java.awt.Point;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.gui.GuiBattle;
import nl.knokko.rpg.main.MainClass;

public class EntityAIMoveArms extends EntityAI {

	public final Model[] arms;
	public final int speed;
	
	protected boolean arm[];
	protected Point previous;

	public EntityAIMoveArms(Entity target, Model... arms) {
		this(target, 1, arms);
	}
	
	public EntityAIMoveArms(Entity target, int swingSpeed, Model... arms){
		super(target);
		this.arms = arms;
		arm = new boolean[arms.length / 2];
		previous = entity.battlePoint.getLocation();
		speed = swingSpeed;
	}
	
	@Override
	public void update(){
		float mx = 0;
		if(MainClass.currentGUI == null){
			mx = (int) (Math.signum(entity.moveX) * entity.moveSpeed);
			previous = new Point();
		}
		if(MainClass.currentGUI instanceof GuiBattle){
			if(previous.x == 0)
				previous.x = entity.battlePoint.x;
			mx = (entity.battlePoint.x - previous.x);
			previous = entity.battlePoint.getLocation();
			if(mx == 0)
				rotateStanding();
		}
		if(mx == 0)
			return;
		if(mx < 0)
			mx = -mx;
		mx /= 2;
		rotateMoving(mx);
	}
	
	protected void rotate(float mx, int max){
		mx *= 1.3;
		int t = 0;
		while(t < arms.length - 1){
			Model arm1 = arms[t];
			Model arm2 = arms[t + 1];
			if(arm[t / 2]){
				arm1.rotation += mx;
				arm2.rotation -= mx;
			}
			else {
				arm1.rotation -= mx;
				arm2.rotation += mx;
			}
			if(arm1.rotation > max || arm2.rotation > max){
				float extra;
				if(arm1.rotation > max)
					extra = arm1.rotation - max;
				else
					extra = arm2.rotation - max;
				if(arm[t / 2]){
					arm1.rotation = max - extra;
					arm2.rotation = -max + extra;
				}
				else {
					arm1.rotation = -max + extra;
					arm2.rotation = max - extra;
				}
				arm[t / 2] = !arm[t / 2];
			}
			t += 2;
		}
	}
	
	protected void rotateStanding(){
		rotate(speed, 10);
	}
	
	protected void rotateMoving(float mx){
		rotate(mx * speed, 25);
	}
}
