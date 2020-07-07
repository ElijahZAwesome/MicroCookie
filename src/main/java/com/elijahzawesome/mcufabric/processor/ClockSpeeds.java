package com.elijahzawesome.mcufabric.processor;

public enum ClockSpeeds {

    LEVEL1(10), LEVEL2(15), LEVEL3(20), LEVEL4(30);

    public final int speed;

    private ClockSpeeds(int speed) {
        this.speed = speed;
    }

}
