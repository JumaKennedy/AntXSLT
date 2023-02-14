package listeners;

import org.testng.IConfigurationListener2;
import org.testng.ITestResult;

public class ConfigurationListerner implements IConfigurationListener2 {
	private int m_count = 0;
    @Override
    public void onConfigurationSuccess(ITestResult tr) {    	
    	//log("configuration success\n");    

    }

    @Override

    public void onConfigurationFailure(ITestResult tr) {
    	log("configuration failure\n");

    }


    @Override
    public void onConfigurationSkip(ITestResult tr) {
    	log("configuration skip\n");           

    }


    @Override

    public void beforeConfiguration(ITestResult tr) {
    	
       
    }
    
    
    private void log(String string) {
        System.out.print(string);
        if (++m_count % 40 == 0) {
	    log("");
        }
    }

}
