package me.kall.enchantedpotato.common.api;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.EntityHitResult;

public class PotatoHitResult extends EntityHitResult {
    public PotatoHitResult(Entity entity) {
        super(entity);
    }
}
