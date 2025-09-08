package me.kall.enchantedpotato.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class GurenNoYumiyaConfig {
    public static final ModConfigSpec INSTANCE;

    public static final ModConfigSpec.DoubleValue SAVED_HOLDING_SECONDS_PER_LEVEL;

    public static final ModConfigSpec.DoubleValue BASE_RADIUS, GAINED_RADIUS_PER_LEVEL;

    public static final ModConfigSpec.IntValue BASE_FIRE_SECONDS, GAINED_FIRE_SECONDS_PER_LEVEL;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("GurenNoYumiya");
        SAVED_HOLDING_SECONDS_PER_LEVEL = builder.defineInRange("SavedHoldingSecondsPerLevel", 0.25, 0, Integer.MAX_VALUE);
        BASE_RADIUS = builder.defineInRange("BaseRadius", 3D, 0D, Double.MAX_VALUE);
        GAINED_RADIUS_PER_LEVEL = builder.defineInRange("GainedRadiusPerLevel", 2D, 0D, Double.MAX_VALUE);
        BASE_FIRE_SECONDS = builder.defineInRange("BaseFireSeconds", 5, 0, Integer.MAX_VALUE);
        GAINED_FIRE_SECONDS_PER_LEVEL = builder.defineInRange("GainedFireSecondsPerLevel", 4, 0, Integer.MAX_VALUE);
        builder.pop();
        INSTANCE = builder.build();
    }
}
