package net.seppevdh.natureupdate.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.fluid.ModFluids;
import net.seppevdh.natureupdate.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModFluidTagGenerator extends FluidTagsProvider {
    public ModFluidTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, NatureUpdate.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Fluids.SOUL_FLUID).add(
                ModFluids.SOURCE_SOUL_FLUID.get(),
                ModFluids.FLOWING_SOUL_FLUID.get()
        );
        this.tag(ModTags.Fluids.END_FLUID).add(
                ModFluids.SOURCE_END_FLUID.get(),
                ModFluids.FLOWING_END_FLUID.get()
        );
    }
}
