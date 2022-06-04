package Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import ReusableMethods.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	//Use single instance during the entire execution.Hence static
	public static RequestSpecification 	req;
	
	public RequestSpecification requestSpecification() throws IOException {
				 
	   //Adding the Logging capabilities
	   //Append the logs of the next run. Hence validate if(req==null)
		
		if(req==null) {
			
		 PrintStream objLog 			= new PrintStream(new FileOutputStream("apiLogs.txt"));
		 req       					    = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL"))
							               .addQueryParam("key", "qaclick123")
							               .addFilter(RequestLoggingFilter.logRequestTo(objLog))
							               .addFilter(ResponseLoggingFilter.logResponseTo(objLog))
							 			   .setContentType(ContentType.JSON).build();
		 return req;	} 
		
		 return req;		
		
	}
	
	
	//Implemented to extract the property value from global.properties file
	
	public String getGlobalValue(String keyName) throws IOException {
		
		Properties prop = new Properties();
		//Reading the file 
		FileInputStream fin = new FileInputStream("C:\\Users\\SRISHTI\\eclipse-workspace\\APIFramework\\src\\test\\java\\Resources\\global.properties");
		//Need to integrate the properties file object the fileInputStream object
		prop.load(fin);
		String value = prop.getProperty(keyName);
		return value;		
		
	}
	
	
	//Implemented to set the property value in global.properties file
	
		public void setGlobalValue(String keyName, String keyValue) throws IOException {
			
			Properties prop = new Properties();
			prop.setProperty(keyName, keyValue);
			//Writing to file 
			File file = new File("C:\\Users\\SRISHTI\\eclipse-workspace\\APIFramework\\src\\test\\java\\Resources\\global.properties");
			FileOutputStream fout = new FileOutputStream(file);
			//Need to integrate the properties file object the fileInputStream object
			prop.store(fout,null);
			System.out.println(keyName + "<-Key successfully added");
			fout.close();
		}
	
	
	
	
	public String returnJsonKey_Value( Response objResponse, String key) {
		
		JsonPath js = ReusableMethods.rawToJson(objResponse);
		String value = js.getString(key);
		return value;
		
	}

}



//Build the common request using specBuilder		
//Build the common request using RequestBuilder
 /**req       			 =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		                .addQueryParam("key", "qaclick123")
		 			    .setContentType(ContentType.JSON).build(); 
		 			    **/
