package factory.agentGUI;

import javax.swing.ImageIcon;

/**
 * @brief anything drawable onto the FactoryProductionManager Frame.
 * @author YiWei Roy Zheng
 */
public class Drawable {

    Integer posX;///<current X-coordinate of the drawable
    Integer posY;///<current Y-coordinate of the drawable
    Double rotation;///<information on degrees of rotation. 0 degrees is due right

    ImageIcon img;///<image of the drawable

    public Drawable(Integer x, Integer y, Double r, ImageIcon i)
    {
        this.posX = x;
        this.posY = y;
        this.rotation = r;
        this.img = i;
    }
    public Drawable(Integer x, Integer y, Double r,String s)
    {
        this.posX = x;
        this.posY = y;
        this.rotation = r;
        this.img = new ImageIcon(s);
    }

    public ImageIcon getImage()
    {
        return this.img;
    }

}