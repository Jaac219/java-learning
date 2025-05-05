package snacks_machine.service;

import snacks_machine.domain.Snack;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SnacksPersistenceService implements ISnacksService {
    private final String FILE_NAME = "snacks.txt";
    private List<Snack> snacks = new ArrayList<>();

    public SnacksPersistenceService() {
        File file = new File(this.FILE_NAME);
        boolean fileExists = false;

        try {
            fileExists = file.exists();
            if (fileExists) {
                this.snacks = this.getSnacksFromFile();
            } else {
                PrintWriter output = new PrintWriter(new FileWriter(file));
                output.close();
                System.out.println("File created.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (!fileExists) this.uploadInitialSnacks();
    }

    @Override
    public void addSnack(Snack snack) {
        this.snacks.add(snack);
        this.addSnackToFile(snack);
    }

    @Override
    public List<Snack> getSnacks() {
        return this.snacks;
    }

    @Override
    public void showSnacks() {
        System.out.println("--- INVENTORY SNACKS --- ");
        StringBuilder inventorySnacks = new StringBuilder();
        for(Snack snack: this.snacks){
            inventorySnacks.append(snack.toString()).append("\n");
        }
        System.out.println(inventorySnacks);
    }

    private List<Snack> getSnacksFromFile() {
        List<Snack> localSnacks = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(this.FILE_NAME));
            for (String line : lines) {
                String[] lineSnack = line.split(",");

                int snackId = Integer.parseInt(lineSnack[0]);
                String name = lineSnack[1];
                double price = Double.parseDouble(lineSnack[2]);

                localSnacks.add(new Snack(name, price));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return localSnacks;
    }

    private void uploadInitialSnacks() {
        this.addSnack(new Snack("Papas", 70));
        this.addSnack(new Snack("Refresco", 50));
        this.addSnack(new Snack("Sandwich", 120));
    }

    private void addSnackToFile(Snack snack) {
        boolean attach = false;
        File file = new File(this.FILE_NAME);

        try {
            attach = file.exists();
            PrintWriter output = new PrintWriter(new FileWriter(file, attach));
            output.println(snack.writeSnack());
            output.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
