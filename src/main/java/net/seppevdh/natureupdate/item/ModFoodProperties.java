package net.seppevdh.natureupdate.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties RICE = new FoodProperties.Builder().nutrition(1)
            .saturationModifier(0.2f).build();
    public static final FoodProperties CORNCOB = new FoodProperties.Builder().nutrition(3)
            .saturationModifier(0.6f).build();
    public static final FoodProperties STRAWBERRY = new FoodProperties.Builder().nutrition(2)
            .saturationModifier(0.2f).build();
    public static final FoodProperties RASPBERRY = new FoodProperties.Builder().nutrition(2)
            .saturationModifier(0.2f).build();
    public static final FoodProperties BLACKBERRY = new FoodProperties.Builder().nutrition(2)
            .saturationModifier(0.2f).build();
    public static final FoodProperties PEAR = new FoodProperties.Builder().nutrition(4)
            .saturationModifier(0.6f).build();
    public static final FoodProperties RHUBARB = new FoodProperties.Builder().nutrition(2)
            .saturationModifier(0.2f).build();
}
