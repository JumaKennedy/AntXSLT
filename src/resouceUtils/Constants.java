package resouceUtils;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Constants {

		public static final String baseUrl = "http://localhost:9090/SimpleRestApp/add8105a/addCUSTinfo.jsp";
		public static final String IEdriver = "C:\\Selenium\\IEdriver\\IEDriverServer.exe";
		public static final String ChromeDriver = "C:\\Selenium\\chromedriver\\chromedriver.exe";
	    
		public static final String Path_TestData = "C://ToolsQA//dat//src//testData//";		
		public static final String File_TestData = "TestData.xlsx";
		public static final String baseUrl2 = "http://localhost:9090/the_NoXml_servlet3/JSPs/Applogin.html";
		public static final String username ="user1";
		//public static final String password = "M1pa55w0rd99@@7";
		public static final String passwordToHash =  "M1password@@";
		
		public static final String s = ("Username/Password combination not found.");
		public static final String endClass =   "============================ END CLASS =================================";
		public static final String partition1 = "========================================================================";
		public static final String partition =  "+-----+-----+-----+----+-----+-----+-----+-----+-----+-----+-----+-----+\n";
        public static final String partition2 = "-----------------------------------------------------------------------";        
        public static final String Servereagnm = "eagnmnss4ae:9443.";
		// jenkins url		
		//public static final String ShotsDir1 = "C:\\Users\\fwdqq0.DEVSUB\\.jenkins\\jobs\\BSA\\workspace\\ScreenShots\\";
		public static final String salt_string = "/the_NoXml_servlet3";
		// local repository 
		//public static final String ShotsDir1 = "C:\\ws\\Ant_webdriver\\ScreenShots\\";
		//public static final String exelReports = "C:\\ws\\Ant_webdriver\\exelReport\\";
		public static final String timenow = getCurrentTime();
		public static final String methodeName = Thread.currentThread().getStackTrace()[2].getMethodName();
		
		public static final String getBaseUrl()  {
			  Path currentRelativePath = Paths.get("");
			  String s = currentRelativePath.toAbsolutePath().toString();	
			  String destDir = s;				  
			  //System.out.println("Base Url " +destDir);
			  return destDir;
			  			  
		  	}
		
		
		public static final String getCurrentTime(){
        DateFormat dateFormat = 
        new SimpleDateFormat("HH:mm:ss:SSS");
        Date dt = new Date();
        return dateFormat.format(dt);    
        }
		
		public static String getMethodName() {
		    return Thread.currentThread().getStackTrace()[2].getMethodName();
		} 
		
		public static final String encript(){
	        
			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				
				e.printStackTrace();
			}
	        md.update(passwordToHash.getBytes());
	 
	        byte byteData[] = md.digest();
	 
	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	         //System.out.println("Digest(in hex format):: " + sb.toString());
	         System.out.println("pass md5 " + sb.toString());
	        }
			
	        return sb.toString();    
	        }
		
		public static String getSecurePass()
		   {
		       String generatedPassword = null;
		       try {
		           MessageDigest md = MessageDigest.getInstance("SHA-1"); 
		           //System.out.println("Raw pass in constant class  " + passwordToHash);
		           byte[] bytes = md.digest(passwordToHash.getBytes());
		           StringBuilder sb = new StringBuilder();
		           for(int i=0; i< bytes.length ;i++)
		           {
		               sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		           }
		           
		           generatedPassword = sb.toString();
		       }
		       catch (NoSuchAlgorithmException e)
		       {
		           e.printStackTrace();
		       }
		       //System.out.println("secure pass in resources.Constants class " + generatedPassword);
		       return generatedPassword.toString();
		   }
		    
		
		public static void HashPSDW()throws Exception{
		
		MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(passwordToHash.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
 
        System.out.println("Digest(in hex format):: " + sb.toString());
		
		/* //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<byteData.length;i++) {
    		String hex=Integer.toHexString(0xff & byteData[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
    	System.out.println("Digest(in hex format):: " + hexString.toString()); */
      }

		public static int getRowUsed() {
			// TODO Auto-generated method stub
			return 0;
		}

		public static String getCellData(int i, int colNum) {
			// TODO Auto-generated method stub
			return null;
		} 
		
		
		
		
		
		
	}	
