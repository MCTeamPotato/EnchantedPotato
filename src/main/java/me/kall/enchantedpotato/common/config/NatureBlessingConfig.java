package me.kall.enchantedpotato.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class NatureBlessingConfig {
    public static final ModConfigSpec INSTANCE;
    public static final ModConfigSpec.DoubleValue BASE_HEAL_AMOUNT, GAINED_HEAL_AMOUNT_PER_LEVEL, BASE_RADIUS, GAINED_RADIUS_PER_LEVEL, MIN_DAMAGE, MAX_DAMAGE, DAMAGE_BONUS_PER_LEVEL, BASE_KNOCKBACK_STRENGTH, GAINED_KNOCKBACK_STRENGTH_PER_LEVEL;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("NatureBlessing");
        BASE_HEAL_AMOUNT = builder.defineInRange("BaseHealAmount", 3.00, 0, Double.MAX_VALUE);
        GAINED_HEAL_AMOUNT_PER_LEVEL = builder.defineInRange("GainedHealAmountPerLevel", 1.00, 0, Double.MAX_VALUE);
        BASE_RADIUS = builder.defineInRange("BaseRadius", 3.00, 0, Double.MAX_VALUE);
        GAINED_RADIUS_PER_LEVEL = builder.defineInRange("GainedRadiusPerLevel", 1.00, 0, Double.MAX_VALUE);
        MIN_DAMAGE = builder.defineInRange("MinDamage", 1, 0, Double.MAX_VALUE);
        MAX_DAMAGE = builder.defineInRange("MaxDamage", 3, 0, Double.MAX_VALUE);
        DAMAGE_BONUS_PER_LEVEL = builder.defineInRange("DamageBaseBonusPerLevel", 2, 0, Double.MAX_VALUE);
        BASE_KNOCKBACK_STRENGTH = builder.defineInRange("BaseKnockbackStrength", 0.5, 0, Double.MAX_VALUE);
        GAINED_KNOCKBACK_STRENGTH_PER_LEVEL = builder.defineInRange("GainedKnockbackStrengthPerLevel", 0.2, 0, Double.MAX_VALUE);
        builder.pop();
        INSTANCE = builder.build();
    }
}
