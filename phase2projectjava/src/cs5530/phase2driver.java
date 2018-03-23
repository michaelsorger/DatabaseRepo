package cs5530;


import java.sql.*;
import java.io.*;

public class phase2driver {

	/**
	 * @param args
	 */
	public static void displayMenu()
	{
		 System.out.println("        Welcome to UUber System     ");
    	 System.out.println("1. Registration:");
    	 System.out.println("2. Reserve:");
    	 System.out.println("3. New UC:");
    	 System.out.println("4. Rides:");
    	 System.out.println("5. Favorite Recordings:");
    	 System.out.println("6. Feedback Recordings:");
    	 System.out.println("7. Usefulness Ratings:");
    	 System.out.println("8. Trust recordings:");
    	 System.out.println("9. UC Browsing:");
    	 System.out.println("10. Useful feedbacks:");
    	 System.out.println("11. UC suggestions:");
    	 System.out.println("12. ‘Two degrees of separation’:");
    	 System.out.println("13.  Statistics:");
    	 System.out.println("14. User awards:");
    	 System.out.println("15. ABORT:");
    	 System.out.println("please enter your choice:");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		phase2Connector con=null;
		String choice = "";
        String sql=null;
        int c=0;
         try
		 {
			//remember to replace the password -> ?
			 	 con= new phase2Connector();
	             System.out.println ("Database connection established");
	         
	             BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	             boolean looping = true;
	             while(looping)
	             {
	            	 displayMenu();
	            	 while ((choice = in.readLine()) == null && choice.length() == 0);
	            	 try{
	            		 c = Integer.parseInt(choice);
	            	 }catch (Exception e)
	            	 {
	            		 //no choice was selected
	            		 continue;
	            	 }
	            	 if (c<1 | c>15)
	            		 continue;
	            	 if (c==1)
	            	 {
	            		 String register_type = "";
	            		 String registration_str = "";
	            		 String user_address = "";
	            		 String phone_num = "";
	            		 String fileName = "";
	            		 System.out.println("registering for user or driver? (enter 'user' or 'driver'");
	            		 while ((register_type = in.readLine()) == null && register_type.length() == 0);
	            		 if(register_type.equals("user"))
	            		 {
		            		 System.out.println("please enter: login password name");
		            		 while ((registration_str = in.readLine()) == null && registration_str.length() == 0);
		            		 String[] reg_arr = registration_str.split("\\s+");
		            		 System.out.println("please enter: address");
		            		 while ((user_address = in.readLine()) == null && user_address.length() == 0);
		            		 System.out.println("please enter: phone number");
		            		 while ((phone_num = in.readLine()) == null && phone_num.length() == 0);
		            		 Registration reg = new Registration();
		            		 System.out.println(reg.registerNewUberUser(reg_arr[0],reg_arr[1],reg_arr[2],user_address,Integer.parseInt(phone_num),con.stmt));
	            		 }
	            		 else if(register_type.equals("driver"))
	            		 {
		            		 System.out.println("please enter: login password name");
		            		 while ((registration_str = in.readLine()) == null && registration_str.length() == 0);
		            		 String[] reg_arr = registration_str.split("\\s+");
		            		 System.out.println("please enter: address");
		            		 while ((user_address = in.readLine()) == null && user_address.length() == 0);
		            		 System.out.println("please enter: phone number");
		            		 while ((phone_num = in.readLine()) == null && phone_num.length() == 0);
		            		 System.out.println("please enter: 'filename' for picture");
		            		 while ((fileName = in.readLine()) == null && fileName.length() == 0);
		            		 Registration reg = new Registration();
		            		 System.out.println(reg.registerNewUberDriver(reg_arr[0],reg_arr[1],reg_arr[2],user_address,Integer.parseInt(phone_num), fileName, con.stmt));
	            		 }
	            		 else
	            		 {
		            		 System.out.println("input did not match options, retry from beginning");
	            		 }
	            	 }
	            	 else if (c==2)
	            	 {	 
	            		 System.out.println("please enter your query below:");
	            		 while ((sql = in.readLine()) == null && sql.length() == 0)
	            			 System.out.println(sql);
	            		 ResultSet rs=con.stmt.executeQuery(sql);
	            		 ResultSetMetaData rsmd = rs.getMetaData();
	            		 int numCols = rsmd.getColumnCount();
	            		 while (rs.next())
	            		 {
	            			 //System.out.print("cname:");
	            			 for (int i=1; i<=numCols;i++)
	            				 System.out.print(rs.getString(i)+"  ");
	            			 System.out.println("");
	            		 }
	            		 System.out.println(" ");
	            		 rs.close();
	            	 }
	            	 else if(c == 15)
	            	 {   
	            		 System.out.println("TERMINATING");
	            		 con.stmt.close(); 
	            		 looping = false;
	            		 break;
	            	 }
	            	 else
	            	 {
	            		 System.out.println("EoM");
	            		 con.stmt.close(); 
	            		 looping = false;
	            		 break;
	            	 }
	             }
		 }
         catch (Exception e)
         {
        	 e.printStackTrace();
        	 System.err.println ("Either connection error or query execution error!");
         }
         finally
         {
        	 if (con != null)
        	 {
        		 try
        		 {
        			 con.closeConnection();
        			 System.out.println ("Database connection terminated");
        		 }
        	 
        		 catch (Exception e) { /* ignore close errors */ }
        	 }	 
         }
	}
}
