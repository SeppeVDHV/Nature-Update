package net.seppevdh.natureupdate.fluid;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.block.ModBlocks;
import net.seppevdh.natureupdate.item.ModItems;

import java.util.function.Supplier;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(Registries.FLUID, NatureUpdate.MOD_ID);

    public static final Supplier<FlowingFluid> SOURCE_SOUL_FLUID = FLUIDS.register("source_soul_fluid",
            () -> new BaseFlowingFluid.Source(ModFluids.SOUL_FLUID_PROPERTIES));
    public static final Supplier<FlowingFluid> FLOWING_SOUL_FLUID = FLUIDS.register("flowing_soul_fluid",
            () -> new BaseFlowingFluid.Flowing(ModFluids.SOUL_FLUID_PROPERTIES));

    public static final Supplier<FlowingFluid> SOURCE_END_FLUID = FLUIDS.register("source_end_fluid",
            () -> new BaseFlowingFluid.Source(ModFluids.END_FLUID_PROPERTIES));
    public static final Supplier<FlowingFluid> FLOWING_END_FLUID = FLUIDS.register("flowing_end_fluid",
            () -> new BaseFlowingFluid.Flowing(ModFluids.END_FLUID_PROPERTIES));

    public static final DeferredBlock<LiquidBlock> SOUL_FLUID_BLOCK = ModBlocks.registerBlock("soul_fluid_block",
            (properties) -> new LiquidBlock(ModFluids.SOURCE_SOUL_FLUID.get(), properties.mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable()
                    .noCollission().strength(100f).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY)));
    public static final DeferredItem<Item> SOUL_FLUID_BUCKET = ModItems.ITEMS.registerItem("soul_fluid_bucket",
            (properties) -> new BucketItem(ModFluids.SOURCE_SOUL_FLUID.get(), properties.craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final DeferredBlock<LiquidBlock> END_FLUID_BLOCK = ModBlocks.registerBlock("end_fluid_block",
            (properties) -> new LiquidBlock(ModFluids.SOURCE_END_FLUID.get(), properties.mapColor(MapColor.COLOR_MAGENTA).replaceable()
                    .noCollission().strength(100f).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY)));
    public static final DeferredItem<Item> END_FLUID_BUCKET = ModItems.ITEMS.registerItem("end_fluid_bucket",
            (properties) -> new BucketItem(ModFluids.SOURCE_END_FLUID.get(), properties.craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final BaseFlowingFluid.Properties SOUL_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.SOUL_FLUID_TYPE, SOURCE_SOUL_FLUID, FLOWING_SOUL_FLUID)
            .slopeFindDistance(2).levelDecreasePerBlock(1).tickRate(12)
            .block(ModFluids.SOUL_FLUID_BLOCK).bucket(ModFluids.SOUL_FLUID_BUCKET);
    public static final BaseFlowingFluid.Properties END_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.END_FLUID_TYPE, SOURCE_END_FLUID, FLOWING_END_FLUID)
            .slopeFindDistance(2).levelDecreasePerBlock(1).tickRate(16)
            .block(ModFluids.END_FLUID_BLOCK).bucket(ModFluids.END_FLUID_BUCKET);

    public static void register (IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
