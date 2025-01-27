package remoteTesting.dockerValidation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.testng.Assert;

public class startDocker {

	public void startFile() throws IOException, InterruptedException {
		
		boolean flag = false;
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("cmd /c start dockerUp.bat");
		
		String f = "output.txt";
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 45);
		long stopnow = cal.getTimeInMillis();
		Thread.sleep(3000);
		
		while(System.currentTimeMillis()<stopnow) {
			if(flag) {break;}
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String currentLine = reader.readLine();
		while(currentLine!=null && !flag) {
			if(currentLine.contains("Sending registration event...")) {
				System.out.println("found my text");
				flag = true;
				break;
			}
			currentLine = reader.readLine();	
		}
		reader.close();
		}
		
		Assert.assertTrue(flag);
		runtime.exec("cmd /c start scaleup.bat");
		Thread.sleep(15000);
		
	}
	
}
