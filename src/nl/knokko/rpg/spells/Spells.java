package nl.knokko.rpg.spells;

import java.awt.Point;

import nl.knokko.rpg.Element;
import nl.knokko.rpg.effects.EffectEnchantWeapon;
import nl.knokko.rpg.effects.EffectPoison;
import nl.knokko.rpg.effects.EffectShield;
import nl.knokko.rpg.effects.EffectStrength;
import nl.knokko.rpg.spells.AttackSpell.BloodDrain;
import nl.knokko.rpg.spells.LastSpell.*;

public final class Spells {
	
	public static final String friend = "You should cast this spell on a friend.";
	public static final String enemy = "You should cast this spell on an enemy.";
	
	public static final String enemies = "You should cast this spell on enemies.";
	
	private static final String[] discriptionHeal = new String[]{"Summon an orb of positive energy that ", "heals it's target.", "", friend};
	private static final String[] discriptionShield = new String[]{"Give a shield to someone that will ", "absorb damage until it breaks.", "", friend};
	private static final String[] discriptionFireBall = new String[]{"Shoot a burning ball of melting stone to", "someone that will deal fire damage.", "", enemy};
	private static final String[] discriptionIceBall = new String[]{"Shoot a cold ball of freezing water to", "someone that will deal water damage.", "", enemy};
	private static final String[] discriptionNeedleShot = new String[]{"Shoot a large needle to someone that", "will deal normal damage.", "", enemy};
	private static final String[] discriptionWindShot = new String[]{"Send a small gust to someone that will", "deal air damage.", "", enemy};
	private static final String[] discriptionDarkVortex = new String[]{"Summon a vortex of complete darkness", "at someone that will deal dark damage.", "", enemy};
	private static final String[] discriptionLightVortex = new String[]{"Summon a vortex of pure light at", "someone that will deal light damage.", "", enemy};
	private static final String[] discriptionRock = new String[]{"Summon a rock above someone's head", "that will fall down and deal rock", "damage.", "", enemy};
	private static final String[] discriptionShock = new String[]{"Summon a small shock at someone that", "will deal lightning damage.", "", enemy};
	private static final String[] discriptionPoisonCloud = new String[]{"Summon a big cloud of poison at", "someone that will deal poison damage.", "", enemy};
	private static final String[] discriptionGrassKnife = new String[]{"Shoot a sharp blade of grass at", "someone that will deal earth damage.", "", enemy};
	
	private static final String[] discriptionFlash = new String[]{"Shoot a large beam of light to", "someone that will deal light damage.", "", enemy};
	private static final String[] discriptionFireBlast = new String[]{"Shoot a large beam of fire to", "someone that will deal fire damage.", "", enemy};
	
	private static final String[] discriptionHeatWave = new String[]{"Create a huge heatwave at the", "battlefield that will deal fire damage to", "a whole team.", "", enemies};
	
	private static final String[] discriptionPoison = new String[]{"Infect someone with deadly poison that", "will deal poison damage every turn.", "", enemy};
	private static final String[] discriptionWither = new String[]{"Let someone's body wither, it will deal", "dark damage every turn.", "", enemy};
	private static final String[] discriptionBurn = new String[]{"Summon several fireballs at someone", "that will deal fire damage and give", "him wounds that will deal fire damage", "every turn.", "", enemy};
	
	private static final String[] discriptionElementSlash(String element, String name){
		return new String[]{"Empower your weapon with " + name, "and attack someone with your weapon,", "this attack will deal " + element + " damage.", "", enemy};
	}
	
	private static final String[] discriptionElementSlash(String element){
		return discriptionElementSlash(element, element);
	}
	
	private static final String[] discriptionPowerAttack = new String[]{"Give yourself a short burst of strength", "and attack someone with your weapon,", "this attack will deal double damage.", "", enemy};
	private static final String[] discriptionBloodDrain = new String[]{"Use the power of a vampire to attack", "someone with your weapon,", "this attack will deal dark damage and", "steal the half of the damage dealt.", "", enemy};
	
