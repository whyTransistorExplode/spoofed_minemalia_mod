package net.bekmod.spoof.mixin;


import net.bekmod.spoof.screenOverlay.ScreenDrawing;
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
        ScreenDrawing.drawText(matrices);
    }


}
