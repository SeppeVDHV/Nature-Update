package net.seppevdh.natureupdate.block.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.block.custom.CraftingFurnaceBlock;
import net.seppevdh.natureupdate.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.seppevdh.natureupdate.recipe.*;
import net.seppevdh.natureupdate.recipe.fueling.ModFuelingTypes;
import net.seppevdh.natureupdate.recipe.pattern.ShapedCraftingFurnaceRecipePattern;
import net.seppevdh.natureupdate.screen.custom.CraftingFurnaceMenu;
import net.seppevdh.natureupdate.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class CraftingFurnaceBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemHandler = new ItemStackHandler(11) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    public static final int[][] INPUT_SLOTS = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8}
    };
    public static final int FUEL_SLOT = 9;
    public static final int OUTPUT_SLOT = 10;

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 200;
    private int fuel = 0;
    private int fuelLevel = 0;
    private int maxFuel = 0;
    private int maxFuelLevel = 13;
    private float xpLevel = 0;
    private int progressSpeedMultiplier = 1;
    private ModFuelingTypes fuelType = ModFuelingTypes.NORMAL;

    public CraftingFurnaceBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.CRAFTING_FURNACE_BE.get(), pPos, pBlockState);
        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> CraftingFurnaceBlockEntity.this.progress;
                    case 1 -> CraftingFurnaceBlockEntity.this.fuel;
                    case 2 -> CraftingFurnaceBlockEntity.this.fuelLevel;
                    case 3 -> CraftingFurnaceBlockEntity.this.maxFuel;
                    case 4 -> CraftingFurnaceBlockEntity.this.maxProgress;
                    case 5 -> switch (CraftingFurnaceBlockEntity.this.fuelType) {
                        case NORMAL -> 0;
                        case SOUL -> 1;
                        case ENDER -> 2;
                    };
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0: CraftingFurnaceBlockEntity.this.progress = value;
                    case 1: CraftingFurnaceBlockEntity.this.fuel = value;
                    case 2: CraftingFurnaceBlockEntity.this.fuelLevel = value;
                    case 3: CraftingFurnaceBlockEntity.this.maxFuel = value;
                    case 4: CraftingFurnaceBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 6;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("name.natureupdate.crafting_furnace");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new CraftingFurnaceMenu(i, inventory, this, this.data);
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        drops();
        dropXp();
        super.preRemoveSideEffects(pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("inventory", itemHandler.serializeNBT(pRegistries));
        pTag.putInt("crafting_furnace.progress", progress);
        pTag.putInt("crafting_furnace.max_progress", maxProgress);
        pTag.putInt("crafting_furnace.fuel", fuel);
        pTag.putInt("crafting_furnace.max_fuel", maxFuel);
        pTag.putInt("crafting_furnace.fuel_level", fuelLevel);
        pTag.putInt("crafting_furnace.max_fuel_level", maxFuelLevel);
        pTag.putFloat("crafting_furnace.experience", xpLevel);
        pTag.putString("crafting_furnace.fuel_type", fuelType.getName());
        pTag.putInt("crafting_furnace.speed_multiplier", progressSpeedMultiplier);

        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);

        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory").get());
        progress = pTag.getInt("crafting_furnace.progress").get();
        maxProgress = pTag.getInt("crafting_furnace.max_progress").get();
        fuel = pTag.getInt("crafting_furnace.fuel").get();
        maxFuel = pTag.getInt("crafting_furnace.max_fuel").get();
        fuelLevel = pTag.getInt("crafting_furnace.fuel_level").get();
        maxFuelLevel = pTag.getInt("crafting_furnace.max_fuel_level").get();
        xpLevel = pTag.getFloat("crafting_furnace.experience").get();
        fuelType = ModFuelingTypes.getTypeByName(pTag.getString("crafting_furnace.fuel_type").get());
        progressSpeedMultiplier = pTag.getInt("crafting_furnace.speed_multiplier").get();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public void tick(Level level, BlockPos pPos, BlockState pState) {
        updateBlockState(level, pPos, pState);
        if (hasRecipe()) {
            if (!hasFuel() && hasValidFuelItem()) {
                newFuel();
            } else if (hasFuel()) {
                decreaseFuel();
            } else {
                decreaseProgress();
            }
            if (hasFuel()) {
                updateMaxProgress();
                increaseProgress();
                if (hasCraftingFinished()) {
                    craftItem();
                    resetProgress();
                }
            }
        } else {
            resetProgress();
            if (hasFuel()) {
                decreaseFuel();
            }
        }
    }

    private void updateBlockState (Level level, BlockPos pos, BlockState state) {
        if (hasFuel()) {
            BlockState newState = state.setValue(CraftingFurnaceBlock.LIT, true).setValue(CraftingFurnaceBlock.FUEL_TYPE, this.fuelType);
            if (!state.equals(newState)) {
                level.setBlock(pos, newState, 2);
            }
        } else {
            if (level.getBlockState(pos).getValue(CraftingFurnaceBlock.LIT)) {
                level.setBlockAndUpdate(pos, state.setValue(CraftingFurnaceBlock.LIT, false));
            }
        }
    }

    private void newFuel() {
        assert this.level != null;
        int burnTime = itemHandler.getStackInSlot(FUEL_SLOT).getBurnTime(RecipeType.SMELTING, this.level.fuelValues());
        if (burnTime > 0) {
            if (itemHandler.getStackInSlot(FUEL_SLOT).is(ModTags.Items.ENDER_FUELING)) {
                fuelType = ModFuelingTypes.ENDER;
            } else if (itemHandler.getStackInSlot(FUEL_SLOT).is(ModTags.Items.SOUL_FUELING)) {
                fuelType = ModFuelingTypes.SOUL;
            } else {fuelType = ModFuelingTypes.NORMAL;}
            if (itemHandler.getStackInSlot(FUEL_SLOT).getCraftingRemainder() == ItemStack.EMPTY) {
                itemHandler.extractItem(FUEL_SLOT, 1, false);
            } else {
                itemHandler.setStackInSlot(FUEL_SLOT, new ItemStack(itemHandler.getStackInSlot(FUEL_SLOT).getCraftingRemainder().getItem(), 1));
            }

            ModFuelingTypes recipeFuelType = ModFuelingTypes.NORMAL;
            if (isRecipeShaped()) recipeFuelType = getCurrentRecipe().get().value().fuelType();
            else if (getCurrentShapelessRecipe().isPresent()) recipeFuelType = getCurrentShapelessRecipe().get().value().fuelType();
            this.progressSpeedMultiplier = ModFuelingTypes.getSpeedMultiplier(this.fuelType, recipeFuelType);

            fuel = burnTime / 2;
            maxFuel = burnTime / 2;
            fuelLevel = 13;
        }
    }

    private boolean hasValidFuelItem() {
        assert this.level != null;
        if (!hasRecipe()) return false;
        ModFuelingTypes neededType;
        if (isRecipeShaped()) {
            neededType = getCurrentRecipe().get().value().fuelType();
        } else {
            neededType = getCurrentShapelessRecipe().get().value().fuelType();
        }
        ModFuelingTypes type = ModFuelingTypes.NORMAL;
        if (itemHandler.getStackInSlot(FUEL_SLOT).is(ModTags.Items.ENDER_FUELING)) {
            type = ModFuelingTypes.ENDER;
        } else if (itemHandler.getStackInSlot(FUEL_SLOT).is(ModTags.Items.SOUL_FUELING)) {
            type = ModFuelingTypes.SOUL;
        }
        return itemHandler.getStackInSlot(FUEL_SLOT).getBurnTime(RecipeType.SMELTING, this.level.fuelValues()) > 0 && ModFuelingTypes.isSufficient(type, neededType);
    }

    private void decreaseFuel() {
        fuel--;
        fuelLevel = (int) Math.ceil(fuel * 13 / (float) maxFuel);
    }

    private void decreaseProgress() {
        progress -= 10;
        if (progress < 0) {
            progress = 0;
        }
    }

    private boolean hasFuel() {
        return fuel > 0;
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        if (hasRecipe()) {
            ItemStack output;
            float xp;
            if (isRecipeShaped()) {
                output = getCurrentRecipe().get().value().result().copy();
                xp = getCurrentRecipe().get().value().experience();
            } else {
                output = getCurrentShapelessRecipe().get().value().result().copy();
                xp = getCurrentShapelessRecipe().get().value().experience();
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (itemHandler.getStackInSlot(INPUT_SLOTS[i][j]).getCraftingRemainder() == ItemStack.EMPTY) {
                        itemHandler.extractItem(INPUT_SLOTS[i][j], 1, false);
                    } else {
                        itemHandler.setStackInSlot(INPUT_SLOTS[i][j], new ItemStack(itemHandler.getStackInSlot(INPUT_SLOTS[i][j]).getCraftingRemainder().getItem(), 1));
                    }
                }
            }
            itemHandler.insertItem(OUTPUT_SLOT, output, false);
            this.xpLevel += xp;
        }
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseProgress() {
        progress += progressSpeedMultiplier;
    }

    private void updateMaxProgress() {
        if (hasRecipe()) {
            if (isRecipeShaped()) {
                maxProgress = getCurrentRecipe().get().value().cookingTime();
            } else {
                maxProgress = getCurrentShapelessRecipe().get().value().cookingTime();
            }
        }
    }

    private boolean isOutputSlotEmpty () {
        return itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty();
    }

    private boolean canAddToOutput (ItemStack stack) {
        ItemStack outStack = itemHandler.getStackInSlot(OUTPUT_SLOT).copy();
        return (stack.is(outStack.getItem())) && ((stack.getCount() + outStack.getCount()) <= stack.getItem().getMaxStackSize(stack))
                && stack.getComponents().equals(outStack.getComponents());
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<CraftingFurnaceRecipe>> recipe = getCurrentRecipe();
        Optional<RecipeHolder<ShapelessCraftingFurnaceRecipe>> shapelessRecipe = getCurrentShapelessRecipe();
        if (recipe.isEmpty() && shapelessRecipe.isEmpty()) {
            return false;
        } else if (recipe.isEmpty()) {
            ItemStack output = shapelessRecipe.get().value().result().copy();
            return isOutputSlotEmpty() || canAddToOutput(output);
        }

        ItemStack output = recipe.get().value().result().copy();
        return isOutputSlotEmpty() || canAddToOutput(output);
    }

    private boolean isRecipeShaped () {
        if (hasRecipe()) {
            return getCurrentRecipe().isPresent();
        }
        return false;
    }

    private Optional<RecipeHolder<CraftingFurnaceRecipe>> getCurrentRecipe() {
        assert this.level != null;
        if (getInputSlotsItems().isEmpty()) {
            return Optional.empty();
        }
        return ((ServerLevel) this.level).recipeAccess()
                .getRecipeFor(ModRecipes.CRAFTING_FURNACE_TYPE.get(), new CraftingFurnaceRecipeInput(new ShapedCraftingFurnaceRecipePattern(3, 3, this.getInputSlotsItems(), null), this.fuelType), level);
    }

    private Optional<RecipeHolder<ShapelessCraftingFurnaceRecipe>> getCurrentShapelessRecipe() {
        assert this.level != null;
        if (getInputSlotsItems().isEmpty()) {
            return Optional.empty();
        }
        return ((ServerLevel) this.level).recipeAccess()
                .getRecipeFor(ModRecipes.SHAPELESS_CRAFTING_FURNACE_TYPE.get(), new CraftingFurnaceRecipeInput(new ShapedCraftingFurnaceRecipePattern(3, 3, this.getInputSlotsItems(), null), this.fuelType), level);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    private List<Optional<Ingredient>> getInputSlotsItems () {
        return List.of(
                toIngredient(itemHandler.getStackInSlot(INPUT_SLOTS[0][0])),
                toIngredient(itemHandler.getStackInSlot(INPUT_SLOTS[0][1])),
                toIngredient(itemHandler.getStackInSlot(INPUT_SLOTS[0][2])),
                toIngredient(itemHandler.getStackInSlot(INPUT_SLOTS[1][0])),
                toIngredient(itemHandler.getStackInSlot(INPUT_SLOTS[1][1])),
                toIngredient(itemHandler.getStackInSlot(INPUT_SLOTS[1][2])),
                toIngredient(itemHandler.getStackInSlot(INPUT_SLOTS[2][0])),
                toIngredient(itemHandler.getStackInSlot(INPUT_SLOTS[2][1])),
                toIngredient(itemHandler.getStackInSlot(INPUT_SLOTS[2][2]))
        );
    }

    public List<ItemStack> getContent () {
        return List.of(
                itemHandler.getStackInSlot(INPUT_SLOTS[0][0]),
                itemHandler.getStackInSlot(INPUT_SLOTS[0][1]),
                itemHandler.getStackInSlot(INPUT_SLOTS[0][2]),
                itemHandler.getStackInSlot(INPUT_SLOTS[1][0]),
                itemHandler.getStackInSlot(INPUT_SLOTS[1][1]),
                itemHandler.getStackInSlot(INPUT_SLOTS[1][2]),
                itemHandler.getStackInSlot(INPUT_SLOTS[2][0]),
                itemHandler.getStackInSlot(INPUT_SLOTS[2][1]),
                itemHandler.getStackInSlot(INPUT_SLOTS[2][2]),
                itemHandler.getStackInSlot(FUEL_SLOT),
                itemHandler.getStackInSlot(OUTPUT_SLOT)
        );
    }

    public int getProgress () {
        return this.progress;
    }

    public int getMaxProgress () {
        return this.maxProgress;
    }

    public ModFuelingTypes getFuelType () {
        return this.fuelType;
    }

    public int getFuelLevel () {
        return this.fuelLevel;
    }

    private Optional<Ingredient> toIngredient (ItemStack item) {
        return item.getItem() == Items.AIR ? Optional.empty() : Optional.of(Ingredient.of(item.getItem()));
    }

    public void dropXp () {
        assert this.level != null;
        if (!this.level.isClientSide()) {
            ExperienceOrb.award((ServerLevel) this.level, Vec3.atCenterOf(this.getBlockPos()), (int) Math.floor(this.xpLevel));
        }
        this.xpLevel = 0f;
    }
}
