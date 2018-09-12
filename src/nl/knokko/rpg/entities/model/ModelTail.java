package nl.knokko.rpg.entities.model;

import java.awt.Point;

import nl.knokko.rpg.entities.Entity;

public class ModelTail extends Model {
		
	public ModelTail left;
	public ModelTail right;
		
	public int extraY;
	
	private boolean pulling;
	
	public ModelTail(String sprite, Point base, Point rotate, Entity owner) {
		super(sprite, base, rotate, owner);
	}
	
	@Override
	public int extraY(){
		return entity.extraY - extraY;
	}
		
	public void connect(ModelTail l, ModelTail r){
		left = l;
		right = r;
	}
		
	public void pull(int y){
		if(pulling)
			return;
		pulling = true;
		extraY += y;
		check(left);
		check(right);
		pulling = false;
	}
		
	private void check(ModelTail model){
		if(model != null){
			if(model.extraY > extraY + 1)
				model.pull(-1);
			if(model.extraY < extraY - 1)
				model.pull(1);
		}
	}
}
