package me.kall.enchantedpotato.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class CaressingMoonlightConfig {
    public static final ModConfigSpec INSTANCE;
    public static final ModConfigSpec.DoubleValue BASE_DAMAGE_FOR_EACH_EFFECT, GAINED_DAMAGE_FOR_EACH_EFFECT_PER_LEVEL;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("CaressingMoonlight");
        BASE_DAMAGE_FOR_EACH_EFFECT = builder.defineInRange("BaseDamageForEachEffect", 1, 0, Double.MAX_VALUE);
        GAINED_DAMAGE_FOR_EACH_EFFECT_PER_LEVEL = builder.defineInRange("GainedDamageForEachEffectPerLevel", 1, 0, Double.MAX_VALUE);
        builder.pop();
        INSTANCE = builder.build();
    }
}
