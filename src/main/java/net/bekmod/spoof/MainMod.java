package net.bekmod.spoof;

import net.bekmod.spoof.entity.Envoy;
import net.bekmod.spoof.service.MessageProcess;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class MainMod {

    private static MainMod instance;

    private Identifier compassTexture;
    private String chatMessage;
    private ArrayList<Envoy> envoys;
    private Thread thread;

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

    public void clearEnvoy(){
        envoys.clear();
    }
    public void addEnvoy(Envoy envoy){
        envoys.add(envoy);
    }

    public void threadedProcessMessage(Text message){
        if (thread == null) thread = new Thread(() -> MessageProcess.processMessage(message));
        thread.start();
    }
}
