package com.elijahzawesome.mcufabric.gui;

import com.elijahzawesome.mcufabric.McuFabric;
import com.elijahzawesome.mcufabric.MicroControllerEntity;
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
import net.minecraft.text.StringRenderable;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

@Environment(EnvType.CLIENT)
public class MicroControllerGui extends LightweightGuiDescription  {

    private BlockPos blockPos;

    private Boolean northEnabled = false;
    private Boolean southEnabled = false;
    private Boolean eastEnabled = false;
    private Boolean westEnabled = false;

    public MicroControllerGui(BlockPos pos, MicroControllerEntity entity) {
        blockPos = pos;
        northEnabled = entity.northPortEnabled;
        southEnabled = entity.southPortEnabled;
        eastEnabled = entity.eastPortEnabled;
        westEnabled = entity.westPortEnabled;

        int screenWidth = MinecraftClient.getInstance().getWindow().getScaledWidth() - 50;
        int screenHeight = MinecraftClient.getInstance().getWindow().getScaledHeight() - 50;

        WPlainPanel root = new WPlainPanel();
        setRootPanel(root);
        //setFullscreen(true);
        root.setSize(screenWidth, screenHeight);

        WLabel title = new WLabel(new TranslatableText("mcufabric.microcontroller.gui.title"));
        root.add(title, 0, 0);

        WButton northEnabledBtn = new WButton();
        UpdatePortButton(northEnabledBtn, "mcufabric.microcontroller.gui.north", northEnabled);
        root.add(northEnabledBtn, 0, 28);
        northEnabledBtn.setSize(70, 28);
        northEnabledBtn.setOnClick(() -> {
            northEnabled = !northEnabled;
            UpdatePortButton(northEnabledBtn, "mcufabric.microcontroller.gui.north", northEnabled);
        });

        WButton southEnabledBtn = new WButton();
        UpdatePortButton(southEnabledBtn, "mcufabric.microcontroller.gui.south", southEnabled);
        root.add(southEnabledBtn, 0, 48);
        southEnabledBtn.setSize(70, 28);
        southEnabledBtn.setOnClick(() -> {
            southEnabled = !southEnabled;
            UpdatePortButton(southEnabledBtn, "mcufabric.microcontroller.gui.south", southEnabled);
        });

        WButton eastEnabledBtn = new WButton();
        UpdatePortButton(eastEnabledBtn, "mcufabric.microcontroller.gui.east", eastEnabled);
        root.add(eastEnabledBtn, 0, 68);
        eastEnabledBtn.setSize(70, 28);
        eastEnabledBtn.setOnClick(() -> {
            eastEnabled = !eastEnabled;
            UpdatePortButton(eastEnabledBtn, "mcufabric.microcontroller.gui.east", eastEnabled);
        });

        WButton westEnabledBtn = new WButton();
        UpdatePortButton(westEnabledBtn, "mcufabric.microcontroller.gui.west", westEnabled);
        root.add(westEnabledBtn, 0, 88);
        westEnabledBtn.setSize(70, 28);
        westEnabledBtn.setOnClick(() -> {
            westEnabled = !westEnabled;
            UpdatePortButton(westEnabledBtn, "mcufabric.microcontroller.gui.west", westEnabled);
        });

        root.validate(this);
    }

    private void UpdatePortButton(WButton btn, String textKey, Boolean enabled) {
        btn.setLabel(
                new TranslatableText(textKey)
                        .append(new TranslatableText("mcufabric.microcontroller.gui." + (enabled ? "enabled" : "disabled"))
                        .formatted(enabled ? Formatting.GREEN : Formatting.RED))
        );
    }

    public void SendUpdatePacket() {
        // Pass the `BlockPos` information
        PacketByteBuf passedData = new PacketByteBuf(Unpooled.buffer());
        passedData.writeBlockPos(blockPos);
        passedData.writeBoolean(northEnabled);
        passedData.writeBoolean(southEnabled);
        passedData.writeBoolean(eastEnabled);
        passedData.writeBoolean(westEnabled);
        ClientSidePacketRegistry.INSTANCE.sendToServer(McuFabric.UPDATE_MCU_PACKET, passedData);
    }

}
