package net.seppevdh.natureupdate.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.seppevdh.natureupdate.block.entity.ModBlockEntities;
import net.seppevdh.natureupdate.recipe.BreezingRecipe;
import net.seppevdh.natureupdate.recipe.BreezingRecipeInput;
import net.seppevdh.natureupdate.recipe.ModRecipes;
import net.seppevdh.natureupdate.recipe.fueling.ModFuelingTypes;
import net.seppevdh.natureupdate.screen.custom.BreezingStandMenu;
import net.seppevdh.natureupdate.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class BreezingStandBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemHandler = new ItemStackHandler(6);

    private static final int[] INPUT_SLOTS = new int[]{0, 1, 2, 3};
    private static final int FUEL_SLOT = 4;
    private static final int OUTPUT_SLOT = 5;
    private static final int FUEL_USES = 4;
    public static final int BREWING_TIME = 600;

    private int fuel = 0;
    private int brewTime = 0;

    protected final ContainerData data = new ContainerData() {
        @Override
        public int get(int i) {
            return switch (i) {
                case 0 -> BreezingStandBlockEntity.this.brewTime;
                case 1 -> BreezingStandBlockEntity.this.fuel;
                default -> 0;
            };
        }

        @Override
        public void set(int i, int value) {
            switch (i) {
                case 0:
                    BreezingStandBlockEntity.this.brewTime = value;
                    break;
                case 1:
                    BreezingStandBlockEntity.this.fuel = value;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    public BreezingStandBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.BREEZING_STAND_BE.get(), pos, blockState);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("name.natureupdate.breezing_stand");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new BreezingStandMenu(containerId, playerInventory, this, this.data);
    }

    public void tick (Level level, BlockPos pos, BlockState state) {
        if (!hasFuel() && hasFuelItem()) useFuelItem();
        if (hasRecipe() && shouldContinue() && hasValidFuels()) {
            if (startedBrewing()) fuel--;
            increaseProgress();
            if (isDoneBrewing()) brewFluid();
        } else {
            brewTime = 0;
        }
    }

    private boolean hasValidFuels() {
        if (getCurrentRecipe().isEmpty() || level == null) return false;
        Optional<ModFuelingTypes> oFire = getCurrentRecipe().get().fireType();
        if (oFire.isEmpty()) return true;
        ModFuelingTypes fire = oFire.get();
        for (BlockPos pos : new BlockPos[]{worldPosition.north(), worldPosition.east(), worldPosition.south(), worldPosition.west()}) {
            if (!fire.isValid(level.getBlockState(pos))) return false;
        }
        return true;
    }

    private boolean shouldContinue () {
        return brewTime > 0 || fuel > 0;
    }

    private void useFuelItem () {
        itemHandler.extractItem(FUEL_SLOT, 1, false);
        fuel += FUEL_USES;
    }

    private boolean hasFuel () {
        return fuel > 0;
    }

    private void increaseProgress () {
        brewTime++;
    }

    private boolean isDoneBrewing () {
        return brewTime >= BREWING_TIME;
    }

    private boolean hasFuelItem () {
        return itemHandler.getStackInSlot(FUEL_SLOT).is(ModTags.Items.BREEZING_FUEL);
    }

    private boolean hasRecipe () {
        return getCurrentRecipe().isPresent();
    }

    private void brewFluid () {
        assert level != null;
        BreezingRecipe recipe = getCurrentRecipe().get();
        RandomSource random = level.getRandom();

        if (random.nextFloat() <= recipe.brewingChance()) {
            itemHandler.setStackInSlot(OUTPUT_SLOT, recipe.output().copy());
            LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(level, EntitySpawnReason.EVENT);
            if (lightningbolt != null) {
                lightningbolt.snapTo(Vec3.atBottomCenterOf(worldPosition));
                lightningbolt.setVisualOnly(false);
                level.addFreshEntity(lightningbolt);
            }
        } else itemHandler.setStackInSlot(OUTPUT_SLOT, recipe.defaultResult().copy());

        for (int i : INPUT_SLOTS) {
            itemHandler.extractItem(i, 1, false);
        }
        level.explode(
                null,
                null,
                new SimpleExplosionDamageCalculator(false, true, Optional.of(1.22f),
                        BuiltInRegistries.BLOCK.get(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity())),
                worldPosition.getX(),
                worldPosition.getY(),
                worldPosition.getZ(),
                2.5f,
                false,
                Level.ExplosionInteraction.TRIGGER,
                ParticleTypes.GUST_EMITTER_SMALL,
                ParticleTypes.GUST_EMITTER_LARGE,
                SoundEvents.WIND_CHARGE_BURST
        );
        for (BlockPos pos : new BlockPos[]{worldPosition.north(), worldPosition.east(), worldPosition.south(), worldPosition.west()}) {
            if (level.getBlockState(pos).getBlock() instanceof CampfireBlock) {
                level.setBlock(pos, level.getBlockState(pos).setValue(CampfireBlock.LIT, false), 2);
            } else if (level.getBlockState(pos).getBlock() instanceof BaseFireBlock) {
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
            }
        }
        this.brewTime = 0;
    }

    private boolean startedBrewing () {
        return brewTime == 0;
    }

    private Optional<BreezingRecipe> getCurrentRecipe () {
        return ((ServerLevel) level).recipeAccess().getRecipeFor(ModRecipes.BREEZING_TYPE.get(),
                new BreezingRecipeInput(getItems(), itemHandler.getStackInSlot(OUTPUT_SLOT)), level).map(RecipeHolder::value);
    }

    private List<ItemStack> getItems () {
        return List.of(
                itemHandler.getStackInSlot(0),
                itemHandler.getStackInSlot(1),
                itemHandler.getStackInSlot(2),
                itemHandler.getStackInSlot(3)
        );
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("breezing_stand.inventory", itemHandler.serializeNBT(registries));
        tag.putInt("breezing_stand.fuel", fuel);
        tag.putInt("breezing_stand.brew_time", brewTime);

        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        itemHandler.deserializeNBT(registries, tag);
        tag.getInt("breezing_stand.fuel").ifPresent(num -> fuel = num);
        tag.getInt("breezing_stand.brew_time").ifPresent(num -> brewTime = num);

        super.loadAdditional(tag, registries);
    }

    private void drops () {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        drops();
        super.preRemoveSideEffects(pos, state);
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

    public int getProgress () {
        return brewTime;
    }

    public List<ItemStack> getContent () {
        return List.of(
                itemHandler.getStackInSlot(INPUT_SLOTS[0]),
                itemHandler.getStackInSlot(INPUT_SLOTS[1]),
                itemHandler.getStackInSlot(INPUT_SLOTS[2]),
                itemHandler.getStackInSlot(INPUT_SLOTS[3]),
                itemHandler.getStackInSlot(FUEL_SLOT),
                itemHandler.getStackInSlot(OUTPUT_SLOT)
        );
    }

    public int getFuelLevel () {
        return fuel;
    }
}
