package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.Panel;
import javax.swing.JSlider;

public class Wizard {

	private JFrame frmHaLightSync;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Wizard window = new Wizard();
					window.frmHaLightSync.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Wizard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHaLightSync = new JFrame();
		frmHaLightSync.setTitle("HA LightSync");
		frmHaLightSync.setBounds(100, 100, 451, 484);
		frmHaLightSync.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHaLightSync.getContentPane().setLayout(null);
		
		JPanel Header = new JPanel();
		Header.setBackground(new Color(224, 255, 255));
		Header.setBounds(0, 0, 434, 72);
		frmHaLightSync.getContentPane().add(Header);
		Header.setLayout(null);
		
		JLabel Title = new JLabel("Home Assistant Light Sync");
		Title.setFont(new Font("Tahoma", Font.BOLD, 30));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(0, 0, 434, 72);
		Header.add(Title);
		
		JLabel lblSetupWizard = new JLabel("Setup Wizard");
		lblSetupWizard.setForeground(new Color(0, 128, 0));
		lblSetupWizard.setBackground(new Color(240, 240, 240));
		lblSetupWizard.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSetupWizard.setBounds(335, 47, 99, 25);
		Header.add(lblSetupWizard);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 182, 193));
		panel.setBounds(0, 71, 434, 309);
		frmHaLightSync.getContentPane().add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(237, 35, 187, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblHomeAssistantMqtt = new JLabel("Home Assistant MQTT Server");
		lblHomeAssistantMqtt.setBounds(29, 35, 198, 20);
		panel.add(lblHomeAssistantMqtt);
		
		textField_1 = new JTextField();
		textField_1.setBounds(237, 81, 187, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblHomeAssistantMqtt_1 = new JLabel("Home Assistant MQTT Port");
		lblHomeAssistantMqtt_1.setBounds(29, 81, 187, 20);
		panel.add(lblHomeAssistantMqtt_1);
		
		JLabel lblClientName = new JLabel("Client Name");
		lblClientName.setBounds(29, 118, 187, 28);
		panel.add(lblClientName);
		
		TextField textField_2 = new TextField();
		textField_2.setBounds(237, 171, 187, 22);
		panel.add(textField_2);
		
		TextField textField_3 = new TextField();
		textField_3.setBounds(237, 118, 187, 22);
		panel.add(textField_3);
		
		JLabel lblMqttUsername = new JLabel("MQTT Username");
		lblMqttUsername.setBounds(29, 171, 187, 22);
		panel.add(lblMqttUsername);
		
		JLabel lblMqttPassword = new JLabel("MQTT Password");
		lblMqttPassword.setBounds(29, 226, 202, 22);
		panel.add(lblMqttPassword);
		
		TextField textField_4 = new TextField();
		textField_4.setBounds(237, 226, 187, 22);
		panel.add(textField_4);
		
		Checkbox checkbox = new Checkbox("Use TLS");
		checkbox.setBounds(29, 265, 71, 22);
		panel.add(checkbox);
		
		JLabel lblUpdate = new JLabel("Update Frequency (ms)");
		lblUpdate.setBounds(112, 265, 146, 22);
		panel.add(lblUpdate);
		
		JSlider slider = new JSlider();
		slider.setBounds(258, 265, 166, 22);
		panel.add(slider);
		
		Panel panel_1 = new Panel();
		panel_1.setBounds(0, 379, 434, 66);
		frmHaLightSync.getContentPane().add(panel_1);
		panel_1.setLayout(null);
	}
}
