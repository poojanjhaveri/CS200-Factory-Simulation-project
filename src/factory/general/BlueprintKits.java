package factory.general;

import java.util.ArrayList;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
@brief stores kit blueprints
 */
public class BlueprintKits implements Blueprint, Serializable {

    ArrayList<Kit> kits;

    public BlueprintKits() {
        this.kits = new ArrayList<Kit>();

    }
    public BlueprintKits(ArrayList<Kit> in)
    {
        this.kits = in;
    }
    public BlueprintKits(String serialized)
    {
        this.kits = this.deserialize(serialized);
    }
    /**
    @brief deserializes the passed string and returns an arraylist of kits
         */
    public ArrayList<Kit> deserialize(String serialized) {

        ArrayList<String> stringform = Util.deserialize(serialized);
        ArrayList<Kit> ret = new ArrayList<Kit>();
        for(int i = 0; i != stringform.size(); i++)
        {
            ret.add(Kit.deserialize(stringform.get(i)));
        }
        return ret;
    }
    public ArrayList<Kit> getKits()
    {
	return this.kits;
    }
    /**
    @brief deserializes the passed string and adds to the current data
     */
    public void updateOne(String serialized)
    {
        this.kits.addAll(this.deserialize(serialized));
    }
    /**
    @brief recreates the kits list using the passed serialized blueprintkits
     */
    public void recreate(String serialized)
    {
        this.kits = this.deserialize(serialized);
    }
    /**
    @brief serializes the current data into a deserializable string
     */
    public String serialize()
    {
        String toreturn ="";
        for(int i = 0; i != this.kits.size(); i++)
        {
            toreturn = toreturn+(this.kits.get(i).serialize().length()+2)+"("+this.kits.get(i).serialize()+")";
        }
        return toreturn;
    }
    public void add(Kit in)
    {
	this.kits.add(in);
    }
    public void save(){
        try {
            FileOutputStream out = new FileOutputStream(FactoryState.PATH_BP_KITS);
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(this);
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED INVOKING BP KITS SAVE METHOD!" + e.getMessage());
            e.printStackTrace();
        }
    }
    public void debug()
    {
	if(this.kits.size() == 0)
	    {
		System.out.println("BlueprintKits debug size 0.");
		return;
	    }
	System.out.println("DEBUG BPKITS");
	for(int i = 0; i!= this.kits.size(); i++)
	    {
		this.kits.get(i).debug();
	    }
    }
    public static void main(String[] args)
    {
	System.out.println("Creating BPKits===========");
        BlueprintKits bp = new BlueprintKits();
	Kit k = new Kit("kit1","im the nyan kit");
	Part p = new Part("part1","im a part dawg");
	p.setFilename("part.png");
	k.addPart(p);
	p = new Part("party2","i party a lot");
	p.setFilename("party.png");
	k.addPart(p);
        bp.add(k);
	System.out.println("INITIAL DEBUG===============");
	bp.debug();
	String s = bp.serialize();
	System.out.println("SERIALIZED: "+s);
	BlueprintKits bp2 = new BlueprintKits(s);
	System.out.println("FINAL DEBUG=============");
	bp2.debug();
	System.out.println("SERIALIZED: "+bp2.serialize());
	if(bp2.serialize().equals(bp.serialize()))
	    {
		System.out.println("Test passed");
	    }else System.out.println("TEST FAILED");
	System.out.println("Testing empty BlueprintKits===========");
	bp = new BlueprintKits();
	bp.debug();
	s = bp.serialize();
	System.out.println("Seralized: " + s);
	bp.recreate(s);
	bp.debug();
	if(s.equals(bp.serialize()))
	    {
		System.out.println("Test passed");
	    }else System.out.println("TEST FAILED");
    }
    
}