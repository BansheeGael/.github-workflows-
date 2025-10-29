package com.banshee.areadig;  // ⬅️ ИЗМЕНЕНО: yourname → banshee

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = "AreaDig", name = "Area Dig 7x7", version = "1.0")
public class AreaDig {
    
    public static boolean enabled = false;
    private KeyBinding toggleKey;
    private BlockQueue blockQueue = new BlockQueue();
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        toggleKey = new KeyBinding("Включить копку 7x7", Keyboard.KEY_F2, "Area Dig");
        ClientRegistry.registerKeyBinding(toggleKey);
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        blockQueue.processQueue();
        
        if (toggleKey.isPressed()) {
            enabled = !enabled;
            if (Minecraft.getMinecraft().thePlayer != null) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(
                    "Area Dig 7x7: " + (enabled ? "§aВКЛЮЧЕНА" : "§cВЫКЛЮЧЕНА")
                );
            }
        }
    }
    
    @SubscribeEvent
    public void onBlockClick(PlayerInteractEvent event) {
        if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK && enabled) {
            event.setCanceled(true);
            startAreaDig(event.x, event.y, event.z);
        }
    }
    
    private void startAreaDig(int x, int y, int z) {
        // Центральный блок
        blockQueue.addBlock(x, y, z, 0);
        
        // Блоки области 7x7
        for (int dx = -3; dx <= 3; dx++) {
            for (int dz = -3; dz <= 3; dz++) {
                if (dx == 0 && dz == 0) continue;
                int delay = 50 + (int)(Math.random() * 150);
                blockQueue.addBlock(x + dx, y, z + dz, delay);
            }
        }
    }
}
