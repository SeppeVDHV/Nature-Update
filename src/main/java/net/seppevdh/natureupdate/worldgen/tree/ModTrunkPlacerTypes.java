package net.seppevdh.natureupdate.worldgen.tree;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.worldgen.tree.custom.DoubleTrunkPlacer;

import java.util.function.Supplier;

public class ModTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, NatureUpdate.MOD_ID);

    public static final Supplier<TrunkPlacerType<DoubleTrunkPlacer>> DOUBLE_TRUNK_PLACER =
            TRUNK_PLACER.register("double_trunk_placer", () -> new TrunkPlacerType<>(DoubleTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus) {
        TRUNK_PLACER.register(eventBus);
    }
}