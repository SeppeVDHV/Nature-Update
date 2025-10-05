package net.seppevdh.natureupdate.event;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.block.ModBlocks;
import net.seppevdh.natureupdate.component.ModDataComponentTypes;
import net.seppevdh.natureupdate.component.custom.MoldFillerType;
import net.seppevdh.natureupdate.component.custom.MoldFillerTypes;
import net.seppevdh.natureupdate.item.ModItems;
import net.seppevdh.natureupdate.item.custom.MoldItem;
import net.seppevdh.natureupdate.villager.ModVillagers;

import java.util.List;
import java.util.Optional;

@EventBusSubscriber(modid = NatureUpdate.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {
    @SubscribeEvent
    public static void addCustomTrades (VillagerTradesEvent event) {
        if (event.getType() == ModVillagers.COOK.getKey()) {
            var trades = event.getTrades();

            trades.get(1).add(villagerTrade(new ItemStack(Items.COAL, 15), new ItemStack(Items.EMERALD), 16, 2, 0.05f));
            trades.get(1).add(villagerTrade(new ItemStack(Items.EMERALD, 2), new ItemStack(ModItems.RICE.get(), 7), 16, 1, 0.05f));
            trades.get(1).add(villagerTrade(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.BEETROOT_SOUP), 10, 2, 0.1f));

            trades.get(2).add(villagerTrade(new ItemStack(Items.BOWL, 25), new ItemStack(Items.EMERALD), 10, 1, 0.2f));
            trades.get(2).add(villagerTrade(new ItemStack(Items.EMERALD, 3), new ItemStack(Items.RABBIT_STEW), 10, 2, 0.1f));

            trades.get(3).add(villagerTrade(new ItemStack(Items.LAVA_BUCKET), new ItemStack(Items.EMERALD), 10, 2, 0f));
            trades.get(3).add(villagerTrade(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.MUSHROOM_STEW), 12, 3, 0.1f));
            trades.get(3).add(villagerTrade(new ItemStack(Items.EMERALD), itemStackForStew(MobEffects.JUMP_BOOST, 160), 12, 6, 0.1f));
            trades.get(3).add(villagerTrade(new ItemStack(Items.EMERALD), itemStackForStew(MobEffects.WEAKNESS, 140), 12, 6, 0.1f));

            trades.get(4).add(villagerTrade(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.BAKED_POTATO, 7), 12, 3, 0.2f));
            trades.get(4).add(villagerTrade(new ItemStack(Items.EMERALD, 3), new ItemStack(Items.GOLDEN_CARROT, 10), 10, 3, 0.2f));

            trades.get(5).add(villagerTrade(new ItemStack(Items.EMERALD), new ItemStack(Items.CAKE), 16, 4, 0.2f));
            trades.get(5).add(villagerTrade(new ItemStack(Items.EMERALD), new ItemStack(Items.GOLDEN_APPLE, 2), 10, 5, 0.3f));
        } else if (event.getType() == VillagerProfession.FARMER) {
            var trades = event.getTrades();

            trades.get(1).add(villagerTrade(new ItemStack(Items.EMERALD, 3), new ItemStack(ModItems.RICE_SEEDS.get(), 5), 15, 1, 0.2f));
            trades.get(1).add(villagerTrade(new ItemStack(Items.EMERALD, 4), new ItemStack(ModBlocks.CORN.get().asItem(), 7), 15, 1, 0.2f));
            trades.get(1).add(villagerTrade(new ItemStack(Items.EMERALD, 3), new ItemStack(ModBlocks.BLACKBERRY.get().asItem(), 5), 15, 1, 0.2f));
            trades.get(1).add(villagerTrade(new ItemStack(Items.EMERALD, 3), new ItemStack(ModBlocks.RASPBERRY.get().asItem(), 5), 15, 1, 0.2f));
            trades.get(1).add(villagerTrade(new ItemStack(Items.EMERALD, 3), new ItemStack(ModBlocks.STRAWBERRY.get().asItem(), 5), 15, 1, 0.2f));
        } else if (event.getType() == ModVillagers.MOLD_SMITH.getKey()) {
            var trades = event.getTrades();

            trades.get(1).add(villagerTrade(new ItemStack(Items.COAL, 15), new ItemStack(Items.EMERALD), 16, 1, 0.05f));
            trades.get(1).add(villagerTrade(new ItemStack(Items.EMERALD, 3), new ItemStack(ModItems.COPPER_AXE_HEAD.get()), 10, 2, 0.1f));
            trades.get(1).add(villagerTrade(new ItemStack(Items.EMERALD, 3), new ItemStack(ModItems.COPPER_PICKAXE_HEAD.get()), 10, 2, 0.1f));
            trades.get(1).add(villagerTrade(new ItemStack(Items.EMERALD, 2), new ItemStack(ModItems.COPPER_SWORD_HEAD.get()), 10, 2, 0.1f));
            trades.get(1).add(villagerTrade(new ItemStack(Items.EMERALD, 2), new ItemStack(ModItems.COPPER_HOE_HEAD.get()), 10, 2, 0.1f));
            trades.get(1).add(villagerTrade(new ItemStack(Items.EMERALD, 1), new ItemStack(ModItems.COPPER_SHOVEL_HEAD.get()), 10, 2, 0.1f));

            trades.get(2).add(villagerTrade(new ItemStack(Items.RAW_COPPER, 8), new ItemStack(Items.EMERALD, 3), new ItemStack(Items.COPPER_INGOT, 9), 16, 2, 0.05f));
            trades.get(2).add(villagerTrade(new ItemStack(Items.RAW_IRON, 8), new ItemStack(Items.EMERALD, 3), new ItemStack(Items.IRON_INGOT, 9), 16, 2, 0.05f));
            trades.get(2).add(villagerTrade(new ItemStack(Items.RAW_GOLD, 8), new ItemStack(Items.EMERALD, 3), new ItemStack(Items.GOLD_INGOT, 9), 16, 2, 0.05f));
            trades.get(2).add(villagerTrade(new ItemStack(ModItems.RAW_TIN.get(), 8), new ItemStack(Items.EMERALD, 3), new ItemStack(ModItems.TIN_INGOT.get(), 9), 16, 2, 0.05f));
            trades.get(2).add(villagerTrade(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.LIGHTNING_ROD), 16, 1, 0.05f));

            trades.get(3).add(villagerTrade(new ItemStack(Items.LAVA_BUCKET), new ItemStack(Items.EMERALD), 10, 2, 0.05f));
            trades.get(3).add(villagerTrade(new ItemStack(ModItems.BRONZE_INGOT.get(), 3), new ItemStack(Items.EMERALD, 4), 16, 1, 0.1f));
            trades.get(3).add(villagerTrade(new ItemStack(Items.DIAMOND), new ItemStack(Items.EMERALD), 16, 2, 0.05f));

            trades.get(4).add(villagerTrade(new ItemStack(ModItems.AXE_MOLD.get()), new ItemStack(Items.EMERALD, 3), getItemStackForMold((MoldItem) ModItems.AXE_MOLD.get(), 1, MoldFillerTypes.BRONZE), 10, 2, 0.1f));
            trades.get(4).add(villagerTrade(new ItemStack(ModItems.PICKAXE_MOLD.get()), new ItemStack(Items.EMERALD, 3), getItemStackForMold((MoldItem) ModItems.PICKAXE_MOLD.get(), 1, MoldFillerTypes.BRONZE), 10, 2, 0.1f));
            trades.get(4).add(villagerTrade(new ItemStack(ModItems.SWORD_MOLD.get()), new ItemStack(Items.EMERALD, 2), getItemStackForMold((MoldItem) ModItems.SWORD_MOLD.get(), 1, MoldFillerTypes.BRONZE), 10, 2, 0.1f));
            trades.get(4).add(villagerTrade(new ItemStack(ModItems.SHOVEL_MOLD.get()), new ItemStack(Items.EMERALD, 1), getItemStackForMold((MoldItem) ModItems.SHOVEL_MOLD.get(), 1, MoldFillerTypes.BRONZE), 10, 2, 0.1f));
            trades.get(4).add(villagerTrade(new ItemStack(ModItems.HOE_MOLD.get()), new ItemStack(Items.EMERALD, 2), getItemStackForMold((MoldItem) ModItems.HOE_MOLD.get(), 1, MoldFillerTypes.BRONZE), 10, 2, 0.1f));
            trades.get(4).add(villagerTrade(new ItemStack(ModItems.SICKLE_MOLD.get()), new ItemStack(Items.EMERALD, 3), getItemStackForMold((MoldItem) ModItems.SICKLE_MOLD.get(), 1, MoldFillerTypes.BRONZE), 10, 2, 0.1f));
            trades.get(4).add(villagerTrade(new ItemStack(ModItems.SHEARS_MOLD.get()), new ItemStack(Items.EMERALD, 2), getItemStackForMold((MoldItem) ModItems.SHEARS_MOLD.get(), 1, MoldFillerTypes.BRONZE), 10, 2, 0.1f));
            trades.get(4).add(villagerTrade(new ItemStack(ModItems.SAW_MOLD.get()), new ItemStack(Items.EMERALD, 3), getItemStackForMold((MoldItem) ModItems.SAW_MOLD.get(), 1, MoldFillerTypes.BRONZE), 10, 2, 0.1f));
            trades.get(4).add(villagerTrade(new ItemStack(ModItems.HELMET_MOLD.get()), new ItemStack(Items.EMERALD, 5), getItemStackForMold((MoldItem) ModItems.HELMET_MOLD.get(), 1, MoldFillerTypes.BRONZE), 10, 2, 0.1f));
            trades.get(4).add(villagerTrade(new ItemStack(ModItems.CHESTPLATE_MOLD.get()), new ItemStack(Items.EMERALD, 8), getItemStackForMold((MoldItem) ModItems.CHESTPLATE_MOLD.get(), 1, MoldFillerTypes.BRONZE), 10, 2, 0.1f));
            trades.get(4).add(villagerTrade(new ItemStack(ModItems.LEGGINGS_MOLD.get()), new ItemStack(Items.EMERALD, 7), getItemStackForMold((MoldItem) ModItems.LEGGINGS_MOLD.get(), 1, MoldFillerTypes.BRONZE), 10, 2, 0.1f));
            trades.get(4).add(villagerTrade(new ItemStack(ModItems.BOOTS_MOLD.get()), new ItemStack(Items.EMERALD, 4), getItemStackForMold((MoldItem) ModItems.BOOTS_MOLD.get(), 1, MoldFillerTypes.BRONZE), 10, 2, 0.1f));

            trades.get(5).add(villagerTrade(new ItemStack(Items.RAW_COPPER_BLOCK, 2), new ItemStack(Items.EMERALD, 5), 12, 3, 0.1f));
            trades.get(5).add(villagerTrade(new ItemStack(Items.RAW_IRON_BLOCK, 2), new ItemStack(Items.EMERALD, 5), 12, 3, 0.1f));
            trades.get(5).add(villagerTrade(new ItemStack(Items.RAW_GOLD_BLOCK, 2), new ItemStack(Items.EMERALD, 5), 12, 3, 0.1f));
            trades.get(5).add(villagerTrade(new ItemStack(ModBlocks.RAW_TIN_BLOCK.get(), 2), new ItemStack(Items.EMERALD, 5), 12, 3, 0.1f));
        }
    }

    private static ItemStack getItemStackForMold (MoldItem item, int count, MoldFillerType filler) {
        ItemStack rtrn = new ItemStack(item, count);
        rtrn.set(ModDataComponentTypes.MOLD_FILLER_TYPE.get(), filler);
        return rtrn;
    }

    private static ItemStack itemStackForStew (Holder<MobEffect> effect, int duration) {
        ItemStack rtrn = new ItemStack(Items.SUSPICIOUS_STEW);
        rtrn.set(DataComponents.SUSPICIOUS_STEW_EFFECTS, new SuspiciousStewEffects(List.of(new SuspiciousStewEffects.Entry(effect, duration))));
        return rtrn;
    }

    private static VillagerTrades.ItemListing villagerTrade (ItemStack input, ItemStack output, int maxUses, int xp, float multiplier) {
        return (trader, random) -> new MerchantOffer(
                new ItemCost(input.getItem(), input.getCount()),
                output, maxUses, xp, multiplier
        );
    }

    private static VillagerTrades.ItemListing villagerTrade (ItemStack input, ItemStack inputB, ItemStack output, int maxUses, int xp, float multiplier) {
        return (trader, random) -> new MerchantOffer(
                new ItemCost(input.getItem(), input.getCount()),
                Optional.of(new ItemCost(inputB.getItem(), inputB.getCount())),
                output, maxUses, xp, multiplier
        );
    }
}