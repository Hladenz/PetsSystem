package xyz.crystallizedprison.petssystem.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import xyz.crystallizedprison.petssystem.Objects.PlayerInfo;
import xyz.crystallizedprison.petssystem.PetsSystem;

import java.util.EventListener;

public class PlayerJoinEvent implements EventListener, Listener {

    PetsSystem main;

    public PlayerJoinEvent(PetsSystem main) {
        this.main = main;
    }

    @EventHandler(ignoreCancelled = true,priority = EventPriority.HIGHEST)
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        PlayerInfo playerInfo = main.GetPlayerInfo(event.getPlayer());
        if (playerInfo.getPet() != null){
            playerInfo.getPet().pet.Equip(playerInfo.getPlayer());
        }
        main.getPlayerList().put(playerInfo.getPlayer(),playerInfo);

    }
}
