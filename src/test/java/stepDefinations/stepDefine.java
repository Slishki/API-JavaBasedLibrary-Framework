package stepDefinations;

	import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
	import java.util.List;

import Resources.APIResource;
import Resources.TestDataBuild;
import Resources.Utils;
import ReusableMethods.ReusableMethods;
	import io.cucumber.java.en.Given;
	import io.cucumber.java.en.Then;
	import io.cucumber.java.en.When;
	import io.restassured.RestAssured;
	import io.restassured.builder.RequestSpecBuilder;
	import io.restassured.builder.ResponseSpecBuilder;
	import io.restassured.http.ContentType;
	import io.restassured.path.json.JsonPath;
	import io.restassured.response.Response;
	import io.restassured.specification.RequestSpecification;
	import io.restassured.specification.ResponseSpecification;
	import pojo.*;
	import static org.junit.Assert.*;

	public class stepDefine extends Utils {
		
			RequestSpecification 			myrequest;
			ResponseSpecification 			res;
			Response 						objResponse;
			JsonPath 						js; 
			//It will help to refer in the next run of the scenario
			static String 					place_Id;
		
			/**@Given("{string} request Payload")
			public void request_Payload(String requestAPI) throws IOException {					   
				myrequest 			 = given().spec(requestSpecification()).body(TestDataBuild.addPlacePayload());			         		
																	         }**/
		
			@Given("{string} request Payload with {string} , {string}, {string}")
			public void request_Payload_with(String apIName, String name, String address, String language) throws IOException {
				myrequest 			  = given().spec(requestSpecification()).body(TestDataBuild.addPlacePayload(name,address,language));
			}
		
			
			@When("user calls {string} API with {string} Http request method")
			public void user_calls_API_with_Http_request_method(String resourceName, String methodName) throws IOException {
				
				 //Build the common response using ResponseBuilder
		         // res 				= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		    	 
		         
				//Enum class to declare the resourceNames
				
				APIResource resourceAPI = APIResource.valueOf(resourceName);
				//Conditional statement to control Http methods
				if(methodName.equalsIgnoreCase("POST")) 
		          {	objResponse 		= myrequest.when().post(resourceAPI.getResource());}				
				
			    else if (methodName.equalsIgnoreCase("GET"))
		         {  
			    	objResponse 		  = given().spec(requestSpecification())
			    			               .queryParam("place_id",place_Id).when()
			    			               .get(resourceAPI.getResource());} 
						          		          
		        else if (methodName.equalsIgnoreCase("DELETE"))
		         {  objResponse 		  = given().spec(requestSpecification()).body(TestDataBuild.deletePayload(place_Id))
		                                   .when().post(resourceAPI.getResource());}		
		       
			 }

		
			@Then("the API call has Http status code {int}")
			public void the_API_call_has_Http_status_code(int statusCode) {
				
				place_Id = returnJsonKey_Value(objResponse,"place_id");
				assertEquals(objResponse.getStatusCode(),statusCode);
			 }
		
			@Then("the {string} in response body is {string}")
			public void the_in_response_body_is(String key, String value) throws IOException {
			   				  
				 String keyValue = returnJsonKey_Value(objResponse,key);
				  //JUnit Assertions
				  //import static org.junit.Assert.*;
				 assertEquals(keyValue,value);			
			}
			
			
			//And validate the "getPlaceAPI" locations "<name>" using the place_Id
			@Then("validate the {string} locations {string} using the place_Id")
			public void validate_the_locations_using_the_place_Id(String resourceName, String locName) {
				
			   //JUnit Assertions
			   //import static org.junit.Assert.*;					
			    String actualName = returnJsonKey_Value(objResponse,"name");
			    assertEquals(actualName,locName);

			}
			
			//And "deletPlaceAPI" to delete the location using place_Id
			@Then("{string} to delete the location using place_Id")
			public void to_delete_the_location_using_place_Id(String resourceName) {
			    
				String status = returnJsonKey_Value(objResponse,"status");
				assertEquals(status,"OK");			
			}
	
	
 }