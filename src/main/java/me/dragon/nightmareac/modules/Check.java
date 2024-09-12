package me.dragon.nightmareac.modules;


import me.dragon.nightmareac.NightmareAC;
import me.dragon.nightmareac.utils.conf.CheckConfigManager;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;


public class Check {
    private  String name;
    private boolean enabled,experimental;
    private String desc;
    private int VLonFlag;
    private String debuginfo;
    private Player player;
    private final boolean setBack = NightmareAC.getPlugin().getConfig().getBoolean(String.format("checks.%s.setBack",name));
    private Location FirstFlag;
    private int flags;

    private double vl;
    public Check(String a,boolean experimental){
        this.name = a;
        this.experimental = experimental;
        this.enabled = NightmareAC.getPlugin().getConfig().getBoolean(String.format("checks.%s.enabled",name));
        this.desc = NightmareAC.getPlugin().getConfig().getString(String.format("checks.%s.description",name));
        this.VLonFlag = NightmareAC.getPlugin().getConfig().getInt(String.format("checks.%s.VLAfterFlag",name));

    }
    public void setDebuginfo(String a){
        this.debuginfo = a;
    }
    public void flagORaddVL(){
        vl++;
        FirstFlag = player.getLocation();

        if (vl > VLonFlag && enabled)  {
            Bukkit.getLogger().info("NightmareAC > %player% failed %check% [%VL%] %desc% | %error%"
                    .replace("%player%",player.getName())
                    .replace("%check%",name)
                    .replace("%VL%",String.valueOf(flags))
                    .replace("%desc%",desc)
                    .replace("%error%",debuginfo));


            for (Player p: Bukkit.getOnlinePlayers()) {
                if (p.hasPermission("nac.alert")){
                    var mm = MiniMessage.miniMessage();
                    flags++;
                    Component parsed = mm.deserialize(CheckConfigManager.ALERT_FORMAT
                            .replace("%player%",player.getName())
                            .replace("%check%",name)
                            .replace("%vl%",String.valueOf(flags))
                            .replace("%desc%",desc)
                            .replace("%error%",debuginfo));
                    BukkitAudiences.create(NightmareAC.getPlugin()).player(p).sendMessage(parsed);

                    vl = 0;
                }
            }
            if (setBack && flags > NightmareAC.getPlugin().getConfig().getInt(String.format("checks.%s.FlagonSetBack",name))  ){
                setBackNow();
            } else if (enabled && flags > NightmareAC.getPlugin().getConfig().getInt(String.format("checks.%s.FlagAfterPunish",name))
            && NightmareAC.getPlugin().getConfig().getBoolean(String.format("checks.%s.punish",name))) {
                for (String e : NightmareAC.getPlugin().getConfig().getStringList(String.format("checks.%s.run",name))){
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (e == "{baseKick}"){
                                player.kickPlayer(NightmareAC.getPlugin().getConfig().getString("PUNISH_MESSAGE"));
                            }
                            player.performCommand(e);
                        }
                    };
                }
            }

        }

    }public double DecreaseBy(double d){
        return  vl -= d;
    }
    public void pairWithPlayer(Player player){
        this.player = player;
    }
    public void setBackNow(){
        player.teleport(FirstFlag);

    }
}
