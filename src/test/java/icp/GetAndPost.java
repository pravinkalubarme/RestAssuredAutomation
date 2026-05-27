package icp;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.*;


public class GetAndPost {
	
	@Test
	public void getRequest() {
		baseURI = "https://reqres.in/api";
		Response response = 
		given().
			header("x-api-key","free_user_3EFvAUglrAwNUBCKUTnrSfTSFWQ").
		get("/users?page=2");
		
		response.then().statusCode(200);
		String email = response.jsonPath().getString("data[0].email");
		System.out.println("Email: "+email);

	}
	@Test
	public void postRequest() {
		baseURI = "https://reqres.in/api";
     	JSONObject requestParams = new JSONObject();
		requestParams.put("name", "John Doe");
		requestParams.put("job", "Software Engineer");
		
		Response res=given().
			header("x-api-key","free_user_3EFvAUglrAwNUBCKUTnrSfTSFWQ").
			header("Content-Type","application/json").
			body(requestParams.toJSONString()).
		post("/users");
		
		System.out.println("Status Code: "+res.getStatusCode());
		System.out.println("Response: "+res.jsonPath().getString("name"));
	}

}
