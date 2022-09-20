
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class ParametrizedTests {
    private static RequestSpecification requestSpecification;

    @BeforeClass
    public static void createRequest() {
        requestSpecification = new RequestSpecBuilder().setBaseUri("http://api.zippopotam.us").build();
    }

    @DataProvider(name = "Test 1")
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][]{
                { "us", "90210", "Beverly Hills"},
                { "us", "12345", "Schenectady"},
                { "ca", "B2R", "Waverley"},
                {"AR", "1601", "ISLA MARTIN GARCIA"}
        };
    }

    @Test(dataProvider = "Test 1")
    public void requestZipCode(String countryCode, String zipCode, String expectedPlaceName){
        given().pathParam("countryCode", countryCode)
                        .pathParam("zipCode", zipCode)
                                .when().
                get("http://zippopotam.us/{countryCode}/{zipCode}").
                then().assertThat()
                .body("places[0].'place name'", equalTo(expectedPlaceName));
                //.assertThat().statusCode(200)
                //.assertThat().contentType(ContentType.JSON)
                //.log().body();
    }

    @Test
    public void requestZipCode(){
        given().spec(requestSpecification).when().get("us/90210").then().assertThat().statusCode(200);
    }

    @Test
    public void testExtractMethod(){
        String placeName = given().spec(requestSpecification).when().get("http://zippopotam.us/us/90210")
                .then().extract().path("places[0]. 'place name'");

        Assert.assertEquals(placeName, "Beverly Hills");
    }

}
