package nl.knokko.rpg.entities.ai;

import java.awt.Point;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.utils.Random;

public class EntityAIMoveLegs extends EntityAI {
	
	public final Model[] legs;
	public final int base;
	
	protected boolean[] leg;
	protected Point previous;

	public EntityAIMoveLegs(Entity target, int baseRotation, Model... legs) {
		super(target);
		this.legs = legs;
		previous = entity.battlePoint.getLocation();
		leg = new boolean[legs.length / 2];
		base = baseRotation;
		if(legs == null || (legs.length > 0 && legs[0] == null))
			Game.console.println("corrupt class is " + entity.getClass());
	}
	
	public EntityAIMoveLegs(Entity target, Model... legs){
		this(target, 5, legs);
	}
	
	@Override
	public void update(){
		int mx = 0;
		if(Game.game.currentGUI == null)
			mx = entity.moveX;
		else {
			mx = (entity.battlePoint.x - previous.x);
			previous = entity.battlePoint.getLocation();
			if(mx == 0){
				int t = 0;
				while(t < legs.length){
					legs[t].rotation = Random.canDivide(t, 2) ? base : -base;
					++t;
				}
			}
		}
		if(mx < 0)
			mx = -mx;
		rotate(mx, 45);
	}
	
	private void rotate(float mx, int max){
		mx /= Game.game.fpsFactor;
		mx *= 1.3;
		int t = 0;
		while(t < legs.length - 1){
			Model leg1 = legs[t];
			Model leg2 = legs[t + 1];
			if(leg[t / 2]){
				leg1.rotation += mx;
				leg2.rotation -= mx;
			}
			else {
				leg1.rotation -= mx;
				leg2.rotation += mx;
			}
			if(leg1.rotation > max || leg2.rotation > max){
				if(leg[t / 2]){
					leg1.rotation = max;
					leg2.rotation = -max;
				}
				else {
					leg1.rotation = -max;
					leg2.rotation = max;
				}
				leg[t / 2] = !leg[t / 2];
			}
			t += 2;
		}
	}
}
