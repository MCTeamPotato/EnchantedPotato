package me.kall.enchantedpotato.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class OceanHuedConfig {
    public static final ModConfigSpec INSTANCE;

    public static final ModConfigSpec.IntValue BASE_COOLDOWN_TICKS, SAVED_COOLDOWN_TICKS_PER_LEVEL;
    public static final ModConfigSpec.DoubleValue
            BASE_RADIUS, GAINED_RADIUS_PER_LEVEL,
            BASE_MAX_DAMAGE_AMOUNT, GAINED_MAX_DAMAGE_AMOUNT_PER_LEVEL,
            BASE_HEALING_AMOUNT_REDUCTION, SAVED_HEALING_AMOUNT_REDUCTION_PER_LEVEL;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("OceanHued");
        BASE_COOLDOWN_TICKS = builder.defineInRange("BaseCooldownTicks", 1200, 0, Integer.MAX_VALUE);
        SAVED_COOLDOWN_TICKS_PER_LEVEL = builder.defineInRange("SavedCooldownTicksPerLevel", 200, 0, Integer.MAX_VALUE);
        BASE_RADIUS = builder.defineInRange("BaseRadius", 5.0D, 0, Double.MAX_VALUE);
        GAINED_RADIUS_PER_LEVEL = builder.defineInRange("GainedRadiusPerLevel", 1.5D, 0, Double.MAX_VALUE);
        BASE_MAX_DAMAGE_AMOUNT = builder.defineInRange("BaseMaxDamageAmount", 12D, 0, Double.MAX_VALUE);
        GAINED_MAX_DAMAGE_AMOUNT_PER_LEVEL = builder.defineInRange("GainedMaxDamageAmountPerLevel", 2D, 0, Double.MAX_VALUE);
        builder.comment("--------");
        builder.comment("Healing amount is reduced to transform to the  amount.");
        BASE_HEALING_AMOUNT_REDUCTION = builder.defineInRange("BaseHealingAmountReduction", 0.5D, 0, 1.0D);
        SAVED_HEALING_AMOUNT_REDUCTION_PER_LEVEL = builder.defineInRange("SavedHealingAmountReductionPerLevel", 0.1D, 0, 1.0D);
        builder.pop();
        INSTANCE = builder.build();
    }

    public static int getCoolDown(int enchantmentLevel) {
        return Math.max(BASE_COOLDOWN_TICKS.get() - SAVED_COOLDOWN_TICKS_PER_LEVEL.get() * (enchantmentLevel - 1), 0);
    }
}
