package com.teampotato.enchantedpotato.event;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.config.DetailsConfig;
import com.teampotato.enchantedpotato.enchantment.ErrorSpore;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

public class ErrorSporeEvents {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingHurt(@NotNull LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        DamageSource source = event.getSource();
        if (level.isClientSide) return;
        event.setAmount(event.getAmount() * (1.0F - ErrorSpore.ERROR_SPORE_LEVEL_MAP.get(Utils.getPotatoEnchantmentLevel(entity, ErrorSpore.ERROR_SPORE, EarlySetupInitializer.equipmentSlotConfig.errorSpore))));
        if (source.getDirectEntity() instanceof LivingEntity sourceDirectEntity) {
            int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(ErrorSpore.ERROR_SPORE, sourceDirectEntity);
            int creeperSpawnChecker = DetailsConfig.ERROR_SPORE_CREEPER_SPAWN_CHECKER.get();
            if (source.getSourcePosition() != null && enchantmentLevel < ErrorSpore.maxLevel && enchantmentLevel > ErrorSpore.maxLevel / creeperSpawnChecker * (creeperSpawnChecker - 1)) {
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
