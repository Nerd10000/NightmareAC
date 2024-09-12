package me.dragon.nightmareac.utils.scheduler;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;

public class TransactionManager implements PacketListener {

    public static long lastExchange;
    public static int predictedPing;

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {

    }
}
