package factory.general;

import java.io.Serializable;

/**
 * @brief digital representation of a part
 * @author Alex Young, David Zhang
 */
public class Part implements Serializable {
    /** CS201 stuff */
    public enum Type {
        p1, p2, p3, p4, p5, p6, p7, p8
    };
    private Type type;
    private boolean inKit;
    private int size;

    /**
     * Creates a part and gives it a unique ID.
     * @author YiWei Roy Zheng
     * @brief instantiates a part into the correct state
     */

    public Part(Type t, boolean inkit, int size) {
        this.type = t;
        this.inKit = false;
        this.size = size;
    }
    /** End CS 201 stuff */

    /** CS 200 stuff */
    private String name;
    private String description;
    private ImageIcon img;
    Integer number;///< the part number, like an ID; useful for comparing

    public Part(String n, String d, String fn) {
        this.name = n;
        this.description = d;
        this.img = new ImageIcon(fn);
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public ImageIcon getImage()
    {
        return this.img;
    }

}