package net.seppevdh.natureupdate.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.seppevdh.natureupdate.block.custom.ShelfBlock;
import net.seppevdh.natureupdate.block.entity.ModBlockEntities;

public class ShelfBlockEntity extends BlockEntity {
    public final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };
    private boolean hasToChangeState = false;
    private BlockState stateToChange = null;

    public ShelfBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.SHELF_BE.get(), pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("inventory", itemHandler.serializeNBT(pRegistries));
        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        drops();
        super.preRemoveSideEffects(pos, state);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory").get());
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public void tick (Level level, BlockPos pos, BlockState state) {
        if (!hasToChangeState) {
            ((ShelfBlock) level.getBlockState(pos).getBlock()).tick(state, (ServerLevel) level, pos, level.random);
        } else {
            level.setBlock(pos, this.stateToChange, 2);
            this.hasToChangeState = false;
        }
    }

    public void scheduleUpdate (BlockState state) {
        this.hasToChangeState = true;
        this.stateToChange = state;
    }
}
