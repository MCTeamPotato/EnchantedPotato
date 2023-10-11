package com.teampotato.enchantedpotato.event.center;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.enchantment.armor.chest.BlessingOfTheNature;
import com.teampotato.enchantedpotato.enchantment.armor.head.DyingOfLight;
import com.teampotato.enchantedpotato.enchantment.armor.legs.Untouchable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

public class EquipChangeCenter {

    @SubscribeEvent
    public static void onEquipmentChange(@NotNull LivingEquipmentChangeEvent event) {
        LivingEntity entity = event.getEntity();
        String fromEnchantments = event.getFrom().getEnchantmentTags().toString();
        String toEnchantments = event.getTo().getEnchantmentTags().toString();
        if (entity.level().isClientSide) return;
        if (EarlySetupInitializer.functionConfig.untouchable) {
            if (fromEnchantments.contains(Untouchable.PATH)) entity.removeTag(EarlySetupInitializer.MOD_ID + ".untouchable");
            if (toEnchantments.contains(Untouchable.PATH)) entity.addTag(EarlySetupInitializer.MOD_ID + ".untouchable");
        }
        if (EarlySetupInitializer.functionConfig.blessingOfTheNature) {
            if (fromEnchantments.contains(BlessingOfTheNature.PATH)) entity.removeTag(EarlySetupInitializer.MOD_ID + ".natureBlessed");
            if (toEnchantments.contains(BlessingOfTheNature.PATH)) entity.addTag(EarlySetupInitializer.MOD_ID + ".natureBlessed");
        }
        if (EarlySetupInitializer.functionConfig.dyingOfLight) {
            if (fromEnchantments.contains(DyingOfLight.PATH)) entity.removeTag(EarlySetupInitializer.MOD_ID + ".dyingOfLightEquip");
            if (toEnchantments.contains(DyingOfLight.PATH)) entity.addTag(EarlySetupInitializer.MOD_ID + ".dyingOfLightEquip");
        }
    }
}
