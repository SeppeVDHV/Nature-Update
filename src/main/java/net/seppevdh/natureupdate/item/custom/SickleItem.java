package net.seppevdh.natureupdate.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Objects;


public class SickleItem extends Item {
    private static ArrayList<HarvestableBlock> HARVESTABLES = new ArrayList<>();

    public SickleItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        BlockState state = level.getBlockState(pos);
        Player player = pContext.getPlayer();
        if (isBlockHarvestable(state.getBlock())) {
            @NotNull HarvestableBlock block = Objects.requireNonNull(getHarvestableBlock(state.getBlock()));
            if (!block.isBlockDoublePlant()) {
                if (state.getValue(block.getAgeProperty()) == 0) {
                    return InteractionResult.PASS;
                }
                level.destroyBlock(pos, true);
                level.setBlockAndUpdate(pos, state.setValue(block.getAgeProperty(), 0));
                if (player != null) {
                    pContext.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(pContext.getHand()));
                }
                return InteractionResult.SUCCESS;
            } else {
                if (state.getValue(block.getAgeProperty()) == 0) {
                    return InteractionResult.PASS;
                }
                assert block.getDoubleBlockHalfProperty() != null;
                level.destroyBlock(pos, true);
                if (state.getValue(block.getDoubleBlockHalfProperty()) == DoubleBlockHalf.LOWER) {
                    level.setBlockAndUpdate(pos, state.setValue(block.getAgeProperty(), 0));
                    if (level.getBlockState(pos.above()).is(state.getBlock())) {
                        level.destroyBlock(pos.above(), true);
                    }
                } else {
                    level.setBlockAndUpdate(pos.below(), state.setValue(block.getAgeProperty(), 0).setValue(block.getDoubleBlockHalfProperty(), DoubleBlockHalf.LOWER));
                }
                if (player != null) {
                    pContext.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(pContext.getHand()));
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    public static ItemAttributeModifiers createAttributes(float pAttackDamage, double pAttackSpeed) {
        return ItemAttributeModifiers.builder().add(
            Attributes.ATTACK_DAMAGE,
            new AttributeModifier(BASE_ATTACK_DAMAGE_ID, pAttackDamage, AttributeModifier.Operation.ADD_VALUE),
            EquipmentSlotGroup.MAINHAND
        ).add(
            Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, pAttackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND
        ).build();
    }

    public static void addHarvestable (Block block, IntegerProperty ageProperty) {
        HARVESTABLES.add(new HarvestableBlock(block, ageProperty));
    }

    public static void addHarvestableDoubleCrop (Block block, IntegerProperty ageProperty, EnumProperty<DoubleBlockHalf> doubleBlockHalfProperty) {
        HARVESTABLES.add(new HarvestableBlock(block, ageProperty, doubleBlockHalfProperty));
    }

    public static void addVanillaHarvestableBlocks () {
        HARVESTABLES.add(new HarvestableBlock(Blocks.WHEAT, CropBlock.AGE));
        HARVESTABLES.add(new HarvestableBlock(Blocks.POTATOES, CropBlock.AGE));
        HARVESTABLES.add(new HarvestableBlock(Blocks.CARROTS, CropBlock.AGE));
        HARVESTABLES.add(new HarvestableBlock(Blocks.BEETROOTS, BeetrootBlock.AGE));
        HARVESTABLES.add(new HarvestableBlock(Blocks.SWEET_BERRY_BUSH, SweetBerryBushBlock.AGE));
        HARVESTABLES.add(new HarvestableBlock(Blocks.NETHER_WART, NetherWartBlock.AGE));
    }

    private static HarvestableBlock getHarvestableBlock (Block block) {
        for (HarvestableBlock block1 : HARVESTABLES) {
            if (block1.isBlock(block)) {
                return block1;
            }
        }
        return null;
    }

    private static boolean isBlockHarvestable (Block block) {
        return getHarvestableBlock(block) != null;
    }

    public static class HarvestableBlock {
        private final boolean isDoublePlant;
        private final Block block;
        private final IntegerProperty ageProperty;
        private EnumProperty<DoubleBlockHalf> doubleBlockHalfProperty = null;

        public HarvestableBlock (Block block, IntegerProperty ageProperty) {
            this.block = block;
            this.ageProperty = ageProperty;
            this.isDoublePlant = false;
        }

        public HarvestableBlock (Block block, IntegerProperty ageProperty, EnumProperty<DoubleBlockHalf> doubleBlockHalfProperty) {
            this.block = block;
            this.ageProperty = ageProperty;
            this.doubleBlockHalfProperty = doubleBlockHalfProperty;
            this.isDoublePlant = true;
        }

        public boolean isBlock (Block block1) {
            return block1 == this.block;
        }

        public boolean isBlockDoublePlant () {
            return this.isDoublePlant;
        }

        @NotNull
        public IntegerProperty getAgeProperty () {
            return this.ageProperty;
        }

        @Nullable
        public EnumProperty<DoubleBlockHalf> getDoubleBlockHalfProperty () {
            return this.doubleBlockHalfProperty;
        }
    }
}
