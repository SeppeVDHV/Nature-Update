package net.seppevdh.natureupdate.screen.custom;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.screen.animation.IntGenerator;

public class BreezingStandScreen extends AbstractContainerScreen<BreezingStandMenu> {
    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "textures/gui/breezing_stand/breezing_stand_gui.png");
    private static final ResourceLocation ARROW_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "textures/gui/breezing_stand/arrow.png");
    private static final ResourceLocation FUEL_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "textures/gui/breezing_stand/fuel.png");
    private static final ResourceLocation BUBBLES_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "textures/gui/breezing_stand/bubbles.png");

    public BreezingStandScreen(BreezingStandMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, Component.translatable("name.natureupdate.breezing_stand"));
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
        renderProgressBubbles(guiGraphics, x, y);
    }

    private void renderFuel(GuiGraphics guiGraphics, int x, int y) {
        guiGraphics.blit(RenderType.GUI_TEXTURED, FUEL_TEXTURE, x + 54, y + 65, 0, 0, menu.getScaledFuelLevel(), 4, 18, 4);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(RenderType.GUI_TEXTURED, ARROW_TEXTURE,x + 104, y + 55, 0, 0, menu.getScaledArrowProgress(), 9, 26, 9);
        }
    }

    private void renderProgressBubbles(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            int bubblesHeight = IntGenerator.BreezingStand.nextInt();
            guiGraphics.blit(RenderType.GUI_TEXTURED, BUBBLES_TEXTURE,x + 57, y + 64 - bubblesHeight, 0, 28 - bubblesHeight, 12, bubblesHeight, 12, 28);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
