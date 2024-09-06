package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	//DataProvider1
	@DataProvider(name="LoginData")
    public String [][] getData() throws IOException
    {
    	String path = ".\\testData\\OpenCart_LoginData.xlsx";
    	
    	ExcelUtility xlutil = new ExcelUtility(path);  // creating an object for excel utilty 
    	
    	int totalrows = xlutil.getRowCount("DataDriverSheet1");
    	int totalcols = xlutil.getCellCount("DataDriverSheet1", 1);
    	
    	String loginData [][] = new String[totalrows][totalcols];  
    	// created for tow dimantion array which can store
    	
    	for (int i=1; i<=totalrows; i++)  // read the data from xl String in two dimentional array
    	{
    		for (int j=0; j<totalcols; j++) // i is row and j is col
    		{
    			loginData[i-1][j] = xlutil.getCellData("DataDriverSheet1", i, j);
    					
    					//why[i-1] - array index stats from zero	
    		}
    	}
    	
    	return loginData; //returning two dimentional aray
    	
    	
    }
}
