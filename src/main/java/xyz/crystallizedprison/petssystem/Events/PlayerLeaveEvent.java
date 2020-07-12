package xyz.crystallizedprison.petssystem.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.crystallizedprison.petssystem.PetsSystem;

public class PlayerLeaveEvent implements Listener {

    PetsSystem main;

    public PlayerLeaveEvent(PetsSystem main) {
        this.main = main;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent event) {
        main.getPlayerList().remove(event.getPlayer());
    }
}
