package testCases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import driverFactory.DriverManager;
import resouceUtils.BaseUtils;
import resouceUtils.DataProviderClass;
import resouceUtils.ExcelUtils;

public class GetExUtils extends DriverManager{
				  
				  
				  String timenow = resouceUtils.Constants.getCurrentTime(); 
				  public static boolean acceptNextAlert = true;
				  private StringBuffer verificationErrors = new StringBuffer();
				  String sTestCaseName;	 
				  private int iTestCaseRow; 
				
				 @Test (enabled = false,  description="log in credentials test'Good login redirect to the bsa page'", priority = 2)
			     public void Step0() throws Exception {
			     	
					  String salt = resouceUtils.Constants.salt_string;
				      String Raw_PawordToHash = resouceUtils.Constants.passwordToHash;				        
				      
				      String securePassword3 = resouces.SecureHassPass.get_SHA_512_SecurePassword(Raw_PawordToHash, salt);
				      System.out.println("SHA-512: " +securePassword3);
				      
				    
				      //System.out.println("password to hash: " +Raw_PawordToHash);
				      //System.out.println("Input from selenium UI automation salted and hashed: " +securePassword3);
				     
						//System.out.println("secure pass in webdriver " + resouceUtils.Constants.getSecurePass());
						driver.get("http://localhost:9090/the_NoXml_servlet3/JSPs/Applogin.html");
					    driver.findElement(By.id("user")).clear();
					    driver.findElement(By.id("user")).sendKeys(resouceUtils.Constants.username);			    
					    driver.findElement(By.id("pass")).clear();	
					    
					    driver.findElement(By.id("pass")).sendKeys(securePassword3);	
					    Thread.sleep(30);
					   BaseUtils.getScreenshot();
					    driver.findElement(By.id("getlogin")).click();
					   //BaseUtils.getScreenshot();
					   // Thread.sleep(300);
					   //closeAlertAndGetItsText();
					    
			       }
			     

	  @Test (enabled = true,  dataProvider = "supplydata", dataProviderClass = DataProviderClass.class, description="zip code field should only contain 5 digits. Please correct your entry",priority=3)
	     public void Step1(String badata, String expected) throws Exception { 
		    driver.get(resouceUtils.Constants.baseUrl);
		    System.out.println("data from excel ###  " +badata+ ""+ expected); 		
		   
		    driver.findElement(By.id("Cust_soc_sec_no_Edit")).clear();
			driver.findElement(By.id("Cust_soc_sec_no_Edit")).sendKeys("415542547");
			driver.findElement(By.id("getData")).click();
			Thread.sleep(300);
			closeAlertAndGetItsText();
			//assertEquals("The Driver license Number field must contain\nat least four alpha-numeric characters.\nPlease correct your entry", closeAlertAndGetItsText();;);
			  
		    BaseUtils.getScreenshot();
		      
		 	  			
		 } 
	  
	  @Test (enabled = false,  dataProvider = "AlphaNumericDataEntry",  description="validation for insert to BSA database", priority = 4)
	     public void Step3(String Lname,String Fname, String Adress,String City, String state, String Lstste, String occu, String milID,String othID, String TpofID) throws Exception {
			    
			        Reporter.log("data supplied: "+Lname+ "\t"+ Fname+ "\t"+Adress+"\t"+ City+"\t"+ state+"\t"+""+ Lstste+"\t"+ ""+occu+"\t"+ milID+"\t"+ othID+"\t"+ TpofID, true); 		
			        driver.get(resouceUtils.Constants.baseUrl);
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
			   		driver.findElement(By.id("add")).click();
			   		Thread.sleep(300);
			   		assertEquals("The Other ID Number field can only contain alpha characters.\nPlease correct your entry", closeAlertAndGetItsText());
			   		driver.findElement(By.id("Cust_other_id_Edit")).clear();
			   		driver.findElement(By.id("Cust_other_id_Edit")).sendKeys(othID);
			   		driver.findElement(By.id("add")).click();
			   		Thread.sleep(3000);
			   		assertEquals("The Other ID Description field can not contain spaces only.\nPlease correct your entry.", closeAlertAndGetItsText());
			   		driver.findElement(By.id("Cust_other_desc_Edit")).clear();
			   		driver.findElement(By.id("Cust_other_desc_Edit")).sendKeys(TpofID);
			   		driver.findElement(By.id("add")).click();
			   		Thread.sleep(300);
			   		assertEquals("The SSN field can only contain nine digits(0-9). Please correct your entry.", closeAlertAndGetItsText());
			   		driver.findElement(By.id("Cust_soc_sec_no_Edit")).clear();
			   		driver.findElement(By.id("Cust_soc_sec_no_Edit")).sendKeys("457887755");
			   		driver.findElement(By.id("add")).click();
			   		Thread.sleep(300);
			   		assertEquals("Invalid Driver License Number, Please correct your entry..", closeAlertAndGetItsText());
			   		driver.findElement(By.id("Driver_License")).clear();
			   		driver.findElement(By.id("Driver_License")).sendKeys("3655224");
			   		driver.findElement(By.id("add")).click();
			   		Thread.sleep(300);
			   		assertEquals("The Alien Registration Number field must contain\nat least four alpha-numeric characters.\nPlease correct your entry..", closeAlertAndGetItsText());
			   		driver.findElement(By.id("Cust_alien_reg_no_Edit")).clear();
			   		driver.findElement(By.id("Cust_alien_reg_no_Edit")).sendKeys("365248");
			   		driver.findElement(By.id("add")).click();
			   		Thread.sleep(300);
			   		assertEquals("Invalid passport #. Please correct your entry.", closeAlertAndGetItsText());
			   		driver.findElement(By.id("Cust_passport_Edits")).clear();
			   		driver.findElement(By.id("Cust_passport_Edits")).sendKeys("587455");
			   		driver.findElement(By.id("add")).click();
			   		Thread.sleep(300);
			   		assertEquals("Error in inserting records to database!", closeAlertAndGetItsText());
			   	
			   		BaseUtils.getScreenshot();
			   	  
	          } 
	  
	  @DataProvider
	  public Object[][] AlphaNumericDataEntry() throws Exception{
		  
		    // Setting up the Test Data Excel file
		  
		    ExcelUtils.setExcelFile(""+resouceUtils.Constants.getBaseUrl()+"\\testData\\ERead1.xlsx","Sheet2");
		 	sTestCaseName = this.toString();
	 
		  	// From above method we get long test case name including package and class name etc.
	 
		  	// The below method will refine your test case name, exactly the name use have used
	 
		  	sTestCaseName = ExcelUtils.getTestCaseName(this.toString());
	 
		    // Fetching the Test Case row number from the Test Data Sheet
	 
		    // Getting the Test Case name to get the TestCase row from the Test Data Excel sheet
	 
		 	iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,0);
	 
		 	Object[][] testObjArray = ExcelUtils.getTableArray(""+resouceUtils.Constants.getBaseUrl()+"\\testData\\ERead1.xlsx","Sheet2",iTestCaseRow);
			   
		    	return (testObjArray);
	 
			}
	  
	  
	  @AfterClass
      public void closeBrowser(){
          String verificationErrorString = verificationErrors.toString();
          if (!"".equals(verificationErrorString)) {
          fail(verificationErrorString);
          
      }			
      }
	      
	}	
