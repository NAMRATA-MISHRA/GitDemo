package day1;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

/*given()
    content type, set cookies, add auth, add parameter, set headers info etc...
when()
    get, post, put, delete
then()
	validate status code, extract response, extract headers cookies & response body....
*/

public class HTTPRequests {

	int id;
	
	@Test(priority=1)
	void getUsers()
	{
		given()
			
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
	}
	
	@Test(priority=2)
	void createUser()
	{
		HashMap data = new HashMap();
		data.put("name","namrata");
		data.put("job", "tester");
		
		id=given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
//		.then()
//			.statusCode(201)
//			.log().all();
	}
	
	@Test(priority=3,dependsOnMethods= {"createUser"})
	void updateUser()
	{
		HashMap data = new HashMap();
		data.put("name","Tomioka");
		data.put("job", "QA");
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.put("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority=4)
	void deleteUser(){
		{
			given()
			
			.when()
				.delete("https://reqres.in/api/users/\"+id")
			.then()
				.statusCode(204)
				.log().all();
		}
	}
	
}
