package com.teampotato.enchantedpotato.api;

import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.phys.AABB;

import java.util.List;

public interface ILevel {
    List<NeutralMob> ep$getNeutralMobs(AABB area);
}
