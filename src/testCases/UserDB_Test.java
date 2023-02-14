package testCases;

import org.json.JSONArray;
import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import dbTest.DataBaseTest;
import driverFactory.DriverManager;

public class UserDB_Test extends DriverManager{

	
	 @Test (enabled = false, invocationCount = 1, threadPoolSize = 1,  description="Given: register user. When: I fill in form fields, And: click submite button, Then: I get data inserted into database", priority = 1)
	  public void Step1() throws Exception {
		
		 driver.get("http://localhost:8070/ReenHairBotique/pages/UserDB.html");
		    
		    driver.findElement(By.id("Lname")).clear();
		    driver.findElement(By.id("Lname")).sendKeys("Juma");
		    driver.findElement(By.id("getData")).click();		    
		    driver.findElement(By.id("res_app")).click();
		    driver.findElement(By.cssSelector("img.ui-datepicker-trigger")).click();
		    driver.findElement(By.linkText("29")).click();
		    new Select(driver.findElement(By.id("tm"))).selectByVisibleText("10:00 AM");
		    driver.findElement(By.id("edit")).click();
		   // Assert.assertEquals(closeAlertAndGetItsText(), "Thanks Juma! you have been resheduled for - 2016-06-29 at 10:00 AM");
		    JQueryAlertHandling();
		    driver.findElement(By.id("delete_entry")).click();
		    driver.findElement(By.id("delete")).click();
		    //Assert.assertEquals(closeAlertAndGetItsText(), "1 Affected, delete successfull for Juma");
		    JQueryAlertHandling();
		    driver.findElement(By.id("getData")).click();
		    //Assert.assertEquals(closeAlertAndGetItsText(), "No Match Found");
		    JQueryAlertHandling();
		    driver.findElement(By.id("Lname")).clear();
		    driver.findElement(By.id("Lname")).sendKeys("Juma");
		    driver.findElement(By.id("getData")).click();
		    //Assert.assertEquals(closeAlertAndGetItsText(), "No Match Found");
		    JQueryAlertHandling();
		    driver.findElement(By.id("mk_app")).click();
		    driver.findElement(By.cssSelector("img.ui-datepicker-trigger")).click();
		    driver.findElement(By.linkText("30")).click();
		    driver.findElement(By.id("savedata")).click();
		    //Assert.assertEquals(closeAlertAndGetItsText(), "Thank you Juma! , you are booked for Appointment on 2016-06-30 at 10:00 AM");
		    JQueryAlertHandling();
		    
				
	 } 
	 
	 @Test (enabled = false, invocationCount = 1, threadPoolSize = 1,  description="Given: Lookup users. When: I fill in form fields, And: click search button, Then: I get fields populated", priority = 2)
	  public void Step2() throws Exception {
		  
		 driver.get("http://localhost:8070/ReenHairBotique/pages/UserDB.html");
		    
		    driver.findElement(By.id("Lname")).clear();
		    driver.findElement(By.id("Lname")).sendKeys("Juma");driver.findElement(By.id("getData")).click();    
		    driver.findElement(By.id("res_app")).click();
		    driver.findElement(By.cssSelector("img.ui-datepicker-trigger")).click();
		    driver.findElement(By.linkText("29")).click();
//		    new Select(driver.findElement(By.id("tm"))).selectByVisibleText("10:00 AM");
//		    driver.findElement(By.id("edit")).click();
//		    Assert.assertEquals(closeAlertAndGetItsText(), "Thanks Juma! you have been resheduled for - 2016-06-29 at 10:00 AM");
//		    driver.findElement(By.id("delete_entry")).click();
//		    driver.findElement(By.id("delete")).click();
//		    Assert.assertEquals(closeAlertAndGetItsText(), "1 Affected, delete successfull for Juma");
//		    driver.findElement(By.id("getData")).click();
//		    Assert.assertEquals(closeAlertAndGetItsText(), "No Match Found");
		    
				
	 } 
	 
