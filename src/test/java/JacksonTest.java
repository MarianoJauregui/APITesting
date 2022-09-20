import dataEntities.Location;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class JacksonTest {


    @Test
    public void requestZipCode(){
        Location location =
                given().when().get("http://api.zippopotam.us/us/90210").as(Location.class);

        Assert.assertEquals("Beverly Hills", location.getPlaces().get(0).getPlaceName());
    }

    @Test
    public void sendZipCodeCheckStatusCode(){
        Location location = new Location();
        location.setCountry("Netherlands");

        given().contentType(ContentType.JSON).body(location).log().body()
                .when().post("http://localhost:9876/lv/1050")
                .then().assertThat().statusCode(200);
    }
}
