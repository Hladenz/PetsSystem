package xyz.crystallizedprison.petssystem.Pets;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.crystallizedprison.petssystem.Objects.PetStructor;
import xyz.crystallizedprison.petssystem.Objects.Tiers;

public class Frog implements PetStructor {

    @Override
    public String ReturnName() {
        return "Frog";
    }

    @Override
    public Tiers ReturnTier() {
        return Tiers.COMMON;
    }

    @Override
    public String ReturnDescription() {
        return "When Equipped it give You Jump boost";
    }

    @Override
    public Material ReturnMaterial() {
        return Material.PARROT_SPAWN_EGG;
    }

    @Override
    public void Equip(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 2, true, false));
    }

    @Override
    public void UnEquip(Player p) {
        p.removePotionEffect(PotionEffectType.JUMP);
    }
}
