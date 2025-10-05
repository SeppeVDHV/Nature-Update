package net.seppevdh.natureupdate.sound;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seppevdh.natureupdate.NatureUpdate;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(Registries.SOUND_EVENT, NatureUpdate.MOD_ID);

    public static final Supplier<SoundEvent> ELECTRODROME = registerSoundEvent("electrodrome");
    public static final ResourceKey<JukeboxSong> ELECTRODROME_KEY = ResourceKey.create(Registries.JUKEBOX_SONG,
            ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "electrodrome"));

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}