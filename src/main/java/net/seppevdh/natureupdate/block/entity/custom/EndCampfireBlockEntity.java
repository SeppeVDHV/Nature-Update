package net.seppevdh.natureupdate.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Clearable;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.seppevdh.natureupdate.block.entity.ModBlockEntities;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Optional;

public class EndCampfireBlockEntity extends BlockEntity implements Clearable {
    private final NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);
    private final int[] cookingProgress = new int[4];
    private final int[] cookingTime = new int[4];

    public EndCampfireBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.END_CAMPFIRE_BE.get(), pos, blockState);
    }

    public int[] getCookingProgress() {
        return cookingProgress;
    }

    public int[] getCookingTime() {
        return cookingTime;
    }

    public static void cookTick(ServerLevel level, BlockPos pos, BlockState state, EndCampfireBlockEntity campfire, RecipeManager.CachedCheck<SingleRecipeInput, CampfireCookingRecipe> check) {
        boolean flag = false;

        for (int i = 0; i < campfire.items.size(); i++) {
            ItemStack itemstack = campfire.items.get(i);
            if (!itemstack.isEmpty()) {
                flag = true;
                campfire.cookingProgress[i]++;
                if (campfire.cookingProgress[i] >= campfire.cookingTime[i]) {
                    SingleRecipeInput singlerecipeinput = new SingleRecipeInput(itemstack);
                    ItemStack itemstack1 = check.getRecipeFor(singlerecipeinput, level)
                            .map(p_409479_ -> p_409479_.value().assemble(singlerecipeinput, level.registryAccess()))
                            .orElse(itemstack);
                    if (itemstack1.isItemEnabled(level.enabledFeatures())) {
                        Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), itemstack1);
                        campfire.items.set(i, ItemStack.EMPTY);
                        level.sendBlockUpdated(pos, state, state, 3);
                        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(state));
                    }
                }
            }
        }

        if (flag) {
            setChanged(level, pos, state);
        }
    }

    public static void cooldownTick(Level level, BlockPos pos, BlockState state, EndCampfireBlockEntity blockEntity) {
        boolean flag = false;

        for (int i = 0; i < blockEntity.items.size(); i++) {
            if (blockEntity.cookingProgress[i] > 0) {
                flag = true;
                blockEntity.cookingProgress[i] = Mth.clamp(blockEntity.cookingProgress[i] - 2, 0, blockEntity.cookingTime[i]);
            }
        }

        if (flag) {
            setChanged(level, pos, state);
        }
    }

    public static void particleTick(Level level, BlockPos pos, BlockState state, EndCampfireBlockEntity blockEntity) {
        RandomSource randomsource = level.random;
        if (randomsource.nextFloat() < 0.11F) {
            for (int i = 0; i < randomsource.nextInt(2) + 2; i++) {
                CampfireBlock.makeParticles(level, pos, state.getValue(CampfireBlock.SIGNAL_FIRE), false);
            }
        }

        int l = state.getValue(CampfireBlock.FACING).get2DDataValue();

        for (int j = 0; j < blockEntity.items.size(); j++) {
            if (!blockEntity.items.get(j).isEmpty() && randomsource.nextFloat() < 0.2F) {
                Direction direction = Direction.from2DDataValue(Math.floorMod(j + l, 4));
                float f = 0.3125F;
                double d0 = pos.getX() + 0.5 - direction.getStepX() * 0.3125F + direction.getClockWise().getStepX() * 0.3125F;
                double d1 = pos.getY() + 0.5;
                double d2 = pos.getZ() + 0.5 - direction.getStepZ() * 0.3125F + direction.getClockWise().getStepZ() * 0.3125F;

                for (int k = 0; k < 4; k++) {
                    level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0, 5.0E-4, 0.0);
                }
            }
        }
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void loadAdditional(CompoundTag p_155312_, HolderLookup.Provider p_323804_) {
        super.loadAdditional(p_155312_, p_323804_);
        this.items.clear();
        ContainerHelper.loadAllItems(p_155312_, this.items, p_323804_);
        p_155312_.getIntArray("CookingTimes")
                .ifPresentOrElse(
                        p_409480_ -> System.arraycopy(p_409480_, 0, this.cookingProgress, 0, Math.min(this.cookingTime.length, p_409480_.length)),
                        () -> Arrays.fill(this.cookingProgress, 0)
                );
        p_155312_.getIntArray("CookingTotalTimes")
                .ifPresentOrElse(
                        p_409481_ -> System.arraycopy(p_409481_, 0, this.cookingTime, 0, Math.min(this.cookingTime.length, p_409481_.length)),
                        () -> Arrays.fill(this.cookingTime, 0)
                );
    }

    @Override
    protected void saveAdditional(CompoundTag p_187486_, HolderLookup.Provider p_324005_) {
        super.saveAdditional(p_187486_, p_324005_);
        ContainerHelper.saveAllItems(p_187486_, this.items, true, p_324005_);
        p_187486_.putIntArray("CookingTimes", this.cookingProgress);
        p_187486_.putIntArray("CookingTotalTimes", this.cookingTime);
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider p_324612_) {
        CompoundTag compoundtag = new CompoundTag();
        ContainerHelper.saveAllItems(compoundtag, this.items, true, p_324612_);
        return compoundtag;
    }

    public boolean placeFood(ServerLevel level, @Nullable LivingEntity entity, ItemStack stack) {
        for (int i = 0; i < this.items.size(); i++) {
            ItemStack itemstack = this.items.get(i);
            if (itemstack.isEmpty()) {
                Optional<RecipeHolder<CampfireCookingRecipe>> optional = level.recipeAccess()
                        .getRecipeFor(RecipeType.CAMPFIRE_COOKING, new SingleRecipeInput(stack), level);
                if (optional.isEmpty()) {
                    return false;
                }

                this.cookingTime[i] = optional.get().value().cookingTime();
                this.cookingProgress[i] = 0;
                this.items.set(i, stack.consumeAndReturn(1, entity));
                level.gameEvent(GameEvent.BLOCK_CHANGE, this.getBlockPos(), GameEvent.Context.of(entity, this.getBlockState()));
                this.markUpdated();
                return true;
            }
        }

        return false;
    }

    private void markUpdated() {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    @Override
    public void preRemoveSideEffects(BlockPos p_394031_, BlockState p_394253_) {
        if (this.level != null) {
            Containers.dropContents(this.level, p_394031_, this.getItems());
        }
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter p_397891_) {
        super.applyImplicitComponents(p_397891_);
        p_397891_.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyInto(this.getItems());
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder p_338620_) {
        super.collectImplicitComponents(p_338620_);
        p_338620_.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(this.getItems()));
    }

    @Override
    public void removeComponentsFromTag(CompoundTag p_332690_) {
        p_332690_.remove("Items");
    }
}
