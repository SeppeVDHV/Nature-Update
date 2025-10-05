package net.seppevdh.natureupdate.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.seppevdh.natureupdate.util.ModTags;

import javax.annotation.ParametersAreNonnullByDefault;

public class EndFireBlock extends BaseFireBlock {
    public static final MapCodec<EndFireBlock> CODEC = simpleCodec(EndFireBlock::new);

    public EndFireBlock(Properties p_53425_) {
        super(p_53425_, 3f);
    }

    @Override
    protected MapCodec<EndFireBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return canSurviveOnBlock(level.getBlockState(pos.below()));
    }

    public static boolean canSurviveOnBlock(BlockState state) {
        return state.is(ModTags.Blocks.END_FIRE_BASE_BLOCKS);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected BlockState updateShape(BlockState oldState, LevelReader level, ScheduledTickAccess tick,
                                     BlockPos oldPos, Direction direction, BlockPos newState, BlockState newPos, RandomSource random) {
        return this.canSurvive(oldState, level, oldPos) ? this.defaultBlockState() : Blocks.AIR.defaultBlockState();
    }

    @Override
    protected boolean canBurn(BlockState state) {
        return true;
    }
}
