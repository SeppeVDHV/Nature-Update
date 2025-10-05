package net.seppevdh.natureupdate.screen.slot;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.seppevdh.natureupdate.block.entity.custom.CraftingFurnaceBlockEntity;
import net.seppevdh.natureupdate.block.entity.custom.WaterBoilerBlockEntity;

public class OutputSlotItemHandler extends SlotItemHandler {
    private BlockEntity blockEntity;

    public OutputSlotItemHandler(BlockEntity blockEntity, IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
        this.blockEntity = blockEntity;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return false;
    }

    @Override
    public void onTake(Player player, ItemStack stack) {
        if (blockEntity instanceof CraftingFurnaceBlockEntity craftingFurnace) {
            craftingFurnace.dropXp();
        } else if (blockEntity instanceof WaterBoilerBlockEntity waterBoiler) {
            waterBoiler.dropXp();
        }
    }
}
