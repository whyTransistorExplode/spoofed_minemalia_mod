package net.bekmod.spoof.mixin;

import net.bekmod.spoof.event.KeyEvent;
import net.bekmod.spoof.event.KeyEventManager;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Inject(at = @At("HEAD"), method = "onKey(JIIII)V", cancellable = true)
    private void onKey(long windowHandle, int keyCode, int scanCode,
                         int action, int modifiers, CallbackInfo ci)
    {
        KeyEventManager.getInstance().fire(new KeyEvent(ci, keyCode));
    }
}
