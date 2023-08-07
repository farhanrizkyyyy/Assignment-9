import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GudangController controller = new GudangController();
        String[] menu = {"Show Items", "Add Item", "Add Category", "Update Item Qty"};

        System.out.println("+========================================================+");
        System.out.println("|                                                        |");
        System.out.println("|                   Inventory App MBY                    |");
        System.out.println("|                                                        |");
        System.out.println("+========================================================+\n");

        while (true) {
            for (int i = 0; i < menu.length; i++) {
                System.out.println(i + 1 + ". " + menu[i]);
            }
            System.out.println("\n0. Exit");
            System.out.print("Select menu: ");
            try {
                int selectedMenu = scanner.nextInt();
                scanner.nextLine();
                menuNavigation(selectedMenu, scanner, controller);
            } catch (InputMismatchException e) {
                System.out.println("Input not valid.");
                scanner.next();
                printDivider();
            }
        }
    }

    private static void menuNavigation(int selectedMenu, Scanner scanner, GudangController controller) {
        switch (selectedMenu) {
            case 1 -> { // show item list
                if (controller.isItemsEmpty()) System.out.println("There is no item in your inventory.");
                else controller.showItems(true);
                printDivider();
            }
            case 2 -> { // add item
                if (controller.isCategoriesEmpty()) {
                    System.out.println("You cannot add an item with no category, please add category first.");
                    addCategoryMenu(controller, scanner);
                    addItemMenu(controller, scanner);
                } else {
                    addItemMenu(controller, scanner);
                }
                printDivider();
            }
            case 3 -> { // add category
                addCategoryMenu(controller, scanner);
                printDivider();
            }
            case 4 -> { // update item
                if (controller.isItemsEmpty()) {
                    System.out.println("There is no item in your inventory.");
                    if (controller.isCategoriesEmpty()) {
                        System.out.println("You cannot add an item with no category, please add category first.");
                        addCategoryMenu(controller, scanner);
                    }
                    addItemMenu(controller, scanner);
                    updateItemQtyMenu(controller, scanner);
                } else {
                    updateItemQtyMenu(controller, scanner);
                }
                printDivider();
            }
            case 0 -> { // exit program
                scanner.close();
                System.exit(0);
            }
            default -> {
                System.out.println("Menu doesn't exist.\n");
                printDivider();
            }
        }
    }

    private static void printDivider() {
        System.out.println();
    }

    private static void updateItemQtyMenu(GudangController controller, Scanner scanner) {
        int selectedCategory, selectedItem, newQty;
        controller.showCategories();
        System.out.print("Select category: ");
        while (true) {
            try {
                selectedCategory = scanner.nextInt();
                if (selectedCategory > 0 && selectedCategory <= controller.getCategoriesSize()) break;
                else throw new Exception();
            } catch (Exception e) {
                System.out.println("Category doesn't exist.");
                scanner.next();
            }
        }
        controller.showItems(false, selectedCategory);
        System.out.print("Select item: ");
        while (true) {
            try {
                selectedItem = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Item doesn't exist.");
                scanner.next();
            }
        }
        System.out.print("Insert qty to add: ");
        while (true) {
            try {
                newQty = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Input must be number.");
                scanner.next();
            }
        }
        controller.updateItem(selectedCategory, selectedItem, Integer.toString(newQty));
        System.out.println("Item successfully updated!\n");

        controller.showItems(true);
        printDivider();
    }

    private static void addItemMenu(GudangController controller, Scanner scanner) {
        controller.showCategories();
        int category, qty;
        String name;
        while (true) {
            System.out.print("Select category: ");
            try {
                category = scanner.nextInt();
                if (category > 0 && category <= controller.getCategoriesSize()) break;
                else throw new Exception();
            } catch (Exception e) {
                System.out.println("Category doesn't exist.");
                scanner.next();
            }
        }

        scanner.nextLine();

        while (true) {
            System.out.print("Item name: ");
            try {
                name = scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Input not valid.");
                scanner.next();
            }
        }

        while (true) {
            System.out.print("Qty: ");
            try {
                qty = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Input not valid.");
                scanner.next();
            }
        }

        controller.addItem(category, name, Integer.toString(qty));
        printDivider();
    }

    private static void addCategoryMenu(GudangController controller, Scanner scanner) {
        while (true) {
            System.out.print("Category name: ");
            String category;
            try {
                category = scanner.nextLine();
                controller.addCategory(category);
                System.out.println("Category added successfully!");
                break;
            } catch (Exception e) {
                System.out.println(e);
                scanner.next();
            }
        }
        printDivider();
    }
}