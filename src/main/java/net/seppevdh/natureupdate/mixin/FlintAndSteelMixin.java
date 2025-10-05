package net.seppevdh.natureupdate.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.advancement.ModAdvancementTriggers;
import net.seppevdh.natureupdate.advancement.custom.EndFireLightTrigger;
import net.seppevdh.natureupdate.block.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({FlintAndSteelItem.class})
public abstract class FlintAndSteelMixin extends Item {
    public FlintAndSteelMixin(Properties properties) {
        super(properties);
    }

    @Inject(
            at = @At("HEAD"),
            method = "useOn"
    )
    private void useOn (UseOnContext context, CallbackInfoReturnable<InteractionResult> callback) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
        if (BaseFireBlock.getState(level, pos).getBlock() == ModBlocks.END_FIRE.get() && context.getPlayer() instanceof ServerPlayer player) {
            ((EndFireLightTrigger) ModAdvancementTriggers.END_FIRE_LIGHT.get()).trigger(player);
        }
    }
}
