package net.surion.testmod.item.custom;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.surion.testmod.TestMod;
import net.surion.testmod.item.ModItems;

public class FuelItems {

    public static void registerFuel() {
        TestMod.LOGGER.info("Registering Fuel Items to " + TestMod.MOD_ID);

        FuelRegistry.INSTANCE
                .add(ModItems.STARLIGHT_ASHES,600);
    }
}
