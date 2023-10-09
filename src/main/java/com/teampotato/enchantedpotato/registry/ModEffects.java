package com.teampotato.enchantedpotato.registry;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.effect.CounterattackMoment;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EarlySetupInitializer.MOD_ID);
    public static final RegistryObject<MobEffect> COUNTERATTACK_MOMENT;

    static {
        COUNTERATTACK_MOMENT = EFFECTS.register(CounterattackMoment.PATH, () -> CounterattackMoment.INSTANCE);
    }
}
