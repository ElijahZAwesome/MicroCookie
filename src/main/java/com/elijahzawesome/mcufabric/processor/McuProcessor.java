package com.elijahzawesome.mcufabric.processor;

import java.util.Optional;

public class McuProcessor {

    public byte R;
    public byte L;
    public byte U;
    public byte D;
    public byte A;
    public byte X;

    public boolean Z;
    public boolean O;
    public boolean C;
    public boolean W;
    public boolean S;

    public final int ClockSpeed;

    public final int MemorySize;
    public final byte[] Memory;

    public McuProcessor(int memUpgrade, Optional<ClockSpeeds> clockSpeedOpt) {
        ClockSpeed = clockSpeedOpt.orElse(ClockSpeeds.LEVEL1).speed;

        MemorySize = MemorySizes.LEVEL1.size + memUpgrade;
        Memory = new byte[MemorySize];

        Reset();
    }

    public void Reset() {
        // stub
    }

    public void step() {
        // stub
    }

}