	private static final String[] discriptionStrengthBoost = new String[]{"Increase someone's strength for", "the rest of the battle. This will make", "his physical attacks deal more damage.", "", friend};
	private static final String[] discriptionIceBlade = new String[]{"Enchant someone's weapon with ice,", "his attacks will deal extra water", "damage for the rest of the battle.", "", friend};
	private static final String[] discriptionEmpower = new String[]{"Enchant someone's weapon, his attacks", "will deal extra damage for the rest of", "the battle.", "", friend};

	private Spells() {}
	
	public static final Heal heal = new Heal(null, null, 0, 1, 20, "heal", Element.LIGHT).addText(discriptionHeal);
	private static final Empower shield = new Empower(null, null, 0, 20, "shield", Element.LIGHT, new EffectShield(null, 1)).addText(discriptionShield);
	
	private static final ShootSpell fireBall = new ShootSpell(new Point(), null, null, 0, Element.FIRE, 1, 10, "fireball", false).addText(discriptionFireBall);
	private static final ShootSpell iceBall = new ShootSpell(new Point(), null, null, 0, Element.WATER, 1, 10, "iceball", false).addText(discriptionIceBall);
	private static final ShootSpell needleShot = new ShootSpell(new Point(), null, null, 0, Element.NORMAL, 1, 10, "needleshot", false).addText(discriptionNeedleShot);
	private static final ShootSpell windShot = new ShootSpell(new Point(), null, null, 0, Element.AIR, 1, 10, "windshot", true).addText(discriptionWindShot);
	private static final LastSpell darkVortex = new LastSpell(null, null, 0, 1, 10, "darkvortex", Element.DARK).addText(discriptionDarkVortex);
	private static final LastSpell lightVortex = new LastSpell(null, null, 0, 1, 10, "lightvortex", Element.LIGHT).addText(discriptionLightVortex);
	private static final DropSpell rock = new DropSpell(new Point(), null, null, 0, 1, 10, "rock", Element.ROCK).addText(discriptionRock);
	private static final LastSpell shock = new LastSpell(null, null, 0, 1, 10, "shock", Element.LIGHTNING).addText(discriptionShock);
	private static final LastSpell poisoncloud = new LastSpell(null, null, 0, 1, 10, "poisoncloud", Element.POISON).addText(discriptionPoisonCloud);
	private static final ShootSpell grassKnife = new ShootSpell(new Point(), null, null, 0, Element.EARTH, 1, 10, "grassknife", true).addText(discriptionGrassKnife);
	
	private static final LaserSpell flash = new LaserSpell(null, null, 0, 2, 20, "flash", Element.LIGHT).addText(discriptionFlash);
	private static final LaserSpell fireBlast = new LaserSpell(null, null, 0, 2, 20, "fireblast", Element.FIRE).addText(discriptionFireBlast);
	
	private static final MassSpell heatWave = new MassSpell(null, false, 0, 40, "heatwave", Element.FIRE, 1, 5).addText(discriptionHeatWave);
	
	private static final Infect poison = new Infect(null, null, 0, 0, 10, "poison", Element.POISON, new EffectPoison(null, 1, Element.POISON)).addText(discriptionPoison);
	private static final Infect wither = new Infect(null, null, 0, 0, 20, "wither", Element.DARK, new EffectPoison(null, 2, Element.DARK)).addText(discriptionWither);
	
	private static final Infect burn = new Infect(null, null, 0, 2, 30, "burn", Element.FIRE, new EffectPoison(null, 2, Element.FIRE)).addText(discriptionBurn);
	
