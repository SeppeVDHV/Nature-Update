package net.seppevdh.natureupdate.advancement.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.seppevdh.natureupdate.advancement.ModAdvancementTriggers;

import java.util.Optional;

public class EndFireLightTrigger extends SimpleCriterionTrigger<EndFireLightTrigger.Instance> {
    public void trigger (ServerPlayer player) {
        this.trigger(player, inst -> true);
    }

    @Override
    public Codec<Instance> codec() {
        return Instance.CODEC;
    }

    public record Instance (Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<Instance> CODEC = RecordCodecBuilder.create(
                inst -> inst.group(
                        EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(Instance::player)
                ).apply(inst, Instance::new)
        );

        public static Criterion<Instance> litEndFire () {
            return ModAdvancementTriggers.END_FIRE_LIGHT.get().createCriterion(new Instance(Optional.empty()));
        }
    }
}
