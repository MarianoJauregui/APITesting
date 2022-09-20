import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ExampleTest {
    private static final Logger LOGGER = LogManager.getLogger(ExampleTest.class);

    private static final String URL = "http://zippopotam.us/us/90210";
    @Test
    public void requestZipCode(){
        given().
                when().
                get(URL).
                then().assertThat()
                .body("places.'place name'", hasItem("Beverly Hills"))
                //.assertThat().statusCode(200)
                //.assertThat().contentType(ContentType.JSON)
                .log().body();
    }

    @Test
    public void testState(){
        given().when().get(URL).then().assertThat().body("places[0].state", equalTo("California"))
                .and().assertThat().body("'post code'", not(hasItem("7777")));
    }

    @Test
    public void testCountry(){
        given().when().get(URL).then().assertThat().body("country", equalTo("United States"))
                .and().assertThat().body("places[0].'state abbreviation'", equalTo("CA"));
    }


}
