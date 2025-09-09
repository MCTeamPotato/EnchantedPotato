package me.kall.enchantedpotato.common.registry;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.common.enchantment.boots.*;
import me.kall.enchantedpotato.common.enchantment.chestplate.Dissolve;
import me.kall.enchantedpotato.common.enchantment.chestplate.NatureBlessing;
import me.kall.enchantedpotato.common.enchantment.chestplate.WonderEggPriority;
import me.kall.enchantedpotato.common.enchantment.digger.MarkFromTheBeneath;
import me.kall.enchantedpotato.common.enchantment.digger.MineCarve;
import me.kall.enchantedpotato.common.enchantment.digger.UniteStonesOfAll;
import me.kall.enchantedpotato.common.enchantment.helmet.OceanHued;
import me.kall.enchantedpotato.common.enchantment.leggings.Untouchable;
import me.kall.enchantedpotato.common.enchantment.weapon.*;
import me.kall.enchantedpotato.common.enchantment.weapon.bow.GraceOfGungnir;
import me.kall.enchantedpotato.common.enchantment.weapon.bow.GurenNoYumiya;
import me.kall.enchantedpotato.common.enchantment.weapon.bow.PressurizedCollapse;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;

public class ModEnchantments {
    private static final EnchantmentRegister REGISTER = new EnchantmentRegister();

    public static final ResourceKey<Enchantment> RUN_LIKE_HELL;
    public static final ResourceKey<Enchantment> BLACK_PARADE;
    public static final ResourceKey<Enchantment> GRACE_OF_GUNGNIR;
    public static final ResourceKey<Enchantment> PRESSURIZED_COLLAPSE;
    public static final ResourceKey<Enchantment> UNTOUCHABLE;
    public static final ResourceKey<Enchantment> GRACE_OF_GAIA;
    public static final ResourceKey<Enchantment> GUREN_NO_YUMIYA;
    public static final ResourceKey<Enchantment> LORA_TRAINER;
    public static final ResourceKey<Enchantment> RIPPLE_OF_DEATH;
    public static final ResourceKey<Enchantment> WONDER_EGG_PRIORITY;
    public static final ResourceKey<Enchantment> POISON_OF_THE_LAST_BREATH;
    public static final ResourceKey<Enchantment> DISSOLVE;
    public static final ResourceKey<Enchantment> MARK_FROM_THE_BENEATH;
    public static final ResourceKey<Enchantment> ARMOR_BREAKING;
    public static final ResourceKey<Enchantment> NATURE_BLESSING;
    public static final ResourceKey<Enchantment> CARESSING_MOONLIGHT;
    public static final ResourceKey<Enchantment> OCEAN_HUED;
    public static final ResourceKey<Enchantment> MINE_CARVE;
    public static final ResourceKey<Enchantment> LOTUS_IN_WATER;
    public static final ResourceKey<Enchantment> UNITE_STONES_OF_ALL;
    public static final ResourceKey<Enchantment> LAW_OF_INERTIA;
    public static final ResourceKey<Enchantment> MENDING_MIRROR;
    public static final ResourceKey<Enchantment> SACRED_RIFTWIND;
    public static final ResourceKey<Enchantment> FINAL_POWER;
    public static final ResourceKey<Enchantment> MERCY;
    public static final ResourceKey<Enchantment> SPACE_LEAPFROG;

    static {
        RUN_LIKE_HELL = REGISTER.register("run_like_hell");
        BLACK_PARADE = REGISTER.register("black_parade");
        GRACE_OF_GUNGNIR = REGISTER.register("grace_of_gungnir");
        PRESSURIZED_COLLAPSE = REGISTER.register("pressurized_collapse");
        UNTOUCHABLE = REGISTER.register("untouchable");
        GRACE_OF_GAIA = REGISTER.register("grace_of_gaia");
        GUREN_NO_YUMIYA = REGISTER.register("guren_no_yumiya");
        LORA_TRAINER = REGISTER.register("lora_trainer");
        RIPPLE_OF_DEATH = REGISTER.register("ripple_of_death");
        WONDER_EGG_PRIORITY = REGISTER.register("wonder_egg_priority");
        POISON_OF_THE_LAST_BREATH = REGISTER.register("poison_of_the_last_breath");
        DISSOLVE = REGISTER.register("dissolve");
        MARK_FROM_THE_BENEATH = REGISTER.register("mark_from_the_beneath");
        ARMOR_BREAKING = REGISTER.register("armor_breaking");
        NATURE_BLESSING = REGISTER.register("nature_blessing");
        CARESSING_MOONLIGHT = REGISTER.register("caressing_moonlight");
        OCEAN_HUED = REGISTER.register("ocean_hued");
        MINE_CARVE = REGISTER.register("mine_carve");
        LOTUS_IN_WATER = REGISTER.register("lotus_in_water");
        UNITE_STONES_OF_ALL = REGISTER.register("unite_stones_of_all");
        LAW_OF_INERTIA = REGISTER.register("law_of_inertia");
        MENDING_MIRROR = REGISTER.register("mending_mirror");
        SACRED_RIFTWIND = REGISTER.register("sacred_riftwind");
        FINAL_POWER = REGISTER.register("final_power");
        MERCY = REGISTER.register("mercy");
        SPACE_LEAPFROG = REGISTER.register("space_leapfrog");
    }

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Item> items = context.lookup(Registries.ITEM);

        BlackParade.INSTANCE.register(context, items);
        GraceOfGaia.INSTANCE.register(context, items);
        LotusInWater.INSTANCE.register(context, items);
        RunLikeHell.INSTANCE.register(context, items);
        SpaceLeapfrog.INSTANCE.register(context, items);
        Dissolve.INSTANCE.register(context, items);
        NatureBlessing.INSTANCE.register(context, items);
        WonderEggPriority.INSTANCE.register(context, items);
        MarkFromTheBeneath.INSTANCE.register(context, items);
        MineCarve.INSTANCE.register(context, items);
        UniteStonesOfAll.INSTANCE.register(context, items);
        OceanHued.INSTANCE.register(context, items);
        Untouchable.INSTANCE.register(context, items);
        GraceOfGungnir.INSTANCE.register(context, items);
        GurenNoYumiya.INSTANCE.register(context, items);
        PressurizedCollapse.INSTANCE.register(context, items);
        ArmorBreaking.INSTANCE.register(context, items);
        CaressingMoonlight.INSTANCE.register(context, items);
        FinalPower.INSTANCE.register(context, items);
        LawOfInertia.INSTANCE.register(context, items);
        LoRATrainer.INSTANCE.register(context, items);
        MendingMirror.INSTANCE.register(context, items);
        Mercy.INSTANCE.register(context, items);
        PoisonOfTheLastBreath.INSTANCE.register(context, items);
        RippleOfDeath.INSTANCE.register(context, items);
        SacredRiftwind.INSTANCE.register(context, items);
    }

    private static final class EnchantmentRegister {
        public @NotNull ResourceKey<Enchantment> register(String id) {
            return ResourceKey.create(Registries.ENCHANTMENT, EnchantedPotato.loc(id));
        }
    }
}
