package xyz.crystallizedprison.petssystem.Events;

import me.clip.ezrankspro.EZRanksPro;
import me.clip.ezrankspro.events.RankupEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import xyz.crystallizedprison.petssystem.Objects.PlayerInfo;
import xyz.crystallizedprison.petssystem.Pet;
import xyz.crystallizedprison.petssystem.PetsSystem;

public class GamblerController implements Listener {

    PetsSystem main;

    public GamblerController(PetsSystem main) {
        this.main = main;
    }

    public static Boolean getChance(double chance) {
        double random = Math.random() * 100;

        return random <= chance;
    }

    @EventHandler(ignoreCancelled = true)
    public void onRankup(RankupEvent event) {
        Player p = event.getPlayer();
        PlayerInfo playerInfo = main.GetPlayerInfo(p);

        if (playerInfo.getEgg() != null){
            if (playerInfo.getEgg().equals(Pet.GAMBLER)){
                if (getChance(45)){
                    if(!EZRanksPro.getAPI().isLastRank(p)){
                        EZRanksPro.getInstance().getActionHandler().executeRankupActions(p,EZRanksPro.getAPI().getCurrentRankup(p));
                    }
                }

            }
        }

    }
}
