package Util;
import static org.testng.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestUtil {
	
	 private static RestUtil RA = null;  
	 RestUtil(){}  
	   
	 public static RestUtil getInstance(){  
	   if (RA == null){ 
		   RA = new RestUtil(); 
	    }            
	  return RA;  
	 }  
	 
	 /**
	  * Prepare Request specification for request
	  * @param baseURI
	  * @param basePath
	  * @return
	 * @throws IOException 
	  */
	 public RequestSpecBuilder buildReq() {
		 
		Properties prop=new Properties();	
		
		try{
			FileReader reader=new FileReader("data/param.properties");    	  
			prop.load(reader);  
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 RequestSpecBuilder req=new RequestSpecBuilder();
		 req.setBaseUri(prop.getProperty("basePath"));
		 req.setBasePath(prop.getProperty("baseURI")); 
		 req.addParam("units", prop.getProperty("unit"));
		 req.addParam("appId", prop.getProperty("appId"));
		 
		 return req;
	 }
	 
	 /**
	  * Get request response for the request specification passed.
	  * @param reqSpec
	  * @return
	  */
	 public Response getResp(RequestSpecification reqSpec){		 
		return RestAssured.given().spec(reqSpec).get();		 
	 }
	 	  
	 /**
	  * Verify Response 
	  * @param resp
	  * @param respCode
	  */
	 public void verifyRespCode(Response resp, int respCode){
		 assertEquals(respCode, resp.getStatusCode());		 
	 }
	 	 
}
