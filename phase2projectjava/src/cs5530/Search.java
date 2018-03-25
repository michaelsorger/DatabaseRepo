package cs5530;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Search {
	public Search() {
	}
	
	
	
	//TODO: Make sure the address searches the value for a specific word
	
	
	public String searchUC(String[] arr, Statement stmt) throws SQLException {
		ArrayList<Integer> cats = new ArrayList<Integer>();
		ArrayList<Integer> models = new ArrayList<Integer>();
		ArrayList<Integer> address = new ArrayList<Integer>();
		boolean category = true;
		boolean model = true;
		boolean addy = true;
		// Avg feeback
		if (arr[3].equals("0")) {
			if (!arr[0].equals("No")) {
				String sql = "Select UC.vin, AVG(F.score) FROM uc_table UC, feedback F WHERE UC.category = '" + arr[0]
						+ "' AND UC.vin = F.vin GROUP BY UC.vin ORDER BY AVG(F.score) DESC";
				ResultSet cat = stmt.executeQuery(sql);

				while (cat.next()) {
					cats.add(cat.getInt(1));
				}
				cat.close();
			} else {
				category = false;
			}

			if (!arr[1].equals("No")) {
				// String sql = "Select UC.vin, AVG(F.score) FROM uc_table UC, feedback F WHERE
				// UC.category = '"+arr[0]+"' AND UC.vin = F.vin GROUP BY UC.vin ORDER BY
				// AVG(F.score) DESC";
				String sql = "Select UC.vin, AVG(F.score) FROM uc_table UC, feedback F, ud_table UD WHERE UD.address LIKE %'"
						+ arr[1]
						+ "'% AND UD.login = UC.login AND UC.vin = F.vin GROUP BY UC.vin ORDER BY AVG(F.score) DESC";
				ResultSet add = stmt.executeQuery(sql);

				while (add.next()) {
					address.add(add.getInt(1));
				}
				add.close();
			} else {
				addy = false;
			}

			if (!arr[2].equals("No")) {
				String sql = "Select UC.vin, AVG(F.score) FROM uc_table UC, feedback F WHERE UC.model = '" + arr[2]
						+ "' AND UC.vin = F.vin GROUP BY UC.vin ORDER BY AVG(F.score) DESC";
				ResultSet mod = stmt.executeQuery(sql);

				while (mod.next()) {
					models.add(mod.getInt(1));
				}
				mod.close();
			} else {
				model = false;
			}

			if(category && addy && model) {
				for (Integer i : cats) {
					if (models.contains(i) && address.contains(i)) {
						System.out.println("Car: " + i);
					}
				}
			}
			else if(!category && addy && model) {
				for (Integer i : models) {
					if (address.contains(i)) {
						System.out.println("Car: " + i);
					}
				}
			}
			else if(category && !addy && model) {
				for (Integer i : cats) {
					if (models.contains(i)) {
						System.out.println("Car: " + i);
					}
				}
			}
			else if(category && addy && !model) {
				for (Integer i : cats) {
					if (address.contains(i)) {
						System.out.println("Car: " + i);
					}
				}
			}
			else if(!category && !addy && model) {
				for (Integer i : models) {
						System.out.println("Car: " + i);
					
				}
			}
			else if(category && !addy && !model) {
				for (Integer i : cats) {
						System.out.println("Car: " + i);
					
				}
			}
			else if(!category && addy && !model) {
				for (Integer i : address) {
						System.out.println("Car: " + i);
				}
			}
			else {
				//Do nothing
			}
			

		}
		// Trusted feedback
		else {
			if (!arr[0].equals("No")) {
				String sql = "Select UC.vin, AVG(F.score) FROM uc_table UC, feedback F, uu_table UU WHERE UC.category = '" + arr[0]
						+ "' AND UC.vin = F.vin AND F.login = UU.login AND UU.isTrusted > 0 GROUP BY UC.vin ORDER BY AVG(F.score) DESC";
				ResultSet cat = stmt.executeQuery(sql);

				while (cat.next()) {
					cats.add(cat.getInt(1));
				}
				cat.close();
			} else {
				category = false;
			}

			if (!arr[1].equals("No")) {
				// String sql = "Select UC.vin, AVG(F.score) FROM uc_table UC, feedback F WHERE
				// UC.category = '"+arr[0]+"' AND UC.vin = F.vin GROUP BY UC.vin ORDER BY
				// AVG(F.score) DESC";
				String sql = "Select UC.vin, AVG(F.score) FROM uc_table UC, feedback F, ud_table UD, uu_table UU WHERE UD.address LIKE %'"
						+ arr[1]
						+ "'% AND UD.login = UC.login AND UC.vin = F.vin AND F.login = UU.login AND UU.isTrusted > 0 GROUP BY UC.vin ORDER BY AVG(F.score) DESC";
				ResultSet add = stmt.executeQuery(sql);

				while (add.next()) {
					address.add(add.getInt(1));
				}
				add.close();
			} else {
				addy = false;
			}

			if (!arr[2].equals("No")) {
				String sql = "Select UC.vin, AVG(F.score) FROM uc_table UC, feedback F, uu_table UU WHERE UC.model = '" + arr[2]
						+ "' AND UC.vin = F.vin AND F.login = UU.login AND UU.isTrusted > 0 GROUP BY UC.vin ORDER BY AVG(F.score) DESC";
				ResultSet mod = stmt.executeQuery(sql);

				while (mod.next()) {
					models.add(mod.getInt(1));
				}
				mod.close();
			} else {
				model = false;
			}

			if(category && addy && model) {
				for (Integer i : cats) {
					if (models.contains(i) && address.contains(i)) {
						System.out.println("Car: " + i);
					}
				}
			}
			else if(!category && addy && model) {
				for (Integer i : models) {
					if (address.contains(i)) {
						System.out.println("Car: " + i);
					}
				}
			}
			else if(category && !addy && model) {
				for (Integer i : cats) {
					if (models.contains(i)) {
						System.out.println("Car: " + i);
					}
				}
			}
			else if(category && addy && !model) {
				for (Integer i : cats) {
					if (address.contains(i)) {
						System.out.println("Car: " + i);
					}
				}
			}
			else if(!category && !addy && model) {
				for (Integer i : models) {
						System.out.println("Car: " + i);
					
				}
			}
			else if(category && !addy && !model) {
				for (Integer i : cats) {
						System.out.println("Car: " + i);
					
				}
			}
			else if(!category && addy && !model) {
				for (Integer i : address) {
						System.out.println("Car: " + i);
				}
			}
			else {
				//Do nothing
			}

		}
		return " ";
	}

}