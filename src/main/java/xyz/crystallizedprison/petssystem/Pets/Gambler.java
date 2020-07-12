package xyz.crystallizedprison.petssystem.Pets;


import me.clip.ezrankspro.events.RankupEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.crystallizedprison.petssystem.Objects.PetStructor;
import xyz.crystallizedprison.petssystem.Objects.Tiers;

public class Gambler implements PetStructor, Listener {

    @Override
    public String ReturnName() {
        return "Gambler";
    }

    @Override
    public Tiers ReturnTier() {
        return Tiers.RARE;
    }

    @Override
    public String ReturnDescription() {
        return "Chance To Double Rankup";
    }

    @Override
    public Material ReturnMaterial() {
        return Material.HUSK_SPAWN_EGG;
    }

    @Override
    public void Equip(Player p) {

    }

    @Override
    public void UnEquip(Player p) {

    }

}
