package me.dragon.nightmareac;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import org.bukkit.entity.Player;

public class MovementPacket implements PacketListener {
    public static double x,y,z,lx,ly,lz,deltaX,deltaZ,deltaY,deltaXZ,lastDeltaXZ;
    public static int airTicks,groundTicks;
    public static float Acceleration,lastAccel;
    public static boolean isInAir;
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (WrapperPlayClientPlayerFlying.isFlying(event.getPacketType()) || event.getPacketType() == PacketType.Play.Client.PLAYER_FLYING ||
        event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION || event.getPacketType() == PacketType.Play.Client.PLAYER_ROTATION||
                event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION ) {

            WrapperPlayClientPlayerFlying wrapper = new WrapperPlayClientPlayerFlying(event);
            lx =x;
            ly = y;
            lz = z;
            lastAccel = Acceleration;
            lastDeltaXZ = deltaXZ;
            x = wrapper.getLocation().getX();
            y = wrapper.getLocation().getY();
            z = wrapper.getLocation().getZ();
            Player player = (Player) event.getPlayer();

            deltaX = x - lx;
            deltaY = y - ly;
            deltaZ = z - lz;
            deltaXZ = Math.hypot(deltaX,deltaZ);
            Acceleration = (float) (deltaXZ - lastDeltaXZ)  * 0.91f;
            if (!player.isOnGround()){
                airTicks++;
                groundTicks = 0;
            }else{
                airTicks = 0;
                groundTicks++;
            }

            if (airTicks > 2){
                isInAir = true;
            }
        }
            // Handle movement packet logic here
    }
}
