package com.w2a.base;
import org.testng.annotations.AfterSuite;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeSuite;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import org.openqa.selenium.*;

public class TestBase {
	 
	/*
	 * WebDriver - done
	 * Properties - done
	 * Logs - log4j jar,  .log, log4j.properties
	 * ExtentReports
	 * DB
	 * Excel
	 * MAil
	 * ReportNG , ExtentReports
	 * Jenkins
	 */
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	
	@BeforeSuite
	public void setUp() throws IOException {
		if(driver==null) {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			config.load(fis);
			
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			OR.load(fis);
		
		
		if(config.getProperty("browser").equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		else if (config.getProperty("browser").equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
			
			
			
		}
		
		driver.get(config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.SECONDS);
		}
	}
	
	@AfterSuite
	public void tearDown() {
		if(driver!=null) {
		driver.quit();
		}
	}
}
