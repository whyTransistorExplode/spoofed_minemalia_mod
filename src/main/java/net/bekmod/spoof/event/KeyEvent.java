package net.bekmod.spoof.event;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

public class KeyEvent {

    private CallbackInfo ci;
    private Integer keycode;


    public CallbackInfo getCi() {
        return ci;
    }

    public KeyEvent(CallbackInfo ci, Integer keycode) {
        this.ci = ci;
        this.keycode = keycode;
    }

    public Integer getKeycode() {
        return keycode;
    }
}
