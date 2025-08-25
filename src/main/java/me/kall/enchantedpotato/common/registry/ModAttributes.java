package me.kall.enchantedpotato.common.registry;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.common.attribute.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class ModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, EnchantedPotato.MOD_ID);

    public static final RegistryObject<Attribute> RUN_LIKE_HELL_COOLDOWN = ATTRIBUTES.register("run_like_hell_cooldown", RunLikeHellCoolDown::new);
    public static final RegistryObject<Attribute> UNTOUCHABLE_COOLDOWN = ATTRIBUTES.register("untouchable_cooldown", UntouchableCoolDown::new);
    public static final RegistryObject<Attribute> ARMOR_BREAKING_DURATION = ATTRIBUTES.register("armor_breaking_duration", ArmorBreakingDuration::new);
    public static final RegistryObject<Attribute> OCEAN_HUED_COUNTING = ATTRIBUTES.register("ocean_hued_counting", OceanHuedCounting::new);
    public static final RegistryObject<Attribute> OCEAN_HUED_HEALING_AMOUNT = ATTRIBUTES.register("ocean_hued_healing_amount", OceanHuedHealingAmount::new);
    public static final RegistryObject<Attribute> OCEAN_HUED_COOLDOWN = ATTRIBUTES.register("ocean_hued_cooldown", OceanHuedCoolDown::new);
    public static final RegistryObject<Attribute> SPACE_LEAPFROG_COOLDOWN = ATTRIBUTES.register("space_leapfrog_cooldown", SpaceLeapfrogCoolDown::new);

    public static void register(@NotNull IEventBus bus) {
        bus.addListener(ModAttributes::registerAttribute);
        ATTRIBUTES.register(bus);
    }

    private static void registerAttribute(@NotNull EntityAttributeModificationEvent event) {
        event.getTypes().forEach(entityType -> event.add(entityType, ModAttributes.ARMOR_BREAKING_DURATION.get()));
        event.add(EntityType.PLAYER, ModAttributes.OCEAN_HUED_COUNTING.get());
        event.add(EntityType.PLAYER, ModAttributes.OCEAN_HUED_HEALING_AMOUNT.get());
        event.add(EntityType.PLAYER, ModAttributes.OCEAN_HUED_COOLDOWN.get());
        event.add(EntityType.PLAYER, ModAttributes.RUN_LIKE_HELL_COOLDOWN.get());
        event.add(EntityType.PLAYER, ModAttributes.UNTOUCHABLE_COOLDOWN.get());
        event.add(EntityType.PLAYER, ModAttributes.SPACE_LEAPFROG_COOLDOWN.get());
    }
}
