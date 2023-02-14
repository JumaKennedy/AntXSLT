package driverFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import resouceUtils.ExcelReportsUtils;


public abstract class DriverManager {
	

	public static WebDriver driver;
	//private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	//String nodeURL = "http://56.160.19.250";      
    public static boolean acceptNextAlert = true;
    String timenow = resouceUtils.Constants.getCurrentTime(); 
    protected HSSFWorkbook workbook;
    protected HSSFSheet sheet;	 
    public static Map<String, Object[]> testresultdata;
    public static int count =1;
	 
	@Parameters("browser")
    @BeforeClass 
    public void openBrowser(@Optional("firefox")  String browser) throws Exception { 		
		Reporter.log(" - Test running on " +browser+ " browser" , true);
		if(browser.equalsIgnoreCase("firefox")) {
	    	OpenFirefox();
	    }
	     if(browser.equalsIgnoreCase("internet")) {
	    	OpenIE();         
	    }
	      if(browser.equalsIgnoreCase("chrome")) {        	
	    	OpenCrome();
	        
	    }		
    }
	@BeforeClass 
	public void addHeaderToExel(){ 
		ExcelReportsUtils.setExelHeader();				
    }
	
	@AfterMethod
	 public void addMethodreportToExel(ITestResult tr) {	 
	    ExcelReportsUtils.WriteMethodReport(tr);	   
	     
	 }	
	
	
	@AfterClass
    public void makeExelReport(){
		ExcelReportsUtils.writeExelfile();  
    }	
	
	@AfterClass
    public void closedBrowser(ITestContext context){
		driver.close();		
	  	Exception e = new Exception();
	  	e.fillInStackTrace();	  	
  	    Reporter.log("\n" +resouceUtils.Constants.partition2, true);
  	    //Reporter.log("\n" +resouceUtils.Constants.endClass, true); 
  	    Reporter.log("======================== End Class: " + getClass().getName()+ " ======================", true);  	                  
  	    Reporter.log("\n" +resouceUtils.Constants.partition2, true); 
  	     	  
    }		
    
    @AfterMethod
	 public void logmethodEvent(ITestResult tr, Method method) {
	 long duration = ((tr.getEndMillis() - tr.getStartMillis())/1000);
	 Reporter.log(resouceUtils.Constants.partition2, true);					
	 Reporter.log("Test " + method.getName() + " complete at " + DateFormat.getDateTimeInstance().format(new Date(getStartime(tr))), true);
	 Reporter.log("\n" +tr.getName()+ " --  Duration\n" + duration+ " Seconds");
	// ExcelReportsUtils.WriteMethodReport(tr);
	 }
    
    @BeforeMethod
    public static long getStartime(ITestResult tr) {
   	long Start = tr.getStartMillis();   
   	return Start;
   	 
    }
    
    @BeforeMethod
    public void partition(ITestResult tr) {
    Reporter.log(resouceUtils.Constants.partition, true);	
   	 
    }
    
    @AfterMethod
	 public static long getRunTime(ITestResult tr) {
	 long duration = ((tr.getEndMillis() - tr.getStartMillis())/1000);		 	
	 return duration;
	 }
    
	public static void OpenIE() throws Exception { 
	   File file = new File(resouceUtils.Constants.IEdriver);	
	  if(!file.exists()) throw new FileNotFoundException(file + " missing or corrupted file - IEDriverServer.exe ");
	  
	  else
	  
	  try {  

		  System.setProperty("webdriver.ie.driver", "C:\\Selenium\\IEdriver\\IEDriverServer.exe");
		  //System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		  DesiredCapabilities cap = new DesiredCapabilities();
		  cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		  cap.setCapability("initialBrowserUrl", resouceUtils.Constants.baseUrl2); 
		  cap.setJavascriptEnabled(true);
		  driver=new InternetExplorerDriver(cap);
		  cap.setJavascriptEnabled(true);       		
          driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  driver.navigate().to("javascript:document.getElementById('overridelink').click()");
		
		} catch (Exception e) {
         //throw new IllegalStateException("Can't start Web Driver - IE", e);
		throw(e);
        } 	                  
        }   

      public static void OpenFirefox() throws IOException, MalformedURLException {        
      
	  try {
	    
			/* URL server = new URL("56.160.19.250:4444/wd/hub");

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("firefox");

			System.out.println("Connecting to " + server);

			driver = new RemoteWebDriver(server, capabilities);
 */
		  driver = new FirefoxDriver();		  
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
			
		} catch (Exception e) {
         throw new IllegalStateException("Can't start Web Driver - FirefoxDriver", e);
		 //throw(e);
       } 		                  
       }    

      public static void OpenCrome() throws FileNotFoundException {        
      
	  File file = new File(resouceUtils.Constants.ChromeDriver);		
	  if(!file.exists()) throw new FileNotFoundException(file + " missing file - chromedriver.exe ");
	    
	  else
	  
	  try {
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        //System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver\\chromedriver.exe");
    	//DesiredCapabilities cap = new DesiredCapabilities();
		/* DesiredCapabilities cap = DesiredCapabilities.chrome();			
		cap.setVersion("24.5.0");
        cap.setPlatform(org.openqa.selenium.Platform.WINDOWS); 
	    driver = new RemoteWebDriver(new URL("http://56.160.19.250:4444/wd/hub"), cap); 			
    	cap.setcap("chrome.switches", Arrays.asList("--disable-local-storage")); */
		
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
        driver.manage().window().maximize();
		} catch (Exception e) {
         throw new IllegalStateException("Can't start Web Driver - Chrome", e);
        } 		                  
        } 
     
      
      public static boolean isElementPresent(By by) {
	        try {
	          driver.findElement(by);
	          return true;
	        } catch (NoSuchElementException e) {
	          return true;
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

      
}



