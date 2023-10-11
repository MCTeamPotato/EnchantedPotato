package com.teampotato.enchantedpotato.event;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.api.EnderTpContainer;
import com.teampotato.enchantedpotato.config.toml.DetailsConfig;
import com.teampotato.enchantedpotato.registry.ModEnchantments;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class EnderEnderEvents {
    @SubscribeEvent
    public static void onLivingHurt(@NotNull LivingHurtEvent event) {
        LivingEntity attacked = event.getEntity();
        ResourceLocation id = ForgeRegistries.ENTITY_TYPES.getKey(attacked.getType());
        DamageSource damageSource = event.getSource();
        if (id == null || event.isCanceled() || attacked.level().isClientSide || !DetailsConfig.ENDER_ENDER_VALID_TARGET.get().contains(id.toString()) || !(damageSource.getDirectEntity() instanceof LivingEntity entity)) return;
        int level = Utils.getPotatoEnchantmentLevel(entity, ModEnchantments.ENDER_ENDER.get(), EarlySetupInitializer.equipmentSlotConfig.enderEnder);
        if (level > 0) {
            if (attacked.getTags().stream().noneMatch(tag -> tag.startsWith(EarlySetupInitializer.MOD_ID + ".end."))) attacked.addTag(EarlySetupInitializer.MOD_ID + ".end." + level);
            event.setAmount(event.getAmount() + (float) level * DetailsConfig.ENDER_ENDER_DAMAGE_INCREASE_PER_LEVEL.get().floatValue());
        }
    }

    @SubscribeEvent
    public static void onEnderTp(EntityTeleportEvent.@NotNull EnderEntity event) {
        LivingEntity attacked = event.getEntityLiving();
        ResourceLocation id = ForgeRegistries.ENTITY_TYPES.getKey(attacked.getType());
        if (id == null || !DetailsConfig.ENDER_ENDER_VALID_TARGET.get().contains(id.toString())) return;
        if ((attacked instanceof EnderMan enderMan && ((EnderTpContainer) enderMan).ep$cantEnderTp()) || (attacked instanceof Shulker shulker && ((EnderTpContainer) shulker).ep$cantEnderTp())) {
            event.setCanceled(true);
        }
    }
}
