package me.kall.enchantedpotato.common.enchantment.weapon.bow;

import me.kall.enchantedpotato.common.config.GurenNoYumiyaConfig;
import me.kall.enchantedpotato.common.network.GurenNoYumiyaPacket;
import me.kall.enchantedpotato.common.registry.ModPackets;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class GurenNoYumiya extends Enchantment {
    public static final String GUREN_NO_YUMIYA_KEY = "GurenNoYumiyaLevel";

    public GurenNoYumiya() {
        super(Rarity.RARE, EnchantmentCategory.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    public int getMaxLevel() {
        return 4;
    }

    public static void apply(@NotNull AbstractArrow arrow) {
        if (arrow.getOwner() instanceof ServerPlayer) {
            int level = arrow.getPersistentData().getInt(GUREN_NO_YUMIYA_KEY);

            double radius = GurenNoYumiyaConfig.BASE_RADIUS.get() + GurenNoYumiyaConfig.GAINED_RADIUS_PER_LEVEL.get() * (double)(level - 1);
            AABB box = new AABB(arrow.blockPosition()).inflate(radius);
            Predicate<LivingEntity> filter = entity -> entity.isAlive() && !entity.fireImmune() && !entity.getUUID().equals(arrow.getOwner().getUUID());

            int fireTicks = (GurenNoYumiyaConfig.BASE_FIRE_SECONDS.get() + GurenNoYumiyaConfig.GAINED_FIRE_SECONDS_PER_LEVEL.get() * (level - 1)) * 20;

            Vec3 pos = arrow.position();
            GurenNoYumiyaPacket packet = new GurenNoYumiyaPacket(pos, radius);
            ModPackets.CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> arrow), packet);

            arrow.level()
                    .getEntitiesOfClass(LivingEntity.class, box, filter)
                    .forEach(entity -> {
                        if (entity.isOnFire()) {
                            int origin = entity.getRemainingFireTicks();
                            entity.setRemainingFireTicks(origin + fireTicks);
                        } else {
                            entity.setRemainingFireTicks(fireTicks);
                        }
                    });
            arrow.getPersistentData().remove(GUREN_NO_YUMIYA_KEY);
        }
    }
}
