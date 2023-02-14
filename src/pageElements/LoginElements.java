package pageElements;

import org.openqa.selenium.By;
import org.testng.Assert;
import driverFactory.DriverManager;
import resouceUtils.Constants;

public class LoginElements extends DriverManager{

	public static void LogonValidation() throws InterruptedException{
		driver.get("http://localhost:9090/the_NoXml_servlet3/JSPs/Applogin.html"); 
		driver.findElement(By.id("getlogin")).click();
		Assert.assertTrue(driver.getPageSource().contains("Please enter your credentials"));
	    driver.findElement(By.id("user")).clear();
	    driver.findElement(By.id("user")).sendKeys("invalid user");
	    driver.findElement(By.id("getlogin")).click();
	    Assert.assertTrue(driver.getPageSource().contains("Please enter Password"));
	   	 
		  driver.findElement(By.id("pass")).clear();
		  driver.findElement(By.id("pass")).sendKeys("Passwordf^554$jjjuu");
		  driver.findElement(By.id("getlogin")).click();
		 
     }
	
	
	
	
	public static void Login(){
		driver.get("http://localhost:9090/the_NoXml_servlet3/JSPs/Applogin.html");
		//String passwordToHash = "e55eccdd7a90af1aa5a563deda77e2448c06e2ec";
	      String salt = Constants.salt_string;
	      String Raw_PawordToHash = Constants.passwordToHash;
	      
	      /*String securePassword = resouces.SecureHassPass.get_SHA_1_SecurePassword(Raw_PawordToHash, salt);
	      System.out.println("SHA-1: " +securePassword);
	      String securePassword1 = resouces.SecureHassPass.get_SHA_256_SecurePassword(Raw_PawordToHash, salt);
	      System.out.println("SHA-256 " +securePassword1);
	      String securePassword2 = resouces.SecureHassPass.get_SHA_384_SecurePassword(Raw_PawordToHash, salt);	      
	      System.out.println("SHA-284: " +securePassword2);*/
	      String securePassword3 = resouces.SecureHassPass.get_SHA_512_SecurePassword(Raw_PawordToHash, salt);
	      //System.out.println("SHA-512: " +securePassword3);
	      		      
	      //System.out.println("password to hash: " +Raw_PawordToHash);
	    
		    driver.findElement(By.id("user")).clear();
			driver.findElement(By.id("pass")).clear();
		    driver.findElement(By.id("user")).sendKeys(Constants.username);	
		    driver.findElement(By.id("pass")).clear();
		    driver.findElement(By.id("pass")).sendKeys(securePassword3);
		    driver.findElement(By.id("getlogin")).click();
		    
     }
	
	public static void logout(){
		driver.findElement(By.id("btnLogout")).click();	
		
     }
	
	
	
}
