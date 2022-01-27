package snw.countgame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public final class DisplayProcess extends BukkitRunnable {
    private final int secs;
    private final int ms;

    public DisplayProcess(int secs, int ms) {
        this.secs = secs;
        this.ms = ms;
    }

    @Override
    public void run() {
        String msstr = String.valueOf(ms);
        for (Player i : Bukkit.getOnlinePlayers()) {
            i.sendTitle(ChatColor.GRAY + "" + ChatColor.BOLD + "预测时间 " + secs + "." + ((msstr.length() == 3) ? msstr.substring(0, 2) : "00"), "", 20, 100, 10);
        }
    }
}
