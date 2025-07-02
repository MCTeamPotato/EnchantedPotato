package me.kall.enchantedpotato.common.registry;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.common.attribute.ArmorBreakingDuration;
import me.kall.enchantedpotato.common.attribute.RunLikeHellCoolDown;
import me.kall.enchantedpotato.common.attribute.UntouchableCoolDown;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, EnchantedPotato.MOD_ID);

    public static final RegistryObject<Attribute> RUN_LIKE_HELL_COOLDOWN = ATTRIBUTES.register("run_like_hell_cooldown", RunLikeHellCoolDown::new);
    public static final RegistryObject<Attribute> UNTOUCHABLE_COOLDOWN = ATTRIBUTES.register("untouchable_cooldown", UntouchableCoolDown::new);
    public static final RegistryObject<Attribute> ARMOR_BREAKING_DURATION = ATTRIBUTES.register("armor_breaking_duration", ArmorBreakingDuration::new);

    public static void register(IEventBus bus) {
        ATTRIBUTES.register(bus);
    }
}
