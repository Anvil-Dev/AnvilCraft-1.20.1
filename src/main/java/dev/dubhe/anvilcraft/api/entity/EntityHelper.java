package dev.dubhe.anvilcraft.api.entity;

import dev.dubhe.anvilcraft.api.entity.forge.EntityHelperImpl;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class EntityHelper {
    public static @NotNull CompoundTag getCustomData(LivingEntity entity) {
        return EntityHelperImpl.getCustomData(entity);
    }
}
