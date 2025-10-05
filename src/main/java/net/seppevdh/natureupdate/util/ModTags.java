package net.seppevdh.natureupdate.util;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.material.Fluid;
import net.seppevdh.natureupdate.NatureUpdate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_BRONZE_TOOL = createTag("needs_bronze_tool");
        public static final TagKey<Block> INCORRECT_FOR_BRONZE_TOOL = createTag("incorrect_for_bronze_tool");
        public static final TagKey<Block> NEEDS_EMERALD_TOOL = createTag("needs_emerald_tool");
        public static final TagKey<Block> INCORRECT_FOR_EMERALD_TOOL = createTag("incorrect_for_emerald_tool");
        public static final TagKey<Block> NEEDS_COPPER_TOOL = createTag("needs_copper_tool");
        public static final TagKey<Block> INCORRECT_FOR_COPPER_TOOL = createTag("incorrect_for_copper_tool");
        public static final TagKey<Block> TABLE = createTag("table");
        public static final TagKey<Block> END_FIRE_BASE_BLOCKS = createTag("end_fire_base_blocks");
        public static final TagKey<Block> NORMAL_FUEL_FIRES = createTag("normal_fuel_fires");
        public static final TagKey<Block> SOUL_FUEL_FIRES = createTag("soul_fuel_fires");
        public static final TagKey<Block> END_FUEL_FIRES = createTag("end_fuel_fires");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> MOSAIC = createTag("mosaic");
        public static final TagKey<Item> MOLDS = createTag("molds");
        public static final TagKey<Item> TABLE = createTag("table");
        public static final TagKey<Item> CHAIR = createTag("chair");
        public static final TagKey<Item> COPPER_REPAIR_INGREDIENT = createTag("copper_repair_ingredient");
        public static final TagKey<Item> BRONZE_REPAIR_INGREDIENT = createTag("bronze_repair_ingredient");
        public static final TagKey<Item> EMERALD_REPAIR_INGREDIENT = createTag("emerald_repair_ingredient");
        public static final TagKey<Item> SOUL_FUELING = createTag("soul_fueling");
        public static final TagKey<Item> ENDER_FUELING = createTag("ender_fueling");
        public static final TagKey<Item> BREEZING_FUEL = createTag("breezing_fuel");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, name));
        }
    }

    public static class Fluids {
        public static final TagKey<Fluid> SOUL_FLUID = createTag("soul_fluid");
        public static final TagKey<Fluid> END_FLUID = createTag("end_fluid");

        private static TagKey<Fluid> createTag(String name) {
            return FluidTags.create(ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, name));
        }
    }
}
