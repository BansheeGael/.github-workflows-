package com.autoexcavate.proxy;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        // Клиент-специфичная логика
    }
    
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        // Клиент-специфичная логика
    }
}