package me.kall.enchantedpotato.common.enchantment.digger;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
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
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class MineCarve extends BaseEnchantment {
    private static final Object2IntMap<Pair<Integer, Runnable>> TASKS = new Object2IntOpenHashMap<>();

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
        if (event.getSource().getEntity() instanceof Player) {
            Player player = (Player) event.getSource().getEntity();
            if (!event.isCanceled() && player.level instanceof ServerLevel) {
                Enchantment mineCarve = ModEnchantments.MINE_CARVE.get();
                int enchantmentLevel = Math.max(EnchantmentHelper.getItemEnchantmentLevel(mineCarve, player.getMainHandItem()), EnchantmentHelper.getItemEnchantmentLevel(mineCarve, player.getOffhandItem()));
                if (enchantmentLevel > 0) {
                    AttributeInstance armor = event.getEntityLiving().getAttribute(Attributes.ARMOR);
                    if (armor == null) return;

                    double amount = MineCarveConfig.BASE_ARMOR_REDUCTION.get() + MineCarveConfig.GAINED_ARMOR_REDUCTION_PER_LEVEL.get() * (double) (enchantmentLevel - 1);
                    int ticks = MineCarveConfig.REDUCTION_TICKS_DURATION.get() + MineCarveConfig.GAINED_TICKS_DURATION_PER_LEVEL.get() * (enchantmentLevel - 1);

                    UUID uniqueId = UUID.randomUUID();
                    while (armor.getModifier(uniqueId) != null) uniqueId = UUID.randomUUID();

                    AttributeModifier attributeModifier = new AttributeModifier(uniqueId, "mine_carve_modifier", -amount, AttributeModifier.Operation.ADDITION);

                    for (AttributeModifier modifier : armor.getModifiers()) {
                        if (modifier.getName().equals(attributeModifier.getName())) {
                            armor.removeModifier(modifier);
                        }
                    }

                    armor.addPermanentModifier(attributeModifier);

                    Runnable task = () -> {
                        try {
                            armor.removeModifier(attributeModifier);
                        } catch (Throwable ignored) {
                        }
                        System.out.println("Removed modifier on " + event.getEntity());
                    };

                    TASKS.put(new Pair<>(ticks, task), 0);
                }
            }
        }
    }

    public static void onServerTick(TickEvent.@NotNull ServerTickEvent event) {
        ServerLifecycleHooks.getCurrentServer().execute(() -> {
            if (event.phase.equals(TickEvent.Phase.END)) {
                if (TASKS.isEmpty()) return;
                new Object2IntOpenHashMap<>(TASKS).object2IntEntrySet().forEach(entry -> {
                    int ticks = entry.getIntValue() + 1;
                    if (ticks >= entry.getKey().getFirst()) {
                        entry.getKey().getSecond().run();
                        TASKS.removeInt(entry.getKey());
                    } else {
                        TASKS.put(entry.getKey(), ticks);
                    }
                });
            }
        });
    }
}
