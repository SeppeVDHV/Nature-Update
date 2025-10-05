package net.seppevdh.natureupdate.compat.jade;

import net.seppevdh.natureupdate.block.custom.BreezingStandBlock;
import net.seppevdh.natureupdate.block.custom.CraftingFurnaceBlock;
import net.seppevdh.natureupdate.block.custom.WaterBoilerBlock;
import net.seppevdh.natureupdate.block.entity.custom.BreezingStandBlockEntity;
import net.seppevdh.natureupdate.block.entity.custom.CraftingFurnaceBlockEntity;
import net.seppevdh.natureupdate.block.entity.custom.EndCampfireBlockEntity;
import net.seppevdh.natureupdate.block.entity.custom.WaterBoilerBlockEntity;
import net.seppevdh.natureupdate.compat.jade.custom.BreezingStandProvider;
import net.seppevdh.natureupdate.compat.jade.custom.CraftingFurnaceProvider;
import net.seppevdh.natureupdate.compat.jade.custom.EndCampFireProvider;
import net.seppevdh.natureupdate.compat.jade.custom.WaterBoilerProvider;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class JadeNatureUpdatePlugin implements IWailaPlugin {
    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(CraftingFurnaceProvider.INSTANCE, CraftingFurnaceBlock.class);
        registration.registerBlockComponent(WaterBoilerProvider.INSTANCE, WaterBoilerBlock.class);
        registration.registerBlockComponent(BreezingStandProvider.INSTANCE, BreezingStandBlock.class);
        registration.registerItemStorageClient(EndCampFireProvider.INSTANCE);
    }

    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(CraftingFurnaceProvider.INSTANCE, CraftingFurnaceBlockEntity.class);
        registration.registerBlockDataProvider(WaterBoilerProvider.INSTANCE, WaterBoilerBlockEntity.class);
        registration.registerBlockDataProvider(BreezingStandProvider.INSTANCE, BreezingStandBlockEntity.class);
        registration.registerItemStorage(EndCampFireProvider.INSTANCE, EndCampfireBlockEntity.class);
    }
}