	 @Test (enabled = false, invocationCount = 1, threadPoolSize = 1,  description="Given: Lookup users. When: I fill in form fields, And: click search button, Then: I get fields populated", priority = 3)
	  public void Step3() throws Exception {
		  
		 driver.get("http://localhost:8070/ReenHairBotique/pages/UserDB.html");		 
		
		    driver.findElement(By.id("ln")).clear();
		    driver.findElement(By.id("ln")).sendKeys("Juma");
		    driver.findElement(By.id("fn")).clear();
		    driver.findElement(By.id("fn")).sendKeys("Kennedy");
		    driver.findElement(By.id("tl")).clear();
		    driver.findElement(By.id("tl")).sendKeys("9012879728");
		    driver.findElement(By.id("ad")).clear();
		    driver.findElement(By.id("ad")).sendKeys("828 woodruff Dr");
		    driver.findElement(By.id("ct")).clear();
		    driver.findElement(By.id("ct")).sendKeys("Ballwin");
		    driver.findElement(By.id("st")).clear();
		    driver.findElement(By.id("st")).sendKeys("MO");
		    driver.findElement(By.cssSelector("img.ui-datepicker-trigger")).click();
		    driver.findElement(By.linkText("30")).click();
		    new Select(driver.findElement(By.id("tm"))).selectByVisibleText("12:00 PM");
		    driver.findElement(By.id("savedata")).click();
		    //assertEquals(closeAlertAndGetItsText(), "Thank you Juma! , you are booked for Appointment on 2016-06-30 at 12:00 PM");
		    driver.findElement(By.id("res_app")).click();
		    driver.findElement(By.name("action")).click();
		    driver.findElement(By.id("Lname")).clear();
		    driver.findElement(By.id("Lname")).sendKeys("Juma");
		    driver.findElement(By.id("getData")).click();
		    driver.findElement(By.id("delete_entry")).click();
		    driver.findElement(By.id("delete")).click();
		    //assertEquals(closeAlertAndGetItsText(), "1 Affected, delete successfull for Juma");  
				
	 } 
	 
	 @Test (enabled = true,   description="Given: Lookup users. When: I fill in form fields, And: click search button, Then: I get fields populated", priority = 4)
		
	 public void TestDB()throws JSONException{
		 //System.out.println(GetRecords());
		 JSONArray jsonarray = new JSONArray("[" +DataBaseTest.GetRecords().toString()+"]");
		 for (int i = 0; i < jsonarray.length(); i++) {
		     org.json.JSONObject jsonobject = jsonarray.getJSONObject(i);
		     String ln = jsonobject.getString("LASTNAME");
		     String fn = jsonobject.getString("FIRSTNAME");
		     String tl = jsonobject.getString("TELEPHONE");
		     String ad = jsonobject.getString("ADDRESS");
		     String ct = jsonobject.getString("CITY");
		     String st = jsonobject.getString("STATE");
		     String dp = jsonobject.getString("DATEOFAPOINTMENT");
		     String tm = jsonobject.getString("TIMEOFSERVICE");
		     String output = "%s | %s | %s | %s | %s | %s | %s | %s";
			 System.out.println(String.format(output, ln, fn, tl, ad, ct, st, dp, tm));
		     
			 driver.get("http://localhost:8070/ReenHairBotique/pages/UserDB.html");
			    
			 driver.findElement(By.id("ln")).clear();
			    driver.findElement(By.id("ln")).sendKeys(ln);
			    driver.findElement(By.id("fn")).clear();
			    driver.findElement(By.id("fn")).sendKeys(fn);
			    driver.findElement(By.id("tl")).clear();
			    driver.findElement(By.id("tl")).sendKeys(tl);
			    driver.findElement(By.id("ad")).clear();
			    driver.findElement(By.id("ad")).sendKeys(ad);
			    driver.findElement(By.id("ct")).clear();
			    driver.findElement(By.id("ct")).sendKeys(ct);
			    driver.findElement(By.id("st")).clear();
			    driver.findElement(By.id("st")).sendKeys(st);
			    driver.findElement(By.cssSelector("img.ui-datepicker-trigger")).click();
			    driver.findElement(By.linkText("29")).click();
			    new Select(driver.findElement(By.id("tm"))).selectByVisibleText("12:00 PM");
		 } 
	}
	 
	 
	 public void JQueryAlertHandling() {

	        WebDriverWait jQueryWait = new WebDriverWait(driver, 2);
	        try{
	            jQueryWait.until(ExpectedConditions.presenceOfElementLocated(By.id("popup_container")));
	             WebElement popup = driver.findElement(By.id("popup_container"));
	             WebElement message = popup.findElement(By.id("popup_message"));
	             Assert.assertEquals("Alert Message", message.getText());
	             popup.findElement(By.id("popup_ok")).click();  
	             System.out.println("Alert  Present");
	        }
	        catch (Exception e){
	             System.out.println("No alert  Present");
	        }        
	     }
	 
	 
	    
}
