package net.surion.testmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.surion.testmod.item.ModItems;

public class ColoredBlock extends Block {

    public static final IntProperty COLOR = IntProperty.of("color",1,3);

    public ColoredBlock(Settings settings) {
        super(settings);
        setDefaultState(this.getDefaultState().with(COLOR,1));
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient()){
            if (player.getMainHandStack().isEmpty()){
                int currentColor = state.get(COLOR);
                if (currentColor < 3){
                    world.setBlockState(pos, state.with(COLOR, currentColor+1),3);}
            } else if (player.getMainHandStack().getItem() == ModItems.PINK_GARNET) {
                world.setBlockState(pos, state.with(COLOR, 1),3);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(COLOR);
        super.appendProperties(builder);
    }
}
