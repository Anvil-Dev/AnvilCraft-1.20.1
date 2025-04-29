package dev.dubhe.anvilcraft.util;

import dev.dubhe.anvilcraft.util.forge.PlayerUtilImpl;
import net.minecraft.world.entity.player.Player;

public abstract class PlayerUtil {
    private PlayerUtil() {
    }

    public static boolean isFakePlayer(Player player) {
        return PlayerUtilImpl.isFakePlayer(player);
    }
}
