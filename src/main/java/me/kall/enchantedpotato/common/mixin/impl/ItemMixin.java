package me.kall.enchantedpotato.common.mixin.impl;

import me.kall.enchantedpotato.common.api.Weapon;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Item.class)
public class ItemMixin implements Weapon {
    @Unique private boolean item$isWeapon, item$tested;

    @Override
    public boolean item$isWeapon() {
        return this.item$isWeapon;
    }

    @Override
    public void item$setAsWeapon() {
        this.item$isWeapon = true;
    }

    @Override
    public boolean item$tested() {
        return this.item$tested;
    }

    @Override
    public void item$setTested() {
        this.item$tested = true;
    }
}
