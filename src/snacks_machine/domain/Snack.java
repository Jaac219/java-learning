package snacks_machine.domain;

import java.io.Serializable;
import java.util.Objects;

public class Snack implements Serializable {
    private static int snacksCount = 0;
    private final int snackId;
    private String name;
    private double price;

    public Snack() {
        this.snackId = ++Snack.snacksCount;
    }

    public Snack(String name, double price) {
        this();
        this.name = name;
        this.price = price;
    }

    public static int getSnacksCount() {
        return snacksCount;
    }

    public int getSnackId() {
        return this.snackId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Snack{" +
                "snackId=" + snackId +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Snack snack = (Snack) o;
        return snackId == snack.snackId && Double.compare(price, snack.price) == 0 && Objects.equals(name, snack.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(snackId, name, price);
    }

    public String writeSnack() {
        return this.snackId + "," + this.name + "," + this.price;
    }
}
