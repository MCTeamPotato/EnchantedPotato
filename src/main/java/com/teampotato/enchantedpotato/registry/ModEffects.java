package com.teampotato.enchantedpotato.registry;

import com.teampotato.enchantedpotato.effect.CounterattackMoment;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, EarlySetupInitializer.MOD_ID);
    public static final DeferredHolder<MobEffect, CounterattackMoment> COUNTERATTACK_MOMENT;

    static {
        COUNTERATTACK_MOMENT = EFFECTS.register(CounterattackMoment.PATH, () -> CounterattackMoment.INSTANCE);
    }
}
