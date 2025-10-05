package net.seppevdh.natureupdate.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
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
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.component.ModDataComponentTypes;
import net.seppevdh.natureupdate.component.custom.MoldFillerTypes;
import net.seppevdh.natureupdate.item.ModItems;
import net.seppevdh.natureupdate.recipe.build_in.mold.ItemUsingRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemUsingCategory implements IRecipeCategory<ItemUsingRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "item_using");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID,
            "textures/gui/item_using/jei.png");
    public static final ResourceLocation DOUBLE_OUTPUT_TEXTURE = ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID,
            "textures/gui/item_using/double_output.png");

    public static final IRecipeType<ItemUsingRecipe> ITEM_USING_RECIPE_TYPE =
            new IRecipeType<>() {
                @Override
                public ResourceLocation getUid() {
                    return UID;
                }

                @Override
                public Class<? extends ItemUsingRecipe> getRecipeClass() {
                    return ItemUsingRecipe.class;
                }
            };

    private final IDrawable background;
    private final IDrawable icon;

    public ItemUsingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 141, 34);
        ItemStack itemStack = new ItemStack(ModItems.AXE_MOLD.get());
        itemStack.set(ModDataComponentTypes.MOLD_FILLER_TYPE.get(), MoldFillerTypes.BRONZE);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, itemStack);
    }

    @Override
    public int getHeight() {
        return 34;
    }

    @Override
    public int getWidth() {
        return 141;
    }

    @Override
    public @NotNull IRecipeType<ItemUsingRecipe> getRecipeType() {
        return ITEM_USING_RECIPE_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("recipetype.natureupdate.item_using");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ItemUsingRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 11, 11).add(recipe.item());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 78, 11).add(recipe.result().getFirst());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 96, 11).add(recipe.result().get(1));
        if (recipe.result().size() > 2) {
            builder.addSlot(RecipeIngredientRole.OUTPUT, 114, 11).add(recipe.result().get(2));
        }
    }


    @Override
    public void draw(ItemUsingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        if (recipe.result().size() > 1) {
            guiGraphics.blit(RenderType.GUI_TEXTURED, DOUBLE_OUTPUT_TEXTURE, 0, 0, 0, 0, this.getWidth(), this.getHeight(), this.getWidth(), this.getHeight());
        } else {
            guiGraphics.blit(RenderType.GUI_TEXTURED, TEXTURE, 0, 0, 0, 0, this.getWidth() - 18, this.getHeight(), this.getWidth() - 18, this.getHeight());
        }
    }
}
