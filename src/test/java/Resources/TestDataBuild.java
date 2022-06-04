package Resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddLocation;
import pojo.Location;

public class TestDataBuild {
	
	public static AddLocation addPlacePayload( String name, String address, String language) {
		
		AddLocation objLoc 	= new AddLocation();
		Location myLoc 		= new Location();
		
		//Serialization
		myLoc.setLat("-38.383494");
		myLoc.setLng("33.427362");
		objLoc.setLocation(myLoc);		
		objLoc.setName(name);		
		objLoc.setAccuracy(50);		
		objLoc.setPhone_number("(+91) 983 893 3937");		
		objLoc.setAddress(address);
		
		List<String> myList 	= new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		objLoc.setTypes(myList);
		
		objLoc.setWebsite("http://google.com");		
		objLoc.setLanguage(language);
		
		return objLoc;
	}
	
	public static String deletePayload(String place_Id) {
		
		String request = "{\"place_id\":\""+place_Id+"\"}";
		return request;	
	}

}
