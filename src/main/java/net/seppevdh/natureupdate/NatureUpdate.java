package net.seppevdh.natureupdate;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.seppevdh.natureupdate.advancement.ModAdvancementTriggers;
import net.seppevdh.natureupdate.block.ModBlocks;
import net.seppevdh.natureupdate.block.custom.CornCropBlock;
import net.seppevdh.natureupdate.block.custom.RiceCropBlock;
import net.seppevdh.natureupdate.block.custom.TreeCropBlock;
import net.seppevdh.natureupdate.block.entity.ModBlockEntities;
import net.seppevdh.natureupdate.block.entity.renderer.EndCampfireBlockEntityRenderer;
import net.seppevdh.natureupdate.block.entity.renderer.ShelfBlockEntityRenderer;
import net.seppevdh.natureupdate.block.entity.renderer.TableBlockEntityRenderer;
import net.seppevdh.natureupdate.block.entity.renderer.WaterBoilerBlockEntityRenderer;
import net.seppevdh.natureupdate.component.ModDataComponentTypes;
import net.seppevdh.natureupdate.component.custom.MoldFillerTypes;
import net.seppevdh.natureupdate.entity.ModEntities;
import net.seppevdh.natureupdate.entity.client.ChairRenderer;
import net.seppevdh.natureupdate.fluid.BaseFluidType;
import net.seppevdh.natureupdate.fluid.ModFluidTypes;
import net.seppevdh.natureupdate.fluid.ModFluids;
import net.seppevdh.natureupdate.item.ModCreativeModeTabs;
import net.seppevdh.natureupdate.item.ModItems;
import net.seppevdh.natureupdate.item.custom.MoldItem;
import net.seppevdh.natureupdate.item.custom.SickleItem;
import net.seppevdh.natureupdate.loot.ModLootModifiers;
import net.seppevdh.natureupdate.particle.EndFlameParticles;
import net.seppevdh.natureupdate.particle.ModParticles;
import net.seppevdh.natureupdate.recipe.ModRecipes;
import net.seppevdh.natureupdate.screen.ModMenuTypes;
import net.seppevdh.natureupdate.screen.custom.*;
import net.seppevdh.natureupdate.sound.ModSounds;
import net.seppevdh.natureupdate.villager.ModVillagers;
import net.seppevdh.natureupdate.worldgen.tree.ModTrunkPlacerTypes;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import java.util.List;
import java.util.Map;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(NatureUpdate.MOD_ID)
public class NatureUpdate {
    public static final String MOD_ID = "natureupdate";
    private static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public NatureUpdate(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModDataComponentTypes.register(modEventBus);

        ModSounds.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModTrunkPlacerTypes.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);

        ModVillagers.register(modEventBus);
        ModEntities.register(modEventBus);
        ModParticles.register(modEventBus);
        ModAdvancementTriggers.register(modEventBus);

