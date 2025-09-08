package me.kall.enchantedpotato.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class LoRATrainerConfig {
    public static final ModConfigSpec INSTANCE;
    public static final ModConfigSpec.IntValue BASE_KILL_COUNT, SAVED_KILL_COUNT_PER_LEVEL;

    public static final ModConfigSpec.DoubleValue BASE_DAMAGE_BONUS, GAINED_DAMAGE_BONUS_PER_LEVEL;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("LoRATrainer");
        BASE_KILL_COUNT = builder.defineInRange("BaseKillCount", 100, 0, Integer.MAX_VALUE);
        SAVED_KILL_COUNT_PER_LEVEL = builder.defineInRange("SavedKillCountPerLevel", 10, 0, Integer.MAX_VALUE);
        BASE_DAMAGE_BONUS = builder.defineInRange("BaseDamageBonus", 0.2, 0, Double.MAX_VALUE);
        GAINED_DAMAGE_BONUS_PER_LEVEL = builder.defineInRange("GainedDamageBonusPerLevel", 0.3, 0, Double.MAX_VALUE);
        builder.pop();
        INSTANCE = builder.build();
    }
}
