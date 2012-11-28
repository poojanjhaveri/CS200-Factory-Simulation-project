package factory.factory200.factoryProductionManager;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class NonNormCtrlPanel extends JPanel {
	// 3 broad non-normative cases: Kit Fails, Parts Missing, No Good Parts
	private JPanel kitFailsPanel, kitFailsInnerPanel;
	private JPanel partsMissingPanel, partsMissingInnerPanel;
	private JPanel noGoodPartPanel, noGoodPartsInnerPanel;

	private JLabel kitFailsLabel, partsMissingLabel, noGoodPartsLabel;
	private JComboBox kitFailsComboBox, partsMissingComboBox, noGoodPartsComboBox;
	private JButton btnLaunchKitFails, btnLaunchMissingParts, btnLaunchNoGoodParts;
	
	private static String[] KIT_FAILS_OPTIONS = { "Parts missing from the kit (part robot drops a part)"};
	private static String[] PARTS_MISSING_OPTIONS = { "Need to wait longer", "Lane jammed", "Feeder algorithm too slow"};
	private static String[] NO_GOOD_PART_OPTIONS = { "All parts are bad", "Parts piled up!", "Robot in the way", "Parts not stable"};
	
	public NonNormCtrlPanel() {
		preparePanel();
	}
	
	private void preparePanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		kitFailsPanel = new JPanel();
		add(kitFailsPanel);
		kitFailsPanel.setLayout(new BoxLayout(kitFailsPanel, BoxLayout.Y_AXIS));

		kitFailsInnerPanel = new JPanel();
		kitFailsPanel.add(kitFailsInnerPanel);
		kitFailsInnerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		kitFailsLabel = new JLabel("Bad Kit - Non normative");
		kitFailsInnerPanel.add(kitFailsLabel);
		kitFailsLabel.setFont(new Font("Century", Font.PLAIN, 17));

		kitFailsComboBox = new JComboBox(KIT_FAILS_OPTIONS);
		kitFailsInnerPanel.add(kitFailsComboBox);

		btnLaunchKitFails = new JButton("Launch Bad Kit Scenario");
		kitFailsPanel.add(btnLaunchKitFails);

		partsMissingPanel = new JPanel();
		add(partsMissingPanel);
		partsMissingPanel.setLayout(new BoxLayout(partsMissingPanel, BoxLayout.Y_AXIS));

		partsMissingInnerPanel = new JPanel();
		partsMissingPanel.add(partsMissingInnerPanel);
		partsMissingInnerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		partsMissingLabel = new JLabel("Missing Parts in Nest - Non normative");
		partsMissingInnerPanel.add(partsMissingLabel);
		partsMissingLabel.setFont(new Font("Century", Font.PLAIN, 17));

		partsMissingComboBox = new JComboBox(PARTS_MISSING_OPTIONS);
		partsMissingInnerPanel.add(partsMissingComboBox);

		btnLaunchMissingParts = new JButton("Launch Missing Parts Scenario");
		partsMissingPanel.add(btnLaunchMissingParts);

		noGoodPartPanel = new JPanel();
		add(noGoodPartPanel);
		noGoodPartPanel.setLayout(new BoxLayout(noGoodPartPanel, BoxLayout.Y_AXIS));

		noGoodPartsInnerPanel = new JPanel();
		noGoodPartPanel.add(noGoodPartsInnerPanel);

		noGoodPartsLabel = new JLabel("No Good Parts in Nest - Non normative");
		noGoodPartsInnerPanel.add(noGoodPartsLabel);
		noGoodPartsLabel.setFont(new Font("Century", Font.PLAIN, 17));

		noGoodPartsComboBox = new JComboBox(NO_GOOD_PART_OPTIONS);
		noGoodPartsInnerPanel.add(noGoodPartsComboBox);

		btnLaunchNoGoodParts = new JButton("Launch No Good Parts Scenario");
		noGoodPartPanel.add(btnLaunchNoGoodParts);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					NonNormCtrlPanel here = new NonNormCtrlPanel();
					here.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
