package me.kall.enchantedpotato.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class SpaceLeapfrogConfig {
    public static final ForgeConfigSpec INSTANCE;

    public static final ForgeConfigSpec.IntValue BASIC_COOLDOWN, SAVED_COOLDOWN_PER_LEVEL;
    public static final ForgeConfigSpec.DoubleValue BASIC_LEAPFROG_DIST, GAINED_LEAPFROG_DIST_PER_LEVEL, BASIC_EXPLOSION_RADIUS, GAINED_EXPLOSION_RADIUS_PER_LEVEL;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("SpaceLeapfrog");
        BASIC_COOLDOWN = builder.defineInRange("BasicCoolDown(ticks)", 1200, 0, Integer.MAX_VALUE);
        SAVED_COOLDOWN_PER_LEVEL = builder.defineInRange("SavedCoolDownPerLevel(ticks)", 200, 0, Integer.MAX_VALUE);
        BASIC_LEAPFROG_DIST = builder.defineInRange("BasicLeapfrogDistance", 5, 0, Double.MAX_VALUE);
        GAINED_LEAPFROG_DIST_PER_LEVEL = builder.defineInRange("GainedLeapfrogDistancePerLevel", 5, 0, Double.MAX_VALUE);
        BASIC_EXPLOSION_RADIUS = builder.defineInRange("BasicExplosionRadius", 3, 0, Double.MAX_VALUE);
        GAINED_EXPLOSION_RADIUS_PER_LEVEL = builder.defineInRange("GainedExplosionRadiusPerLevel", 3, 0, Double.MAX_VALUE);
        builder.pop();
        INSTANCE = builder.build();
    }
}
