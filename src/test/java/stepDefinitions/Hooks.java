package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@deletePlace")
    public void beforeScenario() throws IOException {
        //execute this when place id is null
        StepDefinition s = new StepDefinition();
        if (StepDefinition.place_id == null) {
            s.add_place_payload_with_and_and("Framework", "7807807809", "dumka");
            s.user_call_with_http_request("addPlaceAPI", "POST");
            s.verify_place_id_created_maps_to_using("Framework", "getPlaceAPI");
        }

    }
}
