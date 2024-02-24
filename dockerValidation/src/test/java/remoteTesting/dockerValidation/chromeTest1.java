package remoteTesting.dockerValidation;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class chromeTest1 {
	@BeforeTest
	public void startDockerScale() throws IOException, InterruptedException {
		File fi = new File("output.txt");
		if(fi.delete()) {
			System.out.println("Deleted File");
		}
		startDocker s = new startDocker();
		s.startFile();
	}
	@AfterTest
	public void stopDocker() throws IOException, InterruptedException {
		stopDocker s = new stopDocker();
		s.stopFile();
	}
	
    @Test
	public void test1() throws MalformedURLException{
		// TODO Auto-generated method stub
		//remote webdriver 
		URL u = new URL("http://localhost:4444/wd/hub");
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		RemoteWebDriver driver = new RemoteWebDriver(u,cap);
		driver.get("https://www.google.com/");
		System.out.println(driver.getTitle());
	}

}
