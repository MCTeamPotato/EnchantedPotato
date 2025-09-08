package me.kall.enchantedpotato.common.config.disable;

import net.neoforged.neoforge.common.ModConfigSpec;

public class DisableConfig {
    public static final ModConfigSpec INSTANCE;

    public static final ModConfigSpec.BooleanValue
            RUN_LIKE_HELL, BLACK_PARADE, GRACE_OF_GUNGNIR,
            PRESSURIZED_COLLAPSE, UNTOUCHABLE, GRACE_OF_GAIA,
            GUREN_NO_YUMIYA, LORA_TRAINER, RIPPLE_OF_DEATH,
            WONDER_EGG_PRIORITY, POISON_OF_THE_LAST_BREATH, DISSOLVE,
            MARK_FROM_THE_BENEATH, ARMOR_BREAKING, NATURE_BLESSING,
            CARESSING_MOONLIGHT, OCEAN_HUED, MINE_CARVE,
            LOTUS_IN_WATER, UNITE_STONES_OF_ALL, LAW_OF_INERTIA,
            MENDING_MIRROR, SACRED_RIFTWIND, FINAL_POWER,
            MERCY, SPACE_LEAPFROG;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("DisableConfig");
        RUN_LIKE_HELL = builder.define("DisableRunLikeHell", false);
        BLACK_PARADE = builder.define("DisableBlackParade", false);
        GRACE_OF_GUNGNIR = builder.define("DisableGraceOfGungnir", false);
        PRESSURIZED_COLLAPSE = builder.define("DisablePressurizedCollapse", false);
        UNTOUCHABLE = builder.define("DisableUntouchable", false);
        GRACE_OF_GAIA = builder.define("DisableGraceOfGaia", false);
        GUREN_NO_YUMIYA = builder.define("DisableGurenNoYumiya", false);
        LORA_TRAINER = builder.define("DisableLoRATrainer", false);
        RIPPLE_OF_DEATH = builder.define("DisableRippleOfDeath", false);
        WONDER_EGG_PRIORITY = builder.define("DisableWonderEggPriority", false);
        POISON_OF_THE_LAST_BREATH = builder.define("DisablePoisonOfTheLastBreath", false);
        DISSOLVE = builder.define("DisableDissolve", false);
        MARK_FROM_THE_BENEATH = builder.define("DisableMarkFromTheBeneath", false);
        ARMOR_BREAKING = builder.define("DisableArmorBreaking", false);
        NATURE_BLESSING = builder.define("DisableNatureBlessing", false);
        CARESSING_MOONLIGHT = builder.define("DisableCaressingMoonlight", false);
        OCEAN_HUED = builder.define("DisableOceanHued", false);
        MINE_CARVE = builder.define("DisableMineCarve", false);
        LOTUS_IN_WATER = builder.define("DisableLotusInWater", false);
        UNITE_STONES_OF_ALL = builder.define("DisableUniteStonesOfAll", false);
        LAW_OF_INERTIA = builder.define("DisableLawOfInertia", false);
        MENDING_MIRROR = builder.define("DisableMendingMirror", false);
        SACRED_RIFTWIND = builder.define("DisableSacredRiftWind", false);
        FINAL_POWER = builder.define("DisableFinalPower", false);
        MERCY = builder.define("DisableMercy", false);
        SPACE_LEAPFROG = builder.define("DisableSpaceLeapfrog", false);
        builder.pop();
        INSTANCE = builder.build();
    }
}
