package me.dragon.nightmareac.utils.conf;

import me.dragon.nightmareac.NightmareAC;

import java.util.HashMap;
import java.util.List;

public class CheckConfigManager {

    public static final String ALERT_FORMAT = NightmareAC.getPlugin().getConfig().getString("FLAG_FORMAT");
    public static final String PUNISH_FORMAT = NightmareAC.getPlugin().getConfig().getString("PUNISH_FORMAT");
    public static final String PUNISH_MESSAGE = NightmareAC.getPlugin().getConfig().getString("PUNISH_MESSAGE");

    //CHECKS
    //SPEED (A)
    public static final int speedA_buffer = NightmareAC.getPlugin().getConfig().getInt("checks.speedA.buffer");
    public static final int speedA_vlafterFlag = NightmareAC.getPlugin().getConfig().getInt("checks.speedA.VLAfterFlag");
    public static final String speedA_description = NightmareAC.getPlugin().getConfig().getString("checks.speedA.description");
    public static final boolean speedA_setback = NightmareAC.getPlugin().getConfig().getBoolean("checks.speedA.setBack");
    public static final int speedA_FlagSetBack = NightmareAC.getPlugin().getConfig().getInt("checks.speedA.FlagonSetBack");
    public static final boolean speedA_punish = NightmareAC.getPlugin().getConfig().getBoolean("checks.speedA.punish");
    public static final int speedA_FlagAfterPunish = NightmareAC.getPlugin().getConfig().getInt("checks.speedA.FlagAfterPunish");
    public static final List<String> speedA_runs = NightmareAC.getPlugin().getConfig().getStringList("checks.speedA.run");

    public static int buffer(String a) {
        return NightmareAC.getPlugin().getConfig().getInt("checks." + a + ".buffer");

    }

    public static int VLAfterFlag(String a) {
        return NightmareAC.getPlugin().getConfig().getInt("checks." + a + ".VLAfterFlag");

    }

    public static String desciption(String a) {
        return NightmareAC.getPlugin().getConfig().getString("checks." + a + ".description");
    }
    public static boolean setBack(String a) {
        return NightmareAC.getPlugin().getConfig().getBoolean("checks." + a + ".setback");
    }
    public static int FlagonSetBack(String a) {
        return NightmareAC.getPlugin().getConfig().getInt("checks." + a + ".FlagonSetBack");
    }
    public static boolean punish(String a) {
        return NightmareAC.getPlugin().getConfig().getBoolean("checks." + a + ".punish");
    }
    public static int FlagAfterPunish(String a) {
        return NightmareAC.getPlugin().getConfig().getInt("checks." + a + ".FlagAfterPunish");
    }
    public static List<String> run(String a) {
        return NightmareAC.getPlugin().getConfig().getStringList("checks." + a + ".run");
    }
    public static boolean enabled(String a) {
        return NightmareAC.getPlugin().getConfig().getBoolean("checks." + a + ".");
    }
}
