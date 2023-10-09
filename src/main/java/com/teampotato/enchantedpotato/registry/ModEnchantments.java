package com.teampotato.enchantedpotato.registry;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.enchantment.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public final class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, EarlySetupInitializer.MOD_ID);

    public static final RegistryObject<Enchantment> RUN_LIKE_HELL;
    public static final RegistryObject<Enchantment> BLACK_PARADE;
    public static final RegistryObject<Enchantment> GRACE_OF_GUNGNIR;
    public static final RegistryObject<Enchantment> THIS_IS_LEOPARD;
    public static final RegistryObject<Enchantment> DYING_OF_LIGHT;
    public static final RegistryObject<Enchantment> PRESSURIZED_COLLAPSE;
    public static final RegistryObject<Enchantment> ERROR_SPORE;
    public static final RegistryObject<Enchantment> UNTOUCHABLE;
    public static final RegistryObject<Enchantment> SHIELD_BLADE_COMMENDATION;
    public static final RegistryObject<Enchantment> GAIA_BLESSING;
    public static final RegistryObject<Enchantment> GUREN_NO_YUMIYA;
    public static final RegistryObject<Enchantment> BONE_SUCKALAKA;
    public static final RegistryObject<Enchantment> LORA_TRAINER;
    public static final RegistryObject<Enchantment> RIPPLE_OF_DEATH;
    public static final RegistryObject<Enchantment> POISON_OF_THE_LAST_BREATH;
    public static final RegistryObject<Enchantment> HUA_JIN;
    public static final RegistryObject<Enchantment> WONDER_EGG_PRIORITY;
    public static final RegistryObject<Enchantment> MARK_FROM_THE_BENEATH;
    public static final RegistryObject<Enchantment> ARMOR_BREAKING;
    public static final RegistryObject<Enchantment> BLESSING_OF_THE_NATURE;
    public static final RegistryObject<Enchantment> FLAME_CROSS;
    public static final RegistryObject<Enchantment> OCEAN_HUED;
    public static final RegistryObject<Enchantment> CARESSING_MOONLIGHT;

    static {
        RUN_LIKE_HELL = ENCHANTMENTS.register(RunLikeHell.PATH, RunLikeHell::new);
        BLACK_PARADE = ENCHANTMENTS.register(BlackParade.PATH, BlackParade::new);
        GRACE_OF_GUNGNIR = ENCHANTMENTS.register(GraceOfGungnir.PATH, GraceOfGungnir::new);
        THIS_IS_LEOPARD = ENCHANTMENTS.register(ThisIsLeopard.PATH, ThisIsLeopard::new);
        DYING_OF_LIGHT = ENCHANTMENTS.register(DyingOfLight.PATH, DyingOfLight::new);
        PRESSURIZED_COLLAPSE = ENCHANTMENTS.register(PressurizedCollapse.PATH, PressurizedCollapse::new);
        ERROR_SPORE = ENCHANTMENTS.register(ErrorSpore.PATH, () -> ErrorSpore.ERROR_SPORE);
        UNTOUCHABLE = ENCHANTMENTS.register(Untouchable.PATH, Untouchable::new);
        SHIELD_BLADE_COMMENDATION = ENCHANTMENTS.register(ShieldBladeCommendation.PATH, ShieldBladeCommendation::new);
        GAIA_BLESSING = ENCHANTMENTS.register(GaiaBlessing.PATH, GaiaBlessing::new);
        GUREN_NO_YUMIYA = ENCHANTMENTS.register(GurenNoYumiya.PATH, GurenNoYumiya::new);
        BONE_SUCKALAKA = ENCHANTMENTS.register(BoneSuckalaka.PATH, BoneSuckalaka::new);
        LORA_TRAINER = ENCHANTMENTS.register(LoRATrainer.PATH, LoRATrainer::new);
        RIPPLE_OF_DEATH = ENCHANTMENTS.register(RippleOfDeath.PATH, RippleOfDeath::new);
        POISON_OF_THE_LAST_BREATH = ENCHANTMENTS.register(PoisonOfTheLastBreath.PATH, PoisonOfTheLastBreath::new);
        HUA_JIN = ENCHANTMENTS.register(HuaJin.PATH, HuaJin::new);
        WONDER_EGG_PRIORITY = ENCHANTMENTS.register(WonderEggPriority.PATH, WonderEggPriority::new);
        MARK_FROM_THE_BENEATH = ENCHANTMENTS.register(MarkFromTheBeneath.PATH, MarkFromTheBeneath::new);
        ARMOR_BREAKING = ENCHANTMENTS.register(ArmorBreaking.PATH, ArmorBreaking::new);
        BLESSING_OF_THE_NATURE = ENCHANTMENTS.register(BlessingOfTheNature.PATH, BlessingOfTheNature::new);
        FLAME_CROSS = ENCHANTMENTS.register(FlameCross.PATH, FlameCross::new);
        OCEAN_HUED = ENCHANTMENTS.register(OceanHued.PATH, OceanHued::new);
        CARESSING_MOONLIGHT = ENCHANTMENTS.register(CaressingMoonlight.PATH, CaressingMoonlight::new);
    }
}
