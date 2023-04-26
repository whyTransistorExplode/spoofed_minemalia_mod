package net.bekmod.spoof.service;

import net.bekmod.spoof.MainMod;
import net.bekmod.spoof.entity.Envoy;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.TestOnly;

import java.util.ArrayList;

public class MessageProcess {

    private static final String ENVOY_MESSAGE_PREF1 = "[Envoys]";
    private static final String ENVOY_MESSAGE_PREF2 = "Envoy Landed at ";
    private static final String ENVOY_MESSAGE_PREF3 = " in world";


    public static void setVicinity(ArrayList<Envoy> envoys, ClientPlayerEntity player){
//        if (MinecraftClient.getInstance().player == null) return;
        for (Envoy envoy : envoys) {
            double aE = Math.abs(player.getX() - envoy.getCoordX());
            double bE = Math.abs(player.getZ() - envoy.getCoordZ());
            envoy.setProximity((int)(Math.sqrt(aE*aE + bE*bE)));
        }
    }
    @TestOnly
    public static void setVicinity(ArrayList<Envoy> envoys, double x, double z){
        for (Envoy envoy : envoys) {
            double aE = Math.abs(x - envoy.getCoordX());
            double bE = Math.abs(z- envoy.getCoordZ());
            envoy.setProximity((int)(Math.sqrt(aE*aE + bE*bE)));
        }
    }

    public static void processMessage(Text message){
        checkForEnvoy(message);
    }


    private static void checkForEnvoy(Text message){
//        MainMod.getInstance().setMessage(message.getString());

        if(message.getString().startsWith(ENVOY_MESSAGE_PREF1) && message.getString().contains(ENVOY_MESSAGE_PREF2)) {
            String str = message.getString().substring( ENVOY_MESSAGE_PREF2.length() + message.getString().indexOf(ENVOY_MESSAGE_PREF2)
                    , message.getString().indexOf(ENVOY_MESSAGE_PREF3));
            String[] coords = str.split(" ");

            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);
            int z = Integer.parseInt(coords[2]);
            Envoy envoy = new Envoy(x,y,z, 1, System.currentTimeMillis(), true);

            MainMod.getInstance().addEnvoy(envoy);
        }   // [Envoys] %arrow% Envoy Landed at %coord1% %coord2% %coord3% in world

    }

    private void checkForMaths(){

    }


}
