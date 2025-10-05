package net.seppevdh.natureupdate.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.block.ModBlocks;
import net.seppevdh.natureupdate.fluid.ModFluids;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NatureUpdate.MOD_ID);

    public static final Supplier<CreativeModeTab> NATURE_UPDATE_ITEMS = CREATIVE_MODE_TABS.register("nature_update_items",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BRONZE_INGOT.get()))
                    .title(Component.translatable("creativetab.natureupdate.nature_update_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.TIN_INGOT.get());
                        output.accept(ModItems.RAW_TIN.get());
                        output.accept(ModItems.BRONZE_INGOT.get());
                        output.accept(ModItems.RICE_SEEDS.get());
                        output.accept(ModItems.RICE.get());
                        output.accept(ModBlocks.CORN.get());
                        output.accept(ModBlocks.RHUBARB.get());
                        output.accept(ModItems.ELECTRODROME_MUSIC_DISC.get());
                        output.accept(ModItems.SOUL_COAL.get());
                        output.accept(ModItems.ENDER_COAL.get());
                        output.accept(ModBlocks.OAK_CROP.get());
                        output.accept(ModBlocks.BIRCH_CROP.get());
                        output.accept(ModBlocks.SPRUCE_CROP.get());
                        output.accept(ModBlocks.ACACIA_CROP.get());
                        output.accept(ModBlocks.JUNGLE_CROP.get());
                        output.accept(ModBlocks.DARK_OAK_CROP.get());
                        output.accept(ModBlocks.CHERRY_CROP.get());
                        output.accept(ModBlocks.PALE_OAK_CROP.get());
                        output.accept(ModItems.PEAR.get());
                        output.accept(ModBlocks.BLACKBERRY.get());
                        output.accept(ModBlocks.STRAWBERRY.get());
                        output.accept(ModBlocks.RASPBERRY.get());
                        output.accept(ModItems.AXE_MOLD.get());
                        output.accept(ModItems.PICKAXE_MOLD.get());
                        output.accept(ModItems.SWORD_MOLD.get());
                        output.accept(ModItems.SHOVEL_MOLD.get());
                        output.accept(ModItems.HOE_MOLD.get());
                        output.accept(ModItems.SAW_MOLD.get());
                        output.accept(ModItems.SICKLE_MOLD.get());
                        output.accept(ModItems.SHEARS_MOLD.get());
                        output.accept(ModItems.HELMET_MOLD.get());
                        output.accept(ModItems.CHESTPLATE_MOLD.get());
                        output.accept(ModItems.LEGGINGS_MOLD.get());
                        output.accept(ModItems.BOOTS_MOLD.get());
                        output.accept(ModItems.COPPER_AXE_HEAD.get());
                        output.accept(ModItems.COPPER_PICKAXE_HEAD.get());
                        output.accept(ModItems.COPPER_SWORD_HEAD.get());
                        output.accept(ModItems.COPPER_SHOVEL_HEAD.get());
                        output.accept(ModItems.COPPER_HOE_HEAD.get());
                        output.accept(ModItems.COPPER_SAW_HEAD.get());
                        output.accept(ModItems.BRONZE_AXE_HEAD.get());
                        output.accept(ModItems.BRONZE_PICKAXE_HEAD.get());
                        output.accept(ModItems.BRONZE_SWORD_HEAD.get());
                        output.accept(ModItems.BRONZE_SHOVEL_HEAD.get());
                        output.accept(ModItems.BRONZE_HOE_HEAD.get());
                        output.accept(ModItems.BRONZE_SAW_HEAD.get());
                        output.accept(ModItems.DIAMOND_AXE_HEAD.get());
                        output.accept(ModItems.DIAMOND_PICKAXE_HEAD.get());
                        output.accept(ModItems.DIAMOND_SWORD_HEAD.get());
                        output.accept(ModItems.DIAMOND_SHOVEL_HEAD.get());
                        output.accept(ModItems.DIAMOND_HOE_HEAD.get());
                        output.accept(ModItems.DIAMOND_SAW_HEAD.get());
                        output.accept(ModItems.EMERALD_AXE_HEAD.get());
                        output.accept(ModItems.EMERALD_PICKAXE_HEAD.get());
                        output.accept(ModItems.EMERALD_SWORD_HEAD.get());
                        output.accept(ModItems.EMERALD_SHOVEL_HEAD.get());
                        output.accept(ModItems.EMERALD_HOE_HEAD.get());
                        output.accept(ModItems.EMERALD_SAW_HEAD.get());
                        output.accept(ModItems.GOLDEN_AXE_HEAD.get());
                        output.accept(ModItems.GOLDEN_PICKAXE_HEAD.get());
                        output.accept(ModItems.GOLDEN_SWORD_HEAD.get());
                        output.accept(ModItems.GOLDEN_SHOVEL_HEAD.get());
                        output.accept(ModItems.GOLDEN_HOE_HEAD.get());
                        output.accept(ModItems.GOLDEN_SAW_HEAD.get());
                        output.accept(ModItems.IRON_AXE_HEAD.get());
                        output.accept(ModItems.IRON_PICKAXE_HEAD.get());
                        output.accept(ModItems.IRON_SWORD_HEAD.get());
                        output.accept(ModItems.IRON_SHOVEL_HEAD.get());
                        output.accept(ModItems.IRON_HOE_HEAD.get());
                        output.accept(ModItems.IRON_SAW_HEAD.get());
                        output.accept(ModItems.SICKLE_HEAD.get());
                        output.accept(ModFluids.SOUL_FLUID_BUCKET.get());
                        output.accept(ModFluids.END_FLUID_BUCKET.get());
                        output.accept(ModItems.NETHER_ESSENCE.get());
                        output.accept(ModItems.SOUL_ESSENCE.get());
                        output.accept(ModItems.ENDER_ESSENCE.get());
                    }).build());

    public static final Supplier<CreativeModeTab> NATURE_UPDATE_TOOLS = CREATIVE_MODE_TABS.register("nature_update_tools",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.COPPER_PICKAXE.get()))
                    .title(Component.translatable("creativetab.natureupdate.nature_update_tools"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.BRONZE_SWORD.get());
                        output.accept(ModItems.BRONZE_SHOVEL.get());
                        output.accept(ModItems.BRONZE_HOE.get());
                        output.accept(ModItems.BRONZE_AXE.get());
                        output.accept(ModItems.BRONZE_PICKAXE.get());
                        output.accept(ModItems.BRONZE_HELMET.get());
                        output.accept(ModItems.BRONZE_CHESTPLATE.get());
                        output.accept(ModItems.BRONZE_LEGGINGS.get());
                        output.accept(ModItems.BRONZE_BOOTS.get());
                        output.accept(ModItems.EMERALD_SWORD.get());
                        output.accept(ModItems.EMERALD_SHOVEL.get());
                        output.accept(ModItems.EMERALD_HOE.get());
                        output.accept(ModItems.EMERALD_AXE.get());
                        output.accept(ModItems.EMERALD_PICKAXE.get());
                        output.accept(ModItems.EMERALD_HELMET.get());
                        output.accept(ModItems.EMERALD_CHESTPLATE.get());
                        output.accept(ModItems.EMERALD_LEGGINGS.get());
                        output.accept(ModItems.EMERALD_BOOTS.get());
                        output.accept(ModItems.COPPER_SWORD.get());
                        output.accept(ModItems.COPPER_SHOVEL.get());
                        output.accept(ModItems.COPPER_HOE.get());
                        output.accept(ModItems.COPPER_AXE.get());
                        output.accept(ModItems.COPPER_PICKAXE.get());
                        output.accept(ModItems.COPPER_HELMET.get());
                        output.accept(ModItems.COPPER_CHESTPLATE.get());
                        output.accept(ModItems.COPPER_LEGGINGS.get());
                        output.accept(ModItems.COPPER_BOOTS.get());
                        output.accept(ModItems.BRONZE_HORSE_ARMOR.get());
                        output.accept(ModItems.EMERALD_HORSE_ARMOR.get());
                        output.accept(ModItems.COPPER_HORSE_ARMOR.get());
                        output.accept(ModItems.NETHERITE_HORSE_ARMOR.get());
                        output.accept(ModItems.SICKLE.get());
                        output.accept(ModItems.COPPER_SAW.get());
                        output.accept(ModItems.IRON_SAW.get());
                        output.accept(ModItems.GOLDEN_SAW.get());
                        output.accept(ModItems.EMERALD_SAW.get());
                        output.accept(ModItems.BRONZE_SAW.get());
                        output.accept(ModItems.DIAMOND_SAW.get());
                        output.accept(ModItems.NETHERITE_SAW.get());
                    }).build());

    public static final Supplier<CreativeModeTab> NATURE_UPDATE_BUILDING_BLOCKS = CREATIVE_MODE_TABS.register("nature_update_building_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.SPRUCE_MOSAIC.get()))
                    .title(Component.translatable("creativetab.natureupdate.nature_update_building_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.OAK_MOSAIC.get());
                        output.accept(ModBlocks.OAK_MOSAIC_STAIRS.get());
                        output.accept(ModBlocks.OAK_MOSAIC_SLAB.get());
                        output.accept(ModBlocks.BIRCH_MOSAIC.get());
                        output.accept(ModBlocks.BIRCH_MOSAIC_STAIRS.get());
                        output.accept(ModBlocks.BIRCH_MOSAIC_SLAB.get());
                        output.accept(ModBlocks.SPRUCE_MOSAIC.get());
                        output.accept(ModBlocks.SPRUCE_MOSAIC_STAIRS.get());
                        output.accept(ModBlocks.SPRUCE_MOSAIC_SLAB.get());
                        output.accept(ModBlocks.JUNGLE_MOSAIC.get());
                        output.accept(ModBlocks.JUNGLE_MOSAIC_STAIRS.get());
                        output.accept(ModBlocks.JUNGLE_MOSAIC_SLAB.get());
                        output.accept(ModBlocks.ACACIA_MOSAIC.get());
                        output.accept(ModBlocks.ACACIA_MOSAIC_STAIRS.get());
                        output.accept(ModBlocks.ACACIA_MOSAIC_SLAB.get());
                        output.accept(ModBlocks.DARK_OAK_MOSAIC.get());
                        output.accept(ModBlocks.DARK_OAK_MOSAIC_STAIRS.get());
                        output.accept(ModBlocks.DARK_OAK_MOSAIC_SLAB.get());
                        output.accept(ModBlocks.MANGROVE_MOSAIC.get());
                        output.accept(ModBlocks.MANGROVE_MOSAIC_STAIRS.get());
                        output.accept(ModBlocks.MANGROVE_MOSAIC_SLAB.get());
                        output.accept(ModBlocks.CHERRY_MOSAIC.get());
                        output.accept(ModBlocks.CHERRY_MOSAIC_STAIRS.get());
                        output.accept(ModBlocks.CHERRY_MOSAIC_SLAB.get());
                        output.accept(ModBlocks.PALE_OAK_MOSAIC.get());
                        output.accept(ModBlocks.PALE_OAK_MOSAIC_STAIRS.get());
                        output.accept(ModBlocks.PALE_OAK_MOSAIC_SLAB.get());
                        output.accept(ModBlocks.CRIMSON_MOSAIC.get());
                        output.accept(ModBlocks.CRIMSON_MOSAIC_STAIRS.get());
                        output.accept(ModBlocks.CRIMSON_MOSAIC_SLAB.get());
                        output.accept(ModBlocks.WARPED_MOSAIC.get());
                        output.accept(ModBlocks.WARPED_MOSAIC_STAIRS.get());
                        output.accept(ModBlocks.WARPED_MOSAIC_SLAB.get());
                        output.accept(ModBlocks.SMOOTH_STONE_STAIRS.get());
                        output.accept(ModBlocks.IRON_STAIRS.get());
                        output.accept(ModBlocks.IRON_SLAB.get());
                        output.accept(ModBlocks.IRON_BUTTON.get());
                        output.accept(ModBlocks.IRON_GRATE.get());
                        output.accept(ModBlocks.GOLDEN_STAIRS.get());
                        output.accept(ModBlocks.GOLDEN_SLAB.get());
                        output.accept(ModBlocks.GOLDEN_BUTTON.get());
                        output.accept(ModBlocks.GOLDEN_DOOR.get());
                        output.accept(ModBlocks.GOLDEN_TRAPDOOR.get());
                        output.accept(ModBlocks.GOLDEN_GRATE.get());
                        output.accept(ModBlocks.GOLD_BARS.get());
                        output.accept(ModBlocks.EMERALD_STAIRS.get());
                        output.accept(ModBlocks.EMERALD_SLAB.get());
                        output.accept(ModBlocks.EMERALD_BUTTON.get());
                        output.accept(ModBlocks.EMERALD_DOOR.get());
                        output.accept(ModBlocks.EMERALD_TRAPDOOR.get());
                        output.accept(ModBlocks.EMERALD_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.EMERALD_GRATE.get());
                        output.accept(ModBlocks.EMERALD_BARS.get());
                        output.accept(ModBlocks.LAPIS_STAIRS.get());
                        output.accept(ModBlocks.LAPIS_SLAB.get());
                        output.accept(ModBlocks.LAPIS_BUTTON.get());
                        output.accept(ModBlocks.LAPIS_DOOR.get());
                        output.accept(ModBlocks.LAPIS_TRAPDOOR.get());
                        output.accept(ModBlocks.LAPIS_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.LAPIS_GRATE.get());
                        output.accept(ModBlocks.LAPIS_BARS.get());
                        output.accept(ModBlocks.TIN_STAIRS.get());
                        output.accept(ModBlocks.TIN_SLAB.get());
                        output.accept(ModBlocks.TIN_BUTTON.get());
                        output.accept(ModBlocks.TIN_DOOR.get());
                        output.accept(ModBlocks.TIN_TRAPDOOR.get());
                        output.accept(ModBlocks.TIN_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.TIN_GRATE.get());
                        output.accept(ModBlocks.TIN_BARS.get());
                        output.accept(ModBlocks.BRONZE_STAIRS.get());
                        output.accept(ModBlocks.BRONZE_SLAB.get());
                        output.accept(ModBlocks.BRONZE_BUTTON.get());
                        output.accept(ModBlocks.BRONZE_DOOR.get());
                        output.accept(ModBlocks.BRONZE_TRAPDOOR.get());
                        output.accept(ModBlocks.BRONZE_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.BRONZE_GRATE.get());
                        output.accept(ModBlocks.BRONZE_BARS.get());
                        output.accept(ModBlocks.DIAMOND_STAIRS.get());
                        output.accept(ModBlocks.DIAMOND_SLAB.get());
                        output.accept(ModBlocks.DIAMOND_BUTTON.get());
                        output.accept(ModBlocks.DIAMOND_DOOR.get());
                        output.accept(ModBlocks.DIAMOND_TRAPDOOR.get());
                        output.accept(ModBlocks.DIAMOND_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.DIAMOND_GRATE.get());
                        output.accept(ModBlocks.DIAMOND_BARS.get());
                        output.accept(ModBlocks.NETHERITE_STAIRS.get());
                        output.accept(ModBlocks.NETHERITE_SLAB.get());
                        output.accept(ModBlocks.NETHERITE_BUTTON.get());
                        output.accept(ModBlocks.NETHERITE_DOOR.get());
                        output.accept(ModBlocks.NETHERITE_TRAPDOOR.get());
                        output.accept(ModBlocks.NETHERITE_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.NETHERITE_GRATE.get());
                        output.accept(ModBlocks.NETHERITE_BARS.get());
                        output.accept(ModBlocks.BRONZE_BLOCK.get());
                        output.accept(ModBlocks.TIN_BLOCK.get());
                        output.accept(ModBlocks.RAW_TIN_BLOCK.get());
                        output.accept(ModBlocks.TIN_ORE.get());
                    }).build());

    public static final Supplier<CreativeModeTab> NATURE_UPDATE_FUNCTIONAL_BLOCKS = CREATIVE_MODE_TABS.register("nature_update_functional_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.CRAFTING_FURNACE.get()))
                    .title(Component.translatable("creativetab.natureupdate.nature_update_functional_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.CRAFTING_FURNACE.get());
                        output.accept(ModBlocks.WATER_BOILER.get());
                        output.accept(ModBlocks.WOODCUTTER.get());
                        output.accept(ModBlocks.BREEZING_STAND.get());
                        output.accept(ModBlocks.OAK_TABLE.get());
                        output.accept(ModBlocks.OAK_CHAIR.get());
                        output.accept(ModBlocks.BIRCH_TABLE.get());
                        output.accept(ModBlocks.BIRCH_CHAIR.get());
                        output.accept(ModBlocks.SPRUCE_TABLE.get());
                        output.accept(ModBlocks.SPRUCE_CHAIR.get());
                        output.accept(ModBlocks.ACACIA_TABLE.get());
                        output.accept(ModBlocks.ACACIA_CHAIR.get());
                        output.accept(ModBlocks.JUNGLE_TABLE.get());
                        output.accept(ModBlocks.JUNGLE_CHAIR.get());
                        output.accept(ModBlocks.DARK_OAK_TABLE.get());
                        output.accept(ModBlocks.DARK_OAK_CHAIR.get());
                        output.accept(ModBlocks.MANGROVE_TABLE.get());
                        output.accept(ModBlocks.MANGROVE_CHAIR.get());
                        output.accept(ModBlocks.CHERRY_TABLE.get());
                        output.accept(ModBlocks.CHERRY_CHAIR.get());
                        output.accept(ModBlocks.BAMBOO_TABLE.get());
                        output.accept(ModBlocks.BAMBOO_CHAIR.get());
                        output.accept(ModBlocks.PALE_OAK_TABLE.get());
                        output.accept(ModBlocks.PALE_OAK_CHAIR.get());
                        output.accept(ModBlocks.CRIMSON_TABLE.get());
                        output.accept(ModBlocks.CRIMSON_CHAIR.get());
                        output.accept(ModBlocks.WARPED_TABLE.get());
                        output.accept(ModBlocks.WARPED_CHAIR.get());
                        output.accept(ModBlocks.END_CAMPFIRE);
                        output.accept(ModBlocks.END_TORCH);
                        output.accept(ModBlocks.END_LANTERN);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
