package net.seppevdh.natureupdate.villager;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.block.ModBlocks;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, NatureUpdate.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(Registries.VILLAGER_PROFESSION, NatureUpdate.MOD_ID);

    public static final Holder<PoiType> WATER_BOILER_POI = POI_TYPES.register("water_boiler_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.WATER_BOILER.get().getStateDefinition().getPossibleStates()),
                    1, 1));
    public static final Holder<PoiType> CRAFTING_FURNACE_POI = POI_TYPES.register("crafting_furnace_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.CRAFTING_FURNACE.get().getStateDefinition().getPossibleStates()),
                    1, 1));

    public static final Holder<VillagerProfession> COOK = VILLAGER_PROFESSIONS.register("cook",
            () -> new VillagerProfession(Component.translatable("villagerprofession.natureupdate.cook"), holder -> holder.value() == WATER_BOILER_POI.value(),
                    holder -> holder.value() == WATER_BOILER_POI.value(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.BUCKET_EMPTY));
    public static final Holder<VillagerProfession> MOLD_SMITH = VILLAGER_PROFESSIONS.register("mold_smith",
            () -> new VillagerProfession(Component.translatable("villagerprofession.natureupdate.mold_smith"), holder -> holder.value() == CRAFTING_FURNACE_POI.value(),
                    holder -> holder.value() == CRAFTING_FURNACE_POI.value(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.FURNACE_FIRE_CRACKLE));



    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}