/*
 * Copyright (c) 2014-2023 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package net.bekmod.spoof.features;

import io.netty.buffer.Unpooled;
import net.bekmod.spoof.events.ConnectionPacketOutputListener;
import net.bekmod.spoof.mixin.CustomPayloadC2SPacketAccessor;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;


public final class VanillaSpoof {

	private static VanillaSpoof instance;

	public static VanillaSpoof getInstance(){
		if (instance == null) instance = new VanillaSpoof();
		return instance;
	}

	private VanillaSpoof()
	{

	}
	

	public void onSentConnectionPacket(ConnectionPacketOutputListener.ConnectionPacketOutputEvent event)
	{

		if(!(event.getPacket() instanceof CustomPayloadC2SPacketAccessor))
			return;
		
		CustomPayloadC2SPacketAccessor packet =
			(CustomPayloadC2SPacketAccessor)event.getPacket();
		
		if(packet.getChannel().getNamespace().equals("minecraft")
			&& packet.getChannel().getPath().equals("register"))
			event.cancel();
		
		if(packet.getChannel().getNamespace().equals("minecraft")
			&& packet.getChannel().getPath().equals("brand"))
			event.setPacket(new CustomPayloadC2SPacket(
				CustomPayloadC2SPacket.BRAND,
				new PacketByteBuf(Unpooled.buffer()).writeString("vanilla")));
		
		if(packet.getChannel().getNamespace().equals("fabric"))
			event.cancel();
	}

}
