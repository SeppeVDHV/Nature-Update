package net.seppevdh.natureupdate.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DamageResistant;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.seppevdh.natureupdate.block.custom.CraftingFurnaceBlock;
import net.seppevdh.natureupdate.block.entity.ModBlockEntities;
import net.seppevdh.natureupdate.recipe.*;
import net.seppevdh.natureupdate.screen.custom.WaterBoilerMenu;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class WaterBoilerBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private final int INPUT_SLOT = 0;
    private final int FUEL_SLOT = 1;
    private final int OUTPUT_SLOT = 2;

    protected final ContainerData data;
    private int progress = 0;
    private final int MAX_PROGRESS = 200;
    private int fuel = 0;
    private int fuelLevel = 0;
    private int maxFuel = 0;
    private int maxFuelLevel = 13;
    private float xpLevel = 0f;
    private Fluid storedFluid = Fluids.EMPTY;
    private int fluidAmount = 0;
    public static final int maxFluidSize = 1000;

    public WaterBoilerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.WATER_BOILER_BE.get(), pPos, pBlockState);
        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> WaterBoilerBlockEntity.this.progress;
                    case 1 -> WaterBoilerBlockEntity.this.fuel;
                    case 2 -> WaterBoilerBlockEntity.this.fuelLevel;
                    case 3 -> WaterBoilerBlockEntity.this.maxFuel;
                    case 4 -> WaterBoilerBlockEntity.this.maxFuelLevel;
                    case 5 -> WaterBoilerBlockEntity.this.fluidAmount;
                    default -> 0;
                };
            }

            public Fluid getStoredFluidLevel () {
                return WaterBoilerBlockEntity.this.storedFluid;
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0: WaterBoilerBlockEntity.this.progress = value;
                    case 1: WaterBoilerBlockEntity.this.fuel = value;
                    case 2: WaterBoilerBlockEntity.this.fuelLevel = value;
                    case 3: WaterBoilerBlockEntity.this.maxFuel = value;
                    case 4: WaterBoilerBlockEntity.this.maxFuelLevel = value;
                    case 5: WaterBoilerBlockEntity.this.fluidAmount = value;
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
        return Component.translatable("name.natureupdate.water_boiler");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new WaterBoilerMenu(i, inventory, this, this.data);
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
        pTag.putInt("water_boiler.progress", progress);
        pTag.putInt("water_boiler.fuel", fuel);
        pTag.putInt("water_boiler.max_fuel", maxFuel);
        pTag.putInt("water_boiler.fuel_level", fuelLevel);
        pTag.putInt("water_boiler.max_fuel_level", maxFuelLevel);
        pTag.putFloat("water_boiler.experience", xpLevel);
        pTag.putInt("water_boiler.fluid_amount", fluidAmount);
        if (storedFluid != Fluids.EMPTY) {
            pTag.putBoolean("water_boiler.empty", false);
            pTag.put("water_boiler.fluid", (new FluidStack(storedFluid, 1)).save(pRegistries));
        } else pTag.putBoolean("water_boiler.empty", true);

        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        tag.getCompound("inventory").ifPresent(tag1 ->
                itemHandler.deserializeNBT(registries, tag1)
        );
        tag.getInt("water_boiler.progress").ifPresent(num -> progress = num);
        tag.getInt("water_boiler.fuel").ifPresent(num -> fuel = num);
        tag.getInt("water_boiler.max_fuel").ifPresent(num -> maxFuel = num);
        tag.getInt("water_boiler.fuel_level").ifPresent(num -> fuelLevel = num);
        tag.getInt("water_boiler.max_fuel_level").ifPresent(num -> maxFuelLevel = num);
        tag.getFloat("water_boiler.experience").ifPresent(num -> xpLevel = num);
        tag.getInt("water_boiler.fluid_amount").ifPresent(num -> fluidAmount = num);
        tag.getBoolean("water_boiler.empty").ifPresent(bool -> {
            if (bool) {
                storedFluid = Fluids.EMPTY;
            } else {
                FluidStack.parse(registries, tag.get("water_boiler.fluid")).ifPresent(stack -> storedFluid = stack.getFluid());
            }
        });
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public void tick(Level level, BlockPos pPos, BlockState pState) {
        if (hasFuel()) {
            level.setBlockAndUpdate(pPos, pState.setValue(CraftingFurnaceBlock.LIT, true));
        } else {
            level.setBlockAndUpdate(pPos, pState.setValue(CraftingFurnaceBlock.LIT, false));
        }
        if ((new FluidStack(storedFluid, fluidAmount)).is(Tags.Fluids.LAVA)) {
            if (!Objects.equals(itemHandler.getStackInSlot(INPUT_SLOT).getComponents().get(DataComponents.DAMAGE_RESISTANT), new DamageResistant(DamageTypeTags.IS_FIRE))) {
                itemHandler.extractItem(INPUT_SLOT, 1, false);
            }
            if (!Objects.equals(itemHandler.getStackInSlot(OUTPUT_SLOT).getComponents().get(DataComponents.DAMAGE_RESISTANT), new DamageResistant(DamageTypeTags.IS_FIRE))) {
                itemHandler.extractItem(OUTPUT_SLOT, 1, false);
            }
        }
        if (hasRecipe()) {
            if (!hasFuel() && hasValidFuelItem()) {
                newFuel();
            } else if (hasFuel()) {
                decreaseFuel();
            } else {
                decreaseProgress();
            }
            if (hasFuel()) {
                if (hasCraftingFinished()) {
                    craftItem();
                } else {
                    increaseProgress();
                }
            }
        } else {
            resetProgress();
            if (hasFuel()) {
                decreaseFuel();
            }
        }
    }

    private boolean hasFuel() {
        return this.data.get(1) > 0;
    }

    private void decreaseFuel() {
        fuel--;
        fuelLevel = (int) Math.ceil(fuel * 13 / maxFuel);
    }

    private void decreaseProgress() {
        progress -= 10;
        if (progress < 0) {
            progress = 0;
        }
    }

    private void newFuel() {
        assert this.level != null;
        int burnTime = itemHandler.getStackInSlot(FUEL_SLOT).getBurnTime(RecipeType.SMELTING, this.level.fuelValues());
        if (burnTime > 0) {
            if (itemHandler.getStackInSlot(FUEL_SLOT).getCraftingRemainder() == ItemStack.EMPTY) {
                itemHandler.extractItem(FUEL_SLOT, 1, false);
            } else {
                itemHandler.setStackInSlot(FUEL_SLOT, new ItemStack(itemHandler.getStackInSlot(FUEL_SLOT).getCraftingRemainder().getItem(), 1));
            }
            fuel = burnTime;
            maxFuel = burnTime;
            fuelLevel = 13;
        }
    }

    private boolean hasValidFuelItem() {
        assert this.level != null;
        return itemHandler.getStackInSlot(FUEL_SLOT).getBurnTime(RecipeType.SMELTING, this.level.fuelValues()) > 0;
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        Optional<RecipeHolder<WaterBoilerRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output().copy();
        int amount = recipe.get().value().fluid().getAmount();
        float xp = recipe.get().value().experience();

        this.itemHandler.extractItem(INPUT_SLOT, 1, false);
        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(output.getItem(), this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + output.getCount()));
        this.xpLevel += xp;
        this.fluidAmount -= amount;
        resetProgress();
    }

    private boolean hasCraftingFinished() {
        return progress >= MAX_PROGRESS;
    }

    private void increaseProgress() {
        progress++;
    }

    private boolean isOutputSlotEmptyOrReceivable(ItemStack itemStack) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                (this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + itemStack.getCount() <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize() &&
                        this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(itemStack.getItem()));
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<WaterBoilerRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().value().output();
        return isOutputSlotEmptyOrReceivable(output);
    }

    private Optional<RecipeHolder<WaterBoilerRecipe>> getCurrentRecipe() {
        assert this.level != null;
        return ((ServerLevel) this.level).recipeAccess().getRecipeFor(ModRecipes.WATER_BOILER_TYPE.get(), new WaterBoilerRecipeInput(itemHandler.getStackInSlot(INPUT_SLOT), new FluidStack(this.storedFluid, this.fluidAmount)), level);
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

    public void dropXp () {
        assert this.level != null;
        if (!this.level.isClientSide()) {
            ExperienceOrb.award((ServerLevel) this.level, Vec3.atCenterOf(this.getBlockPos()), (int) Math.floor(this.xpLevel));
        }
        this.xpLevel = 0f;
    }

    public List<ItemStack> getContent () {
        return List.of(
                itemHandler.getStackInSlot(INPUT_SLOT),
                itemHandler.getStackInSlot(FUEL_SLOT),
                itemHandler.getStackInSlot(OUTPUT_SLOT)
        );
    }

    public int getProgress () {
        return progress;
    }

    public int getMaxProgress() {
        return MAX_PROGRESS;
    }

    public void changeFluid (Fluid fluid) {
        this.storedFluid = fluid;
        this.fluidAmount = fluid == Fluids.EMPTY ? 0 : maxFluidSize;
    }

    public boolean canAddFluid (Fluid fluid) {
        return fluid == Fluids.EMPTY ? storedFluid != fluid && fluidAmount >= FluidType.BUCKET_VOLUME : storedFluid == Fluids.EMPTY;
    }

    public Fluid getStoredFluid () {
        return storedFluid;
    }

    public int getFluidAmount() {
        return fluidAmount;
    }
}