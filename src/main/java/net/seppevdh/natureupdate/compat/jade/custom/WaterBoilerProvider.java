package net.seppevdh.natureupdate.compat.jade.custom;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import net.neoforged.neoforge.fluids.FluidStack;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.block.entity.custom.WaterBoilerBlockEntity;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.fluid.JadeFluidObject;
import snownee.jade.api.ui.IElementHelper;

import java.util.List;

public enum WaterBoilerProvider implements IBlockComponentProvider, StreamServerDataProvider<BlockAccessor, WaterBoilerProvider.Data> {
    INSTANCE;

    WaterBoilerProvider () {}

    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        WaterBoilerProvider.Data data = this.decodeFromData(accessor).orElse(null);
        if (data != null) {
            IElementHelper helper = IElementHelper.get();
            tooltip.add(helper.item(data.inventory.get(0)));
            tooltip.append(helper.item(data.inventory.get(1)));
            tooltip.append(helper.spacer(4, 0));
            tooltip.append(helper.progress(data.progress / (float) data.total).translate(new Vec2(-2.0F, 0.0F)));
            tooltip.append(helper.item(data.inventory.get(2)));
            tooltip.add(helper.fluid(JadeFluidObject.of(data.fluid.getFluid())));
            tooltip.append(helper.text(data.fluid.getHoverName()));
            tooltip.append(helper.text(Component.literal(string(data.fluid.getAmount()) + "mB / 1.000mB")));
        }
    }

    private static String string (int number) {
        String value = "" + Math.min(number, 1000);
        return value.equals("1000") ? "1.000" : value;
    }

    public WaterBoilerProvider.Data streamData(BlockAccessor accessor) {
        WaterBoilerBlockEntity blockEntity = (WaterBoilerBlockEntity)accessor.getBlockEntity();
        return new WaterBoilerProvider.Data(blockEntity.getProgress(), blockEntity.getMaxProgress(), blockEntity.getContent(), new FluidStack(blockEntity.getStoredFluid(), blockEntity.getFluidAmount()));
    }

    public StreamCodec<RegistryFriendlyByteBuf, WaterBoilerProvider.Data> streamCodec() {
        return WaterBoilerProvider.Data.STREAM_CODEC;
    }

    public ResourceLocation getUid() {
        return ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "water_boiler");
    }

    public record Data(int progress, int total, List<ItemStack> inventory, FluidStack fluid) {
        public static final StreamCodec<RegistryFriendlyByteBuf, WaterBoilerProvider.Data> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.VAR_INT, Data::progress,
                ByteBufCodecs.VAR_INT, Data::total,
                ItemStack.OPTIONAL_LIST_STREAM_CODEC, Data::inventory,
                FluidStack.OPTIONAL_STREAM_CODEC, Data::fluid,
                Data::new
        );
    }
}
