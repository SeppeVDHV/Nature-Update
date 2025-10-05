package net.seppevdh.natureupdate.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BlockModelPart;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.client.extensions.BlockModelPartExtension;
import net.neoforged.neoforge.fluids.FluidType;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.block.entity.ModBlockEntities;
import net.seppevdh.natureupdate.block.entity.custom.WaterBoilerBlockEntity;
import net.seppevdh.natureupdate.particle.ModParticles;
import org.jetbrains.annotations.Nullable;

public class WaterBoilerBlock extends BaseEntityBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final MapCodec<WaterBoilerBlock> CODEC = simpleCodec(WaterBoilerBlock::new);

    public WaterBoilerBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    /* BLOCK ENTITY */

    @Override
    protected RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new WaterBoilerBlockEntity(blockPos, blockState);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                          Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.getItem() instanceof BucketItem item) {
            if (level.getBlockEntity(pos) instanceof WaterBoilerBlockEntity blockEntity) {
                if (blockEntity.canAddFluid(item.content)) {
                    if (stack.getItem() == Items.BUCKET) {
                        player.addItem(new ItemStack(blockEntity.getStoredFluid().getBucket()));
                    } else {
                        player.addItem(new ItemStack(Items.BUCKET));
                    }
                    stack.shrink(1);
                    blockEntity.changeFluid(item.content);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        
        if (!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);
            if(entity instanceof WaterBoilerBlockEntity waterBoilerBlockEntity) {
                ((ServerPlayer) player).openMenu(new SimpleMenuProvider(waterBoilerBlockEntity, Component.translatable("name.natureupdate.water_boiler")), pos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.SUCCESS_SERVER;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide()) {
            return null;
        }

        return createTickerHelper(pBlockEntityType, ModBlockEntities.WATER_BOILER_BE.get(),
                (level, blockPos, blockState, waterBoilerBlockEntity) -> waterBoilerBlockEntity.tick(level, blockPos, blockState));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LIT);
    }

    /* LIT */

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (!state.getValue(LIT) || ((WaterBoilerBlockEntity) level.getBlockEntity(pos)).getFluidAmount() <= 0) {
            return;
        }

        double xPos = pos.getX();
        double yPos = (double)pos.getY() + (1 / 8d);
        double zPos = pos.getZ();

        double offsetX = random.nextDouble();
        double offSetZ = random.nextDouble();
        level.addParticle(ParticleTypes.BUBBLE, xPos + offsetX, yPos, zPos + offSetZ, 0.0, 0.1, 0.0);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(LIT, false);
    }
}
