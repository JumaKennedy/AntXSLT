package testCases;
		
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import driverFactory.DriverManager;
import pageElements.LoginElements;
import resouceUtils.DataProviderClass;
import resouceUtils.ExcelUtils;

public class UsingDataProviders extends DriverManager{
		
	  public static boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer(); 
	
	  @Test (enabled = false, description="Given: Invalid credentials logon. When: I enter invalid login credentials, And: click login button, Then: Login fail with error.", priority = 2)
	     public void Step0() throws Exception {		  
		  driver.get("http://localhost:8070/the_NoXml_servlet3/JSPs/Applogin.html");
		  LoginElements.LogonValidation();
		  Assert.assertTrue(driver.getPageSource().contains("login fail: Username/Password combination not found"));
	      }
				
	  @Test (enabled = false, dependsOnMethods ="Step0", description="Given: Valid logon Credentials. When: I enter valid login credentials, And: click login button, Then: Login success", priority = 3)
	     public void Step1() throws Exception {		  
		  driver.get("http://localhost:8070/the_NoXml_servlet3/pages/Applogin.html");
		  LoginElements.Login();
		 
	      }	     
			     

	  @Parameters({"ssn", "dl", "Id", "passport"})		
	     @Test (enabled = true,  description="Given: Lookup info. When: I enter valid data into Search fields, And: click look up, Then: I get Fields Populated",priority = 4)
	     public void Step2(String ssn,String dl, String Id,String pass) throws Exception {
		  driver.get("http://localhost:8070/ReenHairBotique/pages/CrudeForm.jsp");			  
		  driver.findElement(By.id("clear")).click();
			driver.findElement(By.id("Cust_soc_sec_no_Edit")).clear();
			driver.findElement(By.id("Cust_soc_sec_no_Edit")).sendKeys(ssn);
			driver.findElement(By.id("Driver_License")).clear();
			driver.findElement(By.id("Driver_License")).sendKeys(dl);
			driver.findElement(By.id("Cust_alien_reg_no_Edit")).clear();
			driver.findElement(By.id("Cust_alien_reg_no_Edit")).sendKeys(Id);
			driver.findElement(By.id("Cust_passport_Edits")).clear();
			driver.findElement(By.id("Cust_passport_Edits")).sendKeys(pass);
			
			driver.findElement(By.id("getData")).click();
			Thread.sleep(300);
			
			closeAlertAndGetItsText();	       
	       } 
	     
