package me.dragon.nightmareac;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import me.dragon.nightmareac.modules.movement.speed.SpeedA;
import me.dragon.nightmareac.utils.BukkitEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class NightmareAC extends JavaPlugin {
    private static Plugin plugin;
    public static final Logger LOGGER = LogManager.getLogger();
    @Override
    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(plugin));
        PacketEvents.getAPI().getSettings().checkForUpdates(false)
                .bStats(false)
                .reEncodeByDefault(false);
        PacketEvents.getAPI().load();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new BukkitEvents(),this);
        PacketEvents.getAPI().getEventManager().registerListener(new MovementPacket(), PacketListenerPriority.LOW);
        PacketEvents.getAPI().getEventManager().registerListener(new SpeedA(), PacketListenerPriority.LOW);

        PacketEvents.getAPI().init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        PacketEvents.getAPI().terminate();
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
