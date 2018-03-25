package cs5530;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class Statistics {
		public Statistics()
		{}
		//Reserve(login, vin, pid primary key, cost int, date time, login foreign key references UU, vin foreign keyreferences UC, pid foreign key references Period);
		
		//registering an uber user, this does not require a photo, so the sql statement is different
		public String StatisticsUC(int num, Statement stmt) throws SQLException
		{
			Set<String> cats = new HashSet<String>();
			String category = "Select DISTINCT category FROM uc_table";
			ResultSet cat = stmt.executeQuery(category);
			
			while(cat.next()) {
				cats.add(cat.getString(1));
			}
			
			System.out.println("Popularity:");
			for(String type : cats){
				String popular = "Select V.vin1, V.num FROM (Select R.vin as vin1, Count(*) as num FROM reserve R GROUP BY R.vin Order BY Count(*) DESC) as V, uc_table UC WHERE UC.category = '"+type+"' AND UC.vin = V.vin1";
				ResultSet rs = stmt.executeQuery(popular);
				int count = 1;
				System.out.println("Category: " + type);
				while(rs.next() && count < num+1) {
					System.out.println("Vin: " + rs.getInt(1) + " Rides: " + rs.getInt(2));
				}
			}
			
			System.out.println("Cost:");
			for(String type : cats){
				String cost = "Select V.vin1, V.avg FROM (Select R.vin as vin1, AVG(R.cost) as avg FROM reserve R GROUP BY R.vin Order BY AVG(R.cost) DESC) as V, uc_table UC WHERE UC.category = '"+type+"' AND UC.vin = V.vin1";
				ResultSet rs = stmt.executeQuery(cost);
				int count = 1;
				
				System.out.println("Category: " + type);
				while(rs.next() && count < num+1) {
					System.out.println("Vin: " + rs.getInt(1) + " Cost: " + rs.getInt(2));
				}
			}
			
			System.out.println("Driver Ratings:");
			for(String type : cats){
				String cost = "Select UC.login, V.avg FROM (Select F.vin as vin1, AVG(F.score) as avg FROM feedback F GROUP BY F.vin Order BY AVG(F.score) DESC) as V, uc_table UC WHERE UC.category = '"+type+"' AND UC.vin = V.vin1";
				ResultSet rs = stmt.executeQuery(cost);
				int count = 1;
				
				System.out.println("Category: " + type);
				while(rs.next() && count < num+1) {
					System.out.println("Driver: " + rs.getString(1) + " Rating: " + rs.getInt(2));
				}
			}
			
			
			return " ";
		}

}