#Google Maps Add API (Http Method-POST)

#Test Scenario Description
Feature: Validate the Place APIs 

#Test Case Description
#Scenario: Verfiy addPlace functionality  

#Given "addPlace" request Payload
#With regular expression we can reuse the BDD statement
#When user calls "addPlace" API with POST Http request
#Then the API call has Http status code 200
#And the "status" in response body is "OK"
#And the "scope" in response body is "APP"

@addPlaceAPI @Regression
Scenario Outline: Verfiy addPlace functionality  

Given "addPlace" request Payload with "<name>" , "<address>", "<language>"
#With regular expression we can reuse the BDD statement
When user calls "addPlaceAPI" API with "POST" Http request method
Then the API call has Http status code 200
And the "status" in response body is "OK"
And the "scope" in response body is "APP"
When user calls "getPlaceAPI" API with "GET" Http request method
And validate the "getPlaceAPI" locations "<name>" using the place_Id
When user calls "deletPlaceAPI" API with "DELETE" Http request method
And "deletPlaceAPI" to delete the location using place_Id

Examples:

 |    name		|			address											|			language		|
 |		TestData|	30, side layout, california 09	|		 French-IN		|