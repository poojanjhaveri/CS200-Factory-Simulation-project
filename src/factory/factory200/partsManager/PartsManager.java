package factory.factory200.partsManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import factory.general.BlueprintParts;
import factory.general.Manager;
import factory.general.Message;
import factory.general.Part;

/**
 * <img src="../img/image02.png"/>
 * @brief JFrame that represents the parts manager
 * @author Jorge Rybar, David Zhang, YiWei Roy Zheng
 */

public class PartsManager extends Manager implements ActionListener {

	/**
	 * To avoid unexpected InvalidClassExceptions during deserialization
	 * we explicitly specify a UID, instead of relying on the compiler to generate one
	 */
	private static final long serialVersionUID = 1L;

	BlueprintParts bp;///<contains list of parts

	private JPanel contentPane;
	private JLabel lblPartsManager;
	private JTabbedPane tabbedPane;
	private JPanel pnlManageParts, comboBoxPanel;
	private JComboBox partComboBox;
	private JPanel managePartsImagePanel;
	private JPanel managePartsBottomPanel;
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
	private JLabel lblSelectedImage;
	private JComboBox cbImageFileName2,cbImageFileName;
	private JLabel lblSelectedImage2;
	private JLabel lblSelectedImage3;
	private boolean noPartSelected;
	private JPanel managePartsButtonPanel;
	private JLabel lblShowDescription;

