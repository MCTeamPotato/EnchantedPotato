package me.kall.enchantedpotato.client.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {
    public static final ForgeConfigSpec INSTANCE;

    public static final ForgeConfigSpec.IntValue RUN_LIKE_HELL_POS_X, RUN_LIKE_HELL_POS_Y_OFFSET, UNTOUCHABLE_POS_X, UNTOUCHABLE_POS_Y_OFFSET;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("EnchantedPotatoClientSettings");
        RUN_LIKE_HELL_POS_X = builder.defineInRange("RunLikeHellCooldownTextPosX", 10, 0, Integer.MAX_VALUE);
        RUN_LIKE_HELL_POS_Y_OFFSET = builder.defineInRange("RunLikeHellCooldownTextPosYOffset", 30, 0, Integer.MAX_VALUE);
        UNTOUCHABLE_POS_X = builder.defineInRange("UntouchableCooldownTextPosX", 10, 0, Integer.MAX_VALUE);
        UNTOUCHABLE_POS_Y_OFFSET = builder.defineInRange("UntouchableCooldownTextPosYOffset", 44, 0, Integer.MAX_VALUE);
        builder.pop();
        INSTANCE = builder.build();
    }
}
