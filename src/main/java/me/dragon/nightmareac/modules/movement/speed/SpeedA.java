package me.dragon.nightmareac.modules.movement.speed;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import me.dragon.nightmareac.MovementPacket;
import me.dragon.nightmareac.NightmareAC;
import me.dragon.nightmareac.modules.Check;
import me.dragon.nightmareac.utils.conf.CheckConfigManager;
import org.bukkit.entity.Player;

public class SpeedA extends Check implements PacketListener {
    public SpeedA() {
        super("Speed(A)",false);
    }
    public double buffer;
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (WrapperPlayClientPlayerFlying.isFlying(event.getPacketType()) || event.getPacketType() == PacketType.Play.Client.PLAYER_FLYING ||
                event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION) {

            //NightmareAC.LOGGER.info(String.format("[debug] x:%s;lx:%s;deltaX:%s", MovementPacket.x,MovementPacket.lx,MovementPacket.deltaX));
            double predictions = ( MovementPacket.lastDeltaXZ - 0.08) * 0.91F;
            double difference = (MovementPacket.deltaXZ - predictions);
            //NightmareAC.LOGGER.info(String.format("prediction=%s;diff=%s;airTicks=%s",predictions,difference,MovementPacket.airTicks));
            //NightmareAC.LOGGER.info(String.format("[SpeedA] prediction=%s; accel=%s; diff=%s",predictions,MovementPacket.Acceleration,difference));
            pairWithPlayer((Player) event.getPlayer());
            if (difference > 0.099D){
                buffer++;
                if (buffer > 3){
                    setDebuginfo(String.format("diff=%s; airTicks=%s",difference,MovementPacket.airTicks));
                    buffer =0;
                    flagORaddVL();
                }
            }else {
                DecreaseBy(0.12);
                buffer -= 0.20;
            }

        }
    }
}
