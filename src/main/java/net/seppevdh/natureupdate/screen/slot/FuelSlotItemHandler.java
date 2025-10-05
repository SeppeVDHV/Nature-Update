package net.seppevdh.natureupdate.screen.slot;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class FuelSlotItemHandler extends SlotItemHandler {
    public FuelSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        assert Minecraft.getInstance().level != null;
        return super.mayPlace(stack) && stack.getBurnTime(RecipeType.SMELTING, Minecraft.getInstance().level.fuelValues()) > 0;
    }
}
