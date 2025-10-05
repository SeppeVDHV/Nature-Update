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
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.seppevdh.natureupdate.block.custom.ShelfBlock;
import net.seppevdh.natureupdate.block.custom.TableBlock;
import net.seppevdh.natureupdate.block.entity.custom.ShelfBlockEntity;
import net.seppevdh.natureupdate.util.ModTags;

public class ShelfBlockEntityRenderer implements BlockEntityRenderer<ShelfBlockEntity> {
    public ShelfBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(ShelfBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay, Vec3 v) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stack0 = pBlockEntity.itemHandler.getStackInSlot(0);
        ItemStack stack1 = pBlockEntity.itemHandler.getStackInSlot(1);
        ItemStack stack2 = pBlockEntity.itemHandler.getStackInSlot(2);
        ItemStack[] stacks = {stack0, stack1, stack2};

        Level level = pBlockEntity.getLevel();
        BlockPos pos = pBlockEntity.getBlockPos();

        assert level != null;
        if (level.getBlockState(pos).getBlock() instanceof ShelfBlock) {
            for (int x = 0; x < 3; x++) {
                ItemStack stack = stacks[x];
                float[][] poss = getPos(level.getBlockState(pos));
                renderItemStack(itemRenderer, 90f * getRotForFacing(level.getBlockState(pos).getValue(TableBlock.FACING)), 0f, 0.4f, poss[x][0], 0.5f, poss[x][1], stack, pBlockEntity, pPoseStack, pBufferSource);
            }
        }
    }

    private static float[][] getPos (BlockState state) {
        float[] basePos = {
                1f / 6, 3f / 6, 5f / 6
        };
        return switch (state.getValue(ShelfBlock.FACING)) {
            case EAST -> new float[][]{{3.5f / 16, 1 - basePos[0]}, {3.5f / 16, 1 - basePos[1]}, {3.5f / 16, 1 - basePos[2]}};
            case SOUTH -> new float[][]{{basePos[0], 3.5f / 16}, {basePos[1], 3.5f / 16}, {basePos[2], 3.5f / 16}};
            case WEST -> new float[][]{{12.5f / 16, basePos[0]}, {12.5f / 16, basePos[1]}, {12.5f / 16, basePos[2]}};
            default -> new float[][]{{1 - basePos[0], 12.5f / 16}, {1 - basePos[1], 12.5f / 16}, {1 - basePos[2], 12.5f / 16}};
        };
    }

    private static int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }

    private static void renderItemStack (ItemRenderer renderer, float rotY, float rot, float scale, float x, float y, float z, ItemStack itemStack, ShelfBlockEntity blockEntity, PoseStack poseStack, MultiBufferSource bufferSource) {
        poseStack.pushPose();
        poseStack.translate(x, y, z);
        poseStack.scale(scale, scale, scale);
        poseStack.mulPose(Axis.YP.rotationDegrees(rotY));
        poseStack.mulPose(Axis.XP.rotationDegrees(rot));
        renderer.renderStatic(itemStack, ItemDisplayContext.FIXED, getLightLevel(blockEntity.getLevel(), blockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, poseStack, bufferSource, blockEntity.getLevel(), 1);
        poseStack.popPose();
    }

    private static int getRotForFacing (Direction side) {
        return switch (side) {
            case NORTH -> 0;
            case WEST -> 1;
            case SOUTH -> 2;
            default -> 3;
        };
    }
}
