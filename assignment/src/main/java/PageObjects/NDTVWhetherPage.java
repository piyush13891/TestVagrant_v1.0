package PageObjects;

import static org.testng.Assert.assertTrue;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Util.NDTVBase;

public class NDTVWhetherPage extends NDTVBase{

	WebDriver driver;	
	WebDriverWait wait;
	
	//Elements 
	@FindBy(className="leaflet-popup-content")
	WebElement cityTempInfoPopUpLeaflet;
	
	private WebElement pinCityName;
	private List<WebElement> citiesOnMapList;

	public NDTVWhetherPage(WebDriver driver){
		super(driver);
		this.driver=driver;
		wait=new WebDriverWait(driver, 10);
		PageFactory.initElements(driver, this);
	}
	
	//Methods
	
	//Pin your city if not pinned 
	public void pinYourCity(String cityName){
				
		pinCityName = wait.until(ExpectedConditions.elementToBeClickable(By.id(cityName)));
		
		if(!pinCityName.isSelected()){
			pinCityName.click();
		}
	}
	
	//Validate if city is on Map
	public Boolean isPinnedCityExistsOnMap(String cityName){
		
		citiesOnMapList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("cityText")));		
		for(WebElement we: citiesOnMapList)
		{
			if(we.getText().equals(cityName)){
				return true;
			}
		}				
		return false;		
	}
	
	/**
	 * Click on the city pinned on MAP
	 * @param cityName
	 */
	public void clickOnPinnedCity(String cityName){
		
		pinCityName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='cityText'][text()='" + cityName + "']")));
		pinCityName.click();
	}
	
	/**
	 * Verify if Whether condition details pop-up is displayed on Map
	 * @param cityName
	 */
	public void VerifyTemperaturePopUp(String cityName){	
		
		assertTrue(cityTempInfoPopUpLeaflet.isDisplayed(),"Verify that pop up is displayed.");		
		assertTrue(cityTempInfoPopUpLeaflet.findElement(
				By.xpath("//span[contains(text(),'" + cityName + "')]")).isDisplayed(),
				"Verify that pop-up is Opened for the clicked City name.");
	}
	
	/**
	 * Get Temperature from the Map 
	 * @return
	 */
	public float getTemperature(String tempUnit){
        
		String strTemp = cityTempInfoPopUpLeaflet.findElement(
				By.xpath("//b[contains(text(),'Temp in " + tempUnit + "')]")).getText();			
		return Float.parseFloat(strTemp.split(":")[1].trim());
    }
		
}
