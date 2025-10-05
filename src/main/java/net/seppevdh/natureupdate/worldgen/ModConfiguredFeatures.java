package net.seppevdh.natureupdate.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.block.ModBlocks;
import net.seppevdh.natureupdate.worldgen.tree.custom.DoubleTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TIN_ORE_KEY = registerKey("tin_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MOD_OAK_KEY = registerKey("mod_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOD_BIRCH_KEY = registerKey("mod_birch");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldTinOres = List.of(OreConfiguration.target(stoneReplaceable,
                        ModBlocks.TIN_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_TIN_ORE_KEY, Feature.ORE, new OreConfiguration(overworldTinOres, 12));

        register(context, MOD_OAK_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new DoubleTrunkPlacer(11, 3, 3),
                BlockStateProvider.simple(Blocks.OAK_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 4),
                new TwoLayersFeatureSize(1, 0, 2)
                ).build()
        );

        register(context, MOD_BIRCH_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.BIRCH_LOG),
                        new DoubleTrunkPlacer(16, 4, 3),
                        BlockStateProvider.simple(Blocks.BIRCH_LEAVES),
                        new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 4),
                        new TwoLayersFeatureSize(1, 0, 2)
                ).build()
        );
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
