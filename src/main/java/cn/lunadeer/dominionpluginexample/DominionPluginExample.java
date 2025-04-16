package cn.lunadeer.dominionpluginexample;

import cn.lunadeer.dominion.api.DominionAPI;
import cn.lunadeer.dominion.api.dtos.DominionDTO;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class DominionPluginExample extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // 1. Get the DominionAPI instance
        try {
            dominionAPI = DominionAPI.getInstance();
            this.getLogger().info("Got Dominion instance");
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            this.getLogger().severe("Failed to get Dominion instance %s".formatted(e.getMessage()));
        }
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // 2. Use the DominionAPI instance to get the player's current dominion
        DominionDTO dominion = dominionAPI.getPlayerCurrentDominion(event.getPlayer());
        if (dominion == null) {
            this.getLogger().info("Player %s is not in a dominion".formatted(event.getPlayer().getName()));
        } else {
            this.getLogger().info("Player %s is in dominion %s".formatted(event.getPlayer().getName(), dominion.getName()));
        }
    }

    @Override
    public void onDisable() {
    }

    private DominionAPI dominionAPI;
}
