package net.seppevdh.natureupdate.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.gui.placement.HorizontalAlignment;
import mezz.jei.api.gui.placement.VerticalAlignment;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.block.ModBlocks;
import net.seppevdh.natureupdate.recipe.fueling.CraftingFurnaceFueling;
import net.seppevdh.natureupdate.recipe.fueling.ModFuelingTypes;
import net.seppevdh.natureupdate.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CraftingFurnaceFuelingCategory implements IRecipeCategory<CraftingFurnaceFueling> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "crafting_furnace_fuel");
    private static final ResourceLocation FLAMES = ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "textures/gui/crafting_furnace/fuel_progress.png");
    private static final ResourceLocation SOUL_FLAMES = ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "textures/gui/crafting_furnace/soul_fuel.png");
    private static final ResourceLocation ENDER_FLAMES = ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "textures/gui/crafting_furnace/ender_fuel.png");

    public static final IRecipeType<CraftingFurnaceFueling> CRAFTING_FURNACE_FUELING_TYPE =
            new IRecipeType<CraftingFurnaceFueling>() {
                @Override
                public ResourceLocation getUid() {
                    return UID;
                }

                @Override
                public Class<? extends CraftingFurnaceFueling> getRecipeClass() {
                    return CraftingFurnaceFueling.class;
                }
            };

    private final IDrawable icon;

    public CraftingFurnaceFuelingCategory (IGuiHelper helper) {
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.CRAFTING_FURNACE.get()));
    }

    @Override
    public int getHeight() {
        return 34;
    }

    @Override
    public int getWidth() {
        return 170;
    }

    @Override
    public IRecipeType<CraftingFurnaceFueling> getRecipeType() {
        return CRAFTING_FURNACE_FUELING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("recipetype.natureupdate.crafting_furnace_fueling");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CraftingFurnaceFueling recipe, IFocusGroup focuses) {
        builder.addInputSlot(1, 17).add(recipe.item()).setStandardSlotBackground();
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, CraftingFurnaceFueling recipe, IFocusGroup focuses) {
        assert Minecraft.getInstance().level != null;
        builder.addText(List.of(
                FormattedText.composite(
                        Component.translatable("name.natureupdate.crafting_furnace_fuel_title"),
                        Component.literal((" " + (recipe.item().getBurnTime(new ItemStack(recipe.item()), RecipeType.SMELTING, Minecraft.getInstance().level.fuelValues())) / 40f)),
                        Component.translatable("name.natureupdate.seconds_shortened")
                ),
                FormattedText.composite(
                        Component.translatable("name.natureupdate.crafting_furnace_fuel_type_title"),
                        Component.literal(": "),
                        Component.translatable("name.natureupdate.crafting_furnace_fuel_type_" + getFuelType(recipe.item()).getName()))
                ), getWidth() - 20, getHeight())
                .setPosition(20, 0)
                .setTextAlignment(HorizontalAlignment.CENTER)
                .setTextAlignment(VerticalAlignment.CENTER)
                .setColor(0xff808080);
    }

    @Override
    public void draw(CraftingFurnaceFueling recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        ResourceLocation flameTexture = FLAMES;
        if (getFuelType(recipe.item()).equals(ModFuelingTypes.ENDER)) {
            flameTexture = ENDER_FLAMES;
        } else if (getFuelType(recipe.item()).equals(ModFuelingTypes.SOUL)) {
            flameTexture = SOUL_FLAMES;
        }

        guiGraphics.blit(RenderType.GUI_TEXTURED, flameTexture, 1, 1, 0, 0, 14, 14, 14, 14);
    }

    private ModFuelingTypes getFuelType (Item item) {
        if (new ItemStack(item).is(ModTags.Items.ENDER_FUELING)) {
            return ModFuelingTypes.ENDER;
        } else if (new ItemStack(item).is(ModTags.Items.SOUL_FUELING)) {
            return ModFuelingTypes.SOUL;
        }
        return ModFuelingTypes.NORMAL;
    }
}
