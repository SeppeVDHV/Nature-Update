package net.seppevdh.natureupdate.screen.custom;

import net.minecraft.client.renderer.RenderType;
import net.seppevdh.natureupdate.NatureUpdate;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CraftingFurnaceScreen extends AbstractContainerScreen<CraftingFurnaceMenu> {
    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "textures/gui/crafting_furnace/crafting_furnace_gui.png");
    private static final ResourceLocation ARROW_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "textures/gui/crafting_furnace/arrow_progress.png");
    private static final ResourceLocation FIRE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "textures/gui/crafting_furnace/fuel_progress.png");
    private static final ResourceLocation SOUL_FIRE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "textures/gui/crafting_furnace/soul_fuel.png");
    private static final ResourceLocation END_FIRE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "textures/gui/crafting_furnace/ender_fuel.png");

    public CraftingFurnaceScreen(CraftingFurnaceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, Component.translatable("name.natureupdate.crafting_furnace"));
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 78;
        this.titleLabelY = -2;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int imageHeight = 180;

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(RenderType.GUI_TEXTURED, GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight, 256, 256);

        renderProgressArrow(guiGraphics, x, y);
        renderFuel(guiGraphics, x, y);
    }

    private void renderFuel(GuiGraphics guiGraphics, int x, int y) {
        ResourceLocation fuelTexture = switch (menu.getFuelType()) {
            case 1 -> SOUL_FIRE_TEXTURE;
            case 2 -> END_FIRE_TEXTURE;
            default -> FIRE_TEXTURE;
        };

        guiGraphics.blit(RenderType.GUI_TEXTURED, fuelTexture, x + 84, y + 66 - menu.getScaledFuelLevel(), 0, 13 - menu.getScaledFuelLevel(), 14, 1 + menu.getScaledFuelLevel(), 14, 14);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(RenderType.GUI_TEXTURED, ARROW_TEXTURE,x + 81, y + 34, 0, 0, menu.getScaledArrowProgress(), 16, 24, 16);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