	private void prepareContentPane() {
		contentPane = new JPanel()
		  {     
	            public void paintComponent(Graphics g) 
	            {
	                Image img = new ImageIcon("pics/background/mainbg.png").getImage();
	               // Dimension size = new Dimension(600, 400);
	               Dimension size= getSize();
	                setPreferredSize(size);
	                setMinimumSize(size);
	                setMaximumSize(size);
	                setSize(size);
	                getContentPane().setLayout(null);
	                
	                g.drawImage(img, 0, 0, null);
	            } 
	        };
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		lblPartsManager = new JLabel("Parts Manager");
		contentPane.add(lblPartsManager, BorderLayout.NORTH);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setOpaque(false);

		contentPane.add(tabbedPane, BorderLayout.CENTER);

		pnlManageParts = new JPanel();
		tabbedPane.addTab("Manage Parts", null, pnlManageParts, null);
		pnlManageParts.setLayout(new BoxLayout(pnlManageParts, BoxLayout.Y_AXIS));
		pnlManageParts.setOpaque(false);

		comboBoxPanel = new JPanel();
		pnlManageParts.add(comboBoxPanel);
		comboBoxPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		comboBoxPanel.setOpaque(false);

		
		partComboBox = new JComboBox();
		partComboBox.addActionListener(this);
		comboBoxPanel.add(partComboBox);

		managePartsImagePanel = new JPanel();
		managePartsImagePanel.setOpaque(false);
		lblSelectedImage= new JLabel();
		managePartsImagePanel.add(lblSelectedImage);
		pnlManageParts.add(managePartsImagePanel);  

		managePartsBottomPanel = new JPanel();
		managePartsBottomPanel.setOpaque(false);
		pnlManageParts.add(managePartsBottomPanel);

		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				processTabChange();
			}
		});
		managePartsBottomPanel.setLayout(new BorderLayout(0, 0));
		
		managePartsButtonPanel = new JPanel();
		managePartsButtonPanel.setOpaque(false);
		managePartsBottomPanel.add(managePartsButtonPanel, BorderLayout.SOUTH);
		
				btnView = new JButton("View");
				managePartsButtonPanel.add(btnView);
				
						btnDelete = new JButton("Delete");
						managePartsButtonPanel.add(btnDelete);
						
						lblShowDescription = new JLabel();
						lblShowDescription.setHorizontalAlignment(SwingConstants.CENTER);
						managePartsBottomPanel.add(lblShowDescription, BorderLayout.CENTER);
						btnDelete.addActionListener(this);
				btnView.addActionListener(this);

		pnlSelectedPart = new JPanel();
		pnlSelectedPart.setOpaque(false);
		tabbedPane.addTab("Selected Part", null, pnlSelectedPart, null);
		pnlSelectedPart.setLayout(new BoxLayout(pnlSelectedPart, BoxLayout.X_AXIS));

		pnlForm = new JPanel();
		pnlForm.setOpaque(false);
		pnlSelectedPart.add(pnlForm);
		pnlForm.setLayout(new BoxLayout(pnlForm, BoxLayout.Y_AXIS));

		pnlPartName = new JPanel();
		pnlPartName.setOpaque(false);
		pnlForm.add(pnlPartName);

		lblPartName = new JLabel("Part name");
		pnlPartName.add(lblPartName);

		tfPartName = new JTextField();
		pnlPartName.add(tfPartName);
		tfPartName.setColumns(10);

		pnlImageFileName = new JPanel();
		pnlImageFileName.setOpaque(false);
		pnlForm.add(pnlImageFileName);

		lblImageFileName = new JLabel("Image file name");
		pnlImageFileName.add(lblImageFileName);

		//tfImageFileName = new JTextField();
		//pnlImageFileName.add(tfImageFileName);
		//tfImageFileName.setColumns(10);
		

		cbImageFileName= new JComboBox();
		cbImageFileName.addActionListener(this);
		pnlImageFileName.add(cbImageFileName);

		pnlDescription = new JPanel();
		pnlDescription.setOpaque(false);
		pnlForm.add(pnlDescription);

		lblDescription = new JLabel("Description");
		pnlDescription.add(lblDescription);

		tfDescription = new JTextField();
		pnlDescription.add(tfDescription);
		tfDescription.setColumns(10);

		pnlNonform = new JPanel();
		pnlNonform.setOpaque(false);
		pnlSelectedPart.add(pnlNonform);
		pnlNonform.setLayout(new BoxLayout(pnlNonform, BoxLayout.Y_AXIS));

		lblPartNumber = new JLabel("[part num]");
		lblPartNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlNonform.add(lblPartNumber);

		pnlImage = new JPanel();
		pnlImage.setOpaque(false);
		pnlImage.setLayout(new BorderLayout());
		pnlNonform.add(pnlImage);

		pnlButton = new JPanel();
		pnlButton.setOpaque(false);
		pnlNonform.add(pnlButton);
		pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.X_AXIS));

		btnUpdate = new JButton("Update");
		pnlButton.add(btnUpdate);
		btnUpdate.addActionListener(this);

		pnlNewPart = new JPanel();
		pnlNewPart.setOpaque(false);
		tabbedPane.addTab("New Part", null, pnlNewPart, null);
		pnlNewPart.setLayout(new BoxLayout(pnlNewPart, BoxLayout.X_AXIS));

		pnlForm2 = new JPanel();
		pnlForm2.setOpaque(false);
		pnlNewPart.add(pnlForm2);
		pnlForm2.setLayout(new BoxLayout(pnlForm2, BoxLayout.Y_AXIS));

		pnlPartName2 = new JPanel();
		pnlPartName2.setOpaque(false);
		pnlForm2.add(pnlPartName2);

		lblPartName2 = new JLabel("Part name:");
		pnlPartName2.add(lblPartName2);

		tfPartName2 = new JTextField();
		pnlPartName2.add(tfPartName2);
		tfPartName2.setColumns(10);

		pnlImageFileName2 = new JPanel();
		pnlImageFileName2.setOpaque(false);
		pnlForm2.add(pnlImageFileName2);

		lblImageFileName2 = new JLabel("Image File Name");
		pnlImageFileName2.add(lblImageFileName2);

		//tfImageFileName2 = new JTextField();
		//pnlImageFileName2.add(tfImageFileName2);
		//tfImageFileName2.setColumns(10);
		
		cbImageFileName2= new JComboBox();
		cbImageFileName2.addActionListener(this);
		pnlImageFileName2.add(cbImageFileName2);
		

		pnlDescription2 = new JPanel();
		pnlDescription2.setOpaque(false);
		pnlForm2.add(pnlDescription2);

		lblDescription2 = new JLabel("Description");
		pnlDescription2.add(lblDescription2);

		tfDescription2 = new JTextField();
		pnlDescription2.add(tfDescription2);
		tfDescription2.setColumns(10);

		pnlNonform2 = new JPanel();
		pnlNonform2.setOpaque(false);
		pnlNewPart.add(pnlNonform2);
		pnlNonform2.setLayout(new BoxLayout(pnlNonform2, BoxLayout.Y_AXIS));

		pnlImage2 = new JPanel();
		pnlImage2.setOpaque(false);
		pnlImage2.setLayout(new BorderLayout());
		pnlNonform2.add(pnlImage2);

		pnlButton2 = new JPanel();
		pnlButton2.setOpaque(false);
		pnlNonform2.add(pnlButton2);
		pnlButton2.setLayout(new BoxLayout(pnlButton2, BoxLayout.X_AXIS));

		btnCreate = new JButton("Create");
		btnCreate.addActionListener(this);
		pnlButton2.add(btnCreate);

		updatePartComboBox();
		updateManagePartsImagePanel();
		populateFileComboBoxes();
		checkToDisableTabs();
		noPartSelected=true;
		
		if(bp.getSize()==0)
			tabbedPane.setSelectedIndex(NEW_PART_TAB_NUM);
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDelete) {
			deletePart(getCurrentPart());
		} else if (e.getSource() == btnView) {
			//if no parts then disable Index 1 (selected part tab)
			if(!noPartSelected)
				tabbedPane.setSelectedIndex(SELECTED_PART_TAB_NUM);
		
		} else if (e.getSource() == btnUpdate) {
			updatePart(); // fill in below
		} else if (e.getSource() == btnCreate) {
			createPart();
		} else if (e.getSource()==partComboBox){
			//when partComboBox is clicked update managePartsImagePanel
			updateManagePartsImagePanel();
			updateDescription();
		} else if (e.getSource()==cbImageFileName){
			updateSelectedPartsImagePanel();
		} else if (e.getSource()==cbImageFileName2){
			updateNewPartsImagePanel();
		} 
		
		checkToDisableTabs();
		this.repaint();
	}


	
	/**
	 * @brief returns the current part
	 * @return the current part
	 */
	private Part getCurrentPart() {
		
		int n=partComboBox.getSelectedIndex();
		//temp code
		System.out.println("Selected Index: "+n);
		String s= (String) partComboBox.getSelectedItem();
		System.out.println("Selected Item: "+s);
		if (n!=0){
			Part temp= new Part(null,null);
			for (int i=0;i<bp.getSize();i++) {
				if (bp.getPartAt(i).getName().equals(s)) {
					temp=bp.getPartAt(i);
					break;
				}
			}
			noPartSelected=false;
			return temp;
		}else{
			Part temp=new Part(null,null);
			noPartSelected=true;
			return temp;
		}
	}

	private void updateManagePartsImagePanel(){
		Part temp= getCurrentPart();
		managePartsImagePanel.removeAll();

		if(temp.getFilename()!=null)
			{lblSelectedImage= new JLabel(new ImageIcon(temp.getFilename()));
			managePartsImagePanel.add(lblSelectedImage);
			}
	}
	private void updateSelectedPartsImagePanel(){
		String s= (String) cbImageFileName.getSelectedItem();

		if(s!=null)
			lblSelectedImage2= new JLabel(new ImageIcon(s));
		pnlImage.removeAll();
		pnlImage.add(lblSelectedImage2,BorderLayout.CENTER);
	}
	private void updateNewPartsImagePanel(){
		String s= (String) cbImageFileName2.getSelectedItem();

		if(s!=null)
			lblSelectedImage3= new JLabel(new ImageIcon(s));
		pnlImage2.removeAll();
		pnlImage2.add(lblSelectedImage3, BorderLayout.CENTER);
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

		this.sendToServer(Message.PULL_PARTS_LIST);
		prepareContentPane();
		this.update();
	}

	/**
	 * @brief creates a part based on form data locally
	 */
	public void createPart() {
		String name, file, d;
		Part temp;

		try{
		name=tfPartName2.getText();
		if (name.equals(""))
			throw new Exception();
		}catch(Exception e){
			//make  tfPartName2 become pink
			tfPartName2.setBackground(Color.pink);
			return;
		}
		file=(String)cbImageFileName2.getSelectedItem();
		d=tfDescription2.getText();

		
			temp=new Part(name, d, file);
			//make  tfPartName2 become white
			tfPartName2.setBackground(Color.white);
			


			bp.add(temp);

			tfPartName2.setText("");
			tfDescription2.setText("");
			btnCreate.setSelected(false);//not working

			/*added by Roy 11/12/12*/
			//this will send the new part to the server
			this.sendToServer(Message.DEFINE_NEW_PART+":"+temp.serialize());
			System.out.println("Sent new part definition: "+temp.serialize());
			/*end*/
		
			checkToDisableTabs();
			
			

	}

	/**
	 * @brief updates part data and sends data to server. currently implemented as
	 * just delete part, and then createPart with new data, in order to minimize 
	 * writing more code for the server
	 *
	 */
	public void updatePart() {
		if(!noPartSelected){
			deletePart(getCurrentPart()); //delete current part

			//make a new part
			String name, file, d;
			Part temp;
			name=tfPartName.getText();
			file=(String)cbImageFileName.getSelectedItem();
			d=tfDescription.getText();

			temp=new Part(name, d, file);

			bp.add(temp);
			btnUpdate.setSelected(false);//not working

			//Since we're making a new part 
			this.sendToServer(Message.DEFINE_NEW_PART+":"+temp.serialize());
			System.out.println("Sent new part definition: "+temp.serialize());
		}
	}

	/**
	 * @brief deletes the part with the specified part number and sends data to
	 * the server
	 */
	public void deletePart(Part pt) {
		if (!noPartSelected){
			String s = Message.UNDEFINE_PART+":"+pt.getNumber();
			System.out.println("sending message " + s);
			this.sendToServer(s);
			bp.removePart(pt);
			updatePartComboBox();
			updateManagePartsImagePanel();
			updateDescription();
			this.repaint();
			}
		checkToDisableTabs();
	}

	/**
	 * @brief sends an update request which should result in the parts list being updated
	 */
	public void update() {
		this.sendToServer(Message.PULL_PARTS_LIST);
		System.out.println("Local copy updated with server copy");
	}

	public void processMessage(String msg) {
		super.processMessage(msg);
		if (msg.contains( Message.PUSH_PARTS_LIST)) {

			this.bp.recreate(this.grabParameter(msg));
			System.out.println("GRABBED NEW PARTS LIST FROM SERVER!: "+msg);
			this.bp.debug();
		}
	}

	private void processTabChange() {
		Component c = tabbedPane.getSelectedComponent();
		if (c.equals(pnlManageParts)){
			//update the list of parts 
			updatePartComboBox();   	
			updateManagePartsImagePanel();
			updateDescription();
			
		}
		else if (c.equals(pnlSelectedPart)){
			Part p= getCurrentPart();

			tfPartName.setText(p.getName());
			cbImageFileName.setSelectedItem(p.getFilename());
			tfDescription.setText(p.getDescription());
			lblPartNumber.setText("Part #"+p.getNumber()+"");
			updateSelectedPartsImagePanel();
			
		}
		else if (c.equals(pnlNewPart)){
			updateNewPartsImagePanel();
		}
		checkToDisableTabs();
	}


	/**
	 * @brief updates the list of parts every time the Manage parts tab is clicked
	 */

	private void updateDescription(){
		if(!noPartSelected){
			Part temp=getCurrentPart();
			String s= temp.getDescription();
			lblShowDescription.setText(s);
			this.repaint();
		}
		
	}
	
	private void updatePartComboBox(){
		managePartsImagePanel.removeAll();
		partComboBox.removeAllItems(); 
		partComboBox.addItem("-No Part Selected-");
		for(int i=0;i<bp.getSize();i++){
			partComboBox.addItem(bp.getPartAt(i).getName());     

		}
	}
	
	private void populateFileComboBoxes(){
            
            /*
		  final ImageFilter imageFilter = new ImageFilter();
	      final File dir = new File("pics/parts");
	      for(final File imgFile : dir.listFiles()) {
	    
	    	  if (imageFilter.accept(imgFile)){
	    	  cbImageFileName.addItem(imgFile.toString());
	    	  cbImageFileName2.addItem(imgFile.toString());
	    	  }*/
            
            cbImageFileName.addItem("pics/parts/part1.png");
            cbImageFileName.addItem("pics/parts/part2.png");
            cbImageFileName.addItem("pics/parts/part3.png");
            cbImageFileName.addItem("pics/parts/part4.png");
            cbImageFileName.addItem("pics/parts/part5.png");
            cbImageFileName.addItem("pics/parts/part6.png");
            cbImageFileName.addItem("pics/parts/part7.png");
            cbImageFileName.addItem("pics/parts/part8.png");
            
            cbImageFileName2.addItem("pics/parts/part1.png");
            cbImageFileName2.addItem("pics/parts/part2.png");
            cbImageFileName2.addItem("pics/parts/part3.png");
            cbImageFileName2.addItem("pics/parts/part4.png");
            cbImageFileName2.addItem("pics/parts/part5.png");
            cbImageFileName2.addItem("pics/parts/part6.png");
            cbImageFileName2.addItem("pics/parts/part7.png");
            cbImageFileName2.addItem("pics/parts/part8.png");
            
	          
	      //} 
		
		
	}
	
	private class ImageFilter{
		
	    String GIF = "gif";
	    String PNG = "png";
	    String JPG = "jpg";
	    String BMP = "bmp";
	    String JPEG = "jpeg";

	    public boolean accept(File file) {
	        if(file != null) {
	            if(file.isDirectory()||file.toString().equals("pics/parts/no.png")||file.toString().equals("pics/parts/badpart.png"))
	                return false;
	            String extension = getExtension(file);
	            if(extension != null && isSupported(extension))
	                return true;
	        }
	        return false;
	    }

	    private String getExtension(File file) {
	        if(file != null) {
	            String filename = file.getName();
	            int dot = filename.lastIndexOf('.');
	            if(dot > 0 && dot < filename.length()-1)
	                return filename.substring(dot+1).toLowerCase();
	        }
	        return null;
	    }

	    private boolean isSupported(String ext) {
	        return ext.equalsIgnoreCase(GIF) || ext.equalsIgnoreCase(PNG) ||
	                ext.equalsIgnoreCase(JPG) || ext.equalsIgnoreCase(BMP) ||
	                ext.equalsIgnoreCase(JPEG);
	    }
	}
	
	
	private void checkToDisableTabs(){
		if (noPartSelected)
			tabbedPane.setEnabledAt(1, false);
		else 
			tabbedPane.setEnabledAt(1, true);
		
	}
	
}

/*
 * TODO
 * 
 * -display part description in pnlManageParts (by adding a new JPanel and JLabel)
 * -change part description to textArea instead of textfield
 * 
 * -fix code comments/ gardening
 * -add test cases to wiki 
 * > reads in any image files that are added to pics/parts, verifying their extensions
 *
 * QUESTIONS
 * -make Create and update button unselected after click
 * 
 * BUGS
 * 
 * EXTRAS
 * -shade disabled tabs and buttons
 * -make images be displayed IN the comboboxes
 * 
 * 
 */
