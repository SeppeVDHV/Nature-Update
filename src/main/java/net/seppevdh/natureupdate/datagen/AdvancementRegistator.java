package net.seppevdh.natureupdate.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AdvancementRegistator {
    public static AdvancementProvider create (PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        return new AdvancementProvider(
                pOutput,
                pRegistries,
                List.of(
                        new ModAdvancementBuilder()
                )
        );
    }
}
