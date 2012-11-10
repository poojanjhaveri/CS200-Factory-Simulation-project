package factory.general;

import java.util.ArrayList;
import java.util.List;

/**
 * Kit Object
 *
 * @brief Kit Object
 * @author James Dalton, YiWei Roy Zheng
 *
 */
public class Kit {

    /** CS 201 stuff */
    public enum Status {
        empty, full, verified, error
    };
    public static enum StandNum {
        none, zero, one, two
    };

    public String name;
    public Status status;
    public StandNum standNum;
    private List<Part> parts = new ArrayList<Part>();
    public int kitNeedsParts;

    public Kit(String name) {
        this.name = name;
        this.status = Status.empty;
        this.standNum = StandNum.none;
//        this.kitNeedsParts = p.length;
    }

    public void addPart(Part p) {
        parts.add(p);
    }
    public int getSize() {
        return parts.size();
    }

    public Part getPart(int i) {
        return parts.get(i);
    }

    /** End CS 201 stuff*/


    /*CS200*/
    String description;
    Integer number;

    public Kit(String n, String d)
    {
        this.name = n;
        this.description = d;
        this.number = (int)(System.currentTimeMillis()/(long)1000);

    }
    public Kit(String n, String d, Integer i) {
        this.name = n;
        this.description = d;
        this.number = i;
    }
    public String getName()
    {
        return this.name;
    }
    public String getDescription()
    {
        return this.description;
    }
    public Integer getNumber()
    {
        return this.number;
    }
    public void setParts(ArrayList<Part> p)
    {
        this.parts = p;
    }
    /**
    @brief string serializes a kit
     */
    public String serialize()
    {
        BlueprintParts bpp = new BlueprintParts((ArrayList<Part>)this.parts);
        return (this.name.length()+2)+"("+this.name+")"+(2+this.description.length())+"("+this.description+")"+(2+this.number.toString().length())+"("+this.number+")"+(2+bpp.serialize().length())+"("+bpp.serialize()+")";
    }
    /**
    @brief deserializes a string serialized kit
     */
    public static Kit deserialize(String serialized)
    {
        ArrayList<String> stringform = Util.deserialize(serialized);
        if(stringform.size() != 4)
        {
            System.out.println("KIT DESERIALIZATION ERROR: " +serialized + "; ARRAY: "+stringform);
        }
        Kit nk = new Kit(stringform.get(0),stringform.get(1),Integer.parseInt(stringform.get(2)));
        ArrayList<Part> p = BlueprintParts.deserialize(stringform.get(3));
        nk.setParts(p);
        return nk;
    }
    public static void main(String[] args)
    {
        Part p = new Part("part1","is a part");
        p.setFilename("part1.png");
        Kit bp = new Kit("kit1","I am a kit lollol");
        bp.addPart(p);
        p = new Part("part2","is (not) a part");
        p.setFilename("part2.png");
        bp.addPart(p);
        p = new Part("alfalfa","heyo");
        p.setFilename("gogo.png");
        bp.addPart(p);
        System.out.println("SERIALIZED KIT");
        System.out.println(bp.serialize());

        Kit des = Kit.deserialize(bp.serialize());
        System.out.println("DESERIALIZED KIT");
        System.out.println(des.getName() + " " + des.getDescription() + " " + des.getNumber());
    }
}