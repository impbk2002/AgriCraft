package com.infinityraider.agricraft.content.irrigation;

import com.infinityraider.agricraft.AgriCraft;
import com.infinityraider.agricraft.api.v1.irrigation.IAgriIrrigationNode;
import com.infinityraider.agricraft.render.blocks.TileEntityIrrigationChannelRenderer;
import com.infinityraider.infinitylib.block.tile.InfinityTileEntityType;
import com.infinityraider.infinitylib.reference.Constants;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class TileEntityIrrigationChannel extends TileEntityIrrigationComponent implements IAgriIrrigationNode {
    private static final double MIN_Y = Constants.UNIT*6;
    private static final double MAX_Y = Constants.UNIT*10;

    public TileEntityIrrigationChannel() {
        super(AgriCraft.instance.getModTileRegistry().irrigation_channel, AgriCraft.instance.getConfig().channelCapacity(), MIN_Y, MAX_Y);
    }

    @Override
    protected void writeTileNBT(@Nonnull CompoundNBT tag) {

    }

    @Override
    protected void readTileNBT(@Nonnull BlockState state, @Nonnull CompoundNBT tag) {

    }

    @Override
    public Optional<IAgriIrrigationNode> getNode(Direction side) {
        return Optional.ofNullable(side == null || side.getAxis().isHorizontal() ? this : null);
    }

    @Override
    public boolean canConnect(@Nonnull IAgriIrrigationNode other, Direction from) {
        if(from.getAxis().isVertical()) {
            return false;
        }
        if(other instanceof TileEntityIrrigationComponent) {
            return this.isSameMaterial((TileEntityIrrigationComponent) other);
        }
        if(other instanceof TileEntityIrrigationTank.MultiBlockNode) {
            return this.isSameMaterial(((TileEntityIrrigationTank.MultiBlockNode) other).getMaterial());
        }
        return false;
    }

    @Override
    public boolean isSource() {
        return false;
    }

    @Override
    public boolean isSink() {
        // TODO: return true on this when a sprinkler is attached
        return false;
    }

    public static RenderFactory createRenderFactory() {
        return new RenderFactory();
    }

    private static class RenderFactory implements InfinityTileEntityType.IRenderFactory<TileEntityIrrigationChannel> {
        @Nullable
        @OnlyIn(Dist.CLIENT)
        public TileEntityIrrigationChannelRenderer createRenderer() {
            return new TileEntityIrrigationChannelRenderer();
        }
    }
}