	  @Test (enabled = true,  dependsOnMethods ="Step2", dataProvider = "supplydata", dataProviderClass = ExcelUtils.class, description="Given: Error Validation. When: I fill in the BSA lookup fields with bad data, And; click lookup button, Then: I get error messages",priority=5)
	     public void Step3(String badata, String expected) throws Exception { 		
		  	
		    driver.findElement(By.id("Cust_last_name_Edit")).clear();
		    driver.findElement(By.id("Cust_last_name_Edit")).sendKeys(badata);
		    driver.findElement(By.id("add")).click();
		    Thread.sleep(30);
			
		    assertEquals("The Last Name field can only contain alpha-numeric characters and spaces. Please correct your entry no", closeAlertAndGetItsText());
		    
		    driver.findElement(By.id("Cust_last_name_Edit")).clear();
		    driver.findElement(By.id("Cust_last_name_Edit")).sendKeys(expected);
		    driver.findElement(By.id("add")).click();
		    Thread.sleep(30);
			
		    assertEquals("The First Name field can only contain alpha-numeric characters and spaces. Please correct your entry.", closeAlertAndGetItsText());
		    
		    driver.findElement(By.id("Cust_first_name_Edit")).clear();
		    driver.findElement(By.id("Cust_first_name_Edit")).sendKeys(badata);
		    driver.findElement(By.id("add")).click();
		    Thread.sleep(30);
			
		    assertEquals("The First Name field can only contain alpha-numeric characters and spaces. Please correct your entry.", closeAlertAndGetItsText());
		    
		    driver.findElement(By.id("Cust_first_name_Edit")).clear();
		    driver.findElement(By.id("Cust_first_name_Edit")).sendKeys(expected);
		    driver.findElement(By.id("add")).click();
		    Thread.sleep(30);
			
		    assertEquals("The Address field can not be empty!", closeAlertAndGetItsText());
		    
		    driver.findElement(By.id("Cust_address_one_Edit")).clear();
		    driver.findElement(By.id("Cust_address_one_Edit")).sendKeys(badata);
		    driver.findElement(By.id("add")).click();
		    Thread.sleep(30);
			
		    assertEquals("The Address field can only contain alpha-numeric characters and spaces. Please correct your entry.", closeAlertAndGetItsText());
		    
		    driver.findElement(By.id("Cust_address_one_Edit")).clear();
		    driver.findElement(By.id("Cust_address_one_Edit")).sendKeys(expected);
		    driver.findElement(By.id("add")).click();
		    Thread.sleep(30);
			
		    assertEquals("City field can not be empty!", closeAlertAndGetItsText());
		    
		    driver.findElement(By.id("Cust_city_Edit")).clear();
		    driver.findElement(By.id("Cust_city_Edit")).sendKeys(badata);
		    driver.findElement(By.id("add")).click();
		    Thread.sleep(30);
			
		    assertEquals("The City field can only contain alphabetic characters. Please correct your entry.", closeAlertAndGetItsText());
		    
		    driver.findElement(By.id("Cust_city_Edit")).clear();
		    driver.findElement(By.id("Cust_city_Edit")).sendKeys(expected);
		    driver.findElement(By.id("add")).click();
		    Thread.sleep(30);
			
			//closeAlertAndGetItsText();
			assertEquals("The State Code is invalid. Please correct your entry.", closeAlertAndGetItsText());
			
		    driver.findElement(By.id("Cust_state_Edit")).clear();
		    driver.findElement(By.id("Cust_state_Edit")).sendKeys("99");
		    driver.findElement(By.id("add")).click();
		    Thread.sleep(30);
			
			//closeAlertAndGetItsText();
			assertEquals("The State Code is invalid. Please correct your entry.", closeAlertAndGetItsText());
		    
		    		    
		    driver.findElement(By.id("Cust_state_Edit")).clear();
		    driver.findElement(By.id("Cust_state_Edit")).sendKeys("tn");
		    driver.findElement(By.id("add")).click();
		    Thread.sleep(30);
			
		    assertEquals("The Phone Number field should only contain seven or ten digits(0-9). Please correct your entry.", closeAlertAndGetItsText());
		    
		    driver.findElement(By.id("Cust_phone_Edit")).clear();
		    driver.findElement(By.id("Cust_phone_Edit")).sendKeys("90178");
		    driver.findElement(By.id("add")).click();
		    Thread.sleep(30);
			
		    assertEquals("The Phone Number field should only contain seven or ten digits(0-9). Please correct your entry.", closeAlertAndGetItsText());
		    
		    driver.findElement(By.id("Cust_phone_Edit")).clear();
		    driver.findElement(By.id("Cust_phone_Edit")).sendKeys("9017824555");
		    driver.findElement(By.id("add")).click();
		    Thread.sleep(30);
			
		    assertEquals("The License State Code is invalid. Please correct your entry.", closeAlertAndGetItsText());
		    
		    driver.findElement(By.id("Cust_driver_lic_Edit")).clear();
		    driver.findElement(By.id("Cust_driver_lic_Edit")).sendKeys("#5");
		    driver.findElement(By.id("add")).click();
		    Thread.sleep(30);
			
		    assertEquals("The License State Code is invalid. Please correct your entry.", closeAlertAndGetItsText());
		    
		    driver.findElement(By.id("Cust_driver_lic_Edit")).clear();
		    driver.findElement(By.id("Cust_driver_lic_Edit")).sendKeys("tx");
		    driver.findElement(By.id("add")).click();
		    Thread.sleep(30);
			
		    assertEquals("The Occupation field can not contain alpha-numeric characters and spaces. Please correct your entry.", closeAlertAndGetItsText());
		    
		    driver.findElement(By.id("Cust_occupation_Edit")).clear();
		    driver.findElement(By.id("Cust_occupation_Edit")).sendKeys(badata);
		    driver.findElement(By.id("add")).click();
		    Thread.sleep(30);
			
		    assertEquals("The Occupation field can not contain alpha-numeric characters and spaces. Please correct your entry.", closeAlertAndGetItsText());
		    
		    driver.findElement(By.id("Cust_occupation_Edit")).clear();
		    driver.findElement(By.id("Cust_occupation_Edit")).sendKeys(expected);
		    driver.findElement(By.id("add")).click();
		    Thread.sleep(30);
			
		    assertEquals("The Military ID Number field must contain\nat least four alpha-numeric characters.\nPlease correct your entry.", closeAlertAndGetItsText());
		    
		    driver.findElement(By.id("Cust_military_id_Edit")).clear();
		    driver.findElement(By.id("Cust_military_id_Edit")).sendKeys(badata);
		    driver.findElement(By.id("add")).click();
		    Thread.sleep(30);
			
		    assertEquals("The Military ID Number field must contain\nat least four alpha-numeric characters.\nPlease correct your entry.", closeAlertAndGetItsText());
		    
		    driver.findElement(By.id("Cust_military_id_Edit")).clear();
		    driver.findElement(By.id("Cust_military_id_Edit")).sendKeys(expected);
		    driver.findElement(By.id("add")).click();
		    Thread.sleep(30);
			
		    assertEquals("The Other ID Number field can only contain alpha characters.\nPlease correct your entry", closeAlertAndGetItsText());
		    
		    driver.findElement(By.id("Cust_other_id_Edit")).clear();
		    driver.findElement(By.id("Cust_other_id_Edit")).sendKeys(badata);
		    driver.findElement(By.id("add")).click();
		    Thread.sleep(30);
			
		    assertEquals("The Other ID Number field can only contain alpha characters.\nPlease correct your entry", closeAlertAndGetItsText());
		    
		    driver.findElement(By.id("Cust_other_id_Edit")).clear();
		    driver.findElement(By.id("Cust_other_id_Edit")).sendKeys(expected);
		    driver.findElement(By.id("add")).click();
		    Thread.sleep(30);
			
		    assertEquals("The Other ID Description field can not contain spaces only.\nPlease correct your entry.", closeAlertAndGetItsText());
		    
		    driver.findElement(By.id("Cust_other_desc_Edit")).clear();
		    driver.findElement(By.id("Cust_other_desc_Edit")).sendKeys(badata);
		    driver.findElement(By.id("add")).click();
		    Thread.sleep(30);
			
		    assertEquals("The Other ID Description field can not contain spaces only.\nPlease correct your entry.", closeAlertAndGetItsText());
		    
		    driver.findElement(By.id("Cust_other_desc_Edit")).clear();
		    driver.findElement(By.id("Cust_other_desc_Edit")).sendKeys(expected);
		    driver.findElement(By.id("add")).click();
		    Thread.sleep(30);
			
		    assertEquals("The SSN field can only contain nine digits(0-9). Please correct your entry.", closeAlertAndGetItsText());	  
		    driver.findElement(By.id("clear")).click();		    
			    
	       }
	     
