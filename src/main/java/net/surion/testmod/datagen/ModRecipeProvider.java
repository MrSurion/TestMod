package net.surion.testmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.surion.testmod.block.ModBlocks;
import net.surion.testmod.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {

        List<ItemConvertible> PINK_GARNET_SMELTABLES = List.of(ModItems.RAW_PINK_GARNET, ModBlocks.PINK_GARNET_ORE,ModBlocks.PINK_GARNET_DEEPSLATE_ORE);

        offerSmelting(recipeExporter, PINK_GARNET_SMELTABLES,RecipeCategory.MISC, ModItems.PINK_GARNET, 0.2f, 200, "pink_garnet");
       offerBlasting(recipeExporter, PINK_GARNET_SMELTABLES,RecipeCategory.MISC, ModItems.PINK_GARNET, 0.2f, 100, "pink_garnet");

        offerReversibleCompactingRecipes(recipeExporter,RecipeCategory.MISC,ModItems.PINK_GARNET, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PINK_GARNET_BLOCK);
        offerReversibleCompactingRecipes(recipeExporter,RecipeCategory.MISC,ModItems.RAW_PINK_GARNET, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_PINK_GARNET_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CHISEL_ITEM)
                .pattern("R")
                .pattern("S")
                .input('R',ModItems.PINK_GARNET)
                .input('S', Items.STICK)
                .criterion("has_pink_garnet", RecipeProvider.conditionsFromItem(ModItems.PINK_GARNET))
                .criterion("has_stick", RecipeProvider.conditionsFromItem(Items.STICK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PINK_GARNET_LAMP)
                .pattern("RRR")
                .pattern("RSR")
                .pattern("RRR")
                .input('R', ModItems.PINK_GARNET)
                .input('S', Items.GLOWSTONE_DUST)
                .criterion("has_pink_garnet", RecipeProvider.conditionsFromItem(ModItems.PINK_GARNET))
                .criterion("has_glowstone_dust", RecipeProvider.conditionsFromItem(Items.GLOWSTONE_DUST))
                .offerTo(recipeExporter);



    }
}

