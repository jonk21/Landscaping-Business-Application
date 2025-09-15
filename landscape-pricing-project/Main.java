import java.sql.SQLException;
import java.util.*;

public class Main {
    private static Scanner scan = new Scanner(System.in);
    private static final double GRASS_PER_SQFT = 5.00;
    private static final double GRAVEL_PER_SQFT = 2.00;

    public static void main(String[] args) {
        int choice = 0;

        do {
            System.out.println("\nMenu");
            System.out.println("1) Add Customer");
            System.out.println("2) Edit Total Cost for Customer");
            System.out.println("3) Delete Customer");
            System.out.println("4) Display All Customers");
            System.out.println("5) Exit");

            while (choice == 0) {
                try {
                    System.out.print("\nEnter your choice: ");
                    choice = Integer.parseInt(scan.nextLine());
                } catch (NumberFormatException ex) {
                    System.out.println("\nError. Please use numbers only for the menu.");
                }
            }

            switch (choice) {
                case 1: addCustomer(); break;
                case 2: editTotalCost(); break;
                case 3: deleteCustomer(); break;
                case 4: displayAllCustomers(); break;
                case 5: System.out.println("\nGoodbye!"); break;
                default: System.out.println("\nError. Please select from the menu.");
            }

            if (choice != 5) pause();
            choice = 0; // reset for next loop
        } while (choice != 5);
    }

    private static void addCustomer() {
        try {
            System.out.print("Enter customer ID: ");
            int id = Integer.parseInt(scan.nextLine());

            System.out.print("Enter name: ");
            String name = scan.nextLine();

            System.out.print("Enter address: ");
            String address = scan.nextLine();

            System.out.print("Enter yard type (grass/gravel): ");
            String yardType = scan.nextLine();

            System.out.print("Enter length: ");
            double length = Double.parseDouble(scan.nextLine());

            System.out.print("Enter width: ");
            double width = Double.parseDouble(scan.nextLine());

            double costPerSqFt = yardType.equalsIgnoreCase("gravel") ? GRAVEL_PER_SQFT : GRASS_PER_SQFT;
            double totalCost = length * width * costPerSqFt;

            Customer c = new Customer(id, name, address, yardType, length, width, totalCost);
            DataIO db = new DataIO();
            db.add(c);
            System.out.println("\nCustomer added.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void editTotalCost() {
        try {
            System.out.print("Enter customer ID: ");
            int id = Integer.parseInt(scan.nextLine());

            System.out.print("Enter new total cost: ");
            double cost = Double.parseDouble(scan.nextLine());

            DataIO db = new DataIO();
            boolean updated = db.update(id, cost);
            if (updated) {
                System.out.println("Total cost updated.");
            } else {
                System.out.println("Customer ID not found.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteCustomer() {
        try {
            System.out.print("Enter customer ID to delete: ");
            int id = Integer.parseInt(scan.nextLine());

            DataIO db = new DataIO();
            boolean deleted = db.delete(id);
            if (deleted) {
                System.out.println("Customer deleted.");
            } else {
                System.out.println("Customer ID not found.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void displayAllCustomers() {
        try {
            DataIO db = new DataIO();
            ArrayList<Customer> list = db.getAll();
            for (Customer c : list) {
                System.out.println(c);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void pause() {
        System.out.print("\nPress ENTER to continue...");
        scan.nextLine();
        System.out.println("\n");
    }
}
