package me.kall.enchantedpotato.common.registry;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.common.enchantment.boots.*;
import me.kall.enchantedpotato.common.enchantment.chestplate.*;
import me.kall.enchantedpotato.common.enchantment.digger.*;
import me.kall.enchantedpotato.common.enchantment.helmet.OceanHued;
import me.kall.enchantedpotato.common.enchantment.leggings.Untouchable;
import me.kall.enchantedpotato.common.enchantment.weapon.*;
import me.kall.enchantedpotato.common.enchantment.weapon.bow.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, EnchantedPotato.MOD_ID);

    public static final RegistryObject<Enchantment>
            RUN_LIKE_HELL, BLACK_PARADE, GRACE_OF_GUNGNIR,
            PRESSURIZED_COLLAPSE, UNTOUCHABLE, GRACE_OF_GAIA,
            GUREN_NO_YUMIYA, LORA_TRAINER, RIPPLE_OF_DEATH,
            WONDER_EGG_PRIORITY, POISON_OF_THE_LAST_BREATH, DISSOLVE,
            MARK_FROM_THE_BENEATH, ARMOR_BREAKING, NATURE_BLESSING,
            CARESSING_MOONLIGHT, OCEAN_HUED, MINE_CARVE,
            LOTUS_IN_WATER, UNITE_STONES_OF_ALL, LAW_OF_INERTIA,
            MENDING_MIRROR, SACRED_RIFTWIND, FINAL_POWER, MERCY;

    static {
        RUN_LIKE_HELL = ENCHANTMENTS.register("run_like_hell", RunLikeHell::new);
        BLACK_PARADE = ENCHANTMENTS.register("black_parade", BlackParade::new);
        GRACE_OF_GUNGNIR = ENCHANTMENTS.register("grace_of_gungnir", GraceOfGungnir::new);
        PRESSURIZED_COLLAPSE = ENCHANTMENTS.register("pressurized_collapse", PressurizedCollapse::new);
        UNTOUCHABLE = ENCHANTMENTS.register("untouchable", Untouchable::new);
        GRACE_OF_GAIA = ENCHANTMENTS.register("grace_of_gaia", GraceOfGaia::new);
        GUREN_NO_YUMIYA = ENCHANTMENTS.register("guren_no_yumiya", GurenNoYumiya::new);
        LORA_TRAINER = ENCHANTMENTS.register("lora_trainer", LoRATrainer::new);
        RIPPLE_OF_DEATH = ENCHANTMENTS.register("ripple_of_death", RippleOfDeath::new);
        WONDER_EGG_PRIORITY = ENCHANTMENTS.register("wonder_egg_priority", WonderEggPriority::new);
        POISON_OF_THE_LAST_BREATH = ENCHANTMENTS.register("poison_of_the_last_breath", PoisonOfTheLastBreath::new);
        DISSOLVE = ENCHANTMENTS.register("dissolve", Dissolve::new);
        MARK_FROM_THE_BENEATH = ENCHANTMENTS.register("mark_from_the_beneath", MarkFromTheBeneath::new);
        ARMOR_BREAKING = ENCHANTMENTS.register("armor_breaking", ArmorBreaking::new);
        NATURE_BLESSING = ENCHANTMENTS.register("nature_blessing", NatureBlessing::new);
        CARESSING_MOONLIGHT = ENCHANTMENTS.register("caressing_moonlight", CaressingMoonlight::new);
        OCEAN_HUED = ENCHANTMENTS.register("ocean_hued", OceanHued::new);
        MINE_CARVE = ENCHANTMENTS.register("mine_carve", MineCarve::new);
        LOTUS_IN_WATER = ENCHANTMENTS.register("lotus_in_water", LotusInWater::new);
        UNITE_STONES_OF_ALL = ENCHANTMENTS.register("unite_stones_of_all", UniteStonesOfAll::new);
        LAW_OF_INERTIA = ENCHANTMENTS.register("law_of_inertia", LawOfInertia::new);
        MENDING_MIRROR = ENCHANTMENTS.register("mending_mirror", MendingMirror::new);
        SACRED_RIFTWIND = ENCHANTMENTS.register("sacred_riftwind", SacredRiftwind::new);
        FINAL_POWER = ENCHANTMENTS.register("final_power", FinalPower::new);
        MERCY = ENCHANTMENTS.register("mercy", Mercy::new);
    }

    public static void register(IEventBus bus) {
        ENCHANTMENTS.register(bus);
    }
}
