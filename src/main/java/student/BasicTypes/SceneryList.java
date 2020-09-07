package main.java.student.BasicTypes;

import java.util.HashMap;
import java.util.Map;

/**
 * Class which defines the list of sceneries.
 */
public class SceneryList {

    /**
     * list of sceneries.
     */
    private Map<String, Scenery> sceneryList;

    private Map<String, Integer> sceneryCount;

    /**
     * Constructor to create a instance of SceneryList.
     */
    public SceneryList() {
        sceneryList = new HashMap<>();
        sceneryCount = new HashMap<>();
    }

    /**
     * Add a new scenery to scenerylist.
     * @param scenery a new scenery.
     */
    public void addToSceneryList(Scenery scenery) {
        if (!sceneryList.containsKey(scenery.getId())) {
            sceneryList.put(scenery.getId(), scenery);
        }
        sceneryCount.put(scenery.getId(), sceneryCount.getOrDefault(scenery.getId(), 0) + 1);
    }

    /**
     *
     * @param id
     */
    public void removeItemFromList(String id) {
        if (!sceneryCount.containsKey(id)) {
            return;
        }
        sceneryCount.put(id, sceneryCount.get(id) - 1);
        if (sceneryCount.get(id) == 0) {
            sceneryCount.remove(id);
        }
    }

    /**
     *
     * @return
     */
    public Map<String, Scenery> getSceneryList() {
        return sceneryList;
    }

    /**
     *
     * @return
     */
    public String toString() {
        String string = "";
        for (String key : sceneryCount.keySet()) {
            for (int i = 0; i < sceneryCount.get(key); i++) {
                string += sceneryList.get(key).toString() + "\n";
            }
        }
        return string;
    }
}
