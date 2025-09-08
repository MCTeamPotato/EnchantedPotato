package me.kall.enchantedpotato.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class MendingMirrorConfig {
    public static final ModConfigSpec INSTANCE;
    public static final ModConfigSpec.DoubleValue REMAINING_DURABILITY;
    public static final ModConfigSpec.BooleanValue PLAY_SOUND;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("MendingMirror");
        REMAINING_DURABILITY = builder.defineInRange("RemainingDurability", 0.30, 0.01, 1.00);
        PLAY_SOUND = builder.define("PlaySoundOnRecovering", true);
        builder.pop();
        INSTANCE = builder.build();
    }
}
