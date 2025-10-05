package net.seppevdh.natureupdate.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.block.entity.custom.WaterBoilerBlockEntity;
import net.seppevdh.natureupdate.fluid.renderer.FluidCubeRenderer;


public class WaterBoilerBlockEntityRenderer implements BlockEntityRenderer<WaterBoilerBlockEntity> {
    public WaterBoilerBlockEntityRenderer (BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(WaterBoilerBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay, Vec3 v) {
        float[][] fuelPos = new float[][] {
                {0.3f, 0.2f},
                {0.7f, 0.2f},
                {0.2f, 0.6f},
                {0.8f, 0.6f},
                {0.5f, 0.8f}
        };

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stackInput = pBlockEntity.itemHandler.getStackInSlot(0);
        ItemStack stackFuel = pBlockEntity.itemHandler.getStackInSlot(1);
        Item itemFuel = pBlockEntity.itemHandler.getStackInSlot(1).getItem();
        ItemStack stackResult = pBlockEntity.itemHandler.getStackInSlot(2);

        if (pBlockEntity.getStoredFluid() != Fluids.EMPTY) {
            FluidCubeRenderer.renderFluidCube(pPoseStack, pBufferSource, pBlockEntity.getStoredFluid(), pBlockEntity.getBlockPos(), pBlockEntity.getLevel(), (pBlockEntity.getFluidAmount() / (float) WaterBoilerBlockEntity.maxFluidSize) * 7 / 8f);
        }

        for (int i = 0; i < getItemsToRender(stackInput.getCount()); i++) {
            renderItemStack(itemRenderer, 8f * i, 90f, 0.4f, 0.5f, 0.2f + 0.025f * i, 0.5f, stackInput, pBlockEntity, pPoseStack, pBufferSource);
        }
        for (int i = 0; i < getItemsToRender(stackResult.getCount()); i++) {
            renderItemStack(itemRenderer, 0f, 35f, 0.4f, 0.5f, 0.5625f, 0.5f + 0.025f * i, stackResult, pBlockEntity, pPoseStack, pBufferSource);
        }
        for (int index = 0; index < fuelPos.length; index++) {
            float[] i = fuelPos[index];
            renderItemStack(itemRenderer, 0f, 90f, 0.4f, i[0], 1.03125f, i[1], new ItemStack(itemFuel), pBlockEntity, pPoseStack, pBufferSource);
            if (index >= getItemsToRender(stackFuel.getCount()) - 1) {
                break;
            }
        }
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }

    private void renderItemStack (ItemRenderer renderer, float rotY, float rot, float scale, float x, float y, float z, ItemStack itemStack, WaterBoilerBlockEntity blockEntity, PoseStack poseStack, MultiBufferSource bufferSource) {
        poseStack.pushPose();
        poseStack.translate(x, y, z);
        poseStack.scale(scale, scale, scale);
        poseStack.mulPose(Axis.YP.rotationDegrees(rotY));
        poseStack.mulPose(Axis.XP.rotationDegrees(rot));
        renderer.renderStatic(itemStack, ItemDisplayContext.FIXED, getLightLevel(blockEntity.getLevel(), blockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, poseStack, bufferSource, blockEntity.getLevel(), 1);
        poseStack.popPose();
    }

    private int getItemsToRender (int count) {
        if (count < 1) {
            return 0;
        } else if (count == 1) {
            return 1;
        } else if (count < 17) {
            return 2;
        } else if (count < 33) {
            return 3;
        } else if (count < 49) {
            return 4;
        }
        return 5;
    }
}
