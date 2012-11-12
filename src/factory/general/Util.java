package factory.general;

import java.util.ArrayList;

/**
@brief utility functions go here
@author YiWei Roy Zheng
 */

public class Util {

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
            if(i != array.size()-1) {
                toreturn = toreturn + delimiter;
            }
        }
        return toreturn;
    }
    /**
    parses the serialized string in the following format
    ##sssssssssssssssssssss##ssssssssssssssssssssssssss#ssss
    where # is a number and s is a character
    # determines how many characters to parse
     */
    public static ArrayList<String> deserialize(String serialized)
    {
        ArrayList<String> stringform = new ArrayList<String>();
        String integer = "";
        for(int i = 0; i != serialized.length(); i++)
        {
            if(serialized.charAt(i) == '(' && integer.length() != 0)
            {
                Integer chars = Integer.parseInt(integer);
		//                System.out.println("DEBUG:"+chars);
                integer = "";
                String part = "";
                for(int ii = 0; ii != chars; ii++)
                {
                    part = part + serialized.charAt(ii+i);
                }
                String s = "";
                for(int ii = 1; ii < part.length()-1; ii++)
                    s = s+part.charAt(ii);

                stringform.add(s);
                i += chars-1;
            }
            else
            {
                integer = integer + serialized.charAt(i);
            }
        }
        return stringform;
    }
    public static String serialize(ArrayList<String> arr)
    {
	String toreturn = "";
	for(int i = 0; i != arr.size(); i++)
	    {
		toreturn = toreturn + (arr.get(i).length()+2)+"("+arr.get(i)+")";
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