package cs5530;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;

/*New UC: A user may record the details of a new UC, and may update the information regarding
an existing UC he/she owns.
*/
public class NewUC {
		public NewUC()
		{}
		
		public String updateNewUberCar(int uvin, String ucategory, String umake, String umodel, int uyear, String ulogin, Statement stmt, BufferedReader in)
		{
		    String output="good";
			String inp_case = "";
			System.out.println("new or update existing information? (new/update)");
   		    try {
				while ((inp_case = in.readLine()) == null && inp_case.length() == 0);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
   		    
   		    if(inp_case.equals("new"))
   		    {
   		    	//insert
   				String sql= "INSERT INTO uc_table (vin, category, make, model, year, login) VALUES ("+uvin+", '"+ucategory+"', '"+umake+"', '"+umodel+"', "+uyear+",'"+ulogin+"')";
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
   		    }
   		    
   		    else if(inp_case.equals("update"))
   		    {
   		    	//update
   		    	String sql="UPDATE uc_table SET vin = "+uvin+", category = '"+ucategory+"', make = '"+umake+"', model = '"+umodel+"', year = "+uyear+" WHERE '"+ulogin+"' = uc_table.login";
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
   		    }
   		    
   		    else
   		    {
   		    	System.out.println("wrong input, not updating");
   		    }

		    return output;
		}
}
