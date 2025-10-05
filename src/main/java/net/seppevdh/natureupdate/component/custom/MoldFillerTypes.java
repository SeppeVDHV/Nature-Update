package net.seppevdh.natureupdate.component.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.seppevdh.natureupdate.item.ModItems;

import java.util.ArrayList;
import java.util.List;

public class MoldFillerTypes {
    // vanilla
    public static final MoldFillerType EMPTY = new MoldFillerType("empty").withFillerItem(Items.AIR);
    public static final MoldFillerType IRON = new MoldFillerType("iron").withFillerItem(Items.IRON_INGOT);
    public static final MoldFillerType GOLD = new MoldFillerType("gold").withFillerItem(Items.GOLD_INGOT);
    public static final MoldFillerType DIAMOND = new MoldFillerType("diamond");

    // custom
    public static final MoldFillerType COPPER = new MoldFillerType("copper").withFillerItem(Items.COPPER_INGOT);
    public static final MoldFillerType BRONZE = new MoldFillerType("bronze").withFillerItem(ModItems.BRONZE_INGOT.get());
    public static final MoldFillerType EMERALD = new MoldFillerType("emerald");

    public static void registerMoldFiller (MoldFillerType moldFiller) {
        registeredFillers.add(moldFiller);
    }

    public static void registerVanillaMoldFillers () {
        registerMoldFiller(EMPTY);
        registerMoldFiller(IRON);
        registerMoldFiller(GOLD);
        registerMoldFiller(DIAMOND);
    }

    private static ArrayList<MoldFillerType> registeredFillers = new ArrayList<>();

    public static ArrayList<MoldFillerType> getFillerTypes () {
        return registeredFillers;
    }

    public static List<MoldFillerType> getDefaultFillers()  {
        return registeredFillers.subList(1, registeredFillers.size());
    }

    public static MoldFillerType getTypeById (String id) {
        for (MoldFillerType filler : registeredFillers) {
            if (filler.id().equals(id)) {
                return filler;
            }
        }
        return EMPTY;
    }

    public static MoldFillerType getTypeByFillerItem (Item item) {
        for (MoldFillerType filler : registeredFillers) {
            if (filler.isFillerItem(item)) {
                return filler;
            }
        }
        return null;
    }
}
