package stepDefinations;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	
	@After("@addPlaceAPI")
	public void afterScenario() {
		System.out.println("Execution completed and logs generated successfully!");
	}
	
	@Before("@addPlaceAPI")
    public void beforeScenario() {
		System.out.println("Execution Started!");
	}
	

}
