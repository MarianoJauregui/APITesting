import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ExampleTest {

    @Test
    public void requestZipCode(){
        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().assertThat()
                .body("places[0].'place name'", equalTo("Beverly Hills"));
    }
}
