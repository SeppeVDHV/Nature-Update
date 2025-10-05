package net.seppevdh.natureupdate.compat.jade.custom;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.block.entity.custom.CraftingFurnaceBlockEntity;
import net.seppevdh.natureupdate.recipe.fueling.ModFuelingTypes;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElementHelper;

import java.util.List;

public enum CraftingFurnaceProvider implements IBlockComponentProvider, StreamServerDataProvider<BlockAccessor, CraftingFurnaceProvider.Data> {
    INSTANCE;

    CraftingFurnaceProvider () {}

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        Data data = this.decodeFromData(blockAccessor).orElse(null);
        if (data != null) {
            IElementHelper helper = IElementHelper.get();
            tooltip.add(helper.item(data.inventory.getFirst()));
            tooltip.append(helper.item(data.inventory.get(1)));
            tooltip.append(helper.item(data.inventory.get(2)));
            tooltip.add(helper.item(data.inventory.get(3)));
            tooltip.append(helper.item(data.inventory.get(4)));
            tooltip.append(helper.item(data.inventory.get(5)));
            tooltip.append(helper.spacer(2, 0));
            tooltip.append(helper.item(data.inventory.get(9)));
            tooltip.append(helper.spacer(4, 0));
            tooltip.append(helper.progress(data.progress / (float) data.total));
            tooltip.append(helper.item(data.inventory.get(10)));
            tooltip.add(helper.item(data.inventory.get(6)));
            tooltip.append(helper.item(data.inventory.get(7)));
            tooltip.append(helper.item(data.inventory.get(8)));
        }
    }

    @Override
    public CraftingFurnaceProvider.Data streamData(BlockAccessor blockAccessor) {
        CraftingFurnaceBlockEntity blockEntity = (CraftingFurnaceBlockEntity) blockAccessor.getBlockEntity();
        return new Data(blockEntity.getProgress(), blockEntity.getMaxProgress(), blockEntity.getContent(), blockEntity.getFuelType(), blockEntity.getFuelLevel());
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, Data> streamCodec() {
        return Data.STREAM_CODEC;
    }

    @Override
    public ResourceLocation getUid() {
        return ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "crafting_furnace");
    }

    public record Data (int progress, int total, List<ItemStack> inventory, ModFuelingTypes fuelType, int fuelLevel) {
        public static final StreamCodec<RegistryFriendlyByteBuf, Data> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.VAR_INT, Data::progress,
                ByteBufCodecs.VAR_INT, Data::total,
                ItemStack.OPTIONAL_LIST_STREAM_CODEC, Data::inventory,
                ByteBufCodecs.STRING_UTF8.map(ModFuelingTypes::getTypeByName, ModFuelingTypes::getName), Data::fuelType,
                ByteBufCodecs.VAR_INT, Data::fuelLevel,
                Data::new
        );
    }
}