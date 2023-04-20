package net.bekmod.spoof.mixin;

import net.bekmod.spoof.MainMod;
import net.minecraft.client.network.message.MessageHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MessageHandler.class)
public class GameMessageMixin {

    @Inject(method = "onGameMessage", at = @At("HEAD"))
    public void onGameMessage(Text message, boolean overlay, CallbackInfo ci){
//        MainMod.getInstance().setMessage(message.getString());
//        ClientMod.LOGGER.info(message.getString());

       MainMod.getInstance().threadedProcessMessage(message);
    }
}
