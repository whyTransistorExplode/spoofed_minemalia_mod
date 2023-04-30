package net.bekmod.spoof.screenOverlay;

import net.bekmod.spoof.MainMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class ScreenDrawing {

    private final static int locationX = 50;
    private final static int locationY = 20;

    public static void drawText( MatrixStack matrices){
        if (!MainMod.getInstance().isSwitchOverlay()) return;

        ClientWorld world = MinecraftClient.getInstance().world;
        ClientPlayerEntity player = MinecraftClient.getInstance().player;


        String text;//.setStyle(Style.EMPTY.withExclusiveFormatting(Formatting.GRAY));
        String text2;
        if (world != null) {
            BlockPos spawnPos = world.getSpawnPos();

            text = "spawn location: " + spawnPos.getX() + "  " + spawnPos.getY() + "  " + spawnPos.getZ();


            if (player != null) {
                double aE = Math.abs(player.getX() - spawnPos.getX());
                double bE = Math.abs(player.getZ() - spawnPos.getZ());

                int distance = (int)(Math.sqrt(aE*aE + bE*bE));

                int count = world.getPlayers().size();
                text2 = "distance: " + distance + "       Y:" + ((int)player.getY()) + "    players: " + count;

            }else text2 = "";
        }else {
            text = "spawn location may not have been initialized!";
            text2 = "";
        }
        /*
			ScreenDrawing.drawString(MainMod.matrices,String.valueOf(MainMod.getDistance()) + "blocks", HorizontalAlignment.LEFT,
					locationX+ 45, locationY + 10,100,0x101010);
*/

        Screen.drawStringWithShadow(matrices, MinecraftClient.getInstance().textRenderer,
                text,   35 + locationX, 5 + locationY, 0xFF_FFFFFF);
        Screen.drawStringWithShadow(matrices, MinecraftClient.getInstance().textRenderer,
                text2,   35 + locationX, 20 + locationY, 0xFF_FFFFFF);

    }
}
