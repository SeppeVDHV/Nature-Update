package net.seppevdh.natureupdate.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.Tags;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.block.ModBlocks;
import net.seppevdh.natureupdate.component.custom.MoldFillerType;
import net.seppevdh.natureupdate.component.custom.MoldFillerTypes;
import net.seppevdh.natureupdate.fluid.ModFluids;
import net.seppevdh.natureupdate.item.ModItems;
import net.seppevdh.natureupdate.item.custom.SawItem;
import net.seppevdh.natureupdate.recipe.builder.*;
import net.seppevdh.natureupdate.recipe.fueling.ModFuelingTypes;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
        super(provider, output);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
            super(packOutput, provider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new ModRecipeProvider(provider, output);
        }

        @Override
        public String getName() {
            return "My Recipes";
        }
    }

    @Override
    protected void buildRecipes() {
        oreSmelting(output, ModBlocks.TIN_ORE.get(), RecipeCategory.TOOLS, ModItems.TIN_INGOT.get(), 0.3f, 200, "tin_powder");
        oreSmelting(output, ModItems.RAW_TIN.get(), RecipeCategory.TOOLS, ModItems.TIN_INGOT.get(), 0.3f, 200, "tin_powder");
        oreBlasting(output, ModBlocks.TIN_ORE.get(), RecipeCategory.TOOLS, ModItems.TIN_INGOT.get(), 0.3f, 200, "tin_powder");
        oreBlasting(output, ModItems.RAW_TIN.get(), RecipeCategory.TOOLS, ModItems.TIN_INGOT.get(), 0.3f, 200, "tin_powder");

        shaped(RecipeCategory.MISC, ModBlocks.CRAFTING_FURNACE.get())
                .pattern("ABA")
                .pattern("BAB")
                .pattern("ABA")
                .define('A', Blocks.FURNACE)
                .define('B', Blocks.CRAFTING_TABLE)
                .unlockedBy(getHasName(Blocks.FURNACE), has(Blocks.FURNACE)).save(output);

        blockRecipe(output, ModItems.RAW_TIN.get(), ModBlocks.RAW_TIN_BLOCK.get(), 1, RecipeCategory.BUILDING_BLOCKS);
        blockRecipe(output, ModItems.TIN_INGOT.get(), ModBlocks.TIN_BLOCK.get(), 1, RecipeCategory.BUILDING_BLOCKS);
        blockRecipe(output, ModItems.BRONZE_INGOT.get(), ModBlocks.BRONZE_BLOCK.get(), 1, RecipeCategory.BUILDING_BLOCKS);

        conversionRecipe(output, ModBlocks.RAW_TIN_BLOCK.get(), new ItemStack(ModItems.RAW_TIN.get(), 9), RecipeCategory.MISC);
        conversionRecipe(output, ModBlocks.TIN_BLOCK.get(), new ItemStack(ModItems.TIN_INGOT.get(), 9), RecipeCategory.MISC);
        conversionRecipe(output, ModBlocks.BRONZE_BLOCK.get(), new ItemStack(ModItems.BRONZE_INGOT.get(), 9), RecipeCategory.MISC);
        compactingRecipe(output, Blocks.IRON_BLOCK, new ItemStack(ModBlocks.IRON_BUTTON.get(), 4), RecipeCategory.BUILDING_BLOCKS, 1);
        compactingRecipe(output, Blocks.GOLD_BLOCK, new ItemStack(ModBlocks.GOLDEN_BUTTON.get(), 4), RecipeCategory.BUILDING_BLOCKS, 1);
        compactingRecipe(output, Blocks.EMERALD_BLOCK, new ItemStack(ModBlocks.EMERALD_BUTTON.get(), 4), RecipeCategory.BUILDING_BLOCKS, 1);
        compactingRecipe(output, Blocks.DIAMOND_BLOCK, new ItemStack(ModBlocks.DIAMOND_BUTTON.get(), 4), RecipeCategory.BUILDING_BLOCKS, 1);
        compactingRecipe(output, Blocks.NETHERITE_BLOCK, new ItemStack(ModBlocks.NETHERITE_BUTTON.get(), 4), RecipeCategory.BUILDING_BLOCKS, 1);
        compactingRecipe(output, ModBlocks.TIN_BLOCK.get(), new ItemStack(ModBlocks.TIN_BUTTON.get(), 4), RecipeCategory.BUILDING_BLOCKS, 1);
        compactingRecipe(output, ModBlocks.BRONZE_BLOCK.get(), new ItemStack(ModBlocks.BRONZE_BUTTON.get(), 4), RecipeCategory.BUILDING_BLOCKS, 1);
        compactingRecipe(output, Blocks.LAPIS_BLOCK, new ItemStack(ModBlocks.LAPIS_BUTTON.get(), 4), RecipeCategory.BUILDING_BLOCKS, 1);

        chiseledRecipe(output, Blocks.OAK_SLAB, new ItemStack(ModBlocks.OAK_MOSAIC.get()), RecipeCategory.DECORATIONS);
        chiseledRecipe(output, Blocks.BIRCH_SLAB, new ItemStack(ModBlocks.BIRCH_MOSAIC.get()), RecipeCategory.DECORATIONS);
        chiseledRecipe(output, Blocks.SPRUCE_SLAB, new ItemStack(ModBlocks.SPRUCE_MOSAIC.get()), RecipeCategory.DECORATIONS);
        chiseledRecipe(output, Blocks.ACACIA_SLAB, new ItemStack(ModBlocks.ACACIA_MOSAIC.get()), RecipeCategory.DECORATIONS);
        chiseledRecipe(output, Blocks.JUNGLE_SLAB, new ItemStack(ModBlocks.JUNGLE_MOSAIC.get()), RecipeCategory.DECORATIONS);
        chiseledRecipe(output, Blocks.DARK_OAK_SLAB, new ItemStack(ModBlocks.DARK_OAK_MOSAIC.get()), RecipeCategory.DECORATIONS);
        chiseledRecipe(output, Blocks.MANGROVE_SLAB, new ItemStack(ModBlocks.MANGROVE_MOSAIC.get()), RecipeCategory.DECORATIONS);
        chiseledRecipe(output, Blocks.CHERRY_SLAB, new ItemStack(ModBlocks.CHERRY_MOSAIC.get()), RecipeCategory.DECORATIONS);
        chiseledRecipe(output, Blocks.PALE_OAK_SLAB, new ItemStack(ModBlocks.PALE_OAK_MOSAIC.get()), RecipeCategory.DECORATIONS);
        chiseledRecipe(output, Blocks.CRIMSON_SLAB, new ItemStack(ModBlocks.CRIMSON_MOSAIC.get()), RecipeCategory.DECORATIONS);
        chiseledRecipe(output, Blocks.WARPED_SLAB, new ItemStack(ModBlocks.WARPED_MOSAIC.get()), RecipeCategory.DECORATIONS);

        stairsRecipe(output, ModBlocks.OAK_MOSAIC.get(), new ItemStack(ModBlocks.OAK_MOSAIC_STAIRS.get(), 4), RecipeCategory.DECORATIONS);
        stairsRecipe(output, ModBlocks.BIRCH_MOSAIC.get(), new ItemStack(ModBlocks.BIRCH_MOSAIC_STAIRS.get(), 4), RecipeCategory.DECORATIONS);
        stairsRecipe(output, ModBlocks.SPRUCE_MOSAIC.get(), new ItemStack(ModBlocks.SPRUCE_MOSAIC_STAIRS.get(), 4), RecipeCategory.DECORATIONS);
        stairsRecipe(output, ModBlocks.ACACIA_MOSAIC.get(), new ItemStack(ModBlocks.ACACIA_MOSAIC_STAIRS.get(), 4), RecipeCategory.DECORATIONS);
        stairsRecipe(output, ModBlocks.JUNGLE_MOSAIC.get(), new ItemStack(ModBlocks.JUNGLE_MOSAIC_STAIRS.get(), 4), RecipeCategory.DECORATIONS);
        stairsRecipe(output, ModBlocks.DARK_OAK_MOSAIC.get(), new ItemStack(ModBlocks.DARK_OAK_MOSAIC_STAIRS.get(), 4), RecipeCategory.DECORATIONS);
        stairsRecipe(output, ModBlocks.MANGROVE_MOSAIC.get(), new ItemStack(ModBlocks.MANGROVE_MOSAIC_STAIRS.get(), 4), RecipeCategory.DECORATIONS);
        stairsRecipe(output, ModBlocks.CHERRY_MOSAIC.get(), new ItemStack(ModBlocks.CHERRY_MOSAIC_STAIRS.get(), 4), RecipeCategory.DECORATIONS);
        stairsRecipe(output, ModBlocks.PALE_OAK_MOSAIC.get(), new ItemStack(ModBlocks.PALE_OAK_MOSAIC_STAIRS.get(), 4), RecipeCategory.DECORATIONS);
        stairsRecipe(output, ModBlocks.WARPED_MOSAIC.get(), new ItemStack(ModBlocks.WARPED_MOSAIC_STAIRS.get(), 4), RecipeCategory.DECORATIONS);
        stairsRecipe(output, ModBlocks.CRIMSON_MOSAIC.get(), new ItemStack(ModBlocks.CRIMSON_MOSAIC_STAIRS.get(), 4), RecipeCategory.DECORATIONS);
        stairsRecipe(output, Blocks.IRON_BLOCK, new ItemStack(ModBlocks.IRON_STAIRS.get(), 4), RecipeCategory.DECORATIONS, 1);
        stairsRecipe(output, Blocks.GOLD_BLOCK, new ItemStack(ModBlocks.GOLDEN_STAIRS.get(), 4), RecipeCategory.DECORATIONS, 1);
        stairsRecipe(output, Blocks.DIAMOND_BLOCK, new ItemStack(ModBlocks.DIAMOND_STAIRS.get(), 4), RecipeCategory.DECORATIONS, 1);
        stairsRecipe(output, Blocks.EMERALD_BLOCK, new ItemStack(ModBlocks.EMERALD_STAIRS.get(), 4), RecipeCategory.DECORATIONS, 1);
        stairsRecipe(output, Blocks.NETHERITE_BLOCK, new ItemStack(ModBlocks.NETHERITE_STAIRS.get(), 4), RecipeCategory.DECORATIONS, 1);
        stairsRecipe(output, ModBlocks.TIN_BLOCK.get(), new ItemStack(ModBlocks.TIN_STAIRS.get(), 4), RecipeCategory.DECORATIONS, 1);
        stairsRecipe(output, ModBlocks.BRONZE_BLOCK.get(), new ItemStack(ModBlocks.BRONZE_STAIRS.get(), 4), RecipeCategory.DECORATIONS, 1);
        stairsRecipe(output, Blocks.LAPIS_BLOCK, new ItemStack(ModBlocks.LAPIS_STAIRS.get(), 4), RecipeCategory.DECORATIONS, 1);
        stairsRecipe(output, Blocks.SMOOTH_STONE, new ItemStack(ModBlocks.SMOOTH_STONE_STAIRS.get(), 4), RecipeCategory.DECORATIONS, 1);

        slabRecipe(output, ModBlocks.OAK_MOSAIC.get(), new ItemStack(ModBlocks.OAK_MOSAIC_SLAB.get(), 6), RecipeCategory.DECORATIONS);
        slabRecipe(output, ModBlocks.BIRCH_MOSAIC.get(), new ItemStack(ModBlocks.BIRCH_MOSAIC_SLAB.get(), 6), RecipeCategory.DECORATIONS);
        slabRecipe(output, ModBlocks.SPRUCE_MOSAIC.get(), new ItemStack(ModBlocks.SPRUCE_MOSAIC_SLAB.get(), 6), RecipeCategory.DECORATIONS);
        slabRecipe(output, ModBlocks.ACACIA_MOSAIC.get(), new ItemStack(ModBlocks.ACACIA_MOSAIC_SLAB.get(), 6), RecipeCategory.DECORATIONS);
        slabRecipe(output, ModBlocks.JUNGLE_MOSAIC.get(), new ItemStack(ModBlocks.JUNGLE_MOSAIC_SLAB.get(), 6), RecipeCategory.DECORATIONS);
        slabRecipe(output, ModBlocks.DARK_OAK_MOSAIC.get(), new ItemStack(ModBlocks.DARK_OAK_MOSAIC_SLAB.get(), 6), RecipeCategory.DECORATIONS);
        slabRecipe(output, ModBlocks.MANGROVE_MOSAIC.get(), new ItemStack(ModBlocks.MANGROVE_MOSAIC_SLAB.get(), 6), RecipeCategory.DECORATIONS);
        slabRecipe(output, ModBlocks.CHERRY_MOSAIC.get(), new ItemStack(ModBlocks.CHERRY_MOSAIC_SLAB.get(), 6), RecipeCategory.DECORATIONS);
        slabRecipe(output, ModBlocks.PALE_OAK_MOSAIC.get(), new ItemStack(ModBlocks.PALE_OAK_MOSAIC_SLAB.get(), 6), RecipeCategory.DECORATIONS);
        slabRecipe(output, ModBlocks.WARPED_MOSAIC.get(), new ItemStack(ModBlocks.WARPED_MOSAIC_SLAB.get(), 6), RecipeCategory.DECORATIONS);
        slabRecipe(output, ModBlocks.CRIMSON_MOSAIC.get(), new ItemStack(ModBlocks.CRIMSON_MOSAIC_SLAB.get(), 6), RecipeCategory.DECORATIONS);
        slabRecipe(Blocks.IRON_BLOCK, new ItemStack(ModBlocks.IRON_SLAB.get(), 6), RecipeCategory.DECORATIONS, 2);
        slabRecipe(Blocks.GOLD_BLOCK, new ItemStack(ModBlocks.GOLDEN_SLAB.get(), 6), RecipeCategory.DECORATIONS, 2);
        slabRecipe(Blocks.DIAMOND_BLOCK, new ItemStack(ModBlocks.DIAMOND_SLAB.get(), 6), RecipeCategory.DECORATIONS, 2);
        slabRecipe(Blocks.EMERALD_BLOCK, new ItemStack(ModBlocks.EMERALD_SLAB.get(), 6), RecipeCategory.DECORATIONS, 2);
        slabRecipe(Blocks.NETHERITE_BLOCK, new ItemStack(ModBlocks.NETHERITE_SLAB.get(), 6), RecipeCategory.DECORATIONS, 2);
        slabRecipe(ModBlocks.TIN_BLOCK.get(), new ItemStack(ModBlocks.TIN_SLAB.get(), 6), RecipeCategory.DECORATIONS, 2);
        slabRecipe(ModBlocks.BRONZE_BLOCK.get(), new ItemStack(ModBlocks.BRONZE_SLAB.get(), 6), RecipeCategory.DECORATIONS, 2);
        slabRecipe(Blocks.LAPIS_BLOCK, new ItemStack(ModBlocks.LAPIS_SLAB.get(), 6), RecipeCategory.DECORATIONS, 2);

        pressurePlateRecipe(Items.EMERALD, ModBlocks.EMERALD_PRESSURE_PLATE.get(), RecipeCategory.DECORATIONS);
        pressurePlateRecipe(Items.DIAMOND, ModBlocks.DIAMOND_PRESSURE_PLATE.get(), RecipeCategory.DECORATIONS);
        pressurePlateRecipe(Items.NETHERITE_INGOT, ModBlocks.NETHERITE_PRESSURE_PLATE.get(), RecipeCategory.DECORATIONS);
        pressurePlateRecipe(ModItems.TIN_INGOT.get(), ModBlocks.TIN_PRESSURE_PLATE.get(), RecipeCategory.DECORATIONS);
        pressurePlateRecipe(ModItems.BRONZE_INGOT.get(), ModBlocks.BRONZE_PRESSURE_PLATE.get(), RecipeCategory.DECORATIONS);
        pressurePlateRecipe(Items.LAPIS_LAZULI, ModBlocks.LAPIS_PRESSURE_PLATE.get(), RecipeCategory.DECORATIONS);

        doorRecipe(output, Items.GOLD_INGOT, new ItemStack(ModBlocks.GOLDEN_DOOR.get(), 3), RecipeCategory.DECORATIONS);
        doorRecipe(output, Items.DIAMOND, new ItemStack(ModBlocks.DIAMOND_DOOR.get(), 3), RecipeCategory.DECORATIONS);
        doorRecipe(output, Items.NETHERITE_INGOT, new ItemStack(ModBlocks.NETHERITE_DOOR.get(), 3), RecipeCategory.DECORATIONS);
        doorRecipe(output, Items.EMERALD, new ItemStack(ModBlocks.EMERALD_DOOR.get(), 3), RecipeCategory.DECORATIONS);
        doorRecipe(output, ModItems.TIN_INGOT.get(), new ItemStack(ModBlocks.TIN_DOOR.get(), 3), RecipeCategory.DECORATIONS);
        doorRecipe(output, ModItems.BRONZE_INGOT.get(), new ItemStack(ModBlocks.BRONZE_DOOR.get(), 3), RecipeCategory.DECORATIONS);
        doorRecipe(output, Items.LAPIS_LAZULI, new ItemStack(ModBlocks.LAPIS_DOOR.get(), 3), RecipeCategory.DECORATIONS);

        shaped(RecipeCategory.DECORATIONS, ModBlocks.GOLDEN_TRAPDOOR.get())
                .pattern("GG")
                .pattern("GG")
                .define('G', Items.GOLD_INGOT)
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT)).save(output);
        trapdoorRecipe(output, Items.DIAMOND, new ItemStack(ModBlocks.DIAMOND_TRAPDOOR.get(), 2), RecipeCategory.DECORATIONS);
        trapdoorRecipe(output, Items.NETHERITE_INGOT, new ItemStack(ModBlocks.NETHERITE_TRAPDOOR.get(), 2), RecipeCategory.DECORATIONS);
        trapdoorRecipe(output, Items.EMERALD, new ItemStack(ModBlocks.EMERALD_TRAPDOOR.get(), 2), RecipeCategory.DECORATIONS);
        trapdoorRecipe(output, ModItems.TIN_INGOT.get(), new ItemStack(ModBlocks.TIN_TRAPDOOR.get(), 2), RecipeCategory.DECORATIONS);
        trapdoorRecipe(output, ModItems.BRONZE_INGOT.get(), new ItemStack(ModBlocks.BRONZE_TRAPDOOR.get(), 2), RecipeCategory.DECORATIONS);
        trapdoorRecipe(output, Items.LAPIS_LAZULI, new ItemStack(ModBlocks.LAPIS_TRAPDOOR.get(), 2), RecipeCategory.DECORATIONS);

        grateRecipe(output, Blocks.IRON_BLOCK, new ItemStack(ModBlocks.IRON_GRATE.get(), 4), RecipeCategory.DECORATIONS, 1);
        grateRecipe(output, Blocks.GOLD_BLOCK, new ItemStack(ModBlocks.GOLDEN_GRATE.get(), 4), RecipeCategory.DECORATIONS, 1);
        grateRecipe(output, Blocks.EMERALD_BLOCK, new ItemStack(ModBlocks.EMERALD_GRATE.get(), 4), RecipeCategory.DECORATIONS, 1);
        grateRecipe(output, Blocks.LAPIS_BLOCK, new ItemStack(ModBlocks.LAPIS_GRATE.get(), 4), RecipeCategory.DECORATIONS, 1);
        grateRecipe(output, ModBlocks.TIN_BLOCK.get(), new ItemStack(ModBlocks.TIN_GRATE.get(), 4), RecipeCategory.DECORATIONS, 1);
        grateRecipe(output, ModBlocks.BRONZE_BLOCK.get(), new ItemStack(ModBlocks.BRONZE_GRATE.get(), 4), RecipeCategory.DECORATIONS, 1);
        grateRecipe(output, Blocks.DIAMOND_BLOCK, new ItemStack(ModBlocks.DIAMOND_GRATE.get(), 4), RecipeCategory.DECORATIONS, 1);
        grateRecipe(output, Blocks.NETHERITE_BLOCK, new ItemStack(ModBlocks.NETHERITE_GRATE.get(), 4), RecipeCategory.DECORATIONS, 1);

        stonecutterResultFromBase(RecipeCategory.MISC, ModItems.AXE_MOLD.get(), Blocks.STONE);
        stonecutterResultFromBase(RecipeCategory.MISC, ModItems.PICKAXE_MOLD.get(), Blocks.STONE);
        stonecutterResultFromBase(RecipeCategory.MISC, ModItems.SWORD_MOLD.get(), Blocks.STONE);
        stonecutterResultFromBase(RecipeCategory.MISC, ModItems.SHOVEL_MOLD.get(), Blocks.STONE);
        stonecutterResultFromBase(RecipeCategory.MISC, ModItems.HOE_MOLD.get(), Blocks.STONE);
        stonecutterResultFromBase(RecipeCategory.MISC, ModItems.SAW_MOLD.get(), Blocks.STONE);
        stonecutterResultFromBase(RecipeCategory.MISC, ModItems.SICKLE_MOLD.get(), Blocks.STONE);
        stonecutterResultFromBase(RecipeCategory.MISC, ModItems.SHEARS_MOLD.get(), Blocks.STONE);
        stonecutterResultFromBase(RecipeCategory.MISC, ModItems.HELMET_MOLD.get(), Blocks.STONE);
        stonecutterResultFromBase(RecipeCategory.MISC, ModItems.CHESTPLATE_MOLD.get(), Blocks.STONE);
        stonecutterResultFromBase(RecipeCategory.MISC, ModItems.LEGGINGS_MOLD.get(), Blocks.STONE);
        stonecutterResultFromBase(RecipeCategory.MISC, ModItems.BOOTS_MOLD.get(), Blocks.STONE);

        toolRecipes(output, new ItemStack(ModItems.COPPER_SWORD.get()), ModItems.COPPER_SWORD_HEAD.get(), new ItemStack(ModItems.COPPER_SHOVEL.get()), ModItems.COPPER_SHOVEL_HEAD.get(), new ItemStack(ModItems.COPPER_AXE.get()),
                ModItems.COPPER_AXE_HEAD.get(), new ItemStack(ModItems.COPPER_PICKAXE.get()), ModItems.COPPER_PICKAXE_HEAD.get(), new ItemStack(ModItems.COPPER_HOE.get()), ModItems.COPPER_HOE_HEAD.get());
        toolRecipes(output, new ItemStack(ModItems.BRONZE_SWORD.get()), ModItems.BRONZE_SWORD_HEAD.get(), new ItemStack(ModItems.BRONZE_SHOVEL.get()), ModItems.BRONZE_SHOVEL_HEAD.get(), new ItemStack(ModItems.BRONZE_AXE.get()),
                ModItems.BRONZE_AXE_HEAD.get(), new ItemStack(ModItems.BRONZE_PICKAXE.get()), ModItems.BRONZE_PICKAXE_HEAD.get(), new ItemStack(ModItems.BRONZE_HOE.get()), ModItems.BRONZE_HOE_HEAD.get());
        toolRecipes(output, new ItemStack(ModItems.EMERALD_SWORD.get()), ModItems.EMERALD_SWORD_HEAD.get(), new ItemStack(ModItems.EMERALD_SHOVEL.get()), ModItems.EMERALD_SHOVEL_HEAD.get(), new ItemStack(ModItems.EMERALD_AXE.get()),
                ModItems.EMERALD_AXE_HEAD.get(), new ItemStack(ModItems.EMERALD_PICKAXE.get()), ModItems.EMERALD_PICKAXE_HEAD.get(), new ItemStack(ModItems.EMERALD_HOE.get()), ModItems.EMERALD_HOE_HEAD.get());
        toolRecipes(output, new ItemStack(Items.IRON_SWORD), ModItems.IRON_SWORD_HEAD.get(), new ItemStack(Items.IRON_SHOVEL), ModItems.IRON_SHOVEL_HEAD.get(), new ItemStack(Items.IRON_AXE),
                ModItems.IRON_AXE_HEAD.get(), new ItemStack(Items.IRON_PICKAXE), ModItems.IRON_PICKAXE_HEAD.get(), new ItemStack(Items.IRON_HOE), ModItems.IRON_HOE_HEAD.get());
        toolRecipes(output, new ItemStack(Items.GOLDEN_SWORD), ModItems.GOLDEN_SWORD_HEAD.get(), new ItemStack(Items.GOLDEN_SHOVEL), ModItems.GOLDEN_SHOVEL_HEAD.get(), new ItemStack(Items.GOLDEN_AXE),
                ModItems.GOLDEN_AXE_HEAD.get(), new ItemStack(Items.GOLDEN_PICKAXE), ModItems.GOLDEN_PICKAXE_HEAD.get(), new ItemStack(Items.GOLDEN_HOE), ModItems.GOLDEN_HOE_HEAD.get());
        toolRecipes(output, new ItemStack(Items.DIAMOND_SWORD), ModItems.DIAMOND_SWORD_HEAD.get(), new ItemStack(Items.DIAMOND_SHOVEL), ModItems.DIAMOND_SHOVEL_HEAD.get(), new ItemStack(Items.DIAMOND_AXE),
                ModItems.DIAMOND_AXE_HEAD.get(), new ItemStack(Items.DIAMOND_PICKAXE), ModItems.DIAMOND_PICKAXE_HEAD.get(), new ItemStack(Items.DIAMOND_HOE), ModItems.DIAMOND_HOE_HEAD.get());

        shaped(RecipeCategory.TOOLS, ModItems.SICKLE.get())
                .pattern("H")
                .pattern("S")
                .pattern("S")
                .define('H', ModItems.SICKLE_HEAD.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.SICKLE_HEAD.get()), has(ModItems.SICKLE_HEAD.get())).save(output);

        sawRecipe(output, ModItems.COPPER_SAW_HEAD.get(), (SawItem) ModItems.COPPER_SAW.get());
        sawRecipe(output, ModItems.BRONZE_SAW_HEAD.get(), (SawItem) ModItems.BRONZE_SAW.get());
        sawRecipe(output, ModItems.IRON_SAW_HEAD.get(), (SawItem) ModItems.IRON_SAW.get());
        sawRecipe(output, ModItems.GOLDEN_SAW_HEAD.get(), (SawItem) ModItems.GOLDEN_SAW.get());
        sawRecipe(output, ModItems.DIAMOND_SAW_HEAD.get(), (SawItem) ModItems.DIAMOND_SAW.get());
        sawRecipe(output, ModItems.EMERALD_SAW_HEAD.get(), (SawItem) ModItems.EMERALD_SAW.get());

        tableRecipe(output, Items.OAK_PLANKS, new ItemStack(ModBlocks.OAK_TABLE.get()));
        tableRecipe(output, Items.BIRCH_PLANKS, new ItemStack(ModBlocks.BIRCH_TABLE.get()));
        tableRecipe(output, Items.SPRUCE_PLANKS, new ItemStack(ModBlocks.SPRUCE_TABLE.get()));
        tableRecipe(output, Items.ACACIA_PLANKS, new ItemStack(ModBlocks.ACACIA_TABLE.get()));
        tableRecipe(output, Items.JUNGLE_PLANKS, new ItemStack(ModBlocks.JUNGLE_TABLE.get()));
        tableRecipe(output, Items.DARK_OAK_PLANKS, new ItemStack(ModBlocks.DARK_OAK_TABLE.get()));
        tableRecipe(output, Items.MANGROVE_PLANKS, new ItemStack(ModBlocks.MANGROVE_TABLE.get()));
        tableRecipe(output, Items.CHERRY_PLANKS, new ItemStack(ModBlocks.CHERRY_TABLE.get()));
        tableRecipe(output, Items.BAMBOO_PLANKS, new ItemStack(ModBlocks.BAMBOO_TABLE.get()));
        tableRecipe(output, Items.PALE_OAK_PLANKS, new ItemStack(ModBlocks.PALE_OAK_TABLE.get()));
        tableRecipe(output, Items.CRIMSON_PLANKS, new ItemStack(ModBlocks.CRIMSON_TABLE.get()));
        tableRecipe(output, Items.WARPED_PLANKS, new ItemStack(ModBlocks.WARPED_TABLE.get()));

        chairRecipe(output, Items.OAK_PLANKS, new ItemStack(ModBlocks.OAK_CHAIR.get()));
        chairRecipe(output, Items.BIRCH_PLANKS, new ItemStack(ModBlocks.BIRCH_CHAIR.get()));
        chairRecipe(output, Items.SPRUCE_PLANKS, new ItemStack(ModBlocks.SPRUCE_CHAIR.get()));
        chairRecipe(output, Items.ACACIA_PLANKS, new ItemStack(ModBlocks.ACACIA_CHAIR.get()));
        chairRecipe(output, Items.JUNGLE_PLANKS, new ItemStack(ModBlocks.JUNGLE_CHAIR.get()));
        chairRecipe(output, Items.DARK_OAK_PLANKS, new ItemStack(ModBlocks.DARK_OAK_CHAIR.get()));
        chairRecipe(output, Items.MANGROVE_PLANKS, new ItemStack(ModBlocks.MANGROVE_CHAIR.get()));
        chairRecipe(output, Items.CHERRY_PLANKS, new ItemStack(ModBlocks.CHERRY_CHAIR.get()));
        chairRecipe(output, Items.BAMBOO_PLANKS, new ItemStack(ModBlocks.BAMBOO_CHAIR.get()));
        chairRecipe(output, Items.PALE_OAK_PLANKS, new ItemStack(ModBlocks.PALE_OAK_CHAIR.get()));
        chairRecipe(output, Items.CRIMSON_PLANKS, new ItemStack(ModBlocks.CRIMSON_CHAIR.get()));
        chairRecipe(output, Items.WARPED_PLANKS, new ItemStack(ModBlocks.WARPED_CHAIR.get()));

        shelfRecipe(output, Blocks.STRIPPED_OAK_LOG, ModBlocks.OAK_SHELF.get());

        barsRecipe(output, Items.GOLD_INGOT, ModBlocks.GOLD_BARS.get());
        barsRecipe(output, Items.EMERALD, ModBlocks.EMERALD_BARS.get());
        barsRecipe(output, Items.DIAMOND, ModBlocks.DIAMOND_BARS.get());
        barsRecipe(output, Items.NETHERITE_INGOT, ModBlocks.NETHERITE_BARS.get());
        barsRecipe(output, Items.LAPIS_LAZULI, ModBlocks.LAPIS_BARS.get());
        barsRecipe(output, ModItems.TIN_INGOT.get(), ModBlocks.TIN_BARS.get());
        barsRecipe(output, ModItems.BRONZE_INGOT.get(), ModBlocks.BRONZE_BARS.get());

        cFShaped(Items.CAKE)
                .pattern("MMM")
                .pattern("SES")
                .pattern("WWW")
                .define('M', Items.MILK_BUCKET)
                .define('S', Items.SUGAR)
                .define('E', ItemTags.EGGS)
                .define('W', Items.WHEAT)
                .experience(2.7f)
                .cookingTime(400)
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT)).save(output);

        cFShaped(ModBlocks.WATER_BOILER.get())
                .pattern("IWI")
                .pattern("GRG")
                .pattern("RBR")
                .define('I', Items.IRON_INGOT)
                .define('W', Items.WATER_BUCKET)
                .define('G', Tags.Items.GLASS_PANES)
                .define('R', Items.REDSTONE)
                .define('B', ModItems.BRONZE_INGOT.get())
                .experience(4.5f)
                .cookingTime(500)
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE)).save(output);

        cFShapeless(ModItems.BRONZE_INGOT, 8)
                .requires(Items.COPPER_INGOT, 8)
                .requires(ModItems.TIN_INGOT.get())
                .experience(3.5f)
                .cookingTime(500)
                .unlockedBy(getHasName(ModItems.TIN_INGOT.get()), has(ModItems.TIN_INGOT.get())).save(output);

        wBRecipe(Ingredient.of(ModItems.RICE_SEEDS.get()), new ItemStack(ModItems.RICE.get()))
                .experience(0.4f)
                .fluidRequirement(Fluids.WATER, 10)
                .unlockedBy(getHasName(ModItems.RICE_SEEDS.get()), has(ModItems.RICE_SEEDS.get())).save(output);

        wBRecipe(Ingredient.of(Items.COAL, Items.CHARCOAL), new ItemStack(ModItems.SOUL_COAL.get()))
                .experience(1.4f)
                .fluidRequirement(ModFluids.SOURCE_SOUL_FLUID.get(), 250)
                .unlockedBy(getHasName(ModFluids.SOUL_FLUID_BUCKET.get()), has(ModFluids.SOUL_FLUID_BUCKET.get())).save(output);

        soulMoldFillingRecipe(output, ModItems.AXE_MOLD.get(), Items.EMERALD, 3, MoldFillerTypes.EMERALD);
        soulMoldFillingRecipe(output, ModItems.AXE_MOLD.get(), Items.DIAMOND, 3, MoldFillerTypes.DIAMOND);
        moldFillingRecipe(output, ModItems.AXE_MOLD.get(), ModItems.BRONZE_INGOT.get(), 3, MoldFillerTypes.BRONZE);
        moldFillingRecipe(output, ModItems.AXE_MOLD.get(), Items.COPPER_INGOT, 3, MoldFillerTypes.COPPER);
        moldFillingRecipe(output, ModItems.AXE_MOLD.get(), Items.IRON_INGOT, 3, MoldFillerTypes.IRON);
        moldFillingRecipe(output, ModItems.AXE_MOLD.get(), Items.GOLD_INGOT, 3, MoldFillerTypes.GOLD);

        soulMoldFillingRecipe(output, ModItems.PICKAXE_MOLD.get(), Items.EMERALD, 3, MoldFillerTypes.EMERALD);
        soulMoldFillingRecipe(output, ModItems.PICKAXE_MOLD.get(), Items.DIAMOND, 3, MoldFillerTypes.DIAMOND);
        moldFillingRecipe(output, ModItems.PICKAXE_MOLD.get(), ModItems.BRONZE_INGOT.get(), 3, MoldFillerTypes.BRONZE);
        moldFillingRecipe(output, ModItems.PICKAXE_MOLD.get(), Items.COPPER_INGOT, 3, MoldFillerTypes.COPPER);
        moldFillingRecipe(output, ModItems.PICKAXE_MOLD.get(), Items.IRON_INGOT, 3, MoldFillerTypes.IRON);
        moldFillingRecipe(output, ModItems.PICKAXE_MOLD.get(), Items.GOLD_INGOT, 3, MoldFillerTypes.GOLD);

        soulMoldFillingRecipe(output, ModItems.SHOVEL_MOLD.get(), Items.EMERALD, 1, MoldFillerTypes.EMERALD);
        soulMoldFillingRecipe(output, ModItems.SHOVEL_MOLD.get(), Items.DIAMOND, 1, MoldFillerTypes.DIAMOND);
        moldFillingRecipe(output, ModItems.SHOVEL_MOLD.get(), ModItems.BRONZE_INGOT.get(), 1, MoldFillerTypes.BRONZE);
        moldFillingRecipe(output, ModItems.SHOVEL_MOLD.get(), Items.COPPER_INGOT, 1, MoldFillerTypes.COPPER);
        moldFillingRecipe(output, ModItems.SHOVEL_MOLD.get(), Items.IRON_INGOT, 1, MoldFillerTypes.IRON);
        moldFillingRecipe(output, ModItems.SHOVEL_MOLD.get(), Items.GOLD_INGOT, 1, MoldFillerTypes.GOLD);

        soulMoldFillingRecipe(output, ModItems.SWORD_MOLD.get(), Items.EMERALD, 2, MoldFillerTypes.EMERALD);
        soulMoldFillingRecipe(output, ModItems.SWORD_MOLD.get(), Items.DIAMOND, 2, MoldFillerTypes.DIAMOND);
        moldFillingRecipe(output, ModItems.SWORD_MOLD.get(), ModItems.BRONZE_INGOT.get(), 2, MoldFillerTypes.BRONZE);
        moldFillingRecipe(output, ModItems.SWORD_MOLD.get(), Items.COPPER_INGOT, 2, MoldFillerTypes.COPPER);
        moldFillingRecipe(output, ModItems.SWORD_MOLD.get(), Items.IRON_INGOT, 2, MoldFillerTypes.IRON);
        moldFillingRecipe(output, ModItems.SWORD_MOLD.get(), Items.GOLD_INGOT, 2, MoldFillerTypes.GOLD);

        soulMoldFillingRecipe(output, ModItems.HOE_MOLD.get(), Items.EMERALD, 2, MoldFillerTypes.EMERALD);
        soulMoldFillingRecipe(output, ModItems.HOE_MOLD.get(), Items.DIAMOND, 2, MoldFillerTypes.DIAMOND);
        moldFillingRecipe(output, ModItems.HOE_MOLD.get(), ModItems.BRONZE_INGOT.get(), 2, MoldFillerTypes.BRONZE);
        moldFillingRecipe(output, ModItems.HOE_MOLD.get(), Items.COPPER_INGOT, 2, MoldFillerTypes.COPPER);
        moldFillingRecipe(output, ModItems.HOE_MOLD.get(), Items.IRON_INGOT, 2, MoldFillerTypes.IRON);
        moldFillingRecipe(output, ModItems.HOE_MOLD.get(), Items.GOLD_INGOT, 2, MoldFillerTypes.GOLD);

        soulMoldFillingRecipe(output, ModItems.HELMET_MOLD.get(), Items.EMERALD, 5, MoldFillerTypes.EMERALD);
        soulMoldFillingRecipe(output, ModItems.HELMET_MOLD.get(), Items.DIAMOND, 5, MoldFillerTypes.DIAMOND);
        moldFillingRecipe(output, ModItems.HELMET_MOLD.get(), ModItems.BRONZE_INGOT.get(), 5, MoldFillerTypes.BRONZE);
        moldFillingRecipe(output, ModItems.HELMET_MOLD.get(), Items.COPPER_INGOT, 5, MoldFillerTypes.COPPER);
        moldFillingRecipe(output, ModItems.HELMET_MOLD.get(), Items.IRON_INGOT, 5, MoldFillerTypes.IRON);
        moldFillingRecipe(output, ModItems.HELMET_MOLD.get(), Items.GOLD_INGOT, 5, MoldFillerTypes.GOLD);

        soulMoldFillingRecipe(output, ModItems.CHESTPLATE_MOLD.get(), Items.EMERALD, 8, MoldFillerTypes.EMERALD);
        soulMoldFillingRecipe(output, ModItems.CHESTPLATE_MOLD.get(), Items.DIAMOND, 8, MoldFillerTypes.DIAMOND);
        moldFillingRecipe(output, ModItems.CHESTPLATE_MOLD.get(), ModItems.BRONZE_INGOT.get(), 8, MoldFillerTypes.BRONZE);
        moldFillingRecipe(output, ModItems.CHESTPLATE_MOLD.get(), Items.COPPER_INGOT, 8, MoldFillerTypes.COPPER);
        moldFillingRecipe(output, ModItems.CHESTPLATE_MOLD.get(), Items.IRON_INGOT, 8, MoldFillerTypes.IRON);
        moldFillingRecipe(output, ModItems.CHESTPLATE_MOLD.get(), Items.GOLD_INGOT, 8, MoldFillerTypes.GOLD);

        soulMoldFillingRecipe(output, ModItems.LEGGINGS_MOLD.get(), Items.EMERALD, 7, MoldFillerTypes.EMERALD);
        soulMoldFillingRecipe(output, ModItems.LEGGINGS_MOLD.get(), Items.DIAMOND, 7, MoldFillerTypes.DIAMOND);
        moldFillingRecipe(output, ModItems.LEGGINGS_MOLD.get(), ModItems.BRONZE_INGOT.get(), 7, MoldFillerTypes.BRONZE);
        moldFillingRecipe(output, ModItems.LEGGINGS_MOLD.get(), Items.COPPER_INGOT, 7, MoldFillerTypes.COPPER);
        moldFillingRecipe(output, ModItems.LEGGINGS_MOLD.get(), Items.IRON_INGOT, 7, MoldFillerTypes.IRON);
        moldFillingRecipe(output, ModItems.LEGGINGS_MOLD.get(), Items.GOLD_INGOT, 7, MoldFillerTypes.GOLD);

        soulMoldFillingRecipe(output, ModItems.BOOTS_MOLD.get(), Items.EMERALD, 4, MoldFillerTypes.EMERALD);
        soulMoldFillingRecipe(output, ModItems.BOOTS_MOLD.get(), Items.DIAMOND, 4, MoldFillerTypes.DIAMOND);
        moldFillingRecipe(output, ModItems.BOOTS_MOLD.get(), ModItems.BRONZE_INGOT.get(), 4, MoldFillerTypes.BRONZE);
        moldFillingRecipe(output, ModItems.BOOTS_MOLD.get(), Items.COPPER_INGOT, 4, MoldFillerTypes.COPPER);
        moldFillingRecipe(output, ModItems.BOOTS_MOLD.get(), Items.IRON_INGOT, 4, MoldFillerTypes.IRON);
        moldFillingRecipe(output, ModItems.BOOTS_MOLD.get(), Items.GOLD_INGOT, 4, MoldFillerTypes.GOLD);

        sawMoldFillingRecipe(output, ModItems.BRONZE_INGOT.get(), MoldFillerTypes.BRONZE);
        sawMoldFillingRecipe(output, Items.COPPER_INGOT, MoldFillerTypes.COPPER);
        soulSawMoldFillingRecipe(output, Items.DIAMOND, MoldFillerTypes.DIAMOND);
        soulSawMoldFillingRecipe(output, Items.EMERALD, MoldFillerTypes.EMERALD);
        sawMoldFillingRecipe(output, Items.GOLD_INGOT, MoldFillerTypes.GOLD);
        sawMoldFillingRecipe(output, Items.IRON_INGOT, MoldFillerTypes.IRON);

        moldFillingRecipe(output, ModItems.SHEARS_MOLD, Items.IRON_INGOT, 2, MoldFillerTypes.IRON);
        moldFillingRecipe(output, ModItems.SICKLE_MOLD, Items.IRON_INGOT, 3, MoldFillerTypes.IRON);

        netheriteSmithing(ModItems.DIAMOND_SAW.get(), RecipeCategory.COMBAT, ModItems.NETHERITE_SAW.get());
        netheriteSmithing(Items.DIAMOND_HORSE_ARMOR, RecipeCategory.COMBAT, ModItems.NETHERITE_HORSE_ARMOR.get());

        woodCutterRecipe(ItemTags.LOGS, Items.STICK, 8, output, "_from_log");
        woodCutterRecipe(ItemTags.LOGS, Items.LADDER, 4, output, "_from_log");
        woodCutterRecipe(ItemTags.LOGS, Items.CRAFTING_TABLE, 1, output, "_from_log");
        woodCutterRecipe(ItemTags.LOGS, Items.WOODEN_AXE, 1, output, "_from_log");
        woodCutterRecipe(ItemTags.LOGS, Items.WOODEN_PICKAXE, 1, output, "_from_log");
        woodCutterRecipe(ItemTags.LOGS, Items.WOODEN_SWORD, 1, output, "_from_log");
        woodCutterRecipe(ItemTags.LOGS, Items.WOODEN_HOE, 1, output, "_from_log");
        woodCutterRecipe(ItemTags.LOGS, Items.WOODEN_SHOVEL, 1, output, "_from_log");
        woodCutterRecipe(ItemTags.LOGS, Items.BOWL, 6, output, "_from_log");
        woodCutterRecipe(ItemTags.LOGS, 2, Items.CHEST, 1, output, "_from_log");
        woodCutterRecipe(ItemTags.LOGS, 2, Items.CHISELED_BOOKSHELF, 1, output, "_from_log");

        woodCutterRecipe(ItemTags.PLANKS, Items.STICK, 2, output);
        woodCutterRecipe(ItemTags.PLANKS, Items.LADDER, 1, output);
        woodCutterRecipe(ItemTags.PLANKS, 4, Items.CRAFTING_TABLE, 1, output);
        woodCutterRecipe(ItemTags.PLANKS, 4, Items.WOODEN_AXE, 1, output);
        woodCutterRecipe(ItemTags.PLANKS, 4, Items.WOODEN_PICKAXE, 1, output);
        woodCutterRecipe(ItemTags.PLANKS, 4, Items.WOODEN_SWORD, 1, output);
        woodCutterRecipe(ItemTags.PLANKS, 4, Items.WOODEN_HOE, 1, output);
        woodCutterRecipe(ItemTags.PLANKS, 4, Items.WOODEN_SHOVEL, 1, output);
        woodCutterRecipe(ItemTags.PLANKS, 4, Items.BOWL, 6, output);
        woodCutterRecipe(ItemTags.PLANKS, 8, Items.CHEST, 1, output);
        woodCutterRecipe(ItemTags.PLANKS, 8, Items.CHISELED_BOOKSHELF, 1, output);

        woodCutterRecipe(ItemTags.WOODEN_SLABS, Items.STICK, 1, output, "from_slab");

        woodCutterRecipe(ItemTags.OAK_LOGS, Items.OAK_DOOR, 2, output, "_from_log");
        woodCutterRecipe(ItemTags.OAK_LOGS, Items.OAK_PLANKS, 4, output, "_from_log");
        woodCutterRecipe(ItemTags.OAK_LOGS, Items.OAK_BUTTON, 6, output, "_from_log");
        woodCutterRecipe(ItemTags.OAK_LOGS, Items.OAK_FENCE_GATE, 1, output, "_from_log");
        woodCutterRecipe(ItemTags.OAK_LOGS, Items.OAK_PRESSURE_PLATE, 6, output, "_from_log");
        woodCutterRecipe(ItemTags.OAK_LOGS, Items.OAK_SLAB, 8, output, "_from_log");
        woodCutterRecipe(ItemTags.OAK_LOGS, Items.OAK_STAIRS, 4, output, "_from_log");
        woodCutterRecipe(ItemTags.OAK_LOGS, Items.OAK_TRAPDOOR, 2, output, "_from_log");
        woodCutterRecipe(ItemTags.OAK_LOGS, ModBlocks.OAK_CHAIR.get(), 1, output, "_from_log");
        woodCutterRecipe(ItemTags.OAK_LOGS, ModBlocks.OAK_MOSAIC.get(), 4, output, "_from_log");
        woodCutterRecipe(ItemTags.OAK_LOGS, ModBlocks.OAK_MOSAIC_SLAB.get(), 8, output, "_from_log");
        woodCutterRecipe(ItemTags.OAK_LOGS, ModBlocks.OAK_MOSAIC_STAIRS.get(), 4, output, "_from_log");
        woodCutterRecipe(Items.OAK_LOG, 3, Items.OAK_WOOD, 2, output);
        woodCutterRecipe(Items.STRIPPED_OAK_LOG, 3, Items.STRIPPED_OAK_WOOD, 2, output);
        woodCutterRecipe(Items.STRIPPED_OAK_LOG, ModBlocks.OAK_SHELF.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.OAK_PLANKS, 2, Items.OAK_DOOR, 1, output);
        woodCutterRecipe(Items.OAK_PLANKS, Items.OAK_BUTTON, 1, output);
        woodCutterRecipe(Items.OAK_PLANKS, 4, Items.OAK_FENCE_GATE, 1, output);
        woodCutterRecipe(Items.OAK_PLANKS, Items.OAK_PRESSURE_PLATE, 1, output);
        woodCutterRecipe(Items.OAK_PLANKS, Items.OAK_SLAB, 2, output);
        woodCutterRecipe(Items.OAK_PLANKS, Items.OAK_STAIRS, 1, output);
        woodCutterRecipe(Items.OAK_PLANKS, 2, Items.OAK_TRAPDOOR, 1, output);
        woodCutterRecipe(Items.OAK_PLANKS, 4, ModBlocks.OAK_CHAIR.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.OAK_PLANKS, ModBlocks.OAK_MOSAIC.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.OAK_PLANKS, ModBlocks.OAK_MOSAIC_SLAB.get(), 2, output, "_from_woodcutter");
        woodCutterRecipe(Items.OAK_PLANKS, ModBlocks.OAK_MOSAIC_STAIRS.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.OAK_PLANKS, 5, Items.OAK_BOAT, 1, output);
        woodCutterRecipe(Items.OAK_PLANKS, 13, Items.OAK_CHEST_BOAT, 1, output);
        woodCutterRecipe(Items.OAK_PLANKS, 3, ModBlocks.OAK_TABLE.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.OAK_PLANKS, 5, Items.OAK_FENCE, 3, output);

        woodCutterRecipe(ItemTags.BIRCH_LOGS, Items.BIRCH_DOOR, 2, output, "_from_log");
        woodCutterRecipe(ItemTags.BIRCH_LOGS, Items.BIRCH_PLANKS, 4, output, "_from_log");
        woodCutterRecipe(ItemTags.BIRCH_LOGS, Items.BIRCH_BUTTON, 6, output, "_from_log");
        woodCutterRecipe(ItemTags.BIRCH_LOGS, Items.BIRCH_FENCE_GATE, 1, output, "_from_log");
        woodCutterRecipe(ItemTags.BIRCH_LOGS, Items.BIRCH_PRESSURE_PLATE, 6, output, "_from_log");
        woodCutterRecipe(ItemTags.BIRCH_LOGS, Items.BIRCH_SLAB, 8, output, "_from_log");
        woodCutterRecipe(ItemTags.BIRCH_LOGS, Items.BIRCH_STAIRS, 4, output, "_from_log");
        woodCutterRecipe(ItemTags.BIRCH_LOGS, Items.BIRCH_TRAPDOOR, 2, output, "_from_log");
        woodCutterRecipe(ItemTags.BIRCH_LOGS, ModBlocks.BIRCH_CHAIR.get(), 1, output, "_from_log");
        woodCutterRecipe(ItemTags.BIRCH_LOGS, ModBlocks.BIRCH_MOSAIC.get(), 4, output, "_from_log");
        woodCutterRecipe(ItemTags.BIRCH_LOGS, ModBlocks.BIRCH_MOSAIC_SLAB.get(), 8, output, "_from_log");
        woodCutterRecipe(ItemTags.BIRCH_LOGS, ModBlocks.BIRCH_MOSAIC_STAIRS.get(), 4, output, "_from_log");
        woodCutterRecipe(Items.BIRCH_LOG, 3, Items.BIRCH_WOOD, 2, output);
        woodCutterRecipe(Items.STRIPPED_BIRCH_LOG, 3, Items.STRIPPED_BIRCH_WOOD, 2, output);
        woodCutterRecipe(Items.BIRCH_PLANKS, 2, Items.BIRCH_DOOR, 1, output);
        woodCutterRecipe(Items.BIRCH_PLANKS, Items.BIRCH_BUTTON, 1, output);
        woodCutterRecipe(Items.BIRCH_PLANKS, 4, Items.BIRCH_FENCE_GATE, 1, output);
        woodCutterRecipe(Items.BIRCH_PLANKS, Items.BIRCH_PRESSURE_PLATE, 1, output);
        woodCutterRecipe(Items.BIRCH_PLANKS, Items.BIRCH_SLAB, 2, output);
        woodCutterRecipe(Items.BIRCH_PLANKS, Items.BIRCH_STAIRS, 1, output);
        woodCutterRecipe(Items.BIRCH_PLANKS, 2, Items.BIRCH_TRAPDOOR, 1, output);
        woodCutterRecipe(Items.BIRCH_PLANKS, 4, ModBlocks.BIRCH_CHAIR.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.BIRCH_PLANKS, ModBlocks.BIRCH_MOSAIC.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.BIRCH_PLANKS, ModBlocks.BIRCH_MOSAIC_SLAB.get(), 2, output, "_from_woodcutter");
        woodCutterRecipe(Items.BIRCH_PLANKS, ModBlocks.BIRCH_MOSAIC_STAIRS.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.BIRCH_PLANKS, 5, Items.BIRCH_BOAT, 1, output);
        woodCutterRecipe(Items.BIRCH_PLANKS, 13, Items.BIRCH_CHEST_BOAT, 1, output);
        woodCutterRecipe(Items.BIRCH_PLANKS, 3, ModBlocks.BIRCH_TABLE.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.BIRCH_PLANKS, 5, Items.BIRCH_FENCE, 3, output);

        woodCutterRecipe(ItemTags.SPRUCE_LOGS, Items.SPRUCE_DOOR, 2, output, "_from_log");
        woodCutterRecipe(ItemTags.SPRUCE_LOGS, Items.SPRUCE_PLANKS, 4, output, "_from_log");
        woodCutterRecipe(ItemTags.SPRUCE_LOGS, Items.SPRUCE_BUTTON, 6, output, "_from_log");
        woodCutterRecipe(ItemTags.SPRUCE_LOGS, Items.SPRUCE_FENCE_GATE, 1, output, "_from_log");
        woodCutterRecipe(ItemTags.SPRUCE_LOGS, Items.SPRUCE_PRESSURE_PLATE, 6, output, "_from_log");
        woodCutterRecipe(ItemTags.SPRUCE_LOGS, Items.SPRUCE_SLAB, 8, output, "_from_log");
        woodCutterRecipe(ItemTags.SPRUCE_LOGS, Items.SPRUCE_STAIRS, 4, output, "_from_log");
        woodCutterRecipe(ItemTags.SPRUCE_LOGS, Items.SPRUCE_TRAPDOOR, 2, output, "_from_log");
        woodCutterRecipe(ItemTags.SPRUCE_LOGS, ModBlocks.SPRUCE_CHAIR.get(), 1, output, "_from_log");
        woodCutterRecipe(ItemTags.SPRUCE_LOGS, ModBlocks.SPRUCE_MOSAIC.get(), 4, output, "_from_log");
        woodCutterRecipe(ItemTags.SPRUCE_LOGS, ModBlocks.SPRUCE_MOSAIC_SLAB.get(), 8, output, "_from_log");
        woodCutterRecipe(ItemTags.SPRUCE_LOGS, ModBlocks.SPRUCE_MOSAIC_STAIRS.get(), 4, output, "_from_log");
        woodCutterRecipe(Items.SPRUCE_LOG, 3, Items.SPRUCE_WOOD, 2, output);
        woodCutterRecipe(Items.STRIPPED_SPRUCE_LOG, 3, Items.STRIPPED_SPRUCE_WOOD, 2, output);
        woodCutterRecipe(Items.SPRUCE_PLANKS, 2, Items.SPRUCE_DOOR, 1, output);
        woodCutterRecipe(Items.SPRUCE_PLANKS, Items.SPRUCE_BUTTON, 1, output);
        woodCutterRecipe(Items.SPRUCE_PLANKS, 4, Items.SPRUCE_FENCE_GATE, 1, output);
        woodCutterRecipe(Items.SPRUCE_PLANKS, Items.SPRUCE_PRESSURE_PLATE, 1, output);
        woodCutterRecipe(Items.SPRUCE_PLANKS, Items.SPRUCE_SLAB, 2, output);
        woodCutterRecipe(Items.SPRUCE_PLANKS, Items.SPRUCE_STAIRS, 1, output);
        woodCutterRecipe(Items.SPRUCE_PLANKS, 2, Items.SPRUCE_TRAPDOOR, 1, output);
        woodCutterRecipe(Items.SPRUCE_PLANKS, 4, ModBlocks.SPRUCE_CHAIR.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.SPRUCE_PLANKS, ModBlocks.SPRUCE_MOSAIC.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.SPRUCE_PLANKS, ModBlocks.SPRUCE_MOSAIC_SLAB.get(), 2, output, "_from_woodcutter");
        woodCutterRecipe(Items.SPRUCE_PLANKS, ModBlocks.SPRUCE_MOSAIC_STAIRS.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.SPRUCE_PLANKS, 5, Items.SPRUCE_BOAT, 1, output);
        woodCutterRecipe(Items.SPRUCE_PLANKS, 13, Items.SPRUCE_CHEST_BOAT, 1, output);
        woodCutterRecipe(Items.SPRUCE_PLANKS, 3, ModBlocks.SPRUCE_TABLE.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.SPRUCE_PLANKS, 5, Items.SPRUCE_FENCE, 3, output);

        woodCutterRecipe(ItemTags.ACACIA_LOGS, Items.ACACIA_DOOR, 2, output, "_from_log");
        woodCutterRecipe(ItemTags.ACACIA_LOGS, Items.ACACIA_PLANKS, 4, output, "_from_log");
        woodCutterRecipe(ItemTags.ACACIA_LOGS, Items.ACACIA_BUTTON, 6, output, "_from_log");
        woodCutterRecipe(ItemTags.ACACIA_LOGS, Items.ACACIA_FENCE_GATE, 1, output, "_from_log");
        woodCutterRecipe(ItemTags.ACACIA_LOGS, Items.ACACIA_PRESSURE_PLATE, 6, output, "_from_log");
        woodCutterRecipe(ItemTags.ACACIA_LOGS, Items.ACACIA_SLAB, 8, output, "_from_log");
        woodCutterRecipe(ItemTags.ACACIA_LOGS, Items.ACACIA_STAIRS, 4, output, "_from_log");
        woodCutterRecipe(ItemTags.ACACIA_LOGS, Items.ACACIA_TRAPDOOR, 2, output, "_from_log");
        woodCutterRecipe(ItemTags.ACACIA_LOGS, ModBlocks.ACACIA_CHAIR.get(), 1, output, "_from_log");
        woodCutterRecipe(ItemTags.ACACIA_LOGS, ModBlocks.ACACIA_MOSAIC.get(), 4, output, "_from_log");
        woodCutterRecipe(ItemTags.ACACIA_LOGS, ModBlocks.ACACIA_MOSAIC_SLAB.get(), 8, output, "_from_log");
        woodCutterRecipe(ItemTags.ACACIA_LOGS, ModBlocks.ACACIA_MOSAIC_STAIRS.get(), 4, output, "_from_log");
        woodCutterRecipe(Items.ACACIA_LOG, 3, Items.ACACIA_WOOD, 2, output);
        woodCutterRecipe(Items.STRIPPED_ACACIA_LOG, 3, Items.STRIPPED_ACACIA_WOOD, 2, output);
        woodCutterRecipe(Items.ACACIA_PLANKS, 2, Items.ACACIA_DOOR, 1, output);
        woodCutterRecipe(Items.ACACIA_PLANKS, Items.ACACIA_BUTTON, 1, output);
        woodCutterRecipe(Items.ACACIA_PLANKS, 4, Items.ACACIA_FENCE_GATE, 1, output);
        woodCutterRecipe(Items.ACACIA_PLANKS, Items.ACACIA_PRESSURE_PLATE, 1, output);
        woodCutterRecipe(Items.ACACIA_PLANKS, Items.ACACIA_SLAB, 2, output);
        woodCutterRecipe(Items.ACACIA_PLANKS, Items.ACACIA_STAIRS, 1, output);
        woodCutterRecipe(Items.ACACIA_PLANKS, 2, Items.ACACIA_TRAPDOOR, 1, output);
        woodCutterRecipe(Items.ACACIA_PLANKS, 4, ModBlocks.ACACIA_CHAIR.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.ACACIA_PLANKS, ModBlocks.ACACIA_MOSAIC.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.ACACIA_PLANKS, ModBlocks.ACACIA_MOSAIC_SLAB.get(), 2, output, "_from_woodcutter");
        woodCutterRecipe(Items.ACACIA_PLANKS, ModBlocks.ACACIA_MOSAIC_STAIRS.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.ACACIA_PLANKS, 5, Items.ACACIA_BOAT, 1, output);
        woodCutterRecipe(Items.ACACIA_PLANKS, 13, Items.ACACIA_CHEST_BOAT, 1, output);
        woodCutterRecipe(Items.ACACIA_PLANKS, 3, ModBlocks.ACACIA_TABLE.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.ACACIA_PLANKS, 5, Items.ACACIA_FENCE, 3, output);

        woodCutterRecipe(ItemTags.JUNGLE_LOGS, Items.JUNGLE_DOOR, 2, output, "_from_log");
        woodCutterRecipe(ItemTags.JUNGLE_LOGS, Items.JUNGLE_PLANKS, 4, output, "_from_log");
        woodCutterRecipe(ItemTags.JUNGLE_LOGS, Items.JUNGLE_BUTTON, 6, output, "_from_log");
        woodCutterRecipe(ItemTags.JUNGLE_LOGS, Items.JUNGLE_FENCE_GATE, 1, output, "_from_log");
        woodCutterRecipe(ItemTags.JUNGLE_LOGS, Items.JUNGLE_PRESSURE_PLATE, 6, output, "_from_log");
        woodCutterRecipe(ItemTags.JUNGLE_LOGS, Items.JUNGLE_SLAB, 8, output, "_from_log");
        woodCutterRecipe(ItemTags.JUNGLE_LOGS, Items.JUNGLE_STAIRS, 4, output, "_from_log");
        woodCutterRecipe(ItemTags.JUNGLE_LOGS, Items.JUNGLE_TRAPDOOR, 2, output, "_from_log");
        woodCutterRecipe(ItemTags.JUNGLE_LOGS, ModBlocks.JUNGLE_CHAIR.get(), 1, output, "_from_log");
        woodCutterRecipe(ItemTags.JUNGLE_LOGS, ModBlocks.JUNGLE_MOSAIC.get(), 4, output, "_from_log");
        woodCutterRecipe(ItemTags.JUNGLE_LOGS, ModBlocks.JUNGLE_MOSAIC_SLAB.get(), 8, output, "_from_log");
        woodCutterRecipe(ItemTags.JUNGLE_LOGS, ModBlocks.JUNGLE_MOSAIC_STAIRS.get(), 4, output, "_from_log");
        woodCutterRecipe(Items.JUNGLE_LOG, 3, Items.JUNGLE_WOOD, 2, output);
        woodCutterRecipe(Items.STRIPPED_JUNGLE_LOG, 3, Items.STRIPPED_JUNGLE_WOOD, 2, output);
        woodCutterRecipe(Items.JUNGLE_PLANKS, 2, Items.JUNGLE_DOOR, 1, output);
        woodCutterRecipe(Items.JUNGLE_PLANKS, Items.JUNGLE_BUTTON, 1, output);
        woodCutterRecipe(Items.JUNGLE_PLANKS, 4, Items.JUNGLE_FENCE_GATE, 1, output);
        woodCutterRecipe(Items.JUNGLE_PLANKS, Items.JUNGLE_PRESSURE_PLATE, 1, output);
        woodCutterRecipe(Items.JUNGLE_PLANKS, Items.JUNGLE_SLAB, 2, output);
        woodCutterRecipe(Items.JUNGLE_PLANKS, Items.JUNGLE_STAIRS, 1, output);
        woodCutterRecipe(Items.JUNGLE_PLANKS, 2, Items.JUNGLE_TRAPDOOR, 1, output);
        woodCutterRecipe(Items.JUNGLE_PLANKS, 4, ModBlocks.JUNGLE_CHAIR.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.JUNGLE_PLANKS, ModBlocks.JUNGLE_MOSAIC.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.JUNGLE_PLANKS, ModBlocks.JUNGLE_MOSAIC_SLAB.get(), 2, output, "_from_woodcutter");
        woodCutterRecipe(Items.JUNGLE_PLANKS, ModBlocks.JUNGLE_MOSAIC_STAIRS.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.JUNGLE_PLANKS, 5, Items.JUNGLE_BOAT, 1, output);
        woodCutterRecipe(Items.JUNGLE_PLANKS, 13, Items.JUNGLE_CHEST_BOAT, 1, output);
        woodCutterRecipe(Items.JUNGLE_PLANKS, 3, ModBlocks.JUNGLE_TABLE.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.JUNGLE_PLANKS, 5, Items.JUNGLE_FENCE, 3, output);

        woodCutterRecipe(ItemTags.DARK_OAK_LOGS, Items.DARK_OAK_DOOR, 2, output, "_from_log");
        woodCutterRecipe(ItemTags.DARK_OAK_LOGS, Items.DARK_OAK_PLANKS, 4, output, "_from_log");
        woodCutterRecipe(ItemTags.DARK_OAK_LOGS, Items.DARK_OAK_BUTTON, 6, output, "_from_log");
        woodCutterRecipe(ItemTags.DARK_OAK_LOGS, Items.DARK_OAK_FENCE_GATE, 1, output, "_from_log");
        woodCutterRecipe(ItemTags.DARK_OAK_LOGS, Items.DARK_OAK_PRESSURE_PLATE, 6, output, "_from_log");
        woodCutterRecipe(ItemTags.DARK_OAK_LOGS, Items.DARK_OAK_SLAB, 8, output, "_from_log");
        woodCutterRecipe(ItemTags.DARK_OAK_LOGS, Items.DARK_OAK_STAIRS, 4, output, "_from_log");
        woodCutterRecipe(ItemTags.DARK_OAK_LOGS, Items.DARK_OAK_TRAPDOOR, 2, output, "_from_log");
        woodCutterRecipe(ItemTags.DARK_OAK_LOGS, ModBlocks.DARK_OAK_CHAIR.get(), 1, output, "_from_log");
        woodCutterRecipe(ItemTags.DARK_OAK_LOGS, ModBlocks.DARK_OAK_MOSAIC.get(), 4, output, "_from_log");
        woodCutterRecipe(ItemTags.DARK_OAK_LOGS, ModBlocks.DARK_OAK_MOSAIC_SLAB.get(), 8, output, "_from_log");
        woodCutterRecipe(ItemTags.DARK_OAK_LOGS, ModBlocks.DARK_OAK_MOSAIC_STAIRS.get(), 4, output, "_from_log");
        woodCutterRecipe(Items.DARK_OAK_LOG, 3, Items.DARK_OAK_WOOD, 2, output);
        woodCutterRecipe(Items.STRIPPED_DARK_OAK_LOG, 3, Items.STRIPPED_DARK_OAK_WOOD, 2, output);
        woodCutterRecipe(Items.DARK_OAK_PLANKS, 2, Items.DARK_OAK_DOOR, 1, output);
        woodCutterRecipe(Items.DARK_OAK_PLANKS, Items.DARK_OAK_BUTTON, 1, output);
        woodCutterRecipe(Items.DARK_OAK_PLANKS, 4, Items.DARK_OAK_FENCE_GATE, 1, output);
        woodCutterRecipe(Items.DARK_OAK_PLANKS, Items.DARK_OAK_PRESSURE_PLATE, 1, output);
        woodCutterRecipe(Items.DARK_OAK_PLANKS, Items.DARK_OAK_SLAB, 2, output);
        woodCutterRecipe(Items.DARK_OAK_PLANKS, Items.DARK_OAK_STAIRS, 1, output);
        woodCutterRecipe(Items.DARK_OAK_PLANKS, 2, Items.DARK_OAK_TRAPDOOR, 1, output);
        woodCutterRecipe(Items.DARK_OAK_PLANKS, 4, ModBlocks.DARK_OAK_CHAIR.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.DARK_OAK_PLANKS, ModBlocks.DARK_OAK_MOSAIC.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.DARK_OAK_PLANKS, ModBlocks.DARK_OAK_MOSAIC_SLAB.get(), 2, output, "_from_woodcutter");
        woodCutterRecipe(Items.DARK_OAK_PLANKS, ModBlocks.DARK_OAK_MOSAIC_STAIRS.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.DARK_OAK_PLANKS, 5, Items.DARK_OAK_BOAT, 1, output);
        woodCutterRecipe(Items.DARK_OAK_PLANKS, 13, Items.DARK_OAK_CHEST_BOAT, 1, output);
        woodCutterRecipe(Items.DARK_OAK_PLANKS, 3, ModBlocks.DARK_OAK_TABLE.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.DARK_OAK_PLANKS, 5, Items.DARK_OAK_FENCE, 3, output);

        woodCutterRecipe(ItemTags.MANGROVE_LOGS, Items.MANGROVE_DOOR, 2, output, "_from_log");
        woodCutterRecipe(ItemTags.MANGROVE_LOGS, Items.MANGROVE_PLANKS, 4, output, "_from_log");
        woodCutterRecipe(ItemTags.MANGROVE_LOGS, Items.MANGROVE_BUTTON, 6, output, "_from_log");
        woodCutterRecipe(ItemTags.MANGROVE_LOGS, Items.MANGROVE_FENCE_GATE, 1, output, "_from_log");
        woodCutterRecipe(ItemTags.MANGROVE_LOGS, Items.MANGROVE_PRESSURE_PLATE, 6, output, "_from_log");
        woodCutterRecipe(ItemTags.MANGROVE_LOGS, Items.MANGROVE_SLAB, 8, output, "_from_log");
        woodCutterRecipe(ItemTags.MANGROVE_LOGS, Items.MANGROVE_STAIRS, 4, output, "_from_log");
        woodCutterRecipe(ItemTags.MANGROVE_LOGS, Items.MANGROVE_TRAPDOOR, 2, output, "_from_log");
        woodCutterRecipe(ItemTags.MANGROVE_LOGS, ModBlocks.MANGROVE_CHAIR.get(), 1, output, "_from_log");
        woodCutterRecipe(ItemTags.MANGROVE_LOGS, ModBlocks.MANGROVE_MOSAIC.get(), 4, output, "_from_log");
        woodCutterRecipe(ItemTags.MANGROVE_LOGS, ModBlocks.MANGROVE_MOSAIC_SLAB.get(), 8, output, "_from_log");
        woodCutterRecipe(ItemTags.MANGROVE_LOGS, ModBlocks.MANGROVE_MOSAIC_STAIRS.get(), 4, output, "_from_log");
        woodCutterRecipe(Items.MANGROVE_LOG, 3, Items.MANGROVE_WOOD, 2, output);
        woodCutterRecipe(Items.STRIPPED_MANGROVE_LOG, 3, Items.STRIPPED_MANGROVE_WOOD, 2, output);
        woodCutterRecipe(Items.MANGROVE_PLANKS, 2, Items.MANGROVE_DOOR, 1, output);
        woodCutterRecipe(Items.MANGROVE_PLANKS, Items.MANGROVE_BUTTON, 1, output);
        woodCutterRecipe(Items.MANGROVE_PLANKS, 4, Items.MANGROVE_FENCE_GATE, 1, output);
        woodCutterRecipe(Items.MANGROVE_PLANKS, Items.MANGROVE_PRESSURE_PLATE, 1, output);
        woodCutterRecipe(Items.MANGROVE_PLANKS, Items.MANGROVE_SLAB, 2, output);
        woodCutterRecipe(Items.MANGROVE_PLANKS, Items.MANGROVE_STAIRS, 1, output);
        woodCutterRecipe(Items.MANGROVE_PLANKS, 2, Items.MANGROVE_TRAPDOOR, 1, output);
        woodCutterRecipe(Items.MANGROVE_PLANKS, 4, ModBlocks.MANGROVE_CHAIR.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.MANGROVE_PLANKS, ModBlocks.MANGROVE_MOSAIC.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.MANGROVE_PLANKS, ModBlocks.MANGROVE_MOSAIC_SLAB.get(), 2, output, "_from_woodcutter");
        woodCutterRecipe(Items.MANGROVE_PLANKS, ModBlocks.MANGROVE_MOSAIC_STAIRS.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.MANGROVE_PLANKS, 5, Items.MANGROVE_BOAT, 1, output);
        woodCutterRecipe(Items.MANGROVE_PLANKS, 13, Items.MANGROVE_CHEST_BOAT, 1, output);
        woodCutterRecipe(Items.MANGROVE_PLANKS, 3, ModBlocks.MANGROVE_TABLE.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.MANGROVE_PLANKS, 5, Items.MANGROVE_FENCE, 3, output);

        woodCutterRecipe(ItemTags.CHERRY_LOGS, Items.CHERRY_DOOR, 2, output, "_from_log");
        woodCutterRecipe(ItemTags.CHERRY_LOGS, Items.CHERRY_PLANKS, 4, output, "_from_log");
        woodCutterRecipe(ItemTags.CHERRY_LOGS, Items.CHERRY_BUTTON, 6, output, "_from_log");
        woodCutterRecipe(ItemTags.CHERRY_LOGS, Items.CHERRY_FENCE_GATE, 1, output, "_from_log");
        woodCutterRecipe(ItemTags.CHERRY_LOGS, Items.CHERRY_PRESSURE_PLATE, 6, output, "_from_log");
        woodCutterRecipe(ItemTags.CHERRY_LOGS, Items.CHERRY_SLAB, 8, output, "_from_log");
        woodCutterRecipe(ItemTags.CHERRY_LOGS, Items.CHERRY_STAIRS, 4, output, "_from_log");
        woodCutterRecipe(ItemTags.CHERRY_LOGS, Items.CHERRY_TRAPDOOR, 2, output, "_from_log");
        woodCutterRecipe(ItemTags.CHERRY_LOGS, ModBlocks.CHERRY_CHAIR.get(), 1, output, "_from_log");
        woodCutterRecipe(ItemTags.CHERRY_LOGS, ModBlocks.CHERRY_MOSAIC.get(), 4, output, "_from_log");
        woodCutterRecipe(ItemTags.CHERRY_LOGS, ModBlocks.CHERRY_MOSAIC_SLAB.get(), 8, output, "_from_log");
        woodCutterRecipe(ItemTags.CHERRY_LOGS, ModBlocks.CHERRY_MOSAIC_STAIRS.get(), 4, output, "_from_log");
        woodCutterRecipe(Items.CHERRY_LOG, 3, Items.CHERRY_WOOD, 2, output);
        woodCutterRecipe(Items.STRIPPED_CHERRY_LOG, 3, Items.STRIPPED_CHERRY_WOOD, 2, output);
        woodCutterRecipe(Items.CHERRY_PLANKS, 2, Items.CHERRY_DOOR, 1, output);
        woodCutterRecipe(Items.CHERRY_PLANKS, Items.CHERRY_BUTTON, 1, output);
        woodCutterRecipe(Items.CHERRY_PLANKS, 4, Items.CHERRY_FENCE_GATE, 1, output);
        woodCutterRecipe(Items.CHERRY_PLANKS, Items.CHERRY_PRESSURE_PLATE, 1, output);
        woodCutterRecipe(Items.CHERRY_PLANKS, Items.CHERRY_SLAB, 2, output);
        woodCutterRecipe(Items.CHERRY_PLANKS, Items.CHERRY_STAIRS, 1, output);
        woodCutterRecipe(Items.CHERRY_PLANKS, 2, Items.CHERRY_TRAPDOOR, 1, output);
        woodCutterRecipe(Items.CHERRY_PLANKS, 4, ModBlocks.CHERRY_CHAIR.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.CHERRY_PLANKS, ModBlocks.CHERRY_MOSAIC.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.CHERRY_PLANKS, ModBlocks.CHERRY_MOSAIC_SLAB.get(), 2, output, "_from_woodcutter");
        woodCutterRecipe(Items.CHERRY_PLANKS, ModBlocks.CHERRY_MOSAIC_STAIRS.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.CHERRY_PLANKS, 5, Items.CHERRY_BOAT, 1, output);
        woodCutterRecipe(Items.CHERRY_PLANKS, 13, Items.CHERRY_CHEST_BOAT, 1, output);
        woodCutterRecipe(Items.CHERRY_PLANKS, 3, ModBlocks.CHERRY_TABLE.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.CHERRY_PLANKS, 5, Items.CHERRY_FENCE, 3, output);

        woodCutterRecipe(ItemTags.BAMBOO_BLOCKS, Items.BAMBOO_DOOR, 2, output, "_from_block");
        woodCutterRecipe(ItemTags.BAMBOO_BLOCKS, Items.BAMBOO_PLANKS, 4, output, "_from_block");
        woodCutterRecipe(ItemTags.BAMBOO_BLOCKS, Items.BAMBOO_BUTTON, 6, output, "_from_block");
        woodCutterRecipe(ItemTags.BAMBOO_BLOCKS, Items.BAMBOO_FENCE_GATE, 1, output, "_from_block");
        woodCutterRecipe(ItemTags.BAMBOO_BLOCKS, Items.BAMBOO_PRESSURE_PLATE, 6, output, "_from_block");
        woodCutterRecipe(ItemTags.BAMBOO_BLOCKS, Items.BAMBOO_SLAB, 8, output, "_from_block");
        woodCutterRecipe(ItemTags.BAMBOO_BLOCKS, Items.BAMBOO_STAIRS, 4, output, "_from_block");
        woodCutterRecipe(ItemTags.BAMBOO_BLOCKS, Items.BAMBOO_TRAPDOOR, 2, output, "_from_block");
        woodCutterRecipe(ItemTags.BAMBOO_BLOCKS, ModBlocks.BAMBOO_CHAIR.get(), 1, output, "_from_block");
        woodCutterRecipe(ItemTags.BAMBOO_BLOCKS, Items.BAMBOO_MOSAIC, 4, output, "_from_block");
        woodCutterRecipe(ItemTags.BAMBOO_BLOCKS, Items.BAMBOO_MOSAIC_SLAB, 8, output, "_from_block");
        woodCutterRecipe(ItemTags.BAMBOO_BLOCKS, Items.BAMBOO_MOSAIC_STAIRS, 4, output, "_from_block");
        woodCutterRecipe(Items.BAMBOO_PLANKS, 2, Items.BAMBOO_DOOR, 1, output);
        woodCutterRecipe(Items.BAMBOO_PLANKS, Items.BAMBOO_BUTTON, 1, output);
        woodCutterRecipe(Items.BAMBOO_PLANKS, 4, Items.BAMBOO_FENCE_GATE, 1, output);
        woodCutterRecipe(Items.BAMBOO_PLANKS, Items.BAMBOO_PRESSURE_PLATE, 1, output);
        woodCutterRecipe(Items.BAMBOO_PLANKS, Items.BAMBOO_SLAB, 2, output);
        woodCutterRecipe(Items.BAMBOO_PLANKS, Items.BAMBOO_STAIRS, 1, output);
        woodCutterRecipe(Items.BAMBOO_PLANKS, 2, Items.BAMBOO_TRAPDOOR, 1, output);
        woodCutterRecipe(Items.BAMBOO_PLANKS, 4, ModBlocks.BAMBOO_CHAIR.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.BAMBOO_PLANKS, Items.BAMBOO_MOSAIC, 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.BAMBOO_PLANKS, Items.BAMBOO_MOSAIC_SLAB, 2, output, "_from_woodcutter");
        woodCutterRecipe(Items.BAMBOO_PLANKS, Items.BAMBOO_MOSAIC_STAIRS, 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.BAMBOO_PLANKS, 5, Items.BAMBOO_RAFT, 1, output);
        woodCutterRecipe(Items.BAMBOO_PLANKS, 13, Items.BAMBOO_CHEST_RAFT, 1, output);
        woodCutterRecipe(Items.BAMBOO_PLANKS, 3, ModBlocks.BAMBOO_TABLE.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.BAMBOO_PLANKS, 5, Items.BAMBOO_FENCE, 3, output);

        woodCutterRecipe(ItemTags.PALE_OAK_LOGS, Items.PALE_OAK_DOOR, 2, output, "_from_log");
        woodCutterRecipe(ItemTags.PALE_OAK_LOGS, Items.PALE_OAK_PLANKS, 4, output, "_from_log");
        woodCutterRecipe(ItemTags.PALE_OAK_LOGS, Items.PALE_OAK_BUTTON, 6, output, "_from_log");
        woodCutterRecipe(ItemTags.PALE_OAK_LOGS, Items.PALE_OAK_FENCE_GATE, 1, output, "_from_log");
        woodCutterRecipe(ItemTags.PALE_OAK_LOGS, Items.PALE_OAK_PRESSURE_PLATE, 6, output, "_from_log");
        woodCutterRecipe(ItemTags.PALE_OAK_LOGS, Items.PALE_OAK_SLAB, 8, output, "_from_log");
        woodCutterRecipe(ItemTags.PALE_OAK_LOGS, Items.PALE_OAK_STAIRS, 4, output, "_from_log");
        woodCutterRecipe(ItemTags.PALE_OAK_LOGS, Items.PALE_OAK_TRAPDOOR, 2, output, "_from_log");
        woodCutterRecipe(ItemTags.PALE_OAK_LOGS, ModBlocks.PALE_OAK_CHAIR.get(), 1, output, "_from_log");
        woodCutterRecipe(ItemTags.PALE_OAK_LOGS, ModBlocks.PALE_OAK_MOSAIC.get(), 4, output, "_from_log");
        woodCutterRecipe(ItemTags.PALE_OAK_LOGS, ModBlocks.PALE_OAK_MOSAIC_SLAB.get(), 8, output, "_from_log");
        woodCutterRecipe(ItemTags.PALE_OAK_LOGS, ModBlocks.PALE_OAK_MOSAIC_STAIRS.get(), 4, output, "_from_log");
        woodCutterRecipe(Items.PALE_OAK_LOG, 3, Items.PALE_OAK_WOOD, 2, output);
        woodCutterRecipe(Items.STRIPPED_PALE_OAK_LOG, 3, Items.STRIPPED_PALE_OAK_WOOD, 2, output);
        woodCutterRecipe(Items.PALE_OAK_PLANKS, 2, Items.PALE_OAK_DOOR, 1, output);
        woodCutterRecipe(Items.PALE_OAK_PLANKS, Items.PALE_OAK_BUTTON, 1, output);
        woodCutterRecipe(Items.PALE_OAK_PLANKS, 4, Items.PALE_OAK_FENCE_GATE, 1, output);
        woodCutterRecipe(Items.PALE_OAK_PLANKS, Items.PALE_OAK_PRESSURE_PLATE, 1, output);
        woodCutterRecipe(Items.PALE_OAK_PLANKS, Items.PALE_OAK_SLAB, 2, output);
        woodCutterRecipe(Items.PALE_OAK_PLANKS, Items.PALE_OAK_STAIRS, 1, output);
        woodCutterRecipe(Items.PALE_OAK_PLANKS, 2, Items.PALE_OAK_TRAPDOOR, 1, output);
        woodCutterRecipe(Items.PALE_OAK_PLANKS, 4, ModBlocks.PALE_OAK_CHAIR.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.PALE_OAK_PLANKS, ModBlocks.PALE_OAK_MOSAIC.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.PALE_OAK_PLANKS, ModBlocks.PALE_OAK_MOSAIC_SLAB.get(), 2, output, "_from_woodcutter");
        woodCutterRecipe(Items.PALE_OAK_PLANKS, ModBlocks.PALE_OAK_MOSAIC_STAIRS.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.PALE_OAK_PLANKS, 5, Items.PALE_OAK_BOAT, 1, output);
        woodCutterRecipe(Items.PALE_OAK_PLANKS, 13, Items.PALE_OAK_CHEST_BOAT, 1, output);
        woodCutterRecipe(Items.PALE_OAK_PLANKS, 3, ModBlocks.PALE_OAK_TABLE.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.PALE_OAK_PLANKS, 5, Items.PALE_OAK_FENCE, 3, output);

        woodCutterRecipe(ItemTags.CRIMSON_STEMS, Items.CRIMSON_DOOR, 2, output, "_from_stem");
        woodCutterRecipe(ItemTags.CRIMSON_STEMS, Items.CRIMSON_PLANKS, 4, output, "_from_stem");
        woodCutterRecipe(ItemTags.CRIMSON_STEMS, Items.CRIMSON_BUTTON, 6, output, "_from_stem");
        woodCutterRecipe(ItemTags.CRIMSON_STEMS, Items.CRIMSON_FENCE_GATE, 1, output, "_from_stem");
        woodCutterRecipe(ItemTags.CRIMSON_STEMS, Items.CRIMSON_PRESSURE_PLATE, 6, output, "_from_stem");
        woodCutterRecipe(ItemTags.CRIMSON_STEMS, Items.CRIMSON_SLAB, 8, output, "_from_stem");
        woodCutterRecipe(ItemTags.CRIMSON_STEMS, Items.CRIMSON_STAIRS, 4, output, "_from_stem");
        woodCutterRecipe(ItemTags.CRIMSON_STEMS, Items.CRIMSON_TRAPDOOR, 2, output, "_from_stem");
        woodCutterRecipe(ItemTags.CRIMSON_STEMS, ModBlocks.CRIMSON_CHAIR.get(), 1, output, "_from_stem");
        woodCutterRecipe(ItemTags.CRIMSON_STEMS, ModBlocks.CRIMSON_MOSAIC.get(), 4, output, "_from_stem");
        woodCutterRecipe(ItemTags.CRIMSON_STEMS, ModBlocks.CRIMSON_MOSAIC_SLAB.get(), 8, output, "_from_stem");
        woodCutterRecipe(ItemTags.CRIMSON_STEMS, ModBlocks.CRIMSON_MOSAIC_STAIRS.get(), 4, output, "_from_stem");
        woodCutterRecipe(Items.CRIMSON_STEM, 3, Items.CRIMSON_HYPHAE, 2, output);
        woodCutterRecipe(Items.STRIPPED_CRIMSON_STEM, 3, Items.STRIPPED_CRIMSON_HYPHAE, 2, output);
        woodCutterRecipe(Items.CRIMSON_PLANKS, 2, Items.CRIMSON_DOOR, 1, output);
        woodCutterRecipe(Items.CRIMSON_PLANKS, Items.CRIMSON_BUTTON, 1, output);
        woodCutterRecipe(Items.CRIMSON_PLANKS, 4, Items.CRIMSON_FENCE_GATE, 1, output);
        woodCutterRecipe(Items.CRIMSON_PLANKS, Items.CRIMSON_PRESSURE_PLATE, 1, output);
        woodCutterRecipe(Items.CRIMSON_PLANKS, Items.CRIMSON_SLAB, 2, output);
        woodCutterRecipe(Items.CRIMSON_PLANKS, Items.CRIMSON_STAIRS, 1, output);
        woodCutterRecipe(Items.CRIMSON_PLANKS, 2, Items.CRIMSON_TRAPDOOR, 1, output);
        woodCutterRecipe(Items.CRIMSON_PLANKS, 4, ModBlocks.CRIMSON_CHAIR.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.CRIMSON_PLANKS, ModBlocks.CRIMSON_MOSAIC.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.CRIMSON_PLANKS, ModBlocks.CRIMSON_MOSAIC_SLAB.get(), 2, output, "_from_woodcutter");
        woodCutterRecipe(Items.CRIMSON_PLANKS, ModBlocks.CRIMSON_MOSAIC_STAIRS.get(), 1, output, "_from_woodcutter");
        //woodCutterRecipe(Items.CRIMSON_PLANKS, 5, ModItems.CRIMSON_BOAT.get(), 1, output);
        //woodCutterRecipe(Items.CRIMSON_PLANKS, 13, ModItems.CRIMSON_CHEST_BOAT.get(), 1, output);
        woodCutterRecipe(Items.CRIMSON_PLANKS, 3, ModBlocks.CRIMSON_TABLE.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.CRIMSON_PLANKS, 5, Items.CRIMSON_FENCE, 3, output);

        woodCutterRecipe(ItemTags.WARPED_STEMS, Items.WARPED_DOOR, 2, output, "_from_stem");
        woodCutterRecipe(ItemTags.WARPED_STEMS, Items.WARPED_PLANKS, 4, output, "_from_stem");
        woodCutterRecipe(ItemTags.WARPED_STEMS, Items.WARPED_BUTTON, 6, output, "_from_stem");
        woodCutterRecipe(ItemTags.WARPED_STEMS, Items.WARPED_FENCE_GATE, 1, output, "_from_stem");
        woodCutterRecipe(ItemTags.WARPED_STEMS, Items.WARPED_PRESSURE_PLATE, 6, output, "_from_stem");
        woodCutterRecipe(ItemTags.WARPED_STEMS, Items.WARPED_SLAB, 8, output, "_from_stem");
        woodCutterRecipe(ItemTags.WARPED_STEMS, Items.WARPED_STAIRS, 4, output, "_from_stem");
        woodCutterRecipe(ItemTags.WARPED_STEMS, Items.WARPED_TRAPDOOR, 2, output, "_from_stem");
        woodCutterRecipe(ItemTags.WARPED_STEMS, ModBlocks.WARPED_CHAIR.get(), 1, output, "_from_stem");
        woodCutterRecipe(ItemTags.WARPED_STEMS, ModBlocks.WARPED_MOSAIC.get(), 4, output, "_from_stem");
        woodCutterRecipe(ItemTags.WARPED_STEMS, ModBlocks.WARPED_MOSAIC_SLAB.get(), 8, output, "_from_stem");
        woodCutterRecipe(ItemTags.WARPED_STEMS, ModBlocks.WARPED_MOSAIC_STAIRS.get(), 4, output, "_from_stem");
        woodCutterRecipe(Items.WARPED_STEM, 3, Items.WARPED_HYPHAE, 2, output);
        woodCutterRecipe(Items.STRIPPED_WARPED_STEM, 3, Items.STRIPPED_WARPED_HYPHAE, 2, output);
        woodCutterRecipe(Items.WARPED_PLANKS, 2, Items.WARPED_DOOR, 1, output);
        woodCutterRecipe(Items.WARPED_PLANKS, Items.WARPED_BUTTON, 1, output);
        woodCutterRecipe(Items.WARPED_PLANKS, 4, Items.WARPED_FENCE_GATE, 1, output);
        woodCutterRecipe(Items.WARPED_PLANKS, Items.WARPED_PRESSURE_PLATE, 1, output);
        woodCutterRecipe(Items.WARPED_PLANKS, Items.WARPED_SLAB, 2, output);
        woodCutterRecipe(Items.WARPED_PLANKS, Items.WARPED_STAIRS, 1, output);
        woodCutterRecipe(Items.WARPED_PLANKS, 2, Items.WARPED_TRAPDOOR, 1, output);
        woodCutterRecipe(Items.WARPED_PLANKS, 4, ModBlocks.WARPED_CHAIR.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.WARPED_PLANKS, ModBlocks.WARPED_MOSAIC.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.WARPED_PLANKS, ModBlocks.WARPED_MOSAIC_SLAB.get(), 2, output, "_from_woodcutter");
        woodCutterRecipe(Items.WARPED_PLANKS, ModBlocks.WARPED_MOSAIC_STAIRS.get(), 1, output, "_from_woodcutter");
        //woodCutterRecipe(Items.WARPED_PLANKS, 5, ModItems.WARPED_BOAT.get(), 1, output);
        //woodCutterRecipe(Items.WARPED_PLANKS, 13, ModItems.WARPED_CHEST_BOAT.get(), 1, output);
        woodCutterRecipe(Items.WARPED_PLANKS, 3, ModBlocks.WARPED_TABLE.get(), 1, output, "_from_woodcutter");
        woodCutterRecipe(Items.WARPED_PLANKS, 5, Items.WARPED_FENCE, 3, output);

        shaped(RecipeCategory.MISC, ModBlocks.WOODCUTTER.get())
                .pattern(" C ")
                .pattern("WWW")
                .define('C', Items.COPPER_INGOT)
                .define('W', ItemTags.LOGS)
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT)).save(output);

        shaped(RecipeCategory.MISC, ModBlocks.BREEZING_STAND.get())
                .pattern(" R ")
                .pattern("SSS")
                .define('R', Items.BREEZE_ROD)
                .define('S', ItemTags.STONE_CRAFTING_MATERIALS)
                .unlockedBy(getHasName(Items.BREEZE_ROD), has(Items.BREEZE_ROD)).save(output);

        breezing(ModFluids.SOUL_FLUID_BUCKET.get())
                .inputItem(Items.BASALT)
                .inputItem(Items.SOUL_SOIL)
                .inputItem(Items.GOLD_NUGGET)
                .inputItem(ModItems.SOUL_ESSENCE.get())
                .fluidOutput(Items.WATER_BUCKET)
                .chance(0.2f)
                .fireRequirement(ModFuelingTypes.SOUL)
                .defaultResult(Items.BUCKET)
                .unlockedBy(getHasName(Items.SOUL_SOIL), has(Items.SOUL_SOIL)).save(output);

        shaped(RecipeCategory.MISC, ModBlocks.END_CAMPFIRE.get())
                .pattern(" S ")
                .pattern("SCS")
                .pattern("LLL")
                .define('S', Items.STICK)
                .define('C', Items.POPPED_CHORUS_FRUIT)
                .define('L', ItemTags.LOGS)
                .unlockedBy(getHasName(Items.POPPED_CHORUS_FRUIT), has(Items.POPPED_CHORUS_FRUIT)).save(output);

        shaped(RecipeCategory.MISC, ModBlocks.END_TORCH.get())
                .pattern("P")
                .pattern("C")
                .pattern("S")
                .define('S', Items.STICK)
                .define('P', Items.POPPED_CHORUS_FRUIT)
                .define('C', ItemTags.FURNACE_MINECART_FUEL)
                .unlockedBy(getHasName(Items.POPPED_CHORUS_FRUIT), has(Items.POPPED_CHORUS_FRUIT)).save(output);

        shaped(RecipeCategory.MISC, ModBlocks.END_LANTERN.get())
                .pattern("NNN")
                .pattern("NTN")
                .pattern("NNN")
                .define('N', Items.GOLD_NUGGET)
                .define('T', ModBlocks.END_TORCH.get())
                .unlockedBy(getHasName(ModBlocks.END_TORCH.get()), has(ModBlocks.END_TORCH.get())).save(output);

        cFShapeless(ModItems.SOUL_ESSENCE.get())
                .requires(Items.LAPIS_LAZULI, 7)
                .requires(ModItems.NETHER_ESSENCE.get())
                .requires(Items.SOUL_SAND)
                .experience(2f)
                .cookingTime(400)
                .fuelType(ModFuelingTypes.NORMAL)
                .unlockedBy(getHasName(ModItems.NETHER_ESSENCE.get()), has(ModItems.NETHER_ESSENCE.get())).save(output);

        cFShaped(ModItems.NETHER_ESSENCE.get(), 2)
                .pattern("ETE")
                .pattern("ENE")
                .pattern("EEE")
                .define('E', Items.EMERALD)
                .define('N', Items.NETHERRACK)
                .define('T', ModItems.NETHER_ESSENCE.get())
                .experience(0.6f)
                .cookingTime(200)
                .fuelType(ModFuelingTypes.SOUL)
                .unlockedBy(getHasName(ModItems.NETHER_ESSENCE.get()), has(ModItems.NETHER_ESSENCE.get())).save(output, "_from_cloning");

        breezing(ModFluids.END_FLUID_BUCKET.get())
                .inputItem(Items.POPPED_CHORUS_FRUIT)
                .inputItem(Items.ENDER_EYE)
                .inputItem(Items.END_STONE)
                .inputItem(ModItems.ENDER_ESSENCE.get())
                .fluidOutput(ModFluids.SOUL_FLUID_BUCKET.get())
                .chance(0.2f)
                .fireRequirement(ModFuelingTypes.ENDER)
                .defaultResult(ModItems.POLLUTED_SOUL_FLUID_BUCKET.get())
                .unlockedBy(getHasName(ModFluids.SOUL_FLUID_BUCKET.get()), has(ModFluids.SOUL_FLUID_BUCKET.get())).save(output);

        SimpleCookingRecipeBuilder.generic(Ingredient.of(ModItems.POLLUTED_SOUL_FLUID_BUCKET.get()), RecipeCategory.MISC,
                        ModFluids.SOUL_FLUID_BUCKET.get(), 0.7f, 200, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new)
                .group("soul_fluid_bucket").unlockedBy(getHasName(ModItems.POLLUTED_SOUL_FLUID_BUCKET.get()), has(ModItems.POLLUTED_SOUL_FLUID_BUCKET.get()))
                .save(output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "soul_fluid_bucket_from_blasting")));

        cFShaped(ModItems.ENDER_ESSENCE.get(), 2)
                .pattern("CTC")
                .pattern("CEC")
                .pattern("CCC")
                .define('E', Items.END_STONE)
                .define('C', Items.POPPED_CHORUS_FRUIT)
                .define('T', ModItems.ENDER_ESSENCE.get())
                .experience(0.6f)
                .cookingTime(200)
                .fuelType(ModFuelingTypes.ENDER)
                .unlockedBy(getHasName(ModItems.ENDER_ESSENCE.get()), has(ModItems.ENDER_ESSENCE.get())).save(output, "_from_cloning");
    }

    protected void oreSmelting(RecipeOutput output, ItemLike pIngredient, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(output, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredient, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected void oreBlasting(RecipeOutput output, ItemLike pIngredient, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(output, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredient, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput output, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                ItemLike pIngredient, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        SimpleCookingRecipeBuilder.generic(Ingredient.of(pIngredient), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(output, NatureUpdate.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(pIngredient));
    }

    protected void blockRecipe(RecipeOutput output, ItemLike item, ItemLike block, int count, RecipeCategory category) {
        shaped(category, block, count)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', item)
                .unlockedBy(getHasName(item), has(item)).save(output);
    }

    protected void conversionRecipe(RecipeOutput recipeOutput, ItemLike input, ItemStack output, RecipeCategory category) {
        shapeless(category, output.getItem(), output.getCount())
                .requires(input)
                .unlockedBy(getHasName(input), has(input)).save(recipeOutput, getConversionRecipeName(output.getItem(), input) + "_from_block");
    }

    protected void chiseledRecipe (RecipeOutput recipeOutput, ItemLike input, ItemStack output, RecipeCategory category) {
        shaped(category, output.getItem(), output.getCount())
                .pattern("A")
                .pattern("A")
                .define('A', input)
                .unlockedBy(getHasName(input), has(input)).save(recipeOutput);
    }

    protected void stairsRecipe (RecipeOutput output,ItemLike material, ItemStack stairs, RecipeCategory category) {
        shaped(category, stairs.getItem(), stairs.getCount())
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .define('A', material)
                .unlockedBy(getHasName(material), has(material)).save(output);
    }

    protected void stairsRecipe (RecipeOutput output, ItemLike material, ItemStack stairs, RecipeCategory category, int stoneCutterCount) {
        stairsRecipe(output, material, stairs, category);
        stonecutterResultFromBase(category, stairs.getItem(), material, stoneCutterCount);
    }

    protected void slabRecipe (RecipeOutput output, ItemLike material, ItemStack slab, RecipeCategory category) {
        shaped(category, slab.getItem(), slab.getCount())
                .pattern("AAA")
                .define('A', material)
                .unlockedBy(getHasName(material), has(material)).save(output);
    }

    protected void slabRecipe (ItemLike material, ItemStack slab, RecipeCategory category, int stoneCutterCount) {
        slabRecipe(output, material, slab, category);
        stonecutterResultFromBase(category, slab.getItem(), material, stoneCutterCount);
    }

    protected void pressurePlateRecipe (ItemLike material, ItemLike pressurePlate, RecipeCategory category) {
        pressurePlate(pressurePlate, material);
    }

    protected void doorRecipe (RecipeOutput output, ItemLike material, ItemStack door, RecipeCategory category) {
        shaped(category, door.getItem(), door.getCount())
                .pattern("AA")
                .pattern("AA")
                .pattern("AA")
                .define('A', material)
                .unlockedBy(getHasName(material), has(material)).save(output);
    }

    protected void trapdoorRecipe (RecipeOutput output, ItemLike material, ItemStack trapdoor, RecipeCategory category) {
        shaped(category, trapdoor.getItem(), trapdoor.getCount())
                .pattern("AAA")
                .pattern("AAA")
                .define('A', material)
                .unlockedBy(getHasName(material), has(material)).save(output);
    }

    protected void trapdoorRecipe (RecipeOutput output, ItemLike material, ItemStack trapdoor, RecipeCategory category, int stoneCutterCount) {
        trapdoorRecipe(output, material, trapdoor, category);
        stonecutterResultFromBase(category, trapdoor.getItem(), material, stoneCutterCount);
    }

    protected void compactingRecipe (RecipeOutput output, ItemLike material, ItemStack result, RecipeCategory category, int stoneCutterCount) {
        shaped(category, result.getItem(), result.getCount())
                .pattern("AA")
                .pattern("AA")
                .define('A', material)
                .unlockedBy(getHasName(material), has(material)).save(output);
        stonecutterResultFromBase(category, result.getItem(), material, stoneCutterCount);
    }

    protected void grateRecipe (RecipeOutput output, ItemLike material, ItemStack result, RecipeCategory category, int stonecuttercount) {
        shaped(category, result.getItem(), result.getCount())
                .pattern(" A ")
                .pattern("A A")
                .pattern(" A ")
                .define('A', material)
                .unlockedBy(getHasName(material), has(material)).save(output);
        stonecutterResultFromBase(category, result.getItem(), material, stonecuttercount);
    }

    protected void toolRecipes (RecipeOutput output, ItemStack sword, ItemLike swordHead, ItemStack shovel, ItemLike shovelHead, ItemStack axe, ItemLike axeHead, ItemStack pickaxe, ItemLike pickaxeHead, ItemStack hoe, ItemLike hoeHead) {
        shaped(RecipeCategory.TOOLS, sword.getItem(), sword.getCount())
                .pattern("A")
                .pattern("#")
                .define('A', swordHead)
                .define('#', Items.STICK)
                .unlockedBy(getHasName(swordHead), has(swordHead)).save(output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, sword.toString().split(":")[1])));
        shaped(RecipeCategory.TOOLS, shovel.getItem(), shovel.getCount())
                .pattern("A")
                .pattern("#")
                .pattern("#")
                .define('A', shovelHead)
                .define('#', Items.STICK)
                .unlockedBy(getHasName(shovelHead), has(shovelHead)).save(output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, shovel.toString().split(":")[1])));
        shaped(RecipeCategory.TOOLS, axe.getItem(), axe.getCount())
                .pattern("A")
                .pattern("#")
                .pattern("#")
                .define('A', axeHead)
                .define('#', Items.STICK)
                .unlockedBy(getHasName(axeHead), has(axeHead)).save(output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, axe.toString().split(":")[1])));
        shaped(RecipeCategory.TOOLS, pickaxe.getItem(), pickaxe.getCount())
                .pattern("A")
                .pattern("#")
                .pattern("#")
                .define('A', pickaxeHead)
                .define('#', Items.STICK)
                .unlockedBy(getHasName(pickaxeHead), has(pickaxeHead)).save(output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, pickaxe.toString().split(":")[1])));
        shaped(RecipeCategory.TOOLS, hoe.getItem(), hoe.getCount())
                .pattern("A")
                .pattern("#")
                .pattern("#")
                .define('A', hoeHead)
                .define('#', Items.STICK)
                .unlockedBy(getHasName(hoeHead), has(hoeHead)).save(output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, hoe.toString().split(":")[1])));
    }

    protected void sawRecipe (RecipeOutput output, Item sawHead, SawItem saw) {
        shaped(RecipeCategory.TOOLS, saw)
                .pattern("H")
                .pattern("A")
                .define('H', sawHead)
                .define('A', ModItems.SAW_HANDLE.get())
                .unlockedBy(getHasName(sawHead), has(sawHead)).save(output);
    }

    protected void tableRecipe (RecipeOutput output, ItemLike material, ItemStack result) {
        shaped(RecipeCategory.DECORATIONS, result.getItem(), result.getCount())
                .pattern("PP")
                .pattern("SS")
                .define('P', material)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(material), has(material)).save(output);
    }

    protected void chairRecipe (RecipeOutput output, ItemLike material, ItemStack result) {
        shaped(RecipeCategory.DECORATIONS, result.getItem(), result.getCount())
                .pattern("P ")
                .pattern("PP")
                .pattern("SS")
                .define('P', material)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(material), has(material)).save(output);
    }

    protected void shelfRecipe (RecipeOutput output, ItemLike logs, ItemLike shelf) {
        shaped(RecipeCategory.DECORATIONS, shelf, 6)
                .pattern("LLL")
                .pattern("   ")
                .pattern("LLL")
                .define('L', logs)
                .unlockedBy(getHasName(logs), has(logs)).save(output);
    }

    protected void barsRecipe (RecipeOutput output, ItemLike ingot, ItemLike bars) {
        shaped(RecipeCategory.DECORATIONS, bars, 16)
                .pattern("III")
                .pattern("III")
                .define('I', ingot)
                .unlockedBy(getHasName(ingot), has(ingot)).save(output);
    }

    protected void moldFillingRecipe (RecipeOutput output, ItemLike mold, ItemLike fillerItem, int size, MoldFillerType filler) {
        cFShapeless(mold)
                .requires(mold)
                .requires(fillerItem, size)
                .cookingTime(150 * size)
                .withCustomFillerType(filler)
                .experience(2f)
                .unlockedBy(getHasName(fillerItem), has(fillerItem)).save(output, "_" + filler.id() + "_filler");
    }

    protected void soulMoldFillingRecipe (RecipeOutput output, ItemLike mold, ItemLike fillerItem, int size, MoldFillerType filler) {
        cFShapeless(mold)
                .requires(mold)
                .requires(fillerItem, size)
                .cookingTime(150 * size)
                .fuelType(ModFuelingTypes.SOUL)
                .withCustomFillerType(filler)
                .experience(2f)
                .unlockedBy(getHasName(fillerItem), has(fillerItem)).save(output, "_" + filler.id() + "_filler");
    }

    protected void sawMoldFillingRecipe (RecipeOutput output, ItemLike fillerItem, MoldFillerType filler) {
        cFShapeless(ModItems.SAW_MOLD.get())
                .requires(ModItems.SAW_MOLD.get())
                .requires(fillerItem, 2)
                .requires(Items.COPPER_INGOT)
                .cookingTime(450)
                .withCustomFillerType(filler)
                .experience(2f)
                .unlockedBy(getHasName(fillerItem), has(fillerItem)).save(output, "_" + filler.id() + "_filler");
    }

    protected void soulSawMoldFillingRecipe (RecipeOutput output, ItemLike fillerItem, MoldFillerType filler) {
        cFShapeless(ModItems.SAW_MOLD.get())
                .requires(ModItems.SAW_MOLD.get())
                .requires(fillerItem, 2)
                .requires(Items.COPPER_INGOT)
                .cookingTime(450)
                .fuelType(ModFuelingTypes.SOUL)
                .withCustomFillerType(filler)
                .experience(2f)
                .unlockedBy(getHasName(fillerItem), has(fillerItem)).save(output, "_" + filler.id() + "_filler");
    }

    protected CraftingFurnaceRecipeBuilder cFShaped (ItemLike result, int count) {
        return CraftingFurnaceRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), result, count);
    }

    protected CraftingFurnaceRecipeBuilder cFShaped(ItemLike result) {
        return CraftingFurnaceRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), result);
    }

    protected ShapelessCraftingFurnaceRecipeBuilder cFShapeless(ItemLike result, int count) {
        return ShapelessCraftingFurnaceRecipeBuilder.shapeless(this.registries.lookupOrThrow(Registries.ITEM), result, count);
    }

    protected ShapelessCraftingFurnaceRecipeBuilder cFShapeless(ItemLike result) {
        return ShapelessCraftingFurnaceRecipeBuilder.shapeless(this.registries.lookupOrThrow(Registries.ITEM), result);
    }

    protected WaterBoilerRecipeBuilder wBRecipe(Ingredient input, ItemStack output) {
        return WaterBoilerRecipeBuilder.of(input, output);
    }

    protected void woodCutterRecipe (ItemLike input, ItemLike result, int count, RecipeOutput output) {
        WoodCutterRecipeBuilder.build(Ingredient.of(input), result, count, output);
    }

    protected void woodCutterRecipe (ItemLike input, int count, ItemLike result, int resultCount, RecipeOutput output) {
        WoodCutterRecipeBuilder.build(Ingredient.of(input), count, result, resultCount, output);
    }

    protected void woodCutterRecipe (TagKey<Item> input, ItemLike result, int count, RecipeOutput output) {
        WoodCutterRecipeBuilder.build(Ingredient.of(this.registries.lookupOrThrow(Registries.ITEM).getOrThrow(input)), result, count, output);
    }

    protected void woodCutterRecipe (TagKey<Item> input, int count, ItemLike result, int resultCount, RecipeOutput output) {
        WoodCutterRecipeBuilder.build(Ingredient.of(this.registries.lookupOrThrow(Registries.ITEM).getOrThrow(input)), count, result, resultCount, output);
    }

    protected void woodCutterRecipe (ItemLike input, ItemLike result, int count, RecipeOutput output, String suffix) {
        WoodCutterRecipeBuilder.build(Ingredient.of(input), result, count, output, suffix);
    }

    protected void woodCutterRecipe (ItemLike input, int count, ItemLike result, int resultCount, RecipeOutput output, String suffix) {
        WoodCutterRecipeBuilder.build(Ingredient.of(input), count, result, resultCount, output, suffix);
    }

    protected void woodCutterRecipe (TagKey<Item> input, ItemLike result, int count, RecipeOutput output, String suffix) {
        WoodCutterRecipeBuilder.build(Ingredient.of(this.registries.lookupOrThrow(Registries.ITEM).getOrThrow(input)), result, count, output, suffix);
    }

    protected void woodCutterRecipe (TagKey<Item> input, int count, ItemLike result, int resultCount, RecipeOutput output, String suffix) {
        WoodCutterRecipeBuilder.build(Ingredient.of(this.registries.lookupOrThrow(Registries.ITEM).getOrThrow(input)), count, result, resultCount, output, suffix);
    }

    protected BreezingRecipeBuilder breezing (ItemLike input) {
        return BreezingRecipeBuilder.of(this.registries.lookupOrThrow(Registries.ITEM), new ItemStack(input));
    }

    protected BreezingRecipeBuilder breezing (ItemLike input, int count) {
        return BreezingRecipeBuilder.of(this.registries.lookupOrThrow(Registries.ITEM), new ItemStack(input, count));
    }
}