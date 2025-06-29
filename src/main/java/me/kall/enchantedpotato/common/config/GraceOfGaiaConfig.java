package me.kall.enchantedpotato.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class GraceOfGaiaConfig {
    public static final ForgeConfigSpec INSTANCE;

    public static final ForgeConfigSpec.IntValue BASE_VALID_Y, GAINED_Y_PER_LEVEL, MAX_DMG_REDUCTION_PERCENT;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("GraceOfGaia");
        BASE_VALID_Y = builder.defineInRange("BaseValidYForEnchantmentTakingEffect", -40, -64, Integer.MAX_VALUE);
        GAINED_Y_PER_LEVEL = builder.defineInRange("GainedYPerLevel", 15, 0, Integer.MAX_VALUE);
        MAX_DMG_REDUCTION_PERCENT = builder.defineInRange("MaxDamageReductionPercent(%)", 60, 0, 100);
        builder.pop();
        INSTANCE = builder.build();
    }

    public static float getMaxDamageReduction() {
        return MAX_DMG_REDUCTION_PERCENT.get().floatValue() / 100.00F;
    }
}
