package net.seppevdh.natureupdate.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.seppevdh.natureupdate.block.ModBlocks;
import net.seppevdh.natureupdate.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        this.builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(ModBlocks.OAK_MOSAIC.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.BIRCH_MOSAIC.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.SPRUCE_MOSAIC.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.ACACIA_MOSAIC.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.JUNGLE_MOSAIC.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.DARK_OAK_MOSAIC.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.MANGROVE_MOSAIC.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.CHERRY_MOSAIC.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.CRIMSON_MOSAIC.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.WARPED_MOSAIC.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.OAK_MOSAIC_STAIRS.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.BIRCH_MOSAIC_STAIRS.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.SPRUCE_MOSAIC_STAIRS.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.ACACIA_MOSAIC_STAIRS.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.JUNGLE_MOSAIC_STAIRS.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.DARK_OAK_MOSAIC_STAIRS.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.MANGROVE_MOSAIC_STAIRS.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.CHERRY_MOSAIC_STAIRS.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.CRIMSON_MOSAIC_STAIRS.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.WARPED_MOSAIC_STAIRS.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.OAK_MOSAIC_SLAB.getId(), new FurnaceFuel(150), false)
                .add(ModBlocks.BIRCH_MOSAIC_SLAB.getId(), new FurnaceFuel(150), false)
                .add(ModBlocks.SPRUCE_MOSAIC_SLAB.getId(), new FurnaceFuel(150), false)
                .add(ModBlocks.ACACIA_MOSAIC_SLAB.getId(), new FurnaceFuel(150), false)
                .add(ModBlocks.JUNGLE_MOSAIC_SLAB.getId(), new FurnaceFuel(150), false)
                .add(ModBlocks.DARK_OAK_MOSAIC_SLAB.getId(), new FurnaceFuel(150), false)
                .add(ModBlocks.MANGROVE_MOSAIC_SLAB.getId(), new FurnaceFuel(150), false)
                .add(ModBlocks.CHERRY_MOSAIC_SLAB.getId(), new FurnaceFuel(150), false)
                .add(ModBlocks.CRIMSON_MOSAIC_SLAB.getId(), new FurnaceFuel(150), false)
                .add(ModBlocks.WARPED_MOSAIC_SLAB.getId(), new FurnaceFuel(150), false)
                .add(ModBlocks.OAK_TABLE.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.BIRCH_TABLE.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.SPRUCE_TABLE.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.ACACIA_TABLE.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.JUNGLE_TABLE.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.DARK_OAK_TABLE.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.MANGROVE_TABLE.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.CHERRY_TABLE.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.CRIMSON_TABLE.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.WARPED_TABLE.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.OAK_CHAIR.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.BIRCH_CHAIR.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.SPRUCE_CHAIR.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.ACACIA_CHAIR.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.JUNGLE_CHAIR.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.DARK_OAK_CHAIR.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.MANGROVE_CHAIR.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.CHERRY_CHAIR.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.CRIMSON_CHAIR.getId(), new FurnaceFuel(300), false)
                .add(ModBlocks.WARPED_CHAIR.getId(), new FurnaceFuel(300), false);

        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(ModItems.RICE_SEEDS.getId(), new Compostable(0.3f), false)
                .add(ModBlocks.CORN.getId(), new Compostable(0.65f), false)
                .add(ModItems.PEAR.getId(), new Compostable(0.65f), false)
                .add(ModBlocks.STRAWBERRY.getId(), new Compostable(0.5f), false)
                .add(ModBlocks.RASPBERRY.getId(), new Compostable(0.5f), false)
                .add(ModBlocks.BLACKBERRY.getId(), new Compostable(0.5f), false)
                .add(ModBlocks.OAK_CROP.getId(), new Compostable(0.5f), false)
                .add(ModBlocks.SPRUCE_CROP.getId(), new Compostable(0.5f), false)
                .add(ModBlocks.BIRCH_CROP.getId(), new Compostable(0.5f), false)
                .add(ModBlocks.ACACIA_CROP.getId(), new Compostable(0.5f), false)
                .add(ModBlocks.JUNGLE_CROP.getId(), new Compostable(0.5f), false)
                .add(ModBlocks.DARK_OAK_CROP.getId(), new Compostable(0.5f), false)
                .add(ModBlocks.CHERRY_CROP.getId(), new Compostable(0.5f), false);
    }
}
