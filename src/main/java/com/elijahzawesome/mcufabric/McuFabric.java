package com.elijahzawesome.mcufabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;

public class McuFabric implements ModInitializer {

    public static final MicroController MicroController = new MicroController(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
    public static BlockEntityType<MicroControllerEntity> BlockEntity;

    public static final Identifier UPDATE_MCU_PACKET = new Identifier("mcufabric", "microcontroller_update");

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier("mcufabric", "microcontroller"), MicroController);
        Registry.register(Registry.ITEM,
                new Identifier("mcufabric", "microcontroller"),
                new BlockItem(MicroController, new Item.Settings().group(ItemGroup.MISC))
        );
        BlockEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                "mcufabric:microcontroller",
                BlockEntityType.Builder.create(MicroControllerEntity::new, MicroController).build(null)
        );

        // Register packet listeners
        ServerSidePacketRegistry.INSTANCE.register(UPDATE_MCU_PACKET, (packetContext, attachedData) -> {
            // Get the BlockPos we put earlier in the IO thread
            BlockPos pos = attachedData.readBlockPos();
            packetContext.getTaskQueue().execute(() -> {
                // Execute on the main thread

                // ALWAYS validate that the information received is valid in a C2S packet!
                if(packetContext.getPlayer().world.canSetBlock(pos)){
                    // Turn to diamond
                    System.out.println("fuck moment");
                    //packetContext.getPlayer().world.setBlockState(pos, Blocks.DIAMOND_BLOCK.getDefaultState());
                }

            });
        });
    }
}
