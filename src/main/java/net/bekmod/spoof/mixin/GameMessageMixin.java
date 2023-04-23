package net.bekmod.spoof.mixin;

import net.bekmod.spoof.MainMod;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public class GameMessageMixin {

    @Inject(at = @At("HEAD"),
            method = "addMessage(Lnet/minecraft/text/Text;I)V",
            cancellable = true)
    public void onGameMessage(Text chatText, int chatLineId, CallbackInfo ci){
        MainMod.getInstance().threadedProcessMessage(chatText);
    }
}
