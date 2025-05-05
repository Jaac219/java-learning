package snacks_machine.presentation;

import snacks_machine.service.ISnacksService;
import snacks_machine.domain.Snack;
import snacks_machine.service.SnacksPersistenceService;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class SnacksMachine {
    public static void main(String[] args) {
        snacksMachine();
    }

    public static void snacksMachine() {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        List<Snack> products = new ArrayList<>();
        ISnacksService snacksService = new SnacksPersistenceService();

        System.out.println("*** Snacks Machine ***");
        snacksService.showSnacks();

        while (!exit) {
            try {
                var option = showMenu(scanner);
                exit = optionsExec(option, scanner, products, snacksService);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(); // Imprime salto de linea con cada iteración
            }
        }
    }

    private static int showMenu(Scanner scanner) {
        System.out.println("""
                1. Buy snack
                2. Show ticket
                3. Add new snack
                4. Exit
                Select one option: \s""");

        return Integer.parseInt(scanner.nextLine());
    }

    private static boolean optionsExec(int option, Scanner scanner, List<Snack> products, ISnacksService snacksService) {
        boolean exit = false;
        switch (option) {
            case 1 -> buySnack(scanner, products, snacksService);
            case 2 -> showTicket(products);
            case 3 -> addSnack(scanner, snacksService);
            default -> {
                System.out.println("Good bye!");
                exit = true;
            }
        }
        return exit;
    }

    private static void buySnack(Scanner scanner, List<Snack> products, ISnacksService snacksService) {
        System.out.print("Insert snack id: ");
        int snackId = Integer.parseInt(scanner.nextLine());
        boolean snackFound = false;

        for (Snack snack: snacksService.getSnacks()) {
            if (snackId == snack.getSnackId()) {
                products.add(snack);
                System.out.println("Ok, add snack: " + snack);
                snackFound = true;
                break;
            }
        }

        if (!snackFound) System.out.println("Snack id not found: " + snackId);
    }

    private static void showTicket(List<Snack> products) {
        StringBuilder ticket = new StringBuilder("*** Buy ticket ***");
        double total = 0.0;

        for (Snack product : products) {
            ticket.append("\n\t- ")
                    .append(product.getName())
                    .append(" - $")
                    .append(product.getPrice());

            total += product.getPrice();
        }

        ticket.append("\n\tTotal -> $").append(total);
        System.out.println(ticket);
    }

    private static void addSnack(Scanner scanner, ISnacksService snacksService) {
        System.out.print("Snack name: ");
        String name = scanner.nextLine();
        System.out.print("Snack price: ");
        double price = Double.parseDouble(scanner.nextLine());

        snacksService.addSnack(new Snack(name, price));
        System.out.println("Your snack was successfully added");
    }
}
