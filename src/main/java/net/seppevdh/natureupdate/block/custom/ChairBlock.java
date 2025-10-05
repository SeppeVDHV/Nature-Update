package net.seppevdh.natureupdate.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.seppevdh.natureupdate.entity.ModEntities;
import net.seppevdh.natureupdate.entity.custom.ChairEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChairBlock extends HorizontalDirectionalBlock {
    private static final MapCodec<ChairBlock> CODEC = simpleCodec(ChairBlock::new);
    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(3, 0, 3, 5, 6, 5),
            Block.box(11, 0, 3, 13, 6, 5),
            Block.box(3, 0, 11, 5, 6, 13),
            Block.box(11, 0, 11, 13, 6, 13),
            Block.box(3, 6, 3, 13, 8, 13)
    );

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            Entity entity = null;
            List<ChairEntity> entities = level.getEntities(ModEntities.CHAIR.get(), new AABB(pos), chair -> true);
            if (entities.isEmpty()) {
                entity = ModEntities.CHAIR.get().spawn((ServerLevel) level, pos, EntitySpawnReason.TRIGGERED);
                player.startRiding(entity);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if(!level.isClientSide()) {
            List<ChairEntity> entities = level.getEntities(ModEntities.CHAIR.get(), new AABB(pos), chair -> true);
            if(!entities.isEmpty()) {
                for (ChairEntity entity : entities) {
                    entity.kill((ServerLevel) level);
                }
            }
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    public ChairBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MapCodec<ChairBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return getShape(pState);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return getShape(pState);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    private VoxelShape getShape (BlockState state) {
        return switch (state.getValue(FACING)) {
            case NORTH -> Shapes.or(SHAPE, Block.box(3, 8, 11, 13, 16, 13));
            case EAST -> Shapes.or(SHAPE, Block.box(3, 8, 3, 5, 16, 13));
            case SOUTH -> Shapes.or(SHAPE, Block.box(3, 8, 3, 13, 16, 5));
            default -> Shapes.or(SHAPE, Block.box(11, 8, 3, 13, 16, 13));
        };
    }
}