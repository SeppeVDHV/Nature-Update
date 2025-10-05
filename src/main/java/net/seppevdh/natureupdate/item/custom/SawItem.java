package net.seppevdh.natureupdate.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SawItem extends Item {
    public SawItem(Item.Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        BlockState state = level.getBlockState(pos);
        Player player = pContext.getPlayer();
        ItemStack stack = pContext.getItemInHand();
        if (state.is(BlockTags.LOGS)) {
            assert player != null;
            stack.hurtAndBreak((int) Math.floor(removeLog(level, pos) / 7d), player, LivingEntity.getSlotForHand(pContext.getHand()));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    private static int removeLog (Level level, BlockPos pos) {
        int rtrn = 1;
        level.destroyBlock(pos, true);
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                for (int z = -1; z < 2; z++) {
                    if (level.getBlockState(pos.above(y).east(x).south(z)).is(BlockTags.LOGS)) {
                        removeLog(level, pos.above(y).east(x).south(z));
                        rtrn++;
                    }
                }
            }
        }
        return rtrn;
    }

    public static ItemAttributeModifiers createAttributes(ToolMaterial toolTier, float pAttackDamage, double pAttackSpeed) {
        return ItemAttributeModifiers.builder().add(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(BASE_ATTACK_DAMAGE_ID, pAttackDamage + toolTier.attackDamageBonus(), AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
        ).add(
                Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, pAttackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND
        ).build();
    }
}