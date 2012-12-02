package factory.general;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
/**
 * Kit Object
 *
 * @brief Kit Object
 * @author James Dalton, YiWei Roy Zheng, Poojan Jhaveri
 *
 */
public class Kit implements Serializable{

    /** CS 201 stuff */
    public enum Status {

        empty, ready, full, verified, error
    };

    public static enum StandNum {

        none, zero, one, two
    };
    public String name;
    public Status status;
    public StandNum standNum;
    public List<Part> parts = new ArrayList<Part>();
    public int kitNeedsParts;
    public boolean beingUsedByPartsAgent; // for Alex
    public List<Part> missingParts;
    
    public Kit(String name) {
        this.name = name;
        this.status = Status.empty;
        this.standNum = StandNum.none;
        this.beingUsedByPartsAgent = false;
//        this.kitNeedsParts = p.length;
    }

    public void addPart(Part p) {
        parts.add(p);
    }

    public int getSize() {
        return this.parts.size();
    }

    public Part getPart(int i) {
        return parts.get(i);
    }
    /** End CS 201 stuff*/
    /*CS200*/
    String description;
    Integer number;

    public Kit(String n, String d) {

        // Kit name 
        this.name = n;
        this.description = d;
        this.number = (int) (System.currentTimeMillis() / (long) 1000);
    }

    public Kit(String n, String d, Integer i) {
        this.name = n;
        this.number = i;
        this.description = d;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setParts(ArrayList<Part> p) {
        this.parts = p;
    }
    /**
       @brief this function blows don't use it
@deprecated
     */
public Kit cloneSelf()
{
    System.out.println("CALLING Kit::cloneSelf() WHICH IS DEPRECATED. USE Kit::deepClone() INSTEAD!");
   Kit n = new Kit(this.getName(),this.getDescription(),this.getNumber());
   ArrayList<Part> p = new ArrayList<Part>();
   for(int i = 0; i != n.getSize(); i++)
   {
       p.add(n.getPart(i).clone());
   }
   n.setParts(p);
   return n;
}
    /**
    @brief string serializes a kit
     */
    public String serialize() {
        if (this.parts == null) {
            System.out.println("CRITICAL ERROR SERIALIZING KIT: PARTS ARRAY WAS NEVER SET!");
            return null;
        }
        BlueprintParts bpp = new BlueprintParts((ArrayList<Part>) this.parts);
        return (this.name.length() + 2) + "(" + this.name + ")" + (2 + this.description.length()) + "(" + this.description + ")" + (2 + this.number.toString().length()) + "(" + this.number + ")" + (2 + bpp.serialize().length()) + "(" + bpp.serialize() + ")";
    }

    /**
    @brief deserializes a string serialized kit
     */
    public static Kit deserialize(String serialized) {
        ArrayList<String> stringform = Util.deserialize(serialized);
        if (stringform.size() != 4) {
            System.out.println("KIT DESERIALIZATION ERROR: " + serialized + "; ARRAY: " + stringform);
        }
        Kit nk = new Kit(stringform.get(0), stringform.get(1), Integer.parseInt(stringform.get(2)));
        ArrayList<Part> p = BlueprintParts.deserialize(stringform.get(3));
        nk.setParts(p);
        return nk;
    }
    /**
@brief prints out relevant information about the kit
    */
    public void debug() {
        System.out.println(this.getName() + " " + this.getDescription() + " " + this.getNumber());
        for (int i = 0; i != this.parts.size(); i++) {
            System.out.println(this.parts.get(i).serialize());
        }
    }

    public static void main(String[] args) {
        Part p = new Part("part1", "is a part");
        p.setFilename("part1.png");
        Kit bp = new Kit("kit1", "I am a kit lollol");
        bp.addPart(p);
        p = new Part("part2", "is (not) a part");
        p.setFilename("part2.png");
        bp.addPart(p);
        p = new Part("alfalfa", "heyo");
        p.setFilename("gogo.png");
        bp.addPart(p);
        bp.debug();
        System.out.println("SERIALIZED KIT");
        System.out.println(bp.serialize());

        Kit des = Kit.deserialize(bp.serialize());
        System.out.println("DESERIALIZED KIT");
        System.out.println(des.serialize());
        des.debug();
        if (des.serialize().equals(bp.serialize())) {
            System.out.println("Test passed");
        } else {
            System.out.println("TEST FAILED");
        }

        System.out.println("Testing empty parts kit");
        des = new Kit("mahkit", "its my kit");
        des.setParts(new ArrayList<Part>());
        String s = des.serialize();
        System.out.println("Serialized:" + s);
        Kit newkit = Kit.deserialize(s);
        System.out.println("Serialized deserialized serialized kit:" + newkit.serialize());
        if (s.equals(newkit.serialize())) {
            System.out.println("Test passed");
        } else {
            System.out.println("TEST FAILED");
        }

	System.out.println("Deep Clone Test");
	Kit blah = new Kit("heykit","myscrip");
	for(int i =0 ; i != 4; i++)
	    {
		blah.addPart(new Part("hey","non",311));
		blah.getPart(i).setFilename("fname");
	    }
	System.out.println("BEFORE SIZE: "+blah.getSize());
blah.debug();
		Kit blah2 = Kit.deepClone(blah);
	blah2.debug();
	System.out.println("AFTER SIZE: " + blah2.getSize());
	Boolean good = true;	
	if(!blah.getName().equals(blah2.getName()))
	    {
		System.out.println("NAME DOES NOT MATCH");
		good = false;
	    }
	if(!blah.getDescription().equals(blah2.getDescription()))
	    {
		System.out.println("DESCRIPTION DOESN TO MATCH");
		good = false;
	    }
	if(blah.getSize() != blah2.getSize())
	    {
		System.out.println("PARTS SIZE DOES NOT MATCH");
		good = false;
	    }
	if(good)
	    {
		System.out.println("Test passed");
	    }else System.out.println("TEST FAILED");
    }
    public List<Part> getParts()
    {
        return this.parts;
    }
    public static Kit deepClone(Kit in)
    {
        Kit k = new Kit(in.getName(),in.getDescription(),in.getNumber());
        ArrayList<Part> dp = new ArrayList<Part>();
        if( in.getSize() < 4)
        {
            System.out.println("SIZE OF KIT IS LESS THAN 4");
            in.debug();
        }
        for(int i = 0; i != in.getSize(); i++)
        {
            dp.add(in.getPart(i));
        }
        k.setParts(dp);
        return k;   
    }
}