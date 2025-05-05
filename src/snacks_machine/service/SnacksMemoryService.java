package snacks_machine.service;

import snacks_machine.domain.Snack;

import java.util.ArrayList;
import java.util.List;

public class SnacksMemoryService implements ISnacksService {
    private final List<Snack> snacks;

    public SnacksMemoryService() {
        snacks = new ArrayList<>();
        snacks.add(new Snack("Papas", 70));
        snacks.add(new Snack("Refresco", 50));
        snacks.add(new Snack("Sandwich", 120));
    }

    @Override
    public void addSnack(Snack snack) {
        snacks.add(snack);
        this.showSnacks();
    }

    @Override
    public void showSnacks() {
        StringBuilder snacksInventory = new StringBuilder();

        for (Snack snack : snacks) {
            snacksInventory.append(snack.toString()).append("\n");
        }

        System.out.println("--- Snacks in the inventory ---");
        System.out.println(snacksInventory);
    }

    @Override
    public List<Snack> getSnacks() {
        return snacks;
    }
}
