package dbTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import driverFactory.DriverManager;
import resouceUtils.DataConnection;

public class DataBaseTest extends DriverManager {
	
	String ln, fn, tl, ad, ct, st, dp, tm;
	
	DataBaseTest(){
		
	}
	
	 
	 
	 @SuppressWarnings("unchecked")
		public static JSONObject GetRecords(){
			
			
			//System.out.println("Input ########## "  + LastName );
			Connection dbConnection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;			
			
			JSONObject jsonObj = new JSONObject();	
			String selectSQL =  "SELECT FIRSTNAME, LASTNAME, TELEPHONE, ADDRESS, CITY, STATE, DATEOFAPOINTMENT, TIMEOFSERVICE FROM apointments WHERE LASTNAME = ?";
			
			
		try
		{
			if(dbConnection == null || dbConnection.isClosed())
				{
				dbConnection = DataConnection.getConnection();
				}
			    String name = "Juma";			
				preparedStatement = dbConnection.prepareStatement(selectSQL);
				preparedStatement.setString(1, name);

				// execute select SQL stetement
				 rs = preparedStatement.executeQuery();
				
				 int count = 0;
				 if (rs.next()) {
					    do {
					    	++count;
					    	String ln = rs.getString("LASTNAME");
							String fn = rs.getString("FIRSTNAME");					
							String tl= rs.getString("TELEPHONE");						
							String ad = rs.getString("ADDRESS");							
							String ct= rs.getString("CITY");
							String st= rs.getString("STATE");	
							String dp= rs.getString("DATEOFAPOINTMENT");	
							String tm= rs.getString("TIMEOFSERVICE");	
							
							/*System.out.println("LASTNAME " + ln);
			   				System.out.println("FIRSTNAME " + fn);
			   				System.out.println("TELEPHONE " + tl);
			   				System.out.println("ADDRESS " + ad);
			   				System.out.println("CITY " + ct);
			   				System.out.println("STATE " + st); 
			   				System.out.println("DATE OF APOINTMENT " + dp);  
						*/
							//String output = "User #%d: %s | %s | %s | %s | %s | %s | %s | %s";
						   // System.out.println(String.format(output, ++count, ln, fn, tl, ad, ct, st, dp, tm));
						      System.out.println("count "+count);
							 
						    jsonObj.put("LASTNAME", ln);
							jsonObj.put("FIRSTNAME", fn);
							jsonObj.put("TELEPHONE" , tl);
							jsonObj.put("ADDRESS", ad);
							jsonObj.put("CITY", ct);
							jsonObj.put("STATE", st); 
							jsonObj.put("DATEOFAPOINTMENT", dp);  
							jsonObj.put("TIMEOFSERVICE", tm);  
							
							///System.out.println(jsonObj); 
			 				
					    } while (rs.next());
					    rs.close();
					} else {
						jsonObj = new JSONObject();
						jsonObj.put("totalRows", 0);
					}		
				 rs.close();
			}
		
			catch(SQLException esqle)
			{
				jsonObj = new JSONObject();
				jsonObj.put("totalRows", 0);
			}
			catch(Exception exp)
			{
				jsonObj = new JSONObject();
				jsonObj.put("totalRows", 0);				
				
			}
			finally{
			try{
		        
		        if (!dbConnection.isClosed()){                                
		        	dbConnection.close();                           
		        }
		        
		        }catch(SQLException esqle){
		               System.out.println("In " + esqle.getMessage());                            
		        }
			}
		return jsonObj; 	           
		}       
		  

	 
}


			
