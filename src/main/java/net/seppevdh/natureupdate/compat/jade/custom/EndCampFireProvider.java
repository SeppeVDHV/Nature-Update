package net.seppevdh.natureupdate.compat.jade.custom;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.NbtOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.block.entity.custom.EndCampfireBlockEntity;
import org.jetbrains.annotations.Nullable;
import snownee.jade.api.*;
import snownee.jade.api.theme.IThemeHelper;
import snownee.jade.api.view.*;

import java.util.List;
import java.util.Optional;

public enum EndCampFireProvider implements IServerExtensionProvider<ItemStack>, IClientExtensionProvider<ItemStack, ItemView> {
    INSTANCE;

    private static final MapCodec<Integer> COOKING_TIME_CODEC = Codec.INT.fieldOf("natureupdate.end_campfire:cooking");

    EndCampFireProvider() {}

    public List<ClientViewGroup<ItemView>> getClientGroups(Accessor<?> accessor, List<ViewGroup<ItemStack>> groups) {
        return ClientViewGroup.map(groups, (stack) -> {
            CustomData customData = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
            if (customData.isEmpty()) {
                return null;
            } else {
                Optional<Integer> result = customData.read(COOKING_TIME_CODEC).result();
                if (result.isEmpty()) {
                    return null;
                } else {
                    String text = IThemeHelper.get().seconds(result.get(), accessor.tickRate()).getString();
                    return (new ItemView(stack)).amountText(text);
                }
            }
        }, null);
    }

    public @Nullable List<ViewGroup<ItemStack>> getGroups(Accessor<?> accessor) {
        Object var3 = accessor.getTarget();
        if (var3 instanceof EndCampfireBlockEntity endCampfire) {
            List<ItemStack> list = Lists.newArrayList();

            for(int i = 0; i < endCampfire.getCookingTime().length; ++i) {
                ItemStack stack = endCampfire.getItems().get(i);
                if (!stack.isEmpty()) {
                    stack = stack.copy();
                    CustomData customData = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).update(NbtOps.INSTANCE, COOKING_TIME_CODEC, endCampfire.getCookingTime()[i] - endCampfire.getCookingProgress()[i]).getOrThrow();
                    stack.set(DataComponents.CUSTOM_DATA, customData);
                    list.add(stack);
                }
            }

            return List.of(new ViewGroup(list));
        } else {
            return null;
        }
    }

    @Override
    public ResourceLocation getUid() {
        return ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, "end_campfire");
    }
}
