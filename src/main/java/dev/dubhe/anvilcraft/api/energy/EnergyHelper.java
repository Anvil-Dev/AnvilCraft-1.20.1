package dev.dubhe.anvilcraft.api.energy;

import dev.dubhe.anvilcraft.api.energy.forge.EnergyHelperImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

public class EnergyHelper {
    public static void insertEnergy(Level level, BlockPos pos, Direction direction, int amount) {
        EnergyHelperImpl.insertEnergy(level, pos, direction, amount);
    }
}
