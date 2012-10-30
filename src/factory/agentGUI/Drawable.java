package factory.agentGUI;

import javax.swing.ImageIcon;

/**
 * @brief anything drawable onto the FactoryProductionManager Frame.
 * @author YiWei Roy Zheng
 */
public class Drawable {

    Coordinate cords;
    Double rotation;///<information on degrees of rotation. 0 degrees is due right

    ImageIcon img;///<image of the drawable

    public Drawable(Integer x, Integer y, Double r, ImageIcon i)
    {
        this.cords = new Coordinate(x,y);
        this.rotation = r;
        this.img = i;
    }
    public Drawable(Integer x, Integer y, Double r,String s)
    {
        this.cords = new Coordinate(x,y);
        this.rotation = r;
        this.img = new ImageIcon(s);
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