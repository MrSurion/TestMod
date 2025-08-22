package net.surion.testmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.surion.testmod.TestMod;
import net.surion.testmod.component.ModDataComponentTypes;
import net.surion.testmod.component.MyCustomComponent;
import net.surion.testmod.item.custom.BellItem;
import net.surion.testmod.item.custom.CauliflowerDrinkItem;
import net.surion.testmod.item.custom.ChiselItem;

import java.util.List;

public class ModItems {

    public static final Item PINK_GARNET = registerItem("pink_garnet", new Item(new Item.Settings().component(ModDataComponentTypes.MY_CUSTOM_COMPONENT, new MyCustomComponent(1f,2f,false))){
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            if (stack.contains(ModDataComponentTypes.MY_CUSTOM_COMPONENT)){
                boolean isBroken = stack.get(ModDataComponentTypes.MY_CUSTOM_COMPONENT).broken();
                float weight = stack.get(ModDataComponentTypes.MY_CUSTOM_COMPONENT).weight();
                float height = stack.get(ModDataComponentTypes.MY_CUSTOM_COMPONENT).height();
                tooltip.add(Text.translatable("tooltip.testmod.pink_garnet_isBroken",isBroken).formatted(Formatting.GOLD));
                tooltip.add(Text.translatable("tooltip.testmod.pink_garnet_weight",weight).formatted(Formatting.GOLD));
                tooltip.add(Text.translatable("tooltip.testmod.pink_garnet_height",height).formatted(Formatting.GOLD));
            }
            super.appendTooltip(stack, context, tooltip, type);
        }
    });
    public static final Item RAW_PINK_GARNET= registerItem("raw_pink_garnet", new Item(new Item.Settings()));

    public static final Item CHISEL_ITEM = registerItem("chisel", new ChiselItem(new Item.Settings().maxDamage(32)));

    public static final Item CAULIFLOWER = registerItem("cauliflower", new Item(new Item.Settings().food(ModFoodComponents.CAULIFLOWER)));
    public static final Item CAULIFLOWER_DRINK = registerItem("cauliflower_drink", new CauliflowerDrinkItem(new Item.Settings().food(ModFoodComponents.CAULIFLOWER)){
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltips.testmod.cauliflower_drink"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });


    public static final Item STARLIGHT_ASHES =registerItem("starlight_ashes", new Item(new Item.Settings()));

    public static final Item BELL = registerItem("bell", new BellItem(new Item.Settings().component(ModDataComponentTypes.CLICK_COUNT_COMPONENT,0)));


    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(TestMod.MOD_ID, name), item);
    }

    public static void  registerModItems() {
        TestMod.LOGGER.info("Registering Mod Items for " + TestMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(PINK_GARNET);
            fabricItemGroupEntries.add(RAW_PINK_GARNET);
        });
    }
}
