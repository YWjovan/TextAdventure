package main.java.student.BasicTypes;

/**
 * Class which defines the item.
 */
public class Item extends BasicObject {
    /**
     * Constructor to create Item instance.
     * @param id  id of item
     * @param description decription of item
     */
    public Item(String id, String description) {
        super(id, description);
    }

    /**
     * Returns the item id.
     * @return the item id.
     */
    public String getId() {
        return super.getId();
    }

    /**
     * Returns the item description.
     * @return the item description.
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
