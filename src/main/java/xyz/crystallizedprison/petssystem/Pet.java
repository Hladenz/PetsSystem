package xyz.crystallizedprison.petssystem;

import xyz.crystallizedprison.petssystem.Objects.Tiers;
import xyz.crystallizedprison.petssystem.Pets.*;
import xyz.crystallizedprison.petssystem.Objects.PetStructor;

import java.util.ArrayList;
import java.util.List;

public enum Pet {

    OWL (new Owl()),
    FROG (new Frog()),
    MOLE (new Mole()),
    LUCKYJACK (new LuckyJack()),
    INVESTOR (new Investor()),
    MAFIA (new Mafia()),
    GAMBLER (new Gambler());

    public final PetStructor pet;

    Pet(PetStructor pet) {
        this.pet = pet;
    }

}
