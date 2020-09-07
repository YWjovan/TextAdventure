package main.java.student.BasicTypes;

/**
 * Class which defines the scenery.
 */
public class Scenery extends BasicObject {

    /**
     * Constructor to create a scenery instance.
     * @param id id of scenery.
     * @param description description of scenery.
     */
    public Scenery(String id, String description) {
        super(id, description);
    }

    /**
     * Return the scenery id.
     * @return id of scenery.
     */
    public String getId() {
        return super.getId();
    }

    /**
     * Return the scenery description.
     * @return description of scenery.
     */
    public String getDescription() {
        return super.getDescription();
    }

    /**
     *
     * @return
     */
    public String toString() {
        return super.toString();
    }
}
