package cs5530;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class GetFeedBack {
	public GetFeedBack() {
	}
	// Reserve(login, vin, pid primary key, cost int, date time, login foreign key
	// references UU, vin foreign keyreferences UC, pid foreign key references
	// Period);

	// registering an uber user, this does not require a photo, so the sql statement
	// is different
	public String feedBack(String driver, int num, Statement stmt) {

		String query = "Select UC.vin, F.text, AVG(R.rating) from feedback F, ud_table UD, uc_table UC, rating R WHERE F.vin = UC.vin AND UC.login = '"
				+ driver + "' AND R.fid = F.fid GROUP BY R.fid ORDER BY AVG(R.rating);";
		try {
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			int count = 0;
				while (rs.next() && count < num) {
						for(int j = 1; j < 4; j++) {
						 System.out.print(rs.getString(j)+" ");
						 //System.out.println("");
						 }
						
					count++;
				}
			rs.close();
			System.out.println("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return " ";
	}

}