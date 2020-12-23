package com.TestVagrant.assignment;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import PageObjects.NDTVWhetherPage;
import Util.Common;
import Util.RestUtil;
import WhetherObjects.WhetherDataAPI;
import WhetherObjects.WhetherDataUI;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class IntegrationTests {
	
	
	WebDriver driver;	
	NDTVWhetherPage whetherPageNDTV;
	RestUtil RA;
	RequestSpecBuilder reqSpec;
	Common common;
	
	@BeforeTest
	public void setup(){
		
		//Initialization
		RA = RestUtil.getInstance();	
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		driver=new FirefoxDriver();
		common=new Common();
	}
	
	@AfterTest
	public void TearDown(){
		driver.quit();		
	}
	
	@Test
	@Parameters({"city"})  
	public void temperatureToleranceTest(String cityName) {
	  	
		//Get Whether data using API
		reqSpec = RA.buildReq();
		reqSpec.addParam("q", cityName);
		
		Response resp = RA.getResp(reqSpec.build());
					
		WhetherDataAPI whtAPI= new WhetherDataAPI();
		whtAPI.setTempCelsius(common.getTemperatureAPI(resp.getBody().asString()));			
						
		//Get whether data using UI 
		driver.get("https://social.ndtv.com/static/Weather/report/?pfrom=home-topsubnavigation");
		
		whetherPageNDTV = new NDTVWhetherPage(driver);		
		whetherPageNDTV.pinYourCity(cityName);
		
		assertTrue(whetherPageNDTV.isPinnedCityExistsOnMap(cityName));
				
		whetherPageNDTV.clickOnPinnedCity(cityName);
		whetherPageNDTV.VerifyTemperaturePopUp(cityName);
		
		WhetherDataUI whtUI= new WhetherDataUI();
		whtUI.setTempCelsius(whetherPageNDTV.getTemperature("Degrees"));
		
		float temperatureFahrenheit = whetherPageNDTV.getTemperature("Fahrenheit");
		System.out.println("Temperature in Fahrenheit" + temperatureFahrenheit);		

		//Assert Temperatures
		common.compareTemperature(whtAPI.getTempCelsius(),whtUI.getTempCelsius());	
	}
}
