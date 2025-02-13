package com.infinityraider.agricraft.content;

import com.infinityraider.agricraft.content.core.BlockCropSticks;
import com.infinityraider.agricraft.content.core.BlockSeedAnalyzer;
import com.infinityraider.agricraft.content.core.CropStickVariant;
import com.infinityraider.agricraft.content.decoration.BlockGrate;
import com.infinityraider.agricraft.content.irrigation.BlockIrrigationChannelHollow;
import com.infinityraider.agricraft.content.irrigation.BlockIrrigationChannelNormal;
import com.infinityraider.agricraft.content.irrigation.BlockIrrigationTank;

public class AgriBlockRegistry {

    private static final AgriBlockRegistry INSTANCE = new AgriBlockRegistry();

    public static AgriBlockRegistry getInstance() {
        return INSTANCE;
    }

    // crop sticks
    public final BlockCropSticks crop_sticks_wood;
    public final BlockCropSticks crop_sticks_iron;
    public final BlockCropSticks crop_sticks_obsidian;

    // analyzer
    public final BlockSeedAnalyzer seed_analyzer;

    // irrigation
    public final BlockIrrigationTank tank;
    public final BlockIrrigationChannelNormal channel;
    public final BlockIrrigationChannelHollow channel_hollow;
    //public final BlockSprinkler sprinkler;

    // storage
    //public final BlockBase seed_storage;

    // Decoration
    public final BlockGrate grate;

    private AgriBlockRegistry() {
        this.crop_sticks_wood = new BlockCropSticks(CropStickVariant.WOOD);
        this.crop_sticks_iron = new BlockCropSticks(CropStickVariant.IRON);
        this.crop_sticks_obsidian = new BlockCropSticks(CropStickVariant.OBSIDIAN);

        this.seed_analyzer = new BlockSeedAnalyzer();

        this.tank = new BlockIrrigationTank();
        this.channel = new BlockIrrigationChannelNormal();
        this.channel_hollow = new BlockIrrigationChannelHollow();
        //this.sprinkler = new BlockSprinkler();

        this.grate = new BlockGrate();

        //this.seed_storage = new BlockSeedStorage();
    }
}
