package net.seppevdh.natureupdate.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.screen.animation.IntGenerator;

public class WaterBoilerScreen extends AbstractContainerScreen<WaterBoilerMenu> {
    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "textures/gui/water_boiler/water_boiler_gui.png");
    private static final ResourceLocation ARROW_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "textures/gui/water_boiler/arrow_progress.png");
    private static final ResourceLocation FIRE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "textures/gui/water_boiler/fuel_progress.png");
    private static final ResourceLocation BUBBLE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "textures/gui/water_boiler/bubbles.png");


    public WaterBoilerScreen(WaterBoilerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, Component.translatable("name.natureupdate.water_boiler"));
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 70;
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

        if (menu.hasFuel()) {
            renderBubbles(guiGraphics, x, y);
        }
    }

    private void renderFuel(GuiGraphics guiGraphics, int x, int y) {
        guiGraphics.blit(RenderType.GUI_TEXTURED, FIRE_TEXTURE, x + 63, y + 57 - menu.getScaledFuelLevel(), 0, 13 - menu.getScaledFuelLevel(), 14, 1 + menu.getScaledFuelLevel(), 14, 14);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if (menu.isCrafting()) {
            guiGraphics.blit(RenderType.GUI_TEXTURED, ARROW_TEXTURE,x + 111, y + 16, 0, 0, 9, menu.getScaledArrowProgress(), 9, 26);
        }
    }

    private void renderBubbles (GuiGraphics guiGraphics, int x, int y) {
        int size = IntGenerator.WaterBoiler.nextInt();
        guiGraphics.blit(RenderType.GUI_TEXTURED, BUBBLE_TEXTURE, x + 63, y + 39 - size, 0, 23 - size, 16, 1 + size, 16, 24);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
