package net.surion.testmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.surion.testmod.block.ModBlocks;
import net.surion.testmod.item.ModItemGroups;
import net.surion.testmod.item.ModItems;
import net.surion.testmod.item.custom.FuelItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMod implements ModInitializer {
	public static final String MOD_ID = "testmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModItemGroups.registerGroups();

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

        FuelItems.registerFuel();
	}
}