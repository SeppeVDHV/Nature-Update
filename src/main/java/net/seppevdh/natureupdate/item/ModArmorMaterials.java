package net.seppevdh.natureupdate.item;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvents;
import net.seppevdh.natureupdate.util.ModTags;

import java.util.EnumMap;

public class ModArmorMaterials {
    public static ResourceKey<EquipmentAsset> BRONZE = EquipmentAssets.createId("bronze");
    public static ResourceKey<EquipmentAsset> EMERALD = EquipmentAssets.createId("emerald");
    public static ResourceKey<EquipmentAsset> COPPER = EquipmentAssets.createId("copper");

    public static final ArmorMaterial BRONZE_ARMOR_MATERIAL = new ArmorMaterial (ModToolTiers.BRONZE.durability(),
            Util.make(new EnumMap<>(ArmorType.class), attribute -> {
                attribute.put(ArmorType.BOOTS, 3);
                attribute.put(ArmorType.LEGGINGS, 5);
                attribute.put(ArmorType.CHESTPLATE, 8);
                attribute.put(ArmorType.HELMET, 3);
                attribute.put(ArmorType.BODY, 6);
            }), 12, SoundEvents.ARMOR_EQUIP_IRON, 1f, 0f, ModTags.Items.BRONZE_REPAIR_INGREDIENT, BRONZE);

    public static final ArmorMaterial EMERALD_ARMOR_MATERIAL = new ArmorMaterial (ModToolTiers.EMERALD.durability(),
            Util.make(new EnumMap<>(ArmorType.class), attribute -> {
                attribute.put(ArmorType.BOOTS, 2);
                attribute.put(ArmorType.LEGGINGS, 5);
                attribute.put(ArmorType.CHESTPLATE, 7);
                attribute.put(ArmorType.HELMET, 3);
                attribute.put(ArmorType.BODY, 5);
            }), 21, SoundEvents.ARMOR_EQUIP_GOLD, 0.7f, 0f, ModTags.Items.EMERALD_REPAIR_INGREDIENT, EMERALD);

    public static final ArmorMaterial COPPER_ARMOR_MATERIAL = new ArmorMaterial (ModToolTiers.COPPER.durability(),
            Util.make(new EnumMap<>(ArmorType.class), attribute -> {
                attribute.put(ArmorType.BOOTS, 1);
                attribute.put(ArmorType.LEGGINGS, 3);
                attribute.put(ArmorType.CHESTPLATE, 5);
                attribute.put(ArmorType.HELMET, 1);
                attribute.put(ArmorType.BODY, 4);
            }), 14, SoundEvents.ARMOR_EQUIP_IRON, 0f, 0f, ModTags.Items.COPPER_REPAIR_INGREDIENT, COPPER);
}