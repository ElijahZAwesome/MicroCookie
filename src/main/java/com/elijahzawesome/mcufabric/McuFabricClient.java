package com.elijahzawesome.mcufabric;

import com.elijahzawesome.mcufabric.gui.MicroControllerGui;
import com.elijahzawesome.mcufabric.gui.MicroControllerGuiScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class McuFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ScreenRegistry.<MicroControllerGui, MicroControllerGuiScreen>register(McuFabric.SCREEN_HANDLER_TYPE,
                (gui, inventory, title) -> new MicroControllerGuiScreen(gui, inventory.player, title));
    }
}
