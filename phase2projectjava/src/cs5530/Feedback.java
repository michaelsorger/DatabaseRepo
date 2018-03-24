package cs5530;
import java.sql.*;

/*
 * Users can record their feedback for a UC. We should record the date, the
	numerical score (0= terrible, 10= excellent), and an optional short text. No changes are allowed; only one
	feedback per user per UC is allowed.
 */
public class Feedback {
		public Feedback()
		{}
		
		public String feedback(int fid, String utext, int uscore, String udate, String ulogin, int vin, Statement stmt)
		{
			String output="good";
			String sql= "INSERT INTO feedback(fid, text, score, fbdate, login, vin) VALUES ("+fid+", '"+utext+"', "+uscore+", '"+udate+"', '"+ulogin+"' ,"+vin+")";
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
