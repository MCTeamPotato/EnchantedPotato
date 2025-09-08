package me.kall.enchantedpotato.common.config.disable;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.common.api.JsonConfig;
import net.neoforged.fml.loading.FMLLoader;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class DisableConfig {
    public static final DisableEntry
            RUN_LIKE_HELL, BLACK_PARADE, GRACE_OF_GUNGNIR,
            PRESSURIZED_COLLAPSE, UNTOUCHABLE, GRACE_OF_GAIA,
            GUREN_NO_YUMIYA, LORA_TRAINER, RIPPLE_OF_DEATH,
            WONDER_EGG_PRIORITY, POISON_OF_THE_LAST_BREATH, DISSOLVE,
            MARK_FROM_THE_BENEATH, ARMOR_BREAKING, NATURE_BLESSING,
            CARESSING_MOONLIGHT, OCEAN_HUED, MINE_CARVE,
            LOTUS_IN_WATER, UNITE_STONES_OF_ALL, LAW_OF_INERTIA,
            MENDING_MIRROR, SACRED_RIFTWIND, FINAL_POWER,
            MERCY, SPACE_LEAPFROG;

    private static final JsonConfig INSTANCE;

    static {
        INSTANCE = JsonConfig.create(FMLLoader.getGamePath().resolve("config").resolve(EnchantedPotato.MOD_ID), "1.0.0");

        for (DisableKeys k : DisableKeys.values()) {
            INSTANCE.put(k.key, false);
        }

        INSTANCE.initialize();

        Definer definer = new Definer();
        RUN_LIKE_HELL = definer.define(DisableKeys.RUN_LIKE_HELL);
        BLACK_PARADE = definer.define(DisableKeys.BLACK_PARADE);
        GRACE_OF_GUNGNIR = definer.define(DisableKeys.GRACE_OF_GUNGNIR);
        PRESSURIZED_COLLAPSE = definer.define(DisableKeys.PRESSURIZED_COLLAPSE);
        UNTOUCHABLE = definer.define(DisableKeys.UNTOUCHABLE);
        GRACE_OF_GAIA = definer.define(DisableKeys.GRACE_OF_GAIA);
        GUREN_NO_YUMIYA = definer.define(DisableKeys.GUREN_NO_YUMIYA);
        LORA_TRAINER = definer.define(DisableKeys.LORA_TRAINER);
        RIPPLE_OF_DEATH = definer.define(DisableKeys.RIPPLE_OF_DEATH);
        WONDER_EGG_PRIORITY = definer.define(DisableKeys.WONDER_EGG_PRIORITY);
        POISON_OF_THE_LAST_BREATH = definer.define(DisableKeys.POISON_OF_THE_LAST_BREATH);
        DISSOLVE = definer.define(DisableKeys.DISSOLVE);
        MARK_FROM_THE_BENEATH = definer.define(DisableKeys.MARK_FROM_THE_BENEATH);
        ARMOR_BREAKING = definer.define(DisableKeys.ARMOR_BREAKING);
        NATURE_BLESSING = definer.define(DisableKeys.NATURE_BLESSING);
        CARESSING_MOONLIGHT = definer.define(DisableKeys.CARESSING_MOONLIGHT);
        OCEAN_HUED = definer.define(DisableKeys.OCEAN_HUED);
        MINE_CARVE = definer.define(DisableKeys.MINE_CARVE);
        LOTUS_IN_WATER = definer.define(DisableKeys.LOTUS_IN_WATER);
        UNITE_STONES_OF_ALL = definer.define(DisableKeys.UNITE_STONES_OF_ALL);
        LAW_OF_INERTIA = definer.define(DisableKeys.LAW_OF_INERTIA);
        MENDING_MIRROR = definer.define(DisableKeys.MENDING_MIRROR);
        SACRED_RIFTWIND = definer.define(DisableKeys.SACRED_RIFTWIND);
        FINAL_POWER = definer.define(DisableKeys.FINAL_POWER);
        MERCY = definer.define(DisableKeys.MERCY);
        SPACE_LEAPFROG = definer.define(DisableKeys.SPACE_LEAPFROG);
    }

    private static final class Definer {
        @Contract("_ -> new")
        public @NotNull DisableEntry define(@NotNull DisableKeys key) {
            return new DisableEntry(key.key);
        }
    }

    public static final class DisableEntry {
        private final String key;

        public DisableEntry(String key) {
            this.key = key;
        }

        public boolean get() {
            return INSTANCE.getBoolean(this.key);
        }
    }

    public enum DisableKeys {
        RUN_LIKE_HELL("DisableRunLikeHell"),
        BLACK_PARADE("DisableBlackParade"),
        GRACE_OF_GUNGNIR("DisableGraceOfGungnir"),
        PRESSURIZED_COLLAPSE("DisablePressurizedCollapse"),
        UNTOUCHABLE("DisableUntouchable"),
        GRACE_OF_GAIA("DisableGraceOfGaia"),
        GUREN_NO_YUMIYA("DisableGurenNoYumiya"),
        LORA_TRAINER("DisableLoRATrainer"),
        RIPPLE_OF_DEATH("DisableRippleOfDeath"),
        WONDER_EGG_PRIORITY("DisableWonderEggPriority"),
        POISON_OF_THE_LAST_BREATH("DisablePoisonOfTheLastBreath"),
        DISSOLVE("DisableDissolve"),
        MARK_FROM_THE_BENEATH("DisableMarkFromTheBeneath"),
        ARMOR_BREAKING("DisableArmorBreaking"),
        NATURE_BLESSING("DisableNatureBlessing"),
        CARESSING_MOONLIGHT("DisableCaressingMoonlight"),
        OCEAN_HUED("DisableOceanHued"),
        MINE_CARVE("DisableMineCarve"),
        LOTUS_IN_WATER("DisableLotusInWater"),
        UNITE_STONES_OF_ALL("DisableUniteStonesOfAll"),
        LAW_OF_INERTIA("DisableLawOfInertia"),
        MENDING_MIRROR("DisableMendingMirror"),
        SACRED_RIFTWIND("DisableSacredRiftWind"),
        FINAL_POWER("DisableFinalPower"),
        MERCY("DisableMercy"),
        SPACE_LEAPFROG("DisableSpaceLeapfrog");

        public final String key;

        DisableKeys(String key) {
            this.key = key;
        }
    }

    public static void init() {}
}
