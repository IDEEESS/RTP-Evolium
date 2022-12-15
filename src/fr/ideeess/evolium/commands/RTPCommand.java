package fr.ideeess.evolium.commands;

import fr.ideeess.evolium.main.Main;
import fr.ideeess.evolium.timer.CooldownTimer;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RTPCommand implements CommandExecutor {
    Main main;
    int x = 0;
    int y = 64;
    int z = 0;

    public RTPCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player){
            if (player.hasPermission("evolium.randomtp.rtp")){
                World world = player.getWorld();
                if (!main.getCooldownCMD().containsKey(player)) {
                    int whatX = 1 + (int) (Math.random() * ((2 - 1) + 1)); // Génere un nombre en 1 et 2 pour savoir si c'est négatif ou non
                    int whatZ = 1 + (int) (Math.random() * ((2 - 1) + 1)); // Génere un nombre en 1 et 2 pour savoir si c'est négatif ou non

                    if (whatX == 1) {
                        this.x = 500 + (int) (Math.random() * ((5000 - 500) + 1)); // Génération du X positif
                    } else {
                        this.x = -500 + (int) (Math.random() * ((-5000 + 500) - 1)); // Génération du X négatif
                    }

                    if (whatZ == 1) {
                        this.z = 500 + (int) (Math.random() * ((5000 - 500) + 1)); // Génération du Z positif
                    } else {
                        this.z = -500 + (int) (Math.random() * ((-5000 + 500) - 1)); // Génération du Z négatif
                    }
                    if (world.getBlockAt(x, y, z).getType() != Material.AIR) {

                        for (int i = 64; i > 320; i++) {
                            Bukkit.broadcastMessage(String.valueOf(i));

                            if (world.getBlockAt(x, y, z).getType() == Material.AIR) {
                                this.y = i;
                                break;
                            }
                        }
                    }   //Ajout du joueur dans la HashMap

                    int seconds = Math.toIntExact(System.currentTimeMillis() / 1000);

                    main.getCooldownCMD().put(player, seconds + 1800); // 1800 secondes car 30 minutes = 1800 secondes

                    //Cooldown et puis Téléportation
                    CooldownTimer cooldownTimer = new CooldownTimer(x, y, z, player, main);
                    cooldownTimer.runTaskTimer(main, 0, 20);
                    return true;
                    }

                    //Vérification si cooldown passé
                    int millis = Math.toIntExact(System.currentTimeMillis() / 1000);
                    int playerCooldown = main.getCooldownCMD().get(player);

                    if (millis > playerCooldown){
                        main.getCooldownCMD().remove(player);
                        player.sendMessage(ChatColor.GREEN + "Merci de réessayer");
                        return true;
                    }

                int timeRestant = playerCooldown - millis;

                int minutes = Math.toIntExact(timeRestant / 60);
                int secondes = Math.toIntExact(timeRestant - minutes *60);
                player.sendMessage(ChatColor.RED + "[ERREUR] Il vous reste " + ChatColor.GOLD + minutes + ChatColor.RED +" minutes et " + ChatColor.GOLD + secondes + ChatColor.RED + " secondes avant de pouvoir vous téléporter aléatoirement");
                return false;
            }
            player.sendMessage(ChatColor.RED + "[ERREUR] Vous n'avez pas la permission requise pour vous téléporter aléatoirement");
            return false;
        }
        sender.sendMessage(ChatColor.RED + "[ERREUR] Vous n'êtes pas un joueur");
        return false;
    }
}