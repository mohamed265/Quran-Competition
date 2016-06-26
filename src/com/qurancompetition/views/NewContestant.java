package com.qurancompetition.views;

import java.awt.Color;
import java.awt.Font;
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

public class NewContestant extends JFrame {

	public NewContestant() {
		super("تسجيل متسابق جديد");

		this.setResizable(false);
		setBounds(250, 115, 800, 550);
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

		JLabel message = new JLabel("تسجيل متسابق فى النظام");
		message.setBounds(250, 10, 900, 100);
		message.setFont(new Font("Time New Roman", Font.TYPE1_FONT, 37));
		message.setForeground(Color.BLACK);
		panel.add(message);

		JLabel deviceNameLabel = new JLabel("الاسم");
		deviceNameLabel.setBounds(430, 150, 120, 25);
		// deviceNameLabel.setFont(new Font("Time New Roman", Font.TYPE1_FONT,
		// 16));
		panel.add(deviceNameLabel);

		final JTextField deviceNameInput = new JTextField(20);
		deviceNameInput.setBounds(250, 150, 160, 25);
		panel.add(deviceNameInput);

		JLabel passwordLabel = new JLabel("الرقم القومى");
		passwordLabel.setBounds(430, 190, 120, 25);
		// passwordLabel.setFont(new Font("Time New Roman", Font.TYPE1_FONT,
		// 16));
		panel.add(passwordLabel);

		final JTextField passwordText = new JTextField(20);
		passwordText.setBounds(250, 190, 160, 25);
		panel.add(passwordText);

		JLabel RepasswordLabel = new JLabel("تاريخ الميلاد");
		RepasswordLabel.setBounds(430, 230, 120, 25);
		// RepasswordLabel.setFont(new Font("Time New Roman", Font.TYPE1_FONT,
		// 16));
		panel.add(RepasswordLabel);

		final JTextField RepasswordText = new JTextField(20);
		RepasswordText.setBounds(250, 230, 160, 25);
		panel.add(RepasswordText);

		JButton save = new JButton("حفظ البيانات");
		save.setBounds(430 + 60, 375, 160, 25);
		panel.add(save);

		//// JButton register = new JButton("تسجيل فى مسابقة");
		// register.setBounds(250 + 60, 375, 160, 25);
		// panel.add(register);

		JButton close = new JButton("إغلاق");
		close.setBounds(70 + 60, 375, 160, 25);
		panel.add(close);

		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (deviceNameInput.getText() == "" || RepasswordText.getText() == "") {
					JOptionPane.showMessageDialog(null, "الادخالات فارغة", null, JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {

					DataBase.saveCompetitor(deviceNameInput.getText(), RepasswordText.getText(),
							passwordText.getText());
					JOptionPane.showMessageDialog(null, "تم حفظ بيانات المتسابق بنجاح", null,
							JOptionPane.INFORMATION_MESSAGE);
					// dispose();
					deviceNameInput.setText("");
					RepasswordText.setText("");
					passwordText.setText("");
				} catch (Exception w) {
					JOptionPane.showMessageDialog(null, "خطأ فى الادخالات", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// register.addActionListener(new ActionListener() {
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
