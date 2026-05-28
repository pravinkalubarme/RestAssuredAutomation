package icp;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {

	@Test
	public void getCaptchId() {
		
		baseURI = "http://localhost:3000";
		Response response = given().post("/api/captcha/create");
		Assert.assertEquals(response.getStatusCode(), 200);
		String captchaId = response.jsonPath().getString("captchaId");
		getCaptchaValue(captchaId);

	}
	
	@Test
	public String getCaptchaValue(String captchaId) {
		
		baseURI = "http://localhost:3000";
		Response response = given().get("/api/captcha/"+captchaId);
		Assert.assertEquals(response.getStatusCode(), 200);
		response.then().
					statusCode(200).
					body("value", notNullValue());
		String captchValue = response.jsonPath().getString("value");
		System.out.println("Captcha Value: "+captchValue);
		return captchValue;
	}
	
	@Test
	public void login() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:3000");
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("Password123!");
		String captcha = driver.findElement(By.id("captchaBox")).getText();
		driver.findElement(By.id("captchaInput")).sendKeys(captcha);
		driver.findElement(By.id("loginBtn")).click();
		String toastMessage = driver.findElement(By.id("message")).getText();
		System.out.println("Toast Message: "+toastMessage);
		driver.quit();
		System.out.println("Login successful with captcha: "+captcha);
	}
}