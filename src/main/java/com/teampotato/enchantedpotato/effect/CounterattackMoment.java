package com.teampotato.enchantedpotato.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class CounterattackMoment extends MobEffect {
    public static final CounterattackMoment INSTANCE = new CounterattackMoment();
    public static final String PATH = "counterattack_moment";
    
    public CounterattackMoment() {
        super(MobEffectCategory.BENEFICIAL, 52368423);
    }
}
