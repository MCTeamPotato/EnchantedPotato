package me.kall.enchantedpotato.common.enchantment.chestplate;

import me.kall.enchantedpotato.common.api.ExtendedLivingEntity;
import me.kall.enchantedpotato.common.config.NatureBlessingConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class NatureBlessing extends BaseEnchantment {
    public static final BaseEnchantment INSTANCE = new NatureBlessing();

    @Override
    public HolderSet<Item> supportedItems(HolderGetter<Item> items) {
        return items.getOrThrow(ItemTags.CHEST_ARMOR_ENCHANTABLE);
    }

    @Override
    public Rarity rarity() {
        return Rarity.VERY_RARE;
    }

    @Override
    public int maxLevel() {
        return 5;
    }

    @Override
    public Enchantment.Cost dynamicCost() {
        return cost(28, 18);
    }

    @Override
    public Enchantment.Cost constantCost() {
        return cost(22, 15);
    }

    @Override
    public int anvilCost() {
        return 4;
    }

    @Override
    public EquipmentSlotGroup slotGroup() {
        return EquipmentSlotGroup.CHEST;
    }

    @Override
    public ResourceKey<Enchantment> key() {
        return ModEnchantments.NATURE_BLESSING;
    }

    public static void onPlayerTick(PlayerTickEvent.@NotNull Post event) {
        if (event.getEntity() instanceof ServerPlayer player && player.level() instanceof ServerLevel level) {
            int enchantmentLevel = getLevel(player.getItemBySlot(EquipmentSlot.CHEST), level, ModEnchantments.NATURE_BLESSING);
            if (enchantmentLevel == 0) return;
            if (((ExtendedLivingEntity)player).natureBlessing$getInterval() <= 0) {
                ((ExtendedLivingEntity)player).natureBlessing$setInterval(100);
                player.heal(NatureBlessingConfig.BASE_HEAL_AMOUNT.get().floatValue() + NatureBlessingConfig.GAINED_HEAL_AMOUNT_PER_LEVEL.get().floatValue() * (float) (enchantmentLevel - 1));

                double radius = NatureBlessingConfig.BASE_RADIUS.get() + NatureBlessingConfig.GAINED_RADIUS_PER_LEVEL.get() * (double) (enchantmentLevel - 1);
                List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(radius));

                float damageBonus = NatureBlessingConfig.DAMAGE_BONUS_PER_LEVEL.get().floatValue() * (float) (enchantmentLevel - 1);
                float minDamage = NatureBlessingConfig.MIN_DAMAGE.get().floatValue() + damageBonus;
                float maxDamage = NatureBlessingConfig.MAX_DAMAGE.get().floatValue() + damageBonus;
                float damage = ThreadLocalRandom.current().nextFloat(minDamage, maxDamage);

                double knockbackStrength = NatureBlessingConfig.BASE_KNOCKBACK_STRENGTH.get() + NatureBlessingConfig.GAINED_KNOCKBACK_STRENGTH_PER_LEVEL.get() * (double) (enchantmentLevel - 1);

                for (LivingEntity entity : entities) {
                    if (entity.getUUID() == player.getUUID() || player.isAlliedTo(entity) || entity instanceof Player || !entity.isAlive()) continue;
                    entity.hurt(player.damageSources().indirectMagic(player, player), damage);

                    Vec3 knockbackDir = new Vec3(entity.getX() - player.getX(), 0, entity.getZ() - player.getZ()).normalize();

                    entity.setDeltaMovement(entity.getDeltaMovement().add(knockbackDir.x * knockbackStrength, 0.3, knockbackDir.z * knockbackStrength));
                    entity.hurtMarked = true;
                }
            }
        }
    }

}
