package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.Panel;
import javax.swing.JSlider;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Wizard {

	private JFrame frmHaLightSync;
	private JTextField brokerip;
	private JTextField qosfield;
	private JPasswordField passwordfield;
	private JTextField topicfield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if (api.dataoperation.retrieve("wizard").equals("false")) {
						Wizard window = new Wizard();
						window.frmHaLightSync.setVisible(true);
					} else if (api.dataoperation.retrieve("wizard").equals("true")) {
						gui.MainWindow.main(args);
					} else if (!api.dataoperation.retrieve("wizard").equals("true")
							&& !api.dataoperation.retrieve("wizard").equals("false")) {
						JOptionPane.showMessageDialog(null,
								"This installation of HALightSync seems to be corrupted (No halsconf directory in the folder that the jar file is in, try creating a folder named halsconf in the same folder as you jar file) or the wizard is not yet completed, don't panic, if this keep happening, please delete all the file with no extension from the program folder",
								"HALightSync is Corrupted or Wizard is not completed", JOptionPane.ERROR_MESSAGE);
						Wizard window = new Wizard();
						window.frmHaLightSync.setVisible(true);
					} else {
						Wizard window = new Wizard();
						window.frmHaLightSync.setVisible(true);
					}
				} catch (Exception e) {
					Wizard window = new Wizard();
					window.frmHaLightSync.setVisible(true);
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
		frmHaLightSync.setResizable(false);
		frmHaLightSync.setTitle("HA LightSync by Siwat Sirichai");
		frmHaLightSync.setBounds(100, 100, 442, 371);
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

		JPanel FormField = new JPanel();
		FormField.setBackground(new Color(255, 182, 193));
		FormField.setBounds(0, 71, 434, 202);
		frmHaLightSync.getContentPane().add(FormField);
		FormField.setLayout(null);

		brokerip = new JTextField();
		brokerip.setText("tcp://yourserver:1883");
		brokerip.setToolTipText("tcp://yourserver:1883");
		brokerip.setBounds(209, 35, 215, 20);
		FormField.add(brokerip);
		brokerip.setColumns(10);

		JLabel brokerlabel = new JLabel("Home Assistant MQTT Server");
		brokerlabel.setBounds(29, 35, 198, 20);
		FormField.add(brokerlabel);

		JLabel clientnamelabel = new JLabel("Client Name");
		clientnamelabel.setBounds(29, 66, 73, 28);
		FormField.add(clientnamelabel);

		TextField usernamefield = new TextField();
		usernamefield.setBounds(158, 97, 266, 22);
		FormField.add(usernamefield);

		TextField clientnamefield = new TextField();
		clientnamefield.setText("HALightSync");
		clientnamefield.setBounds(158, 64, 153, 22);
		FormField.add(clientnamefield);

		JLabel usernamelabel = new JLabel("MQTT Username");
		usernamelabel.setBounds(29, 97, 146, 22);
		FormField.add(usernamelabel);

		JLabel mqttpasswordlabel = new JLabel("MQTT Password");
		mqttpasswordlabel.setBounds(29, 130, 202, 22);
		FormField.add(mqttpasswordlabel);

		qosfield = new JTextField();
		qosfield.setText("2");
		qosfield.setBounds(362, 66, 62, 20);
		FormField.add(qosfield);
		qosfield.setColumns(10);

		passwordfield = new JPasswordField();
		passwordfield.setBounds(158, 131, 266, 20);
		FormField.add(passwordfield);

		JLabel qoslabel = new JLabel("QoS");
		qoslabel.setBounds(325, 69, 46, 14);
		FormField.add(qoslabel);

		topicfield = new JTextField();
		topicfield.setText("/halsclient/livingroom");
		topicfield.setBounds(158, 163, 266, 20);
		FormField.add(topicfield);
		topicfield.setColumns(10);

		JLabel mqtttopiclabel = new JLabel("MQTT Topic");
		mqtttopiclabel.setBounds(29, 166, 86, 14);
		FormField.add(mqtttopiclabel);

		Panel Footer = new Panel();
		Footer.setBackground(new Color(250, 250, 210));
		Footer.setBounds(0, 272, 434, 68);
		frmHaLightSync.getContentPane().add(Footer);
		Footer.setLayout(null);

		JButton testbutton = new JButton("TEST");
		testbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (api.mqtt.testserver(brokerip.getText(), clientnamefield.getText(), usernamefield.getText(),
						passwordfield.getPassword())) {
					JOptionPane.showMessageDialog(null, "Connection Established!", "HALightSync ",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Connection Failed!", "HALightSync", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		testbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		testbutton.setBackground(new Color(216, 191, 216));
		testbutton.setFont(new Font("Tahoma", Font.BOLD, 17));
		testbutton.setBounds(10, 11, 197, 44);
		Footer.add(testbutton);

		JButton donebutton = new JButton("DONE");
		donebutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (api.mqtt.testserver(brokerip.getText(), clientnamefield.getText(), usernamefield.getText(),
						passwordfield.getPassword())) {
					api.dataoperation.store(brokerip.getText(), "brokerip");
					api.dataoperation.store(usernamefield.getText(), "username");
					api.dataoperation.store(String.valueOf(passwordfield.getPassword()), "password");
					api.dataoperation.store(topicfield.getText(), "topic");
					api.dataoperation.store("true", "wizard");
					api.dataoperation.store(clientnamefield.getText(), "clientid");
					api.dataoperation.store("1000", "pushingrate");
					api.dataoperation.store("3", "quality");
					JOptionPane.showMessageDialog(null,
							"Setup Wizard Completed, This program will now exit, the default class had been changed from wizard to main window! (just start it again to use it)",
							"HALightSync ", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				} else {
					JOptionPane.showMessageDialog(null, "Connection Failed!", "HALightSync", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		donebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(arg0);
			}
		});
		donebutton.setBackground(new Color(152, 251, 152));
		donebutton.setFont(new Font("Tahoma", Font.BOLD, 17));
		donebutton.setBounds(227, 11, 197, 44);
		Footer.add(donebutton);
	}
}
