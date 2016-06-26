package com.qurancompetition.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.qurancompetition.db.DataBase;

public class CompetitionResults extends JFrame implements ActionListener {

	private String[] competitionsName, levelsName;
	private JComboBox comboBox, levelBox;
	ArrayList<String[]> competitionList;
	HashMap<Integer, ArrayList<tempObject>> map;
	private java.awt.List regisd;
	private int competitionId = 0, levelId = 0;
	boolean b1, b2;

	public CompetitionResults() throws HeadlessException {
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

		levelsName = new String[31];
		levelsName[0] = "الجميع";
		for (int i = 0; i < 30; i++)
			levelsName[i + 1] = (i + 1) + "";
		levelBox = new JComboBox(levelsName);
		levelBox.setBounds(250, 160, 220, 25);
		panel.add(levelBox);
		
		comboBox.addActionListener(this);
		levelBox.addActionListener(this);

		JLabel ll = new JLabel("المستوى");
		ll.setBounds(500, 160, 220, 25);
		ll.setFont(new Font("Time New Roman", Font.TYPE1_FONT, 20));
		panel.add(ll);

		regisd = new java.awt.List();
		regisd.setBounds(300, 200, 330, 200);
		panel.add(regisd);
	}

	public class tempObject implements Comparator<tempObject> {
		public String name;
		public int result;

		@Override
		public int compare(tempObject o1, tempObject o2) {
			return -(o1.result - o2.result);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("comboBox : " + e.getSource().equals(comboBox));
		if (e.getSource().equals(comboBox)) {
			competitionId = comboBox.getSelectedIndex();
			regisd.clear();
			levelId = 0;
			levelBox.setSelectedIndex(0);
			map = new HashMap<>();
			for (int i = 0; i < 31; i++) {
				map.put(i, new ArrayList<tempObject>());
			}
			regisd.clear();
			if (competitionId != 0) {
				competitionId = Integer.parseInt(competitionList.get(competitionId - 1)[0]);
				ArrayList<String[]> temp = DataBase.getAllCompetitorByCompetitionId(competitionId);
				int level;
				for (int i = 0; i < temp.size(); i++) {
					level = Integer.parseInt(temp.get(i)[2]);
					tempObject to = new tempObject();
					to.name = DataBase.getCompetitorNameById(Integer.parseInt(temp.get(i)[3]));
					to.result = Integer.parseInt(temp.get(i)[1]);
					map.get(level).add(to);
					map.get(0).add(to);
				}
				Collections.sort(map.get(0), new tempObject());
				for (int i = 0; i < map.get(0).size(); i++)
					regisd.add(map.get(0).get(i).name + " النتيجة " + map.get(0).get(i).result);
			}
		} else {
			try{
			levelId = levelBox.getSelectedIndex();
			regisd.clear();
			regisd.clear();
			Collections.sort(map.get(levelId), new tempObject());
			for (int i = 0; i < map.get(levelId).size(); i++)
				regisd.add(map.get(levelId).get(i).name + " النتيجة " + map.get(levelId).get(i).result);
			}catch(Exception b){}
		}

	}
}