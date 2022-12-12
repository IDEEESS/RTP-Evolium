package fr.ideeess.evolium.timer;

import fr.ideeess.evolium.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CooldownTimer extends BukkitRunnable {
    int x;
    int y;
    int z;
    Player player;
    Main main;

    public CooldownTimer(int x, int y, int z, Player player , Main main) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.player = player;
        this.main = main;
    }

    int t = 5;

    @Override
    public void run() {

        player.sendMessage(ChatColor.GOLD + "Téléportation dans " + ChatColor.RED + t + ChatColor.GOLD + " secondes");

        if (t == 0){
            player.teleport(new Location(player.getWorld(), x,y,z));
            player.sendMessage(ChatColor.GOLD + "Téléportaion en cours");
            player.sendMessage(ChatColor.GOLD + "Vous avez été téléporté en x: " + ChatColor.RED + x + ChatColor.GOLD + " , y: " + ChatColor.RED + y + ChatColor.GOLD + " , z: " + ChatColor.RED + z);
            PlayerInvunerabilityCooldown playerInvunerabilityCooldown = new PlayerInvunerabilityCooldown(player);
            playerInvunerabilityCooldown.runTaskTimer(main,0,20);
            cancel();
        }

        t--;

    }
}
