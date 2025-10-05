package net.seppevdh.natureupdate.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.advancement.ModAdvancementTriggers;
import net.seppevdh.natureupdate.advancement.custom.EndFireLightTrigger;
import net.seppevdh.natureupdate.block.ModBlocks;
import net.seppevdh.natureupdate.block.custom.EndFireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({BaseFireBlock.class})
public abstract class FireBlockMixin extends Block {
    public FireBlockMixin(Properties p_49795_) {
        super(p_49795_);
    }

    @Inject(
        method = {"getState"},
        at = {@At("HEAD")},
        cancellable = true
    )
    private static void getState (BlockGetter getter, BlockPos pos, CallbackInfoReturnable<BlockState> callBack) {
        if (EndFireBlock.canSurviveOnBlock(getter.getBlockState(pos.below()))) {
            callBack.setReturnValue(ModBlocks.END_FIRE.get().defaultBlockState());
        }
    }
}
