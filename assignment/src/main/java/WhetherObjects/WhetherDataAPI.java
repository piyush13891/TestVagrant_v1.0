package WhetherObjects;

public class WhetherDataAPI {

	private static float temperatureFahrenheit;
	private static float temperatureCelsius;
	private static String condition;
	
	public float getTempCelsius (){
		return temperatureCelsius;
	}
	
	public void setTempCelsius (float temperatureCelsius ){
		WhetherDataAPI.temperatureCelsius = temperatureCelsius;
	}
	
	
	public float getTempFahrenheit(){
		return temperatureFahrenheit;
	}
	
	public void setTempFahrenheit  (float temperatureFahrenheit){
		WhetherDataAPI.temperatureFahrenheit =temperatureFahrenheit;
	}
	
	public String getCondition(){
		return condition;
	}
	
	public void setCondition  (String condition){
		WhetherDataAPI.condition =condition;
	}
}
