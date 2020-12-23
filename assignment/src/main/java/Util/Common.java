package Util;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import io.restassured.path.json.JsonPath;

public class Common {

	public float getTemperatureAPI(String resp){
		
		JsonPath jsonPath = new JsonPath(resp);
		return jsonPath.getFloat("main.temp");		
	}
	
	public void compareTemperature(float t1, float t2){
		
		FileReader reader;
		float tolerance = 0;
		
		try {
			reader = new FileReader("data/param.properties");
			Properties prop=new Properties();  
			prop.load(reader);
			tolerance = Float.parseFloat(prop.getProperty("tolerance"));
					
			if(Math.abs(t1 - t2) <= tolerance){
				assertTrue(true);
			}
			else
			{	fail("Temperature Comparision failed. Tolerance considered - " + tolerance);
				throw new Exception("Temperature are not equivalent after tolerance consideration.");
			}		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
				 System.out.println(e);
		}
	}
}
