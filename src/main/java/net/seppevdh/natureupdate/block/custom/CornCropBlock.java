package net.seppevdh.natureupdate.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TriState;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import net.seppevdh.natureupdate.block.ModBlocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CornCropBlock extends CropBlock {
    public static final MapCodec<CornCropBlock> CODEC = simpleCodec(CornCropBlock::new);
    public static final int MAX_AGE = 8;
    private static final int DOUBLE_PLANT_AGE_INTERSECTION = 4;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(1, -1, 1, 14, 4, 14),
            Block.box(1, -1, 1, 14, 8, 14),
            Block.box(1, -1, 1, 14, 12, 14),
            Block.box(1, -1, 1, 14, 15, 14),
            Block.box(1, -1, 1, 14, 19, 14),
            Block.box(1, -1, 1, 14, 22, 14),
            Block.box(1, -1, 1, 14, 25, 14),
            Block.box(1, -1, 1, 14, 28, 14),
            Block.box(1, -1, 1, 14, 32, 14),
            Block.box(1, -17, 1, 14, 3, 14),
            Block.box(1, -17, 1, 14, 6, 14),
            Block.box(1, -17, 1, 14, 9, 14),
            Block.box(1, -17, 1, 14, 12, 14),
            Block.box(1, -17, 1, 14, 15, 14)
    };
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE + 1 + DOUBLE_PLANT_AGE_INTERSECTION);
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    public CornCropBlock(Properties BlockProperties) {
        super(BlockProperties);
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isAreaLoaded(pos, 1)) return;
        if (level.getRawBrightness(pos, 0) >= 9) {
            int currentAge = this.getAge(state);

            if (currentAge < MAX_AGE) {
                float growthSpeed = getGrowthSpeed(state, level, pos);

                if (CommonHooks.canCropGrow(level, pos, state, random.nextInt((int)(25.0F / growthSpeed) + 1) == 0)) {
                    if(currentAge + 1 >= DOUBLE_PLANT_AGE_INTERSECTION) {
                        if(level.getBlockState(pos.above(1)).is(Blocks.AIR) || level.getBlockState(pos.above(1)).is(this)) {
                            level.setBlock(pos.above(1), this.getStateForAge(currentAge + 2 + MAX_AGE - DOUBLE_PLANT_AGE_INTERSECTION), 2);
                            level.setBlock(pos, this.getStateForAge(currentAge + 1), 2);
                        }
                    } else {
                        level.setBlock(pos, this.getStateForAge(currentAge + 1), 2);
                    }

                    CommonHooks.fireCropGrowPost(level, pos, state);
                }
            }
        }
    }

    @Override
    public BlockState getStateForAge(int pAge) {
        if (pAge <= MAX_AGE) {
            return this.defaultBlockState().setValue(this.getAgeProperty(), pAge).setValue(HALF, DoubleBlockHalf.LOWER);
        }
        return this.defaultBlockState().setValue(this.getAgeProperty(), pAge).setValue(HALF, DoubleBlockHalf.UPPER);
    }

    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_BY_AGE[this.getAge(pState)];
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(AGE, 0).setValue(HALF, DoubleBlockHalf.LOWER);
    }

    @Override
    public void growCrops(Level pLevel, BlockPos pPos, BlockState pState) {
        int nextAge = this.getAge(pState) + this.getBonemealAgeIncrease(pLevel);
        int maxAge = this.getMaxAge();
        if(nextAge > maxAge) {
            nextAge = maxAge;
        }

        if (nextAge < DOUBLE_PLANT_AGE_INTERSECTION) {
            pLevel.setBlock(pPos, this.getStateForAge(nextAge), 2);
        } else {
            if (pLevel.getBlockState(pPos.above(1)).is(Blocks.AIR) ||
                pLevel.getBlockState(pPos.above(1)).is(this)) {
                pLevel.setBlock(pPos, this.getStateForAge(nextAge), 2);
                pLevel.setBlock(pPos.above(1), this.getStateForAge(nextAge + MAX_AGE - DOUBLE_PLANT_AGE_INTERSECTION + 1), 2);
            } else {
                nextAge = DOUBLE_PLANT_AGE_INTERSECTION - 1;
                pLevel.setBlock(pPos, this.getStateForAge(nextAge), 2);
            }
        }
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return ModBlocks.CORN.get();
    }

    @Override
    @NotNull
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE, HALF);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState) {
        return pState.getValue(AGE) < MAX_AGE;
    }

    @Override
    protected boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        if (pState.getValue(HALF) == DoubleBlockHalf.LOWER) {
            if (pState.getValue(AGE) >= DOUBLE_PLANT_AGE_INTERSECTION) {
                return pLevel.getBlockState(pPos.below()).getBlock() instanceof FarmBlock && pLevel.getBlockState(pPos.above()).is(this) &&
                        pLevel.getBlockState(pPos.above()).getValue(HALF) == DoubleBlockHalf.UPPER;
            } else {
                return pLevel.getBlockState(pPos.below()).getBlock() instanceof FarmBlock;
            }
        } else {
            return pLevel.getBlockState(pPos.below()).is(this) && pLevel.getBlockState(pPos.below()).getValue(HALF) == DoubleBlockHalf.LOWER;
        }
    }

    @Override
    public TriState canSustainPlant(BlockState state, BlockGetter level, BlockPos soilPosition, Direction facing, BlockState plant) {
        return super.canSustainPlant(state, level, soilPosition, facing, plant);
    }
}