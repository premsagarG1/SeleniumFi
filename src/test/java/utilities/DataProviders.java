package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="loginData")
	public String[][] getDate() throws IOException{
		String path=".\\testData\\Book4.xlsx"; //taking xl file from testData folder
		
		ExcelUtils01 util=new ExcelUtils01(path); //create obj for utility class
		
		int totalRows=util.getRowCount("Sheet1");
		int totalcals=util.getCellCount("Sheet1", 1);
		
		String loginData[][]=new String[totalRows][totalcals];//create String 2 dimentional array to store data
		
		for(int i=1;i<=totalRows;i++) {//read data from xl file to store in string
			
			for(int j=0;j<totalcals;j++) {
				
				loginData[i-1][j]=util.getCellData("Sheet1", i, j);//1,0
			}
		}
		
		return loginData;//return String 2 dim array
	}
}
