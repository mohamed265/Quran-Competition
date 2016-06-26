package com.qurancompetition.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.qurancompetition.db.DataBase;

public class EditResults extends JFrame {

	private String[] competitionsName;
	private JComboBox comboBox;
	private ArrayList<String[]> registered;
	private ArrayList<String[]> unregistered;
	private ArrayList<String[]> competitionList;
	private java.awt.List list1;
	private java.awt.List regisd;
	private int competitionId = 0;

	public EditResults() throws HeadlessException {
		super("إدخال نتائج المسابقة");

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

	private void placeComponents(final JPanel panel) {
		panel.setLayout(null);

		JLabel message = new JLabel("إدخال نتائج المسابقة");
		message.setBounds(220, -10, 900, 100);
		message.setFont(new Font("Time New Roman", Font.TYPE1_FONT, 37));
		message.setForeground(Color.BLACK);
		panel.add(message);

		JLabel deviceNameLabel = new JLabel("المسابقات");
		deviceNameLabel.setBounds(500, 110, 120, 25);
		deviceNameLabel.setFont(new Font("Time New Roman", Font.TYPE1_FONT, 20));
		panel.add(deviceNameLabel);

		competitionList = DataBase.getAllCompetition();
		competitionsName = new String[competitionList.size() + 1];
		competitionsName[0] = "اختر مسابقة";
		for (int i = 0; i < competitionList.size(); i++)
			competitionsName[i + 1] = competitionList.get(i)[1];
		comboBox = new JComboBox(competitionsName);
		comboBox.setBounds(250, 110, 220, 25);
		panel.add(comboBox);

		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				competitionId = comboBox.getSelectedIndex();
				regisd.clear();
				list1.clear();
				registered = new ArrayList<>();
				unregistered = new ArrayList<>();
				if (competitionId != 0) {
					competitionId = Integer.parseInt(competitionList.get(competitionId - 1)[0]);
					// registered
					ArrayList<String[]> temp = DataBase.getAllCompetitorByCompetitionId(competitionId);
					System.out.println("temp " + temp.size());
					for (int i = 0; i < temp.size(); i++)
						if (!temp.get(i)[1].equals("0")) {
							regisd.add(DataBase.getCompetitorNameById(Integer.parseInt(temp.get(i)[3])));
							registered.add(temp.get(i));
						} else {
							unregistered.add(temp.get(i));
							list1.add(DataBase.getCompetitorNameById(Integer.parseInt(temp.get(i)[3])));
						}

					// unregistered =
					// DataBase.getAllCompetitorNotRegisteredInByCompetitionId(competitionId);
					// for (int i = 0; i < unregistered.size(); i++)
					// list1.add(unregistered.get(i)[1]);
				}
			}
		});

		JLabel ll = new JLabel("المتسابقين لهم نتائج");
		ll.setBounds(100, 160, 200, 25);
		ll.setFont(new Font("Time New Roman", Font.TYPE1_FONT, 20));
		panel.add(ll);

		regisd = new java.awt.List();
		regisd.setBounds(100, 200, 200, 200);
		panel.add(regisd);

		JLabel lll = new JLabel("المتسابقين ليس لهم");
		lll.setBounds(500, 160, 200, 25);
		lll.setFont(new Font("Time New Roman", Font.TYPE1_FONT, 20));
		panel.add(lll);

		list1 = new java.awt.List();
		list1.setBounds(500, 200, 200, 200);
		panel.add(list1);

		JButton remove = new JButton("<< حذف النتيجة");
		remove.setBounds(335, 320, 120, 25);
		panel.add(remove);

		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (regisd.getSelectedIndex() != -1) {
					int index = regisd.getSelectedIndex();
					unregistered.add(registered.get(index));
					list1.add(regisd.getSelectedItem());
					DataBase.updateResult(Integer.parseInt(registered.get(index)[0]), 0);
					regisd.remove(index);
					registered.remove(index);
				} else
					JOptionPane.showMessageDialog(null, "قم باختيار متسابق", null, JOptionPane.ERROR_MESSAGE);
			}
		});

		JButton add = new JButton("إضافة النتيجة >>");
		add.setBounds(335, 250, 120, 25);
		panel.add(add);

		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = list1.getSelectedIndex();

				String s = (String) JOptionPane.showInputDialog(null, "ادخل النتيجة", "نتيجة المتسابق فى المسابقة",
						JOptionPane.PLAIN_MESSAGE, null, null, null);
				if (s == null)
					JOptionPane.showMessageDialog(null, "ادخل النتيجة", null, JOptionPane.ERROR_MESSAGE);
				else if (list1.getSelectedIndex() != -1) {
					int result = Integer.parseInt(s);
					System.out.println("id " + unregistered.get(index)[0] + " result " + result);
					DataBase.updateResult(Integer.parseInt(unregistered.get(index)[0]), result);
					registered.add(unregistered.get(index));
					regisd.add(list1.getSelectedItem());
					list1.remove(index);
					unregistered.remove(index);
				} else
					JOptionPane.showMessageDialog(null, "قم باختيار متسابق", null, JOptionPane.ERROR_MESSAGE);
			}
		});
	}
}