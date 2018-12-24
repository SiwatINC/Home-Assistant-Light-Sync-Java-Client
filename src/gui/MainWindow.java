package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.net.ssl.SSLEngineResult.Status;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JSlider;
import java.awt.Label;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import api.*;

public class MainWindow {
	public static int running = 0;
	private JFrame frmHalightsync;

	/**
	 * Launch the application.
	 */
	public static MainWindow window = new MainWindow();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window.frmHalightsync.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHalightsync = new JFrame();
		frmHalightsync.setResizable(false);
		frmHalightsync.setTitle("HALightSync");
		frmHalightsync.setBounds(100, 100, 580, 292);
		frmHalightsync.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHalightsync.getContentPane().setLayout(null);

		JPanel TopPanel = new JPanel();
		TopPanel.setBackground(new Color(153, 255, 255));
		TopPanel.setBounds(0, 0, 573, 216);
		frmHalightsync.getContentPane().add(TopPanel);
		TopPanel.setLayout(null);

		JButton RelaunchWizardButton = new JButton("Relaunch Wizard");
		RelaunchWizardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		RelaunchWizardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] args = null;
				api.dataoperation.store("false", "wizard");
				gui.Wizard.main(args);
				window.frmHalightsync.dispose();
			}
		});

		JTabbedPane ModeSelector = new JTabbedPane(JTabbedPane.TOP);
		ModeSelector.setBounds(0, 38, 573, 178);
		TopPanel.add(ModeSelector);

		JLayeredPane PictureMode = new JLayeredPane();
		PictureMode.setBackground(new Color(255, 255, 102));
		ModeSelector.addTab("Picture Mode", null, PictureMode, null);
		ModeSelector.setBackgroundAt(0, new Color(255, 255, 102));
		ModeSelector.setForegroundAt(0, new Color(0, 0, 0));

		JPanel UIPicturePane = new JPanel();
		UIPicturePane.setBackground(new Color(255, 255, 102));
		UIPicturePane.setBounds(0, 0, 572, 150);
		PictureMode.add(UIPicturePane);
		UIPicturePane.setLayout(null);
		api.mqtt.connect(api.dataoperation.retrieve("brokerip"), api.dataoperation.retrieve("clientid"), api.dataoperation.retrieve("username"), api.dataoperation.retrieve("password").toCharArray());
		JSlider QualitySlider = new JSlider();
		QualitySlider.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				api.dataoperation.store(Integer.toString(QualitySlider.getValue()), "quality");
			}
		});
		QualitySlider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				api.dataoperation.store(Integer.toString(QualitySlider.getValue()), "quality");
			}
		});
		QualitySlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
			}
		});
		QualitySlider.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
			}
		});
		
				JLabel OperationStateLabel = new JLabel("Idle");
				OperationStateLabel.setFont(new Font("Dialog", Font.BOLD, 20));
				OperationStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
				OperationStateLabel.setBounds(12, 3, 102, 38);
				UIPicturePane.add(OperationStateLabel);

		JLabel RValueLabel = new JLabel("R:");
		RValueLabel.setForeground(Color.RED);
		RValueLabel.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		RValueLabel.setBounds(365, 77, 61, 27);
		UIPicturePane.add(RValueLabel);
		
				JSlider slider = new JSlider();
				slider.setBackground(new Color(255, 255, 102));
				slider.setForeground(Color.RED);
				slider.setMajorTickSpacing(150);
				slider.setMinorTickSpacing(10);
				slider.setPaintLabels(true);
				slider.setPaintTicks(true);
				slider.setMinimum(100);
				slider.setMaximum(1000);
				slider.setValue(Integer.parseInt(api.dataoperation.retrieve("pushingrate")));
				slider.setSnapToTicks(true);
				slider.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseDragged(MouseEvent arg0) {
						api.dataoperation.store(Integer.toString(slider.getValue()), "pushingrate");
						OperationStateLabel.setText("Forced Exit (Slider Adjusted)");
					}
				});
				slider.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						api.dataoperation.store(Integer.toString(slider.getValue()), "pushingrate");
						OperationStateLabel.setText("Forced Exit (Slider Adjusted)");
					}
				});
				slider.setBounds(349, 6, 200, 43);
				UIPicturePane.add(slider);

		JLabel GValueLabel = new JLabel("G:");
		GValueLabel.setForeground(Color.GREEN);
		GValueLabel.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		GValueLabel.setBounds(426, 77, 68, 27);
		UIPicturePane.add(GValueLabel);

		JLabel BValueLabel = new JLabel("B:");
		BValueLabel.setForeground(Color.BLUE);
		BValueLabel.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		BValueLabel.setBounds(492, 76, 57, 28);
		UIPicturePane.add(BValueLabel);
		QualitySlider.setPaintLabels(true);
		QualitySlider.setSnapToTicks(true);
		QualitySlider.setPaintTicks(true);
		QualitySlider.setMajorTickSpacing(1);
		QualitySlider.setMinorTickSpacing(1);
		QualitySlider.setForeground(new Color(255, 51, 102));
		QualitySlider.setBackground(new Color(255, 255, 102));
		QualitySlider.setMinimum(1);
		QualitySlider.setMaximum(9);
		QualitySlider.setValue(Integer.parseInt(api.dataoperation.retrieve("quality")));
		QualitySlider.setBounds(131, 100, 429, 50);
		UIPicturePane.add(QualitySlider);

		JLabel QualityLabel = new JLabel("Quality");
		QualityLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		QualityLabel.setBounds(12, 100, 108, 33);
		UIPicturePane.add(QualityLabel);

		JLabel BrokerLabel = new JLabel("Broker : " + api.dataoperation.retrieve("brokerip"));
		BrokerLabel.setBounds(131, 6, 244, 16);
		UIPicturePane.add(BrokerLabel);

		JLabel TopicLabel = new JLabel("Topic : " + api.dataoperation.retrieve("topic"));
		TopicLabel.setBounds(131, 25, 244, 16);
		UIPicturePane.add(TopicLabel);

		JLabel ResolutionLabel = new JLabel("Resolution : " + api.analyzescreen.getscreensize().width + "x"
				+ api.analyzescreen.getscreensize().height);
		ResolutionLabel.setBounds(131, 43, 156, 16);
		UIPicturePane.add(ResolutionLabel);
		JButton StartButton = new JButton("Start");
		String topic = api.dataoperation.retrieve("topic");
		class analyzerfunction extends Thread {
			public void run() {
				while (running == 1) {
					object.color colordata = new object.color();
					colordata = api.analyzescreen.decodecolor(
							api.analyzescreen.getcolor(Integer.parseInt(api.dataoperation.retrieve("quality")),
									api.analyzescreen.getscreensize().width, api.analyzescreen.getscreensize().height));
					RValueLabel.setText("R:"+Math.round(colordata.red));
					GValueLabel.setText("G:"+Math.round(colordata.green));
					BValueLabel.setText("B:"+Math.round(colordata.blue));
					api.mqtt.publish("R"+Math.round(colordata.red)+"G"+Math.round(colordata.green)+"B"+Math.round(colordata.blue), topic, 2);
					yield();
					try {
						Thread.sleep(Integer.parseInt(api.dataoperation.retrieve("pushingrate")));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
		analyzerfunction analyzercode = new analyzerfunction();
		Thread analyzer = new Thread(analyzercode);
		ExecutorService analyzerservice = Executors.newFixedThreadPool(1);
		analyzer.setPriority(10);
		analyzerservice.execute(analyzer);
		StartButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				running = 1;
				QualitySlider.setEnabled(false);
				slider.setEnabled(false);
				OperationStateLabel.setText("Running");
				analyzerservice.execute(analyzer);
			}
		});
		StartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		StartButton.setBackground(new Color(51, 255, 51));
		StartButton.setBounds(10, 38, 108, 26);
		UIPicturePane.add(StartButton);
		
				JButton StopButton = new JButton("Stop");
				StopButton.addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent e) {
						OperationStateLabel.setText("Idle");
						QualitySlider.setEnabled(true);
						slider.setEnabled(true);
						running = 0;
					}
				});
				StopButton.setBackground(new Color(255, 51, 0));
				StopButton.setBounds(10, 64, 108, 26);
				UIPicturePane.add(StopButton);


		JButton TestButton = new JButton("Test MQTT Connection");
		TestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (api.mqtt.testserver(api.dataoperation.retrieve("brokerip"), api.dataoperation.retrieve("clientid"),
						api.dataoperation.retrieve("username"), api.dataoperation.retrieve("password").toCharArray())) {
					JOptionPane.showMessageDialog(null, "Connection Established!", "HALightSync ",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Connection Failed!", "HALightSync", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		TestButton.setBackground(new Color(255, 204, 153));
		TestButton.setBounds(130, 65, 216, 26);
		UIPicturePane.add(TestButton);

		JLabel PushingLabel = new JLabel("Pushing");
		PushingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PushingLabel.setBounds(416, 48, 68, 25);
		UIPicturePane.add(PushingLabel);

		JLabel RateLabel = new JLabel("Rate");
		RateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		RateLabel.setBounds(416, 66, 68, 16);
		UIPicturePane.add(RateLabel);

		JLayeredPane SoundMode = new JLayeredPane();
		SoundMode.setForeground(new Color(255, 153, 153));
		SoundMode.setBackground(new Color(255, 153, 153));
		ModeSelector.addTab("Sound Mode", null, SoundMode, null);
		ModeSelector.setBackgroundAt(1, new Color(255, 153, 153));

		JPanel UISoundPane = new JPanel();
		UISoundPane.setBackground(new Color(255, 153, 153));
		UISoundPane.setBounds(0, 0, 568, 150);
		SoundMode.add(UISoundPane);
		UISoundPane.setLayout(null);

		JLabel ComingSoonText = new JLabel("Coming Soon");
		ComingSoonText.setFont(new Font("Tahoma", Font.BOLD, 56));
		ComingSoonText.setForeground(new Color(0, 0, 0));
		ComingSoonText.setHorizontalAlignment(SwingConstants.CENTER);
		ComingSoonText.setBounds(0, 5, 568, 145);
		UISoundPane.add(ComingSoonText);
		RelaunchWizardButton.setBounds(406, 10, 156, 23);
		TopPanel.add(RelaunchWizardButton);
		RelaunchWizardButton.setBackground(new Color(204, 255, 153));

		JLabel BuildLabel = new JLabel("Public Beta 1 (Build 0369)");
		BuildLabel.setLabelFor(frmHalightsync);
		BuildLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		BuildLabel.setBounds(10, 7, 248, 23);
		TopPanel.add(BuildLabel);

		JPanel Footer = new JPanel();
		Footer.setBackground(new Color(255, 153, 255));
		Footer.setBounds(0, 213, 573, 48);
		frmHalightsync.getContentPane().add(Footer);
		Footer.setLayout(null);

		JLabel CreditLabel = new JLabel("Home Assistant Light Sync by Siwat Sirichai");
		CreditLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		CreditLabel.setBounds(0, 0, 573, 48);
		Footer.add(CreditLabel);
		CreditLabel.setHorizontalAlignment(SwingConstants.CENTER);

	}
}
