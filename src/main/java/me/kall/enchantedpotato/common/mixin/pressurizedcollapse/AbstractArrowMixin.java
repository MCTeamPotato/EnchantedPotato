package me.kall.enchantedpotato.common.mixin.pressurizedcollapse;

import me.kall.enchantedpotato.common.config.PressurizedCollapseConfig;
import me.kall.enchantedpotato.common.enchantment.weapon.bow.PressurizedCollapse;
import me.kall.enchantedpotato.common.network.PressurizedCollapsePacket;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import me.kall.enchantedpotato.common.registry.ModPackets;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.fml.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin {
    @Unique private float pressurizedCollapse$chargeTime;
    @Unique private int pressurizedCollapse$level;


    @Inject(method = {"onHitEntity", "onHitBlock"}, at = @At("HEAD"))
    private void onHit(CallbackInfo ci) {
        AbstractArrow arrow = (AbstractArrow) (Object) this;
        if (arrow.level instanceof ServerLevel && arrow.getOwner() instanceof Player && this.pressurizedCollapse$chargeTime != 0) {
            double baseRange = PressurizedCollapseConfig.BASE_RANGE.get();
            double maxExtraRange = PressurizedCollapseConfig.MAX_EXTRA_RANGE.get();
            float maxChargeTime = PressurizedCollapseConfig.MAX_CHARGE_TIME.get().floatValue();

            float chargeBonus = Math.min((this.pressurizedCollapse$chargeTime - 20.0F) / (maxChargeTime - 20.0F), 1.0F);
            double range = baseRange + (maxExtraRange * chargeBonus);
            PressurizedCollapse.apply(arrow.position(), range, arrow.level, this.pressurizedCollapse$level);

            PressurizedCollapsePacket packet = new PressurizedCollapsePacket(arrow.position(), range);
            ModPackets.CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> arrow), packet);
        }
    }

    @Inject(method = "shoot", at = @At("HEAD"))
    private void onShoot(double x, double y, double z, float velocity, float inaccuracy, CallbackInfo ci) {
        AbstractArrow arrow = (AbstractArrow) (Object) this;
        if (arrow.getOwner() instanceof Player) {
            Player player = (Player) arrow.getOwner();
            ItemStack bow = player.getMainHandItem();
            if (!(bow.getItem() instanceof BowItem)) bow = player.getOffhandItem();
            if (!(bow.getItem() instanceof BowItem)) return;

            this.pressurizedCollapse$level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.PRESSURIZED_COLLAPSE.get(), bow);
            if (this.pressurizedCollapse$level != 0) {
                this.pressurizedCollapse$chargeTime = (float) player.getTicksUsingItem();
            }
        }
    }
}
