import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class oneSingleJson {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, JsonGenerationException,JsonMappingException,DatabindException, IOException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		
		ArrayList<CustomerDetails> a = new ArrayList<CustomerDetails>();
		
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business","root" ,"root");
		JSONArray js = new JSONArray();
		// object of statement class will help us to execute query
		Statement st = conn.createStatement();
		ResultSet rs=st.executeQuery("Select * from CustomerInfo where location = 'Asia' and purchasedDate = curdate();");
		while(rs.next()) {
			CustomerDetails c= new CustomerDetails();
			// 3 different json files, 3 diff java objects
			c.setCourseName(rs.getString(1));
			c.setPurchasedDate(rs.getString(2));
			c.setAmount(rs.getInt(3));
			c.setLocation(rs.getString(4));
			a.add(c);
		}
		for(int i=0;i<a.size();i++) {
			ObjectMapper o = new ObjectMapper();
			o.writeValue(new File("C:\\Users\\namra\\Workspace-Automation\\JsonJava\\customerInfo"+i+".json"),a.get(i));
			//gson
			Gson g = new Gson();
			String jsonString = g.toJson(a.get(i));
			js.add(jsonString);
			
		}
		//create json string from java object
		
		//Json simple
		JSONObject jo = new JSONObject();
		jo.put("data", js);
		System.out.println(jo.toJSONString());
		String unescapeString = StringEscapeUtils.unescapeJava(jo.toJSONString());
		System.out.println(unescapeString);
		String newStr = unescapeString.replace("\"{","{");
		System.out.println(newStr);
		String finalStr = newStr.replace("}\"","}");
		System.out.println(finalStr);
		try(FileWriter file = new FileWriter("C:\\Users\\namra\\Workspace-Automation\\JsonJava\\SingleJson.json")){
			file.write(finalStr);
		}
		
		
		conn.close();
   }

}
