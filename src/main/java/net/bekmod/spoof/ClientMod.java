package net.bekmod.spoof;

import net.bekmod.spoof.event.KeyEvent;
import net.bekmod.spoof.event.KeyEventManager;
import net.bekmod.spoof.gui.SimpleGUI;
import net.bekmod.spoof.gui.manager.ScreenManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientMod implements ClientModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("bekmod");

    private static KeyBinding openGUI;
    private static KeyBinding homeSpawn;
    private static KeyBinding pVault;
    private static KeyBinding cBack;
    private static KeyBinding cSpawn;
    private static KeyBinding autoPerformReaction;

    @Override
    public void onInitializeClient() {
//        instance = this;
        LOGGER.info("Hello cracked world!");
        MainMod.loadMainMod();

        setKeyBinds();
    }

    public void setKeyBinds(){

        openGUI = KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.bekmod.opengui",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_F6,
                        "key.category.gui"));
        homeSpawn = KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.bekmod.homespawn",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_H,
                        "key.category.key"));
        pVault = KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.bekmod.pvault",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_V,
                        "key.category.key"));
        cBack = KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.bekmod.cback",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_P,
                        "key.category.key"));
        cSpawn = KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.bekmod.cspawn",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_O,
                        "key.category.key"));
        autoPerformReaction = KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.bekmod.reactionbutton",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_KP_0,
                        "key.category.key"));

        /* Client tick events */
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGUI.wasPressed()) {
                if (client.player != null)
                    MinecraftClient.getInstance().setScreen(new ScreenManager(SimpleGUI.getInstance()));
            }
            while (cBack.wasPressed()){
                if(client.player != null)
                    client.player.sendMessage(Text.of("/back"));
            }
            while (cSpawn.wasPressed()){
                if(client.player != null)
                    client.player.sendMessage(Text.of("/spawn"));
            }
            while(autoPerformReaction.wasPressed()){
                if(client.player != null)
                    MainMod.getInstance().performReaction();
            }
        });

        /* custom registered key events */
        KeyEventManager.getInstance().registerEvent(
                (keyEvent) ->{
                    if(keyEvent.getKeycode() >= GLFW.GLFW_KEY_1 && keyEvent.getKeycode() <= GLFW.GLFW_KEY_9){
                        if(homeSpawn.isPressed()){
                            keyEvent.getCi().cancel();
                            MinecraftClient.getInstance().player.sendMessage(Text.of("/home " + (keyEvent.getKeycode() - 48)));
                        }
                        if(pVault.isPressed()){
                            keyEvent.getCi().cancel();
                            MinecraftClient.getInstance().player.sendMessage(Text.of("/pv " + (keyEvent.getKeycode() - 48)));
                        }
                    }
                }
        );
    }

}
