package net.bekmod.spoof.screenOverlay;

import net.bekmod.spoof.MainMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ScreenDrawing {

    private final static int locationX = 50;
    private final static int locationY = 20;

    public static void drawText( MatrixStack matrices){
        if (!MainMod.getInstance().isSwitchOverlay()) return;

        World world = null;
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            world = player.getWorld();
        }
        Text text;//.setStyle(Style.EMPTY.withExclusiveFormatting(Formatting.GRAY));
        Text text2;
        if (world != null) {
            BlockPos spawnPos = world.getSpawnPos();
            text = Text.of("spawn location: " + spawnPos.getX() + "  " + spawnPos.getY() + "  " + spawnPos.getZ());
            int distance = (int)(Math.sqrt(spawnPos.getX()*spawnPos.getX() + spawnPos.getZ()*spawnPos.getZ()));
            text2 = Text.of("distance: " + distance + "my horizontal position: " + player.getY());

        }else {
            text = Text.of("spawn location may not have been initialized!");
            text2 = Text.empty();
        }
        /*
			ScreenDrawing.drawString(MainMod.matrices,String.valueOf(MainMod.getDistance()) + "blocks", HorizontalAlignment.LEFT,
					locationX+ 45, locationY + 10,100,0x101010);
*/

        Screen.drawCenteredText(matrices, MinecraftClient.getInstance().textRenderer,
                text,   35 + locationX, 5 + locationY, 0xFF_FFFFFF);
        Screen.drawCenteredText(matrices, MinecraftClient.getInstance().textRenderer,
                text2,   35 + locationX, 35 + locationY, 0xFF_FFFFFF);

    }
}