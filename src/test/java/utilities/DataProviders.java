package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")	
	Object[][] getData_DataDriven() throws IOException {
		String path= ".\\testData\\DataDrivenTesting.xlsx";
		ExcelUtilityFile euf =new ExcelUtilityFile(path);
		
		int total_rows= euf.getRowCount("Sheet1");
		int total_cols= euf.getCellCount("Sheet1", 1);
		
		Object logindata[][]= new String[total_rows][total_cols];
		
		for(int i=1;i<=total_rows;i++) {
			for (int j=0;j<total_cols; j++) {
				logindata[i-1][j]= euf.getCellData("Sheet1", i, j);
			}
		}
		return logindata;
		}

}
