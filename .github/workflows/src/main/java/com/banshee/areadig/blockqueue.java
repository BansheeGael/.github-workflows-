package com.banshee.areadig;  // ⬅️ ИЗМЕНЕНО: yourname → banshee

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.concurrent.ConcurrentLinkedQueue;

public class BlockQueue {
    private ConcurrentLinkedQueue<QueuedBlock> queue = new ConcurrentLinkedQueue<>();
    private long lastAction = 0;
    
    public void addBlock(int x, int y, int z, int delay) {
        queue.add(new QueuedBlock(x, y, z, System.currentTimeMillis() + delay));
    }
    
    public void processQueue() {
        long now = System.currentTimeMillis();
        if (now - lastAction < 40) return;
        
        QueuedBlock block = queue.peek();
        if (block != null && now >= block.time) {
            queue.poll();
            breakBlock(block.x, block.y, block.z);
            lastAction = now;
        }
    }
    
    private void breakBlock(int x, int y, int z) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null || mc.theWorld == null) return;
        
        // Проверяем, что блок не воздух и не бедрок
        if (mc.theWorld.isAirBlock(x, y, z)) return;
        
        String blockName = mc.theWorld.getBlock(x, y, z).getUnlocalizedName();
        if (blockName.contains("bedrock")) return;
        
        // Начало копки
        mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(
            C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, 
            x, y, z, 
            ForgeDirection.UP.ordinal()
        ));
        
        // Завершение копки  
        mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(
            C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK,
            x, y, z,
            ForgeDirection.UP.ordinal()
        ));
    }
    
    private static class QueuedBlock {
        final int x, y, z;
        final long time;
        
        QueuedBlock(int x, int y, int z, long time) {
            this.x = x; 
            this.y = y; 
            this.z = z;
            this.time = time;
        }
    }
}
