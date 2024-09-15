package me.dragon.nightmareac.modules.movement.speed;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.potion.Potion;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import me.dragon.nightmareac.MovementPacket;
import me.dragon.nightmareac.NightmareAC;
import me.dragon.nightmareac.modules.Check;
import me.dragon.nightmareac.utils.conf.CheckConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class SpeedA extends Check implements PacketListener {
    public SpeedA() {
        super("Speed(A)",false);
    }
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (WrapperPlayClientPlayerFlying.isFlying(event.getPacketType()) || event.getPacketType() == PacketType.Play.Client.PLAYER_FLYING ||
                event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION) {
            Player player = (Player) event.getPlayer();
            //NightmareAC.LOGGER.info(String.format("[debug] x:%s;lx:%s;deltaX:%s", MovementPacket.x,MovementPacket.lx,MovementPacket.deltaX));
            double deltaXZ = MovementPacket.deltaXZ;
            double predictions = deltaXZ * 0.91F;
            double difference = Math.abs(deltaXZ - predictions);
            NightmareAC.LOGGER.info(String.format("diff=%s;airTicks=%s", difference, MovementPacket.airTicks));
            //NightmareAC.LOGGER.info(String.format("[SpeedA] prediction=%s; accel=%s; diff=%s",predictions,MovementPacket.Acceleration,difference));
            pairWithPlayer((Player) event.getPlayer());

            if (difference > 0.026D && MovementPacket.airTicks > 2) {
                setDebuginfo(String.format("diff=%s; airTicks=%s; flags=%s", difference, MovementPacket.airTicks,returnVL()));
                Bukkit.getScheduler().runTask(NightmareAC.getPlugin(),() -> {
                   flagORaddVL();
                });
                DecreaseBy(0.17);
            } else {
                DecreaseBy(0.58);
            }
        }
    }
}
