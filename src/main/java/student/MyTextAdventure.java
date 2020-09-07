package main.java.student;

import main.java.ias.Player;
import main.java.ias.TextAdventure;
import main.java.ias.TextAdventureException;
import main.java.student.BasicTypes.*;



public class MyTextAdventure implements TextAdventure {

    private String name;

    private int boardWidth;

    private int boardHeight;

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
     * Fields means the game map.
     */
    private Field[][] fields;

    /**
     *
     * @param name
     * @param boardWidth
     * @param boardHeight
     * @throws TextAdventureException
     */
    public MyTextAdventure(String name, int boardWidth, int boardHeight) throws TextAdventureException {
        if (boardWidth <= 0) {
            throw new TextAdventureException("BoardWidth must be positive.");
        }
        if (boardHeight <= 0) {
            throw new TextAdventureException("BoardHeight must be positive.");
        }
        this.name = name;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        itemList = new ItemList();
        sceneryList = new SceneryList();
        rules = new Rules();
        fields = new Field[boardWidth][boardHeight];
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                fields[i][j] = new Field();
            }
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws TextAdventureException
     */
    public boolean checkIdValid(String id) throws TextAdventureException {
        if (id == null || id.length() == 0) {
            return false;
        }
        for (int i = 0; i < id.length(); i++) {
            if (!Character.isLetter(id.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void addItemType(String id, String description) throws TextAdventureException {
        if (!checkIdValid(id)) {
            throw new TextAdventureException(id + " is invalid name");
        }
        if (sceneryList.getSceneryList().containsKey(id)) {
            throw new TextAdventureException(id + " has existed in scenery.");
        }
        itemList.addToItemlist(new Item(id, description));
    }

    @Override
    public void addSceneryType(String id, String description) throws TextAdventureException {
        if (!checkIdValid(id)) {
            throw new TextAdventureException(id + " is invalid name");
        }
        if (itemList.getItemList().containsKey(id)) {
            throw new TextAdventureException(id + " has existed in item.");
        }
        sceneryList.addToSceneryList(new Scenery(id, description));
    }

    @Override
    public void placeItem(String type, int x, int y) throws TextAdventureException {
        fields[x][y].addToObjects(type);
    }

    /**
     *
     * @param id
     * @return
     * @throws TextAdventureException
     */
    public boolean checkItemExisted(String id) throws TextAdventureException {
        return itemList.getItemList().containsKey(id) || sceneryList.getSceneryList().containsKey(id);
    }

    /**
     *
     * @param description
     * @return
     * @throws TextAdventureException
     */
    public boolean checkDescriptionValid(String description) throws TextAdventureException {
        if (description == null || description.length() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public void addComposition(String in1, String in2, String out, String description) throws TextAdventureException {
        if (!checkItemExisted(in1)) {
            throw new TextAdventureException(in1 + " is not existed.");
        }
        if (!checkItemExisted(in2)) {
            throw new TextAdventureException(in2 + " is not existed.");
        }
        if (!checkItemExisted(out)) {
            throw new TextAdventureException(out + " is not existed.");
        }
        if (!checkDescriptionValid(description)) {
            throw new TextAdventureException("description must be valid.");
        }
        rules.getCompositions().put(in1 + "-" + in2, out + "-" + description);
    }

    @Override
    public void addDecomposition(String in, String out1, String out2, String description) throws TextAdventureException {
        if (!checkItemExisted(in)) {
            throw new TextAdventureException(in + " is not existed.");
        }
        if (!checkItemExisted(out1)) {
            throw new TextAdventureException(out1 + " is not existed.");
        }
        if (!checkItemExisted(out2)) {
            throw new TextAdventureException(out2 + " is not existed.");
        }
        if (!checkDescriptionValid(description)) {
            throw new TextAdventureException("description must be valid.");
        }
        rules.getDecompositions().put(in, out1 + "-" + out2 + "-" + description);
    }

    @Override
    public void addTransformation(String in1, String in2, String out1, String out2, String description) throws TextAdventureException {
        if (!checkItemExisted(in1)) {
            throw new TextAdventureException(in1 + " is not existed.");
        }
        if (!checkItemExisted(in2)) {
            throw new TextAdventureException(in2 + " is not existed.");
        }
        if (!checkItemExisted(out1)) {
            throw new TextAdventureException(out1 + " is not existed.");
        }
        if (!checkItemExisted(out2)) {
            throw new TextAdventureException(out2 + " is not existed.");
        }
        if (!checkDescriptionValid(description)) {
            throw new TextAdventureException("description must be valid.");
        }
        rules.getTransformations().put(in1 + "-" + in2, out1 + "-" + out2 + "-" + description);
    }

    @Override
    public Player startGame(int x, int y) throws TextAdventureException {
        Player play = new NewPlayer(x, y);
        ((NewPlayer) play).setFields(fields);
        ((NewPlayer) play).setItemList(itemList);
        ((NewPlayer) play).setRules(rules);
        ((NewPlayer) play).setSceneryList(sceneryList);
        return play;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String toString() {
        return name + "|(" + boardWidth + "," + boardHeight + ")|" + "\n" + itemList.toString() + sceneryList.toString() + rules.toString() + fields.toString();
    }
}
