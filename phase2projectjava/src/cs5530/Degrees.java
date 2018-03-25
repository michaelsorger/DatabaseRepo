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

public class Degrees {
	public Degrees() {
	}
	// Reserve(login, vin, pid primary key, cost int, date time, login foreign key
	// references UU, vin foreign keyreferences UC, pid foreign key references
	// Period);

	// registering an uber user, this does not require a photo, so the sql statement
	// is different
	public Integer getDegree(String user1, String user2, Statement stmt) throws SQLException {

		int count = 1;

		String new_q = "Select Count(*) FROM u_favorite_uc F WHERE F.login = '" + user1
				+ "' AND F.vin = ANY(Select F1.vin FROM u_favorite_uc F1 WHERE F1.login = '" + user2 + "')";
		// "SELECT 1" + "FROM friends f1\n" + "WHERE f1.member_id = 1\n" + " AND
		// f1.friend_id = 7";

		ResultSet test = stmt.executeQuery(new_q);

		if (test.next()) {
			if (test.getInt(1) > 0) {
				return count;
			} else {
				Set<String> users = new HashSet<String>();
				String sql = "Select DISTINCT F.login FROM u_favorite_uc F WHERE F.vin = ANY(Select F1.vin FROM u_favorite_uc F1 WHERE F1.login = '"
						+ user1 + "')";
				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					users.add(rs.getString(1));
				}
				return recursiveDegree(count, users, user2, stmt);
			}
		}

		return -1;
		//return 1;
	}

	public static int recursiveDegree(int count, Set<String> users, String user2, Statement stmt)
			throws SQLException {
		count++;
		
		for(String name : users) {
			String new_q = "Select Count(*) FROM u_favorite_uc F WHERE F.login = '"+name+"' AND F.vin = ANY(Select F1.vin FROM u_favorite_uc F1 WHERE F1.login = '"+user2+"')";
			ResultSet test = stmt.executeQuery(new_q);
			if(test.next()) {
				if(test.getInt(1) > 0) {
					return count; 
				}
			}
		}

		for(String name : users) {
			String sql = "Select DISTINCT F.login FROM u_favorite_uc F WHERE F.vin = ANY(Select F1.vin FROM u_favorite_uc F1 WHERE F1.login = '"+name+"'";
			ResultSet test = stmt.executeQuery(sql);
			while(test.next()) {
				users.add(test.getString(1));
			}
		}
				
	
		return recursiveDegree(count, users, user2, stmt);
	}

}

// int count = 1;
// ArrayList<Integer> start = new ArrayList<Integer>();
// ArrayList<Integer> dest = new ArrayList<Integer>();
// String query = "Select F.vin FROM u_favorite_uc WHERE F.login = '" + user1 +
// "'";
// String query1 = "Select F.vin FROM u_favorite_uc WHERE F.login = '" + user2 +
// "'";
// try {
// ResultSet rs = stmt.executeQuery(query);
// while (rs.next()) {
// start.add(rs.getInt(1));
// }
// rs.close();
// rs = stmt.executeQuery(query1);
// while (rs.next()) {
// dest.add(rs.getInt(1));
// }
// rs.close();
//
// for (int i : start) {
// for (int j : dest) {
// if (i == j) {
// return count;
// }
// }
// }
//
// } catch (SQLException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
//
// return getDegree(count, start, dest, stmt);