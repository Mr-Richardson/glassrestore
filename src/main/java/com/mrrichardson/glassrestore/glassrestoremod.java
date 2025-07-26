package com.mrrichardson.glassrestore;

import net.fabricmc.api.ModInitializer; import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents; import net.minecraft.block.Block; import net.minecraft.block.BlockState; import net.minecraft.block.Blocks; import net.minecraft.particle.BlockStateParticleEffect; import net.minecraft.particle.ParticleTypes; import net.minecraft.server.world.ServerWorld; import net.minecraft.util.math.BlockPos; import net.minecraft.util.math.Direction;

public class GlassRestoreMod implements ModInitializer {

@Override
public void onInitialize() {
    System.out.println("GlassRestoreMod geladen!");

    PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
        if (world.isClient) return;

        Block brokenBlock = state.getBlock();
        if (isRestorableGlass(brokenBlock)) {
            BlockState originalState = state;
            ServerWorld serverWorld = (ServerWorld) world;

            serverWorld.getServer().getScheduler().schedule(() -> {
                if (serverWorld.getBlockState(pos).isAir()) {
                    serverWorld.setBlockState(pos, originalState);

                    // Erzeuge Glaspartikel an der Stelle
                    serverWorld.spawnParticles(
                        new BlockStateParticleEffect(ParticleTypes.BLOCK, originalState),
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        20, 0.3, 0.3, 0.3, 0.01
                    );
                }
            }, java.time.Duration.ofSeconds(60));
        }
    });
}

private boolean isRestorableGlass(Block block) {
    return block == Blocks.GLASS ||
           block == Blocks.TINTED_GLASS ||
           block == Blocks.WHITE_STAINED_GLASS ||
           block == Blocks.LIGHT_GRAY_STAINED_GLASS ||
           block == Blocks.GRAY_STAINED_GLASS ||
           block == Blocks.BLACK_STAINED_GLASS ||
           block == Blocks.RED_STAINED_GLASS ||
           block == Blocks.ORANGE_STAINED_GLASS ||
           block == Blocks.YELLOW_STAINED_GLASS ||
           block == Blocks.LIME_STAINED_GLASS ||
           block == Blocks.GREEN_STAINED_GLASS ||
           block == Blocks.CYAN_STAINED_GLASS ||
           block == Blocks.LIGHT_BLUE_STAINED_GLASS ||
           block == Blocks.BLUE_STAINED_GLASS ||
           block == Blocks.PURPLE_STAINED_GLASS ||
           block == Blocks.MAGENTA_STAINED_GLASS ||
           block == Blocks.PINK_STAINED_GLASS ||
           block == Blocks.GLASS_PANE ||
           block == Blocks.WHITE_STAINED_GLASS_PANE ||
           block == Blocks.LIGHT_GRAY_STAINED_GLASS_PANE ||
           block == Blocks.GRAY_STAINED_GLASS_PANE ||
           block == Blocks.BLACK_STAINED_GLASS_PANE ||
           block == Blocks.RED_STAINED_GLASS_PANE ||
           block == Blocks.ORANGE_STAINED_GLASS_PANE ||
           block == Blocks.YELLOW_STAINED_GLASS_PANE ||
           block == Blocks.LIME_STAINED_GLASS_PANE ||
           block == Blocks.GREEN_STAINED_GLASS_PANE ||
           block == Blocks.CYAN_STAINED_GLASS_PANE ||
           block == Blocks.LIGHT_BLUE_STAINED_GLASS_PANE ||
           block == Blocks.BLUE_STAINED_GLASS_PANE ||
           block == Blocks.PURPLE_STAINED_GLASS_PANE ||
           block == Blocks.MAGENTA_STAINED_GLASS_PANE ||
           block == Blocks.PINK_STAINED_GLASS_PANE;
}

}
