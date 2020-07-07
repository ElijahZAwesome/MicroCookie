package com.elijahzawesome.mcufabric.processor;

public enum MemorySizes {

    LEVEL1(4096), LEVEL2(8192);

    public final int size;

    private MemorySizes(int size) {
        this.size = size;
    }

}
