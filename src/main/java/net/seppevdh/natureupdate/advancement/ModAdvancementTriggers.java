package net.seppevdh.natureupdate.advancement;

import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.advancement.custom.EndFireLightTrigger;

public class ModAdvancementTriggers {
    public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS =
            DeferredRegister.create(BuiltInRegistries.TRIGGER_TYPES, NatureUpdate.MOD_ID);

    public static final DeferredHolder<CriterionTrigger<?>, CriterionTrigger<EndFireLightTrigger.Instance>> END_FIRE_LIGHT =
            TRIGGERS.register("end_fire_light", EndFireLightTrigger::new);

    public static void register (IEventBus eventBus) {
        TRIGGERS.register(eventBus);
    }
}
