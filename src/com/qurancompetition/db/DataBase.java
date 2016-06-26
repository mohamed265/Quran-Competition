package com.qurancompetition.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBase {

	public static Connection c = null;

	static {
		try {
			Class.forName("org.sqlite.JDBC");
			if (!new File("db.sqllite").exists()) {
				c = DriverManager.getConnection("jdbc:sqlite:db.sqllite");
				createDataBase();
			} else
				c = DriverManager.getConnection("jdbc:sqlite:db.sqllite");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}

	public static Connection getConnection() {
		return c;
	}

	public static void close() {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Closed database successfully");
	}

	public static void createDataBase() {
		Statement stmt = null;
		try {
			stmt = c.createStatement();

			String sql = "CREATE TABLE `competitor` (`name`	TEXT,`dateofbirth` TEXT,`id` TEXT,PRIMARY KEY(id))";
			stmt.executeUpdate(sql);

			String sqll = "CREATE TABLE `competition` (`id`	INTEGER PRIMARY KEY AUTOINCREMENT,`name` TEXT,`minlevel` INTEGER)";
			stmt.executeUpdate(sqll);

			String sqlll = "CREATE TABLE `registration` (`id` INTEGER PRIMARY KEY AUTOINCREMENT,`result` INTEGER,`level` INTEGER,`competitionId` INTEGER,`competitorId`	INTEGER)";
			stmt.executeUpdate(sqlll);

			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Tables created successfully");
	}

	public static void saveCompetition(String name, int minLevel) {
		Statement stmt = null;
		try {
			c.setAutoCommit(false);

			stmt = c.createStatement();
			String sql = "INSERT INTO competition (name,minlevel) " + "VALUES ('" + name + "', " + minLevel + ")";
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Record created successfully");
	}

	public static void saveCompetitor(String name, String dob, String nid) throws Exception {
		Statement stmt = null;
		try {
			c.setAutoCommit(false);

			stmt = c.createStatement();
			String sql = "INSERT INTO competitor (name,dateofbirth,id) " + "VALUES ('" + name + "', " + dob + "," + nid
					+ ")";
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			throw e;
			// System.exit(0);
		}
		System.out.println("Record created successfully");
	}

	public static ArrayList<String[]> getAllCompetition() {
		ArrayList<String[]> list = new ArrayList<>();
		Statement stmt = null;
		try {
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM competition;");
			while (rs.next()) {
				String[] temp = new String[2];
				temp[0] = rs.getString("id");
				temp[1] = rs.getString("name");
				System.out.println("ID = " + temp[0]);
				System.out.println("NAME = " + temp[1]);
				System.out.println();
				list.add(temp);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return list;
	}

	public static ArrayList<String[]> getAllCompetitorByCompetitionId(int competitionId) {
		ArrayList<String[]> list = new ArrayList<>();
		Statement stmt = null;
		try {
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM registration WHERE competitionId = " + competitionId);
			while (rs.next()) {
				String[] temp = new String[4];
				temp[0] = rs.getString("id");
				temp[1] = rs.getString("result");
				temp[2] = rs.getString("level");
				temp[3] = rs.getString("competitorId");

				System.out.println("ID = " + temp[0]);
				System.out.println("result = " + temp[1]);
				System.out.println("level = " + temp[2]);
				System.out.println("competitorId = " + temp[3]);
				System.out.println();
				list.add(temp);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return list;
	}

	public static String getCompetitorNameById(int id) {
		Statement stmt = null;
		String temp = null;
		try {
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM competitor WHERE id = " + id);
			while (rs.next()) {
				temp = rs.getString("name");
				System.out.println("name = " + temp);
				System.out.println();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return temp;
	}

	public static String removeRegistration(int id) {
		Statement stmt = null;
		String temp = null;
		try {
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String sql = "DELETE from registration where ID = " + id;
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return temp;
	}

	public static int saveRegistration(int level, int competitorId, int competitionId) {
		Statement stmt = null;
		try {
			c.setAutoCommit(false);
			String sql = "Insert into registration (result, level, competitionId, competitorId) VALUES (0 , " + level
					+ " , " + competitionId + " , " + competitorId + ")";
			stmt = c.createStatement();
			stmt.executeUpdate(sql);
			c.commit();
			int x = 0;
			sql = "select max(id) from registration";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				x = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			return x;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return 0;
	}

	public static ArrayList<String[]> getAllCompetitorNotRegisteredInByCompetitionId(int competitionId) {
		ArrayList<String[]> list = new ArrayList<>();
		Statement stmt = null;
		try {
			c.setAutoCommit(false);
			ArrayList<String[]> lis = getAllCompetitorByCompetitionId(competitionId);
			String sql = "SELECT * FROM competitor where id not in (";
			for (int i = 0; i < lis.size(); i++) {
				if (i != 0)
					sql += " , ";
				sql += lis.get(i)[3];
			}
			sql += " )";
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String[] temp = new String[2];
				temp[0] = rs.getString("id");
				temp[1] = rs.getString("name");

				System.out.println("ID = " + temp[0]);
				System.out.println("name = " + temp[1]);
				System.out.println();
				list.add(temp);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return list;
	}

	public static int getStartLevel(int competitorId) {
		Statement stmt = null;
		int temp = 0;
		try {
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select max(registration.level) as level from registration inner join competition on competition.id = registration.competitionId where registration.result >= competition.minlevel and registration.competitorId = "
							+ competitorId);
			while (rs.next()) {
				temp = rs.getInt("level");
				System.out.println("name = " + temp);
				System.out.println();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			temp = 0;
		}
		return temp;
	}

	public static int updateResult(int competitorId, int result) {
		Statement stmt = null;
		int temp = 0;
		try {
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String sql = "UPDATE registration set result = " + result + " where id = " + competitorId;
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			temp = 0;
		}
		return temp;
	}
}
