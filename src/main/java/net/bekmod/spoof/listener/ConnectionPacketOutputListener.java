package net.bekmod.spoof.listener;

import net.bekmod.spoof.event.CancellableEvent;
import net.bekmod.spoof.event.Listener;
import net.minecraft.network.packet.Packet;
import net.bekmod.spoof.events.PacketOutputListener;

import java.util.ArrayList;


public interface ConnectionPacketOutputListener extends Listener
{
    public void onSentConnectionPacket(ConnectionPacketOutputEvent event);


    public static class ConnectionPacketOutputEvent
            extends CancellableEvent<ConnectionPacketOutputListener>
    {
        private Packet<?> packet;

        public ConnectionPacketOutputEvent(Packet<?> packet)
        {
            this.packet = packet;
        }

        public Packet<?> getPacket()
        {
            return packet;
        }

        public void setPacket(Packet<?> packet)
        {
            this.packet = packet;
        }

        @Override
        public void fire(ArrayList<ConnectionPacketOutputListener> listeners)
        {
            for(ConnectionPacketOutputListener listener : listeners)
            {
                listener.onSentConnectionPacket(this);

                if(isCancelled())
                    break;
            }
        }

        @Override
        public Class<ConnectionPacketOutputListener> getListenerType()
        {
            return ConnectionPacketOutputListener.class;
        }
    }
}