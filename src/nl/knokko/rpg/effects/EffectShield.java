package nl.knokko.rpg.effects;

import nl.knokko.rpg.Attack;
import nl.knokko.rpg.entities.Entity;

public class EffectShield extends StatusEffect {

	public EffectShield(Entity target, int power) {
		super(target, power);
	}
	
	public EffectShield(Entity target){
		this(target, 0);
	}

	@Override
	public boolean isPositive() {
		return true;
	}

	@Override
	public StatusEffect clone() {
		return new EffectShield(entity, power);
	}
	
	@Override
	public int defence(Attack attack, Entity attacker, int damage){
		power -= damage;
		if(power > 0)
			return 0;
		entity.effects.remove(this);
		return -power;
	}

}
