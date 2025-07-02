package me.kall.enchantedpotato.common.registry;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.common.enchantment.*;
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
            MARK_FROM_THE_BENEATH;

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
    }

    public static void register(IEventBus bus) {
        ENCHANTMENTS.register(bus);
    }
}
