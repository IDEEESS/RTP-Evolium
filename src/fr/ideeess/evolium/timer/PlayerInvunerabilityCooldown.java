package fr.ideeess.evolium.timer;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerInvunerabilityCooldown extends BukkitRunnable {
    Player player;

    public PlayerInvunerabilityCooldown(Player player) {
        this.player = player;
    }

    int t = 1;

    @Override
    public void run() {

        player.setInvulnerable(true);

        if (t == 0){
            player.setInvulnerable(false);
            cancel();
        }

    }
}
