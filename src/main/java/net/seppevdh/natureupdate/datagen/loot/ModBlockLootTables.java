package net.seppevdh.natureupdate.datagen.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.seppevdh.natureupdate.block.ModBlocks;
import net.seppevdh.natureupdate.block.custom.CornCropBlock;
import net.seppevdh.natureupdate.block.custom.RhubarbCropBlock;
import net.seppevdh.natureupdate.block.custom.RiceCropBlock;
import net.seppevdh.natureupdate.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.TIN_BLOCK.get());
        dropSelf(ModBlocks.RAW_TIN_BLOCK.get());
        dropSelf(ModBlocks.BRONZE_BLOCK.get());

        dropSelf(ModBlocks.MOD_OAK_SAPLING.get());

        dropSelf(ModBlocks.OAK_MOSAIC.get());
        dropSelf(ModBlocks.BIRCH_MOSAIC.get());
        dropSelf(ModBlocks.SPRUCE_MOSAIC.get());
        dropSelf(ModBlocks.JUNGLE_MOSAIC.get());
        dropSelf(ModBlocks.ACACIA_MOSAIC.get());
        dropSelf(ModBlocks.DARK_OAK_MOSAIC.get());
        dropSelf(ModBlocks.MANGROVE_MOSAIC.get());
        dropSelf(ModBlocks.CHERRY_MOSAIC.get());
        dropSelf(ModBlocks.PALE_OAK_MOSAIC.get());
        dropSelf(ModBlocks.CRIMSON_MOSAIC.get());
        dropSelf(ModBlocks.WARPED_MOSAIC.get());

        add(ModBlocks.TIN_ORE.get(),
                block -> createOreDrop(ModBlocks.TIN_ORE.get(), ModItems.RAW_TIN.get()));

        dropSelf(ModBlocks.OAK_MOSAIC_STAIRS.get());
        dropSelf(ModBlocks.BIRCH_MOSAIC_STAIRS.get());
        dropSelf(ModBlocks.SPRUCE_MOSAIC_STAIRS.get());
        dropSelf(ModBlocks.JUNGLE_MOSAIC_STAIRS.get());
        dropSelf(ModBlocks.ACACIA_MOSAIC_STAIRS.get());
        dropSelf(ModBlocks.DARK_OAK_MOSAIC_STAIRS.get());
        dropSelf(ModBlocks.MANGROVE_MOSAIC_STAIRS.get());
        dropSelf(ModBlocks.CHERRY_MOSAIC_STAIRS.get());
        dropSelf(ModBlocks.PALE_OAK_MOSAIC_STAIRS.get());
        dropSelf(ModBlocks.CRIMSON_MOSAIC_STAIRS.get());
        dropSelf(ModBlocks.WARPED_MOSAIC_STAIRS.get());

        dropSelf(ModBlocks.IRON_STAIRS.get());
        dropSelf(ModBlocks.IRON_BUTTON.get());
        add(ModBlocks.IRON_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.IRON_SLAB.get()));
        dropSelf(ModBlocks.IRON_GRATE.get());

        dropSelf(ModBlocks.SMOOTH_STONE_STAIRS.get());

        dropSelf(ModBlocks.GOLDEN_STAIRS.get());
        dropSelf(ModBlocks.GOLDEN_TRAPDOOR.get());
        dropSelf(ModBlocks.GOLDEN_BUTTON.get());
        add(ModBlocks.GOLDEN_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.GOLDEN_SLAB.get()));
        add(ModBlocks.GOLDEN_DOOR.get(),
                block -> createDoorTable(ModBlocks.GOLDEN_DOOR.get()));
        dropSelf(ModBlocks.GOLDEN_GRATE.get());

        dropSelf(ModBlocks.EMERALD_STAIRS.get());
        dropSelf(ModBlocks.EMERALD_TRAPDOOR.get());
        dropSelf(ModBlocks.EMERALD_BUTTON.get());
        dropSelf(ModBlocks.EMERALD_PRESSURE_PLATE.get());
        add(ModBlocks.EMERALD_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.EMERALD_SLAB.get()));
        add(ModBlocks.EMERALD_DOOR.get(),
                block -> createDoorTable(ModBlocks.EMERALD_DOOR.get()));
        dropSelf(ModBlocks.EMERALD_GRATE.get());

        dropSelf(ModBlocks.LAPIS_STAIRS.get());
        dropSelf(ModBlocks.LAPIS_TRAPDOOR.get());
        dropSelf(ModBlocks.LAPIS_BUTTON.get());
        dropSelf(ModBlocks.LAPIS_PRESSURE_PLATE.get());
        add(ModBlocks.LAPIS_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.LAPIS_SLAB.get()));
        add(ModBlocks.LAPIS_DOOR.get(),
                block -> createDoorTable(ModBlocks.LAPIS_DOOR.get()));
        dropSelf(ModBlocks.LAPIS_GRATE.get());

        dropSelf(ModBlocks.TIN_STAIRS.get());
        dropSelf(ModBlocks.TIN_TRAPDOOR.get());
        dropSelf(ModBlocks.TIN_BUTTON.get());
        dropSelf(ModBlocks.TIN_PRESSURE_PLATE.get());
        add(ModBlocks.TIN_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.TIN_SLAB.get()));
        add(ModBlocks.TIN_DOOR.get(),
                block -> createDoorTable(ModBlocks.TIN_DOOR.get()));
        dropSelf(ModBlocks.TIN_GRATE.get());

        dropSelf(ModBlocks.BRONZE_STAIRS.get());
        dropSelf(ModBlocks.BRONZE_TRAPDOOR.get());
        dropSelf(ModBlocks.BRONZE_BUTTON.get());
        dropSelf(ModBlocks.BRONZE_PRESSURE_PLATE.get());
        add(ModBlocks.BRONZE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.BRONZE_SLAB.get()));
        add(ModBlocks.BRONZE_DOOR.get(),
                block -> createDoorTable(ModBlocks.BRONZE_DOOR.get()));
        dropSelf(ModBlocks.BRONZE_GRATE.get());

        dropSelf(ModBlocks.DIAMOND_STAIRS.get());
        dropSelf(ModBlocks.DIAMOND_TRAPDOOR.get());
        dropSelf(ModBlocks.DIAMOND_BUTTON.get());
        dropSelf(ModBlocks.DIAMOND_PRESSURE_PLATE.get());
        add(ModBlocks.DIAMOND_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.DIAMOND_SLAB.get()));
        add(ModBlocks.DIAMOND_DOOR.get(),
                block -> createDoorTable(ModBlocks.DIAMOND_DOOR.get()));
        dropSelf(ModBlocks.DIAMOND_GRATE.get());

        dropSelf(ModBlocks.NETHERITE_STAIRS.get());
        dropSelf(ModBlocks.NETHERITE_TRAPDOOR.get());
        dropSelf(ModBlocks.NETHERITE_BUTTON.get());
        dropSelf(ModBlocks.NETHERITE_PRESSURE_PLATE.get());
        add(ModBlocks.NETHERITE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.NETHERITE_SLAB.get()));
        add(ModBlocks.NETHERITE_DOOR.get(),
                block -> createDoorTable(ModBlocks.NETHERITE_DOOR.get()));
        dropSelf(ModBlocks.NETHERITE_GRATE.get());

        add(ModBlocks.OAK_MOSAIC_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.OAK_MOSAIC_SLAB.get()));
        add(ModBlocks.BIRCH_MOSAIC_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.BIRCH_MOSAIC_SLAB.get()));
        add(ModBlocks.ACACIA_MOSAIC_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.ACACIA_MOSAIC_SLAB.get()));
        add(ModBlocks.JUNGLE_MOSAIC_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.JUNGLE_MOSAIC_SLAB.get()));
        add(ModBlocks.SPRUCE_MOSAIC_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.SPRUCE_MOSAIC_SLAB.get()));
        add(ModBlocks.DARK_OAK_MOSAIC_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.DARK_OAK_MOSAIC_SLAB.get()));
        add(ModBlocks.MANGROVE_MOSAIC_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.MANGROVE_MOSAIC_SLAB.get()));
        add(ModBlocks.CHERRY_MOSAIC_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.CHERRY_MOSAIC_SLAB.get()));
        add(ModBlocks.PALE_OAK_MOSAIC_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.PALE_OAK_MOSAIC_SLAB.get()));
        add(ModBlocks.CRIMSON_MOSAIC_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.CRIMSON_MOSAIC_SLAB.get()));
        add(ModBlocks.WARPED_MOSAIC_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.WARPED_MOSAIC_SLAB.get()));

        add(ModBlocks.RICE_CROP.get(), createCropDrops(ModBlocks.RICE_CROP.get(), ModItems.RICE_SEEDS.get(),
                ModItems.RICE_SEEDS.get(), createCropLootItemCondition(ModBlocks.RICE_CROP.get(), RiceCropBlock.AGE, RiceCropBlock.MAX_AGE)));

        add(ModBlocks.CORN.get(), createCropDrops(ModBlocks.CORN.get(), ModBlocks.CORN.get().asItem(),
                ModBlocks.CORN.get().asItem(), createCropLootItemCondition(ModBlocks.CORN.get(), CornCropBlock.AGE, CornCropBlock.MAX_AGE)));

        add(ModBlocks.RHUBARB.get(), createCropDrops(ModBlocks.RHUBARB.get(), ModBlocks.RHUBARB.get().asItem(),
                ModBlocks.RHUBARB.get().asItem(), createCropLootItemCondition(ModBlocks.RHUBARB.get(), RhubarbCropBlock.AGE, RhubarbCropBlock.MAX_AGE)));

        dropSelf(ModBlocks.OAK_CROP.get());
        dropSelf(ModBlocks.BIRCH_CROP.get());
        dropSelf(ModBlocks.SPRUCE_CROP.get());
        dropSelf(ModBlocks.ACACIA_CROP.get());
        dropSelf(ModBlocks.JUNGLE_CROP.get());
        dropSelf(ModBlocks.DARK_OAK_CROP.get());
        dropSelf(ModBlocks.CHERRY_CROP.get());
        dropSelf(ModBlocks.PALE_OAK_CROP.get());

        addFoodBushBlockDrops(ModBlocks.STRAWBERRY.get(), ModBlocks.STRAWBERRY.get().asItem());
        addFoodBushBlockDrops(ModBlocks.RASPBERRY.get(), ModBlocks.RASPBERRY.get().asItem());
        addFoodBushBlockDrops(ModBlocks.BLACKBERRY.get(), ModBlocks.BLACKBERRY.get().asItem());

        dropSelf(ModBlocks.CRAFTING_FURNACE.get());
        dropSelf(ModBlocks.WATER_BOILER.get());

        dropSelf(ModBlocks.OAK_TABLE.get());
        dropSelf(ModBlocks.BIRCH_TABLE.get());
        dropSelf(ModBlocks.SPRUCE_TABLE.get());
        dropSelf(ModBlocks.ACACIA_TABLE.get());
        dropSelf(ModBlocks.JUNGLE_TABLE.get());
        dropSelf(ModBlocks.DARK_OAK_TABLE.get());
        dropSelf(ModBlocks.MANGROVE_TABLE.get());
        dropSelf(ModBlocks.CHERRY_TABLE.get());
        dropSelf(ModBlocks.BAMBOO_TABLE.get());
        dropSelf(ModBlocks.CRIMSON_TABLE.get());
        dropSelf(ModBlocks.WARPED_TABLE.get());
        dropSelf(ModBlocks.PALE_OAK_TABLE.get());

        dropSelf(ModBlocks.OAK_CHAIR.get());
        dropSelf(ModBlocks.BIRCH_CHAIR.get());
        dropSelf(ModBlocks.SPRUCE_CHAIR.get());
        dropSelf(ModBlocks.ACACIA_CHAIR.get());
        dropSelf(ModBlocks.JUNGLE_CHAIR.get());
        dropSelf(ModBlocks.DARK_OAK_CHAIR.get());
        dropSelf(ModBlocks.MANGROVE_CHAIR.get());
        dropSelf(ModBlocks.CHERRY_CHAIR.get());
        dropSelf(ModBlocks.BAMBOO_CHAIR.get());
        dropSelf(ModBlocks.CRIMSON_CHAIR.get());
        dropSelf(ModBlocks.WARPED_CHAIR.get());
        dropSelf(ModBlocks.PALE_OAK_CHAIR.get());

        dropSelf(ModBlocks.OAK_SHELF.get());

        dropSelf(ModBlocks.GOLD_BARS.get());
        dropSelf(ModBlocks.EMERALD_BARS.get());
        dropSelf(ModBlocks.LAPIS_BARS.get());
        dropSelf(ModBlocks.DIAMOND_BARS.get());
        dropSelf(ModBlocks.NETHERITE_BARS.get());
        dropSelf(ModBlocks.TIN_BARS.get());
        dropSelf(ModBlocks.BRONZE_BARS.get());

        dropSelf(ModBlocks.WOODCUTTER.get());
        dropSelf(ModBlocks.BREEZING_STAND.get());

        add(ModBlocks.END_FIRE.get(), noDrop());

        add(ModBlocks.END_CAMPFIRE.get(), applyExplosionDecay(
                        ModBlocks.END_CAMPFIRE.get(), LootTable.lootTable().withPool(
                                LootPool.lootPool().when(hasSilkTouch()).add(LootItem.lootTableItem(ModBlocks.END_CAMPFIRE.get()))
                ).withPool(
                        LootPool.lootPool().when(doesNotHaveSilkTouch())
                                .add(LootItem.lootTableItem(Items.POPPED_CHORUS_FRUIT))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 2f)))
                )
        ));

        dropSelf(ModBlocks.END_TORCH.get());
        dropOther(ModBlocks.END_WALL_TORCH.get(), ModBlocks.END_TORCH.get());
        dropSelf(ModBlocks.END_LANTERN.get());
    }

    private LootItemCondition.Builder createCropLootItemCondition(Block block, IntegerProperty ageProperty, int maxAge) {
        return LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                StatePropertiesPredicate.Builder.properties().hasProperty(ageProperty, maxAge)
        );
    }

    HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

    private void addFoodBushBlockDrops (Block bushBlock, Item seedItem) {
        add(bushBlock, block -> this.applyExplosionDecay(
            block, LootTable.lootTable().withPool(
                LootPool.lootPool().when(createCropLootItemCondition(bushBlock, SweetBerryBushBlock.AGE, SweetBerryBushBlock.MAX_AGE))
                        .add(LootItem.lootTableItem(seedItem))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
            ).withPool(
                LootPool.lootPool().when(createCropLootItemCondition(bushBlock, SweetBerryBushBlock.AGE, SweetBerryBushBlock.MAX_AGE - 1))
                        .add(LootItem.lootTableItem(seedItem))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
            )
        ));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
