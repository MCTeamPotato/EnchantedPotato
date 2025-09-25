package me.kall.enchantedpotato.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class MineCarveConfig {
    public static final ForgeConfigSpec INSTANCE;

    public static final ForgeConfigSpec.DoubleValue BASE_ARMOR_REDUCTION, GAINED_ARMOR_REDUCTION_PER_LEVEL;
    public static final ForgeConfigSpec.IntValue REDUCTION_TICKS_DURATION, GAINED_TICKS_DURATION_PER_LEVEL;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("MineCarve");
        BASE_ARMOR_REDUCTION = builder.defineInRange("BaseArmorReductionAmount", 2.0, 0.0, Double.MAX_VALUE);
        GAINED_ARMOR_REDUCTION_PER_LEVEL = builder.defineInRange("GainedArmorReductionAmountPerLevel", 0.5, 0.0, Double.MAX_VALUE);
        REDUCTION_TICKS_DURATION = builder.defineInRange("ReductionTicksDuration", 600, 0, Integer.MAX_VALUE);
        GAINED_TICKS_DURATION_PER_LEVEL = builder.defineInRange("GainedTicksDurationPerLevel", 300, 0, Integer.MAX_VALUE);
        builder.pop();
        INSTANCE = builder.build();
    }
}
