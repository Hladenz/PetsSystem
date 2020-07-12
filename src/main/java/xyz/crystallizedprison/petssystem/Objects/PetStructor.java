package xyz.crystallizedprison.petssystem.Objects;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public interface PetStructor {


    public String ReturnName();
    public Tiers ReturnTier();
    public String ReturnDescription();
    public Material ReturnMaterial();

    public void Equip(Player p);
    public void UnEquip(Player p);



}
