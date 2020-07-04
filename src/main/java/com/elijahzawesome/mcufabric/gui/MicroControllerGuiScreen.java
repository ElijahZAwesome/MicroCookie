package com.elijahzawesome.mcufabric.gui;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;

public class MicroControllerGuiScreen extends CottonClientScreen {

    private final MicroControllerGui Panel;

    public MicroControllerGuiScreen(MicroControllerGui description) {
        super((GuiDescription) description);
        Panel = description;
    }

    @Override
    public void onClose() {
        super.onClose();
        Panel.SendUpdatePacket();
    }
}
