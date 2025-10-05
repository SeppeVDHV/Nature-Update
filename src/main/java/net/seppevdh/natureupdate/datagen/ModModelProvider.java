package net.seppevdh.natureupdate.datagen;

import com.mojang.math.Quadrant;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.model.VariantMutator;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.block.ModBlocks;
import net.seppevdh.natureupdate.fluid.ModFluids;
import net.seppevdh.natureupdate.item.ModArmorMaterials;
import net.seppevdh.natureupdate.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, NatureUpdate.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ModItems.PEAR.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RICE_SEEDS.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModBlocks.RASPBERRY.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModBlocks.STRAWBERRY.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModBlocks.BLACKBERRY.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModBlocks.CORN.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModBlocks.RHUBARB.asItem(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ModItems.BRONZE_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BRONZE_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BRONZE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BRONZE_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BRONZE_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BRONZE_SAW.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateFlatItem(ModItems.EMERALD_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.EMERALD_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.EMERALD_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.EMERALD_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.EMERALD_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.EMERALD_SAW.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateFlatItem(ModItems.COPPER_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.COPPER_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.COPPER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.COPPER_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.COPPER_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.COPPER_SAW.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateFlatItem(ModItems.IRON_SAW.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.GOLDEN_SAW.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.DIAMOND_SAW.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.NETHERITE_SAW.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateFlatItem(ModItems.SICKLE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateFlatItem(ModItems.COPPER_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BRONZE_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.EMERALD_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.NETHERITE_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        
        itemModels.generateTrimmableItem(ModItems.BRONZE_HELMET.get(), ModArmorMaterials.BRONZE, ResourceLocation.withDefaultNamespace("trims/items/helmet_trim"), false);
        itemModels.generateTrimmableItem(ModItems.BRONZE_CHESTPLATE.get(), ModArmorMaterials.BRONZE, ResourceLocation.withDefaultNamespace("trims/items/chestplate_trim"), false);
        itemModels.generateTrimmableItem(ModItems.BRONZE_LEGGINGS.get(), ModArmorMaterials.BRONZE, ResourceLocation.withDefaultNamespace("trims/items/leggings_trim"), false);
        itemModels.generateTrimmableItem(ModItems.BRONZE_BOOTS.get(), ModArmorMaterials.BRONZE, ResourceLocation.withDefaultNamespace("trims/items/boots_trim"), false);

        itemModels.generateTrimmableItem(ModItems.EMERALD_HELMET.get(), ModArmorMaterials.EMERALD, ResourceLocation.withDefaultNamespace("trims/items/helmet_trim"), false);
        itemModels.generateTrimmableItem(ModItems.EMERALD_CHESTPLATE.get(), ModArmorMaterials.EMERALD, ResourceLocation.withDefaultNamespace("trims/items/chestplate_trim"), false);
        itemModels.generateTrimmableItem(ModItems.EMERALD_LEGGINGS.get(), ModArmorMaterials.EMERALD, ResourceLocation.withDefaultNamespace("trims/items/leggings_trim"), false);
        itemModels.generateTrimmableItem(ModItems.EMERALD_BOOTS.get(), ModArmorMaterials.EMERALD, ResourceLocation.withDefaultNamespace("trims/items/boots_trim"), false);

        itemModels.generateTrimmableItem(ModItems.COPPER_HELMET.get(), ModArmorMaterials.COPPER, ResourceLocation.withDefaultNamespace("trims/items/helmet_trim"), false);
        itemModels.generateTrimmableItem(ModItems.COPPER_CHESTPLATE.get(), ModArmorMaterials.COPPER, ResourceLocation.withDefaultNamespace("trims/items/chestplate_trim"), false);
        itemModels.generateTrimmableItem(ModItems.COPPER_LEGGINGS.get(), ModArmorMaterials.COPPER, ResourceLocation.withDefaultNamespace("trims/items/leggings_trim"), false);
        itemModels.generateTrimmableItem(ModItems.COPPER_BOOTS.get(), ModArmorMaterials.COPPER, ResourceLocation.withDefaultNamespace("trims/items/boots_trim"), false);

        itemModels.generateFlatItem(ModItems.INVINCIBLE_ITEM.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ModItems.RAW_TIN.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.TIN_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BRONZE_INGOT.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ModItems.RICE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ELECTRODROME_MUSIC_DISC.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ModItems.SOUL_COAL.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ENDER_COAL.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ModFluids.SOUL_FLUID_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModFluids.END_FLUID_BUCKET.get(), ModelTemplates.FLAT_ITEM);

        itemModels.itemModelOutput.accept(ModFluids.SOUL_FLUID_BLOCK.asItem(), ItemModelUtils.plainModel(ModelTemplates.FLAT_ITEM.create(
                ModelLocationUtils.getModelLocation(ModFluids.SOUL_FLUID_BLOCK.asItem()), TextureMapping.layer0(TextureMapping.getBlockTexture(ModFluids.SOUL_FLUID_BLOCK.get())), itemModels.modelOutput
        )));
        itemModels.itemModelOutput.accept(ModFluids.END_FLUID_BLOCK.asItem(), ItemModelUtils.plainModel(ModelTemplates.FLAT_ITEM.create(
                ModelLocationUtils.getModelLocation(ModFluids.END_FLUID_BLOCK.asItem()), TextureMapping.layer0(TextureMapping.getBlockTexture(ModFluids.END_FLUID_BLOCK.get())), itemModels.modelOutput
        )));

        parentItem(ModBlocks.OAK_CHAIR);
        parentItem(ModBlocks.BIRCH_CHAIR);
        parentItem(ModBlocks.SPRUCE_CHAIR);
        parentItem(ModBlocks.ACACIA_CHAIR);
        parentItem(ModBlocks.JUNGLE_CHAIR);
        parentItem(ModBlocks.DARK_OAK_CHAIR);
        parentItem(ModBlocks.MANGROVE_CHAIR);
        parentItem(ModBlocks.CHERRY_CHAIR);
        parentItem(ModBlocks.BAMBOO_CHAIR);
        parentItem(ModBlocks.PALE_OAK_CHAIR);
        parentItem(ModBlocks.CRIMSON_CHAIR);
        parentItem(ModBlocks.WARPED_CHAIR);

        parentItem(ModBlocks.OAK_TABLE);
        parentItem(ModBlocks.BIRCH_TABLE);
        parentItem(ModBlocks.SPRUCE_TABLE);
        parentItem(ModBlocks.ACACIA_TABLE);
        parentItem(ModBlocks.JUNGLE_TABLE);
        parentItem(ModBlocks.DARK_OAK_TABLE);
        parentItem(ModBlocks.MANGROVE_TABLE);
        parentItem(ModBlocks.CHERRY_TABLE);
        parentItem(ModBlocks.BAMBOO_TABLE);
        parentItem(ModBlocks.PALE_OAK_TABLE);
        parentItem(ModBlocks.CRIMSON_TABLE);
        parentItem(ModBlocks.WARPED_TABLE);

        parentItem(ModBlocks.CRAFTING_FURNACE);
        parentItem(ModBlocks.WOODCUTTER);

        itemModels.generateFlatItem(ModBlocks.BREEZING_STAND.get().asItem(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ModItems.NETHER_ESSENCE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.SOUL_ESSENCE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ENDER_ESSENCE.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ModItems.POLLUTED_SOUL_FLUID_BUCKET.get(), ModelTemplates.FLAT_ITEM);

        // blocks

        blockModels.createTrivialCube(ModBlocks.TIN_ORE.get());
        blockModels.createTrivialCube(ModBlocks.RAW_TIN_BLOCK.get());

        blockModels.createTrivialCube(ModBlocks.IRON_GRATE.get());
        blockModels.createTrivialCube(ModBlocks.GOLDEN_GRATE.get());
        blockModels.createTrivialCube(ModBlocks.DIAMOND_GRATE.get());
        blockModels.createTrivialCube(ModBlocks.NETHERITE_GRATE.get());
        blockModels.createTrivialCube(ModBlocks.EMERALD_GRATE.get());
        blockModels.createTrivialCube(ModBlocks.LAPIS_GRATE.get());
        blockModels.createTrivialCube(ModBlocks.TIN_GRATE.get());
        blockModels.createTrivialCube(ModBlocks.BRONZE_GRATE.get());

        createButton(blockModels, ModBlocks.IRON_BUTTON, Blocks.IRON_BLOCK);
        createButton(blockModels, ModBlocks.GOLDEN_BUTTON, Blocks.GOLD_BLOCK);
        createButton(blockModels, ModBlocks.DIAMOND_BUTTON, Blocks.DIAMOND_BLOCK);
        createButton(blockModels, ModBlocks.EMERALD_BUTTON, Blocks.EMERALD_BLOCK);
        createButton(blockModels, ModBlocks.NETHERITE_BUTTON, Blocks.NETHERITE_BLOCK);
        createButton(blockModels, ModBlocks.LAPIS_BUTTON, Blocks.LAPIS_BLOCK);

        createDoor(blockModels, ModBlocks.GOLDEN_DOOR);
        createDoor(blockModels, ModBlocks.DIAMOND_DOOR);
        createDoor(blockModels, ModBlocks.EMERALD_DOOR);
        createDoor(blockModels, ModBlocks.NETHERITE_DOOR);
        createDoor(blockModels, ModBlocks.LAPIS_DOOR);

        createTrapDoor(blockModels, ModBlocks.GOLDEN_TRAPDOOR);
        createTrapDoor(blockModels, ModBlocks.DIAMOND_TRAPDOOR);
        createTrapDoor(blockModels, ModBlocks.EMERALD_TRAPDOOR);
        createTrapDoor(blockModels, ModBlocks.NETHERITE_TRAPDOOR);
        createTrapDoor(blockModels, ModBlocks.LAPIS_TRAPDOOR);

        createStairs(blockModels, ModBlocks.IRON_STAIRS, Blocks.IRON_BLOCK);
        createStairs(blockModels, ModBlocks.GOLDEN_STAIRS, Blocks.GOLD_BLOCK);
        createStairs(blockModels, ModBlocks.DIAMOND_STAIRS, Blocks.DIAMOND_BLOCK);
        createStairs(blockModels, ModBlocks.EMERALD_STAIRS, Blocks.EMERALD_BLOCK);
        createStairs(blockModels, ModBlocks.NETHERITE_STAIRS, Blocks.NETHERITE_BLOCK);
        createStairs(blockModels, ModBlocks.LAPIS_STAIRS, Blocks.LAPIS_BLOCK);

        createSlab(blockModels, ModBlocks.IRON_SLAB, Blocks.IRON_BLOCK);
        createSlab(blockModels, ModBlocks.GOLDEN_SLAB, Blocks.GOLD_BLOCK);
        createSlab(blockModels, ModBlocks.DIAMOND_SLAB, Blocks.DIAMOND_BLOCK);
        createSlab(blockModels, ModBlocks.EMERALD_SLAB, Blocks.EMERALD_BLOCK);
        createSlab(blockModels, ModBlocks.NETHERITE_SLAB, Blocks.NETHERITE_BLOCK);
        createSlab(blockModels, ModBlocks.LAPIS_SLAB, Blocks.LAPIS_BLOCK);

        createPressurePlate(blockModels, ModBlocks.DIAMOND_PRESSURE_PLATE, Blocks.DIAMOND_BLOCK);
        createPressurePlate(blockModels, ModBlocks.EMERALD_PRESSURE_PLATE, Blocks.EMERALD_BLOCK);
        createPressurePlate(blockModels, ModBlocks.NETHERITE_PRESSURE_PLATE, Blocks.NETHERITE_BLOCK);
        createPressurePlate(blockModels, ModBlocks.LAPIS_PRESSURE_PLATE, Blocks.LAPIS_BLOCK);

        blockModels.family(ModBlocks.TIN_BLOCK.get())
                .door(ModBlocks.TIN_DOOR.get())
                .slab(ModBlocks.TIN_SLAB.get())
                .stairs(ModBlocks.TIN_STAIRS.get())
                .pressurePlate(ModBlocks.TIN_PRESSURE_PLATE.get())
                .button(ModBlocks.TIN_BUTTON.get())
                .trapdoor(ModBlocks.TIN_TRAPDOOR.get());

        blockModels.family(ModBlocks.BRONZE_BLOCK.get())
                .door(ModBlocks.BRONZE_DOOR.get())
                .slab(ModBlocks.BRONZE_SLAB.get())
                .stairs(ModBlocks.BRONZE_STAIRS.get())
                .pressurePlate(ModBlocks.BRONZE_PRESSURE_PLATE.get())
                .button(ModBlocks.BRONZE_BUTTON.get())
                .trapdoor(ModBlocks.BRONZE_TRAPDOOR.get());

        blockModels.family(ModBlocks.OAK_MOSAIC.get())
                .stairs(ModBlocks.OAK_MOSAIC_STAIRS.get())
                .slab(ModBlocks.OAK_MOSAIC_SLAB.get());
        blockModels.family(ModBlocks.BIRCH_MOSAIC.get())
                .stairs(ModBlocks.BIRCH_MOSAIC_STAIRS.get())
                .slab(ModBlocks.BIRCH_MOSAIC_SLAB.get());
        blockModels.family(ModBlocks.SPRUCE_MOSAIC.get())
                .stairs(ModBlocks.SPRUCE_MOSAIC_STAIRS.get())
                .slab(ModBlocks.SPRUCE_MOSAIC_SLAB.get());
        blockModels.family(ModBlocks.ACACIA_MOSAIC.get())
                .stairs(ModBlocks.ACACIA_MOSAIC_STAIRS.get())
                .slab(ModBlocks.ACACIA_MOSAIC_SLAB.get());
        blockModels.family(ModBlocks.JUNGLE_MOSAIC.get())
                .stairs(ModBlocks.JUNGLE_MOSAIC_STAIRS.get())
                .slab(ModBlocks.JUNGLE_MOSAIC_SLAB.get());
        blockModels.family(ModBlocks.DARK_OAK_MOSAIC.get())
                .stairs(ModBlocks.DARK_OAK_MOSAIC_STAIRS.get())
                .slab(ModBlocks.DARK_OAK_MOSAIC_SLAB.get());
        blockModels.family(ModBlocks.MANGROVE_MOSAIC.get())
                .stairs(ModBlocks.MANGROVE_MOSAIC_STAIRS.get())
                .slab(ModBlocks.MANGROVE_MOSAIC_SLAB.get());
        blockModels.family(ModBlocks.CHERRY_MOSAIC.get())
                .stairs(ModBlocks.CHERRY_MOSAIC_STAIRS.get())
                .slab(ModBlocks.CHERRY_MOSAIC_SLAB.get());
        blockModels.family(ModBlocks.PALE_OAK_MOSAIC.get())
                .stairs(ModBlocks.PALE_OAK_MOSAIC_STAIRS.get())
                .slab(ModBlocks.PALE_OAK_MOSAIC_SLAB.get());
        blockModels.family(ModBlocks.CRIMSON_MOSAIC.get())
                .stairs(ModBlocks.CRIMSON_MOSAIC_STAIRS.get())
                .slab(ModBlocks.CRIMSON_MOSAIC_SLAB.get());
        blockModels.family(ModBlocks.WARPED_MOSAIC.get())
                .stairs(ModBlocks.WARPED_MOSAIC_STAIRS.get())
                .slab(ModBlocks.WARPED_MOSAIC_SLAB.get());

        createMetalBars(blockModels, ModBlocks.GOLD_BARS);
        createMetalBars(blockModels, ModBlocks.EMERALD_BARS);
        createMetalBars(blockModels, ModBlocks.LAPIS_BARS);
        createMetalBars(blockModels, ModBlocks.DIAMOND_BARS);
        createMetalBars(blockModels, ModBlocks.NETHERITE_BARS);
        createMetalBars(blockModels, ModBlocks.TIN_BARS);
        createMetalBars(blockModels, ModBlocks.BRONZE_BARS);

        createCustomFire(blockModels, ModBlocks.END_FIRE);
        blockModels.createCampfires(ModBlocks.END_CAMPFIRE.get());

        blockModels.createNormalTorch(ModBlocks.END_TORCH.get(), ModBlocks.END_WALL_TORCH.get());
        blockModels.createLantern(ModBlocks.END_LANTERN.get());
    }

    @Override
    protected @NotNull Stream<? extends Holder<Block>> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().filter(this::isValidBlock);
    }

    private static final List<DeferredBlock<? extends Block>> blockList = List.of(
            ModBlocks.SMOOTH_STONE_STAIRS,
            ModBlocks.RICE_CROP,
            ModBlocks.OAK_CROP,
            ModBlocks.BIRCH_CROP,
            ModBlocks.SPRUCE_CROP,
            ModBlocks.ACACIA_CROP,
            ModBlocks.JUNGLE_CROP,
            ModBlocks.DARK_OAK_CROP,
            ModBlocks.CHERRY_CROP,
            ModBlocks.PALE_OAK_CROP,
            ModBlocks.OAK_TABLE,
            ModBlocks.BIRCH_TABLE,
            ModBlocks.SPRUCE_TABLE,
            ModBlocks.ACACIA_TABLE,
            ModBlocks.JUNGLE_TABLE,
            ModBlocks.DARK_OAK_TABLE,
            ModBlocks.MANGROVE_TABLE,
            ModBlocks.CHERRY_TABLE,
            ModBlocks.BAMBOO_TABLE,
            ModBlocks.CRIMSON_TABLE,
            ModBlocks.WARPED_TABLE,
            ModBlocks.PALE_OAK_TABLE,
            ModBlocks.OAK_CHAIR,
            ModBlocks.BIRCH_CHAIR,
            ModBlocks.SPRUCE_CHAIR,
            ModBlocks.ACACIA_CHAIR,
            ModBlocks.JUNGLE_CHAIR,
            ModBlocks.DARK_OAK_CHAIR,
            ModBlocks.MANGROVE_CHAIR,
            ModBlocks.CHERRY_CHAIR,
            ModBlocks.BAMBOO_CHAIR,
            ModBlocks.CRIMSON_CHAIR,
            ModBlocks.WARPED_CHAIR,
            ModBlocks.PALE_OAK_CHAIR,
            ModBlocks.CORN,
            ModBlocks.STRAWBERRY,
            ModBlocks.RASPBERRY,
            ModBlocks.BLACKBERRY,
            ModBlocks.OAK_SHELF,
            ModBlocks.CRAFTING_FURNACE,
            ModBlocks.WATER_BOILER,
            ModBlocks.MOD_OAK_SAPLING,
            ModBlocks.WOODCUTTER,
            ModFluids.SOUL_FLUID_BLOCK,
            ModFluids.END_FLUID_BLOCK,
            ModBlocks.BREEZING_STAND,
            ModBlocks.RHUBARB
    );

    private boolean isValidBlock (DeferredHolder<Block, ? extends Block> block) {
        for (DeferredBlock<? extends Block> block1 : blockList) {
            if (block.is(block1.getId())) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected @NotNull Stream<? extends Holder<Item>> getKnownItems() {
        return ModItems.ITEMS.getEntries().stream().filter(x -> !x.get().toString().contains("mold") && !x.get().toString().contains("head") && !x.get().toString().contains("handle"));
    }

    private void parentItem (DeferredBlock<Block> block) {
        ModelTemplate template = ModelTemplates.create(block.getRegisteredName());
        template.createBaseTemplate(ResourceLocation.fromNamespaceAndPath(NatureUpdate.MOD_ID, block.getId().getPath()), Map.of());
    }

    private void createButton (BlockModelGenerators blockModels, DeferredBlock<Block> button, Block fullBlock) {
        TexturedModel texturedmodel = BlockModelGenerators.TEXTURED_MODELS.getOrDefault(fullBlock, TexturedModel.CUBE.get(fullBlock));
        TextureMapping mapping = texturedmodel.getMapping();

        MultiVariant variant0 = BlockModelGenerators.plainVariant(ModelTemplates.BUTTON.create(button.get(), mapping, blockModels.modelOutput));
        MultiVariant variant1 = BlockModelGenerators.plainVariant(ModelTemplates.BUTTON_PRESSED.create(button.get(), mapping, blockModels.modelOutput));
        ResourceLocation variant2 = ModelTemplates.BUTTON_INVENTORY.create(button.get(), mapping, blockModels.modelOutput);

        blockModels.blockStateOutput.accept(BlockModelGenerators.createButton(button.get(), variant0, variant1));
        blockModels.registerSimpleItemModel(button.get(), variant2);
    }

    private void createSlab (BlockModelGenerators blockModels, DeferredBlock<Block> slab, Block fullBlock) {
        TexturedModel texturedmodel = BlockModelGenerators.TEXTURED_MODELS.getOrDefault(fullBlock, TexturedModel.CUBE.get(fullBlock));
        TextureMapping mapping = texturedmodel.getMapping();

        ResourceLocation variant3 = ModelTemplates.SLAB_BOTTOM.create(slab.get(), mapping, blockModels.modelOutput);
        MultiVariant variant0 = BlockModelGenerators.plainVariant(variant3);
        MultiVariant variant1 = BlockModelGenerators.plainVariant(ModelTemplates.SLAB_TOP.create(slab.get(), mapping, blockModels.modelOutput));
        MultiVariant variant2 = BlockModelGenerators.plainVariant(ModelTemplates.CUBE_ALL.create(fullBlock, mapping, blockModels.modelOutput));

        blockModels.blockStateOutput.accept(BlockModelGenerators.createSlab(slab.get(), variant0, variant1, variant2));
        blockModels.registerSimpleItemModel(slab.get(), variant3);
    }

    private void createStairs (BlockModelGenerators blockModels, DeferredBlock<Block> stairs, Block fullBlock) {
        TexturedModel texturedmodel = BlockModelGenerators.TEXTURED_MODELS.getOrDefault(fullBlock, TexturedModel.CUBE.get(fullBlock));
        TextureMapping mapping = texturedmodel.getMapping();

        ResourceLocation variant3 = ModelTemplates.STAIRS_STRAIGHT.create(stairs.get(), mapping, blockModels.modelOutput);
        MultiVariant variant0 = BlockModelGenerators.plainVariant(variant3);
        MultiVariant variant1 = BlockModelGenerators.plainVariant(ModelTemplates.STAIRS_INNER.create(stairs.get(), mapping, blockModels.modelOutput));
        MultiVariant variant2 = BlockModelGenerators.plainVariant(ModelTemplates.STAIRS_OUTER.create(stairs.get(), mapping, blockModels.modelOutput));

        blockModels.blockStateOutput.accept(BlockModelGenerators.createStairs(stairs.get(), variant1, variant0, variant2));
        blockModels.registerSimpleItemModel(stairs.get(), variant3);
    }

    private void createPressurePlate (BlockModelGenerators blockModels, DeferredBlock<Block> button, Block fullBlock) {
        TexturedModel texturedmodel = BlockModelGenerators.TEXTURED_MODELS.getOrDefault(fullBlock, TexturedModel.CUBE.get(fullBlock));
        TextureMapping mapping = texturedmodel.getMapping();

        ResourceLocation variant2 = ModelTemplates.PRESSURE_PLATE_UP.create(button.get(), mapping, blockModels.modelOutput);
        MultiVariant variant0 = BlockModelGenerators.plainVariant(variant2);
        MultiVariant variant1 = BlockModelGenerators.plainVariant(ModelTemplates.PRESSURE_PLATE_DOWN.create(button.get(), mapping, blockModels.modelOutput));

        blockModels.blockStateOutput.accept(BlockModelGenerators.createPressurePlate(button.get(), variant0, variant1));
        blockModels.registerSimpleItemModel(button.get(), variant2);
    }

    private static ModelTemplate createCustomTemplate (ResourceLocation parent, TextureSlot... slots) {
        return new ModelTemplate(Optional.of(parent), Optional.empty(), slots);
    }

    private static ResourceLocation createModelResourceLocation (DeferredBlock<Block> block, String suffix) {
        return ModelLocationUtils.getModelLocation(block.get()).withSuffix(suffix);
    }

    private static Map<TextureSlot, ResourceLocation> createBarsMapping (DeferredBlock<Block> block) {
        ResourceLocation location = block.getId().withPrefix("block/");
        return Map.of(TextureSlot.EDGE, location, TextureSlot.create("bars"), location, TextureSlot.PARTICLE, location);
    }

    public static ResourceLocation create(ModelTemplate template, ResourceLocation modelLocation, Map<TextureSlot, ResourceLocation> map, BiConsumer<ResourceLocation, ModelInstance> output) {
        output.accept(modelLocation, () -> template.createBaseTemplate(modelLocation, map));
        return modelLocation;
    }

    public void createCustomFire (BlockModelGenerators blockModels, DeferredBlock<Block> fireBlock) {
        VariantMutator Y_ROT_90 = VariantMutator.Y_ROT.withValue(Quadrant.R90);
        VariantMutator Y_ROT_180 = VariantMutator.Y_ROT.withValue(Quadrant.R180);
        VariantMutator Y_ROT_270 = VariantMutator.Y_ROT.withValue(Quadrant.R270);
        MultiVariant multivariant = blockModels.createFloorFireModels(fireBlock.get());
        MultiVariant multivariant1 = blockModels.createSideFireModels(fireBlock.get());
        blockModels.blockStateOutput
                .accept(
                        MultiPartGenerator.multiPart(fireBlock.get())
                                .with(multivariant)
                                .with(multivariant1)
                                .with(multivariant1.with(Y_ROT_90))
                                .with(multivariant1.with(Y_ROT_180))
                                .with(multivariant1.with(Y_ROT_270))
                );
    }

    private void createMetalBars (BlockModelGenerators blockModels, DeferredBlock<Block> metalBars) {
        final VariantMutator Y_ROT_90 = VariantMutator.Y_ROT.withValue(Quadrant.R90);
        MultiVariant multivariant = BlockModelGenerators.plainVariant(create(createCustomTemplate(ResourceLocation.withDefaultNamespace("block/iron_bars_post_ends"),
                TextureSlot.PARTICLE, TextureSlot.EDGE), createModelResourceLocation(metalBars, "_post_ends"), createBarsMapping(metalBars), blockModels.modelOutput));
        MultiVariant multivariant1 = BlockModelGenerators.plainVariant(create(createCustomTemplate(ResourceLocation.withDefaultNamespace("block/iron_bars_post"),
                TextureSlot.PARTICLE, TextureSlot.create("bars")), createModelResourceLocation(metalBars, "_post"), createBarsMapping(metalBars), blockModels.modelOutput));
        MultiVariant multivariant2 = BlockModelGenerators.plainVariant(create(createCustomTemplate(ResourceLocation.withDefaultNamespace("block/iron_bars_cap"),
                TextureSlot.PARTICLE, TextureSlot.create("bars")), createModelResourceLocation(metalBars, "_cap"), createBarsMapping(metalBars), blockModels.modelOutput));
        MultiVariant multivariant3 = BlockModelGenerators.plainVariant(create(createCustomTemplate(ResourceLocation.withDefaultNamespace("block/iron_bars_cap_alt"),
                TextureSlot.PARTICLE, TextureSlot.create("bars")), createModelResourceLocation(metalBars, "_cap_alt"), createBarsMapping(metalBars), blockModels.modelOutput));
        MultiVariant multivariant4 = BlockModelGenerators.plainVariant(create(createCustomTemplate(ResourceLocation.withDefaultNamespace("block/iron_bars_side"),
                TextureSlot.PARTICLE, TextureSlot.EDGE, TextureSlot.create("bars")), createModelResourceLocation(metalBars, "_side"), createBarsMapping(metalBars), blockModels.modelOutput));
        MultiVariant multivariant5 = BlockModelGenerators.plainVariant(create(createCustomTemplate(ResourceLocation.withDefaultNamespace("block/iron_bars_side_alt"),
                TextureSlot.PARTICLE, TextureSlot.EDGE, TextureSlot.create("bars")), createModelResourceLocation(metalBars, "_side_alt"), createBarsMapping(metalBars), blockModels.modelOutput));
        blockModels.blockStateOutput
                .accept(
                        MultiPartGenerator.multiPart(metalBars.get())
                                .with(multivariant)
                                .with(
                                        BlockModelGenerators.condition()
                                                .term(BlockStateProperties.NORTH, false)
                                                .term(BlockStateProperties.EAST, false)
                                                .term(BlockStateProperties.SOUTH, false)
                                                .term(BlockStateProperties.WEST, false),
                                        multivariant1
                                )
                                .with(
                                        BlockModelGenerators.condition()
                                                .term(BlockStateProperties.NORTH, true)
                                                .term(BlockStateProperties.EAST, false)
                                                .term(BlockStateProperties.SOUTH, false)
                                                .term(BlockStateProperties.WEST, false),
                                        multivariant2
                                )
                                .with(
                                        BlockModelGenerators.condition()
                                                .term(BlockStateProperties.NORTH, false)
                                                .term(BlockStateProperties.EAST, true)
                                                .term(BlockStateProperties.SOUTH, false)
                                                .term(BlockStateProperties.WEST, false),
                                        multivariant2.with(Y_ROT_90)
                                )
                                .with(
                                        BlockModelGenerators.condition()
                                                .term(BlockStateProperties.NORTH, false)
                                                .term(BlockStateProperties.EAST, false)
                                                .term(BlockStateProperties.SOUTH, true)
                                                .term(BlockStateProperties.WEST, false),
                                        multivariant3
                                )
                                .with(
                                        BlockModelGenerators.condition()
                                                .term(BlockStateProperties.NORTH, false)
                                                .term(BlockStateProperties.EAST, false)
                                                .term(BlockStateProperties.SOUTH, false)
                                                .term(BlockStateProperties.WEST, true),
                                        multivariant3.with(Y_ROT_90)
                                )
                                .with(BlockModelGenerators.condition().term(BlockStateProperties.NORTH, true), multivariant4)
                                .with(BlockModelGenerators.condition().term(BlockStateProperties.EAST, true), multivariant4.with(Y_ROT_90))
                                .with(BlockModelGenerators.condition().term(BlockStateProperties.SOUTH, true), multivariant5)
                                .with(BlockModelGenerators.condition().term(BlockStateProperties.WEST, true), multivariant5.with(Y_ROT_90))
                );
        blockModels.registerSimpleFlatItemModel(metalBars.get());
    }

    private void createDoor (BlockModelGenerators blockModels, DeferredBlock<Block> door) {
        blockModels.createDoor(door.get());
    }

    private void createTrapDoor (BlockModelGenerators blockModels, DeferredBlock<Block> trapdoor) {
        blockModels.createTrapdoor(trapdoor.get());
    }
}
