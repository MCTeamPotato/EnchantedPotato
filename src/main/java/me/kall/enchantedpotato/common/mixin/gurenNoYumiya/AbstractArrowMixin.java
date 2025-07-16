package me.kall.enchantedpotato.common.mixin.gurennoyumiya;

import me.kall.enchantedpotato.common.config.GurenNoYumiyaConfig;
import me.kall.enchantedpotato.common.enchantment.weapon.bow.GurenNoYumiya;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin extends Projectile {
    @Unique private static final int BOW_CHARGE_TIME = 1, BASE_EXTRA_CHARGE_TIME_REQUIRED = 1;

    protected AbstractArrowMixin(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = {"onHitEntity", "onHitBlock"}, at = @At("HEAD"))
    private void onHit(CallbackInfo ci) {
        GurenNoYumiya.apply((AbstractArrow) (Object) this);
    }

    @Inject(method = "shoot", at = @At("HEAD"))
    private void onShoot(double x, double y, double z, float velocity, float inaccuracy, CallbackInfo ci) {
        if (this.getOwner() instanceof Player player) {
            ItemStack bow = player.getMainHandItem();
            if (!(bow.getItem() instanceof BowItem)) bow = player.getOffhandItem();
            if (!(bow.getItem() instanceof BowItem)) return;
            int level = bow.getEnchantmentLevel(ModEnchantments.GUREN_NO_YUMIYA.get());
            if (level != 0) {
                double demandSeconds = BOW_CHARGE_TIME + (BASE_EXTRA_CHARGE_TIME_REQUIRED - GurenNoYumiyaConfig.SAVED_HOLDING_SECONDS_PER_LEVEL.get() * (level - 1));
                int demandTicks = (int) (demandSeconds * 20);
                int holdingTicks = player.getTicksUsingItem();
                if (holdingTicks >= demandTicks) {
                    this.addTag("gurenNoYumiya" + level);
                }
            }
        }
    }
}
