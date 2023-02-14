package resouceUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

@SuppressWarnings("unused")
public class ExcelUtils {
	 
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;	
	static HSSFWorkbook workbook;	 
	static HSSFSheet sheet;
	//define a test result data object
	static Map<String, Object[]> testresultdata;
	
	
    public static void setExcelHeaders() {
    //create a new work book
     workbook = new HSSFWorkbook();
     //create a new work sheet
     sheet = workbook.createSheet("Test Result");
     testresultdata = new LinkedHashMap<String, Object[]>();
     //add test result excel file column header
     //write the header in the first row
     testresultdata.put("1", new Object[] {"Test Step Id","Start time", "Method Name", "Exception",  "Description of Action", "Expected Result","Actual Result"});
    
    }
	
	public void writeoutExcel() throws IOException {
	  
		 Set<String> keyset = testresultdata.keySet();
	    int rownum = 0;	
	   
	    for (String key : keyset) {
	        Row row = sheet.createRow(rownum++);
	        Object [] objArr = testresultdata.get(key);
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
           sheet.autoSizeColumn((short) (columnPosition));
      }
	    
	    HSSFSheet sheet = workbook.getSheetAt(0);
		SetfontStyles(workbook, sheet.getRow(0));						
		SetBackground(workbook, sheet.getRow(1));
		
		
		
	    try {
	    	
	    	DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa"); 
			//Path currentRelativePath = Paths.get("");
			//String s = currentRelativePath.toAbsolutePath().toString();
			
			//String destDir = ""+resouceUtils.Constants.ShotsDir1+"\\"+className+"\\"+methodName+"";						
			String destDir = ""+resouceUtils.Constants.getBaseUrl()+"\\exelReport\\";	
			System.out.println("Current relative path is ######: " + destDir);
	    	
	    	//String destDir = ""+resouceUtils.Constants.exelReports+"";			
			String destFile = dateFormat.format(new Date()) + ".xls";
			
	        FileOutputStream out =new FileOutputStream(new File(destDir + "/" + destFile));
	        workbook.write(out);
	        out.close();
	        System.out.println("Excel written successfully.. " + destDir);
	         
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
	  
	  

      //This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method

     public static void setExcelFile(String Path,String SheetName) throws Exception {

	   try {

			// Open the Excel file

			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			} catch (Exception e){

				throw (e);

			}

	}
     
     @DataProvider(name="supplydata")
	 public static Object[][] supplydata() throws  IOException
	    {
	        Object a[][]=new Object[3][2];
	        InputStream fis=new FileInputStream(""+resouceUtils.Constants.getBaseUrl()+"\\testData\\exRead.xlsx");
	        XSSFWorkbook wb = new XSSFWorkbook(fis);
	        Sheet s=wb.getSheetAt(0);
	        int rowcount=s.getLastRowNum();
	        for(int i=0;i<rowcount;i++)
	        {
	        Row r=s.getRow(i+1);
	        int noofcells=r.getLastCellNum();
	        for(int j=0;j<noofcells;j++)
	        {
	        Cell c=r.getCell(j);
	        if(c.getCellType()==org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING)
	        {
	        a[i][j]=c.getStringCellValue();
	        }
	        else
	        if(c.getCellType()==org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC)
	        {
	        a[i][j]=String.valueOf(c.getNumericCellValue());
	        }
	        System.out.println("data from excel ###  "+a[i][j]);
	        }
	        }
	         return a;	        
	     }

     @DataProvider
	  public static Object[][] AlphaNumericDataEntry() throws Exception{
		  
		    String sTestCaseName;	 
		    int iTestCaseRow;
		    // Setting up the Test Data Excel file
		  
   	        ExcelUtils.setExcelFile(""+resouceUtils.Constants.getBaseUrl()+"\\testData\\ERead1.xlsx","Sheet2");
	 
		 	sTestCaseName = null;
	 
		  	// From above method we get long test case name including package and class name etc.
	 
		  	// The below method will refine your test case name, exactly the name use have used
	 
		  	//sTestCaseName = ExcelUtils.getTestCaseName(this.toString());
	 
		    // Fetching the Test Case row number from the Test Data Sheet
	 
		    // Getting the Test Case name to get the TestCase row from the Test Data Excel sheet
	 
		 	iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,0);
	 
		 	Object[][] testObjArray = ExcelUtils.getTableArray(""+resouceUtils.Constants.getBaseUrl()+"\\testData\\ERead1.xlsx","Sheet2",iTestCaseRow);
			   
		    	return (testObjArray);
	 
			}
     
     public static Object[][] getExTableArray(String FilePath, String SheetName, int iTestCaseRow)    throws Exception
		
		{   
		
		   String[][] tabArray = null;
		
		   try{
		
			   FileInputStream ExcelFile = new FileInputStream(FilePath);
		
			   // Access the required test data sheet
		
			   ExcelWBook = new XSSFWorkbook(ExcelFile);
		
			   ExcelWSheet = ExcelWBook.getSheet(SheetName);
		
			   int startCol = 1;
		
			   int ci=0,cj=0;
		
			   int totalRows = 1;
		
			   int totalCols = 2;
		
			   tabArray=new String[totalRows][totalCols];
		
				   for (int j=startCol;j<=totalCols;j++, cj++)
		
				   {
		
					   tabArray[ci][cj]=getCellData(iTestCaseRow,j);
		
					   System.out.println(tabArray[ci][cj]);
		
				   }
		
			}
		
			catch (FileNotFoundException e)
		
			{
		
				System.out.println("Could not read the Excel sheet");
		
				e.printStackTrace();
		
			}
		
			catch (IOException e)
		
			{
		
				System.out.println("Could not read the Excel sheet");
		
				e.printStackTrace();
		
			}
		
			return(tabArray);
		
		}


public static Object[][] getTableArray(String FilePath, String SheetName, int iTestCaseRow)    throws Exception

{   

   String[][] tabArray = null;

   try{

	   FileInputStream ExcelFile = new FileInputStream(FilePath);

	   // Access the required test data sheet

	   ExcelWBook = new XSSFWorkbook(ExcelFile);

	   ExcelWSheet = ExcelWBook.getSheet(SheetName);

	   int startCol = 1;

	   int ci=0,cj=0;

	   int totalRows = 1;

	   int totalCols = 10;

	   tabArray=new String[totalRows][totalCols];

		   for (int j=startCol;j<=totalCols;j++, cj++)

		   {

			   tabArray[ci][cj]=getCellData(iTestCaseRow,j);

			   System.out.println(tabArray[ci][cj]);

		   }

	}

	catch (FileNotFoundException e)

	{

		System.out.println("Could not read the Excel sheet");

		e.printStackTrace();

	}

	catch (IOException e)

	{

		System.out.println("Could not read the Excel sheet");

		e.printStackTrace();

	}

	return(tabArray);

}

//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

public static String getCellData(int RowNum, int ColNum) throws Exception{

   try{

	  Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

	  String CellData = Cell.getStringCellValue();

	  return CellData;

	  }catch (Exception e){

		return"";

		}

	}

public static String getTestCaseName(String sTestCase)throws Exception{

	String value = sTestCase;

	try{

		int posi = value.indexOf("@");

		value = value.substring(0, posi);

		posi = value.lastIndexOf(".");	

		value = value.substring(posi + 1);

		return value;

			}catch (Exception e){

		throw (e);

		}

	}

public static int getRowContains(String sTestCaseName, int colNum) throws Exception{

	int i;

	try {

		int rowCount = ExcelUtils.getRowUsed();

		for ( i=0 ; i<rowCount; i++){

			if  (ExcelUtils.getCellData(i,colNum).equalsIgnoreCase(sTestCaseName)){

				break;

			}

		}

		return i;

			}catch (Exception e){

		throw(e);

		}

	}

public static int getRowUsed() throws Exception {

		try{

			int RowCount = ExcelWSheet.getLastRowNum();

			return RowCount;

		}catch (Exception e){

			System.out.println(e.getMessage());

			throw (e);

		}

	}

}