package net.bekmod.spoof.service;

import net.bekmod.spoof.ClientMod;
import net.bekmod.spoof.MainMod;
import net.bekmod.spoof.entity.Envoy;
import net.bekmod.spoof.entity.solutions.Result;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.TestOnly;

import java.util.ArrayList;

public class MessageProcess {

    private static final String ENVOY_MESSAGE_PREF1 = "[Envoys]";
    private static final String ENVOY_MESSAGE_PREF2 = "Envoy Landed at ";
    private static final String ENVOY_MESSAGE_PREF3 = " in world";
    private static final String REACTIONS_MESSAGE = "Reaction";
    private static final String REACTIONS_MESSAGE_TYPEFAST = "Type \"";
    private static final String REACTIONS_MESSAGE_MATH = "Solve ";
    private static final String REACTIONS_MESSAGE_MATH_1 = " first to get a reward!";
    private static final String REACTIONS_MESSAGE_TYPEFAST_1 = "\" first to win!";


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
       /* checking became unnecessary since compass spawn location shows the required coordinates all the time */
        // checkForEnvoy(message);
        checkForReactions(message.getString());
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

    private static void checkForReactions(String text){
        if(!text.contains(REACTIONS_MESSAGE)) return;

        int index = text.indexOf(REACTIONS_MESSAGE_MATH);
        /* math reaction*/
        if(index != -1){
            mathCalculate(text.substring(index + REACTIONS_MESSAGE_MATH.length(), text.indexOf(REACTIONS_MESSAGE_MATH_1)));
        return;
        }
        index = text.indexOf(REACTIONS_MESSAGE_TYPEFAST);
        if(index != -1){
            typeFast(text.substring(index + REACTIONS_MESSAGE_TYPEFAST.length(),text.indexOf(REACTIONS_MESSAGE_TYPEFAST_1)));
        }

    }

    private static void mathCalculate(String text){
        Result result = MathHelper.calculateText(text);
        System.out.println(result.isSuccess() + "   answer: " + result.getNumber());
        MainMod.getInstance().setBuffedAnswer(String.valueOf(((int)result.getNumber().getNumber())));
    }

    private static void typeFast(String text){
        MainMod.getInstance().setBuffedAnswer(text);
    }

}
