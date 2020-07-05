package com.elijahzawesome.mcufabric;

import com.elijahzawesome.mcufabric.gui.MicroControllerGui;
import com.elijahzawesome.mcufabric.gui.MicroControllerGuiScreen;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

@SuppressWarnings({"NewExpressionSideOnly", "MethodCallSideOnly", "deprecation"})
public class MicroController extends HorizontalFacingBlock implements BlockEntityProvider {

    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");
    //public static final DirectionProperty FACING = DirectionProperty.of("facing");

    public MicroController(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(ACTIVE, false).with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(world.isClient)
            OpenGui(pos, (MicroControllerEntity)world.getBlockEntity(pos));

        return ActionResult.SUCCESS;
    }

    @Environment(EnvType.CLIENT)
    private void OpenGui(BlockPos pos, MicroControllerEntity entity) {
        MinecraftClient.getInstance().openScreen(new MicroControllerGuiScreen(new MicroControllerGui(pos, entity)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(ACTIVE);
        stateManager.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new MicroControllerEntity();
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 0.1875f, 1.0f);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing());
    }
}