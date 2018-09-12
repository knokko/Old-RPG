package nl.knokko.rpg.items;

import java.awt.Color;
import java.util.ArrayList;

import nl.knokko.rpg.Element;
import nl.knokko.rpg.effects.EffectPoison;

public final class Items {
	
	static final ArrayList<Item> items = new ArrayList<Item>();

	public static final Item wood = new Item("Wood", 5);
	public static final ItemWeapon woodSword = new ItemWeapon("Wooden Sword", 5, 15, 50);
	public static final ItemWeapon woodAxe = new ItemWeapon("Wooden Axe", 3, 13, 40);
	public static final ItemPotion potion = new ItemPotion("Potion", 10, 100);
	public static final Item foidskin = new Item("Foid Skin", 10);
	public static final ItemChestplate foidChestplate = new ItemChestplate("Foid Chestplate", 100, 2, 1);
	public static final ItemHelmet foidHelmet = new ItemHelmet("Foid Helmet", 50, 1, 0);
	public static final ItemLeggings foidLeggings = new ItemLeggings("Foid Leggings", 100, 2, 1);
	public static final ItemBoots foidBoots = new ItemBoots("Foid Boots", 50, 1, 0);
	public static final ItemEther ether = new ItemEther("Ether", 30, 100);
	public static final ItemWeapon torch = new ItemWeapon("Torch", 20, 2, 50, Element.FIRE);
	public static final Item mantidBlade = new ItemWeapon("Mantid Blade", 3, 12, 35, Element.POISON).addText("This blade poisons it's", "targets.").addEffect(new EffectPoison(null, 2, Element.POISON));
	public static final Item wing = new Item("Wing", 10);
	public static final Item feather = new Item("Feather", 5);
	public static final ItemPotion orangePotion = new ItemPotion("Orange Potion", 20, 200);
	public static final ItemEther orangeEther = new ItemEther("Orange Ether", 60, 200);
	public static final Item frodeskin = new Item("Frode Skin", 15);
	public static final Item snakeTooth = new Item("Snake Tooth", 25);
	public static final ItemWeapon pickaxe = new ItemWeapon("Pickaxe", 3, 20, 70);
	public static final Item miayGem = new Item("Miay Gem", 50, Element.EARTH);
	public static final ItemWeapon stoneSword = new ItemWeapon("Stone Sword", 7, 25, 100);
	public static final ItemWeapon miaySword = new ItemWeapon("Miay Sword", 15, 30, 250);
	public static final ItemWeapon miayRod = new ItemWeapon("Miay Rod", 30, 5, 300, Element.EARTH);
	public static final ItemHelmet miayHelmet = new ItemHelmet("Miay Helmet", 300, 3, 4);
	public static final ItemChestplate miayChestplate = new ItemChestplate("Miay Chestplate", 500, 6, 7);
	public static final ItemLeggings miayLeggings = new ItemLeggings("Miay Leggings", 450, 5, 7);
	public static final ItemBoots miayBoots = new ItemBoots("Miay Boots", 300, 3, 4);
	public static final Item tarf = new Item("Tarf", 100);
	public static final ItemWeapon tarfSword = new ItemWeapon("Tarf Sword", 10, 45, 500);
	public static final ItemWeapon fireTooth = new ItemWeapon("Fire Tooth", 30, 50, 1000, Element.FIRE).setTextColor(Color.ORANGE).addEffect(new EffectPoison(null, 5, Element.FIRE)).addText("", "This sword empowers", "fire and burns targets.").setElementPower(Element.FIRE, 50);
	public static final Item darkEssence = new Item("Dark Essence", 100);
	public static final ItemWeapon darkBlade = new ItemWeapon("Dark Blade", 10, 40, 500, Element.DARK);
	public static final ItemWeapon darkWand = new ItemWeapon("Dark Wand", 45, 25, 600, Element.DARK).addText("This wand empowers", "dark.").setElementPower(Element.DARK, 50);
	public static final ItemHelmet tarfHelmet = new ItemHelmet("Tarf Helmet", 600, 7, 4);
	public static final ItemChestplate tarfChestplate = new ItemChestplate("Tarf Chestplate", 1000, 10, 7);
	public static final ItemLeggings tarfLeggings = new ItemLeggings("Tarf Leggings", 900, 8, 7);
	public static final ItemBoots tarfBoots = new ItemBoots("Tarf Boots", 600, 7, 4);
	public static final ItemWeapon woodHammer = new ItemWeapon("Wooden Hammer", 12, 12, 50);
	public static final ItemWeapon miayHammer = new ItemWeapon("Miay Hammer", 20, 20, 250);
	public static final ItemWeapon darkHammer = new ItemWeapon("Dark Hammer", 30, 30, 600, Element.DARK);
	public static final ItemPotion betterPotion = new ItemPotion("Better Potion", 40, 400);
	public static final ItemEther betterEther = new ItemEther("Better Ether", 120, 400);
	public static final ItemBoots darkBoots = new ItemBoots("Dark Boots", 600, 1, 8).addText("", "25% dark resistance").setElementResistance(Element.DARK, 25);
	public static final ItemLeggings darkLeggings = new ItemLeggings("Dark Leggings", 900, 2, 14).addText("", "40% dark resistance").setElementResistance(Element.DARK, 40);
	public static final ItemChestplate darkChestplate = new ItemChestplate("Dark Chestplate", 1000, 2, 16).addText("", "50% dark resistance").setElementResistance(Element.DARK, 50);
	public static final ItemHelmet darkHelmet = new ItemHelmet("Dark Helmet", 600, 1, 8).addText("", "25% dark resistance").setElementResistance(Element.DARK, 25);
	public static final Item darkSkin = new Item("Dark Skin", 100);
	public static final Item lightEssence = new Item("Light Essence", 100);
	public static final Item cystEssence = new Item("Cyst Essence", 150);
	public static final ItemWeapon cystBlade = new ItemWeapon("Cyst Blade", 30, 80, 1500, Element.DARK).setTextColor(new Color(180, 0, 255)).addText("", "This sword empowers", "dark.").setElementPower(Element.DARK, 50);
	public static final ItemWeapon cystScepter = new ItemWeapon("Cyst Scepter", 80, 40, 1500, Element.DARK).setTextColor(new Color(180, 0, 255));
	public static final ItemHelmet frodeHelmet = new ItemHelmet("Frode Helmet", 600, 3, 7).setElementResistance(Element.FIRE, 20);
	public static final ItemChestplate frodeChestplate = new ItemChestplate("Frode Chestplate", 1000, 6, 10).setElementResistance(Element.FIRE, 30);
	public static final ItemLeggings frodeLeggings = new ItemLeggings("Frode Leggings", 900, 6, 8).setElementResistance(Element.FIRE, 30);
	public static final ItemBoots frodeBoots = new ItemBoots("Frode Boots", 600, 3, 7).setElementResistance(Element.FIRE, 20);
	public static final Item bronze = new Item("Bronze", 120);
	public static final ItemWeapon bronzeSword = new ItemWeapon("Bronze Sword", 15, 55, 600);
	public static final ItemWeapon bronzeHammer = new ItemWeapon("Bronze Hammer", 40, 40, 700);
	public static final ItemHelmet bronzeHelmet = new ItemHelmet("Bronze Helmet", 700, 10, 5);
	public static final ItemChestplate bronzeChestplate = new ItemChestplate("Bronze Chestplate", 1200, 15, 8);
	public static final ItemLeggings bronzeLeggings = new ItemLeggings("Bronze Leggings", 1080, 13, 7);
	public static final ItemBoots bronzeBoots = new ItemBoots("Bronze Boots", 700, 10, 5);
	public static final Item gold = new Item("Gold", 300);
	public static final ItemWeapon kingsSword = new ItemWeapon("Kings Sword", 20, 65, 1500);
	
	public static final ItemArmor[] foidArmor = new ItemArmor[]{foidHelmet, foidChestplate, foidLeggings, foidBoots};
	public static final ItemArmor[] miayArmor = new ItemArmor[]{miayHelmet, miayChestplate, miayLeggings, miayBoots};
	public static final ItemArmor[] tarfArmor = new ItemArmor[]{tarfHelmet, tarfChestplate, tarfLeggings, tarfBoots};
	public static final ItemArmor[] darkArmor = new ItemArmor[]{darkHelmet, darkChestplate, darkLeggings, darkBoots};
	public static final ItemArmor[] frodeArmor = new ItemArmor[]{frodeHelmet, frodeChestplate, frodeLeggings, frodeBoots};
	public static final ItemArmor[] bronzeArmor = new ItemArmor[]{bronzeHelmet, bronzeChestplate, bronzeLeggings, bronzeBoots};
	
	public static final Item fromId(int id){
		return id >= 0 && id < items.size() ? items.get(id) : null;
	}
}
