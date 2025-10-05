package net.seppevdh.natureupdate.screen.slot;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.seppevdh.natureupdate.util.ModTags;

public class BreezingFuelSlotItemHandler extends SlotItemHandler {
    public BreezingFuelSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return super.mayPlace(stack) && stack.is(ModTags.Items.BREEZING_FUEL);
    }
}
