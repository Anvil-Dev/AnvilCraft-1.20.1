package dev.dubhe.anvilcraft.block.entity;

import dev.dubhe.anvilcraft.api.depository.DepositoryHolder;
import dev.dubhe.anvilcraft.api.depository.ItemDepository;
import dev.dubhe.anvilcraft.block.entity.forge.CrabTrapBlockEntityImpl;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

@Getter
public class CrabTrapBlockEntity extends BlockEntity implements DepositoryHolder {
    public CrabTrapBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    private final ItemDepository depository = new ItemDepository(9);

    public static @NotNull CrabTrapBlockEntity createBlockEntity(
        BlockEntityType<?> type, BlockPos pos, BlockState blockState
    ) {
        return CrabTrapBlockEntityImpl.createBlockEntity(type, pos, blockState);
    }

    public static void onBlockEntityRegister(BlockEntityType<CrabTrapBlockEntity> type) {
        CrabTrapBlockEntityImpl.onBlockEntityRegister(type);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Inventory", depository.serializeNbt());
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        depository.deserializeNbt(tag.getCompound("Inventory"));
    }
}
