package me.kall.enchantedpotato.common.registry;

import me.kall.enchantedpotato.EnchantedPotato;
import me.kall.enchantedpotato.common.enchantment.*;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ModEvents {
    public static void register(@NotNull IEventBus bus) {
        bus.addListener(EventPriority.LOWEST, RunLikeHell::onLivingHurt);
        bus.addListener(EventPriority.LOWEST, RunLikeHell::onLivingTick);
        bus.addListener(EventPriority.LOWEST, RunLikeHell::onChangeTarget);
        bus.addListener(EventPriority.LOWEST, BlackParade::onLivingDie);
        bus.addListener(EventPriority.LOWEST, Untouchable::onLivingHurt);
        bus.addListener(EventPriority.LOWEST, GraceOfGaia::onPlayerHurt);
        bus.addListener(EventPriority.LOWEST, LoRATrainer::onLivingDamage);
        bus.addListener(EventPriority.LOWEST, RippleOfDeath::onLivingDeath);
        bus.addListener(EventPriority.LOWEST, Dissolve::onPlayerHurt);

        bus.addListener(ModEvents::onServerStarted);
    }

    public static void onServerStarted(@NotNull ServerStartedEvent event) {
        Path directory = event.getServer().getServerDirectory().toPath().resolve("config").resolve(EnchantedPotato.MOD_ID);
        try {
            if (!Files.exists(directory)) Files.createDirectories(directory);
            Path readmePath = directory.resolve("README.txt");
            String content = "By default, EnchantedPotato will make mobs with invisibility effect untargetable by other mobs.\nIf this is unexpected for you, you can disable it in runLikeHell.toml";
            Files.writeString(readmePath, content, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            EnchantedPotato.LOGGER.error(e);
        }
    }
}
