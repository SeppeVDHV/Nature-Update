package net.seppevdh.natureupdate.trim;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.item.ModItems;

import java.util.Map;

public class ModTrimMaterials {
    public static final ResourceKey<TrimMaterial> BRONZE =
            ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "bronze"));

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context, BRONZE, ModItems.BRONZE_INGOT.get(), Style.EMPTY.withColor(TextColor.parseColor("#c19e35").getOrThrow()), 0.8F);
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> trimKey, Item item, Style style, float itemModelIndex) {
        TrimMaterial trimmaterial = new TrimMaterial(MaterialAssetGroup.create("bismuth"),
                Component.translatable(Util.makeDescriptionId("trim_material", trimKey.location())).withStyle(style));
        context.register(trimKey, trimmaterial);
    }
}