package listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import driverFactory.DriverManager;


public class ExelReportListener extends TestListenerAdapter{
	
			
	//public static WebDriver driver;
	 String sTestCaseName;
	
	  //define an Excel Work Book
	  static HSSFWorkbook workbook;
	  static //define an Excel Work sheet
	  HSSFSheet sheet;
	  
	  String timenow = resouceUtils.Constants.getCurrentTime(); 
	  
	  @Override
	    public void onStart(ITestContext context) {
		  setExelHeader();
		}
	  
	  @Override
	    public void onFinish(ITestContext context) {
		  writeExelfile();		    
	    }
	 
	  
	 @Override
	    public void onTestSuccess(ITestResult tr) {	
		 WriteMethodReport(tr);
	    }
	  
	  @Override
	    public void onTestFailure(ITestResult tr) {		    
		    WriteMethodReport(tr);	
	  }
	  
	  @Override
	    public void onTestSkipped(ITestResult tr) { 		  
		  WriteMethodReport(tr);
	    }
	  
	  @SuppressWarnings("unused")
	private String getParams(ITestResult tr) {
			String params = "";	
			
			if (tr.getParameters().length == 0) {				
				params = "No inputs";		
			}
			
			if (tr.getParameters().length != 0) {
				for (Object parameter : tr.getParameters()) {
					params += parameter.toString() + ", ";
				}
			}			
			return params.toString();
			
			}
	    
	    
	  
	  public static void setExelHeader() {
		  //PropertyConfigurator.configure(log4jConfigFile);
		  //create a new work book
	      workbook = new HSSFWorkbook();
	      //create a new work sheet
	      sheet = workbook.createSheet("Test Result");	      
	      DriverManager.testresultdata = new LinkedHashMap<String, Object[]>();
	     //write the header in the first row
	      DriverManager.testresultdata.put("1", new Object[] {"Test Step","Start time", "Duration", "Errors",  "Test Scenarios", "Iputs Values",  "Status"});
	     
		}
	  
	   
	 
	    public static void writeExelfile() {
		  Set<String> keyset = DriverManager.testresultdata.keySet();
		    int rownum = 0;	
		   
		    for (String key : keyset) {
		        Row row = sheet.createRow(rownum++);
		        Object [] objArr = DriverManager.testresultdata.get(key);
		        int cellnum = 0;
		        for (Object obj : objArr) {
		            Cell cell = row.createCell(cellnum++);
		            if(obj instanceof Date) 
		                cell.setCellValue((Date)obj);
		            else if(obj instanceof Boolean)
		                cell.setCellValue((Boolean)obj);
		            else if(obj instanceof String)
		                cell.setCellValue((String)obj);
		            else if(obj instanceof Double)
		                cell.setCellValue((Double)obj);
		        }
		    }
		    
		    for(int columnPosition = 0; columnPosition< 10; columnPosition++) {
		    	if(columnPosition==5){
		    	sheet.setColumnWidth(5, 3500);
		    	//continue;
		    		}
		    	sheet.autoSizeColumn((short) (columnPosition));
	        }
		    
		    //sheet.setColumnWidth(5, 7500);
		    
		    //HSSFSheet sheet = workbook.getSheetAt(0);
			SetfontStyles(workbook, sheet.getRow(0));						
			//SetBackground(workbook, sheet.getRow(1));
			
		    try {
		    	 Calendar now = Calendar.getInstance();
		    	 int year = now.get(Calendar.YEAR);
		    	 int month = (now.get(Calendar.MONTH) + 1);
		    	 int day = now.get(Calendar.DATE);
		    	 
		    	DateFormat dateFormat = new SimpleDateFormat("hh-mm-ssaa"); 
				Path currentRelativePath = Paths.get("");
				String s = currentRelativePath.toAbsolutePath().toString();
				
				//String destDir = ""+resouceUtils.Constants.ShotsDir1+"\\"+className+"\\"+methodName+"";						
				String destDir = ""+s+"\\exelReport\\"+month+"-"+day+"-"+year+"\\";	
				//System.out.println("Check excel Report at: " + destDir);
		    	
		    	//String destDir = ""+resouceUtils.Constants.exelReports+"";			
				String destFile = dateFormat.format(new Date()) + ".xls";
				
				File dir = new File(destDir);
				if(!dir.exists()){
					new File(destDir).mkdirs();
				}	
				
		        FileOutputStream out =new FileOutputStream(new File(destDir + "/" + destFile));
		        workbook.write(out);
		        out.close();
		        System.out.println("Excel Report written successfully at " + destDir);
		         
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    
	    }
	  
		  
		  //add font weight
		  public static void SetfontStyles(Workbook wb, Row row){
			    CellStyle style = wb.createCellStyle();//Create style
			    Font font = wb.createFont();//Create font
			    font.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
			    font.setColor(HSSFColor.GREEN.index);
			    style.setFont(font);//set it to bold

			    for(int i = 0; i < row.getLastCellNum(); i++){//For each cell in the row 
			        row.getCell(i).setCellStyle(style);//Set the style
			    }
			}
		  
		  public static void setwidth(){
			  for(int i = 0; i < 10; i++){//For each cell in the row 
				  sheet.setColumnWidth(2, 7500);
			    }
			}
		  
		  
		  public static void SetBackground(Workbook wb, Row row){
			    CellStyle style = wb.createCellStyle();//Create style			   
			    style.setFillForegroundColor(IndexedColors.TAN.getIndex());
			    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			    //row.setRowStyle(style);
			    			    
			    for(int i=0;i<row.getLastCellNum();i++){
			    	row.getCell(i).setCellStyle(style);
			    	 row.setRowStyle(style);
				}  
			}
		  
		  
		  public static void WriteMethodReport(ITestResult tr) {
			     
			    int priority = tr.getMethod().getPriority();
				String exp=""; 
			    String descrip = tr.getMethod().getDescription();
				String params = "";				
				long start = DriverManager.getStartime(tr);	
				
				
				if (tr.getParameters().length != 0) {

					for (Object parameter : tr.getParameters()) {
						
						params += parameter.toString() + " \n";

					}

				}

				String status = null;

				switch (tr.getStatus()) {

				case ITestResult.SUCCESS:

					status = "Pass";

					break;

				case ITestResult.FAILURE:

					status = "Failed";
					exp = tr.getThrowable().getMessage();
					break;

				case ITestResult.SKIP:

					status = "Skipped";

				}
				Reporter.log("\n - Description "+descrip);
				DriverManager.testresultdata.put(""+priority+"" , new Object[] {"" + tr.getName(), DateFormat.getDateTimeInstance().format(new Date(start)) , DriverManager.getRunTime(tr)+"s", exp ,  descrip, params, status});
			}
		  
			  
}
