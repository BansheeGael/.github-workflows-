package com.autoexcavate.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import com.autoexcavate.util.AreaDigger;

public class AutoDigHandler {
    private final AreaDigger areaDigger;
    private boolean wasDigging = false;
    private long lastDigTime = 0;
    
    public AutoDigHandler() {
        this.areaDigger = new AreaDigger();
    }
    
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;
        
        EntityPlayer player = event.player;
        World world = player.worldObj;
        
        // Only process on server side
        if (world.isRemote) return;
        
        // Check activation conditions
        if (shouldActivateAutoDig(player)) {
            long currentTime = System.currentTimeMillis();
            
            // Limit call frequency (every 100ms)
            if (currentTime - lastDigTime > 100) {
                areaDigger.digAreaAroundPlayer(player, world);
                lastDigTime = currentTime;
            }
        }
    }
    
    private boolean shouldActivateAutoDig(EntityPlayer player) {
        // Check for empty hand
        ItemStack heldItem = player.getHeldItem();
        if (heldItem != null) return false;
        
        // Check survival mode
        if (player.capabilities.isCreativeMode) return false;
        
        // Check if player is looking at block and holding left click
        Minecraft mc = Minecraft.getMinecraft();
        if (mc == null) return false;
        
        MovingObjectPosition mop = mc.objectMouseOver;
        if (mop == null || mop.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) 
            return false;
            
        // Check if left mouse button is pressed
        return mc.gameSettings.keyBindAttack.getIsKeyPressed();
    }
}