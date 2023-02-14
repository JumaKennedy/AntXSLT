package resouceUtils;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
	
	public class DataProviderClass extends ExcelUtils {
		
			@DataProvider(name = "dataProvider")
			public static Object[][] provideData(Method method) {
		 
				Object[][] result = null;
		 
				if (method.getName().equals("Step0")) {
					result = new Object[][] {
							{ "55F$3#t", "38118" }, {"PST", "63012" }, { "()+{]%()^><@-=!!!", "76002" }  // zip code
					};
				} else if (method.getName().equals("Step7")) {
					result = new Object[][] { 
							{ "59G&8iR9", "DL9087" }, {"@*Drop", "PSA842155" }, { "()+Ali&hN=09)(", "A 8457254" } // alphanumeric lookup buttons
					};
						
				} else if (method.getName().equals("Step3")) {
					result = new Object[][] { 
							{ "5UTb&6$#\\|", "DL 9087" }, {"PST#-+sDD", "PSA 842155" } // alphanumeric with space 
					};
				}
			 
					return result;
			 
				}
	
	
			@DataProvider
			public static Object[][] ZipCode()
			{
			//Rows - Number of times your test has to be repeated.
			//Columns - Number of parameters in test data.
			Object[][] data = new Object[3][2];
		
			// 1st row
			data[0][0] ="3811";
			data[0][1] = "38118";
		
			// 2nd row
			data[1][0] ="@$^&Doh{]**";
			data[1][1] = "63012";
			
			// 3rd row
			data[2][0] ="!!!GGGTttzT!8T#";
			data[2][1] = "76002";
		
			return data;
			}
			
	
			@DataProvider
			public static Object[][] alphaNumeric()
			{
			//Rows - Number of times your test has to be repeated.
			//Columns - Number of parameters in test data.
			Object[][] data = new Object[3][2];
		
			// 1st row
			data[0][0] ="854";
			data[0][1] = "8547787";
		
			// 2nd row
			data[1][0] ="##0()";
			data[1][1] = "Driv12";
			
			// 3rd row
			data[2][0] ="/*-+*H@";
			data[2][1] = "DL76002";
		
			return data;
			}
			
			@DataProvider
			public static Object[][] filenames()
			{
			//Rows - Number of times your test has to be repeated.
			//Columns - Number of parameters in test data.
			Object[][] data = new Object[1][2];
		
			// 1st row
			data[0][0] ="this is a file";
			data[0][1] = "renamed file";	
		
			return data;
			}
			
			@DataProvider
			public static Object[][] alphanumericwith4digits() {
		        return new Object[][] { { "55D", "DL9087" }, {"PST", "PSA842155" }, { "7|4()+Ali", "A 8457254" } };
		    }
			
			
			@DataProvider
			public static Object[][] SSNtest() {
		        return new Object[][] { { "078051120" }, { "123121234" }, { "000000000" }, { "111111111" }, { "222222222" }, 
			        		            { "333333333" }, { "444444444" }, { "555555555" }, { "777777777" },
			        		            { "888888888" }, { "999999999" }, { "()_=(*&|%" }, { "@$%^&*(_\\" },
			        		            { "EEEEEEEEE" }, { "drop tabl" }, { "delete(-)" }, { "select*$bsa" },
			        		            { "219099999" }, { "^()-+-+++" }, { "select %Bob%" }, { "123456789" }};
		    }
			
			
	
			@DataProvider
			public static Object[][] AlphaNUmericAndSpace()
			{
			//Rows - Number of times your test has to be repeated.
			//Columns - Number of parameters in test data.
			Object[][] data = new Object[2][2];
		
			// 1st row
			data[0][0] ="8&&5$44HH 99hh";
			data[0][1] = "8765 ceres hill";
		
			// 2nd row
			data[1][0] ="--++55///*h{]**";
			data[1][1] = "Smith Hempston";
			
			
			return data;
			}
	
	
	
			@DataProvider 
			public static Object[][] ValidDataProvider() {
				return new Object[][]{
					{ 'A', 65 },{ 'a', 97 },
					{ 'B', 66 },{ 'b', 98 },
					{ 'C', 67 },{ 'c', 99 },
					{ 'D', 68 },{ 'd', 100 },
					{ 'Z', 90 },{ 'z', 122 },
					{ '1', 49 },{ '9', 57 }
				};
			  }
			
			@DataProvider
			public static Object[][] password1()
			{
			//Rows - Number of times your test has to be repeated.
			//Columns - Number of parameters in test data.
			Object[][] data = new Object[1][2];
		
			// 1st row no special char space
			data[0][0] ="passw";			
			
			// 2nd row alphanumeric and length
			
			data[0][1] = "M1pa55w0rd997";
		
			return data;
			}
			
			@DataProvider
			public static Object[][] password() {
		        //return new Object[][] { {"user1", "pass55"}, {"user1", "PST555kkhh"}, { "user1", "M1pa55w0rd99@@7" } };
		        return new Object[][] { { "user1", "M1pa55w0rd99@@7" } };
		    }
			
			@DataProvider
			public static Object[][] Encript() {
		        
				return new Object[][] { { "user1"} };
		    }
			
			  
			 
			
 } 
