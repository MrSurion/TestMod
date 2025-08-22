package net.surion.testmod.component;

import com.mojang.serialization.Codec;
import net.surion.testmod.TestMod;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final ComponentType<Integer> CLICK_COUNT_COMPONENT  =
            register("click_count", builder -> builder.codec(Codec.INT));

    public static final ComponentType<MyCustomComponent> MY_CUSTOM_COMPONENT= register( "custom",
            builder -> builder.codec(MyCustomComponent.CODEC));



    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(TestMod.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {
        TestMod.LOGGER.info("Registering Data Component Types for " + TestMod.MOD_ID);
    }
}