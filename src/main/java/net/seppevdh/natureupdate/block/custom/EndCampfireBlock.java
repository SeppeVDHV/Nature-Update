package net.seppevdh.natureupdate.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.seppevdh.natureupdate.block.entity.ModBlockEntities;
import net.seppevdh.natureupdate.block.entity.custom.EndCampfireBlockEntity;

import javax.annotation.Nullable;

public class EndCampfireBlock extends CampfireBlock {
    public EndCampfireBlock(Properties properties) {
        super(false, 3, properties);
    }

    @Override
    protected InteractionResult useItemOn(
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult
    ) {
        if (level.getBlockEntity(pos) instanceof EndCampfireBlockEntity blockEntity) {
            ItemStack itemstack = player.getItemInHand(hand);
            if (level.recipeAccess().propertySet(RecipePropertySet.CAMPFIRE_INPUT).test(itemstack)) {
                if (level instanceof ServerLevel serverlevel && blockEntity.placeFood(serverlevel, player, itemstack)) {
                    player.awardStat(Stats.INTERACT_WITH_CAMPFIRE);
                    return InteractionResult.SUCCESS_SERVER;
                }

                return InteractionResult.CONSUME;
            } else if (stack.is(Items.FLINT_AND_STEEL) && !state.getValue(LIT)) {
                level.setBlock(pos, state.setValue(LIT, true), 3);
                stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack));
            }
        }

        return InteractionResult.TRY_WITH_EMPTY_HAND;
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier applier) {
        if (state.getValue(LIT) && entity instanceof LivingEntity) {
            entity.hurt(level.damageSources().campfire(), 3);
        }

        super.entityInside(state, level, pos, entity, applier);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockentity) {
        if (level instanceof ServerLevel serverlevel) {
            if (state.getValue(LIT)) {
                RecipeManager.CachedCheck<SingleRecipeInput, CampfireCookingRecipe> cachedcheck = RecipeManager.createCheck(RecipeType.CAMPFIRE_COOKING);
                return createTickerHelper(
                        blockentity,
                        ModBlockEntities.END_CAMPFIRE_BE.get(),
                        (level1, pos1, state1, blockEntity1) -> EndCampfireBlockEntity.cookTick(serverlevel, pos1, state1, blockEntity1, cachedcheck)
                );
            } else {
                return createTickerHelper(blockentity, ModBlockEntities.END_CAMPFIRE_BE.get(), EndCampfireBlockEntity::cooldownTick);
            }
        } else {
            return state.getValue(LIT) ? createTickerHelper(blockentity, ModBlockEntities.END_CAMPFIRE_BE.get(), EndCampfireBlockEntity::particleTick) : null;
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new EndCampfireBlockEntity(pos, state);
    }
}