        ModFluidTypes.register(modEventBus);
        ModFluids.register(modEventBus);
        ModLootModifiers.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    public static void log (String text) {
        LOGGER.info(text);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            log("Starting Nature Update...");

            SickleItem.addVanillaHarvestableBlocks();
            SickleItem.addHarvestable(ModBlocks.RICE_CROP.get(), RiceCropBlock.AGE);
            SickleItem.addHarvestable(ModBlocks.STRAWBERRY.get(), SweetBerryBushBlock.AGE);
            SickleItem.addHarvestable(ModBlocks.RASPBERRY.get(), SweetBerryBushBlock.AGE);
            SickleItem.addHarvestable(ModBlocks.BLACKBERRY.get(), SweetBerryBushBlock.AGE);
            SickleItem.addHarvestableDoubleCrop(ModBlocks.CORN.get(), CornCropBlock.AGE, CornCropBlock.HALF);

            TreeCropBlock.registerVanillaTreeCropBlocks();

            MoldFillerTypes.registerVanillaMoldFillers();
            MoldFillerTypes.registerMoldFiller(MoldFillerTypes.COPPER);
            MoldFillerTypes.registerMoldFiller(MoldFillerTypes.BRONZE);
            MoldFillerTypes.registerMoldFiller(MoldFillerTypes.EMERALD);

            ((MoldItem) ModItems.AXE_MOLD.get()).registerMoldTransformers(Map.of("iron", ModItems.IRON_AXE_HEAD.get(),"gold", ModItems.GOLDEN_AXE_HEAD.get(),
                    "copper", ModItems.COPPER_AXE_HEAD.get(),"bronze", ModItems.BRONZE_AXE_HEAD.get(),
                    "diamond", ModItems.DIAMOND_AXE_HEAD.get(),"emerald", ModItems.EMERALD_AXE_HEAD.get()));
            ((MoldItem) ModItems.PICKAXE_MOLD.get()).registerMoldTransformers(Map.of("iron", ModItems.IRON_PICKAXE_HEAD.get(),"gold", ModItems.GOLDEN_PICKAXE_HEAD.get(),
                    "copper", ModItems.COPPER_PICKAXE_HEAD.get(),"bronze", ModItems.BRONZE_PICKAXE_HEAD.get(),
                    "diamond", ModItems.DIAMOND_PICKAXE_HEAD.get(),"emerald", ModItems.EMERALD_PICKAXE_HEAD.get()));
            ((MoldItem) ModItems.SWORD_MOLD.get()).registerMoldTransformers(Map.of("iron", ModItems.IRON_SWORD_HEAD.get(),"gold", ModItems.GOLDEN_SWORD_HEAD.get(),
                    "copper", ModItems.COPPER_SWORD_HEAD.get(),"bronze", ModItems.BRONZE_SWORD_HEAD.get(),
                    "diamond", ModItems.DIAMOND_SWORD_HEAD.get(),"emerald", ModItems.EMERALD_SWORD_HEAD.get()));
            ((MoldItem) ModItems.SHOVEL_MOLD.get()).registerMoldTransformers(Map.of("iron", ModItems.IRON_SHOVEL_HEAD.get(),"gold", ModItems.GOLDEN_SHOVEL_HEAD.get(),
                    "copper", ModItems.COPPER_SHOVEL_HEAD.get(),"bronze", ModItems.BRONZE_SHOVEL_HEAD.get(),
                    "diamond", ModItems.DIAMOND_SHOVEL_HEAD.get(),"emerald", ModItems.EMERALD_SHOVEL_HEAD.get()));
            ((MoldItem) ModItems.HOE_MOLD.get()).registerMoldTransformers(Map.of("iron", ModItems.IRON_HOE_HEAD.get(),"gold", ModItems.GOLDEN_HOE_HEAD.get(),
                    "copper", ModItems.COPPER_HOE_HEAD.get(),"bronze", ModItems.BRONZE_HOE_HEAD.get(),
                    "diamond", ModItems.DIAMOND_HOE_HEAD.get(),"emerald", ModItems.EMERALD_HOE_HEAD.get()));
            ((MoldItem) ModItems.SAW_MOLD.get()).registerMoldTransformers(Map.of("iron", ModItems.IRON_SAW_HEAD.get(),"gold", ModItems.GOLDEN_SAW_HEAD.get(),
                    "copper", ModItems.COPPER_SAW_HEAD.get(),"bronze", ModItems.BRONZE_SAW_HEAD.get(),
                    "diamond", ModItems.DIAMOND_SAW_HEAD.get(),"emerald", ModItems.EMERALD_SAW_HEAD.get()));

            ((MoldItem) ModItems.SICKLE_MOLD.get()).registerMoldTransformers(Map.of("iron", ModItems.SICKLE_HEAD.get()));
            ((MoldItem) ModItems.SAW_MOLD.get()).registerDefaultTransformer(List.of(ModItems.SAW_HANDLE.get()));
            ((MoldItem) ModItems.SHEARS_MOLD.get()).registerMoldTransformers(Map.of("iron", Items.SHEARS));
            ((MoldItem) ModItems.HELMET_MOLD.get()).registerMoldTransformers(Map.of("iron", Items.IRON_HELMET,"gold", Items.GOLDEN_HELMET,
                    "copper", ModItems.COPPER_HELMET.get(),"bronze", ModItems.BRONZE_HELMET.get(),
                    "diamond", Items.DIAMOND_HELMET,"emerald", ModItems.EMERALD_HELMET.get()));
            ((MoldItem) ModItems.CHESTPLATE_MOLD.get()).registerMoldTransformers(Map.of("iron", Items.IRON_CHESTPLATE,"gold", Items.GOLDEN_CHESTPLATE,
                    "copper", ModItems.COPPER_CHESTPLATE.get(),"bronze", ModItems.BRONZE_CHESTPLATE.get(),
                    "diamond", Items.DIAMOND_CHESTPLATE,"emerald", ModItems.EMERALD_CHESTPLATE.get()));
            ((MoldItem) ModItems.LEGGINGS_MOLD.get()).registerMoldTransformers(Map.of("iron", Items.IRON_LEGGINGS,"gold", Items.GOLDEN_LEGGINGS,
                    "copper", ModItems.COPPER_LEGGINGS.get(),"bronze", ModItems.BRONZE_LEGGINGS.get(),
                    "diamond", Items.DIAMOND_LEGGINGS,"emerald", ModItems.EMERALD_LEGGINGS.get()));
            ((MoldItem) ModItems.BOOTS_MOLD.get()).registerMoldTransformers(Map.of("iron", Items.IRON_BOOTS,"gold", Items.GOLDEN_BOOTS,
                    "copper", ModItems.COPPER_BOOTS.get(),"bronze", ModItems.BRONZE_BOOTS.get(),
                    "diamond", Items.DIAMOND_BOOTS,"emerald", ModItems.EMERALD_BOOTS.get()));

            ((MoldItem) ModItems.AXE_MOLD.get()).registerItemUsingRecipes();
            ((MoldItem) ModItems.PICKAXE_MOLD.get()).registerItemUsingRecipes();
            ((MoldItem) ModItems.SWORD_MOLD.get()).registerItemUsingRecipes();
            ((MoldItem) ModItems.SHOVEL_MOLD.get()).registerItemUsingRecipes();
            ((MoldItem) ModItems.HOE_MOLD.get()).registerItemUsingRecipes();
            ((MoldItem) ModItems.SAW_MOLD.get()).registerItemUsingRecipes();
            ((MoldItem) ModItems.SICKLE_MOLD.get()).registerItemUsingRecipes();
            ((MoldItem) ModItems.SHEARS_MOLD.get()).registerItemUsingRecipes();
            ((MoldItem) ModItems.HELMET_MOLD.get()).registerItemUsingRecipes();
            ((MoldItem) ModItems.CHESTPLATE_MOLD.get()).registerItemUsingRecipes();
            ((MoldItem) ModItems.LEGGINGS_MOLD.get()).registerItemUsingRecipes();
            ((MoldItem) ModItems.BOOTS_MOLD.get()).registerItemUsingRecipes();
        });
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.CHAIR.get(), ChairRenderer::new);

            ItemBlockRenderTypes.setRenderLayer(ModBlocks.GOLDEN_DOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.GOLDEN_TRAPDOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.GOLDEN_GRATE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.EMERALD_DOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.EMERALD_TRAPDOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.EMERALD_GRATE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.DIAMOND_DOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.DIAMOND_TRAPDOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.DIAMOND_GRATE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.NETHERITE_DOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.NETHERITE_TRAPDOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.NETHERITE_GRATE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.LAPIS_DOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.LAPIS_TRAPDOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.LAPIS_GRATE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.TIN_DOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.TIN_TRAPDOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.TIN_GRATE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BRONZE_DOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BRONZE_TRAPDOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BRONZE_GRATE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.IRON_GRATE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.GOLD_BARS.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.EMERALD_BARS.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.LAPIS_BARS.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.DIAMOND_BARS.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.NETHERITE_BARS.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.TIN_BARS.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BRONZE_BARS.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.END_CAMPFIRE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.END_FIRE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_SOUL_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_SOUL_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_END_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_END_FLUID.get(), RenderType.translucent());
        }

        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.WATER_BOILER_BE.get(), WaterBoilerBlockEntityRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntities.TABLE_BE.get(), TableBlockEntityRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntities.SHELF_BE.get(), ShelfBlockEntityRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntities.END_CAMPFIRE_BE.get(), EndCampfireBlockEntityRenderer::new);
        }

        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(ModMenuTypes.CRAFTING_FURNACE_MENU.get(), CraftingFurnaceScreen::new);
            event.register(ModMenuTypes.WATER_BOILER_MENU.get(), WaterBoilerScreen::new);
            event.register(ModMenuTypes.TABLE_MENU.get(), TableScreen::new);
            event.register(ModMenuTypes.WOODCUTTER_MENU.get(), WoodCutterScreen::new);
            event.register(ModMenuTypes.BREEZING_STAND_MENU.get(), BreezingStandScreen::new);
        }

        @SubscribeEvent
        public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(ModParticles.END_FLAME_PARTICLES.get(), EndFlameParticles.Provider::new);
        }

        @SubscribeEvent
        public static void onClientExtension (RegisterClientExtensionsEvent event) {
            event.registerFluidType(((BaseFluidType) ModFluidTypes.SOUL_FLUID_TYPE.get()).getClientFluidTypeExtensions(),
                    ModFluidTypes.SOUL_FLUID_TYPE.get());
            event.registerFluidType(((BaseFluidType) ModFluidTypes.END_FLUID_TYPE.get()).getClientFluidTypeExtensions(),
                    ModFluidTypes.END_FLUID_TYPE.get());
        }
    }
}
