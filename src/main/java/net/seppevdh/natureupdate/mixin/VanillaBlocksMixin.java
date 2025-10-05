package net.seppevdh.natureupdate.mixin;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.seppevdh.natureupdate.worldgen.tree.ModTreeGrowers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Function;

@Mixin({Blocks.class})
public abstract class VanillaBlocksMixin {
    @Inject(
        method = "register",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void register (String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties, CallbackInfoReturnable<Block> callBack) {
        ResourceKey<Block> resourceKey = ResourceKey.create(Registries.BLOCK, ResourceLocation.withDefaultNamespace(name));
        switch (name) {
            case "torchflower":
                Block flower = factory.apply(properties.lightLevel(state -> 14).setId(resourceKey));
                callBack.setReturnValue(Registry.register(BuiltInRegistries.BLOCK, resourceKey, flower));
                break;
            case "oak_sapling":
                Block oak = new SaplingBlock(ModTreeGrowers.MOD_OAK, properties.setId(resourceKey));
                callBack.setReturnValue(Registry.register(BuiltInRegistries.BLOCK, resourceKey, oak));
                break;
            case "birch_sapling":
                Block birch = new SaplingBlock(ModTreeGrowers.MOD_BIRCH, properties.setId(resourceKey));
                callBack.setReturnValue(Registry.register(BuiltInRegistries.BLOCK, resourceKey, birch));
                break;
        }
    }
}
