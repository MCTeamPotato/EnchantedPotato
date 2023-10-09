package com.teampotato.enchantedpotato.mixin.api;

import com.teampotato.enchantedpotato.api.IAttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AttributeModifier.class)
public class AttributeModifierMixin implements IAttributeModifier {
    @Mutable @Shadow @Final private double amount;

    @Override
    public void ep$setAmount(double amount) {
        this.amount = amount;
    }
}
