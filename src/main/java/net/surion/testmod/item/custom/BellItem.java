package net.surion.testmod.item.custom;

import net.minecraft.client.sound.Sound;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.surion.testmod.component.ModDataComponentTypes;

import java.util.List;

public class BellItem extends Item {
    public BellItem(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient()) {
            int count = stack.getOrDefault(ModDataComponentTypes.CLICK_COUNT_COMPONENT, 0);

            count++;

            if (count >= 5) {

                stack.set(ModDataComponentTypes.CLICK_COUNT_COMPONENT, 0);
                world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.BLOCKS, 1f, 1f);
            } else {
                stack.set(ModDataComponentTypes.CLICK_COUNT_COMPONENT, count);
            }
        }

        return TypedActionResult.success(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (stack.contains(ModDataComponentTypes.CLICK_COUNT_COMPONENT)){
            int count = stack.get(ModDataComponentTypes.CLICK_COUNT_COMPONENT);
            tooltip.add(Text.translatable("tooltip.testmod.bell_item",count).formatted(Formatting.GOLD));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
