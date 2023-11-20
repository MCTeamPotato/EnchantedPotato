package com.teampotato.enchantedpotato.event;

import com.teampotato.enchantedpotato.config.toml.DetailsConfig;
import com.teampotato.enchantedpotato.enchantment.armor.legs.ErrorSpore;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.registry.ModEnchantments;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;

public class ErrorSporeEvents {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingHurt(@NotNull LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        DamageSource source = event.getSource();
        if (level.isClientSide) return;
        event.setAmount(event.getAmount() * (1.0F - ErrorSpore.ERROR_SPORE_LEVEL_MAP.get(Utils.getPotatoEnchantmentLevel(entity, ModEnchantments.ERROR_SPORE.get(), EarlySetupInitializer.equipmentSlotConfig.errorSpore))));
        if (source.getDirectEntity() instanceof LivingEntity sourceDirectEntity) {
            int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.ERROR_SPORE.get(), sourceDirectEntity);
            int creeperSpawnChecker = DetailsConfig.ERROR_SPORE_CREEPER_SPAWN_CHECKER.get();
            if (source.getSourcePosition() != null && enchantmentLevel < ModEnchantments.ERROR_SPORE.get().getMaxLevel() && enchantmentLevel > ModEnchantments.ERROR_SPORE.get().getMaxLevel() / creeperSpawnChecker * (creeperSpawnChecker - 1)) {
                Creeper creeper = new Creeper(EntityType.CREEPER, level);
                creeper.setPos(source.getSourcePosition());
                creeper.addTag(EarlySetupInitializer.MOD_ID + ".errorSpore");
                level.addFreshEntity(creeper);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingDrop(@NotNull LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();
        if (!entity.level().isClientSide && entity.removeTag(EarlySetupInitializer.MOD_ID + ".errorSpore")) event.setCanceled(true);
    }
}
