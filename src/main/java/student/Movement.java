package main.java.student;

/**
 *
 */
public class Movement {
    /**
     *
     */
    private int currX;

    private int currY;

    private int boardWidth;

    private int boardHeight;

    private int[] dirX = {-1, 0, 1, -1, 1, -1, 0, 1};

    private int[] dirY = {-1, -1, 1, 0, 0, 1, 1, 1};

    private String[] directions = {"NW", "N", "NE", "W", "E", "SW", "S", "SE"};

    /**
     *
     * @param boardWidth
     */
    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    /**
     *
     * @param boardHeight
     */
    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    /**
     *
     * @return
     */
    public int getCurrX() {
        return currX;
    }

    /**
     *
     * @return
     */
    public int getCurrY() {
        return currY;
    }

    /**
     *
     * @param x
     * @param y
     */
    public Movement(int x, int y) {
        currX = x;
        currY = y;
    }

    /**
     * player go to direction.
     * @param direction
     * @return message.
     */
    public String go(String direction) {
        if (direction == null) {
            return "Sorry, You should input the direction.";
        }
        int index = -1;
        for (int i = 0; i < 8; i++) {
            if (directions[i].equals(direction)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return "Sorry, No such direction.";
        }

        if (checkOutside(currX + dirX[index], currY + dirY[index])) {
            return "Sorry, You can not go outside the field.";
        }
        currX += dirX[index];
        currY += dirY[index];
        return "You go " + direction + ", now you are in (" + currX + "," + currY + ")";
    }

    /**
     *
     * @param x
     * @param y
     * @return true or false.
     */
    public boolean checkOutside(int x, int y) {
        return x < 0 || y < 0 || x >= boardWidth || y >= boardHeight;
    }
}
