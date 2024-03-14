import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class extractJson {

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		ObjectMapper o = new ObjectMapper();
		 CustomerDetailsAppium c= o.readValue(new File("C:\\Users\\namra\\Workspace-Automation\\JsonJava\\customerInfo0.json"), CustomerDetailsAppium.class);
		System.out.println(c.getAmount());
		 System.out.println(c.getCourseName());
		System.out.println(c.getLocation());
		System.out.println(c.getPurchasedDate());
		System.out.println(c.getStudentName());
		//validate records in web table are correct or not
	}

}