	     @Test (enabled = true,  dependsOnMethods ="Step2", dataProvider = "AlphaNumericDataEntry", dataProviderClass = ExcelUtils.class, description="Given: Inserting data. When: I fill in customer information, And: click Add button, Then: I get the Infomation added to the database", priority = 6)
	     public void Step4(String Lname,String Fname, String Adress,String City, String state, String Lstste, String occu, String milID,String othID, String TpofID) throws Exception {
			    
	    	    driver.get("http://localhost:8070/ReenHairBotique/pages/CrudeForm.jsp");	
		        driver.findElement(By.id("clear")).click();
		   		driver.findElement(By.id("Cust_last_name_Edit")).clear();
		   		driver.findElement(By.id("Cust_last_name_Edit")).sendKeys(Lname);
		   		driver.findElement(By.id("Cust_first_name_Edit")).clear();
		   		driver.findElement(By.id("Cust_first_name_Edit")).sendKeys(Fname);
		   		driver.findElement(By.id("Cust_address_one_Edit")).clear();
		   		driver.findElement(By.id("Cust_address_one_Edit")).sendKeys(Adress);
		   		driver.findElement(By.id("Cust_city_Edit")).clear();
		   		driver.findElement(By.id("Cust_city_Edit")).sendKeys(City);
		   		driver.findElement(By.id("Cust_state_Edit")).clear();
		   		driver.findElement(By.id("Cust_state_Edit")).sendKeys(state);
		   		
		   		driver.findElement(By.cssSelector("img.ui-datepicker-trigger")).click();
		   	    driver.findElement(By.linkText("23")).click();
		   	    
		   		driver.findElement(By.id("Cust_phone_Edit")).clear();
		   		driver.findElement(By.id("Cust_phone_Edit")).sendKeys("9012548782");
		   		driver.findElement(By.id("Cust_driver_lic_Edit")).clear();
		   		driver.findElement(By.id("Cust_driver_lic_Edit")).sendKeys(Lstste);
		   		driver.findElement(By.id("Cust_occupation_Edit")).clear();
		   		driver.findElement(By.id("Cust_occupation_Edit")).sendKeys(occu);
		   		driver.findElement(By.id("Cust_military_id_Edit")).clear();
		   		driver.findElement(By.id("Cust_military_id_Edit")).sendKeys(milID);
		   		driver.findElement(By.id("Cust_other_id_Edit")).clear();
		   		driver.findElement(By.id("Cust_other_id_Edit")).sendKeys(othID);
		   		driver.findElement(By.id("Cust_other_desc_Edit")).clear();
		   		driver.findElement(By.id("Cust_other_desc_Edit")).sendKeys(TpofID);
		   		driver.findElement(By.id("Cust_soc_sec_no_Edit")).clear();
		   		driver.findElement(By.id("Cust_soc_sec_no_Edit")).sendKeys("457887755");
		   		driver.findElement(By.id("Driver_License")).clear();
		   		driver.findElement(By.id("Driver_License")).sendKeys("3655224");
		   		driver.findElement(By.id("Cust_alien_reg_no_Edit")).clear();
		   		driver.findElement(By.id("Cust_alien_reg_no_Edit")).sendKeys("365248");
		   		driver.findElement(By.id("Cust_passport_Edits")).clear();
		   		driver.findElement(By.id("Cust_passport_Edits")).sendKeys("587455");
		   		driver.findElement(By.id("add")).click();
		   		Thread.sleep(30);
		   		//assertEquals("Error in inserting records to database!", closeAlertAndGetItsText());	
			   		
			   	 
	          } 
	     
