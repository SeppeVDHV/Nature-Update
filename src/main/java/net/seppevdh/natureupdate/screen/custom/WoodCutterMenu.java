package net.seppevdh.natureupdate.screen.custom;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.seppevdh.natureupdate.block.ModBlocks;
import net.seppevdh.natureupdate.recipe.WoodCutterRecipe;
import net.seppevdh.natureupdate.recipe.build_in.listed.ModListedRecipes;
import net.seppevdh.natureupdate.recipe.pattern.WoodCutterRecipeInput;
import net.seppevdh.natureupdate.screen.ModMenuTypes;

import java.util.List;
import java.util.Optional;

public class WoodCutterMenu extends AbstractContainerMenu {
    public static final int INPUT_SLOT = 0;
    public static final int RESULT_SLOT = 1;
    private static final int INV_SLOT_START = 2;
    private static final int INV_SLOT_END = 29;
    private static final int USE_ROW_SLOT_START = 29;
    private static final int USE_ROW_SLOT_END = 38;
    private final ContainerLevelAccess access;

    final DataSlot selectedRecipeIndex = DataSlot.standalone();
    private final Level level;
    private List<WoodCutterRecipe> recipesForInput = List.of();

    private ItemStack input = ItemStack.EMPTY;

    long lastSoundTime;
    final Slot inputSlot;

    final Slot resultSlot;
    Runnable slotUpdateListener = () -> {};
    public final Container container = new SimpleContainer(1) {
        @Override
        public void setChanged() {
            super.setChanged();
            WoodCutterMenu.this.slotsChanged(this);
            WoodCutterMenu.this.slotUpdateListener.run();
        }
    };

    final ResultContainer resultContainer = new ResultContainer();

    public WoodCutterMenu(int i, Inventory itemStacks, RegistryFriendlyByteBuf friendlyByteBuf) {
        this(i, itemStacks, ContainerLevelAccess.NULL);
    }

    public WoodCutterMenu(int containerId, Inventory playerInventory, final ContainerLevelAccess access) {
        super(ModMenuTypes.WOODCUTTER_MENU.get(), containerId);
        this.access = access;
        this.level = playerInventory.player.level();
        this.inputSlot = this.addSlot(new Slot(this.container, 0, 20, 33));
        this.resultSlot = this.addSlot(new Slot(this.resultContainer, 1, 143, 33) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                int count = WoodCutterMenu.this.recipesForInput.get(WoodCutterMenu.this.getSelectedRecipeIndex()).count();
                if (WoodCutterMenu.this.inputSlot.getItem().getCount() >= count) {
                    stack.onCraftedBy(player, stack.getCount());
                    WoodCutterMenu.this.resultContainer.awardUsedRecipes(player, this.getRelevantItems());
                    ItemStack itemstack = WoodCutterMenu.this.inputSlot.remove(count);
                    if (!itemstack.isEmpty()) {
                        WoodCutterMenu.this.setupResultSlot(WoodCutterMenu.this.selectedRecipeIndex.get());
                    }

                    access.execute((level, pos) -> {
                        long i = level.getGameTime();
                        if (WoodCutterMenu.this.lastSoundTime != i) {
                            level.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F);
                            WoodCutterMenu.this.lastSoundTime = i;
                        }
                    });
                } else {
                    stack.shrink(stack.getCount());
                    WoodCutterMenu.this.slotsChanged(WoodCutterMenu.this.container);
                }
                super.onTake(player, stack);
            }

            private List<ItemStack> getRelevantItems() {
                return List.of(WoodCutterMenu.this.inputSlot.getItem());
            }
        });
        this.addStandardInventorySlots(playerInventory, 8, 84);
        this.addDataSlot(this.selectedRecipeIndex);
    }

    public int getSelectedRecipeIndex() {
        return this.selectedRecipeIndex.get();
    }

    public List<WoodCutterRecipe> getVisibleRecipes() {
        return this.recipesForInput;
    }

    public int getNumberOfVisibleRecipes() {
        return this.recipesForInput.size();
    }

    public boolean hasInputItem() {
        return this.inputSlot.hasItem() && !this.recipesForInput.isEmpty();
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, ModBlocks.WOODCUTTER.get());
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (this.selectedRecipeIndex.get() == id) {
            return false;
        } else {
            if (this.isValidRecipeIndex(id)) {
                this.selectedRecipeIndex.set(id);
                this.setupResultSlot(id);
            }

            return true;
        }
    }

    private boolean isValidRecipeIndex(int recipeIndex) {
        return recipeIndex >= 0 && recipeIndex < this.recipesForInput.size();
    }

    @Override
    public void slotsChanged(Container inventory) {
        ItemStack itemstack = this.inputSlot.getItem();
        if (!itemstack.is(this.input.getItem())) {
            this.input = itemstack.copy();
            this.setupRecipeList(itemstack);
        }
    }

    private void setupRecipeList(ItemStack stack) {
        this.selectedRecipeIndex.set(-1);
        this.resultSlot.set(ItemStack.EMPTY);
        if (!stack.isEmpty()) {
            this.recipesForInput = ModListedRecipes.getWoodCutterRecipes().stream().filter(r -> r.matches(new WoodCutterRecipeInput(stack), level)).toList();
        } else {
            this.recipesForInput = List.of();
        }
    }

    void setupResultSlot(int id) {
        Optional<WoodCutterRecipe> optional;
        if (!this.recipesForInput.isEmpty() && this.isValidRecipeIndex(id)) {
            WoodCutterRecipe recipe = this.recipesForInput.get(id);
            optional = Optional.of(recipe);
        } else {
            optional = Optional.empty();
        }

        optional.ifPresentOrElse(recipe -> {
            this.resultContainer.setRecipeUsed(new RecipeHolder<>(recipe.getResourceKey(), recipe));
            this.resultSlot.set(recipe.assemble(new WoodCutterRecipeInput(this.container.getItem(0)), this.level.registryAccess()));
        }, () -> {
            this.resultSlot.set(ItemStack.EMPTY);
            this.resultContainer.setRecipeUsed(null);
        });
        this.broadcastChanges();
    }

    @Override
    public MenuType<?> getType() {
        return ModMenuTypes.WOODCUTTER_MENU.get();
    }

    public void registerUpdateListener(Runnable listener) {
        this.slotUpdateListener = listener;
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.container != this.resultContainer && super.canTakeItemForPickAll(stack, slot);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            Item item = itemstack1.getItem();
            itemstack = itemstack1.copy();
            if (index == 1) {
                item.onCraftedBy(itemstack1, player);
                if (!this.moveItemStackTo(itemstack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index == 0) {
                if (!this.moveItemStackTo(itemstack1, 2, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!ModListedRecipes.getWoodCutterRecipes().stream().filter(recipe -> recipe.matches(new WoodCutterRecipeInput(inputSlot.getItem()), level)).toList().isEmpty()) {
                if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 2 && index < 29) {
                if (!this.moveItemStackTo(itemstack1, 29, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 29 && index < 38 && !this.moveItemStackTo(itemstack1, 2, 29, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            }

            slot.setChanged();
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
            if (index == 1) {
                player.drop(itemstack1, false);
            }

            this.broadcastChanges();
        }

        return itemstack;
    }
    
    @Override
    public void removed(Player player) {
        super.removed(player);
        this.resultContainer.removeItemNoUpdate(1);
        this.access.execute((p_40313_, p_40314_) -> this.clearContainer(player, this.container));
    }
}