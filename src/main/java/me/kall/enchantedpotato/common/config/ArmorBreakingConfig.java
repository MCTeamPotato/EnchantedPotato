package me.kall.enchantedpotato.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ArmorBreakingConfig {
    public static final ModConfigSpec INSTANCE;

    public static final ModConfigSpec.IntValue BASE_DURATION, GAINED_DURATION_PER_LEVEL;
    public static final ModConfigSpec.DoubleValue BASE_ARMOR_REDUCTION, GAINED_ARMOR_REDUCTION_PER_LEVEL, BASE_ARMOR_TOUGHNESS_REDUCTION, GAINED_ARMOR_TOUGHNESS_REDUCTION_PER_LEVEL, MINUS_ARMOR_REDUCTION_AMOUNT;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("ArmorBreaking");
        BASE_DURATION = builder.defineInRange("BaseDuration(ticks)", 200, 0, Integer.MAX_VALUE);
        GAINED_DURATION_PER_LEVEL = builder.defineInRange("GainedDurationPerLevel(ticks)", 100, 0, Integer.MAX_VALUE);
        BASE_ARMOR_REDUCTION = builder.defineInRange("BaseArmorReduction(%)", 0.25D, 0, 1.0D);
        GAINED_ARMOR_REDUCTION_PER_LEVEL = builder.defineInRange("GainedArmorReductionPerLevel(%)", 0.1D, 0, 1.0D);
        BASE_ARMOR_TOUGHNESS_REDUCTION = builder.defineInRange("BaseArmorToughnessReduction(%)", 0.2D, 0, 1.0D);
        GAINED_ARMOR_TOUGHNESS_REDUCTION_PER_LEVEL = builder.defineInRange("GainedArmorToughnessReductionPerLevel(%)", 0.1D, 0, 1.0D);
        MINUS_ARMOR_REDUCTION_AMOUNT = builder.defineInRange("MinArmorReductionAmount", 2D, 0D, Double.MAX_VALUE);
        builder.pop();
        INSTANCE = builder.build();
    }
}