	     @Parameters({"zip", "ssn", "dl", "alien", "pass"})		
	     @Test (enabled = false, dependsOnMethods ="Step2", description="Given: General lookups. When: I enter valid data into Search fields, And: click lookup button, Then: I get Fields Populated",priority = 7)
	     public void Step5(String zip, String ssn,String dl, String ali,String pass) throws Exception {
	    	     driver.get("http://localhost:8070/ReenHairBotique/pages/CrudeForm.jsp");			
	    	 	//System.out.println("data from XML ## "+ssn+"\n"+ dl+"\n"+ ali+"\n"+ pass);
	    		 driver.get("http://localhost:8070/ReenHairBotique/pages/CrudeForm.jsp");		
	    		driver.findElement(By.id("Cust_zip_Edit")).clear();
				driver.findElement(By.id("Cust_zip_Edit")).sendKeys(zip);
		   		driver.findElement(By.id("Cust_soc_sec_no_Edit")).sendKeys(ssn);
		   		driver.findElement(By.id("Driver_License")).clear();
		   		driver.findElement(By.id("Driver_License")).sendKeys(dl);
		   		driver.findElement(By.id("Cust_alien_reg_no_Edit")).clear();
		   		driver.findElement(By.id("Cust_alien_reg_no_Edit")).sendKeys(ali);
		   		driver.findElement(By.id("Cust_passport_Edits")).clear();
		   		driver.findElement(By.id("Cust_passport_Edits")).sendKeys(pass);			   		
		   		driver.findElement(By.id("getData")).click();			   		
		   		closeAlertAndGetItsText();
		   		    	        
	       } 
	     
	     @Test (enabled = true,  dataProvider = "alphanumericwith4digits", dataProviderClass = DataProviderClass.class, description="Given: validate User inputs. When: I enter invalid data in look up field, And: click lookup buttton, Then: I get an Error message" , priority = 8)
	     public void Step6(String badata, String expected) throws Exception {	    	

	             driver.get("http://localhost:8070/ReenHairBotique/pages/CrudeForm.jsp");			            
	            driver.findElement(By.id("Cust_zip_Edit")).clear();
				driver.findElement(By.id("Cust_zip_Edit")).sendKeys(badata);
				driver.findElement(By.id("getData")).click();
				closeAlertAndGetItsText();
				
	            driver.findElement(By.id("Cust_soc_sec_no_Edit")).clear();
		   		driver.findElement(By.id("Cust_soc_sec_no_Edit")).sendKeys(badata);
		   		driver.findElement(By.id("getData")).click();
		   		Thread.sleep(30);
		   		//assertEquals("The SSN field can only contain nine digits(0-9). Please correct your entry.", closeAlertAndGetItsText());
		   		
		   		closeAlertAndGetItsText();
		   		
		   		driver.findElement(By.id("Driver_License")).clear();
		   		driver.findElement(By.id("Driver_License")).sendKeys(badata);
		   		driver.findElement(By.id("getData")).click();
		   		Thread.sleep(300);				   		
		   		closeAlertAndGetItsText();
		   		//assertEquals("The SSN field can only contain nine digits(0-9). Please correct your entry.", closeAlertAndGetItsText());
		   		
		   		driver.findElement(By.id("Cust_alien_reg_no_Edit")).clear();
		   	    driver.findElement(By.id("Cust_alien_reg_no_Edit")).sendKeys(badata);
		   	    driver.findElement(By.id("getData")).click();
		   	    Thread.sleep(30);				   		
		   		closeAlertAndGetItsText();
		   	    //assertEquals("The Alien Registration Number field must contain\nat least four alpha-numeric characters.\nPlease correct your entry", closeAlertAndGetItsText());
		   	    
		   	    driver.findElement(By.id("Cust_passport_Edits")).clear();
		   	    driver.findElement(By.id("Cust_passport_Edits")).sendKeys(badata);
		   	    driver.findElement(By.id("getData")).click();
		   	    Thread.sleep(30);				   		
		   		closeAlertAndGetItsText();			    
		   	    //assertEquals("The Passport Number field must contain\nat least four alpha-numeric characters.\nPlease correct your entry", closeAlertAndGetItsText());
		   	    	   	   	    
				   	 
	          } 
	     
