package net.seppevdh.natureupdate.worldgen.tree.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.seppevdh.natureupdate.worldgen.tree.ModTrunkPlacerTypes;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class DoubleTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<DoubleTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((p_70136_) ->
            trunkPlacerParts(p_70136_).apply(p_70136_, DoubleTrunkPlacer::new));

    public DoubleTrunkPlacer(int pBaseHeight, int pHeightOffset, int pBranchHeightOffset) {
        super(pBaseHeight, pHeightOffset, pBranchHeightOffset);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.DOUBLE_TRUNK_PLACER.get();
    }

    @Override
    @ParametersAreNonnullByDefault
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> setter,
                                                            RandomSource random, int freeHeight, BlockPos pos, TreeConfiguration config) {
        setDirtAt(level, setter, random, pos.below(), config);
        int height = getRandomHeight(random);

        List<FoliagePlacer.FoliageAttachment> attachments = new ArrayList<>(List.of());

        for(int i = 0; i < height; i++) {
            placeLog(level, setter, random, pos.above(i), config);
            if (i < (height - 1)) {
                placeLog(level, setter, random, pos.above(i).east(), config);
                placeLog(level, setter, random, pos.above(i).south(), config);
                placeLog(level, setter, random, pos.above(i).east().south(), config);
            }

            if (i >= height - heightRandB) {
                BlockPos[] posList = new BlockPos[] {
                        pos.above(i).north(),
                        pos.above(i).north().east(),
                        pos.above(i).east(2),
                        pos.above(i).east(2).south(),
                        pos.above(i).south(2).east(),
                        pos.above(i).south(2),
                        pos.above(i).west().south(),
                        pos.above(i).west()
                };
                Direction.Axis[] dirList = new Direction.Axis[] {
                        Direction.Axis.Z,
                        Direction.Axis.X,
                        Direction.Axis.Z,
                        Direction.Axis.X
                };
                for (int j = 0; j < 8; j++) {
                    if (random.nextDouble() < 0.1) {
                        BlockPos pos1 = posList[j];
                        final Direction.Axis dir = dirList[Math.floorDiv(j, 2)];
                        placeLog(level, setter, random, pos1, config, state -> state.trySetValue(RotatedPillarBlock.AXIS, dir));
                        double d = random.nextDouble();
                        if (d < (1 / 3d)) {
                            attachments.add(new FoliagePlacer.FoliageAttachment(pos1, 0, false));
                        } else {
                            BlockPos pos2 = pos1;
                            if (j < 2) pos2 = pos2.north();
                            else if (j < 4) pos2 = pos2.east();
                            else if (j < 6) pos2 = pos2.south();
                            else pos2 = pos2.west();
                            if (d < (2 / 3d)) {
                                placeLog(level, setter, random, pos2, config, state -> state.trySetValue(RotatedPillarBlock.AXIS, dir));
                                attachments.add(new FoliagePlacer.FoliageAttachment(pos2, 0, false));
                            } else {
                                placeLog(level, setter, random, pos2.above(), config, state -> state.trySetValue(RotatedPillarBlock.AXIS, dir));
                                attachments.add(new FoliagePlacer.FoliageAttachment(pos2.above(), 0, false));
                            }
                        }
                    }
                }
            }
        }

        attachments.add(new FoliagePlacer.FoliageAttachment(pos.above(height), 0, true));

        return attachments;
    }

    private int getRandomHeight (RandomSource pRandom) {
        int rtrn = baseHeight;
        if (pRandom.nextBoolean()) {
            rtrn += Math.round(heightRandA * pRandom.nextFloat());
        } else {
            rtrn -= Math.round(heightRandA * pRandom.nextFloat());
        }
        return rtrn;
    }
}
