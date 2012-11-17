package factory.factory200.partsManager;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import factory.general.Manager;
import factory.general.Message;
import factory.general.BlueprintParts;
import factory.general.Part;

/**
 * <img src="../img/image02.png"/>
 * @brief JFrame that represents the parts manager
 * @author David Zhang, YiWei Roy Zheng, Jorge Rybar
 */

public class PartsManager extends Manager implements ActionListener {


    BlueprintParts bp;///<contains list of parts

    private JPanel contentPane;
    private JLabel lblPartsManager;
    private JTabbedPane tabbedPane;
    private JPanel pnlManageParts, comboBoxPanel;
    private JComboBox partComboBox;
    private JPanel managePartsImagePanel;
    private JPanel managePartsButtonPanel;
    private JButton btnView;
    private JButton btnDelete;
    private JPanel pnlSelectedPart;
    private JPanel pnlForm;
    private JPanel pnlPartName;
    private JPanel pnlImageFileName;
    private JPanel pnlDescription;
    private JPanel pnlNonform;
    private JPanel pnlImage;
    private JPanel pnlButton;
    private JButton btnUpdate;
    private JPanel pnlNewPart;
    private JLabel lblPartName;
    private JLabel lblImageFileName;
    private JLabel lblDescription;
    private JTextField tfPartName;
    private JTextField tfImageFileName;
    private JTextField tfDescription;
    
    private static final int MANAGE_PARTS_TAB_NUM = 0;
    private static final int NEW_PART_TAB_NUM = 2;
    private static final int SELECTED_PART_TAB_NUM = 1;
    private JLabel lblPartNumber;
    private JPanel pnlForm2;
    private JPanel pnlNonform2;
    private JPanel pnlImage2;
    private JPanel pnlPartName2;
    private JPanel pnlImageFileName2;
    private JPanel pnlDescription2;
    private JLabel lblPartName2;
    private JTextField tfPartName2;
    private JLabel lblImageFileName2;
    private JTextField tfImageFileName2;
    private JLabel lblDescription2;
    private JTextField tfDescription2;
    private JPanel pnlButton2;
    private JButton btnCreate;

    private void prepareContentPane() {
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        lblPartsManager = new JLabel("Parts Manager");
        contentPane.add(lblPartsManager, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
 
        
        
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        pnlManageParts = new JPanel();
        tabbedPane.addTab("Manage Parts", null, pnlManageParts, null);
        pnlManageParts.setLayout(new BoxLayout(pnlManageParts, BoxLayout.Y_AXIS));
        
                comboBoxPanel = new JPanel();
                pnlManageParts.add(comboBoxPanel);
                comboBoxPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
                
                        partComboBox = new JComboBox();
                        comboBoxPanel.add(partComboBox);
   
        managePartsImagePanel = new JPanel();
        pnlManageParts.add(managePartsImagePanel);  

        managePartsButtonPanel = new JPanel();
        pnlManageParts.add(managePartsButtonPanel);
        
        tabbedPane.addChangeListener(new ChangeListener() {
          public void stateChanged(ChangeEvent e) {
            processTabChange();
          }
        });

        btnView = new JButton("View");
        managePartsButtonPanel.add(btnView);
        btnView.addActionListener(this);

        btnDelete = new JButton("Delete");
        managePartsButtonPanel.add(btnDelete);
        btnDelete.addActionListener(this);

        pnlSelectedPart = new JPanel();
        tabbedPane.addTab("Selected Part", null, pnlSelectedPart, null);
        pnlSelectedPart.setLayout(new BoxLayout(pnlSelectedPart, BoxLayout.X_AXIS));

        pnlForm = new JPanel();
        pnlSelectedPart.add(pnlForm);
        pnlForm.setLayout(new BoxLayout(pnlForm, BoxLayout.Y_AXIS));

        pnlPartName = new JPanel();
        pnlForm.add(pnlPartName);
        
        lblPartName = new JLabel("Part name");
        pnlPartName.add(lblPartName);
        
        tfPartName = new JTextField();
        pnlPartName.add(tfPartName);
        tfPartName.setColumns(10);

        pnlImageFileName = new JPanel();
        pnlForm.add(pnlImageFileName);
        
        lblImageFileName = new JLabel("Image file name");
        pnlImageFileName.add(lblImageFileName);
        
        tfImageFileName = new JTextField();
        pnlImageFileName.add(tfImageFileName);
        tfImageFileName.setColumns(10);

        pnlDescription = new JPanel();
        pnlForm.add(pnlDescription);
        
        lblDescription = new JLabel("Description");
        pnlDescription.add(lblDescription);
        
        tfDescription = new JTextField();
        pnlDescription.add(tfDescription);
        tfDescription.setColumns(10);

        pnlNonform = new JPanel();
        pnlSelectedPart.add(pnlNonform);
        pnlNonform.setLayout(new BoxLayout(pnlNonform, BoxLayout.Y_AXIS));
        
        lblPartNumber = new JLabel("[part num]");
        lblPartNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlNonform.add(lblPartNumber);

        pnlImage = new JPanel();
        pnlNonform.add(pnlImage);

        pnlButton = new JPanel();
        pnlNonform.add(pnlButton);
        pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.X_AXIS));

