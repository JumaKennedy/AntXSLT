package listeners;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import driverFactory.DriverManager;
import resouceUtils.BaseUtils;


public class ConsolReportListener extends TestListenerAdapter {

	
	 //private int m_count = 0;
	 static Calendar now = Calendar.getInstance();
	 static int year = now.get(Calendar.YEAR);
	 static int month = (now.get(Calendar.MONTH) + 1);
	 static int day = now.get(Calendar.DATE);
	 private static String BaseDir = System.getProperty("user.dir").toString();
	 static String OracleHome = System.getenv("Oracle_Home");
	 static String javaHome = System.getenv("java_home").toString();
   
	 public void onStart(ITestContext context) {
	    	//PropertyConfigurator.configure(log4jConfigFile);
	    	/*try {
				updateProps();
			} catch (Exception e) {			
				e.printStackTrace();
			}*/
	    //Reporter.log("\n"+resouceUtils.Constants.partition2);	
		
	    String startime = resouceUtils.Constants.getCurrentTime();
	    Reporter.log("\n - Test SUIT - "+ context.getName()+ " Started at: "+startime+" \n ");
	    
		}
	 
		    
	    @Override
	    public void onTestStart(ITestResult tr) {
	    	
	    	long start = DriverManager.getStartime(tr);		    		
	    	//Reporter.log("\n"+resouceUtils.Constants.partition);
	    	Reporter.log("\n - Starting Test: "+ tr.getName()+ " - Start time : "+  DateFormat.getDateTimeInstance().format(new Date(start)));
		    Reporter.log("\n"+resouceUtils.Constants.partition); 
	    	 
	    }
	 
	   public void updateProps() throws Exception 
	    {   
	        FileOutputStream out = new FileOutputStream(""+BaseDir+"/testDb.properties");
	        FileInputStream in = new FileInputStream(""+BaseDir+"/testDb.properties");
	        	        
	        Properties props = new Properties();
	        props.load(in);
	        in.close();	        
	        
	        props.setProperty("workspaceUrl", BaseDir);
	        props.setProperty("Oracle", OracleHome);	
	        props.setProperty("Java_Home", javaHome);	    
	        props.setProperty("path", "file///" +BaseDir);
	        
	        //workspaceUrl=file:///C:/ws/DataDrivenTesting
	       	        
	        props.store(out, null);
	        out.close();
	    } 
	   
	   @Override
	    public void onTestSuccess(ITestResult tr) {
		   String descrip = tr.getMethod().getDescription();
		   int priority = tr.getMethod().getPriority();
		   DateFormat dateFormat = new SimpleDateFormat("hh_mm_ssaa");
		   String methodName = tr.getName();
		   String className = tr.getMethod().getTestClass().getName();
		   String destDir = ""+BaseDir+"\\ScreenShots\\"+month+"-"+day+"-"+year+"\\"+className+"\\"+methodName+"";				
		   String destFile = dateFormat.format(new Date()) + ".png";
		   
		   try { 
			   File dir = new File(destDir);					
			   if(!dir.exists()){
			   new File(destDir).mkdirs();
			   }
				// call screenshot method
			   //BaseUtils.Robotic_shot(DriverFactory.DriverManager.driver, ""+(destDir + "\\" + destFile)+"") ;
			   BaseUtils.takeSnapShot(driverFactory.DriverManager.driver, ""+(destDir + "\\" + destFile)+"") ;
			   Reporter.log("\n - Screenshot taken saved at " + destFile);
			   } catch (Exception e) {					
					e.printStackTrace();
			}	
		   
		   Reporter.log("\n - Test Name :" +tr.getName()+ " - Test Description: "+ descrip+ "\n - Test Priority: " +priority);
		   printTestResults(tr);
			
	    }
	 
	   
	   @Override
	    public void onTestFailure(ITestResult tr) {
			   
		   	   String descrip = tr.getMethod().getDescription();
			   int priority = tr.getMethod().getPriority();
			   DateFormat dateFormat = new SimpleDateFormat("hh_mm_ssaa");
			   String methodName = tr.getName();
			   String className = tr.getMethod().getTestClass().getName();
			   String destDir = ""+BaseDir+"\\ScreenShots\\"+month+"-"+day+"-"+year+"\\"+className+"\\"+methodName+"";				
			   String destFile = dateFormat.format(new Date()) + "Failed_" + ".png";
			   
		   try {			   
			    File dir = new File(destDir);
			    
				if(!dir.exists()){
					new File(destDir).mkdirs();
				}
			   BaseUtils.Robotic_shot(driverFactory.DriverManager.driver, ""+(destDir + "\\" + destFile)+"") ;
			   //BaseUtils.takeSnapShot(DriverFactory.DriverManager.driver, ""+(destDir + "\\" + destFile)+"");
			   Reporter.log("\n - Screenshot taken saved at " + destFile);
			   } catch (Exception e) {					
					e.printStackTrace();
			}
		   
		   Reporter.log("\n - Test Name :" +tr.getName()+ " - Test Description: "+ descrip+ "\n - Test Priority: " +priority);
		   printTestResults(tr);
		   
	    }
		 	 
	 
    @Override
    public void onTestSkipped(ITestResult tr) {    	
    	 Reporter.log("\n - Test SKIP");
    	 printTestResults(tr);        
    }
	 
  
	
    //Called when the test in xml suite finishes
    @Override
    public void onFinish(ITestContext context) {
    	
    	Reporter.log(" - Test Suit - "+ context.getName()+ " Completed date: "+context.getStartDate());
        Reporter.log("\n"+resouceUtils.Constants.partition1, true);
       
    }
	  
    @Override   
     public void onTestFailedButWithinSuccessPercentage(ITestResult tr) {   
    	Reporter.log(" - test failed but within success % " + getTestMethodName(tr));
  
        }
    
    
    private void printTestResults(ITestResult tr) {

		//Reporter.log("\n - This Method resides in class:- " + tr.getTestClass().getName(), true);
		String params = "";
		
		if (tr.getParameters().length != 0) {

			for (Object parameter : tr.getParameters()) {
				
				params += parameter.toString() + " \n";

			}

		Reporter.log("\n - Test Method " + getTestMethodName(tr) + " had the following parameters : \n" + params, true);

		}

		String status = null;

		switch (tr.getStatus()) {

		case ITestResult.SUCCESS:

			status = "Pass";

			break;

		case ITestResult.FAILURE:

			status = "Failed";

			break;

		case ITestResult.SKIP:

			status = "Skipped";

		}

		Reporter.log("\n - " +getTestMethodName(tr) +" Complete Test Status: - " + status, true);

	}
    
		
    public static String getTestMethodName(ITestResult tr) {
    return tr.getMethod().getConstructorOrMethod().getName();
    	
    }

    
    /*private void log(String string) {
        System.out.print(string);
        if (++m_count % 40 == 0) {
	    log("");
        }
    }*/

}

