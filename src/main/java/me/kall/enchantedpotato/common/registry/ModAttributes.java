package me.kall.enchantedpotato.common.registry;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.common.attribute.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

public class ModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, EnchantedPotato.MOD_ID);

    public static final DeferredHolder<Attribute, Attribute> RUN_LIKE_HELL_COOLDOWN = ATTRIBUTES.register("run_like_hell_cooldown", RunLikeHellCoolDown::new);
    public static final DeferredHolder<Attribute, Attribute> UNTOUCHABLE_COOLDOWN = ATTRIBUTES.register("untouchable_cooldown", UntouchableCoolDown::new);
    public static final DeferredHolder<Attribute, Attribute> ARMOR_BREAKING_DURATION = ATTRIBUTES.register("armor_breaking_duration", ArmorBreakingDuration::new);
    public static final DeferredHolder<Attribute, Attribute> OCEAN_HUED_COUNTING = ATTRIBUTES.register("ocean_hued_counting", OceanHuedCounting::new);
    public static final DeferredHolder<Attribute, Attribute> OCEAN_HUED_HEALING_AMOUNT = ATTRIBUTES.register("ocean_hued_healing_amount", OceanHuedHealingAmount::new);
    public static final DeferredHolder<Attribute, Attribute> OCEAN_HUED_COOLDOWN = ATTRIBUTES.register("ocean_hued_cooldown", OceanHuedCoolDown::new);
    public static final DeferredHolder<Attribute, Attribute> SPACE_LEAPFROG_COOLDOWN = ATTRIBUTES.register("space_leapfrog_cooldown", SpaceLeapfrogCoolDown::new);

    public static void register(@NotNull IEventBus bus) {
        bus.addListener(ModAttributes::registerAttribute);
        ATTRIBUTES.register(bus);
    }

    private static void registerAttribute(@NotNull EntityAttributeModificationEvent event) {
        event.getTypes().forEach(entityType -> event.add(entityType, ModAttributes.ARMOR_BREAKING_DURATION.getDelegate()));
        event.add(EntityType.PLAYER, ModAttributes.OCEAN_HUED_COUNTING.getDelegate());
        event.add(EntityType.PLAYER, ModAttributes.OCEAN_HUED_HEALING_AMOUNT.getDelegate());
        event.add(EntityType.PLAYER, ModAttributes.OCEAN_HUED_COOLDOWN.getDelegate());
        event.add(EntityType.PLAYER, ModAttributes.RUN_LIKE_HELL_COOLDOWN.getDelegate());
        event.add(EntityType.PLAYER, ModAttributes.UNTOUCHABLE_COOLDOWN.getDelegate());
        event.add(EntityType.PLAYER, ModAttributes.SPACE_LEAPFROG_COOLDOWN.getDelegate());
    }
}
