package cs5530;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class Trust {
		public Trust()
		{}
		//Reserve(login, vin, pid primary key, cost int, date time, login foreign key references UU, vin foreign keyreferences UC, pid foreign key references Period);
		
		//registering an uber user, this does not require a photo, so the sql statement is different
		public String rateTrust(String login, String other_login, String rating, Statement stmt)
		{
			String sql= "INSERT INTO trust VALUES ('"+login+"', '"+other_login+"', '"+rating+"')";
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

}