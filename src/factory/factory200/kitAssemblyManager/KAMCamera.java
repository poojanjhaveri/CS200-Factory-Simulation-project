/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory200.kitAssemblyManager;


import javax.swing.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Deepa
 */
public class KAMCamera {
    private ImageIcon camera;
    private boolean visible;
    private int x;
    private int y;
    
    public KAMCamera(){
        camera=new ImageIcon("KAMCamera.png");
        visible=false;
    }

    /**
     * @return the camera
     */
    public ImageIcon getCamera() {
        return camera;
    }

    /**
     * @param camera the camera to set
     */
    public void setCamera(ImageIcon camera) {
        this.camera = camera;
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
    
    
}
