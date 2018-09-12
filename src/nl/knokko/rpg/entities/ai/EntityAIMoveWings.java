package nl.knokko.rpg.entities.ai;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.model.Model;

public class EntityAIMoveWings extends EntityAIMoveArms {
	
	public int max;

	public EntityAIMoveWings(Entity target, int swingSpeed, int maxRotation, Model... arms) {
		super(target, swingSpeed, arms);
		max = maxRotation;
	}
	
	@Override
	protected void rotateStanding(){
		rotate(speed, max);
	}
	
	@Override
	protected void rotateMoving(float mx){
		rotate(mx * speed, max);
	}
}
