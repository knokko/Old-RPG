package nl.knokko.rpg.entities.ai;

import java.util.Random;

import nl.knokko.rpg.entities.Entity;

public class EntityAIMoveFloating extends EntityAI {
	
	public int flyMinX;
	public int flyMinY;
	public int flyMaxX;
	public int flyMaxY;

	public EntityAIMoveFloating(Entity target, int minX, int minY, int maxX, int maxY) {
		super(target);
		flyMinX = minX;
		flyMinY = minY;
		flyMaxX = maxX;
		flyMaxY = maxY;
	}
	
	@Override
	public void update(){
		Random random = new Random();
		if(flyMaxX > flyMinX){
			int distanceX = flyMaxX - flyMinX;
			int randomX = random.nextInt(distanceX) - distanceX / 2 - entity.extraX;
			if(randomX > 0)
				entity.extraX++;
			if(randomX < 0)
				entity.extraX--;
		}
		if(flyMaxY > flyMinY){
			int distanceY = flyMaxY - flyMinY;
			int randomY = random.nextInt(distanceY) - distanceY / 2 - entity.extraY;
			if(randomY > 0)
				entity.extraY++;
			if(randomY < 0)
				entity.extraY--;
		}
	}

}
