package net.seppevdh.natureupdate.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotRichTooltipCallback;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;
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
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.block.ModBlocks;
import net.seppevdh.natureupdate.compat.jei.animation.IntGenerator;
import net.seppevdh.natureupdate.recipe.BreezingRecipe;
import net.seppevdh.natureupdate.recipe.fueling.ModFuelingTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class BreezingRecipeCategory implements IRecipeCategory<BreezingRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "breezing");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID,
            "textures/gui/breezing_stand/jei.png");
    private static final ResourceLocation ARROW_TEXTURE = ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID,
            "textures/gui/breezing_stand/jei_arrow.png");
    private static final ResourceLocation BUBBLES_TEXTURE = ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID,
            "textures/gui/breezing_stand/bubbles.png");

    private final List<ItemStack> fuelItems;

    public static final IRecipeType<BreezingRecipe> BREEZING_RECIPE_TYPE =
            new IRecipeType<BreezingRecipe>() {
                @Override
                public ResourceLocation getUid() {
                    return UID;
                }

                @Override
                public Class<? extends BreezingRecipe> getRecipeClass() {
                    return BreezingRecipe.class;
                }
            };

    private final IDrawable icon;

    public BreezingRecipeCategory(IGuiHelper helper, List<ItemStack> fuelItems) {
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.BREEZING_STAND.get()));
        this.fuelItems = fuelItems;
    }

    @Override
    public int getHeight() {
        return 79;
    }

    @Override
    public int getWidth() {
        return 125;
    }

    @Override
    public @NotNull IRecipeType<BreezingRecipe> getRecipeType() {
        return BREEZING_RECIPE_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("recipetype.natureupdate.breezing");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, BreezingRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 51, 38).add(recipe.fluidOutput());

        try {
            builder.addSlot(RecipeIngredientRole.INPUT, 24, 2).add(recipe.ingredients().get(0));
            builder.addSlot(RecipeIngredientRole.INPUT, 42, 2).add(recipe.ingredients().get(1));
            builder.addSlot(RecipeIngredientRole.INPUT, 60, 2).add(recipe.ingredients().get(2));
            builder.addSlot(RecipeIngredientRole.INPUT, 78, 2).add(recipe.ingredients().get(3));
        } catch (Throwable ignored) {}

        builder.addSlot(RecipeIngredientRole.OUTPUT, 83, 38).add(recipe.output());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 107, 38).add(recipe.defaultResult());
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 2, 46).add(Ingredient.of(fuelItems.stream().map(ItemStack::getItem)));
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, BreezingRecipe recipe, IFocusGroup focuses) {
        builder.addText(FormattedText.composite(
                        Component.translatable("name.natureupdate.fire_requirement." + transformToString(recipe.fireType())),
                        Component.literal(", "),
                        Component.translatable("name.natureupdate.recipe.chance"),
                        Component.literal(": §6" + toPercentage(recipe.brewingChance()) + "%")
                ), 123, 14)
                .setPosition(1, 64)
                .setColor(0xff808080);
    }

    private static String transformToString (Optional<ModFuelingTypes> fireType) {
        return fireType.map(ModFuelingTypes::getName).orElse("none");
    }

    @Override
    public void draw(BreezingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        int nextInt = IntGenerator.BreezingStand.nextInt();
        int arrowLength = (int) Math.floor((nextInt % 45) / 4.5f);
        int bubblesHeight = 1 + Math.min(nextInt % 30, 27);

        guiGraphics.blit(RenderType.GUI_TEXTURED, TEXTURE, 0, 0, 0, 0, getWidth(), getHeight(), getWidth(), getHeight());
        guiGraphics.blit(RenderType.GUI_TEXTURED, ARROW_TEXTURE, 70, 41, 0, 0, arrowLength, 8, 10, 8);
        guiGraphics.blit(RenderType.GUI_TEXTURED, BUBBLES_TEXTURE, 27, 55 - bubblesHeight, 0, 28 - bubblesHeight, 12, bubblesHeight, 12, 28);
    }

    private String toPercentage (float raw) {
        float per = raw * 100;
        if (per < 1) {
            return "<1";
        }
        return "" + (Math.round(per * 10) / 10);
    }
}
