package net.bekmod.spoof.mixin;


import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {


    @Inject(at = @At("RETURN"), method = "render")
    private void onRender(MatrixStack matrices, float tickDelta, CallbackInfo info) {
        drawTextureC();
    }


    public void drawTextureC() {
//		ScreenDrawing.texturedRect(matrices, 0, 0, 510, 30, MainMod.getCompassTexture(), 0, 0, 1f, 1f, 0xFF_FFFFFF);
//        if (MainMod.isIsLooking()) {
//            nm1 = 0.0f;
//            nm2 = 1;
//            if (MainMod.getCompassTextureUID() > 0) {
//                nm2 = MainMod.getCompassTextureUID() / 17f;
//                nm1 = (MainMod.getCompassTextureUID() - 1) / 17f;
//            }
//            ScreenDrawing.texturedRect(matrices, locationX, locationY, 25, 25, MainMod.getCompassTexture(), nm1, 0, nm2, 1f, 0xFF_FFFFFF);
//            Text text = new LiteralText(getDistance() + " blocks " + (MainMod.isPlayerHidden() ? " Hidden" : "")).setStyle(Style.EMPTY.withExclusiveFormatting(Formatting.GRAY));
//
///*
//			ScreenDrawing.drawString(MainMod.matrices,String.valueOf(MainMod.getDistance()) + "blocks", HorizontalAlignment.LEFT,
//					locationX+ 45, locationY + 10,100,0x101010);
//*/
//            Screen.drawCenteredText(matrices, MinecraftClient.getInstance().textRenderer, text, locationX + 65, locationY + 10, 0xFF_FFFFFF);

//        }
    }
}
