package net.seppevdh.natureupdate.worldgen.tree;

import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.worldgen.ModConfiguredFeatures;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower MOD_OAK = new TreeGrower(NatureUpdate.MOD_ID + ":oak", 0.1F,
            Optional.of(ModConfiguredFeatures.MOD_OAK_KEY), Optional.empty(),
            Optional.of(TreeFeatures.OAK), Optional.of(TreeFeatures.FANCY_OAK), Optional.of(TreeFeatures.OAK_BEES_005), Optional.of(TreeFeatures.FANCY_OAK_BEES_005));
    public static final TreeGrower MOD_BIRCH = new TreeGrower(NatureUpdate.MOD_ID + ":birch",
            Optional.of(ModConfiguredFeatures.MOD_BIRCH_KEY), Optional.of(TreeFeatures.BIRCH), Optional.of(TreeFeatures.BIRCH_BEES_005));
}