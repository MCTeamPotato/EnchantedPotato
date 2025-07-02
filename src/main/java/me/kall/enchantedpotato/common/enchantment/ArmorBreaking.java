package me.kall.enchantedpotato.common.enchantment;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.common.api.ExtendedLivingEntity;
import me.kall.enchantedpotato.common.config.ArmorBreakingConfig;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

public class ArmorBreaking extends Enchantment {
    public static final String TAG = "armorBreaking";

    public ArmorBreaking() {
        super(Rarity.RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    public boolean canEnchant(@NotNull ItemStack stack) {
        Item item = stack.getItem();
        return item instanceof AxeItem || item instanceof SwordItem || item instanceof BowItem;
    }

    public static void onLivingHurt(@NotNull LivingHurtEvent event) {
        if (!event.isCanceled() && event.getSource().getEntity() instanceof Player player && player.level() instanceof ServerLevel) {
            int level = player.getMainHandItem().getEnchantmentLevel(ModEnchantments.ARMOR_BREAKING.get());
            if (level == 0) level = player.getOffhandItem().getEnchantmentLevel(ModEnchantments.ARMOR_BREAKING.get());
            if (level == 0) return;
            LivingEntity entity = event.getEntity();
            Set<String> tagSet = entity.getTags();
            if (tagSet.stream().anyMatch(entry -> entry.startsWith(TAG))) {
                boolean updateRequired = false;
                Iterator<String> tags = tagSet.iterator();
                while (tags.hasNext()) {
                    String tag = tags.next();
                    if (tag.startsWith(TAG)) {
                        int old = Integer.parseInt(tag.split("g")[1]);
                        if (old != level) {
                            updateRequired = true;
                            tags.remove();
                            break;
                        }
                    }
                }
                if (updateRequired) entity.addTag(TAG + level);
            } else {
                entity.addTag(TAG + level);
            }
            ((ExtendedLivingEntity)entity).armorBreaking$getAttribute().setBaseValue(ArmorBreakingConfig.BASE_DURATION.get().doubleValue() + ArmorBreakingConfig.GAINED_DURATION_PER_LEVEL.get().doubleValue() * (double) (level - 1));
        }
    }

    public static void checkPossibleTagError(LivingEvent.@NotNull LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (!event.isCanceled() && entity.level() instanceof ServerLevel) {
            ((ExtendedLivingEntity)entity).armorBreaking$bumpInterval();
            if (((ExtendedLivingEntity)entity).armorBreaking$getInterval() <= 600) return;
            ((ExtendedLivingEntity)entity).armorBreaking$clearInterval();
            Set<String> tags = entity.getTags();
            Predicate<String> isTag = tag -> tag.startsWith(TAG);
            long count = tags.stream().filter(isTag).count();
            if (count == 0) return;
            if (count != 1) {
                EnchantedPotato.LOGGER.error("{} has multiple {} tags, that's not reasonable! Removing all.", entity, TAG);
                tags.removeIf(isTag);
            }
        }
    }
}
