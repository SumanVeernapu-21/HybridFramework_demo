package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {
public static WebDriver driver;
public Logger logger;
public Properties p;
	
	@Parameters({"OS", "Browser"})
	@BeforeClass (groups= {"Sanity", "Regression", "Master"})
	public void setup(@Optional("Windows") String os, @Optional("chrome") String br) throws IOException {
		//In the above setup method declaration, we need to give @Optional("Windows") before String os as we are making sure that 
		//test will be executed even though you do not pass os through testng.xml file. we need not give this if you are executing 
		//from testng.xml file, but this is required when you are executing from pom.xml file
		
		
		//pre-req steps to create variable 'p' (to read Config.properties file) and load file
		FileReader file= new FileReader("./src//test//resources//Config.properties"); //we can use fileinputstream also instead of filereader
		p= new Properties();
		p.load(file);
		
		logger=  LogManager.getLogger(this.getClass()); //This statement is required for Extent reporting
		
		switch(br.toLowerCase()) { //getting browser 'br' from xml file and launch driver according to browser
		case "chrome": driver= new ChromeDriver(); break;
		case "firefox": driver= new FirefoxDriver(); break;
		case "edge": driver= new EdgeDriver(); break;
		default: System.out.println("Invalid browser"); return;
		}		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.manage().window().maximize();
		driver.get(p.getProperty("appurl")); //reading url from Config.properties file	
	}
	
	@AfterClass (groups= {"Sanity", "Regression", "Master"})
	public void tearDown() {
		driver.quit();
	}
	
	//this is the method created using RandomStringUtils to get random string 
	public String RandomString() {
		String randomstring= RandomStringUtils.secure().nextAlphabetic(5);
		return randomstring;
	}
		
	//this is the method created using RandomStringUtils to get random number 
	public String Randomnumber() {
		String randomnumber = RandomStringUtils.secure().nextNumeric(10);
		return randomnumber;
	}
		
	//this is the method created using RandomStringUtils to get random alphanumberic  
	public String RandomAplhaNum() {
		String randomalphanum = RandomStringUtils.secure().nextAlphanumeric(7);
		return randomalphanum;
	}

	public String captureScreen(String tname) throws IOException{

		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot= (TakesScreenshot) driver;
		//Thread.sleep(3000);
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timestamp +".png";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}
