package net.seppevdh.natureupdate.fluid.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.textures.FluidSpriteCache;

public class FluidCubeRenderer {
    private static void vertex (PoseStack poseStack, VertexConsumer vertex, float x, float y, float z, int color, float u, float v, int packLight) {
       vertex.addVertex(poseStack.last(), x == 0f ? x + 0.0001f : x - 0.0001f, y, z == 0f ? z + 0.0001f : z - 0.0001f)
               .setColor(color)
               .setLight(packLight)
               .setUv(u, v)
               .setNormal(poseStack.last(), 0f, 1f, 0f);
    }

    private static int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }

    public static void renderFluidCube (PoseStack poseStack, MultiBufferSource buffer, Fluid fluid, BlockPos pos, Level level, float top) {
        IClientFluidTypeExtensions extension = IClientFluidTypeExtensions.of(fluid.defaultFluidState());
        VertexConsumer vertex = buffer.getBuffer(RenderType.TRANSLUCENT);
        TextureAtlasSprite sprite = FluidSpriteCache.getFluidSprites(level, pos, fluid.defaultFluidState())[0];
        int lightLevel = getLightLevel(level, pos);

        int color = extension.getTintColor();

        poseStack.pushPose();

        // top face
        vertex(poseStack, vertex, 0f, top, 1f, color, sprite.getU0(), sprite.getV1(), lightLevel);
        vertex(poseStack, vertex, 1f, top, 1f, color, sprite.getU1(), sprite.getV1(), lightLevel);
        vertex(poseStack, vertex, 1f, top, 0f, color, sprite.getU1(), sprite.getV0(), lightLevel);
        vertex(poseStack, vertex, 0f, top, 0f, color, sprite.getU0(), sprite.getV0(), lightLevel);

        vertex(poseStack, vertex, 1f, top, 1f, color, sprite.getU0(), sprite.getV1(), lightLevel);
        vertex(poseStack, vertex, 0f, top, 1f, color, sprite.getU1(), sprite.getV1(), lightLevel);
        vertex(poseStack, vertex, 0f, top, 0f, color, sprite.getU1(), sprite.getV0(), lightLevel);
        vertex(poseStack, vertex, 1f, top, 0f, color, sprite.getU0(), sprite.getV0(), lightLevel);

        // north face
        vertex(poseStack, vertex, 0f, 0f, 0f, color, sprite.getU0(), sprite.getV1(), lightLevel);
        vertex(poseStack, vertex, 1f, 0f, 0f, color, sprite.getU1(), sprite.getV1(), lightLevel);
        vertex(poseStack, vertex, 1f, top, 0f, color, sprite.getU1(), sprite.getV0(), lightLevel);
        vertex(poseStack, vertex, 0f, top, 0f, color, sprite.getU0(), sprite.getV0(), lightLevel);

        vertex(poseStack, vertex, 0f, top, 0f, color, sprite.getU0(), sprite.getV1(), lightLevel);
        vertex(poseStack, vertex, 1f, top, 0f, color, sprite.getU1(), sprite.getV1(), lightLevel);
        vertex(poseStack, vertex, 1f, 0f, 0f, color, sprite.getU1(), sprite.getV0(), lightLevel);
        vertex(poseStack, vertex, 0f, 0f, 0f, color, sprite.getU0(), sprite.getV0(), lightLevel);

        // south face
        vertex(poseStack, vertex, 0f, top, 1f, color, sprite.getU0(), sprite.getV1(), lightLevel);
        vertex(poseStack, vertex, 1f, top, 1f, color, sprite.getU1(), sprite.getV1(), lightLevel);
        vertex(poseStack, vertex, 1f, 0f, 1f, color, sprite.getU1(), sprite.getV0(), lightLevel);
        vertex(poseStack, vertex, 0f, 0f, 1f, color, sprite.getU0(), sprite.getV0(), lightLevel);

        vertex(poseStack, vertex, 0f, 0f, 1f, color, sprite.getU0(), sprite.getV1(), lightLevel);
        vertex(poseStack, vertex, 1f, 0f, 1f, color, sprite.getU1(), sprite.getV1(), lightLevel);
        vertex(poseStack, vertex, 1f, top, 1f, color, sprite.getU1(), sprite.getV0(), lightLevel);
        vertex(poseStack, vertex, 0f, top, 1f, color, sprite.getU0(), sprite.getV0(), lightLevel);

        // west face
        vertex(poseStack, vertex, 0f, 0f, 0f, color, sprite.getU0(), sprite.getV1(), lightLevel);
        vertex(poseStack, vertex, 0f, 0f, 1f, color, sprite.getU1(), sprite.getV1(), lightLevel);
        vertex(poseStack, vertex, 0f, top, 1f, color, sprite.getU1(), sprite.getV0(), lightLevel);
        vertex(poseStack, vertex, 0f, top, 0f, color, sprite.getU0(), sprite.getV0(), lightLevel);

        vertex(poseStack, vertex, 0f, top, 0f, color, sprite.getU0(), sprite.getV1(), lightLevel);
        vertex(poseStack, vertex, 0f, top, 1f, color, sprite.getU1(), sprite.getV1(), lightLevel);
        vertex(poseStack, vertex, 0f, 0f, 1f, color, sprite.getU1(), sprite.getV0(), lightLevel);
        vertex(poseStack, vertex, 0f, 0f, 0f, color, sprite.getU0(), sprite.getV0(), lightLevel);

        // east face
        vertex(poseStack, vertex, 1f, top, 0f, color, sprite.getU0(), sprite.getV1(), lightLevel);
        vertex(poseStack, vertex, 1f, top, 1f, color, sprite.getU1(), sprite.getV1(), lightLevel);
        vertex(poseStack, vertex, 1f, 0f, 1f, color, sprite.getU1(), sprite.getV0(), lightLevel);
        vertex(poseStack, vertex, 1f, 0f, 0f, color, sprite.getU0(), sprite.getV0(), lightLevel);

        vertex(poseStack, vertex, 1f, 0f, 0f, color, sprite.getU0(), sprite.getV1(), lightLevel);
        vertex(poseStack, vertex, 1f, 0f, 1f, color, sprite.getU1(), sprite.getV1(), lightLevel);
        vertex(poseStack, vertex, 1f, top, 1f, color, sprite.getU1(), sprite.getV0(), lightLevel);
        vertex(poseStack, vertex, 1f, top, 0f, color, sprite.getU0(), sprite.getV0(), lightLevel);

        poseStack.popPose();
    }
}
