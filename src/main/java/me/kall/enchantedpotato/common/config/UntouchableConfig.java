package me.kall.enchantedpotato.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class UntouchableConfig {
    public static final ForgeConfigSpec INSTANCE;
    public static final ForgeConfigSpec.IntValue BASIC_COOLDOWN, SAVED_COOLDOWN_PER_LEVEL, BASIC_SLOWNESS_DURATION, GAINED_SLOWNESS_DURATION_PER_LEVEL;

    public static final ForgeConfigSpec.DoubleValue BASIC_RADIUS, GAINED_RADIUS_PER_LEVEL, BASIC_FORCE, GAINED_FORCE_PER_LEVEL;


    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("Untouchable");
        builder.push("CoolDown");
        builder.comment("Untouchable enchantment cooldown calculation: BasicCoolDown - CurrentEnchantmentLevel * SavedCoolDownPerLevel");
        BASIC_COOLDOWN = builder.defineInRange("BasicCoolDown(ticks)", 1200, 0, Integer.MAX_VALUE);
        SAVED_COOLDOWN_PER_LEVEL = builder.defineInRange("SavedCoolDownPerLevel(ticks)", 200, 0, Integer.MAX_VALUE);
        builder.pop();
        builder.push("KnockBack");
        BASIC_RADIUS = builder.defineInRange("BasicRadius", 5.0, 0, Double.MAX_VALUE);
        GAINED_RADIUS_PER_LEVEL = builder.defineInRange("GainedRadiusPerLevel", 1.5, 0, Double.MAX_VALUE);
        BASIC_FORCE = builder.defineInRange("BasicForce", 0.8, 0, Double.MAX_VALUE);
        GAINED_FORCE_PER_LEVEL = builder.defineInRange("GainedForcePerLevel", 0.2, 0, Double.MAX_VALUE);
        BASIC_SLOWNESS_DURATION = builder.defineInRange("BasicSlownessDuration", 100, 0, Integer.MAX_VALUE);
        GAINED_SLOWNESS_DURATION_PER_LEVEL = builder.defineInRange("GainedSlownessDurationPerLevel", 50, 0, Integer.MAX_VALUE);
        builder.pop();
        builder.pop();
        INSTANCE = builder.build();
    }
}
