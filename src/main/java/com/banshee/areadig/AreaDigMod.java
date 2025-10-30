package com.banshee.areadig;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = "areadig", name = "Area Dig Mod", version = "1.0")
public class AreaDigMod {
    
    @Mod.Init
    public void init(FMLInitializationEvent event) {
        BlockClickHandler.register();
    }
}
