package net.bekmod.spoof.screenOverlay;

import net.bekmod.spoof.MainMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class ScreenDrawing {

    private final static int locationX =  400;
    private final static int locationY = 10;

    public static void drawText( MatrixStack matrices){
        if (!MainMod.getInstance().isSwitchOverlay()) return;

        ClientWorld world = MinecraftClient.getInstance().world;
        ClientPlayerEntity player = MinecraftClient.getInstance().player;


        String text;//.setStyle(Style.EMPTY.withExclusiveFormatting(Formatting.GRAY));
        String text2;
        String text3 = null;
        Text text4;
        int color = 0xFF_FFFFFF;
        if (world != null) {
            BlockPos spawnPos = world.getSpawnPos();

            text = "spawn location: " + spawnPos.getX() + "  " + spawnPos.getY() + "  " + spawnPos.getZ();
            if (player != null) {
                double aE = Math.abs(player.getX() - spawnPos.getX());
                double bE = Math.abs(player.getZ() - spawnPos.getZ());

                int distance = (int)(Math.sqrt(aE*aE + bE*bE));

                int count = world.getPlayers().size();
                text2 = "distance: " + distance + "  Y:" + ((int)player.getY());
                text3 = "players: " + (count - 1);
                if (count > 2)
                    color = 0xFF_FC8C03;
                else if( count > 1)
                    color = 0xFF_F5D905;

                Screen.drawTextWithShadow(matrices, MinecraftClient.getInstance().textRenderer,
                        text3,   120 + locationX, 16 + locationY, color);
            }else text2 = "";


        }else {
            text = "spawn location may not have been initialized!";
            text2 = "";
        }

        if(MainMod.getInstance().isBuffedReaction()) {
            text4 = Text.literal("answer: " + MainMod.getInstance().getBuffedAnswer())
                    .setStyle(Style.EMPTY.withBold(true));
            Screen.drawTextWithShadow(matrices, MinecraftClient.getInstance().textRenderer,
                    text4, locationX, 32 + locationY, 0xFF_0BEB07);
        }
        /*
			ScreenDrawing.drawString(MainMod.matrices,String.valueOf(MainMod.getDistance()) + "blocks", HorizontalAlignment.LEFT,
					locationX+ 45, locationY + 10,100,0x101010);
*/

        Screen.drawTextWithShadow(matrices, MinecraftClient.getInstance().textRenderer,
                text,     locationX,  locationY, 0xFF_FFFFFF);
        Screen.drawTextWithShadow(matrices, MinecraftClient.getInstance().textRenderer,
                text2,    locationX, 16 + locationY, 0xFF_FFFFFF);



    }
}
