package com.autoexcavate.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.ForgeHooks;

public class AreaDigger {
    private static final int AREA_WIDTH = 7;  // Width of digging area
    private static final int AREA_HEIGHT = 2; // Height of digging area (no depth)
    
    public void digAreaAroundPlayer(EntityPlayer player, World world) {
        if (world.isRemote) return;
        
        int playerX = MathHelper.floor_double(player.posX);
        int playerY = MathHelper.floor_double(player.posY);
        int playerZ = MathHelper.floor_double(player.posZ);
        
        // Determine player's facing direction
        int direction = MathHelper.floor_double((double)((player.rotationYaw * 4.0F) / 360.0F) + 0.5D) & 3;
        
        // Position digging area in front of player
        int digStartX = playerX;
        int digStartZ = playerZ;
        int digStartY = playerY; // Start at player's feet level
        
        // Adjust position based on direction
        switch (direction) {
            case 0: // South
                digStartZ += 2;
                break;
            case 1: // West
                digStartX -= 2;
                break;
            case 2: // North
                digStartZ -= 2;
                break;
            case 3: // East
                digStartX += 2;
                break;
        }
        
        // Calculate digging area boundaries
        int startX = digStartX - (AREA_WIDTH / 2);
        int startZ = digStartZ - (AREA_WIDTH / 2);
        int startY = digStartY;
        int endY = startY + AREA_HEIGHT - 1;
        
        // Dig the area
        for (int x = startX; x < startX + AREA_WIDTH; x++) {
            for (int z = startZ; z < startZ + AREA_WIDTH; z++) {
                for (int y = startY; y <= endY; y++) {
                    // Skip blocks under player's feet
                    if (Math.abs(x - playerX) <= 1 && Math.abs(z - playerZ) <= 1 && y <= playerY + 1) {
                        continue;
                    }
                    
                    digBlockAtPosition(world, x, y, z, player);
                }
            }
        }
    }
    
    private void digBlockAtPosition(World world, int x, int y, int z, EntityPlayer player) {
        Block block = world.getBlock(x, y, z);
        
        // Check if block can be dug
        if (block == null || block.isAir(world, x, y, z) || 
            block.getBlockHardness(world, x, y, z) < 0) {
            return;
        }
        
        // Get block metadata
        int metadata = world.getBlockMetadata(x, y, z);
        
        // Check if player can harvest the block (fixed syntax)
        if (ForgeHooks.canHarvestBlock(block, player, metadata)) {
            // Harvest the block with proper drops
            block.harvestBlock(world, player, x, y, z, metadata);
            
            // Set to air
            world.setBlockToAir(x, y, z);
            
            // Add break effects
            world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) + (metadata << 12));
        }
    }
    
    private ItemStack findBestToolInInventory(EntityPlayer player, Block block) {
        // Find best tool in inventory for this block
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            ItemStack stack = player.inventory.getStackInSlot(i);
            if (stack != null && stack.getItem().getDigSpeed(stack, block, 0) > 1.0F) {
                return stack;
            }
        }
        return null;
    }
}