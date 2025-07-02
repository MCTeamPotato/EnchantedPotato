package me.kall.enchantedpotato.common.enchantment;

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
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Set;

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
}
