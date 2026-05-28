package icp;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class PutAndPatch {

	@Test
	public void putRequest() {
		baseURI = "https://reqres.in/api";
     	JSONObject requestParams = new JSONObject();
		requestParams.put("name", "John Doe");
		requestParams.put("job", "Software Engineer");
		
		Response response=given().
			header("x-api-key","free_user_3EFvAUglrAwNUBCKUTnrSfTSFWQ").
			header("Content-Type","application/json").
			body(requestParams.toJSONString()).
		    put("/users/2");
		response.then().statusCode(200);
		System.out.println("Response: "+response.jsonPath().getString("name"));
		//response.then().log().all();
		System.out.println("putRequest completed successfully.");
		
	}
	
	@Test
	public void patchRequest() {
		baseURI = "https://reqres.in/api";
     	JSONObject requestParams = new JSONObject();
		requestParams.put("name", "John Lennon");
		requestParams.put("job", "QA Engineer");
		
		Response response=given().
			header("x-api-key","free_user_3EFvAUglrAwNUBCKUTnrSfTSFWQ").
			header("Content-Type","application/json").
			body(requestParams.toJSONString()).
		    patch("/users/2");
		response.then().statusCode(200);
		System.out.println("Response: "+response.jsonPath().getString("name"));
		//response.then().log().all();
		System.out.println("patchRequest completed successfully.");
		
	}
	
	@Test
	public void deleteRequest() {
		baseURI = "https://reqres.in/api";	
		Response response=given().
			header("x-api-key","free_user_3EFvAUglrAwNUBCKUTnrSfTSFWQ").
		    delete("/users/2");
		response.then().statusCode(204);
		//response.then().log().all();
		System.out.println("deleteRequest completed successfully.");
		}
}
