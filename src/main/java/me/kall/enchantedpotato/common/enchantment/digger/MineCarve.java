package me.kall.enchantedpotato.common.enchantment.digger;

import me.kall.enchantedpotato.common.config.MineCarveConfig;
import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import me.kall.enchantedpotato.common.registry.ModEnchantments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class MineCarve extends BaseEnchantment {
    public MineCarve() {
        super(Rarity.RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public boolean isDisabled() {
        return DisableConfig.MINE_CARVE.get();
    }

    @Override
    public boolean canEnchant(@NotNull ItemStack stack) {
        return stack.getItem() instanceof PickaxeItem && super.canEnchant(stack);
    }

    public static void onLivingHurt(@NotNull LivingHurtEvent event) {
        if (!event.isCanceled() && event.getSource().getEntity() instanceof Player player && player.level() instanceof ServerLevel) {
            Enchantment mineCarve = ModEnchantments.MINE_CARVE.get();
            int enchantmentLevel = Math.max(player.getMainHandItem().getEnchantmentLevel(mineCarve), player.getOffhandItem().getEnchantmentLevel(mineCarve));
            if (enchantmentLevel > 0) {
                AttributeInstance armor = event.getEntity().getAttribute(Attributes.ARMOR);
                if (armor == null) return;

                double amount = MineCarveConfig.BASE_ARMOR_REDUCTION.get() + MineCarveConfig.GAINED_ARMOR_REDUCTION_PER_LEVEL.get() * (double) (enchantmentLevel - 1);

                UUID uniqueId = UUID.randomUUID();
                while (armor.getModifier(uniqueId) != null) uniqueId = UUID.randomUUID();

                AttributeModifier attributeModifier = new AttributeModifier(uniqueId, "mine_carve_modifier", -amount, AttributeModifier.Operation.ADDITION);

                for (AttributeModifier modifier : armor.getModifiers()) {
                    if (modifier.getName().equals(attributeModifier.getName())) armor.removeModifier(modifier);
                }

                armor.addPermanentModifier(attributeModifier);
            }
        }
    }
}
