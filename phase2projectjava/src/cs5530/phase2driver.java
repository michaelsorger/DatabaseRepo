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
    	 System.out.println("15. DISCONNECT/'LOG OUT OF USER SESSION':");
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
            	 //try another number
            	 if (c<1 | c>15)
            		 continue;
            	 
            	 /*
            	  * FINISHED - Michael
            	  * Registration: Registration: a new user (either UD or UU) has to provide the appropriate information;
					he/she can pick a login-name and a password. The login name should be checked for uniqueness.
            	  */
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
	            		 System.out.println("please enter: 'filename' for picture (must be in same working directory)");
	            		 while ((fileName = in.readLine()) == null && fileName.length() == 0);
	            		 Registration reg = new Registration();
	            		 System.out.println(reg.registerNewUberDriver(reg_arr[0],reg_arr[1],reg_arr[2],user_address,Integer.parseInt(phone_num), fileName, con.stmt));
            		 }
            		 else
            		 {
	            		 System.out.println("input did not match options, retry from beginning");
            		 }
            	 }
            	 
            	 /*Reserve: After registration, a user can record a reservation to any UC (the same user may reserve
    			   the same UC multiple times). Each user session (meaning each time after a user has logged into the system)
    			   may add one or more reservations, and all reservations added by a user in a user session are reported to
    			   him/her for the final review and confirmation, before they are added into the database.
    			 */
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
            	
            	 // FINISHED - Michael
            	 // New UC: A user may record the details of a new UC, and may update the information regarding
            	 //an existing UC he/she owns. 
            	 // *NOTE I'M ASSUMING THAT THE USER ONLY KNOWS HIS/HER LOGIN, THIS MAKES IT THE ONE HE/SHE 'OWNS'
            	 else if (c==3)
            	 {	 
            		 String arguments = "";
            		 System.out.println("please enter: vin category make model year login");
            		 while ((arguments = in.readLine()) == null && arguments.length() == 0);
            	     String[] arg_arr = arguments.split("\\s+");
            	     NewUC new_uc = new NewUC();
            	     System.out.println(new_uc.updateNewUberCar(Integer.parseInt(arg_arr[0]),arg_arr[1],arg_arr[2],arg_arr[3],Integer.parseInt(arg_arr[4]),arg_arr[5],con.stmt, in));
            	 } 
            	 
            	 //FINISHED - Michael
            	 /*Rides: A user can record a ride with a UC (the same user may ride the same UC multiple times).
            	 Each user session (meaning each time after a user has logged into the system) may add one or more rides,
            	 and all rides added by a user in a user session are reported to him/her for the final review and confirmation,
            	 before they are added into the database. Note that a user may only record a ride at a UC during a period
            	 that the associated UD is available
            	 */
            	 else if (c==4)
            	 {	 
            		 String arguments = "";
            		 System.out.println("please enter: rid cost date from to login vin (date in form 1000-01-01, driver login, car vin");
            		 while ((arguments = in.readLine()) == null && arguments.length() == 0);
            	     String[] arg_arr = arguments.split("\\s+");
            	     Rides rides = new Rides();
            	     System.out.println(rides.recordRide(Integer.parseInt(arg_arr[0]),Integer.parseInt(arg_arr[1]),arg_arr[2],arg_arr[3],arg_arr[4],arg_arr[5],Integer.parseInt(arg_arr[6]),con.stmt, in));
            	 }
            	 
            	 //FINISHED - Michael
            	 /* Favorite recordings: Users can declare a UC as his/her favorite car to hire
            	  */
            	 else if (c==5)
            	 {	 
            		 String arguments = "";
            		 System.out.println("please enter for favoriting an uber car: fid vin login (vin of uber car, user login)");
            		 while ((arguments = in.readLine()) == null && arguments.length() == 0);
            	     String[] arg_arr = arguments.split("\\s+");
            	     UFavoriteUC u_fav_uc = new UFavoriteUC();
            	     System.out.println(u_fav_uc.favoriteUC(Integer.parseInt(arg_arr[0]),Integer.parseInt(arg_arr[1]),arg_arr[2],con.stmt));
            	 }
            	 
            	 /* FINISHED - Michael
            	  * Users can record their feedback for a UC. We should record the date, the
					numerical score (0= terrible, 10= excellent), and an optional short text. No changes are allowed; only one
					feedback per user per UC is allowed.
            	  */
            	 else if (c==6)
            	 {	 
            		 String arguments = "";
            		 String text = "";
            		 System.out.println("please first enter for providing feedback: text (message to provide feedback)");
            		 while ((text = in.readLine()) == null && text.length() == 0);
            		 System.out.println("please now enter for providing feedback: fid score date login vin (date in form 1000-01-01)");
            		 while ((arguments = in.readLine()) == null && arguments.length() == 0);
            	     String[] arg_arr = arguments.split("\\s+");
            	     Feedback fb = new Feedback();
            	     //public String favoriteUC(int fid, String utext, int uscore, String udate, String ulogin, int vin, Statement stmt)
            	     System.out.println(fb.feedback(Integer.parseInt(arg_arr[0]), text, Integer.parseInt(arg_arr[1]),arg_arr[2],arg_arr[3], Integer.parseInt(arg_arr[4]),con.stmt));
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
