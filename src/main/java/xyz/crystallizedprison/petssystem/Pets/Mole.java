package xyz.crystallizedprison.petssystem.Pets;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.crystallizedprison.petssystem.Objects.PetStructor;
import xyz.crystallizedprison.petssystem.Objects.Tiers;

public class Mole implements PetStructor {

    @Override
    public String ReturnName() {
        return "Mole";
    }

    @Override
    public Tiers ReturnTier() {
        return Tiers.COMMON;
    }

    @Override
    public String ReturnDescription() {
        return "Give you 100k for Each 100 Blocks you Mine";
    }

    @Override
    public Material ReturnMaterial() {
        return Material.RABBIT_SPAWN_EGG ;
    }

    @Override
    public void Equip(Player p) {

    }

    @Override
    public void UnEquip(Player p) {

    }
}
