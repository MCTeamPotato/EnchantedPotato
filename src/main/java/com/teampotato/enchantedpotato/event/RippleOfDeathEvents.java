package com.teampotato.enchantedpotato.event;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.config.DetailsConfig;
import com.teampotato.enchantedpotato.enchantment.RippleOfDeath;
import com.teampotato.enchantedpotato.util.Constants;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

public class RippleOfDeathEvents {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLivingDeath(@NotNull LivingDeathEvent event) {
        if (event.isCanceled()) return;
        DamageSource damageSource = event.getSource();
        if (damageSource.getDirectEntity() instanceof LivingEntity entity) {
            Level level = entity.level();
            if (level.isClientSide()) return;
            double x = entity.getX();
            double y = entity.getY();
            double z = entity.getZ();
            if (Utils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.rippleOfDeath, RippleOfDeath.PATH)) {
                for (Monster monster : level.getEntitiesOfClass(Monster.class, new AABB(x - Constants.rippleOfDeathX, y - Constants.rippleOfDeathY, z - Constants.rippleOfDeathZ, x + Constants.rippleOfDeathX, y + Constants.rippleOfDeathY, z + Constants.rippleOfDeathZ))) {
                    monster.hurt(monster.level().damageSources().generic(), DetailsConfig.RIPPLE_OF_DEATH_EXTRA_DAMAGE.get().floatValue());
                }
            }
        }
    }
}
