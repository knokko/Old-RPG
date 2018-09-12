package nl.knokko.rpg.entities.ai;

import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.entities.model.ModelTail;
import nl.knokko.rpg.utils.Random;

public class EntityAIMoveTail extends EntityAI {
	
	public ModelTail[] tail;
	
	public int height;
	public int ground;

	public EntityAIMoveTail(Entity target, int maxHeight, int requiredGroundModels, ModelTail[] tailModels) {
		super(target);
		height = maxHeight;
		tail = tailModels;
		ground = requiredGroundModels;
	}
	
	@Override
	public void update(){
		if(Random.chance(2) && enoughModelsOnGround()){
			ModelTail model = tail[Random.randomInt(tail.length - 1)];
			if(model.extraY < height)
				model.pull(1);
			else
				model.pull(-1);
		}
		else {
			ModelTail model = tail[Random.randomInt(tail.length - 1)];
			if(model.extraY > 0)
				model.pull(-1);
			else
				model.pull(1);
		}
	}
	
	public boolean enoughModelsOnGround(){
		int groundModels = 0;
		int t = 0;
		while(t < tail.length){
			ModelTail model = tail[t];
			if(model.extraY == 0)
				++groundModels;
			++t;
		}
		return groundModels >= ground;
	}
}
