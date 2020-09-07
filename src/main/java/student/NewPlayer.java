package main.java.student;

import main.java.ias.Player;
import main.java.student.BasicTypes.BasicObject;
import main.java.student.BasicTypes.Item;
import main.java.student.BasicTypes.ItemList;
import main.java.student.BasicTypes.SceneryList;

import java.util.HashMap;
import java.util.Map;

public class NewPlayer implements Player {

    private Map<String, BasicObject> myInventoryList;

    private Map<String, Integer> myInventoryCount;

    private int count;

    private Movement movement;
    /**
     * The instance of item list.
     */
    private ItemList itemList;

    /**
     * The instance of scenery list.
     */
    private SceneryList sceneryList;

    /**
     * The instance of rules.
     */
    private Rules rules;

    /**
     *
     * @param itemList
     */
    public void setItemList(ItemList itemList) {
        this.itemList = itemList;
    }

    /**
     *
     * @param sceneryList
     */
    public void setSceneryList(SceneryList sceneryList) {
        this.sceneryList = sceneryList;
    }

    /**
     *
     * @param rules
     */
    public void setRules(Rules rules) {
        this.rules = rules;
    }

    /**
     *
     * @param fields
     */
    public void setFields(Field[][] fields) {
        this.fields = fields;
        movement.setBoardWidth(fields.length);
        movement.setBoardWidth(fields[0].length);
    }

    /**
     * Fields means the game map.
     */
    private Field[][] fields;

    NewPlayer(int x, int y) {
        movement = new Movement(x, y);
        myInventoryList = new HashMap<>();
        myInventoryCount = new HashMap<>();
        count = 0;
    }

    @Override
    public String go(String direction) {
        return movement.go(direction);
    }

    @Override
    public String[] look() {
        String[] res = fields[movement.getCurrX()][movement.getCurrY()].getObjectList();
        for (int i = 0; i < res.length; i++) {
            if (itemList.getItemList().containsKey(res[i])) {
                res[i] = itemList.getItemList().get(res[i]).toString();
            } else if (sceneryList.getSceneryList().containsKey(res[i])) {
                res[i] = sceneryList.getSceneryList().get(res[i]).toString();
            }
        }
        return res;
    }

    @Override
    public String[] inventory() {
        String[] res = new String[count];
        int i = 0;
        for (String id : myInventoryCount.keySet()) {
            for (int j = 0; j < myInventoryCount.get(id); j++) {
                res[i++] = myInventoryList.get(id).toString();
            }
        }
        return res;
    }

    @Override
    public String take(String item) {
        if (!itemList.getItemList().containsKey(item) && !sceneryList.getSceneryList().containsKey(item)) {
            return "Sorry, no such item.";
        }
        if (!fields[movement.getCurrX()][movement.getCurrY()].isContains(item)) {
            return "Sorry, no such item here.";
        }
        if (sceneryList.getSceneryList().containsKey(item)) {
            return "Sorry, You can not take the item.";
        }
        addItemToInventory(itemList.getItemList().get(item));
        fields[movement.getCurrX()][movement.getCurrY()].removeObjectFromField(item);
        return "You pick the " + item + " successfully!";
    }

    @Override
    public String drop(String item) {
        if (!myInventoryList.containsKey(item)) {
            return "Sorry, no such item in your inventory.";
        }
        removeItemFromInventory(item);
        fields[movement.getCurrX()][movement.getCurrY()].addToObjects(item);
        return "You drop the " + item + " successfully!";
    }

