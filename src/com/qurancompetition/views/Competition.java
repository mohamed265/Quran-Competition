package com.qurancompetition.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.qurancompetition.db.DataBase;

public class Competition extends JFrame {

	public Competition() throws HeadlessException {
		super("مسابقة جديدة");

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setResizable(false);
		setBounds(250, 115, 400, 270);
		JPanel panel = new JPanel();

		JMenuBar menuBar = new JMenuBar();
		menuBar.setAlignmentX(RIGHT_ALIGNMENT);
		setJMenuBar(menuBar);
		JMenu prog = new JMenu("برنامج");

		menuBar.add(prog);

		JMenuItem exitAction = new JMenuItem("إغلاق");

		prog.add(exitAction);

		exitAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		placeComponents(panel);
		add(panel);
		setVisible(true);
	}

	private void placeComponents(JPanel panel) {
		panel.setLayout(null);

		/*
		 * BufferedImage myPicture; try {
		 * 
		 * myPicture = ImageIO.read(new File(("bkimage.png"))); JLabel picLabel
		 * = new JLabel(new ImageIcon(myPicture)); picLabel.setBounds(0, 0, 800,
		 * 700); panel.add(picLabel); } catch (IOException e2) {
		 * e2.printStackTrace(); } /
		 **/

		JLabel message = new JLabel("مسابقة جديدة");
		message.setBounds(130, -15, 450, 100);
		message.setFont(new Font("Time New Roman", Font.TYPE1_FONT, 37));
		message.setForeground(Color.BLACK);
		panel.add(message);

		JLabel deviceNameLabel = new JLabel("الاسم");
		deviceNameLabel.setBounds(300, 90, 120, 25);
		// deviceNameLabel.setFont(new Font("Time New Roman", Font.TYPE1_FONT,
		// 16));
		panel.add(deviceNameLabel);

		final JTextField deviceNameInput = new JTextField(20);
		deviceNameInput.setBounds(50, 90, 160, 25);
		panel.add(deviceNameInput);

		JLabel passwordLabel = new JLabel("الحد الادنى");
		passwordLabel.setBounds(300, 130, 120, 25);
		// passwordLabel.setFont(new Font("Time New Roman", Font.TYPE1_FONT,
		// 16));
		panel.add(passwordLabel);

		final JTextField passwordText = new JTextField(20);
		passwordText.setBounds(50, 130, 160, 25);
		panel.add(passwordText);
		/*
		 * JLabel RepasswordLabel = new JLabel("السكن");
		 * RepasswordLabel.setBounds(430, 230, 120, 25);
		 * //RepasswordLabel.setFont(new Font("Time New Roman", Font.TYPE1_FONT,
		 * 16)); panel.add(RepasswordLabel);
		 * 
		 * final JTextField RepasswordText = new JTextField(20);
		 * RepasswordText.setBounds(250, 230, 160, 25);
		 * panel.add(RepasswordText);
		 */

		JButton save = new JButton("حفظ البيانات");
		save.setBounds(250, 175, 100, 25);
		panel.add(save);

		// JButton addDevice = new JButton("تسجيل فى مسابقة");
		// addDevice.setBounds(250 + 60, 375, 160, 25);
		// panel.add(addDevice);

		JButton close = new JButton("إغلاق");
		close.setBounds(50, 175, 100, 25);
		panel.add(close);

		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DataBase.saveCompetition(deviceNameInput.getText(), Integer.parseInt(passwordText.getText()));
					JOptionPane.showMessageDialog(null, "تم حفظ بيانات المسابقة بنجاح", null, JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "خطأ فى الادخالات", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// addDevice.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// }
		// });

		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}

}
