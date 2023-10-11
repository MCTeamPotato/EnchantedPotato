package com.teampotato.enchantedpotato.registry;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.enchantment.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public final class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, EarlySetupInitializer.MOD_ID);

    public static final RegistryObject<Enchantment> RUN_LIKE_HELL, BLACK_PARADE, GRACE_OF_GUNGNIR, THIS_IS_LEOPARD,
            DYING_OF_LIGHT, PRESSURIZED_COLLAPSE, ERROR_SPORE, UNTOUCHABLE, SHIELD_BLADE_COMMENDATION, GAIA_BLESSING,
            GUREN_NO_YUMIYA, BONE_SUCKALAKA, LORA_TRAINER, RIPPLE_OF_DEATH, POISON_OF_THE_LAST_BREATH,
            HUA_JIN, WONDER_EGG_PRIORITY, MARK_FROM_THE_BENEATH, ARMOR_BREAKING,
            BLESSING_OF_THE_NATURE, FLAME_CROSS, OCEAN_HUED, CARESSING_MOONLIGHT,
            TRUE_MAN, MINE_CARVE, UNITE_STONES_OF_ALL, WENDING_WATERS_SERENE_LOTUS, KING_OF_RIDING,
            LAW_OF_INERTIA, SHOOTING_STAR, TRIBACK, SNIPER, MUSICIAN, ENDER_ENDER, MULTI_LOAD,
            BLACK_ELEGANCE, WHITE_INNOCENCE, ARMS_DRUM, RICK_ROD, GOLDFISH_FIREWORKS,
            SOFT_TOUCH, COPPERHOLIC, WELL_OF_BLOOD, MISSILE;


    static {
        RUN_LIKE_HELL = ENCHANTMENTS.register(RunLikeHell.PATH, RunLikeHell::new);
        BLACK_PARADE = ENCHANTMENTS.register(BlackParade.PATH, BlackParade::new);
        GRACE_OF_GUNGNIR = ENCHANTMENTS.register(GraceOfGungnir.PATH, GraceOfGungnir::new);
        THIS_IS_LEOPARD = ENCHANTMENTS.register(ThisIsLeopard.PATH, ThisIsLeopard::new);
        DYING_OF_LIGHT = ENCHANTMENTS.register(DyingOfLight.PATH, DyingOfLight::new);
        PRESSURIZED_COLLAPSE = ENCHANTMENTS.register(PressurizedCollapse.PATH, PressurizedCollapse::new);
        ERROR_SPORE = ENCHANTMENTS.register(ErrorSpore.PATH, () -> {
            if (ErrorSpore.ERROR_SPORE == null) ErrorSpore.ERROR_SPORE = new ErrorSpore();
            return ErrorSpore.ERROR_SPORE;
        });
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
        TRUE_MAN = ENCHANTMENTS.register(TrueMan.PATH, TrueMan::new);
        MINE_CARVE = ENCHANTMENTS.register(MineCarve.PATH, MineCarve::new);
        UNITE_STONES_OF_ALL = ENCHANTMENTS.register(UniteStonesOfAll.PATH, UniteStonesOfAll::new);
        WENDING_WATERS_SERENE_LOTUS = ENCHANTMENTS.register(WendingWatersSereneLotus.PATH, WendingWatersSereneLotus::new);
        KING_OF_RIDING = ENCHANTMENTS.register(KingOfRiding.PATH, KingOfRiding::new);
        LAW_OF_INERTIA = ENCHANTMENTS.register(LawOfInertia.PATH, LawOfInertia::new);
        SHOOTING_STAR = ENCHANTMENTS.register(ShootingStar.PATH, ShootingStar::new);
        TRIBACK = ENCHANTMENTS.register(Triback.PATH, Triback::new);
        SNIPER = ENCHANTMENTS.register(Sniper.PATH, Sniper::new);
        MUSICIAN = ENCHANTMENTS.register(Musician.PATH, Musician::new);
        ENDER_ENDER = ENCHANTMENTS.register(EnderEnder.PATH, EnderEnder::new);
        MULTI_LOAD = ENCHANTMENTS.register(MultiLoad.PATH, MultiLoad::new);
        BLACK_ELEGANCE = ENCHANTMENTS.register(BlackElegance.PATH, BlackElegance::new);
        WHITE_INNOCENCE = ENCHANTMENTS.register(WhiteInnocence.PATH, WhiteInnocence::new);
        ARMS_DRUM = ENCHANTMENTS.register(ArmsDrum.PATH, ArmsDrum::new);
        RICK_ROD = ENCHANTMENTS.register(RickRod.PATH, RickRod::new);
        GOLDFISH_FIREWORKS = ENCHANTMENTS.register(GoldfishFireworks.PATH, GoldfishFireworks::new);
        SOFT_TOUCH = ENCHANTMENTS.register(SoftTouch.PATH, SoftTouch::new);
        COPPERHOLIC = ENCHANTMENTS.register(Copperholic.PATH, Copperholic::new);
        WELL_OF_BLOOD = ENCHANTMENTS.register(WellOfBlood.PATH, WellOfBlood::new);
        MISSILE = ENCHANTMENTS.register(Missile.PATH, Missile::new);
    }
}
