package cs5530;
import java.sql.*;

/*
 * Favorite recordings: Users can declare a UC as his/her favorite car to hire
 */
public class UFavoriteUC {
		public UFavoriteUC()
		{}
		
		public String favoriteUC(int fid, int vin, String ulogin, Statement stmt)
		{
			String output="good";

	    	//insert into rides
	    	//insert
			String sql= "INSERT INTO u_favorite_uc(fid, vin, login) VALUES ("+fid+", "+vin+", '"+ulogin+"')";
   		 	System.out.println("executing: "+sql);
   		 	try{
	   		 	int ret =stmt.executeUpdate(sql);
	   		 	if(ret == -1)
	   		 	{
	   		 		System.out.println("error updating database table");
	   		 		output = "bad";
	   		 	}
   		 	}
   		 	catch(Exception e)
   		 	{
   		 		e.printStackTrace();
   		 		output = "bad";
   		 		System.out.println("cannot execute the query");
   		 	}
		    return output;
		}
}
