package nl.knokko.rpg.entities.ai;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.model.Model;
import nl.knokko.rpg.utils.Random;

public class EntityAIMoveRandom extends EntityAI {
	
	public final Model[] models;
	
	public final int speed;
	public final int min;
	public final int max;

	public EntityAIMoveRandom(Entity target, int maxSpeed, int minRotation, int maxRotation, Model... modelList) {
		super(target);
		speed = maxSpeed;
		min = minRotation;
		max = maxRotation;
		models = modelList;
	}
	
	@Override
	public void update(){
		int t = 0;
		while(t < models.length){
			int random = Random.randomInt(-speed, speed);
			models[t].rotation += random;
			if(models[t].rotation > 360)
				models[t].rotation -= 360;
			if(models[t].rotation < 0)
				models[t].rotation += 360;
			if(models[t].rotation < min)
				models[t].rotation = min;
			if(models[t].rotation > max)
				models[t].rotation = max;
			++t;
		}
	}
}
