package factory.factory200.factoryProductionManager;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel for non-normative controls; placed in FPM 
 * @author David Zhang
 */
public class NonNormCtrlPanel extends JPanel {
	// 3 broad non-normative cases: Kit Fails, Parts Missing, No Good Parts
	private JPanel kitFailsPanel;
	private JPanel partsMissingPanel;
	private JPanel noGoodPartPanel;

	private JPanel statusPanel;
	private static final JLabel STATUS_LABEL = new JLabel("Currently running scenario: ");
	private JPanel controlPanel;
	private JLabel currentStatusLabel; // the JLabel you update to show the status

	private JLabel kitFailsLabel, partsMissingLabel, noGoodPartsLabel;
	private JComboBox kitFailsComboBox, partsMissingComboBox, noGoodPartsComboBox;
	private JButton btnLaunchKitFails, btnLaunchMissingParts, btnLaunchNoGoodParts;

	private static String[] KIT_FAILS_OPTIONS = { "Parts missing from the kit (part robot drops a part)"};
	private static String[] PARTS_MISSING_OPTIONS = { "Need to wait longer", "Lane jammed", "Feeder algorithm too slow"};
	private static String[] NO_GOOD_PART_OPTIONS = { "All parts are bad", "Parts piled up!", "Robot in the way", "Parts not stable"};

	private static Dimension BUTTON_DIMENSION = new Dimension(300,100);
	private JPanel kitFailsInnerPanel;
	private JPanel partsMissingInnerPanel;
	private JPanel noGoodPartsInnerPanel;


	public NonNormCtrlPanel() {
		preparePanel();
//		kitFailsPanel.add(Box.createRigidArea(new Dimension(1, 0)));
	}

	private void preparePanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		controlPanel = new JPanel();
		controlPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(controlPanel);
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));

		kitFailsPanel = new JPanel();
		
		controlPanel.add(kitFailsPanel);
		kitFailsPanel.setLayout(new BoxLayout(kitFailsPanel, BoxLayout.Y_AXIS));

		kitFailsLabel = new JLabel("Bad Kit - Non normative");
		kitFailsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		kitFailsPanel.add(kitFailsLabel);
		kitFailsLabel.setFont(new Font("Century", Font.PLAIN, 17));

		kitFailsInnerPanel = new JPanel();
		kitFailsPanel.add(kitFailsInnerPanel);

		kitFailsComboBox = new JComboBox(KIT_FAILS_OPTIONS);
		kitFailsInnerPanel.add(kitFailsComboBox);

		btnLaunchKitFails = new JButton("Launch Bad Kit Scenario");
		kitFailsInnerPanel.add(btnLaunchKitFails);
		btnLaunchKitFails.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLaunchKitFails.setPreferredSize(BUTTON_DIMENSION);

		partsMissingPanel = new JPanel();
		controlPanel.add(partsMissingPanel);
		partsMissingPanel.setLayout(new BoxLayout(partsMissingPanel, BoxLayout.Y_AXIS));

		partsMissingLabel = new JLabel("Missing Parts in Nest - Non normative");
		partsMissingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		partsMissingPanel.add(partsMissingLabel);
		partsMissingLabel.setFont(new Font("Century", Font.PLAIN, 17));

		partsMissingInnerPanel = new JPanel();
		partsMissingPanel.add(partsMissingInnerPanel);

		partsMissingComboBox = new JComboBox(PARTS_MISSING_OPTIONS);
		partsMissingInnerPanel.add(partsMissingComboBox);

		btnLaunchMissingParts = new JButton("Launch Missing Parts Scenario");
		partsMissingInnerPanel.add(btnLaunchMissingParts);
		btnLaunchMissingParts.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLaunchMissingParts.setPreferredSize(BUTTON_DIMENSION);

		noGoodPartPanel = new JPanel();
		controlPanel.add(noGoodPartPanel);
		noGoodPartPanel.setLayout(new BoxLayout(noGoodPartPanel, BoxLayout.Y_AXIS));

		noGoodPartsLabel = new JLabel("No Good Parts in Nest - Non normative");
		noGoodPartsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		noGoodPartPanel.add(noGoodPartsLabel);
		noGoodPartsLabel.setFont(new Font("Century", Font.PLAIN, 17));

		noGoodPartsInnerPanel = new JPanel();
		noGoodPartPanel.add(noGoodPartsInnerPanel);

		noGoodPartsComboBox = new JComboBox(NO_GOOD_PART_OPTIONS);
		noGoodPartsInnerPanel.add(noGoodPartsComboBox);

		btnLaunchNoGoodParts = new JButton("Launch No Good Parts Scenario");
		noGoodPartsInnerPanel.add(btnLaunchNoGoodParts);
		btnLaunchNoGoodParts.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLaunchNoGoodParts.setPreferredSize(BUTTON_DIMENSION);

		statusPanel = new JPanel();
		add(statusPanel);
		STATUS_LABEL.setFont(new Font("Century", Font.PLAIN, 18));

		statusPanel.add(STATUS_LABEL);

		currentStatusLabel = new JLabel("none");
		currentStatusLabel.setFont(new Font("Impact", Font.ITALIC, 15));
		statusPanel.add(currentStatusLabel);
	}

	//	/**
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//
	//			public void run() {
	//				try {
	//					NonNormCtrlPanel here = new NonNormCtrlPanel();
	//					here.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}
}
