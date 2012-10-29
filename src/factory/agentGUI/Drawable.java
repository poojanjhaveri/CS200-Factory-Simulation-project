package factory.agentGUI;

/**
 * @brief anything drawable onto the FactoryProductionManager Frame.
 * @author YiWei Roy Zheng
 */
public class Drawable {

    Integer posX;///<current X-coordinate of the drawable
    Integer poxY;///<current Y-coordinate of the drawable
    Double rotation;///<information on degrees of rotation. 0 degrees is due right

    ImageIcon img;///<image of the drawable

    public Drawable(Integer x, Integer y, ImageIcon i)
    {
	this.posX = x;
	this.posY = y;
	this.rotation = 0;
	this.img = i;
    }
    public Drawable(Integer x, Integer y, String s)
    {
	this.posX = x;
	this.posY = y;
	this.rotation = 0;
	this.img = new ImageIcon(s);
    }
    public ImageIcon getImage()
    {
	return this.img;
    }

}