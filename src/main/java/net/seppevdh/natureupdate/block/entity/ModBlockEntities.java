package net.seppevdh.natureupdate.block.entity;

import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.seppevdh.natureupdate.block.entity.custom.*;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, NatureUpdate.MOD_ID);

    public static final Supplier<BlockEntityType<CraftingFurnaceBlockEntity>> CRAFTING_FURNACE_BE =
            BLOCK_ENTITIES.register("crafting_furnace_be", () -> new BlockEntityType<>(
                    CraftingFurnaceBlockEntity::new, ModBlocks.CRAFTING_FURNACE.get()));
    public static final Supplier<BlockEntityType<WaterBoilerBlockEntity>> WATER_BOILER_BE =
            BLOCK_ENTITIES.register("water_boiler_be", () -> new BlockEntityType<>(
                    WaterBoilerBlockEntity::new, ModBlocks.WATER_BOILER.get()));
    public static final Supplier<BlockEntityType<TableBlockEntity>> TABLE_BE =
            BLOCK_ENTITIES.register("table_be", () -> new BlockEntityType<>(
                    TableBlockEntity::new, ModBlocks.OAK_TABLE.get(), ModBlocks.BIRCH_TABLE.get(), ModBlocks.SPRUCE_TABLE.get(), ModBlocks.ACACIA_TABLE.get(),
                    ModBlocks.JUNGLE_TABLE.get(), ModBlocks.DARK_OAK_TABLE.get(), ModBlocks.MANGROVE_TABLE.get(), ModBlocks.CHERRY_TABLE.get(), ModBlocks.BAMBOO_TABLE.get(),
                    ModBlocks.PALE_OAK_TABLE.get(), ModBlocks.CRIMSON_TABLE.get(), ModBlocks.WARPED_TABLE.get()
            ));
    public static final Supplier<BlockEntityType<ShelfBlockEntity>> SHELF_BE =
            BLOCK_ENTITIES.register("shelf_be", () -> new BlockEntityType<>(
                    ShelfBlockEntity::new, ModBlocks.OAK_SHELF.get()
            ));
    public static final Supplier<BlockEntityType<BreezingStandBlockEntity>> BREEZING_STAND_BE =
            BLOCK_ENTITIES.register("breezing_stand_be", () -> new BlockEntityType<>(
                    BreezingStandBlockEntity::new, ModBlocks.BREEZING_STAND.get()
            ));
    public static final Supplier<BlockEntityType<EndCampfireBlockEntity>> END_CAMPFIRE_BE =
            BLOCK_ENTITIES.register("end_campfire_be", () -> new BlockEntityType<>(
                    EndCampfireBlockEntity::new, ModBlocks.END_CAMPFIRE.get()
            ));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
