package me.kall.enchantedpotato.common.enchantment;

import it.unimi.dsi.fastutil.ints.Int2IntArrayMap;
import me.kall.enchantedpotato.common.config.GraceOfGaiaConfig;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import org.jetbrains.annotations.NotNull;

public class GraceOfGaia extends Enchantment {
    public static final Int2IntArrayMap LEVEL_Y_MAP = new Int2IntArrayMap();

    public GraceOfGaia() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
    }

    public int getMaxLevel() {
        return 3;
    }

    public static void onPlayerHurt(@NotNull LivingDamageEvent event) {
        if (!event.isCanceled() && event.getEntity() instanceof ServerPlayer player && player.level() instanceof ServerLevel) {
            int level = player.getItemBySlot(EquipmentSlot.FEET).getEnchantmentLevel(ModEnchantments.GRACE_OF_GAIA.get());
            if (level == 0) return;
            if (LEVEL_Y_MAP.isEmpty()) {
                int i = 1;
                int max = ModEnchantments.GRACE_OF_GAIA.get().getMaxLevel();
                while (i <= max) {
                    LEVEL_Y_MAP.put(i, GraceOfGaiaConfig.BASE_VALID_Y.get() + (i - 1) * GraceOfGaiaConfig.GAINED_Y_PER_LEVEL.get());
                    i++;
                }
            }
            int baseY = LEVEL_Y_MAP.get(level);
            int currentY = player.getOnPos().getY();
            if (currentY >= baseY) return;
            float absorbed = (float) (baseY - currentY) / 100F;
            if (absorbed > GraceOfGaiaConfig.getMaxDamageReduction()) absorbed = GraceOfGaiaConfig.getMaxDamageReduction();
            event.setAmount(event.getAmount() * (1.0F - absorbed));
        }
    }
}
