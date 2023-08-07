import java.util.ArrayList;

public class GudangController {
    private ArrayList<String> categories = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> items = new ArrayList<>();

    public int getCategoriesSize() {
        return categories.size();
    }

    public boolean isCategoriesEmpty() {
        return categories.size() == 0;
    }

    public boolean isItemsEmpty() {
        return items.size() == 0;
    }

    public void addItem(int categoryIndex, String name, String qty) {
        ArrayList<String> item = new ArrayList<>();
        String category = categories.get(categoryIndex - 1);
        item.add(category);
        item.add(name);
        item.add(qty);
        items.get(categoryIndex - 1).add(item);

        System.out.println("Item successfully added!");
    }

    public void addCategory(String category) {
        categories.add(category);
        items.add(new ArrayList<>());
    }

    public void showCategories() {
        for (int i = 0; i < categories.size(); i++) {
            System.out.println(i + 1 + ". " + categories.get(i));
        }
    }

    public void showItems(boolean showAll, int... categoryIndex) {
        if (showAll) {
            for (int i = 0; i < items.size(); i++) {
                System.out.println("=============== " + categories.get(i) + " ===============");
                for (int j = 0; j < items.get(i).size(); j++) {
                    System.out.println(j + 1 + ". " + items.get(i).get(j).get(1) + " - " + items.get(i).get(j).get(2));
                }
                System.out.println();
            }
        } else {
            for (int i = 0; i < items.get(categoryIndex[0] - 1).size(); i++) {
                System.out.println(i + 1 + ". " + items.get(categoryIndex[0] - 1).get(i).get(1) + " - " + items.get(categoryIndex[0] - 1).get(i).get(2));
            }
        }
    }

    public void updateItem(int categoryIndex, int itemIndex, String addedQty) {
        int addedQtyToInt = Integer.parseInt(addedQty);
        int itemQty = Integer.parseInt(items.get(categoryIndex - 1).get(itemIndex - 1).get(2));
        int newQty = itemQty + addedQtyToInt;
        items.get(categoryIndex - 1).get(itemIndex - 1).set(2, Integer.toString(newQty));
    }
}

