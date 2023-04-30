package net.bekmod.spoof.mixin;


import net.bekmod.spoof.screenOverlay.ScreenDrawing;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {


//    @Inject(at = @At("RETURN"), method = "render")
//    private void onRender(MatrixStack matrices, float tickDelta, CallbackInfo info) {
//        ScreenDrawing.drawText(matrices);
//
//    }

    @Inject(
            at = @At(value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/systems/RenderSystem;enableBlend()V",
                    remap = false,
                    ordinal = 3),
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;F)V")
    private void onRender(MatrixStack matrixStack, float partialTicks,
                          CallbackInfo ci)
    {
        if(MinecraftClient.getInstance().options.debugEnabled)
            return;

        ScreenDrawing.drawText(matrixStack);
    }


}
