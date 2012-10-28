
package factory;

public class Part {

    public enum Type {p1, p2, p3, p4, p5, p6, p7, p8};
    public Type type;
    public boolean inKit;
    public int size;
//public boolean good; 

    public Part(Type t, boolean inkit, int size) {
        this.type = t;
        this.inKit = false;
        this.size = size;
    }
}
