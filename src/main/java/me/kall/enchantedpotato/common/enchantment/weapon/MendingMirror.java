package me.kall.enchantedpotato.common.enchantment.weapon;

import me.kall.enchantedpotato.common.config.MendingMirrorConfig;
import me.kall.enchantedpotato.common.config.disable.DisableConfig;
import me.kall.enchantedpotato.common.data.MendingMirrorData;
import me.kall.enchantedpotato.common.enchantment.BaseEnchantment;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.event.entity.player.PlayerWakeUpEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.UUID;

public class MendingMirror extends BaseEnchantment {
    public static void recordBrokenItem(@NotNull Entity entity, @NotNull ItemStack stack) {
        if (!(entity.level() instanceof ServerLevel level)) return;
        MendingMirrorData data = MendingMirrorData.get(level);

        UUID playerId = entity.getUUID();
        ItemStack copy = stack.copy();
        copy.setDamageValue(0);

        CompoundTag tag = copy.getOrCreateTag();
        if (tag.contains("Enchantments", 9)) {
            ListTag enchants = tag.getList("Enchantments", 10);
            for (int i = 0; i < enchants.size(); i++) {
                CompoundTag enchantTag = enchants.getCompound(i);
                if ("mendingmirror:mending_mirror".equals(enchantTag.getString("id"))) {
                    enchants.remove(i);
                    break;
                }
            }
        }

        int maxDurability = copy.getMaxDamage();
        copy.setDamageValue((int) ((double)maxDurability * (1.00D - MendingMirrorConfig.REMAINING_DURABILITY.get())));

        CompoundTag itemNbt = copy.save(new CompoundTag());
        data.addData(playerId, itemNbt);
    }

    public static void onPlayerWakeUp(@NotNull PlayerWakeUpEvent event) {
        Player player = event.getEntity();
        UUID playerId = player.getUUID();

        if (!(player.level() instanceof ServerLevel level)) return;
        MendingMirrorData data = MendingMirrorData.get(level);

        Set<CompoundTag> recovered = data.removeData(playerId);
        if (recovered != null && !recovered.isEmpty()) {
            recovered.forEach(compoundTag -> {
                ItemStack stack = ItemStack.of(compoundTag);
                if (!player.addItem(stack)) player.drop(stack, true);
            });
            if (MendingMirrorConfig.PLAY_SOUND.get()) player.playSound(SoundEvents.PLAYER_LEVELUP, 1.0F, 1.0F);
        }
    }

    @Override
    public boolean isDisabled() {
        return DisableConfig.MENDING_MIRROR.get();
    }

    @Override
    public HolderSet<Item> supportedItems(HolderGetter<Item> items) {
        return null;
    }

    @Override
    public int weight() {
        return 0;
    }

    @Override
    public int maxLevel() {
        return 0;
    }

    @Override
    public Enchantment.Cost dynamicCost() {
        return null;
    }

    @Override
    public Enchantment.Cost constantCost() {
        return null;
    }

    @Override
    public int anvilCost() {
        return 0;
    }

    @Override
    public EquipmentSlotGroup slotGroup() {
        return EquipmentSlotGroup.HAND;
    }
}
