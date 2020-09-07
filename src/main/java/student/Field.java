package main.java.student;

import java.util.HashMap;
import java.util.Map;

/**
 * Class which defines the field instance.
 */
public class Field {

    /**
     * the map of objects.
     */
    private Map<String, Integer> objectMap;

    private int count;

    /**
     * Constructor to create an instance of field.
     */
    public Field() {
        objectMap = new HashMap<>();
        count = 0;
    }

    /**
     * Add a new object to objects.
     * @param object a new BasicoObject.
     */
    public void addToObjects(String object) {
        objectMap.put(object, objectMap.getOrDefault(object, 0) + 1);
        count++;
    }

    /**
     * Remove an object from the field.
     * @param object object.
     * @return true or false.
     */
    public boolean removeObjectFromField(String object) {
        if (!isContains(object)) {
            return false;
        }
        objectMap.put(object, objectMap.get(object) - 1);
        if (objectMap.get(object) == 0) {
           objectMap.remove(object);
        }
        count--;
        return true;
    }

    /**
     * Return the list of objects.
     * @return objectList.
     */
    public String[] getObjectList() {
        String[] res = new String[count];
        int i = 0;
        for (String key : objectMap.keySet()) {
            for (int j = 0; j < objectMap.get(key); j++) {
                res[i++] = key;
            }
        }
        return res;
    }

    /**
     * Check if the object exists in this field.
     * @param object used to check.
     * @return true or false.
     */
    public boolean isContains(String object) {
        return objectMap.containsKey(object);
    }

    /**
     *
     * @return
     */
    public String toString() {
        String string = "";
        String[] list = getObjectList();
        for (String item : list) {
            string += item + "\n";
        }
        return string;
    }
}

