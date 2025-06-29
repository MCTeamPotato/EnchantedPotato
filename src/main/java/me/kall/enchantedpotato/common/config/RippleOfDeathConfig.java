package me.kall.enchantedpotato.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class RippleOfDeathConfig {
    public static final ForgeConfigSpec INSTANCE;

    public static final ForgeConfigSpec.IntValue BASE_RADIUS, GAINED_RADIUS_PER_LEVEL;
    public static final ForgeConfigSpec.DoubleValue BASIC_DAMAGE_PERCENT, GAINED_DAMAGE_PERCENT_PER_LEVEL;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("RippleOfDeath");
        BASE_RADIUS = builder.defineInRange("BaseRadius", 3, 0, Integer.MAX_VALUE);
        GAINED_RADIUS_PER_LEVEL = builder.defineInRange("GainedRadiusPerLevel", 2, 0, Integer.MAX_VALUE);
        BASIC_DAMAGE_PERCENT = builder.defineInRange("BasicDamagePercent", 0.2D, 0, 1D);
        GAINED_DAMAGE_PERCENT_PER_LEVEL = builder.defineInRange("GainedDamagePercentPerLevel", 0.1F, 0, 1D);
        builder.pop();
        INSTANCE = builder.build();
    }
}
