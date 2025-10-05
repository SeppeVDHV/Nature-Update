package net.seppevdh.natureupdate.block.custom;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.seppevdh.natureupdate.block.ModBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class TreeCropBlock extends CropBlock {
    public static final int MAX_AGE = 7;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);
    private final String seedId;
    private static ArrayList<RegisteredTreeCropBlock> registeredTreeCropBlocks = new ArrayList<>();

    public TreeCropBlock(Properties pProperties, String pSeedId) {
        super(pProperties);
        this.seedId = pSeedId;
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return getSeedOfCrop(this.seedId);
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public BlockState getStateForAge(int pAge) {
        if (pAge >= MAX_AGE) {
            return getGrownSaplingOfCrop(this.seedId).defaultBlockState();
        } else {
            return this.defaultBlockState().setValue(AGE, pAge);
        }
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }

    private record RegisteredTreeCropBlock (String id, ItemLike seed, Block sapling) {
        public boolean isId (String pId) {
            return Objects.equals(id, pId);
        }
    }

    private static ItemLike getSeedOfCrop (String seedId) {
        for (RegisteredTreeCropBlock block: registeredTreeCropBlocks) {
            if (block.isId(seedId)) {
                return block.seed();
            }
        }
        return Items.AIR;
    }

    private static Block getGrownSaplingOfCrop (String seedId) {
        for (RegisteredTreeCropBlock block: registeredTreeCropBlocks) {
            if (block.isId(seedId)) {
                return block.sapling;
            }
        }
        return Blocks.AIR;
    }

    public static void registerTreeCropBlock (String id, ItemLike seed, Block sapling) {
        registeredTreeCropBlocks.add(new RegisteredTreeCropBlock(id, seed, sapling));
    }

    public static void registerVanillaTreeCropBlocks () {
        registeredTreeCropBlocks.add(new RegisteredTreeCropBlock("oak", ModBlocks.OAK_CROP.get().asItem(), Blocks.OAK_SAPLING));
        registeredTreeCropBlocks.add(new RegisteredTreeCropBlock("birch", ModBlocks.BIRCH_CROP.get().asItem(), Blocks.BIRCH_SAPLING));
        registeredTreeCropBlocks.add(new RegisteredTreeCropBlock("spruce", ModBlocks.SPRUCE_CROP.get().asItem(), Blocks.SPRUCE_SAPLING));
        registeredTreeCropBlocks.add(new RegisteredTreeCropBlock("acacia", ModBlocks.ACACIA_CROP.get().asItem(), Blocks.ACACIA_SAPLING));
        registeredTreeCropBlocks.add(new RegisteredTreeCropBlock("jungle", ModBlocks.JUNGLE_CROP.get().asItem(), Blocks.JUNGLE_SAPLING));
        registeredTreeCropBlocks.add(new RegisteredTreeCropBlock("dark_oak", ModBlocks.DARK_OAK_CROP.get().asItem(), Blocks.DARK_OAK_SAPLING));
        registeredTreeCropBlocks.add(new RegisteredTreeCropBlock("cherry", ModBlocks.CHERRY_CROP.get().asItem(), Blocks.CHERRY_SAPLING));
        registeredTreeCropBlocks.add(new RegisteredTreeCropBlock("pale_oak", ModBlocks.PALE_OAK_CROP.get().asItem(), Blocks.PALE_OAK_SAPLING));
    }
}