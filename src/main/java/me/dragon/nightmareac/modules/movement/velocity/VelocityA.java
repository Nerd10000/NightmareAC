package me.dragon.nightmareac.modules.movement.velocity;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import me.dragon.nightmareac.modules.Check;

public class VelocityA extends Check implements PacketListener {

    public VelocityA() {
        super("Velocity(A)", true);
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (WrapperPlayClientPlayerFlying.isFlying(event.getPacketType()) || event.getPacketType() == PacketType.Play.Client.PLAYER_FLYING ||
                event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){

        }
    }
}
