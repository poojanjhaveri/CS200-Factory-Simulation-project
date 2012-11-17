/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory200.factoryProductionManager;

/**
 *
 * @author Deepa
 */

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import factory.general.BlueprintKits;

import factory.general.Kit;
import factory.general.Manager;
import factory.general.Message;

//just for testing the graphics panel
public class Test extends JFrame {
    GraphicsPanel g;
	public Test(){
            g=new GraphicsPanel();
            this.add(g);
            this.setVisible(true);
	    this.setSize(1375,700);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        public static void main(String args[]){
            Test t=new Test();
        }
}

