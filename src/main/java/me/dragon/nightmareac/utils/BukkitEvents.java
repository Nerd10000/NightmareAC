package me.dragon.nightmareac.utils;

import me.dragon.nightmareac.modules.Check;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/*
YAY spartan type shit xd (No offense)
 */
public class BukkitEvents implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Check.vlmap.put(player, (double) 0);
        Check.flagMap.put(player,0);
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Check.vlmap.remove(player);
        Check.flagMap.remove(player);

    }

}
