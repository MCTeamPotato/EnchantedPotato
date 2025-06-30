package me.kall.enchantedpotato.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class DissolveConfig {
    public static final ForgeConfigSpec INSTANCE;

    public static final ForgeConfigSpec.DoubleValue
            BASE_DAMAGE_REDUCTION, GAINED_DAMAGE_REDUCTION_PER_LEVEL,
            BASE_THRESHOLD, SAVED_THRESHOLD_PER_LEVEL, MINUS_THRESHOLD;

    public static final ForgeConfigSpec.IntValue BASE_STRENGTH_DURATION, GAINED_STRENGTH_DURATION_PER_LEVEL;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("Dissolve");
        BASE_DAMAGE_REDUCTION = builder.defineInRange("BaseDamageReductionForExceededPart", 0.5D, 0, 1.0D);
        GAINED_DAMAGE_REDUCTION_PER_LEVEL = builder.defineInRange("GainedDamageReductionForExceededPartPerLevel", 0.1D, 0, 1.0D);
        BASE_THRESHOLD = builder.defineInRange("BaseTriggerThreshold", 0.3D, 0, 1.0D);
        SAVED_THRESHOLD_PER_LEVEL = builder.defineInRange("SavedTriggerThresholdPerLevel", 0.1D, 0, 1.0D);
        MINUS_THRESHOLD = builder.defineInRange("MinusTriggerThreshold", 0.09D, 0, 1.0D);
        BASE_STRENGTH_DURATION = builder.defineInRange("BaseStrengthDuration(ticks)", 200, 0, Integer.MAX_VALUE);
        GAINED_STRENGTH_DURATION_PER_LEVEL = builder.defineInRange("GainedStrengthDurationPerLevel(ticks)", 100, 0, Integer.MAX_VALUE);
        builder.pop();
        INSTANCE = builder.build();
    }
}
