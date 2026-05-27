package icp;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestsExamples {

	@Test
	public void TC1() {
		System.out.println("This is GET request");
		Response response = RestAssured.given().header("x-api-key", "free_user_3EFvAUglrAwNUBCKUTnrSfTSFWQ").get("https://reqres.in/api/users?page=2");
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