	private static final AttackSpell fireSlash = new AttackSpell(new Point(), null, null, 1, Element.FIRE, 10, "fireslash", false).addText(discriptionElementSlash("fire"));
	private static final AttackSpell waterSlash = new AttackSpell(new Point(), null, null, 1, Element.WATER, 10, "waterslash", false).addText(discriptionElementSlash("water"));
	private static final AttackSpell earthSlash = new AttackSpell(new Point(), null, null, 1, Element.EARTH, 10, "earthslash", false).addText(discriptionElementSlash("earth"));
	private static final AttackSpell electricSlash = new AttackSpell(new Point(), null, null, 1, Element.LIGHTNING, 10, "electricslash", false).addText(discriptionElementSlash("lightning"));
	private static final AttackSpell lightSlash = new AttackSpell(new Point(), null, null, 1, Element.LIGHT, 10, "lightslash", false).addText(discriptionElementSlash("light"));
	private static final AttackSpell darkSlash = new AttackSpell(new Point(), null, null, 1, Element.DARK, 10, "darkslash", false).addText(discriptionElementSlash("dark", "darkness"));
	private static final AttackSpell rockSlash = new AttackSpell(new Point(), null, null, 1, Element.ROCK, 10, "rockslash", false).addText(discriptionElementSlash("rock", "stone"));
	private static final AttackSpell airSlash = new AttackSpell(new Point(), null, null, 1, Element.AIR, 10, "airslash", false).addText(discriptionElementSlash("air", "wind"));
	private static final AttackSpell poisonSlash = new AttackSpell(new Point(), null, null, 1, Element.POISON, 10, "poisonslash", false).addText(discriptionElementSlash("poison"));
	
	private static final AttackSpell powerAttack = new AttackSpell(new Point(), null, null, 2, Element.NORMAL, 20, "powerattack", false).addText(discriptionPowerAttack);
	private static final BloodDrain bloodDrain = new BloodDrain(new Point(), null, null, 1, Element.DARK, 20, "blooddrain", false, 50).addText(discriptionBloodDrain);
	
	private static final Empower strengthBoost = new Empower(null, null, 0, 10, "strengthboost", Element.FIRE, new EffectStrength(null, 0, 1.2)).addText(discriptionStrengthBoost);
	private static final Empower iceBlade = new Empower(null, null, 0, 10, "iceblade", Element.WATER, 3, new EffectEnchantWeapon(null, 1, Element.WATER)).addText(discriptionIceBlade);
	private static final Empower empower = new Empower(null, null, 0, 10, "empower", Element.FIRE, new EffectStrength(null, 1, 1)).addText(discriptionEmpower);
	
	public static final Spell fromString(String spell){
		try {
			return (Spell) Spells.class.getMethod(spell).invoke(null);
		} catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public static final Heal heal(){return heal.clone();}
	public static final Empower shield(){return shield.clone();}
	
	public static final ShootSpell fireball(){return fireBall.clone();}
	public static final ShootSpell iceball(){return iceBall.clone();}
	public static final ShootSpell needleshot(){return needleShot.clone();}
	public static final ShootSpell windshot(){return windShot.clone();}
	public static final LastSpell darkvortex(){return darkVortex.clone();}
	public static final LastSpell lightvortex(){return lightVortex.clone();}
	public static final DropSpell rock(){return rock.clone();}
	public static final LastSpell shock(){return shock.clone();}
	public static final LastSpell poisoncloud(){return poisoncloud.clone();}
	public static final ShootSpell grassknife(){return grassKnife.clone();}
	
	public static final LaserSpell flash(){return flash.clone();}
	public static final LaserSpell fireblast(){return fireBlast.clone();}
	
	public static final MassSpell heatwave(){return heatWave.clone();}
	
	public static final Infect poison(){return poison.clone();}
	public static final Infect wither(){return wither.clone();}
	
	public static final Infect burn(){return burn.clone();}
	
	public static final AttackSpell fireslash(){return fireSlash.clone();}
	public static final AttackSpell waterslash(){return waterSlash.clone();}
	public static final AttackSpell earthslash(){return earthSlash.clone();}
	public static final AttackSpell electricslash(){return electricSlash.clone();}
	public static final AttackSpell lightslash(){return lightSlash.clone();}
	public static final AttackSpell darkslash(){return darkSlash.clone();}
	public static final AttackSpell airslash(){return airSlash.clone();}
	public static final AttackSpell rockslash(){return rockSlash.clone();}
	public static final AttackSpell poisonslash(){return poisonSlash.clone();}
	
	public static final AttackSpell powerattack(){return powerAttack.clone();}
	public static final BloodDrain blooddrain(){return bloodDrain.clone();}
	
	public static final Empower strengthboost(){return strengthBoost.clone();}
	public static final Empower empower(){return empower.clone();}
	public static final Empower iceblade(){return iceBlade.clone();}
}
