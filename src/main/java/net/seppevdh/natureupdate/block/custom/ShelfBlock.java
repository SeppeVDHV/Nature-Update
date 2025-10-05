package net.seppevdh.natureupdate.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.seppevdh.natureupdate.block.entity.ModBlockEntities;
import net.seppevdh.natureupdate.block.entity.custom.ShelfBlockEntity;
import net.seppevdh.natureupdate.block.state.ModBlocksStateProperties;
import net.seppevdh.natureupdate.block.state.ShelfType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ShelfBlock extends BaseEntityBlock {
    public static final int MAX_SIZE = 3;

    private static final MapCodec<ShelfBlock> CODEC = simpleCodec(ShelfBlock::new);
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<ShelfType> TYPE = ModBlocksStateProperties.SHELF_TYPE;
    public static final IntegerProperty SIZE = IntegerProperty.create("size", 1, MAX_SIZE);
    private static final VoxelShape[] SHAPES = {
            Shapes.or(
                    Block.box(0, 0, 13, 16, 16, 16),
                    Block.box(0, 0, 11, 16, 4, 13),
                    Block.box(0, 12, 11, 16, 16, 13)
            ),
            Shapes.or(
                    Block.box(0, 0, 0, 3, 16, 16),
                    Block.box(3, 0, 0, 5, 4, 16),
                    Block.box(3, 12, 0, 5, 16, 16)
            ),
            Shapes.or(
                    Block.box(0, 0, 0, 16, 16, 3),
                    Block.box(0, 0, 3, 16, 4, 5),
                    Block.box(0, 12, 3, 16, 16, 5)
            ),
            Shapes.or(
                    Block.box(13, 0, 0, 16, 16, 16),
                    Block.box(11, 0, 0, 13, 4, 16),
                    Block.box(11, 12, 0, 13, 16, 16)
            )
    };

    public ShelfBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    @NotNull
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ShelfBlockEntity(pPos, pState);
    }

    @Override
    @NotNull
    protected RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    @NotNull
    protected BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    @NotNull
    protected BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite()).setValue(TYPE, this.getType(pContext));
    }

    private ShelfType getType (BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        return getType(level, pos);
    }

    private ShelfType getType (Level level, BlockPos pos) {
        return level.hasNeighborSignal(pos) ? ShelfType.SINGLE : ShelfType.UNPOWERED;
    }

    private static BlockPos getBlockPosLeft (BlockPos pos, BlockState state) {
        return getBlockPosLeft(pos, state, 1);
    }

    private static BlockPos getBlockPosLeft (BlockPos pos, BlockState state, int dist) {
        return switch (state.getValue(FACING)) {
            case EAST -> pos.south(dist);
            case NORTH -> pos.east(dist);
            case WEST -> pos.north(dist);
            default -> pos.west(dist);
        };
    }

    private static BlockPos getBlockPosRight (BlockPos pos, BlockState state) {
        return getBlockPosRight(pos, state, 1);
    }

    private static BlockPos getBlockPosRight (BlockPos pos, BlockState state, int dist) {
        return switch (state.getValue(FACING)) {
            case WEST -> pos.south(dist);
            case SOUTH -> pos.east(dist);
            case EAST -> pos.north(dist);
            default -> pos.west(dist);
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, TYPE, SIZE);
    }

    @Override
    @NotNull
    protected InteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if (pLevel.getBlockEntity(pPos) instanceof ShelfBlockEntity shelfBlockEntity && pHitResult.getDirection() == pState.getValue(FACING)) {
            if (pState.getValue(TYPE) == ShelfType.UNPOWERED) {
                ItemStack stack = shelfBlockEntity.itemHandler.getStackInSlot(getPartOfFace(pHitResult.getLocation(), pState.getValue(FACING), pPos));
                shelfBlockEntity.itemHandler.setStackInSlot(getPartOfFace(pHitResult.getLocation(), pState.getValue(FACING), pPos), pStack);
                pPlayer.setItemInHand(pHand, stack);
            } else {
                switch (pState.getValue(TYPE)) {
                    case SINGLE:
                        useItemOnPoweredState(pLevel, pPos, pPlayer, 2);
                        break;
                    case RIGHT:
                        useItemOnPoweredState(pLevel, pPos, pPlayer, 2);
                        useItemOnPoweredState(pLevel, getBlockPosLeft(pPos, pState), pPlayer, 1);
                        if (pState.getValue(SIZE) == 3) {
                            useItemOnPoweredState(pLevel, getBlockPosLeft(pPos, pState, 2), pPlayer, 0);
                        }
                        break;
                    case MIDDLE:
                        useItemOnPoweredState(pLevel, pPos, pPlayer, 1);
                        useItemOnPoweredState(pLevel, getBlockPosRight(pPos, pState), pPlayer, 2);
                        useItemOnPoweredState(pLevel, getBlockPosLeft(pPos, pState), pPlayer, 0);
                        break;
                    case LEFT:
                        if (pState.getValue(SIZE) == 2) {
                            useItemOnPoweredState(pLevel, pPos, pPlayer, 1);
                            useItemOnPoweredState(pLevel, getBlockPosRight(pPos, pState), pPlayer, 2);
                        } else {
                            useItemOnPoweredState(pLevel, pPos, pPlayer, 0);
                            useItemOnPoweredState(pLevel, getBlockPosRight(pPos, pState), pPlayer, 1);
                            useItemOnPoweredState(pLevel, getBlockPosRight(pPos, pState, 2), pPlayer, 2);
                        }
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.CONSUME;
    }

    private void useItemOnPoweredState (Level level, BlockPos pos, Player player, int part) {
        if (level.getBlockEntity(pos) instanceof ShelfBlockEntity blockEntity) {
            for (int i = 0; i < 3; i++) {
                ItemStack stack = player.getInventory().getItem(i + part * 3).copy();
                player.getInventory().setItem(i + part * 3, blockEntity.itemHandler.getStackInSlot(i));
                blockEntity.itemHandler.setStackInSlot(i, stack);
            }
        }
    }

    private static int getPartOfFace (Vec3 vec3, Direction facing, BlockPos pos) {
        Vec3 nVec3 = vec3.subtract(pos.getX(), pos.getY(), pos.getZ());
        int rtrn =  switch (facing) {
            case NORTH : yield 2 - (int) Math.floor(nVec3.x * 3);
            case EAST : yield 2 - (int) Math.floor(nVec3.z * 3);
            case SOUTH : yield (int) Math.floor(nVec3.x * 3);
            default : yield (int) Math.floor(nVec3.z * 3);
        };
        while (rtrn < 0) {
            rtrn += 3;
        }
        return rtrn % 3;
    }

    @Override
    @NotNull
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPES[getIndexByRotation(pState)];
    }

    @Override
    @NotNull
    protected VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPES[getIndexByRotation(pState)];
    }

    @Override
    protected boolean isCollisionShapeFullBlock(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return false;
    }

    private static int getIndexByRotation (BlockState state) {
        return switch (state.getValue(FACING)) {
            case NORTH -> 0;
            case EAST -> 1;
            case SOUTH -> 2;
            default -> 3;
        };
    }

    @Override
    public void tick (BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        /*
        if ((state.getValue(TYPE) == ShelfType.SINGLE || state.getValue(TYPE) == ShelfType.UNPOWERED) && state.getValue(SIZE) != 1) {
            changeState(level, pos, state.getValue(TYPE), 1);
        } else {
            if (state.getValue(TYPE) == ShelfType.LEFT && !(level.getBlockState(getBlockPosRight(pos, state)).getBlock() instanceof ShelfBlock)) {
                changeState(level, pos, ShelfType.SINGLE, 1);
            } else if (state.getValue(TYPE) == ShelfType.MIDDLE && !(level.getBlockState(getBlockPosRight(pos, state)).getBlock() instanceof ShelfBlock)) {
                changeState(level, pos, ShelfType.RIGHT, 2);
                changeState(level, getBlockPosLeft(pos, state), ShelfType.LEFT, 2);
            } else if (state.getValue(TYPE) == ShelfType.MIDDLE && !(level.getBlockState(getBlockPosLeft(pos, state)).getBlock() instanceof ShelfBlock)) {
                changeState(level, pos, ShelfType.LEFT, 2);
                changeState(level, getBlockPosRight(pos, state), ShelfType.RIGHT, 2);
            } else if (state.getValue(TYPE) == ShelfType.RIGHT && !(level.getBlockState(getBlockPosLeft(pos, state)).getBlock() instanceof ShelfBlock)) {
                changeState(level, pos, ShelfType.SINGLE, 1);
            }
        }

        state = level.getBlockState(pos);
        */
        this.updateSize(level, pos, state);
        state = level.getBlockState(pos);

        if (state.getValue(TYPE) != ShelfType.UNPOWERED) {
            if (!level.hasNeighborSignal(pos)) {
                switch (state.getValue(TYPE)) {
                    case MIDDLE :
                        changeState(level, getBlockPosLeft(pos, state), ShelfType.SINGLE, 1);
                        changeState(level, getBlockPosRight(pos, state), ShelfType.SINGLE, 1);
                        break;
                    case LEFT:
                        if (state.getValue(SIZE) == 2) {
                            changeState(level, getBlockPosRight(pos, state), ShelfType.SINGLE, 1);
                        } else {
                            changeState(level, getBlockPosRight(pos, state), ShelfType.LEFT, 2);
                            changeState(level, getBlockPosRight(pos, state, 2), ShelfType.RIGHT, 2);
                        }
                        break;
                    case RIGHT:
                        if (state.getValue(SIZE) == 2) {
                            changeState(level, getBlockPosLeft(pos, state), ShelfType.SINGLE, 1);
                        } else {
                            changeState(level, getBlockPosLeft(pos, state), ShelfType.RIGHT, 2);
                            changeState(level, getBlockPosLeft(pos, state, 2), ShelfType.LEFT, 2);
                        }
                        break;
                }
                changeState(level, pos, ShelfType.SINGLE, 1);
            } else if (state.getValue(SIZE) < MAX_SIZE) {
                BlockPos leftPos = getBlockPosLeft(pos, state);
                BlockPos rightPos = getBlockPosRight(pos, state);
                if (level.getBlockState(leftPos).getBlock() instanceof ShelfBlock && level.getBlockState(leftPos).getValue(TYPE) != ShelfType.UNPOWERED && level.getBlockState(leftPos).getValue(SIZE) + state.getValue(SIZE) <= MAX_SIZE && isTypeAnyOf(state, ShelfType.LEFT, ShelfType.SINGLE)) {
                    int newSize = level.getBlockState(leftPos).getValue(SIZE) + state.getValue(SIZE);
                    if (newSize == 2) {
                        ((ShelfBlockEntity) level.getBlockEntity(pos)).scheduleUpdate(state.setValue(TYPE, ShelfType.RIGHT).setValue(SIZE, 2));
                        ((ShelfBlockEntity) level.getBlockEntity(leftPos)).scheduleUpdate(state.setValue(TYPE, ShelfType.LEFT).setValue(SIZE, 2));
                    } else if (state.getValue(SIZE) == 2) {

                    } else {

                    }
                }
                state = level.getBlockState(pos);
                if (level.getBlockState(rightPos).getBlock() instanceof ShelfBlock && level.getBlockState(rightPos).getValue(TYPE) != ShelfType.UNPOWERED && level.getBlockState(rightPos).getValue(SIZE) + state.getValue(SIZE) <= MAX_SIZE && isTypeAnyOf(state, ShelfType.RIGHT, ShelfType.SINGLE)) {
                    int newSize = level.getBlockState(rightPos).getValue(SIZE) + state.getValue(SIZE);
                    if (newSize == 2) {
                        ((ShelfBlockEntity) level.getBlockEntity(pos)).scheduleUpdate(state.setValue(TYPE, ShelfType.LEFT).setValue(SIZE, 2));
                        ((ShelfBlockEntity) level.getBlockEntity(rightPos)).scheduleUpdate(state.setValue(TYPE, ShelfType.RIGHT).setValue(SIZE, 2));
                    } else if (state.getValue(SIZE) == 2) {

                    } else {

                    }
                }
            }
        } else if (level.hasNeighborSignal(pos)) {
            changeState(level, pos, ShelfType.SINGLE, 1);
        }
    }

    private static void changeState (Level level, BlockPos pos, ShelfType type, int size) {
        if (level.getBlockState(pos).getBlock() instanceof ShelfBlock) {
            level.setBlock(pos, level.getBlockState(pos).setValue(TYPE, type).setValue(SIZE, size), 2);
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide()) {
            return null;
        }

        return createTickerHelper(pBlockEntityType, ModBlockEntities.SHELF_BE.get(),
                (level, blockPos, blockState, shelfBlockEntity) -> shelfBlockEntity.tick(level, blockPos, blockState));
    }

    public void updateSize (Level level, BlockPos pos, BlockState state) {
        BlockPos pos1 = pos;
        int size = 1;
        if (state.getValue(TYPE) == ShelfType.LEFT) {
            while (level.getBlockState(pos1).getBlock() instanceof ShelfBlock && level.getBlockState(pos1).getValue(TYPE) != ShelfType.RIGHT && size < MAX_SIZE) {
                pos1 = getBlockPosRight(pos1, level.getBlockState(pos1));
                size++;
            }
            if (state.getValue(SIZE) != size) {
                changeState(level, pos, state.getValue(TYPE), size);
            }
        } else if (state.getValue(TYPE) == ShelfType.RIGHT) {
            while (level.getBlockState(pos1).getBlock() instanceof ShelfBlock && level.getBlockState(pos1).getValue(TYPE) != ShelfType.LEFT && size < MAX_SIZE) {
                pos1 = getBlockPosLeft(pos1, level.getBlockState(pos1));
                size++;
            }
            if (state.getValue(SIZE) != size) {
                changeState(level, pos, state.getValue(TYPE), size);
            }
        } else if (state.getValue(TYPE) == ShelfType.MIDDLE && state.getValue(SIZE) != 3) {
            changeState(level, pos, ShelfType.MIDDLE, 3);
        }
    }

    private static boolean isTypeAnyOf (BlockState state, ShelfType... types) {
        for (ShelfType type : types) {
            if (state.getValue(TYPE) == type) {
                return true;
            }
        }
        return false;
    }
}