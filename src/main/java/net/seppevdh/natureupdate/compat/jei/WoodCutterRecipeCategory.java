package net.seppevdh.natureupdate.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.block.ModBlocks;
import net.seppevdh.natureupdate.recipe.WoodCutterRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WoodCutterRecipeCategory implements IRecipeCategory<WoodCutterRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "wood_cutting");

    public static final IRecipeType<WoodCutterRecipe> WOOD_CUTTER_RECIPE_TYPE =
            new IRecipeType<WoodCutterRecipe>() {
                @Override
                public ResourceLocation getUid() {
                    return UID;
                }

                @Override
                public Class<? extends WoodCutterRecipe> getRecipeClass() {
                    return WoodCutterRecipe.class;
                }
            };

    private final IDrawable icon;

    public WoodCutterRecipeCategory(IGuiHelper helper) {
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.WOODCUTTER.get()));
    }

    @Override
    public int getHeight() {
        return 34;
    }

    @Override
    public int getWidth() {
        return 82;
    }

    @Override
    public @NotNull IRecipeType<WoodCutterRecipe> getRecipeType() {
        return WOOD_CUTTER_RECIPE_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("recipetype.natureupdate.wood_cutting");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, WoodCutterRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addInputSlot(1, 9).addItemStacks(recipe.input().getValues().stream().map(h -> new ItemStack(h.value(), recipe.count())).toList()).setStandardSlotBackground();

        builder.addOutputSlot(61, 9).add(recipe.result()).setOutputSlotBackground();
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, WoodCutterRecipe recipe, IFocusGroup focuses) {
        builder.addRecipeArrow().setPosition(26, 9);
    }
}
