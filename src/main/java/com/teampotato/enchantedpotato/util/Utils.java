package com.teampotato.enchantedpotato.util;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {
    public static @NotNull List<NeutralMob> getNeutralMobs(AABB area, @NotNull ServerLevel level) {
        List<NeutralMob> list = new ObjectArrayList<>();
        for (Entity entity : level.getEntitiesOfClass(PathfinderMob.class, area)) {
            if (entity instanceof NeutralMob) list.add((NeutralMob) entity);
        }
        return list;
    }

    public static EquipmentSlot @NotNull [] getSlots(String @NotNull [] enchantmentSlots) {
        EquipmentSlot[] slots = new EquipmentSlot[enchantmentSlots.length];
        for (int i = 0; i < enchantmentSlots.length; i++) {
            slots[i] = EquipmentSlot.valueOf(enchantmentSlots[i]);
        }
        return slots;
    }

    public static int getPotatoEnchantmentLevel(LivingEntity entity, Enchantment enchantment, String @NotNull [] equipmentSlots) {
        if (entity == null) return 0;
        for (String slot : equipmentSlots) {
            ItemStack stack = entity.getItemBySlot(EquipmentSlot.valueOf(slot));
            return stack.getEnchantmentLevel(enchantment);
        }
        return 0;
    }

    public static boolean hasPotatoEnchantmentEquipped(LivingEntity entity, String @NotNull [] enchantmentEquipmentSlots, String enchantmentName) {
        if (entity == null) return false;
        for (String slot : enchantmentEquipmentSlots) {
            ItemStack stack =  entity.getItemBySlot(EquipmentSlot.valueOf(slot));
            for (Tag tag : stack.getEnchantmentTags()) {
                if (tag instanceof CompoundTag compoundTag) {
                    ResourceLocation id = ResourceLocation.tryParse(compoundTag.getString("id"));
                    if (id != null && ForgeRegistries.ENCHANTMENTS.getValue(id) != null && id.getPath().equals(enchantmentName)) return true;
                }
            }
        }
        return false;
    }

    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }
}
