import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ExampleTest {
    private static final Logger LOGGER = LogManager.getLogger(ExampleTest.class);

    @Test
    public void requestZipCode(){
        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().assertThat()
                .body("places[0].'place name'", equalTo("Beverly Hills"))
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON);
    }

}
