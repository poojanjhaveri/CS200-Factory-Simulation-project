package factory.general;

import javax.swing.ImageIcon;

public class GUIPart extends Drawable{

    private ImageIcon img;

    public GUIPart(Integer x, Integer y, Double r, ImageIcon i)
    {
	super(x,y,r,i);
    }

    public ImageIcon getImage() {
        return this.img;
    }


}