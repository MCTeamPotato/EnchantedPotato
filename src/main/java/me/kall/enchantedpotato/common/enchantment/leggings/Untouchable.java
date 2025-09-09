package me.kall.enchantedpotato.common.enchantment.leggings;

import me.kall.enchantedpotato.common.api.ExtendedPlayer;
import me.kall.enchantedpotato.common.config.UntouchableConfig;
import me.kall.enchantedpotato.common.config.json.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.NotNull;

public class Untouchable extends BaseEnchantment {
    public static final BaseEnchantment INSTANCE = new Untouchable();

    @Override
    public boolean isDisabled() {
        return DisableConfig.UNTOUCHABLE.get();
    }

    @Override
    public HolderSet<Item> supportedItems(HolderGetter<Item> items) {
        return items.getOrThrow(ItemTags.LEG_ARMOR_ENCHANTABLE);
    }

    @Override
    public Rarity rarity() {
        return Rarity.RARE;
    }

    @Override
    public int maxLevel() {
        return 3;
    }

    @Override
    public Enchantment.Cost dynamicCost() {
        return cost(15, 10);
    }

    @Override
    public Enchantment.Cost constantCost() {
        return cost(12, 8);
    }

    @Override
    public int anvilCost() {
        return 2;
    }

    @Override
    public EquipmentSlotGroup slotGroup() {
        return EquipmentSlotGroup.LEGS;
    }

    @Override
    public ResourceKey<Enchantment> loc() {
        return ModEnchantments.UNTOUCHABLE;
    }

    public static void onLivingHurt(@NotNull LivingIncomingDamageEvent event) {
        if (!event.isCanceled() && event.getEntity() instanceof ServerPlayer player && player.level() instanceof ServerLevel serverLevel) {
            int enchantmentLevel = getLevel(player.getItemBySlot(EquipmentSlot.LEGS), serverLevel, ModEnchantments.UNTOUCHABLE);
            if (enchantmentLevel == 0 || ((ExtendedPlayer)player).untouchable$isInCoolDown()) return;
            int coolDown = UntouchableConfig.BASIC_COOLDOWN.get() - UntouchableConfig.SAVED_COOLDOWN_PER_LEVEL.get() * (enchantmentLevel - 1);
            if (coolDown > 0) ((ExtendedPlayer)player).untouchable$setCoolDown(coolDown);
            knockBack(player, enchantmentLevel);
        }
    }

    private static void knockBack(@NotNull Player player, int level) {
        double radius = UntouchableConfig.BASIC_RADIUS.get() + (level * UntouchableConfig.GAINED_RADIUS_PER_LEVEL.get());
        double force = UntouchableConfig.BASIC_FORCE.get() + (level * UntouchableConfig.GAINED_FORCE_PER_LEVEL.get());
        int slowDuration = UntouchableConfig.BASIC_SLOWNESS_DURATION.get() + (level * UntouchableConfig.GAINED_SLOWNESS_DURATION_PER_LEVEL.get());

        player.level().getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(radius), e -> e instanceof Monster)
                .forEach(entity -> {
                    Vec3 dir = entity.position().subtract(player.position()).normalize();
                    entity.push(dir.x * force, 0.4, dir.z * force);
                    entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, slowDuration, 1));
                });
    }

}
