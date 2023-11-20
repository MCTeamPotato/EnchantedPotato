package com.teampotato.enchantedpotato.registry;

import com.teampotato.enchantedpotato.enchantment.armor.chest.BlessingOfTheNature;
import com.teampotato.enchantedpotato.enchantment.armor.chest.CaressingMoonlight;
import com.teampotato.enchantedpotato.enchantment.armor.chest.HuaJin;
import com.teampotato.enchantedpotato.enchantment.armor.chest.WellOfBlood;
import com.teampotato.enchantedpotato.enchantment.armor.feet.*;
import com.teampotato.enchantedpotato.enchantment.armor.head.DyingOfLight;
import com.teampotato.enchantedpotato.enchantment.armor.head.FlameCross;
import com.teampotato.enchantedpotato.enchantment.armor.head.WonderEggPriority;
import com.teampotato.enchantedpotato.enchantment.armor.legs.*;
import com.teampotato.enchantedpotato.enchantment.bow.GraceOfGungnir;
import com.teampotato.enchantedpotato.enchantment.bow.GurenNoYumiya;
import com.teampotato.enchantedpotato.enchantment.bow.PressurizedCollapse;
import com.teampotato.enchantedpotato.enchantment.bow.TrueMan;
import com.teampotato.enchantedpotato.enchantment.breakable.elytra.ShootingStar;
import com.teampotato.enchantedpotato.enchantment.breakable.fishing_rod.RickRod;
import com.teampotato.enchantedpotato.enchantment.breakable.shield.ArmsDrum;
import com.teampotato.enchantedpotato.enchantment.breakable.shield.BoneSuckalaka;
import com.teampotato.enchantedpotato.enchantment.breakable.shield.ShieldBladeCommendation;
import com.teampotato.enchantedpotato.enchantment.crossbow.GoldfishFireworks;
import com.teampotato.enchantedpotato.enchantment.crossbow.MultiLoad;
import com.teampotato.enchantedpotato.enchantment.crossbow.Sniper;
import com.teampotato.enchantedpotato.enchantment.digger.MarkFromTheBeneath;
import com.teampotato.enchantedpotato.enchantment.digger.MineCarve;
import com.teampotato.enchantedpotato.enchantment.digger.UniteStonesOfAll;
import com.teampotato.enchantedpotato.enchantment.trident.Missile;
import com.teampotato.enchantedpotato.enchantment.trident.Triback;
import com.teampotato.enchantedpotato.enchantment.weapon.*;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public final class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT, EarlySetupInitializer.MOD_ID);

    public static final DeferredHolder<Enchantment, RunLikeHell> RUN_LIKE_HELL;
    public static final DeferredHolder<Enchantment, BlackParade> BLACK_PARADE;
    public static final DeferredHolder<Enchantment, GraceOfGungnir> GRACE_OF_GUNGNIR;
    public static final DeferredHolder<Enchantment, ThisIsLeopard> THIS_IS_LEOPARD;
    public static final DeferredHolder<Enchantment, DyingOfLight> DYING_OF_LIGHT;
    public static final DeferredHolder<Enchantment, PressurizedCollapse> PRESSURIZED_COLLAPSE;
    public static final DeferredHolder<Enchantment, ErrorSpore> ERROR_SPORE;
    public static final DeferredHolder<Enchantment, Untouchable> UNTOUCHABLE;
    public static final DeferredHolder<Enchantment, ShieldBladeCommendation> SHIELD_BLADE_COMMENDATION;
    public static final DeferredHolder<Enchantment, GaiaBlessing> GAIA_BLESSING;
    public static final DeferredHolder<Enchantment, GurenNoYumiya> GUREN_NO_YUMIYA;
    public static final DeferredHolder<Enchantment, BoneSuckalaka> BONE_SUCKALAKA;
    public static final DeferredHolder<Enchantment, LoRATrainer> LORA_TRAINER;
    public static final DeferredHolder<Enchantment, RippleOfDeath> RIPPLE_OF_DEATH;
    public static final DeferredHolder<Enchantment, PoisonOfTheLastBreath> POISON_OF_THE_LAST_BREATH;
    public static final DeferredHolder<Enchantment, HuaJin> HUA_JIN;
    public static final DeferredHolder<Enchantment, WonderEggPriority> WONDER_EGG_PRIORITY;
    public static final DeferredHolder<Enchantment, MarkFromTheBeneath> MARK_FROM_THE_BENEATH;
    public static final DeferredHolder<Enchantment, ArmorBreaking> ARMOR_BREAKING;
    public static final DeferredHolder<Enchantment, BlessingOfTheNature> BLESSING_OF_THE_NATURE;
    public static final DeferredHolder<Enchantment, FlameCross> FLAME_CROSS;
    public static final DeferredHolder<Enchantment, OceanHued> OCEAN_HUED;
    public static final DeferredHolder<Enchantment, CaressingMoonlight> CARESSING_MOONLIGHT;
    public static final DeferredHolder<Enchantment, TrueMan> TRUE_MAN;
    public static final DeferredHolder<Enchantment, MineCarve> MINE_CARVE;
    public static final DeferredHolder<Enchantment, UniteStonesOfAll> UNITE_STONES_OF_ALL;
    public static final DeferredHolder<Enchantment, WendingWatersSereneLotus> WENDING_WATERS_SERENE_LOTUS;
    public static final DeferredHolder<Enchantment, KingOfRiding> KING_OF_RIDING;
    public static final DeferredHolder<Enchantment, LawOfInertia> LAW_OF_INERTIA;
    public static final DeferredHolder<Enchantment, ShootingStar> SHOOTING_STAR;
    public static final DeferredHolder<Enchantment, Triback> TRIBACK;
    public static final DeferredHolder<Enchantment, Sniper> SNIPER;
    public static final DeferredHolder<Enchantment, Musician> MUSICIAN;
    public static final DeferredHolder<Enchantment, EnderEnder> ENDER_ENDER;
    public static final DeferredHolder<Enchantment, MultiLoad> MULTI_LOAD;
    public static final DeferredHolder<Enchantment, BlackElegance> BLACK_ELEGANCE;
    public static final DeferredHolder<Enchantment, WhiteInnocence> WHITE_INNOCENCE;
    public static final DeferredHolder<Enchantment, ArmsDrum> ARMS_DRUM;
    public static final DeferredHolder<Enchantment, RickRod> RICK_ROD;
    public static final DeferredHolder<Enchantment, GoldfishFireworks> GOLDFISH_FIREWORKS;
    public static final DeferredHolder<Enchantment, SoftTouch> SOFT_TOUCH;
    public static final DeferredHolder<Enchantment, Copperholic> COPPERHOLIC;
    public static final DeferredHolder<Enchantment, WellOfBlood> WELL_OF_BLOOD;
    public static final DeferredHolder<Enchantment, Missile> MISSILE;


    static {
        RUN_LIKE_HELL = ENCHANTMENTS.register(RunLikeHell.PATH, RunLikeHell::new);
        BLACK_PARADE = ENCHANTMENTS.register(BlackParade.PATH, BlackParade::new);
        GRACE_OF_GUNGNIR = ENCHANTMENTS.register(GraceOfGungnir.PATH, GraceOfGungnir::new);
        THIS_IS_LEOPARD = ENCHANTMENTS.register(ThisIsLeopard.PATH, ThisIsLeopard::new);
        DYING_OF_LIGHT = ENCHANTMENTS.register(DyingOfLight.PATH, DyingOfLight::new);
        PRESSURIZED_COLLAPSE = ENCHANTMENTS.register(PressurizedCollapse.PATH, PressurizedCollapse::new);
        ERROR_SPORE = ENCHANTMENTS.register(ErrorSpore.PATH, ErrorSpore::new);
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
