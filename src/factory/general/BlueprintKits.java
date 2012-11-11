package factory.general;

import java.util.ArrayList;
import java.io.Serializable;
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
    public ArrayList<Kit> deserialize() {


    }
    /**
    @brief deserializes the passed string and adds to the current data
     */
    void updateOne(String serialized)
    {
        this.kits.addAll(this.deserialize(serialized));
    }
    /**
    @brief serializes the current data into a deserializable string
     */
    String serialize()
    {
        String toreturn ="";
        for(int i = 0; i != this.kits.size(); i++)
        {
            toreturn = toreturn+this.kits.get(i).serialize().length()+this.kits.get(i).serialize();
        }
        return toreturn;
    }
    /**
    @brief turns the blueprint into a serialized string
     */
    public void recreate(String serialized)
    {
        this.parts = this.deserialize(serialized);
    }
    public static void main(String[] args)
    {
        BlueprintKits bp = new BlueprintKits();
    }

}