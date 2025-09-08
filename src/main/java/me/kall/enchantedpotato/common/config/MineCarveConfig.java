package me.kall.enchantedpotato.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class MineCarveConfig {
    public static final ModConfigSpec INSTANCE;

    public static final ModConfigSpec.DoubleValue BASE_ARMOR_REDUCTION, GAINED_ARMOR_REDUCTION_PER_LEVEL;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("MineCarve");
        BASE_ARMOR_REDUCTION = builder.defineInRange("BaseArmorReductionAmount", 2.0, 0.0, Double.MAX_VALUE);
        GAINED_ARMOR_REDUCTION_PER_LEVEL = builder.defineInRange("GainedArmorReductionAmountPerLevel", 0.5, 0.0, Double.MAX_VALUE);
        builder.pop();
        INSTANCE = builder.build();
    }
}
