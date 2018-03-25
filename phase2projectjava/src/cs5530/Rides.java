package cs5530;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;

/*Rides: A user can record a ride with a UC (the same user may ride the same UC multiple times).
Each user session (meaning each time after a user has logged into the system) may add one or more rides,
and all rides added by a user in a user session are reported to him/her for the final review and confirmation,
before they are added into the database. Note that a user may only record a ride at a UC during a period
that the associated UD is available
*/
public class Rides {
		public Rides()
		{}
		
		//System.out.println("please enter: rid cost date from to login vin");
		//1000-01-01
		public String recordRide(int urid, int ucost, String udate, String ufrom, String uto, String dlogin, int cvin, Statement stmt, BufferedReader in)
		{
		    String output="good";
   		    String confirmation = "";
		    System.out.println("adding values: " + urid + " " + ucost + " " + udate + " " + ufrom + " " + uto + " " + dlogin + " " +cvin);
		    System.out.println("is the correct? (yes/no)");
   		    try {
				while ((confirmation = in.readLine()) == null && confirmation.length() == 0);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
   		    
   		    if(confirmation.equals("yes"))
   		    {
   		    	//insert into rides
   		    	//insert
   				String sql= "INSERT INTO rides(rid, rcost, rdate, rfrom, rto, login, vin) VALUES ("+urid+", "+ucost+", '"+udate+"', '"+ufrom+"', '"+uto+"', '"+dlogin+"', "+cvin+")";
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
   	   		System.out.println("If you'd like to add another ride, press 4 again");
   		    }
   		    else
   		    {
   		    	System.out.println("exiting to main menu, please try again");
   		    	output = "bad";
   		    }

		    return output;
		}
}
