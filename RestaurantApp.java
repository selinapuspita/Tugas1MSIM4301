import java.util.ArrayList;
import java.util.Scanner;

class Menu {
    String name;
    double price;
    String category;

    public Menu(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}

public class RestaurantApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample menu items
        Menu[] menuItems = {
            new Menu("Nasi Goreng", 25000, "Makanan"),
            new Menu("Mie Goreng", 23000, "Makanan"),
            new Menu("Ayam Goreng", 30000, "Makanan"),
            new Menu("Sate Ayam", 30000, "Makanan"),
            new Menu("Es Teh", 8000, "Minuman"),
            new Menu("Kopi Hitam", 10000, "Minuman"),
            new Menu("Jus Jeruk", 20000, "Minuman"),
            new Menu("Es Coklat", 25000, "Minuman")
        };

        // Display menu
        System.out.println("Menu:");
        for (Menu item : menuItems) {
            System.out.println("- " + item.name + " (" + item.category + "): Rp " + item.price);
        }

        // Take orders
        ArrayList<Menu> orderList = new ArrayList<>();
        int maxOrders = 4;
        for (int i = 0; i < maxOrders; i++) {
            System.out.print("Masukkan nama menu (atau 'selesai' untuk mengakhiri): ");
            String menuName = scanner.nextLine();
            if (menuName.equalsIgnoreCase("selesai")) {
                break;
            }

            Menu selectedMenu = null;
            for (Menu item : menuItems) {
                if (item.name.equalsIgnoreCase(menuName)) {
                    selectedMenu = item;
                    break;
                }
            }

            if (selectedMenu != null) {
                orderList.add(selectedMenu);
            } else {
                System.out.println("Menu tidak ditemukan.");
            }
        }

        // Calculate total cost
        double totalCost = 0;
        for (Menu item : orderList) {
            totalCost += item.price;
        }

        // Apply discounts and taxes
        double tax = 0.1 * totalCost;
        double serviceCharge = 20000;
        double discount = 0;

        if (totalCost > 100000) {
            discount = 0.1 * totalCost;
        }

        // "Buy one get one free" logic for drinks
        int freeDrinkCount = 0;
        int numDrinksOrdered = 0;
        for (Menu item : orderList) {
            if (item.category.equalsIgnoreCase("Minuman")) {
                numDrinksOrdered++;
            }
        }

        if (totalCost > 50000) {
            freeDrinkCount = numDrinksOrdered / 2;  // Calculate number of free drinks
            // Assuming the cheapest drink is free, adjust as needed
            for (int i = 0; i < freeDrinkCount; i++) {
                for (int j = orderList.size() - 1; j >= 0; j--) {
                    Menu drink = orderList.get(j);
                    if (drink.category.equalsIgnoreCase("Minuman")) {
                        totalCost -= drink.price;
                        orderList.remove(j);
                        break;
                    }
                }
            }
        }

        totalCost += tax + serviceCharge - discount;

        // Print receipt
        System.out.println("\nStruk Pembayaran:");
        for (Menu item : orderList) {
            System.out.println("- " + item.name + " x 1: Rp " + item.price);
        }
        System.out.println("Total Biaya: Rp " + totalCost);
        System.out.println("Pajak (10%): Rp " + tax);
        System.out.println("Biaya Pelayanan: Rp " + serviceCharge);
        System.out.println("Diskon: Rp " + discount);
        if (freeDrinkCount > 0) {
            System.out.println("Selamat, Anda mendapat " + freeDrinkCount + " minuman gratis!");
        }
    scanner.close();
    }
}