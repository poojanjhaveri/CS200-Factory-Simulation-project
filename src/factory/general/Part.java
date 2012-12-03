package factory.general;

import java.io.Serializable;
import javax.swing.ImageIcon;
import java.util.ArrayList;

/**
 * @brief digital representation of a part
 * @author Alex Young, David Zhang, YiWei Roy Zheng
 */
public class Part implements Serializable {

    /**
     * CS201 stuff
     */
    public enum Type {
    p1, p2, p3, p4, p5, p6, p7, p8
    };
    public Integer type;
    private boolean inKit;
    public int size;
    public int nestNum;
    public String word;
    /*
    public Part(Type t, boolean inkit, int size) {
        this.type = t;
        this.inKit = false;
        this.size = size;
    }*/

    //constructor for testing agent codes
    public Part(int num){
    this.type=num;
    }
    public int getNestNum() {
        return nestNum;
    }

    public void setNestNum(int n) {
        nestNum = n;
    }


    public int getSize() {
        return this.size;
    }
    
    /**
     *
     * @return
     */
    
    public Integer getInt() {
        return type;
    }
    public void deserializeSetType(int t)
    {
	this.type = t;
    }
    public void setType(){
    System.out.println("fileName is (??)" + filename);
    if(filename.contains("part1.png"))
        this.type=0; 
    
    if(filename.contains("part2.png"))
        this.type=1;
    
    if(filename.contains("part3.png"))
        this.type=2;
    if(filename.contains("part4.png"))
        this.type=3;
    if(filename.contains("part5.png"))
        this.type=4;
    if(filename.contains("part6.png"))
        this.type=5;
    if(filename.contains("part7.png"))
        this.type=6;
    if(filename.contains("part8.png"))
        this.type=7;
    }
    
    /* I am adding this constructor to test my agent codes (kevin) */
/*
    public Part(int num) {
        if (num == 1) {
            this.type = 1;
            this.word = "p1";
        }
        if (num == 2) {
            this.type = 2;
            this.word = "p2";
        }
        if (num == 3) {
            this.type = 3;
            this.word = "p3";
        }
        if (num == 4) {
            this.type = 4;
            this.word = "p4";
        }
        if (num == 5) {
            this.type = 5;
            this.word = "p5";
        }
        if (num == 6) {
            this.type = 6;
            this.word = "p6";
        }
        if (num == 7) {
            this.type = 7;
            this.word = "p7";
        }
        if (num == 8) {
            this.type = 8;
            this.word = "p8";
        }
    }
    /**
     * End CS 201 stuff
     */
    /**
     * CS 200 stuff
     */
    private String name;
    private String description;
    Integer number;///< the part number, like an ID; useful for comparing
    GUIPart guipart;///<gui representation of this part
    private String filename;//lives in guipart

    public Part(String n, String d) {
        this.name = n;
        this.description = d;
        //this.number=time()\;
        this.guipart = null;
        this.number = (int) (System.currentTimeMillis()/(long)1000);
        //setType();
   
    }
    
    public Part(String n, String d, Integer num) {
        this.name = n;
        this.description = d;
        this.number = num;
        this.guipart = null;
        //this.type = number;
       // this.type = Global.part_num;
       // Global.part_num++;
      //   setType();
    }
    
    public Part(String n, String d, String file) {
        this.name = n;
        this.description = d;
        this.filename = file;
        this.guipart = null;
        this.number = (int)(System.currentTimeMillis()/(long)1000);
     //   this.type = number;
    //    this.type = Global.part_num;
     //   Global.part_num++;
    //     setType();
    }
    public void setFilename(String in) {
        this.filename = in;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String s)
    {
	this.name = s;
    }
    public Integer getNumber() {
        return number;
    }
    public String getFilename() {
        return this.filename;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String s)
    {
	this.description = s;
    }
    public GUIPart getGUIPart() {
        return this.guipart;
    }
    public void setGUIPart(GUIPart in) {
        this.guipart = in;
    }
    public int getNum(){
    	return this.number;
    }
    public void debug()
    {
    	System.out.println("Name:" + name + "\nNumber: " + this.number + "\nFilename:" + this.filename);
    }
    public String serialize() {
    	//        return "("+this.name+","+this.description+","+this.number+","+this.filename+")";
    	ArrayList<String> arr = new ArrayList<String>();
    	arr.add(this.name);
    	arr.add(this.description);
    	arr.add(this.number+"");
    	arr.add(this.filename);
	if(this.type == null)
	    this.type = 999;
	arr.add(this.type+"");
    	return Util.serialize(arr);
    }
    public static Part deserialize(String des) {
    	//   ArrayList<String> arr = Util.stringExplode(",",des);        
    	ArrayList<String> arr = Util.deserialize(des);
    	if(arr.size() != 4)
    		System.out.println("BAD PART DESERIALIZATION. ARRAY IS "+arr+"; SERIALIZED STRING IS "+des);
    	Part toreturn = new Part(arr.get(0),arr.get(1),Integer.parseInt(arr.get(2)));
	toreturn.deserializeSetType(Integer.parseInt(arr.get(4)));
    	toreturn.setFilename(arr.get(3));
    	return toreturn;
    }

    public static void main(String[] args) {
    	Part p = new Part("part1","mydescriptipn","test.png");
    	String s = p.serialize();
    	System.out.println("Serialized part:\n" + s);
    	Part p2 = Part.deserialize(s);
    	String s2 = p2.serialize();
    	System.out.println("Deserialized serialized part:\n" + s2);
    	if (s.equals(s2)) {
    		System.out.println("Test passed");
    	} else
    		System.out.println("TEST FAILED");
    }
    public Part clone()
    {
        if(this.getName() == null)
        {
            System.out.println("ATTEMPTING TO CLONE A PART WILL NULL NAME");
            
        }
        if(this.getDescription() == null)
        {
            System.out.println("ATTEMPTING OT PRINT A PART WITH NULL DESCp");
            
        }
        if(this.getNumber() == null)
        {
            System.out.println("ATTEMPTING OT PRINT A PART WITH NULL NUM");
        }
        Part n = new Part(this.getName(),this.getDescription(),this.getNumber());
        if(this.filename != null)
        n.setFilename(this.filename);
        return n;
    }
}
