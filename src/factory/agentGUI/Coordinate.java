package factory.agentGUI;

/**
 * A set of x,y coordinates for painting onto a JPanel
 *
 * @brief a set of x,y coordinates for painting onto a JPanel
 */
public class Coordinate {

    /**
     * instantiate a Coordinate class with a set of x,y coordinates
     */
    int x, y;

    public Coordinate(Integer x, Integer y) {
        if (x < 0) {
            System.out.println("Warning: Coordinate initialized with negative value of x (" + x + ")");
        }
        if (y < 0) {
            System.out.println("Warning: Coordinate initialized with negative value of y (" + y + ")");
        }
        this.x = x;
        this.y = y;
    }

    /**
     * sets a new x coordinate
     */
    public void setX(Integer x) {
        if (x < 0) {
            System.out.println("Warning: Attempting to setX Coordinate with negative value of x (" + x + ")");
        }
        this.x = x;
    }

    /**
     * sets a new y coordinate
     */
    public void setY(Integer y) {
        if (y < 0) {
            System.out.println("Warning: Attempting to setX Coordinate with negative value of x (" + y + ")");
        }
        this.y = y;

    }
}
