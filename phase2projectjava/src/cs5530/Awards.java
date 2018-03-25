package cs5530;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Awards {
		public Awards()
		{}
		//Reserve(login, vin, pid primary key, cost int, date time, login foreign key references UU, vin foreign keyreferences UC, pid foreign key references Period);
		
		//registering an uber user, this does not require a photo, so the sql statement is different
		public String userAwards(int num, Statement stmt) throws SQLException
		{
		
			
			Set<String> names = new HashSet<String>();
			String name = "Select DISTINCT login2 FROM trust";
			ResultSet rs = stmt.executeQuery(name);
			
			while(rs.next()) {
				names.add(rs.getString(1));
			}
			rs.close();
			
			Map<String, Integer> map = new HashMap<String, Integer>();
			for(String n : names) {
				int t1 = 0;
				int t0 = 0;
//				String trust = "SELECT T1.log, T1.trust, T0.trust FROM uu_table UU, (SELECT T.login2 as log, Count(*) as trust FROM trust T WHERE T.rating = 1 GROUP BY T.login2) as T1, "
//						+ "(SELECT T.login2 as log, Count(*) as trust FROM trust T WHERE T.rating = 0 GROUP BY T.login2) as T0 WHERE T1.log = T0.log";
				String trust = "Select Count(*) FROM trust T WHERE T.rating = 1 && T.login2 = '"+n+"'";
				String not_trust = "Select Count(*) FROM trust T WHERE T.rating = 0 && T.login2 = '"+n+"'";
				ResultSet t = stmt.executeQuery(trust);
				if(t.next()) {
					t1 = t.getInt(1);
				}
				t.close();
				ResultSet nt = stmt.executeQuery(not_trust);
				if(nt.next()) {
					t0 = nt.getInt(1);
				}
				nt.close();
				
				map.put(n, t1-t0);
				
			}

				//TODO: SORT THIS BOIII
				System.out.println("Trust:");
				for(Map.Entry<String, Integer> pair: map.entrySet()) {
					System.out.println("Name: " + pair.getKey() + " Trust Score: " + pair.getValue());
				}
			
				
				System.out.println("Usefulness:");
				String sql = "Select F.login, AVG(R.rating) FROM feedback F, rating R WHERE F.fid = R.fid GROUP BY F.login ORDER BY AVG(R.rating)";
				ResultSet use = stmt.executeQuery(sql);
				
				while(use.next()) {
					System.out.println("User: " + use.getString(1) + "  Avg Rating: " + use.getInt(2));
				}
				
				
			return " ";
		}

}