package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import driverFactory.DriverManager;
import pageElements.LoginElements;

public class Testlogin extends DriverManager{
    
		  @Test (enabled = true, description="When: Navigate to the Login URL, Then: I get the the login page", priority = 2)
		  public void Step1() throws Exception {
		  LoginElements.LogonValidation(); 
		  Assert.assertTrue(driver.getPageSource().contains("Important Information"));
		   // assertTrue(findElement(By.id("login")).getText().equals("");
		   //assertEquals("Help Document | System Administration" , driver.getTitle());
		  } 
		  
	 	   @Test (enabled = true, description="Given: Login with credentials. When: I enter a valid user name, And: a valid password, Then: Login success, Then I get to BSA page", priority = 3)
		  public void Step2() throws Exception {			  
		  LoginElements.Login();
		   } 
		  
		  @Test (enabled = true, dependsOnMethods="Step2", description="Given: Logout. When: I click logout Button, Then: get logged out, And: I'm taken to the login page", priority = 4)
		  public void Step3() throws Exception {
			  LoginElements.logout();
		   }
			
}
