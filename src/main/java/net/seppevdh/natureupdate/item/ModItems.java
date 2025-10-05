package net.seppevdh.natureupdate.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.block.entity.FuelValues;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.block.ModBlocks;
import net.seppevdh.natureupdate.component.ModDataComponentTypes;
import net.seppevdh.natureupdate.component.custom.MoldFillerTypes;
import net.seppevdh.natureupdate.entity.ModEntities;
import net.seppevdh.natureupdate.item.custom.MoldItem;
import net.seppevdh.natureupdate.item.custom.SawItem;
import net.seppevdh.natureupdate.item.custom.SickleItem;
import net.seppevdh.natureupdate.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(NatureUpdate.MOD_ID);

    public static final DeferredItem<Item> TIN_INGOT = ITEMS.registerItem("tin_ingot", Item::new);
    public static final DeferredItem<Item> RAW_TIN = ITEMS.registerItem("raw_tin", Item::new);
    public static final DeferredItem<Item> BRONZE_INGOT = ITEMS.registerItem("bronze_ingot", Item::new);

    public static final DeferredItem<Item> INVINCIBLE_ITEM = ITEMS.registerItem("invincible_item",
            properties -> new Item(properties.rarity(Rarity.EPIC).stacksTo(1)
                    .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true).fireResistant()));

    public static final DeferredItem<Item> ELECTRODROME_MUSIC_DISC = ITEMS.registerItem("electrodrome_music_disc",
            properties -> new Item(properties.stacksTo(1).jukeboxPlayable(ModSounds.ELECTRODROME_KEY)));

    // tools

    public static final DeferredItem<Item> BRONZE_SWORD = ITEMS.registerItem("bronze_sword",
            properties -> new Item(properties.sword(ModToolTiers.BRONZE, 2, -2.4f)));
    public static final DeferredItem<Item> BRONZE_SHOVEL = ITEMS.registerItem("bronze_shovel",
            properties -> new ShovelItem(ModToolTiers.BRONZE, 1, -3f, properties));
    public static final DeferredItem<Item> BRONZE_HOE = ITEMS.registerItem("bronze_hoe",
            properties -> new HoeItem(ModToolTiers.BRONZE, -3, -0.5f, properties));
    public static final DeferredItem<Item> BRONZE_AXE = ITEMS.registerItem("bronze_axe",
            properties -> new AxeItem (ModToolTiers.BRONZE, 5, -3f, properties));
    public static final DeferredItem<Item> BRONZE_PICKAXE = ITEMS.registerItem("bronze_pickaxe",
            properties -> new Item(properties.pickaxe(ModToolTiers.BRONZE, 1, -2.8f)));

    public static final DeferredItem<Item> EMERALD_SWORD = ITEMS.registerItem("emerald_sword",
            properties -> new Item(properties.sword(ModToolTiers.EMERALD, 3, -2.4f)));
    public static final DeferredItem<Item> EMERALD_SHOVEL = ITEMS.registerItem("emerald_shovel",
            properties -> new ShovelItem(ModToolTiers.EMERALD, 1, -3f, properties));
    public static final DeferredItem<Item> EMERALD_HOE = ITEMS.registerItem("emerald_hoe",
            properties -> new HoeItem(ModToolTiers.EMERALD, -3, -0.5f, properties));
    public static final DeferredItem<Item> EMERALD_AXE = ITEMS.registerItem("emerald_axe",
            properties -> new AxeItem (ModToolTiers.EMERALD, 5, -3.05f, properties));
    public static final DeferredItem<Item> EMERALD_PICKAXE = ITEMS.registerItem("emerald_pickaxe",
            properties -> new Item(properties.pickaxe(ModToolTiers.EMERALD, 0, -2.8f)));

    public static final DeferredItem<Item> COPPER_SWORD = ITEMS.registerItem("copper_sword",
            properties -> new Item(properties.sword(ModToolTiers.COPPER, 1, -2.4f)));
    public static final DeferredItem<Item> COPPER_SHOVEL = ITEMS.registerItem("copper_shovel",
            properties -> new ShovelItem(ModToolTiers.COPPER, 0, -3f, properties));
    public static final DeferredItem<Item> COPPER_HOE = ITEMS.registerItem("copper_hoe",
            properties -> new HoeItem(ModToolTiers.COPPER, -3, -1.5f, properties));
    public static final DeferredItem<Item> COPPER_AXE = ITEMS.registerItem("copper_axe",
            properties -> new AxeItem (ModToolTiers.COPPER, 5, -3.15f, properties));
    public static final DeferredItem<Item> COPPER_PICKAXE = ITEMS.registerItem("copper_pickaxe",
            properties -> new Item(properties.pickaxe(ModToolTiers.COPPER, 0, -2.8f)));

    // seeds

    public static final DeferredItem<Item> RICE_SEEDS = ITEMS.registerItem("rice_seeds",
            properties -> new BlockItem(ModBlocks.RICE_CROP.get(), properties));

    // armor

    public static final DeferredItem<Item> BRONZE_HELMET = ITEMS.registerItem("bronze_helmet",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.BRONZE_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final DeferredItem<Item> BRONZE_CHESTPLATE = ITEMS.registerItem("bronze_chestplate",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.BRONZE_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final DeferredItem<Item> BRONZE_LEGGINGS = ITEMS.registerItem("bronze_leggings",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.BRONZE_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final DeferredItem<Item> BRONZE_BOOTS = ITEMS.registerItem("bronze_boots",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.BRONZE_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final DeferredItem<Item> EMERALD_HELMET = ITEMS.registerItem("emerald_helmet",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final DeferredItem<Item> EMERALD_CHESTPLATE = ITEMS.registerItem("emerald_chestplate",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final DeferredItem<Item> EMERALD_LEGGINGS = ITEMS.registerItem("emerald_leggings",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final DeferredItem<Item> EMERALD_BOOTS = ITEMS.registerItem("emerald_boots",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final DeferredItem<Item> COPPER_HELMET = ITEMS.registerItem("copper_helmet",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final DeferredItem<Item> COPPER_CHESTPLATE = ITEMS.registerItem("copper_chestplate",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final DeferredItem<Item> COPPER_LEGGINGS = ITEMS.registerItem("copper_leggings",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final DeferredItem<Item> COPPER_BOOTS = ITEMS.registerItem("copper_boots",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // horse armor

    public static final DeferredItem<Item> BRONZE_HORSE_ARMOR = ITEMS.registerItem("bronze_horse_armor",
            properties -> new Item(properties.stacksTo(1).horseArmor(ModArmorMaterials.BRONZE_ARMOR_MATERIAL)));
    public static final DeferredItem<Item> EMERALD_HORSE_ARMOR = ITEMS.registerItem("emerald_horse_armor",
            properties -> new Item(properties.stacksTo(1).horseArmor(ModArmorMaterials.EMERALD_ARMOR_MATERIAL)));
    public static final DeferredItem<Item> COPPER_HORSE_ARMOR = ITEMS.registerItem("copper_horse_armor",
            properties -> new Item(properties.stacksTo(1).horseArmor(ModArmorMaterials.COPPER_ARMOR_MATERIAL)));
    public static final DeferredItem<Item> NETHERITE_HORSE_ARMOR = ITEMS.registerItem("netherite_horse_armor",
            properties -> new Item(properties.stacksTo(1).horseArmor(ArmorMaterials.NETHERITE)));

    // foods

    public static final DeferredItem<Item> RICE = ITEMS.registerItem("rice",
            properties -> new BlockItem(ModBlocks.RICE_CROP.get(), properties.food(ModFoodProperties.RICE)));
    public static final DeferredItem<Item> PEAR = ITEMS.registerItem("pear",
            properties -> new Item(properties.food(ModFoodProperties.PEAR)));

    // sickle

    public static final DeferredItem<Item> SICKLE = ITEMS.registerItem("sickle",
            properties -> new SickleItem(properties.durability(128)
                    .attributes(SickleItem.createAttributes(4, 0.8))));

    // saws

    public static final DeferredItem<Item> COPPER_SAW = ITEMS.registerItem("copper_saw",
            properties -> new SawItem(properties.stacksTo(1)
                    .attributes(SawItem.createAttributes(ModToolTiers.COPPER, 7, -3.15))));
    public static final DeferredItem<Item> IRON_SAW = ITEMS.registerItem("iron_saw",
            properties -> new SawItem(properties.stacksTo(1)
                    .attributes(SawItem.createAttributes(ToolMaterial.IRON, 5, -3.1))));
    public static final DeferredItem<Item> GOLDEN_SAW = ITEMS.registerItem("golden_saw",
            properties -> new SawItem(properties.stacksTo(1)
                    .attributes(SawItem.createAttributes(ToolMaterial.GOLD, 3, -3.2))));
    public static final DeferredItem<Item> EMERALD_SAW = ITEMS.registerItem("emerald_saw",
            properties -> new SawItem(properties.stacksTo(1)
                    .attributes(SawItem.createAttributes(ModToolTiers.EMERALD, 5, -3.05))));
    public static final DeferredItem<Item> BRONZE_SAW = ITEMS.registerItem("bronze_saw",
            properties -> new SawItem(properties.stacksTo(1)
                    .attributes(SawItem.createAttributes(ModToolTiers.BRONZE, 5, -3.05))));
    public static final DeferredItem<Item> DIAMOND_SAW = ITEMS.registerItem("diamond_saw",
            properties -> new SawItem(properties.stacksTo(1)
                    .attributes(SawItem.createAttributes(ToolMaterial.DIAMOND, 5, -3))));
    public static final DeferredItem<Item> NETHERITE_SAW = ITEMS.registerItem("netherite_saw",
            properties -> new SawItem(properties.stacksTo(1)
                    .attributes(SawItem.createAttributes(ToolMaterial.NETHERITE, 5, -2.75))));

    // molds

    public static final DeferredItem<Item> AXE_MOLD = ITEMS.registerItem("axe_mold",
            properties -> new MoldItem(properties.stacksTo(16).component(ModDataComponentTypes.MOLD_FILLER_TYPE.get(), MoldFillerTypes.EMPTY)));
    public static final DeferredItem<Item> PICKAXE_MOLD = ITEMS.registerItem("pickaxe_mold",
            properties -> new MoldItem(properties.stacksTo(16).component(ModDataComponentTypes.MOLD_FILLER_TYPE.get(), MoldFillerTypes.EMPTY)));
    public static final DeferredItem<Item> SWORD_MOLD = ITEMS.registerItem("sword_mold",
            properties -> new MoldItem(properties.stacksTo(16).component(ModDataComponentTypes.MOLD_FILLER_TYPE.get(), MoldFillerTypes.EMPTY)));
    public static final DeferredItem<Item> SHOVEL_MOLD = ITEMS.registerItem("shovel_mold",
            properties -> new MoldItem(properties.stacksTo(16).component(ModDataComponentTypes.MOLD_FILLER_TYPE.get(), MoldFillerTypes.EMPTY)));
    public static final DeferredItem<Item> HOE_MOLD = ITEMS.registerItem("hoe_mold",
            properties -> new MoldItem(properties.stacksTo(16).component(ModDataComponentTypes.MOLD_FILLER_TYPE.get(), MoldFillerTypes.EMPTY)));
    public static final DeferredItem<Item> SAW_MOLD = ITEMS.registerItem("saw_mold",
            properties -> new MoldItem(properties.stacksTo(16).component(ModDataComponentTypes.MOLD_FILLER_TYPE.get(), MoldFillerTypes.EMPTY)));
    public static final DeferredItem<Item> SICKLE_MOLD = ITEMS.registerItem("sickle_mold",
            properties -> new MoldItem(properties.stacksTo(16).component(ModDataComponentTypes.MOLD_FILLER_TYPE.get(), MoldFillerTypes.EMPTY)));
    public static final DeferredItem<Item> SHEARS_MOLD = ITEMS.registerItem("shears_mold",
            properties -> new MoldItem(properties.stacksTo(16).component(ModDataComponentTypes.MOLD_FILLER_TYPE.get(), MoldFillerTypes.EMPTY)));
    public static final DeferredItem<Item> HELMET_MOLD = ITEMS.registerItem("helmet_mold",
            properties -> new MoldItem(properties.stacksTo(16).component(ModDataComponentTypes.MOLD_FILLER_TYPE.get(), MoldFillerTypes.EMPTY)));
    public static final DeferredItem<Item> CHESTPLATE_MOLD = ITEMS.registerItem("chestplate_mold",
            properties -> new MoldItem(properties.stacksTo(16).component(ModDataComponentTypes.MOLD_FILLER_TYPE.get(), MoldFillerTypes.EMPTY)));
    public static final DeferredItem<Item> LEGGINGS_MOLD = ITEMS.registerItem("leggings_mold",
            properties -> new MoldItem(properties.stacksTo(16).component(ModDataComponentTypes.MOLD_FILLER_TYPE.get(), MoldFillerTypes.EMPTY)));
    public static final DeferredItem<Item> BOOTS_MOLD = ITEMS.registerItem("boots_mold",
            properties -> new MoldItem(properties.stacksTo(16).component(ModDataComponentTypes.MOLD_FILLER_TYPE.get(), MoldFillerTypes.EMPTY)));

    // tool heads

    public static final DeferredItem<Item> BRONZE_AXE_HEAD = ITEMS.registerItem("bronze_axe_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> BRONZE_PICKAXE_HEAD = ITEMS.registerItem("bronze_pickaxe_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> BRONZE_SWORD_HEAD = ITEMS.registerItem("bronze_sword_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> BRONZE_SHOVEL_HEAD = ITEMS.registerItem("bronze_shovel_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> BRONZE_HOE_HEAD = ITEMS.registerItem("bronze_hoe_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> BRONZE_SAW_HEAD = ITEMS.registerItem("bronze_saw_head",
            properties -> new Item(properties.stacksTo(16)));

    public static final DeferredItem<Item> COPPER_AXE_HEAD = ITEMS.registerItem("copper_axe_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> COPPER_PICKAXE_HEAD = ITEMS.registerItem("copper_pickaxe_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> COPPER_SWORD_HEAD = ITEMS.registerItem("copper_sword_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> COPPER_SHOVEL_HEAD = ITEMS.registerItem("copper_shovel_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> COPPER_HOE_HEAD = ITEMS.registerItem("copper_hoe_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> COPPER_SAW_HEAD = ITEMS.registerItem("copper_saw_head",
            properties -> new Item(properties.stacksTo(16)));

    public static final DeferredItem<Item> GOLDEN_AXE_HEAD = ITEMS.registerItem("golden_axe_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> GOLDEN_PICKAXE_HEAD = ITEMS.registerItem("golden_pickaxe_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> GOLDEN_SWORD_HEAD = ITEMS.registerItem("golden_sword_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> GOLDEN_SHOVEL_HEAD = ITEMS.registerItem("golden_shovel_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> GOLDEN_HOE_HEAD = ITEMS.registerItem("golden_hoe_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> GOLDEN_SAW_HEAD = ITEMS.registerItem("golden_saw_head",
            properties -> new Item(properties.stacksTo(16)));

    public static final DeferredItem<Item> IRON_AXE_HEAD = ITEMS.registerItem("iron_axe_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> IRON_PICKAXE_HEAD = ITEMS.registerItem("iron_pickaxe_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> IRON_SWORD_HEAD = ITEMS.registerItem("iron_sword_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> IRON_SHOVEL_HEAD = ITEMS.registerItem("iron_shovel_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> IRON_HOE_HEAD = ITEMS.registerItem("iron_hoe_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> IRON_SAW_HEAD = ITEMS.registerItem("iron_saw_head",
            properties -> new Item(properties.stacksTo(16)));

    public static final DeferredItem<Item> DIAMOND_AXE_HEAD = ITEMS.registerItem("diamond_axe_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> DIAMOND_PICKAXE_HEAD = ITEMS.registerItem("diamond_pickaxe_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> DIAMOND_SWORD_HEAD = ITEMS.registerItem("diamond_sword_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> DIAMOND_SHOVEL_HEAD = ITEMS.registerItem("diamond_shovel_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> DIAMOND_HOE_HEAD = ITEMS.registerItem("diamond_hoe_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> DIAMOND_SAW_HEAD = ITEMS.registerItem("diamond_saw_head",
            properties -> new Item(properties.stacksTo(16)));

    public static final DeferredItem<Item> EMERALD_AXE_HEAD = ITEMS.registerItem("emerald_axe_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> EMERALD_PICKAXE_HEAD = ITEMS.registerItem("emerald_pickaxe_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> EMERALD_SWORD_HEAD = ITEMS.registerItem("emerald_sword_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> EMERALD_SHOVEL_HEAD = ITEMS.registerItem("emerald_shovel_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> EMERALD_HOE_HEAD = ITEMS.registerItem("emerald_hoe_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> EMERALD_SAW_HEAD = ITEMS.registerItem("emerald_saw_head",
            properties -> new Item(properties.stacksTo(16)));

    public static final DeferredItem<Item> SICKLE_HEAD = ITEMS.registerItem("sickle_head",
            properties -> new Item(properties.stacksTo(16)));
    public static final DeferredItem<Item> SAW_HANDLE = ITEMS.registerItem("saw_handle",
            properties -> new Item(properties.stacksTo(16)));

    public static final DeferredItem<Item> SOUL_COAL = ITEMS.registerItem("soul_coal",
            properties -> new Item(properties) {
                @Override
                public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType, FuelValues fuelValues) {
                    return 2000;
                }
            });

    public static final DeferredItem<Item> ENDER_COAL = ITEMS.registerItem("ender_coal",
            properties -> new Item(properties) {
                @Override
                public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType, FuelValues fuelValues) {
                    return 2400;
                }
            });

    public static final DeferredItem<Item> NETHER_ESSENCE = ITEMS.registerItem("nether_essence",
            Item::new);
    public static final DeferredItem<Item> SOUL_ESSENCE = ITEMS.registerItem("soul_essence",
            Item::new);
    public static final DeferredItem<Item> ENDER_ESSENCE = ITEMS.registerItem("ender_essence",
            Item::new);
    public static final DeferredItem<Item> POLLUTED_SOUL_FLUID_BUCKET = ITEMS.registerItem("polluted_soul_fluid_bucket",
            Item::new);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
