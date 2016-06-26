package com.qurancompetition.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainWindow() {
		super("مسابقة القران الكريم");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setResizable(false);
		setBounds(250, 115, 800, 550);
		JPanel panel = new JPanel();

		JMenuBar menuBar = new JMenuBar();
		menuBar.setAlignmentX(RIGHT_ALIGNMENT);
		setJMenuBar(menuBar);
		// Define and add two drop down menu to the menubar
		JMenu prog = new JMenu("برنامج");
		JMenu student = new JMenu("متسابقين");
		JMenu s = new JMenu("مسابقة");
		JMenu helpMenu = new JMenu("مساعدة");

		menuBar.add(prog);
		menuBar.add(student);
		menuBar.add(s);
		menuBar.add(helpMenu);

		// Create and add simple menu item to one of the drop down menu
		JMenuItem exitAction = new JMenuItem("خروج");
		JMenuItem newConsitant = new JMenuItem("تسجيل متسابق جديد");
		JMenuItem enterCompetion = new JMenuItem("إدخال متسابق فى المسابقة");
		JMenuItem ente = new JMenuItem("إدخال نتائج المسابقة");
		JMenuItem ss = new JMenuItem("مسابقة جديدة");
		JMenuItem sss = new JMenuItem("نتائج المسابقة");
		JMenuItem h = new JMenuItem("مساعدة");

		prog.addSeparator();
		prog.add(exitAction);
		student.add(newConsitant);
		student.add(enterCompetion);
		student.add(ente);
		s.add(ss);
		s.add(sss);
		helpMenu.add(h);

		h.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = "تم بحمد الله إنشاء برنامج مسابقات القرأن الكريم";
				message += "\nييتم تخزين بيانات المتسابقين و بينات المسابقة فى النظام";
				message += "\nيعتبر النظام المتسابق ناجح اذا كانت نتيجتة تزيد على الحد الادنى للمسابقة ويضمن نجاحة تسجيلة فى المسابقات التالية فى مستوى اعلى";
				message += "\nتم التصميم بواسطة  م/محمد فؤاد مرسى";
				message += "\nللاستفسار 01007523776";
				message += "\nانشرة ولك الاجر ولا تنسونا من صالح دعائكم";
				JOptionPane.showMessageDialog(null, message, "مساعدة", JOptionPane.INFORMATION_MESSAGE, null);

			}
		});

		sss.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CompetitionResults();
			}
		});

		ente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EditResults();
			}
		});

		exitAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		newConsitant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new NewContestant();

			}
		});

		enterCompetion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegisterInCompetition();

			}
		});

		ss.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Competition();

			}
		});

		placeComponents(panel);
		add(panel);
		setVisible(true);
	}

	private void placeComponents(JPanel panel) {
		panel.setLayout(null);

		BufferedImage myPicture, iconProfile;
		try {
			/*
			 * iconProfile = ImageIO.read(new File("userpic.jpg")); JLabel
			 * iconProfileLab = new JLabel(new ImageIcon(iconProfile));
			 * iconProfileLab.setBounds(100, 80, 150, 150); Border border =
			 * BorderFactory.createLineBorder(Color.BLACK, 5);
			 * iconProfileLab.setBorder(border);
			 * iconProfileLab.setOpaque(false); panel.add(iconProfileLab);
			 */

			myPicture = ImageIO.read(new File(("bkimage.png")));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setBounds(0, 0, 800, 700);
			panel.add(picLabel);
		} catch (IOException e2) {
			e2.printStackTrace();
		} /**/
		/*
		 * JLabel message = new JLabel(
		 * "اهلا بك فى برنامج مسابقة حفظ القران الكريم"); message.setBounds(340,
		 * 150, 900, 100); message.setFont(new Font("Time New Roman",
		 * Font.ITALIC, 37)); message.setForeground(Color.BLACK);
		 * panel.add(message);
		 * 
		 * /*JLabel deviceNameLabel = new JLabel("Device Name");
		 * deviceNameLabel.setBounds(370, 250, 120, 25);
		 * deviceNameLabel.setFont(new Font("Time New Roman", Font.ITALIC, 18));
		 * panel.add(deviceNameLabel);
		 * 
		 * final JTextField deviceNameInput = new JTextField(20);
		 * deviceNameInput.setBounds(500, 250, 160, 25);
		 * panel.add(deviceNameInput);
		 * 
		 * JLabel passwordLabel = new JLabel("Password");
		 * passwordLabel.setBounds(370, 290, 120, 25); passwordLabel.setFont(new
		 * Font("Time New Roman", Font.ITALIC, 18)); panel.add(passwordLabel);
		 * 
		 * final JPasswordField passwordText = new JPasswordField(20);
		 * passwordText.setBounds(500, 290, 160, 25); panel.add(passwordText);
		 * 
		 * JLabel RepasswordLabel = new JLabel("Re Password");
		 * RepasswordLabel.setBounds(370, 330, 120, 25);
		 * RepasswordLabel.setFont(new Font("Time New Roman", Font.ITALIC, 18));
		 * panel.add(RepasswordLabel);
		 * 
		 * final JPasswordField RepasswordText = new JPasswordField(20);
		 * RepasswordText.setBounds(500, 330, 160, 25);
		 * panel.add(RepasswordText);
		 * 
		 * JButton addDevice = new JButton("Register Device");
		 * addDevice.setBounds(500, 375, 160, 25); panel.add(addDevice);
		 * 
		 * addDevice.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { } });
		 */
	}

}
