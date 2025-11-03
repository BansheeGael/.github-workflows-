package com.autoexcavate;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import com.autoexcavate.proxy.CommonProxy;

@Mod(modid = AutoExcavateMod.MODID, version = AutoExcavateMod.VERSION, name = AutoExcavateMod.NAME)
public class AutoExcavateMod {
    public static final String MODID = "journeymap_enhancement";
    public static final String VERSION = "1.0.0";
    public static final String NAME = "JourneyMap Enhancement";

    @SidedProxy(
            clientSide = "com.autoexcavate.proxy.ClientProxy",
            serverSide = "com.autoexcavate.proxy.CommonProxy"
    )
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        // Регистрация команд, если нужно
    }
}