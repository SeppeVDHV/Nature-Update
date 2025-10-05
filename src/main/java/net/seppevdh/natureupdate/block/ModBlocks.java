package net.seppevdh.natureupdate.block;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.block.custom.*;
import net.seppevdh.natureupdate.item.ModFoodProperties;
import net.seppevdh.natureupdate.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.seppevdh.natureupdate.particle.ModParticles;
import net.seppevdh.natureupdate.worldgen.tree.ModTreeGrowers;

import java.util.function.Function;
import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(NatureUpdate.MOD_ID);

    public static final DeferredBlock<Block> TIN_BLOCK = registerBlock("tin_block",
            properties -> new Block(properties.strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> BRONZE_BLOCK = registerBlock("bronze_block",
            properties -> new Block(properties.strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> TIN_ORE = registerBlock("tin_ore",
            properties -> new Block(properties.strength(5f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> RAW_TIN_BLOCK = registerBlock("raw_tin_block",
            properties -> new Block(properties.strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    // food crops

    public static final DeferredBlock<Block> RICE_CROP = registerBlock("rice_crop",
            properties -> new RiceCropBlock(properties.mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.CROP)
                    .noOcclusion().noCollission().pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> CORN = registerBlockOnly("corn",
            properties -> new CornCropBlock(properties.mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.CROP)
                    .noOcclusion().noCollission().pushReaction(PushReaction.DESTROY).offsetType(BlockBehaviour.OffsetType.XZ).dynamicShape()));
    public static final DeferredBlock<Block> RHUBARB = registerBlockOnly("rhubarb",
            properties -> new RhubarbCropBlock(properties.mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.CROP)
                    .noOcclusion().noCollission().pushReaction(PushReaction.DESTROY).dynamicShape()));

    // bushes

    public static final DeferredBlock<Block> BLACKBERRY = registerBlockOnly("blackberry",
            properties -> new FoodBushBlock(properties.mapColor(MapColor.PLANT).randomTicks().noCollission()
                    .sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY), "blackberry", true));
    public static final DeferredBlock<Block> STRAWBERRY = registerBlockOnly("strawberry",
            properties -> new FoodBushBlock(properties.mapColor(MapColor.PLANT).randomTicks().noCollission()
                    .sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY), "strawberry"));
    public static final DeferredBlock<Block> RASPBERRY = registerBlockOnly("raspberry",
            properties -> new FoodBushBlock(properties.mapColor(MapColor.PLANT).randomTicks().noCollission()
                    .sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY), "raspberry"));

    // tree crops

    public static final DeferredBlock<Block> OAK_CROP = registerBlock("oak_crop",
            properties -> new TreeCropBlock(properties.mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.CROP)
                    .noOcclusion().pushReaction(PushReaction.DESTROY), "oak"));
    public static final DeferredBlock<Block> BIRCH_CROP = registerBlock("birch_crop",
            properties -> new TreeCropBlock(properties.mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.CROP)
                    .noOcclusion().pushReaction(PushReaction.DESTROY), "birch"));
    public static final DeferredBlock<Block> ACACIA_CROP = registerBlock("acacia_crop",
            properties -> new TreeCropBlock(properties.mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.CROP)
                    .noOcclusion().pushReaction(PushReaction.DESTROY), "acacia"));
    public static final DeferredBlock<Block> SPRUCE_CROP = registerBlock("spruce_crop",
            properties -> new TreeCropBlock(properties.mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.CROP)
                    .noOcclusion().pushReaction(PushReaction.DESTROY), "spruce"));
    public static final DeferredBlock<Block> JUNGLE_CROP = registerBlock("jungle_crop",
            properties -> new TreeCropBlock(properties.mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.CROP)
                    .noOcclusion().pushReaction(PushReaction.DESTROY), "jungle"));
    public static final DeferredBlock<Block> DARK_OAK_CROP = registerBlock("dark_oak_crop",
            properties -> new TreeCropBlock(properties.mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.CROP)
                    .noOcclusion().pushReaction(PushReaction.DESTROY), "dark_oak"));
    public static final DeferredBlock<Block> CHERRY_CROP = registerBlock("cherry_crop",
            properties -> new TreeCropBlock(properties.mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.CROP)
                    .noOcclusion().pushReaction(PushReaction.DESTROY), "cherry"));
    public static final DeferredBlock<Block> PALE_OAK_CROP = registerBlock("pale_oak_crop",
            properties -> new TreeCropBlock(properties.mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.CROP)
                    .noOcclusion().pushReaction(PushReaction.DESTROY), "pale_oak"));

    // tree saplings

    @Deprecated
    public static final DeferredBlock<Block> MOD_OAK_SAPLING = registerBlock("oak_sapling",
            properties -> new SaplingBlock(ModTreeGrowers.MOD_OAK, properties.mapColor(MapColor.PLANT).noCollission().randomTicks()
                    .instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));

    // building blocks

    public static final DeferredBlock<Block> IRON_STAIRS = registerBlock("iron_stairs",
            properties -> new StairBlock(Blocks.IRON_BLOCK.defaultBlockState(), properties.sound(SoundType.METAL).strength(5)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> IRON_SLAB = registerBlock("iron_slab",
            properties -> new SlabBlock(properties.sound(SoundType.METAL).strength(5).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> IRON_BUTTON = registerBlock("iron_button",
            properties -> new ButtonBlock(BlockSetType.IRON,10, properties.strength(3f).requiresCorrectToolForDrops()
                    .noCollission().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> IRON_GRATE = registerBlock("iron_grate",
            properties -> new Block(properties.strength(3.0F, 6.0F).sound(SoundType.METAL)
                    .mapColor(MapColor.METAL).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> GOLDEN_STAIRS = registerBlock("golden_stairs",
            properties -> new StairBlock(Blocks.GOLD_BLOCK.defaultBlockState(), properties.sound(SoundType.METAL).strength(5)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> GOLDEN_SLAB = registerBlock("golden_slab",
            properties -> new SlabBlock(properties.sound(SoundType.METAL).strength(5).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> GOLDEN_DOOR = registerBlock("golden_door",
            properties -> new DoorBlock(BlockSetType.GOLD, properties.strength(5f).requiresCorrectToolForDrops().noOcclusion()
                    .sound(SoundType.METAL)));
    public static final DeferredBlock<Block> GOLDEN_TRAPDOOR = registerBlock("golden_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.GOLD, properties.strength(5f).requiresCorrectToolForDrops().noOcclusion()
                    .sound(SoundType.METAL)));
    public static final DeferredBlock<Block> GOLDEN_BUTTON = registerBlock("golden_button",
            properties -> new ButtonBlock(BlockSetType.GOLD,10, properties.strength(3f).requiresCorrectToolForDrops()
                    .noCollission().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> GOLDEN_GRATE = registerBlock("golden_grate",
            properties -> new Block(properties.strength(3.0F, 6.0F).sound(SoundType.METAL)
                    .mapColor(MapColor.GOLD).noOcclusion().requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> GOLD_BARS = registerBlock("gold_bars",
            properties -> new IronBarsBlock(properties.strength(3f, 6f).sound(SoundType.METAL)
                    .mapColor(MapColor.GOLD).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> EMERALD_STAIRS = registerBlock("emerald_stairs",
            properties -> new StairBlock(Blocks.EMERALD_BLOCK.defaultBlockState(), properties.sound(SoundType.METAL)
                    .strength(5).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> EMERALD_SLAB = registerBlock("emerald_slab",
            properties -> new SlabBlock(properties.sound(SoundType.METAL).strength(5).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> EMERALD_DOOR = registerBlock("emerald_door",
            properties -> new DoorBlock(BlockSetType.COPPER, properties.strength(5f).requiresCorrectToolForDrops()
                    .noOcclusion().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> EMERALD_TRAPDOOR = registerBlock("emerald_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.COPPER, properties.strength(5f).requiresCorrectToolForDrops()
                    .noOcclusion().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> EMERALD_BUTTON = registerBlock("emerald_button",
            properties -> new ButtonBlock(BlockSetType.STONE,10, properties.strength(3f).requiresCorrectToolForDrops()
                    .noCollission().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> EMERALD_PRESSURE_PLATE = registerBlock("emerald_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.STONE, properties.strength(3f).requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));
    public static final DeferredBlock<Block> EMERALD_GRATE = registerBlock("emerald_grate",
            properties -> new Block(properties.strength(3.0F, 6.0F).sound(SoundType.METAL)
                    .mapColor(MapColor.EMERALD).noOcclusion().requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> EMERALD_BARS = registerBlock("emerald_bars",
            properties -> new IronBarsBlock(properties.strength(3f, 6f).sound(SoundType.METAL)
                    .mapColor(MapColor.EMERALD).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> LAPIS_STAIRS = registerBlock("lapis_stairs",
            properties -> new StairBlock(Blocks.LAPIS_BLOCK.defaultBlockState(), properties.sound(SoundType.STONE)
                    .strength(5).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> LAPIS_SLAB = registerBlock("lapis_slab",
            properties -> new SlabBlock(properties.sound(SoundType.STONE).strength(5).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> LAPIS_DOOR = registerBlock("lapis_door",
            properties -> new DoorBlock(BlockSetType.COPPER, properties.strength(5f).requiresCorrectToolForDrops()
                    .noOcclusion().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> LAPIS_TRAPDOOR = registerBlock("lapis_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.COPPER, properties.strength(5f).requiresCorrectToolForDrops()
                    .noOcclusion().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> LAPIS_BUTTON = registerBlock("lapis_button",
            properties -> new ButtonBlock(BlockSetType.STONE,10, properties.strength(3f).requiresCorrectToolForDrops()
                    .noCollission().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> LAPIS_PRESSURE_PLATE = registerBlock("lapis_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.STONE, properties.strength(3f).requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));
    public static final DeferredBlock<Block> LAPIS_GRATE = registerBlock("lapis_grate",
            properties -> new Block(properties.strength(3.0F, 6.0F).mapColor(MapColor.LAPIS)
                    .noOcclusion().requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> LAPIS_BARS = registerBlock("lapis_bars",
            properties -> new IronBarsBlock(properties.strength(3f, 6f).sound(SoundType.STONE)
                    .mapColor(MapColor.LAPIS).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> TIN_STAIRS = registerBlock("tin_stairs",
            properties -> new StairBlock(TIN_BLOCK.get().defaultBlockState(), properties.sound(SoundType.METAL)
                    .strength(5).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> TIN_SLAB = registerBlock("tin_slab",
            properties -> new SlabBlock(properties.sound(SoundType.METAL).strength(5).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> TIN_DOOR = registerBlock("tin_door",
            properties -> new DoorBlock(BlockSetType.COPPER, properties.strength(5f).requiresCorrectToolForDrops()
                    .noOcclusion().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> TIN_TRAPDOOR = registerBlock("tin_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.COPPER, properties.strength(5f).requiresCorrectToolForDrops()
                    .noOcclusion().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> TIN_BUTTON = registerBlock("tin_button",
            properties -> new ButtonBlock(BlockSetType.STONE,10, properties.strength(3f).requiresCorrectToolForDrops()
                    .noCollission().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> TIN_PRESSURE_PLATE = registerBlock("tin_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.STONE, properties.strength(3f).requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));
    public static final DeferredBlock<Block> TIN_GRATE = registerBlock("tin_grate",
            properties -> new Block(properties.strength(3.0F, 6.0F).sound(SoundType.METAL)
                    .mapColor(MapColor.COLOR_GRAY).noOcclusion().requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> TIN_BARS = registerBlock("tin_bars",
            properties -> new IronBarsBlock(properties.strength(3f, 6f).sound(SoundType.METAL)
                    .mapColor(MapColor.COLOR_GRAY).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> BRONZE_STAIRS = registerBlock("bronze_stairs",
            properties -> new StairBlock(BRONZE_BLOCK.get().defaultBlockState(), properties.sound(SoundType.METAL).strength(5)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> BRONZE_SLAB = registerBlock("bronze_slab",
            properties -> new SlabBlock(properties.sound(SoundType.METAL).strength(5).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> BRONZE_DOOR = registerBlock("bronze_door",
            properties -> new DoorBlock(BlockSetType.COPPER, properties.strength(5f).requiresCorrectToolForDrops().noOcclusion()
                    .sound(SoundType.METAL)));
    public static final DeferredBlock<Block> BRONZE_TRAPDOOR = registerBlock("bronze_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.COPPER, properties.strength(5f).requiresCorrectToolForDrops()
                    .noOcclusion().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> BRONZE_BUTTON = registerBlock("bronze_button",
            properties -> new ButtonBlock(BlockSetType.STONE,10, properties.strength(3f).requiresCorrectToolForDrops()
                    .noCollission().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> BRONZE_PRESSURE_PLATE = registerBlock("bronze_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.STONE, properties.strength(3f).requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));
    public static final DeferredBlock<Block> BRONZE_GRATE = registerBlock("bronze_grate",
            properties -> new Block(properties.strength(3.0F, 6.0F).sound(SoundType.METAL)
                    .mapColor(MapColor.SAND).noOcclusion().requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> BRONZE_BARS = registerBlock("bronze_bars",
            properties -> new IronBarsBlock(properties.strength(3f, 6f).sound(SoundType.METAL)
                    .mapColor(MapColor.SAND).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> DIAMOND_STAIRS = registerBlock("diamond_stairs",
            properties -> new StairBlock(Blocks.DIAMOND_BLOCK.defaultBlockState(), properties.sound(SoundType.METAL)
                    .strength(5).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DIAMOND_SLAB = registerBlock("diamond_slab",
            properties -> new SlabBlock(properties.sound(SoundType.METAL).strength(5).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DIAMOND_DOOR = registerBlock("diamond_door",
            properties -> new DoorBlock(BlockSetType.IRON, properties.strength(5f).requiresCorrectToolForDrops()
                    .noOcclusion().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> DIAMOND_TRAPDOOR = registerBlock("diamond_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.IRON, properties.strength(5f).requiresCorrectToolForDrops()
                    .noOcclusion().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> DIAMOND_BUTTON = registerBlock("diamond_button",
            properties -> new ButtonBlock(BlockSetType.STONE,10, properties.strength(3f).requiresCorrectToolForDrops()
                    .noCollission().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> DIAMOND_PRESSURE_PLATE = registerBlock("diamond_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.STONE, properties.strength(3f).requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));
    public static final DeferredBlock<Block> DIAMOND_GRATE = registerBlock("diamond_grate",
            properties -> new Block(properties.strength(3.0F, 6.0F).sound(SoundType.METAL)
                    .mapColor(MapColor.DIAMOND).noOcclusion().requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DIAMOND_BARS = registerBlock("diamond_bars",
            properties -> new IronBarsBlock(properties.strength(3f, 6f).sound(SoundType.METAL)
                    .mapColor(MapColor.DIAMOND).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> NETHERITE_STAIRS = registerBlock("netherite_stairs",
            properties -> new StairBlock(Blocks.NETHERITE_BLOCK.defaultBlockState(), properties.sound(SoundType.NETHERITE_BLOCK)
                    .strength(5).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> NETHERITE_SLAB = registerBlock("netherite_slab",
            properties -> new SlabBlock(properties.sound(SoundType.NETHERITE_BLOCK).strength(5).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> NETHERITE_DOOR = registerBlock("netherite_door",
            properties -> new DoorBlock(BlockSetType.IRON, properties.strength(5f).requiresCorrectToolForDrops()
                    .noOcclusion().sound(SoundType.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> NETHERITE_TRAPDOOR = registerBlock("netherite_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.IRON, properties.strength(5f).requiresCorrectToolForDrops()
                    .noOcclusion().sound(SoundType.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> NETHERITE_BUTTON = registerBlock("netherite_button",
            properties -> new ButtonBlock(BlockSetType.STONE,10, properties.strength(3f).
                    requiresCorrectToolForDrops().noCollission().sound(SoundType.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> NETHERITE_PRESSURE_PLATE = registerBlock("netherite_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.STONE, properties.strength(3f).requiresCorrectToolForDrops()
                    .sound(SoundType.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> NETHERITE_GRATE = registerBlock("netherite_grate",
            properties -> new Block(properties.strength(3.0F, 6.0F).sound(SoundType.NETHERITE_BLOCK)
                    .mapColor(MapColor.COLOR_BLACK).noOcclusion().requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> NETHERITE_BARS = registerBlock("netherite_bars",
            properties -> new IronBarsBlock(properties.strength(3f, 6f).sound(SoundType.METAL)
                    .mapColor(MapColor.COLOR_BLACK).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> OAK_MOSAIC = registerBlock("oak_mosaic",
            properties -> new Block(properties.mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final DeferredBlock<Block> BIRCH_MOSAIC = registerBlock("birch_mosaic",
            properties -> new Block(properties.mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final DeferredBlock<Block> SPRUCE_MOSAIC = registerBlock("spruce_mosaic",
            properties -> new Block(properties.mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final DeferredBlock<Block> JUNGLE_MOSAIC = registerBlock("jungle_mosaic",
            properties -> new Block(properties.mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final DeferredBlock<Block> ACACIA_MOSAIC = registerBlock("acacia_mosaic",
            properties -> new Block(properties.mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final DeferredBlock<Block> DARK_OAK_MOSAIC = registerBlock("dark_oak_mosaic",
            properties -> new Block(properties.mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final DeferredBlock<Block> MANGROVE_MOSAIC = registerBlock("mangrove_mosaic",
            properties -> new Block(properties.mapColor(MapColor.COLOR_RED).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final DeferredBlock<Block> CHERRY_MOSAIC = registerBlock("cherry_mosaic",
            properties -> new Block(properties.mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.CHERRY_WOOD).ignitedByLava()));
    public static final DeferredBlock<Block> CRIMSON_MOSAIC = registerBlock("crimson_mosaic",
            properties -> new Block(properties.mapColor(MapColor.CRIMSON_STEM).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)));
    public static final DeferredBlock<Block> WARPED_MOSAIC = registerBlock("warped_mosaic",
            properties -> new Block(properties.mapColor(MapColor.WARPED_STEM).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)));
    public static final DeferredBlock<Block> PALE_OAK_MOSAIC = registerBlock("pale_oak_mosaic",
            properties -> new Block(properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));

    public static final DeferredBlock<Block> OAK_MOSAIC_STAIRS = registerBlock("oak_mosaic_stairs",
            properties -> new StairBlock(OAK_MOSAIC.get().defaultBlockState(), properties.mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> BIRCH_MOSAIC_STAIRS = registerBlock("birch_mosaic_stairs",
            properties -> new StairBlock(BIRCH_MOSAIC.get().defaultBlockState(), properties.mapColor(MapColor.SAND)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> SPRUCE_MOSAIC_STAIRS = registerBlock("spruce_mosaic_stairs",
            properties -> new StairBlock(SPRUCE_MOSAIC.get().defaultBlockState(), properties.mapColor(MapColor.PODZOL)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> JUNGLE_MOSAIC_STAIRS = registerBlock("jungle_mosaic_stairs",
            properties -> new StairBlock(JUNGLE_MOSAIC.get().defaultBlockState(), properties.mapColor(MapColor.DIRT)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> ACACIA_MOSAIC_STAIRS = registerBlock("acacia_mosaic_stairs",
            properties -> new StairBlock(ACACIA_MOSAIC.get().defaultBlockState(), properties.mapColor(MapColor.COLOR_ORANGE)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> DARK_OAK_MOSAIC_STAIRS = registerBlock("dark_oak_mosaic_stairs",
            properties -> new StairBlock(DARK_OAK_MOSAIC.get().defaultBlockState(), properties.mapColor(MapColor.COLOR_BROWN)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> MANGROVE_MOSAIC_STAIRS = registerBlock("mangrove_mosaic_stairs",
            properties -> new StairBlock(MANGROVE_MOSAIC.get().defaultBlockState(), properties.mapColor(MapColor.COLOR_RED)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> CHERRY_MOSAIC_STAIRS = registerBlock("cherry_mosaic_stairs",
            properties -> new StairBlock(CHERRY_MOSAIC.get().defaultBlockState(), properties.mapColor(MapColor.TERRACOTTA_WHITE)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.CHERRY_WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> CRIMSON_MOSAIC_STAIRS = registerBlock("crimson_mosaic_stairs",
            properties -> new StairBlock(CRIMSON_MOSAIC.get().defaultBlockState(), properties.mapColor(MapColor.CRIMSON_STEM)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD).noOcclusion()));
    public static final DeferredBlock<Block> WARPED_MOSAIC_STAIRS = registerBlock("warped_mosaic_stairs",
            properties -> new StairBlock(WARPED_MOSAIC.get().defaultBlockState(), properties.mapColor(MapColor.WARPED_STEM)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD).noOcclusion()));
    public static final DeferredBlock<Block> PALE_OAK_MOSAIC_STAIRS = registerBlock("pale_oak_mosaic_stairs",
            properties -> new StairBlock(PALE_OAK_MOSAIC.get().defaultBlockState(), properties.mapColor(MapColor.QUARTZ)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));

    public static final DeferredBlock<Block> OAK_MOSAIC_SLAB = registerBlock("oak_mosaic_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> BIRCH_MOSAIC_SLAB = registerBlock("birch_mosaic_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> SPRUCE_MOSAIC_SLAB = registerBlock("spruce_mosaic_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> JUNGLE_MOSAIC_SLAB = registerBlock("jungle_mosaic_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> ACACIA_MOSAIC_SLAB = registerBlock("acacia_mosaic_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> DARK_OAK_MOSAIC_SLAB = registerBlock("dark_oak_mosaic_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> MANGROVE_MOSAIC_SLAB = registerBlock("mangrove_mosaic_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.COLOR_RED).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> CHERRY_MOSAIC_SLAB = registerBlock("cherry_mosaic_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.CHERRY_WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> CRIMSON_MOSAIC_SLAB = registerBlock("crimson_mosaic_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.CRIMSON_STEM).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD).noOcclusion()));
    public static final DeferredBlock<Block> WARPED_MOSAIC_SLAB = registerBlock("warped_mosaic_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.WARPED_STEM).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD).noOcclusion()));
    public static final DeferredBlock<Block> PALE_OAK_MOSAIC_SLAB = registerBlock("pale_oak_mosaic_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));

    public static final DeferredBlock<Block> SMOOTH_STONE_STAIRS = registerBlock("smooth_stone_stairs",
            properties -> new StairBlock(Blocks.SMOOTH_STONE.defaultBlockState(), properties.sound(SoundType.STONE)
                    .strength(5).requiresCorrectToolForDrops()));

    // container blocks

    public static final DeferredBlock<Block> CRAFTING_FURNACE = registerBlock("crafting_furnace",
            properties -> new CraftingFurnaceBlock(properties.strength(2f).requiresCorrectToolForDrops().randomTicks()
                    .lightLevel(litBlockEmission(13))));
    public static final DeferredBlock<Block> WATER_BOILER = registerBlock("water_boiler",
            properties -> new WaterBoilerBlock(properties.strength(2f).requiresCorrectToolForDrops().randomTicks()
                    .noOcclusion().lightLevel(litBlockEmission(13))));

    // furniture

    public static final DeferredBlock<Block> OAK_TABLE = registerBlock("oak_table",
            properties -> new TableBlock(properties.mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> BIRCH_TABLE = registerBlock("birch_table",
            properties -> new TableBlock(properties.mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> SPRUCE_TABLE = registerBlock("spruce_table",
            properties -> new TableBlock(properties.mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> ACACIA_TABLE = registerBlock("acacia_table",
            properties -> new TableBlock(properties.mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> JUNGLE_TABLE = registerBlock("jungle_table",
            properties -> new TableBlock(properties.mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> DARK_OAK_TABLE = registerBlock("dark_oak_table",
            properties -> new TableBlock(properties.mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> MANGROVE_TABLE = registerBlock("mangrove_table",
            properties -> new TableBlock(properties.mapColor(MapColor.COLOR_RED).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> CHERRY_TABLE = registerBlock("cherry_table",
            properties -> new TableBlock(properties.mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.CHERRY_WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> BAMBOO_TABLE = registerBlock("bamboo_table",
            properties -> new TableBlock(properties.mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.BAMBOO_WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> CRIMSON_TABLE = registerBlock("crimson_table",
            properties -> new TableBlock(properties.mapColor(MapColor.CRIMSON_STEM).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD).noOcclusion()));
    public static final DeferredBlock<Block> WARPED_TABLE = registerBlock("warped_table",
            properties -> new TableBlock(properties.mapColor(MapColor.WARPED_STEM).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD).noOcclusion()));
    public static final DeferredBlock<Block> PALE_OAK_TABLE = registerBlock("pale_oak_table",
            properties -> new TableBlock(properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));

    public static final DeferredBlock<Block> OAK_CHAIR = registerBlock("oak_chair",
            properties -> new ChairBlock(properties.mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> BIRCH_CHAIR = registerBlock("birch_chair",
            properties -> new ChairBlock(properties.mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> SPRUCE_CHAIR = registerBlock("spruce_chair",
            properties -> new ChairBlock(properties.mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> ACACIA_CHAIR = registerBlock("acacia_chair",
            properties -> new ChairBlock(properties.mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> JUNGLE_CHAIR = registerBlock("jungle_chair",
            properties -> new ChairBlock(properties.mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> DARK_OAK_CHAIR = registerBlock("dark_oak_chair",
            properties -> new ChairBlock(properties.mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> MANGROVE_CHAIR = registerBlock("mangrove_chair",
            properties -> new ChairBlock(properties.mapColor(MapColor.COLOR_RED).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> BAMBOO_CHAIR = registerBlock("bamboo_chair",
            properties -> new ChairBlock(properties.mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.BAMBOO_WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> CHERRY_CHAIR = registerBlock("cherry_chair",
            properties -> new ChairBlock(properties.mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.CHERRY_WOOD).ignitedByLava().noOcclusion()));
    public static final DeferredBlock<Block> CRIMSON_CHAIR = registerBlock("crimson_chair",
            properties -> new ChairBlock(properties.mapColor(MapColor.CRIMSON_STEM).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD).noOcclusion()));
    public static final DeferredBlock<Block> WARPED_CHAIR = registerBlock("warped_chair",
            properties -> new ChairBlock(properties.mapColor(MapColor.WARPED_STEM).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD).noOcclusion()));
    public static final DeferredBlock<Block> PALE_OAK_CHAIR = registerBlock("pale_oak_chair",
            properties -> new ChairBlock(properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()));

    @Deprecated
    public static final DeferredBlock<Block> OAK_SHELF = registerBlock("oak_shelf",
            properties -> new ShelfBlock(properties.mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().noOcclusion()
                    .randomTicks()));

    public static final DeferredBlock<Block> WOODCUTTER = registerBlock("woodcutter",
            properties -> new WoodCutterBlock(properties.mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(3.5F)));

    public static final DeferredBlock<Block> BREEZING_STAND = registerBlock("breezing_stand",
            properties -> new BreezingStandBlock(properties.mapColor(MapColor.METAL).strength(0.5F).lightLevel(state -> 1)
                    .noOcclusion().randomTicks().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> END_FIRE = registerBlockOnly("end_fire",
            properties -> new EndFireBlock(properties.mapColor(MapColor.COLOR_PURPLE).replaceable().noCollission()
                    .instabreak().lightLevel(state -> 15).sound(SoundType.WOOL)
                    .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> END_CAMPFIRE = registerBlock("end_campfire",
            properties -> new EndCampfireBlock(properties.mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F).sound(SoundType.WOOD)
                    .lightLevel(litBlockEmission(12)).noOcclusion().ignitedByLava()));

    public static final DeferredBlock<Block> END_TORCH = registerBlockOnly("end_torch",
            properties -> new TorchBlock(ModParticles.END_FLAME_PARTICLES.get(), properties.noCollission().instabreak()
                    .lightLevel(p_50886_ -> 12).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> END_WALL_TORCH = registerBlockOnly("end_wall_torch",
            properties -> new WallTorchBlock(ModParticles.END_FLAME_PARTICLES.get(), properties.noCollission().instabreak()
                    .lightLevel(p_50886_ -> 12).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> END_LANTERN = registerBlock("end_lantern",
            properties -> new LanternBlock(properties.mapColor(MapColor.METAL).forceSolidOn().strength(3.5F).sound(SoundType.LANTERN)
                    .lightLevel(state -> 12).noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static <T extends Block> DeferredBlock<T> registerBlock (String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> toReturn = registerBlockOnly(name, function);
        registerItemWithProperties(name, properties -> new BlockItem(toReturn.get(), properties));
        return toReturn;
    }

    public static <T extends Block> DeferredBlock<T> registerBlockOnly (String name, Function<BlockBehaviour.Properties, T> function) {
        return BLOCKS.registerBlock(name, function);
    }

    private static <T extends Item> void registerItemWithProperties(String name, Function<Item.Properties, T> function) {
        ModItems.ITEMS.registerItem(name, function);
    }

    private static void registerCustomBlockItems () {
        ModItems.ITEMS.registerItem("corn", properties -> new BlockItem(CORN.get(), properties.food(ModFoodProperties.CORNCOB)));
        ModItems.ITEMS.registerItem("strawberry", properties -> new BlockItem(STRAWBERRY.get(), properties.food(ModFoodProperties.STRAWBERRY)));
        ModItems.ITEMS.registerItem("raspberry", properties -> new BlockItem(RASPBERRY.get(), properties.food(ModFoodProperties.RASPBERRY)));
        ModItems.ITEMS.registerItem("blackberry", properties -> new BlockItem(BLACKBERRY.get(), properties.food(ModFoodProperties.BLACKBERRY)));
        ModItems.ITEMS.registerItem("rhubarb", properties -> new BlockItem(RHUBARB.get(), properties.food(ModFoodProperties.RHUBARB)));
        ModItems.ITEMS.registerItem("end_torch", properties -> new StandingAndWallBlockItem(END_TORCH.get(), END_WALL_TORCH.get(), Direction.DOWN, properties));
    }

    public static void register(IEventBus eventBus) {
        registerCustomBlockItems();
        BLOCKS.register(eventBus);
    }

    private static ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
        return (p_50763_) -> (Boolean) p_50763_.getValue(BlockStateProperties.LIT) ? pLightValue : 0;
    }
}