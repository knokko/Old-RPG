package nl.knokko.rpg.items;

import java.awt.*;

import nl.knokko.rpg.Element;
import nl.knokko.rpg.ElementStats;
import nl.knokko.rpg.entities.Entity;
import nl.knokko.rpg.gui.GuiBattle;
import nl.knokko.rpg.main.Game;
import nl.knokko.rpg.utils.PointUtils;
import nl.knokko.rpg.utils.Resources;

public abstract class ItemEquipment extends Item {
	
	public final Image textureForward;
	public final Image textureBehind;
	public final Image textureFront;
	public final Image textureBackward;
	public final Image textureBackwardRunning;
	public final Image textureForwardRunning;
	protected Image theTexture;
	
	public ElementStats elementStats = new ElementStats();
	
	ItemEquipment(String itemName, int value) {
		this(itemName, value, Element.NORMAL);
	}
	
	ItemEquipment(String itemName, int value, Element element) {
		super(itemName, value, element);
		textureForward = Resources.getImage("sprites/equipment/forward/" + itemName + ".png");
		textureBackward = Resources.getImage("sprites/equipment/backward/" + itemName + ".png");
		textureFront = Resources.getImage("sprites/equipment/front/" + itemName + ".png");
		textureBehind = Resources.getImage("sprites/equipment/behind/" + itemName + ".png");
		if(this instanceof ItemLeggings || this instanceof ItemBoots){
			textureBackwardRunning = Resources.getImage("sprites/equipment/backward/running/" + itemName + ".png");
			textureForwardRunning = Resources.getImage("sprites/equipment/forward/running/" + itemName + ".png");
		}
		else {
			textureBackwardRunning = null;
			textureForwardRunning = null;
		}
		theTexture = textureForward;
	}
	
	public void drawOnEntity(Graphics gr, Entity entity, int width, int height){
		Point screen = PointUtils.gameToScreenPosition(entity.position);
		if(entity.rotation == 3){
			theTexture = textureFront;
		}
		else if(entity.rotation == 1){
			theTexture = textureBehind;
		}
		else if(entity.rotation == 0){
			theTexture = textureForward;
			if(this instanceof ItemLeggings || this instanceof ItemBoots){
				if(Game.game.currentGUI instanceof GuiBattle){
					GuiBattle battle = (GuiBattle) Game.game.currentGUI;
					if(battle.currentAttacker == entity && entity.walkProgress >= 3){
						theTexture = textureForwardRunning;
					}
				}
			}
		}
		else if(entity.rotation == 2){
			theTexture = textureBackward;
			if(this instanceof ItemLeggings || this instanceof ItemBoots){
				if(Game.game.currentGUI instanceof GuiBattle){
					GuiBattle battle = (GuiBattle) Game.game.currentGUI;
					if(battle.currentAttacker == entity && entity.walkProgress >= 3){
						theTexture = textureBackwardRunning;
					}
				}
			}
		}
		gr.drawImage(theTexture, (screen.x), (screen.y), (width), (height), null);
	}
	
	public void drawOnEntity(Graphics gr, Entity entity, int width, int height, Point point){
		if(entity.rotation == 3){
			theTexture = textureFront;
		}
		else if(entity.rotation == 1){
			theTexture = textureBehind;
		}
		else if(entity.rotation == 0){
			theTexture = textureForward;
			if(this instanceof ItemLeggings || this instanceof ItemBoots){
				if(Game.game.currentGUI instanceof GuiBattle){
					GuiBattle battle = (GuiBattle) Game.game.currentGUI;
					if(battle.currentAttacker == entity && entity.walkProgress >= 3){
						theTexture = textureForwardRunning;
					}
				}
			}
		}
		else if(entity.rotation == 2){
			theTexture = textureBackward;
			if(this instanceof ItemLeggings || this instanceof ItemBoots){
				if(Game.game.currentGUI instanceof GuiBattle){
					GuiBattle battle = (GuiBattle) Game.game.currentGUI;
					if(battle.currentAttacker == entity && entity.walkProgress >= 3){
						theTexture = textureBackwardRunning;
					}
				}
			}
		}
		gr.drawImage(theTexture, point.x, point.y, width, height, null);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends ItemEquipment> T setElementPower(Element element, int power){
		elementStats.setPower(element, power);
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends ItemEquipment> T setElementResistance(Element element, int resistance){
		elementStats.setResistance(element, resistance);
		return (T) this;
	}
	
	public int getElementPower(Element element){
		return elementStats.getPower(element);
	}
	
	public int getElementResistance(Element element){
		return elementStats.getResistance(element);
	}
}
