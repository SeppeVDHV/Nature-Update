package net.seppevdh.natureupdate.event;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterSelectItemModelPropertyEvent;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.property.MoldFillerTypeProperty;

@EventBusSubscriber(modid = NatureUpdate.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void addCustomItemProperties (RegisterSelectItemModelPropertyEvent event) {
        event.register(ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "mold_filler_type"), MoldFillerTypeProperty.TYPE);
    }
}