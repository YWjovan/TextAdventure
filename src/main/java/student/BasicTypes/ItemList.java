package main.java.student.BasicTypes;

import java.util.HashMap;
import java.util.Map;

/**
 * Class which defines the list of items.
 */
public class ItemList {
    /**
     * list of items.
     */
    private Map<String, Item> itemList;

    private Map<String, Integer> itemCount;
    /**
     * Constructor to create ItemList instance.
     */
    public ItemList() {
        itemList = new HashMap<>();
        itemCount = new HashMap<>();
    }

    /**
     * Add item to itemlist.
     * @param item  item was added to itemlist.
     */
    public void addToItemlist(Item item) {
        if (!itemList.containsKey(item.getId())) {
            itemList.put(item.getId(), item);
        }
        itemCount.put(item.getId(), itemCount.getOrDefault(item.getId(), 0) + 1);
    }

    /**
     *
     * @param id
     */
    public void removeItemFromList(String id) {
        if (!itemCount.containsKey(id)) {
            return;
        }
        itemCount.put(id, itemCount.get(id) - 1);
        if (itemCount.get(id) == 0) {
            itemCount.remove(id);
        }
    }
    /**
     *
     * @return
     */
    public Map<String, Item> getItemList() {
        return itemList;
    }

    /**
     *
     * @return
     */
    public String toString() {
        String string = "";
        for (String key : itemCount.keySet()) {
            for (int i = 0; i < itemCount.get(key); i++) {
                string += itemList.get(key).toString() + "\n";
            }
        }
        return string;
    }
}
