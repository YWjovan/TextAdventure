package main.java.student.BasicTypes;

/**
 * Class which defines the basic object.
 */
public class BasicObject {
    /**
     * id of the object.
     */
    private String id;

    /**
     * description of the object.
     */
    private String description;

    /**
     * Constructor to create object instance.
     * @param id object's id.
     * @param description object's description.
     */
    public BasicObject(String id, String description) {
        this.id = id;
        this.description = description;
    }

    /**
     * Return object's id.
     * @return id of object.
     */
    public String getId() {
        return id;
    }

    /**
     * Return object's decription.
     * @return description of object.
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return
     */
    public String toString() {
        return id + " - " + description;
    }
}
