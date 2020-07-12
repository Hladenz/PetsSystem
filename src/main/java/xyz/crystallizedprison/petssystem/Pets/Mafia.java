package xyz.crystallizedprison.petssystem.Pets;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.crystallizedprison.petssystem.Objects.PetStructor;
import xyz.crystallizedprison.petssystem.Objects.Tiers;

public class Mafia implements PetStructor {

    @Override
    public String ReturnName() {
        return "Mafia";
    }

    @Override
    public Tiers ReturnTier() {
        return Tiers.RARE;
    }

    @Override
    public String ReturnDescription() {
        return "Double All the Money you give to your Gang and How much you recieve";
    }

    @Override
    public Material ReturnMaterial() {
        return Material.DOLPHIN_SPAWN_EGG;
    }

    @Override
    public void Equip(Player p) {

    }

    @Override
    public void UnEquip(Player p) {

    }


}