        btnUpdate = new JButton("Update");
        pnlButton.add(btnUpdate);
        btnUpdate.addActionListener(this);

        pnlNewPart = new JPanel();
        tabbedPane.addTab("New Part", null, pnlNewPart, null);
        pnlNewPart.setLayout(new BoxLayout(pnlNewPart, BoxLayout.X_AXIS));
        
        pnlForm2 = new JPanel();
        pnlNewPart.add(pnlForm2);
        pnlForm2.setLayout(new BoxLayout(pnlForm2, BoxLayout.Y_AXIS));
        
        pnlPartName2 = new JPanel();
        pnlForm2.add(pnlPartName2);
        
        lblPartName2 = new JLabel("Part name:");
        pnlPartName2.add(lblPartName2);
        
        tfPartName2 = new JTextField();
        pnlPartName2.add(tfPartName2);
        tfPartName2.setColumns(10);
        
        pnlImageFileName2 = new JPanel();
        pnlForm2.add(pnlImageFileName2);
        
        lblImageFileName2 = new JLabel("Image File Name");
        pnlImageFileName2.add(lblImageFileName2);
        
        tfImageFileName2 = new JTextField();
        pnlImageFileName2.add(tfImageFileName2);
        tfImageFileName2.setColumns(10);
        
        pnlDescription2 = new JPanel();
        pnlForm2.add(pnlDescription2);
        
        lblDescription2 = new JLabel("Description");
        pnlDescription2.add(lblDescription2);
        
        tfDescription2 = new JTextField();
        pnlDescription2.add(tfDescription2);
        tfDescription2.setColumns(10);
        
        pnlNonform2 = new JPanel();
        pnlNewPart.add(pnlNonform2);
        pnlNonform2.setLayout(new BoxLayout(pnlNonform2, BoxLayout.Y_AXIS));
        
        pnlImage2 = new JPanel();
        pnlNonform2.add(pnlImage2);
        
        pnlButton2 = new JPanel();
        pnlNonform2.add(pnlButton2);
        pnlButton2.setLayout(new BoxLayout(pnlButton2, BoxLayout.X_AXIS));
        
