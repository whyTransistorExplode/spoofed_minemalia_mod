package net.bekmod.spoof.mixin;

import net.bekmod.spoof.MainMod;
import net.bekmod.spoof.service.MessageProcess;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public class GameMessageMixin {

    @Inject(at = @At("HEAD"),
            method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V",
            cancellable = true)
    public void onGameMessage(Text message,
                              @Nullable MessageSignatureData signature,
                              @Nullable MessageIndicator indicator, CallbackInfo ci){
        if (MainMod.getInstance().isMessageProcessorOn())
        MessageProcess.processMessage(message);
//        MainMod.getInstance().threadedProcessMessage(chatText);
    }
}
