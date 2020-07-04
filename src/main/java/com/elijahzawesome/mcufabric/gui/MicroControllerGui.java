package com.elijahzawesome.mcufabric.gui;

import com.elijahzawesome.mcufabric.McuFabric;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.Vector3d;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;

@Environment(EnvType.CLIENT)
public class MicroControllerGui extends LightweightGuiDescription  {

    private BlockPos blockPos;

    public MicroControllerGui(BlockPos pos) {
        blockPos = pos;

        int screenWidth = MinecraftClient.getInstance().getWindow().getScaledWidth() - 50;
        int screenHeight = MinecraftClient.getInstance().getWindow().getScaledHeight() - 50;

        WPlainPanel root = new WPlainPanel();
        setRootPanel(root);
        //setFullscreen(true);
        root.setSize(screenWidth, screenHeight);

        WLabel title = new WLabel(new TranslatableText("mcufabric.microcontroller.gui.title"));
        root.add(title, 0, 0);

        WButton northModeBtn = new WButton(new TranslatableText("mcufabric.microcontroller.gui.north"));
        root.add(northModeBtn, 0, 28);
        northModeBtn.setSize(50, 50);
        northModeBtn.setOnClick(this::SendUpdatePacket);

        root.validate(this);
    }

    public void SendUpdatePacket() {
        // Pass the `BlockPos` information
        PacketByteBuf passedData = new PacketByteBuf(Unpooled.buffer());
        passedData.writeBlockPos(blockPos);
        ClientSidePacketRegistry.INSTANCE.sendToServer(McuFabric.UPDATE_MCU_PACKET, passedData);
    }

}
