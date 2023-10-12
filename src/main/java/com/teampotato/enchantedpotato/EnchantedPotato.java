package com.teampotato.enchantedpotato;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.teampotato.enchantedpotato.config.json.reloadable.*;
import com.teampotato.enchantedpotato.config.toml.DetailsConfig;
import com.teampotato.enchantedpotato.event.*;
import com.teampotato.enchantedpotato.event.center.EquipChangeCenter;
import com.teampotato.enchantedpotato.mixin.EarlySetupInitializer;
import com.teampotato.enchantedpotato.registry.ModEffects;
import com.teampotato.enchantedpotato.registry.ModEnchantments;
import com.teampotato.enchantedpotato.util.Constants;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(EarlySetupInitializer.MOD_ID)
public class EnchantedPotato {

    public EnchantedPotato() {
        setupEvents();
        IEventBus bus =  FMLJavaModLoadingContext.get().getModEventBus();
        ModEnchantments.ENCHANTMENTS.register(bus);
        ModEffects.EFFECTS.register(bus);
        bus.addListener((FMLCommonSetupEvent event) -> event.enqueueWork(Constants::initConstants));
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DetailsConfig.DETAILS_CONFIG, EarlySetupInitializer.MOD_ID + "/details.toml");
        EarlySetupInitializer.LOGGER.info("Oh, potato, I'm enchanted by you.");
    }

    private static void setupEvents() {
        final IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.register(EquipChangeCenter.class);

        bus.addListener((RegisterCommandsEvent event) -> event.getDispatcher().register(
                LiteralArgumentBuilder.<CommandSourceStack>literal(EarlySetupInitializer.MOD_ID)
                        .requires(player -> player.hasPermission(2))
                        .then(LiteralArgumentBuilder.literal("reloadconfig"))
                        .executes(context -> {
                            CanApplyAtEnchantingTableConfig.readConfig(CanApplyAtEnchantingTableConfig.FILE);
                            IsAllowedOnBooksConfig.readConfig(IsAllowedOnBooksConfig.FILE);
                            IsCurseConfig.readConfig(IsCurseConfig.FILE);
                            IsDiscoverableConfig.readConfig(IsDiscoverableConfig.FILE);
                            IsTradeableConfig.readConfig(IsTradeableConfig.FILE);
                            IsTreasureOnlyConfig.readConfig(IsTreasureOnlyConfig.FILE);
                            EarlySetupInitializer.LOGGER.info("EnchantedPotato Attribute Config reloaded!");
                            ServerPlayer player = context.getSource().getPlayer();
                            if (player != null) player.displayClientMessage(Component.literal("EnchantedPotato Attribute Config reloaded!"), true);
                            return 1;
                        })
        ));

        if (EarlySetupInitializer.functionConfig.blackParade) bus.register(BlackParadeEvents.class);
        if (EarlySetupInitializer.functionConfig.boneSuckalaka) bus.register(BoneSuckalakaEvents.class);
        if (EarlySetupInitializer.functionConfig.dyingOfLight) bus.register(DyingOfLightEvents.class);
        if (EarlySetupInitializer.functionConfig.errorSpore) bus.register(ErrorSporeEvents.class);
        if (EarlySetupInitializer.functionConfig.gaiaBlessing) bus.register(GaiaBlessingEvents.class);
        if (EarlySetupInitializer.functionConfig.loraTrainer) bus.register(LoRATrainerEvents.class);
        if (EarlySetupInitializer.functionConfig.pressurizedCollapse) bus.register(PressurizedCollapseEvents.class);
        if (EarlySetupInitializer.functionConfig.rippleOfDeath) bus.register(RippleOfDeathEvents.class);
        if (EarlySetupInitializer.functionConfig.runLikeHell) bus.register(RunLikeHellEvents.class);
        if (EarlySetupInitializer.functionConfig.shieldBladeCommendation) bus.register(ShieldBladeCommendationEvents.class);
        if (EarlySetupInitializer.functionConfig.thisIsLeopard) bus.register(ThisIsLeopardEvents.class);
        if (EarlySetupInitializer.functionConfig.untouchable) bus.register(UntouchableEvents.class);
        if (EarlySetupInitializer.functionConfig.markFromTheBeneath) bus.register(MarkFromTheBeneathEvents.class);
        if (EarlySetupInitializer.functionConfig.oceanHued) bus.register(OceanHuedEvents.class);
        if (EarlySetupInitializer.functionConfig.huaJin) bus.register(HuaJinEvents.class);
        if (EarlySetupInitializer.functionConfig.flameCross) bus.register(FlameCrossEvents.class);
        if (EarlySetupInitializer.functionConfig.armorBreaking) bus.register(ArmorBreakingEvents.class);
        if (EarlySetupInitializer.functionConfig.mineCarve) bus.register(MineCarveEvents.class);
        if (EarlySetupInitializer.functionConfig.enderEnder) bus.register(EnderEnderEvents.class);
    }
}
