package main.java.student;

import java.util.HashMap;
import java.util.Map;

/**
 * Class which defines the rules.
 */
public class Rules {
    /**
     * Composition rules.
     */
    private Map<String, String> compositions;

    /**
     * Decompostion rules.
     */
    private Map<String, String> decompositions;

    /**
     * Transformation rules.
     */
    private Map<String, String> transformations;

    /**
     * Constructor to create a rule instance.
     */
    public Rules() {
        compositions = new HashMap<>();
        decompositions = new HashMap<>();
        transformations = new HashMap<>();
    }

    /**
     *
     * @return
     */
    public Map<String, String> getCompositions() {
        return compositions;
    }

    /**
     *
     * @return
     */
    public Map<String, String> getDecompositions() {
        return decompositions;
    }

    /**
     *
     * @return
     */
    public Map<String, String> getTransformations() {
        return transformations;
    }

    /**
     *
     * @return
     */
    public String toString() {
        String string = "";
        string += "composition:" + "\n";
        for (String key : compositions.keySet()) {
            string += key + "," + compositions.get(key) + "\n";
        }
        string += "decomposition:" + "\n";
        for (String key : decompositions.keySet()) {
            string += key + "," + decompositions.get(key) + "\n";
        }
        string += "transformation:" + "\n";
        for (String key : transformations.keySet()) {
            string += key + "," + transformations.get(key) + "\n";
        }
        return string;
    }
}
