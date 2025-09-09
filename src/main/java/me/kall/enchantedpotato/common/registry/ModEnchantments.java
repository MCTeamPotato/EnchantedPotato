package me.kall.enchantedpotato.common.registry;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import me.kall.enchantedpotato.EnchantedPotato;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModEnchantments {
    private static final EnchantmentRegister REGISTER = new EnchantmentRegister();

    public static final ResourceKey<Enchantment>
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

    private static final class EnchantmentRegister {
        public final Set<ResourceLocation> ENCHANTMENTS = new ObjectOpenHashSet<>();

        public @NotNull ResourceKey<Enchantment> register(String id) {
            ResourceLocation resourceLocation = EnchantedPotato.loc(id);
            ENCHANTMENTS.add(resourceLocation);
            return ResourceKey.create(Registries.ENCHANTMENT, resourceLocation);
        }
    }
}