        btnCreate = new JButton("Create");
        btnCreate.addActionListener(this);
        pnlButton2.add(btnCreate);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDelete) {
            deletePart(getCurrentPart());
        } else if (e.getSource() == btnView) {
            tabbedPane.setSelectedIndex(SELECTED_PART_TAB_NUM);
        } else if (e.getSource() == btnUpdate) {
            updatePart(); // fill in below
        } else if (e.getSource() == btnCreate) {
        	createPart();
        }
    }

    /**
     * @brief returns the current part
     * @return the current part
     */
    private Part getCurrentPart() {
    	String s= (String) partComboBox.getSelectedItem();
    	Part temp= new Part(null,null);
    	for (int i=0;i<bp.getSize();i++) {
    		if (bp.getPartAt(i).getName().equals(s)) {
    			temp=bp.getPartAt(i);
    			break;
    		}
    		
    	}
    	return temp;
    }
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    PartsManager frame = new PartsManager();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public PartsManager() {
    	this.bp = new BlueprintParts();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        prepareContentPane();
	//	this.mcon.out(Message.PULL_PARTS_LIST);
        this.update();
    }

    /**
     * @brief creates a part based on form data locally
     */
    public void createPart() {
    	String name, file, d;
    	Part temp;
    	
    	name=tfPartName2.getText();
    	file=tfImageFileName2.getText();
    	d=tfDescription2.getText();
    	
    	temp=new Part(name, d, file);
    	
    	bp.add(temp);
    	
    	tfPartName2.setText("");
    	tfImageFileName2.setText("");
    	tfDescription2.setText("");
    	btnCreate.setSelected(false);//not working

	/*added by Roy 11/12/12*/
	//this will send the new part to the server
		this.mcon.out(Message.DEFINE_NEW_PART+":"+temp.serialize());
		System.out.println("Sent new part definition: "+temp.serialize());
	/*end*/
    }

    /**
     * @brief updates part data and sends data to server. currently implemented as
     * just delete part, and then createPart with new data, in order to minimize 
     * writing more code for the server
     *
     */
    public void updatePart() {
    	
    	deletePart(getCurrentPart()); //delete current part
    	
    	//make a new part
    	String name, file, d;
    	Part temp;
    	name=tfPartName.getText();
    	file=tfImageFileName.getText();
    	d=tfDescription.getText();
    	
    	temp=new Part(name, d, file);
    	
    	bp.add(temp);
    	btnUpdate.setSelected(false);//not working
    	
    	//Since we're making a new part 
		this.mcon.out(Message.DEFINE_NEW_PART+":"+temp.serialize());
		System.out.println("Sent new part definition: "+temp.serialize());
    	
    	
    	
    }

    /**
     * @brief deletes the part with the specified part number and sends data to
     * the server
     */
    public void deletePart(Part pt) {
        String s = Message.UNDEFINE_PART+":"+pt.getNumber();
        System.out.println("sending message " + s);
        this.mcon.out(s);
    	bp.removePart(pt);
    	updateComboBox();
    }
    
    /**
     * @brief sends an update request which should result in the parts list being updated
     */
    public void update() {
        this.mcon.out(Message.PULL_PARTS_LIST);
	System.out.println("Local copy updated with server copy");
    }
    
    private void parseUpdate(String msg) {
        //code to parse the serialized parts list
    }
    
    
    public void processMessage(String msg) {
        super.processMessage(msg);
        if(msg.contains( Message.PUSH_PARTS_LIST)) {
        	
	       this.bp.recreate(this.grabParameter(msg));
	       System.out.println("GRABBED NEW PARTS LIST FROM SERVER!: "+msg);
		   this.bp.debug();
	   }
    }
    
    private void processTabChange() {
      Component c = tabbedPane.getSelectedComponent();
     if (c.equals(pnlManageParts)){
    	 //update the list of parts 
    	updateComboBox();   		 
    	 }
     else if (c.equals(pnlSelectedPart)){
    	 Part p= getCurrentPart();
    	 
    	 tfPartName.setText(p.getName());
    	 tfImageFileName.setText(p.getFilename());
    	 tfDescription.setText(p.getDescription());
    	 
    	 
     }
    	 
     }

    
    /**
     * @brief updates the list of parts every time the Manage parts tab is clicked
     */
    
    private void updateComboBox(){
    	 partComboBox.removeAllItems();
    	 for(int i=0;i<bp.getSize();i++){
    		 partComboBox.addItem(bp.getPartAt(i).getName());     
    	
    	 }
    }
}

/*
 * TODO
 * 
 * -add images support
 * -make it pretty 
 * 
 *	-ROY:  Delete part from server
 *
 *
 * QUESTIONS
 * 
 * 
 * BUGS
 * -make Create and update button unselected after click
 * -
 * 
 * 
 */
