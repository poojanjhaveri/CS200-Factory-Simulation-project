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
    
    public Type type;
    public boolean inKit;
    public int size;
//    public boolean good;

    /**
     * Creates a part and gives it a unique ID.
     *
     * @brief instantiates a part into the correct state
     */
    public Part(String n, String d, String fn) {
        //TODO need to implement system to generate unique part numbers (we could cheat and do it the dumb way, grab time())
    }
    
    /** End CS 201 stuff */
    
    /** CS 200 stuff */
    private String name;
    private String description;
    private String imageFileName;
//    Integer number;///< the part number, like an ID; useful for comparing

    

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }
    
    public Part(Type t, boolean inkit, int size) {
        this.type = t;
        this.inKit = false;
        this.size = size;
    }
}
