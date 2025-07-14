package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;

public class EnderEnder extends Enchantment {
    public EnderEnder() {
        super(Rarity.RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    public boolean canEnchant(@NotNull ItemStack stack) {
        Item item = stack.getItem();
        return item instanceof AxeItem || item instanceof SwordItem || item instanceof TridentItem;
    }

    private static boolean isEnder(LivingEntity entity) {
        return entity instanceof EnderMan || entity instanceof Shulker;
    }

    public static void onLivingHurt(@NotNull LivingHurtEvent event) {
        if (!event.isCanceled() && event.getSource().getEntity() instanceof Player player && player.level() instanceof ServerLevel) {
            int level = Math.max(player.getMainHandItem().getEnchantmentLevel(ModEnchantments.ENDER_ENDER.get()), player.getOffhandItem().getEnchantmentLevel(ModEnchantments.ENDER_ENDER.get()));
            if (level == 0) return;
            LivingEntity attacked = event.getEntity();
            if (isEnder(attacked)) {
                attacked.getPersistentData().putInt("TeleportEnded", 10);
                event.setAmount(event.getAmount() + level);
            }
        }
    }

    public static void onEnderTp(EntityTeleportEvent.EnderEntity event) {
        if (!event.isCanceled() && event.getEntityLiving().getPersistentData().getInt("TeleportEnded") != 0) {
            event.setCanceled(true);
        }
    }

    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (!event.isCanceled() && entity.level() instanceof ServerLevel) {
            CompoundTag data = entity.getPersistentData();
            int count = data.getInt("TeleportEnded");
            if (count == 0) {
                data.remove("TeleportEnded");
                return;
            }
            data.putInt("TeleportEnded", count - 1);
        }
    }
}
