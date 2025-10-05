package net.seppevdh.natureupdate.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.seppevdh.natureupdate.block.ModBlocks;

public class FoodBushBlock extends SweetBerryBushBlock {
    private final String seed;
    private boolean ShouldDealDamage = false;

    public FoodBushBlock(Properties pProperties, String hoverItem) {
        super(pProperties);
        this.seed = hoverItem;
    }

    public FoodBushBlock(Properties pProperties, String hoverItem, boolean dealsDamageToEntitiesInside) {
        this(pProperties, hoverItem);
        this.ShouldDealDamage = dealsDamageToEntitiesInside;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        int i = pState.getValue(AGE);
        boolean flag = i == 3;
        if (i > 1) {
            int j = 1 + pLevel.random.nextInt(2);
            popResource(pLevel, pPos, new ItemStack(this.getSeed(), j + (flag ? 1 : 0)));
            pLevel.playSound(null, pPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
            BlockState blockstate = pState.setValue(AGE, Integer.valueOf(1));
            pLevel.setBlock(pPos, blockstate, 2);
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(pPlayer, blockstate));
            return InteractionResult.SUCCESS_SERVER;
        } else {
            return super.useWithoutItem(pState, pLevel, pPos, pPlayer, pHitResult);
        }
    }

    private Item getSeed () {
        return switch (this.seed) {
            case "strawberry" -> ModBlocks.STRAWBERRY.get().asItem();
            case "raspberry" -> ModBlocks.RASPBERRY.get().asItem();
            case "blackberry" -> ModBlocks.BLACKBERRY.get().asItem();
            default -> Items.AIR;
        };
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean b) {
        return new ItemStack(this.getSeed());
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier applier) {
        if (this.ShouldDealDamage) {
            super.entityInside(state, level, pos, entity, applier);
        } else {
            if (entity instanceof LivingEntity && entity.getType() != EntityType.FOX && entity.getType() != EntityType.BEE) {
                entity.makeStuckInBlock(state, new Vec3(0.8F, 0.75, 0.8F));
            }
        }
    }
}
