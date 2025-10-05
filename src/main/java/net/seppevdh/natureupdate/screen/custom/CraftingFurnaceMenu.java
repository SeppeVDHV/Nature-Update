package net.seppevdh.natureupdate.screen.custom;

import net.neoforged.neoforge.items.SlotItemHandler;
import net.seppevdh.natureupdate.block.ModBlocks;
import net.seppevdh.natureupdate.block.entity.custom.CraftingFurnaceBlockEntity;
import net.seppevdh.natureupdate.recipe.fueling.ModFuelingTypes;
import net.seppevdh.natureupdate.screen.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.seppevdh.natureupdate.screen.slot.FuelSlotItemHandler;
import net.seppevdh.natureupdate.screen.slot.OutputSlotItemHandler;

public class CraftingFurnaceMenu extends AbstractContainerMenu {
    public final CraftingFurnaceBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public CraftingFurnaceMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(6));
    }

    public CraftingFurnaceMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.CRAFTING_FURNACE_MENU.get(), pContainerId);
        blockEntity = ((CraftingFurnaceBlockEntity) entity);
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 0, 14, 9));
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 1, 32, 9));
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 2, 50, 9));
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 3, 14, 27));
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 4, 32, 27));
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 5, 50, 27));
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 6, 14, 45));
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 7, 32, 45));
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 8, 50, 45));
        this.addSlot(new FuelSlotItemHandler(blockEntity.itemHandler, 9, 83, 63));
        this.addSlot(new OutputSlotItemHandler(blockEntity, blockEntity.itemHandler, 10, 120, 28));

        addDataSlots(data);
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getFuelType () {
        return this.data.get(5);
    }

    public int getScaledArrowProgress() {
        return (int) Math.floor(this.data.get(0) * 24 / (float) this.data.get(4));
    }

    public int getScaledFuelLevel() {
        return this.data.get(2);
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    private static final int TE_INVENTORY_SLOT_COUNT = 11;
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        //if (pIndex == CraftingFurnaceBlockEntity.OUTPUT_SLOT) {return null;}
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlocks.CRAFTING_FURNACE.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 90 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 148));
        }
    }
}
