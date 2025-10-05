package net.seppevdh.natureupdate.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.item.crafting.RecipeType;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.block.ModBlocks;
import net.seppevdh.natureupdate.recipe.*;
import net.seppevdh.natureupdate.recipe.build_in.listed.ModListedRecipes;
import net.seppevdh.natureupdate.recipe.build_in.mold.ItemUsingRecipes;
import net.seppevdh.natureupdate.recipe.fueling.CraftingFurnaceFueling;
import net.seppevdh.natureupdate.screen.custom.BreezingStandScreen;
import net.seppevdh.natureupdate.screen.custom.CraftingFurnaceScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.seppevdh.natureupdate.screen.custom.WaterBoilerScreen;
import net.seppevdh.natureupdate.screen.custom.WoodCutterScreen;
import net.seppevdh.natureupdate.util.ModTags;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@JeiPlugin
public class JEINatureUpdatePlugin implements IModPlugin {
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        assert Minecraft.getInstance().level != null;
        List<ItemStack> itemStream = registration.getJeiHelpers().getIngredientManager().getAllItemStacks().stream().toList();
        List<ItemStack> breezingFuelItems = itemStream.stream().filter(x -> x.is(ModTags.Items.BREEZING_FUEL)).toList();
        List<ItemStack> fuelItems = itemStream.stream().filter(x -> x.getBurnTime(ModRecipes.CRAFTING_FURNACE_TYPE.get(), Minecraft.getInstance().level.fuelValues()) > 0).toList();
        List<ItemStack> soulFuelItems = fuelItems.stream().filter(x -> x.is(ModTags.Items.SOUL_FUELING) || x.is(ModTags.Items.ENDER_FUELING)).toList();
        List<ItemStack> endFuelItems = fuelItems.stream().filter(x -> x.is(ModTags.Items.ENDER_FUELING)).toList();

        registration.addRecipeCategories(
                new CraftingFurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper(), fuelItems, soulFuelItems, endFuelItems),
                new ShapelessCraftingFurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper(), fuelItems, soulFuelItems, endFuelItems),
                new WaterBoilerRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new ItemUsingCategory(registration.getJeiHelpers().getGuiHelper()),
                new CraftingFurnaceFuelingCategory(registration.getJeiHelpers().getGuiHelper()),
                new WoodCutterRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new BreezingRecipeCategory(registration.getJeiHelpers().getGuiHelper(), breezingFuelItems)
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<CraftingFurnaceRecipe> craftingFurnaceRecipes = ModListedRecipes.getCraftingFurnaceRecipes().stream().toList();
        List<ShapelessCraftingFurnaceRecipe> shapelessCraftingFurnaceRecipes = ModListedRecipes.getShapelessCraftingFurnaceRecipes().stream().toList();
        List<WaterBoilerRecipe> waterBoilerRecipes = ModListedRecipes.getWaterBoilerRecipes().stream().toList();
        List<WoodCutterRecipe> woodCutterRecipes = ModListedRecipes.getWoodCutterRecipes().stream().toList();
        List<BreezingRecipe> breezingRecipes = ModListedRecipes.getBreezingRecipes().stream().toList();
        IIngredientManager ingredientManager = registration.getIngredientManager();
        assert Minecraft.getInstance().level != null;

        Stream<ItemStack> items = ingredientManager.getAllItemStacks().stream();

        List<CraftingFurnaceFueling> craftingFurnaceFuelItems = items.filter(x -> x.getBurnTime(RecipeType.SMELTING, Minecraft.getInstance().level.fuelValues()) > 0)
                .sorted(Comparator.comparingInt(x -> x.getBurnTime(RecipeType.SMELTING, Minecraft.getInstance().level.fuelValues())))
                .map(x -> new CraftingFurnaceFueling(x.getItem()))
                .toList();

        registration.addRecipes(CraftingFurnaceRecipeCategory.CRAFTING_FURNACE_RECIPE_TYPE, craftingFurnaceRecipes);
        registration.addRecipes(ShapelessCraftingFurnaceRecipeCategory.SHAPELESS_CRAFTING_FURNACE_RECIPE_TYPE, shapelessCraftingFurnaceRecipes);
        registration.addRecipes(WaterBoilerRecipeCategory.WATER_BOILER_RECIPE_TYPE, waterBoilerRecipes);
        registration.addRecipes(ItemUsingCategory.ITEM_USING_RECIPE_TYPE, ItemUsingRecipes.getRecipes());
        registration.addRecipes(CraftingFurnaceFuelingCategory.CRAFTING_FURNACE_FUELING_TYPE, craftingFurnaceFuelItems);
        registration.addRecipes(WoodCutterRecipeCategory.WOOD_CUTTER_RECIPE_TYPE, woodCutterRecipes);
        registration.addRecipes(BreezingRecipeCategory.BREEZING_RECIPE_TYPE, breezingRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CraftingFurnaceScreen.class, 70, 25, 20, 20,
                CraftingFurnaceRecipeCategory.CRAFTING_FURNACE_RECIPE_TYPE,
                ShapelessCraftingFurnaceRecipeCategory.SHAPELESS_CRAFTING_FURNACE_RECIPE_TYPE,
                CraftingFurnaceFuelingCategory.CRAFTING_FURNACE_FUELING_TYPE
        );
        registration.addRecipeClickArea(WaterBoilerScreen.class, 43, 35, 20, 20,
                WaterBoilerRecipeCategory.WATER_BOILER_RECIPE_TYPE
        );
        registration.addRecipeClickArea(WoodCutterScreen.class, 43, 35, 20, 20,
                WoodCutterRecipeCategory.WOOD_CUTTER_RECIPE_TYPE
        );
        registration.addRecipeClickArea(InventoryScreen.class, 95, 60, 20, 20,
                ItemUsingCategory.ITEM_USING_RECIPE_TYPE
        );
        registration.addRecipeClickArea(BreezingStandScreen.class, 43, 35, 20, 20,
                BreezingRecipeCategory.BREEZING_RECIPE_TYPE
        );
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(CraftingFurnaceRecipeCategory.CRAFTING_FURNACE_RECIPE_TYPE, new ItemStack(ModBlocks.CRAFTING_FURNACE.get()));
        registration.addCraftingStation(ShapelessCraftingFurnaceRecipeCategory.SHAPELESS_CRAFTING_FURNACE_RECIPE_TYPE, new ItemStack(ModBlocks.CRAFTING_FURNACE.get()));
        registration.addCraftingStation(CraftingFurnaceFuelingCategory.CRAFTING_FURNACE_FUELING_TYPE, new ItemStack(ModBlocks.CRAFTING_FURNACE.get()));

        registration.addCraftingStation(WaterBoilerRecipeCategory.WATER_BOILER_RECIPE_TYPE, new ItemStack(ModBlocks.WATER_BOILER.get()));
        registration.addCraftingStation(RecipeTypes.SMELTING_FUEL, new ItemStack(ModBlocks.WATER_BOILER.get()));
        registration.addCraftingStation(WoodCutterRecipeCategory.WOOD_CUTTER_RECIPE_TYPE, new ItemStack(ModBlocks.WOODCUTTER.get()));
        registration.addCraftingStation(BreezingRecipeCategory.BREEZING_RECIPE_TYPE, new ItemStack(ModBlocks.BREEZING_STAND.get()));
        registration.addCraftingStation(RecipeTypes.CAMPFIRE_COOKING, new ItemStack(ModBlocks.END_CAMPFIRE.get()));
    }
}
