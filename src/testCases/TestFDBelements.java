package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import driverFactory.DriverManager;

public class TestFDBelements extends DriverManager {
	
	 @Test (enabled = true, invocationCount = 1, threadPoolSize = 1,  description="Given: Lookup users. When: I fill in form fields, And: click search button, Then: I get FBD contact info", priority = 2)
	  public void Step0() throws Exception {
		  
			//System.out.printf("%n[START] Thread Id : %s is started!", Thread.currentThread().getId());
			driver.get("https://phone.usps.gov/pls/teldirprodnp/td_global_pkg.main");
		    driver.findElement(By.id("city")).clear();
		    driver.findElement(By.id("city")).sendKeys("memphis");
		    new Select(driver.findElement(By.id("state"))).selectByVisibleText("TENNESSEE");
		    driver.findElement(By.id("zip")).clear();
		    driver.findElement(By.id("zip")).sendKeys("38118");
		    driver.findElement(By.name("sbutton1")).click();
			driver.findElement(By.linkText("FREDDIE M. ARNETT")).click();
		    //closeAlertAndGetItsText(); test fail
			//System.out.printf("%n[END] Thread Id : %s", Thread.currentThread().getId());
				
	 } 
	  
	  @Test (enabled = true, dependsOnMethods="Step0", description="Given: Use search tips. When: I click home link, And: click link search tips, Then: I get search tips page.", priority = 3)
	  public void Step1() throws Exception {
			
			//System.out.printf("%n[START] Thread Id : %s is started!", Thread.currentThread().getId());
		    driver.findElement(By.linkText("Home")).click();
		    driver.findElement(By.linkText("Search Tips")).click();		    
			 //System.out.printf("%n[END] Thread Id : %s", Thread.currentThread().getId());
	  }
	  
	  @Test (enabled = true, dependsOnMethods="Step0", description="Given: Log in as admin, When: I enter user name and password, And: click logon button, Then: I get logon on to FDB admin", priority = 4)
	  public void Step2() throws Exception {
			
			//System.out.printf("%n[START] Thread Id : %s is started!", Thread.currentThread().getId());
			driver.findElement(By.linkText("For Administrators")).click();
		    driver.findElement(By.linkText("Login")).click();
		    driver.findElement(By.id("username")).clear();
		    driver.findElement(By.id("username")).sendKeys("fwdddds");
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys("password");
		    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		    driver.findElement(By.linkText("Return to Blue Page")).click();
			//System.out.printf("%n[END] Thread Id : %s", Thread.currentThread().getId());
			
	  }
	  
		
}