    @Override
    public String convert(String item1, String item2) {
        if ((!itemList.getItemList().containsKey(item1) && !sceneryList.getSceneryList().containsKey(item1))
                || (!itemList.getItemList().containsKey(item2) && !sceneryList.getSceneryList().containsKey(item2))) {
            return "Sorry! No such " + item1  + " or " + item2;
        }
        if ((!fields[movement.getCurrX()][movement.getCurrY()].isContains(item1) || !fields[movement.getCurrX()][movement.getCurrY()].isContains(item2))
                && (!myInventoryList.containsKey(item1) || !myInventoryList.containsKey(item2))) {
            return "Sorry! You can not decompose the item without it.";
        }
        String key = item1 + "-" + item2;
        if (!rules.getCompositions().containsKey(key) && !rules.getTransformations().containsKey(key)) {
            return "Sorry! " + item1 + " and " + item2 + " can not be converted";
        }

        if (itemList.getItemList().containsKey(item1)) {
            removeItemFromInventory(item1);
        } else if (sceneryList.getSceneryList().containsKey(item1)) {
            fields[movement.getCurrX()][movement.getCurrY()].removeObjectFromField(item1);
        }
        if (itemList.getItemList().containsKey(item2)) {
            removeItemFromInventory(item2);
        } else if (sceneryList.getSceneryList().containsKey(item2)) {
            fields[movement.getCurrX()][movement.getCurrY()].removeObjectFromField(item2);
        }

        String conversion = null;
        if (rules.getCompositions().containsKey(key)) {
            conversion = rules.getCompositions().get(key);
            String[] res = conversion.split("-");
            if (itemList.getItemList().containsKey(res[0])) {
                addItemToInventory(itemList.getItemList().get(res[0]));
            } else if (sceneryList.getSceneryList().containsKey(res[0])) {
                fields[movement.getCurrX()][movement.getCurrY()].addToObjects(res[0]);
            }
            return res[0] + " - " + res[1];
        } else {
            conversion = rules.getTransformations().get(key);
            String[] res = conversion.split("-");
            if (itemList.getItemList().containsKey(res[0])) {
                addItemToInventory(itemList.getItemList().get(res[0]));
            } else if (sceneryList.getSceneryList().containsKey(res[0])) {
                fields[movement.getCurrX()][movement.getCurrY()].addToObjects(res[0]);
            }
            if (itemList.getItemList().containsKey(res[1])) {
                addItemToInventory(itemList.getItemList().get(res[1]));
            } else if (sceneryList.getSceneryList().containsKey(res[1])) {
                fields[movement.getCurrX()][movement.getCurrY()].addToObjects(res[1]);
            }
            return res[2];
        }
    }

    @Override
    public String decompose(String item) {
        if (!itemList.getItemList().containsKey(item) && sceneryList.getSceneryList().containsKey(item)) {
            return "Sorry! No such item.";
        }
        if (!fields[movement.getCurrX()][movement.getCurrY()].isContains(item) && !myInventoryList.containsKey(item)) {
            return "Sorry! You can not decompose the item without it.";
        }
        if (!rules.getDecompositions().containsKey(item)) {
            return "Sorry! " + item + " can not be decomposed.";
        }
        String decomposition = rules.getDecompositions().get(item);
        String[] res = decomposition.split("-");
        if (sceneryList.getSceneryList().containsKey(item)) {
            fields[movement.getCurrX()][movement.getCurrY()].removeObjectFromField(item);
        } else if (itemList.getItemList().containsKey(item)) {
            removeItemFromInventory(item);
        }

        if (sceneryList.getSceneryList().containsKey(res[0])) {
            fields[movement.getCurrX()][movement.getCurrY()].addToObjects(res[0]);
        } else if (itemList.getItemList().containsKey(res[0])) {
            addItemToInventory(itemList.getItemList().get(res[0]));
        }
        if (sceneryList.getSceneryList().containsKey(res[1])) {
            fields[movement.getCurrX()][movement.getCurrY()].addToObjects(res[1]);
        } else if (itemList.getItemList().containsKey(res[1])) {
            addItemToInventory(itemList.getItemList().get(res[1]));
        }
        return res[2];
    }

    /**
     *
     * @param item
     */
    public void removeItemFromInventory(String item) {
        if (!myInventoryList.containsKey(item)) {
            return;
        }
        count--;
        myInventoryCount.put(item, myInventoryCount.get(item) - 1);
        if (myInventoryCount.get(item) == 0) {
            myInventoryList.remove(item);
        }
    }

    /**
     *
     * @param item
     */
    public void addItemToInventory(Item item) {
        count++;
        myInventoryList.put(item.getId(), item);
        myInventoryCount.put(item.getId(), myInventoryCount.getOrDefault(item.getId(), 0) + 1);
    }
}
