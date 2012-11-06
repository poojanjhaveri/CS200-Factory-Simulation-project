package factory.factory200.partsManager;

import factory.general.Manager;
import factory.general.Message;
import java.awt.BorderLayout;
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
import javax.swing.border.EmptyBorder;

/**
 * <img src="../img/image02.png"/>
 * @brief JFrame that represents the parts manager
 * @author David Zhang, YiWei Roy Zheng
 */
public class PartsManager extends Manager implements ActionListener {
    // TODO: NEED GUIPart.java class in partsManager package

    private JPanel contentPane;
    private JLabel lblPartsManager;
    private JTabbedPane tabbedPane;
    private JPanel managePartsPanel, comboBoxPanel;
    private JComboBox partComboBox;
    private JPanel managePartsImagePanel;
    private JPanel managePartsButtonPanel;
    private JButton viewButton;
    private JButton deleteButton;
    private JPanel selectedPartPanel;
    private JPanel formPanel;
    private JPanel partNamePanel;
    private JPanel imageFileNamePanel;
    private JPanel descriptionPanel;
    private JPanel nonformPanel;
    private JPanel imagePanel;
    private JPanel buttonPanel;
    private JButton submitButton;
    private JPanel newPartPanel;

    private void prepareContentPane() {
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        lblPartsManager = new JLabel("Parts Manager");
        contentPane.add(lblPartsManager, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        managePartsPanel = new JPanel();
        tabbedPane.addTab("Manage Parts", null, managePartsPanel, null);
        managePartsPanel.setLayout(new BoxLayout(managePartsPanel, BoxLayout.Y_AXIS));

        comboBoxPanel = new JPanel();
        managePartsPanel.add(comboBoxPanel);
        comboBoxPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        partComboBox = new JComboBox();
        comboBoxPanel.add(partComboBox);

        managePartsImagePanel = new JPanel();
        managePartsPanel.add(managePartsImagePanel);

        managePartsButtonPanel = new JPanel();
        managePartsPanel.add(managePartsButtonPanel);

        viewButton = new JButton("View");
        managePartsButtonPanel.add(viewButton);
        viewButton.addActionListener(this);

        deleteButton = new JButton("Delete");
        managePartsButtonPanel.add(deleteButton);
        deleteButton.addActionListener(this);

        selectedPartPanel = new JPanel();
        tabbedPane.addTab("Selected Part", null, selectedPartPanel, null);
        selectedPartPanel.setLayout(new BoxLayout(selectedPartPanel, BoxLayout.X_AXIS));

        formPanel = new JPanel();
        selectedPartPanel.add(formPanel);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        partNamePanel = new JPanel();
        formPanel.add(partNamePanel);

        imageFileNamePanel = new JPanel();
        formPanel.add(imageFileNamePanel);

        descriptionPanel = new JPanel();
        formPanel.add(descriptionPanel);

        nonformPanel = new JPanel();
        selectedPartPanel.add(nonformPanel);
        nonformPanel.setLayout(new BoxLayout(nonformPanel, BoxLayout.Y_AXIS));

        imagePanel = new JPanel();
        nonformPanel.add(imagePanel);

        buttonPanel = new JPanel();
        nonformPanel.add(buttonPanel);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        submitButton = new JButton("Update");
        buttonPanel.add(submitButton);

        newPartPanel = new JPanel();
        tabbedPane.addTab("New Part", null, newPartPanel, null);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            p.println("delete button hit!");
            sendToServer(Message.TEST_SERVER);
        }
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        prepareContentPane();
    }

    /**
     * @brief creates a part based on form data; sends data to server
     */
    public void createPart() {
    }

    /**
     * @brief updates part data and sends data to server
     */
    public void updatePart() {
    }

    /**
     * @brief deletes the part with the specified part number and sends data to
     * the server
     */
    public void deletePart(int partNumber) {
    }
    /**
    @brief sends an update request which should result in the parts list being updated
     */
    public void update()
    {
        this.sendToServer(Message.PULL_PARTS_LIST);
    }
    private void parseUpdate(String msg)
    {
        //code to parse the serialized parts list
    }
    public void processMessage(String msg)
    {
        //super.processMessage(msg);
        //if(msg == Message.PUSH_PARTS_LIST)
        //   {

        // }
    }
}

//This is just a test to try out git
