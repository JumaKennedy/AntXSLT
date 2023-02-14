package listeners;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.Reporter;

public class EventHandleListener implements WebDriverEventListener{

	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
		Reporter.log("inside method afterChangeValueOf on " + arg0.toString());
	}
 
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		
		Reporter.log("inside method afterClickOn on " + arg0.toString());
	}
 
	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		
		Reporter.log("Find happened on " + arg1.toString() 
				+ " Using method " + arg0.toString());
	}
 
	public void afterNavigateBack(WebDriver arg0) {		
 
		Reporter.log("Inside the after navigateback to " + arg0.getCurrentUrl());
	}
 
	public void afterNavigateForward(WebDriver arg0) {
		
		Reporter.log("Inside the afterNavigateForward to " + arg0.getCurrentUrl());
	}
 
	public void afterNavigateTo(String arg0, WebDriver arg1) {
		
		Reporter.log("Inside the afterNavigateTo to " + arg0);
	}
 
	public void afterScript(String arg0, WebDriver arg1) {
		
		Reporter.log("Inside the afterScript to, Script is " + arg0);
	}
 
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
		
 
		Reporter.log("Inside the beforeChangeValueOf method");
	}
 
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		
		Reporter.log("About to click on the " + arg0.toString());
 
	}
 
	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		
		Reporter.log("Just before finding element " + arg1.toString());
 
	}
 
	public void beforeNavigateBack(WebDriver arg0) {
		
		Reporter.log("Just before beforeNavigateBack " + arg0.getCurrentUrl());
 
	}
 
	public void beforeNavigateForward(WebDriver arg0) {
		
		Reporter.log("Just before beforeNavigateForward " + arg0.getCurrentUrl());
 
	}
 
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		
		Reporter.log("Just before beforeNavigateTo " + arg0);
	}
 
	public void beforeScript(String arg0, WebDriver arg1) {
		
		Reporter.log("Just before beforeScript " + arg0);
	}
 
	public void onException(Throwable arg0, WebDriver arg1) {
		
		Reporter.log("Exception occured at " + arg0.getMessage());
 
	}
}
