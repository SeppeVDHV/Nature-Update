package net.seppevdh.natureupdate.component.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

public class MoldFillerType implements Comparable<MoldFillerType> {
    public static final Codec<MoldFillerType> CODEC = RecordCodecBuilder.create(
            codecI -> codecI.group(
                    Codec.STRING.fieldOf("id").forGetter(p_filler -> p_filler.id)
            ).apply(codecI, MoldFillerType::new)
    );

    String id;
    Item fillerItem = Items.AIR;

    public MoldFillerType (@NotNull String id) {
        this.id = id;
    }

    public MoldFillerType withFillerItem (Item fillerItem) {
        this.fillerItem = fillerItem;
        return this;
    }

    public boolean isFillerItem(Item item) {
        return item == this.fillerItem;
    }

    public String id () {
        return this.id;
    }

    public Item fillerItem () {
        return this.fillerItem;
    }

    @Override
    public int compareTo(@NotNull MoldFillerType o) {
        if (Objects.equals(o.id, this.id)) {
            return this.isFillerItem(o.fillerItem) ? 0 : 1;
        }
        return this.isFillerItem(o.fillerItem) ? -1 : -2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MoldFillerType o) {
            return Objects.equals(this.id, o.id) && this.isFillerItem(o.fillerItem);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private static Optional<MoldFillerType> getThis (Optional<String> s) {
        return s.map(MoldFillerTypes::getTypeById);
    }
}