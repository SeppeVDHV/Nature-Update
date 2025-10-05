package net.seppevdh.natureupdate.compat.jade.custom;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.block.entity.custom.BreezingStandBlockEntity;
import net.seppevdh.natureupdate.block.entity.custom.CraftingFurnaceBlockEntity;
import net.seppevdh.natureupdate.recipe.fueling.ModFuelingTypes;
import org.jetbrains.annotations.Nullable;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.StreamServerDataProvider;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElementHelper;

import java.util.List;

public enum BreezingStandProvider implements IBlockComponentProvider, StreamServerDataProvider<BlockAccessor, BreezingStandProvider.Data> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        Data data = this.decodeFromData(blockAccessor).orElse(null);
        if (data != null) {
            IElementHelper helper = IElementHelper.get();
            tooltip.add(helper.smallItem(new ItemStack(Items.WIND_CHARGE)));
            tooltip.append(helper.text(Component.literal("" + data.fuelLevel)));
            tooltip.add(helper.item(data.inventory.getFirst()));
            tooltip.append(helper.item(data.inventory.get(1)));
            tooltip.append(helper.item(data.inventory.get(2)));
            tooltip.append(helper.item(data.inventory.get(3)));
            tooltip.append(helper.progress(data.progress / (float) BreezingStandBlockEntity.BREWING_TIME));
            tooltip.append(helper.item(data.inventory.get(5)));
        }
    }

    @Override
    public @Nullable Data streamData(BlockAccessor blockAccessor) {
        BreezingStandBlockEntity blockEntity = (BreezingStandBlockEntity) blockAccessor.getBlockEntity();
        return new Data(blockEntity.getProgress(), blockEntity.getContent(), blockEntity.getFuelLevel());
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, Data> streamCodec() {
        return Data.STREAM_CODEC;
    }

    @Override
    public ResourceLocation getUid() {
        return ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "breezing_stand");
    }

    public record Data (int progress, List<ItemStack> inventory, int fuelLevel) {
        public static final StreamCodec<RegistryFriendlyByteBuf, Data> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.VAR_INT, Data::progress,
                ItemStack.OPTIONAL_LIST_STREAM_CODEC, Data::inventory,
                ByteBufCodecs.VAR_INT, Data::fuelLevel,
                Data::new
        );
    }
}
