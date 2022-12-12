package fr.ideeess.evolium.main;

import fr.ideeess.evolium.commands.RTPCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Main extends JavaPlugin {
    private HashMap<Player,Integer> cooldownCMD = new HashMap<Player, Integer>();
    @Override
    public void onEnable() {
        this.getCommand("rtp").setExecutor(new RTPCommand(this));
    }

    public HashMap<Player, Integer> getCooldownCMD() {
        return cooldownCMD;
    }
}
