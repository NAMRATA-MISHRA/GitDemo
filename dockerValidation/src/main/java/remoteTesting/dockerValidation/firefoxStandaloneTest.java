package remoteTesting.dockerValidation;


import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class firefoxStandaloneTest {

	public static void main(String[] args) throws MalformedURLException{
		// TODO Auto-generated method stub
		//remote webdriver 
		URL u = new URL("http://localhost:4444/wd/hub");
		DesiredCapabilities cap = DesiredCapabilities.firefox();
		RemoteWebDriver driver = new RemoteWebDriver(u,cap);
		driver.get("https://www.google.com/");
		System.out.println(driver.getTitle());
	}

}
