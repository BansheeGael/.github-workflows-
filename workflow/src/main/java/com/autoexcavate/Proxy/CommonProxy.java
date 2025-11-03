package com.autoexcavate.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import com.autoexcavate.handler.AutoDigHandler;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        // Общая инициализация
    }
    
    public void init(FMLInitializationEvent event) {
        // Регистрируем обработчик тиков
        FMLCommonHandler.instance().bus().register(new AutoDigHandler());
    }
}