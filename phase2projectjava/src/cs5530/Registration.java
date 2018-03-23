package cs5530;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class Registration {
		public Registration()
		{}
		
		//registering an uber user, this does not require a photo, so the sql statement is different
		public String registerNewUberUser(String ulogin, String upassword, String uname, String uaddress, int uphone, Statement stmt)
		{
			String sql= "INSERT INTO uu_table (login, password, name, address, phone, isTrusted) VALUES ('"+ulogin+"', '"+upassword+"', '"+uname+"', '"+uaddress+"', "+uphone+","+0+")";
			String output="good";
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
   		 		System.out.println("cannot execute the query");
   		 	}
		    return output;
		}
		
		//registering an uber driver, photo provided by file filename
		public String registerNewUberDriver(String ulogin, String upassword, String uname, String uaddress, int uphone, String picFilePath, Statement stmt)
		{
			final String dir = System.getProperty("user.dir");
	        System.out.println("current dir = " + dir);
			
			String sql= "INSERT INTO ud_table (login, password, name, address, phone, photo) VALUES ('"+ulogin+"', '"+upassword+"', '"+uname+"', '"+uaddress+"', "+uphone+", LOAD_FILE('"+dir+File.separator+picFilePath+"'))";
			String output="good";
   		 	System.out.println("executing "+sql);
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
   		 		System.out.println("cannot execute the query");
   		 	}
		    return output;
		}

}
