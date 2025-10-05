package net.seppevdh.natureupdate.fluid;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.seppevdh.natureupdate.NatureUpdate;
import org.joml.Vector4f;

import java.util.function.Supplier;

public class ModFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, NatureUpdate.MOD_ID);

    public static final Supplier<FluidType> SOUL_FLUID_TYPE = registerFluidType("soul_fluid",
            new BaseFluidType(getStillTexture("soul_fluid"), getFlowingTexture("soul_fluid"), FluidType.Properties.create()
                    .canSwim(false).canDrown(true).density(1700).viscosity(2400)
                    .canExtinguish(false).canHydrate(false).supportsBoating(true).descriptionId("block.natureupdate.soul_fluid")));

    public static final Supplier<FluidType> END_FLUID_TYPE = registerFluidType("end_fluid",
            new BaseFluidType(getStillTexture("end_fluid"), getFlowingTexture("end_fluid"), FluidType.Properties.create()
                    .canSwim(false).canDrown(true).density(2200).viscosity(3500)
                    .canExtinguish(false).canHydrate(false).supportsBoating(true).descriptionId("block.natureupdate.end_fluid")));

    private static Supplier<FluidType> registerFluidType(String name, FluidType fluidType) {
        return FLUID_TYPES.register(name, () -> fluidType);
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }

    private static ResourceLocation getStillTexture (String name) {
        return ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "block/" + name + "_still");
    }

    private static ResourceLocation getFlowingTexture (String name) {
        return ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "block/" + name + "_flow");
    }
}
