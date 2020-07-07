package com.elijahzawesome.mcufabric;

import net.minecraft.block.Block;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class MemorySlot extends Slot {
    public MemorySlot(Inventory inventory, int i, int j, int k) {
        super(inventory, i, j, k);
    }

    public boolean canInsert(ItemStack stack) {
        // Need to only allow memory sticks
        return true;
    }
}
