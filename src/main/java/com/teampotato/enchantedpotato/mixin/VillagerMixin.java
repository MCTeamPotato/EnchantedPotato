package com.teampotato.enchantedpotato.mixin;

import com.teampotato.enchantedpotato.EarlySetupInitializer;
import com.teampotato.enchantedpotato.enchantment.PoisonOfTheLastBreath;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.village.ReputationEventType;
import net.minecraft.world.entity.npc.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Villager.class)
public abstract class VillagerMixin {
    @Inject(method = "tellWitnessesThatIWasMurdered", at = @At("HEAD"), cancellable = true)
    private void onTell(Entity murderer, CallbackInfo ci) {
        if (EarlySetupInitializer.functionConfig.poisonOfTheLastBreath && murderer instanceof LivingEntity entity && Utils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.poisonOfTheLastBreath, PoisonOfTheLastBreath.PATH)) {
            ci.cancel();
        }
    }

    @Inject(method = "onReputationEventFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/gossip/GossipContainer;add(Ljava/util/UUID;Lnet/minecraft/world/entity/ai/gossip/GossipType;I)V", ordinal = 3), cancellable = true)
    private void onVillagerHurt(ReputationEventType type, Entity target, CallbackInfo ci) {
        if (EarlySetupInitializer.functionConfig.poisonOfTheLastBreath && target instanceof LivingEntity entity && Utils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.poisonOfTheLastBreath, PoisonOfTheLastBreath.PATH)) {
            ci.cancel();
        }
    }

    @Inject(method = "onReputationEventFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/gossip/GossipContainer;add(Ljava/util/UUID;Lnet/minecraft/world/entity/ai/gossip/GossipType;I)V", ordinal = 4), cancellable = true)
    private void onVillagerKilled(ReputationEventType type, Entity target, CallbackInfo ci) {
        if (EarlySetupInitializer.functionConfig.poisonOfTheLastBreath && target instanceof LivingEntity entity && Utils.hasPotatoEnchantmentEquipped(entity, EarlySetupInitializer.equipmentSlotConfig.poisonOfTheLastBreath, PoisonOfTheLastBreath.PATH)) {
            ci.cancel();
        }
    }
}
