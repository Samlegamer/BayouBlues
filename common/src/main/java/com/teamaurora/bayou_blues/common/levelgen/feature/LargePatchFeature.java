package com.teamaurora.bayou_blues.common.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

@SuppressWarnings("deprecation")
public class LargePatchFeature extends Feature<BlockStateConfiguration> {

    public LargePatchFeature(Codec<BlockStateConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<BlockStateConfiguration> featurePlaceContext) {
        WorldGenLevel level = featurePlaceContext.level();
        BlockStateConfiguration config = featurePlaceContext.config();
        BlockPos pos = featurePlaceContext.origin();
        int i = 0;
        for (BlockPos newPos : BlockPos.betweenClosed(pos.offset(-6, -6, -6), pos.offset(6, 6, 6))) {
            if (config.state.getBlock().canSurvive(config.state, level, newPos)) {
                if (featurePlaceContext.random().nextFloat() <= 1.0F - (newPos.distSqr(pos) / 72)) {
                    level.setBlock(newPos, config.state, 3);
                    i++;
                }
            }
        }
        return i > 0;
    }
}
