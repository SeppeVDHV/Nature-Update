package net.seppevdh.natureupdate.block.state;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.seppevdh.natureupdate.recipe.fueling.ModFuelingTypes;

public class ModBlocksStateProperties {
    public static final EnumProperty<ShelfType> SHELF_TYPE = EnumProperty.create("type", ShelfType.class);
    public static final EnumProperty<ModFuelingTypes> FUEL_TYPE = EnumProperty.create("fuel_type", ModFuelingTypes.class);
}