	     @Test (enabled = true,  description="Given: Validate buttons. When: I click submit buttons without inputs. Then: I get error messege. And: the curser focus on input required field. " , priority = 9)
		  public void Step7() throws Exception {
	    	    driver.get("http://localhost:8070/ReenHairBotique/pages/CrudeForm.jsp");		
				driver.findElement(By.id("clear")).click();
				driver.findElement(By.id("getData")).click();
			    Thread.sleep(300);					   
			    assertEquals("Please enter Zip code, SSN, Driver Lisence, Alien or passport to be looked up or click discard button and go to the next 8105A image.", closeAlertAndGetItsText());
				
				
				driver.findElement(By.id("add")).click();
				Thread.sleep(30);						
				assertEquals("Customer Last Name or Customer SSN is required. If no Customer Last Name\nor Customer SSN is available, click on the \"Discard\" button and go to the next 8105A image.", closeAlertAndGetItsText());
				
				driver.findElement(By.id("clear")).click();   
				driver.findElement(By.id("dis")).click();
				Thread.sleep(30);						
				assertEquals("You must check at least one Discard Reason", closeAlertAndGetItsText());
				driver.findElement(By.id("cb1")).click();
				driver.findElement(By.id("cb2")).click();
				driver.findElement(By.id("cb3")).click();
				driver.findElement(By.id("dis")).click();
				Thread.sleep(300);
				assertEquals("Error in discarding item, discard not recorded in database!", closeAlertAndGetItsText());
						
	    	          
						 					
		   } 
	     
	     @Test (enabled = false,  dataProvider = "ZipCode", dataProviderClass = DataProviderClass.class, description="Given: Zip code 5 digit validation. When: I enter invalid data, And: click look up button, Then: I get Error 'Zip code field should only contain 5 digits. Please correct your entry'", priority = 9)
		  public void Step8(String badata, String expected) throws Exception { 
	    	     driver.get("http://localhost:8070/ReenHairBotique/pages/CrudeForm.jsp");		
				driver.findElement(By.id("Cust_zip_Edit")).clear();
				driver.findElement(By.id("Cust_zip_Edit")).sendKeys(badata);
				driver.findElement(By.id("getData")).click();
				Thread.sleep(30);				
				assertEquals("Zip code field should only contain 5 digits. Please correct your entry.", closeAlertAndGetItsText());						
				driver.findElement(By.id("Cust_zip_Edit")).clear();
				driver.findElement(By.id("Cust_zip_Edit")).sendKeys(expected);
				driver.findElement(By.id("getData")).click();
				closeAlertAndGetItsText();
	    	      
		   } 
	     
	     @Test (enabled = false,  description="Given: Logout. When: I click logout button, Then: I get logged out, And: I'm redirected to login page", priority = 10)
	     public void Step9() throws Exception {
	    	 LoginElements.logout();
	    	 Assert.assertTrue(driver.getPageSource().contains("Important Information"));
				   	
	       }
	     
	    
	     @AfterClass
	      public void verifyStatus(){
	          String verificationErrorString = verificationErrors.toString();
	          if (!"".equals(verificationErrorString)) {
	          fail(verificationErrorString);
	          
	      }			
	      }
	}	
