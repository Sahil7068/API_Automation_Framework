package resources;

import pojo.AddPlace;
import pojo.LongLat;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayload(String name, String phone_number, String address){

        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setAddress(address);
        addPlace.setLanguage("French-IN");
        addPlace.setName(name);
        addPlace.setPhone_number(phone_number);
        addPlace.setWebsite("http://google.com");

        List<String> types = new ArrayList<>();
        types.add("shoe park");
        types.add("shop");
        addPlace.setTypes(types);

        LongLat longLat = new LongLat();
        longLat.setLat(-38.383494);
        longLat.setLng(33.427362);
        addPlace.setLocation(longLat);

        return addPlace;


    }
}
