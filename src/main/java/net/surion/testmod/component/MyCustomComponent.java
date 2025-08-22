package net.surion.testmod.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record MyCustomComponent(float weight, float height , boolean broken) {


    public static final Codec<MyCustomComponent> CODEC = RecordCodecBuilder.create(builder -> {
        return builder.group(
                Codec.FLOAT.fieldOf("weight").forGetter(MyCustomComponent::weight),
                Codec.FLOAT.fieldOf("leaves").forGetter(MyCustomComponent::height),
                Codec.BOOL.optionalFieldOf("broken", false).forGetter(MyCustomComponent::broken)
        ).apply(builder, MyCustomComponent::new);
    });
}
