package net.surion.testmod.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ChiselItem extends Item {

    public ChiselItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();

        // protezione: se non c'è player o siamo client -> PASS
        if (player == null) {return ActionResult.PASS;}
        if (world.isClient()) {return ActionResult.PASS;}

        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);

        // se il blocco cliccato NON è STONE -> non fare nulla
        if (!state.isOf(Blocks.STONE)) {return ActionResult.PASS;}


        // qui siamo server-side e il blocco è STONE -> esegui la logica
        ItemStack cobblestoneStack = new ItemStack(Items.COBBLESTONE, 4);
        boolean inserted = player.getInventory().insertStack(cobblestoneStack);
        if (!inserted) {
            player.dropItem(cobblestoneStack, false);
        }

        // prendi lo stack che il player tiene in mano
        ItemStack stackInHand = player.getStackInHand(context.getHand());

        // se il player non è in creative danneggialo correttamente
        if (!player.getAbilities().creativeMode) {
            if (stackInHand.isDamageable()) {
                // usa cast a ServerPlayerEntity per la callback di rottura
                context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                        item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));
            }
        }

        // feedback visivo e sonoro
        player.swingHand(context.getHand());
        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);

        return ActionResult.SUCCESS;
    }
}
