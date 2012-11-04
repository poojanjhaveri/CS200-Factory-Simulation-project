package factory.general;

import factory.general.Coordinate;
import javax.swing.ImageIcon;

/**
 * @brief anything drawable onto the FactoryProductionManager Frame.
 * @author YiWei Roy Zheng
 */
public class Drawable {

    protected Coordinate cords;
    protected Double rotation;///<information on degrees of rotation. 0 degrees is due right

    ImageIcon img;///<image of the drawable

    public Drawable(Integer x, Integer y, Double r, ImageIcon i)
    {
        this.cords = new Coordinate(x,y);
        this.setRotation(r);
        this.img = i;
    }
    public Drawable(Integer x, Integer y, Double r,String s)
    {
        this.cords = new Coordinate(x,y);
        this.rotation = r;
        this.img = new ImageIcon(s);
    }
    public void setRotation(Double in)
    {
        while(in >= 360)in -= 360;
        this.rotation = in;
    }
    public Coordinate getCoordinate()
    {
        return this.cords;
    }
    public Double getRotation()
    {
        return this.rotation;
    }
    public ImageIcon getImage()
    {
        return this.img;
    }

}