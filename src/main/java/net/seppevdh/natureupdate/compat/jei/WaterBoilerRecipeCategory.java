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
import net.seppevdh.natureupdate.block.ModBlocks;
import net.seppevdh.natureupdate.compat.jei.animation.IntGenerator;
import net.seppevdh.natureupdate.recipe.WaterBoilerRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WaterBoilerRecipeCategory implements IRecipeCategory<WaterBoilerRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "water_boiling");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID,
            "textures/gui/water_boiler/water_boiler_jei_gui.png");
    private static final ResourceLocation ARROW_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID,
                    "textures/gui/water_boiler/arrow_progress.png");
    private static final ResourceLocation FIRE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID,
                    "textures/gui/water_boiler/fuel_progress.png");

    public static final IRecipeType<WaterBoilerRecipe> WATER_BOILER_RECIPE_TYPE =
            new IRecipeType<>() {
                @Override
                public ResourceLocation getUid() {
                    return UID;
                }

                @Override
                public Class<? extends WaterBoilerRecipe> getRecipeClass() {
                    return WaterBoilerRecipe.class;
                }
            };

    private final IDrawable background;
    private final IDrawable icon;

    public WaterBoilerRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 106, 82);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.WATER_BOILER.get()));
    }

    @Override
    public int getHeight() {
        return 82;
    }

    @Override
    public int getWidth() {
        return 106;
    }

    @Override
    public @NotNull IRecipeType<WaterBoilerRecipe> getRecipeType() {
        return WATER_BOILER_RECIPE_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("recipetype.natureupdate.water_boiling");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, WaterBoilerRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 58, 11).add(recipe.inputItem());
        builder.addSlot(RecipeIngredientRole.INPUT, 27, 18).add(recipe.fluid().getFluid(), recipe.fluid().getAmount())
                .setFluidRenderer(1000, true, 16, 16);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 58, 52).add(recipe.output());

        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 27, 55).add(ItemStack.EMPTY);
    }

    @Override
    public void draw(WaterBoilerRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        int nextInt = IntGenerator.WaterBoiler.nextInt();
        int arrowLength = (int) Math.ceil((nextInt % 400) * 0.065);
        int fuelHeight = 14 - ((int) Math.floor((nextInt % 600) * 14f / 600));

        guiGraphics.blit(RenderType.GUI_TEXTURED, TEXTURE, 0, 0, 0, 0, this.getWidth(), this.getHeight(), this.getWidth(), this.getHeight());
        guiGraphics.blit(RenderType.GUI_TEXTURED, ARROW_TEXTURE, 75, 10, 0, 0, 9, arrowLength, 9, 26);
        guiGraphics.blit(RenderType.GUI_TEXTURED, FIRE_TEXTURE, 27, 52 - fuelHeight, 0, 14 - fuelHeight, 14, fuelHeight, 14, 14);
    }
}
