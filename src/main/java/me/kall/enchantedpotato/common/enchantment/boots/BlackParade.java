package me.kall.enchantedpotato.common.enchantment.boots;

import me.kall.enchantedpotato.common.config.BlackParadeConfig;
import me.kall.enchantedpotato.common.config.json.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.NotNull;

public class BlackParade extends BaseEnchantment {
    public static final BaseEnchantment INSTANCE = new BlackParade();

    public void register(@NotNull BootstrapContext<Enchantment> context, HolderGetter<Item> items) {
        Enchantment.EnchantmentDefinition definition = definition(items);
        Enchantment.Builder builder = Enchantment.enchantment(definition);

        context.register(ModEnchantments.BLACK_PARADE, builder.build(ModEnchantments.BLACK_PARADE.location()));
    }

    @Override
    public boolean isDisabled() {
        return DisableConfig.BLACK_PARADE.get();
    }

    @Override
    public HolderSet<Item> supportedItems(@NotNull HolderGetter<Item> items) {
        return items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE);
    }

    @Override
    public Rarity rarity() {
        return Rarity.RARE;
    }

    @Override
    public Enchantment.Cost dynamicCost() {
        return cost(12, 8);
    }

    @Override
    public Enchantment.Cost constantCost() {
        return cost(10, 6);
    }

    @Override
    public int anvilCost() {
        return 1;
    }

    @Override
    public EquipmentSlotGroup slotGroup() {
        return EquipmentSlotGroup.FEET;
    }

    @Override
    public ResourceKey<Enchantment> loc() {
        return ModEnchantments.BLACK_PARADE;
    }

    public static void onLivingDie(@NotNull LivingDeathEvent event) {
        if (!event.isCanceled() && event.getSource().getEntity() instanceof Player player && player.level() instanceof ServerLevel level && getLevel(player.getItemBySlot(EquipmentSlot.FEET), level, ModEnchantments.BLACK_PARADE) != 0) {
            MobEffectInstance speedInstance = player.getEffect(MobEffects.MOVEMENT_SPEED);
            int configDuration = BlackParadeConfig.SPEED_DURATION.get();
            int configAmplifier = BlackParadeConfig.SPEED_AMPLIFIER.get();
            if (speedInstance == null) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, configDuration, configAmplifier));
            } else {
                int duration = speedInstance.getDuration();
                player.removeEffect(MobEffects.MOVEMENT_SPEED);
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, configDuration + duration, configAmplifier));
            }
        }
    }
}
