package net.bekmod.spoof;

import net.bekmod.spoof.entity.Envoy;
import net.bekmod.spoof.service.MessageProcess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Objects;

public class MainMod {

    private static MainMod instance;

    private Identifier compassTexture;
    private String chatMessage;
    private ArrayList<Envoy> envoys;
    private Thread thread;
    private boolean switchOverlay;
    private boolean isBuffedReaction;
    private boolean isMessageProcessorOn = true;
    private String buffedAnswer;

    public static MainMod getInstance() {
        if (instance == null) instance = new MainMod();
        return instance;
    }
    static void loadMainMod(){
        if (instance == null) instance = new MainMod();
    }
    private MainMod(){
        compassTexture = new Identifier("bekmod","texture/indicator_v2_compass_compact.png");
        envoys = new ArrayList<>();
    }

    public String getMessage() {
        return chatMessage;
    }
    public void setMessage(String message){
        chatMessage = message;
    }

    public ArrayList<Envoy> getEnvoys(){
        return (ArrayList<Envoy>) envoys.clone();
    }
    public ArrayList<Envoy> getProximitedEnvoys(){
        if(MinecraftClient.getInstance().player != null)
            MessageProcess.setVicinity(envoys, MinecraftClient.getInstance().player);
        return (ArrayList<Envoy>) envoys.clone();
    }

    public void clearEnvoy(){
        envoys.clear();
    }
    public void addEnvoy(Envoy envoy){
        envoys.add(envoy);
    }

//    public void threadedProcessMessage(Text message){
//        if (thread == null) thread = new Thread(() -> MessageProcess.processMessage(message));
//        thread.start();
//    }

    public boolean isSwitchOverlay() {
        return switchOverlay;
    }

    public void setSwitchOverlay() {
        this.switchOverlay = !this.switchOverlay;
    }

    public void performReaction(){
        if (!isBuffedReaction) return;

        isBuffedReaction = false;
        if (MinecraftClient.getInstance().player != null) {
            MainMod.getInstance().sendChatMessage(Text.of(buffedAnswer));
        }
    }
    public boolean isBuffedReaction(){
        return  isBuffedReaction;
    }


    public String getBuffedAnswer(){
        return buffedAnswer;
    }

    public void setBuffedAnswer(String buffedAnswer) {
        this.buffedAnswer = buffedAnswer;
        isBuffedReaction = true;
    }

    public boolean isMessageProcessorOn() {
        return isMessageProcessorOn;
    }

    public void switchMessageProc() {
        isMessageProcessorOn = !isMessageProcessorOn;
    }


    public void sendCommand(String text){
        if (MinecraftClient.getInstance().player != null) {
            if (text.startsWith("/"))
            MinecraftClient.getInstance().player.networkHandler.sendChatCommand(text.substring(1));
        }
    }

    public void sendChatMessage(Text text){

        if (MinecraftClient.getInstance().player != null) {
            MinecraftClient.getInstance().player.networkHandler.sendChatMessage(text.getString());
        }
    }
}
