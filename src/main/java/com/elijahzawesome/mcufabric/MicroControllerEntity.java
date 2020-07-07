package com.elijahzawesome.mcufabric;

import com.elijahzawesome.mcufabric.processor.McuProcessor;
import com.elijahzawesome.mcufabric.processor.MemorySizes;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;

import java.util.Optional;

public class MicroControllerEntity extends BlockEntity implements BlockEntityClientSerializable {

    public boolean northPortEnabled = false;
    public boolean southPortEnabled = false;
    public boolean eastPortEnabled = false;
    public boolean westPortEnabled = false;

    public McuProcessor processor;

    private MemorySizes[] memorySlots = new MemorySizes[8];

    public MicroControllerEntity() {
        super(McuFabric.BlockEntity);
        processor = new McuProcessor(0, Optional.empty());
    }

    public void SetPorts(boolean north, boolean south, boolean east, boolean west) {
        northPortEnabled = north;
        southPortEnabled = south;
        eastPortEnabled = east;
        westPortEnabled = west;
        markDirty();
        sync();
    }

    // Serialize the BlockEntity
    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);

        tag.putBoolean("northPortEnabled", northPortEnabled);
        tag.putBoolean("southPortEnabled", southPortEnabled);
        tag.putBoolean("eastPortEnabled", eastPortEnabled);
        tag.putBoolean("westPortEnabled", westPortEnabled);

        return tag;
    }

    // Deserialize the BlockEntity
    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);

        northPortEnabled = tag.getBoolean("northPortEnabled");
        southPortEnabled = tag.getBoolean("southPortEnabled");
        eastPortEnabled = tag.getBoolean("eastPortEnabled");
        westPortEnabled = tag.getBoolean("westPortEnabled");
    }

    @Override
    public void fromClientTag(CompoundTag tag) {
        fromTag(null, tag);
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        return toTag(tag);
    }
}