package net.seppevdh.natureupdate.property;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.seppevdh.natureupdate.component.ModDataComponentTypes;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public record MoldFillerTypeProperty() implements SelectItemModelProperty<String> {
    public static final SelectItemModelProperty.Type<MoldFillerTypeProperty, String> TYPE = SelectItemModelProperty.Type.create(
            MapCodec.unit(new MoldFillerTypeProperty()), Codec.STRING
    );

    @Nullable
    @Override
    public String get(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed, ItemDisplayContext displayContext) {
        return Objects.requireNonNull(stack.get(ModDataComponentTypes.MOLD_FILLER_TYPE.get())).id();
    }

    @Override
    public Codec<String> valueCodec() {
        return Codec.STRING;
    }

    @Override
    public Type<? extends SelectItemModelProperty<String>, String> type() {
        return TYPE;
    }
}
