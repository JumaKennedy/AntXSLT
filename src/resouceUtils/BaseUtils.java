
package resouceUtils;
			
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import driverFactory.DriverManager;

public abstract class BaseUtils extends DriverManager {
	

			@SuppressWarnings("unused")
			private static XSSFRow Row;
			
			 static Calendar now = Calendar.getInstance();
	    	 static int year = now.get(Calendar.YEAR);
	    	 static int month = (now.get(Calendar.MONTH) + 1);
	    	 static int day = now.get(Calendar.DATE);	    	 
	    	 static String BaseDir = System.getProperty("user.dir"); 
	    	 
			public static WebDriver driver;
			public static boolean acceptNextAlert = true;
			
			public static void takeSnapShot(WebDriver driver,String fileWithPath) throws Exception{
		        TakesScreenshot scrShot =((TakesScreenshot)driver);	
		        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		        File DestFile=new File(fileWithPath);
		        FileUtils.copyFile(SrcFile, DestFile);		        
		    }
			
			public static void Robotic_shot(WebDriver driver,String fileWithPath) throws Exception{					 
				BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		        ImageIO.write(image, "png", new File(fileWithPath));		        
			}
		  
		    //to call from test class
		    public static void getScreenshot() throws Exception {	
			    Exception e = new Exception();
				e.fillInStackTrace();
				String methodName = e.getStackTrace()[1].getMethodName();
				String className = e.getStackTrace()[1].getClassName() ;	
				DateFormat dateFormat = new SimpleDateFormat("hh-mm-ssaa");
				String destDir = ""+BaseDir+"\\ScreenShots\\"+month+"-"+day+"-"+year+"\\"+className+"\\"+methodName+"";	
				String destFile = dateFormat.format(new Date()) + ".png";				
				
				try { 
					   File dir = new File(destDir);					
					   if(!dir.exists()){
					   new File(destDir).mkdirs();
					   }
					   //call when using robotic screenshot uncomment when using javaScrip shot
					   //Robotic_shot(driverFactory.DriverManager.driver, ""+(destDir + "\\" + destFile)+"") ;
					   //Commented out - call when using javaScript shots
					   takeSnapShot(driverFactory.DriverManager.driver, ""+(destDir + "\\" + destFile)+"") ;
					   Reporter.log("\n - Screenshot taken saved at " + destFile);
					   } catch (Exception e1) {					
							e.printStackTrace();
					}	
			 }
		  
		     public static void readProps()  {
				  //Path currentRelativePath = Paths.get("");
				  //String s = currentRelativePath.toAbsolutePath().toString();	
				  String destDir = ""+BaseDir+"\\src\\db.properties";	
				  File file = new File(destDir);
				  System.out.println("read at " +destDir);
				  
					FileInputStream fileInput = null;
					try {
						fileInput = new FileInputStream(file);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					Properties prop = new Properties();
					
					//load properties file
					try {
						prop.load(fileInput);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
			  	}
			
			
				public static String getPopupMessage() {
				String message = null;
				try {
				Alert alert = driver.switchTo().alert();
				message = alert.getText();
				alert.accept();
				} catch (Exception e) {
				message = null;
				}
				Reporter.log("message "+message, true);
				return message;
				} 
		
		      
			   
			   public static void Check_login() throws Exception{
				if(driver.getPageSource().contains("Username/Password combination not found.")){
			    	throw new Exception("login fail " + resouceUtils.Constants.s);				
				}			
				} 	
		
			  
		
			   public static boolean isTextPresent(String text){
			   try{
		       boolean b = driver.getPageSource().contains(text);
		       return b;
			   }
			   catch(Exception e){
		       return false;
			   }
		       }
			
				
				public static void Delete_class_Pictures() throws IOException {	
					
					Exception e = new Exception();
					e.fillInStackTrace();
					
					String className = e.getStackTrace()[1].getClassName() ;		
					Path currentRelativePath = Paths.get("");
					String s = currentRelativePath.toAbsolutePath().toString();					
					//String destDir = ""+resouceUtils.Constants.ShotsDir1+"\\"+className+"\\"+methodName+"";						
					String destDir = ""+s+"\\ScreenShots\\";	
					System.out.println("Current relative path is ######: " + destDir);				
				    			
					
					Reporter.log("+-+-+Now running test class - " +className + " time now is: " +resouceUtils.Constants.timenow+ "+-+-", true);
					
			        try {		   
						File dir = new File(destDir);
						if(dir.exists()){				
						FileUtils.cleanDirectory(new File(destDir));
						System.out.println("+-+-+Deleted old pictures at - " +destDir+""+className+ " - +-+-\n");
						}
						else if(!dir.exists()){
						new File(destDir).mkdirs();
						}
					} catch (FileNotFoundException e2) {
						e2.printStackTrace();
						throw(e2);				
					}		  
		        } 
				
				
			    
			    public void testAlertOk(){
			    //Now we would click on AlertButton
			    WebElement button = driver.findElement(By.id("AlerButton"));
			    button.click();
				    try {
				    //Now once we hit AlertButton we get the alert
				    Alert alert = driver.switchTo().alert();
				    //Text displayed on Alert using getText() method of Alert class
				    String AlertText = alert.getText();
				    //accept() method of Alert Class is used for ok button
				    alert.accept();
				    //Verify Alert displayed correct message to user
				    Assert.assertEquals("this is alert box",AlertText);
				    } catch (Exception e) {
				    e.printStackTrace();
				    }
			    }
			    
			  //Canceling and fetch pop-up in Selenium-WebDriver
			    public static String cancelPopupMessageBox() {
					String message = null;
					try {
					WebDriverWait wait = new WebDriverWait(driver, 2);
				    wait.until(ExpectedConditions.alertIsPresent());
					Alert alert = driver.switchTo().alert();
					message = alert.getText();
					alert.dismiss();
					
					 Assert.assertEquals("this is alert msg",message);
					} catch (Exception e) {
					message = null;
					}
					return message;
					}
			    
			    // check and wiat and capture web dialogs
				  public void checkAlert() {
					    try {
					        WebDriverWait wait = new WebDriverWait(driver, 2);
					        wait.until(ExpectedConditions.alertIsPresent());
					        Alert alert = driver.switchTo().alert();
					        alert.accept();
					    } catch (Exception e) {
					    	System.out.println("no alert present ");//exception handling
					    }
					}
				  
				  public static String closeAlertAndGetItsText() {
					  try {
					      Alert alert = driver.switchTo().alert();
					      String alertText = alert.getText();
					      if (acceptNextAlert) {
					        alert.accept();
					      } else {
					        alert.dismiss();
					      }
					      return alertText;
					      } finally {
					      acceptNextAlert = true;
					    }
					  }
				  		    
				 
				  
				  public void checkAlert2() {
					   
				  WebDriverWait wait = new WebDriverWait(driver, 300 /*timeout in seconds*/);
				  if(wait.until(ExpectedConditions.alertIsPresent())==null)
				        System.out.println("alert was not present");
				  else
				        System.out.println("alert was present");
				        Assert.assertTrue(true);
				  }
				
}			


			