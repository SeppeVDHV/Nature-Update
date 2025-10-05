package net.seppevdh.natureupdate.item.custom;

import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.seppevdh.natureupdate.component.ModDataComponentTypes;
import net.seppevdh.natureupdate.component.custom.MoldFillerType;
import net.seppevdh.natureupdate.component.custom.MoldFillerTypes;
import net.seppevdh.natureupdate.recipe.build_in.mold.ItemUsingRecipes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MoldItem extends Item {
    private Map<String, Item> transfromingItems = Map.of();
    private List<Item> defaultTransformer = List.of();

    public MoldItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Component getName(ItemStack pStack) {
        String id = Util.makeDescriptionId("item", BuiltInRegistries.ITEM.getKey(this));
        if (pStack.get(ModDataComponentTypes.MOLD_FILLER_TYPE.get()) != MoldFillerTypes.EMPTY) {
            id = id + "_" + pStack.get(ModDataComponentTypes.MOLD_FILLER_TYPE.get()).id();
        }
        return Component.translatable(id);
    }

    @Override
    public ItemStack getCraftingRemainder(ItemStack itemStack) {
        if (itemStack.get(ModDataComponentTypes.MOLD_FILLER_TYPE.get()) != MoldFillerTypes.EMPTY) {
            ItemStack rtrn = new ItemStack(this);
            rtrn.set(ModDataComponentTypes.MOLD_FILLER_TYPE.get(), MoldFillerTypes.EMPTY);
            return rtrn;
        }
        return ItemStack.EMPTY;
    }

    public void registerMoldTransformers (Map<String, Item> transfromers) {
        this.transfromingItems = transfromers;
    }

    public void registerDefaultTransformer (List<Item> items) {
        this.defaultTransformer = items;
    }

    public void registerItemUsingRecipes () {
        for (MoldFillerType filler : MoldFillerTypes.getFillerTypes()) {
            if (this.transfromingItems.containsKey(filler.id())) {
                ItemStack itemStack = new ItemStack(this);
                itemStack.set(ModDataComponentTypes.MOLD_FILLER_TYPE.get(), filler);
                ArrayList<ItemStack> itemStacks = new ArrayList<>();
                itemStacks.add(new ItemStack(this));
                itemStacks.add(new ItemStack(this.transfromingItems.get(filler.id())));
                if (!this.defaultTransformer.isEmpty()) {
                    for (Item item : this.defaultTransformer) {
                        itemStacks.add(new ItemStack(item));
                    }
                }
                ItemUsingRecipes.registerRecipe(itemStack, itemStacks.stream().toList());
            }
        }
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (this.transfromingItems.containsKey(stack.get(ModDataComponentTypes.MOLD_FILLER_TYPE.get()).id())) {
            if (stack.getCount() > 1) {
                ItemStack itemStack = stack;
                itemStack.setCount(stack.getCount() - 1);
                player.setItemInHand(hand, itemStack);
            } else {
                player.setItemInHand(hand, ItemStack.EMPTY);
            }
            player.addItem(new ItemStack(this.transfromingItems.get(stack.get(ModDataComponentTypes.MOLD_FILLER_TYPE.get()).id())));
            player.addItem(this.getCraftingRemainder(stack));
            if (!this.defaultTransformer.isEmpty()) {
                for (Item item : this.defaultTransformer) {
                    player.addItem(new ItemStack(item));
                }
            }
            return InteractionResult.CONSUME;
        }
        return super.use(level, player, hand);
    }
}