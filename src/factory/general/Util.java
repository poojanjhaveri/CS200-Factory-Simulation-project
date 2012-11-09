import java.util.ArrayList;

/**
@brief utility functions go here
@author YiWei Roy Zheng
 */

public class Util{

    public static ArrayList<String> stringExplode(String needle,String haystack)
    {
	ArrayList<String> toreturn = new ArrayList<String>();
	String single = "";
	for(int i = 0; i != haystack.length(); i++)
	    {
		if(needle.charAt(0) == haystack.charAt(i))
		    {
			if(!(single.length() == 0))
			    {
				toreturn.add(single);
				single = "";
			    }
		    }
		else single = single + haystack.charAt(i);
		if(i == haystack.length()-1 && single.length() != 0)
		    {
			toreturn.add(single);
		    }
	    }
	return toreturn;
    }
    public static String stringImplode(String delimiter,ArrayList<String> array)
    {
	String toreturn = "";
	for(int i = 0; i != array.size(); i++)
	    {
		toreturn = toreturn + array.get(i);
		if(i != array.size()-1){
		    toreturn = toreturn + delimiter;
		}
	    }
	return toreturn;
    }

    public static void main(String[] args)
    {
	ArrayList<String> explode = Util.stringExplode(" ","hey there bro"); 
	System.out.println(explode);
	String hey = "hey";
	String h2 = "h2";
	System.out.println(hey.charAt(0) == h2.charAt(0));
	System.out.println(Util.stringImplode(",",explode));
    }

}