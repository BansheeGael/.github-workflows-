package com.black.workflow;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = AreaDigMod.MODID, version = AreaDigMod.VERSION)
public class AreaDigMod {
    public static final String MODID = "AreaDigMod";
    public static final String VERSION = "1.0.0";
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println(MODID + " loaded!");
    }
}