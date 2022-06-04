package ReusableMethods;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods {

	//To convert the raw data response to JSon format
	public static JsonPath rawToJson(Response response) {
		
        String responsAsString = response.asString();
        JsonPath js = new JsonPath(responsAsString);
		return js;
	}
	
}
