package net.seppevdh.natureupdate.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.item.ModItems;

import java.util.function.Consumer;

public class ModAdvancementBuilder implements AdvancementSubProvider {
    @Override
    public void generate(HolderLookup.Provider pRegistries, Consumer<AdvancementHolder> pWriter) {
        AdvancementHolder root = Advancement.Builder.advancement()
                .display(
                        ModItems.BRONZE_PICKAXE.get(),
                        componentForAdvancement("root"),
                        componentForAdvancement("root.desc"),
                        ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "textures/gui/advancement/nature_update"),
                        AdvancementType.TASK,
                        false, false, false
                )
                .requirements(AdvancementRequirements.Strategy.OR)
                .addCriterion("inventory_changed", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRAFTING_TABLE))
                .save(pWriter, "root");
    }

    private static Component componentForAdvancement (String name) {
        return Component.translatable("advancement.natureupdate." + name);
    }
}
