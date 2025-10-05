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
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.block.ModBlocks;
import net.seppevdh.natureupdate.compat.jei.animation.IntGenerator;
import net.seppevdh.natureupdate.recipe.CraftingFurnaceRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.seppevdh.natureupdate.recipe.fueling.ModFuelingTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CraftingFurnaceRecipeCategory implements IRecipeCategory<CraftingFurnaceRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "crafted_cooking");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID,
            "textures/gui/crafting_furnace/crafting_furnace_jei_gui.png");
    private static final ResourceLocation FIRE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID,
                    "textures/gui/crafting_furnace/fuel_progress.png");
    private static final ResourceLocation SOUL_FIRE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID,
                    "textures/gui/crafting_furnace/soul_fuel.png");
    private static final ResourceLocation END_FIRE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID,
                    "textures/gui/crafting_furnace/ender_fuel.png");

    public static final IRecipeType<CraftingFurnaceRecipe> CRAFTING_FURNACE_RECIPE_TYPE =
            new IRecipeType<CraftingFurnaceRecipe>() {
                @Override
                public ResourceLocation getUid() {
                    return UID;
                }

                @Override
                public Class<? extends CraftingFurnaceRecipe> getRecipeClass() {
                    return CraftingFurnaceRecipe.class;
                }
            };

    private final IDrawable icon;
    private final List<ItemStack> fuelItems;
    private final List<ItemStack> soulFuelItems;
    private final List<ItemStack> endFuelItems;

    public CraftingFurnaceRecipeCategory(IGuiHelper helper, List<ItemStack> fuelItems, List<ItemStack> soulFuelItems, List<ItemStack> endFuelItems) {
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.CRAFTING_FURNACE.get()));
        this.fuelItems = fuelItems;
        this.soulFuelItems = soulFuelItems;
        this.endFuelItems = endFuelItems;
    }

    @Override
    public int getHeight() {
        return 102;
    }

    @Override
    public int getWidth() {
        return 170;
    }

    @Override
    public @NotNull IRecipeType<CraftingFurnaceRecipe> getRecipeType() {
        return CRAFTING_FURNACE_RECIPE_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("recipetype.natureupdate.crafted_cooking");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CraftingFurnaceRecipe recipe, @NotNull IFocusGroup focuses) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (recipe.pattern().ingredients().get(i * 3 + j).isPresent()) {
                    builder.addSlot(RecipeIngredientRole.INPUT, 6 + j * 18, 6 + i * 18).add(recipe.pattern().ingredients().get(i * 3 + j).get());
                } else {builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 6 + j * 18, 6 + i * 18).add(Items.AIR);}
            }
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 112, 25).add(recipe.result());

        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 75, 60).addItemStacks(recipe.fuelType() == ModFuelingTypes.NORMAL ? fuelItems : recipe.fuelType() == ModFuelingTypes.SOUL ? soulFuelItems : endFuelItems);
    }

    @Override
    public void draw(CraftingFurnaceRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        guiGraphics.blit(RenderType.GUI_TEXTURED, TEXTURE, 0, 0, 0, 0, this.getWidth(), this.getHeight(), this.getWidth(), this.getHeight());

        int anInt = IntGenerator.Fuel.nextInt();
        int fuelHeight = 14 - (int) Math.floor((anInt / (float) IntGenerator.Fuel.intLimit) * 14);

        ResourceLocation fireTexture = switch (recipe.fuelType()) {
            case NORMAL -> FIRE_TEXTURE;
            case SOUL -> SOUL_FIRE_TEXTURE;
            case ENDER -> END_FIRE_TEXTURE;
        };

        guiGraphics.blit(RenderType.GUI_TEXTURED, fireTexture, 76, 57 - fuelHeight, 0, 14 - fuelHeight, 14, fuelHeight, 14, 14);
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, CraftingFurnaceRecipe recipe, IFocusGroup focuses) {
        builder.addText(FormattedText.composite(
                Component.literal((recipe.cookingTime() / 20) + ""),
                Component.translatable("name.natureupdate.seconds_shortened")
        ), 37, 17)
                .setPosition(96, 47)
                .setTextAlignment(HorizontalAlignment.RIGHT)
                .setTextAlignment(VerticalAlignment.TOP)
                .setColor(0xff808080);

        builder.addText(FormattedText.composite(
                        Component.literal(recipe.experience() + " "),
                        Component.translatable("name.natureupdate.experience_shortened")
                ), 48, 14)
                .setPosition(84, 4)
                .setTextAlignment(HorizontalAlignment.RIGHT)
                .setTextAlignment(VerticalAlignment.BOTTOM)
                .setColor(0xff808080);

        builder.addAnimatedRecipeArrow(recipe.cookingTime()).setPosition(74, 24);

        builder.addText(Component.translatable("name.natureupdate.fuel_requirement." + recipe.fuelType().getName()), 172, 17)
                .setPosition(4, 83)
                .setTextAlignment(HorizontalAlignment.CENTER)
                .setTextAlignment(VerticalAlignment.TOP)
                .setColor(0xff808080);
    }
}
