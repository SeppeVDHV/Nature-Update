package net.seppevdh.natureupdate.screen;

import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.seppevdh.natureupdate.NatureUpdate;
import net.seppevdh.natureupdate.screen.custom.*;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, NatureUpdate.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<CraftingFurnaceMenu>> CRAFTING_FURNACE_MENU =
            registerMenuType("crafting_furnace_menu", CraftingFurnaceMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<WaterBoilerMenu>> WATER_BOILER_MENU =
            registerMenuType("water_boiler_menu", WaterBoilerMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<TableMenu>> TABLE_MENU =
            registerMenuType("table_menu", TableMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<WoodCutterMenu>> WOODCUTTER_MENU =
            registerMenuType("woodcutter_menu", WoodCutterMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<BreezingStandMenu>> BREEZING_STAND_MENU =
            registerMenuType("breezing_stand_menu", BreezingStandMenu::new);

    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
