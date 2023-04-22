package net.bekmod.spoof.mixin;

import net.bekmod.spoof.MainMod;
import net.minecraft.client.gui.hud.ChatHudListener;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;
import net.minecraft.util.ChatUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(ChatHudListener.class)
public class GameMessageMixin {

    @Inject(method = "onChatMessage", at = @At("RETURN"))
    public void onGameMessage(MessageType messageType, Text message, UUID sender, CallbackInfo ci){

        MainMod.getInstance().threadedProcessMessage(message);
    }
}
