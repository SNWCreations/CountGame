package snw.countgame;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public final class CountTimer extends BukkitRunnable {
    private int ms;
    private int secs;
    private boolean isShowOnly;
    private boolean isOnce;
    private int ticked;
    private int anInt;

    public void start() {
        isShowOnly = false;
        isOnce = true;
        runTaskTimer(CountGame.getInstance(), 0L, 1L);
    }

    @Override
    public void run() {
        int fadein = 0;
        if (ticked++ == 0) {
            fadein = 20;
        } else if (ticked >= 20) {
            isOnce = true;
            ms = ms + 50;
            if (ms >= 1000) {
                secs++;
                ms = 0;
            }
        }

        if (isShowOnly && anInt++ == 40) {
            cancel();
            return;
        }

        if (isOnce) {
            isOnce = false;
            String msstr = String.valueOf(ms);
            for (Player i : Bukkit.getOnlinePlayers()) {
                i.sendTitle(ChatColor.GRAY + "" + ChatColor.BOLD + "预测时间 " + (((secs == 3 && ms != 0 || secs > 3)) ? ChatColor.MAGIC : "") + "" + (secs < 10 ? "0" : "") + secs + "." + ((msstr.length() == 3) ? msstr.substring(0, 2) : "00"), "", fadein, 30, 10);
            }
        }
    }

    public void stop() {
        isShowOnly = true;
        Bukkit.broadcastMessage(ChatColor.RED + "计时停止。");
        new DisplayProcess(secs, ms).runTaskLater(CountGame.getInstance(), 140L);
    }
}
