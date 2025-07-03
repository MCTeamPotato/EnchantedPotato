package me.kall.enchantedpotato.client.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {
    public static final ForgeConfigSpec INSTANCE;

    public static final ForgeConfigSpec.IntValue
            RUN_LIKE_HELL_POS_X, RUN_LIKE_HELL_POS_Y_OFFSET,
            UNTOUCHABLE_POS_X, UNTOUCHABLE_POS_Y_OFFSET,
            OCEAN_HUED_COUNTING_POS_X, OCEAN_HUED_COUNTING_POS_Y_OFFSET,
            OCEAN_HUED_COOLDOWN_POS_X, OCEAN_HUED_COOLDOWN_POS_Y_OFFSET,
            OCEAN_HUED_HEALING_AMOUNT_POS_X, OCEAN_HUED_HEALING_AMOUNT_POS_Y_OFFSET;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("EnchantedPotatoClientSettings");
        RUN_LIKE_HELL_POS_X = builder.defineInRange("RunLikeHellCooldownTextPosX", 10, 0, Integer.MAX_VALUE);
        RUN_LIKE_HELL_POS_Y_OFFSET = builder.defineInRange("RunLikeHellCooldownTextPosYOffset", 16, 0, Integer.MAX_VALUE);
        UNTOUCHABLE_POS_X = builder.defineInRange("UntouchableCooldownTextPosX", 10, 0, Integer.MAX_VALUE);
        UNTOUCHABLE_POS_Y_OFFSET = builder.defineInRange("UntouchableCooldownTextPosYOffset", 30, 0, Integer.MAX_VALUE);
        OCEAN_HUED_COUNTING_POS_X = builder.defineInRange("OceanHuedCountingPosX", 10, 0, Integer.MAX_VALUE);
        OCEAN_HUED_COUNTING_POS_Y_OFFSET = builder.defineInRange("OceanHuedCountingPosYOffSet", 44, 0, Integer.MAX_VALUE);
        OCEAN_HUED_COOLDOWN_POS_X = builder.defineInRange("OceanHuedCooldownPosX", 10, 0, Integer.MAX_VALUE);
        OCEAN_HUED_COOLDOWN_POS_Y_OFFSET = builder.defineInRange("OceanHuedCooldownPosYOffset", 44, 0, Integer.MAX_VALUE);
        OCEAN_HUED_HEALING_AMOUNT_POS_X = builder.defineInRange("OceanHuedHealingAmountPosX", 10, 0, Integer.MAX_VALUE);
        OCEAN_HUED_HEALING_AMOUNT_POS_Y_OFFSET = builder.defineInRange("OceanHuedHealingAmountPosYOffset", 44, 0, Integer.MAX_VALUE);
        builder.pop();
        INSTANCE = builder.build();
    }
}
