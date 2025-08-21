package net.surion.testmod.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent CAULIFLOWER = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 200),0.15f)
            .build();
}
