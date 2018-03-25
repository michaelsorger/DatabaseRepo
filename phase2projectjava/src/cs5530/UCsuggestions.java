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
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class UCsuggestions {
	public UCsuggestions() {
	}
	// Reserve(login, vin, pid primary key, cost int, date time, login foreign key
	// references UU, vin foreign keyreferences UC, pid foreign key references
	// Period);

	// registering an uber user, this does not require a photo, so the sql statement
	// is different
	public String suggestions(int uc, String username, Statement stmt) {
		HashMap<Integer, Integer> cars = new HashMap<Integer, Integer>();
		String oq = "SELECT DISTINCT(R.login) From reserve R WHERE R.vin = " + uc + "";
		ArrayList<String> list = new ArrayList<String>();
		

		try {
			ResultSet rs = stmt.executeQuery(oq);

			while (rs.next()) {
				list.add(rs.getString(1));
			}
			rs.close();
			
			
			for(String s : list) {
				String sql = "Select R.vin, Count(*) FROM reserve R WHERE R.login = '" + s
						+ "' GROUP BY R.vin";
				ResultSet rs1 = stmt.executeQuery(sql);

				while (rs1.next()) {

					int car = rs1.getInt(1);
					int rides = rs1.getInt(2);
					if (cars.containsKey(car)) {
						int new_count = cars.get(car) + rides;
						cars.remove(car);
						cars.put(car, new_count);
					} else {
						cars.put(car, rides);
					}

				}
				rs1.close();
			}
			
			
			//TODO: SORT THIS BOIII
			for (Map.Entry<Integer, Integer> entry : cars.entrySet()) {
			    int key = entry.getKey();
			    int value = entry.getValue();
			    System.out.println("Vin: " + key + " Rides: " + value);
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return " ";
	}

}