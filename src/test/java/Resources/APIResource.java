package Resources;

public enum APIResource {
	
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletPlaceAPI("/maps/api/place/delete/json");
	private String resource;
	
	//Constructor
	APIResource(String resource){
		
		this.resource=resource;
	}
	
	//Method defined to return the resource value

	public String getResource() {
		
		return resource;
	}
	
	

}
