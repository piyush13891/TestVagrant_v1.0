package WhetherObjects;

public class WhetherDataUI {

	private static float temperatureFahrenheit;
	private static float temperatureCelsius;
	private static String condition;
	
	public float getTempCelsius (){
		return temperatureCelsius;
	}
	
	public void setTempCelsius (float temperatureCelsius ){
		WhetherDataUI.temperatureCelsius = temperatureCelsius;
	}
	
	
	public float getTempFahrenheit(){
		return temperatureFahrenheit;
	}
	
	public void setTempFahrenheit  (float temperatureFahrenheit){
		WhetherDataUI.temperatureFahrenheit =temperatureFahrenheit;
	}
	
	public String getCondition(){
		return condition;
	}
	
	public void setCondition  (String condition){
		WhetherDataUI.condition =condition;
	}
}
