package me.kall.enchantedpotato.common.mixin.poisonofthelastbreath;

import me.kall.enchantedpotato.common.api.ExtendedLivingEntity;
import me.kall.enchantedpotato.common.enchantment.weapon.PoisonOfTheLastBreath;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Slime.class)
public abstract class SlimeMixin extends Mob implements ExtendedLivingEntity {
    protected SlimeMixin(EntityType<? extends Mob> entityType, Level level) {
        super(entityType, level);
    }

    @Redirect(method = "remove", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/EntityType;create(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;"))
    private <T extends Entity> @Nullable T onCreate(EntityType<T> instance, Level level) {
        Player player = this.enchantedPotato$getLastHurtByPlayer();
        if (player == null) return instance.create(level);
        if (PoisonOfTheLastBreath.has(player)) return null;
        return instance.create(level);
    }
}
