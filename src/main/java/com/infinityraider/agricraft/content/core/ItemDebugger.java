package com.infinityraider.agricraft.content.core;

import com.google.common.collect.ImmutableList;
import com.infinityraider.agricraft.util.debug.*;
import com.infinityraider.infinitylib.item.ItemDebuggerBase;
import com.infinityraider.infinitylib.utility.debug.DebugMode;
import java.util.List;

public class ItemDebugger extends ItemDebuggerBase {
    private static final List<DebugMode> MODES = ImmutableList.of(
            new DebugModeIrrigationNetwork(),
            new DebugModeCheckSoil(),
            new DebugModeClearGrass(),
            new DebugModeCoreInfo(),
            new DebugModeIGrowable(),
            new DebugModeDiffLight(),
            new DebugModeFillComponent()
    );

    public ItemDebugger() {
        super(true);
    }

    @Override
    protected List<DebugMode> getDebugModes() {
        return MODES;
    }
}
