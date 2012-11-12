package factory.factory200.kitManager;

import factory.general.Manager;
import factory.general.Message;
import javax.swing.JFrame;
import javax.swing.*;



/*
 * 
 * 
 * 
 * @author : Poojan Jhaveri
 * @brief : Kit containing the parts for production
 * 
 * 
 */



public class KitsManager extends Manager {

	/**
	 * @param args
	 */
         private JPanel contentpane;
    
    
	
         public KitsManager()
         {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 450, 300);
             
             preparepanel();
         }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

            KitsManager frame = new KitsManager();
            frame.setVisible(true);
            
	}
	
	//getters and setters as needed 
	
        private void preparepanel()
        {
            contentpane = new JPanel();
          
            
            
            
            
            
        }
        

}
