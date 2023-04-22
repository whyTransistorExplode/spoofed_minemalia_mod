package net.bekmod.spoof.mixin;

import net.bekmod.spoof.MainMod;
import net.bekmod.spoof.service.MessageProcess;
import net.minecraft.client.network.message.MessageHandler;
import net.minecraft.data.Main;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MessageHandler.class)
public class GameMessageMixin {

    @Inject(method = "onGameMessage", at = @At("HEAD"))
    public void onGameMessage(Text message, boolean overlay, CallbackInfo ci){


        MainMod.getInstance().threadedProcessMessage(message);
    }
}
