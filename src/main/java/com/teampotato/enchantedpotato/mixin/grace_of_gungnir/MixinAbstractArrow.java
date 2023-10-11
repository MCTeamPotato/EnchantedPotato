package com.teampotato.enchantedpotato.mixin.grace_of_gungnir;

import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.enchantment.bow.GraceOfGungnir;
import com.teampotato.enchantedpotato.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(AbstractArrow.class)
public abstract class MixinAbstractArrow extends Entity {
    public MixinAbstractArrow(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow public abstract double getBaseDamage();

    @Unique
    private AbstractArrow ep$getThis() {
        return (AbstractArrow) (Object) this;
    }

    @Unique
    private boolean ep$graceOfGungnirTracked;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/Projectile;tick()V", shift = At.Shift.AFTER))
    private void onTick(CallbackInfo ci) {
        Level level = this.level();
        if (level.isClientSide) return;
        if (!this.ep$graceOfGungnirTracked) {
            this.ep$graceOfGungnirTracked = true;
            if (!(ep$getThis().getOwner() instanceof LivingEntity owner) || this.getTags().contains(EarlySetupInitializer.MOD_ID + ".newArrow") || !Utils.hasPotatoEnchantmentEquipped(owner, EarlySetupInitializer.equipmentSlotConfig.graceOfGungnir, GraceOfGungnir.PATH)) return;
            BlockPos arrowBlockPos = this.blockPosition();
            AABB arrowBoundingBox = this.getBoundingBox().expandTowards(this.getDeltaMovement());
            ChunkPos arrowChunkPos = level.getChunk(arrowBlockPos.getX(), arrowBlockPos.getY()).getPos();
            ChunkPos hitChunkPos = level.getChunk((int) arrowBoundingBox.maxX, (int) arrowBoundingBox.maxZ).getPos();
            List<Mob> enemies = level.getEntitiesOfClass(Mob.class, new AABB(arrowChunkPos.getMinBlockX(), (int) arrowBoundingBox.minY - 5, arrowChunkPos.getMaxBlockZ(), hitChunkPos.getMinBlockX(), (int) arrowBoundingBox.maxY + 5, hitChunkPos.getMaxBlockZ()));

            if (!enemies.isEmpty()) {
                LivingEntity nearestEnemy = ep$getNearestEnemy(enemies, this.position());
                if (nearestEnemy == null) return;
                AbstractArrow newArrow = new Arrow(level, owner);

                newArrow.setPos(nearestEnemy.getX(), nearestEnemy.getY(), nearestEnemy.getZ());
                newArrow.setBaseDamage(this.getBaseDamage());
                newArrow.setDeltaMovement(this.getDeltaMovement());
                newArrow.addTag(EarlySetupInitializer.MOD_ID + ".newArrow");
                level.addFreshEntity(newArrow);
                this.remove(Entity.RemovalReason.DISCARDED);
            }
        }
    }

    @Unique
    @Nullable
    private static LivingEntity ep$getNearestEnemy(@NotNull List<Mob> enemies, Vec3 arrowPos) {
        LivingEntity nearestEnemy = null;
        double closestDistance = Double.MAX_VALUE;

        for (LivingEntity enemy : enemies) {
            double distance = enemy.distanceToSqr(arrowPos);
            if (distance < closestDistance) {
                closestDistance = distance;
                nearestEnemy = enemy;
            }
        }
        return nearestEnemy;
    }
}
