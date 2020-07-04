package com.elijahzawesome.mcufabric;

import com.elijahzawesome.mcufabric.processor.McuProcessor;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;

public class MicroControllerEntity extends BlockEntity {

    public int number = 7;
    public McuProcessor processor;

    public MicroControllerEntity() {
        super(McuFabric.BlockEntity);
        processor = new McuProcessor();
    }

    // Serialize the BlockEntity
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);

        // Save the current value of the number to the tag
        tag.putInt("number", number);

        return tag;
    }

    // Deserialize the BlockEntity
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        number = tag.getInt("number");
    }
}