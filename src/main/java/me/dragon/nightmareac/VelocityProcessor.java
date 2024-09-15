package me.dragon.nightmareac;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityVelocity;

public class VelocityProcessor implements PacketListener {
    public static long taken;
    public double y,x,z,fromY,fromX,fromZ,deltaX,deltaY,deltaZ,velocity3d,velocityX,velocityY,velocityZ;


    @Override
    public void onPacketSend(PacketSendEvent event) {
        if (event.getPacketType() == PacketType.Play.Server.ENTITY_VELOCITY){
            taken = System.currentTimeMillis();
            WrapperPlayServerEntityVelocity wrapper = new WrapperPlayServerEntityVelocity(event);


        }
    }
}
