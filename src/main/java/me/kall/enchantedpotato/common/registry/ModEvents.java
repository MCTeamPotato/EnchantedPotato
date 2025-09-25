package me.kall.enchantedpotato.common.registry;

import me.kall.enchantedpotato.common.enchantment.boots.BlackParade;
import me.kall.enchantedpotato.common.enchantment.boots.GraceOfGaia;
import me.kall.enchantedpotato.common.enchantment.boots.RunLikeHell;
import me.kall.enchantedpotato.common.enchantment.chestplate.Dissolve;
import me.kall.enchantedpotato.common.enchantment.chestplate.NatureBlessing;
import me.kall.enchantedpotato.common.enchantment.digger.MarkFromTheBeneath;
import me.kall.enchantedpotato.common.enchantment.digger.MineCarve;
import me.kall.enchantedpotato.common.enchantment.digger.UniteStonesOfAll;
import me.kall.enchantedpotato.common.enchantment.helmet.OceanHued;
import me.kall.enchantedpotato.common.enchantment.leggings.Untouchable;
import me.kall.enchantedpotato.common.enchantment.weapon.*;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import org.jetbrains.annotations.NotNull;

public class ModEvents {
    public static void register(@NotNull IEventBus bus) {
        bus.addListener(EventPriority.LOWEST, RunLikeHell::onLivingHurt);
        bus.addListener(EventPriority.LOWEST, RunLikeHell::onLivingTick);
        bus.addListener(EventPriority.LOWEST, BlackParade::onLivingDie);
        bus.addListener(EventPriority.LOWEST, Untouchable::onLivingHurt);
        bus.addListener(EventPriority.LOWEST, GraceOfGaia::onPlayerHurt);
        bus.addListener(EventPriority.LOWEST, LoRATrainer::onLivingDamage);
        bus.addListener(EventPriority.LOWEST, RippleOfDeath::onLivingDeath);
        bus.addListener(EventPriority.LOWEST, Dissolve::onPlayerHurt);
        bus.addListener(EventPriority.LOWEST, MarkFromTheBeneath::onPlayerDig);
        bus.addListener(EventPriority.LOWEST, ArmorBreaking::onLivingHurt);
        bus.addListener(NatureBlessing::onPlayerTick);
        bus.addListener(EventPriority.LOWEST, CaressingMoonlight::onLivingHurt);
        bus.addListener(EventPriority.LOWEST, OceanHued::onHeal);
        bus.addListener(EventPriority.LOWEST, OceanHued::onLivingHurt);
        bus.addListener(EventPriority.LOWEST, OceanHued::onPlayerDeath);
        bus.addListener(EventPriority.LOWEST, MineCarve::onLivingHurt);
        bus.addListener(MineCarve::onServerTick);
        bus.addListener(UniteStonesOfAll::onServerStarted);
        bus.addListener(EventPriority.LOWEST, UniteStonesOfAll::onBlockBreak);
        bus.addListener(EventPriority.LOWEST, LawOfInertia::onLivingTick);
        bus.addListener(MendingMirror::onPlayerWakeUp);
        bus.addListener(EventPriority.LOWEST, SacredRiftwind::onLivingHurt);
        bus.addListener(EventPriority.LOWEST, Mercy::onLivingDamage);
    }
}
