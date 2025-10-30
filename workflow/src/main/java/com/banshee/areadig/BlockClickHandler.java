package com.banshee.areadig;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class BlockClickHandler {
    
    public static void register() {
        // Регистрация обработчика событий
    }
    
    @SubscribeEvent
    public void onBlockClick(PlayerInteractEvent event) {
        if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
            EntityPlayer player = event.entityPlayer;
            World world = player.worldObj;
            int x = event.x;
            int y = event.y;
            int z = event
