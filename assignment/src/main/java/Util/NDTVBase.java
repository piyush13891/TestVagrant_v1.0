package Util;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;

public class NDTVBase {

	public NDTVBase(WebDriver driver){
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);		
		driver.manage().window().maximize();
	}	
}
