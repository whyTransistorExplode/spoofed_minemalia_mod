package net.bekmod.spoof.mixin;



import net.bekmod.spoof.features.VanillaSpoof;
import net.bekmod.spoof.listener.ConnectionPacketOutputListener;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin  {


    @ModifyVariable(at = @At("HEAD"),
            method = "send(Lnet/minecraft/network/packet/Packet;Lnet/minecraft/network/PacketCallbacks;)V")
    public Packet<?> modifyPacket(Packet<?> packet)
    {
        ConnectionPacketOutputListener.ConnectionPacketOutputEvent event =
                new ConnectionPacketOutputListener.ConnectionPacketOutputEvent(packet);
        VanillaSpoof.getInstance().onSentConnectionPacket(event);
//        events.add(event);
//        EventManager.fire(event);
        return event.getPacket();
    }
}
